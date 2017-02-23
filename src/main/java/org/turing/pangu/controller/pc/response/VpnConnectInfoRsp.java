package org.turing.pangu.controller.pc.response;

import org.turing.pangu.controller.common.BaseRsp;

public class VpnConnectInfoRsp extends BaseRsp{
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getVpnId() {
		return vpnId;
	}
	public void setVpnId(Long vpnId) {
		this.vpnId = vpnId;
	}

	
	public boolean isUsed() {
		return isUsed;
	}
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}


	private String ip;
	private String userName;
	private String password;
	private Long vpnId;
	private boolean isUsed = false;
}
