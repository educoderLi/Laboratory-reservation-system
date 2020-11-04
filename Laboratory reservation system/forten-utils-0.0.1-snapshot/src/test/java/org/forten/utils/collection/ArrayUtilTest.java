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

import org.junit.Test;

/**
 * 数组对象工具单元测试
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 1.0
 */
public class ArrayUtilTest {
	@Test
	public void testIsEmpty() throws Exception {
		Object[] obj = null;
		assertEquals(true, ArrayUtil.isEmpty(obj));

		obj = ArrayUtil.EMPTY_OBJECT_ARRAY;
		assertEquals(true, ArrayUtil.isEmpty(obj));

		obj = new Object[] { new Object() };
		assertEquals(false, ArrayUtil.isEmpty(obj));
	}

	@Test
	public void testIsOutOfBound() throws Exception {
		Object[] obj = null;
		assertEquals(true, ArrayUtil.isOutOfBound(obj, 0));

		obj = ArrayUtil.EMPTY_OBJECT_ARRAY;
		assertEquals(true, ArrayUtil.isOutOfBound(obj, 0));

		obj = new Object[] { new Object(), new Object(), new Object() };
		assertEquals(false, ArrayUtil.isOutOfBound(obj, 0));
		assertEquals(false, ArrayUtil.isOutOfBound(obj, 2));
		assertEquals(true, ArrayUtil.isOutOfBound(obj, -1));
		assertEquals(true, ArrayUtil.isOutOfBound(obj, 3));
		assertEquals(true, ArrayUtil.isOutOfBound(obj, 4));
	}

	@Test
	public void testToArray() throws Exception {
		Integer[] arr = ArrayUtil.toArray(Integer.class, 1, 2, 3, 4, 5);
		assertEquals(5, arr.length);
		assertEquals(1, arr[0].intValue());
		assertEquals(2, arr[1].intValue());
		assertEquals(3, arr[2].intValue());
		assertEquals(4, arr[3].intValue());
		assertEquals(5, arr[4].intValue());

		arr = ArrayUtil.toArray(Integer.class);
		assertEquals(true, arr != null && arr.length == 0);
	}

	@Test
	public void testIsArray() throws Exception {
		String[] arr1 = new String[] { "1", "2", "3", "4" };
		assertEquals(true, ArrayUtil.isArray(arr1));

		String arr2 = "a";
		assertEquals(false, ArrayUtil.isArray(arr2));

		String arr3[] = null;
		assertEquals(false, ArrayUtil.isArray(arr3));

		String arr4[] = ArrayUtil.EMPTY_STRING_ARRAY;
		assertEquals(true, ArrayUtil.isArray(arr4));
	}

	@Test
	public void testGetWithoutDuplicatesArray() throws Exception {
		String[] arr = new String[] { "1", "2", "3", "4" };
		Object[] a1 = ArrayUtil.getWithoutDuplicatesArray(String.class, arr);
		assertEquals(4, a1.length);

		arr = new String[] { "1", "2", "3", "3", "2" };
		String[] a2 = ArrayUtil.getWithoutDuplicatesArray(String.class, arr);
		assertEquals(3, a2.length);

		arr = null;
		String[] a3 = ArrayUtil.getWithoutDuplicatesArray(String.class, arr);
		assertEquals(0, a3.length);

		arr = null;
		Object[] a4 = ArrayUtil.getWithoutDuplicatesArray(arr);
		assertEquals(0, a4.length);
	}
}
