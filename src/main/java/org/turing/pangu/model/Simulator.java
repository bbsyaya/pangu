/**
 * 
 * Title：Simulator
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年03月08日 
 * @since 2017年03月08日 
 */

package org.turing.pangu.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

 /**Simulator*/
 public class Simulator extends BaseModel<Simulator>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 3381721488933615410L;

  /***/
  private Long computerId;

  /***/
  private Integer isValid;

  /***/
  private String deviceNumber;

  /***/
  private String deviceToken;

  /***/
  private Date createDate;

  /***/
  private Date updateDate;


  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Long getComputerId()
  {
   return this.computerId;
  }

  /**设置*/
  public void setComputerId(Long computerId)
  {
    this.computerId=computerId;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getIsValid()
  {
   return this.isValid;
  }

  /**设置*/
  public void setIsValid(Integer isValid)
  {
    this.isValid=isValid;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  @NotEmpty(groups = {Default.class,Save.class})
  public String getDeviceNumber()
  {
   return this.deviceNumber;
  }

  /**设置*/
  public void setDeviceNumber(String deviceNumber)
  {
    this.deviceNumber=deviceNumber;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  @NotEmpty(groups = {Default.class,Save.class})
  public String getDeviceToken()
  {
   return this.deviceToken;
  }

  /**设置*/
  public void setDeviceToken(String deviceToken)
  {
    this.deviceToken=deviceToken;
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
