package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.model.PhoneTrunk;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.PhoneTrunkService;
import org.turing.pangu.service.PhoneTrunkServiceImpl;

public class PhoneTrunkEngine implements EngineListen{
	private static final Logger logger = Logger.getLogger(PhoneTrunkEngine.class);
	private static PhoneTrunkEngine mInstance = new PhoneTrunkEngine();
	private List<PhoneTrunk> phoneTrunkList = new ArrayList<PhoneTrunk>();
	private PhoneTrunkService phoneTrunkService = null;
	public static PhoneTrunkEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new PhoneTrunkEngine();
		return mInstance;
	}
	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		for(BaseService service :serviceList){
			if(service instanceof PhoneTrunkServiceImpl ){
				this.phoneTrunkService = (PhoneTrunkService)service;
			}
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
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
