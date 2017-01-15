package org.turing.pangu.controller.phone.response;

public class LoginRsp  {
	
	private String accessToken; // 访问token
	private UserInfoRsp userInfo;  // 用户ID
	
	public String getAccessToken() {
		return accessToken;
	}
	public UserInfoRsp getUserInfo() {
		return userInfo;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public void setUserInfo(UserInfoRsp userInfo) {
		this.userInfo = userInfo;
	}
}
