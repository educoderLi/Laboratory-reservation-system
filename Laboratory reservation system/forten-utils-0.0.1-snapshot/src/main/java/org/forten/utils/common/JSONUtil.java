///*
// * Copyright 2003-2013 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package org.forten.utils.common;
//
//import java.util.List;
//import java.util.Map;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
///**
// * JSON数据格式处理工具类
// * 
// * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
// * @since 2013-1-4
// */
//public class JSONUtil {
//	private JSONUtil() {
//
//	}
//
//	/**
//	 * 把一个对象中的数据转换成为JSON形式
//	 * 
//	 * @param bean
//	 *            Java对象
//	 * @return JSON串
//	 */
//	public static String beanToJSON(Object bean) {
//		JSONObject jsonObject = JSONObject.fromObject(bean);
//		return jsonObject.toString();
//	}
//
//	/**
//	 * 把一个JSON串形式的数据转换成为一个Java对象
//	 * 
//	 * @param json
//	 *            JSON串
//	 * @param clazz
//	 *            被转换的对象类型
//	 * @param classMap
//	 *            子对象类型
//	 * @return Java对象
//	 */
//	@SuppressWarnings("unchecked")
//	public static <T> T jsonToBean(String json, Class<T> clazz,
//			Map<String, Class<?>> classMap) {
//		JSONObject jsonObject = JSONObject.fromObject(json);
//		return (T) JSONObject.toBean(jsonObject, clazz, classMap);
//	}
//
//	/**
//	 * 把一个JSON串形式的数据转换成为一个Java对象
//	 * 
//	 * @param json
//	 *            JSON串
//	 * @param clazz
//	 *            被转换的对象类型
//	 * @return Java对象
//	 */
//	@SuppressWarnings("unchecked")
//	public static <T> T jsonToBean(String json, Class<T> clazz) {
//		JSONObject jsonObject = JSONObject.fromObject(json);
//		return (T) JSONObject.toBean(jsonObject, clazz);
//	}
//
//	/**
//	 * 把一个JSON串中的一部分属性的数据转换成为一个Java对象
//	 * 
//	 * @param json
//	 *            JSON串
//	 * @param clazz
//	 *            被转换的对象类型
//	 * @param classMap
//	 *            子对象类型
//	 * @return Java对象
//	 */
//	@SuppressWarnings("unchecked")
//	public static <T> T jsonToBean(String json, String fieldName, Class<T> clazz) {
//		JSONObject jsonObject = JSONObject.fromObject(json);
//		jsonObject = jsonObject.getJSONObject(fieldName);
//		return (T) JSONObject.toBean(jsonObject, clazz);
//	}
//
//	/**
//	 * 把一个JSON串中的一部分属性的数据转换成为一个Java对象
//	 * 
//	 * @param json
//	 *            JSON串
//	 * @param clazz
//	 *            被转换的对象类型
//	 * @return Java对象
//	 */
//	@SuppressWarnings("unchecked")
//	public static <T> List<T> jsonToList(String json, String fieldName,
//			Class<T> clazz) {
//		JSONObject jsonObject = JSONObject.fromObject(json);
//		JSONArray jsonArray = jsonObject.getJSONArray(fieldName);
//		List<T> list = (List<T>) JSONArray.toCollection(jsonArray, clazz);
//		return list;
//	}
//
//	/**
//	 * 把从JSON串中属性名为key的属性以Object类型取出
//	 * 
//	 * @param json
//	 *            JSON串
//	 * @param key
//	 *            属性名
//	 * @return 属性值的Object类型
//	 */
//	public static Object getObjectFromJSON(String json, String key) {
//		JSONObject jsonObject = JSONObject.fromObject(json);
//		return jsonObject.get(key);
//	}
//
//	/**
//	 * 把从JSON串中属性名为key的属性以Boolean类型取出
//	 * 
//	 * @param json
//	 *            JSON串
//	 * @param key
//	 *            属性名
//	 * @return 属性值的Boolean类型
//	 */
//	public static boolean getBooleanFromJSON(String json, String key) {
//		JSONObject jsonObject = JSONObject.fromObject(json);
//		return jsonObject.getBoolean(key);
//	}
//
//	/**
//	 * 把从JSON串中属性名为key的属性以Double类型取出
//	 * 
//	 * @param json
//	 *            JSON串
//	 * @param key
//	 *            属性名
//	 * @return 属性值的Double类型
//	 */
//	public static double getDoubleFromJSON(String json, String key) {
//		JSONObject jsonObject = JSONObject.fromObject(json);
//		return jsonObject.getDouble(key);
//	}
//
//	/**
//	 * 把从JSON串中属性名为key的属性以Int类型取出
//	 * 
//	 * @param json
//	 *            JSON串
//	 * @param key
//	 *            属性名
//	 * @return 属性值的Int类型
//	 */
//	public static int getIntFromJSON(String json, String key) {
//		JSONObject jsonObject = JSONObject.fromObject(json);
//		return jsonObject.getInt(key);
//	}
//
//	/**
//	 * 把从JSON串中属性名为key的属性以Long类型取出
//	 * 
//	 * @param json
//	 *            JSON串
//	 * @param key
//	 *            属性名
//	 * @return 属性值的Long类型
//	 */
//	public static long getLongFromJSON(String json, String key) {
//		JSONObject jsonObject = JSONObject.fromObject(json);
//		return jsonObject.getLong(key);
//	}
//
//	/**
//	 * 把从JSON串中属性名为key的属性以String类型取出
//	 * 
//	 * @param json
//	 *            JSON串
//	 * @param key
//	 *            属性名
//	 * @return 属性值的String类型
//	 */
//	public static String getStringFromJSON(String json, String key) {
//		JSONObject jsonObject = JSONObject.fromObject(json);
//		return jsonObject.getString(key);
//	}
//}
