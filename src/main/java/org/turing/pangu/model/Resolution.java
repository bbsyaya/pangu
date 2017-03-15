/**
 * 
 * Title：Resolution
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年03月10日 
 * @since 2017年03月10日 
 */

package org.turing.pangu.model;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;

 /**Resolution*/
 public class Resolution extends BaseModel<Resolution>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 4578141489151028886L;

  /***/
  private Long platformId;

  /***/
  private Integer height;

  /***/
  private Integer width;

  /***/
  private Integer isSupport;


  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
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
  @NotNull(groups = {Default.class,Save.class})
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
  @NotNull(groups = {Default.class,Save.class})
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
  @NotNull(groups = {Default.class,Save.class})
  public Integer getIsSupport()
  {
   return this.isSupport;
  }

  /**设置*/
  public void setIsSupport(Integer isSupport)
  {
    this.isSupport=isSupport;
  }

  //自动生成区域结束
 }
