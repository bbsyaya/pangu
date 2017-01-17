package org.turing.pangu.engine;

import java.util.List;

import org.turing.pangu.model.App;
import org.turing.pangu.model.RemainVpn;

public class AppEngine {
	private static AppEngine mInstance = new AppEngine();
	private List<App> list = null;
	public static AppEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new AppEngine();
		return mInstance;
	}
	
	public synchronized List<App> getList() {
		return list;
	}
	public synchronized void setList(List<App> list) {
		this.list = list;
	}
}
