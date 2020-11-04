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
public class Base64UtilTest {
	@Test
	public void testEncodeAndDecode() throws Exception {
		String str = Base64Util.encode("中华人民共和国");
		String newstr = Base64Util.decode(str);
		assertEquals("中华人民共和国", newstr);

		str = Base64Util.encode("中华人民共和国中华人民共和国", "GBK");
		newstr = Base64Util.decode(str, "GBK");
		assertEquals("中华人民共和国中华人民共和国", newstr);

		str = Base64Util.encode("");
		newstr = Base64Util.decode(str);
		assertEquals("", str);
		assertEquals("", newstr);

		str = Base64Util.encode(null);
		newstr = Base64Util.decode(null);
		assertEquals("", str);
		assertEquals("", newstr);
	}

	@Test(expected = RuntimeException.class)
	public void testException() throws Exception {
		Base64Util.encode("中华人民共和国", "gbks");
		fail("未捕获到应抛出的RuntimeException");
	}
}
