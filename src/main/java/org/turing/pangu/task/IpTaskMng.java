package org.turing.pangu.task;

import java.util.ArrayList;
import java.util.List;

public class IpTaskMng {
	private List<VpnTask> list = new ArrayList<VpnTask>(); 
	public List<VpnTask> getList() {
		return list;
	}
	public void setList(List<VpnTask> list) {
		this.list = list;
	}

}
