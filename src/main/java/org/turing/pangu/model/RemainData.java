/**
 * 
 * Title：RemainData
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年01月15日 
 * @since 2017年01月15日 
 */

package org.turing.pangu.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

 /**RemainData*/
 public class RemainData extends BaseModel<RemainData>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 5725141484483833454L;

  /***/
  private String token;

  /***/
  private Long addActiveDevice;

  /***/
  private Long addInactiveDevice;

  /***/
  private Long remainActiveDevice;

  /***/
  private Long remainInactiveDevice;

  /***/
  private String remainPath;

  /***/
  private Date createDate;


  
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
  public Long getAddActiveDevice()
  {
   return this.addActiveDevice;
  }

  /**设置*/
  public void setAddActiveDevice(Long addActiveDevice)
  {
    this.addActiveDevice=addActiveDevice;
  }

  
  /**获取*/
  @JsonProperty
  public Long getAddInactiveDevice()
  {
   return this.addInactiveDevice;
  }

  /**设置*/
  public void setAddInactiveDevice(Long addInactiveDevice)
  {
    this.addInactiveDevice=addInactiveDevice;
  }

  
  /**获取*/
  @JsonProperty
  public Long getRemainActiveDevice()
  {
   return this.remainActiveDevice;
  }

  /**设置*/
  public void setRemainActiveDevice(Long remainActiveDevice)
  {
    this.remainActiveDevice=remainActiveDevice;
  }

  
  /**获取*/
  @JsonProperty
  public Long getRemainInactiveDevice()
  {
   return this.remainInactiveDevice;
  }

  /**设置*/
  public void setRemainInactiveDevice(Long remainInactiveDevice)
  {
    this.remainInactiveDevice=remainInactiveDevice;
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

  //自动生成区域结束
 }
