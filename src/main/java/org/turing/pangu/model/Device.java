/**
 * 
 * Title：Device
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年03月13日 
 * @since 2017年03月13日 
 */

package org.turing.pangu.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

 /**Device*/
 public class Device extends BaseModel<Device>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 653941489370520017L;

  /***/
  private Long appId;

  /***/
  private Integer isActived;

  /***/
  private Integer isStock;

  /***/
  private String phone;

  /***/
  private String ip;

  /***/
  private String imei;

  /***/
  private String imsi;

  /***/
  private Integer width;

  /***/
  private Integer height;

  /***/
  private String brand;

  /***/
  private String manufacture;

  /***/
  private String model;

  /***/
  private Integer sdk;

  /***/
  private Integer networkType;

  /***/
  private Integer networkSubtype;

  /***/
  private Integer deviceType;

  /***/
  private String configure;

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
  @NotNull(groups = {Default.class,Save.class})
  public Integer getIsActived()
  {
   return this.isActived;
  }

  /**设置*/
  public void setIsActived(Integer isActived)
  {
    this.isActived=isActived;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getIsStock()
  {
   return this.isStock;
  }

  /**设置*/
  public void setIsStock(Integer isStock)
  {
    this.isStock=isStock;
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
  @Length(max =255 )
  @NotEmpty(groups = {Default.class,Save.class})
  public String getImei()
  {
   return this.imei;
  }

  /**设置*/
  public void setImei(String imei)
  {
    this.imei=imei;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getImsi()
  {
   return this.imsi;
  }

  /**设置*/
  public void setImsi(String imsi)
  {
    this.imsi=imsi;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getWidth()
  {
   return this.width;
  }

  /**设置*/
  public void setWidth(Integer width)
  {
    this.width=width;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getHeight()
  {
   return this.height;
  }

  /**设置*/
  public void setHeight(Integer height)
  {
    this.height=height;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getBrand()
  {
   return this.brand;
  }

  /**设置*/
  public void setBrand(String brand)
  {
    this.brand=brand;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getManufacture()
  {
   return this.manufacture;
  }

  /**设置*/
  public void setManufacture(String manufacture)
  {
    this.manufacture=manufacture;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getModel()
  {
   return this.model;
  }

  /**设置*/
  public void setModel(String model)
  {
    this.model=model;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getSdk()
  {
   return this.sdk;
  }

  /**设置*/
  public void setSdk(Integer sdk)
  {
    this.sdk=sdk;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getNetworkType()
  {
   return this.networkType;
  }

  /**设置*/
  public void setNetworkType(Integer networkType)
  {
    this.networkType=networkType;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getNetworkSubtype()
  {
   return this.networkSubtype;
  }

  /**设置*/
  public void setNetworkSubtype(Integer networkSubtype)
  {
    this.networkSubtype=networkSubtype;
  }

  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getDeviceType()
  {
   return this.deviceType;
  }

  /**设置*/
  public void setDeviceType(Integer deviceType)
  {
    this.deviceType=deviceType;
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
