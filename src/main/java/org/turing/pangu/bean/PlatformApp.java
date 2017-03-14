package org.turing.pangu.bean;

import java.util.ArrayList;
import java.util.List;

import org.turing.pangu.model.App;
import org.turing.pangu.model.Platform;

public class PlatformApp {
	public Platform getPf() {
		return pf;
	}
	public void setPf(Platform pf) {
		this.pf = pf;
	}
	public List<App> getAppList() {
		return appList;
	}
	public void setAppList(List<App> appList) {
		this.appList = appList;
	}
	
	private Platform pf;
	private List<App> appList = new ArrayList<App>();
}
