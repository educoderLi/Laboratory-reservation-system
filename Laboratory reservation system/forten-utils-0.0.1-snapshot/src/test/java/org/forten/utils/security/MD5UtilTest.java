/*
 * Copyright 2012 ITC Tsinghua University. All Rights Reserved.
 */
package org.forten.utils.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * 
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * 
 * @since 2012-5-8
 */
public class MD5UtilTest {
	private String result;

	@Test
	public void testEncode() throws Exception {
		String nullStr = null;
		System.out.println(MD5Util.encrypt("123456"));
		result = MD5Util.encrypt("123456", "UTF-8", true);
		assertEquals(32, result.length());

		result = MD5Util.encrypt("", true);
		assertEquals(32, result.length());

		result = MD5Util.encrypt(nullStr, true);
		assertEquals(32, result.length());

		result = MD5Util.encrypt("", false);
		assertEquals(0, result.length());

		result = MD5Util.encrypt(nullStr, false);
		assertEquals(0, result.length());
	}

	@Test(expected = RuntimeException.class)
	public void testException() throws Exception {
		MD5Util.encrypt("中华人民共和国", "GBKs");
		fail("未捕获到应抛出的RuntimeException");
	}

	@Test
	public void testEncryptByteArray() throws Exception {
		String str = "";
		String result = MD5Util.encrypt(str.getBytes());
		assertEquals("", result);

		byte[] b = null;
		result = MD5Util.encrypt(b);
		assertEquals("", result);

		result = MD5Util.encrypt(b, true);
		assertEquals(32, result.length());

		str = "";
		result = MD5Util.encrypt(str.getBytes(), true);
		assertEquals(32, result.length());
	}
}
