package org.turing.pangu.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DateConverter.class);

	private static final List<String> formarts = new ArrayList<String>(4);

	static {
		formarts.add("yyyy-MM");
		formarts.add("yyyy-MM-dd");
		formarts.add("yyyy-MM-dd HH:mm");
		formarts.add("yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public Date convert(String date) {
		
		String value = date.trim();
		if ("".equals(value)) {
			return null;
		}
		if(date.matches("^\\d{4}-\\d{1,2}$")){ 
			return parseDate(date, formarts.get(0));
		}else if(date.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
			return parseDate(date, formarts.get(1));
		}else if(date.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
			return parseDate(date, formarts.get(2));
		}else if(date.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
			return parseDate(date, formarts.get(3));
		}else {
			throw new IllegalArgumentException("Invalid boolean value '" + date + "'");
		}
		
		
	}
	
	public  Date parseDate(String dateStr, String format) {
		Date date=null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			date = (Date) dateFormat.parse(dateStr);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}

}
