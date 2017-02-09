package org.turing.pangu.controller.phone.request;

import org.turing.pangu.controller.common.BaseReq;

public class TaskFinishReq extends BaseReq{
	public String getVpnToken() {
		return vpnToken;
	}
	public void setVpnToken(String vpnToken) {
		this.vpnToken = vpnToken;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	private String vpnToken; // vpn Token
	private String taskId; // 任务ID
}
