package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Msg;

/**
 * Actividades del M�dico
 * @author Lama
 *
 */
public class MEXMEActividad extends X_EXME_Actividad {

	private static final long serialVersionUID = 1L;
	public MEXMEActividad(Properties ctx, int EXME_Actividad_ID, String trxName) {
		super(ctx, EXME_Actividad_ID, trxName);
	}

	public MEXMEActividad(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	/**
	 *  Before Save
	 *  @param newRecord new
	 *  @param success success
	 *  @return true
	 */
	protected boolean beforeSave(boolean newRecord) {
		if(getStartDate().after(getEndDate())){
			log.saveError("error.dateIniFin", Msg.getElement(getCtx(), "EXME_Actividad_ID "));
			return false;
		}
		return true;
	} //  beforeSave

}
