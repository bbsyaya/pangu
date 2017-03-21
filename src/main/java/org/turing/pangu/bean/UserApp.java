package org.turing.pangu.bean;

import java.util.ArrayList;
import java.util.List;

import org.turing.pangu.model.App;
import org.turing.pangu.model.User;

public class UserApp {
	private User user;
	private List<App> appList = new ArrayList<App>();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<App> getAppList() {
		return appList;
	}
	public void setAppList(List<App> appList) {
		this.appList = appList;
	}
	
	
}
