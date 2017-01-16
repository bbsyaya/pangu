package org.turing.pangu.service;



import org.turing.pangu.model.RemainVpn;

/** InvestService */
public interface RemainVpnService extends BaseService<RemainVpn, Long> {
	public boolean isWhileListIp(String ip);

}
