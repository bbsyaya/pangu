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
import org.turing.pangu.mapper.SimulatorMapper;
import org.turing.pangu.model.Simulator;

@Repository
public class SimulatorDaoImpl extends BaseDaoImpl<Simulator, Long> implements SimulatorDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SimulatorDaoImpl.class);

	@Autowired
	private SimulatorMapper mapper;
	
	@Autowired
	public void setSimulatorMapper(SimulatorMapper mapper) {
		super.setBaseMapper(mapper);
	}

}
