package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.model.CmnPayUser;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.CmnPayUserService;
import org.turing.pangu.service.CmnPayUserServiceImpl;

public class CmnPayUserEngine implements EngineListen{
	private static final Logger logger = Logger.getLogger(CmnPayUserEngine.class);
	private static CmnPayUserEngine mInstance = new CmnPayUserEngine();
	public List<CmnPayUser> userList = new ArrayList<CmnPayUser>();
	private CmnPayUserService cmnPayUserService;
	private final int PAY_TYPE_COUNT = 0; // 次数
	private final int PAY_TYPE_DAY = 1;   // 日付费
	private final int PAY_TYPE_MONTH = 2; // 月付费
	private final int PAY_TYPE_YEAR = 3;  // 年付费
	private final int PAY_TYPE_PERMANENT = 4; // 永久
	public static CmnPayUserEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new CmnPayUserEngine();
		return mInstance;
	}

	public List<CmnPayUser> getUserList(){
		return userList;
	}
	public long getCount(String token){
		for(CmnPayUser cpt:userList){
			if(cpt.getToken().equals(token)){
				switch(cpt.getPayType()){
					case PAY_TYPE_COUNT:
						return cpt.getCount();
					case PAY_TYPE_DAY:
						return 99999;
					case PAY_TYPE_MONTH:
						return 99999;
					case PAY_TYPE_YEAR:
						return 99999;
					case PAY_TYPE_PERMANENT:
						return 99999;
				}
			}
		}
		return 0L;
	}
	// 减少一次
	public void minusCount(String token){
		for(CmnPayUser cpt:userList){
			if(cpt.getToken().equals(token)){
				switch(cpt.getPayType()){
					case PAY_TYPE_COUNT:
						cpt.setCount(cpt.getCount() - 1);
						return;
					default:
							break;
				}
			}
		}
	}
	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
		for(BaseService service :serviceList){
			if(service instanceof CmnPayUserServiceImpl ){
				this.cmnPayUserService = (CmnPayUserService)service;
			}
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(null != cmnPayUserService){
			userList.clear();
			userList = cmnPayUserService.selectAll();
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
