package org.turing.pangu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.AppDao;
import org.turing.pangu.dao.UmengBrandDao;
import org.turing.pangu.model.App;
import org.turing.pangu.model.UmengBrand;



@Service("umengBrandServiceImpl")
public class UmengBrandServiceImpl extends BaseServiceImpl<UmengBrand,Long> implements UmengBrandService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UmengBrandServiceImpl.class);

    private UmengBrandDao dao;

	@Autowired
	public void setDao(UmengBrandDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}
}
