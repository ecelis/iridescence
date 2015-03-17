package org.compiere.model.form;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


/**
 * @author odelarosa
 * 
 */
public class QuestionnaireBean {
	
	private String category;
	private int categoryOrder;
	private InputStream i1;
	private InputStream i2;
	private InputStream i3;
	private String q1;
	private String q2;
	private String q3;
	private String r1;
	private String r2;
	private String r3;

	public String getCategory() {
		return category;
	}

	public int getCategoryOrder() {
		return categoryOrder;
	}

	public InputStream getI1() {
		return i1;
	}

	public InputStream getI2() {
		return i2;
	}

	public InputStream getI3() {
		return i3;
	}

	public String getQ1() {
		return q1;
	}

	public String getQ2() {
		return q2;
	}

	public String getQ3() {
		return q3;
	}

	public String getR1() {
		return r1;
	}

	public String getR2() {
		return r2;
	}

	public String getR3() {
		return r3;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setCategoryOrder(int categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	public void setI1(InputStream i1) {
		this.i1 = i1;
	}

	public void setI2(InputStream i2) {
		this.i2 = i2;
	}

	public void setI3(InputStream i3) {
		this.i3 = i3;
	}

	public void setQ1(String q1) {
		this.q1 = q1;
	}

	public void setQ2(String q2) {
		this.q2 = q2;
	}

	public void setQ3(String q3) {
		this.q3 = q3;
	}

	public void setR1(String r1) {
		this.r1 = r1;
	}

	public void setR2(String r2) {
		this.r2 = r2;
	}

	public void setR3(String r3) {
		this.r3 = r3;
	}
	
	/**
	 * @param q1
	 * @param r1
	 */
	public void setQuestionAnswer1(String q1, Object r1) {
		setQ1(q1);
		if (r1 instanceof byte[]) {
			setR1("{img}");
			setI1(new ByteArrayInputStream((byte[]) r1));
		} else if (r1 instanceof String) {
			setR1((String) r1);
		}
	}

	/**
	 * @param q2
	 * @param r2
	 */
	public void setQuestionAnswer2(String q2, Object r2) {
		setQ2(q2);
		if (r2 instanceof byte[]) {
			setR2("{img}");
			setI2(new ByteArrayInputStream((byte[]) r2));
		} else if (r2 instanceof String) {
			setR2((String) r2);
		}
	}

	/**
	 * @param q3
	 * @param r3
	 */
	public void setQuestionAnswer3(String q3, Object r3) {
		setQ3(q3);
		if (r3 instanceof byte[]) {
			setR3("{img}");
			setI3(new ByteArrayInputStream((byte[]) r3));
		} else if (r3 instanceof String) {
			setR3((String) r3);
		}
	}
}
