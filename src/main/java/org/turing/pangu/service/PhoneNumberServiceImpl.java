package org.turing.pangu.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.PhoneNumberDao;
import org.turing.pangu.model.PhoneNumber;
import org.turing.pangu.utils.RandomUtils;



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
		List<PhoneNumber> list = new ArrayList<PhoneNumber>();
		list = dao.selectPhoneNumber(model);
		if(list == null || list.size() == 0)
			return null;
		return list.get(RandomUtils.getRandom(0, list.size()));
	}
}
