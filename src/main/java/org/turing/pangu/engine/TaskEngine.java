package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.controller.common.VpnTask;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.pc.request.VpnSwitchFinishReq;
import org.turing.pangu.controller.pc.response.VpnLoginRsp;
import org.turing.pangu.model.App;
import org.turing.pangu.model.RemainVpn;
import org.turing.pangu.utils.RandomUtils;
/*
 * 任务引擎，负责每日任务生成,配置任务,跟踪任务进展,
 * */
public class TaskEngine {
	private static TaskEngine mInstance = new TaskEngine();
	private List<VpnTask> vpnTaskList = new ArrayList<VpnTask>();
	private int vpnTokenLengh = 16;
	private int phoneTaskIdLengh = 32;
	public static TaskEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new TaskEngine();
		return mInstance;
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
		for(VpnTask tmp :vpnTaskList){
			if(tmp.getDeviceId().equals(task.getDeviceId())){
				vpnTaskList.remove(tmp); // 删除原来的VPN task
			}
		}
		vpnTaskList.add(task);
		return task.getToken();
	}
	public synchronized boolean vpnIsNeedSwitch(String token){
		for(VpnTask task :vpnTaskList){
			if(task.getToken().equals(token)){
				if(task.getPhoneTaskList() == null || task.getPhoneTaskList().size() == 0){
					return false;
				}
				for(PhoneTask pTask:task.getPhoneTaskList()){
					if(pTask.getIsFinished() == 0 ){ // 发现模拟器还有未完成的任务
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
				break;
			}
		}
		return true;
	}
	// 创建今日任务
	public void createTodayTask(){
		
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
				pTask = getTask(task.getToken(),deviceId,task.getOperType());
				task.getPhoneTaskList().add(pTask);
			}
		}
		return pTask;
	}
	private synchronized PhoneTask getTask(String vpnToken,String deviceId,int operType){
		PhoneTask task = new PhoneTask();
		task.setDeviceId(deviceId);
		task.setOperType(operType);
		task.setTaskId(vpnToken + RandomUtils.getRandom(phoneTaskIdLengh/2));//32位
		// ---- todo
		return null;
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
