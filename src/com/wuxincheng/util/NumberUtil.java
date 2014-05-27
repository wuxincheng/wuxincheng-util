package com.wuxincheng.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数值操作工具类
 * 
 * @author wuxincheng(wxcking)
 *
 */
public class NumberUtil {
	
	public static void main(String[] args) {
		BigDecimal bg = new BigDecimal("12.03");
		
		System.out.println(bg);
	}
	
	/**
	 * 从字符串中提取数值字符串
	 * 
	 * @param numberStr
	 * @return
	 */
	public static String extractNumberStr(String numberStr){
		if (numberStr != null && !"".equals(numberStr)) {
			// (?<!\\d)\\D里面 \\D表示非数字
			// (?<!\\d)表示去除数字后面的情况(去除所有非数字内容, 除了数字之间的那个.号)
			// 把数字前面的非数字部分都替换成""即替换成空
			numberStr = numberStr.replaceAll("(?<!\\d)\\D", "");
		}
	    return numberStr;
	}
	
	/**
	 * 从字符串中提取数值
	 * 
	 * @param numberStr
	 * @return
	 */
	public static Integer formatStrToInteger(String numberStr){
		// 默认为0
		Integer strTemp = null; 
		
		if (numberStr != null && !"".equals(numberStr)) {
			// (?<!\\d)\\D里面 \\D表示非数字
			// (?<!\\d)表示去除数字后面的情况(去除所有非数字内容, 除了数字之间的那个.号)
			// 把数字前面的非数字部分都替换成""即替换成空
			numberStr = numberStr.replaceAll("(?<!\\d)\\D", "");
		    strTemp = Integer.parseInt(numberStr);
		}
		
	    return strTemp;
	}
	
	/**
	 * 从字符串中提取数值
	 * 
	 * @param numberStr
	 * @return
	 */
	public static BigDecimal formatStrToBigDecimal(String numberStr){
		// 默认为0
		BigDecimal strTemp = null; 
		
		if (numberStr != null && !"".equals(numberStr)) {
			// (?<!\\d)\\D里面 \\D表示非数字
			// (?<!\\d)表示去除数字后面的情况(去除所有非数字内容, 除了数字之间的那个.号)
			// 把数字前面的非数字部分都替换成""即替换成空
			numberStr = numberStr.replaceAll("(?<!\\d)\\D", "");
			Double doubleValue = Double.parseDouble(numberStr);
		    strTemp = BigDecimal.valueOf(doubleValue);
		}
		
	    return strTemp;
	}

	/**
	 * 从字符串中提取数值
	 * 
	 * @param numberStr
	 * @return
	 */
	public static Double formatStrToDouble(String numberStr){
		// 默认为0
		Double strTemp = null; 
		
		if (numberStr != null && !"".equals(numberStr)) {
			// (?<!\\d)\\D里面 \\D表示非数字
			// (?<!\\d)表示去除数字后面的情况(去除所有非数字内容, 除了数字之间的那个.号)
			// 把数字前面的非数字部分都替换成""即替换成空
			numberStr = numberStr.replaceAll("(?<!\\d)\\D", "");
		    strTemp = Double.parseDouble(numberStr);
		}
		
	    return strTemp;
	}
	
	/**
	 * 从字符串中提取数值
	 * 
	 * @param numberStr
	 * @return
	 */
	public static String formatStrToNumberStr(String numberStr){
		// 默认为0
		String strTemp; 
		
		if (numberStr != null && !"".equals(numberStr)) {
			// (?<!\\d)\\D里面 \\D表示非数字
			// (?<!\\d)表示去除数字后面的情况(去除所有非数字内容, 除了数字之间的那个.号)
			// 把数字前面的非数字部分都替换成""即替换成空
			strTemp = numberStr.replaceAll("(?<!\\d)\\D", "");
		} else {
			strTemp = "";
		}
		
	    return strTemp;
	}
	
	/**
	 * 以千分位格式化数字, 一般在报表中使用
	 * 
	 * @param number
	 * @return
	 */
	public static String formatNumberForReport(Double number){
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("###,###,###,##0.00"); // 格式化样式
		return df.format(number);
	}

	/**
	 * 格式化数字
	 * 
	 * @param number
	 * @param format
	 * @return
	 */
	public static String formatNumber(Integer number, String format){
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		return df.format(number);
	}
	
	/**
	 * 验证必须为数字（整数或小数）
	 * 
	 * @return
	 */
	public static boolean validateNumber(String rate){
		String pattern = "[0-9]+(.[0-9]+)?";
        Pattern p = Pattern.compile(pattern);  
        Matcher m = p.matcher(rate);  
        boolean flag = m.matches();
        return flag;
	}
	
	/**
	 * 判断数字是否为null
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Number number) {
		return isEmpty(number);
	}

	/**
	 * 判断数字不是否为null
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Number number) {
		return !isEmpty(number);
	}

	/**
	 * 判断两个数是否相等,如果其中一个为null的话都会返回false
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean equals(Number first, Number second) {
		if (isEmpty(first))
			return false;
		if (isEmpty(second))
			return false;
		return first.equals(second);
	}

	/**
	 * 默认保留两位小数
	 * 
	 * @param Float
	 *            value
	 * @return
	 */
	public static Float toFloat(Float value) {
		return toFloat(value, 2);
	}

	/**
	 * 保留point前的小数位
	 * 
	 * @param value
	 * @param point
	 * @return
	 */
	public static Float toFloat(Float value, int point) {
		NumberFormat formater = NumberFormat.getInstance();
		formater.setMaximumFractionDigits(2);
		formater.setMinimumFractionDigits(2);

		try {
			return formater.parse(formater.format(value)).floatValue();
		} catch (ParseException e) {
			e.printStackTrace();
			return value;
		}
	}

	public static String setLong(int value, int max, int min) {
		// 得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(max);
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(min);

		return nf.format(value);
	}
	
	public static Double formatValue(Object value){
		if (value != null && !"".equals(value)) {
			String ss = value.toString().replaceAll(" ", "").replaceAll(",", "");
			return Double.parseDouble(ss);
		} else {
			return 0d;
		}
	}
	
}
