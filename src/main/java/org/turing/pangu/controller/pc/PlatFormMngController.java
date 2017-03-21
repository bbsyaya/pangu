package org.turing.pangu.controller.pc;

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
import org.turing.pangu.bean.PlatformUser;
import org.turing.pangu.controller.common.BaseController;
import org.turing.pangu.controller.common.PGResponse;
import org.turing.pangu.controller.pc.request.AppReq;
import org.turing.pangu.controller.pc.request.PlatFormReq;
import org.turing.pangu.controller.pc.request.UserReq;
import org.turing.pangu.engine.AppEngine;
import org.turing.pangu.engine.TaskEngine;
import org.turing.pangu.engine.UserEngine;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Platform;
import org.turing.pangu.model.User;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.PlatformService;
import org.turing.pangu.service.UserService;
import org.turing.pangu.utils.Const;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author turing
 * @des 处理手机端过来的所有关于用户信息相关请求（如：注册，登录，修改密码）
 *
 */
@Controller("platFormMngController")
@RequestMapping("/pf")
public class PlatFormMngController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PlatFormMngController.class);

	@Resource(name="platformServiceImpl")
	private PlatformService platformService;
	
	@Resource(name="appServiceImpl")
	private AppService appService;
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@RequestMapping(value = "/managerPf", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> managerPf(@RequestBody PlatFormReq req) {
		logger.info("managerPf---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		if(null == req || null == req.getCreateDate() )
		{
			rsp.setAllData(Const.common_error, "common_error", null);
			return rsp;
		}
		Platform pf = new Platform();
		pf.setId(req.getId());
		List<Platform> list = platformService.selectList(pf);
		if(null == list || list.size() == 0){
			platformService.insert(req);
		}else{
			platformService.update(req);
		}
		UserEngine.getInstance().setUserValidByPlatformId(req.getId(),req.getIsValid());
		rsp.setAllData(Const.common_ok, "common_ok", null);
		logger.info("managerPf---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	
	@RequestMapping(value = "/managerUser", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> managerUser(@RequestBody UserReq req) {
		logger.info("managerUser---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		if(null == req || null == req.getCreateDate() )
		{
			rsp.setAllData(Const.common_error, "common_error", null);
			return rsp;
		}
		User user = new User();
		user.setId(req.getId());
		List<User> list = userService.selectList(user);
		if(null == list || list.size() == 0){
			userService.insert(req);
		}else{
			userService.update(req);
		}
		AppEngine.getInstance().setAppValidByUserId(req.getId(),req.getIsValid());
		rsp.setAllData(Const.common_ok, "common_ok", null);
		logger.info("managerUser---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	@RequestMapping(value = "/managerApp", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> managerApp(@RequestBody AppReq req) {
		logger.info("managerApp---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		if(null == req || null == req.getCreateDate() )
		{
			rsp.setAllData(Const.common_error, "common_error", null);
			return rsp;
		}
		App app = new App();
		app.setId(req.getId());
		List<App> list = appService.selectList(app);
		if(null == list || list.size() == 0){
			appService.insert(req);
		}else{
			appService.update(req);
		}
		rsp.setAllData(Const.common_ok, "common_ok", null);
		logger.info("managerApp---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	@RequestMapping(value = "/getAllInfo", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody List<PlatformUser> getAllInfo(HttpServletRequest request) {
		logger.info("getAllInfo---" + new Date());
		List<PlatformUser> list = AppEngine.getInstance().getPlatformUserAppList();
		return list;
	}
}
