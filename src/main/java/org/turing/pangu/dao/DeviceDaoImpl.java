/**
 * 
 * Title：User
 * Copyright: Copyright (c) 2014
 * Company: 深电中心
 * @author axi
 * @version 1.0, 2016年08月23日 
 * @since 2016年08月23日 
 */

package org.turing.pangu.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.turing.pangu.mapper.DeviceMapper;
import org.turing.pangu.model.Device;

@Repository
public class DeviceDaoImpl extends BaseDaoImpl<Device, Long> implements DeviceDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DeviceDaoImpl.class);

	@Autowired
	private DeviceMapper mapper;
	
	@Autowired
	public void setDeviceMapper(DeviceMapper mapper) {
		super.setBaseMapper(mapper);
	}

	@Override
	public List<Device> selectCanRemainData(Device device) {
		// TODO Auto-generated method stub
		List<Device> list = null;
		try {
			list = mapper.selectTimeSpan(device);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	@Override
	public int selectCountByTimeSpan(Device device) {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			count = (int)mapper.selectCountByTimeSpan(device);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public List<Device> selectTimeSpan(Device device) {
		// TODO Auto-generated method stub
		List<Device> list = null;
		try {
			list = mapper.selectTimeSpan(device);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

}
