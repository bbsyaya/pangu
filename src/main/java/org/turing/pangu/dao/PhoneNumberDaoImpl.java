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
import org.turing.pangu.mapper.PhoneNumberMapper;
import org.turing.pangu.model.PhoneNumber;

@Repository
public class PhoneNumberDaoImpl extends BaseDaoImpl<PhoneNumber, Long> implements PhoneNumberDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PhoneNumberDaoImpl.class);

	@Autowired
	private PhoneNumberMapper mapper;
	
	@Autowired
	public void setMapper(PhoneNumberMapper mapper) {
		super.setBaseMapper(mapper);
	}

	@Override
	public List<PhoneNumber> selectPhoneNumber(PhoneNumber model) {
		// TODO Auto-generated method stub
		return mapper.selectPhoneNumber(model);
	}
}
