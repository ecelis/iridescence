/**
 *
 */
package com.ecaresoft.util;

import java.io.Serializable;

/**
 * @author mrojas
 *
 */
public class LabelValueBean implements Serializable {

	private static final long serialVersionUID = -821993774084312543L;

	private String label;
	private String value;


	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


	public LabelValueBean(String label, String value) {
		this.label = label;
		this.value = value;
	}

	@Override
	public String toString() {
		return "LabelValueBean [label=" + label + ", value=" + value + "]";
	}
}
