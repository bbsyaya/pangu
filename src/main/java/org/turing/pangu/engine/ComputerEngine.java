package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Computer;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.AppServiceImpl;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.ComputerService;
import org.turing.pangu.service.ComputerServiceImpl;

public class ComputerEngine implements EngineListen{
	private static final Logger logger = Logger.getLogger(ComputerEngine.class);
	private static ComputerEngine mInstance = new ComputerEngine();
	public List<Computer> computerList = new ArrayList<Computer>();
	private ComputerService computerService;
	public static ComputerEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new ComputerEngine();
		return mInstance;
	}

	public List<Computer> getComputerList(){
		return computerList;
	}
	public Computer getComputerInfo(long appId){
		for(Computer cpt:computerList){
			if(cpt.getId() == appId){
				return cpt;
			}
		}
		return null;
	}
	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
		for(BaseService service :serviceList){
			if(service instanceof ComputerServiceImpl ){
				this.computerService = (ComputerService)service;
			}
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(null != computerService){
			computerList.clear();
			Computer cpt = new Computer();
			cpt.setIsValid(1);
			computerList = computerService.selectList(cpt);
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
