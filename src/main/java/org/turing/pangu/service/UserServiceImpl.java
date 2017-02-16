package org.turing.pangu.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.UserDao;
import org.turing.pangu.model.User;



@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<User,Long> implements UserService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserDao dao;

	@Autowired
	public void setUserDao(UserDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}	
}
