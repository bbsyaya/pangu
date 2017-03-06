package org.turing.pangu.task;

import java.util.ArrayList;
import java.util.List;

import org.turing.pangu.model.VpnGroup;

public class BusinessVpnGroup {
	private VpnGroup group;
	private List<BusinessStaticVpn> staticVpnList = new ArrayList<BusinessStaticVpn>();
	public VpnGroup getGroup() {
		return group;
	}
	public void setGroup(VpnGroup group) {
		this.group = group;
	}
	public List<BusinessStaticVpn> getStaticVpnList() {
		return staticVpnList;
	}
	public void setStaticVpnList(List<BusinessStaticVpn> staticVpnList) {
		this.staticVpnList = staticVpnList;
	}
}
