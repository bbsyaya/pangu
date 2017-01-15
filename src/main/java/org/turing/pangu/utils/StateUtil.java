package org.turing.pangu.utils;

import org.turing.pangu.utils.EnumUtils.FundType;
import org.turing.pangu.utils.EnumUtils.MobileOperateType;
import org.turing.pangu.utils.EnumUtils.MobileProjectState;
import org.turing.pangu.utils.EnumUtils.ProjectState;

public class StateUtil {

	/**
	 * 根据手机端状态得到后台状态
	 * 
	 * @param state
	 * @return
	 */
	public static int[] getDbProjectState(int state) {
		int[] inState = null;
		if (state == MobileProjectState.BORROWING.ordinal()) {
			inState = new int[] { ProjectState.BORROWING.ordinal() };
		} else if (state == MobileProjectState.FULL.ordinal()) {
			inState = new int[] { ProjectState.FULL.ordinal() };
		} else if (state == MobileProjectState.DRAIN.ordinal()) {
			inState = new int[] { ProjectState.DRAIN.ordinal(), ProjectState.FINANCIALLOANNO.ordinal() };
		} else if (state == MobileProjectState.LOAN.ordinal()) {
			inState = new int[] { ProjectState.FINANCIALLOANYES.ordinal() };
		} else if (state == MobileProjectState.RECEIVABLES.ordinal()) {
			inState = new int[] { ProjectState.REPAIED.ordinal(), ProjectState.NORMALEND.ordinal() };
		} else if (state == MobileProjectState.BAD.ordinal()) {
			inState = new int[] { ProjectState.INVESTBAD.ordinal() };
		} else if (state == MobileProjectState.OVERDUE.ordinal()) {
			inState = new int[] { ProjectState.INVESTOVERDUE.ordinal() };
		}
		return inState;
	}

	/**
	 * 根据后台状态得到手机端状态
	 * 
	 * @param state
	 * @return
	 */
	public static int getMobileProjectState(int state) {
		ProjectState projectState = ProjectState.values()[state];
		switch (projectState) {
		case BORROWING:
			return MobileProjectState.BORROWING.ordinal();
		case FULL:
			return MobileProjectState.FULL.ordinal();
		case DRAIN:
		case FINANCIALLOANNO:
			return MobileProjectState.DRAIN.ordinal();
		case FINANCIALLOANYES:
		case FINANCEOVERDUE:
			return MobileProjectState.LOAN.ordinal();
		case NORMALFINANCEFREEZE:
		case NORMALFINANCEREPAY:
		case GUARANTEEREPAY:
		case GUARANTEEFINANCEREPAY:
		case GUARANTEEBAD:
		case INVESTFINANCEREPAY:
		case REPAIED:
		case NORMALEND:
			return MobileProjectState.RECEIVABLES.ordinal();
		case INVESTBAD:
			return MobileProjectState.BAD.ordinal();
		case INVESTOVERDUE:
			return MobileProjectState.OVERDUE.ordinal();
		default:
			return -1;
		}
	}

	public static int[] getDbRecordState(int state) {
		int[] inState = null;
		MobileOperateType operateType = MobileOperateType.values()[state];
		switch (operateType) {
		case RECHARGE:
			inState = new int[] { FundType.FUNDRECHARGE.ordinal() };
			break;
		case WITHDRAW:
			inState = new int[] { FundType.FUNDWITHDRAW.ordinal() };
			break;
		case FREEZE:
			inState = new int[] { FundType.FREEZEINVEST.ordinal() };
			break;
		case REFUND:
			inState = new int[] { FundType.RELIEVEDRAIN.ordinal(), FundType.RELIEVELOANNO.ordinal() };
			break;
		case OVERDUEINTEREST:
			inState = new int[] { -1 };// TODO
			break;
		case CHARGE:
			inState = new int[] { FundType.FEEPAY.ordinal() };
			break;
		case RECEIVABLES:
			inState = new int[] { FundType.REPAYIN.ordinal() };
			break;
		case LOANINTEREST:
			inState = new int[] { FundType.LOANOUT.ordinal() };
			break;
		case ACTIVEAWARD:
			inState = new int[] { FundType.ACTIVEAWARDRECEIVE.ordinal() };
			break;
		case BAD:
			inState = new int[] { -1 };// TODO
		default:
			break;
		}
		return inState;
	}

	public static int getMobileRecordState(int state) {
		int inState = -1;
		FundType fundType = FundType.values()[state];
		switch (fundType) {
		case FUNDRECHARGE:
		case OFFLINERECHARGE:
			inState = MobileOperateType.RECHARGE.ordinal();
			break;
		case FUNDWITHDRAW:
		case OFFLINEWITHDRAW:
			inState = MobileOperateType.WITHDRAW.ordinal();
			break;
		case FREEZEINVEST:
			inState = MobileOperateType.INVEST.ordinal();
			break;
		case RELIEVEDRAIN:
		case RELIEVELOANNO:
			inState = MobileOperateType.REFUND.ordinal();
			break;
		case LOANOUT:
			inState = MobileOperateType.LOANINTEREST.ordinal();
			break;
		case FEEPAY:
			inState = MobileOperateType.CHARGE.ordinal();
			break;
		case REPAYIN:
			inState = MobileOperateType.RECEIVABLES.ordinal();
			break;
		case ACTIVEAWARDRECEIVE:
			inState = MobileOperateType.ACTIVEAWARD.ordinal();
			break;
		default:
			inState=-1;
			break;
		}

		return inState;
	}

	public static String getMobileRecordStateText(int state) {
		String text = "";
		MobileOperateType operateType = MobileOperateType.values()[state];
		switch (operateType) {
		case RECHARGE:
			text = SpringUtils.getMessage("phone_record_state_recharge");
			break;
		case WITHDRAW:
			text = SpringUtils.getMessage("phone_record_state_withdraw");
			break;
		case FREEZE:
			text = SpringUtils.getMessage("phone_record_state_freeze");
			break;
		case INVEST:
			text = SpringUtils.getMessage("phone_record_state_invest");
			break;
		case REFUND:
			text = SpringUtils.getMessage("phone_record_state_refund");
			break;
		case LOANINTEREST:
			text = SpringUtils.getMessage("phone_record_state_loanInterest");
			break;
		case OVERDUEINTEREST:
			text = SpringUtils.getMessage("phone_record_state_overdueInterest");
			break;
		case CHARGE:
			text = SpringUtils.getMessage("phone_record_state_charge");
			break;
		case RECEIVABLES:
			text = SpringUtils.getMessage("phone_record_state_receivables");
			break;
		case BAD:
			text = SpringUtils.getMessage("phone_record_state_bad");
			break;
		case ACTIVEAWARD:
			text = SpringUtils.getMessage("phone_record_state_actInveaward");
			break;
		default:
			break;
		}
		return text;
	}
}
