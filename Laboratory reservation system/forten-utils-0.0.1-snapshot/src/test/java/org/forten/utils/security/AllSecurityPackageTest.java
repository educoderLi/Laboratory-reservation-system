/*
 * Copyright 2012 ITC Tsinghua University. All Rights Reserved.
 */
package org.forten.utils.security;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * 
 * @since 2012-5-8
 */
@RunWith(Suite.class)
@SuiteClasses({ Base64UtilTest.class, MD5UtilTest.class,
		TripletsDESUtilTest.class, UrlCodeUtilTest.class })
public class AllSecurityPackageTest {

}
