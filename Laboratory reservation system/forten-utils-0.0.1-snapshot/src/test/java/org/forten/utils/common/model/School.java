/*
 * Copyright 2003-2013 the original author or authors.
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
package org.forten.utils.common.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 
 * @author <a href="mailto:du_yi@bbn.cn">Duyi</a>
 * @since 2013-1-4
 */
public class School implements Serializable {
	private static final long serialVersionUID = 1292583312403901952L;

	private int id;
	private String name;
	private Set<Student> studentSet;
	private Student master;

	/**
	 * 
	 */
	public School() {
		super();
		studentSet = new HashSet<Student>();
	}

	/**
	 * @param id
	 * @param name
	 */
	public School(int id, String name, Student... students) {
		super();
		this.id = id;
		this.name = name;
		studentSet = new HashSet<Student>();
		for (Student student : students) {
			studentSet.add(student);
		}
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the studentSet
	 */
	public Set<Student> getStudentSet() {
		return studentSet;
	}

	/**
	 * @param studentSet
	 *            the studentSet to set
	 */
	public void setStudentSet(Set<Student> studentSet) {
		this.studentSet = studentSet;
	}

	/**
	 * @return the master
	 */
	public Student getMaster() {
		return master;
	}

	/**
	 * @param master
	 *            the master to set
	 */
	public void setMaster(Student master) {
		this.master = master;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		School other = (School) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + ", studentSet="
				+ studentSet + "]";
	}
}
