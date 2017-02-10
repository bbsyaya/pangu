package org.turing.pangu.bean;

public class TaskConfigureBean {
	  public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Integer getIncrementMoney() {
		return incrementMoney;
	}

	public void setIncrementMoney(Integer incrementMoney) {
		this.incrementMoney = incrementMoney;
	}

	public Integer getIncrementWaterAmy() {
		return incrementWaterAmy;
	}

	public void setIncrementWaterAmy(Integer incrementWaterAmy) {
		this.incrementWaterAmy = incrementWaterAmy;
	}

	public Integer getStockMoney() {
		return stockMoney;
	}

	public void setStockMoney(Integer stockMoney) {
		this.stockMoney = stockMoney;
	}

	public Integer getStockWaterAmy() {
		return stockWaterAmy;
	}

	public void setStockWaterAmy(Integer stockWaterAmy) {
		this.stockWaterAmy = stockWaterAmy;
	}

  private Long appId;

  /***/
  private Integer incrementMoney;

  /***/
  private Integer incrementWaterAmy;

  /***/
  private Integer stockMoney;

  /***/
  private Integer stockWaterAmy;
}
