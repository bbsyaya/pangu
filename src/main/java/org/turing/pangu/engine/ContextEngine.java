package org.turing.pangu.engine;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.turing.pangu.utils.HttpUtils;

public class ContextEngine implements ServletContextListener {
	private static final Logger logger = Logger.getLogger(DeviceEngine.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("ContextEngine -->contextDestroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("ContextEngine -->contextInitialized");
		Timer timer = new Timer();// 实例化Timer类
		timer.schedule(new TimerTask() {
			public void run() {
				initPangu();
				this.cancel();
			}
		}, 5000);// 五百毫秒
	}

	private void initPangu() {
		String serverURL = "http://pangu.u-app.cn/pc/moneyIFuckYou.pangu";
		//String serverURL ="http://localhost:8080/pc/moneyIFuckYou.pangu";
		String result = HttpUtils.doGet(serverURL, HttpUtils.UTF8);// 建立http// get联机
		System.out.println("initPangu" + result);
	}
}
