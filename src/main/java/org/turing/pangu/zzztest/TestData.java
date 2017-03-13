package org.turing.pangu.zzztest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.turing.pangu.bean.HeightWidth;
import org.turing.pangu.bean.TaskConfigureBean;
import org.turing.pangu.bean.VpnConnectInfo;
import org.turing.pangu.engine.AppEngine;
import org.turing.pangu.engine.EngineMng;
import org.turing.pangu.engine.PhoneBrandEngine;
import org.turing.pangu.engine.VpnEngine;
import org.turing.pangu.engine.WifiMngEngine;
import org.turing.pangu.iptrunk.BaiduLocation;
import org.turing.pangu.iptrunk.LocationMng;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Computer;
import org.turing.pangu.model.DynamicVpn;
import org.turing.pangu.model.PhoneBrand;
import org.turing.pangu.model.Resolution;
import org.turing.pangu.phone.BrandBuildInfo;
import org.turing.pangu.phone.ChangeDeviceInfo;
import org.turing.pangu.phone.GenerateData;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.ComputerService;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.service.DynamicVpnService;
import org.turing.pangu.service.IpTrunkService;
import org.turing.pangu.service.PhoneBrandService;
import org.turing.pangu.service.PhoneNumberService;
import org.turing.pangu.service.PlatformService;
import org.turing.pangu.service.RemainVpnService;
import org.turing.pangu.service.ResolutionService;
import org.turing.pangu.service.SimulatorService;
import org.turing.pangu.service.TaskService;
import org.turing.pangu.service.UserService;
import org.turing.pangu.service.VpnGroupService;
import org.turing.pangu.utils.RandomUtils;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml" })
public class TestData {

	@Resource(name="platformServiceImpl")
	private PlatformService platformService;
	
	@Resource(name="appServiceImpl")
	private AppService appService;
	
	@Resource(name="remainVpnServiceImpl")
	private RemainVpnService remainVpnService;
	
	@Resource(name="dynamicVpnServiceImpl")
	private DynamicVpnService dynamicVpnService;
	
	@Resource(name="deviceServiceImpl")
	private DeviceService deviceService;
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@Resource(name="taskServiceImpl")
	private TaskService taskService;
	
	@Resource(name="vpnGroupServiceImpl")
	private VpnGroupService vpnGroupService;
	
	@Resource(name = "ipTrunkServiceImpl")
	private IpTrunkService ipTrunkService;
	
	@Resource(name = "phoneNumberServiceImpl")
	private PhoneNumberService phoneNumberService;
	
	@Resource(name = "phoneBrandServiceImpl")
	private PhoneBrandService phoneBrandService;

	@Resource(name = "computerServiceImpl")
	private ComputerService computerService;
	
	@Resource(name = "simulatorServiceImpl")
	private SimulatorService simulatorService;
	
	@Resource(name = "resolutionServiceImpl")
	private ResolutionService resolutionService;

	
	private String getStr(int index,String ip){
		return null;
	}
	private List<BaseService> getAllServiecInstance(){
		List<BaseService> list = new ArrayList<BaseService>();
		list.add(userService);
		list.add(dynamicVpnService);
		list.add(vpnGroupService);
		list.add(remainVpnService);
		list.add(platformService);
		list.add(appService);
		list.add(deviceService);
		list.add(taskService);
		list.add(ipTrunkService);
		list.add(phoneNumberService);
		list.add(phoneBrandService);
		list.add(computerService);
		list.add(simulatorService);
		list.add(resolutionService);
		return list;
	}
	
