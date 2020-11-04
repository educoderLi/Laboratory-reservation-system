/*
 * Copyright 2012 ITC Tsinghua University. All Rights Reserved.
 */
package org.forten.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.forten.utils.collection.ArrayUtil;
import org.forten.utils.common.StringUtil;
import org.forten.utils.system.LogUtil;

/**
 * 
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * 
 * @since 2012-5-8
 */
public final class MD5Util {
	private static Logger log = Logger.getLogger(MD5Util.class);

	private static final String TEXT_MSG = "MD5加密前的明文：%s";
	private static final String MD5_TEXT_MSG = "MD5加密后的密文：%s";
	private static final String ORIGIN_IS_NULL_MSG = "加密前的明文不能是null，系统默认为其付值为空字符串";
	private static final String ORIGIN_IS_EMPTY_MSG = "加密前的明文内容无效，系统默认返回空字符串";
	private static final String ENCRYPT_ERROR_MSG = "加密错误：%s : %s";

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private MD5Util() {

	}

	/**
	 * MD5加密方法
	 * 
	 * @param origin
	 *            明文字符串
	 * @param charset
	 *            使用的字符集名称
	 * @param doEmptyTextString
	 *            是否对空文本明文进行加密
	 *            <p>
	 *            <code>true</code>
	 *            :明文为null时先将origin付值为""，然后进行MD5加密；明文为""时直接进行MD5加密
	 *            </p>
	 *            <p>
	 *            <code>false</code>:明文为null或""时，直接返回""
	 *            </p>
	 * @return MD5密文
	 */
	public static String encrypt(String origin, String charset,
			boolean doEmptyTextString) {
		LogUtil.debug(log, TEXT_MSG, origin);
		if (!doEmptyTextString) {
			// 明文为null或""时，直接返回""
			if (!StringUtil.hasText(origin)) {
				LogUtil.debug(log, ORIGIN_IS_EMPTY_MSG);
				return "";
			}
		} else {
			// 明文为null时直接返回""；明文为""时跳出分支，进行后续的MD5加密
			if (origin == null) {
				LogUtil.warn(log, ORIGIN_IS_NULL_MSG);
				origin = "";
			}
		}
		String resultString = new String(origin);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToString(md.digest(resultString
					.getBytes(charset)));
		} catch (Exception e) {
			LogUtil.error(log, ENCRYPT_ERROR_MSG, e.getClass().getName(),
					e.getMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			throw new RuntimeException(e.getMessage(), e);
		}
		LogUtil.debug(log, MD5_TEXT_MSG, resultString);
		return resultString;
	}

	/**
	 * MD5加密，不对空字符串进行加密处理
	 * 
	 * @param origin
	 *            明文字符串
	 * @param charset
	 *            使用的字符集名称
	 * @return MD5密文
	 */
	public static String encrypt(String origin, String charset) {
		return encrypt(origin, charset, false);
	}

	/**
	 * 用UTF-8字符集对文本进行MD5加密
	 * 
	 * @param origin
	 *            明文字符串
	 * @param doEmptyTextString
	 *            是否对空文本明文进行加密
	 *            <p>
	 *            <code>true</code>:明文为null时直接返回""；明文为""时进行后续的MD5加密
	 *            </p>
	 *            <p>
	 *            <code>false</code>:明文为null或""时，直接返回""
	 *            </p>
	 * @return MD5密文
	 */
	public static String encrypt(String origin, boolean doEmptyTextString) {
		return encrypt(origin, "UTF-8", doEmptyTextString);
	}

	/**
	 * 用UTF-8字符集对文本进行MD5加密，不对空字符串进行加密处理
	 * 
	 * @param origin
	 *            明文字符串
	 * @return MD5密文
	 */
	public static String encrypt(String origin) {
		return encrypt(origin, "UTF-8", false);
	}

	public static String encrypt(byte[] bytes, boolean doEmptyTextString) {
		if (!doEmptyTextString) {
			// 明文为null或""时，直接返回""
			if (ArrayUtil.isEmpty(bytes)) {
				LogUtil.debug(log, ORIGIN_IS_EMPTY_MSG);
				return "";
			}
		}else{
			if (ArrayUtil.isEmpty(bytes)) {
				LogUtil.warn(log, ORIGIN_IS_EMPTY_MSG);
				bytes = ArrayUtil.EMPTY_BYTE_ARRAY;
			}
		}
		String resultString = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToString(md.digest(bytes));
		} catch (NoSuchAlgorithmException e) {
		}
		return resultString;
	}

	public static String encrypt(byte[] bytes) {
		return encrypt(bytes, false);
	}

	/*
	 * 转换字节数组为十六进制字串
	 */
	private static String byteArrayToString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/*
	 * 获取十六进制数字
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
