package org.turing.pangu.task;

import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.model.Task;

public interface DateUpdateListen {
	public void updateAllocTask(int operType,Task dbTask);
	public void updateExecuteTask(PhoneTask pTask,Boolean isSuccess);
}
