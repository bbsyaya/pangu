package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import org.turing.pangu.service.BaseService;

public class EngineMng {
	private List<EngineListen> ltnList = new ArrayList<EngineListen>();
	private List<BaseService> servicesList = new ArrayList<BaseService>();
	private static EngineMng mInstance = new EngineMng();
	
 	public static EngineMng getInstance()
	{
		if(null == mInstance)
			mInstance = new EngineMng();
		return mInstance;
	}
	// 设置监听
	private void setListen(){
		ltnList.add(AppEngine.getInstance());
		ltnList.add(TaskEngine.getInstance());
		ltnList.add(DeviceEngine.getInstance());
		ltnList.add(PlatformEngine.getInstance());
		ltnList.add(VpnEngine.getInstance());
		ltnList.add(IpTrunkEngine.getInstance());
	}
	// 设置服务
	private void setService(){
		for(EngineListen lst:ltnList){
			lst.setService(servicesList);
		}
	}
	private void setInit(){
		for(EngineListen lst:ltnList){
			lst.init();
		}
	}
	
	public void initEngine(List<BaseService> list){
		this.servicesList = list;
		setListen();
		setService();
		setInit();
	}
}
