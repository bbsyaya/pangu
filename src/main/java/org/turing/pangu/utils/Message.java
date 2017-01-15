package org.turing.pangu.utils;


/**
 * 消息
 * 
 * @author sylar
 */
public class Message {
	/** 内容 */
	private String content;

	/** 状态 */
	private int Status;

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	/** 数据 */
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Message(int status, String string) {
		generateMessage(status, content, null, false, null, null);
	}

	public Message(int status, String content, Object data) {
		generateMessage(status, content, data, false, null, null);
	}

	public Message(int status, String content, Object data, boolean isSourceContent, Object... args) {
		generateMessage(status, content, data, isSourceContent, args);
	}

	private void generateMessage(int status, String content, Object data, boolean isSourceContent, Object... args) {
		this.Status = status;
		this.data = data;
		this.content = isSourceContent ? content : SpringUtils.getMessage(content, args);
	}

	/**
	 * 返回成功消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 成功消息
	 */
	public static Message success(Object data, Object... args) {
		return new Message(Const.common_ok, "common_ok", data, false, args);
	}

	/**
	 * 返回成功消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 成功消息
	 */
	public static Message success(String content, Object... args) {
		return new Message(Const.common_ok, content, null, false, args);
	}

	/**
	 * 返回成功消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 成功消息
	 */
	public static Message success(Object... args) {
		return new Message(Const.common_ok, "common_ok", null, false, args);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static Message warn(Object... args) {
		return new Message(Const.common_warn, "common_warn", null, false, args);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static Message error(String content) {
		return new Message(Const.common_error, content, null, false, null, null);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static Message error(Object... args) {
		return new Message(Const.common_error, "common_error", null, false, args);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static Message unknown(Object... args) {
		return new Message(Const.common_error, "common_error", null, false, args);
	}

	/**
	 * 返回特定错误消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static Message specificError(int status, String content, Object data, Object... args) {
		return new Message(status, content, data, false, args);
	}
	
	/**
	 * 返回特定错误消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static Message specificError(int status, String content,boolean isSourceContent, Object data, Object... args) {
		return new Message(status, content, data, isSourceContent, args);
	}

	/**
	 * 返回特定错误消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static Message specificError(int status, String content) {
		return new Message(status, content, null, false, null, null);
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return this.content;
	}

}