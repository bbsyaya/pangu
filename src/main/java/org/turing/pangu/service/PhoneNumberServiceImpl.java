package org.turing.pangu.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.PhoneNumberDao;
import org.turing.pangu.model.PhoneNumber;



@Service("phoneNumberServiceImpl")
public class PhoneNumberServiceImpl extends BaseServiceImpl<PhoneNumber,Long> implements PhoneNumberService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PhoneNumberServiceImpl.class);

    private PhoneNumberDao dao;

	@Autowired
	public void setDao(PhoneNumberDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}

	@Override
	public PhoneNumber selectOnePhoneNumber(PhoneNumber model) {
		// TODO Auto-generated method stub
		return null;
	}
}
