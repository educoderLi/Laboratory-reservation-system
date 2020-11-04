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
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.forten.utils.common.model.School;
//import org.forten.utils.common.model.Student;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// * 
// * 
// * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
// * @since 2013-1-4
// */
//public class JSONUtilTest {
//	private School school;
//	private Student s1;
//	private Student s2;
//	private Student s3;
//	private Student master;
//
//	@Before
//	public void init() {
//		s1 = new Student(1, "s1");
//		s2 = new Student(2, "s2");
//		s3 = new Student(3, "s3");
//		master = new Student(0, "Master");
//		school = new School(1, "tsinghua", s1, s2, s3);
//		school.setMaster(master);
//	}
//
//	@Test
//	public void testName() throws Exception {
//		String json = JSONUtil.beanToJSON(school);
//		System.out.println(json);
//		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
//		classMap.put("studentSet", Student.class);
//		School s = JSONUtil.jsonToBean(json, School.class, classMap);
//		System.out.println(s);
//
//		Student master = JSONUtil.jsonToBean(json, "master", Student.class);
//		System.out.println(master);
//
//		List<Student> studentSet = JSONUtil.jsonToList(json, "studentSet",
//				Student.class);
//		for (Student student : studentSet) {
//			System.out.println(student);
//		}
//	}
//}
