package org.turing.pangu.controller.phone.request;

import org.turing.pangu.controller.common.BaseReq;

public class ApkLoginReq extends BaseReq{
	private String name;
	private String password;

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
}
