package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.turing.pangu.bean.TaskConfigureBean;
import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.pc.response.VpnOperUpdateRsp;
import org.turing.pangu.controller.phone.request.TaskFinishReq;
import org.turing.pangu.model.Task;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.TaskService;
import org.turing.pangu.service.TaskServiceImpl;
import org.turing.pangu.task.DateUpdateListen;
import org.turing.pangu.task.TaskExtend;
import org.turing.pangu.task.VpnTask;
import org.turing.pangu.utils.DateUtils;
/*
 * 任务引擎，负责每日任务生成,配置任务,跟踪任务进展,
 * */
public class TaskEngine implements DateUpdateListen,EngineListen{
	private static final Logger logger = Logger.getLogger(TaskEngine.class);
	private static TaskEngine mInstance = new TaskEngine();
	private List<Task> allTaskList = new ArrayList<Task>();
	public List<TaskExtend> todayTaskList = new ArrayList<TaskExtend>();

	public static final int INCREMENT_MONEY_TYPE = 0;//操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	public static final int INCREMENT_WATERAMY_TYPE = 1;//操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	public static final int STOCK_MONEY_TYPE = 2;//操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	public static final int STOCK_WATERAMY_TYPE = 3;//操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军	
	public static final int VPN_TOKEN_LENGH = 16;
	public static final int PHONE_TASKID_LENGH = 32;
	
