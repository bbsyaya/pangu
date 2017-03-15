package org.turing.pangu.engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.util.FileUtil;
import org.turing.pangu.model.Resolution;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.utils.RandomUtils;

public class WifiMngEngine implements EngineListen{
	private static final Logger logger = Logger.getLogger(WifiMngEngine.class);
	private static WifiMngEngine mInstance = new WifiMngEngine();
	public List<Resolution> resolutionList = new ArrayList<Resolution>();
	public String[] wifiPrefix = {};
	public String[] wifiPostfix = {};
	public String[] wifiConnector = {};
	public static WifiMngEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new WifiMngEngine();
		return mInstance;
	}
	private String getRandomString()
	{
		int len = RandomUtils.getRandom(2, 6);
		String ret = "";
		Long time = new Date().getTime();
		switch((int)(time % 4)){
			case 0:
				ret = RandomUtils.getRandomNumbers(len);
				break;
			case 1:
				ret = RandomUtils.getRandomLetters(len);
				break;
			case 2:
				ret = RandomUtils.getRandomNumbersAndLetters(len);
				break;
			case 3:
				ret = RandomUtils.getRandomCapitalLetters(len);
				break;
		}
		return ret;
	}
	public String getWifiName(){
		String name = "";
		int random = RandomUtils.getRandom(0,100);
		if(random > 0 && random < 70){ // 70% 是两位
			name = "" + wifiPrefix[RandomUtils.getRandom(0,wifiPrefix.length)] + wifiPostfix[RandomUtils.getRandom(0,wifiPostfix.length)];
		}else if(random > 70 && random > 90){// 20% 是三位
			name = "" + wifiPrefix[RandomUtils.getRandom(0,wifiPrefix.length)] + wifiPostfix[RandomUtils.getRandom(0,wifiPostfix.length)]+ wifiConnector[RandomUtils.getRandom(0,wifiConnector.length)]
					+ getRandomString();
		}else{// 10% 是1位
			name = "" + wifiPostfix[RandomUtils.getRandom(0,wifiPostfix.length)];
		}
		return name;
	}
	
	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		String wifiPrefixPath =Thread.currentThread().getContextClassLoader().getResource("").getPath()+ "txt/wifi_prefix.txt";
		String wifiPostfixPath =Thread.currentThread().getContextClassLoader().getResource("").getPath()+ "txt/wifi_postfix.txt";
		String wifiConnectorPath =Thread.currentThread().getContextClassLoader().getResource("").getPath()+ "txt/wifi_connector.txt";
		
		File file = null;
		String contentStr = "";
		try {
			file = new File(wifiPrefixPath);
			contentStr = FileUtil.readAsString(file);
			wifiPrefix = contentStr.split("\r\n");
			
			file = new File(wifiPostfixPath);
			contentStr = FileUtil.readAsString(file);
			wifiPostfix = contentStr.split("\r\n");
			
			file = new File(wifiConnectorPath);
			contentStr = FileUtil.readAsString(file);
			wifiConnector = contentStr.split("\r\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upDate() {
		// TODO Auto-generated method stub
		
	}
}
