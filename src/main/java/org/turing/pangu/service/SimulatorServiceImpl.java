package org.turing.pangu.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.SimulatorDao;
import org.turing.pangu.model.Simulator;



@Service("simulatorServiceImpl")
public class SimulatorServiceImpl extends BaseServiceImpl<Simulator,Long> implements SimulatorService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SimulatorServiceImpl.class);

    private SimulatorDao dao;

	@Autowired
	public void setUserDao(SimulatorDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}	
}
