package org.turing.pangu.controller.phone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.turing.pangu.controller.BaseController;
import org.turing.pangu.controller.phone.request.BaseReq;
import org.turing.pangu.controller.phone.request.GetAuthCodeReq;
import org.turing.pangu.controller.phone.request.LoginReq;
import org.turing.pangu.controller.phone.request.RegisterReq;
import org.turing.pangu.controller.phone.response.LoginRsp;
import org.turing.pangu.dao.UserDaoImpl;
import org.turing.pangu.utils.Const;

/**
 * 
 * @author turing
 * @des 处理手机端过来的所有关于用户信息相关请求（如：注册，登录，修改密码）
 *
 */
@Controller("mobileUserController")
@RequestMapping("/mobile/user")
public class MobileUserController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MobileUserController.class);
	@Autowired
	private UserDaoImpl userService;
	/**
	 * 获取短信接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAuthCode", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody MobileResponse<String> getAuthCode(@RequestBody GetAuthCodeReq req) {
		MobileResponse<String> rsp = new MobileResponse<String>();
		// 参数验证
		if (req == null) {
			rsp.setAllData(Const.common_param_error, "common_param_error");
			return rsp;
		} else if (req.type == null || req.type < 0) {
			rsp.setAllData(Const.phone_user_codeType_null, "phone_user_codeType_null");
			return rsp;
		}

		// 0注册\1找回登录密码\2交易密码\3充值\4提现
		switch (req.type) {
		case 0:
			return registerSendCode(req);// 注册
		case 2:
			return resetTradePasswordSendCode(req);// 重置交易密码
		default:
			rsp.setAllData(Const.phone_user_codeType_error, "phone_user_codeType_error");
			return rsp;
		}
	}

	/**
	 * 注册-发送验证码
	 * 
	 * @author turing
	 * @return
	 */
	private MobileResponse<String> registerSendCode(GetAuthCodeReq req) {
		MobileResponse<String> rsp = new MobileResponse<String>();
		return rsp;
	}

	/**
	 * 重置交易密码-发送验证码
	 * 
	 * @author turing
	 * @param req
	 * @return
	 */
	private MobileResponse<String> resetTradePasswordSendCode(GetAuthCodeReq req) {
		MobileResponse<String> rsp = new MobileResponse<String>();



		return rsp;
	}

	/**
	 * 注册
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody MobileResponse<LoginRsp> register(@RequestBody RegisterReq req) {
		return null;
	}

	/**
	 * 登录
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody MobileResponse<LoginRsp> login(@RequestBody LoginReq req) {
		MobileResponse<LoginRsp> rsp = new MobileResponse<LoginRsp>();
		return rsp;
	}

	/**
	 * 获取授权token
	 * 
	 * @author turing
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getOAuthToken", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody MobileResponse<String> getOAuthToken(@RequestBody BaseReq req) {
		MobileResponse<String> ret = new MobileResponse<String>();
		//ret.setAllData(Const.common_ok, "common_ok", tokenService.getOauthToken());
		return ret;
	}

}
