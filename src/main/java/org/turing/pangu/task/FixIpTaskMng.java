package org.turing.pangu.task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.turing.pangu.bean.ConnectVpnInfo;
import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.controller.common.VpnTask;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.pc.response.VpnOperUpdateRsp;
import org.turing.pangu.controller.phone.request.TaskFinishReq;
import org.turing.pangu.engine.RemainEngine;
import org.turing.pangu.engine.TaskEngine;
import org.turing.pangu.engine.TimeZoneMng;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.RemainVpn;
import org.turing.pangu.utils.DateUtils;
import org.turing.pangu.utils.FileUtil;
import org.turing.pangu.utils.RandomUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/*存量任务管理器*/
public class FixIpTaskMng implements TaskIF{
	private static final Logger logger = Logger.getLogger(TaskEngine.class);
	private static FixIpTaskMng mInstance = new FixIpTaskMng();
	private FixIpTask mIncrementIpList = null; //固定IP中用来增量的IP,来源由存量文件中没有的固定IP
	private FixIpTask mStockIpList = null; //固定IP中用来做增量的IP,来源由存量库
	private List<RemainVpn> whiteIpList = new ArrayList<RemainVpn>();
	public static final int IP_INIT = 0;//0:初始  1:已下发 2:已连接 3:已完成
	public static final int IP_SEND = 1;//0:初始  1:已下发 2:已连接 3:已完成
	public static final int IP_CONNECTED = 2;//0:初始  1:已下发 2:已连接 3:已完成
	public static final int IP_FINISHED = 3;//0:初始  1:已下发 2:已连接 3:已完成
	private List<ConnectVpnInfo> mCurrentRunVpnList = new ArrayList<ConnectVpnInfo>();
	private DateUpdateListen mListen = null;
	public static FixIpTaskMng getInstance(){
		if(null == mInstance)
			mInstance = new FixIpTaskMng();
		return mInstance;
	}
	private void clearList(){
		mIncrementIpList.getList().clear();
		mStockIpList.getList().clear();
	}
	public void setDataUpdateListen(DateUpdateListen listen){
		mListen = listen;
	}
	@Override
	public PhoneTask getTask(String deviceId,String remoteIp,String realIp){
		//先去留存IP中取
		for(FixedIpProcess process:mStockIpList.getList()){
			if(process.getIp().equals(remoteIp)){
				for(PhoneTask task:process.getPhoneTaskList()){
					if(task.getDeviceId().equals(deviceId)){ // 发现取过任务了
						if(task.getIsFinished() == 0){ // 任务没完成,返回原来的任务
							logger.info("getTask---end - repeat task not finished,return old task");
							task.setSendTimes(task.getSendTimes()+1);
							return task; 
						}
						else{
							logger.info("getTask---end - repeat task finished,return null task");
							return null;// 任务完成,这种情况下不给任务
						}
					}
					// 分配一个任务
					if( task.getSendTimes() == 0 ){
						if( false == addOneAllocTask(process.getOperType())){
							// 已经用完了这种类型的任务
							return null;
						}
						task.setDeviceId(deviceId);
						task.setSendTimes(task.getSendTimes()+1);
						task.setOperType(process.getOperType());
						return task;
					}
				}
			}
		}
		//再去新增留存IP中取
		for(FixedIpProcess process:mIncrementIpList.getList()){
			if(process.getIp().equals(remoteIp)){
				
			}
		}
		return null;
	}
	@Override
	public String addVpnTask(VpnLoginReq req, String remoteIp, String realIp) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public VpnOperUpdateRsp vpnIsNeedSwitch(String token) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean switchVpnFinish(String token, String remoteIp, String realIp) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void taskFinish(TaskFinishReq req, String remoteIp, String realIp) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void createFixedIpTask(int incrementMoney,int incrementWater, int stockMoney,int stockWater){
		setTaskCount(incrementMoney,incrementWater,stockMoney,stockWater);
		clearList();
		createStockIpList();
		createIncrementIpList();
	}
	/* 获得一个连接VPN信息 */
	public ConnectVpnInfo getConnectVpnInfo(){
		ConnectVpnInfo info = new ConnectVpnInfo();
		int random = (int)(Math.random()*4);
		String ip = "";
		for(RemainVpn vpn :TaskEngine.getInstance().getFixedIpList()){
			int flag = 0;
			ConnectVpnInfo tmp = null;
			for(ConnectVpnInfo con:mCurrentRunVpnList){
				tmp = con;
				if(con.getVpnId() == vpn.getId()){
					flag = 1; // 说明VPN已经被别人占用了
					return null;
				}
			}
			if( flag == 0  ){
				info.setVpnId(tmp.getVpnId());
				info.setPassword(tmp.getPassword());
				info.setUserName(tmp.getUserName());
				mCurrentRunVpnList.add(info);
				break;
			}
		}
		switch(random){
			case TaskEngine.INCREMENT_MONEY_TYPE:
			case TaskEngine.INCREMENT_WATERAMY_TYPE:
				ip = getCanUsedIp(random,mIncrementIpList.getList());
				break;
				
			case TaskEngine.STOCK_MONEY_TYPE:
			case TaskEngine.STOCK_WATERAMY_TYPE:
				ip = getCanUsedIp(random,mStockIpList.getList());
				break;
		}
		info.setIp(ip);
		return info;
	}

