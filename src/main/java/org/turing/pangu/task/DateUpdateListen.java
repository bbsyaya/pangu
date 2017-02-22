package org.turing.pangu.task;

import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.model.Task;

public interface DateUpdateListen {
	public void updateAllocTask(int type,Task dbTask);
	public void updateExecuteTask(PhoneTask pTask);
}
