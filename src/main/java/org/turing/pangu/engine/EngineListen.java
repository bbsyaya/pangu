package org.turing.pangu.engine;

import java.util.List;

import org.turing.pangu.service.BaseService;

public interface EngineListen {
	public void setService(List<BaseService> serviceList);
	public void init();
	public void open();
	public void close();
}
