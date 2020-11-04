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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import org.forten.utils.system.Assert;

/**
 * Number工具类。参考springframework的util包下的NumberUtils类写成，主要为了与springframework解耦。
 * 
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-5-7
 */
public abstract class NumberUtil {
	private static final String CONVERTE_OVERFLOW_MESSAGE = "不能转换%s类型的数字 [%s]成为目标类型[%s]: 范围溢出";
	private static final String CAN_NOT_CONVERT_TO_TARGET_TYPE_MSG = "不能转换字符串 [%s] 成为目标类型 [%s]";
	private static final String NUMBER_IS_REQUIRED_MSG = "数字不能为空";
	private static final String NUMBER_TEXT_IS_REQUIRED_MSG = "数字文本不能为空";
	private static final String TARGET_CLASS_IS_REQUIRED_MSG = "目标类型不能为空";
	private static final String CAN_NOT_PARSE_NUMBER_MSG = "不能解析数字: %s";
	private static final String OVERFLOW_MSG = "数字范围溢出";

	/**
	 * 转换Number对象成为目标class类型的对象
	 * 
	 * @param number
	 *            要被转换的Number对象
	 * @param targetClass
	 *            目标class类型
	 * @return 转型成功的对象
	 * @throws IllegalArgumentException
	 *             若目标class不被支持则抛出此异常：如目标class不是Number的子类型
	 * @see java.lang.Byte
	 * @see java.lang.Short
	 * @see java.lang.Integer
	 * @see java.lang.Long
	 * @see java.math.BigInteger
	 * @see java.lang.Float
	 * @see java.lang.Double
	 * @see java.math.BigDecimal
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T convertNumberToTargetClass(
			Number number, Class<T> targetClass)
			throws IllegalArgumentException {

		Assert.notNull(number, NUMBER_IS_REQUIRED_MSG);
		Assert.notNull(targetClass, TARGET_CLASS_IS_REQUIRED_MSG);

		if (targetClass.isInstance(number)) {
			return (T) number;
		} else if (targetClass.equals(Byte.class)) {
			long value = number.longValue();
			if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
				raiseOverflowException(number, targetClass);
			}
			return (T) new Byte(number.byteValue());
		} else if (targetClass.equals(Short.class)) {
			long value = number.longValue();
			if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
				raiseOverflowException(number, targetClass);
			}
			return (T) new Short(number.shortValue());
		} else if (targetClass.equals(Integer.class)) {
			long value = number.longValue();
			if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
				raiseOverflowException(number, targetClass);
			}
			return (T) new Integer(number.intValue());
		} else if (targetClass.equals(Long.class)) {
			return (T) new Long(number.longValue());
		} else if (targetClass.equals(BigInteger.class)) {
			if (number instanceof BigDecimal) {
				// 为了精度不失真，使用BigDecimal自己的转换方法
				return (T) ((BigDecimal) number).toBigInteger();
			} else {
				// 原值不是Big*的数字，使用long值转换
				return (T) BigInteger.valueOf(number.longValue());
			}
		} else if (targetClass.equals(Float.class)) {
			return (T) new Float(number.floatValue());
		} else if (targetClass.equals(Double.class)) {
			return (T) new Double(number.doubleValue());
		} else if (targetClass.equals(BigDecimal.class)) {
			// 总是使用BigDecimal(String)以避免BigDecimal(double)的结果不确定性
			return (T) new BigDecimal(number.toString());
		} else {
			throw new IllegalArgumentException(String.format(
					CONVERTE_OVERFLOW_MESSAGE, number.getClass().getName(),
					number, targetClass));
		}
	}

	/**
	 * 转换Number对象成为目标class类型的对象,此方法是<code>null</code>安全的
	 * 
	 * @param number
	 *            要被转换的Number对象
	 * @param targetClass
	 *            目标class类型
	 * @return 转型成功的对象
	 * @throws IllegalArgumentException
	 *             若目标class不被支持则抛出此异常：如目标class不是Number的子类型
	 * @see java.lang.Byte
	 * @see java.lang.Short
	 * @see java.lang.Integer
	 * @see java.lang.Long
	 * @see java.math.BigInteger
	 * @see java.lang.Float
	 * @see java.lang.Double
	 * @see java.math.BigDecimal
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T convertNumberToTargetClassSafeNull(
			Number number, Class<T> targetClass)
			throws IllegalArgumentException {
		Assert.notNull(targetClass, TARGET_CLASS_IS_REQUIRED_MSG);

		if (targetClass.isInstance(number)) {
			return (T) number;
		} else if (targetClass.equals(Byte.class)) {
			if (number == null) {
				return (T) Byte.valueOf("0");
			}
			long value = number.longValue();
			if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
				raiseOverflowException(number, targetClass);
			}
			return (T) new Byte(number.byteValue());
		} else if (targetClass.equals(Short.class)) {
			if (number == null) {
				return (T) Short.valueOf("0");
			}
			long value = number.longValue();
			if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
				raiseOverflowException(number, targetClass);
			}
			return (T) new Short(number.shortValue());
		} else if (targetClass.equals(Integer.class)) {
			if (number == null) {
				return (T) Integer.valueOf("0");
			}
			long value = number.longValue();
			if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
				raiseOverflowException(number, targetClass);
			}
			return (T) new Integer(number.intValue());
		} else if (targetClass.equals(Long.class)) {
			return (number == null) ? (T) new Long(0) : (T) new Long(
					number.longValue());
		} else if (targetClass.equals(BigInteger.class)) {
			if (number instanceof BigDecimal) {
				// 为了精度不失真，使用BigDecimal自己的转换方法
				return number == null ? (T) BigInteger.ZERO
						: (T) ((BigDecimal) number).toBigInteger();
			} else {
				// 原值不是Big*的数字，使用long值转换
				return number == null ? (T) BigInteger.ZERO : (T) BigInteger
						.valueOf(number.longValue());
			}
		} else if (targetClass.equals(Float.class)) {
			return number == null ? (T) new Float(0.0) : (T) new Float(
					number.floatValue());
		} else if (targetClass.equals(Double.class)) {
			return number == null ? (T) new Double(0.0) : (T) new Double(
					number.doubleValue());
		} else if (targetClass.equals(BigDecimal.class)) {
			// 总是使用BigDecimal(String)以避免BigDecimal(double)的结果不确定性
			return number == null ? (T) BigDecimal.ZERO : (T) new BigDecimal(
					number.toString());
		} else {
			throw new IllegalArgumentException(String.format(
					CONVERTE_OVERFLOW_MESSAGE, number.getClass().getName(),
					number, targetClass));
		}
	}

	/**
	 * 为Number和目标类型触发溢出异常
	 * 
	 * @param number
	 *            尝试要被转换的Number
	 * @param targetClass
	 *            目标类型
	 */
	private static void raiseOverflowException(Number number,
			Class<?> targetClass) {
		throw new IllegalArgumentException(String.format(
				CONVERTE_OVERFLOW_MESSAGE, number.getClass().getName(), number,
				targetClass));
	}

