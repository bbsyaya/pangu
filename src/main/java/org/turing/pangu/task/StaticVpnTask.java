package org.turing.pangu.task;

import org.turing.pangu.model.RemainIp;


/*固定IP的生命周期*/
public class StaticVpnTask extends VpnTask{
	public RemainIp getIpInfo() {
		return ipInfo;
	}
	public void setIpInfo(RemainIp ipInfo) {
		this.ipInfo = ipInfo;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	
	public int getRunType() {
		return runType;
	}
	public void setRunType(int runType) {
		this.runType = runType;
	}
	public int getSendCount() {
		return sendCount;
	}
	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	private RemainIp ipInfo;
	private int statu = 0; // 0:初始  1:已下发 2:已连接 3:已完成
	private int runType = 0;//0:跑增量  1:跑存量
	private int sendCount = 0; // 下发次数
}
