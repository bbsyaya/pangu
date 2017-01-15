/**
 * 
 * Title：Device
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

 /**Device*/
 public class Device extends BaseModel<Device>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 7789091484483217998L;

  /***/
  private String token;

  /***/
  private Integer deviceType;

  /***/
  private String androidId;

  /***/
  private String androidSerial;

  /***/
  private String androidVersion;

  /***/
  private String blueTooth;

  /***/
  private String board;

  /***/
  private String brand;

  /***/
  private String bssid;

  /***/
  private String carrier;

  /***/
  private String carrierCode;

  /***/
  private String countryCode;

  /***/
  private String display;

  /***/
  private String height;

  /***/
  private String imei;

  /***/
  private String ip;

  /***/
  private String mac;

  /***/
  private String manufacture;

  /***/
  private String model;

  /***/
  private String phone;

  /***/
  private Integer phoneStatus;

  /***/
  private Integer sdk;

  /***/
  private String simSerial;

  /***/
  private Integer simStatus;

  /***/
  private String ssid;

  /***/
  private String ua;

  /***/
  private Date createDate;

  /***/
  private Date updateDate;


  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  @NotEmpty(groups = {Default.class,Save.class})
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
  @Length(max =255 )
  public String getAndroidId()
  {
   return this.androidId;
  }

  /**设置*/
  public void setAndroidId(String androidId)
  {
    this.androidId=androidId;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getAndroidSerial()
  {
   return this.androidSerial;
  }

  /**设置*/
  public void setAndroidSerial(String androidSerial)
  {
    this.androidSerial=androidSerial;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getAndroidVersion()
  {
   return this.androidVersion;
  }

  /**设置*/
  public void setAndroidVersion(String androidVersion)
  {
    this.androidVersion=androidVersion;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getBlueTooth()
  {
   return this.blueTooth;
  }

  /**设置*/
  public void setBlueTooth(String blueTooth)
  {
    this.blueTooth=blueTooth;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getBoard()
  {
   return this.board;
  }

  /**设置*/
  public void setBoard(String board)
  {
    this.board=board;
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
  public String getBssid()
  {
   return this.bssid;
  }

  /**设置*/
  public void setBssid(String bssid)
  {
    this.bssid=bssid;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getCarrier()
  {
   return this.carrier;
  }

  /**设置*/
  public void setCarrier(String carrier)
  {
    this.carrier=carrier;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getCarrierCode()
  {
   return this.carrierCode;
  }

  /**设置*/
  public void setCarrierCode(String carrierCode)
  {
    this.carrierCode=carrierCode;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getCountryCode()
  {
   return this.countryCode;
  }

  /**设置*/
  public void setCountryCode(String countryCode)
  {
    this.countryCode=countryCode;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getDisplay()
  {
   return this.display;
  }

  /**设置*/
  public void setDisplay(String display)
  {
    this.display=display;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getHeight()
  {
   return this.height;
  }

  /**设置*/
  public void setHeight(String height)
  {
    this.height=height;
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
  @NotEmpty(groups = {Default.class,Save.class})
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
  public String getMac()
  {
   return this.mac;
  }

  /**设置*/
  public void setMac(String mac)
  {
    this.mac=mac;
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
  public Integer getPhoneStatus()
  {
   return this.phoneStatus;
  }

  /**设置*/
  public void setPhoneStatus(Integer phoneStatus)
  {
    this.phoneStatus=phoneStatus;
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
  @Length(max =255 )
  public String getSimSerial()
  {
   return this.simSerial;
  }

  /**设置*/
  public void setSimSerial(String simSerial)
  {
    this.simSerial=simSerial;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getSimStatus()
  {
   return this.simStatus;
  }

  /**设置*/
  public void setSimStatus(Integer simStatus)
  {
    this.simStatus=simStatus;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getSsid()
  {
   return this.ssid;
  }

  /**设置*/
  public void setSsid(String ssid)
  {
    this.ssid=ssid;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getUa()
  {
   return this.ua;
  }

  /**设置*/
  public void setUa(String ua)
  {
    this.ua=ua;
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
