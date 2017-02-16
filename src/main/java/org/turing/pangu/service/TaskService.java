package org.turing.pangu.service;



import java.util.List;

import org.turing.pangu.model.Task;

/** InvestService */
public interface TaskService extends BaseService<Task, Long> {
	public List<Task> selectTodayTaskList(Task task);
}
