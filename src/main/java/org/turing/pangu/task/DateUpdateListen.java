package org.turing.pangu.task;

import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.model.Task;

public interface DateUpdateListen {
	public void updateAllocTask(int operType,int vpnType,Task dbTask);
	public void updateExecuteTask(int vpnType,PhoneTask pTask);
}
