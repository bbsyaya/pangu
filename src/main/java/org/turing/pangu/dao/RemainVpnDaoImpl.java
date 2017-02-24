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
import org.turing.pangu.mapper.RemainVpnMapper;
import org.turing.pangu.model.RemainVpn;

@Repository
public class RemainVpnDaoImpl extends BaseDaoImpl<RemainVpn, Long> implements RemainVpnDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RemainVpnDaoImpl.class);

	@Autowired
	private RemainVpnMapper mapper;
	
	@Autowired
	public void setRemainVpnMapper(RemainVpnMapper mapper) {
		super.setBaseMapper(mapper);
	}

	@Override
	public List<RemainVpn> selectValid(RemainVpn model) {
		// TODO Auto-generated method stub
		return mapper.selectList(model);
	}

}
