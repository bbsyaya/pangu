package org.turing.pangu.controller.phone.request;

public class ModifyPasswordReq extends BaseReq{
	public String oldPassword;  // 旧密码
	public String newPassword;	// 新密码
	public int type;			// 类型
}
