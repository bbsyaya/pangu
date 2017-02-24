/**
 * 
 * Title：RemainIp
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年02月24日 
 * @since 2017年02月24日 
 */

package org.turing.pangu.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

 /**RemainIp*/
 public class RemainIp extends BaseModel<RemainIp>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 1906951487909274208L;

  /***/
  private Long vpnId;

  /***/
  private String ip;

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
  @Length(max =255 )
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
