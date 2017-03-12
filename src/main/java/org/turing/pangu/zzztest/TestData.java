package org.turing.pangu.zzztest;

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
import org.turing.pangu.phone.ChangeDeviceInfo;
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
	/*
	 * @Test public void testInsertPlatform(){ Platform pf = new Platform();
	 * pf.setCreateDate(new Date()); pf.setCreateDate(new Date());
	 * pf.setName("小神灯"); pf.setIntroduce("介绍小神灯"); pf.setUpdateDate(new
	 * Date()); platformService.insert(pf); }
	 * 
	 * @Test public void testInsertUser() { User user = new User();
	 * user.setCreateDate(new Date()); user.setUpdateDate(new Date());
	 * user.setIp("127.0.0.1"); user.setPhone("15817321796");
	 * user.setName("turing"); user.setPassword("123123");
	 * userService.insert(user);
	 * 
	 * }
	 * 
	 * @Test public void testInsertApp(){
	 * 
	 * App app = new App(); app.setCreateDate(new Date());
	 * app.setPlatformId(1L); app.setUserId(1L); app.setUpdateDate(new Date());
	 * 
	 * app.setApkPath("wzq.apk"); app.setName("天天五子棋");
	 * app.setPackageName("org.kyf.xsd.wzq");
	 * app.setToken("3A667ACDE49623D32A39A316521F4"); appService.insert(app);
	 * 
	 * app.setApkPath("wzry.apk"); app.setName("亡者荣耀");
	 * app.setPackageName("org.kyf.xsd.wzry");
	 * app.setToken("BD395BF96128FC6339F4E3155C18F"); appService.insert(app);
	 * 
	 * app.setApkPath("wzt.apk"); app.setName("女王武则天");
	 * app.setPackageName("org.kyf.xsd.wzt");
	 * app.setToken("867BD3F1FE69A796BC523464497DA"); appService.insert(app);
	 * 
	 * app.setApkPath("test.apk"); app.setName("测试");
	 * app.setPackageName("ni.wo.test");
	 * app.setToken("90OKIJUHHHUUJ796BC523464497DA"); appService.insert(app); }
	 * 
	 * @Test public void testInsertVpn(){ RemainVpn vpn = new RemainVpn();
	 * vpn.setCreateDate(new Date()); vpn.setUpdateDate(new Date());
	 * vpn.setName("山东VPN"); vpn.setIpList(
	 * "101.105.54.92|220.80.21.78|123.43.67.129|200.30.201.178|23.143.167.29|20.180.213.8|13.143.207.168"
	 * ); remainVpnService.insert(vpn);
	 * 
	 * vpn.setName("广东VPN"); vpn.setIpList(
	 * "10.205.154.192|120.180.221.178|230.63.79.29|140.130.101.78|123.43.67.129|207.18.138.82|103.143.107.68"
	 * ); remainVpnService.insert(vpn); }
	 */
	/*
	 * @Test public void testInsertDevice(){ Device dev = new Device();
	 * dev.setCreateDate(new Date()); dev.setUpdateDate(new Date());
	 * 
	 * for(int index = 0; index < 1000; index++){
	 * dev.setImei(RandomUtils.getRandom(16)); dev.setIsRemainIp(1);
	 * dev.setDeviceType(0); dev.setIsActived(1); String ipAddress = ""; {
	 * Integer ip0 = (int)(Math.random() * 255); Integer ip1 =
	 * (int)(Math.random() * 255); Integer ip2 = (int)(Math.random() * 255);
	 * Integer ip3 = (int)(Math.random() * 255); ipAddress = ip0.toString()+":"
	 * +ip1.toString()+":" +ip2.toString()+":" +ip3.toString(); }
	 * dev.setIp(ipAddress); int random = (int)(Math.random() * 100); int mod =
	 * random%4 + 1; dev.setAppId((long) mod); deviceService.insert(dev);
	 * dev.setCreateDate(new Date()); dev.setUpdateDate(new Date()); } }
	 */
	
	private String getStr(int index,String ip){
		List<VpnConnectInfo> lista = new ArrayList<VpnConnectInfo>();

		if( index == 1){
			VpnConnectInfo a = new VpnConnectInfo();
			VpnConnectInfo b = new VpnConnectInfo();
			VpnConnectInfo c = new VpnConnectInfo();
			VpnConnectInfo d = new VpnConnectInfo();
			
			a.setVpnName("Turing");
			a.setTunnelType("Automatic");
			a.setIp(ip);
			a.setAuthenticationMethod("{Chap, MsChapv2}");
			a.setEncryptionLevel("Optional");
			a.setUserName("radiusyun04\\airobot");
			a.setPassword("qwerfdsa");
			lista.add(a);
			
			b.setVpnName("Turing");
			b.setTunnelType("Automatic");
			b.setIp(ip);
			b.setAuthenticationMethod("{Chap, MsChapv2}");
			b.setEncryptionLevel("Optional");
			b.setUserName("airobot");
			b.setPassword("qwerfdsa");
			lista.add(b);
	
			c.setVpnName("Turing");
			c.setTunnelType("Automatic");
			c.setIp(ip);
			c.setAuthenticationMethod("{Chap, MsChapv2}");
			c.setEncryptionLevel("Optional");
			c.setUserName("chuanqi520");
			c.setPassword("27933460ac9a");
			lista.add(c);
	
			d.setVpnName("Turing");
			d.setTunnelType("Automatic");
			d.setIp(ip);
			d.setAuthenticationMethod("{Chap, MsChapv2}");
			d.setEncryptionLevel("Optional");
			d.setUserName("moshouzx");
			d.setPassword("b7d6a5358a6b");
			lista.add(d);
		}else{
			VpnConnectInfo a = new VpnConnectInfo();
			a.setVpnName("Turing");
			a.setTunnelType("Automatic");
			a.setIp(ip);
			a.setAuthenticationMethod("{Chap, MsChapv2, Pap}");
			a.setEncryptionLevel("L2tp");
			a.setUserName("airobot");
			a.setPassword("qwerfdsa");
			lista.add(a);
		}
		
		String json = JSON.toJSONString(lista);
		// L2tp Pptp	
		return json;
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
	@Test
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
	
	public void testUpdateDevice() {
		//EngineMng.getInstance().initEngine(getAllServiecInstance());		
		List<PhoneBrand> list = new ArrayList<PhoneBrand>();
		list = phoneBrandService.selectAll();
		List<HeightWidth> hwList = new ArrayList<HeightWidth>();
		int flag = 0;
		for(PhoneBrand brand:list){
			flag = 0;
			HeightWidth hwidth = new HeightWidth();
			hwidth.setHeight(brand.getHeight());
			hwidth.setWidth(brand.getWidth());
			for(HeightWidth hw :hwList){
				if(hwidth.getHeight() == hw.getHeight() && hwidth.getWidth() == hw.getWidth()){
					flag = 1;
					break;
				}
			}
			if(flag == 0){
				hwList.add(hwidth);
			}
		}
		for(HeightWidth hw :hwList){
			Resolution res = new Resolution();
			res.setHeight(hw.getHeight());
			res.setWidth(hw.getWidth());
			res.setPlatformId(1L);
			res.setIsSupport(1);
			resolutionService.insert(res);
		}
		/*
		for(PhoneBrand brand:list){
			int random = RandomUtils.getRandom(100);
			if(random % 20 == 0){
				brand.setImeiHead("35");
			}else{
				brand.setImeiHead("46");
			}
			brand.setChinaMobile(1);
			brand.setChinaTelecom(1);
			brand.setChinaUnicom(1);
			brand.setCreateDate(new Date());
			brand.setUpdateDate(new Date());
			phoneBrandService.update(brand);
		}
		*/
		
		//for(Device dev :list){
			//IpTrunkEngine.getInstance().saveIpInfoToDb(dev.getIp());
		//}119.90.141.77
		//List<Device> cityIpList = DeviceEngine.getInstance().selectLastWeekExcludeTodayByIp("119.90.141.77");
		//List<StockTask> stockList = IpTrunkEngine.getInstance().getStockInfoList("119.90.141.77");
		//List<Device> list = deviceService.selectAll();
		//for(Device dev :list){
			//IpTrunkEngine.getInstance().saveIpInfoToDb(dev.getIp());
			//i++;
		//}
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
