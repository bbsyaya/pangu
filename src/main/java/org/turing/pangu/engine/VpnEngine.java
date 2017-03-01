package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import org.turing.pangu.model.DynamicVpn;
import org.turing.pangu.model.RemainVpn;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.DynamicVpnService;
import org.turing.pangu.service.DynamicVpnServiceImpl;
import org.turing.pangu.service.RemainVpnService;
import org.turing.pangu.service.RemainVpnServiceImpl;
import org.turing.pangu.service.TaskService;
import org.turing.pangu.service.TaskServiceImpl;

public class VpnEngine implements EngineListen{
	
	private static VpnEngine mInstance = new VpnEngine();
	private List<RemainVpn> remainVpnList = new ArrayList<RemainVpn>();
	private List<DynamicVpn> dynamicVpnList = new ArrayList<DynamicVpn>();
	private RemainVpnService remainVpnService = null;
	private DynamicVpnService dynamicVpnService = null;
	
 	public static VpnEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new VpnEngine();
		return mInstance;
	}
	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
		for(BaseService service :serviceList){
			if(service instanceof RemainVpnServiceImpl ){
				this.remainVpnService = (RemainVpnService)service;
			}
			if(service instanceof DynamicVpnServiceImpl ){
				this.dynamicVpnService = (DynamicVpnService)service;
			}
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(null != remainVpnService){
			remainVpnList = remainVpnService.selectAll();
		}
		if(null != dynamicVpnService){
			dynamicVpnList = dynamicVpnService.selectAll();
		}
	}
	public boolean isWhiteIp(String ip){
		for(RemainVpn remain :remainVpnList){
			if(remain.getIpList().contains(ip)){
				return true;
			}
		}
		return false;
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
