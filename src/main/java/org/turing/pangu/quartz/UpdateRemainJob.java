package org.turing.pangu.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.turing.pangu.engine.RemainEngine;

public class UpdateRemainJob {
	private static final Logger logger = Logger.getLogger(UpdateRemainJob.class);
	public void execute(){  
        try{ 
        	logger.debug("execute - UpdateRemainJob -- " + new Date());
        	RemainEngine.getInstance().updateRemainData();
         }catch(Exception ex){  
             ex.printStackTrace();  
         }  
     } 
}
