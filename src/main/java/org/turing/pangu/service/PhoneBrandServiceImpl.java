package org.turing.pangu.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.PhoneBrandDao;
import org.turing.pangu.model.PhoneBrand;



@Service("phoneBrandServiceImpl")
public class PhoneBrandServiceImpl extends BaseServiceImpl<PhoneBrand,Long> implements PhoneBrandService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PhoneBrandServiceImpl.class);

    private PhoneBrandDao dao;

	@Autowired
	public void setDao(PhoneBrandDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}
}
