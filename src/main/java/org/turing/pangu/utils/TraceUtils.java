package org.turing.pangu.utils;

import org.apache.log4j.Logger;

public class TraceUtils {
	private static final Logger logger = Logger.getLogger(TraceUtils.class);
	public static String getTraceInfo(){    
        StringBuffer sb = new StringBuffer();     
        StackTraceElement[] stacks = new Throwable().getStackTrace();    
        int stacksLen = stacks.length;    
        sb.append("class: " ).append(stacks[1].getClassName()).append("; method: ").append(stacks[1].getMethodName()).append("; number: ").append(stacks[1].getLineNumber());    
        logger.info(sb.toString());
        return sb.toString();    
    } 
}
