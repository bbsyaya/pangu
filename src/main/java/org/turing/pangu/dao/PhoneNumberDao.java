/**
 * 
 * Title：User
 * Copyright: Copyright (c) 2014
 * Company: 深电中心
 * @author axi
 * @version 1.0, 2016年08月22日 
 * @since 2016年08月22日 
 */

package org.turing.pangu.dao;

import java.util.List;

import org.turing.pangu.model.PhoneNumber;

public interface PhoneNumberDao extends BaseDao<PhoneNumber, Long> {
	public List<PhoneNumber> selectPhoneNumber(PhoneNumber model);
}
