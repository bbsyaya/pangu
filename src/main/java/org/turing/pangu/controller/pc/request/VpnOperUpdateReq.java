package org.turing.pangu.controller.pc.request;

import org.turing.pangu.controller.common.BaseReq;

public class VpnOperUpdateReq extends BaseReq{
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
