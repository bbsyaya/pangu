package org.turing.pangu.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import org.turing.pangu.model.BaseModel;

public class MessageUtils {

	private static final Logger logger = Logger.getLogger(MessageUtils.class);

	public static String getContent(List list, String key) {
		String message = SpringUtils.getMessage(key);
		// 获取验证码
		try {
			for (Object object : list) {
				Class c;
				c = object.getClass();
				if (!c.getSuperclass().equals(BaseModel.class)) {
					Field[] fs = c.getDeclaredFields();
					Field[] superFs = c.getSuperclass().getDeclaredFields();
					List<Field[]> fsList = new ArrayList();
					fsList.add(fs);
					fsList.add(superFs);
					message = replaceContent(message, fsList, object);
				} else {
					Field[] fs = c.getDeclaredFields();
					List<Field[]> fsList = new ArrayList();
					fsList.add(fs);
					message = replaceContent(message, fsList, object);
				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		return message;
	}

	private static String replaceContent(String message, List<Field[]> list, Object object) throws IllegalArgumentException, IllegalAccessException {
		for (Field[] fs : list) {
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true);

				if (message.indexOf(f.getName()) != -1 && f.get(object) != null) {
					String value = f.get(object).toString();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					if (f.getType().equals(Date.class)) {
						value = format.format(f.get(object));
					}
					if (f.getType().equals(BigDecimal.class)) {
						BigDecimal money = new BigDecimal(value);
						money = DecimalUtil.getDecimalDown(money);
						value = money.toString();

					}
					message = message.replace("{" + f.getName() + "}", value);
				}
			}
		}
		return message;
	}
}
