package org.turing.pangu.service;



import java.util.List;

import org.turing.pangu.model.RemainIp;

/** InvestService */
public interface RemainIpService extends BaseService<RemainIp, Long> {
	public List<RemainIp> getValidIpList(RemainIp model);
}
