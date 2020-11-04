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

import org.forten.utils.system.Assert;

/**
 * 对象工具类
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-5-7
 */
public abstract class ObjectUtil {
	/**
	 * 如果对象为<code>null</code>则返回默认值
	 * 
	 * @param object
	 *            待判断的对象
	 * @param defaultValue
	 *            默认值
	 * @return 对象本身或默认值
	 */
	public static <T> T getDefaultValueIfNull(T object, T defaultValue) {
		return object != null ? object : defaultValue;
	}

	/**
	 * 比较两个对象的大小，并且定义<code>null</code>的大小
	 * 
	 * @param value1
	 *            比较值1
	 * @param value2
	 *            比较值2
	 * @param nullGreater
	 *            若为<code>true</code>则<code>null</code>为大，反之，若为
	 *            <code>false</code>则<code>null</code>为小
	 * @return value1<value2 返回-1；value1==value2 返回0；value1>value2 返回1
	 */
	public static <T extends Comparable<T>> int compare(T value1, T value2,
			boolean nullGreater) {
		if (value1 == value2) {
			return 0;
		} else if (value1 == null) {
			return nullGreater ? 1 : -1;
		} else if (value2 == null) {
			return nullGreater ? -1 : 1;
		}
		return value1.compareTo(value2);
	}

	/**
	 * 获得最小值
	 * 
	 * @param values
	 *            值列表
	 * @return 返回值列表中最小的值
	 */
	public static <T extends Comparable<T>> T min(T[] values) {
		T result = null;
		if (values != null) {
			for (T value : values) {
				if (compare(value, result, true) < 0) {
					result = value;
				}
			}
		}
		return result;
	}

	/**
	 * 获得最大值
	 * 
	 * @param values
	 *            值列表
	 * @return 返回值列表中最大的值
	 */
	public static <T extends Comparable<T>> T max(T[] values) {
		T result = null;
		if (values != null) {
			for (T value : values) {
				if (compare(value, result, false) > 0) {
					result = value;
				}
			}
		}
		return result;
	}

	/**
	 * 判断所给值是否在min和max之间，包含min和max <code>min<=value<=max</code>
	 * 
	 * @param value
	 *            被判断的值
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return value在min和max之间返回<code>true</code>，否则返回<code>false</code>
	 */
	public static <T extends Comparable<T>> boolean isBetween(T value, T min,
			T max) {
		Assert.notNull(value);
		Assert.notNull(min);
		Assert.notNull(max);
		// 若min大于max则交换这两个值
		if (min.compareTo(max) > 0) {
			T temp = min;
			min = max;
			max = temp;
		}
		return (value.compareTo(min) >= 0 && value.compareTo(max) <= 0) ? true
				: false;
	}
}
