package org.turing.pangu.service;



import java.util.List;

import org.turing.pangu.model.PhoneNumber;

/** InvestService */
public interface PhoneNumberService extends BaseService<PhoneNumber, Long> {
	public PhoneNumber selectOnePhoneNumber(PhoneNumber model);
}
