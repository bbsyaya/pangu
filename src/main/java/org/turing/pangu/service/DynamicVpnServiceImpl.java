package org.turing.pangu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.AppDao;
import org.turing.pangu.dao.DynamicVpnDao;
import org.turing.pangu.dao.RemainVpnDao;
import org.turing.pangu.engine.VpnEngine;
import org.turing.pangu.model.App;
import org.turing.pangu.model.DynamicVpn;
import org.turing.pangu.model.RemainVpn;



@Service("dynamicVpnServiceImpl")
public class DynamicVpnServiceImpl extends BaseServiceImpl<DynamicVpn,Long> implements DynamicVpnService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DynamicVpnServiceImpl.class);

    private DynamicVpnDao dao;

	@Autowired
	public void setDynamicVpnDao(DynamicVpnDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}
}
