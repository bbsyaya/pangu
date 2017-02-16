package org.turing.pangu.controller.phone;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.turing.pangu.controller.common.BaseController;
import org.turing.pangu.controller.common.PGResponse;
import org.turing.pangu.controller.common.PhoneTask;
import org.turing.pangu.controller.phone.request.GetTaskReq;
import org.turing.pangu.controller.phone.request.ReportReq;
import org.turing.pangu.controller.phone.request.TaskFinishReq;
import org.turing.pangu.controller.phone.response.GetTaskRsp;
import org.turing.pangu.engine.TaskEngine;
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
	
	/*
	 * 设备登录
	 * */
	@RequestMapping(value = "/deviceLogin", method = RequestMethod.POST)
	public @ResponseBody String deviceLogin(HttpServletRequest request,
			HttpServletResponse rsp) {
		
		return "";
	}
	
	/*
	 * 手机端请求任务
	 * */
	@RequestMapping(value = "/getTask", method = RequestMethod.POST)
	public @ResponseBody GetTaskRsp getTask(HttpServletRequest request) {
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		String realIp = TaskEngine.getInstance().getRealIp(request);
		String contentStr = getRequestBody(request);
		GetTaskReq req = JSON.parseObject(contentStr,
				new TypeReference<GetTaskReq>() {
				});
		GetTaskRsp rsp = new GetTaskRsp();
		PhoneTask task = TaskEngine.getInstance().getTask(req.getDeviceId(), remoteIp, realIp);
		rsp.setTask(task);
		if(task.getAppId() != 0L){
			rsp.setIsHaveTask(1);
		}else{
			rsp.setIsHaveTask(0);
		}
		rsp.setLoopTime(TaskEngine.SPAN_TIME);
		rsp.setTaskIp(remoteIp);
		return rsp;
	}
	/*
	 * 任务完成情况
	 * */
	@RequestMapping(value = "/taskFinish", method = RequestMethod.POST)
	public @ResponseBody String taskFinish(HttpServletRequest request,
			HttpServletResponse rsp) {
		String result = "";
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		String realIp = TaskEngine.getInstance().getRealIp(request);
		String contentStr = getRequestBody(request);
		TaskFinishReq req = JSON.parseObject(contentStr,
				new TypeReference<TaskFinishReq>() {
				});
		
		TaskEngine.getInstance().taskFinish(req,remoteIp,realIp);
		return "";
	}
	/**
	 * 获取短信接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public @ResponseBody String report(HttpServletRequest request,
			HttpServletResponse rspod) {
		logger.debug("report---" + "--" + new Date());
		String result = "";
		PGResponse<String> rsp = new PGResponse<String>();
		// ----------------------------------------------
		String contentStr = getRequestBody(request);
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
		if (remainVpnService.isWhileListIp(req.getDevice().getIp())) {
			deviceService.saveReport(req, true);
		} else {
			deviceService.saveReport(req, false);
		}
		rsp.setAllData(Const.common_ok, "common_ok", null);

		result = JSON.toJSONString(rsp);
		return result;
	}
	
	private String getRequestBody(HttpServletRequest request){
		InputStream is = null;
		String contentStr = "";
		try {
			is = request.getInputStream();
			contentStr = IOUtils.toString(is, "utf-8");
			}catch (IOException e) {
				e.printStackTrace();
			}
		return contentStr;
	}

}
