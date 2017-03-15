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
import org.turing.pangu.mapper.ResolutionMapper;
import org.turing.pangu.model.Resolution;

@Repository
public class ResolutionDaoImpl extends BaseDaoImpl<Resolution, Long> implements ResolutionDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ResolutionDaoImpl.class);

	@Autowired
	private ResolutionMapper mapper;
	
	@Autowired
	public void setMapper(ResolutionMapper mapper) {
		super.setBaseMapper(mapper);
	}
}
