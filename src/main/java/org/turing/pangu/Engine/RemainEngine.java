package org.turing.pangu.Engine;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.quartz.simpl.RAMJobStore;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.Platform;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.service.PlatformService;
import org.turing.pangu.service.PlatformServiceImpl;
import org.turing.pangu.utils.DateUtils;
import org.turing.pangu.utils.FileUtil;
import org.turing.pangu.utils.JsonUtils;
import org.turing.pangu.utils.RandomUtils;


public class RemainEngine {
	private static RemainEngine mInstance = new RemainEngine();
	private String remainRootPath = PropertyEngine.getProperty("download.root").toString(); // 留存文件根目录
	private String remainDownload = PropertyEngine.getProperty("download.remain").toString(); // 下载留存文件
	private int remainCount = 200;
	private int remainAllCount = 400;
	
	private PlatformService platformService;
	private AppService appService;
	private DeviceService deviceService;
	
	public void setService(PlatformService platformService,AppService appService,DeviceService deviceService){
		this.platformService = platformService;
		this.appService = appService;
		this.deviceService = deviceService;
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
			device.setIsRemainIp(1); // 查询经过留存IP验证的数据
			List<Device> deviceList = deviceService.selectCanRemainData(device);
			
			if( null == deviceList || deviceList.size() == 0 )
				return false;
			
			Date tomorrowMorning = DateUtils.getTomorrowMorning();
			
			String yestodayRemain = DateFormat(todayMorning) + "_" + app.getId() + "_" + app.getPackageName() + ".json";
			String todayRemain = DateFormat(tomorrowMorning) + "_" + app.getId() + "_" + app.getPackageName() + ".json";
			
			String yesPath = remainRootPath + remainDownload + app.getUserId() + "/" + app.getPlatformId() + "/" + yestodayRemain;
			String tomPath = remainRootPath + remainDownload + app.getUserId() + "/" + app.getPlatformId() + "/" + todayRemain;
			
			if(FileUtil.isFileExist(yesPath))
			{
				File file = new File(yesPath);
				try {
					String json = FileUtils.readFileToString(file);
					deviceRemainList = JsonUtils.toObject(json, new ArrayList<Device>().getClass());
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
			}

			
			//FileUtil.
			
			//String pfPath = remainRoot + pf.getName();
			// 创建目录
			//FileUtils.c
		}
		return false;
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
