package org.turing.pangu.controller.pc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.turing.pangu.bean.DynamicVpnExtend;
import org.turing.pangu.bean.PlatformApp;
import org.turing.pangu.controller.common.BaseController;
import org.turing.pangu.controller.common.PGResponse;
import org.turing.pangu.controller.pc.request.GetAppListReq;
import org.turing.pangu.controller.pc.request.GetDynamicVpnListReq;
import org.turing.pangu.controller.pc.request.GetRemainVpnListReq;
import org.turing.pangu.controller.pc.request.GetTaskListReq;
import org.turing.pangu.controller.pc.request.LoginReq;
import org.turing.pangu.controller.pc.request.SaveBlackIpListReq;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.pc.request.VpnOperUpdateReq;
import org.turing.pangu.controller.pc.request.VpnSwitchFinishReq;
import org.turing.pangu.controller.pc.response.VpnLoginRsp;
import org.turing.pangu.controller.pc.response.VpnOperUpdateRsp;
import org.turing.pangu.controller.phone.request.GetBlackIpListReq;
import org.turing.pangu.engine.AppEngine;
import org.turing.pangu.engine.ComputerEngine;
import org.turing.pangu.engine.DeviceEngine;
import org.turing.pangu.engine.EngineMng;
import org.turing.pangu.engine.PlatformEngine;
import org.turing.pangu.engine.TaskConfigureEngine;
import org.turing.pangu.engine.TaskEngine;
import org.turing.pangu.engine.TimeMng;
import org.turing.pangu.model.Computer;
import org.turing.pangu.model.DeviceFromNet;
import org.turing.pangu.model.DynamicVpn;
import org.turing.pangu.model.Platform;
import org.turing.pangu.model.RemainVpn;
import org.turing.pangu.model.Task;
import org.turing.pangu.model.User;
import org.turing.pangu.phone.ChangeDeviceInfo;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.CmnPayUserService;
import org.turing.pangu.service.ComputerService;
import org.turing.pangu.service.DeviceFromNetService;
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
import org.turing.pangu.utils.Const;
import org.turing.pangu.utils.TraceUtils;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author turing
 * @des 处理手机端过来的所有关于用户信息相关请求（如：注册，登录，修改密码）
 *
 */
