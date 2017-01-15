package org.turing.pangu.service;

import java.util.concurrent.TimeUnit;

public interface CacheHandle{

	/**
	 * 清除缓存
	 */
	void clear();

	Object getCache(String key);

	void setCache(String key, Object value, Long timeout);
	
	void setCache(String key, Object value, Long timeout,TimeUnit timeUnit);

	boolean hasKey(String key);

	boolean deleteCache(String key);

	void refreshGroupKeys(String group, String key);

	void deleteGroup(String group);
	
	boolean validateAuthCode(String key,String authCode);

	void setPermanentCache(String key, Object value);
}
