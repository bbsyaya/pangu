package org.turing.pangu.utils;

public class EnumUtils {

	/**
	 * 用户状态（0未激活，1激活，2锁定 3黑名单）
	 * 
	 * @author turing
	 */
	public enum UserState {
		/** 未激活 */
		UNACTIVED,
		/** 激活 */
		ACTIVED,
		/** 锁定 */
		LOCKED,
		/** 黑名单 */
		BLACKED
	}

	/**
	 * 记录状态（0处理中，1成功，2失败）
	 * 
	 * @author turing
	 */
	public enum RecordState {
		/** 处理中 */
		HANDLEING,
		/** 成功 */
		SUCCESSS,
		/** 失败 */
		ERROR
	}

	/**
	 * 操作类型
	 * 
	 * @author turing
	 */
	public enum OperateType {
		/** 0充值 */
		RECHARGE,
		/** 1提现 */
		WITHDRAW,
		/** 2投资 */
		INVEST,
		/** 3流标 */
		DRAIN,
		/** 4财务放款拒绝 */
		LOANNO,
		/** 5通过放款 */
		LOANYES,
		/** 6废弃状态-手续费 */
		CHARGE,
		/** 7融资正常冻结 */
		NORMALFINANCEFREEZE,
		/** 8正常还理财 */
		NORMALFINANCEREPAY,
		/** 9理财逾期 */
		INVESTOVERDUE,
		/** 10担保垫付 */
		GUARANTEEREPAY,
		/** 11逾期还担保 */
		GUARANTEEFINANCEREPAY,
		/** 12担保坏账 */
		GUARANTEEBAD,
		/** 13逾期计息 */
		OVERDUEINTEREST,
		/** 14逾期还理财 */
		INVESTFINANCEREPAY,
		/** 15理财坏账 */
		INVESTBAD,
		/** 16备付金 */
		RESERVE,
		/** 17废弃状态-活动奖励 */
		ACTIVEAWARD,
		/** 18绑卡 */
		BINDBANK,
		/** 19线下充值 */
		OFFLINERECHARGE,
		/** 20线下提现 */
		OFFLINEWITHDRAW,
	}

	public enum FundType {
		/** 0资金充值 */
		FUNDRECHARGE,
		/** 1资金提现 */
		FUNDWITHDRAW,
		/** 2发放活动奖励 */
		ACTIVEAWARDGRANT,
		/** 3领取活动奖励 */
		ACTIVEAWARDRECEIVE,
		/** 4投资冻结 */
		FREEZEINVEST,
		/** 5备付金冻结 */
		FREEZERESERVE,
		/** 6还款冻结 */
		FREEZEREPAY,
		/** 7流标退款 */
		RELIEVEDRAIN,
		/** 8投资生效解冻 */
		RELIEVELOANYES,
		/** 9财务放款拒绝退款 */
		RELIEVELOANNO,
		/** 10借入 */
		LOANIN,
		/** 11借出 */
		LOANOUT,
		/** 12缴纳手续费 */
		FEEPAY,
		/** 13收取手续费 */
		FEECOLLECT,
		/** 14线下充值 */
		OFFLINERECHARGE,
		/** 15线下提现 */
		OFFLINEWITHDRAW,
		/** 16还款 */
		REPAYOUT,
		/** 17回款 */
		REPAYIN
	}

	/**
	 * 项目状态
	 * 
	 * @author turing
	 */
	public enum ProjectState {
		/** 0初始状态 */
		INITIAL,
		/** 1待审核 */
		AUDITING,
		/** 2审核通过 */
		RISKYES,
		/** 3审核不通过 */
		RISKNO,
		/** 4财务审核通过 */
		FINANCIALYES,
		/** 5财务拒绝通过 */
		FINANCIALNO,
		/** 6总经理审核通过 */
		BOSSYES,
		/** 7总经理拒绝通过 */
		BOSSNO,
		/** 8运营拒绝通过 */
		TRADENO,
		/** 9运营确认通过 */
		TRADEYES,
		/** 10发标借款中 */
		BORROWING,
		/** 11满标 */
		FULL,
		/** 12流标 */
		DRAIN,
		/** 13财务放款拒绝 */
		FINANCIALLOANNO,
		/** 14财务放款通过 */
		FINANCIALLOANYES,
		/** 15融资方正常还款冻结 */
		NORMALFINANCEFREEZE,
		/** 16融资方正常还理财 */
		NORMALFINANCEREPAY,
		/** 17融资方逾期 */
		FINANCEOVERDUE,
		/** 18担保垫付 */
		GUARANTEEREPAY,
		/** 19逾期还担保 */
		GUARANTEEFINANCEREPAY,
		/** 20担保坏账 */
		GUARANTEEBAD,
		/** 21理财逾期 */
		INVESTOVERDUE,
		/** 22逾期还理财 */
		INVESTFINANCEREPAY,
		/** 23理财端坏账 */
		INVESTBAD,
		/** 24已还款 */
		REPAIED,
		/** 25正常结束 */
		NORMALEND
	}

