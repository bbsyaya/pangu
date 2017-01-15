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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.turing.pangu.mapper.AppMapper;
import org.turing.pangu.model.App;

@Repository
public class AppDaoImpl extends BaseDaoImpl<App, Long> implements AppDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AppDaoImpl.class);

	@Autowired
	private AppMapper mapper;
	
	@Autowired
	public void setAppMapper(AppMapper mapper) {
		super.setBaseMapper(mapper);
	}

}
