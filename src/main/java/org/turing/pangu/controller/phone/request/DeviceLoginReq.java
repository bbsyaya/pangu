package org.turing.pangu.controller.phone.request;

import org.turing.pangu.controller.common.BaseReq;

public class DeviceLoginReq extends BaseReq{
	private String deviceId;
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
