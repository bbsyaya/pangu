package org.turing.pangu.zzztest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.client.utils.HttpClientUtils;
import org.junit.Test;
import org.turing.pangu.controller.phone.request.GetTaskReq;
import org.turing.pangu.controller.phone.response.GetTaskRsp;
import org.turing.pangu.model.Device;
import org.turing.pangu.utils.HttpUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class testTask {
	@Test
	public void getTask(){
		//String getTaskUrl = "http://localhost:8080/mobile/getTask.pangu";
		//String taskFinishUrl = "http://localhost:8080/mobile/taskFinish.pangu";
		String getTaskUrl="http://pangu.u-app.cn/mobile/getTask.pangu";
		GetTaskReq req = new GetTaskReq();
		req.setDeviceId("eeeeewww");
		req.setAccessToken("00kkfdajl");
		String json = JSON.toJSONString(req);
		String contentStr = HttpUtils.doPost(getTaskUrl, json, HttpUtils.UTF8);
		GetTaskRsp rsp = JSON.parseObject(contentStr,
				new TypeReference<GetTaskRsp>() {
				});
		Device device = new Device();
		device.setImei("9ifdajkljfdslajlfsa");
		device.setImsi("37uhfaiouofjfla");
		rsp.getTask().setStockInfo(device);
		contentStr = JSON.toJSONString(rsp);
		System.out.print(contentStr);
	}

}
