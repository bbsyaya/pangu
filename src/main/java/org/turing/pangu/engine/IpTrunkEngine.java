package org.turing.pangu.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.turing.pangu.iptrunk.BaiduLocation;
import org.turing.pangu.iptrunk.LocationMng;
import org.turing.pangu.model.Device;
import org.turing.pangu.model.IpTrunk;
import org.turing.pangu.service.BaseService;
import org.turing.pangu.service.IpTrunkService;
import org.turing.pangu.service.IpTrunkServiceImpl;
import org.turing.pangu.task.IpQueryResult;
import org.turing.pangu.task.StockTask;
import org.turing.pangu.utils.RandomUtils;

import com.alibaba.fastjson.JSON;

public class IpTrunkEngine implements EngineListen{
	private static final Logger logger = Logger.getLogger(IpTrunkEngine.class);
	private static IpTrunkEngine mInstance = new IpTrunkEngine();
	public List<IpTrunk> ipTrunkList = new ArrayList<IpTrunk>();
	private IpTrunkService ipTrunkService;
	private int QUERY_COUNT = 5;
	public static IpTrunkEngine getInstance()
	{
		if(null == mInstance)
			mInstance = new IpTrunkEngine();
		return mInstance;
	}
	private boolean isExistInList(List<StockTask> list ,Device device){
		for(StockTask deviceTmp :list){
			if(deviceTmp.getDevice().getAppId() == device.getAppId()){
				return true;
			}
		}
		return false;
	}
	public void saveIpInfoToDb(String ip){
		IpTrunk ipTrunk = new IpTrunk();
		ipTrunk.setIp(ip);
		List<IpTrunk> list = ipTrunkService.selectList(ipTrunk);
		if(list == null || list.size() == 0){
			LocationMng mng = new LocationMng();
			BaiduLocation location = mng.getLocation(ip);
			if(null == location ){
				return;
			}
			ipTrunk.setIp(ip);
			ipTrunk.setAllocCount(0);
			ipTrunk.setSuccessCount(0);
			ipTrunk.setCityCode(Integer.parseInt(location.getContent().getAddress_detail().getCityCode()));
			ipTrunk.setCreateDate(new Date());
			ipTrunk.setUpdateDate(new Date());
			ipTrunk.setIsVaild(1);
			ipTrunk.setAddress(location.getContent().getAddress());
			ipTrunk.setConfigure(JSON.toJSONString(location).toString());
			ipTrunkService.insert(ipTrunk);
		}else
		{
			ipTrunk = list.get(0);
			ipTrunk.setAllocCount(ipTrunk.getAllocCount() + 1);
			ipTrunk.setSuccessCount(ipTrunk.getSuccessCount()+1);
			ipTrunk.setUpdateDate(new Date());
			ipTrunkService.update(ipTrunk);
		}
	}
	// 获得每个应用的留存信息,每个应用都有对应的一个留存
	public IpQueryResult getStockInfoList(String ip){
		IpQueryResult result = new IpQueryResult();
		List<StockTask> stockDeviceList = new ArrayList<StockTask>();
		// 先查一样的IP存不存在
		List<Device> sameIplist = DeviceEngine.getInstance().selectLastMonthExcludeTodayByIp(ip);// 通过IP找到记录
		for(Device dev :sameIplist){
			if(true == AppEngine.getInstance().isActiveApp(dev.getAppId()) 
					&& false == isExistInList(stockDeviceList,dev)){
				StockTask tmpStock = new StockTask();
				tmpStock.setDevice(dev);
				stockDeviceList.add(tmpStock);
			}
			// 每个应用都有留存,退出!
			if( stockDeviceList.size() == AppEngine.getInstance().getAppList().size()){
				return result;
			}
		}
		// 通过ip 得到 城市 code
		LocationMng mng = new LocationMng();
		BaiduLocation location = mng.getLocation(ip);
		if( null == location || location.getStatus().equals("1"))
			return result;
		
		IpTrunk ipTrunk = new IpTrunk();
		ipTrunk.setCityCode(Integer.parseInt(location.getContent().getAddress_detail().getCityCode())); // 城市code 一致就OK 
		// 通过城市code 反查 有哪些 ip
		List<IpTrunk> listFromDB = ipTrunkService.selectList(ipTrunk); // 取100条这个城市的IP
		if(null == listFromDB || listFromDB.size() == 0 )
			return result;
		int loopTimes = listFromDB.size()>QUERY_COUNT?QUERY_COUNT:listFromDB.size();
		for(int index = 0; index < loopTimes; index++ ){
			int random = 0;
			if(loopTimes < QUERY_COUNT){
				random = index;
			}else{
				random = RandomUtils.getRandom(0, listFromDB.size());
			}
			IpTrunk tmpIp = listFromDB.get(random);
			List<Device> cityIpList = DeviceEngine.getInstance().selectLastMonthExcludeTodayByIp(ip);// 通过同城IP找到记录
			for(Device dev :cityIpList){
				if(true == AppEngine.getInstance().isActiveApp(dev.getAppId()) 
						&& false == isExistInList(stockDeviceList,dev)){
					StockTask tmpStock = new StockTask();
					tmpStock.setDevice(dev);
					stockDeviceList.add(tmpStock);
				}
			}
			// 每个应用都有留存,退出!
			if( stockDeviceList.size() == AppEngine.getInstance().getAppList().size()){
				break;
			}
		}
		// 通过ip 查 对应的记录
		result.setStockList(stockDeviceList);
		result.setLocation(location);
		return result;
	}
	@Override
	public void setService(List<BaseService> serviceList) {
		// TODO Auto-generated method stub
		for(BaseService service :serviceList){
			if(service instanceof IpTrunkServiceImpl ){
				this.ipTrunkService = (IpTrunkService)service;
			}
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(null != ipTrunkService){
		}
	}


	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void upDate() {
		// TODO Auto-generated method stub
		
	}



}
