package org.turing.pangu.service.quartz;

import org.turing.pangu.Engine.RemainEngine;

public class RemainJob {
	public void execute(){  
        try{  
        	RemainEngine.getInstance().generateRemainFile();
         }catch(Exception ex){  
             ex.printStackTrace();  
         }  
     } 
}
