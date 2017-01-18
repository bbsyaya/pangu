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
import org.turing.pangu.mapper.DynamicVpnMapper;
import org.turing.pangu.model.DynamicVpn;

@Repository
public class DynamicVpnDaoImpl extends BaseDaoImpl<DynamicVpn, Long> implements DynamicVpnDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DynamicVpnDaoImpl.class);

	@Autowired
	private DynamicVpnMapper mapper;
	
	@Autowired
	public void setDynamicVpnMapper(DynamicVpnMapper mapper) {
		super.setBaseMapper(mapper);
	}

}
