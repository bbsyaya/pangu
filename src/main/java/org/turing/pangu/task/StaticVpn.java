package org.turing.pangu.task;

import org.turing.pangu.bean.ConnectVpnInfo;


/*静态VPN对应于数据库中静态VPN的条目*/
public class StaticVpn {
	private ConnectVpnInfo connectInfo = null;
	private StaticVpnTask vpnTask = null;
	public ConnectVpnInfo getConnectInfo() {
		return connectInfo;
	}
	public void setConnectInfo(ConnectVpnInfo connectInfo) {
		this.connectInfo = connectInfo;
	}
	public StaticVpnTask getVpnTask() {
		return vpnTask;
	}
	public void setVpnTask(StaticVpnTask vpnTask) {
		this.vpnTask = vpnTask;
	}
}
