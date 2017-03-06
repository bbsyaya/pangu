package org.turing.pangu.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.PhoneTrunkDao;
import org.turing.pangu.model.PhoneTrunk;



@Service("phoneTrunkServiceImpl")
public class PhoneTrunkServiceImpl extends BaseServiceImpl<PhoneTrunk,Long> implements PhoneTrunkService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PhoneTrunkServiceImpl.class);

    private PhoneTrunkDao dao;

	@Autowired
	public void setPhoneTrunkDao(PhoneTrunkDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}
}
