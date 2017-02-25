package org.turing.pangu.engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Synchronization;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.turing.pangu.bean.VpnConnectInfo;
import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.pc.response.VpnConnectInfoRsp;
import org.turing.pangu.controller.pc.response.VpnOperUpdateRsp;
import org.turing.pangu.controller.phone.request.TaskFinishReq;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.RemainIp;
import org.turing.pangu.model.RemainVpn;
import org.turing.pangu.model.Task;
import org.turing.pangu.model.VpnGroup;
import org.turing.pangu.service.RemainIpService;
import org.turing.pangu.service.RemainVpnService;
import org.turing.pangu.service.VpnGroupService;
import org.turing.pangu.task.BusinessStaticVpn;
import org.turing.pangu.task.BusinessVpnGroup;
import org.turing.pangu.task.DateUpdateListen;
import org.turing.pangu.task.StaticVpn;
import org.turing.pangu.task.StaticVpnTask;
import org.turing.pangu.task.TaskExtend;
import org.turing.pangu.task.TaskIF;
import org.turing.pangu.task.TaskListSort;
import org.turing.pangu.task.VpnTask;
import org.turing.pangu.utils.FileUtil;
import org.turing.pangu.utils.RandomUtils;
import org.turing.pangu.utils.TraceUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

//存量任务管理器
/*
public class TaskStaticIpEngine implements TaskIF {
	private static final Logger logger = Logger.getLogger(TaskStaticIpEngine.class);
	private static TaskStaticIpEngine mInstance = new TaskStaticIpEngine();
	private List<StaticVpnTask> mIncrementIpList = new ArrayList<StaticVpnTask>(); // 固定IP中用来增量的IP,来源由存量文件中没有的固定IP
	private List<StaticVpnTask> mStockIpList = new ArrayList<StaticVpnTask>(); // 固定IP中用来做增量的IP,来源由存量库
	private List<StaticVpn> mCurrentRunVpnList = new ArrayList<StaticVpn>(); // 当前在跑的VPN
	private List<BusinessVpnGroup> mBusVpnGroup = new ArrayList<BusinessVpnGroup>();
	
	public static final int IP_INIT = 0;// 0:初始 1:已下发 2:已连接 3:已完成 4:超时
	public static final int IP_SEND = 1;// 0:初始 1:已下发 2:已连接 3:已完成
	public static final int IP_CONNECTED = 2;// 0:初始 1:已下发 2:已连接 3:已完成
	public static final int IP_FINISHED = 3;// 0:初始 1:已下发 2:已连接 3:已完成
	public static final int IP_TIMEOUT = 4;// 0:初始 1:已下发 2:已连接 3:已完成
	
	public static final int IS_RUN_INCREMENT = 0;// 0:跑增量 1:跑存量
	public static final int IS_RUN_STOCK = 1;
	private RemainVpnService remainVpnService;
	private RemainIpService remainIpService;
	private VpnGroupService vpnGroupService;

	public void setService(RemainVpnService remainVpnService,
			RemainIpService remainIpService, VpnGroupService vpnGroupService) {
		this.remainVpnService = remainVpnService;
		this.remainIpService = remainIpService;
		this.vpnGroupService = vpnGroupService;
	}
	private DateUpdateListen mListen = null;

	public static TaskStaticIpEngine getInstance() {
		if (null == mInstance)
			mInstance = new TaskStaticIpEngine();
		return mInstance;
	}
	//初始化VPN组,从DB中取数据到内存
	private void initVpnGroup(){
		VpnGroup model = new VpnGroup();
		model.setIsValid(1);
		List<VpnGroup> list = vpnGroupService.selectList(model);
		for(VpnGroup vpnGroup:list){	
			BusinessVpnGroup busVpnGroup = new BusinessVpnGroup();
			busVpnGroup.setGroup(vpnGroup);
			RemainVpn remainVpnModel = new RemainVpn();
			remainVpnModel.setIsValid(1);
			remainVpnModel.setGroupId(vpnGroup.getId());
			List<RemainVpn> remainVpnlist = remainVpnService.selectList(remainVpnModel);
			for(RemainVpn remainVpn :remainVpnlist){
				BusinessStaticVpn staticVpn = new BusinessStaticVpn();
				staticVpn.setVpn(remainVpn);
				RemainIp remainIpModel = new RemainIp();
				remainIpModel.setIsVaild(1);
				remainIpModel.setVpnId(remainVpn.getId());
				List<RemainIp> remainIplist = remainIpService.selectList(remainIpModel);
				staticVpn.setIpList(remainIplist);
				busVpnGroup.getStaticVpnList().add(staticVpn);
			}
			mBusVpnGroup.add(busVpnGroup);
		}
	}
	private RemainIp findRemainIpByIpAndGroup(String ip,Long groupId){
		for(BusinessVpnGroup group:mBusVpnGroup){
			if(group.getGroup().getId() == groupId){
				for(BusinessStaticVpn staticVpn:group.getStaticVpnList()){
					for(RemainIp remainIp:staticVpn.getIpList()){
						if(remainIp.getIp().equals(ip)){
							return remainIp;
						}
					}
				}
			}
		}
		return null;
	}
	private RemainIp findRemainIpByIp(String ip){
		for(BusinessVpnGroup group:mBusVpnGroup){
			for(BusinessStaticVpn staticVpn:group.getStaticVpnList()){
				for(RemainIp remainIp:staticVpn.getIpList()){
					if(remainIp.getIp().equals(ip)){
						return remainIp;
					}
				}
			}
		}
		return null;
	}
	private RemainIp getRandomRemainIp(){
		int size = mBusVpnGroup.size();
		int random = (int)(Math.random()* size);
		size = mBusVpnGroup.get(random).getStaticVpnList().size();
		int randomVpn = (int)(Math.random()*size);
		size = mBusVpnGroup.get(random).getStaticVpnList().get(randomVpn).getIpList().size();
		int randomIp = (int)(Math.random()* size);
		return mBusVpnGroup.get(random).getStaticVpnList().get(randomVpn).getIpList().get(randomIp);
	}
	private void initRunVpn() {
		mCurrentRunVpnList.clear();
		for (BusinessVpnGroup group :mBusVpnGroup) { // 设置多少组
			StaticVpn runVpn = new StaticVpn();
			VpnConnectInfoRsp info = new VpnConnectInfoRsp();
			info.setGroupId(group.getGroup().getId());
			runVpn.setConnectInfo(info);
			mCurrentRunVpnList.add(runVpn);
		}
	}

	@Override
	public void init(DateUpdateListen listen) {
		// TODO Auto-generated method stub
		setDataUpdateListen(listen);
		clear();
		initVpnGroup();
		createStaticIpTask();
		initRunVpn();
	}

	@Override
	public boolean isHavaTaskByOperType(int type, Task dbTask) {
		// TODO Auto-generated method stub
		TaskExtend ext = (TaskExtend) dbTask;
		switch (type) {
		case TaskEngine.INCREMENT_MONEY_TYPE:
			return (ext.getStaticIpIncrementMoney() - ext
					.getStaticIpAllocIncrementMoney()) > 0 ? true : false;
		case TaskEngine.INCREMENT_WATERAMY_TYPE:
			return (ext.getStaticIpIncrementWaterAmy() - ext
					.getStaticIpAllocIncrementWaterAmy()) > 0 ? true : false;
		case TaskEngine.STOCK_MONEY_TYPE:
			return (ext.getStockMoney() - ext.getAllotStockMoney()) > 0 ? true
					: false;
		case TaskEngine.STOCK_WATERAMY_TYPE:
			return (ext.getStockWaterAmy() - ext.getAllotStockWaterAmy()) > 0 ? true
					: false;
		}
		return false;
	}

	@Override
	public synchronized PhoneTask getTask(String deviceId, String remoteIp, String realIp) {
		PhoneTask pTask = null;
		logger.info("TaskStaticIpEngine getTask---000 |" + deviceId);
		for (StaticVpn vpn : mCurrentRunVpnList) {
			//ip一样并且VPN已经登陆过才能下发任务
			if (vpn.getConnectInfo().getIp().equals(remoteIp)&&true == vpn.getConnectInfo().isUsed()){
				if (vpn.getVpnTask().getRunType() == IS_RUN_STOCK) {//刷存量
					for (PhoneTask task : vpn.getVpnTask().getPhoneTaskList()) {
						if (task.getDeviceId().equals(deviceId)) { // 发现取过任务了
							if (task.getIsFinished() == 0) { // 任务没完成,返回原来的任务
								logger.info("getTask---end - repeat task not finished,return old task");
								task.setSendTimes(task.getSendTimes() + 1);
								return task;
							} else {
								logger.info("getTask---end - repeat task finished,return null task");
								return null;// 任务完成,这种情况下不给任务
							}
						}
						// 所有该Ip的量都已经保存在vpnTask 中了
						task.setOperType(vpn.getVpnTask().getOperType());
						task.setDeviceId(deviceId);
						
						for (Task dbTask : TaskEngine.getInstance().todayTaskList) {
							if (task.getApp().getId() == dbTask.getAppId()) {
								logger.info("updateAllocTask -- IS_RUN_STOCK ---operType:" + task.getOperType());
								mListen.updateAllocTask(task.getOperType(),
										TaskEngine.USED_STATIC_VPN, dbTask);
								break;
							}
						}
						return task;
					}
				} else { //刷增量
					for (PhoneTask task : vpn.getVpnTask().getPhoneTaskList()) {
						if (task.getDeviceId().equals(deviceId)) { // 发现取过任务了
							if (task.getIsFinished() == 0) { // 任务没完成,返回原来的任务
								logger.info("getTask---end - repeat task not finished,return old task");
								task.setSendTimes(task.getSendTimes() + 1);
								return task;
							} else {
								logger.info("getTask---end - repeat task finished,return null task");
								return null;// 任务完成,这种情况下不给任务
							}
						}
					}
					pTask = getTask(vpn.getVpnTask(),deviceId,vpn.getVpnTask().getOperType(),remoteIp,realIp);
					if(pTask != null){
						logger.info("getTask---002--"+pTask.toString());
						vpn.getVpnTask().getPhoneTaskList().add(pTask);
					}else{
						logger.info("getTask---not task");
					}
				}
			}
		}
		return pTask;
	}
	private synchronized PhoneTask getTask(VpnTask task,String deviceId,int operType,String remoteIp,String realIp){
		PhoneTask pTask = new PhoneTask();
		long appId = getOptimalAppId(task,remoteIp,realIp);
		if(appId == 0L){
			return null;
		}
		pTask.setDeviceId(deviceId);
		pTask.setVpnToken(task.getToken());
		pTask.setOperType(operType);
		pTask.setTaskId(task.getToken() + RandomUtils.getRandom(TaskEngine.PHONE_TASKID_LENGH/2));//32位
		pTask.setApp(TaskEngine.getInstance().getAppInfo(appId));
		pTask.setOperType(task.getOperType());
		pTask.setTimes(1);// 暂定一次
		pTask.setSpanTime(5);//暂定5S 
		pTask.setStockInfo(null);
		return pTask;
	}

	// 获得最优 appID
	private synchronized long getOptimalAppId(VpnTask task,String remoteIp,String realIp){
		// -- 排序
		logger.info("getOptimalAppId---000");
		logger.info("operType:"+task.getOperType()+"remoteIp:"+remoteIp+"realIp:"+realIp);
		TaskListSort.taskSort(TaskEngine.getInstance().todayTaskList, task.getOperType());//对任务列表排序
		{// 处理增量
			logger.info("getOptimalAppId---004--INCREMENT");
			int flag = 0;
			for(Task dbTask:TaskEngine.getInstance().todayTaskList){
				flag = 0;
				for(PhoneTask tmpTask:task.getPhoneTaskList()){
					if(tmpTask.getApp().getId() == dbTask.getAppId()){
						flag = 1;
						logger.info("getOptimalAppId---005-- INCREMENT existed");
						break;
					}
				}
				if(flag == 0 && isHavaTaskByOperType(task.getOperType(),dbTask)){
					logger.info("updateAllocTask -- IS_RUN_INCREMENT ---operType:" + task.getOperType());
					mListen.updateAllocTask(TaskEngine.USED_STATIC_VPN,task.getOperType(),dbTask); // 对应派发 ++ 
					logger.info("getOptimalAppId---end--return INCREMENT ");
					return dbTask.getAppId();
				}
			}
		}
		logger.info("getOptimalAppId---end--not task send ");
		return 0L;
	}
	@Override
	public String vpnLogin(VpnLoginReq req, String remoteIp, String realIp) {
		// TODO Auto-generated method stub
		// 此IP不能运行任务了
		TraceUtils.getTraceInfo();
		logger.info("remoteIp:" + remoteIp);
		if(false == IpMngEngine.getInstance().isCanGetTask(remoteIp)){
			logger.info("vpnLogin---001--isCanGetTask - over");
			return null;
		}
		for (StaticVpn vpn : mCurrentRunVpnList) {
			if (vpn.getConnectInfo().getIp().equals(remoteIp)) 
			{
				logger.info("vpnLogin---001--isCanGetTask - over");
				vpn.getConnectInfo().setUsed(true); // 设置资源被占用
				vpn.getVpnTask().setStatu(IP_CONNECTED);
				vpn.getVpnTask().setDeviceId(req.getDeviceId());
				vpn.getVpnTask().setCreateTime(new Date());
				// .. 这里指定设置 oper 
				
				for(int index = 0; index < 100; index++){
					int taskIndex = RandomUtils.getRandom(0, TaskEngine.getInstance().getTodayTaskList().size());
					int operType = 0;
					if(vpn.getVpnTask().getRunType() == IS_RUN_INCREMENT){
						operType = RandomUtils.getRandom(0, 2);
					}else{
						operType = RandomUtils.getRandom(2, 4);
					}
					TaskExtend dbTask = TaskEngine.getInstance().getTodayTaskList().get(taskIndex);
					if(true == isHavaTaskByOperType(operType, dbTask)) {
						vpn.getVpnTask().setOperType(operType);
						logger.info("set operType:" + operType + "| appId:" + dbTask.getAppId());
						break;
					}
				}
				return vpn.getVpnTask().getToken(); // 返回token
			}
		}
		return null;
	}
	@Override
	public synchronized VpnOperUpdateRsp vpnIsNeedSwitch(String token, String remoteIp,
			String realIp) {
		// TODO Auto-generated method stub
		TraceUtils.getTraceInfo();
		VpnOperUpdateRsp dataRsp = new VpnOperUpdateRsp();
		dataRsp.setIsSwitchVpn(1);
		for (StaticVpn vpn : mCurrentRunVpnList) {
			if (vpn.getConnectInfo().getIp().equals(remoteIp)) {
				if (vpn.getVpnTask().getPhoneTaskList() == null
						|| vpn.getVpnTask().getPhoneTaskList().size() == 0) {
					logger.info("vpnIsNeedSwitch---false---001---end");
					dataRsp.setIsSwitchVpn(0);
					dataRsp.setFinishedTaskCount(0);
					dataRsp.setTaskTotal(0);
				}
				dataRsp.setTaskTotal(vpn.getVpnTask().getPhoneTaskList().size());
				for (PhoneTask pTask : vpn.getVpnTask().getPhoneTaskList()) {
					dataRsp.setFinishedTaskCount(dataRsp.getFinishedTaskCount() + 1);
					if (pTask.getIsFinished() == 0
							&& false == TaskEngine.isTimeOut(vpn.getVpnTask())) { // 发现模拟器还有未完成的任务
						logger.info("vpnIsNeedSwitch---false---002---end");
						dataRsp.setIsSwitchVpn(0);
						dataRsp.setFinishedTaskCount(dataRsp
								.getFinishedTaskCount() - 1);
					}
				}
			}
		}
		// 需要切换VPN了
		if (dataRsp.getIsSwitchVpn() == 1 && mCurrentRunVpnList.size() > 0) {
			for (int i = mCurrentRunVpnList.size()-1; i >=0; i--){
				StaticVpn vpn = mCurrentRunVpnList.get(i);
				if (vpn.getConnectInfo().getIp().equals(remoteIp)) {
					vpn.getConnectInfo().setUsed(false);
					vpn.getVpnTask().setStatu(IP_FINISHED);
					// 同步到任务
					vpn.getVpnTask().getIpInfo().setSuccessCount(vpn.getVpnTask().getIpInfo().getSuccessCount()+1);
					updateRemainIpInfo(vpn.getVpnTask());
					mCurrentRunVpnList.remove(vpn);//删除这个已结束的VPN
					break;
				}
			}
			dataRsp.setConnectInfo(getConnectVpnInfo()); // 换一个新的VPN
		}
		logger.info("vpnIsNeedSwitch---true---end");
		return dataRsp;
	}
	private synchronized void updateRemainIpInfo(StaticVpnTask vpnTask){
		//------------登陆成功后更新IP信息至DB
		vpnTask.getIpInfo().setUpdateDate(new Date());
		remainIpService.update(vpnTask.getIpInfo());
		//-------------
		if(vpnTask.getRunType() == IS_RUN_INCREMENT){
			for(int index = 0;index < mIncrementIpList.size();index++){
				StaticVpnTask tmp =  mIncrementIpList.get(index);
				if(tmp.getToken().equals(vpnTask.getToken())){
					mIncrementIpList.set(index, vpnTask);
				}
			}
		}else{
			for(int index = 0;index < mStockIpList.size();index++){
				StaticVpnTask tmp =  mStockIpList.get(index);
				if(tmp.getToken().equals(vpnTask.getToken())){
					mStockIpList.set(index, vpnTask);
				}
			}
		}
	}
	@Override
	public boolean switchVpnFinish(String token, String remoteIp, String realIp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized void taskFinish(TaskFinishReq req, String remoteIp, String realIp) {
		// TODO Auto-generated method stub
		TraceUtils.getTraceInfo();
		logger.info("taskId:"+req.getTaskId()+"--remoteIp:"+remoteIp+"--realIp:"+realIp);

		for(StaticVpn task:mCurrentRunVpnList){
			if(task.getVpnTask().getToken().equals(req.getVpnToken())){
				for(PhoneTask tmpTask:task.getVpnTask().getPhoneTaskList()){
					if(tmpTask.getTaskId().equals(req.getTaskId())){
						logger.info("taskFinish---001--findandsettastkFinished");
						if(req.getIsFinished() == 1 ){
							tmpTask.setIsFinished(1);//找到任务并设为完成状态
							mListen.updateExecuteTask(TaskEngine.USED_STATIC_VPN,tmpTask); // 更新执行任务
						}
					}
				}
			}
		}
		logger.info("taskFinish---end");
	}

	private void createStaticIpTask() {
		createStockIpList();
		createIncrementIpList();
	}

	// 获得一个连接VPN信息 
	public synchronized VpnConnectInfoRsp getConnectVpnInfo() {
		StaticVpnTask task = null;
		int random = (int) (Math.random() * 10) + 1;
		int flag = 0;
		int vpnIndex = 0;
		TraceUtils.getTraceInfo();
		if (null == mCurrentRunVpnList || mCurrentRunVpnList.size() == 0) {
			logger.info("mCurrentRunVpnList is null or size = 0");
			return null;
		}
		//1.看有没有可用的vpn资源
		for (StaticVpn staticVpn : mCurrentRunVpnList) {
			VpnConnectInfoRsp conInfo = staticVpn.getConnectInfo();
			if (conInfo.isUsed() == true) {
				logger.info("mCurrentRunVpnList is be used");
				flag = 1;
			} else {
				logger.info("mCurrentRunVpnList is not be used");
				flag = 0;
				break;
			}
			vpnIndex++;
		}
		if (flag == 1) { // 当前无可用资源VPN
			logger.info("sorry,mCurrentRunVpnList is all be used");
			return null;
		}
		if (random % 2 == 0) {
			logger.info("get info from mIncrementIpList");
			task = getCanUsedVpn(mIncrementIpList);
			if (task == null) {
				logger.info("get info from mStockIpList");
				task = getCanUsedVpn(mStockIpList);
			}
		} else {
			logger.info("get info from mStockIpList");
			task = getCanUsedVpn(mStockIpList);
			if (task == null) {
				logger.info("get info from mIncrementIpList");
				task = getCanUsedVpn(mIncrementIpList);
			}
		}
		if (task == null) {// 全部静态IP都跑完了
			logger.info("sorry, all ip is run finished");
			return null;
		}
		mCurrentRunVpnList.get(vpnIndex).setVpnTask(task);
		mCurrentRunVpnList.get(vpnIndex).getConnectInfo().setIp(task.getIpInfo().getIp());
		List<VpnConnectInfo> vpnList =  JSON.parseObject(task.getIpInfo().getConfigure(),
				new TypeReference<List<VpnConnectInfo>>() {
				});
		mCurrentRunVpnList.get(vpnIndex).getConnectInfo().setVpnList(vpnList);
		logger.info("return a can user info");
		return mCurrentRunVpnList.get(vpnIndex).getConnectInfo();
	}

	// 获取一个可以使用的VPN
	private synchronized StaticVpnTask getCanUsedVpn(List<StaticVpnTask> list) {
		for (StaticVpnTask process : list) {
			if (process.getStatu() == IP_INIT) { // 发现还没下发的IP
				process.setStatu(IP_SEND);
				process.setCreateTime(new Date());
				process.setSendCount(process.getSendCount() + 1);
				process.getIpInfo().setAllocCount(process.getIpInfo().getAllocCount()+1);
				return process;
			}
		}
		return null;
	}
	private void createIncrementIpList() {
		int moneyCount = 0;
		int waterCount = 0;
		if(null == TaskEngine.getInstance().getTodayTaskList() || TaskEngine.getInstance().getTodayTaskList().size() == 0)
			return ;
			
		for (TaskExtend task : TaskEngine.getInstance().getTodayTaskList()) {
			moneyCount = moneyCount + task.getStaticIpIncrementMoney();
			waterCount = waterCount + task.getStaticIpIncrementWaterAmy();
		}

		int incrementTotalCount =(moneyCount + waterCount)/TaskEngine.getInstance().getTodayTaskList().size();

		int flag = 0;
		int index = 0;
		for (int yiwan = 0; yiwan < 10000; yiwan++) { 
			// ip 不能在 stock中存在
			RemainIp remainIp = getRandomRemainIp();// 随机取
			flag = 0;
			for (VpnTask tast : mStockIpList) {
				StaticVpnTask process = (StaticVpnTask) tast;
				if (remainIp.getIp().equals(process.getIpInfo().getIp())) { // 如果发现相等的IP
					flag = 1;
					break;
				}
			}
			//ip也不能在 stock中存在
			for (VpnTask tast : mIncrementIpList) {
				StaticVpnTask process = (StaticVpnTask) tast;
				if (remainIp.getIp().equals(process.getIpInfo().getIp())&&flag==0) { // 如果发现相等的IP
					flag = 1;
					break;
				}
			}
			if(flag==0){
				StaticVpnTask ipProcess = new StaticVpnTask();
				ipProcess.setRunType(IS_RUN_INCREMENT);
				ipProcess.setIpInfo(remainIp);
				ipProcess.setToken(RandomUtils
						.getRandom(TaskEngine.VPN_TOKEN_LENGH));
				mIncrementIpList.add(ipProcess);
				index++;
				if(index >= incrementTotalCount){
					return;
				}
			}
		}
	}

	private PhoneTask createPhoneTask(StaticVpnTask process, Device device) {
		PhoneTask task = new PhoneTask();
		task.setStockInfo(device);// 设置好 设备信息
		task.setApp(TaskEngine.getInstance().getAppInfo(device.getAppId())); // 设置好app信息
		task.setVpnToken(process.getToken());
		task.setTaskId(process.getToken()
				+ RandomUtils.getRandom(TaskEngine.PHONE_TASKID_LENGH / 2));// 32位
		return task;
	}

	private void createStockIpList() {
		int flag = 0;
		// --
		RemainEngine.getInstance().generateRemainFile();
		for (App app : TaskEngine.getInstance().getAppList()) { // 轮询每个文件
			String todayPath = RemainEngine.getInstance().getAppRemainFilePath(new Date(), app);
			List<Device> deviceList = new ArrayList<Device>();
			if (FileUtil.isFileExist(todayPath)) {
				File file = new File(todayPath);
				try {
					String json = FileUtils.readFileToString(file);
					deviceList = JSON.parseObject(json,
							new TypeReference<ArrayList<Device>>() {
							});
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (Device device : deviceList) {
				List<StaticVpnTask> list = mStockIpList;
				flag = 0;
				for (StaticVpnTask process : list) {
					if (device.getIp().equals(process.getIpInfo().getIp())) { // 如果Ip相等
						PhoneTask task = createPhoneTask(process, device);
						process.getPhoneTaskList().add(task);
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					StaticVpnTask process = new StaticVpnTask();
					process.setRunType(IS_RUN_STOCK);
					process.setIpInfo(findRemainIpByIp(device.getIp()));
					process.setToken(RandomUtils
							.getRandom(TaskEngine.VPN_TOKEN_LENGH));
					PhoneTask task = createPhoneTask(process, device);
					process.getPhoneTaskList().add(task);
					list.add(process);
				}
			}
		}
	}

	private void clear() {
		if(null != mIncrementIpList)
			mIncrementIpList.clear();
		
		if(null != mStockIpList)
			mStockIpList.clear();
		
		if(null != mCurrentRunVpnList)
			mCurrentRunVpnList.clear();
	}

	private void setDataUpdateListen(DateUpdateListen listen) {
		mListen = listen;
	}
	@Override
	public void CheckVpnTimeoutJob() {
		// TODO Auto-generated method stub
		TraceUtils.getTraceInfo();
		if(null == mCurrentRunVpnList || mCurrentRunVpnList.size() == 0){
			return;
		}
		for (int i = mCurrentRunVpnList.size()-1; i >=0; i--){
			StaticVpn vpn = mCurrentRunVpnList.get(i);
			if(null == vpn.getVpnTask() )
				return;
			
			if(vpn.getVpnTask().getStatu() == IP_CONNECTED && true == TaskEngine.isTimeOut(vpn.getVpnTask())){
				vpn.getVpnTask().setStatu(IP_TIMEOUT);
				vpn.getVpnTask().getIpInfo().setSuccessCount(vpn.getVpnTask().getIpInfo().getSuccessCount()+1);
				updateRemainIpInfo(vpn.getVpnTask());
				mCurrentRunVpnList.remove(vpn);//删除这个已结束的VPN
				logger.info("remove isTimeOut task");
			}else{
				if(true == TaskEngine.isFreeTimeOut(vpn.getVpnTask())){
					updateRemainIpInfo(vpn.getVpnTask());
					mCurrentRunVpnList.remove(vpn);//删除这个已结束的VPN
					logger.info("remove isFreeTimeOut task");
				}
			}
		}
	}
}
*/
