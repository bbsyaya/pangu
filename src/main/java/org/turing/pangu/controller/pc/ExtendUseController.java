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
import org.turing.pangu.utils.Const;

import com.alibaba.fastjson.JSON;

@Controller("extendUseController")
@RequestMapping("/common")
public class ExtendUseController extends BaseController {
	private static final Logger logger = Logger.getLogger(PCMngController.class);
    private final String token = "*UJHGGG$%432HBNJklP)_+:?>Z!)(HJkQ!EDXSA=|]";
    private final String tokenKey = "*&^%$EDc3@:{'?>B";


	@RequestMapping(value = "/apkLogin", method = RequestMethod.GET)
	public @ResponseBody PGResponse<String> apkLogin(String info,HttpServletRequest request) {
		logger.info("apkLogin---" + new Date());
		PGResponse<String> rsp = new PGResponse<String>();
		rsp.setAllData(Const.common_ok, "common_ok", null);
		logger.info("apkLogin---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
	
	@RequestMapping(value = "/getFakeApkInfo", method = RequestMethod.GET)
	public @ResponseBody PGResponse<ChangeDeviceInfo> getFakeApkInfo(String info,HttpServletRequest request) {
		logger.info("getFakeApkInfo---" + new Date());
		// 通过ip 得到 城市 code
		PGResponse<ChangeDeviceInfo> rsp = new PGResponse<ChangeDeviceInfo>();
		if(null == info){
			rsp.setAllData(Const.common_error, "账户到期,请联系QQ:274413443", null);
			return rsp;
		}
		long count = CmnPayUserEngine.getInstance().getCount(info);
		if(count <= 0){
			rsp.setAllData(Const.common_error, "账户到期,请联系QQ:274413443", null);
			return rsp;
		}
		
		String remoteIp = TaskEngine.getInstance().getRemoteIp(request);
		LocationMng mng = new LocationMng();
		BaiduLocation location = mng.getLocation(remoteIp);
		Task task = TaskEngine.getInstance().todayTaskList.get(0);
		App app = AppEngine.getInstance().getAppInfo(task.getAppId());
		ChangeDeviceInfo devicInfo = PhoneBrandEngine.getInstance().getNewDeviceInfo(remoteIp,location, app);
		rsp.setAllData(Const.common_ok, ""+count, devicInfo);
		CmnPayUserEngine.getInstance().minusCount(info);
		logger.info("getFakeApkInfo---" + JSON.toJSONString(rsp).toString());
		return rsp;
	}
}
