package org.turing.pangu.controller.phone;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import org.turing.pangu.engine.RemainEngine;
import org.turing.pangu.model.App;
import org.turing.pangu.service.AppService;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.service.RemainVpnService;
import org.turing.pangu.utils.Const;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * 
 * @author turing
 * @des 处理手机端过来的所有关于用户信息相关请求（如：注册，登录，修改密码）
 *
 */
@Controller("mobileReportController")
@RequestMapping("/mobile")
public class MobileReportController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(MobileReportController.class);

	@Resource(name = "appServiceImpl")
	private AppService appService;

	@Resource(name = "remainVpnServiceImpl")
	private RemainVpnService remainVpnService;

	@Resource(name = "deviceServiceImpl")
	private DeviceService deviceService;

	@RequestMapping(value = "/reportGet", method = RequestMethod.GET)
	public @ResponseBody PGResponse<String> reportGet(HttpServletRequest req,
			HttpServletResponse resp) {
		logger.debug("report---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		String str = req.getParameter("report");
		ReportReq report = JSON.parseObject(str,
				new TypeReference<ReportReq>() {
				});

		// 1. 先验证是否为有效appid
		App app = appService.select(report.getAppId());
		if (app == null) {
			rsp.setAllData(Const.common_error, "common_error", null);
			return rsp;
		}
		// 2. 验证是否为 VPN ip
		// 3. 写入DB
		if (remainVpnService.isWhileListIp(report.getDevice().getIpAddress())) {
			deviceService.saveReport(report, true);
		} else {
			deviceService.saveReport(report, false);
		}
		rsp.setAllData(Const.common_ok, "common_ok", null);
		return rsp;
	}

	/**
	 * 获取短信接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public @ResponseBody String reportPost(HttpServletRequest request,
			HttpServletResponse rspod) {
		logger.debug("report---" + "--" + new Date());
		String result = "";
		PGResponse<String> rsp = new PGResponse<String>();
		// ----------------------------------------------
		InputStream is = null;
		String contentStr = "";
		try {
			is = request.getInputStream();
			contentStr = IOUtils.toString(is, "utf-8");

			ReportReq req = JSON.parseObject(contentStr,
					new TypeReference<ReportReq>() {
					});
			if (null == req || req.equals("")) {
				rsp.setAllData(Const.common_error, "common_error", null);
				result = JSON.toJSONString(rsp);
				return result;
			}

			// ----------------------------------------------

			// 1. 先验证是否为有效appid
			App app = appService.select(req.getAppId());
			if (app == null) {
				rsp.setAllData(Const.common_error, "common_error", null);
				result = JSON.toJSONString(rsp);
				return result;
			}
			// 2. 验证是否为 VPN ip
			// 3. 写入DB
			if (remainVpnService.isWhileListIp(req.getDevice().getIpAddress())) {
				deviceService.saveReport(req, true);
			} else {
				deviceService.saveReport(req, false);
			}
			rsp.setAllData(Const.common_ok, "common_ok", null);

		} catch (IOException e) {
			rsp.setAllData(Const.common_error, "common_error", null);
			e.printStackTrace();
		}
		result = JSON.toJSONString(rsp);
		return result;
	}

}
