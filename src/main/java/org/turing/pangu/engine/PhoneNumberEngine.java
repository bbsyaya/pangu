package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.model.PhoneNumber;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.PhoneNumberService;
import org.turing.pangu.service.PhoneNumberServiceImpl;

public class PhoneNumberEngine implements EngineListen{
	private static final Logger logger = Logger.getLogger(PhoneNumberEngine.class);
	private static PhoneNumberEngine mInstance = new PhoneNumberEngine();
	private List<PhoneNumber> phoneTrunkList = new ArrayList<PhoneNumber>();
	private PhoneNumberService phoneTrunkService = null;
	public static PhoneNumberEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new PhoneNumberEngine();
		return mInstance;
	}
	public String getPhoneNumber(String city,String operType){
		
		return "";
	}
	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		for(BaseService service :serviceList){
			if(service instanceof PhoneNumberServiceImpl ){
				this.phoneTrunkService = (PhoneNumberService)service;
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
	@Override
	public void upDate() {
		// TODO Auto-generated method stub
		
	}
}
