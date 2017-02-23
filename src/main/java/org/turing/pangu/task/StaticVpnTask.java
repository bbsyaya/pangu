package org.turing.pangu.task;


/*固定IP的生命周期*/
public class StaticVpnTask extends VpnTask{
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
	
	public int getRunType() {
		return runType;
	}
	public void setRunType(int runType) {
		this.runType = runType;
	}

	private String ip = "";
	private int statu = 0; // 0:初始  1:已下发 2:已连接 3:已完成
	private int runType = 0;//0:跑增量  1:跑存量
}
