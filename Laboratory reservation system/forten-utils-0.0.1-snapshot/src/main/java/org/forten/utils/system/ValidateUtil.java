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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.forten.utils.collection.CollectionUtil;

/**
 * Bean格式校验工具类，依赖于Hibernate Validator框架。
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2014-1-22
 */
public class ValidateUtil {
	// 私有构造器
	private ValidateUtil() {

	}

	/**
	 * 获得校验器
	 * 
	 * @return 校验器
	 */
	public static Validator getValidator() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * 校验Bean中的数据，返回校验失败的消息列表
	 * 
	 * @param bean
	 *            待校验的Bean
	 * @return 返回校验后的消息List，如果校验全部成功则返回一个空的String列表
	 */
	public static <T> List<String> validate(T bean) {
		Set<ConstraintViolation<T>> cvSet = getValidator().validate(bean);
		List<String> msgList = new ArrayList<String>();
		if (!CollectionUtil.isEmpty(cvSet)) {
			for (ConstraintViolation<T> cv : cvSet) {
				msgList.add(cv.getMessage());
			}
		}
		return msgList;
	}

	/**
	 * 校验Bean中的数据，如果出现校验失败，则抛出异常
	 * 
	 * @param bean
	 *            待校验的Bean
	 * @throws ValidateException
	 *             如果出现校验失败，则抛出该异常，此异常中的messages是一个字符串List，
	 *             可以把带有校验失败信息的字符串装入这个List
	 */
	public static <T> void validateThrow(T bean) throws ValidateException {
		Set<ConstraintViolation<T>> cvSet = getValidator().validate(bean);
		List<String> msgList = new ArrayList<String>();
		if (!CollectionUtil.isEmpty(cvSet)) {
			for (ConstraintViolation<T> cv : cvSet) {
				msgList.add(cv.getMessage());
			}
			throw new ValidateException(msgList);
		}
	}
}
