package org.turing.pangu.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.AppDao;
import org.turing.pangu.dao.PlatformDao;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Platform;



@Service("platformServiceImpl")
public class PlatformServiceImpl extends BaseServiceImpl<Platform,Long> implements PlatformService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PlatformServiceImpl.class);

    private PlatformDao dao;

	@Autowired
	public void setPlatformDao(PlatformDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}	
}
