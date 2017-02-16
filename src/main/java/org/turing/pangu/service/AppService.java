package org.turing.pangu.service;



import java.util.List;

import org.turing.pangu.model.App;

/** InvestService */
public interface AppService extends BaseService<App, Long> {
	public List<App> selectCanRunApps(App model);
}
