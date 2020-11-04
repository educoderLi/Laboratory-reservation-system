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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.forten.utils.collection.ArrayUtil;
import org.forten.utils.collection.CollectionUtil;

/**
 * String处理工具类。参考springframework的util包下的SpringUtils类写成，主要为了与springframework解耦。
 * 
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Keith Donald
 * @author Rob Harrop
 * @author Rick Evans
 * @author Arjen Poutsma
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-5-7
 */
public final class StringUtil {
	// 私有构造器
	private StringUtil() {

	}

	/**
	 * 检查str是否是<code>null</code>或长度为0
	 * 
	 * @param str
	 *            被检查的String
	 * @return 是<code>null</code>或长度为0返回<code>true</code> 否则返回<code>false</code>
	 */
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 检查str是否包含文本字符
	 * 
	 * @param str
	 *            被检查的String
	 * @return 如果str是<code>null</code>或长度为0或不包含文本字符返回<code>true</code> 否则返回
	 *         <code>false</code>
	 */
	public static boolean hasText(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查str是否包含空白字符
	 * 
	 * @param str
	 *            被检查的String
	 * @return 如果str包含空白字符返回<code>true</code> 若长度为0或不含有空白字符则返回<code>false</code>
	 */
	public static boolean containsWhitespace(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 去除str前后的空白字符
	 * 
	 * @param str
	 *            被操作的String
	 * @return 已经把str前后空白字符去除完成后的字符串
	 */
	public static String trimWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		while (sb.length() > 0
				&& Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 去除str前后中间的所有空白字符
	 * 
	 * @param str
	 *            被操作的String
	 * @return 已经把str前后中间所有空白字符去除完成后的字符串
	 */
	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		int index = 0;
		while (sb.length() > index) {
			if (Character.isWhitespace(sb.charAt(index))) {
				sb.deleteCharAt(index);
			} else {
				index++;
			}
		}
		return sb.toString();
	}

	/**
	 * 检查str是否是以prefix开头的 prefix忽略大小写
	 * 
	 * @param str
	 *            被检查的String
	 * @param prefix
	 *            忽略大小写的字符串前缀
	 * @return 如果str或prefix任一个为<code>null</code> 返回<code>false</code>
	 *         如果str的长度小于prefix的长度 返回<code>false</code> str是以prefix(忽略大小写)为前缀返回
	 *         <code>true</code> 否则返回<code>false</code>
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	/**
	 * 检查str是否是以suffix结束的 suffix忽略大小写
	 * 
	 * @param str
	 *            被检查的String
	 * @param suffix
	 *            忽略大小写的字符串后缀
	 * @return 如果str或后缀是<code>null</code> 返回<code>false</code>
	 *         如果str的长度小于suffix的长度 返回<code>false</code> str是以suffix(忽略大小写)为后缀返回
	 *         <code>true</code> 否则返回<code>false</code>
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		if (str == null || suffix == null) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length())
				.toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

	/**
	 * 统计子字符串在str中的出现次数
	 * 
	 * @param str
	 *            被统计的字符串
	 * @param sub
	 *            子字符串
	 * @return 子串在str中出现过的次数
	 */
	public static int countOccurrencesOf(String str, String sub) {
		if (str == null || sub == null || str.length() == 0
				|| sub.length() == 0) {
			return 0;
		}
		int count = 0;
		int pos = 0;
		int idx;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * 将inString中的oldPattern替换为newPattern
	 * 
	 * @param inString
	 *            被操作的字符串
	 * @param oldPattern
	 *            将被替换的老字符串
	 * @param newPattern
	 *            被替换成的新字符串
	 * @return 替换完成后的字符串
	 */
	public static String replace(String inString, String oldPattern,
			String newPattern) {
		if (!hasLength(inString) || !hasLength(oldPattern)
				|| newPattern == null) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sb.append(inString.substring(pos));
		// remember to append any characters to the right of a match
		return sb.toString();
	}

	/**
	 * 删除inString中的所有pattern字符串
	 * 
	 * @param inString
	 *            被操作的字符串
	 * @param pattern
	 *            被刪除的字符串
	 * @return 刪除特定pattern后的字符串
	 */
	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}

	/**
	 * 删除inString中的所有在charsToDelete中的字符
	 * 
	 * @param inString
	 *            被操作的字符串
	 * @param charsToDelete
	 *            一个由将被删除的字符组成的字符串
	 * @return 删除所有charsToDelete中含有的字符后的字符串
	 */
	public static String deleteAnyChar(String inString, String charsToDelete) {
		if (!hasLength(inString) || !hasLength(charsToDelete)) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 *            被操作的字符串
	 * @return 首字母大写后的字符串
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	/**
	 * 首字母小写
	 * 
	 * @param str
	 *            被操作的字符串
	 * @return 首字母小写后的字符串
	 */
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	private static String changeFirstCharacterCase(String str,
			boolean capitalize) {
		if (!hasText(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		if (capitalize) {
			sb.append(Character.toUpperCase(str.charAt(0)));
		} else {
			sb.append(Character.toLowerCase(str.charAt(0)));
		}
		sb.append(str.substring(1));
		return sb.toString();
	}

	/**
	 * 向array1中加入array2中的字符串元素
	 * 
	 * @param array1
	 *            添加目标字符串数组
	 * @param array2
	 *            将被加入的字符串元素
	 * @return 添加完成的字符串数组
	 */
	public static String[] addStringToArray(String[] array1, String... array2) {
		if (ArrayUtil.isEmpty(array1)) {
			return array2;
		}
		if (ArrayUtil.isEmpty(array2)) {
			return array1;
		}
		String[] newArr = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, newArr, 0, array1.length);
		System.arraycopy(array2, 0, newArr, array1.length, array2.length);
		return newArr;
	}

	/**
	 * 合并两个字符串数组 排除重复的元素
	 * 
	 * @param array1
	 *            字符串数组1
	 * @param array2
	 *            字符串数组2
	 * @return 合并排重后的新字符串数组
	 */
	public static String[] mergeStringArrays(String[] array1, String... array2) {
		if (ArrayUtil.isEmpty(array1)) {
			return array2;
		}
		if (ArrayUtil.isEmpty(array2)) {
			return array1;
		}
		List<String> result = new ArrayList<String>();
		result.addAll(Arrays.asList(array1));
		for (String str : array2) {
			if (!result.contains(str)) {
				result.add(str);
			}
		}
		return toStringArray(result);
	}

	/**
	 * 排序字符串数组
	 * 
	 * @param array
	 *            被排序的字符串数组
	 * @return 排序完成后的字符串数组
	 */
	public static String[] sortStringArray(String[] array) {
		if (ArrayUtil.isEmpty(array)) {
			return ArrayUtil.EMPTY_STRING_ARRAY;
		}
		Arrays.sort(array);
		return array;
	}

	/**
	 * 将字符串容器转换成为字符串数组
	 * 
	 * @param collection
	 *            字符串容器
	 * @return 字符串数组
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (CollectionUtil.isEmpty(collection)) {
			return ArrayUtil.EMPTY_STRING_ARRAY;
		}
		return collection.toArray(new String[collection.size()]);
	}

	/**
	 * 去除字符串数组中每一个元素的空白字符
	 * 
	 * @param array
	 *            被操作字符串数组
	 * @return 去除完元素空白字符的字符串数组
	 */
	public static String[] trimArrayElements(String... array) {
		if (ArrayUtil.isEmpty(array)) {
			return ArrayUtil.EMPTY_STRING_ARRAY;
		}
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			String element = array[i];
			result[i] = (element != null ? element.trim() : "");
		}
		return result;
	}

	/**
	 * 去除字符串数组中重复的元素
	 * 
	 * @param array
	 *            被操作的字符串数组
	 * @return 排重后的字符串数组
	 */
	public static String[] removeDuplicateStrings(String... array) {
		if (ArrayUtil.isEmpty(array)) {
			return ArrayUtil.EMPTY_STRING_ARRAY;
		}
		Set<String> set = new TreeSet<String>();
		for (String element : array) {
			set.add(element);
		}
		return toStringArray(set);
	}

	/**
	 * 判断字符串是否与模式匹配
	 * 
	 * @param input
	 *            被判断的字符串
	 * @param pattern
	 *            模式
	 * @return 若相匹配返回<code>true</code>，否则返回<code>false</code>
	 */
	public static boolean isMatch(String input, String pattern) {
		return Pattern.matches(pattern, input);
	}

	/**
	 * 连接字符串数组中的每个元素成为一个字符串，用分隔符隔开
	 * 
	 * @param perfix
	 *            每个元素的前缀
	 * @param suffix
	 *            每个元素的后缀
	 * @param separator
	 *            元素之间的分隔符
	 * @param deleteLastSeparator
	 *            是否删除最后一个分隔符
	 * @param array
	 *            字符串数组
	 * @return 连接完成的字符串
	 */
	public static String joinArrayElementToString(String perfix, String suffix,
			String separator, boolean deleteLastSeparator, String... array) {
		if (ArrayUtil.isEmpty(array)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (String string : array) {
			sb.append(perfix);
			sb.append(string);
			sb.append(suffix);
			sb.append(separator);
		}
		if (deleteLastSeparator)
			sb.delete(sb.length() - separator.length(), sb.length());
		return sb.toString();
	}
}
