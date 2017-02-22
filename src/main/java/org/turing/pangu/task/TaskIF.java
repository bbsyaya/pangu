package org.turing.pangu.task;

import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.pc.response.VpnOperUpdateRsp;
import org.turing.pangu.controller.phone.request.TaskFinishReq;

public interface TaskIF{
	public String addVpnTask(VpnLoginReq req,String remoteIp,String realIp);
	public VpnOperUpdateRsp vpnIsNeedSwitch(String token);
	public boolean switchVpnFinish(String token,String remoteIp,String realIp);
	
	public PhoneTask getTask(String deviceId,String remoteIp,String realIp);
	public void taskFinish(TaskFinishReq req,String remoteIp,String realIp);
}
