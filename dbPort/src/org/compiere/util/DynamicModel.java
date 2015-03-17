package org.compiere.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DynamicModel {
	
	private Map<String, MetaData> map = null;
	
	public DynamicModel(){
		map = new HashMap<String, MetaData>();
	}
	
	public DynamicModel(String name, String value){
		this(name,value,null);
	}
	
	public DynamicModel(String name, String value, String type){
		map = new HashMap<String, MetaData>();
		map.put(name, new MetaData(value, type));
	}
	
	public void setValue(String name, String value){
		map.put(name, new MetaData(value, null));
	}
	
	public void setValue(String name, String value, String type){
		map.put(name, new MetaData(value, type));		
	}
	
	public String getValue(String key){
		return map.get(key).toString();
	}
	
	public MetaData getMetaData(String key){
		return map.get(key);
	}
	
	@Override
	public String toString(){
		Set<String> s = map.keySet();
		String str = "";
		for(String o : s)
			str = str + "-" + o;
		return str;
	}
	
	public class MetaData{
		private String type = null;
		private String value = null;
		
		public MetaData() {
			this(null,null);
		}
		
		public MetaData(String value) {
			this(value,null);
		}
		
		public MetaData(String value, String type) {
			this.value = value;
			this.type = type;			
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
		@Override
		public String toString(){
			return this.value;
		}
	}
	
	
}
