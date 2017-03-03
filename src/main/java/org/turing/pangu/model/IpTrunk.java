package org.turing.pangu.model;

/**
 * 
 * Title：IpTrunk
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年03月02日 
 * @since 2017年03月02日 
 */

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

 /**IpTrunk*/
 public class IpTrunk extends BaseModel<IpTrunk>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 4584321488451968357L;

  /***/
  private String ip;

  /***/
  private String address;

  /***/
  private Integer cityCode;

  /***/
  private Integer isVaild;

  /***/
  private String configure;

  /***/
  private Integer allocCount;

  /***/
  private Integer successCount;

  /***/
  private Date createDate;

  /***/
  private Date updateDate;


  
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
  public Integer getCityCode()
  {
   return this.cityCode;
  }

  /**设置*/
  public void setCityCode(Integer cityCode)
  {
    this.cityCode=cityCode;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getIsVaild()
  {
   return this.isVaild;
  }

  /**设置*/
  public void setIsVaild(Integer isVaild)
  {
    this.isVaild=isVaild;
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
  public Integer getAllocCount()
  {
   return this.allocCount;
  }

  /**设置*/
  public void setAllocCount(Integer allocCount)
  {
    this.allocCount=allocCount;
  }

  
  /**获取*/
  @JsonProperty
  public Integer getSuccessCount()
  {
   return this.successCount;
  }

  /**设置*/
  public void setSuccessCount(Integer successCount)
  {
    this.successCount=successCount;
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
