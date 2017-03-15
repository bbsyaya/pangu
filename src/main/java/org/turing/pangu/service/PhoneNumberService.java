package org.turing.pangu.service;



import org.turing.pangu.model.PhoneNumber;

/** InvestService */
public interface PhoneNumberService extends BaseService<PhoneNumber, Long> {
	public PhoneNumber selectOnePhoneNumber(PhoneNumber model);
}
