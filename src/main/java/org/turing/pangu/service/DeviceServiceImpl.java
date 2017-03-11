package org.turing.pangu.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.DeviceDao;
import org.turing.pangu.model.Device;
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
		device.setIsStock(1);
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

	@Override
	public int selectCountByTimeSpan(Device device) {
		// TODO Auto-generated method stub
		return dao.selectCountByTimeSpan(device);
	}

	@Override
	public List<Device> selectStockByIp(Device device) {
		// TODO Auto-generated method stub
		return dao.selectList(device);
	}

	@Override
	public List<Device> selectTimeSpan(Device device) {
		// TODO Auto-generated method stub
		return dao.selectTimeSpan(device);
	}
}
