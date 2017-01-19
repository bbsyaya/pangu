package org.turing.pangu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.controller.phone.request.ReportReq;
import org.turing.pangu.dao.DeviceDao;
import org.turing.pangu.dao.UserDao;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.RemainData;
import org.turing.pangu.model.User;
import org.turing.pangu.utils.DateUtils;



@Service("deviceServiceImpl")
public class DeviceServiceImpl extends BaseServiceImpl<Device,Long> implements DeviceService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DeviceServiceImpl.class);

    private DeviceDao dao;

	@Autowired
	public void setDeviceDao(DeviceDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}

	@Override
	public boolean saveReport(ReportReq req,boolean isWhiteIp) {
		// TODO Auto-generated method stub
		Device device = new Device();
		//----------------------------------------------------------------------
		device.setAndroidId(req.getDevice().getAndroidId());
		device.setAndroidSerial(req.getDevice().getAndroidSerial());
		device.setAndroidVersion(req.getDevice().getAndroidVersion());
		device.setBlueTooth(req.getDevice().getBlueTooth());
		device.setBoard(req.getDevice().getBoard());
		device.setBrand(req.getDevice().getBrand());
		device.setBssid(req.getDevice().getBssid());
		device.setCarrier(req.getDevice().getCarrier());
		device.setCarrierCode(req.getDevice().getCarrierCode());
		device.setCountryCode(req.getDevice().getCountryCode());
		device.setDisplay(req.getDevice().getDisplay());
		device.setImei(req.getDevice().getImei());
		device.setImsi(req.getDevice().getImsi());
		device.setIp(req.getDevice().getIpAddress());
		//device.set(req.getDevice().getLatitude());
		//device.setAndroidSerial(req.getDevice().getLongitude());
		device.setMac(req.getDevice().getMacAddress());
		device.setManufacture(req.getDevice().getManufacture());
		device.setModel(req.getDevice().getModel());
		//device.set(req.getDevice().getOsArch());
		//device.setAndroidSerial(req.getDevice().getOsName());
		//device.setAndroidSerial(req.getDevice().getOsVersion());
		device.setPhone(req.getDevice().getPhoneNumber());
		device.setPhoneStatus(Integer.valueOf(req.getDevice().getPhoneStatus()));
		device.setSdk(Integer.valueOf(req.getDevice().getSdk()));
		device.setSimSerial(req.getDevice().getSimSerial());
		device.setSimStatus(Integer.valueOf(req.getDevice().getSimStatus()));
		device.setSsid(req.getDevice().getSsid());
		device.setUa(req.getDevice().getUa());
		device.setHeight(Integer.valueOf(req.getDevice().getHeight()));
		device.setWidth(Integer.valueOf(req.getDevice().getWidth()));
		//----------------------------------------------------------------------
		device.setAppId(req.getAppId());
		device.setDeviceType(req.getDevice_type());
		device.setIsActived(req.getIs_active());
		Integer whiteIp = (isWhiteIp == true) ? 1: 0;
		device.setIsWhiteIp(whiteIp);
		device.setIsRemain(req.getIs_remain());
		
		dao.insert(device);
		return false;
	}

	@Override
	public List<Device> selectCanRemainData(Device device) {
		// TODO Auto-generated method stub
		return dao.selectCanRemainData(device);
	}
	@Override
	public String getRemainIpList() {
		// TODO Auto-generated method stub
		StringBuffer ipStr = new StringBuffer();
		Device device = new Device();
		Date yesterdayMorning = DateUtils.getYesterdayMorning();
		Date yesterdayNight = DateUtils.getYesterdayNight();
		device.setIsWhiteIp(1);
		device.setCreateDate(yesterdayMorning);
		device.setUpdateDate(yesterdayNight);
		List<Device> list = dao.selectCanRemainData(device);
		if(null == list || list.size() == 0 )
			return null;
		for(Device dev : list){
			ipStr.append(dev.getIp());
			ipStr.append("|");
		}
		return ipStr.toString();
	}
}
