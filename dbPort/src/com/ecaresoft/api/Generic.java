package com.ecaresoft.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.struts.util.LabelValueBean;
import org.compiere.model.MDiagnostico;
import org.compiere.model.PO;
import org.compiere.util.CustomHashMap;
import org.compiere.util.KeyNamePair;
import org.compiere.util.ValueNamePair;

/**
 * @author odelarosa
 * 
 */
@XmlRootElement
public class Generic {
	public static Generic fromDocument(Document document) {
		Generic gen = new Generic();
		gen.setId2(Integer.parseInt(document.get("tablaID")));
		gen.setDescription(document.get(MDiagnostico.COLUMNNAME_Description));
		gen.setValue(document.get(MDiagnostico.COLUMNNAME_Value));
		gen.setName(document.get(MDiagnostico.COLUMNNAME_Name));
		gen.setId(Integer.parseInt(document.get("id")));
		return gen;
	}
	
	public static List<Generic> fromCustomHashMap(CustomHashMap docs) {

		final List<Generic> genrics = new ArrayList<Generic>();
		final Set<Entry<Object, Object>> documentos = docs.entrySet();
		for (Entry<Object, Object> entry : documentos) {
			final Generic gen = new Generic();
			gen.setId(Integer.parseInt(String.valueOf(entry.getKey())));
			gen.setValue((String) entry.getValue());
			genrics.add(gen);
		}
		return genrics;
	}
	// deprecated
	public static Generic toGeneric(LabelValueBean bean,int tableId){
		Generic gen = new Generic();
		if(StringUtils.isNumeric(bean.getValue())){
			gen.setId(Integer.valueOf(bean.getValue()));
		}
		gen.setValue(bean.getValue());
		gen.setName(bean.getLabel());
		gen.setId2(tableId);
		return gen;
	}
	public static Generic toGeneric(KeyNamePair bean,int tableId){
		Generic gen = new Generic();
//		if(StringUtils.isNumeric(bean.getID())){
//			gen.setId(Integer.valueOf(bean.getID()));
//		}
		gen.setId(bean.getKey());
		gen.setValue(bean.getID());
		gen.setName(bean.getName());
		gen.setId2(tableId);
		return gen;
	}
	public static Generic toGeneric(ValueNamePair bean,int tableId){
		Generic gen = new Generic();
		if(StringUtils.isNumeric(bean.getID())){
			gen.setId(Integer.valueOf(bean.getID()));
		}
		gen.setValue(bean.getID());
		gen.setName(bean.getName());
		gen.setId2(tableId);
		return gen;
	}
	// deprecated
	public static List<Generic> toGenericListLV(List<LabelValueBean> beans,int tableId){
		List<Generic> list = new ArrayList<Generic>();
		if(beans != null){
			for(LabelValueBean bean : beans){
				list.add(Generic.toGeneric(bean, tableId));
			}
		}
		return list;
		
	}
	public static List<Generic> toGenericListVN(List<ValueNamePair> beans,int tableId){
		List<Generic> list = new ArrayList<Generic>();
		if(beans != null){
			for(ValueNamePair bean : beans){
				list.add(Generic.toGeneric(bean, tableId));
			}
		}
		return list;
		
	}
	
	public static List<Generic> toGenericListKN(List<KeyNamePair> keys,int tableId){
		List<Generic> list = new ArrayList<Generic>();
		if(keys != null){
			for(KeyNamePair bean : keys){
				list.add(Generic.toGeneric(bean, tableId));
			}
		}
		return list;
		
	}
	// deprecated
	public static List<LabelValueBean> toListLabelValueBean(List<Generic> beans){
		List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		if(beans != null){
			for(Generic bean : beans){
				list.add(new LabelValueBean(bean.getName(), bean.getValue()));
			}
		}
		return list;
	}
	public static List<ValueNamePair> toListValueNamePair(List<Generic> beans){
		List<ValueNamePair> list = new ArrayList<ValueNamePair>();
		if(beans != null){
			for(Generic bean : beans){
				list.add(new ValueNamePair(bean.getValue(), bean.getName()));
			}
		}
		return list;
	}
	public static Generic toGenericPO(PO po) {
		if(po.get_ID() > 0){
			Generic gen = new Generic();
			gen.setId(po.get_ID());
			gen.setId2(po.get_Table_ID());
			gen.setDescription((String) po.get_Value(MDiagnostico.COLUMNNAME_Description));
			gen.setValue((String) po.get_Value(MDiagnostico.COLUMNNAME_Value));
			gen.setName((String) po.get_Value(MDiagnostico.COLUMNNAME_Name));
			return gen;
		}else{
			return null;
		}
	}
	private boolean checked = false;
	private String description;
	private int id = -1;
	private int id2 = -1;

	private String name;

	private String value;

	public Generic() {
	}

	public Generic(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Generic other = (Generic) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public int getId2() {
		return id2;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return name;
	}
}
