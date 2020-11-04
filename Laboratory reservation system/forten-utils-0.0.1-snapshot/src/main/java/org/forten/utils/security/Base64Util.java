/*
 * Copyright 2012 ITC Tsinghua University. All Rights Reserved.
 */
package org.forten.utils.security;

import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.forten.utils.common.StringUtil;
import org.forten.utils.system.LogUtil;

/**
 * Base 64编解码工具类
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * 
 * @since 2012-5-8
 */
public final class Base64Util {
	private static Logger log = Logger.getLogger(Base64Util.class);

	private static final String ENCODING_ERROR_MSG = "BASE64编码异常： %s : %s";
	private static final String DECODING_ERROR_MSG = "BASE64解码异常： %s : %s";
	private static final String ORIGIN_IS_NULL_MSG = "BASE64原始文本是null或空字符串，系统默认返回空字符串";
	private static final String CODE_IS_NULL_MSG = "BASE64编码文本是null或空字符串，系统默认返回空字符串";
	private static final String TEXT_MSG = "BASE64编码前的文本：%s";
	private static final String BASE64_TEXT_MSG = "BASE64编码后的文本：%s";

	private Base64Util() {

	}

	/**
	 * 对原始文本进行Base 64编码的方法，如果原始文本为空或空字符串则直接返回一个空字符串
	 * 
	 * @param origin
	 *            原始文本
	 * @param charset
	 *            编码使用的字符集
	 * @return Base 64编码文本
	 */
	public static String encode(String origin, String charset) {
		LogUtil.debug(log, TEXT_MSG, origin);
		if (!StringUtil.hasText(origin)) {
			LogUtil.warn(log, ORIGIN_IS_NULL_MSG);
			return "";
		}
		String codeText = null;
		try {
			codeText = new String(Base64.encode(origin.getBytes(charset)),
					charset);
		} catch (Exception e) {
			LogUtil.error(log, ENCODING_ERROR_MSG, e.getClass().getName(),
					e.getMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			throw new RuntimeException(e.getMessage(), e);
		}
		LogUtil.debug(log, BASE64_TEXT_MSG, codeText);
		return codeText;
	}

	/**
	 * 对编码文本进行Base 64解码的方法，如果编码文本为空或空字符串则直接返回一个空字符串
	 * 
	 * @param code
	 *            Base 64编码文本
	 * @param charset
	 *            解码使用的字符集
	 * @return Base 64原始文本
	 */
	public static String decode(String code, String charset) {
		LogUtil.debug(log, BASE64_TEXT_MSG, code);
		if (!StringUtil.hasText(code)) {
			LogUtil.warn(log, CODE_IS_NULL_MSG);
			return "";
		}
		String codeText = null;
		try {
			codeText = new String(Base64.decode(code.getBytes(charset)),
					charset);
		} catch (Exception e) {
			LogUtil.error(log, DECODING_ERROR_MSG, e.getClass().getName(),
					e.getMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			throw new RuntimeException(e.getMessage(), e);
		}
		LogUtil.debug(log, TEXT_MSG, codeText);
		return codeText;
	}

	/**
	 * 使用UTF-8字符集对原始文本进行Base 64编码的方法，如果原始文本为空或空字符串则直接返回一个空字符串
	 * 
	 * @param origin
	 *            原始文本
	 * @return Base 64编码文本
	 */
	public static String encode(String origin) {
		return encode(origin, "UTF-8");
	}

	/**
	 * 使用UTF-8字符集对Base 64编码文本进行解码的方法，如果编码文本为空或空字符串则直接返回一个空字符串
	 * 
	 * @param code
	 *            Base 64编码文本
	 * @return Base 64解码后的原始文本
	 */
	public static String decode(String code) {
		return decode(code, "UTF-8");
	}
}
