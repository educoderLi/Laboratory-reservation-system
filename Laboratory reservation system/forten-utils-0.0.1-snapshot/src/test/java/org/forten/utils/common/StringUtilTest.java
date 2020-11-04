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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.forten.utils.collection.ArrayUtil;
import org.junit.Test;

/**
 * String处理工具类单元测试
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 1.0
 */
public class StringUtilTest {
	@Test
	public void testHasLength() throws Exception {
		assertEquals(false, StringUtil.hasLength(null));
		assertEquals(false, StringUtil.hasLength(""));
		assertEquals(true, StringUtil.hasLength(" "));
		assertEquals(true, StringUtil.hasLength("a"));
	}

	@Test
	public void testHasText() throws Exception {
		assertEquals(false, StringUtil.hasText(null));
		assertEquals(false, StringUtil.hasText(""));
		assertEquals(false, StringUtil.hasText(" "));
		assertEquals(true, StringUtil.hasText(" a "));
		assertEquals(true, StringUtil.hasText("a"));
	}

	@Test
	public void testContainsWhitespace() throws Exception {
		assertEquals(false, StringUtil.containsWhitespace(null));
		assertEquals(false, StringUtil.containsWhitespace(""));
		assertEquals(true, StringUtil.containsWhitespace(" "));
		assertEquals(true, StringUtil.containsWhitespace(" a "));
		assertEquals(true, StringUtil.containsWhitespace("ab c"));
		assertEquals(false, StringUtil.containsWhitespace("a"));
	}

	@Test
	public void testTrimWhitespace() throws Exception {
		assertEquals(null, StringUtil.trimWhitespace(null));
		assertEquals("", StringUtil.trimWhitespace(""));
		assertEquals("", StringUtil.trimWhitespace(" "));
		assertEquals("a", StringUtil.trimWhitespace(" a "));
		assertEquals("ab c", StringUtil.trimWhitespace("ab c"));
		assertEquals("a", StringUtil.trimWhitespace("a"));
	}

	@Test
	public void testTrimAllWhitespace() throws Exception {
		assertEquals(null, StringUtil.trimAllWhitespace(null));
		assertEquals("", StringUtil.trimAllWhitespace(""));
		assertEquals("", StringUtil.trimAllWhitespace(" "));
		assertEquals("a", StringUtil.trimAllWhitespace(" a "));
		assertEquals("abc", StringUtil.trimAllWhitespace("ab c"));
		assertEquals("a", StringUtil.trimAllWhitespace("a"));
	}

	@Test
	public void testStartsWithIgnoreCase() throws Exception {
		assertEquals(false, StringUtil.startsWithIgnoreCase(null, null));
		assertEquals(false, StringUtil.startsWithIgnoreCase("abc", "abcd"));
		assertEquals(true, StringUtil.startsWithIgnoreCase("abcdef", "abcd"));
		assertEquals(true, StringUtil.startsWithIgnoreCase("abcdef", "AbcD"));

	}

	@Test
	public void testEndsWithIgnoreCase() throws Exception {
		assertEquals(false, StringUtil.endsWithIgnoreCase(null, null));
		assertEquals(false, StringUtil.endsWithIgnoreCase("bcd", "abcd"));
		assertEquals(true, StringUtil.endsWithIgnoreCase("abcdef", "cdef"));
		assertEquals(true, StringUtil.endsWithIgnoreCase("abcdef", "CDef"));
	}

	@Test
	public void testCountOccurrencesOf() throws Exception {
		assertEquals(0, StringUtil.countOccurrencesOf(null, null));
		assertEquals(0, StringUtil.countOccurrencesOf("", ""));
		assertEquals(0, StringUtil.countOccurrencesOf("", null));
		assertEquals(0, StringUtil.countOccurrencesOf(null, ""));
		assertEquals(3, StringUtil.countOccurrencesOf("asdfasdfasdf", "asdf"));
		assertEquals(1,
				StringUtil.countOccurrencesOf("asdfasdfasdf", "asdfasdf"));
	}

	@Test
	public void testReplace() throws Exception {
		assertEquals(null, StringUtil.replace(null, "a", "A"));
		assertEquals("", StringUtil.replace("", "a", "A"));
		assertEquals("abc", StringUtil.replace("abc", "", "A"));
		assertEquals("abc", StringUtil.replace("abc", null, "A"));
		assertEquals("abc", StringUtil.replace("abc", "a", null));
		assertEquals("Abc", StringUtil.replace("abc", "a", "A"));
		assertEquals("ABcde", StringUtil.replace("abcde", "ab", "AB"));
	}

	@Test
	public void testDelete() throws Exception {
		assertEquals("abcdeabcde", StringUtil.delete("abcdeabcde", "abcdef"));
		assertEquals("cdecde", StringUtil.delete("abcdeabcde", "ab"));
	}

	@Test
	public void testDeleteAnyChar() throws Exception {
		assertEquals("", StringUtil.deleteAnyChar("", ""));
		assertEquals(null, StringUtil.deleteAnyChar(null, null));
		assertEquals(null, StringUtil.deleteAnyChar(null, ""));
		assertEquals("", StringUtil.deleteAnyChar("", null));
		assertEquals("cece", StringUtil.deleteAnyChar("abcdeabcde", "abd"));
	}

