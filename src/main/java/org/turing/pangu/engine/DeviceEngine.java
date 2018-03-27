package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.controller.phone.request.ReportReq;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.DeviceFromNet;
import org.turing.pangu.phone.ChangeDeviceInfo;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.service.DeviceServiceImpl;
import org.turing.pangu.utils.DateUtils;

import com.alibaba.fastjson.JSON;

public class DeviceEngine implements EngineListen{
	private static final Logger logger = Logger.getLogger(DeviceEngine.class);
	private static DeviceEngine mInstance = new DeviceEngine();
	private List<Device> deviceReportListCache1 = new ArrayList<Device>(); // 用于保存上报信息的两个缓存
	private List<Device> deviceReportListCache2 = new ArrayList<Device>();

	private int usedWhichList = 1; 
	private DeviceService deviceService;
	public static DeviceEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new DeviceEngine();
		return mInstance;
	}
	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
		for(BaseService service :serviceList){
			if(service instanceof DeviceServiceImpl ){
				this.deviceService = (DeviceService)service;
			}
		}
	}
	// 查询最近一月记录
	public List<Device> selectLastMonthExcludeTodayByIp(String ip){
		List<Device> list = null;
		Device dev = new Device();
		dev.setIp(ip);
		dev.setCreateDate(DateUtils.getMonthFromNow());
		dev.setUpdateDate(DateUtils.getYesterdayNight());
		list = deviceService.selectTimeSpan(dev);
		return list;
	}
	// 查询最近一周记录
	public List<Device> selectLastWeekExcludeTodayByIp(String ip){
		List<Device> list = null;
		Device dev = new Device();
		dev.setIp(ip);
		dev.setCreateDate(DateUtils.getWeekFromNow());
		dev.setUpdateDate(DateUtils.getYesterdayNight());
		list = deviceService.selectTimeSpan(dev);
		return list;
	}
	// 查询最近一周记录
	public List<Device> selectTodayListByIp(String ip){
		List<Device> list = null;
		Device dev = new Device();
		dev.setIp(ip);
		dev.setCreateDate(DateUtils.getTimesMorning());
		dev.setUpdateDate(DateUtils.getTimesNight());
		list = deviceService.selectTimeSpan(dev);
		return list;
	}
	public List<Device> selectStockByIp(String ip){
		List<Device> list = new ArrayList<Device>();
		Device model = new Device();
		model.setIp(ip);
		list = deviceService.selectStockByIp(model);
		return list;
	}
	// 由定时器调用
	public void saveReportDateToDB(){
		List<Device> needSaveList = null;
		if(usedWhichList == 1){
			needSaveList = deviceReportListCache1;
			usedWhichList = 2;
		}else{
			needSaveList = deviceReportListCache2;
			usedWhichList = 1;
		}
		for(Device device:needSaveList){
			device.setUpdateDate(new Date());
			deviceService.insert(device);
			IpTrunkEngine.getInstance().saveIpInfoToDb(device.getIp());
		}
		needSaveList.clear(); //清空
	}
	// 保存外部设备上报的请求
	public DeviceFromNet saveReportFromExt(ChangeDeviceInfo changeInfo){
		// TODO Auto-generated method stub
		DeviceFromNet device = new DeviceFromNet();
		//----------------------------------------------------------------------
		
		if(null == changeInfo)
			return null;

		if(null != changeInfo){
			device.setConfigure(JSON.toJSONString(changeInfo));
		}
		
		
		if( null != changeInfo.getBuildInfo().getBrand())
			device.setBrand(changeInfo.getBuildInfo().getBrand());
		
		
		if( null != changeInfo.getImei())
			device.setImei(changeInfo.getImei());
		
		if( null != changeInfo.getImsi())
			device.setImsi(changeInfo.getImsi());
		
		if( null != changeInfo.getIp())
			device.setIp(changeInfo.getIp());

		
		if( null != changeInfo.getBuildInfo().getManufacture())
			device.setManufacture(changeInfo.getBuildInfo().getManufacture());
		
		if( null != changeInfo.getBuildInfo().getModel())
			device.setModel(changeInfo.getBuildInfo().getModel());

		if( null != changeInfo.getPhone())
			device.setPhone(changeInfo.getPhone());
		
		device.setSdk(19);
		if( null != changeInfo.getBuildInfo().getSdk() && !changeInfo.getBuildInfo().getSdk().equals(""))
			device.setSdk(Integer.valueOf(changeInfo.getBuildInfo().getSdk()));
		
		
		device.setHeight(1280);
		if( null != changeInfo.getHeight() && !changeInfo.getHeight().equals(""))
			device.setHeight(Integer.valueOf(changeInfo.getHeight()));
		
		device.setWidth(720);
		if( null != changeInfo.getWidth() && !changeInfo.getWidth().equals(""))
			device.setWidth(Integer.valueOf(changeInfo.getWidth()));
		
		// --- 手机网络
		if( null != changeInfo.getNetworkType() && !changeInfo.getNetworkType().equals(""))
			device.setNetworkType(changeInfo.getNetworkType());
		
		
		if( null != changeInfo.getNetworkSubType() && !changeInfo.getNetworkSubType().equals(""))
			device.setNetworkSubtype(changeInfo.getNetworkSubType());
		
		//----------------------------------------------------------------------

		device.setAppId(1L);
		device.setDeviceType(1);
		device.setIsActived(1);
		device.setIsStock(1);
		device.setCreateDate(new Date());
		device.setUpdateDate(new Date());
		return device;
	}
	// 保存外部设备上报的请求
	public boolean saveReportToCache(ReportReq req){
		// TODO Auto-generated method stub
		Device device = new Device();
		ChangeDeviceInfo changeInfo = req.getDevice();
		//----------------------------------------------------------------------
		if(null == req || null == changeInfo)
			return false;

		if(null != changeInfo){
			device.setConfigure(JSON.toJSONString(changeInfo));
		}
		
		
		if( null != changeInfo.getBuildInfo().getBrand())
			device.setBrand(changeInfo.getBuildInfo().getBrand());
		
		
		if( null != changeInfo.getImei())
			device.setImei(changeInfo.getImei());
		
		if( null != changeInfo.getImsi())
			device.setImsi(changeInfo.getImsi());
		
		if( null != changeInfo.getIp())
			device.setIp(changeInfo.getIp());

		
		if( null != changeInfo.getBuildInfo().getManufacture())
			device.setManufacture(changeInfo.getBuildInfo().getManufacture());
		
		if( null != changeInfo.getBuildInfo().getModel())
			device.setModel(changeInfo.getBuildInfo().getModel());

		if( null != changeInfo.getPhone())
			device.setPhone(changeInfo.getPhone());
		
		device.setSdk(19);
		if( null != changeInfo.getBuildInfo().getSdk() && !changeInfo.getBuildInfo().getSdk().equals(""))
			device.setSdk(Integer.valueOf(changeInfo.getBuildInfo().getSdk()));
		
		
		device.setHeight(1280);
		if( null != changeInfo.getHeight() && !changeInfo.getHeight().equals(""))
			device.setHeight(Integer.valueOf(changeInfo.getHeight()));
		
		device.setWidth(720);
		if( null != changeInfo.getWidth() && !changeInfo.getWidth().equals(""))
			device.setWidth(Integer.valueOf(changeInfo.getWidth()));
		
		// --- 手机网络
		if( null != changeInfo.getNetworkType() && !changeInfo.getNetworkType().equals(""))
			device.setNetworkType(changeInfo.getNetworkType());
		
		
		if( null != changeInfo.getNetworkSubType() && !changeInfo.getNetworkSubType().equals(""))
			device.setNetworkSubtype(changeInfo.getNetworkSubType());
		
		//----------------------------------------------------------------------

		device.setAppId(req.getAppId());
		device.setDeviceType(req.getDevice_type());
		device.setIsActived(req.getIs_active());
		device.setIsStock(req.getIs_remain());
		device.setCreateDate(new Date());
		device.setUpdateDate(new Date());
		if(usedWhichList == 1){
			deviceReportListCache1.add(device);
		}else{
			deviceReportListCache2.add(device);
		}
		return false;
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
	@Override
	public void upDate() {
		// TODO Auto-generated method stub
		saveReportDateToDB();
	}
}
