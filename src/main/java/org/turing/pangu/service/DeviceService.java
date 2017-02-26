package org.turing.pangu.service;



import java.util.List;

import org.turing.pangu.controller.phone.request.ReportReq;
import org.turing.pangu.model.Device;

/** InvestService */
public interface DeviceService extends BaseService<Device, Long> {
	public List<Device> selectCanRemainData(Device device);
	public String getRemainIpList();
	public int selectCountByTimeSpan(Device device);
	public List<Device> selectStockByIp(Device device);

}
