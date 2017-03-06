/**
 * 
 * Title：Task
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年03月06日 
 * @since 2017年03月06日 
 */

package org.turing.pangu.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

 /**Task*/
 public class Task extends BaseModel<Task>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 2225971488785185221L;

  /***/
  private Long appId;

  /***/
  private Integer incrementMoney;

  /***/
  private Integer incrementWaterAmy;

  /***/
  private Integer stockMoney;

  /***/
  private Integer stockWaterAmy;

  /***/
  private Integer allotIncrementMoney;

  /***/
  private Integer allotIncrementWaterAmy;

  /***/
  private Integer allotStockMoney;

  /***/
  private Integer allotStockWaterAmy;

  /***/
  private Integer executeIncrementMoney;

  /***/
  private Integer executeIncrementWaterAmy;

  /***/
  private Integer executeStockMoney;

  /***/
  private Integer executeStockWaterAmy;

  /***/
  private Integer executeIncrementMoneyFail;

  /***/
  private Integer executeIncrementWaterAmy_fail;

  /***/
  private Integer executeStockMoneyFail;

  /***/
  private Integer executeStockWaterAmy_fail;

  /***/
  private Date createDate;

  /***/
  private Date updateDate;

  public void init(){
	  setIncrementMoney(0);
	  setIncrementWaterAmy(0);
	  setStockMoney(0);
	  setStockWaterAmy(0);
	  setAllotIncrementMoney(0);
	  setAllotIncrementWaterAmy(0);
	  setAllotStockMoney(0);
	  setAllotStockWaterAmy(0);
	  setExecuteIncrementMoney(0);
	  setExecuteIncrementWaterAmy(0);
	  setExecuteStockMoney(0);
	  setExecuteStockWaterAmy(0);
	  setExecuteIncrementMoneyFail(0);
	  setExecuteIncrementWaterAmy_fail(0);
	  setExecuteStockMoneyFail(0);
	  setExecuteStockWaterAmy_fail(0);
  }
  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Long getAppId()
  {
   return this.appId;
  }

  /**设置*/
  public void setAppId(Long appId)
  {
    this.appId=appId;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getIncrementMoney()
  {
   return this.incrementMoney;
  }

  /**设置*/
  public void setIncrementMoney(Integer incrementMoney)
  {
    this.incrementMoney=incrementMoney;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getIncrementWaterAmy()
  {
   return this.incrementWaterAmy;
  }

  /**设置*/
  public void setIncrementWaterAmy(Integer incrementWaterAmy)
  {
    this.incrementWaterAmy=incrementWaterAmy;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getStockMoney()
  {
   return this.stockMoney;
  }

  /**设置*/
  public void setStockMoney(Integer stockMoney)
  {
    this.stockMoney=stockMoney;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getStockWaterAmy()
  {
   return this.stockWaterAmy;
  }

  /**设置*/
  public void setStockWaterAmy(Integer stockWaterAmy)
  {
    this.stockWaterAmy=stockWaterAmy;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getAllotIncrementMoney()
  {
   return this.allotIncrementMoney;
  }

  /**设置*/
  public void setAllotIncrementMoney(Integer allotIncrementMoney)
  {
    this.allotIncrementMoney=allotIncrementMoney;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getAllotIncrementWaterAmy()
  {
   return this.allotIncrementWaterAmy;
  }

  /**设置*/
  public void setAllotIncrementWaterAmy(Integer allotIncrementWaterAmy)
  {
    this.allotIncrementWaterAmy=allotIncrementWaterAmy;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getAllotStockMoney()
  {
   return this.allotStockMoney;
  }

  /**设置*/
  public void setAllotStockMoney(Integer allotStockMoney)
  {
    this.allotStockMoney=allotStockMoney;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getAllotStockWaterAmy()
  {
   return this.allotStockWaterAmy;
  }

  /**设置*/
  public void setAllotStockWaterAmy(Integer allotStockWaterAmy)
  {
    this.allotStockWaterAmy=allotStockWaterAmy;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getExecuteIncrementMoney()
  {
   return this.executeIncrementMoney;
  }

  /**设置*/
  public void setExecuteIncrementMoney(Integer executeIncrementMoney)
  {
    this.executeIncrementMoney=executeIncrementMoney;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getExecuteIncrementWaterAmy()
  {
   return this.executeIncrementWaterAmy;
  }

  /**设置*/
  public void setExecuteIncrementWaterAmy(Integer executeIncrementWaterAmy)
  {
    this.executeIncrementWaterAmy=executeIncrementWaterAmy;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getExecuteStockMoney()
  {
   return this.executeStockMoney;
  }

  /**设置*/
  public void setExecuteStockMoney(Integer executeStockMoney)
  {
    this.executeStockMoney=executeStockMoney;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getExecuteStockWaterAmy()
  {
   return this.executeStockWaterAmy;
  }

  /**设置*/
  public void setExecuteStockWaterAmy(Integer executeStockWaterAmy)
  {
    this.executeStockWaterAmy=executeStockWaterAmy;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getExecuteIncrementMoneyFail()
  {
   return this.executeIncrementMoneyFail;
  }

  /**设置*/
  public void setExecuteIncrementMoneyFail(Integer executeIncrementMoneyFail)
  {
    this.executeIncrementMoneyFail=executeIncrementMoneyFail;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getExecuteIncrementWaterAmy_fail()
  {
   return this.executeIncrementWaterAmy_fail;
  }

  /**设置*/
  public void setExecuteIncrementWaterAmy_fail(Integer executeIncrementWaterAmy_fail)
  {
    this.executeIncrementWaterAmy_fail=executeIncrementWaterAmy_fail;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getExecuteStockMoneyFail()
  {
   return this.executeStockMoneyFail;
  }

  /**设置*/
  public void setExecuteStockMoneyFail(Integer executeStockMoneyFail)
  {
    this.executeStockMoneyFail=executeStockMoneyFail;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getExecuteStockWaterAmy_fail()
  {
   return this.executeStockWaterAmy_fail;
  }

  /**设置*/
  public void setExecuteStockWaterAmy_fail(Integer executeStockWaterAmy_fail)
  {
    this.executeStockWaterAmy_fail=executeStockWaterAmy_fail;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Date getCreateDate()
  {
   return this.createDate;
  }

  /**设置*/
  public void setCreateDate(Date createDate)
  {
    this.createDate=createDate;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Date getUpdateDate()
  {
   return this.updateDate;
  }

  /**设置*/
  public void setUpdateDate(Date updateDate)
  {
    this.updateDate=updateDate;
  }

  //自动生成区域结束
 }
