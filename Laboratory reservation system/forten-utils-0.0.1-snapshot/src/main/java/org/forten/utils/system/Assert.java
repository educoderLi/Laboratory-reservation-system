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
package org.forten.utils.system;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

import org.forten.utils.collection.ArrayUtil;
import org.forten.utils.collection.CollectionUtil;
import org.forten.utils.common.ObjectUtil;
import org.forten.utils.common.StringUtil;

/**
 * 断言工具类。参考springframework的util包下的Assert类写成，主要为了与springframework解耦。
 * 
 * @author Keith Donald
 * @author Juergen Hoeller
 * @author Colin Sampaleanu
 * @author Rob Harrop
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-5-7
 */
public abstract class Assert {
	private static final String ARGUMENT_IS_REQUIRED_MSG = "此参数是必需的,不能为null";
	private static final String TEXT_IS_REQUIRED_MSG = "此字符串必须包含文字文本内容。不能为null,空串或仅由空白字符组成";
	private static final String MUST_BETWEEN_MSG = "value必须在min和max之间,value[%s],min[%s],max[%s]";
	private static final String ARRAY_IS_NOT_EMPTY_MSG = "数组不能为空: 它至少要包含一个元素";
	private static final String COLLECTION_IS_NOT_EMPTY_MSG = "容器不能为空: 它至少要包含一个元素";
	private static final String MAP_IS_NOT_EMPTY_MSG = "Map不能为空: 它至少要包含一个元素";
	private static final String ARRAY_NOT_CONTAIN_NULL_ELEMENT_MSG = "数组中不可包含null元素";
	private static final String IS_INSTANCE_OF_MSG = "对象 [%s] 必须是%s类的实例";
	private static final String NOT_MATCH_PATTERN_MSG = "字符串[%s]不能与模式[%s]相匹配";
	private static final String MUST_BE_TRUE_MSG = "表达式结果必须为true";

	private static final String CLASS_IS_NOT_NULL_MSG = "类型参数不能是null";

	/**
	 * 断言对象不为空
	 * 
	 * @param object
	 *            被断言对象
	 * @param message
	 *            错误消息文本
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言对象不为空
	 * 
	 * @param object
	 *            被断言对象
	 */
	public static void notNull(Object object) {
		notNull(object, ARGUMENT_IS_REQUIRED_MSG);
	}

	/**
	 * 断言字符串包含文字文本
	 * 
	 * @param text
	 *            被断言的字符串
	 * @param message
	 *            错误消息文本
	 */
	public static void hasText(String text, String message) {
		if (!StringUtil.hasText(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言字符串包含文字文本
	 * 
	 * @param text
	 *            被断言的字符串
	 */
	public static void hasText(String text) {
		hasText(text, TEXT_IS_REQUIRED_MSG);
	}

	/**
	 * 断言value是否在min和max范围内 包含min和max两个值。<code>min<=value<=max</code>
	 * 
	 * @param value
	 *            被判断的值
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param message
	 *            错误消息文本
	 */
	public static <T extends Comparable<T>> void isBetween(T value, T min,
			T max, String message) {
		if (!ObjectUtil.isBetween(value, min, max)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言value是否在min和max范围内 包含min和max两个值。<code>min<=value<=max</code>
	 * 
	 * @param value
	 *            被判断的值
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 */
	public static <T extends Comparable<T>> void isBetween(T value, T min, T max) {
		isBetween(value, min, max,
				String.format(MUST_BETWEEN_MSG, value, min, max));
	}

	/**
	 * 断言数组不是空
	 * 
	 * @param array
	 *            被判断的数组
	 * @param message
	 *            错误消息文本
	 */
	public static void notEmpty(Object[] array, String message) {
		if (ArrayUtil.isEmpty(array)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言数组不是空
	 * 
	 * @param array
	 *            被判断的数组
	 */
	public static void notEmpty(Object[] array) {
		notEmpty(array, ARRAY_IS_NOT_EMPTY_MSG);
	}

	/**
	 * 断言数组中不包含空元素
	 * 
	 * @param array
	 *            被判断的数组
	 * @param message
	 *            错误消息文本
	 */
	public static void noNullElements(Object[] array, String message) {
		notEmpty(array);
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				throw new IllegalArgumentException(message);
			}
		}
	}

	/**
	 * 断言数组中不包含空元素
	 * 
	 * @param array
	 *            被判断的数组
	 */
	public static void noNullElements(Object[] array) {
		noNullElements(array, ARRAY_NOT_CONTAIN_NULL_ELEMENT_MSG);
	}

	/**
	 * 断言容器不是空
	 * 
	 * @param collection
	 *            被判断的容器
	 * @param message
	 *            错误消息文本
	 */
	public static void notEmpty(Collection<?> collection, String message) {
		if (CollectionUtil.isEmpty(collection)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言容器不是空
	 * 
	 * @param collection
	 *            被判断的容器
	 */
	public static void notEmpty(Collection<?> collection) {
		notEmpty(collection, COLLECTION_IS_NOT_EMPTY_MSG);
	}

	/**
	 * 断言Map不是空
	 * 
	 * @param map
	 *            被判断的Map
	 * @param message
	 *            错误消息文本
	 */
	public static void notEmpty(Map<?, ?> map, String message) {
		if (CollectionUtil.isEmpty(map)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言Map不是空
	 * 
	 * @param map
	 *            被判断的Map
	 */
	public static void notEmpty(Map<?, ?> map) {
		notEmpty(map, MAP_IS_NOT_EMPTY_MSG);
	}

	/**
	 * 断言一个对象是一个类的实例
	 * 
	 * @param clazz
	 *            类
	 * @param obj
	 *            对象
	 */
	public static void isInstanceOf(Class<?> clazz, Object obj) {
		isInstanceOf(clazz, obj, String.format(IS_INSTANCE_OF_MSG,
				(obj != null ? obj.getClass().getName() : "null"), clazz));
	}

	/**
	 * 断言一个对象是一个类的实例
	 * 
	 * @param clazz
	 *            类
	 * @param obj
	 *            对象
	 * @param message
	 *            错误文本消息
	 */
	public static void isInstanceOf(Class<?> clazz, Object obj, String message) {
		notNull(clazz, CLASS_IS_NOT_NULL_MSG);
		if (!clazz.isInstance(obj)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言boolean表达式结果为<code>true</code>
	 * 
	 * @param expression
	 *            表达式
	 * @param message
	 *            错误消息文本
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言boolean表达式结果为<code>true</code>
	 * 
	 * @param expression
	 *            表达式
	 */
	public static void isTrue(boolean expression) {
		isTrue(expression, MUST_BE_TRUE_MSG);
	}

	/**
	 * 断言字符串与模式相匹配
	 * 
	 * @param input
	 *            字符串
	 * @param pattern
	 *            匹配模式
	 * @param message
	 *            错误消息文本
	 */
	public static void matchesPattern(String input, String pattern,
			String message) {
		if (!Pattern.matches(pattern, input)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言字符串与模式相匹配
	 * 
	 * @param input
	 *            字符串
	 * @param pattern
	 *            匹配模式
	 */
	public static void matchesPattern(String input, String pattern) {
		matchesPattern(input, pattern,
				String.format(NOT_MATCH_PATTERN_MSG, input, pattern));
	}
}
