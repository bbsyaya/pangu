package org.turing.pangu.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * Plugin - 本地文件存储
 * 
 */
@Component("fileUploadUtils")
public class FileUploadUtils implements ServletContextAware {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FileUploadUtils.class);

	/** servletContext */
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	// 上传图片
	public String upload(String path, File file, String contentType) {
		File destFile = new File(servletContext.getRealPath(path));
		try {
			FileUtils.moveFile(file, destFile);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return path;
	}

	public String changeFileNameAndMoveFile(String prefix, String filePath, String movePath) {
		File file = new File(servletContext.getRealPath("/") + filePath);
		String newFileName = "";
		if (file.exists()) {
			if (!file.isDirectory()) {
				newFileName=movePath+ prefix + file.getName();
				File destFile = new File(servletContext.getRealPath(newFileName));
				try {
					FileUtils.moveFile(file, destFile);
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return newFileName;
	}
}