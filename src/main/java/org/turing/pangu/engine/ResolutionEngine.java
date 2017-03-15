package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.model.PhoneBrand;
import org.turing.pangu.model.Resolution;
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
			//if(res.getIsSupport() == 1 && res.getPlatformId() == platform_id){ // 目前分辨率不分平台
			if(res.getIsSupport() == 1){
				list.add(res);
			}
		}
		return list;
	}
	public boolean isSupportResolution(PhoneBrand model,Long platform_id){
		List<Resolution> list = getPlatFormSupportResolution(platform_id);
		for(Resolution  res :list){
			System.out.print("\n model = "+model.getWidth() + "X" + model.getHeight());
			System.out.print("\n res = "+res.getWidth() + "X" + res.getHeight());
			if(model.getHeight().equals(res.getHeight())&& model.getWidth().equals(res.getWidth())){
				return true;
			}
		}
		return false;
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
