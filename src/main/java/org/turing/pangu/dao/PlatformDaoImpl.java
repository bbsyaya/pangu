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

import org.turing.pangu.mapper.PlatformMapper;
import org.turing.pangu.model.Platform;

@Repository
public class PlatformDaoImpl extends BaseDaoImpl<Platform, Long> implements PlatformDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PlatformDaoImpl.class);

	@Autowired
	private PlatformMapper mapper;
	
	@Autowired
	public void setPlatformMapper(PlatformMapper mapper) {
		super.setBaseMapper(mapper);
	}

}
