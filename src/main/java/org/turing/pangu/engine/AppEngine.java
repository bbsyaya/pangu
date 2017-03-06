package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.model.App;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.AppServiceImpl;
import org.turing.pangu.service.BaseService;

public class AppEngine implements EngineListen{
	private static final Logger logger = Logger.getLogger(AppEngine.class);
	private static AppEngine mInstance = new AppEngine();
	public List<App> appList = new ArrayList<App>();
	private AppService appService;
	public static AppEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new AppEngine();
		return mInstance;
	}

	public List<App> getAppList(){
		return appList;
	}
	public App getAppInfo(long appId){
		for(App app:appList){
			if(app.getId() == appId){
				return app;
			}
		}
		return null;
	}
	public boolean isActiveApp(Long appId){
		for(App app:appList){
			if(app.getId() == appId){
				return true;
			}
		}
		return false;
	}
	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
		for(BaseService service :serviceList){
			if(service instanceof AppServiceImpl ){
				this.appService = (AppService)service;
			}
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(null != appService){
			App model = new App();
			model.setIsCanRun(1);
			appList.clear();
			appList = appService.selectCanRunApps(model);
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
