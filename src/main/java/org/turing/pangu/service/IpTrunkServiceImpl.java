package org.turing.pangu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.AppDao;
import org.turing.pangu.dao.IpTrunkDao;
import org.turing.pangu.model.App;
import org.turing.pangu.model.IpTrunk;



@Service("ipTrunkServiceImpl")
public class IpTrunkServiceImpl extends BaseServiceImpl<IpTrunk,Long> implements IpTrunkService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IpTrunkServiceImpl.class);

    private IpTrunkDao dao;

	@Autowired
	public void setIpTrunkDao(IpTrunkDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}
}
