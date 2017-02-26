package org.turing.pangu.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.turing.pangu.engine.TaskEngine;

public class UpdateTaskToDBJob {
	private static final Logger logger = Logger.getLogger(UpdateTaskToDBJob.class);
	public void execute(){  
        try{ 
        	logger.info("execute - UpdateTaskToDBJob -- " + new Date());
        	TaskEngine.getInstance().updateTaskToDBJob();
         }catch(Exception ex){  
             ex.printStackTrace();  
         }  
     } 
}