	private String getCanUsedIp(int operType,List<FixedIpProcess> list){
		
		for(FixedIpProcess process:list){
			if(process.getStatu() == IP_INIT){ // 发现还没下发的IP
				process.setStatu(IP_SEND);
				process.setOperType(operType);
				return process.getIp();
			}
		}
		return null;
	}
	private boolean addOneAllocTask(int operType){
		switch(operType){
			case TaskEngine.INCREMENT_MONEY_TYPE:
				mIncrementIpList.setMoneyAllotCount(mIncrementIpList.getMoneyAllotCount()+1);
				if(mIncrementIpList.getMoneyAllotCount() <= mIncrementIpList.getMoneyTotalCount())
				{
					return true;
				}
				break;
			case TaskEngine.INCREMENT_WATERAMY_TYPE:
				mIncrementIpList.setWaterAllotCount(mIncrementIpList.getWaterAllotCount()+1);
				if(mIncrementIpList.getWaterAllotCount() <= mIncrementIpList.getWaterTotalCount())
				{
					return true;
				}
				break;
			case TaskEngine.STOCK_MONEY_TYPE:	
				mStockIpList.setMoneyAllotCount(mStockIpList.getMoneyAllotCount()+1);
				if(mStockIpList.getMoneyAllotCount() <= mStockIpList.getMoneyTotalCount())
				{
					return true;
				}
				break;
			case TaskEngine.STOCK_WATERAMY_TYPE:
				mStockIpList.setWaterAllotCount(mStockIpList.getWaterAllotCount()+1);
				if(mStockIpList.getWaterAllotCount() <= mStockIpList.getWaterTotalCount())
				{
					return true;
				}
				break;
		}
		return false;
	}
	/*设置固定Ip的执行任务数量*/
	private void setTaskCount(int incrementMoney,int incrementWater, int stockMoney,int stockWater){
		mIncrementIpList.setMoneyTotalCount(incrementMoney);
		mIncrementIpList.setWaterTotalCount(incrementWater);
		mStockIpList.setMoneyTotalCount(stockMoney);
		mStockIpList.setWaterTotalCount(stockWater);
	}
	private void createIncrementIpList(){
		int incrementTotalCount = mIncrementIpList.getMoneyTotalCount() + mIncrementIpList.getWaterTotalCount();
		List<RemainVpn> whiteIpList = TaskEngine.getInstance().getFixedIpList();
		int flag = 0;
		for(int index = 0; index < incrementTotalCount; index++){
			flag = 0;
			String tmpIp = "";
			for(RemainVpn vpn :whiteIpList){
				String[] ipList = vpn.getIpList().split("\\|");
				for(String ip :ipList){
					tmpIp = ip; 
					List<FixedIpProcess> list =  mStockIpList.getList();
					for(FixedIpProcess process:list){
						if(ip.equals(process.getIp())){ //如果发现相等的IP
							flag = 1;
							break;
						}
					}
				}
			}
			if(flag == 0){
				FixedIpProcess ipProcess = new FixedIpProcess();
				ipProcess.setIp(tmpIp);
				ipProcess.setToken(RandomUtils.getRandom(TaskEngine.VPN_TOKEN_LENGH));
			}
		}
	}
	
	private PhoneTask createPhoneTask(FixedIpProcess process,Device device){
		PhoneTask task = new PhoneTask();
		task.setStockInfo(device);//设置好 设备信息
		task.setApp(TaskEngine.getInstance().getAppInfo(device.getAppId())); // 设置好app信息
		task.setVpnToken(process.getToken());
		task.setTaskId(process.getToken() + RandomUtils.getRandom(TaskEngine.PHONE_TASKID_LENGH/2));//32位		
		return task;
	}
	private void createStockIpList(){
		int flag = 0;
		// -- 
		RemainEngine.getInstance().generateRemainFile();
		for(App app: TaskEngine.getInstance().getAppList())
		{ // 轮询每个文件
			Date tomorrowMorning = DateUtils.getTomorrowMorning();
			String todayRemain = RemainEngine.DateFormat(tomorrowMorning) + "_" + app.getId() + "_" + app.getPackageName() + ".json";
			todayRemain = RemainEngine.remainDownload + app.getUserId() + "/" + app.getPlatformId() + "/" + todayRemain;
			String tomPath = RemainEngine.remainRootPath + todayRemain;
			List<Device> deviceList = null;
			if(FileUtil.isFileExist(tomPath))
			{
				File file = new File(tomPath);
				try {
					String json = FileUtils.readFileToString(file);
					deviceList = JSON.parseObject(json, new TypeReference<ArrayList<Device>>(){});				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(Device device :deviceList){
				List<FixedIpProcess> list =  mStockIpList.getList();
				flag = 0;
				
				for(FixedIpProcess process:list){
					if(device.getIp().equals(process.getIp())){ // 如果Ip相等
						PhoneTask task = createPhoneTask(process,device);
						process.getPhoneTaskList().add(task);
						flag = 1;
						break;
					}
				}
				if(flag == 0){
					FixedIpProcess process = new FixedIpProcess();
					process.setIp(device.getIp());
					process.setToken(RandomUtils.getRandom(TaskEngine.VPN_TOKEN_LENGH));
					PhoneTask task = createPhoneTask(process,device);
					process.getPhoneTaskList().add(task);
					list.add(process);
				}
			}
		}
	}

}
