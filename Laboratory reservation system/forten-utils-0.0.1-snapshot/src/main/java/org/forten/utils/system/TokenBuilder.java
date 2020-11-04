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

import org.forten.utils.security.MD5Util;

/**
 * 
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-10-22
 */
public final class TokenBuilder {
	private volatile static TokenBuilder instance;

	private TokenBuilder() {
		super();
	}

	public static TokenBuilder getInstance() {
		if (instance == null) {
			synchronized (TokenBuilder.class) {
				if (instance == null) {
					instance = new TokenBuilder();
				}
			}
		}
		return instance;
	}

	public String getToken() {
		Long currentTime = CurrentTimeKeyBuilder.getInstance().nextPK();
		String token = MD5Util.encrypt(currentTime.toString().getBytes());
		return token;
	}
}
