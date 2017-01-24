package org.turing.pangu.mapper;

import java.util.List;

import org.turing.pangu.model.App;

 /**AppMapper*/
 public interface AppMapper extends BaseMapper<App,Long>
 {
	public List<App> selectModelList(App model);
 }
 

