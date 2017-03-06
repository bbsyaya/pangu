package org.turing.pangu.iptrunk;
import org.turing.pangu.zzztest.HttpUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * Created by turingkuang on 2017/2/28.
 */
public class LocationMng {    
    //http://api.map.baidu.com/location/ip?ak=WjjGWy6piQ7XuOcjGCwx3slTErxhOr7g&coor=bd09ll
    public BaiduLocation getLocation(String ip){
        // 先取Ip ,再取经纬度
    	BaiduLocation location = null;
        String serverURL = "http://api.map.baidu.com/location/ip?ak=WjjGWy6piQ7XuOcjGCwx3slTErxhOr7g&ip="+ip+"&coor=bd09ll";
    	String result = HttpUtils.doGet(serverURL, HttpUtils.UTF8);// 建立http get联机
    	if(null == result || result.equals(""))
    		return location;
    	
    	location = JSON.parseObject(result,new TypeReference<BaiduLocation>(){
        });
        return location;
    }
}
