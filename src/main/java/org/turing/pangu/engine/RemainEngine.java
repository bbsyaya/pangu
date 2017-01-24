package org.turing.pangu.engine;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.quartz.simpl.RAMJobStore;
import org.turing.pangu.controller.phone.MobileReportController;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.Platform;
import org.turing.pangu.model.RemainData;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.service.PlatformService;
import org.turing.pangu.service.PlatformServiceImpl;
import org.turing.pangu.service.RemainDataService;
import org.turing.pangu.utils.DateUtils;
import org.turing.pangu.utils.FileUtil;
import org.turing.pangu.utils.HttpUtil;
import org.turing.pangu.utils.JsonUtils;
import org.turing.pangu.utils.RandomUtils;
import org.turing.pangu.utils.SpringContextHolder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;


public class RemainEngine {
	private static final Logger logger = Logger.getLogger(RemainEngine.class);
	private static RemainEngine mInstance = new RemainEngine();
	private String remainRootPath = PropertyEngine.getProperty("download.root").toString(); // 留存文件根目录
	private String remainDownload = PropertyEngine.getProperty("download.remain").toString(); // 下载留存文件
	private int remainCount = 200;
	private int remainAllCount = 400;
	
	private PlatformService platformService;
	private AppService appService;
	private DeviceService deviceService;
	private RemainDataService remainDataService;
	
