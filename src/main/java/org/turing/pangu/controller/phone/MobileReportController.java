package org.turing.pangu.controller.phone;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.turing.pangu.controller.common.BaseController;
import org.turing.pangu.controller.common.PGResponse;
import org.turing.pangu.controller.phone.request.ReportReq;
import org.turing.pangu.dao.UserDaoImpl;
import org.turing.pangu.model.App;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.service.RemainVpnService;

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

	@Resource(name="appServiceImpl")
	private AppService appService;
	
	@Resource(name="remainVpnServiceImpl")
	private RemainVpnService remainVpnService;
	
	@Resource(name="deviceServiceImpl")
	private DeviceService deviceService;
	/**
	 * 获取短信接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/report", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody PGResponse<String> report(@RequestBody ReportReq req) {
		logger.trace("report---" + req.getAppId() + "--" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		//1. 先验证是否为有效appid
		App app = appService.select(req.getAppId());
		if(app == null){
			rsp.setAllData(1, "上报失败");
			return rsp;
		}
		//2. 验证是否为 VPN ip
		//3. 写入DB
		if(remainVpnService.isWhileListIp(req.getDevice().getIpAddress())){
			deviceService.saveReport(req, true);
		}else{
			deviceService.saveReport(req, false);
		}
		rsp.setAllData(0, "上报成功");
		return rsp;
	}


}
