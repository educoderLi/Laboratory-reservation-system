/*
 * Copyright 2012 ITC Tsinghua University. All Rights Reserved.
 */
package org.forten.utils.security;

import org.apache.commons.codec.net.URLCodec;
import org.apache.log4j.Logger;
import org.forten.utils.common.StringUtil;
import org.forten.utils.system.LogUtil;

/**
 * URL编码工具类
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * 
 * @since 2012-5-8
 */
public final class UrlCodeUtil {
	private static Logger log = Logger.getLogger(UrlCodeUtil.class);

	private static final String ENCODING_ERROR_MSG = "URL编码异常： %s : %s";
	private static final String DECODING_ERROR_MSG = "URL解码异常： %s : %s";
	private static final String ORIGIN_IS_NULL_MSG = "URL原始文本是null或空字符串，系统默认返回空字符串";
	private static final String CODE_IS_NULL_MSG = "URL编码文本是null或空字符串，系统默认返回空字符串";
	private static final String TEXT_MSG = "URL编码前的文本：%s";
	private static final String URL_CODE_TEXT_MSG = "URL编码后的文本：%s";

	private UrlCodeUtil() {

	}

	/**
	 * 对原始文本进行URL编码的方法，如果原始文本为空或空字符串则直接返回一个空字符串
	 * 
	 * @param origin
	 *            原始文本
	 * @param charset
	 *            编码使用的字符集
	 * @return URL编码文本
	 */
	public static String encode(String origin, String charset) {
		LogUtil.debug(log, TEXT_MSG, origin);
		if (!StringUtil.hasText(origin)) {
			LogUtil.warn(log, ORIGIN_IS_NULL_MSG);
			return "";
		}
		String result = null;
		try {
			result = new URLCodec().encode(origin, charset);
		} catch (Exception e) {
			LogUtil.error(log, ENCODING_ERROR_MSG, e.getClass().getName(),
					e.getMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			throw new RuntimeException(e.getMessage(), e);
		}
		LogUtil.debug(log, URL_CODE_TEXT_MSG, result);
		return result;
	}

	/**
	 * 对编码文本进行URL解码的方法，如果编码文本为空或空字符串则直接返回一个空字符串
	 * 
	 * @param code
	 *            URL编码文本
	 * @param charset
	 *            解码使用的字符集
	 * @return URL原始文本
	 */
	public static String decode(String code, String charset) {
		LogUtil.debug(log, URL_CODE_TEXT_MSG, code);
		if (!StringUtil.hasText(code)) {
			LogUtil.warn(log, CODE_IS_NULL_MSG);
			return "";
		}
		String result = null;
		try {
			result = new URLCodec().decode(code, charset);
		} catch (Exception e) {
			LogUtil.error(log, DECODING_ERROR_MSG, e.getClass().getName(),
					e.getMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			throw new RuntimeException(e.getMessage(), e);
		}
		LogUtil.debug(log, TEXT_MSG, result);
		return result;
	}

	/**
	 * 使用UTF-8字符集对原始文本进行URL编码的方法，如果原始文本为空或空字符串则直接返回一个空字符串
	 * 
	 * @param origin
	 *            原始文本
	 * @return URL编码文本
	 */
	public static String encode(String origin) {
		return encode(origin, "UTF-8");
	}

	/**
	 * 使用UTF-8字符集对URL编码文本进行解码的方法，如果编码文本为空或空字符串则直接返回一个空字符串
	 * 
	 * @param code
	 *            URL编码文本
	 * @return URL解码后的原始文本
	 */
	public static String decode(String code) {
		return decode(code, "UTF-8");
	}
}
