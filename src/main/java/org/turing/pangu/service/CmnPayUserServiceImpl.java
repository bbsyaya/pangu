package org.turing.pangu.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.CmnPayUserDao;
import org.turing.pangu.model.CmnPayUser;



@Service("cmnPayUserServiceImpl")
public class CmnPayUserServiceImpl extends BaseServiceImpl<CmnPayUser,Long> implements CmnPayUserService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CmnPayUserServiceImpl.class);

    private CmnPayUserDao dao;

	@Autowired
	public void setDao(CmnPayUserDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}
}
