package org.turing.pangu.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.AppDao;
import org.turing.pangu.dao.RemainDataDao;
import org.turing.pangu.model.App;
import org.turing.pangu.model.RemainData;



@Service("remainDataServiceImpl")
public class RemainDataServiceImpl extends BaseServiceImpl<RemainData,Long> implements RemainDataService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RemainDataServiceImpl.class);

    private RemainDataDao dao;

	@Autowired
	public void setRemainDataDao(RemainDataDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}	
}
