package org.turing.pangu.task;

import org.turing.pangu.model.Task;
/*
 * 动态IP和固定IP都要刷增量，对总增量的分食
 * */
public class TaskExtend extends Task{
	private int staticIpIncrementMoney = 0 ;
	private int staticIpIncrementWaterAmy = 0 ;
	private int staticIpAllocIncrementMoney = 0 ;
	private int staticIpAllocIncrementWaterAmy = 0 ;
	private int staticIpExecuteIncrementMoney = 0 ;
	private int staticIpExecuteIncrementWaterAmy = 0 ;
	
	private int dynamicIpAllocIncrementMoney = 0 ;
	private int dynamicIpAllocIncrementWaterAmy = 0 ;
	private int dynamicIpIncrementMoney = 0 ;
	private int dynamicIpIncrementWaterAmy = 0 ;
	private int dynamicIpExecuteIncrementMoney = 0 ;
	private int dynamicIpExecuteIncrementWaterAmy = 0 ;


	public int getStaticIpIncrementMoney() {
		return staticIpIncrementMoney;
	}

	public void setStaticIpIncrementMoney(int staticIpIncrementMoney) {
		this.staticIpIncrementMoney = staticIpIncrementMoney;
	}

	public int getStaticIpIncrementWaterAmy() {
		return staticIpIncrementWaterAmy;
	}

	public void setStaticIpIncrementWaterAmy(int staticIpIncrementWaterAmy) {
		this.staticIpIncrementWaterAmy = staticIpIncrementWaterAmy;
	}

	public int getStaticIpAllocIncrementMoney() {
		return staticIpAllocIncrementMoney;
	}

	public void setStaticIpAllocIncrementMoney(int staticIpAllocIncrementMoney) {
		this.staticIpAllocIncrementMoney = staticIpAllocIncrementMoney;
	}

	public int getStaticIpAllocIncrementWaterAmy() {
		return staticIpAllocIncrementWaterAmy;
	}

	public void setStaticIpAllocIncrementWaterAmy(int staticIpAllocIncrementWaterAmy) {
		this.staticIpAllocIncrementWaterAmy = staticIpAllocIncrementWaterAmy;
	}

	public int getStaticIpExecuteIncrementMoney() {
		return staticIpExecuteIncrementMoney;
	}

	public void setStaticIpExecuteIncrementMoney(int staticIpExecuteIncrementMoney) {
		this.staticIpExecuteIncrementMoney = staticIpExecuteIncrementMoney;
	}

	public int getStaticIpExecuteIncrementWaterAmy() {
		return staticIpExecuteIncrementWaterAmy;
	}

	public void setStaticIpExecuteIncrementWaterAmy(
			int staticIpExecuteIncrementWaterAmy) {
		this.staticIpExecuteIncrementWaterAmy = staticIpExecuteIncrementWaterAmy;
	}

	public int getDynamicIpAllocIncrementMoney() {
		return dynamicIpAllocIncrementMoney;
	}

	public void setDynamicIpAllocIncrementMoney(int dynamicIpAllocIncrementMoney) {
		this.dynamicIpAllocIncrementMoney = dynamicIpAllocIncrementMoney;
	}

	public int getDynamicIpAllocIncrementWaterAmy() {
		return dynamicIpAllocIncrementWaterAmy;
	}

	public void setDynamicIpAllocIncrementWaterAmy(
			int dynamicIpAllocIncrementWaterAmy) {
		this.dynamicIpAllocIncrementWaterAmy = dynamicIpAllocIncrementWaterAmy;
	}

	public int getDynamicIpIncrementMoney() {
		return dynamicIpIncrementMoney;
	}

	public void setDynamicIpIncrementMoney(int dynamicIpIncrementMoney) {
		this.dynamicIpIncrementMoney = dynamicIpIncrementMoney;
	}

	public int getDynamicIpIncrementWaterAmy() {
		return dynamicIpIncrementWaterAmy;
	}

	public void setDynamicIpIncrementWaterAmy(int dynamicIpIncrementWaterAmy) {
		this.dynamicIpIncrementWaterAmy = dynamicIpIncrementWaterAmy;
	}

	public int getDynamicIpExecuteIncrementMoney() {
		return dynamicIpExecuteIncrementMoney;
	}

	public void setDynamicIpExecuteIncrementMoney(int dynamicIpExecuteIncrementMoney) {
		this.dynamicIpExecuteIncrementMoney = dynamicIpExecuteIncrementMoney;
	}

	public int getDynamicIpExecuteIncrementWaterAmy() {
		return dynamicIpExecuteIncrementWaterAmy;
	}

	public void setDynamicIpExecuteIncrementWaterAmy(
			int dynamicIpExecuteIncrementWaterAmy) {
		this.dynamicIpExecuteIncrementWaterAmy = dynamicIpExecuteIncrementWaterAmy;
	}
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
