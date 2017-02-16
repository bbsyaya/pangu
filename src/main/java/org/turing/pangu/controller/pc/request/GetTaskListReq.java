package org.turing.pangu.controller.pc.request;

import java.util.Date;

import org.turing.pangu.controller.common.BaseReq;

public class GetTaskListReq extends BaseReq{
	private Long appId;
	private Date startDate;
	private Date endDate;
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}
}
