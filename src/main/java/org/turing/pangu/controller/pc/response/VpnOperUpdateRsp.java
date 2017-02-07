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
	private String remoteIp;	//getRemoteAddr
	private String realIp; 		//x-forwarded-for
	private int isSwitchVpn;    //1: 是 0:否
	private int loopTime;		// 下次询问时长 单位秒
}
