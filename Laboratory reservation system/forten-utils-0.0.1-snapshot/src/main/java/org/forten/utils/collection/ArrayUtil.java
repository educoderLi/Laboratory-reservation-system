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
package org.forten.utils.collection;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 数组对象工具类
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-5-7
 */
public final class ArrayUtil {
	// 私有构造器
	private ArrayUtil() {

	}

	/**
	 * 空对象数组定义
	 */
	public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
	/**
	 * 空int型数组定义
	 */
	public static final int[] EMPTY_INTEGER_ARRAY = new int[0];
	/**
	 * 空byte型数组定义
	 */
	public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
	/**
	 * 空short型数组定义
	 */
	public static final short[] EMPTY_SHORT_ARRAY = new short[0];
	/**
	 * 空long型数组定义
	 */
	public static final long[] EMPTY_LONG_ARRAY = new long[0];
	/**
	 * 空boolean型数组定义
	 */
	public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
	/**
	 * 空float型数组定义
	 */
	public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
	/**
	 * 空double型数组定义
	 */
	public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
	/**
	 * 空char型数组定义
	 */
	public static final char[] EMPTY_CHAR_ARRAY = new char[0];
	/**
	 * 空字符串数组定义
	 */
	public static final String[] EMPTY_STRING_ARRAY = new String[0];

