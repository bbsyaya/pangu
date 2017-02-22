package org.turing.pangu.task;

import java.util.ArrayList;
import java.util.List;

public class FixIpTask {
	private List<FixedIpProcess> list = new ArrayList<FixedIpProcess>(); 
	private int moneyTotalCount = 0;
	private int waterTotalCount= 0;
	private int moneyAllotCount = 0;
	private int waterAllotCount = 0;
	private int moneyFinishedCount= 0;
	private int waterFinishedCount= 0;
	public List<FixedIpProcess> getList() {
		return list;
	}
	public void setList(List<FixedIpProcess> list) {
		this.list = list;
	}
	public int getMoneyAllotCount() {
		return moneyAllotCount;
	}
	public void setMoneyAllotCount(int moneyAllotCount) {
		this.moneyAllotCount = moneyAllotCount;
	}
	public int getWaterAllotCount() {
		return waterAllotCount;
	}
	public void setWaterAllotCount(int waterAllotCount) {
		this.waterAllotCount = waterAllotCount;
	}
	public int getMoneyTotalCount() {
		return moneyTotalCount;
	}
	public void setMoneyTotalCount(int moneyTotalCount) {
		this.moneyTotalCount = moneyTotalCount;
	}
	public int getWaterTotalCount() {
		return waterTotalCount;
	}
	public void setWaterTotalCount(int waterTotalCount) {
		this.waterTotalCount = waterTotalCount;
	}
	public int getMoneyFinishedCount() {
		return moneyFinishedCount;
	}
	public void setMoneyFinishedCount(int moneyFinishedCount) {
		this.moneyFinishedCount = moneyFinishedCount;
	}
	public int getWaterFinishedCount() {
		return waterFinishedCount;
	}
	public void setWaterFinishedCount(int waterFinishedCount) {
		this.waterFinishedCount = waterFinishedCount;
	}
}
