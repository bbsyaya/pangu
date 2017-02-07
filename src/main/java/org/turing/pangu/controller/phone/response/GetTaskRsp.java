package org.turing.pangu.controller.phone.response;

import org.turing.pangu.controller.common.BaseRsp;
import org.turing.pangu.model.Device;


public class GetTaskRsp extends BaseRsp{
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getShellPath() {
		return shellPath;
	}
	public void setShellPath(String shellPath) {
		this.shellPath = shellPath;
	}
	public int getOperType() {
		return operType;
	}
	public void setOperType(int operType) {
		this.operType = operType;
	}
	public long getApp_id() {
		return app_id;
	}
	public void setApp_id(long app_id) {
		this.app_id = app_id;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getSpanTime() {
		return spanTime;
	}
	public void setSpanTime(int spanTime) {
		this.spanTime = spanTime;
	}
	public int getIsHaveTask() {
		return isHaveTask;
	}
	public void setIsHaveTask(int isHaveTask) {
		this.isHaveTask = isHaveTask;
	}
	public int getLoopTime() {
		return loopTime;
	}
	public void setLoopTime(int loopTime) {
		this.loopTime = loopTime;
	}
	private Device device; // 存量的设备信息
	private String task_id; //任务id, 32位
	private String shellPath;
	private int operType; // 操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	private long app_id; 
	private String packageName;
	private int times; // 跑的次数
	private int spanTime; // times > 1次 以上间隔时间,单位秒
	private int isHaveTask; // 是否有任务
	private int loopTime; // 循环请求任务时间间隔，单位秒
}
