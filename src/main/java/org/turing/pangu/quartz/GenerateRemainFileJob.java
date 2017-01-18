package org.turing.pangu.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.turing.pangu.engine.RemainEngine;

public class GenerateRemainFileJob {
	private static final Logger logger = Logger.getLogger(GenerateRemainFileJob.class);
	public void execute(){  
        try{ 
        	logger.debug("execute - GenerateRemainFileJob -- " + new Date());
        	RemainEngine.getInstance().generateRemainFile();
         }catch(Exception ex){  
             ex.printStackTrace();  
         }  
     } 
}
