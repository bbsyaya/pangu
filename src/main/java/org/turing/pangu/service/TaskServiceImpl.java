package org.turing.pangu.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.turing.pangu.dao.TaskDao;
import org.turing.pangu.dao.UserDao;
import org.turing.pangu.model.Task;
import org.turing.pangu.model.User;



@Service("taskServiceImpl")
public class TaskServiceImpl extends BaseServiceImpl<Task,Long> implements TaskService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TaskServiceImpl.class);

    private TaskDao dao;

	@Autowired
	public void setTaskDao(TaskDao dao) {
		super.setBaseDao(dao);
		this.dao = dao;
	}

	@Override
	public List<Task> selectTodayTaskList(Task task) {
		// TODO Auto-generated method stub
		return dao.selectTimeSpan(task);
	}	
}
