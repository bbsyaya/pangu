package org.turing.pangu.task;

import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.pc.response.VpnOperUpdateRsp;
import org.turing.pangu.controller.phone.request.TaskFinishReq;
import org.turing.pangu.model.Task;

public interface TaskIF{
	public String vpnLogin(VpnLoginReq req,String remoteIp,String realIp);
	public VpnOperUpdateRsp vpnIsNeedSwitch(String token,String remoteIp,String realIp);
	public boolean switchVpnFinish(String token,String remoteIp,String realIp);
	
	public PhoneTask getTask(String deviceId,String remoteIp,String realIp);
	public void taskFinish(TaskFinishReq req,String remoteIp,String realIp);
	public void init(DateUpdateListen listen);
	public boolean isHavaTaskByOperType(int type,Task dbTask);
}
