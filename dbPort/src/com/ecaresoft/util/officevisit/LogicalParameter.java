package com.ecaresoft.util.officevisit;

import java.util.Arrays;
import java.util.List;

import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * @author odelarosa
 *
 */
public class LogicalParameter extends AReportParameter {

	private boolean value = true;

	/**
	 * @param id
	 * @param name
	 */
	public LogicalParameter(int id, String name) {
		super(id, name);
		
		setType(LOGICAL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ecaresoft.util.officevisit.AReportParameter#getParams()
	 */
	@Override
	public List<Object> getParams() {
		return Arrays.asList(value ? (Object) "Y" : "N", getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ecaresoft.util.officevisit.AReportParameter#getSql()
	 */
	@Override
	public String getSql() {
		return " AND res.textbinary = ? AND res.exme_pregunta_id = ? ";
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return getName() + " - " + getStrValue();
	}

	@Override
	public String getStrValue() {
		return Utilerias.getAppMsg(Env.getCtx(), value ? "imag.checked" : "msj.reglaCuestionario.negativo");
	}

}
