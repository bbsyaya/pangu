package org.turing.pangu.service;



import java.util.List;

import org.turing.pangu.model.App;
import org.turing.pangu.model.PageModel;
import org.turing.pangu.model.ParamModel;
import org.turing.pangu.model.User;

/** InvestService */
public interface AppService extends BaseService<App, Long> {
	public List<App> selectCanRunApps(App model);
}
