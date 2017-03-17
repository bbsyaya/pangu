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
import org.turing.pangu.mapper.DeviceFromNetMapper;
import org.turing.pangu.mapper.DeviceMapper;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.DeviceFromNet;

@Repository
public class DeviceFromNetDaoImpl extends BaseDaoImpl<DeviceFromNet, Long> implements DeviceFromNetDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DeviceFromNetDaoImpl.class);

	@Autowired
	private DeviceFromNetMapper mapper;
	
	@Autowired
	public void setDeviceFromNetMapper(DeviceFromNetMapper mapper) {
		super.setBaseMapper(mapper);
	}

}
