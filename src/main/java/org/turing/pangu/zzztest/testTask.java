package org.turing.pangu.zzztest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.client.utils.HttpClientUtils;
import org.junit.Test;
import org.turing.pangu.controller.phone.request.GetTaskReq;
import org.turing.pangu.controller.phone.request.TaskFinishReq;
import org.turing.pangu.controller.phone.response.GetTaskRsp;
import org.turing.pangu.model.Device;
import org.turing.pangu.utils.HttpUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
/*
 * 
 * {
    "isHaveTask": 1,
    "loopTime": 10,
    "task": {
        "appId": 3,
        "deviceId": "eeeeewww",
        "isFinished": 0,
        "operType": 2,
        "spanTime": 5,
        "stockInfo": {
            "androidId": "0g0d4ss1n72c6fwn",
            "androidSerial": "zq6c8k051se2judn",
            "androidVersion": "4.4",
            "appId": 3,
            "blueTooth": "cf:e4:7b:c8:37:b8",
            "board": "ZL9",
            "bootloader": "Android/armeabi/armeabi:4.4/金立GN708T/eng.work.47120588.060365:eng/test-keys",
            "brand": "",
            "bssid": "7e:c0:8b:0d:53:b2",
            "carrier": "China Mobile",
            "carrierCode": "46002",
            "countryCode": "CN",
            "cpuAbi": "armeabi",
            "createDate": 1485261881000,
            "device": "ZL9",
            "deviceType": 0,
            "display": "4.4.283.P2..IT",
            "height": 960,
            "id": 5011,
            "imei": "863955035740300",
            "imsi": "460074174449092",
            "ip": "127.0.0.1",
            "isActived": 0,
            "isRemain": 0,
            "isWhiteIp": 0,
            "mac": "7e:c0:8b:0d:53:b2",
            "manufacture": "",
            "model": "金立GN708T",
            "phone": "+8617865018446",
            "phoneStatus": 1,
            "product": "ZL9",
            "sdk": 19,
            "simSerial": "89860045293689388947",
            "simStatus": 5,
            "ssid": "ChinaNet-01wcd",
            "ua": "Dalvik/1.4.0(Linux; U; 4.4;  Build/7T68C",
            "updateDate": 1485261881000,
            "width": 540
        },
        "taskId": "7G6Q6qHGQB30J8x57fP8Sh4337Nb9O4x",
        "times": 1,
        "vpnToken": "7G6Q6qHGQB30J8x5"
    },
    "taskIp": "127.0.0.1"
}*/
public class testTask {
	@Test
	public void test(){
		getTask();
		taskFinish();
		getTask();
	}
	
	public void getTask(){
		String getTaskUrl = "http://localhost:8080/mobile/getTask.pangu";
		//String getTaskUrl="http://pangu.u-app.cn/mobile/getTask.pangu";
		//String taskFinishUrl = "http://localhost:8080/mobile/taskFinish.pangu";
		
		GetTaskReq req = new GetTaskReq();
		req.setDeviceId("eeeeewww");
		req.setAccessToken("00kkfdajl");
		String json = JSON.toJSONString(req);
		String contentStr = HttpUtils.doPost(getTaskUrl, json, HttpUtils.UTF8);
		GetTaskRsp rsp = JSON.parseObject(contentStr,
				new TypeReference<GetTaskRsp>() {
				});
		Device device = new Device();
		contentStr = JSON.toJSONString(rsp);
		System.out.print("\n");
		System.out.print(contentStr);
	}
	
	public void taskFinish(){
		String taskFinishUrl = "http://localhost:8080/mobile/taskFinish.pangu";
		
		TaskFinishReq req = new TaskFinishReq();
		req.setTaskId("IFMro5Ga9629g556wt6yFdC1dlt2CNHP");
		req.setVpnToken("IFMro5Ga9629g556");
		req.setIsFinished(1);
		String json = JSON.toJSONString(req);
		String contentStr = HttpUtils.doPost(taskFinishUrl, json, HttpUtils.UTF8);
		System.out.print("\n");
		System.out.print(contentStr);
	}

}
