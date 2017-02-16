/**
 * 
 * Title：RemainData
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年01月17日 
 * @since 2017年01月17日 
 */

package org.turing.pangu.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

 /**RemainData*/
 public class RemainData extends BaseModel<RemainData>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 4086231484631377987L;

  /***/
  private Long appId;

  /***/
  private Long active;

  /***/
  private Long inactive;

  /***/
  private Long remain;

  /***/
  private Long remainActive;

  /***/
  private Long unremain;

  /***/
  private String remainPath;

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
  public Long getActive()
  {
   return this.active;
  }

  /**设置*/
  public void setActive(Long active)
  {
    this.active=active;
  }

  
  /**获取*/
  @JsonProperty
  public Long getInactive()
  {
   return this.inactive;
  }

  /**设置*/
  public void setInactive(Long inactive)
  {
    this.inactive=inactive;
  }

  
  /**获取*/
  @JsonProperty
  public Long getRemain()
  {
   return this.remain;
  }

  /**设置*/
  public void setRemain(Long remain)
  {
    this.remain=remain;
  }

  
  /**获取*/
  @JsonProperty
  public Long getRemainActive()
  {
   return this.remainActive;
  }

  /**设置*/
  public void setRemainActive(Long remainActive)
  {
    this.remainActive=remainActive;
  }

  
  /**获取*/
  @JsonProperty
  public Long getUnremain()
  {
   return this.unremain;
  }

  /**设置*/
  public void setUnremain(Long unremain)
  {
    this.unremain=unremain;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getRemainPath()
  {
   return this.remainPath;
  }

  /**设置*/
  public void setRemainPath(String remainPath)
  {
    this.remainPath=remainPath;
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
