package com.ecaresoft.util.officevisit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author odelarosa
 *
 */
public class NumericParameter extends AReportParameter {

	public final static int BETWEEN = 4;
	public final static int EQUALS = 1;
	public final static int GTE = 2;
	public final static int LTE = 3;

	private int operator;
	private BigDecimal value;
	private BigDecimal value2;

	/**
	 * @param id
	 * @param name
	 */
	public NumericParameter(int id, String name) {
		super(id, name);
		
		setType(NUMERIC);
	}

	public int getOperator() {
		return operator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ecaresoft.util.officevisit.AReportParameter#getParams()
	 */
	@Override
	public List<Object> getParams() {
		List<Object> params = new ArrayList<Object>();

		if (operator == BETWEEN) {
			params.add(value);
			params.add(value2);
		} else {
			params.add(value);
		}

		params.add(getId());

		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ecaresoft.util.officevisit.AReportParameter#getSql()
	 */
	@Override
	public String getSql() {
		StringBuilder str = new StringBuilder();

		if (operator == GTE) {
			str.append(" AND COALESCE(res.textbinary::decimal, 0) >= ? ");
		} else if (operator == LTE) {
			str.append(" AND COALESCE(res.textbinary::decimal, 0) <= ? ");
		} else if (operator == EQUALS) {
			str.append(" AND COALESCE(res.textbinary::decimal, 0) = ? ");
		} else if (operator == BETWEEN) {
			str.append(" AND COALESCE(res.textbinary::decimal, 0) between ? and ? ");
		}

		str.append(" AND res.exme_pregunta_id = ? ");

		return str.toString();
	}

	@Override
	public String getStrValue() {
		StringBuilder str = new StringBuilder();

		if (operator == GTE) {
			str.append(" >= " + getValue().toString());
		} else if (operator == LTE) {
			str.append(" <= " + getValue().toString());
		} else if (operator == EQUALS) {
			str.append(" = " + getValue().toString());
		} else if (operator == BETWEEN) {
			str.append(" between " + getValue().toString() + " AND " + getValue2().toString());
		}

		return str.toString();
	}

	public BigDecimal getValue() {
		return value;
	}

	public BigDecimal getValue2() {
		return value2;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public void setValue2(BigDecimal value2) {
		this.value2 = value2;
	}

	@Override
	public String toString() {
		return getName() + getStrValue();
	}

}
