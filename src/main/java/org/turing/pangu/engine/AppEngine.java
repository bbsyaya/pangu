package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.bean.PlatformUser;
import org.turing.pangu.bean.UserApp;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Platform;
import org.turing.pangu.model.User;
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
	private List<App> getAppListByUserId(Long userId){
		List<App> list = new ArrayList<App>();
		for( App app :appList ){
			if(app.getUserId() == userId){
				list.add(app);
			}
		}
		return list;
	}
	
	public List<App> getAllAppListRealTime(){
		List<App> list = new ArrayList<App>();
		App app = new App();
		list = appService.selectAll();
		return list;
	}
	
	public List<App> getAppListRealTimeByUserId(Long userId){
		List<App> list = new ArrayList<App>();
		App app = new App();
		app.setUserId(userId);
		list = appService.selectList(app);
		return list;
	}
	public void setAppValidByUserId(Long userId,int isValid){
		App app = new App();
		app.setUserId(userId);
		List<App> list = appService.selectList(app);
		if(null == list || list.size() == 0)
			return;
		
		for(App tmp:list){
			tmp.setIsCanRun(isValid);
			tmp.setUpdateDate(new Date());
			appService.update(tmp);
		}
	}
	public List<PlatformUser> getPlatformUserAppList(){
		List<PlatformUser> list = new ArrayList<PlatformUser>();
		List<Platform>  pfList = PlatformEngine.getInstance().getPlatformListRealTime();
		for(Platform pf : pfList){
			PlatformUser pa = new PlatformUser();
			List<UserApp> userAppList = new ArrayList<UserApp>();
			List<User> userList = UserEngine.getInstance().getUserListRealTimeByPlatformId(pf.getId());
			for(User user:userList){
				UserApp userApp = new UserApp();
				userApp.setUser(user);
				userApp.setAppList(getAppListRealTimeByUserId(user.getId()));
				userAppList.add(userApp);
			}
			pa.setUserList(userAppList);
			pa.setPf(pf);
			list.add(pa);
		}
		return list;
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
	public int getActiveUserCount(){
		List<Long> list = new ArrayList<Long>();
		int flag = 0;
		for(App app:appList){
			flag = 0;
			for(Long count : list){
				if(app.getUserId() == count){
					flag = 1;
				}
			}
			if(flag == 0){
				list.add(app.getUserId());
			}
			
		}
		return list.size();
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

	@Override
	public void upDate() {
		// TODO Auto-generated method stub
		
	}



}
