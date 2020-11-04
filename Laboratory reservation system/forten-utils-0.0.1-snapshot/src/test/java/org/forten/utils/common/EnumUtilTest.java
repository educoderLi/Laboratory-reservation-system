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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * 枚举处理工具类单元测试
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 1.0
 */
public class EnumUtilTest {
	@Test
	public void testGetEnumArray() throws Exception {
		TestEnum[] arr = EnumUtil.getEnumArray(TestEnum.class);
		assertEquals(3, arr.length);
		assertEquals("ENUM1", arr[0].name());
		assertEquals("ENUM2", arr[1].name());
		assertEquals("ENUM3", arr[2].name());
	}

	@Test
	public void testGetEnumList() throws Exception {
		List<TestEnum> list = EnumUtil.getEnumList(TestEnum.class);
		assertEquals(3, list.size());
		assertEquals("ENUM1", list.get(0).name());
		assertEquals("ENUM2", list.get(1).name());
		assertEquals("ENUM3", list.get(2).name());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetEnumByNum() throws Exception {
		TestEnum enum1 = EnumUtil.getEnumByNum(TestEnum.class, 0);
		TestEnum enum2 = EnumUtil.getEnumByNum(TestEnum.class, 1);
		TestEnum enum3 = EnumUtil.getEnumByNum(TestEnum.class, 2);
		assertEquals("ENUM1", enum1.name());
		assertEquals("ENUM2", enum2.name());
		assertEquals("ENUM3", enum3.name());
		EnumUtil.getEnumByNum(TestEnum.class, 3);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetEnumByName() throws Exception {
		TestEnum enum1 = EnumUtil.getEnumByName(TestEnum.class, "ENUM1");
		TestEnum enum2 = EnumUtil.getEnumByName(TestEnum.class, "ENUM2");
		TestEnum enum3 = EnumUtil.getEnumByName(TestEnum.class, "ENUM3");
		assertEquals("ENUM1", enum1.name());
		assertEquals("ENUM2", enum2.name());
		assertEquals("ENUM3", enum3.name());
		EnumUtil.getEnumByName(TestEnum.class, null);
		fail();
	}

	@Test
	public void testGetEnumMapWithNameKey() throws Exception {
		Map<String, TestEnum> map = EnumUtil
				.getEnumMapWithNameKey(TestEnum.class);
		assertEquals(3, map.size());
		assertEquals(TestEnum.ENUM1, map.get("ENUM1"));
	}

	@Test
	public void testGetEnumMapWithOrdinalKey() throws Exception {
		Map<Integer, TestEnum> map = EnumUtil
				.getEnumMapWithOrdinalKey(TestEnum.class);
		assertEquals(3, map.size());
	}

	enum TestEnum {
		ENUM1, ENUM2, ENUM3;
	}
}
