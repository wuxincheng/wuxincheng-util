package com.wuxincheng.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证
 * 
 * @author wuxincheng
 *
 */
public class Validation {
	public static boolean isNull(String str) {
		return str == null;
	}

	public static boolean isEmpty(String str) {
		return (str == null) || (str.length() <= 0);
	}

	public static boolean isBlank(String str) {
		return (isEmpty(str)) || (str.matches("^\\s*$"));
	}

	public static boolean isDigits(String str) {
		return (!isEmpty(str)) && (str.matches("^\\d+$"));
	}

	public static boolean isInt(String num, String type) {
		String pattern = "";
		if (("0+".equalsIgnoreCase(type)) || ("+0".equalsIgnoreCase(type)))
			pattern = "^((0)|(0*[1-9]\\d*))$";
		else if ("+".equalsIgnoreCase(type))
			pattern = "^0*[1-9]\\d*$";
		else if (("0-".equalsIgnoreCase(type)) || ("-0".equalsIgnoreCase(type)))
			pattern = "^((0)|(-0*[1-9]\\d*))$";
		else if ("-".equalsIgnoreCase(type))
			pattern = "^-0*[1-9]\\d*$";
		else
			pattern = "^((0)|(-?0*[1-9]\\d*))$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(num);
		boolean b = m.matches();
		return b;
	}

	public static boolean isInt(String str) {
		return isInt(str, "");
	}

	public static boolean isIntPositive(String str) {
		return isInt(str, "+");
	}

	public static boolean isFloat(String num, String type) {
		String pattern = "";
		if (("0+".equalsIgnoreCase(type)) || ("+0".equalsIgnoreCase(type)))
			pattern = "^\\d+(\\.\\d+)?$";
		else if ("+".equalsIgnoreCase(type))
			pattern = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";
		else if (("0-".equalsIgnoreCase(type)) || ("-0".equalsIgnoreCase(type)))
			pattern = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";
		else if ("-".equalsIgnoreCase(type))
			pattern = "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$";
		else
			pattern = "^(-?\\d+)(\\.\\d+)?$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(num);
		boolean b = m.matches();
		return b;
	}

	public static boolean isFloat(String str) {
		return isFloat(str, "");
	}

	public static boolean isFloatPositive(String str) {
		return isFloat(str, "+");
	}

	public static boolean isMoney(String str) {
		return (!isEmpty(str))
				&& (str.matches("^(([1-9]\\d*)|\\d+\\.\\d{1,2})$"));
	}

	public static boolean isNav(String str) {
		return (!isEmpty(str))
				&& (str.matches("^(([1-9]\\d{0,1})|\\d{1,2}\\.\\d{1,4})$"));
	}

	public static boolean isIndex(String str) {
		return (!isEmpty(str))
				&& (str.matches("^(([1-9]\\d{2,5})|\\d{3,6}\\.\\d{1,2})$"));
	}

	public static boolean isDate(String str) {
		return checkDate(str);
	}

	public static boolean isMatch(String str, String pattern) {
		return (!isEmpty(str)) && (str.matches(pattern));
	}

	public static boolean checkFundCode(String str) {
		return (!isEmpty(str)) && (str.matches("^\\d{6}$"));
	}

	public static boolean checkTradeAcco(String str) {
		return (!isEmpty(str)) && (str.length() <= 17);
	}

	public static boolean checkFundAcco(String str) {
		return (!isEmpty(str)) && (str.length() <= 12);
	}

	public static boolean checkBirthday(String str) {
		if (!checkDate(str)) {
			return false;
		}
		@SuppressWarnings("deprecation")
		int thisyear = new Date().getYear() + 1900;
		int age = thisyear - Integer.parseInt(str.substring(0, 4));
		if ((age < 0) || (age > 150)) {
			return false;
		}

		return true;
	}

