/*
 * Copyright 2012 ITC Tsinghua University. All Rights Reserved.
 */
package org.forten.utils.security;

import java.io.ByteArrayOutputStream;

import org.apache.log4j.Logger;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.forten.utils.common.NumberUtil;
import org.forten.utils.common.StringUtil;
import org.forten.utils.system.LogUtil;

/**
 * 
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * 
 * @since 2012-5-8
 */
public final class TripletsDESUtil {
	private static Logger log = Logger.getLogger(TripletsDESUtil.class);

	private static final String KEY_LENGTH_ERROR_MSG = "密钥的长度长必须是16或24";
	private static final String GENERATE_KEY_LENGTH_ERROR_MSG = "密钥的长度必须是16或24，而输入的参数值是%d，系统使用默认值24为其重新付值";
	private static final String GENERATE_KEY_MSG = "生成密钥为：%s";
	private static final String NOT_HEX_MSG = "密文不是16进制数字";
	private static final String DATA_LENGHT_NOT_EVEN_MSG = "密文长度非偶数";
	private static final String TEXT_MSG = "3DES加密前的明文：%s";
	private static final String TRIPLETS_DES_TEXT_MSG = "3DES加密后的密文：%s";
	private static final String ENCRYPT_STRING_IS_NULL_MSG = "加密前的明文不能是null，系统默认为其付值为空字符串";
	private static final String ENCRYPT_STRING_IS_EMPTY_MSG = "加密前的明文是空或空字符串，系统默认返回空字符串";
	private static final String DECRYPT_STRING_IS_NULL_MSG = "不能解密空字符串文本，系统直接返回空字符串";
	private static final String ENCRYPT_ERROR_MSG = "加密错误： %s : %s";
	private static final String DECRYPT_ERROR_MSG = "解密错误： %s : %s";

	private static String hexString = "0123456789ABCDEF";

	private TripletsDESUtil() {

	}

