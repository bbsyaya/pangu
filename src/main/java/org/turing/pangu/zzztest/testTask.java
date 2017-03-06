package org.turing.pangu.zzztest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.turing.pangu.bean.VpnConnectInfo;
import org.turing.pangu.controller.pc.request.VpnConnectInfoReq;
import org.turing.pangu.controller.pc.request.VpnLoginReq;
import org.turing.pangu.controller.phone.request.GetTaskReq;
import org.turing.pangu.controller.phone.request.TaskFinishReq;
import org.turing.pangu.controller.phone.response.GetTaskRsp;
import org.turing.pangu.model.Device;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
public class testTask {
	
	public void test(){
		//for(int i = 0;i<1000;i++)
		{
			//getConnectInfo();
		}
		List<VpnConnectInfo> lista = new ArrayList<VpnConnectInfo>();

		VpnConnectInfo a = new VpnConnectInfo();
		VpnConnectInfo b = new VpnConnectInfo();
		VpnConnectInfo c = new VpnConnectInfo();
		VpnConnectInfo d = new VpnConnectInfo();
		
		a.setVpnName("Turing");
		a.setTunnelType("Automatic");
		a.setIp("139.196.39.239");
		a.setAuthenticationMethod("{Chap, MsChapv2}");
		a.setEncryptionLevel("Optional");
		a.setUserName("radiusyun04\\airobot");
		a.setPassword("qwerfdsa");
		lista.add(a);
		
		b.setVpnName("Turing");
		b.setTunnelType("Automatic");
		b.setIp("139.196.39.239");
		b.setAuthenticationMethod("{Chap, MsChapv2}");
		b.setEncryptionLevel("Optional");
		b.setUserName("airobot");
		b.setPassword("qwerfdsa");
		lista.add(b);

		c.setVpnName("Turing");
		c.setTunnelType("Automatic");
		c.setIp("139.196.39.239");
		c.setAuthenticationMethod("{Chap, MsChapv2}");
		c.setEncryptionLevel("Optional");
		c.setUserName("chuanqi520");
		c.setPassword("27933460ac9a");
		lista.add(c);

		d.setVpnName("Turing");
		d.setTunnelType("Automatic");
		d.setIp("139.196.39.239");
		d.setAuthenticationMethod("{Chap, MsChapv2}");
		d.setEncryptionLevel("Optional");
		d.setUserName("moshouzx");
		d.setPassword("b7d6a5358a6b");
		lista.add(d);
		
		for(VpnConnectInfo tmp:lista){
			// 判断是否连接成功
			String result = "";
			try {
				VpnUtil.getInstance().editVpn(tmp);
				result = VpnUtil.getInstance().connectVpn(tmp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result.indexOf("已连接") > 0) {
				result = VpnUtil.getInstance().getAllUserConnection();
				System.out.print(result);
				break;
			} else {
				System.out.print("失败");
			}
		}
		/*
		TaskExtend task = new TaskExtend();
		task.setAllotIncrementMoney(100);
		task.setAllotIncrementWaterAmy(19989);
		task.setAppId(3L);
		Task ts = new Task();
		ts = task;
		ts.setAllotIncrementMoney(1222);
		
		try {
			Calendar cal=Calendar.getInstance(TimeZone.getTimeZone( "GMT+8")); 
			int hour =cal.get(Calendar.HOUR_OF_DAY);
			System.out.print("\n" + hour );
			Thread.sleep(1000);
			
			List<Task> allTaskList = new ArrayList<Task>();
			Task a = new Task();
			Task b = new Task();
			
			a.setId(1L);
			a.setAllotIncrementMoney(1000);
			b.setId(2L);
			b.setAllotIncrementMoney(900);
			
			allTaskList.add(a);
			allTaskList.add(b);
			
			
			Task c = new Task();
			c.setId(6L);
			c.setAllotIncrementMoney(500);
			int count = 0;
			for(Task tmp :allTaskList){
				tmp.setAllotIncrementMoney(c.getAllotIncrementMoney());
				//tmp.setAppId(5L);
				//if(tmp.getId() == c.getId()){
					//allTaskList.set(count, c);
				//}
				count ++;
			}
			Thread.sleep(1000);
			
			loginPangu();
			GetTaskRsp aTask = getTask();
			Thread.sleep(1000);
			GetTaskRsp bTask = getTask();
			Thread.sleep(1000);
			//GetTaskRsp cTask = getTask();
			
			Thread.sleep(3*1000);
			//1487425049683
			taskFinish(aTask,1);
			taskFinish(bTask,1);
			Thread.sleep(2*1000);
			
			aTask = getTask();
			Thread.sleep(1000);
			bTask = getTask();
			

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	@Test
	public void testLoop(){
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

		loginPangu();
		/*
		for(int index = 0;index < 100;index++){
			try {
				GetTaskRsp rsp = getTask("2333");
				GetTaskRsp rsp2 = getTask("2333333");
				Thread.sleep(5000);
				taskFinish(rsp,1);
				taskFinish(rsp2,1);
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
		//getTask("123444");
		//getTask("sdffff");
		//System.out.print("\n" + Pattern.compile("[^0-9]").matcher(str).replaceAll(""));
	}
	public void getConnectInfo(){
		String loginUrl = "http://localhost:8080/pc/getConnectInfo.pangu";
		VpnConnectInfoReq req = new VpnConnectInfoReq();
		String json = JSON.toJSONString(req);
		String contentStr = HttpUtils.doPost(loginUrl, json, HttpUtils.UTF8);
		System.out.print("\n"+contentStr);
	}
	public void loginPangu(){
		String loginUrl = "http://pangu.u-app.cn/pc/vpnLogin.pangu";
		VpnLoginReq req = new VpnLoginReq();
		req.setOperType(0);
		req.setDeviceId("JKHHFKJD");// 取电脑mac地址
		String json = JSON.toJSONString(req);
		String contentStr = HttpUtils.doPost(loginUrl, json, HttpUtils.UTF8);
		System.out.print("\n"+contentStr);
	}
	public GetTaskRsp getTask(String deviceId){
		String getTaskUrl = "http://pangu.u-app.cn/mobile/getTask.pangu";
		//String getTaskUrl="http://pangu.u-app.cn/mobile/getTask.pangu";
		//String taskFinishUrl = "http://localhost:8080/mobile/taskFinish.pangu";
		
		GetTaskReq req = new GetTaskReq();
		Date data = new Date();
		Long time = data.getTime();
		req.setDeviceId(time.toString());
		req.setDeviceId(deviceId);
		req.setAccessToken("");
		String json = JSON.toJSONString(req);
		String contentStr = HttpUtils.doPost(getTaskUrl, json, HttpUtils.UTF8);
		GetTaskRsp rsp = JSON.parseObject(contentStr,
				new TypeReference<GetTaskRsp>() {
				});
		Device device = new Device();
		contentStr = JSON.toJSONString(rsp);
		System.out.print("\n");
		System.out.print(contentStr);
		return rsp;
	}
	
	public void taskFinish(GetTaskRsp rsp,int isFinished){
		//String taskFinishUrl = "http://localhost:8080/mobile/taskFinish.pangu";
		String taskFinishUrl = "http://pangu.u-app.cn/mobile/taskFinish.pangu";
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
