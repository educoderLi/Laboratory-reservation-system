/*
 * Copyright 2003-2014 the original author or authors.
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

import java.util.List;

/**
 * Bean格式校验异常类
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2014-1-22
 */
public class ValidateException extends RuntimeException {
	private static final long serialVersionUID = 7845477358424127031L;

	private List<String> messages;

	// 携带校验消息
	public ValidateException(List<String> messages) {
		super();
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}
}
