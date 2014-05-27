package com.wuxincheng.util;

import java.io.File;

/**
 * 文件工具类
 *
 * @author	 wuxincheng(wxcking)
 * @version	 V1.0  
 * @Date	 2013年11月26日 上午10:55:18
 */
public class FileUtil {
	
	/**
	 * 创建文件夹
	 * 
	 * @param filePath
	 */
	public static void createOrCheckFilePathExist(String filePath){
		if (filePath != null && !"".equals(filePath)) {
			File checkFileTemp = new File(filePath);
			if (!checkFileTemp.exists()) {
				// 创建文件夹, 如：在f盘创建/TXT文件夹/testTXT/两个文件夹
				checkFileTemp.mkdirs();
			} else {
				
			}
		}
	}
	
	/**
	 * 根据文件路径和文件名称判断文件是否存在
	 * 
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @return
	 */
	public static Boolean isExist(String filePath, String fileName){
		Boolean flag = false;
		
		if (filePath != null && !"".equals(filePath) && fileName != null && !"".equals(fileName)) {
			filePath = completeDirectory(filePath);
			
			File file = new File(filePath + fileName);
			
			flag = file.exists();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 自动为文件目录添加最后的/
	 * @param srcPath	原目录名
	 * @return
	 */
	public static String completeDirectory(String srcPath) {
		srcPath = srcPath.replaceAll("\\*", "/");
		if(!srcPath.endsWith("/")) {
			srcPath += "/";
		}
		return srcPath;
	}
	
}
