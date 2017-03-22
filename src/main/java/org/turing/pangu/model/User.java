/**
 * 
 * Title：User
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

 /**User*/
 public class User extends BaseModel<User>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 3253931490102007131L;

  /***/
  private Long platformId;

  /***/
  private Integer isValid;

  /***/
  private String name;

  /***/
  private String password;

  /***/
  private String phone;

  /***/
  private String ip;

  /***/
  private Date createDate;

  /***/
  private Date updateDate;


  
  /**获取*/
  @JsonProperty
  public Long getPlatformId()
  {
   return this.platformId;
  }

  /**设置*/
  public void setPlatformId(Long platformId)
  {
    this.platformId=platformId;
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
  public String getPhone()
  {
   return this.phone;
  }

  /**设置*/
  public void setPhone(String phone)
  {
    this.phone=phone;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getIp()
  {
   return this.ip;
  }

  /**设置*/
  public void setIp(String ip)
  {
    this.ip=ip;
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
