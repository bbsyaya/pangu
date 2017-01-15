package org.turing.pangu.controller.phone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.turing.pangu.controller.BaseController;
import org.turing.pangu.controller.phone.request.ReportReq;
import org.turing.pangu.dao.UserDaoImpl;

/**
 * 
 * @author turing
 * @des 处理手机端过来的所有关于用户信息相关请求（如：注册，登录，修改密码）
 *
 */
@Controller("mobileUserController")
@RequestMapping("/mobile")
public class MobileReportController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MobileReportController.class);
	@Autowired
	private UserDaoImpl userService;
	/**
	 * 获取短信接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/report", method = RequestMethod.POST, consumes = "application/json")
	
	public @ResponseBody MobileResponse<String> report(@RequestBody ReportReq req) {
		MobileResponse<String> rsp = new MobileResponse<String>();
		//1. 先验证是否为有效token
		
		
		//2. 验证是否为 VPN ip
		
		//3. 写入DB
		return rsp;
	}


}
