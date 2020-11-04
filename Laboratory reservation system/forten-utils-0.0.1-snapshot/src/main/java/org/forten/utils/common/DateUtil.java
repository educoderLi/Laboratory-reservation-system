/*
 * Copyright 2003-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.forten.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.forten.utils.system.Assert;
import org.forten.utils.system.LogUtil;

/**
 * 日期工具类
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-5-7
 */
public final class DateUtil {
	private static Logger log = Logger.getLogger(DateUtil.class);

	private static final String DATA_NOT_EMPTY_MSG = "日期不可为空";
	private static final String DATA_FORMAT_NOT_EMPTY_MSG = "日期格式字符串不可为空";
	private static final String DATA_STRING_NOT_EMPTY_MSG = "字符串日期不可为空";
	private static final String DATA_FORMAT_ERROR_MSG = "日期格式解析错误:日期格式为[%s]";

	// 私有构造器
	private DateUtil() {

	}

	/**
	 * 日期格式定义样式
	 */
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	/**
	 * 时间格式定义样式
	 */
	public static final String TIME_PATTERN = "HH:mm:ss";
	/**
	 * 日期时间格式定义样式
	 */
	public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 按格式定义样式把日期对象转成日期字符串
	 * 
	 * @param aDate
	 *            日期对象
	 * @param pattern
	 *            格式定义样式字符串
	 * @return 格式化后的日期字符串
	 */
	public static String convertDateToString(Date aDate, String pattern) {
		Assert.notNull(aDate, DATA_NOT_EMPTY_MSG);
		Assert.hasText(pattern, DATA_FORMAT_NOT_EMPTY_MSG);
		return new SimpleDateFormat(pattern).format(aDate);
	}

	/**
	 * 按默认格式定义样式把日期对象转成日期字符串
	 * 
	 * @param aDate
	 *            日期对象
	 * @return 按默认格式定义样式格式化后的日期字符串
	 */
	public static String convertDateToString(Date aDate) {
		return convertDateToString(aDate, DATETIME_PATTERN);
	}

	/**
	 * 按格式定义样式把日期字符串转成日期对象
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param pattern
	 *            格式定义样式字符串
	 * @return 日期对象
	 */
	public static Date convertStringToDate(String strDate, String pattern) {
		Assert.hasText(strDate, DATA_STRING_NOT_EMPTY_MSG);
		Assert.hasText(pattern, DATA_FORMAT_NOT_EMPTY_MSG);
		Date date = null;
		try {
			date = new SimpleDateFormat(pattern).parse(strDate);
		} catch (ParseException e) {
			LogUtil.error(log, e, DATA_FORMAT_ERROR_MSG, pattern);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * 按默认格式定义样式把日期字符串转成日期对象
	 * 
	 * @param strDate
	 *            日期字符串
	 * @return 日期对象
	 */
	public static Date convertStringToDate(String strDate) {
		return convertStringToDate(strDate, DATETIME_PATTERN);
	}

	/**
	 * 获得当前时间
	 * 
	 * @return 当前时间
	 */
	public static Date currentTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 获得指定时间的Date对象
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月 范围1-12
	 * @param date
	 *            日 以不同月份的天数为范围
	 * @param hourOfDay
	 *            小时 范围0-23
	 * @param minute
	 *            分钟 范围0-59
	 * @param second
	 *            秒 范围0-59
	 * @return 填写完成的Date对象
	 */
	public static Date getCommonTime(int year, int month, int date,
			int hourOfDay, int minute, int second) {
		Assert.isBetween(month, 1, 12);
		Assert.isBetween(hourOfDay, 0, 23);
		Assert.isBetween(minute, 0, 59);
		Assert.isBetween(second, 0, 59);
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			Assert.isBetween(date, 1, 31);
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			Assert.isBetween(date, 1, 30);
			break;
		case 2:
			if (isLeapYear(year)) {
				Assert.isBetween(date, 1, 29);
			} else {
				Assert.isBetween(date, 1, 28);
			}
			break;
		default:
			throw new IllegalArgumentException("非法的月分取值["+month+"]");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date, hourOfDay, minute, second);
		return calendar.getTime();
	}

	/**
	 * 获得指定时间的Date对象
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月 范围1-12
	 * @param date
	 *            日 以不同月份的天数为范围
	 * @return 填写完成的Date对象
	 */
	public static Date getCommonTime(int year, int month, int date) {
		return getCommonTime(year, month, date, 0, 0, 0);
	}

	/**
	 * 判断是否为闰年
	 * 
	 * @param year
	 *            年份
	 * @return 是否为闰年 可以被400整除的年份或能被4整除但不能被100整除的年份是闰年，返回<code>true</code>，否则返回
	 *         <code>false</code>
	 */
	public static boolean isLeapYear(int year) {
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	}

	/**
	 * 通过给出的日期计算出新的日期
	 * 
	 * @param date
	 *            基于的日期
	 * @param amount
	 *            计算的数量
	 * @param field
	 *            计算单位,使用java.util.Calendar类中的常量,如Calendar.DAY_OF_MONTH
	 * @return
	 */
	public static Date calculateDate(Date date, int amount, int field) {
		Assert.notNull(date, DATA_NOT_EMPTY_MSG);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}
}
