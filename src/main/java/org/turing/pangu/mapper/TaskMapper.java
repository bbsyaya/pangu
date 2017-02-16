package org.turing.pangu.mapper;

import java.util.List;

import org.turing.pangu.model.Task;

 /**AppMapper*/
 public interface TaskMapper extends BaseMapper<Task,Long>{
	 public List<Task> selectTimeSpan(Task task);
 }
 