	/**
	 * 使用一致的<code>decode</code> / <code>valueOf</code>方法解析数字文本成为目标类型
	 * <p>
	 * 在解析前先对数字文本做Trim操作。支持十六进制数字文本(以 "0x","0X","#"开头的数字文本)
	 * 
	 * @param text
	 *            要被转换的数字文本
	 * @param targetClass
	 *            被解析成的目标类型
	 * @return 解析成功的对象
	 * @throws IllegalArgumentException
	 *             若目标class不被支持则抛出此异常：如目标class不是Number的子类型
	 * @see java.lang.Byte#decode
	 * @see java.lang.Short#decode
	 * @see java.lang.Integer#decode
	 * @see java.lang.Long#decode
	 * @see #decodeBigInteger(String)
	 * @see java.lang.Float#valueOf
	 * @see java.lang.Double#valueOf
	 * @see java.math.BigDecimal#BigDecimal(String)
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T parseNumber(String text,
			Class<T> targetClass) {
		Assert.notNull(text, NUMBER_TEXT_IS_REQUIRED_MSG);
		Assert.notNull(targetClass, TARGET_CLASS_IS_REQUIRED_MSG);
		String trimmed = StringUtil.trimAllWhitespace(text);

		if (targetClass.equals(Byte.class)) {
			return (T) (isHexNumber(trimmed) ? Byte.decode(trimmed) : Byte
					.valueOf(trimmed));
		} else if (targetClass.equals(Short.class)) {
			return (T) (isHexNumber(trimmed) ? Short.decode(trimmed) : Short
					.valueOf(trimmed));
		} else if (targetClass.equals(Integer.class)) {
			return (T) (isHexNumber(trimmed) ? Integer.decode(trimmed)
					: Integer.valueOf(trimmed));
		} else if (targetClass.equals(Long.class)) {
			return (T) (isHexNumber(trimmed) ? Long.decode(trimmed) : Long
					.valueOf(trimmed));
		} else if (targetClass.equals(BigInteger.class)) {
			return (T) (isHexNumber(trimmed) ? decodeBigInteger(trimmed)
					: new BigInteger(trimmed));
		} else if (targetClass.equals(Float.class)) {
			return (T) Float.valueOf(trimmed);
		} else if (targetClass.equals(Double.class)) {
			return (T) Double.valueOf(trimmed);
		} else if (targetClass.equals(BigDecimal.class)
				|| targetClass.equals(Number.class)) {
			return (T) new BigDecimal(trimmed);
		} else {
			throw new IllegalArgumentException(String.format(
					CAN_NOT_CONVERT_TO_TARGET_TYPE_MSG, text,
					targetClass.getName()));
		}
	}

	/**
	 * 使用一致的<code>decode</code> / <code>valueOf</code>
	 * 方法解析数字文本成为目标类型,并使用指定的NumberFormat为数字对象格式化
	 * <p>
	 * 在解析前先对数字文本做Trim操作。支持十六进制数字文本(以 "0x","0X","#"开头的数字文本)
	 * 
	 * @param text
	 *            要被转换的数字文本
	 * @param targetClass
	 *            被解析成的目标类型
	 * @param numberFormat
	 *            数字格式对象
	 * @return 被解析好的数字
	 * @throws IllegalArgumentException
	 *             若目标class不被支持则抛出此异常：如目标class不是Number的子类型
	 * @see java.text.NumberFormat#parse
	 * @see #convertNumberToTargetClass
	 * @see #parseNumber(String, Class)
	 */
	public static <T extends Number> T parseNumber(String text,
			Class<T> targetClass, NumberFormat numberFormat) {
		if (numberFormat != null) {
			Assert.notNull(text, NUMBER_TEXT_IS_REQUIRED_MSG);
			Assert.notNull(targetClass, TARGET_CLASS_IS_REQUIRED_MSG);
			DecimalFormat decimalFormat = null;
			boolean resetBigDecimal = false;
			if (numberFormat instanceof DecimalFormat) {
				decimalFormat = (DecimalFormat) numberFormat;
				if (BigDecimal.class.equals(targetClass)
						&& !decimalFormat.isParseBigDecimal()) {
					decimalFormat.setParseBigDecimal(true);
					resetBigDecimal = true;
				}
			}
			try {
				Number number = numberFormat.parse(StringUtil
						.trimAllWhitespace(text));
				return convertNumberToTargetClass(number, targetClass);
			} catch (ParseException ex) {
				throw new IllegalArgumentException(String.format(
						CAN_NOT_PARSE_NUMBER_MSG, ex.getMessage()));
			} finally {
				if (resetBigDecimal) {
					decimalFormat.setParseBigDecimal(false);
				}
			}
		} else {
			return parseNumber(text, targetClass);
		}
	}

