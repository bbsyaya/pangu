package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.controller.common.VpnTask;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.pc.request.VpnSwitchFinishReq;
import org.turing.pangu.controller.pc.response.VpnLoginRsp;
import org.turing.pangu.controller.phone.request.TaskFinishReq;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.RemainVpn;
import org.turing.pangu.model.Task;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.service.PlatformService;
import org.turing.pangu.service.RemainDataService;
import org.turing.pangu.utils.RandomUtils;
/*
 * 任务引擎，负责每日任务生成,配置任务,跟踪任务进展,
 * */
public class TaskEngine {
	private static TaskEngine mInstance = new TaskEngine();
	private List<VpnTask> vpnTaskList = new ArrayList<VpnTask>();
	private List<Task> todayTaskList = new ArrayList<Task>();
	public static final int SPAN_TIME = 10;// 10S 
	public static final int INCREMENT_MONEY_TYPE = 0;//操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	public static final int INCREMENT_WATERAMY_TYPE = 1;//操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	public static final int STOCK_MONEY_TYPE = 3;//操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	public static final int STOCK_WATERAMY_TYPE = 4;//操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	
	public static final int INCREMENT_MONEY_TIMEOUT = 5*60*1000;
	public static final int INCREMENT_WATERAMY_TIMEOUT = 3*60*1000;
	public static final int STOCK_MONEY_TIMEOUT = 5*60*1000;
	public static final int STOCK_WATERAMY_TIMEOUT = 3*60*1000;
	
	private Device mDevice = null; // 记录找到的存量信息
	private int vpnTokenLengh = 16;
	private int phoneTaskIdLengh = 32;
	private PlatformService platformService;
	private AppService appService;
	private DeviceService deviceService;
	private RemainDataService remainDataService;
	
	public static TaskEngine getInstance(){
		if(null == mInstance)
			mInstance = new TaskEngine();
		return mInstance;
	}

	public void setService(PlatformService platformService,AppService appService,DeviceService deviceService,RemainDataService remainDataService){
		this.platformService = platformService;
		this.appService = appService;
		this.deviceService = deviceService;
		this.remainDataService = remainDataService;
	}
	
	// 更新任务配置
	public boolean updateTaskConfigure(){
		return true;
	}
	public synchronized String addVpnTask(VpnLoginReq req,String remoteIp,String realIp){
		VpnTask task = new VpnTask();
		task.setDeviceId(req.getDeviceId());
		task.setOperType(req.getOperType());
		task.setRemoteIp(remoteIp);
		task.setRealIp(realIp);
		task.setToken(RandomUtils.getRandom(vpnTokenLengh));
		task.setCreateTime(new Date());
		for(VpnTask tmp :vpnTaskList){
			if(tmp.getDeviceId().equals(task.getDeviceId())){
				vpnTaskList.remove(tmp); // 删除原来的VPN task
			}
		}
		vpnTaskList.add(task);
		return task.getToken();
	}
	private synchronized boolean isTimeOut(VpnTask task){
		Date nowTime = new Date();
		switch(task.getOperType()){
		case INCREMENT_MONEY_TYPE:
			return (nowTime.getTime() - task.getCreateTime().getTime() > INCREMENT_MONEY_TIMEOUT)?true:false;
		case INCREMENT_WATERAMY_TYPE:
			return (nowTime.getTime() - task.getCreateTime().getTime() > INCREMENT_WATERAMY_TIMEOUT)?true:false;	
		case STOCK_MONEY_TYPE:	
			return (nowTime.getTime() - task.getCreateTime().getTime() > STOCK_MONEY_TIMEOUT)?true:false;
		case STOCK_WATERAMY_TYPE:
			return (nowTime.getTime() - task.getCreateTime().getTime() > STOCK_WATERAMY_TIMEOUT)?true:false;
		}
		return true;
	}
	public synchronized boolean vpnIsNeedSwitch(String token){
		for(VpnTask task :vpnTaskList){
			if(task.getToken().equals(token)){
				if(task.getPhoneTaskList() == null || task.getPhoneTaskList().size() == 0){
					return false;
				}
				for(PhoneTask pTask:task.getPhoneTaskList()){
					Date nowTime = new Date();
					if(pTask.getIsFinished() == 0 && false == isTimeOut(task)){ // 发现模拟器还有未完成的任务
						return false;
					}
				}
			}
		}
		return true;
	}
	public synchronized boolean switchVpnFinish(String token,String remoteIp,String realIp){
		for(VpnTask task :vpnTaskList){
			if(task.getToken().equals(token)){
				task.setRemoteIp(remoteIp);
				task.setRealIp(realIp);
				task.setCreateTime(new Date());//重新设置超时起点时间
				break;
			}
		}
		return true;
	}
	// 创建今日任务
	public void createTodayTask(){
		
	}
	// 手机端一个任务完成
	public synchronized void taskFinish(TaskFinishReq req,String remoteIp,String realIp){
		for(VpnTask task:vpnTaskList){
			if(task.getToken().equals(req.getVpnToken())){
				for(PhoneTask tmpTask:task.getPhoneTaskList()){
					if(tmpTask.getTaskId().equals(req.getTaskId())){
						tmpTask.setIsFinished(1);//找到任务并设为完成状态
					}
				}
			}
		}
	}
	//取一个任务
	public synchronized PhoneTask getTask(String deviceId,String remoteIp,String realIp){
		// 1. 先查VPN用途
		PhoneTask pTask = null;
		for(VpnTask task:vpnTaskList){
			if(task.getRemoteIp().equals(remoteIp)){
				for(PhoneTask tmpTask:task.getPhoneTaskList()){
					if(tmpTask.getDeviceId().equals(deviceId)){ // 发现取过任务了
						if(tmpTask.getIsFinished() == 0) // 任务没完成,返回原来的任务
							return tmpTask; 
						else
							return null;// 任务完成,这种情况下不给任务
					}
				}
				pTask = getTask(task,deviceId,task.getOperType(),remoteIp,realIp);
				task.getPhoneTaskList().add(pTask);
			}
		}
		return pTask;
	}
	private synchronized PhoneTask getTask(VpnTask task,String deviceId,int operType,String remoteIp,String realIp){
		PhoneTask pTask = new PhoneTask();
		pTask.setDeviceId(deviceId);
		pTask.setVpnToken(task.getToken());
		pTask.setOperType(operType);
		pTask.setTaskId(task.getToken() + RandomUtils.getRandom(phoneTaskIdLengh/2));//32位
		pTask.setAppId(getOptimalAppId(task,remoteIp,realIp));
		pTask.setOperType(task.getOperType());
		pTask.setTimes(1);// 暂定一次
		pTask.setSpanTime(5);//暂定5S 
		pTask.setStockInfo(mDevice);
		
		return pTask;
	}
	
