package org.turing.pangu.controller.phone.request;

public class RegisterReq extends BaseReq{
	/**手机号码*/
	public String mobile;      // 手机号码
	/**密码*/
	public String password;    // 密码
	/**短信验证码*/
	public String code;        // 短信验证码
	/**邀请码*/
	public String invitationCode;   // 邀请码
	/**类型（0个人/1企业）*/
	public Integer type; 		//类型
	

	
	
}
