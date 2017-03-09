package org.turing.pangu.controller.phone.request;

import org.turing.pangu.controller.common.BaseReq;

public class GetNewPhoneInfoReq extends BaseReq{
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	private String accessToken;
}
