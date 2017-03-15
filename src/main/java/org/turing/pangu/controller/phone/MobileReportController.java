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
import org.turing.pangu.controller.phone.request.DeviceLoginReq;
import org.turing.pangu.controller.phone.request.GetAppInfoReq;
import org.turing.pangu.controller.phone.request.GetBlackIpListReq;
import org.turing.pangu.controller.phone.request.GetPlatformInfoReq;
import org.turing.pangu.controller.phone.request.GetTaskReq;
import org.turing.pangu.controller.phone.request.ReportReq;
import org.turing.pangu.controller.phone.request.TaskFinishReq;
import org.turing.pangu.controller.phone.response.DeviceLoginRsp;
import org.turing.pangu.controller.phone.response.GetBlackIpListRsp;
import org.turing.pangu.controller.phone.response.GetTaskRsp;
import org.turing.pangu.engine.AppEngine;
import org.turing.pangu.engine.DeviceEngine;
import org.turing.pangu.engine.PlatformEngine;
import org.turing.pangu.engine.TaskEngine;
import org.turing.pangu.engine.TimeMng;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Platform;
import org.turing.pangu.service.DeviceService;
import org.turing.pangu.utils.Const;
import org.turing.pangu.utils.RandomUtils;
import org.turing.pangu.utils.TraceUtils;

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

	@Resource(name = "deviceServiceImpl")
	private DeviceService deviceService;
	
	/*
	 * 设备登录
	 * */
	@RequestMapping(value = "/deviceLogin", method = RequestMethod.POST)
	public @ResponseBody DeviceLoginRsp deviceLogin(HttpServletRequest request,
			HttpServletResponse rsponsed) {
		TraceUtils.getTraceInfo();
		String contentStr = getRequestBody(request);
		logger.info("req:" + contentStr);
		logger.info("ip:" + TaskEngine.getInstance().getRemoteIp(request));
		DeviceLoginReq req = JSON.parseObject(contentStr,
				new TypeReference<DeviceLoginReq>() {
				});
		DeviceLoginRsp rsp = new DeviceLoginRsp();
		rsp.setDeviceId(req.getDeviceId());
		if(req.getDeviceId().equals("turing")){
			rsp.setDeviceId(RandomUtils.getRandomNumbersAndCapitalLetters(16));
		}
		rsp.setDeviceToken(RandomUtils.getRandomNumbersAndCapitalLetters(32));
		logger.info("rsp:" + JSON.toJSONString(rsp).toString());
		return rsp;
	}

	@RequestMapping(value = "/getBlackIpList", method = RequestMethod.POST)
	public @ResponseBody GetBlackIpListRsp getBlackIpList(HttpServletRequest request) {
		TraceUtils.getTraceInfo();
		String contentStr = getRequestBody(request);
		logger.info("req:" + contentStr);
		logger.info("ip:" + TaskEngine.getInstance().getRemoteIp(request));
		GetBlackIpListReq req = JSON.parseObject(contentStr,
				new TypeReference<GetBlackIpListReq>() {
				});
		GetBlackIpListRsp rsp = new GetBlackIpListRsp();
		Platform model = null;
		model = PlatformEngine.getInstance().getPlatformInfo(req.getPlatformId());
		String[] ipList = model.getBlackIp().split("\\|");
		int count = 0;
		for(String ip :ipList){
			rsp.getIpList().add(ip);
			count++;
		}
		rsp.setCount(count);
		logger.info("rsp:" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	/**
	 * 获取平台信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getPlatformInfo", method = RequestMethod.POST)
	public @ResponseBody Platform getPlatformInfo(HttpServletRequest request) {
		TraceUtils.getTraceInfo();
		String contentStr = getRequestBody(request);
		GetPlatformInfoReq req = JSON.parseObject(contentStr,
				new TypeReference<GetPlatformInfoReq>() {
				});
		Platform model = null;
		model = PlatformEngine.getInstance().getPlatformInfo(req.getPlatformId());
		logger.info("getPlatformInfo---end" + model.toString());
		return model;
	}
	
	/**
	 * 获取app信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAppInfo", method = RequestMethod.POST)
	public @ResponseBody App getAppInfo(HttpServletRequest request) {
		TraceUtils.getTraceInfo();
		String contentStr = getRequestBody(request);
		GetAppInfoReq req = JSON.parseObject(contentStr,
				new TypeReference<GetAppInfoReq>() {
				});
		
		App model = null;
		model = AppEngine.getInstance().getAppInfo(req.getAppId());
		logger.info("getAppInfo---end" + model.toString());
		return model;
	}
	/*
	 * 手机端请求任务
	 * */
	@RequestMapping(value = "/getTask", method = RequestMethod.POST)
	public @ResponseBody GetTaskRsp getTask(HttpServletRequest request) {
		Date start = new Date();
		TraceUtils.getTraceInfo();
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		String realIp = TaskEngine.getInstance().getRealIp(request);
		String contentStr = getRequestBody(request);
		logger.info("req:" + contentStr);
		logger.info("ip:" + remoteIp);
		GetTaskReq req = JSON.parseObject(contentStr,
				new TypeReference<GetTaskReq>() {
				});
		GetTaskRsp rsp = new GetTaskRsp();
		PhoneTask task = TaskEngine.getInstance().getTask(req.getDeviceId(), remoteIp, realIp);
		if(null == task){
			rsp.setIsHaveTask(0);
		}else{
			rsp.setTask(task);
			rsp.setIsHaveTask(1);
		}
		rsp.setLoopTime(TimeMng.SPAN_TIME);
		rsp.setTaskIp(remoteIp);
		logger.info("rsp:" + JSON.toJSONString(rsp).toString());
		TimeMng.spellTime(start);
		return rsp;
	}

	/*
	 * 任务完成情况
	 * */
	@RequestMapping(value = "/taskFinish", method = RequestMethod.POST)
	public @ResponseBody PGResponse<String> taskFinish(HttpServletRequest request,
			HttpServletResponse rspod) {
		Date start = new Date();
		TraceUtils.getTraceInfo();
		PGResponse<String> rsp = new PGResponse<String>();
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		String realIp = TaskEngine.getInstance().getRealIp(request);
		String contentStr = getRequestBody(request);
		logger.info("req:" + contentStr);
		logger.info("ip:" + remoteIp);
		TaskFinishReq req = JSON.parseObject(contentStr,
				new TypeReference<TaskFinishReq>() {
				});
		
		TaskEngine.getInstance().taskFinish(req,remoteIp,realIp);
		rsp.setAllData(Const.common_ok, "common_ok", null);
		String result = JSON.toJSONString(rsp);
		logger.info("taskFinish---end" + result);
		TimeMng.spellTime(start);
		return rsp;
	}
	/**
	 * 获取短信接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public @ResponseBody PGResponse<String> report(HttpServletRequest request,
			HttpServletResponse rspod) {
		Date start = new Date();
		TraceUtils.getTraceInfo();
		String result = "";
		PGResponse<String> rsp = new PGResponse<String>();
		// ----------------------------------------------
		String contentStr = getRequestBody(request);
		logger.info("req:" + contentStr);
		logger.info("ip:" + TaskEngine.getInstance().getRemoteIp(request));
		ReportReq req = JSON.parseObject(contentStr,
				new TypeReference<ReportReq>() {
				});
		if (null == req || req.equals("")) {
			rsp.setAllData(Const.common_error, "common_error", null);
			result = JSON.toJSONString(rsp);
			logger.info("rsp:" + result.toString());
			return rsp;
		}
		// ----------------------------------------------
		// 1. 先验证是否为有效appid
		App app = AppEngine.getInstance().getAppInfo(req.getAppId());
		if (app == null) {
			rsp.setAllData(Const.common_error, "common_error", null);
			result = JSON.toJSONString(rsp);
			logger.info("rsp:" + result.toString());
			return rsp;
		}
		// 2. 验证是否为 VPN ip
		// 3. 写入DB
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		req.getDevice().setIp(remoteIp);
		DeviceEngine.getInstance().saveReport(req);
		rsp.setAllData(Const.common_ok, "common_ok", null);
		result = JSON.toJSONString(rsp);
		logger.info("rsp:" + result.toString());
		TimeMng.spellTime(start);
		return rsp;
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
