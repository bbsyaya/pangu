package org.turing.pangu.mapper;

import java.util.List;

import org.turing.pangu.model.DeviceFromNet;

 /**AppMapper*/
 public interface DeviceFromNetMapper extends BaseMapper<DeviceFromNet,Long>{
	 public List<DeviceFromNet> selectTimeSpan(DeviceFromNet device);
	 public long selectCountByTimeSpan(DeviceFromNet device);
	 
 }
 

