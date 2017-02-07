/**
 * 
 * Title：Task
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年02月07日 
 * @since 2017年02月07日 
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
  private static final long serialVersionUID = 7037101486433532793L;

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
  private Date createDate;

  /***/
  private Date updateDate;


  
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
