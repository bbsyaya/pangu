package org.turing.pangu.controller.pc.request;

import org.turing.pangu.controller.common.BaseReq;

public class GetRemainListReq extends BaseReq{
	private Long appId;

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}
}
