package org.turing.pangu.controller.phone.response;

import org.turing.pangu.controller.common.BaseRsp;
import org.turing.pangu.controller.common.PhoneTask;
public class GetTaskRsp extends BaseRsp{
	public int getLoopTime() {
		return loopTime;
	}
	public void setLoopTime(int loopTime) {
		this.loopTime = loopTime;
	}
	public int getIsHaveTask() {
		return isHaveTask;
	}
	public void setIsHaveTask(int isHaveTask) {
		this.isHaveTask = isHaveTask;
	}
	public PhoneTask getTask() {
		return task;
	}
	public void setTask(PhoneTask task) {
		this.task = task;
	}
	public String getTaskIp() {
		return taskIp;
	}
	public void setTaskIp(String taskIp) {
		this.taskIp = taskIp;
	}
	private PhoneTask task; // 一个任务
	private int loopTime; // 循环请求任务时间间隔，单位秒
	private int isHaveTask = 0; // 是否有任务, 1:有 0: 没有
	private String taskIp; // 执行任务的IP
}
