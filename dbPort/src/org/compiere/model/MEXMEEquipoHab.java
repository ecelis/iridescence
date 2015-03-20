package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Utilerias;
/**
 * 
 * @author Raul Montemayor
 * @version 1.0
 * Creado 01/Abril/2009
 */

public class MEXMEEquipoHab extends X_EXME_EquipoHab {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7654140829552408609L;
	/**	Logger							*/
	protected transient static CLogger	s_log = CLogger.getCLogger ("MEXMEEquipoHab");

    public MEXMEEquipoHab(Properties ctx, int EXME_EquipoH_ID, String trxName) {
        super(ctx, EXME_EquipoH_ID, trxName);
    }
    
    public MEXMEEquipoHab(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    /**
     * Valida antes de guardar
     * @return falso si la fecha final es mayor que la final
     */
	protected boolean beforeSave (boolean newRecord){
		boolean save = true;
		//Valida que la fecha final sea mayor que la inicial
		if(getFecha_Fin().getTime()<= getFecha_Ini().getTime()){
			s_log.saveError(Utilerias.getMessage(getCtx(), null, "ece.error.fecha"), "");//
			
			save = false;
		}
		 
		return save;
	}
	
}
