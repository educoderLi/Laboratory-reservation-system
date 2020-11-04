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

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.forten.utils.system.Assert;

/**
 * 枚举处理工具类
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-5-7
 */
public final class EnumUtil {
	private static final String CLASS_NOT_NULL_MSG = "枚举类型不可为null";
	private static final String NAME_MUST_HAS_TEXT_MSG = "枚举元素名称必须包含非空字符";

	// 私有构造器
	private EnumUtil() {

	}

	/**
	 * 获取特定枚举类的数组
	 * 
	 * @param clazz
	 *            枚举Class
	 * @return 枚举元素数组
	 */
	public static <E extends Enum<E>> E[] getEnumArray(Class<E> clazz) {
		Assert.notNull(clazz, CLASS_NOT_NULL_MSG);
		return clazz.getEnumConstants();
	}

	/**
	 * 按序号获得指定枚举类的枚举元素
	 * 
	 * @param clazz
	 *            枚举Class
	 * @param num
	 *            序号
	 * @return 符合序号的枚举元素
	 */
	public static <E extends Enum<E>> E getEnumByNum(Class<E> clazz, int num) {
		Assert.notNull(clazz, CLASS_NOT_NULL_MSG);
		E[] enums = getEnumArray(clazz);
		Assert.isBetween(num, 0, enums.length - 1);
		return enums[num];
	}

	/**
	 * 按名称获得指定枚举类的枚举元素
	 * 
	 * @param clazz
	 *            枚举Class
	 * @param enumName
	 *            枚举名称
	 * @return 符合名称的枚举元素
	 */
	public static <E extends Enum<E>> E getEnumByName(Class<E> clazz,
			String enumName) {
		Assert.notNull(clazz, CLASS_NOT_NULL_MSG);
		Assert.hasText(enumName, NAME_MUST_HAS_TEXT_MSG);
		return Enum.valueOf(clazz, enumName);
	}

	/**
	 * 获取特定枚举类的元素列表
	 * 
	 * @param clazz
	 *            枚举Class枚举Class
	 * @return 枚举元素有序列表
	 */
	public static <E extends Enum<E>> List<E> getEnumList(Class<E> clazz) {
		Assert.notNull(clazz, CLASS_NOT_NULL_MSG);
		return Arrays.asList(getEnumArray(clazz));
	}

	/**
	 * 获取特定枚举类的元素Map，以枚举名称为key，以枚举对象为value
	 * 
	 * @param clazz
	 *            枚举Class
	 * @return 封装好的枚举Map
	 */
	public static <E extends Enum<E>> Map<String, E> getEnumMapWithNameKey(
			Class<E> clazz) {
		Assert.notNull(clazz, CLASS_NOT_NULL_MSG);
		Map<String, E> map = new LinkedHashMap<String, E>();
		for (E e : getEnumArray(clazz)) {
			map.put(e.name(), e);
		}
		return map;
	}

	/**
	 * 获取特定枚举类的元素Map，以枚举序号为key，以枚举对象为value
	 * 
	 * @param clazz
	 *            枚举Class
	 * @return 封装好的枚举Map
	 */
	public static <E extends Enum<E>> Map<Integer, E> getEnumMapWithOrdinalKey(
			Class<E> clazz) {
		Assert.notNull(clazz, CLASS_NOT_NULL_MSG);
		Map<Integer, E> map = new LinkedHashMap<Integer, E>();
		for (E e : getEnumArray(clazz)) {
			map.put(e.ordinal(), e);
		}
		return map;
	}
}
