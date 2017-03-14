package org.turing.pangu.engine;

import java.util.Date;

import org.apache.log4j.Logger;


/*
 * 每天的时间区间管理
 * */
public class TimeMng {
	private static final Logger logger = Logger.getLogger(TimeMng.class);
	public static int SPAN_TIME = 10;// 10S 
	
	public static int INCREMENT_MONEY_TIMEOUT = 10*60*1000;
	public static int INCREMENT_WATERAMY_TIMEOUT = 6*60*1000;
	public static int STOCK_MONEY_TIMEOUT = 10*60*1000;
	public static int STOCK_WATERAMY_TIMEOUT = 6*60*1000;
	public static int FREE_TIMEOUT = INCREMENT_MONEY_TIMEOUT;
	public static float OPEN_MAX_VPN_PHONE_WEIGHT = 1;

	public static int MAX_SEND_COUNT_FOR_APP = 1;
	private static TimeMng mInstance = new TimeMng();
	public static TimeMng getInstance()
	{
		if(null == mInstance)
			mInstance = new TimeMng();
		return mInstance;
	}
	public static int getMaxSendCount(){
		return MAX_SEND_COUNT_FOR_APP;
	}
	public static float getTimeZoneWeight(){
		float weigth = 0.0f;
		return 1.0f;
		/*
		Calendar cal=Calendar.getInstance(TimeZone.getTimeZone( "GMT+8")); 
		int hour =cal.get(Calendar.HOUR_OF_DAY);
		if(hour >= 18 && hour <= 24 ){
			weigth = 1;
		}else if( hour >= 0 && hour <= 6 ){
			weigth = 0.5f;
		}else{
			weigth = 0.8f;
		}
		return weigth;
		*/
	}
	public static void spellTime(Date start){
		Date end = new Date();
		logger.info("spellTime: " + (end.getTime() - start.getTime()) + " ms");
	}
}
