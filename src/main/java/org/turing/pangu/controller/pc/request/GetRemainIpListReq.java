package org.turing.pangu.controller.pc.request;

import org.turing.pangu.controller.common.BaseReq;

public class GetRemainIpListReq extends BaseReq{
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