	/**
	 * 3DES加密方法
	 * 
	 * @param key
	 *            加密所使用的密钥
	 * @param in
	 *            待加密明文
	 * @param charset
	 *            使用的字符集名称
	 * @param doEmptyTextString
	 *            是否对空文本明文进行加密
	 *            <p>
	 *            <code>true</code>
	 *            :明文为null时先将origin付值为""，然后进行3DES加密；明文为""时直接进行3DES加密
	 *            </p>
	 *            <p>
	 *            <code>false</code>:明文为null或""时，直接返回""
	 *            </p>
	 * @return 加密后的密文文本
	 */
	public static String encrypt(String key, String in, String charset,
			boolean doEmptyTextString) {
		LogUtil.debug(log, TEXT_MSG, in);
		String result = "";
		if (!doEmptyTextString) {
			// 明文为null或""时，直接返回""
			if (!StringUtil.hasText(in)) {
				LogUtil.debug(log, ENCRYPT_STRING_IS_EMPTY_MSG);
				return result;
			}
		} else {
			// 明文为null时直接返回""；明文为""时跳出分支，进行后续的3DES加密
			if (in == null) {
				LogUtil.warn(log, ENCRYPT_STRING_IS_NULL_MSG);
				in = "";
			}
		}
		try {
			byte[] hexkey = null;
			byte[] out = null;
			// 转换密钥
			hexkey = string2Byte(key);
			// 3DES加密
			out = tripleDES(true, hexkey, in.getBytes(charset));
			// 转换成hex字符串
			result = byte2String(out);
			LogUtil.debug(log, TRIPLETS_DES_TEXT_MSG, result);
		} catch (Exception e) {
			LogUtil.error(log, ENCRYPT_ERROR_MSG, e.getClass().getName(),
					e.getMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			throw new RuntimeException(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 3DES加密方法，不对空字符串进行加密处理
	 * 
	 * @param key
	 *            加密所使用的密钥
	 * @param in
	 *            待加密明文
	 * @param charset
	 *            使用的字符集名称
	 * @return 加密后的密文文本
	 */
	public static String encrypt(String key, String in, String charset) {
		return encrypt(key, in, charset, false);
	}

	/**
	 * 使用UTF-8字符集进行3DES加密的方法
	 * 
	 * @param key
	 *            加密所使用的密钥
	 * @param in
	 *            待加密明文
	 * @param doEmptyTextString
	 *            是否对空文本明文进行加密
	 *            <p>
	 *            <code>true</code>:明文为null时直接返回""；明文为""时进行后续的3DES加密
	 *            </p>
	 *            <p>
	 *            <code>false</code>:明文为null或""时，直接返回""
	 *            </p>
	 * @return 加密后的密文文本
	 */
	public static String encrypt(String key, String in,
			boolean doEmptyTextString) {
		return encrypt(key, in, "UTF-8", doEmptyTextString);
	}

	/**
	 * 使用UTF-8字符集进行3DES加密的方法，不对空字符串进行加密处理
	 * 
	 * @param key
	 *            加密所使用的密钥
	 * @param in
	 *            待加密明文
	 * @return 加密后的密文文本
	 */
	public static String encrypt(String key, String in) {
		return encrypt(key, in, "UTF-8", false);
	}

	/**
	 * 3DES解密方法
	 * 
	 * @param key
	 *            解密所使用的密钥
	 * @param in
	 *            待解密的密文
	 * @param charset
	 *            使用的字符集名称
	 * @return 解密后的明文文本
	 */
	public static String decrypt(String key, String in, String charset) {
		LogUtil.debug(log, TRIPLETS_DES_TEXT_MSG, in);
		String result = "";
		if (!StringUtil.hasText(in)) {
			LogUtil.warn(log, DECRYPT_STRING_IS_NULL_MSG);
			return result;
		}
		try {
			byte[] hexkey = null;
			byte[] cipher = null;
			byte[] out = null;
			// 转换密钥
			hexkey = string2Byte(key);
			// 转换密文
			cipher = string2Byte(in);
			// 解密
			out = tripleDES(false, hexkey, cipher);
			result = new String(out, charset);
			LogUtil.debug(log, TEXT_MSG, result);
		} catch (Exception e) {
			LogUtil.error(log, DECRYPT_ERROR_MSG, e.getClass().getName(),
					e.getMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			throw new RuntimeException(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 使用UTF-8字符集进行3DES解密的方法
	 * 
	 * @param key
	 *            解密所使用的密钥
	 * @param in
	 *            待解密的密文
	 * @return 解密后的明文文本
	 */
	public static String decrypt(String key, String in) {
		return decrypt(key, in, "UTF-8");
	}

	/**
	 * 随机生成密钥的方法
	 * 
	 * @param length
	 *            密钥长度，只能用16或24，如果不是这两个值，则系统将生成24长度的密钥
	 * @return 生成的密钥
	 */
	public static String generateKey(int length) {
		if (length != 16 && length != 24) {
			LogUtil.warn(log, GENERATE_KEY_LENGTH_ERROR_MSG, length);
			length = 24;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length * 2; i++) {
			int num = NumberUtil.getRandomInteger(0, 15);
			sb.append(hexString.charAt(num));
		}
		String result = sb.toString();
		LogUtil.debug(log, GENERATE_KEY_MSG, result);
		return result;
	}

	/*
	 * 16进制数据转换成字符串
	 */
	private static String byte2String(byte[] in) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < in.length; i++) {
			out.append(hexString.charAt((in[i] & 0xF0) >> 4));
			out.append(hexString.charAt(in[i] & 0x0F));
		}
		return out.toString();
	}

	/*
	 * 16进制形式的字符串转换成数字
	 */
	private static byte[] string2Byte(String in) {
		in = in.trim();
		in = in.toUpperCase();
		if (in.length() % 2 != 0) {
			LogUtil.error(log, DATA_LENGHT_NOT_EVEN_MSG);
			throw new DataLengthException(DATA_LENGHT_NOT_EVEN_MSG);
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream(in.length() / 2);
		// 将每2个16进制整数组装成一个字符
		for (int i = 0; i < in.length(); i += 2) {
			int hb = hexString.indexOf(in.charAt(i)) << 4;
			int lb = hexString.indexOf(in.charAt(i + 1));
			if ((hb == -1) || (lb == -1)) {
				LogUtil.error(log, NOT_HEX_MSG);
				throw new IllegalArgumentException(NOT_HEX_MSG);
			}
			baos.write(hb | lb);
		}
		return baos.toByteArray();
	}

	/*
	 * 3DES 算法实现，ECB方式 PKCS#7填充
	 * 
	 * @throws InvalidCipherTextException
	 */
	private static byte[] tripleDES(boolean isEncrypt, byte[] key, byte[] in)
			throws InvalidCipherTextException {
		BufferedBlockCipher cipher;
		KeyParameter keypara;

		// 初始化，使用PKCS7方式填充
		cipher = new PaddedBufferedBlockCipher(new DESedeEngine());

		// 数组key的长度应该是16或24个字符
		if ((key.length != 16) && (key.length != 24)) {
			LogUtil.error(log, KEY_LENGTH_ERROR_MSG);
			throw new DataLengthException(KEY_LENGTH_ERROR_MSG);
		}
		// 初始化密钥
		keypara = new KeyParameter(key);
		// 设置加解密方式和密钥
		cipher.init(isEncrypt, keypara);

		// 计算输出缓冲区大小
		int outsize = cipher.getOutputSize(in.length);
		byte[] out = new byte[outsize];
		// 处理数据（不包含最后一包输出）
		int outlen = cipher.processBytes(in, 0, in.length, out, 0);
		// 处理最后一包输出
		outlen += cipher.doFinal(out, outlen);
		// 如果是解密，长度会小于size，因为有填充
		if (outlen < outsize) {
			// 去掉冗余的字符
			byte[] tmp = new byte[outlen];
			System.arraycopy(out, 0, tmp, 0, outlen);
			out = tmp;
		}
		return out;
	}
}
