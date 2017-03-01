package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.pc.response.VpnOperUpdateRsp;
import org.turing.pangu.controller.phone.request.TaskFinishReq;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.Task;
import org.turing.pangu.task.DateUpdateListen;
import org.turing.pangu.task.StaticVpn;
import org.turing.pangu.task.TaskExtend;
import org.turing.pangu.task.TaskIF;
import org.turing.pangu.task.TaskListSort;
import org.turing.pangu.task.VpnTask;
import org.turing.pangu.utils.RandomUtils;
import org.turing.pangu.utils.TraceUtils;
/*
 * 任务引擎，负责每日任务生成,配置任务,跟踪任务进展,
 * */
public class TaskDynamicIpEngine implements TaskIF{
	private static final Logger logger = Logger.getLogger(TaskDynamicIpEngine.class);
	private static TaskDynamicIpEngine mInstance = new TaskDynamicIpEngine();
	private List<VpnTask> vpnTaskList = new ArrayList<VpnTask>();
	private DateUpdateListen mListen = null;
	private Device mDevice = null;
	
	public static TaskDynamicIpEngine getInstance(){
		if(null == mInstance)
			mInstance = new TaskDynamicIpEngine();
		return mInstance;
	}
	public void setDataUpdateListen(DateUpdateListen listen){
		mListen = listen;
	}
	public boolean isRunVpn(String remoteIp){
		for(VpnTask task :vpnTaskList){
			if(task.getRemoteIp().equals(remoteIp)){
				return true;
			}
		}
		return false;
	}
	@Override
	public void init(DateUpdateListen listen) {
		// TODO Auto-generated method stub
		setDataUpdateListen(listen);
		clear();
	}
	@Override
	public boolean isHavaTaskByOperType(int type, Task dbTask) {
		// TODO Auto-generated method stub
		TaskExtend ext = (TaskExtend)dbTask;
		switch(type){
		case TaskEngine.INCREMENT_MONEY_TYPE:
			return (ext.getIncrementMoney() - ext.getAllotIncrementMoney()) > 0 ? true:false;
		case TaskEngine.INCREMENT_WATERAMY_TYPE:
			return (ext.getIncrementWaterAmy() - ext.getAllotIncrementWaterAmy()) > 0 ? true:false;
		case TaskEngine.STOCK_MONEY_TYPE:
			return (ext.getStockMoney() - ext.getAllotStockMoney()) > 0 ? true:false;
		case TaskEngine.STOCK_WATERAMY_TYPE:
			return (ext.getStockWaterAmy() - ext.getAllotIncrementWaterAmy()) > 0 ? true:false;

		}
		return false;
	}
	@Override
	public synchronized String vpnLogin(VpnLoginReq req,String remoteIp,String realIp){
		TraceUtils.getTraceInfo();
		// 此IP不能运行任务了
		if(false == IpMngEngine.getInstance().isCanGetTask(remoteIp)){
			return null;
		}
		VpnTask task = new VpnTask();
		task.setDeviceId(req.getDeviceId());
		task.setOperType(req.getOperType());
		task.setRemoteIp(remoteIp);
		task.setRealIp(realIp);
		task.setToken(RandomUtils.getRandom(TaskEngine.VPN_TOKEN_LENGH));
		task.setCreateTime(new Date());
		logger.info("vpnLogin---001--"+task.toString());
		if( vpnTaskList.size() > 0){
			for (int i = vpnTaskList.size()-1; i >=0; i--){
				VpnTask tmp = vpnTaskList.get(i);
				if(tmp.getRemoteIp().equals(task.getRemoteIp())){
					vpnTaskList.remove(tmp); // 删除原来的VPN task
					logger.info("vpnLogin---002--del old task");
				}
			}
		}
		vpnTaskList.add(task);
		DeviceEngine.getInstance().selectDeviceByIp(remoteIp);//先去数据库中取好数据
		logger.info("vpnLogin---end");
		return task.getToken();
	}
	@Override
	public synchronized VpnOperUpdateRsp vpnIsNeedSwitch(String token,String remoteIp,String realIp){
		TraceUtils.getTraceInfo();
		VpnOperUpdateRsp dataRsp = new VpnOperUpdateRsp();
		dataRsp.setIsSwitchVpn(1);
		for(VpnTask task :vpnTaskList){
			if(task.getRemoteIp().equals(remoteIp)){
				if(task.getPhoneTaskList() == null || task.getPhoneTaskList().size() == 0){
					logger.info("not task run");
					dataRsp.setIsSwitchVpn(0);
					dataRsp.setFinishedTaskCount(0);
					dataRsp.setTaskTotal(0);
				}
				dataRsp.setTaskTotal(task.getPhoneTaskList().size());
				for(PhoneTask pTask:task.getPhoneTaskList()){
					dataRsp.setFinishedTaskCount(dataRsp.getFinishedTaskCount()+1);
					if(pTask.getIsFinished() == 0 && false == TaskEngine.isTimeOut(task)){ // 发现模拟器还有未完成的任务
						logger.info("task not finish");
						dataRsp.setIsSwitchVpn(0);
						dataRsp.setFinishedTaskCount(dataRsp.getFinishedTaskCount()-1);
					}
				}
			}
		}
		logger.info("vpnIsNeedSwitch---true---end");
		return dataRsp;
	}
	@Override
	public synchronized boolean switchVpnFinish(String token,String remoteIp,String realIp){
		// 此IP不能运行任务了
		TraceUtils.getTraceInfo();
		if(false == IpMngEngine.getInstance().isCanGetTask(remoteIp)){
			logger.info("ip is Cannot GetTask ");
			return false;
		}
		for(VpnTask task :vpnTaskList){
			if(task.getRemoteIp().equals(remoteIp)){
				if(!remoteIp.equals(task.getRemoteIp())){
					logger.info("warn !!! newRemoteIp != oldRemoteIp");
				}
				if(!realIp.equals(task.getRealIp())){
					logger.info("warn !!!  newRealIp != oldRealIp");
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

	// 手机端一个任务完成
	@Override
	public synchronized void taskFinish(TaskFinishReq req,String remoteIp,String realIp){
		TraceUtils.getTraceInfo();
		logger.info("taskId:"+req.getTaskId()+"--remoteIp:"+remoteIp+"--realIp:"+realIp);
		for(VpnTask task:vpnTaskList){
			if(task.getToken().equals(req.getVpnToken())){
				for(PhoneTask tmpTask:task.getPhoneTaskList()){
					if(tmpTask.getTaskId().equals(req.getTaskId())){
						if(req.getIsFinished() == 1 ){
							tmpTask.setIsFinished(1);//找到任务并设为完成状态
							mListen.updateExecuteTask(tmpTask); // 更新执行任务
							logger.info(" set tast Finished appId:"+tmpTask.getApp().getId());
						}
					}
				}
			}
		}
		logger.info("taskFinish---end");
	}
	//取一个任务
	@Override
	public synchronized PhoneTask getTask(String deviceId,String remoteIp,String realIp){
		// 1. 先查VPN用途
		PhoneTask pTask = null;
		TraceUtils.getTraceInfo();
		logger.info("deviceId:"+deviceId+"--remoteIp:"+remoteIp+"--realIp:"+realIp);
		for(VpnTask task:vpnTaskList){
			if(task.getRemoteIp().equals(remoteIp)){
				if(task.getPhoneTaskList().size() > AppEngine.getInstance().appList.size() * TimeZoneMng.getInstance().getTimeZoneWeight() ){
					return pTask;
				}
				for(PhoneTask tmpTask:task.getPhoneTaskList()){
					if(tmpTask.getDeviceId().equals(deviceId)){ // 发现取过任务了
						if(tmpTask.getIsFinished() == 0){ // 任务没完成,返回原来的任务
							logger.info("repeat task not finished,return old task");
							return tmpTask; 
						}
						else{
							logger.info("repeat task finished,return null task");
							return null;// 任务完成,这种情况下不给任务
						}
					}
				}
				pTask = getTask(task,deviceId,remoteIp,realIp);
				if(pTask != null){
					logger.info("Task:"+pTask.toString());
					task.getPhoneTaskList().add(pTask);
				}else{
					logger.info("not task");
				}
			}
		}
		return pTask;
	}
	private synchronized PhoneTask getTask(VpnTask task,String deviceId,String remoteIp,String realIp){
		PhoneTask pTask = new PhoneTask();
		int operType = task.getOperType();
        List<Device> deviceList = DeviceEngine.getInstance().getStockListFromPhoneByIp(remoteIp);
        // 只要有存量即使跑增量也改成存量 , 没有存量即使跑存量也改成跑增量,
        if(deviceList.size() > 0){ //这时不管什么情况都改成存量
        	if(task.getOperType() == TaskEngine.INCREMENT_MONEY_TYPE){
        		operType = TaskEngine.STOCK_MONEY_TYPE;
        	}else if(task.getOperType() == TaskEngine.INCREMENT_MONEY_TYPE){
        		operType = TaskEngine.STOCK_WATERAMY_TYPE;
        	}
        }else{
        	if(task.getOperType() == TaskEngine.STOCK_MONEY_TYPE){
        		operType = TaskEngine.INCREMENT_MONEY_TYPE;
        	}else if(task.getOperType() == TaskEngine.STOCK_WATERAMY_TYPE){
        		operType = TaskEngine.INCREMENT_MONEY_TYPE;
        	}
        }
        
		long appId = getOptimalAppId(task,operType,remoteIp,realIp);
		if(appId == 0L){
			return null;
		}
		pTask.setDeviceId(deviceId);
		pTask.setVpnToken(task.getToken());
		pTask.setOperType(operType);
		pTask.setTaskId(task.getToken() + RandomUtils.getRandom(TaskEngine.PHONE_TASKID_LENGH/2));//32位
		pTask.setApp(AppEngine.getInstance().getAppInfo(appId));
		pTask.setOperType(task.getOperType());
		pTask.setTimes(1);// 暂定一次
		pTask.setSpanTime(5);//暂定5S 
		pTask.setStockInfo(null);
		return pTask;
	}
    private synchronized long getOptimalAppId(VpnTask task,int operType,String remoteIp,String realIp){
        // -- 排序
		TraceUtils.getTraceInfo();
        mDevice = null;
        logger.info("getOptimalAppId--operType:"+operType+" remoteIp:"+remoteIp+" realIp:"+realIp);
        TaskListSort.taskSort(TaskEngine.getInstance().todayTaskList, operType);//对任务列表排序
        
        List<Device> deviceList = DeviceEngine.getInstance().getStockListFromPhoneByIp(remoteIp);
        // 处理存量
        if(operType == TaskEngine.STOCK_MONEY_TYPE || operType == TaskEngine.STOCK_WATERAMY_TYPE ){
            logger.info("getOptimalAppId---002--STOCK");
            int flag = 0;
            /*算法核心:
             * 一个IP只能运行一个APP,即便找到了很多符合条件的device,但只运行一次
             * */
            for(Device dev:deviceList){
                for(Task dbTask:TaskEngine.getInstance().todayTaskList){
                    flag = 0;
                	for(PhoneTask tmpTask:task.getPhoneTaskList()){
    					// 同一个用户,同一个应用不下发两次
    					App same = AppEngine.getInstance().getAppInfo(dbTask.getAppId());
    					if(tmpTask.getApp().getUserId() == same.getUserId()){
    						flag = 1;
    						logger.info("find same user:"+same.getUserId()+"|AppId:"+same.getId());
    						break;
    					}
                    }
                    if(flag == 0 && dev.getAppId() == dbTask.getAppId() && isHavaTaskByOperType(operType,dbTask)){
                        int random = (int)(1+Math.random()*(10-1+1));
                        //if(random%2 == 0)// 当有很多条数据时 50% 方式衰减
                        { 
                        	mListen.updateAllocTask(operType,dbTask); // 对应派发 ++ 
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
            for(Task dbTask:TaskEngine.getInstance().todayTaskList){
                flag = 0;
                for(PhoneTask tmpTask:task.getPhoneTaskList()){
					// 同一个用户,同一个应用不下发两次,只要用户名不一样就可以分配
					App same = AppEngine.getInstance().getAppInfo(dbTask.getAppId());
					if(tmpTask.getApp().getUserId() == same.getUserId()){
						flag = 1;
						logger.info("find same user:"+same.getUserId()+"|AppId:"+same.getId());
						break;
					}
                }
                if(flag == 0 && isHavaTaskByOperType(operType,dbTask)){
                	mListen.updateAllocTask(operType,dbTask); // 对应派发 ++ 
                    logger.info("getOptimalAppId---end--return INCREMENT ");
                    return dbTask.getAppId();
                }
            }
        }
        logger.info("getOptimalAppId---end--not task send ");
        return 0L;
    }
    /*
	// 获得最优 appID
	private synchronized long getOptimalAppId(VpnTask task,String remoteIp,String realIp){
		// -- 排序
		TraceUtils.getTraceInfo();
		logger.info("operType:"+task.getOperType()+"remoteIp:"+remoteIp+"realIp:"+realIp);
		TaskListSort.taskSort(TaskEngine.getInstance().todayTaskList, task.getOperType());//对任务列表排序
		{// 处理增量
			int flag = 0;
			for(Task dbTask:TaskEngine.getInstance().todayTaskList){
				flag = 0;
				for(PhoneTask tmpTask:task.getPhoneTaskList()){
					// 同一个用户不下发两次
					App isSame = TaskEngine.getInstance().getAppInfo(dbTask.getAppId());
					if(tmpTask.getApp().getUserId() == isSame.getUserId()){
						flag = 1;
						logger.info("find same user ");
						break;
					}
					// 同一个应用也不下发两次
					if(tmpTask.getApp().getId() == dbTask.getAppId()){
						flag = 1;
						logger.info("find same app");
						break;
					}
				}
				if(flag == 0 && isHavaTaskByOperType(task.getOperType(),dbTask)){
					mListen.updateAllocTask(TaskEngine.USED_DYNAMIC_VPN,task.getOperType(),dbTask); // 对应派发 ++ 
					logger.info("new task send appId:" + dbTask.getAppId());
					return dbTask.getAppId();
				}
			}
		}
		logger.info("end--not task send 0L");
		return 0L;
	}
	*/
	private void clear(){
		if(null != vpnTaskList)
			vpnTaskList.clear();
	}
	@Override
	public void CheckVpnTimeoutJob() {	
		/*
		if(null == vpnTaskList || vpnTaskList.size() == 0){
			return;
		}
		TraceUtils.getTraceInfo();
		for (int i = vpnTaskList.size()-1; i >=0; i--){
			VpnTask vpn = vpnTaskList.get(i);
			if(true == TaskEngine.isTimeOut(vpn)){
				vpnTaskList.remove(vpn);//删除这个已结束的VPN
				logger.info("remove isTimeOut vpn ");
			}
		}
		*/
	}
}
