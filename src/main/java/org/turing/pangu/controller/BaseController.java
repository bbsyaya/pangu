package org.turing.pangu.controller;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class BaseController {

	@Resource(name = "validator")
	private Validator validator;

	/** "验证结果"参数名称 */
	private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

	private static final String CODE = "code";
	private static final String INFO = "info";

	/**
	 * 数据验证
	 * 
	 * @param target
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Object target, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = getValidResult(target, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

	/**
	 * 数据验证
	 * 
	 * @param target
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected Set<ConstraintViolation<Object>> getValidResult(Object target, Class<?>... groups) {
		return getValidResult(target, null, groups);
	}

	/**
	 * 数据验证
	 * 
	 * @param target
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected Set<ConstraintViolation<Object>> getValidResult(Object target, String[] properties, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = new HashSet<ConstraintViolation<Object>>();
		if (properties != null && properties.length > 0) {
			for (String property : properties) {
				constraintViolations.addAll(validator.validateProperty(target, property, groups));
			}
		} else {
			constraintViolations = validator.validate(target, groups);
		}
		return constraintViolations;
	}

	/*
	 * protected List<Message> getValidMessageList(Object target,Class<?>... groups) { return getValidMessageList( target,null,groups); }
	 */

	// protected List<Message> getValidMessageList(Object target,String[] properties, Class<?>... groups)
	// {
	// List<Message> messageList = new ArrayList<Message>();
	// Set<ConstraintViolation<Object>> constraintViolations=getValidResult(target,properties, groups);
	// if (!constraintViolations.isEmpty()) {
	// for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
	// String modelName=target.getClass().getSimpleName();
	// String propertyName=constraintViolation.getPropertyPath().toString();
	// String annotationName = constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
	// StringBuffer annotationCodeBuffer =new StringBuffer();
	// annotationCodeBuffer.append(".").append(modelName);
	// annotationCodeBuffer.append(".").append(propertyName);
	// annotationCodeBuffer.append(".").append(annotationName);
	//
	// String annotationCode=CODE+annotationCodeBuffer.toString();
	// String annotationMessage=INFO+annotationCodeBuffer.toString();
	// Message message=Message.annotation(annotationCode,annotationMessage);
	// messageList.add(message);
	// }
	// }
	// return messageList;
	// }
}
