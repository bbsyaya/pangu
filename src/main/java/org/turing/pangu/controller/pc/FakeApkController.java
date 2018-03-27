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

@Controller("fakeApkController")
@RequestMapping("/")
public class FakeApkController extends BaseController {
	private static final Logger logger = Logger.getLogger(PCMngController.class);
	private static String RSP_MSG = "登录错误,请联系QQ:274413443";
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody PGResponse<String> submitContent(String name,String phone,String content,HttpServletRequest request) {
		logger.info("submitContent-name:" + name);
		logger.info("submitContent-phone:" + phone);
		logger.info("submitContent-content:" + content);
		return null;
	}
}
