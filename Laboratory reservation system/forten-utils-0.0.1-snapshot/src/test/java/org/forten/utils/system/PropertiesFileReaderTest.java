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
package org.forten.utils.system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Properties属性文件内容读取器单元测试
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 1.0
 */
public class PropertiesFileReaderTest {
	@Test
	// 正常情况
	public void testGetValue() throws Exception {
		String actual = PropertiesFileReader.getValue("test", "TEST_KEY");
		assertEquals("TEST_STRING", actual);
		actual = PropertiesFileReader.getValue("test", "EMPTY_VALUE");
		assertEquals("", actual);
	}

	@Test(expected = AssertionError.class)
	// 文件不存在
	public void testGetValueNoFile() throws Exception {
		PropertiesFileReader.getValue("no_this_file", "no_this_key");
		fail("未捕获到应抛出的Exception");
	}

	@Test(expected = AssertionError.class)
	// 键不存在
	public void testGetValueNoKey() throws Exception {
		PropertiesFileReader.getValue("test", "no_this_key");
		fail("未捕获到应抛出的Exception");
	}
}
