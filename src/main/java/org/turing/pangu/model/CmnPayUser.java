/**
 * 
 * Title：CmnPayUser
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年03月21日 
 * @since 2017年03月21日 
 */

package org.turing.pangu.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

 /**CmnPayUser*/
 public class CmnPayUser extends BaseModel<CmnPayUser>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 363171490059844808L;

  /***/
  private String name;

  /***/
  private String password;

  /***/
  private String token;

  /***/
  private Integer isValid;

  /***/
  private Long count;

  /***/
  private Integer payType;

  /***/
  private Date payDate;

  /***/
  private Date createDate;

  /***/
  private Date updateDate;


  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getName()
  {
   return this.name;
  }

  /**设置*/
  public void setName(String name)
  {
    this.name=name;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getPassword()
  {
   return this.password;
  }

  /**设置*/
  public void setPassword(String password)
  {
    this.password=password;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getToken()
  {
   return this.token;
  }

  /**设置*/
  public void setToken(String token)
  {
    this.token=token;
  }

  
  /**获取*/
  @JsonProperty
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
  public Long getCount()
  {
   return this.count;
  }

  /**设置*/
  public void setCount(Long count)
  {
    this.count=count;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getPayType()
  {
   return this.payType;
  }

  /**设置*/
  public void setPayType(Integer payType)
  {
    this.payType=payType;
  }

  
  /**获取*/
  @JsonProperty
  public Date getPayDate()
  {
   return this.payDate;
  }

  /**设置*/
  public void setPayDate(Date payDate)
  {
    this.payDate=payDate;
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
