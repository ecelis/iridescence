package org.compiere.util;

import java.util.HashMap;

public class CustomHashMap extends HashMap<Object, Object> {

	private static final long serialVersionUID = 1L;
	private HashMap<Object, Object> custom = new HashMap<Object, Object>();

	public Object put(Object key, Object value, Object custom) {
		this.custom.put(key, custom);
		return super.put(key, value);
	}

	public Object getCustom(Object key){
		return this.custom.get(key);
	}
	
}
