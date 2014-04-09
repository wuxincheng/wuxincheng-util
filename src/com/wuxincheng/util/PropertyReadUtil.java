package com.wuxincheng.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 读取配置文件工具类
 * 		如: parameters.properties
 * 
 * @author wuxincheng
 *
 */
public class PropertyReadUtil {

	/** 资源属性文件properties */
	private ResourceBundle bundle;

	/**
	 * 初始化时读取资源属性文件
	 * @param FileName 文件名称(不包括后缀.properties)
	 */
	public PropertyReadUtil(String FileName) {
		bundle = ResourceBundle.getBundle(FileName);
	}

	/**
	 * 根据Key获取值
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		if (key == null || key.equals("") || key.equals("null")) {
			return "";
		}
		String result = "";
		try {
			result = bundle.getString(key);
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
