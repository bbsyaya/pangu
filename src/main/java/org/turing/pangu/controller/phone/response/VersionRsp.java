package org.turing.pangu.controller.phone.response;


public class VersionRsp {
	private String version;        // 1.0.4
	private Integer whichPlatform;     // 哪个平台 0:android   1:iOS
	private String url;			  // 下载地址
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getWhichPlatform() {
		return whichPlatform;
	}
	public void setWhichPlatform(Integer whichPlatform) {
		this.whichPlatform = whichPlatform;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
