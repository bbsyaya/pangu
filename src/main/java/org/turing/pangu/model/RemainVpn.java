/**
 * 
 * Title：RemainVpn
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

 /**RemainVpn*/
 public class RemainVpn extends BaseModel<RemainVpn>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 4979611484483986455L;

  /***/
  private String ipList;

  /***/
  private String name;

  /***/
  private Date createDate;

  /***/
  private Date updateDate;


  
  /**获取*/
  @JsonProperty
  @Length(max =5592405 )
  public String getIpList()
  {
   return this.ipList;
  }

  /**设置*/
  public void setIpList(String ipList)
  {
    this.ipList=ipList;
  }

  
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
