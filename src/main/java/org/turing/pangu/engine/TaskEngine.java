package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.turing.pangu.bean.TaskConfigureBean;
import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.controller.common.VpnTask;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.pc.response.VpnOperUpdateRsp;
import org.turing.pangu.controller.phone.request.TaskFinishReq;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.Platform;
import org.turing.pangu.model.RemainVpn;
import org.turing.pangu.model.Task;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.service.PlatformService;
import org.turing.pangu.service.RemainVpnService;
import org.turing.pangu.service.TaskService;
import org.turing.pangu.utils.DateUtils;
import org.turing.pangu.utils.RandomUtils;
/*
 * 任务引擎，负责每日任务生成,配置任务,跟踪任务进展,
 * */
public class TaskEngine {
	private static final Logger logger = Logger.getLogger(TaskEngine.class);
	private static TaskEngine mInstance = new TaskEngine();
	private List<VpnTask> vpnTaskList = new ArrayList<VpnTask>();
	private List<Task> allTaskList = new ArrayList<Task>();
	private List<Task> todayTaskList = new ArrayList<Task>();
	private List<App> appList = new ArrayList<App>();
	private List<RemainVpn> whiteIpList = new ArrayList<RemainVpn>();
	private List<Platform> platformList = new ArrayList<Platform>();
	public static final int INCREMENT_MONEY_TYPE = 0;//操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	public static final int INCREMENT_WATERAMY_TYPE = 1;//操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	public static final int STOCK_MONEY_TYPE = 2;//操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	public static final int STOCK_WATERAMY_TYPE = 3;//操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军	
	private Device mDevice = null; // 记录找到的存量信息
	private int vpnTokenLengh = 16;
	private int phoneTaskIdLengh = 32;
	private PlatformService platformService;
	private AppService appService;
	private DeviceService deviceService;
	private TaskService taskService;
	private RemainVpnService remainVpnService;
	public static TaskEngine getInstance(){
		if(null == mInstance)
			mInstance = new TaskEngine();
		return mInstance;
	}

