package org.turing.pangu.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.turing.pangu.engine.RemainEngine;
import org.turing.pangu.engine.TaskEngine;

public class UpdateTaskToDBJob {
	private static final Logger logger = Logger.getLogger(UpdateTaskToDBJob.class);
	public void execute(){  
        try{ 
        	logger.debug("execute - UpdateRemainJob -- " + new Date());
        	TaskEngine.getInstance().UpdateTaskToDBJob();
         }catch(Exception ex){  
             ex.printStackTrace();  
         }  
     } 
}
