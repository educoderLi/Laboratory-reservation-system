/*
 * Copyright 2003-2012 the original author or authors.
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

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * 日期工具类单元测试
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 1.0
 */
public class DateUtilTest {
	@Test
	public void testIsLeapYear() throws Exception {
		assertEquals(true, DateUtil.isLeapYear(1904));
		assertEquals(false, DateUtil.isLeapYear(1900));
		assertEquals(true, DateUtil.isLeapYear(2000));
		assertEquals(false, DateUtil.isLeapYear(1905));
		assertEquals(true, DateUtil.isLeapYear(2000));
		assertEquals(false, DateUtil.isLeapYear(1905));
	}

	@Test
	public void testConvertDateToString() throws Exception {
		Date date = DateUtil.getCommonTime(2012, 1, 2, 15, 22, 30);
		String str = DateUtil.convertDateToString(date);
		assertEquals("2012-01-02 15:22:30", str);
	}

	@Test
	public void testConvertStringToDate() throws Exception {
		String str = "2012-01-02 15:22:30";
		Date date1 = DateUtil.convertStringToDate(str);
		Date date2 = DateUtil.getCommonTime(2012, 1, 2, 15, 22, 30);
		// 整除10000再乘10000是为了消除两个日期对象对应long值的误差
		assertEquals(date1.getTime(), date2.getTime() / 10000 * 10000);
	}

	@Test
	public void testGetCommonTime() throws Exception {
		Date date = DateUtil.getCommonTime(2001, 1, 1, 1, 1, 1);
		assertEquals("2001-01-01 01:01:01", DateUtil.convertDateToString(date));
	}

	@Test
	public void testCalculateDate() throws Exception {
		Date date = DateUtil.getCommonTime(2012, 1, 1, 3, 20, 0);
		Date before = DateUtil.getCommonTime(2011, 12, 30, 3, 20, 0);
		Date after = DateUtil.getCommonTime(2012, 1, 3, 3, 20, 0);

		assertEquals(before.toString(),
				DateUtil.calculateDate(date, -2, Calendar.DAY_OF_MONTH)
						.toString());
		assertEquals(after.toString(),
				DateUtil.calculateDate(date, 2, Calendar.DAY_OF_MONTH)
						.toString());

		date = DateUtil.getCommonTime(2012, 2, 29, 3, 20, 0);
		before = DateUtil.getCommonTime(2011, 2, 28, 3, 20, 0);
		after = DateUtil.getCommonTime(2013, 2, 28, 3, 20, 0);

		assertEquals(before.toString(),
				DateUtil.calculateDate(date, -1, Calendar.YEAR).toString());
		assertEquals(after.toString(),
				DateUtil.calculateDate(date, 1, Calendar.YEAR).toString());
	}
}
