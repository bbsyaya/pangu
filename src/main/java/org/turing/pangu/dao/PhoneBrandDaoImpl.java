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
import org.turing.pangu.mapper.PhoneBrandMapper;
import org.turing.pangu.model.PhoneBrand;

@Repository
public class PhoneBrandDaoImpl extends BaseDaoImpl<PhoneBrand, Long> implements PhoneBrandDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PhoneBrandDaoImpl.class);

	@Autowired
	private PhoneBrandMapper mapper;
	
	@Autowired
	public void setMapper(PhoneBrandMapper mapper) {
		super.setBaseMapper(mapper);
	}
}