	public static final int USED_STATIC_VPN = 0;//操作类型  0:静态VPN 1:动态VPN
	public static final int USED_DYNAMIC_VPN = 1;
	private TaskService taskService;
	public static TaskEngine getInstance(){
		if(null == mInstance)
			mInstance = new TaskEngine();
		return mInstance;
	}
	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
		for(BaseService service :serviceList){
			if(service instanceof TaskServiceImpl ){
				this.taskService = (TaskService)service;
			}
		}
	}
	
	public List<TaskExtend> getTodayTaskList(){
		return todayTaskList;
	}
	public void init(){
		todayTaskListInit();
		allTaskList.clear();
		allTaskList = taskService.selectAll();
		IpMngEngine.getInstance().clearIpList();
		TaskDynamicIpEngine.getInstance().init(this);
	}
	private void todayTaskListInit(){
		Date fromTime = DateUtils.getTimesMorning();
		Date toTime = new Date();
		todayTaskList.clear();
		List<Task> listFromDB = getTodayTaskList(fromTime, toTime);
		for(Task task:listFromDB){
			TaskExtend tExd = new TaskExtend();
			tExd.setDBTask(task);
			int fixedIpIncrementMoney = task.getIncrementMoney() / 10;
			int fixedIpIncrementWaterAmy = task.getIncrementWaterAmy() / 10;
			tExd.setStaticIpIncrementMoney(fixedIpIncrementMoney);
			tExd.setStaticIpIncrementWaterAmy(fixedIpIncrementWaterAmy);
			tExd.setDynamicIpIncrementMoney(task.getIncrementMoney() - fixedIpIncrementMoney);
			tExd.setDynamicIpIncrementWaterAmy(task.getIncrementWaterAmy() - fixedIpIncrementWaterAmy);
			todayTaskList.add(tExd);
		}
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
	//检查VPN连接超时的job
	public void checkVpnTimeoutJob(){
		TaskDynamicIpEngine.getInstance().CheckVpnTimeoutJob();
	}
	// 更新缓存任务信息至数据库
	public void updateTaskToDBJob(){
		for(Task task:todayTaskList){
			task.setUpdateDate(new Date());
			taskService.update(task);
		}
	}
	public synchronized String vpnLogin(VpnLoginReq req,String remoteIp,String realIp){
		String ret = null;
		ret = TaskDynamicIpEngine.getInstance().vpnLogin(req, remoteIp, realIp);
		return ret;
	}

	public synchronized VpnOperUpdateRsp vpnIsNeedSwitch(String token,String remoteIp,String realIp){
		return TaskDynamicIpEngine.getInstance().vpnIsNeedSwitch(token,remoteIp,realIp);
	}
	public synchronized boolean switchVpnFinish(String token,String remoteIp,String realIp){

		return TaskDynamicIpEngine.getInstance().switchVpnFinish(token,remoteIp,realIp);
	}
	
	// 手机端一个任务完成
	public synchronized void taskFinish(TaskFinishReq req,String remoteIp,String realIp){
		TaskDynamicIpEngine.getInstance().taskFinish(req,remoteIp,realIp);
	}
	//取一个任务
	public synchronized PhoneTask getTask(String deviceId,String remoteIp,String realIp){
		return TaskDynamicIpEngine.getInstance().getTask(deviceId,remoteIp,realIp);
	}
	
	// 创建今日任务
	public void createTodayTask(){
		logger.info("createTodayTask---000");
		List<TaskConfigureBean> list = TaskConfigureEngine.getInstance().getAllAppConfigure();
		updateTaskToDBJob(); // 先保存
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("createTodayTask---001");
		Task checkModel = new Task();
		//先查下今天记录
		List<Task> listFromDB = getTodayTaskList(DateUtils.getTimesMorning(), DateUtils.getTimesNight());
		if(null == listFromDB || listFromDB.size() == 0){
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
		}
		logger.info("createTodayTask---002");
		todayTaskListInit();
		allTaskList.clear();
		allTaskList = taskService.selectAll();
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
	public void updateExecuteTask(PhoneTask pTask,Boolean isSuccess){
		logger.info("updateExecute---000");
		for(TaskExtend task : todayTaskList){
			if(pTask.getApp().getId() == task.getAppId()){
				logger.info("updateExecute---001--appId:"+pTask.getApp().getId());
				switch(pTask.getOperType()){
				case INCREMENT_MONEY_TYPE:
					if(isSuccess)
						task.setExecuteIncrementMoney(task.getExecuteIncrementMoney() + 1);
					else
						task.setExecuteIncrementMoneyFail(task.getExecuteIncrementMoneyFail() + 1);
					break;
				case INCREMENT_WATERAMY_TYPE:
					if(isSuccess)
						task.setExecuteIncrementWaterAmy(task.getExecuteIncrementWaterAmy() + 1);
					else
						task.setExecuteIncrementWaterAmy_fail(task.getExecuteIncrementWaterAmy_fail() + 1);
					break;
				case STOCK_MONEY_TYPE:	
					if(isSuccess)
						task.setExecuteStockMoney(task.getExecuteStockMoney() + 1);
					else
						task.setExecuteStockMoneyFail(task.getExecuteStockMoneyFail() + 1);
					break;
				case STOCK_WATERAMY_TYPE:
					if(isSuccess)
						task.setExecuteStockWaterAmy(task.getExecuteStockWaterAmy() + 1);
					else
						task.setExecuteStockWaterAmy_fail(task.getExecuteStockWaterAmy_fail() + 1);
					break;
				}
			}
		}
		logger.info("updateExecute---end");
	}
	public void updateAllocTask(int type,Task taskExt){
		TaskExtend task = (TaskExtend)taskExt;
		switch(type){
		case INCREMENT_MONEY_TYPE:
			task.setAllotIncrementMoney(task.getAllotIncrementMoney() + 1);
			break;
		case INCREMENT_WATERAMY_TYPE:
			task.setAllotIncrementWaterAmy(task.getAllotIncrementWaterAmy() + 1);
			break;
		case STOCK_MONEY_TYPE:	
			task.setAllotStockMoney(task.getAllotStockMoney() + 1);
			break;
		case STOCK_WATERAMY_TYPE:
			task.setAllotStockWaterAmy(task.getAllotStockWaterAmy() + 1);
			break;
		}
		synchronizedAppTaskData(task);
	}
	private synchronized void synchronizedAppTaskData(Task dbTask){
		int count = 0;
		for(Task tmp :allTaskList){
			if(tmp.getId() == dbTask.getId()){
				allTaskList.set(count, dbTask);
			}
			count++;
		}
	}
	
	public static boolean isFreeTimeOut(VpnTask task){
		return (new Date().getTime() - task.getCreateTime().getTime() > TimeZoneMng.FREE_TIMEOUT)?true:false;
	}
	public static boolean isTimeOut(VpnTask task){
		Date nowTime = new Date();
		switch(task.getOperType()){
		case TaskEngine.INCREMENT_MONEY_TYPE:
			return (nowTime.getTime() - task.getCreateTime().getTime() > TimeZoneMng.INCREMENT_MONEY_TIMEOUT)?true:false;
		case TaskEngine.INCREMENT_WATERAMY_TYPE:
			return (nowTime.getTime() - task.getCreateTime().getTime() > TimeZoneMng.INCREMENT_WATERAMY_TIMEOUT)?true:false;	
		case TaskEngine.STOCK_MONEY_TYPE:	
			return (nowTime.getTime() - task.getCreateTime().getTime() > TimeZoneMng.STOCK_MONEY_TIMEOUT)?true:false;
		case TaskEngine.STOCK_WATERAMY_TYPE:
			return (nowTime.getTime() - task.getCreateTime().getTime() > TimeZoneMng.STOCK_WATERAMY_TIMEOUT)?true:false;
		}
		return true;
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
