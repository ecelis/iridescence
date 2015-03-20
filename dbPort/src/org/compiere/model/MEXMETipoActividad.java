package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Msg;

/**
 * Tipo de Actividad
 * @author Lama
 *
 */
public class MEXMETipoActividad extends X_EXME_TipoActividad {
	private static final long serialVersionUID = 1L;
	public MEXMETipoActividad(Properties ctx, int EXME_TipoActividad_ID,
			String trxName) {
		super(ctx, EXME_TipoActividad_ID, trxName);
	}

	public MEXMETipoActividad(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 *  Before Save
	 *  @param newRecord new
	 *  @param success success
	 *  @return true
	 */
	protected boolean beforeSave(boolean newRecord) {

		//Validamos que el color no sea repetido
		if(getAD_Color_ID() > 0 && is_ValueChanged("AD_Color_ID")){
			int count = 0;
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT AD_Color_ID FROM EXME_TipoActividad WHERE AD_Color_ID = ?");
			
			count = DB.getSQLValue(get_TrxName(),sql.toString(),getAD_Color_ID());
			
			if(count>0){
				//JOptionPane.showMessageDialog(null, Msg.getMsg(Env.getCtx(), "error.color.repeated"));
				log.saveError("error.color.repeated", Msg.getElement(getCtx(), "EXME_TipoActividad_ID "));
				//log.saveError("SaveError", Msg.getElement(getCtx(), "EXME_TipoActividad_ID ") + "Color repetido");
				return false;
			}
		}		
		return true;
	} //  beforeSave
}
