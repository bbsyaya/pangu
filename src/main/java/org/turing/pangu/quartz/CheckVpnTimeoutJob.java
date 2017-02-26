package org.turing.pangu.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.turing.pangu.engine.DeviceEngine;
import org.turing.pangu.engine.TaskEngine;

public class CheckVpnTimeoutJob {
	private static final Logger logger = Logger.getLogger(CheckVpnTimeoutJob.class);
	public void execute(){  
        try{ 
        	logger.info("execute - CheckVpnTimeoutJob -- " + new Date());
        	TaskEngine.getInstance().checkVpnTimeoutJob();
        	DeviceEngine.getInstance().saveReportDateToDB();
         }catch(Exception ex){  
             ex.printStackTrace();  
         }  
     } 
}
