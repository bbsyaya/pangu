package org.turing.pangu.controller.pc.request;

public class CurdVpnReq {
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIpList() {
		return ipList;
	}
	public void setIpList(String ipList) {
		this.ipList = ipList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	private Long id;
	private String ipList;
	private String name;
	private int type; // 1:增,2修改
}
