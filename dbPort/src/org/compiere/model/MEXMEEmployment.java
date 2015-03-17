/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Utilerias;

/**
 * Employment Information
 * 
 * @author Lorena Lama
 * 
 */
public class MEXMEEmployment extends X_EXME_Employment {

	private static final long serialVersionUID = 1L;

	/**
	 * @param ctx
	 * @param EXME_Employment_ID
	 * @param trxName
	 */
	public MEXMEEmployment(Properties ctx, int EXME_Employment_ID, String trxName) {
		super(ctx, EXME_Employment_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEEmployment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**************************************************************************
	 * Return printable String representation
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer retStr = new StringBuffer();
		retStr.append(getName());
		if (getEXME_Puesto_ID() > 0 && getJobName() != null)
			retStr.append(", ").append(getJobName());
		if (getPhone() != null && getPhone().length() > 0)
			retStr.append(", ").append(getPhone());
		if (getPhone2() != null && getPhone2().length() > 0)
			retStr.append(", ").append(getPhone2());
		if (getPhone3() != null && getPhone3().length() > 0)
			retStr.append(", ").append(getPhone3());
		return retStr.toString();
	} // toString

	private String jobName = null;

	public String getJobName() {
		if (jobName == null && getEXME_Puesto_ID() > 0) {
			jobName = (String) Utilerias.getFieldFrom(getCtx(), X_EXME_Puesto.Table_Name, "NVL(" + X_EXME_Puesto.COLUMNNAME_Name + ","
					+ X_EXME_Puesto.COLUMNNAME_Value+ ") as employ ", X_EXME_Puesto.COLUMNNAME_EXME_Puesto_ID, getEXME_Puesto_ID());
		}
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

}
