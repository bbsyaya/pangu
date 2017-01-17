package org.turing.pangu.engine;

import java.util.List;

import org.turing.pangu.model.Platform;
import org.turing.pangu.model.RemainVpn;

public class PlatformEngine {
	private static PlatformEngine mInstance = new PlatformEngine();
	private List<Platform> list = null;
	public static PlatformEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new PlatformEngine();
		return mInstance;
	}
	
	public synchronized List<Platform> getList() {
		return list;
	}
	public synchronized void setList(List<Platform> list) {
		this.list = list;
	}
}
