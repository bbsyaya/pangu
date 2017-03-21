/**
 * 
 * Title：PhoneBrand
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年03月17日 
 * @since 2017年03月17日 
 */

package org.turing.pangu.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

 /**PhoneBrand*/
 public class PhoneBrand extends BaseModel<PhoneBrand>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 4380451489731736556L;

  /***/
  private String imeiHead;

  /***/
  private Integer sdk;

  /***/
  private String brand;

  /***/
  private String manufacture;

  /***/
  private String model;

  /***/
  private Integer width;

  /***/
  private Integer height;

  /***/
  private Integer weight;

  /***/
  private Integer chinaMobile;

  /***/
  private Integer chinaUnicom;

  /***/
  private Integer chinaTelecom;

  /***/
  private String configure;

  /***/
  private Date createDate;

  /***/
  private Date updateDate;


  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getImeiHead()
  {
   return this.imeiHead;
  }

  /**设置*/
  public void setImeiHead(String imeiHead)
  {
    this.imeiHead=imeiHead;
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
  public Integer getWeight()
  {
   return this.weight;
  }

  /**设置*/
  public void setWeight(Integer weight)
  {
    this.weight=weight;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getChinaMobile()
  {
   return this.chinaMobile;
  }

  /**设置*/
  public void setChinaMobile(Integer chinaMobile)
  {
    this.chinaMobile=chinaMobile;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getChinaUnicom()
  {
   return this.chinaUnicom;
  }

  /**设置*/
  public void setChinaUnicom(Integer chinaUnicom)
  {
    this.chinaUnicom=chinaUnicom;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getChinaTelecom()
  {
   return this.chinaTelecom;
  }

  /**设置*/
  public void setChinaTelecom(Integer chinaTelecom)
  {
    this.chinaTelecom=chinaTelecom;
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
