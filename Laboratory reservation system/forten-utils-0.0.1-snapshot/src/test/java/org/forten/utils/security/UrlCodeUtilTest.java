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
public class UrlCodeUtilTest {
	@Test
	public void testEncodeAndDecode() throws Exception {
		String str = UrlCodeUtil.encode("中华人民共和国");
		String newstr = UrlCodeUtil.decode(str);
		assertEquals("中华人民共和国", newstr);

		str = UrlCodeUtil.encode("中华人民共和国中华人民共和国", "GBK");
		newstr = UrlCodeUtil.decode(str, "GBK");
		assertEquals("中华人民共和国中华人民共和国", newstr);

		str = UrlCodeUtil.encode("");
		newstr = UrlCodeUtil.decode(str);
		assertEquals("", str);
		assertEquals("", newstr);

		str = UrlCodeUtil.encode(null);
		newstr = UrlCodeUtil.decode(null);
		assertEquals("", str);
		assertEquals("", newstr);
	}

	@Test(expected = RuntimeException.class)
	public void testException() throws Exception {
		UrlCodeUtil.encode("中华人民共和国", "gbks");
		fail("未捕获到应抛出的RuntimeException");
	}
}
