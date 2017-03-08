/**
 * 
 * Title：DynamicVpn
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

 /**DynamicVpn*/
 public class DynamicVpn extends BaseModel<DynamicVpn>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 2431481488935838615L;

  /***/
  private Long groupId;

  /***/
  private Integer isValid;

  /***/
  private String name;

  /***/
  private String domain;

  /***/
  private String user;

  /***/
  private String password;

  /***/
  private String configure;

  /***/
  private Date createDate;

  /***/
  private Date updateDate;


  
  /**获取*/
  @JsonProperty
  public Long getGroupId()
  {
   return this.groupId;
  }

  /**设置*/
  public void setGroupId(Long groupId)
  {
    this.groupId=groupId;
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
  @Length(max =21845 )
  public String getConfigure()
  {
   return this.configure;
  }

  /**设置*/
  public void setConfigure(String configure)
  {
    this.configure=configure;
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
