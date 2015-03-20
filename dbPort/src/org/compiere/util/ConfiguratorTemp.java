package org.compiere.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * Archivo temporal del configurador
 * 
 * @author odelarosa
 * 
 */
public class ConfiguratorTemp implements Serializable {

	private static final long serialVersionUID = 1198372229163635479L;
	private List<ValueName> set = new ArrayList<ValueName>();
	int index = -1;

	public ConfiguratorTemp() {
	}

	public List<ValueName> getSet() {
		return set;
	}

	public void setSet(List<ValueName> set) {
		this.set = set;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void add(String step, String key, Object value) {
		if (value instanceof Serializable) {
			key = StringUtils.removeStart(key, "#");
			Properties props = find(step);
			props.put(key, value instanceof String ? new String(value.toString()) : value);
			if (index == -1) {
				set.add(new ValueName(step, props));
			} else {
				set.set(index, new ValueName(step, props));
			}
		}
	}

	public Object get(String step, String key) {
		Properties props = find(step);
		key = StringUtils.removeStart(key, "#");
		if (props != null) {
			return props.get(key);
		} else {
			return null;
		}
	}

	private Properties find(String step) {
		Properties props = null;
		index = set.indexOf(new ValueName(step, null));
		if (index == -1) {
			props = new Properties();
		} else {
			ValueName vn = set.get(index);
			props = (Properties) vn.getValue();
		}
		return props;
	}

	public boolean contains(String step) {
		int index = set.indexOf(new ValueName(step, null));
		return index != -1 ? true : false;
	}

	public void reset() {
		set.clear();
	}

}
