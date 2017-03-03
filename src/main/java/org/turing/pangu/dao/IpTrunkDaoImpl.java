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
import org.turing.pangu.mapper.IpTrunkMapper;
import org.turing.pangu.model.App;
import org.turing.pangu.model.IpTrunk;

@Repository
public class IpTrunkDaoImpl extends BaseDaoImpl<IpTrunk, Long> implements IpTrunkDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IpTrunkDaoImpl.class);

	@Autowired
	private IpTrunkMapper mapper;
	
	@Autowired
	public void setAppMapper(IpTrunkMapper mapper) {
		super.setBaseMapper(mapper);
	}
}
