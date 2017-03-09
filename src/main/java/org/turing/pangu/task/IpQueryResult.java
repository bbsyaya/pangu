package org.turing.pangu.task;

import java.util.ArrayList;
import java.util.List;

import org.turing.pangu.iptrunk.BaiduLocation;

public class IpQueryResult {
	public List<StockTask> getStockList() {
		return stockList;
	}
	public void setStockList(List<StockTask> stockList) {
		this.stockList = stockList;
	}
	public BaiduLocation getLocation() {
		return location;
	}
	public void setLocation(BaiduLocation location) {
		this.location = location;
	}
	private List<StockTask> stockList = new ArrayList<StockTask>();
	private BaiduLocation location = new BaiduLocation();
}
