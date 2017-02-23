package org.turing.pangu.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.turing.pangu.controller.common.PhoneTask;

/*
 * PC 端的 VPN 任务管理，与 PhoneTask 是 1对多的关系
 * */
public class VpnTask {
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public String getRealIp() {
		return realIp;
	}
	public void setRealIp(String realIp) {
		this.realIp = realIp;
	}
	public int getOperType() {
		return operType;
	}
	public void setOperType(int operType) {
		this.operType = operType;
	}
	public List<PhoneTask> getPhoneTaskList() {
		return phoneTaskList;
	}
	public void setPhoneTaskList(List<PhoneTask> phoneTaskList) {
		this.phoneTaskList = phoneTaskList;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	private String deviceId; //pc 设备ID
	private String remoteIp;
	private String realIp;
	private String token; // 16位
	private int operType; // 操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	private Date createTime;// Vpn 任务创建时间
	private List<PhoneTask> phoneTaskList = new ArrayList<PhoneTask>();//PhoneTask 的 operType 必须和 VpnTask 的 operType 一致
}
