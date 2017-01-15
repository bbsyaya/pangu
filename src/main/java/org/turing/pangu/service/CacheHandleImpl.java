package org.turing.pangu.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service("cacheHandleImpl")
public class CacheHandleImpl implements CacheHandle {

	@Resource(name = "messageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@CacheEvict(value = { "setting" }, allEntries = true)
	public void clear() {
		reloadableResourceBundleMessageSource.clearCache();
	}

	@Override
	public Object getCache(String key) {
		if (!hasKey(key)) {
			return null;
		}
		ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
		Object value = valueOper.get(key); // 从缓存获取数据
		return value;
	}

	@Override
	public void setCache(String key, Object value, Long timeout) {
		setCache(key, value, null, null);
	}

	@Override
	public void setPermanentCache(String key, Object value) {
		setCache(key, value, null);

	}

	@Override
	public void setCache(String key, Object value, Long timeout, TimeUnit timeUnit) {
		ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
		if (hasKey(key)) {
			deleteCache(key);
		}
		if (timeout == null || timeout <= 0) { // 如果没有设置过期时间,则无限期缓存
			valueOper.set(key, (Object) value);
		} else { // 否则设置缓存时间
			valueOper.set(key, (Object) value, timeout, timeUnit);
		}
	}

	@Override
	public boolean hasKey(String key) {
		if (key == null || key.isEmpty()) {
			return false;
		}
		return redisTemplate.hasKey(key);
	}

	@Override
	public boolean deleteCache(String key) {
		boolean result = false;
		redisTemplate.delete(key);
		return result;
	}

	@Override
	public void refreshGroupKeys(String group, String key) {
		Set<String> result;
		if (hasKey(group)) {
			result = (Set<String>) getCache(group);
		} else {
			result = new HashSet<String>();
		}
		result.add(key);
		setPermanentCache(group, result);
	}

	@Override
	public void deleteGroup(String group) {
		if (!hasKey(group)) {
			return;
		}

		Set<String> keys = (Set<String>) getCache(group);

		for (String key : keys) {
			if (hasKey(key)) {
				deleteCache(key);
			}
		}
	}

	@Override
	public boolean validateAuthCode(String key, String authCode) {
		if (hasKey(key)) {
			String code = getCache(key).toString();
			if (code.equals(authCode)) {
				deleteCache(key);
				return true;
			} else {
				String keyCount = key + ".count";
				Integer count = 0;
				if (hasKey(keyCount)) {
					count = count + Integer.valueOf(getCache(keyCount).toString());
					setCache(keyCount, count, 60l);
				} else {
					setCache(keyCount, 1, 60l);
				}
				if (count == 3) {
					deleteCache(keyCount);
					deleteCache(key);
				}
				return false;
			}
		} else {
			return false;
		}
	}
}
