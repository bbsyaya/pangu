package org.turing.pangu.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.log4j.Logger;

public class CopyUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CopyUtils.class);

	public static void CopyParentToChildren(Object sourceObj, Object targetObj, Class<?> clazz) throws Exception {
		if (sourceObj == null || targetObj == null) {
			throw new Exception("源对象和目标对象不能为null");
		}

		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				if (Modifier.isFinal(fields[i].getModifiers())) {
					continue;
				}
				Object sourceValue = fields[i].get(sourceObj);
				fields[i].set(targetObj, sourceValue);
			} catch (SecurityException e) {
				logger.error(e.getMessage(), e);
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage(), e);
			}

		}
		if (clazz.getSuperclass() == Object.class) {
			return;
		}
	}

	/**
	 * 首字母大写，in:deleteDate，out:DeleteDate
	 */
	private static String upperHeadChar(String in) {
		String head = in.substring(0, 1);
		String out = head.toUpperCase() + in.substring(1, in.length());
		return out;
	}
}