	/**
	 * 校验数组是是否为空或无长度
	 * 
	 * @param array
	 *            待校验数组
	 * @return 数据为空或无长度返回<code>true</code>，否则返回<code>false</code>
	 */
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 校验数组是是否为空或无长度
	 * 
	 * @param array
	 *            待校验数组
	 * @return 数据为空或无长度返回<code>true</code>，否则返回<code>false</code>
	 */
	public static boolean isEmpty(int[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 校验数组是是否为空或无长度
	 * 
	 * @param array
	 *            待校验数组
	 * @return 数据为空或无长度返回<code>true</code>，否则返回<code>false</code>
	 */
	public static boolean isEmpty(short[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 校验数组是是否为空或无长度
	 * 
	 * @param array
	 *            待校验数组
	 * @return 数据为空或无长度返回<code>true</code>，否则返回<code>false</code>
	 */
	public static boolean isEmpty(byte[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 校验数组是是否为空或无长度
	 * 
	 * @param array
	 *            待校验数组
	 * @return 数据为空或无长度返回<code>true</code>，否则返回<code>false</code>
	 */
	public static boolean isEmpty(long[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 校验数组是是否为空或无长度
	 * 
	 * @param array
	 *            待校验数组
	 * @return 数据为空或无长度返回<code>true</code>，否则返回<code>false</code>
	 */
	public static boolean isEmpty(double[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 校验数组是是否为空或无长度
	 * 
	 * @param array
	 *            待校验数组
	 * @return 数据为空或无长度返回<code>true</code>，否则返回<code>false</code>
	 */
	public static boolean isEmpty(float[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 校验数组是是否为空或无长度
	 * 
	 * @param array
	 *            待校验数组
	 * @return 数据为空或无长度返回<code>true</code>，否则返回<code>false</code>
	 */
	public static boolean isEmpty(char[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 校验数组是是否为空或无长度
	 * 
	 * @param array
	 *            待校验数组
	 * @return 数组为空或无长度返回<code>true</code>，否则返回<code>false</code>
	 */
	public static boolean isEmpty(boolean[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 校验数组访问索引是否越界
	 * 
	 * @param arr
	 *            待校验数组
	 * @param index
	 *            数组访问索引
	 * @return 数组为空、无长度、索引值小于零或大于等于数组长度返回<code>true</code>， 否则返回
	 *         <code>false</code>
	 */
	public static boolean isOutOfBound(Object[] arr, int index) {
		if (isEmpty(arr)) {
			return true;
		}
		return (index >= arr.length || index < 0);
	}

	/**
	 * 校验数组访问索引是否越界
	 * 
	 * @param arr
	 *            待校验数组
	 * @param index
	 *            数组访问索引
	 * @return 数组为空、无长度、索引值小于零或大于等于数组长度返回<code>true</code>， 否则返回
	 *         <code>false</code>
	 */
	public static boolean isOutOfBound(int[] arr, int index) {
		if (isEmpty(arr)) {
			return true;
		}
		return (index >= arr.length || index < 0);
	}

	/**
	 * 校验数组访问索引是否越界
	 * 
	 * @param arr
	 *            待校验数组
	 * @param index
	 *            数组访问索引
	 * @return 数组为空、无长度、索引值小于零或大于等于数组长度返回<code>true</code>， 否则返回
	 *         <code>false</code>
	 */
	public static boolean isOutOfBound(short[] arr, int index) {
		if (isEmpty(arr)) {
			return true;
		}
		return (index >= arr.length || index < 0);
	}

	/**
	 * 校验数组访问索引是否越界
	 * 
	 * @param arr
	 *            待校验数组
	 * @param index
	 *            数组访问索引
	 * @return 数组为空、无长度、索引值小于零或大于等于数组长度返回<code>true</code>， 否则返回
	 *         <code>false</code>
	 */
	public static boolean isOutOfBound(byte[] arr, int index) {
		if (isEmpty(arr)) {
			return true;
		}
		return (index >= arr.length || index < 0);
	}

	/**
	 * 校验数组访问索引是否越界
	 * 
	 * @param arr
	 *            待校验数组
	 * @param index
	 *            数组访问索引
	 * @return 数组为空、无长度、索引值小于零或大于等于数组长度返回<code>true</code>， 否则返回
	 *         <code>false</code>
	 */
	public static boolean isOutOfBound(long[] arr, int index) {
		if (isEmpty(arr)) {
			return true;
		}
		return (index >= arr.length || index < 0);
	}

	/**
	 * 校验数组访问索引是否越界
	 * 
	 * @param arr
	 *            待校验数组
	 * @param index
	 *            数组访问索引
	 * @return 数组为空、无长度、索引值小于零或大于等于数组长度返回<code>true</code>， 否则返回
	 *         <code>false</code>
	 */
	public static boolean isOutOfBound(double[] arr, int index) {
		if (isEmpty(arr)) {
			return true;
		}
		return (index >= arr.length || index < 0);
	}

	/**
	 * 校验数组访问索引是否越界
	 * 
	 * @param arr
	 *            待校验数组
	 * @param index
	 *            数组访问索引
	 * @return 数组为空、无长度、索引值小于零或大于等于数组长度返回<code>true</code>， 否则返回
	 *         <code>false</code>
	 */
	public static boolean isOutOfBound(float[] arr, int index) {
		if (isEmpty(arr)) {
			return true;
		}
		return (index >= arr.length || index < 0);
	}

	/**
	 * 校验数组访问索引是否越界
	 * 
	 * @param arr
	 *            待校验数组
	 * @param index
	 *            数组访问索引
	 * @return 数组为空、无长度、索引值小于零或大于等于数组长度返回<code>true</code>， 否则返回
	 *         <code>false</code>
	 */
	public static boolean isOutOfBound(char[] arr, int index) {
		if (isEmpty(arr)) {
			return true;
		}
		return (index >= arr.length || index < 0);
	}

	/**
	 * 校验数组访问索引是否越界
	 * 
	 * @param arr
	 *            待校验数组
	 * @param index
	 *            数组访问索引
	 * @return 数组为空、无长度、索引值小于零或大于等于数组长度返回<code>true</code>， 否则返回
	 *         <code>false</code>
	 */
	public static boolean isOutOfBound(boolean[] arr, int index) {
		if (isEmpty(arr)) {
			return true;
		}
		return (index >= arr.length || index < 0);
	}

	/**
	 * 数组简易生成方法
	 * 
	 * @param clazz
	 *            数组元素类型
	 * @param items
	 *            数组元素
	 * @return 由数组元素组成的数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Class<T> clazz, final T... items) {
		if (items == null) {
			return (T[]) Array.newInstance(clazz, 0);
		}
		return items;
	}

	/**
	 * 判断对象是否是数组，如果数组是null则返回<code>false</code>
	 * 
	 * @param obj
	 *            待判断的对象
	 * @return 是数组:true 否则:false
	 */
	public static boolean isArray(Object obj) {
		if (obj == null) {
			return false;
		}
		return obj.getClass().isArray();
	}

	/**
	 * 获得一个无重复元素的对象数组，如果输入数组为空或为空数组则返回空数组
	 * 
	 * @param array
	 *            待排重数组
	 * @return 排重完成后的数组
	 */
	public static Object[] getWithoutDuplicatesArray(Object[] array) {
		if (isEmpty(array)) {
			return EMPTY_OBJECT_ARRAY;
		}
		List<Object> list = Arrays.asList(array);
		list = CollectionUtil.getWithoutDuplicatesList(list);
		return list.toArray(new Object[0]);
	}

	/**
	 * 获得一个无重复元素的泛型对象数组，如果输入数组为空或为空数组则返回空数组
	 * 
	 * @param clazz
	 *            泛型类型
	 * @param array
	 *            待排重数组
	 * @return 排重完成后的数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] getWithoutDuplicatesArray(Class<T> clazz, T[] array) {
		if (isEmpty(array)) {
			return (T[]) Array.newInstance(clazz, 0);
		}
		List<T> list = Arrays.asList(array);
		list = CollectionUtil.getWithoutDuplicatesList(list);
		return list.toArray((T[]) Array.newInstance(clazz, list.size()));
	}
}
