package org.turing.pangu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.RemainVpnDao;
import org.turing.pangu.engine.VpnEngine;
import org.turing.pangu.model.RemainVpn;



@Service("remainVpnServiceImpl")
public class RemainVpnServiceImpl extends BaseServiceImpl<RemainVpn,Long> implements RemainVpnService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RemainVpnServiceImpl.class);

    private RemainVpnDao dao;

	@Autowired
	public void setRemainVpnDao(RemainVpnDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}

	@Override
	public boolean isWhileListIp(String ip) {
		// TODO Auto-generated method stub		
		return false;
	}	
}
