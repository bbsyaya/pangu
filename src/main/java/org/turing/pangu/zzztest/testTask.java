package org.turing.pangu.zzztest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.turing.pangu.controller.pc.request.VpnConnectInfoReq;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.pc.request.VpnOperUpdateReq;
import org.turing.pangu.controller.pc.response.VpnLoginRsp;
import org.turing.pangu.controller.phone.request.GetTaskReq;
import org.turing.pangu.controller.phone.request.TaskFinishReq;
import org.turing.pangu.controller.phone.response.GetTaskRsp;
import org.turing.pangu.model.Device;
import org.turing.pangu.utils.AESUtils;
import org.turing.pangu.utils.HttpUtils;
import org.turing.pangu.utils.RandomUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
public class testTask {
	//private String host = "http://localhost:8080/";
	private String host = "http://pangu.u-app.cn/";
	public void test(){
		String contentStr = "";
		GetTaskRsp rsp = JSON.parseObject(contentStr,
				new TypeReference<GetTaskRsp>() {
				});
		Device device = new Device();
		contentStr = JSON.toJSONString(rsp);
	}
	@Test
	public void testLoop(){
		Date d = new Date();  
        System.out.println(d);  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss");  
        String dateNowStr = sdf.format(d);  
        System.out.println("格式化后的日期：" + dateNowStr);  
		/*
		String cost = "来自 111.206.227.118 的回复: 字节=32 时间=40ms TTL=49";
		//Pattern compile = Pattern.compile("(\\d+\\.\\d+)|(\\d+)");
		Pattern compile = Pattern.compile("(\\d+)ms");
        Matcher matcher = compile.matcher(cost);
        matcher.find();
        String str = matcher.group();
        String encoding=System.getProperty("file.encoding");
        System.out.println("Default System Encoding: " + encoding);
        */
        String token = "*UJHGGG$%432HBNJklP)_+:?>Z!)(HJkQ!EDXSA=|]";
        String tokenKey = "*&^%$EDc3@:{'?>B";
        String str = AESUtils.encryptToBase64(token,tokenKey);
        System.out.print(RandomUtils.getRandomNumbersAndCapitalLetters(64));
        String str1 = AESUtils.decryptFromBase64(str, tokenKey);
        //System.out.print(str1);
        
		VpnLoginRsp rsp = loginPangu(0,"kwk0www");
		
		for(int index = 0;index < 1;index++){
			try {
				Thread.sleep(1000);
				//GetTaskRsp rsp1 = getTask("2333"+index);
				Thread.sleep(1000);
				//if(rsp1.getIsHaveTask() == 1){
					//taskFinish(rsp1,0);
				//}
				//operUpdate(rsp.getToken());
				/*
				
				GetTaskRsp rsp2 = getTask("2333333"+index);				
				Thread.sleep(1000);
				if(rsp2.getIsHaveTask() == 1){
				//operUpdate(rsp.getToken());
					taskFinish(rsp2,1);
				}
				
				
				//operUpdate(rsp.getToken());
				
				GetTaskRsp rsp3 = getTask("233999"+index);
				Thread.sleep(1000);
				if(rsp3.getIsHaveTask() == 1){
					taskFinish(rsp3,0);
				}
				
				//operUpdate(rsp.getToken());
				
				GetTaskRsp rsp4 = getTask("233555999"+index);
				Thread.sleep(1000);
				if(rsp4.getIsHaveTask() == 1){
					taskFinish(rsp4,0);
				}
				
				operUpdate(rsp.getToken());
				*/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//getTask("123444");
		//getTask("sdffff");
		//System.out.print("\n" + Pattern.compile("[^0-9]").matcher(str).replaceAll(""));
	}
	public void getConnectInfo(){
		String loginUrl = host+"pc/getConnectInfo.pangu";
		VpnConnectInfoReq req = new VpnConnectInfoReq();
		String json = JSON.toJSONString(req);
		String contentStr = HttpUtils.doPost(loginUrl, json, HttpUtils.UTF8);
		System.out.print("\n"+contentStr);
	}
	public VpnLoginRsp loginPangu(int operType,String deviceId){
		String loginUrl = host + "pc/vpnLogin.pangu";
		VpnLoginReq req = new VpnLoginReq();
		req.setOperType(operType);
		req.setDeviceId(deviceId);// 取电脑mac地址
		String json = JSON.toJSONString(req);
		String contentStr = HttpUtils.doPost(loginUrl, json, HttpUtils.UTF8);
		VpnLoginRsp rsp = JSON.parseObject(contentStr,
				new TypeReference<VpnLoginRsp>() {
				});
		System.out.print("\n"+contentStr);
		return rsp;
	}
	public GetTaskRsp getTask(String deviceId){
		String getTaskUrl = host + "mobile/getTask.pangu";
		GetTaskReq req = new GetTaskReq();
		Date data = new Date();
		req.setDeviceId(deviceId);
		req.setAccessToken("");
		String json = JSON.toJSONString(req);
		String contentStr = HttpUtils.doPost(getTaskUrl, json, HttpUtils.UTF8);
		GetTaskRsp rsp = JSON.parseObject(contentStr,
				new TypeReference<GetTaskRsp>() {
				});
		System.out.print("\n" + contentStr);
		return rsp;
	}
	public void operUpdate(String token){
		String operUpdateUrl = host + "pc/vpnOperUpdate.pangu";
		VpnOperUpdateReq req = new VpnOperUpdateReq();
		req.setToken(token);
		String json = JSON.toJSONString(req);
		String contentStr = HttpUtils.doPost(operUpdateUrl, json, HttpUtils.UTF8);
		System.out.print("\n" + contentStr);
	}
	public void taskFinish(GetTaskRsp rsp,int isFinished){
		String taskFinishUrl = host + "mobile/taskFinish.pangu";
		TaskFinishReq req = new TaskFinishReq();
		req.setTaskId(rsp.getTask().getTaskId());
		req.setVpnToken(rsp.getTask().getVpnToken());
		req.setIsFinished(isFinished);
		String json = JSON.toJSONString(req);
		String contentStr = HttpUtils.doPost(taskFinishUrl, json, HttpUtils.UTF8);
		System.out.print("\n");
		System.out.print(contentStr);
	}
	
}
