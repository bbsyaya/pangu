package org.turing.pangu.zzztest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.turing.pangu.bean.TaskConfigureBean;
import org.turing.pangu.engine.RemainEngine;
import org.turing.pangu.engine.TaskEngine;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.RemainData;
import org.turing.pangu.model.RemainVpn;
import org.turing.pangu.model.User;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.service.PlatformService;
import org.turing.pangu.service.RemainDataService;
import org.turing.pangu.service.RemainVpnService;
import org.turing.pangu.service.TaskService;
import org.turing.pangu.service.UserService;
import org.turing.pangu.utils.DateUtils;
import org.turing.pangu.utils.RandomUtils;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })
public class TestData {
	
	@Resource(name="appServiceImpl")
	private AppService appService;
	
	@Resource(name="platformServiceImpl")
	private PlatformService platformService;
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@Resource(name="remainVpnServiceImpl")
	private RemainVpnService remainVpnService;
	
	@Resource(name="remainDataServiceImpl")
	private RemainDataService remainDataService;
	
	@Resource(name="deviceServiceImpl")
	private DeviceService deviceService;
	
	@Resource(name="taskServiceImpl")
	private TaskService taskService;
	
	/*
	@Test
	public void testInsertPlatform(){
		Platform pf = new Platform();
		pf.setCreateDate(new Date());
		pf.setCreateDate(new Date());
		pf.setName("小神灯");
		pf.setIntroduce("介绍小神灯");
		pf.setUpdateDate(new Date());
		platformService.insert(pf);
	}
	
	@Test
	public void testInsertUser()
	{
		User user = new User();
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		user.setIp("127.0.0.1");
		user.setPhone("15817321796");
		user.setName("turing");
		user.setPassword("123123");
		userService.insert(user);
		
	}
	@Test
	public void testInsertApp(){
		
		App app = new App();
		app.setCreateDate(new Date());
		app.setPlatformId(1L);
		app.setUserId(1L);
		app.setUpdateDate(new Date());
		
		app.setApkPath("wzq.apk");
		app.setName("天天五子棋");
		app.setPackageName("org.kyf.xsd.wzq");
		app.setToken("3A667ACDE49623D32A39A316521F4");
		appService.insert(app);
		
		app.setApkPath("wzry.apk");
		app.setName("亡者荣耀");
		app.setPackageName("org.kyf.xsd.wzry");
		app.setToken("BD395BF96128FC6339F4E3155C18F");
		appService.insert(app);
		
		app.setApkPath("wzt.apk");
		app.setName("女王武则天");
		app.setPackageName("org.kyf.xsd.wzt");
		app.setToken("867BD3F1FE69A796BC523464497DA");
		appService.insert(app);
		
		app.setApkPath("test.apk");
		app.setName("测试");
		app.setPackageName("ni.wo.test");
		app.setToken("90OKIJUHHHUUJ796BC523464497DA");
		appService.insert(app);
	}
	 
	@Test
	public void testInsertVpn(){
		RemainVpn vpn = new RemainVpn();
		vpn.setCreateDate(new Date());
		vpn.setUpdateDate(new Date());
		vpn.setName("山东VPN");
		vpn.setIpList("101.105.54.92|220.80.21.78|123.43.67.129|200.30.201.178|23.143.167.29|20.180.213.8|13.143.207.168");
		remainVpnService.insert(vpn);
		
		vpn.setName("广东VPN");
		vpn.setIpList("10.205.154.192|120.180.221.178|230.63.79.29|140.130.101.78|123.43.67.129|207.18.138.82|103.143.107.68");
		remainVpnService.insert(vpn);
	}*/
	/*
	@Test
	public void testInsertDevice(){
		Device dev = new Device();
		dev.setCreateDate(new Date());
		dev.setUpdateDate(new Date());
		
		for(int index = 0; index < 1000; index++){
			dev.setImei(RandomUtils.getRandom(16));
			dev.setIsRemainIp(1);
			dev.setDeviceType(0);
			dev.setIsActived(1);
			String ipAddress = "";
			{
				Integer ip0 = (int)(Math.random() * 255);
				Integer ip1 = (int)(Math.random() * 255);
				Integer ip2 = (int)(Math.random() * 255);
				Integer ip3 = (int)(Math.random() * 255);
				ipAddress =  ip0.toString()+":" +ip1.toString()+":" +ip2.toString()+":" +ip3.toString();
			}
			dev.setIp(ipAddress);
			int random = (int)(Math.random() * 100);
			int mod = random%4 + 1;
			dev.setAppId((long) mod);
			deviceService.insert(dev);
			dev.setCreateDate(new Date());
			dev.setUpdateDate(new Date());
		}
	}
	*/

	public void testUpdateDevice(){
		List<Device> list = deviceService.selectAll();
		List<RemainVpn> vpnList =  remainVpnService.selectAll();
		String ip = "";
		for(Device dev :list){
			dev.setIsWhiteIp(0);
			dev.setIsRemain(0);
			dev.setIsActived(0);
			int random = (int)(Math.random()*100);
			if(random % 8 == 0){
				dev.setIsActived(1);
			}
			if( random % 5 == 0){
				int tmp = (int)(Math.random()*100);
				for(RemainVpn vpn : vpnList){
					if(tmp % 2 == 0){
						String[] ipList = vpn.getIpList().split("\\|");
						int index = (int)(Math.random()*ipList.length);
						ip = ipList[index];
						dev.setIp(ip);
						break;
					}else{
						tmp++;
					}
				}
				dev.setIsWhiteIp(1);
				if(random%3 == 0){
					dev.setIsActived(1);
					dev.setIsRemain(1);
				}
				Date yesterdayMorning = DateUtils.getYesterdayMorning();
				Date yesterdayNight = DateUtils.getYesterdayNight();
				dev.setCreateDate(yesterdayMorning);
				dev.setUpdateDate(yesterdayNight);
				deviceService.update(dev);
			}
		}
	}
	
	@Test
	public void remain(){
		TaskEngine.getInstance().setService(platformService, appService, deviceService,taskService);
		//RemainEngine.getInstance().generateRemainFile();
		//testUpdateDevice();
		List<TaskConfigureBean> list = new ArrayList<TaskConfigureBean>();
		for(int i =0 ; i < 5; i++){
			TaskConfigureBean bean = new TaskConfigureBean();
			bean.setAppId((long)i);
			bean.setIncrementMoney(100);
			bean.setIncrementWaterAmy(100);
			bean.setStockMoney(100);
			bean.setStockWaterAmy(100);
			list.add(bean);
		}
		String json = JSON.toJSONString(list);
		System.out.print(json);

	}

}
