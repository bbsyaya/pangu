package org.turing.pangu.service;



import java.util.List;

import org.turing.pangu.model.VpnGroup;

/** InvestService */
public interface VpnGroupService extends BaseService<VpnGroup, Long> {
	public List<VpnGroup> getValidIpList(VpnGroup model);
}
