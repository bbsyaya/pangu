/**
 * 
 * Title：User
 * Copyright: Copyright (c) 2014
 * Company: 深电中心
 * @author axi
 * @version 1.0, 2016年08月23日 
 * @since 2016年08月23日 
 */

package org.turing.pangu.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.turing.pangu.mapper.AppMapper;
import org.turing.pangu.mapper.TaskMapper;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Task;

@Repository
public class TaskDaoImpl extends BaseDaoImpl<Task, Long> implements TaskDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TaskDaoImpl.class);

	@Autowired
	private TaskMapper mapper;
	
	@Autowired
	public void setAppMapper(TaskMapper mapper) {
		super.setBaseMapper(mapper);
	}
}
