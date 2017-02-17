/**
 * 
 * Title：Platform
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年02月17日 
 * @since 2017年02月17日 
 */

package org.turing.pangu.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

 /**Platform*/
 public class Platform extends BaseModel<Platform>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 8084751487329886826L;

  /***/
  private String name;

  /***/
  private String introduce;

  /***/
  private String shellConfig;

  /***/
  private String fixData;

  /***/
  private String resolution;

  /***/
  private String blackIp;

  /***/
  private String taskConfig;

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
  public String getIntroduce()
  {
   return this.introduce;
  }

  /**设置*/
  public void setIntroduce(String introduce)
  {
    this.introduce=introduce;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getShellConfig()
  {
   return this.shellConfig;
  }

  /**设置*/
  public void setShellConfig(String shellConfig)
  {
    this.shellConfig=shellConfig;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getFixData()
  {
   return this.fixData;
  }

  /**设置*/
  public void setFixData(String fixData)
  {
    this.fixData=fixData;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getResolution()
  {
   return this.resolution;
  }

  /**设置*/
  public void setResolution(String resolution)
  {
    this.resolution=resolution;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getBlackIp()
  {
   return this.blackIp;
  }

  /**设置*/
  public void setBlackIp(String blackIp)
  {
    this.blackIp=blackIp;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getTaskConfig()
  {
   return this.taskConfig;
  }

  /**设置*/
  public void setTaskConfig(String taskConfig)
  {
    this.taskConfig=taskConfig;
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
