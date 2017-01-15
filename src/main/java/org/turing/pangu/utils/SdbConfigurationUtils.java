package org.turing.pangu.utils;

import java.util.ResourceBundle;

public class SdbConfigurationUtils {

	private static Object lock = new Object();
	private static SdbConfigurationUtils config = null;
	private static ResourceBundle rb = null;
	private static final String CONFIG_FILE = "sdb";

	private SdbConfigurationUtils() {
		rb = ResourceBundle.getBundle(CONFIG_FILE);
	}

	public static SdbConfigurationUtils getInstance() {
		synchronized (lock) {
			if (null == config) {
				config = new SdbConfigurationUtils();
			}
		}
		return (config);
	}

	public String getValue(String key) {
		return (rb.getString(key));
	}
}