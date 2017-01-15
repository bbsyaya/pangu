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

}
