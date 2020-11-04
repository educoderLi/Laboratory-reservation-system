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
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

import org.junit.Test;

/**
 * Number工具类单元测试
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 1.0
 */
public class NumberUtilTest {
	@Test
	public void testConvertNumberToTargetClass() throws Exception {
		assertEquals(new Long(1L),
				NumberUtil.convertNumberToTargetClass(1, Long.class));
		assertEquals(new Integer(1),
				NumberUtil.convertNumberToTargetClass(1, Integer.class));
		assertEquals(Byte.valueOf("1"),
				NumberUtil.convertNumberToTargetClass(1, Byte.class));
		assertEquals(Short.valueOf("1"),
				NumberUtil.convertNumberToTargetClass(1, Short.class));
		assertEquals(new Float(1.0F),
				NumberUtil.convertNumberToTargetClass(1, Float.class));
		assertEquals(new Double(1.0),
				NumberUtil.convertNumberToTargetClass(1, Double.class));
	}

	@Test
	public void testConverNumberToTargetClassSafeNull() throws Exception {
		assertEquals(new Long(1L),
				NumberUtil.convertNumberToTargetClass(1, Long.class));
		assertEquals(new Integer(1),
				NumberUtil.convertNumberToTargetClass(1, Integer.class));
		assertEquals(Byte.valueOf("1"),
				NumberUtil.convertNumberToTargetClass(1, Byte.class));
		assertEquals(Short.valueOf("1"),
				NumberUtil.convertNumberToTargetClass(1, Short.class));
		assertEquals(new Float(1.0F),
				NumberUtil.convertNumberToTargetClass(1, Float.class));
		assertEquals(new Double(1.0),
				NumberUtil.convertNumberToTargetClass(1, Double.class));
		assertEquals(BigInteger.ONE,
				NumberUtil.convertNumberToTargetClass(1, BigInteger.class));
		assertEquals(BigDecimal.ONE,
				NumberUtil.convertNumberToTargetClass(1, BigDecimal.class));

		assertEquals(new Long(0),
				NumberUtil.convertNumberToTargetClassSafeNull(null, Long.class));
		assertEquals(new Integer(0),
				NumberUtil.convertNumberToTargetClassSafeNull(null,
						Integer.class));
		assertEquals(Byte.valueOf("0"),
				NumberUtil.convertNumberToTargetClassSafeNull(null, Byte.class));
		assertEquals(Short.valueOf("0"),
				NumberUtil
						.convertNumberToTargetClassSafeNull(null, Short.class));
		assertEquals(new Float(0.0F),
				NumberUtil
						.convertNumberToTargetClassSafeNull(null, Float.class));
		assertEquals(new Double(0.0),
				NumberUtil.convertNumberToTargetClassSafeNull(null,
						Double.class));
		assertEquals(BigInteger.ZERO,
				NumberUtil.convertNumberToTargetClassSafeNull(null,
						BigInteger.class));
		assertEquals(BigDecimal.ZERO,
				NumberUtil.convertNumberToTargetClassSafeNull(null,
						BigDecimal.class));

		assertEquals(new Long(0),
				NumberUtil.convertNumberToTargetClassSafeNull(null, Long.class));
		assertEquals(new Integer(0),
				NumberUtil.convertNumberToTargetClassSafeNull(null,
						Integer.class));
		assertEquals(Byte.valueOf("0"),
				NumberUtil.convertNumberToTargetClassSafeNull(null, Byte.class));
		assertEquals(Short.valueOf("0"),
				NumberUtil
						.convertNumberToTargetClassSafeNull(null, Short.class));
		assertEquals(new Float(0.0F),
				NumberUtil
						.convertNumberToTargetClassSafeNull(null, Float.class));
		assertEquals(new Double(0.0),
				NumberUtil.convertNumberToTargetClassSafeNull(null,
						Double.class));
		assertEquals(BigInteger.ZERO,
				NumberUtil.convertNumberToTargetClassSafeNull(null,
						BigInteger.class));
		assertEquals(BigDecimal.ZERO,
				NumberUtil.convertNumberToTargetClassSafeNull(null,
						BigDecimal.class));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConvertNumberToTargetClassWhenValueOverflow()
			throws Exception {
		assertEquals(Byte.valueOf("555555"),
				NumberUtil.convertNumberToTargetClass(555555, Byte.class));
	}

	@Test
	public void testParseNumber() throws Exception {
		assertEquals(new Long(123), NumberUtil.parseNumber("123", Long.class));
		assertEquals(new Integer(123),
				NumberUtil.parseNumber("123", Integer.class));
		assertEquals(Byte.valueOf("123"),
				NumberUtil.parseNumber("123", Byte.class));
		assertEquals(Short.valueOf("123"),
				NumberUtil.parseNumber("123", Short.class));
		assertEquals(new Float(123.0F),
				NumberUtil.parseNumber("123", Float.class));
		assertEquals(new BigDecimal(123.0),
				NumberUtil.parseNumber("123", BigDecimal.class));
		assertEquals(BigInteger.valueOf(123),
				NumberUtil.parseNumber("123", BigInteger.class));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseNumberWhenValueOverflow() throws Exception {
		assertEquals(Byte.valueOf("12345"),
				NumberUtil.parseNumber("12345", Byte.class));
	}

	@Test
	public void testParseNumberWithFormat() throws Exception {
		assertEquals(new Integer(12345), NumberUtil.parseNumber("12,345",
				Integer.class, new DecimalFormat("000,000,000")));
	}

	@Test
	public void testGetRandomInteger() throws Exception {
		int times = 10000;
		int random = NumberUtil.getRandomInteger(1, 1);
		assertEquals(1, random);
		random = NumberUtil.getRandomInteger(Integer.MIN_VALUE,Integer.MAX_VALUE);
		for (int i = 0; i < times; i++) {
			random = NumberUtil.getRandomInteger(1, 100);
			assertTrue(random <= 100 && random >= 1);
		}
		for (int i = 0; i < times; i++) {
			random = NumberUtil.getRandomInteger(50, 100);
			assertTrue(random <= 100 && random >= 50);
		}
	}
	
	@Test
	public void testGetRandomLong() throws Exception {
		int times = 10000;
		long random = NumberUtil.getRandomLong(1, 1);
		assertEquals(1, random);
		random = NumberUtil.getRandomLong(Long.MIN_VALUE,Long.MAX_VALUE);
		for (int i = 0; i < times; i++) {
			random = NumberUtil.getRandomLong(1, 100);
			assertTrue(random <= 100 && random >= 1);
		}
		for (int i = 0; i < times; i++) {
			random = NumberUtil.getRandomLong(2011040200000000000L, 2012040200000000000L);
			assertTrue(random <= 2012040200000000000L && random >= 2011040200000000000L);
		}
	}
}
