package org.turing.pangu.controller.phone.request;

import org.turing.pangu.controller.common.BaseReq;

public class GetPlatformInfoReq extends BaseReq{
	private long platformId;

	public long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(long platformId) {
		this.platformId = platformId;
	}
}