	public void setService(PlatformService platformService,AppService appService,DeviceService deviceService,RemainDataService remainDataService){
		
		//platformService = SpringContextHolder.getBean(PlatformServiceImpl.class);
		
		this.platformService = platformService;
		this.appService = appService;
		this.deviceService = deviceService;
		this.remainDataService = remainDataService;
	}
	public static RemainEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new RemainEngine();
		return mInstance;
	}
	private Date reduceDate(Date date,long day){
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		time-=day; // 减去得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}
	
	private Date addDate(Date date,long day){
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		time+=day; // 减去得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}
	private String DateFormat(Date date){
		// 给定模式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // public final String format(Date date)
        String s = sdf.format(date);
        System.out.println(s);
        return s;
	}
	// -- 产生留存文件
	// -- 400份数据,各取200
	public boolean generateRemainFile()
	{
		List<Device> deviceRemainList = null;
		List<Platform> list = PlatformEngine.getInstance().getList();
		List<App> appList = AppEngine.getInstance().getList();
		if(null == list || null == appList){
			list = platformService.selectAll();
			PlatformEngine.getInstance().setList(list);
			
			appList = appService.selectAll();
			AppEngine.getInstance().setList(appList);
		}
		if(null == list || list.size() == 0 || null == appList || appList.size() == 0 )
			return false;
		
		// 为每个app生成留存文件
		for(App app : appList){
			Device device = new Device();
			Date todayMorning = DateUtils.getTimesMorning();
			Date todayNight = DateUtils.getTimesNight();
			device.setCreateDate(todayMorning);
			device.setUpdateDate(todayNight);
			device.setAppId(app.getId());
			device.setIsWhiteIp(1);
			List<Device> deviceList = deviceService.selectCanRemainData(device);
			
			if( null == deviceList || deviceList.size() == 0 )
				return false;
			
			Date tomorrowMorning = DateUtils.getTomorrowMorning();
			
			String yestodayRemain = DateFormat(todayMorning) + "_" + app.getId() + "_" + app.getPackageName() + ".json";
			String todayRemain = DateFormat(tomorrowMorning) + "_" + app.getId() + "_" + app.getPackageName() + ".json";
			yestodayRemain = remainDownload + app.getUserId() + "/" + app.getPlatformId() + "/" + yestodayRemain;
			todayRemain = remainDownload + app.getUserId() + "/" + app.getPlatformId() + "/" + todayRemain;
			
			String yesPath = remainRootPath + yestodayRemain;
			String tomPath = remainRootPath + todayRemain;
			
			if(FileUtil.isFileExist(yesPath))
			{
				File file = new File(yesPath);
				try {
					String json = FileUtils.readFileToString(file);
					// -- ali关键转换
					deviceRemainList = JSON.parseObject(json, new TypeReference<ArrayList<Device>>(){});					
					if(deviceRemainList.size() > remainCount ){
						removeList(deviceRemainList,remainCount);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(deviceList.size() > remainCount)
			{
				removeList(deviceList,remainCount); // 从中选200个
			}
			List<Device> newlist = mergeList(deviceRemainList,deviceList);
			String newjson = JsonUtils.toJson(newlist);
			
			if(!FileUtil.isFileExist(tomPath)){
				FileUtil.makeDirectory(tomPath);
				File file = new File(tomPath);
				try {
					FileUtils.write(file, newjson, "UTF-8");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			saveRemainData(app,todayRemain);
		}
		return false;
	}
	public void updateRemainData()
	{
		List<App> appList = AppEngine.getInstance().getList();
		if(null == appList){	
			if(null == platformService){
				HttpUtil.get("http://pangu.u-app.cn/pc/index.pangu"); // 以这种方式赋值
			}
			appList = appService.selectAll();
			AppEngine.getInstance().setList(appList);
		}
		if(null == appList || appList.size() == 0 )
			return;
		
		// 为每个app生成留存文件
		for(App app : appList){
			saveRemainData(app,null);
		}
		
	}
	// 保存运营数据
	private boolean saveRemainData(App app,String remainPath)
	{
		RemainData data = new RemainData();
		Date todayMorning = DateUtils.getTimesMorning();
		Date todayNight = DateUtils.getTimesNight();
		
		Device dev = new Device();
		dev.setAppId(app.getId());
		dev.setIsActived(0);
		Integer noneActived = deviceService.selectCountByTimeSpan(dev);
		
		
		dev.setIsActived(1);
		Integer actived = deviceService.selectCountByTimeSpan(dev);
		
		dev.setIsActived(null);
		
		dev.setIsRemain(0);
		Integer noneRemain = deviceService.selectCountByTimeSpan(dev);
		//-----------------------------
		dev.setIsRemain(1);
		Integer remain = deviceService.selectCountByTimeSpan(dev);
		//------------------------------
		dev.setIsRemain(1);
		dev.setIsActived(1);
		Integer remain_active = deviceService.selectCountByTimeSpan(dev);
		
		data.setAppId(app.getId());
		data.setCreateDate(new Date());
		if(null != remainPath){
			data.setRemainPath(remainPath);
		}
		data.setActive(actived.longValue());
		data.setInactive(noneActived.longValue());
		data.setRemain(remain.longValue());
		data.setUnremain(noneRemain.longValue());
		data.setRemainActive(remain_active.longValue());
		
		data.setCreateDate(todayMorning);
		data.setUpdateDate(todayNight);
		
		List<RemainData> list = remainDataService.getRemainData(data);
		data.setUpdateDate(new Date());
		if(null == list || list.size() == 0 ){
			remainDataService.insert(data);
			return true;
		}
		if(list.size() > 1){
			logger.error("saveRemainData" + "今日留存数据大于1条是不可能的");
			return false;
		}
		data.setId(list.get(0).getId());
		if(null == remainPath){
			data.setRemainPath(list.get(0).getRemainPath());
		}
		remainDataService.update(data);
		return true;
		
	}
	private void removeList(List<Device> list,int needCount){
		int tmp = list.size() - needCount;
		for(int index = 0; index < tmp;index++)
		{
			int random = (int)(Math.random()*list.size());
			list.remove(random);
		}
	}
	// 合并两天的留存
	private List<Device> mergeList(List<Device> oldlist,List<Device> newlist){
		if( null == oldlist || oldlist.size() == 0 || null == newlist || newlist.size() == 0 )
			return newlist;
		
		for(Device d:oldlist){
			int random = (int)(Math.random()*newlist.size());
			newlist.add(random, d);
		}
		return 	newlist;
	}
}
