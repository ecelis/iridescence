package org.compiere.model.form;

/**
 * @author odelarosa
 * 
 */
public class Option {
	private int id;
	private int listValue;
	private String name;
	private int seqNo;

	public int getId() {
		return id;
	}

	public int getListValue() {
		return listValue;
	}

	public String getName() {
		return name;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setListValue(int listValue) {
		this.listValue = listValue;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	@Override
	public String toString() {
		return name;
	}

}
