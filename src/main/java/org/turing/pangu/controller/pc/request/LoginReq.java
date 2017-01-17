package org.turing.pangu.controller.pc.request;

import org.turing.pangu.controller.common.BaseReq;

public class LoginReq extends BaseReq{
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String name;
	private String password;
}