	class SortByIncrementMoney implements Comparator{

		@Override
		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			Task s1 = (Task) arg0;
			Task s2 = (Task) arg1;
			if((s1.getIncrementMoney()-s1.getAllotIncrementMoney())>(s2.getIncrementMoney()-s2.getAllotIncrementMoney()))
				return 1;
			return 0;
		}
		
	}
	class SortByIncrementWaterAmy implements Comparator{

		@Override
		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			Task s1 = (Task) arg0;
			Task s2 = (Task) arg1;
			if((s1.getIncrementWaterAmy()-s1.getAllotIncrementWaterAmy())>(s2.getIncrementWaterAmy()-s2.getAllotIncrementWaterAmy()))
				return 1;
			return 0;
		}
		
	}
	class SortByStockMoney implements Comparator{

		@Override
		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			Task s1 = (Task) arg0;
			Task s2 = (Task) arg1;
			if((s1.getStockMoney()-s1.getAllotStockMoney())>(s2.getStockMoney()-s2.getAllotStockMoney()))
				return 1;
			return 0;
		}
		
	}
	class SortByStockWaterAmy implements Comparator{

		@Override
		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			Task s1 = (Task) arg0;
			Task s2 = (Task) arg1;
			if((s1.getStockWaterAmy()-s1.getAllotStockWaterAmy())>(s2.getStockWaterAmy()-s2.getAllotStockWaterAmy()))
				return 1;
			return 0;
		}
	}
	// 获得最优 appID
	private synchronized long getOptimalAppId(VpnTask task,String remoteIp,String realIp){
		// -- 排序
		mDevice = null;
		switch(task.getOperType()){
			case INCREMENT_MONEY_TYPE:
				Collections.sort(todayTaskList, new SortByIncrementMoney());
				break;
			case INCREMENT_WATERAMY_TYPE:
				Collections.sort(todayTaskList, new SortByIncrementWaterAmy());
				break;	
			case STOCK_MONEY_TYPE:	
				Collections.sort(todayTaskList, new SortByStockMoney());
				break;
			case STOCK_WATERAMY_TYPE:
				Collections.sort(todayTaskList, new SortByStockWaterAmy());
				break;
		}
		// 处理存量
		if(task.getOperType() == STOCK_MONEY_TYPE || task.getOperType() == STOCK_WATERAMY_TYPE ){
			Device device = new Device();
			device.setIp(remoteIp);
			List<Device> deviceList = deviceService.selectStockByIp(device);
			if(null == deviceList || deviceList.size() == 0){
				return 0L;
			}
			for(Device dev:deviceList){
				for(Task dbTask:todayTaskList){
					if(dev.getAppId() == dbTask.getAppId() && isHavaTaskByOperType(task.getOperType(),dbTask)){
						updateAllocTask(task.getOperType(),dbTask); // 对应派发 ++ 
						mDevice = dev; // 保存好不容易找到的存量信息，函数外赋值。
						return dbTask.getAppId();
					}
				}
			}
			return 0L;
		}else{// 处理增量
			int flag = 0;
			for(Task dbTask:todayTaskList){
				flag = 0;
				for(PhoneTask tmpTask:task.getPhoneTaskList()){
					if(tmpTask.getAppId() == dbTask.getAppId()){
						flag = 1;
						break;
					}
				}
				if(flag == 0 && isHavaTaskByOperType(task.getOperType(),dbTask)){
					updateAllocTask(task.getOperType(),dbTask); // 对应派发 ++ 
					return dbTask.getAppId();
				}
			}
		}
		return 0L;
	}
	private void updateAllocTask(int type,Task dbTask){
		switch(type){
		case INCREMENT_MONEY_TYPE:
			dbTask.setAllotIncrementMoney(dbTask.getAllotIncrementMoney() + 1);
		case INCREMENT_WATERAMY_TYPE:
			dbTask.setAllotIncrementWaterAmy(dbTask.getAllotIncrementWaterAmy() + 1);
		case STOCK_MONEY_TYPE:	
			dbTask.setAllotStockMoney(dbTask.getAllotStockMoney() + 1);
		case STOCK_WATERAMY_TYPE:
			dbTask.setAllotStockWaterAmy(dbTask.getAllotStockWaterAmy() + 1);
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

	
	
	
	
	public synchronized String getRemoteIp(HttpServletRequest request){
		return request.getRemoteAddr();
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
	
}
