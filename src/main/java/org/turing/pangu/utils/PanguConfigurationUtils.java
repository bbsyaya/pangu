package org.turing.pangu.utils;

import java.util.ResourceBundle;

public class PanguConfigurationUtils {

	private static Object lock = new Object();
	private static PanguConfigurationUtils config = null;
	private static ResourceBundle rb = null;
	private static final String CONFIG_FILE = "pangu";

	private PanguConfigurationUtils() {
		rb = ResourceBundle.getBundle(CONFIG_FILE);
	}

	public static PanguConfigurationUtils getInstance() {
		synchronized (lock) {
			if (null == config) {
				config = new PanguConfigurationUtils();
			}
		}
		return (config);
	}

	public String getValue(String key) {
		return (rb.getString(key));
	}
}