	public void setService(RemainVpnService remainVpnService,PlatformService platformService,AppService appService,DeviceService deviceService,TaskService taskService){
		this.platformService = platformService;
		this.appService = appService;
		this.deviceService = deviceService;
		this.taskService = taskService;
		this.remainVpnService = remainVpnService;
	}
	public List<App> getAppList(){
		return appList;
	}
	public void init(){
		if(null != appService){
			App model = new App();
			model.setIsCanRun(1);
			appList = appService.selectCanRunApps(model);
		}
		if(null != platformService){
			platformList = platformService.selectAll();
		}
		if(null != remainVpnService){
			whiteIpList = remainVpnService.selectAll();
		}
		Date fromTime = DateUtils.getTimesMorning();
		Date toTime = new Date();
		
		todayTaskList.clear();
		todayTaskList = getTodayTaskList(fromTime, toTime);
		allTaskList.clear();
		allTaskList = taskService.selectAll();
		
		vpnTaskList.clear();
		IpMngEngine.getInstance().clearIpList();
	}
	public boolean isWhiteIp(String ip){
		for(RemainVpn remain :whiteIpList){
			if(remain.getIpList().contains(ip)){
				return true;
			}
		}
		return false;
	}
	public Platform getPlatformInfo(long pfId){
		for(Platform pf:platformList){
			if(pf.getId() == pfId){
				return pf;
			}
		}
		return null;
	}
	public List<Task> getAllDBTaskByAppId(long appId){
		List<Task> list = new ArrayList<Task>();
		for(Task task:allTaskList){
			if(task.getAppId() == appId){
				list.add(task);
			}
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
	public synchronized String getRemoteIp(HttpServletRequest request){
		String ip = request.getHeader("X-Real-IP"); 
		if(null == ip ){
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/** 
	   * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
	   * 
	   * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
	   * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
	   * 
	   * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
	   * 192.168.1.100 
	   * 
	   * 用户真实IP为： 192.168.1.110 
	   * 
	   * @param request 
	   * @return 
	   */ 
	public synchronized String getRealIp(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	}
	// 更新任务配置
	public boolean updateTaskConfigure(){
		return true;
	}
	// 更新缓存任务信息至数据库
	public void UpdateTaskToDBJob(){
		for(Task task:todayTaskList){
			task.setUpdateDate(new Date());
			taskService.update(task);
		}
	}
	public synchronized String addVpnTask(VpnLoginReq req,String remoteIp,String realIp){
		logger.info("addVpnTask---000");
		// 此IP不能运行任务了
		if(false == IpMngEngine.getInstance().isCanGetTask(remoteIp)){
			return null;
		}
		VpnTask task = new VpnTask();
		task.setDeviceId(req.getDeviceId());
		task.setOperType(req.getOperType());
		task.setRemoteIp(remoteIp);
		task.setRealIp(realIp);
		task.setToken(RandomUtils.getRandom(vpnTokenLengh));
		task.setCreateTime(new Date());
		logger.info("addVpnTask---001--"+task.toString());
		for (int i = vpnTaskList.size()-1; i >=0; i--){
			VpnTask tmp = vpnTaskList.get(i);
			if(tmp.getDeviceId().equals(task.getDeviceId())){
				vpnTaskList.remove(tmp); // 删除原来的VPN task
				logger.info("addVpnTask---002--del old task");
			}
		}
		vpnTaskList.add(task);
		logger.info("addVpnTask---end");
		return task.getToken();
	}

	public synchronized VpnOperUpdateRsp vpnIsNeedSwitch(String token){
		logger.info("vpnIsNeedSwitch---000--"+token);
		VpnOperUpdateRsp dataRsp = new VpnOperUpdateRsp();
		dataRsp.setIsSwitchVpn(1);
		for(VpnTask task :vpnTaskList){
			if(task.getToken().equals(token)){
				if(task.getPhoneTaskList() == null || task.getPhoneTaskList().size() == 0){
					logger.info("vpnIsNeedSwitch---false---001---end");
					dataRsp.setIsSwitchVpn(0);
					dataRsp.setFinishedTaskCount(0);
					dataRsp.setTaskTotal(0);
				}
				dataRsp.setTaskTotal(task.getPhoneTaskList().size());
				for(PhoneTask pTask:task.getPhoneTaskList()){
					dataRsp.setFinishedTaskCount(dataRsp.getFinishedTaskCount()+1);
					if(pTask.getIsFinished() == 0 && false == isTimeOut(task)){ // 发现模拟器还有未完成的任务
						logger.info("vpnIsNeedSwitch---false---002---end");
						dataRsp.setIsSwitchVpn(0);
						dataRsp.setFinishedTaskCount(dataRsp.getFinishedTaskCount()-1);
					}
				}
			}
		}
		logger.info("vpnIsNeedSwitch---true---end");
		return dataRsp;
	}
	public synchronized boolean switchVpnFinish(String token,String remoteIp,String realIp){
		// 此IP不能运行任务了
		if(false == IpMngEngine.getInstance().isCanGetTask(remoteIp)){
			return false;
		}
		logger.info("switchVpnFinish---000");
		for(VpnTask task :vpnTaskList){
			if(task.getToken().equals(token)){
				if(remoteIp.equals(task.getRemoteIp())){
					logger.info("switchVpnFinish|newRemoteIp == oldRemoteIp");
				}
				if(realIp.equals(task.getRealIp())){
					logger.info("switchVpnFinish|newRealIp == oldRealIp");
				}
				logger.info("switchVpnFinish|remoteIp:"+remoteIp+"|realIp:"+realIp);
				task.setRemoteIp(remoteIp);
				task.getPhoneTaskList().clear();//清空所有任务
				task.setRealIp(realIp);
				task.setCreateTime(new Date());//重新设置超时起点时间
				break;
			}
		}
		logger.info("switchVpnFinish---end");
		return true;
	}
	// 创建今日任务
	public void createTodayTask(){
		logger.info("createTodayTask---000");
		List<TaskConfigureBean> list = TaskConfigureEngine.getInstance().getAllAppConfigure();
		UpdateTaskToDBJob(); // 先保存
		Date fromTime = new Date(); //开始时间
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("createTodayTask---001");
		for(TaskConfigureBean bean :list){
			Task model = new Task();
			model.init();//把变量设为0
			model.setAppId(bean.getAppId());
			model.setIncrementMoney(bean.getIncrementMoney());
			model.setIncrementWaterAmy(bean.getIncrementWaterAmy());
			model.setStockMoney(bean.getStockMoney());
			model.setStockWaterAmy(bean.getStockWaterAmy());
			model.setCreateDate(new Date());
			model.setUpdateDate(new Date());
			taskService.insert(model);
		}
		logger.info("createTodayTask---002");
		Date toTime = new Date(); // 结束时间 ,取这段时间插入的数据
		todayTaskList.clear();//清空
		todayTaskList = getTodayTaskList(fromTime,toTime);
		IpMngEngine.getInstance().clearIpList();
		
		logger.info("createTodayTask---end");
	}
	private List<Task> getTodayTaskList(Date fromTime,Date toTime){
		logger.info("getTodayTaskList---000");
		Task selectModel = new Task();
		selectModel.setCreateDate(fromTime);
		selectModel.setUpdateDate(toTime);
		List<Task> list = taskService.selectTodayTaskList(selectModel);
		logger.info("getTodayTaskList---end");
		return list;
	}
	// 手机端一个任务完成
	public synchronized void taskFinish(TaskFinishReq req,String remoteIp,String realIp){
		logger.info("taskFinish---000");
		logger.info("taskFinish---taskId:"+req.getTaskId()+"--remoteIp:"+remoteIp+"--realIp:"+realIp);
		for(VpnTask task:vpnTaskList){
			if(task.getToken().equals(req.getVpnToken())){
				for(PhoneTask tmpTask:task.getPhoneTaskList()){
					if(tmpTask.getTaskId().equals(req.getTaskId())){
						logger.info("taskFinish---001--findandsettastkFinished");
						if(req.getIsFinished() == 1 ){
							tmpTask.setIsFinished(1);//找到任务并设为完成状态
							updateExecute(tmpTask); // 更新执行任务
						}
					}
				}
			}
		}
		logger.info("taskFinish---end");
	}
	//取一个任务
	public synchronized PhoneTask getTask(String deviceId,String remoteIp,String realIp){
		// 1. 先查VPN用途
		PhoneTask pTask = null;
		logger.info("getTask---000");
		logger.info("getTask---001--deviceId:"+deviceId+"--remoteIp:"+remoteIp+"--realIp:"+realIp);
		for(VpnTask task:vpnTaskList){
			if(task.getRemoteIp().equals(remoteIp)){
				if(task.getPhoneTaskList().size() > appList.size() * TimeZoneMng.getInstance().getTimeZoneWeight() ){
					return pTask;
				}
				for(PhoneTask tmpTask:task.getPhoneTaskList()){
					if(tmpTask.getDeviceId().equals(deviceId)){ // 发现取过任务了
						if(tmpTask.getIsFinished() == 0){ // 任务没完成,返回原来的任务
							logger.info("getTask---end - repeat task not finished,return old task");
							return tmpTask; 
						}
						else{
							logger.info("getTask---end - repeat task finished,return null task");
							return null;// 任务完成,这种情况下不给任务
						}
					}
				}
				pTask = getTask(task,deviceId,task.getOperType(),remoteIp,realIp);
				if(pTask != null){
					logger.info("getTask---002--"+pTask.toString());
					task.getPhoneTaskList().add(pTask);
				}else{
					logger.info("getTask---not task");
				}
			}
		}
		
		logger.info("getTask---end");
		return pTask;
	}
	
	private synchronized boolean isTimeOut(VpnTask task){
		Date nowTime = new Date();
		logger.info("isTimeOut---000--OperType:"+task.getOperType());
		switch(task.getOperType()){
		case INCREMENT_MONEY_TYPE:
			return (nowTime.getTime() - task.getCreateTime().getTime() > TimeZoneMng.INCREMENT_MONEY_TIMEOUT)?true:false;
		case INCREMENT_WATERAMY_TYPE:
			return (nowTime.getTime() - task.getCreateTime().getTime() > TimeZoneMng.INCREMENT_WATERAMY_TIMEOUT)?true:false;	
		case STOCK_MONEY_TYPE:	
			return (nowTime.getTime() - task.getCreateTime().getTime() > TimeZoneMng.STOCK_MONEY_TIMEOUT)?true:false;
		case STOCK_WATERAMY_TYPE:
			return (nowTime.getTime() - task.getCreateTime().getTime() > TimeZoneMng.STOCK_WATERAMY_TIMEOUT)?true:false;
		}
		return true;
	}
	private void updateExecute(PhoneTask pTask){
		logger.info("updateExecute---000");
		for(Task task : todayTaskList){
			if(pTask.getAppId() == task.getAppId()){
				logger.info("updateExecute---001--appId:"+pTask.getAppId());
				switch(pTask.getOperType()){
				case INCREMENT_MONEY_TYPE:
					task.setExecuteIncrementMoney(task.getExecuteIncrementMoney() + 1);
					break;
				case INCREMENT_WATERAMY_TYPE:
					task.setExecuteIncrementWaterAmy(task.getExecuteIncrementWaterAmy() + 1);
					break;
				case STOCK_MONEY_TYPE:	
					task.setExecuteStockMoney(task.getExecuteStockMoney() + 1);
					break;
				case STOCK_WATERAMY_TYPE:
					task.setExecuteStockWaterAmy(task.getExecuteStockWaterAmy() + 1);
					break;
				}
			}
		}
		logger.info("updateExecute---end");
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
		pTask.setTaskId(task.getToken() + RandomUtils.getRandom(phoneTaskIdLengh/2));//32位
		pTask.setAppId(appId);
		pTask.setOperType(task.getOperType());
		pTask.setTimes(1);// 暂定一次
		pTask.setSpanTime(5);//暂定5S 
		pTask.setStockInfo(mDevice);
		return pTask;
	}

	// 获得最优 appID
	private synchronized long getOptimalAppId(VpnTask task,String remoteIp,String realIp){
		// -- 排序
		mDevice = null;
		logger.info("getOptimalAppId---000");
		logger.info("getOptimalAppId---001--operType:"+task.getOperType()+"remoteIp:"+remoteIp+"realIp:"+realIp);
		TaskListSort.taskSort(todayTaskList, task.getOperType());//对任务列表排序
		// 处理存量
		if(task.getOperType() == STOCK_MONEY_TYPE || task.getOperType() == STOCK_WATERAMY_TYPE ){
			logger.info("getOptimalAppId---002--STOCK");
			Device device = new Device();
			device.setIp(remoteIp);
			List<Device> deviceList = deviceService.selectStockByIp(device);
			if(null == deviceList || deviceList.size() == 0){
				logger.info("getOptimalAppId---003--not STOCK");
				return 0L;
			}
			int flag = 0;
			/*算法核心:
			 * 一个IP只能运行一个APP,即便找到了很多符合条件的device,但只运行一次
			 * */
			for(Device dev:deviceList){
				for(Task dbTask:todayTaskList){
					flag = 0;
					for(PhoneTask tmpTask:task.getPhoneTaskList()){
						if(tmpTask.getAppId() == dbTask.getAppId()){
							flag = 1;
							logger.info("getOptimalAppId---005-- stock existed in run");
							break;
						}
					}
					if(flag == 0 && dev.getAppId() == dbTask.getAppId() && isHavaTaskByOperType(task.getOperType(),dbTask)){
						int random = (int)(1+Math.random()*(10-1+1));
						if(random%2 == 0)// 当有很多条数据时 50% 方式衰减
						{ 
							updateAllocTask(task.getOperType(),dbTask); // 对应派发 ++ 
							mDevice = dev; // 保存好不容易找到的存量信息，函数外赋值。
							logger.info("getOptimalAppId---end--find STOCK mDevice:"+mDevice.toString());
							return dbTask.getAppId();
						}
					}
				}
			}
			logger.info("getOptimalAppId---end--not match STOCK");
			return 0L;
		}else{// 处理增量
			logger.info("getOptimalAppId---004--INCREMENT");
			int flag = 0;
			for(Task dbTask:todayTaskList){
				flag = 0;
				for(PhoneTask tmpTask:task.getPhoneTaskList()){
					if(tmpTask.getAppId() == dbTask.getAppId()){
						flag = 1;
						logger.info("getOptimalAppId---005-- INCREMENT existed");
						break;
					}
				}
				if(flag == 0 && isHavaTaskByOperType(task.getOperType(),dbTask)){
					updateAllocTask(task.getOperType(),dbTask); // 对应派发 ++ 
					logger.info("getOptimalAppId---end--return INCREMENT ");
					return dbTask.getAppId();
				}
			}
		}
		logger.info("getOptimalAppId---end--not task send ");
		return 0L;
	}
	private void updateAllocTask(int type,Task dbTask){
		switch(type){
		case INCREMENT_MONEY_TYPE:
			dbTask.setAllotIncrementMoney(dbTask.getAllotIncrementMoney() + 1);
			break;
		case INCREMENT_WATERAMY_TYPE:
			dbTask.setAllotIncrementWaterAmy(dbTask.getAllotIncrementWaterAmy() + 1);
			break;
		case STOCK_MONEY_TYPE:	
			dbTask.setAllotStockMoney(dbTask.getAllotStockMoney() + 1);
			break;
		case STOCK_WATERAMY_TYPE:
			dbTask.setAllotStockWaterAmy(dbTask.getAllotStockWaterAmy() + 1);
			break;
		}
		synchronizedAppTaskData(dbTask);
	}
	private synchronized void synchronizedAppTaskData(Task dbTask){
		int count = 0;
		for(Task tmp :allTaskList){
			if(tmp.getId() == dbTask.getId()){
				allTaskList.set(count, dbTask);
			}
		}
	}
	// 是否还有该类型的任务
	private synchronized boolean isHavaTaskByOperType(int type,Task dbTask){
		switch(type){
		case INCREMENT_MONEY_TYPE:
			return (dbTask.getIncrementMoney() - dbTask.getAllotIncrementMoney()) > 0 ? true:false;
		case INCREMENT_WATERAMY_TYPE:
			return (dbTask.getIncrementWaterAmy() - dbTask.getAllotIncrementWaterAmy()) > 0 ? true:false;
		case STOCK_MONEY_TYPE:	
			return (dbTask.getStockMoney() - dbTask.getAllotStockMoney()) > 0 ? true:false;
		case STOCK_WATERAMY_TYPE:
			return (dbTask.getStockWaterAmy() - dbTask.getAllotStockWaterAmy()) > 0 ? true:false;
		}
		return false;
	}

	
	
	
	

	
}
