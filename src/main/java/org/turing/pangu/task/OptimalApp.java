package org.turing.pangu.task;

import org.turing.pangu.engine.TaskEngine;
import org.turing.pangu.model.App;
import org.turing.pangu.phone.ChangeDeviceInfo;

public class OptimalApp {
	private App app;
	private int operType = TaskEngine.INCREMENT_WATERAMY_TYPE;
	private ChangeDeviceInfo info;
	
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	}
	public ChangeDeviceInfo getInfo() {
		return info;
	}
	public void setInfo(ChangeDeviceInfo info) {
		this.info = info;
	}
	public int getOperType() {
		return operType;
	}
	public void setOperType(int operType) {
		this.operType = operType;
	}
	
}
