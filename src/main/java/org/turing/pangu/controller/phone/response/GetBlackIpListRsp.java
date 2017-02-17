package org.turing.pangu.controller.phone.response;

import java.util.ArrayList;
import java.util.List;

public class GetBlackIpListRsp {
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<String> getIpList() {
		return ipList;
	}
	public void setIpList(List<String> ipList) {
		this.ipList = ipList;
	}
	private int count = 0;
	private List<String> ipList = new ArrayList<String>();
}
