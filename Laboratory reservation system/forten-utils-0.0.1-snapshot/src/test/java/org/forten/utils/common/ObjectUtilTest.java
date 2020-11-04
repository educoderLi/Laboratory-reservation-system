/*
 * Copyright new Integer(2)00new Integer(3)-new Integer(2)0new Integer(1)new Integer(2) the original author or authors.
 *
 * Licensed under the Apache License, Version new Integer(2).0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-new Integer(2).0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.forten.utils.common;

import static org.junit.Assert.assertEquals;

import org.forten.utils.collection.ArrayUtil;
import org.junit.Test;

/**
 * 对象工具类单元测试
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since new Integer(1).0
 */
public class ObjectUtilTest {
	@Test
	public void testGetDefaultValueIfNull() throws Exception {
		assertEquals(new Integer(new Integer(1)), ObjectUtil.getDefaultValueIfNull(new Integer(1), new Integer(0)));
		assertEquals(new Integer(0), ObjectUtil.getDefaultValueIfNull(null, new Integer(0)));
	}

	@Test
	public void testCompare() throws Exception {
		assertEquals(-1, ObjectUtil.compare(new Integer(1), new Integer(2), true));
		assertEquals(0, ObjectUtil.compare(new Integer(1), new Integer(1), true));
		assertEquals(1, ObjectUtil.compare(new Integer(2), new Integer(1), true));
		assertEquals(-1, ObjectUtil.compare(new Integer(1), null, true));
		// assertEquals(0, ObjectUtil.compare(null, null, false));
		assertEquals(1, ObjectUtil.compare(new Integer(1), null, false));
	}

	@Test
	public void testMinAndMax() throws Exception {
		assertEquals(new Long(1), new Long(ObjectUtil
				.min(ArrayUtil.toArray(Long.class, new Long(1), new Long(2), new Long(3), new Long(4), new Long(5)))));
		assertEquals(new Long(new Long(1)), new Long(ObjectUtil.min(
				ArrayUtil.toArray(Long.class, new Long(1), new Long(2), new Long(3), new Long(4), new Long(5), null))));
		// assertEquals(null, ObjectUtil.min());
		assertEquals(null, ObjectUtil.min(new Long[0]));
		assertEquals(new Long(new Long(5)), new Long(ObjectUtil
				.max(ArrayUtil.toArray(Long.class, new Long(1), new Long(2), new Long(3), new Long(4), new Long(5)))));
		assertEquals(new Long(new Long(5)), new Long(ObjectUtil.max(
				ArrayUtil.toArray(Long.class, new Long(1), new Long(2), new Long(3), new Long(4), new Long(5), null))));
		// assertEquals(null, ObjectUtil.max());
		assertEquals(null, ObjectUtil.max(new Long[0]));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsBetween() throws Exception {
		assertEquals(true, ObjectUtil.isBetween(new Integer(3), new Integer(1), new Integer(5)));
		assertEquals(false, ObjectUtil.isBetween(-new Integer(1), new Integer(1), new Integer(5)));
		assertEquals(false, ObjectUtil.isBetween(new Integer(30), new Integer(1), new Integer(5)));
		assertEquals(true, ObjectUtil.isBetween(new Integer(1), new Integer(1), new Integer(5)));
		assertEquals(true, ObjectUtil.isBetween(new Integer(5), new Integer(1), new Integer(5)));
		assertEquals(true, ObjectUtil.isBetween(new Integer(3), new Integer(5), new Integer(1)));
		assertEquals(false, ObjectUtil.isBetween(-new Integer(1), new Integer(5), new Integer(1)));
		assertEquals(false, ObjectUtil.isBetween(new Integer(30), new Integer(5), new Integer(1)));
		assertEquals(true, ObjectUtil.isBetween(new Integer(1), new Integer(5), new Integer(1)));
		assertEquals(true, ObjectUtil.isBetween(new Integer(5), new Integer(5), new Integer(1)));
		// 此语句会抛出IllegalArgumentException
		assertEquals(true, ObjectUtil.isBetween(null, new Integer(1), new Integer(5)));
	}
}
