package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import org.turing.pangu.bean.ChangedIp;

/*
 * ip 管理引擎
 * */
public class IpMngEngine {
	private static IpMngEngine mInstance = new IpMngEngine();
	private static int mIpMaxOccur = 5; // 此IP在今天最多能出现的次数
	private List<ChangedIp> list = new ArrayList<ChangedIp>();
	public static IpMngEngine getInstance(){
		if(null == mInstance)
			mInstance = new IpMngEngine();
		return mInstance;
	}
	public void clearIpList()
	{
		list.clear();
	}
	/*保存一个IP*/
	public boolean isCanGetTask(String ip)
	{
		ChangedIp cIp = new ChangedIp();
		for(ChangedIp tmpIp:list){
			if(tmpIp.equals(ip)){
				tmpIp.setCount(tmpIp.getCount()+1);
				if(tmpIp.getCount() > mIpMaxOccur){
					return false;
				}else{
					return true;
				}
			}
		}
		cIp.setIp(ip);
		cIp.setCount(1);
		list.add(cIp);
		return true;
	}
	public List<ChangedIp> getIpList(){
		return list;
	}
	
}
