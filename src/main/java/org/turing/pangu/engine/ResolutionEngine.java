package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Resolution;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.AppServiceImpl;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.ResolutionService;
import org.turing.pangu.service.ResolutionServiceImpl;

public class ResolutionEngine implements EngineListen{
	private static final Logger logger = Logger.getLogger(ResolutionEngine.class);
	private static ResolutionEngine mInstance = new ResolutionEngine();
	public List<Resolution> resolutionList = new ArrayList<Resolution>();
	private ResolutionService resolutionService;
	public static ResolutionEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new ResolutionEngine();
		return mInstance;
	}

	public List<Resolution> getAppList(){
		return resolutionList;
	}
	public List<Resolution> getPlatFormSupportResolution(Long platform_id){
		List<Resolution> list = new ArrayList<Resolution>();
		for(Resolution  res :resolutionList){
			if(res.getIsSupport() == 1 && res.getPlatformId() == platform_id){
				list.add(res);
			}
		}
		return list;
	}
	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
		for(BaseService service :serviceList){
			if(service instanceof ResolutionServiceImpl ){
				this.resolutionService = (ResolutionService)service;
			}
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(null != resolutionService){
			resolutionList.clear();
			resolutionList = resolutionService.selectAll();
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
