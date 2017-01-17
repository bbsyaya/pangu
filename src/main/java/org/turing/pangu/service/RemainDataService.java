package org.turing.pangu.service;



import java.util.List;

import org.turing.pangu.model.App;
import org.turing.pangu.model.PageModel;
import org.turing.pangu.model.ParamModel;
import org.turing.pangu.model.RemainData;
import org.turing.pangu.model.User;

/** InvestService */
public interface RemainDataService extends BaseService<RemainData, Long> {
	public List<RemainData> selectTodayData(RemainData model);
}
