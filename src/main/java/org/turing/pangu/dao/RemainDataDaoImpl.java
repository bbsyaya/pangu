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
import org.turing.pangu.mapper.RemainDataMapper;
import org.turing.pangu.model.RemainData;

@Repository
public class RemainDataDaoImpl extends BaseDaoImpl<RemainData, Long> implements RemainDataDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RemainDataDaoImpl.class);

	@Autowired
	private RemainDataMapper mapper;
	
	@Autowired
	public void setRemainDataMapper(RemainDataMapper mapper) {
		super.setBaseMapper(mapper);
	}

	@Override
	public List<RemainData> selectRemainData(RemainData model) {
		// TODO Auto-generated method stub
		List<RemainData> list = null;
		try {
			list = mapper.selectRemainData(model);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

}
