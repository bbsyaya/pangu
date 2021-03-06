package org.turing.pangu.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.DeviceFromNetDao;
import org.turing.pangu.model.DeviceFromNet;



@Service("deviceFromNetServiceImpl")
public class DeviceFromNetServiceImpl extends BaseServiceImpl<DeviceFromNet,Long> implements DeviceFromNetService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DeviceFromNetServiceImpl.class);

    private DeviceFromNetDao dao;

	@Autowired
	public void setDeviceFromNetDao(DeviceFromNetDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}
}
