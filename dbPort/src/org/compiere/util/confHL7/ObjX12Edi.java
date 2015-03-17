package org.compiere.util.confHL7;

import java.util.ArrayList;
import java.util.List;

import org.compiere.util.Constantes;


public class ObjX12Edi {
	
	// Clase de componentes
	private class X12Component {
		private String data;
		private int sequence;
		public X12Component (String data, int sequence) {
			this.data = data;
			this.sequence = sequence;
		}
		public String getData() {
			return data;
		}
		public int getSequence() {
			return sequence;
		}
		public void setData(String data) {
			this.data = data;
		}
		public void setSequence(int sequence) {
			this.sequence = sequence;
		}
		public String toString() {
			return getData();
		}
	}
	
	// Clase de campos
	private class X12Field {
		private List<X12Component> listComp;
		private String data;
		private int sequence;
		private String separator;
		public X12Field (int sequence, String separators) {
			this.sequence = sequence;
			this.separator = separators.substring(2);
			this.listComp = new ArrayList<X12Component>();
		}
		public X12Field (String data, int sequence, String separators) {
			this.data = data;
			this.sequence = sequence;
			this.separator = separators.substring(2);
			this.listComp = new ArrayList<X12Component>();
		}
		public X12Field (List<X12Component> listComp, int sequence, String separators) {
			this.listComp = listComp;
			this.sequence = sequence;
			this.separator = separators.substring(2);
		}
		public void setData(String data) {
			this.data = data;
		}
		public void setSequence(int sequence) {
			this.sequence = sequence;
		}
		public void addComp(String data, int sequence) {
			X12Component comp = new X12Component(data, sequence);
			listComp.add(comp);
		}
		public String getData() {
			return data;
		}
		public int getSequence() {
			return sequence;
		}
		public X12Component getComp(int sequence) {
			for (X12Component comp: listComp) {
				if (comp.getSequence() == sequence) {
					return comp;
				}
			}
			return null;
		}
		public int getLastComp() {
			int res = 0;
			for (X12Component comp: listComp) {
				if (comp.getSequence() > res) {
					res = comp.getSequence();
				}
			}
			return res;
		}
		public String toString() {
			StringBuilder res = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			if (listComp.size() > 0) {
				for (int i = 1; i <= getLastComp(); i++) {
					X12Component comp = getComp(i);
					if (comp == null) {
						res.append(separator);
					} else {
						res.append(comp.toString()).append(separator);
					}
				}
				res = res.deleteCharAt(res.length()-1);
			} else {
				res.append(getData());
			}
			return res.toString();
		}
	}
	
	// Clase de Segmentos
	public class X12Segment {
		private List<X12Field> listField;
		private String name;
		private int sequence;
		private String separatorField;
		private String separators;
		private String separatorSeg;
		public X12Segment (String name, int sequence, String separators) {
			this.name = name;
			this.sequence = sequence;
			this.separatorSeg = separators.substring(0, 1);
			this.separatorField = separators.substring(1, 2);
			this.separators = separators;
			listField = new ArrayList<X12Field>();
		}
		public X12Segment (List<X12Field> listField, String name, int sequence, String separators) {
			this.listField = listField;
			this.name = name;
			this.sequence = sequence;
			this.separatorSeg = separators.substring(0, 1);
			this.separatorField = separators.substring(1, 2);
			this.separators = separators;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSequence(int sequence) {
			this.sequence = sequence;
		}
		public void addField(String data, int sequence) {
			X12Field field = new X12Field(data, sequence, separators);
			listField.add(field);
		}
		public String getName() {
			return name;
		}
		public int getSequence() {
			return sequence;
		}
		public X12Field getField(int sequence) {
			for (X12Field field: listField) {
				if (field.getSequence() == sequence) {
					return field;
				}
			}
			return null;
		}
		public int getLastField() {
			int res = 0;
			for (X12Field field: listField) {
				if (field.getSequence() > res) {
					res = field.getSequence();
				}
			}
			return res;
		}
		public String toString() {
			StringBuilder res = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			res.append(getName()).append(separatorField);
			if (listField.size() > 0) {
				for (int i = 1; i <= getLastField(); i++) {
					X12Field field = getField(i);
					if (field == null) {
						res.append(separatorField);
					} else {
						res.append(field.toString()).append(separatorField);
					}
				}
				res = res.deleteCharAt(res.length()-1);
			}
			res.append(separatorSeg);
			return res.toString();
		}
	}
	
	private List<X12Segment> lstSegmentos = null;
	private String separators = null;
	
	public ObjX12Edi(String separators) {
		lstSegmentos = new ArrayList<X12Segment>();
		this.separators = separators;
	}
	
	public X12Segment newSegment(String name, int sequence) {
		X12Segment seg = new X12Segment(name, sequence, separators);
		lstSegmentos.add(seg);
		return seg;
	}
	
	public X12Segment getSegment(String name) {
		for (X12Segment seg: lstSegmentos) {
			if (seg.getName().equals(name)) {
				return seg;
			}
		}
		return null;
	}
	
	public String toString() {
		StringBuilder res = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		for (X12Segment seg : lstSegmentos) {
			res.append(seg.toString());
		}
		return res.toString();
	}
}
