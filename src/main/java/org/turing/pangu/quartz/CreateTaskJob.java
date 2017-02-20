package org.turing.pangu.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.turing.pangu.engine.TaskEngine;

public class CreateTaskJob {
	private static final Logger logger = Logger.getLogger(CreateTaskJob.class);
	public void execute(){  
        try{ 
        	logger.info("execute - GenerateRemainFileJob -- " + new Date());
        	TaskEngine.getInstance().createTodayTask();
         }catch(Exception ex){  
             ex.printStackTrace();  
         }  
     } 
}
