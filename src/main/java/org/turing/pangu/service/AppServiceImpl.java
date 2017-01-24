package org.turing.pangu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.AppDao;
import org.turing.pangu.model.App;



@Service("appServiceImpl")
public class AppServiceImpl extends BaseServiceImpl<App,Long> implements AppService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AppServiceImpl.class);

    private AppDao dao;

	@Autowired
	public void setAppDao(AppDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}

	@Override
	public List<App> selectCanRunApps(App model) {
		// TODO Auto-generated method stub
		return this.dao.selectModelList(model);
	}

}
