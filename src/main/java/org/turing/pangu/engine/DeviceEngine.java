package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.controller.phone.request.ReportReq;
import org.turing.pangu.model.Device;
import org.turing.pangu.phone.ChangeDeviceInfo;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.service.DeviceServiceImpl;
import org.turing.pangu.utils.DateUtils;

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
	public boolean saveReport(ReportReq req,boolean isWhiteIp){
		// TODO Auto-generated method stub
		Device device = new Device();
		ChangeDeviceInfo changeInfo = req.getDevice();
		//----------------------------------------------------------------------
		if(null == req || null == changeInfo)
			return false;
		if( null != changeInfo.getAndroidId())
			device.setAndroidId(changeInfo.getAndroidId());
		
		if( null != changeInfo.getAndroidSerial())
			device.setAndroidSerial(changeInfo.getAndroidSerial());
			
		if( null != changeInfo.getAndroidVersion())			
			device.setAndroidVersion(changeInfo.getAndroidVersion());
			
		if( null != changeInfo.getBlueTooth())			
			device.setBlueTooth(changeInfo.getBlueTooth());
			
		if( null != changeInfo.getBoard())			
			device.setBoard(changeInfo.getBoard());
		
		if( null != changeInfo.getBrand())
			device.setBrand(changeInfo.getBrand());
		
		if( null != changeInfo.getBssid())
			device.setBssid(changeInfo.getBssid());
		
		if( null != changeInfo.getCarrier())
			device.setCarrier(changeInfo.getCarrier());
		
		if( null != changeInfo.getCarrierCode())
			device.setCarrierCode(changeInfo.getCarrierCode());
		
		if( null != changeInfo.getCountryCode())
			device.setCountryCode(changeInfo.getCountryCode());
		
		if( null != changeInfo.getDisplay())
			device.setDisplay(changeInfo.getDisplay());
		
		if( null != changeInfo.getImei())
			device.setImei(changeInfo.getImei());
		
		if( null != changeInfo.getImsi())
			device.setImsi(changeInfo.getImsi());
		
		if( null != changeInfo.getIp())
			device.setIp(changeInfo.getIp());
		

			//device.set(changeInfo.getLatitude());
			//device.setAndroidSerial(changeInfo.getLongitude());
		if( null != changeInfo.getMac())
			device.setMac(changeInfo.getMac());
		
		if( null != changeInfo.getManufacture())
			device.setManufacture(changeInfo.getManufacture());
		
		if( null != changeInfo.getModel())
			device.setModel(changeInfo.getModel());
		
			//device.set(changeInfo.getOsArch());
			//device.setAndroidSerial(changeInfo.getOsName());
			//device.setAndroidSerial(changeInfo.getOsVersion());
		if( null != changeInfo.getPhone())
			device.setPhone(changeInfo.getPhone());
		
		if( null != changeInfo.getSimSerial())
			device.setSimSerial(changeInfo.getSimSerial());
		
		if( null != changeInfo.getSsid())
			device.setSsid(changeInfo.getSsid());
		
		if( null != changeInfo.getUa())
			device.setUa(changeInfo.getUa());
		
		device.setPhoneStatus(1);
		if( null != changeInfo.getPhoneStatus() && !changeInfo.getPhoneStatus().equals(""))
			device.setPhoneStatus(Integer.valueOf(changeInfo.getPhoneStatus()));
		
		device.setSdk(19);
		if( null != changeInfo.getSdk() && !changeInfo.getSdk().equals(""))
			device.setSdk(Integer.valueOf(changeInfo.getSdk()));
		
		device.setSimStatus(5);
		if( null != changeInfo.getSimStatus() && !changeInfo.getSimStatus().equals(""))
			device.setSimStatus(Integer.valueOf(changeInfo.getSimStatus()));
		
		device.setHeight(1280);
		if( null != changeInfo.getHeight() && !changeInfo.getHeight().equals(""))
			device.setHeight(Integer.valueOf(changeInfo.getHeight()));
		
		device.setWidth(720);
		if( null != changeInfo.getWidth() && !changeInfo.getWidth().equals(""))
			device.setWidth(Integer.valueOf(changeInfo.getWidth()));
		
		if( null != changeInfo.getDevice() && !changeInfo.getDevice().equals(""))
			device.setDevice(changeInfo.getDevice());
		
		if( null != changeInfo.getCpuABI() && !changeInfo.getCpuABI().equals(""))
			device.setCpuAbi(changeInfo.getCpuABI());
		
		if( null != changeInfo.getBootloader() && !changeInfo.getBootloader().equals(""))
			device.setBootloader(changeInfo.getBootloader());
		
		if( null != changeInfo.getProduct() && !changeInfo.getProduct().equals(""))
			device.setProduct(changeInfo.getProduct());
		// --- 手机网络
		if( null != changeInfo.getNetworkType() && !changeInfo.getNetworkType().equals(""))
			device.setNetworkType(changeInfo.getNetworkType());
		
		if( null != changeInfo.getNetworkTypeName() && !changeInfo.getNetworkTypeName().equals(""))
			device.setNetworkTypeName(changeInfo.getNetworkTypeName());
		
		if( null != changeInfo.getNetworkSubType() && !changeInfo.getNetworkSubType().equals(""))
			device.setNetworkSubtype(changeInfo.getNetworkSubType());
		
		if( null != changeInfo.getNetworkSubTypeName() && !changeInfo.getNetworkSubTypeName().equals(""))
			device.setNetworkSubtypeName(changeInfo.getNetworkSubTypeName());
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
		
	}
}
