/**
 * 
 * Title：LoginToken
 * Copyright: Copyright (c) 2014
 * Company: 深电中心
 * @author axi
 * @version 1.0, 2015年10月30日 
 * @since 2015年10月30日 
 */

package org.turing.pangu.controller.common;

import java.util.Date;

/** LoginToken */
public class LoginToken{
	private static final Long serialVersionUID = -7904483570243505456L;
	/** 授权 */
	private String accessToken;
	/** 创建时间 */
	private Date createTime;
	public String getAccessToken() {
		return this.accessToken;
	}

	/** 设置授权 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * 获取创建日期
	 * 
	 * @return 创建日期
	 */
	public Date getCreateTime() {
		if (createTime == null) {
			createTime = new Date();
		}
		return createTime;
	}

	/**
	 * 设置创建日期
	 * 
	 * @param createDate
	 *            创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
