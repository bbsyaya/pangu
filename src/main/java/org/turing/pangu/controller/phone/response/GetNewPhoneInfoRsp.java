package org.turing.pangu.controller.phone.response;

import org.turing.pangu.controller.common.BaseReq;
import org.turing.pangu.controller.phone.request.ReportReq;

public class GetNewPhoneInfoRsp extends BaseReq{
	public ReportReq newPhoneInfo;

	public ReportReq getNewPhoneInfo() {
		return newPhoneInfo;
	}

	public void setNewPhoneInfo(ReportReq newPhoneInfo) {
		this.newPhoneInfo = newPhoneInfo;
	}
}
