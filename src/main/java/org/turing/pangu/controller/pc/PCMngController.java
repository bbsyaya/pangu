package org.turing.pangu.controller.pc;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.turing.pangu.controller.common.BaseController;
import org.turing.pangu.controller.common.PGResponse;
import org.turing.pangu.controller.pc.request.CurdVpnReq;
import org.turing.pangu.controller.pc.request.GetAppListReq;
import org.turing.pangu.controller.pc.request.GetDynamicVpnListReq;
import org.turing.pangu.controller.pc.request.GetRemainIpListReq;
import org.turing.pangu.controller.pc.request.GetRemainDataListReq;
import org.turing.pangu.controller.pc.request.GetRemainVpnListReq;
import org.turing.pangu.controller.pc.request.GetVpnListReq;
import org.turing.pangu.controller.pc.request.VpnOperUpdateReq;
import org.turing.pangu.controller.pc.request.LoginReq;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.pc.request.VpnSwitchFinishReq;
import org.turing.pangu.engine.RemainEngine;
import org.turing.pangu.model.App;
import org.turing.pangu.model.DynamicVpn;
import org.turing.pangu.model.RemainData;
import org.turing.pangu.model.RemainVpn;
import org.turing.pangu.model.User;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.service.DynamicVpnService;
import org.turing.pangu.service.PlatformService;
import org.turing.pangu.service.RemainDataService;
import org.turing.pangu.service.RemainVpnService;
import org.turing.pangu.service.UserService;
import org.turing.pangu.utils.Const;
import org.turing.pangu.utils.DateUtils;

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
	
	@Resource(name="remainDataServiceImpl")
	private RemainDataService remainDataService;
	
	@Resource(name="deviceServiceImpl")
	private DeviceService deviceService;
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public @ResponseBody PGResponse<String> index() {
		logger.debug("index---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		RemainEngine.getInstance().setService(platformService, appService, deviceService, remainDataService);
		RemainEngine.getInstance().updateRemainData();
		rsp.setAllData(Const.common_ok, "common_ok", null);
		return rsp;
	}
	
	// vpn登录请求
	@RequestMapping(value = "/vpnLogin", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> vpnLogin(@RequestBody VpnLoginReq req) {
		PGResponse<String> rsp = new PGResponse<String>();
		return rsp;
	}
	// vpn操作请求
	@RequestMapping(value = "/vpnOperUpdate", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> vpnLogin(@RequestBody VpnOperUpdateReq req) {
		PGResponse<String> rsp = new PGResponse<String>();
		return rsp;
	}
	// vpn切换完成
	@RequestMapping(value = "/vpnSwitchFinish", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> vpnSwitchFinish(@RequestBody VpnSwitchFinishReq req) {
		PGResponse<String> rsp = new PGResponse<String>();
		return rsp;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> login(@RequestBody LoginReq req) {
		logger.debug("login---" + req.getName() + "--" + new Date());
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
		return rsp;
	}
	/**
	 * 获取VPN列表接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDynamicVpnList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<DynamicVpn>> getDynamicVpnList(@RequestBody GetDynamicVpnListReq req) {
		logger.debug("getDynamicVpnList---" + new Date());
		PGResponse<List<DynamicVpn>> rsp = new PGResponse<List<DynamicVpn>>();
		List<DynamicVpn> list = dynamicVpnService.selectAll();
		rsp.setAllData(Const.common_ok, "common_ok", list);
		return rsp;
	}
	
	/**
	 * 获取固定IPVPN列表接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTodayRemainIpList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> getTodayRemainIpList(@RequestBody GetRemainIpListReq req) {
		logger.debug("getTodayRemainIpList---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		String iplist = deviceService.getRemainIpList();
		rsp.setAllData(Const.common_ok, "common_ok", iplist);
		return rsp;
	}
	
	/**
	 * 获取固定IPVPN列表接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getFixedVpnList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<RemainVpn>> getFixedVpnList(@RequestBody GetRemainVpnListReq req) {
		logger.debug("getRemainVpnList---" + new Date());
		PGResponse<List<RemainVpn>> rsp = new PGResponse<List<RemainVpn>>();
		List<RemainVpn> list = remainVpnService.selectAll();
		rsp.setAllData(Const.common_ok, "common_ok", list);
		return rsp;
	}
	/**
	 * 获取运营数据接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAppList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<App>> getAppList(@RequestBody GetAppListReq req) {
		logger.debug("getAppList---" + req.getUserId() + "--" + new Date());
		PGResponse<List<App>> rsp = new PGResponse<List<App>>();
		App model = new App();
		model.setIsCanRun(1);
		List<App> list = appService.selectCanRunApps(model);
		rsp.setAllData(Const.common_ok, "common_ok", list);
		return rsp;
	}
	
	/**
	 * 获取游戏全部运营数据接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getRemainDataList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<RemainData>> getRemainList(@RequestBody GetRemainDataListReq req) {
		logger.debug("getRemainList---" + req.getAppId() + "--" + new Date());
		PGResponse<List<RemainData>> rsp = new PGResponse<List<RemainData>>();
		RemainData data = new RemainData();
		data.setAppId(req.getAppId());
		List<RemainData> list = remainDataService.selectList(data);
		rsp.setAllData(Const.common_ok, "common_ok", list);
		return rsp;
	}
	
	/**
	 * 获取留存文件地址
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getRemainFilePath", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<RemainData>> getRemainFilePath(@RequestBody GetRemainDataListReq req) {
		logger.debug("getRemainFilePath---" + req.getAppId() + "--" + new Date());
		PGResponse<List<RemainData>> rsp = new PGResponse<List<RemainData>>();
		RemainData data = new RemainData();
		//Date todayMorning = DateUtils.getTimesMorning();
		//Date todayNight = DateUtils.getTimesNight();
		data.setAppId(req.getAppId());
		data.setCreateDate(req.getStartDate());
		data.setUpdateDate(req.getEndDate());
		List<RemainData> list = remainDataService.getRemainData(data);
		rsp.setAllData(Const.common_ok, "common_ok", list);
		return rsp;
	}
	
	/**
	 * 获取今日实时数据情况
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getRemain", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<RemainData>> getTodayRemain(@RequestBody GetRemainDataListReq req) {
		logger.debug("getTodayRemain---" + req.getAppId() + "--" + new Date());
		PGResponse<List<RemainData>> rsp = new PGResponse<List<RemainData>>();
		RemainData data = new RemainData();
		Date todayMorning = DateUtils.getTimesMorning();
		Date todayNight = DateUtils.getTimesNight();
		data.setAppId(req.getAppId());
		data.setCreateDate(todayMorning);
		data.setUpdateDate(todayNight);
		List<RemainData> list = remainDataService.getRemainData(data);
		rsp.setAllData(Const.common_ok, "common_ok", list);
		return rsp;
	}
	
	/**
	 * 获取VPN列表接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/curdVpn", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> saveVpn(@RequestBody CurdVpnReq req) {
		logger.debug("saveVpn---" + req.getName() + "--" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		RemainVpn model = new RemainVpn();
		if( req.getType() == 1 ) //增
		{
			model.setIpList(req.getIpList());
			model.setName(req.getName());
			model.setCreateDate(new Date());
			model.setUpdateDate(new Date());
			remainVpnService.insert(model);
		}else if( req.getType() == 2 ){ //改
			model.setIpList(req.getIpList());
			model.setName(req.getName());
			model.setId(req.getId());
			model.setCreateDate(new Date());
			model.setUpdateDate(new Date());
			remainVpnService.update(model);
		}else{
			rsp.setAllData(Const.common_error, "common_error", "");
			return rsp;
		}
		rsp.setAllData(Const.common_ok, "common_ok", "");
		return rsp;
	}
}
