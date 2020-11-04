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

import org.apache.log4j.Logger;
import org.forten.utils.collection.ArrayUtil;

/**
 * Log4j日志记录工具类
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-4-20
 */
public final class LogUtil {
	private LogUtil() {

	}

	public static void debug(Logger log, String msg, Object... params) {
		if (log.isDebugEnabled())
			if (ArrayUtil.isEmpty(params)) {
				log.debug(msg);
			} else {
				log.debug(String.format(msg, params));
			}
	}

	public static void debug(Logger log, Throwable e, String msg,
			Object... params) {
		if (log.isDebugEnabled())
			if (ArrayUtil.isEmpty(params)) {
				log.debug(msg, e);
			} else {
				log.debug(String.format(msg, params), e);
			}
	}

	public static void info(Logger log, String msg, Object... params) {
		if (log.isInfoEnabled())
			if (ArrayUtil.isEmpty(params)) {
				log.info(msg);
			} else {
				log.info(String.format(msg, params));
			}
	}

	public static void info(Logger log, Throwable e, String msg,
			Object... params) {
		if (log.isInfoEnabled())
			if (ArrayUtil.isEmpty(params)) {
				log.info(msg, e);
			} else {
				log.info(String.format(msg, params), e);
			}
	}

	public static void warn(Logger log, String msg, Object... params) {
		if (log.isInfoEnabled())
			if (ArrayUtil.isEmpty(params)) {
				log.warn(msg);
			} else {
				log.warn(String.format(msg, params));
			}
	}

	public static void warn(Logger log, Throwable e, String msg,
			Object... params) {
		if (log.isInfoEnabled())
			if (ArrayUtil.isEmpty(params)) {
				log.warn(msg, e);
			} else {
				log.warn(String.format(msg, params), e);
			}
	}

	public static void error(Logger log, String msg, Object... params) {
		if (log.isInfoEnabled())
			if (ArrayUtil.isEmpty(params)) {
				log.error(msg);
			} else {
				log.error(String.format(msg, params));
			}
	}

	public static void error(Logger log, Throwable e, String msg,
			Object... params) {
		if (log.isInfoEnabled())
			if (ArrayUtil.isEmpty(params)) {
				log.error(msg, e);
			} else {
				log.error(String.format(msg, params), e);
			}
	}

	public static void exception(Logger log, Throwable e, String msg,
			Object... params) {
		if (log.isDebugEnabled())
			e.printStackTrace();
		else if (log.isInfoEnabled())
			if (ArrayUtil.isEmpty(params)) {
				log.error(msg, e);
			} else {
				log.error(String.format(msg, params), e);
			}
	}
}
