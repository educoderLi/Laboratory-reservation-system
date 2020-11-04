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
package org.forten.utils.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化与反序列化工具类。参考springframework的util包下的SerializationUtils类写成，
 * 主要为了与springframework解耦。
 * 
 * @author Dave Syer
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2012-5-7
 */
public abstract class SerializationUtil {
	/**
	 * 将对象序列化为字节数组
	 * 
	 * @param object
	 *            将被序列化的对象
	 * @return 对象序列化后的字节数组
	 */
	public static <T> byte[] serialize(T object) {
		if (object == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException(String.format(
					"类型为: %s 的对象序列化失败", object.getClass(), ex));
		}
		return baos.toByteArray();
	}

	/**
	 * 将字节数组反序列化成对象
	 * 
	 * @param bytes
	 *            被序列化的对象字节数组
	 * @return 对象字节数组反序列化后的对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new ByteArrayInputStream(bytes));
			return (T) ois.readObject();
		} catch (IOException ex) {
			throw new IllegalArgumentException("反序列化对象失败", ex);
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("对象类型未找到，反序列化对象失败", ex);
		}
	}
}
