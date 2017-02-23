package org.turing.pangu.task;

import org.turing.pangu.model.Task;
/*
 * 动态IP和固定IP都要刷增量，对总增量的分食
 * */
public class TaskExtend extends Task{
	public int getFixedIpIncrementMoney() {
		return fixedIpIncrementMoney;
	}
	public void setFixedIpIncrementMoney(int fixedIpIncrementMoney) {
		this.fixedIpIncrementMoney = fixedIpIncrementMoney;
	}
	public int getFixedIpIncrementWaterAmy() {
		return fixedIpIncrementWaterAmy;
	}
	public void setFixedIpIncrementWaterAmy(int fixedIpIncrementWaterAmy) {
		this.fixedIpIncrementWaterAmy = fixedIpIncrementWaterAmy;
	}
	public int getDynamicIpIncrementMoney() {
		return DynamicIpIncrementMoney;
	}
	public void setDynamicIpIncrementMoney(int dynamicIpIncrementMoney) {
		DynamicIpIncrementMoney = dynamicIpIncrementMoney;
	}
	public int getDynamicIpIncrementWaterAmy() {
		return DynamicIpIncrementWaterAmy;
	}
	public void setDynamicIpIncrementWaterAmy(int dynamicIpIncrementWaterAmy) {
		DynamicIpIncrementWaterAmy = dynamicIpIncrementWaterAmy;
	}
	private int fixedIpIncrementMoney = 0 ;
	private int fixedIpIncrementWaterAmy = 0 ;
	private int DynamicIpIncrementMoney = 0 ;
	private int DynamicIpIncrementWaterAmy = 0 ;
	public void setDBTask(Task task){
		this.setAllotIncrementMoney(task.getAllotIncrementMoney());
		this.setAllotIncrementWaterAmy(task.getAllotIncrementWaterAmy());
		this.setAllotStockMoney(task.getAllotStockMoney());
		this.setAllotStockWaterAmy(task.getAllotStockWaterAmy());
		this.setAppId(task.getAppId());
		this.setCreateDate(task.getCreateDate());
		this.setExecuteIncrementMoney(task.getExecuteIncrementMoney());
		this.setExecuteIncrementWaterAmy(task.getExecuteIncrementWaterAmy());
		this.setExecuteStockMoney(task.getExecuteStockMoney());
		this.setExecuteStockWaterAmy(task.getExecuteStockWaterAmy());
		this.setId(task.getId());
		this.setIncrementMoney(task.getIncrementMoney());
		this.setIncrementWaterAmy(task.getIncrementWaterAmy());
		this.setStockMoney(task.getStockMoney());
		this.setStockWaterAmy(task.getStockWaterAmy());
		this.setUpdateDate(task.getUpdateDate());
	}
}