	public static boolean checkDate(String str) {
		if ((isEmpty(str)) || (!str.matches("^\\d{8}$")))
			return false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			sdf.parse(str);
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	public static boolean checkEmail(String str) {
		return (!isEmpty(str))
				&& (str.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"));
	}

	public static boolean checkMobile(String str) {
		return (!isEmpty(str)) && (str.matches("^(1[3-8][0-9])\\d{8}$"));
	}

	public static boolean checkTradePassword(String str) {
		return (!isEmpty(str))
				&& (str.matches("^[^\\s\\u4E00-\\u9FA5]{6,20}$"));
	}

	public static boolean checkName(String str) {
		return (!isEmpty(str)) && (str.matches("^\\S{2,30}$"));
	}

	public static boolean checkVerifyCode(String str) {
		return (!isEmpty(str)) && (str.matches("^[0-9]{1,4}$"));
	}

	public static boolean checkAddress(String str) {
		return (!isEmpty(str)) && (str.matches("^\\S{5,30}$"));
	}

	public static boolean checkPostCode(String str) {
		return (!isEmpty(str)) && (str.matches("^[0-9]{6}$"));
	}

	public static boolean checkBankAcctNo(String str) {
		return (!isEmpty(str)) && (str.matches("^[0-9]{8,20}$"));
	}

	public static boolean checkCertType(String str) {
		return (!isEmpty(str)) && (str.matches("^[0123589]$"));
	}

	public static boolean checkCertNo(String str) {
		return (!isEmpty(str)) && (str.matches("^\\S{3,20}$"));
	}

	public static String checkIdcardNo(String IDStr) {
		String errorInfo = "";
		String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		HashMap<String, String> areaCode = new HashMap<String, String>();
		areaCode.put("11", "北京");
		areaCode.put("12", "天津");
		areaCode.put("13", "河北");
		areaCode.put("14", "山西");
		areaCode.put("15", "内蒙古");
		areaCode.put("21", "辽宁");
		areaCode.put("22", "吉林");
		areaCode.put("23", "黑龙江");
		areaCode.put("31", "上海");
		areaCode.put("32", "江苏");
		areaCode.put("33", "浙江");
		areaCode.put("34", "安徽");
		areaCode.put("35", "福建");
		areaCode.put("36", "江西");
		areaCode.put("37", "山东");
		areaCode.put("41", "河南");
		areaCode.put("42", "湖北");
		areaCode.put("43", "湖南");
		areaCode.put("44", "广东");
		areaCode.put("45", "广西");
		areaCode.put("46", "海南");
		areaCode.put("50", "重庆");
		areaCode.put("51", "四川");
		areaCode.put("52", "贵州");
		areaCode.put("53", "云南");
		areaCode.put("54", "西藏");
		areaCode.put("61", "陕西");
		areaCode.put("62", "甘肃");
		areaCode.put("63", "青海");
		areaCode.put("64", "宁夏");
		areaCode.put("65", "新疆");
		areaCode.put("71", "台湾");
		areaCode.put("81", "香港");
		areaCode.put("82", "澳门");
		areaCode.put("91", "国外");

		String Ai = "";

		if ((IDStr.length() != 15) && (IDStr.length() != 18)) {
			errorInfo = "身份证号码长度应该为15位或18位。";
			return errorInfo;
		}

		if (IDStr.length() == 18)
			Ai = IDStr.substring(0, 17);
		else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (Ai.matches("^\\d$")) {
			errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			return errorInfo;
		}

		String strYear = Ai.substring(6, 10);
		String strMonth = Ai.substring(10, 12);
		String strDay = Ai.substring(12, 14);
		String strBirthday = strYear + strMonth + strDay;
		if (!checkBirthday(strBirthday)) {
			errorInfo = "身份证生日无效。";
			return errorInfo;
		}

		if (areaCode.get(Ai.substring(0, 2)) == null) {
			errorInfo = "身份证地区编码错误。";
			return errorInfo;
		}

		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi += Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (IDStr.length() == 18) {
			if (!Ai.equalsIgnoreCase(IDStr)) {
				errorInfo = "身份证无效，不是合法的身份证号码";
				return errorInfo;
			}
		} else
			return null;

		return null;
	}

	public static boolean checkMinor(String birthday) {
		@SuppressWarnings("deprecation")
		int thisyear = new Date().getYear() + 1900;
		int age = 0;
		try {
			age = thisyear - Integer.parseInt(birthday.substring(0, 4));
		} catch (Exception ex) {
			age = 0;
		}

		if (age < 18) {
			return true;
		}
		return false;
	}

	public static boolean checkCertExpire(String str) {
		if (!checkDate(str)) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		if (str.compareTo(today) <= 0) {
			return false;
		}

		return true;
	}

	public static String checkCertExpireAge(String certtype, String certno,
			String certexpire, String birthday) {
		new SimpleDateFormat("yyyyMMdd").format(new Date());
		// String currentdate = SqlUtil.getFormatDate(new Date().getTime(), "yyyyMMdd");
		String currentdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		if ((certexpire == null) || (certexpire.length() <= 0)) {
			return "证件有效期必须填写";
		}

		int oyear = Integer.parseInt(certexpire.substring(0, 4), 10);
		int omonth = Integer.parseInt(certexpire.substring(4, 6));
		int oday = Integer.parseInt(certexpire.substring(6, 8));

		int year = Integer.parseInt(currentdate.substring(0, 4));
		int month = Integer.parseInt(currentdate.substring(4, 6));
		int day = Integer.parseInt(currentdate.substring(6, 8));

		if (Integer.parseInt(certexpire) < Integer.parseInt(currentdate)) {
			return "证件有效期必须大于当前日期";
		}

		int age = 0;
		int oage = oyear - year;
		if (omonth == month) {
			if (oday > day)
				oage++;
		} else if (omonth > month) {
			oage++;
		}

		String certbirthday = "";

		if ((birthday == null) || (birthday.length() == 0)
				|| (birthday.length() != 8)) {
			birthday = certbirthday;
		}

		if ((birthday != null) && (birthday.length() == 8)) {
			int y = Integer.parseInt(birthday.substring(0, 4));
			int m = Integer.parseInt(birthday.substring(4, 6));
			int d = Integer.parseInt(birthday.substring(6, 8));
			age = year - y;
			if (month == m) {
				if (day > d)
					age++;
			} else if (month > m) {
				age++;
			}
		}

		if ((certexpire.equals("20990101")) && (age > 0) && (age < 46)) {
			if (age < 16)
				return "您未满16周岁,证件有效期最大为5年";
			if ((age >= 16) && (age < 26))
				return "您未满26周岁,证件有效期最大为10年";
			if ((age >= 26) && (age < 46))
				return "您未满46周岁,证件有效期最大为20年";
			if (age < 46) {
				return "证件有效期过大";
			}
		}
		if ((certexpire.equals("20990101")) && (age >= 46)) {
			return null;
		}
		if ((age > 0) && (age < 16) && (oage > 5)) {
			return "您未满16周岁,证件有效期最大为5年";
		}
		if ((age >= 16) && (age < 26) && (oage > 10)) {
			return "您未满26周岁,证件有效期最大为10年";
		}
		if ((age >= 26) && (age < 46) && (oage > 20)) {
			return "您未满46周岁,证件有效期最大为20年";
		}
		if ((age >= 46) && (oage > 100)) {
			return "证件有效期过大";
		}
		return null;
	}

	public static boolean checkProvinceCode(String str) {
		return (!isEmpty(str)) && (str.matches("^\\d{2}$"));
	}

	public static boolean checkCityCode(String str) {
		return (!isEmpty(str)) && (str.matches("^\\d{4}$"));
	}

	public static boolean checkNationality(String str) {
		return (!isEmpty(str)) && (str.matches("^\\d{3}$"));
	}

	public static boolean checkVocation(String str) {
		return (!isEmpty(str)) && (str.matches("^\\d{2}$"));
	}

	public static boolean checkSex(String str) {
		return (!isEmpty(str)) && (str.matches("^[01]$"));
	}

	public static boolean checkTelNo1(String str) {
		return (!isEmpty(str)) && (str.matches("^\\d{2,5}$"));
	}

	public static boolean checkTelNo2(String str) {
		return (!isEmpty(str)) && (str.matches("^\\d{4,10}$"));
	}

	public static boolean checkMarriage(String str) {
		return (!isEmpty(str)) && (str.matches("^[01]$"));
	}

	public static boolean checkSalary(String str) {
		return (!isEmpty(str)) && (str.matches("^[123]$"));
	}

	public static boolean checkYesNo(String str) {
		return (!isEmpty(str)) && (str.matches("^[01]$"));
	}

	public static boolean checkEducation(String str) {
		return (!isEmpty(str)) && (str.matches("^\\d{2}$"));
	}

	public static boolean checkDeliveryType(String str) {
		return (!isEmpty(str)) && (str.matches("^[13]$"));
	}
}
