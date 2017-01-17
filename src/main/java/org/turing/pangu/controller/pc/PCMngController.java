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
import org.turing.pangu.controller.pc.request.GetRemainListReq;
import org.turing.pangu.controller.pc.request.GetVpnListReq;
import org.turing.pangu.controller.pc.request.LoginReq;
import org.turing.pangu.model.App;
import org.turing.pangu.model.RemainData;
import org.turing.pangu.model.RemainVpn;
import org.turing.pangu.model.User;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.DeviceService;
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
@Controller("pcUserController")
@RequestMapping("/pc")
public class PCMngController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PCMngController.class);

	@Resource(name="appServiceImpl")
	private AppService appService;
	
	@Resource(name="remainVpnServiceImpl")
	private RemainVpnService remainVpnService;
	
	@Resource(name="remainDataServiceImpl")
	private RemainDataService remainDataService;
	
	@Resource(name="deviceServiceImpl")
	private DeviceService deviceService;
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> login(@RequestBody LoginReq req) {
		logger.trace("login---" + req.getName() + "--" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		User user = new User();
		user.setName(req.getName());
		user.setPassword(req.getPassword());
		List<User> list = userService.selectList(user);
		if(null == list || list.size() == 0 )
		{
			rsp.setAllData(Const.common_error, "", "");
		}else
		{
			rsp.setAllData(Const.common_ok, "", "");
		}
		return rsp;
	}
	/**
	 * 获取VPN列表接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getVpnList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<RemainVpn>> getVpnList(@RequestBody GetVpnListReq req) {
		logger.trace("getVpnList---" + new Date());
		PGResponse<List<RemainVpn>> rsp = new PGResponse<List<RemainVpn>>();
		List<RemainVpn> list = remainVpnService.selectAll();
		rsp.setAllData(Const.common_ok, "", list);
		return rsp;
	}
	/**
	 * 获取运营数据接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAppList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<App>> getAppList(@RequestBody GetAppListReq req) {
		logger.trace("getAppList---" + req.getUserId() + "--" + new Date());
		PGResponse<List<App>> rsp = new PGResponse<List<App>>();
		List<App> list = appService.selectAll();
		rsp.setAllData(Const.common_ok, "", list);
		return rsp;
	}
	
	/**
	 * 获取游戏全部运营数据接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getRemainList", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<RemainData>> getRemainList(@RequestBody GetRemainListReq req) {
		logger.trace("getRemainList---" + req.getAppId() + "--" + new Date());
		PGResponse<List<RemainData>> rsp = new PGResponse<List<RemainData>>();
		RemainData data = new RemainData();
		data.setAppId(req.getAppId());
		List<RemainData> list = remainDataService.selectList(data);
		rsp.setAllData(Const.common_ok, "", list);
		return rsp;
	}
	
	/**
	 * 获取今日实时数据情况
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTodayRemain", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<List<RemainData>> getTodayRemain(@RequestBody GetRemainListReq req) {
		logger.trace("getTodayRemain---" + req.getAppId() + "--" + new Date());
		PGResponse<List<RemainData>> rsp = new PGResponse<List<RemainData>>();
		RemainData data = new RemainData();
		Date todayMorning = DateUtils.getTimesMorning();
		Date todayNight = DateUtils.getTimesNight();
		data.setAppId(req.getAppId());
		data.setCreateDate(todayMorning);
		data.setUpdateDate(todayNight);
		List<RemainData> list = remainDataService.selectTodayData(data);
		rsp.setAllData(Const.common_ok, "", list);
		return rsp;
	}
	/**
	 * 获取VPN列表接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/curdVpnVpn", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> saveVpn(@RequestBody CurdVpnReq req) {
		logger.trace("saveVpn---" + req.getName() + "--" + new Date());
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
			rsp.setAllData(Const.common_error, "", "");
			return rsp;
		}
		return rsp;
	}
}
