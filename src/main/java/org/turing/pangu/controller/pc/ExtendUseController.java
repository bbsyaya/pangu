package org.turing.pangu.controller.pc;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.turing.pangu.controller.common.BaseController;
import org.turing.pangu.controller.common.PGResponse;
import org.turing.pangu.engine.AppEngine;
import org.turing.pangu.engine.CmnPayUserEngine;
import org.turing.pangu.engine.PhoneBrandEngine;
import org.turing.pangu.engine.TaskEngine;
import org.turing.pangu.iptrunk.BaiduLocation;
import org.turing.pangu.iptrunk.LocationMng;
import org.turing.pangu.model.App;
import org.turing.pangu.model.Task;
import org.turing.pangu.phone.ChangeDeviceInfo;
import org.turing.pangu.utils.AESUtils;
import org.turing.pangu.utils.Const;

import com.alibaba.fastjson.JSON;

@Controller("extendUseController")
@RequestMapping("/common")
public class ExtendUseController extends BaseController {
	private static final Logger logger = Logger.getLogger(PCMngController.class);
	private static String RSP_MSG = "登录错误,请联系QQ:274413443";
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody PGResponse<String> login(String info,HttpServletRequest request) {
		logger.info("login---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		if(null == info || info.equals("")){
			rsp.setAllData(Const.common_error, RSP_MSG, null);
			return rsp;
		}
		info = info.replace(' ', '+');
		String decode = AESUtils.decryptFromBase64(info);
		logger.info("login-info" + decode);
		String[] userAndPass = decode.split(",");
		String token = CmnPayUserEngine.getInstance().getToken(userAndPass[0], userAndPass[1]);
		if(null == token || token.equals("")){
			rsp.setAllData(Const.common_error, "common_error", RSP_MSG);
		}else{
			token = AESUtils.encryptToBase64(token);
			rsp.setAllData(Const.common_ok, "common_ok", token);
		}
		logger.info("login---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	@RequestMapping(value = "/getFakeApkInfoNoDecrypt", method = RequestMethod.GET)
	public @ResponseBody PGResponse<ChangeDeviceInfo> getFakeApkInfoTest(String info,HttpServletRequest request) {
		logger.info("getFakeApkInfo---" + new Date());
		// 通过ip 得到 城市 code
		PGResponse<ChangeDeviceInfo> rsp = new PGResponse<ChangeDeviceInfo>();
		if(null == info){
			rsp.setAllData(Const.common_error, RSP_MSG, null);
			return rsp;
		}
		info = info.replace(' ', '+');
		String token = AESUtils.decryptFromBase64(info);
		
		int type = CmnPayUserEngine.getInstance().getUserType(token);
		if(CmnPayUserEngine.PAY_TYPE_ERROR == type){
			rsp.setAllData(Const.common_error, RSP_MSG, null);
			return rsp;
		}
		
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		LocationMng mng = new LocationMng();
		BaiduLocation location = mng.getLocation(remoteIp);
		ChangeDeviceInfo devicInfo = null;
		if(CmnPayUserEngine.PAY_TYPE_FREE == type){
			devicInfo = PhoneBrandEngine.getInstance().getNewDeviceInfoExt(remoteIp,location, false);
		}else{
			devicInfo = PhoneBrandEngine.getInstance().getNewDeviceInfoExt(remoteIp,location, true);
		}
		rsp.setAllData(Const.common_ok, "OK", devicInfo);
		CmnPayUserEngine.getInstance().minusCount(info);
		logger.info("getFakeApkInfo---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	
	@RequestMapping(value = "/getFakeApkInfo", method = RequestMethod.GET)
	public @ResponseBody PGResponse<String> getFakeApkInfo(String info,HttpServletRequest request) {
		logger.info("getFakeApkInfo---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		if(null == info){
			rsp.setAllData(Const.common_error, RSP_MSG, null);
			return rsp;
		}
		info = info.replace(' ', '+');
		String token = AESUtils.decryptFromBase64(info);
		int type = CmnPayUserEngine.getInstance().getUserType(token);
		if(CmnPayUserEngine.PAY_TYPE_ERROR == type){
			rsp.setAllData(Const.common_error, RSP_MSG, null);
			return rsp;
		}
		
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		LocationMng mng = new LocationMng();
		BaiduLocation location = mng.getLocation(remoteIp);
		ChangeDeviceInfo devicInfo = null;
		if(CmnPayUserEngine.PAY_TYPE_FREE == type){
			devicInfo = PhoneBrandEngine.getInstance().getNewDeviceInfoExt(remoteIp,location, false);
		}else{
			devicInfo = PhoneBrandEngine.getInstance().getNewDeviceInfoExt(remoteIp,location, true);
		}
		rsp.setAllData(Const.common_ok, "", AESUtils.encryptToBase64(JSON.toJSONString(devicInfo).toString()));
		CmnPayUserEngine.getInstance().minusCount(info);
		logger.info("getFakeApkInfo---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
}
