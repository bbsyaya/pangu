package org.turing.pangu.task;

import org.turing.pangu.controller.pc.response.VpnConnectInfoRsp;



/*静态VPN对应于数据库中静态VPN的条目*/
public class StaticVpn {
	private VpnConnectInfoRsp connectInfo = null;
	private StaticVpnTask vpnTask = null;
	public VpnConnectInfoRsp getConnectInfo() {
		return connectInfo;
	}
	public void setConnectInfo(VpnConnectInfoRsp connectInfo) {
		this.connectInfo = connectInfo;
	}
	public StaticVpnTask getVpnTask() {
		return vpnTask;
	}
	public void setVpnTask(StaticVpnTask vpnTask) {
		this.vpnTask = vpnTask;
	}
}
