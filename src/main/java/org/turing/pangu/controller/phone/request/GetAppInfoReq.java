package org.turing.pangu.controller.phone.request;

import org.turing.pangu.controller.common.BaseReq;

public class GetAppInfoReq extends BaseReq{
	private long appId;

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}
}