	@Test
	public void testCapitalizeAndUncapitalize() throws Exception {
		assertEquals("This", StringUtil.capitalize("this"));
		assertEquals("THIS", StringUtil.capitalize("THIS"));

		assertEquals("tHIS", StringUtil.uncapitalize("THIS"));
		assertEquals("this", StringUtil.uncapitalize("This"));
	}

	@Test
	public void testAddStringToArray() throws Exception {
		String[] arr1 = new String[] { "a", "b", "c" };
		assertArrayEquals(new String[] { "d" },
				StringUtil.addStringToArray(ArrayUtil.EMPTY_STRING_ARRAY, "d"));
		assertArrayEquals(new String[] { "a", "b", "c", "d" },
				StringUtil.addStringToArray(arr1, "d"));
		assertArrayEquals(new String[] { "a", "b", "c", "d", "e" },
				StringUtil.addStringToArray(arr1, "d", "e"));
		assertArrayEquals(new String[] { "a", "b", "c", "a", "b", "c" },
				StringUtil.addStringToArray(arr1, "a", "b", "c"));
	}

	@Test
	public void testMergeStringArrays() throws Exception {
		String[] arr1 = new String[] { "a", "b", "c" };
		assertArrayEquals(new String[] { "a", "b", "c" },
				StringUtil.mergeStringArrays(arr1, "a", "b", "c"));
		assertArrayEquals(new String[] { "a", "b", "c", "d" },
				StringUtil.mergeStringArrays(arr1, "a", "b", "c", "d"));
	}

	@Test
	public void testSortStringArray() throws Exception {
		String[] arr1 = new String[] { "a", "c", "b" };
		assertArrayEquals(new String[] { "a", "b", "c" },
				StringUtil.sortStringArray(arr1));
		assertArrayEquals(ArrayUtil.EMPTY_STRING_ARRAY,
				StringUtil.sortStringArray(null));
	}

	@Test
	public void testToStringArray() throws Exception {
		List<String> emptyList = Collections.emptyList();
		assertArrayEquals(ArrayUtil.EMPTY_STRING_ARRAY,
				StringUtil.toStringArray(null));
		assertArrayEquals(ArrayUtil.EMPTY_STRING_ARRAY,
				StringUtil.toStringArray(emptyList));

		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		assertArrayEquals(new String[] { "a", "b", "c" },
				StringUtil.toStringArray(list));
	}

	@Test
	public void testTrimArrayElements() throws Exception {
		assertArrayEquals(new String[] {},
				StringUtil.trimArrayElements(ArrayUtil.EMPTY_STRING_ARRAY));
		assertArrayEquals(new String[] { "a", "b", "c", "" },
				StringUtil.trimArrayElements(" a", "b ", " c ", null));
	}

	@Test
	public void testRemoveDuplicateStrings() throws Exception {
		assertArrayEquals(new String[] { "a", "b", "c" },
				StringUtil.removeDuplicateStrings("a", "a", "a", "b", "b", "c"));
	}

	@Test
	public void testIsMatch() throws Exception {
		boolean match = StringUtil.isMatch("123", "\\d*");
		assertEquals(true, match);
		match = StringUtil.isMatch("123", "\\D*");
		assertEquals(false, match);
		match = StringUtil
				.isMatch("166.111.3.167",
						"((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
		assertEquals(true, match);
		match = StringUtil
				.isMatch("166.111.3.1673",
						"((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
		assertEquals(false, match);
		assertEquals(true, StringUtil.isMatch("中文", "^[\u4e00-\u9fa5]+$"));
		assertEquals(false, StringUtil.isMatch("abcdefg", "^[\u4e00-\u9fa5]+$"));

		match = StringUtil.isMatch("111", "\\d{3}");
		assertEquals(true, match);

		match = StringUtil.isMatch("1", "\\d{1}");
		assertEquals(true, match);

		match = StringUtil.isMatch("1", "\\d{2}");
		assertEquals(false, match);
	}

	@Test
	public void testJoinArrayElementToString() throws Exception {
		assertEquals("",
				StringUtil.joinArrayElementToString("abc.", "", ",", true));
		assertEquals("abc.1.xyz,abc.2.xyz,abc.3.xyz",
				StringUtil.joinArrayElementToString("abc.", ".xyz", ",", true,
						"1", "2", "3"));
		assertEquals("abc.1,abc.2,abc.3,", StringUtil.joinArrayElementToString(
				"abc.", "", ",", false, "1", "2", "3"));
		assertEquals("a.1||a.2||a.3", StringUtil.joinArrayElementToString("a.",
				"", "||", true, "1", "2", "3"));
		assertEquals("a.1||a.2||a.3||", StringUtil.joinArrayElementToString(
				"a.", "", "||", false, "1", "2", "3"));
		assertEquals("a.1|||a.2|||a.3", StringUtil.joinArrayElementToString(
				"a.", "", "|||", true, "1", "2", "3"));
		assertEquals("a.1|||a.2|||a.3|||", StringUtil.joinArrayElementToString(
				"a.", "", "|||", false, "1", "2", "3"));
	}
}
