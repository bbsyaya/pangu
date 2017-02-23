package org.turing.pangu.controller.pc.response;

import org.turing.pangu.controller.common.BaseRsp;

public class VpnOperUpdateRsp extends BaseRsp{
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
	public int getIsSwitchVpn() {
		return isSwitchVpn;
	}
	public void setIsSwitchVpn(int isSwitchVpn) {
		this.isSwitchVpn = isSwitchVpn;
	}
	public int getLoopTime() {
		return loopTime;
	}
	public void setLoopTime(int loopTime) {
		this.loopTime = loopTime;
	}
	public int getTaskTotal() {
		return taskTotal;
	}
	public void setTaskTotal(int taskTotal) {
		this.taskTotal = taskTotal;
	}
	public int getFinishedTaskCount() {
		return finishedTaskCount;
	}
	public void setFinishedTaskCount(int finishedTaskCount) {
		this.finishedTaskCount = finishedTaskCount;
	}
	
	public VpnConnectInfoRsp getConnectInfo() {
		return connectInfo;
	}
	public void setConnectInfo(VpnConnectInfoRsp connectInfo) {
		this.connectInfo = connectInfo;
	}

	private String remoteIp;	//getRemoteAddr
	private String realIp; 		//x-forwarded-for
	private int isSwitchVpn;    //1: 是 0:否
	private int loopTime;		// 下次询问时长 单位秒
	private int taskTotal = 0;		// 任务总数
	private int finishedTaskCount = 0;//已完成的任务数
	private VpnConnectInfoRsp connectInfo;
}
