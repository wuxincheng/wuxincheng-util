package com.wuxincheng.util;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

/**
 * ConfigHelper读取配置文件
 * 
 * @author wuxincheng
 *
 */
public class ConfigHelper {
	private static ConfigHelper config;
	private Properties p;
	private Map<String, Properties> configs = new Hashtable<String, Properties>();

	private ConfigHelper() {
		InputStream in = null;
		try {
			in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("config.properties");
			this.p = new Properties();
			this.p.load(in);
			in.close();
		} catch (Exception localException) {
		}
	}

	public static ConfigHelper getInstance() {
		if (config == null) {
			config = new ConfigHelper();
		}
		return config;
	}

	public String getConfig(String key) {
		return getGbk(this.p.getProperty(key));
	}

	public String getConfig(String key, String filename) {
		try {
			Properties p1 = (Properties) this.configs.get(filename);
			if (p1 == null) {
				InputStream in = null;
				try {
					in = Thread.currentThread().getContextClassLoader()
							.getResourceAsStream(filename);
					p1 = new Properties();
					p1.load(in);
					this.configs.put(filename, p1);
				} catch (Exception ex) {
					if (in != null)
						try {
							in.close();
						} catch (Exception localException1) {
						}
				} finally {
					if (in != null)
						try {
							in.close();
						} catch (Exception localException2) {
						}
				}
				this.configs.put(filename, p1);
			}
			return getGbk(p1.getProperty(key));
		} catch (Exception ex) {
		}
		return null;
	}

	public Properties getProperties(String filename) {
		if (filename == null)
			return this.p;
		return (Properties) this.configs.get(filename);
	}

	private String getGbk(String msg) {
		if (msg == null)
			return null;
		try {
			return new String(msg.getBytes("ISO8859_1"), "GBK");
		} catch (Exception ex) {
		}
		return msg;
	}

	public static void main(String[] args) {
		System.out.println(getInstance().getConfig("mail.host"));
	}
	
}
