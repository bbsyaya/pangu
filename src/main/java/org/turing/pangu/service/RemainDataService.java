package org.turing.pangu.service;



import java.util.List;

import org.turing.pangu.model.RemainData;

/** InvestService */
public interface RemainDataService extends BaseService<RemainData, Long> {
	public List<RemainData> getRemainData(RemainData model);
}
