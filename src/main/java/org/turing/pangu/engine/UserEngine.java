package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Computer;
import org.turing.pangu.model.User;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.AppServiceImpl;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.ComputerService;
import org.turing.pangu.service.ComputerServiceImpl;
import org.turing.pangu.service.SimulatorService;
import org.turing.pangu.service.SimulatorServiceImpl;
import org.turing.pangu.service.UserService;
import org.turing.pangu.service.UserServiceImpl;

public class UserEngine implements EngineListen{
	private static final Logger logger = Logger.getLogger(UserEngine.class);
	private static UserEngine mInstance = new UserEngine();
	public List<User> userList = new ArrayList<User>();
	private UserService userService;
	public static UserEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new UserEngine();
		return mInstance;
	}

	public List<User> getUserList(){
		return userList;
	}
	public User getUserInfo(long userId){
		for(User cpt:userList){
			if(cpt.getId() == userId){
				return cpt;
			}
		}
		return null;
	}
	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
		for(BaseService service :serviceList){
			if(service instanceof UserServiceImpl ){
				this.userService = (UserService)service;
			}
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(null != userService){
			userList.clear();
			userList = userService.selectAll();
		}
	}


	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}



}
