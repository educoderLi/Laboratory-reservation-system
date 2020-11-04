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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * 集合容器工具类单元测试
 * 
 * @author <a href="mailto:du_yi@bbn.cn">杜轶</a>
 * @since 1.0
 */
public class CollectionUtilTest {
	@Test
	public void testIsEmptyForCollection() throws Exception {
		List<Object> list = null;
		assertEquals(true, CollectionUtil.isEmpty(list));

		list = Collections.emptyList();
		assertEquals(true, CollectionUtil.isEmpty(list));

		list = new ArrayList<Object>();
		assertEquals(true, CollectionUtil.isEmpty(list));

		list.add(new Object());
		assertEquals(false, CollectionUtil.isEmpty(list));
	}

	@Test
	public void testIsEmptyForMap() throws Exception {
		Map<String, String> map = null;
		assertEquals(true, CollectionUtil.isEmpty(map));

		map = new HashMap<String, String>();
		assertEquals(true, CollectionUtil.isEmpty(map));

		map.put("key", "value");
		assertEquals(false, CollectionUtil.isEmpty(map));
	}

	@Test
	public void testIsOutOfBound() throws Exception {
		List<Object> list = null;
		assertEquals(true, CollectionUtil.isOutOfBound(list, 0));

		list = Collections.emptyList();
		assertEquals(true, CollectionUtil.isOutOfBound(list, 0));

		list = new ArrayList<Object>();
		assertEquals(true, CollectionUtil.isOutOfBound(list, 0));

		list.add(new Object());
		assertEquals(false, CollectionUtil.isOutOfBound(list, 0));
		assertEquals(true, CollectionUtil.isOutOfBound(list, -1));
		assertEquals(true, CollectionUtil.isOutOfBound(list, 1));
	}

	@Test
	public void testGetWithoutDuplicatesList() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		assertEquals(5, list.size());
		list = CollectionUtil.getWithoutDuplicatesList(list);
		assertEquals(4, list.size());
		
		list = null;
		list = CollectionUtil.getWithoutDuplicatesList(list);
		assertEquals(0, list.size());
	}
}