	public void testWifi(){
		EngineMng.getInstance().initEngine(getAllServiecInstance());
		LocationMng mng = new LocationMng();
		BaiduLocation location = mng.getLocation("116.5.244.222");
		App app = AppEngine.getInstance().getAppInfo(1L);
		ChangeDeviceInfo info = PhoneBrandEngine.getInstance().getNewDeviceInfo(location, app);
		System.out.print("\n"+JSON.toJSONString(info));
		/*
		WifiMngEngine.getInstance().init();
		for(int index = 0;index < 100;index++){
			System.out.print("\n" + WifiMngEngine.getInstance().getWifiName());
		}
		*/
	}
	@Test
	public void testUpdateDevice() {
		//EngineMng.getInstance().initEngine(getAllServiecInstance());		
		List<PhoneBrand> list = new ArrayList<PhoneBrand>();
		list = phoneBrandService.selectAll();
		/*
		PRODUCT: soju,  
		CPU_ABI: armeabi-v7a,  
		TAGS: release-keys,    
		VERSION_CODES.BASE: 1,  
		MODEL: Nexus S,  
		SDK: 10,  
		VERSION.RELEASE: 2.3.6,  
		DEVICE: crespo,  
		DISPLAY: GRK39F,  
		BRAND: google,  
		BOARD: herring,    
		FINGERPRINT: google/soju/crespo:2.3.6/GRK39F/189904:user/release-keys,  
		ID: GRK39F,  
		MANUFACTURER: samsung,  
		USER: android-build  
		*/
		BrandBuildInfo buildInfo = new BrandBuildInfo();
		String[] times = { "2015-11-04","2014-11-03","2015-01-24","2016-02-19","2015-08-07" };
		 
		//Flyme OS 4.1.3.5A，MIUI，华为EMUI 
		// Huawei/JAZZ/hi3630:4.4.2/KOT49H/eng.jenkins.20141022.090132:user/test-keys
		// cpu hi3630,Kirin 920
		for(PhoneBrand brand:list){
			buildInfo.setBrand(brand.getBrand());
			buildInfo.setModel(brand.getModel());
			buildInfo.setManufacture(brand.getModel());
			buildInfo.setType("user");
			buildInfo.setBootloader("unknown");
			buildInfo.setTags("release-keys");
			buildInfo.setId(GenerateData.getInstance().generateBuildId());
			buildInfo.setSerial(RandomUtils.getRandomNumbers(3)+RandomUtils.getRandomCapitalLetters(9));
			
			buildInfo.setUser((RandomUtils.getRandom(10)%3==0)?"root":"user");
			
			buildInfo.setCpu_abi(GenerateData.getInstance().generateCpu());
			buildInfo.setSdk(GenerateData.getInstance().getSdk());
			buildInfo.setDevice(buildInfo.getDisplay());
			buildInfo.setHardWare(buildInfo.getCpu_abi());
			String[] products = {};
			String product = "";
			{
				products = buildInfo.getModel().split(" ");
				if(products.length == 1){
					products = buildInfo.getModel().split("-");
				}
				product = products[products.length-1];
			}
			buildInfo.setProduct(product);
			buildInfo.setDisplay(product);
			buildInfo.setBoard(product);
			buildInfo.setRadioVersion(buildInfo.getModel()+"."+RandomUtils.getRandomNumbersAndCapitalLetters(RandomUtils.getRandom(6, 8)));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			Date date = null;
			try {
				date = sdf.parse(times[RandomUtils.getRandom(0,times.length)]);
				buildInfo.setTime(date.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buildInfo.setIncrement(GenerateData.getInstance().generateIncremental(buildInfo.getModel(), buildInfo.getDisplay(), buildInfo.getTime()));
			buildInfo.setFingerPrint(GenerateData.getInstance().generateFingerprint(buildInfo.getBrand(), buildInfo.getModel(), buildInfo.getIncrement(), buildInfo.getId()));
			if(!brand.getBrand().equalsIgnoreCase("oppo") && !brand.getBrand().equalsIgnoreCase("vivo")
					&&!brand.getBrand().equalsIgnoreCase("huawei")){
				brand.setConfigure(JSON.toJSONString(buildInfo));
				phoneBrandService.update(brand);
			}
		}
	}

	public void remain() {
		EngineMng.getInstance().initEngine(getAllServiecInstance());	
		// RemainEngine.getInstance().generateRemainFile();
		// testUpdateDevice();
		List<TaskConfigureBean> list = new ArrayList<TaskConfigureBean>();
		for (int i = 0; i < 5; i++) {
			TaskConfigureBean bean = new TaskConfigureBean();
			bean.setAppId((long) i);
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
