package org.compiere.util;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * CLase contenedora de un objeto y nombre
 * 
 * @author odelarosa
 * 
 */
public class ValueName implements Comparable<ValueName>, Serializable {

	private static final long serialVersionUID = -3117161421787605810L;
	private Object value;
	private String name;

	public ValueName(String name, Object value) {
		this.value = value;
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValueName other = (ValueName) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(ValueName o) {
		return getName().compareTo(o.getName());
	}

	@Override
	public String toString() {
		return name;
	}

}
