package org.turing.pangu.task;

import org.turing.pangu.controller.common.VpnTask;

/*固定IP的生命周期*/
public class FixedIpProcess extends VpnTask{
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}

	private String ip = "";
	private int statu = 0; // 0:初始  1:已下发 2:已连接 3:已完成
}
