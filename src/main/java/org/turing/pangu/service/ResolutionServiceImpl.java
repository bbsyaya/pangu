package org.turing.pangu.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.PhoneBrandDao;
import org.turing.pangu.dao.ResolutionDao;
import org.turing.pangu.model.PhoneBrand;
import org.turing.pangu.model.Resolution;



@Service("resolutionServiceImpl")
public class ResolutionServiceImpl extends BaseServiceImpl<Resolution,Long> implements ResolutionService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ResolutionServiceImpl.class);

    private ResolutionDao dao;

	@Autowired
	public void setDao(ResolutionDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}
}
