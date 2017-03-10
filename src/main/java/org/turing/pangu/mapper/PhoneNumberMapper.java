package org.turing.pangu.mapper;

import java.util.List;

import org.turing.pangu.model.PhoneNumber;



 /**AppMapper*/
 public interface PhoneNumberMapper extends BaseMapper<PhoneNumber,Long>
 {
	 public List<PhoneNumber> selectPhoneNumber(PhoneNumber model);
 }
 

