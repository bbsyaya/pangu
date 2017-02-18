package org.turing.pangu.controller.phone.response;

import org.turing.pangu.controller.common.BaseReq;

public class DeviceLoginRsp extends BaseReq{
	private String deviceId;
	private String deviceToken;
	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
