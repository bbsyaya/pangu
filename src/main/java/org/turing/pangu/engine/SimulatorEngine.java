package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.model.Simulator;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.SimulatorService;
import org.turing.pangu.service.SimulatorServiceImpl;

public class SimulatorEngine implements EngineListen{
	private static final Logger logger = Logger.getLogger(SimulatorEngine.class);
	private static SimulatorEngine mInstance = new SimulatorEngine();
	public List<Simulator> simulatorList = new ArrayList<Simulator>();
	private SimulatorService simulatorService;
	public static SimulatorEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new SimulatorEngine();
		return mInstance;
	}

	public List<Simulator> getComputerList(){
		return simulatorList;
	}
	public Simulator getSimulatorInfo(long appId){
		for(Simulator cpt:simulatorList){
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
			if(service instanceof SimulatorServiceImpl ){
				this.simulatorService = (SimulatorService)service;
			}
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(null != simulatorService){
			simulatorList.clear();
			Simulator cpt = new Simulator();
			cpt.setIsValid(1);
			simulatorList = simulatorService.selectList(cpt);
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

	@Override
	public void upDate() {
		// TODO Auto-generated method stub
		
	}



}
