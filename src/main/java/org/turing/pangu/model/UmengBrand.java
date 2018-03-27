/**
 * 
 * Title：UmengBrand
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年03月27日 
 * @since 2017年03月27日 
 */

package org.turing.pangu.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

 /**UmengBrand*/
 public class UmengBrand extends BaseModel<UmengBrand>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 570571490585831518L;

  /***/
  private String brand;

  /***/
  private String brandModel;

  /***/
  private String width;

  /***/
  private String height;

  /***/
  private String weight;


  
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
  public String getBrandModel()
  {
   return this.brandModel;
  }

  /**设置*/
  public void setBrandModel(String brandModel)
  {
    this.brandModel=brandModel;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getWidth()
  {
   return this.width;
  }

  /**设置*/
  public void setWidth(String width)
  {
    this.width=width;
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
  public String getWeight()
  {
   return this.weight;
  }

  /**设置*/
  public void setWeight(String weight)
  {
    this.weight=weight;
  }

  //自动生成区域结束
 }
