/**
 * 
 * Title：PhoneTrunk
 * Copyright: Copyright (c) 2016
 * Company: turing
 * @author turing
 * @version 1.0, 2017年03月02日 
 * @since 2017年03月02日 
 */

package org.turing.pangu.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

 /**PhoneTrunk*/
 public class PhoneTrunk extends BaseModel<PhoneTrunk>
 {
    
  //自动生成区域开始
  private static final long serialVersionUID = 6999911488452201392L;

  /***/
  private Integer num;

  /***/
  private String quhao;

  /***/
  private String city;

  /***/
  private String type;


  
  /**获取*/
  @JsonProperty
  @NotNull(groups = {Default.class,Save.class})
  public Integer getNum()
  {
   return this.num;
  }

  /**设置*/
  public void setNum(Integer num)
  {
    this.num=num;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =7 )
  @NotEmpty(groups = {Default.class,Save.class})
  public String getQuhao()
  {
   return this.quhao;
  }

  /**设置*/
  public void setQuhao(String quhao)
  {
    this.quhao=quhao;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =33 )
  @NotEmpty(groups = {Default.class,Save.class})
  public String getCity()
  {
   return this.city;
  }

  /**设置*/
  public void setCity(String city)
  {
    this.city=city;
  }

  
  /**获取*/
  @JsonProperty
  @Length(max =20 )
  @NotEmpty(groups = {Default.class,Save.class})
  public String getType()
  {
   return this.type;
  }

  /**设置*/
  public void setType(String type)
  {
    this.type=type;
  }

  //自动生成区域结束
 }
