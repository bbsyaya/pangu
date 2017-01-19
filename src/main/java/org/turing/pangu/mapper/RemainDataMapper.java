package org.turing.pangu.mapper;

import java.util.List;

import org.turing.pangu.model.RemainData;

 /**AppMapper*/
 public interface RemainDataMapper extends BaseMapper<RemainData,Long>
 {
	 public List<RemainData> selectRemainData(RemainData model);
 }
 

