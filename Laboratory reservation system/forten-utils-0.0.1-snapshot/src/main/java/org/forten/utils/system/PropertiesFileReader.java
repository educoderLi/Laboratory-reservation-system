/*
 * Copyright 2003-2011 the original author or authors.
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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * Properties属性文件内容读取工具类
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-4-20
 */
public abstract class PropertiesFileReader {
	private static Logger log = Logger.getLogger(PropertiesFileReader.class);

	private static final String LOAD_VALUE_ERROR_MSG = "无法获得[%s.properties]文件中键[%s]所对应的值，请检查属性文件或KEY是否存在";

	/**
	 * 从属性文件中读取键所对应的值
	 * 
	 * @param resourceFile
	 *            属性文件名。 注意不要加.properties文件后缀。比如有文件system.properties，
	 *            我们只需要用system作为参数即可。
	 * @param key
	 *            键
	 * @return 键对应的字符串值
	 */
	public static String getValue(String resourceFile, String key) {
		String result = null;
		try {
			result = ResourceBundle.getBundle(resourceFile).getString(key);
		} catch (MissingResourceException e) {
			LogUtil.error(log, LOAD_VALUE_ERROR_MSG, resourceFile, key);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
