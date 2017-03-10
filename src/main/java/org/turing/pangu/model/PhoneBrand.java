/**
 * 
 * Title：PhoneBrand
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年03月10日 
 * @since 2017年03月10日 
 */

package org.turing.pangu.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

 /**PhoneBrand*/
 public class PhoneBrand extends BaseModel<PhoneBrand>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 1913271489149972541L;

  /***/
  private String imeiHead;

  /***/
  private String brand;

  /***/
  private String model;

  /***/
  private Integer height;

  /***/
  private Integer width;

  /***/
  private Integer os;

  /***/
  private String cpu;

  /***/
  private String gpu;

  /***/
  private String cpuFrame;

  /***/
  private Integer gps;

  /***/
  private Integer weight;

  /***/
  private Integer chinaMobile;

  /***/
  private Integer chinaUnicom;

  /***/
  private Integer chinaTelecom;

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
  public Integer getOs()
  {
   return this.os;
  }

  /**设置*/
  public void setOs(Integer os)
  {
    this.os=os;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getCpu()
  {
   return this.cpu;
  }

  /**设置*/
  public void setCpu(String cpu)
  {
    this.cpu=cpu;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getGpu()
  {
   return this.gpu;
  }

  /**设置*/
  public void setGpu(String gpu)
  {
    this.gpu=gpu;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =255 )
  public String getCpuFrame()
  {
   return this.cpuFrame;
  }

  /**设置*/
  public void setCpuFrame(String cpuFrame)
  {
    this.cpuFrame=cpuFrame;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getGps()
  {
   return this.gps;
  }

  /**设置*/
  public void setGps(Integer gps)
  {
    this.gps=gps;
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
