/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package org.turing.pangu.utils;

import java.io.IOException;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JsonUtils.class);

	private static ObjectMapper mapper = new ObjectMapper();

	private JsonUtils() {
	}

	public static String toJson(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	public static <T> T toObject(String json, Class<T> valueType) {
		Assert.hasText(json);
		Assert.notNull(valueType);
		try {
			return mapper.readValue(json, valueType);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	public static <T> T toObject(String json, TypeReference<?> typeReference) {
		Assert.hasText(json);
		Assert.notNull(typeReference);
		try {
			return mapper.readValue(json, typeReference);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	
	public static JsonNode toJsonNode(String json)
	{
		Assert.hasText(json);
		try {
			return mapper.readTree(json);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	

	public static <T> T toObject(String json, JavaType javaType) {
		Assert.hasText(json);
		Assert.notNull(javaType);
		try {
			return mapper.readValue(json, javaType);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	public static void writeValue(Writer writer, Object value) {
		try {
			mapper.writeValue(writer, value);
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage(),e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}

}