	/**
	 * 判断给出的value是否是十六进制字符串
	 */
	private static boolean isHexNumber(String value) {
		int index = (value.startsWith("-") ? 1 : 0);
		return (value.startsWith("0x", index) || value.startsWith("0X", index) || value
				.startsWith("#", index));
	}

	/**
	 * 从一个String解析出一个BigInteger对象，支持十进制，十六进制，八进制
	 * 
	 * @see BigInteger#BigInteger(String, int)
	 */
	private static BigInteger decodeBigInteger(String value) {
		int radix = 10;
		int index = 0;
		boolean negative = false;

		// 处理可能出现的负号
		if (value.startsWith("-")) {
			negative = true;
			index++;
		}

		// 处理可能出现的各种进制前缀
		if (value.startsWith("0x", index) || value.startsWith("0X", index)) {
			index += 2;
			radix = 16;
		} else if (value.startsWith("#", index)) {
			index++;
			radix = 16;
		} else if (value.startsWith("0", index) && value.length() > 1 + index) {
			index++;
			radix = 8;
		}

		BigInteger result = new BigInteger(value.substring(index), radix);
		return (negative ? result.negate() : result);
	}

	/**
	 * 生成min到max间的Integer类型随机数,包含min和max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomInteger(int min, int max) {
		if (max > Integer.MAX_VALUE || min < Integer.MIN_VALUE) {
			throw new IllegalArgumentException(OVERFLOW_MSG);
		}
		return (int) ((max + 1) + (min - (max + 1)) * Math.random());
	}

	/**
	 * 生成min到max间的Long类型随机数,包含min和max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static long getRandomLong(long min, long max) {
		if (max > Long.MAX_VALUE || min < Long.MIN_VALUE) {
			throw new IllegalArgumentException(OVERFLOW_MSG);
		}
		return (long) ((max + 1) + (min - (max + 1)) * Math.random());
	}
}
