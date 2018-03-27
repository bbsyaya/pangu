package org.turing.pangu.engine;

import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;


/*
 * 短信通知引擎
 * */
public class SMSEngine {
	private static final Logger logger = Logger.getLogger(SMSEngine.class);
	private static SMSEngine mInstance = new SMSEngine();

	public static SMSEngine getInstance() {
		if (null == mInstance)
			mInstance = new SMSEngine();
		return mInstance;
	}
}
