package org.turing.pangu.quartz;

import org.turing.pangu.engine.RemainEngine;

public class UpdateRemainJob {
	public void execute(){  
        try{  
        	RemainEngine.getInstance().updateRemainData();
         }catch(Exception ex){  
             ex.printStackTrace();  
         }  
     } 
}
