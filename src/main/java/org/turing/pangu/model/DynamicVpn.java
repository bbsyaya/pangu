/**
 * 
 * Title：DynamicVpn
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年01月18日 
 * @since 2017年01月18日 
 */

package org.turing.pangu.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

 /**DynamicVpn*/
 public class DynamicVpn extends BaseModel<DynamicVpn>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 8046511484738694423L;

  /***/
  private String name;

  /***/
  private String domain;

  /***/
  private String user;

  /***/
  private String password;

  /***/
  private String ipList;

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
  public String getDomain()
  {
   return this.domain;
  }

  /**设置*/
  public void setDomain(String domain)
  {
    this.domain=domain;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getUser()
  {
   return this.user;
  }

  /**设置*/
  public void setUser(String user)
  {
    this.user=user;
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
  @Length(max =715827882 )
  public String getIpList()
  {
   return this.ipList;
  }

  /**设置*/
  public void setIpList(String ipList)
  {
    this.ipList=ipList;
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
