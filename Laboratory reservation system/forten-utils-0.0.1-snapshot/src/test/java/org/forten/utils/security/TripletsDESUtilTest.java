/*
 * Copyright 2012 ITC Tsinghua University. All Rights Reserved.
 */
package org.forten.utils.security;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * 
 * @since 2012-5-8
 */
public class TripletsDESUtilTest {
	private static final String key = "5A45615B3D22156B6D1433275C231A346F5D41371C7B5F42";
	private String enText;
	private String deText;

	@Test
	public void testEncryptAndDecrypt() throws Exception {
		enText = TripletsDESUtil.encrypt(key, "中华人民共和国", "GBK", true);
		deText = TripletsDESUtil.decrypt(key, enText, "GBK");
		assertEquals("中华人民共和国", deText);

		enText = TripletsDESUtil.encrypt(key, "", true);
		deText = TripletsDESUtil.decrypt(key, enText);
		assertTrue(enText.length() > 0);
		assertTrue(deText.length() == 0);
		assertEquals("", deText);

		enText = TripletsDESUtil.encrypt(key, null, true);
		deText = TripletsDESUtil.decrypt(key, enText);
		assertTrue(enText.length() > 0);
		assertTrue(deText.length() == 0);
		assertEquals("", deText);

		enText = TripletsDESUtil.encrypt(key, "", false);
		deText = TripletsDESUtil.decrypt(key, enText);
		assertTrue(enText.length() == 0);
		assertTrue(deText.length() == 0);
		assertEquals(enText, deText);

		enText = TripletsDESUtil.encrypt(key, null, false);
		deText = TripletsDESUtil.decrypt(key, enText);
		assertTrue(enText.length() == 0);
		assertTrue(deText.length() == 0);
		assertEquals(enText, deText);
	}

	@Test
	public void testGenerateKey() throws Exception {
		String key = TripletsDESUtil.generateKey(24);
		assertEquals(48, key.length());

		key = TripletsDESUtil.generateKey(16);
		assertEquals(32, key.length());

		key = TripletsDESUtil.generateKey(0);
		assertEquals(48, key.length());
	}

	@Test(expected = RuntimeException.class)
	public void testUnsupportedEncodingException() throws Exception {
		TripletsDESUtil.encrypt(key, "123456", "GBKs");
		fail("未捕获到应抛出的RuntimeException");
	}

	@Test(expected = RuntimeException.class)
	public void testDataLengthException() throws Exception {
		TripletsDESUtil.decrypt(key, "asdft");
		fail("未捕获到应抛出的RuntimeException");
	}
}
