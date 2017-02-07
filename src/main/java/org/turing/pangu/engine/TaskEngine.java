package org.turing.pangu.engine;

import java.util.List;

import org.turing.pangu.model.App;
import org.turing.pangu.model.RemainVpn;
/*
 * 任务引擎，负责每日任务生成,配置任务,跟踪任务进展,
 * */
public class TaskEngine {
	private static TaskEngine mInstance = new TaskEngine();
	public static TaskEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new TaskEngine();
		return mInstance;
	}
	// 更新任务配置
	public boolean updateTaskConfigure(){
		return true;
	}
}
