package org.turing.pangu.controller.phone.request;

import org.turing.pangu.model.Device;

public class ReportReq extends BaseReq{
	private String token;
	private NeedChangedInfo device;
	private String device_type; // 0: 模拟器 ，1:真机 	
	private String is_active;	// 0:active 1:inactive
	private String is_remote_ip;	// 0:留存IP 1:未知,需验证
}