@Controller("pcMngController")
@RequestMapping("/pc")
public class PCMngController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PCMngController.class);

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
	
	@Resource(name = "deviceFromNetServiceImpl")
	private DeviceFromNetService deviceFromNetService;
	
	@Resource(name = "cmnPayUserServiceImpl")
	private CmnPayUserService cmnPayUserService;
	
	
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
		list.add(deviceFromNetService);
		list.add(cmnPayUserService);
		return list;
	}
	
	@RequestMapping(value = "/moneyIFuckYou", method = RequestMethod.GET)
	public @ResponseBody PGResponse<String> moneyIFuckYou() {
		logger.info("moneyIFuckYou---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		EngineMng.getInstance().initEngine(getAllServiecInstance());
		TaskConfigureEngine.getInstance().init();
		rsp.setAllData(Const.common_ok, "common_ok", null);
		logger.info("moneyIFuckYou---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	
	@RequestMapping(value = "/createTodayTask", method = RequestMethod.GET)
	public @ResponseBody PGResponse<String> createTodayTask() {
		logger.info("createTodayTask---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		TaskEngine.getInstance().createTodayTask();
		rsp.setAllData(Const.common_ok, "common_ok", null);
		logger.info("createTodayTask---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	@RequestMapping(value = "/remoteIp", method = RequestMethod.GET)
	public @ResponseBody PGResponse<String> remoteIp(HttpServletRequest request) {
		logger.info("remoteIp---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		rsp.setAllData(Const.common_ok, "common_ok", remoteIp);
		return rsp;
	}
	@RequestMapping(value = "/proxyIp", method = RequestMethod.GET)
	public @ResponseBody PGResponse<String> proxyIp(HttpServletRequest request) {
		logger.info("proxyIp---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		String realIp = TaskEngine.getInstance().getRealIp(request);
		rsp.setAllData(Const.common_ok, "common_ok", realIp);
		return rsp;
	}
	@RequestMapping(value = "/reportExtDeviceInfo", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String reportExtDeviceInfo(@RequestBody ChangeDeviceInfo req,HttpServletRequest request) {
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		if(!req.getImei().equals("")){
			DeviceFromNet test = new DeviceFromNet();
			test.setImei(req.getImei());
			List<DeviceFromNet> list = deviceFromNetService.selectList(test); // 去重
			if(null != list && list.size() > 0 )
				return "OKle";
		}
		
		req.setIp(remoteIp);
		DeviceFromNet model = DeviceEngine.getInstance().saveReportFromExt(req);
		deviceFromNetService.insert(model);
		return "OK";
	}
	// vpn登录请求
	@RequestMapping(value = "/vpnLogin", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<VpnLoginRsp> vpnLogin(@RequestBody VpnLoginReq req,HttpServletRequest request) {
		Date start = new Date();
		TraceUtils.getTraceInfo();
		logger.info("req:" + JSON.toJSONString(req).toString());
		logger.info("ip:" + TaskEngine.getInstance().getRemoteIp(request));
		PGResponse<VpnLoginRsp> rsp = new PGResponse<VpnLoginRsp>();
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		String realIp = TaskEngine.getInstance().getRealIp(request);
		String token = TaskEngine.getInstance().vpnLogin(req,remoteIp,realIp);
		VpnLoginRsp dataRsp = new VpnLoginRsp();
		dataRsp.setRemoteIp(remoteIp);
		dataRsp.setRealIp(realIp);
		dataRsp.setLoopTime(TimeMng.SPAN_TIME);
		if(null == token ){
			dataRsp.setToken("");
			rsp.setAllData(Const.common_error, "common_error", dataRsp);
		}else{
			dataRsp.setToken(token);
			rsp.setAllData(Const.common_ok, "common_ok", dataRsp);
		}
		logger.info("rsp:" + JSON.toJSONString(rsp).toString());
		TimeMng.spellTime(start);
		return rsp;
	}
	// vpn操作请求
	@RequestMapping(value = "/vpnOperUpdate", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<VpnOperUpdateRsp> vpnOperUpdate(@RequestBody VpnOperUpdateReq req,HttpServletRequest request) {
		Date start = new Date();
		TraceUtils.getTraceInfo();
		logger.info("req:" + JSON.toJSONString(req).toString());
		logger.info("ip:" + TaskEngine.getInstance().getRemoteIp(request));
		PGResponse<VpnOperUpdateRsp> rsp = new PGResponse<VpnOperUpdateRsp>();
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		String realIp = TaskEngine.getInstance().getRealIp(request);
		VpnOperUpdateRsp dataRsp =  TaskEngine.getInstance().vpnIsNeedSwitch(req.getToken(),remoteIp,realIp);
		dataRsp.setRemoteIp(remoteIp);
		dataRsp.setRealIp(realIp);
		dataRsp.setLoopTime(TimeMng.SPAN_TIME);
		rsp.setAllData(Const.common_ok, "common_ok", dataRsp);
		logger.info("rsp:" + JSON.toJSONString(rsp).toString());
		TimeMng.spellTime(start);
		return rsp;
	}
	// vpn切换完成
	@RequestMapping(value = "/vpnSwitchFinish", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> vpnSwitchFinish(@RequestBody VpnSwitchFinishReq req,HttpServletRequest request) {
		Date start = new Date();
		TraceUtils.getTraceInfo();
		logger.info("req:" + JSON.toJSONString(req).toString());
		logger.info("ip:" + TaskEngine.getInstance().getRemoteIp(request));
		PGResponse<String> rsp = new PGResponse<String>();
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		String realIp = TaskEngine.getInstance().getRealIp(request);
		if(false == TaskEngine.getInstance().switchVpnFinish(req.getToken(), remoteIp, realIp)){
			rsp.setAllData(Const.common_error, "common_error", "");
		}else{
			rsp.setAllData(Const.common_ok, "common_ok", "");
		}
		
		logger.info("rsp:" + JSON.toJSONString(rsp).toString());
		TimeMng.spellTime(start);
		return rsp;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> login(@RequestBody LoginReq req) {
		Date start = new Date();
		TraceUtils.getTraceInfo();
		logger.info("login---" + req.getName() + "--" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		User user = new User();
		user.setName(req.getName());
		user.setPassword(req.getPassword());
		List<User> list = userService.selectList(user);
		if(null == list || list.size() == 0 )
		{
			rsp.setAllData(Const.common_error, "common_error", "");
		}else
		{
			rsp.setAllData(Const.common_ok, "common_ok", "");
		}
		logger.info("login---" + JSON.toJSONString(rsp).toString());
		TimeMng.spellTime(start);
		return rsp;
	}
	/**
	 * 获取VPN列表接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDynamicVpnList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<DynamicVpnExtend>> getDynamicVpnList(@RequestBody GetDynamicVpnListReq req) {
		TraceUtils.getTraceInfo();
		PGResponse<List<DynamicVpnExtend>> rsp = new PGResponse<List<DynamicVpnExtend>>();
		List<DynamicVpnExtend> extList = new ArrayList<DynamicVpnExtend>();
		DynamicVpn vpn = new DynamicVpn();
		vpn.setIsValid(1);
		List<DynamicVpn> list = dynamicVpnService.selectList(vpn);
		for(DynamicVpn tmpVpn :list){
			DynamicVpnExtend ext = new DynamicVpnExtend();
			Computer cpt = ComputerEngine.getInstance().getComputerInfoByVpnId(tmpVpn.getId());
			ext.setVpn(tmpVpn);
			ext.setComputer(cpt);
			extList.add(ext);
		}
		rsp.setAllData(Const.common_ok, "common_ok", extList);
		logger.info("getDynamicVpnList---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	
	/**
	 * 获取黑名单IP
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBlackIpList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> getBlackIpList(@RequestBody GetBlackIpListReq req) {
		TraceUtils.getTraceInfo();
		PGResponse<String> rsp = new PGResponse<String>();
		Platform platform = PlatformEngine.getInstance().getPlatformInfo(req.getPlatformId());
		if(null == platform){
			rsp.setAllData(Const.common_error, "common_error", null);
		}else{
			rsp.setAllData(Const.common_ok, "common_ok", platform.getBlackIp());
		}
		
		logger.info("getBlackIpList---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	/**
	 * 保存黑名单IP
	 * 
	 * @return
	 */
	@RequestMapping(value = "/saveBlackIpList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> saveBlackIpList(@RequestBody SaveBlackIpListReq req) {
		TraceUtils.getTraceInfo();
		PGResponse<String> rsp = new PGResponse<String>();
		Platform platform  = PlatformEngine.getInstance().getPlatformInfo(req.getPlatformId());
		if(platform != null && !req.getIpList().equals("")){
			platform.setBlackIp(req.getIpList());
			platform.setUpdateDate(new Date());
			
			platformService.update(platform);
		}
		rsp.setAllData(Const.common_ok, "common_ok", "");
		logger.info("saveBlackIpList---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	
	/**
	 * 获取固定IPVPN列表接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getStaticVpnList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<RemainVpn>> getStaticVpnList(@RequestBody GetRemainVpnListReq req) {
		TraceUtils.getTraceInfo();
		PGResponse<List<RemainVpn>> rsp = new PGResponse<List<RemainVpn>>();
		List<RemainVpn> list = remainVpnService.selectAll();
		rsp.setAllData(Const.common_ok, "common_ok", list);
		logger.info("getRemainVpnList---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	/**
	 * 获取app列表信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAppList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<PlatformApp>> getAppList(@RequestBody GetAppListReq req) {
		TraceUtils.getTraceInfo();
		PGResponse<List<PlatformApp>> rsp = new PGResponse<List<PlatformApp>>();
		List<PlatformApp> list = AppEngine.getInstance().getPlatformAppList();
		rsp.setAllData(Const.common_ok, "common_ok", list);
		logger.info("getAppList---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	
	/**
	 * 获取全部运营数据接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTaskDataList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<Task>> getTaskDataList(@RequestBody GetTaskListReq req) {
		TraceUtils.getTraceInfo();
		PGResponse<List<Task>> rsp = new PGResponse<List<Task>>();
		List<Task> list = TaskEngine.getInstance().getAllDBTaskByAppId(req.getAppId());
		rsp.setAllData(Const.common_ok, "common_ok", list);
		logger.info("getTaskDataList---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
}
