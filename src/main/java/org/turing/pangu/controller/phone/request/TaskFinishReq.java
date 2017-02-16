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
	public int getIsFinished() {
		return isFinished;
	}
	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}
	
	private String vpnToken; // vpn Token
	private String taskId; // 任务ID
	private int isFinished;// 任务是否完成  1:完成 0:没有完成
}
