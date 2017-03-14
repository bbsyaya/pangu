/**
 * 
 * Title：Computer
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年03月14日 
 * @since 2017年03月14日 
 */

package org.turing.pangu.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

 /**Computer*/
 public class Computer extends BaseModel<Computer>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 7710131489462338020L;

  /***/
  private Long userId;

  /***/
  private Long vpnId;

  /***/
  private Integer isValid;

  /***/
  private String deviceSerial;

  /***/
  private String deviceToken;

  /***/
  private String address;

  /***/
  private Date createDate;

  /***/
  private Date updateDate;


  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Long getUserId()
  {
   return this.userId;
  }

  /**设置*/
  public void setUserId(Long userId)
  {
    this.userId=userId;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Long getVpnId()
  {
   return this.vpnId;
  }

  /**设置*/
  public void setVpnId(Long vpnId)
  {
    this.vpnId=vpnId;
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
  @NotEmpty(groups = {Default.class,Save.class})
  public String getDeviceSerial()
  {
   return this.deviceSerial;
  }

  /**设置*/
  public void setDeviceSerial(String deviceSerial)
  {
    this.deviceSerial=deviceSerial;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
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
  @Length(max =255 )
  public String getAddress()
  {
   return this.address;
  }

  /**设置*/
  public void setAddress(String address)
  {
    this.address=address;
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
