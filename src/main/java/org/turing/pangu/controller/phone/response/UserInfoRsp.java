package org.turing.pangu.controller.phone.response;


public class UserInfoRsp {
    private Long id; //会员ID
    private String mobile;  //手机号（部分隐藏）；
    private Long createDate; //注册时间
    private String email; //邮件地址
    private Integer state; //用户状态（0：未激活，1：激活，2：被锁 3：被加入黑名单）
    private Long point; //积分
    private String realName; //真实姓名（部分隐藏）
    private String companyName; //公司名称
	private Boolean isCertified; //身份认证是否成功
    private String portraitUrl; //头像URL地址
    private Boolean isTradePassword; //是否设置过交易密码
    private Integer whichBank; //所属银行
    private String bankCard; //银行卡号（部分隐藏）
    private Boolean isBandBank;//是否绑定银行卡
    
    /**
     * 会员ID
     * @return
     */
    public Long getId() {
		return id;
	}
    
    /**
     * 会员ID
     * @param id
     */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 手机号（部分隐藏）；
	 * @return
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 手机号（部分隐藏）；
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 注册时间
	 * @return
	 */
	public Long getCreateDate() {
		return createDate;
	}
	
	/**
	 * 注册时间
	 * @param createDate
	 */
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 邮件地址
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 邮件地址
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 用户状态（0：未激活，1：激活，2：被锁 3：被加入黑名单）
	 * @return
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * 用户状态（0：未激活，1：激活，2：被锁 3：被加入黑名单）
	 * @param state
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * 积分
	 * @return
	 */
	public Long getPoint() {
		return point;
	}
	
	/**
	 * 积分
	 * @param point
	 */
	public void setPoint(Long point) {
		this.point = point;
	}
	
	/**
	 * 真实姓名（部分隐藏）
	 * @return
	 */
	public String getRealName() {
		return realName;
	}
	
	/**
	 * 真实姓名（部分隐藏）
	 * @param realName
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	/**
	 * 身份认证是否成功
	 * @return
	 */
	public Boolean getIsCertified() {
		return isCertified;
	}
	
	/**
	 * 身份认证是否成功
	 * @param isCertified
	 */
	public void setIsCertified(Boolean isCertified) {
		this.isCertified = isCertified;
	}
	
	
	/**
	 * 头像URL地址
	 * @return
	 */
	public String getPortraitUrl() {
		return portraitUrl;
	}
	
	/**
	 * 头像URL地址
	 * @param portraitUrl
	 */
	public void setPortraitUrl(String portraitUrl) {
		this.portraitUrl = portraitUrl;
	}
	
	/**
	 * 是否设置过交易密码
	 * @return
	 */
	public Boolean getIsTradePassword() {
		return isTradePassword;
	}
	
	/**
	 * 是否设置过交易密码
	 * @param isTradePassword
	 */
	public void setIsTradePassword(Boolean isTradePassword) {
		this.isTradePassword = isTradePassword;
	}
	
	/**
	 * 所属银行
	 * @return
	 */
	public Integer getWhichBank() {
		return whichBank;
	}
	
	/**
	 * 所属银行
	 * @param whichBank
	 */
	public void setWhichBank(Integer whichBank) {
		this.whichBank = whichBank;
	}
	
	/**
	 * 银行卡号（部分隐藏）
	 * @return
	 */
	public String getBankCard() {
		return bankCard;
	}
	
	/**
	 * 银行卡号（部分隐藏）
	 * @param bankCard
	 */
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
	/**
	 * 公司名称
	 * @return
	 */
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 是否绑定银行卡
	 * @return
	 */
	public Boolean getIsBandBank() {
		return isBandBank;
	}
	
	/**
	 * 是否绑定银行卡
	 * @return
	 */
	public void setIsBandBank(Boolean isBandBank) {
		this.isBandBank = isBandBank;
	}
	
	
}
