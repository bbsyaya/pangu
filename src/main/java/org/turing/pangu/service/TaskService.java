package org.turing.pangu.service;



import java.util.List;

import org.turing.pangu.model.PageModel;
import org.turing.pangu.model.ParamModel;
import org.turing.pangu.model.Task;
import org.turing.pangu.model.User;

/** InvestService */
public interface TaskService extends BaseService<Task, Long> {
	public List<Task> selectTodayTaskList(Task task);
}
