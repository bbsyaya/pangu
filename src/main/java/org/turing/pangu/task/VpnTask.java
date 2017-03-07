package org.turing.pangu.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.turing.pangu.controller.common.PhoneTask;

/*
 * PC 端的 VPN 任务管理，与 PhoneTask 是 1对多的关系
 * */
public class VpnTask {
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public String getRealIp() {
		return realIp;
	}
	public void setRealIp(String realIp) {
		this.realIp = realIp;
	}
	public int getOperType() {
		return operType;
	}
	public void setOperType(int operType) {
		this.operType = operType;
	}
	public List<PhoneTask> getPhoneTaskList() {
		return phoneTaskList;
	}
	public void setPhoneTaskList(List<PhoneTask> phoneTaskList) {
		this.phoneTaskList = phoneTaskList;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public List<StockTask> getStockDeviceList() {
		return stockDeviceList;
	}
	public void setStockDeviceList(List<StockTask> stockDeviceList) {
		this.stockDeviceList = stockDeviceList;
	}

	public int getTaskReportFinishedCount() {
		return taskReportFinishedCount;
	}
	public void setTaskReportFinishedCount(int taskReportFinishedCount) {
		this.taskReportFinishedCount = taskReportFinishedCount;
	}
	public int getTaskReportNotFinishedCount() {
		return taskReportNotFinishedCount;
	}
	public void setTaskReportNotFinishedCount(int taskReportNotFinishedCount) {
		this.taskReportNotFinishedCount = taskReportNotFinishedCount;
	}

	public List<IncrementTask> getIncrementList() {
		return incrementList;
	}
	public void setIncrementList(List<IncrementTask> incrementList) {
		this.incrementList = incrementList;
	}

	public int getTaskIncrementTotalCount() {
		return taskIncrementTotalCount;
	}
	public void setTaskIncrementTotalCount(int taskIncrementTotalCount) {
		this.taskIncrementTotalCount = taskIncrementTotalCount;
	}
	public int getTaskStockTotalCount() {
		return taskStockTotalCount;
	}
	public void setTaskStockTotalCount(int taskStockTotalCount) {
		this.taskStockTotalCount = taskStockTotalCount;
	}
	public int getTaskAllocIncrementCount() {
		return taskAllocIncrementCount;
	}
	public void setTaskAllocIncrementCount(int taskAllocIncrementCount) {
		this.taskAllocIncrementCount = taskAllocIncrementCount;
	}
	public int getTaskAllocStockCount() {
		return taskAllocStockCount;
	}
	public void setTaskAllocStockCount(int taskAllocStockCount) {
		this.taskAllocStockCount = taskAllocStockCount;
	}



	private String deviceId; //pc 设备ID
	private String remoteIp;
	private String realIp;
	private String token; // 16位
	private int operType; // 操作类型  0:增量赚钱 1:增量水军 2:存量赚钱 3:存量水军
	private Date createTime;// Vpn 任务创建时间
	//-------------小任务池------------------------------
	private int taskIncrementTotalCount; // 一次ip 任务总数
	private int taskStockTotalCount; // 一次ip 任务总数
	private int taskAllocIncrementCount; // 已分配增量的数量
	private int taskAllocStockCount; // 已分配增量的数量
	private int taskReportFinishedCount; // 上报已正常完成的数量
	private int taskReportNotFinishedCount; // 上报已正常完成的数量
	//--------------------------------------------------------
	private List<IncrementTask> incrementList = new ArrayList<IncrementTask>(); //这个IP可以跑的存量任务列表
	private List<StockTask> stockDeviceList = new ArrayList<StockTask>(); //这个IP的留存都在这,注意 device中的IP会和remoteIp 不同，只是在同一个城市
	private List<PhoneTask> phoneTaskList = new ArrayList<PhoneTask>();//这是实际在跑的任务列表 PhoneTask 的 operType 必须和 VpnTask 的 operType 一致
}
