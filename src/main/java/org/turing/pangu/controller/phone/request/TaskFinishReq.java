package org.turing.pangu.controller.phone.request;

import org.turing.pangu.controller.common.BaseReq;

public class TaskFinishReq extends BaseReq{
	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	private String task_id; // 任务ID
}
