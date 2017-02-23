package org.turing.pangu.task;

import java.util.Date;

import org.turing.pangu.engine.TaskEngine;
import org.turing.pangu.engine.TimeZoneMng;
import org.turing.pangu.model.Task;

public class TaskUtils {
	public static boolean isTimeOut(VpnTask task){
		Date nowTime = new Date();
		switch(task.getOperType()){
		case TaskEngine.INCREMENT_MONEY_TYPE:
			return (nowTime.getTime() - task.getCreateTime().getTime() > TimeZoneMng.INCREMENT_MONEY_TIMEOUT)?true:false;
		case TaskEngine.INCREMENT_WATERAMY_TYPE:
			return (nowTime.getTime() - task.getCreateTime().getTime() > TimeZoneMng.INCREMENT_WATERAMY_TIMEOUT)?true:false;	
		case TaskEngine.STOCK_MONEY_TYPE:	
			return (nowTime.getTime() - task.getCreateTime().getTime() > TimeZoneMng.STOCK_MONEY_TIMEOUT)?true:false;
		case TaskEngine.STOCK_WATERAMY_TYPE:
			return (nowTime.getTime() - task.getCreateTime().getTime() > TimeZoneMng.STOCK_WATERAMY_TIMEOUT)?true:false;
		}
		return true;
	}
	// 是否还有该类型的任务
	public static boolean isHavaTaskByOperType(int type,Task dbTask){
		switch(type){
		case TaskEngine.INCREMENT_MONEY_TYPE:
			return (dbTask.getIncrementMoney() - dbTask.getAllotIncrementMoney()) > 0 ? true:false;
		case TaskEngine.INCREMENT_WATERAMY_TYPE:
			return (dbTask.getIncrementWaterAmy() - dbTask.getAllotIncrementWaterAmy()) > 0 ? true:false;
		case TaskEngine.STOCK_MONEY_TYPE:	
			return (dbTask.getStockMoney() - dbTask.getAllotStockMoney()) > 0 ? true:false;
		case TaskEngine.STOCK_WATERAMY_TYPE:
			return (dbTask.getStockWaterAmy() - dbTask.getAllotStockWaterAmy()) > 0 ? true:false;
		}
		return false;
	}
}
