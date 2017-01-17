package org.turing.pangu.quartz;

import org.turing.pangu.engine.RemainEngine;

public class GenerateRemainFileJob {
	public void execute(){  
        try{  
        	RemainEngine.getInstance().generateRemainFile();
         }catch(Exception ex){  
             ex.printStackTrace();  
         }  
     } 
}
