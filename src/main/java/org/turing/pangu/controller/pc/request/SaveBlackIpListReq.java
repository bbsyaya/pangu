package org.turing.pangu.controller.pc.request;

public class SaveBlackIpListReq {
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	public String getIpList() {
		return ipList;
	}
	public void setIpList(String ipList) {
		this.ipList = ipList;
	}
	private Long platformId;
	private String ipList;
}
