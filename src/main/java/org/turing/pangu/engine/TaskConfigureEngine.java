package org.turing.pangu.engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.util.FileUtil;
import org.turing.pangu.bean.TaskConfigureBean;
import org.turing.pangu.model.App;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

// 任务配置引擎
public class TaskConfigureEngine {
	private static final int INCREMENT_MONEY_COUNT = 300;
	private static final int INCREMENT_WATERAMY_COUNT = 3000;
	private static final int STOCK_MONEY_COUNT = 60;
	private static final int STOCK_WATERAMY_COUNT = 180;
	private String configurePath = "";
	private List<TaskConfigureBean> configureList = new ArrayList<TaskConfigureBean>(); 
	File mFile = null;
	private static TaskConfigureEngine mInstance = new TaskConfigureEngine();
	public static TaskConfigureEngine getInstance(){
		if(null == mInstance)
			mInstance = new TaskConfigureEngine();
		return mInstance;
	}
	public void init(){
		configurePath =Thread.currentThread().getContextClassLoader().getResource("").getPath();
		configurePath = configurePath + "json/taskConfigure.json";
		int flag = 0;
		mFile = new File(configurePath);
		String contentStr = "";
		try {
			contentStr = FileUtil.readAsString(mFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		configureList = JSON.parseObject(contentStr,
				new TypeReference<List<TaskConfigureBean>>() {
				});
		List<App> appList = TaskEngine.getInstance().getAppList();
		for(App app : appList){
			flag = 0;
			for(TaskConfigureBean bean:configureList){
				if(app.getId() == bean.getAppId()){
					flag = 1;
					break;
				}
			}
			if(0 == flag){
				TaskConfigureBean bean = new TaskConfigureBean();
				bean.setAppId(app.getId());
				bean.setIncrementMoney(INCREMENT_MONEY_COUNT);
				bean.setIncrementWaterAmy(INCREMENT_WATERAMY_COUNT);
				bean.setStockMoney(STOCK_MONEY_COUNT);
				bean.setStockWaterAmy(STOCK_WATERAMY_COUNT);
				configureList.add(bean);
			}
		}
		//----------------------------------------------------------
		List<TaskConfigureBean> tmpList = new ArrayList<TaskConfigureBean>(); 
		for(TaskConfigureBean bean:configureList){
			if(bean == null)break;
			
			flag = 0;
			for(App app : appList){
				if(app.getId() == bean.getAppId()){
					flag = 1;
					tmpList.add(bean);
					break;
				}
			}
		}
		configureList = tmpList;
		saveConfigure();
	}
	public void updateOneAppConfigure(List<TaskConfigureBean> beanList){
		for(TaskConfigureBean tmpBean:configureList){
			for(TaskConfigureBean bean:beanList){
				if(tmpBean.getAppId() == bean.getAppId()){
					tmpBean = bean;
					break;
				}
			}
		}
		saveConfigure();
	}
	public List<TaskConfigureBean> getAllAppConfigure(){
		return configureList;
	}
	// 写文件
	private void saveConfigure(){
		try{
			String jsonContent = JSON.toJSONString(configureList);
			FileUtil.writeAsString(mFile, jsonContent);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
