package org.turing.pangu.task;

import java.util.ArrayList;
import java.util.List;

import org.turing.pangu.model.RemainIp;
import org.turing.pangu.model.RemainVpn;

public class BusinessStaticVpn {
	private RemainVpn vpn;
	private List<RemainIp> ipList = new ArrayList<RemainIp>();
	public RemainVpn getVpn() {
		return vpn;
	}
	public void setVpn(RemainVpn vpn) {
		this.vpn = vpn;
	}
	public List<RemainIp> getIpList() {
		return ipList;
	}
	public void setIpList(List<RemainIp> ipList) {
		this.ipList = ipList;
	}
}
