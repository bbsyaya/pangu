package org.turing.pangu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.RemainIpDao;
import org.turing.pangu.dao.VpnGroupDao;
import org.turing.pangu.model.RemainIp;
import org.turing.pangu.model.VpnGroup;



@Service("vpnGroupServiceImpl")
public class VpnGroupServiceImpl extends BaseServiceImpl<VpnGroup,Long> implements VpnGroupService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(VpnGroupServiceImpl.class);

    private VpnGroupDao dao;

	@Autowired
	public void setVpnGroupDao(VpnGroupDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}

	@Override
	public List<VpnGroup> getValidIpList(VpnGroup model) {
		// TODO Auto-generated method stub
		return dao.selectValid(model);
	}	
}
