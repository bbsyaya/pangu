package org.turing.pangu.controller.phone.request;

import org.turing.pangu.controller.common.BaseReq;

public class GetTaskReq extends BaseReq{
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	private String deviceId;
	private String accessToken;
}
