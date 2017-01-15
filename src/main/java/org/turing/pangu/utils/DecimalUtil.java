package org.turing.pangu.utils;

import java.math.BigDecimal;

public class DecimalUtil {
	
	
	/**
	 * 向上得到两位小数
	 * @author dick
	 * @param decimal
	 * @return
	 */
	public static BigDecimal getDecimalUp(BigDecimal decimal){
		decimal=decimal.setScale(2, BigDecimal.ROUND_UP);
		return decimal;
	}
	
	/**
	 * 向下得到两位小数
	 * @author dick
	 * @param decimal
	 * @return
	 */
	public static BigDecimal getDecimalDown(BigDecimal decimal){
		decimal=decimal.setScale(2, BigDecimal.ROUND_DOWN);
		return decimal;
	}
}
