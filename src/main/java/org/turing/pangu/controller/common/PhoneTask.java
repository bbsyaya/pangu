package org.turing.pangu.controller.common;

import java.util.Date;

import org.turing.pangu.engine.TaskEngine;
import org.turing.pangu.model.App;
import org.turing.pangu.phone.ChangeDeviceInfo;

public class PhoneTask {
	public ChangeDeviceInfo getChangeDeviceInfo() {
		return changeDeviceInfo;
	}
	public void setChangeDeviceInfo(ChangeDeviceInfo changeDeviceInfo) {
		this.changeDeviceInfo = changeDeviceInfo;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
	public int getIsFinished() {
		return isFinished;
	}
	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}
	public String getVpnToken() {
		return vpnToken;
	}
	public void setVpnToken(String vpnToken) {
		this.vpnToken = vpnToken;
	}
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	}
	public int getSendTimes() {
		return sendTimes;
	}
	public void setSendTimes(int sendTimes) {
		this.sendTimes = sendTimes;
	}
	
	public int getIsReport() {
		return isReport;
	}
	public void setIsReport(int isReport) {
		this.isReport = isReport;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	private ChangeDeviceInfo changeDeviceInfo; // 增量或存量设备信息
	private String deviceId;
	private String vpnToken; // 带上VPN的token
	private String taskId; //任务id, 32位
	private String shellPath;
	private int operType = 0; // 操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	private App app;
	private int times = 0; // 跑的次数
	private int spanTime; // times > 1次 以上间隔时间,单位秒
	private int sendTimes = 0;// 下发次数
	private int isFinished = TaskEngine.TASK_STATE_INIT; // 是否完成,1: 是  0:否
	private int isReport = 0; // 是否上报,1: 是 0:否
	private Date createTime = new Date();// Vpn 任务创建时间
}
