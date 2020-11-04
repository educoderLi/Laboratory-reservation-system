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
package org.forten.utils.system;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

/**
 * Java Bean属性拷贝工具
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-5-7
 */
public final class BeanPropertyUtil {
	private static Logger log = Logger.getLogger(BeanPropertyUtil.class);

	private static final String OBJECT_NOT_NULL_MSG = "对象不可为null";

	private BeanPropertyUtil() {

	}

	/**
	 * Java Bean属性拷贝
	 * 
	 * @param dist
	 *            目标对象
	 * @param orig
	 *            原始对象
	 */
	public static void copy(Object dist, Object orig) {
		Assert.notNull(dist, OBJECT_NOT_NULL_MSG);
		Assert.notNull(orig, OBJECT_NOT_NULL_MSG);
		try {
			PropertyUtils.copyProperties(dist, orig);
		} catch (Exception e) {
			LogUtil.error(log, e, e.getMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}
	}
}
