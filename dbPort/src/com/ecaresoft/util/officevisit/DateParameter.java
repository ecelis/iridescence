package com.ecaresoft.util.officevisit;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

/**
 * @author odelarosa
 *
 */
public class DateParameter extends AReportParameter {

	private Timestamp date;
	private Timestamp date2;
	private Timestamp date3;
	private int operator;

	/**
	 * @param id
	 * @param name
	 */
	public DateParameter(int id, String name) {
		super(id, name);
		
		setType(DATE);
	}

	public Timestamp getDate() {
		return date;
	}

	public Timestamp getDate2() {
		return date2;
	}

	public Timestamp getDate3() {
		return date3;
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
		List<Object> list = new ArrayList<Object>();
		if (operator == NumericParameter.BETWEEN) {
			list.add(TimeUtil.getInitialRange(Env.getCtx(), date2));
			list.add(TimeUtil.getFinalRange(Env.getCtx(), date3));
		} else {
			list.add(TimeUtil.getInitialRange(Env.getCtx(), date));
		}

		list.add(getId());

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ecaresoft.util.officevisit.AReportParameter#getSql()
	 */
	@Override
	public String getSql() {
		StringBuilder str = new StringBuilder();

		if (operator == NumericParameter.BETWEEN) {
			str.append(" AND textbinary is not null AND to_date(textbinary, 'MM/dd/yyyy') between ? and ? ");
		} else if (operator == NumericParameter.EQUALS) {
			str.append(" AND textbinary is not null AND  to_date(textbinary, 'MM/dd/yyyy') = ? ");
		} else if (operator == NumericParameter.GTE) {
			str.append(" AND textbinary is not null AND  to_date(textbinary, 'MM/dd/yyyy') >= ? ");
		} else if (operator == NumericParameter.LTE) {
			str.append(" AND textbinary is not null AND  to_date(textbinary, 'MM/dd/yyyy') <= ? ");
		}

		str.append(" AND res.exme_pregunta_id = ? ");

		return str.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ecaresoft.util.officevisit.AReportParameter#getStrValue()
	 */
	@Override
	public String getStrValue() {
		StringBuilder str = new StringBuilder();

		if (operator == NumericParameter.GTE) {
			str.append(" >= " + Constantes.getShortDate(Env.getCtx()).format(getDate()));
		} else if (operator == NumericParameter.LTE) {
			str.append(" <= " + Constantes.getShortDate(Env.getCtx()).format(getDate()));
		} else if (operator == NumericParameter.EQUALS) {
			str.append(" = " + Constantes.getShortDate(Env.getCtx()).format(getDate()));
		} else if (operator == NumericParameter.BETWEEN) {
			str.append(" between " + Constantes.getShortDate(Env.getCtx()).format(getDate2()) + " AND " + Constantes.getShortDate(Env.getCtx()).format(getDate3()));
		}
		return str.toString();
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public void setDate2(Timestamp date2) {
		this.date2 = date2;
	}

	public void setDate3(Timestamp date3) {
		this.date3 = date3;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}
	
	@Override
	public String toString() {
		
		return getName() + getStrValue();
	}

}
