package org.turing.pangu.controller.phone.request;


public class ReportReq extends BaseReq{
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public NeedChangedInfo getDevice() {
		return device;
	}
	public void setDevice(NeedChangedInfo device) {
		this.device = device;
	}
	public int getDevice_type() {
		return device_type;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public int getIs_remain_ip() {
		return is_remain_ip;
	}
	public void setIs_remain_ip(int is_remain_ip) {
		this.is_remain_ip = is_remain_ip;
	}
	private String accessToken; // 访问token,前期为空
	private Long appId;
	private NeedChangedInfo device;
	private int device_type; // 0: 模拟器 ，1:真机 	
	private int is_active;	// 1:active 0:inactive
	private int is_remain_ip;	// 1:留存IP 0:未知,需验证
}