	/**
	 * 还款方式（0一次性 1按月 2按年）
	 * 
	 * @author turing
	 *
	 */
	public enum RepayType {
		/** 一次性 */
		ONCEREPAY,
		/** 先息后本，月末付息 */
		MonthEndInterestBeforePrincipalRepay,
		/** 先息后本，月初付息 */
		MonthBeginInterestBeforePrincipalRepay,
		/** 自定义按月还款 */
		MonthSelfDefineRepay
	}

	/**
	 * 项目填写项的类型
	 * 
	 * @author turing
	 *
	 */
	public enum ItemType {
		/** 文字 */
		TEXT,
		/** 图片 */
		IMAGE
	}

	/**
	 * 用户类型（-1全部 0投资个人，1投资企业，2融资企业，3担保企业，4、保理企业）
	 * 
	 * @author turing
	 *
	 */
	public enum UserType {
		/** 0投资个人 */
		INVESTPERSON,
		/** 1投资企业 */
		INVESTCOMPANY,
		/** 2融资企业 */
		FINANCECOMPANY,
		/** 3担保企业 */
		GUARANTEECOMPANY,
		/** 4平台 */
		PLATFORM
	}

	/**
	 * 还款状态（0待还款，1已还款）
	 * 
	 * @author turing
	 *
	 */
	public enum PlanState {
		/** 待还款 */
		REPAIEDING,
		/** 已还款 */
		REPAIEDED,
		/** 坏账 */
		BAD
	}

	/**
	 * 推荐类型
	 * 
	 * @author turing
	 *
	 */
	public enum RecommendType {
		/** 普通 */
		NORMAL,
		/** 推荐到首页 */
		RECOMMENDINDEX
	}

	/**
	 * 手机项目状态
	 * 
	 * @author Dick
	 *
	 */
	public enum MobileProjectState {
		/** 发标借款中 */
		BORROWING,
		/** 满标 */
		FULL,
		/** 流标 */
		DRAIN,
		/** 放款 */
		LOAN,
		/** 收款 */
		RECEIVABLES,
		/** 坏账 */
		BAD,
		/** 理财逾期 */
		OVERDUE

	}

	/**
	 * 操作类型(-1全部、0充值、1提现、2投标、3流标退款、4财务放款拒绝、5放款、6手续费、7融资正常冻结、8正常还理财、9逾期、10担保垫付，11逾期还担保、12担保坏账、13逾期计息、14逾期还理财、15理财坏账、16冻结、17活动奖励)
	 * 
	 * @author Dick
	 */
	public enum MobileOperateType {
		/** 充值 */
		RECHARGE,
		/** 提现 */
		WITHDRAW,
		/** 冻结 */
		FREEZE,
		/** 退款 */
		REFUND,
		/** 放款计息 */
		LOANINTEREST,
		/** 手续费 */
		CHARGE,
		/** 收款 */
		RECEIVABLES,
		/** 坏账 */
		BAD,
		/** 逾期计息 */
		OVERDUEINTEREST,
		/** 　投资 */
		INVEST,
		/** 活动奖励 */
		ACTIVEAWARD

	}

	public enum PayType {
		KUAIQIAN, OFFLINE, YEEPAY
	}

	public enum ThirdOperateType {
		/** 绑卡 */
		BINDBANK,
		/** 充值 */
		RECHARGE,
		/** 提现 */
		WITHDRAW
	}

	public enum PayOriginType {
		/** 融资方 */
		FINANCE,
		/** 担保方 */
		GUARANTEE,
		/** 平台方 */
		PLATFORM
	}

	public enum RightsType {
		/** 菜单 */
		MENU,
		/** 页面 */
		PAGE,
		/** 功能 */
		FUNCTION
	}

}
