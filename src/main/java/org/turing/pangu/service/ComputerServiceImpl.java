package org.turing.pangu.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.ComputerDao;
import org.turing.pangu.dao.SimulatorDao;
import org.turing.pangu.dao.UserDao;
import org.turing.pangu.model.Computer;
import org.turing.pangu.model.Simulator;
import org.turing.pangu.model.User;



@Service("computerServiceImpl")
public class ComputerServiceImpl extends BaseServiceImpl<Computer,Long> implements ComputerService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ComputerServiceImpl.class);

    private ComputerDao dao;

	@Autowired
	public void setComputerDao(ComputerDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}	
}
