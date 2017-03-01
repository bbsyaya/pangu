package org.turing.pangu.engine;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.Platform;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.service.PlatformService;
import org.turing.pangu.utils.DateUtils;
import org.turing.pangu.utils.FileUtil;
import org.turing.pangu.utils.JsonUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
/*
public class RemainEngine implements EngineListen{
	private static final Logger logger = Logger.getLogger(RemainEngine.class);
	private static RemainEngine mInstance = new RemainEngine();
	public static String remainRootPath = PropertyEngine.getProperty(
			"download.root").toString(); // 留存文件根目录
	public static String remainDownload = PropertyEngine.getProperty(
			"download.remain").toString(); // 下载留存文件
	private int remainCount = 100;
	private int remainAllCount = 200;
	private PlatformService platformService;
	private AppService appService;
	private DeviceService deviceService;

	public static RemainEngine getInstance() {
		if (null == mInstance)
			mInstance = new RemainEngine();
		return mInstance;
	}

	private Date reduceDate(Date date, long day) {
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
		time -= day; // 减去得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}

	private Date addDate(Date date, long day) {
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
		time += day; // 减去得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}

	public static String DateFormat(Date date) {
		// 给定模式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(date);
		return s;
	}

	// -- 产生留存文件
	// -- 400份数据,各取200
	public String getAppRemainFilePath(Date date, App app) {
		String path = DateFormat(date) + "_" + app.getId() + "_"
				+ app.getPackageName() + ".json";
		path = remainRootPath + remainDownload + app.getUserId() + "/"
				+ app.getPlatformId() + "/" + path;
		return path;
	}

	public boolean generateRemainFile() {
		List<Device> deviceRemainList = null;
		List<Platform> list = PlatformEngine.getInstance().getPlatformList();
		List<App> appList = AppEngine.getInstance().getAppList();
		if (null == list || null == appList) {
			list = PlatformEngine.getInstance().getPlatformList();
			appList = AppEngine.getInstance().getAppList();
		}
		if (null == list || list.size() == 0 || null == appList
				|| appList.size() == 0)
			return false;

		// 为每个app生成留存文件
		for (App app : appList) {
			Device device = new Device();
			Date yestodayMorning = DateUtils.getYesterdayMorning();
			Date yestodayNight = DateUtils.getYesterdayNight();
			Date todayMorning = DateUtils.getTimesMorning();
			Date todayNight = DateUtils.getTimesNight();
			device.setCreateDate(yestodayMorning);
			device.setUpdateDate(yestodayNight);
			device.setAppId(app.getId());
			device.setIsWhiteIp(1);
			List<Device> deviceList = deviceService.selectCanRemainData(device);

			if (null == deviceList || deviceList.size() == 0)
				return false;
			String yesPath = getAppRemainFilePath(yestodayMorning, app);
			String tomPath = getAppRemainFilePath(todayMorning, app);

			if (FileUtil.isFileExist(yesPath)) {
				File file = new File(yesPath);
				try {
					String json = FileUtils.readFileToString(file);
					// -- ali关键转换
					deviceRemainList = JSON.parseObject(json,
							new TypeReference<ArrayList<Device>>() {
							});
					if (deviceRemainList.size() > remainCount) {
						removeList(deviceRemainList, remainCount);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (deviceList.size() > remainCount) {
				removeList(deviceList, remainCount); // 从中选200个
			}
			List<Device> newlist = mergeList(deviceRemainList, deviceList);
			String newjson = JsonUtils.toJson(newlist);

			if (!FileUtil.isFileExist(tomPath)) {
				FileUtil.makeDirectory(tomPath);
				File file = new File(tomPath);
				try {
					FileUtils.write(file, newjson, "UTF-8");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	private void removeList(List<Device> list, int needCount) {
		int tmp = list.size() - needCount;
		for (int index = 0; index < tmp; index++) {
			int random = (int) (Math.random() * list.size());
			list.remove(random);
		}
	}

	// 合并两天的留存
	private List<Device> mergeList(List<Device> oldlist, List<Device> newlist) {
		if (null == oldlist || oldlist.size() == 0 || null == newlist
				|| newlist.size() == 0)
			return newlist;

		for (Device d : oldlist) {
			int random = (int) (Math.random() * newlist.size());
			newlist.add(random, d);
		}
		return newlist;
	}

	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
		this.platformService = platformService
		this.appService = appService;
		this.deviceService = deviceService;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}*/
