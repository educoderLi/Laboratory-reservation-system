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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * 集合容器工具类
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-5-7
 */
public final class CollectionUtil {
	// 私有构造方法
	private CollectionUtil() {

	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param <T>
	 *            集合泛型类型
	 * @param coll
	 *            集合对象
	 * @return 空：<code>true</code> 非空：<code>false</code>
	 */
	public static <T> boolean isEmpty(Collection<T> coll) {
		return coll == null || coll.isEmpty();
	}

	/**
	 * 判断Map是否为空
	 * 
	 * @param map
	 *            Map对象
	 * @return 空：<code>true</code> 非空：<code>false</code>
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return map == null || map.isEmpty();
	}

	/**
	 * 判断index值是否超出指定集合对象的索引范围
	 * 
	 * @param <T>
	 *            集合泛型类型
	 * @param coll
	 *            集合对象
	 * @param index
	 *            索引值
	 * @return 超出:<code>true</code> 未超出:<code>false</code>
	 */
	public static <T> boolean isOutOfBound(Collection<T> coll, int index) {
		if (isEmpty(coll)) {
			return true;
		}
		return ((index >= coll.size()) || (index < 0)) ? true : false;
	}

	/**
	 * 获取一个没有重复元素的List
	 * 
	 * @param origList
	 *            可能含有重复元素的List
	 * @return 没有重复元素的List
	 */
	public static <T> List<T> getWithoutDuplicatesList(List<T> origList) {
		if (isEmpty(origList)) {
			return Collections.emptyList();
		}
		return new ArrayList<T>(new LinkedHashSet<T>(origList));
	}
}
