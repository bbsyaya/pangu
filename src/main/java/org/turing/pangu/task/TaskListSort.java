package org.turing.pangu.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.turing.pangu.engine.TaskEngine;
import org.turing.pangu.model.Task;

public class TaskListSort {
	public static void taskSort(List<TaskExtend> todayTaskList,int operType){
		switch(operType){
		case TaskEngine.INCREMENT_MONEY_TYPE:
			Collections.sort(todayTaskList, new SortByIncrementMoney(true));
			break;
		case TaskEngine.INCREMENT_WATERAMY_TYPE:
			Collections.sort(todayTaskList, new SortByIncrementWaterAmy(true));
			break;	
		case TaskEngine.STOCK_MONEY_TYPE:	
			Collections.sort(todayTaskList, new SortByStockMoney(true));
			break;
		case TaskEngine.STOCK_WATERAMY_TYPE:
			Collections.sort(todayTaskList, new SortByStockWaterAmy(true));
			break;
	}
	}
	static class SortByIncrementMoney implements Comparator{
		private boolean reverseOrder; // 是否倒序
        public SortByIncrementMoney(boolean reverseOrder) {
            this.reverseOrder = reverseOrder;
        }
		@Override
		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			Task s0 = (Task) arg0;
			Task s1 = (Task) arg1;
			if(reverseOrder) 
                return ((s1.getIncrementMoney()-s1.getAllotIncrementMoney())-(s0.getIncrementMoney()-s0.getAllotIncrementMoney()));
            else 
                return ((s0.getIncrementMoney()-s0.getAllotIncrementMoney())-(s1.getIncrementMoney()-s1.getAllotIncrementMoney()));
		}
		
	}
	static class SortByIncrementWaterAmy implements Comparator{
		private boolean reverseOrder; // 是否倒序
        public SortByIncrementWaterAmy(boolean reverseOrder) {
            this.reverseOrder = reverseOrder;
        }
		@Override
		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			Task s0 = (Task) arg0;
			Task s1 = (Task) arg1;
			if(reverseOrder) 
                return ((s1.getIncrementWaterAmy()-s1.getAllotIncrementWaterAmy())-(s0.getIncrementWaterAmy()-s0.getAllotIncrementWaterAmy()));
            else 
                return ((s0.getIncrementWaterAmy()-s0.getAllotIncrementWaterAmy())-(s1.getIncrementWaterAmy()-s1.getAllotIncrementWaterAmy()));
		}
		
	}
	static class SortByStockMoney implements Comparator{
		private boolean reverseOrder; // 是否倒序
        public SortByStockMoney(boolean reverseOrder) {
            this.reverseOrder = reverseOrder;
        }
		@Override
		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			Task s0 = (Task) arg0;
			Task s1 = (Task) arg1;
			if(reverseOrder) 
                return ((s1.getStockMoney()-s1.getAllotStockMoney())-(s0.getStockMoney()-s0.getAllotStockMoney()));
            else 
                return ((s0.getStockMoney()-s0.getAllotStockMoney())-(s1.getStockMoney()-s1.getAllotStockMoney()));
		}
		
	}
	static class SortByStockWaterAmy implements Comparator{
		private boolean reverseOrder; // 是否倒序
        public SortByStockWaterAmy(boolean reverseOrder) {
            this.reverseOrder = reverseOrder;
        }
		@Override
		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			Task s0 = (Task) arg0;
			Task s1 = (Task) arg1;
			if(reverseOrder) 
                return ((s1.getStockWaterAmy()-s1.getAllotStockWaterAmy())-(s0.getStockWaterAmy()-s0.getAllotStockWaterAmy()));
            else 
                return ((s0.getStockWaterAmy()-s0.getAllotStockWaterAmy())-(s1.getStockWaterAmy()-s1.getAllotStockWaterAmy()));
		}
	}
}
