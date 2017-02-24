package org.turing.pangu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.RemainIpDao;
import org.turing.pangu.model.RemainIp;



@Service("remainIpServiceImpl")
public class RemainIpServiceImpl extends BaseServiceImpl<RemainIp,Long> implements RemainIpService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RemainIpServiceImpl.class);

    private RemainIpDao dao;

	@Autowired
	public void setRemainDataDao(RemainIpDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}

	@Override
	public List<RemainIp> getValidIpList(RemainIp model) {
		// TODO Auto-generated method stub
		return dao.selectValid(model);
	}	
}
