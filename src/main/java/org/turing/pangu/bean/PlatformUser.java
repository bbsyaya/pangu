package org.turing.pangu.bean;

import java.util.ArrayList;
import java.util.List;

import org.turing.pangu.model.Platform;

public class PlatformUser {
	public Platform getPf() {
		return pf;
	}
	public void setPf(Platform pf) {
		this.pf = pf;
	}
	
	public List<UserApp> getUserList() {
		return userList;
	}
	public void setUserList(List<UserApp> userList) {
		this.userList = userList;
	}

	private Platform pf;
	private List<UserApp> userList = new ArrayList<UserApp>();
}
