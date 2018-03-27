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
import org.turing.pangu.mapper.UmengBrandMapper;
import org.turing.pangu.model.App;
import org.turing.pangu.model.UmengBrand;

@Repository
public class UmengBrandDaoImpl extends BaseDaoImpl<UmengBrand, Long> implements UmengBrandDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UmengBrandDaoImpl.class);

	@Autowired
	private UmengBrandMapper mapper;
	
	@Autowired
	public void setMapper(UmengBrandMapper mapper) {
		super.setBaseMapper(mapper);
	}
}
