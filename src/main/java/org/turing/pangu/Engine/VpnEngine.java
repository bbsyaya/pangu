package org.turing.pangu.Engine;

import java.util.List;

import org.turing.pangu.model.RemainVpn;

public class VpnEngine {
	private static VpnEngine mInstance = new VpnEngine();
	private List<RemainVpn> list = null;
	public static VpnEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new VpnEngine();
		return mInstance;
	}
	
	public synchronized List<RemainVpn> getList() {
		return list;
	}
	public synchronized void setList(List<RemainVpn> list) {
		this.list = list;
	}
}
