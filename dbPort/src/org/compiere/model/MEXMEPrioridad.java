package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEPrioridad extends X_EXME_Prioridad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEPrioridad.class);

	/**
	 * Constructor de MEXMEPrioridad
	 * @param ctx                Propiedades
	 * @param EXME_Prioridad_ID  ID de Prioridad
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMEPrioridad(Properties ctx, int EXME_Prioridad_ID, String trxName) {
		super(ctx, EXME_Prioridad_ID, trxName);
		
	}
	
	/**
	 * Constructor de MEXMEPrioridad
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMEPrioridad(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	public static List<LabelValueBean> getPrioridad(Properties ctx, String trxName)
    {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LabelValueBean> resultados = new ArrayList<LabelValueBean>();
		try{
			sql.append("SELECT EXME_Prioridad_ID, Value FROM EXME_Prioridad WHERE IsActive = 'Y'");
			
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Prioridad"));
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			rs = pstmt.executeQuery();
			while (rs.next()){
				LabelValueBean Prioridad = new LabelValueBean(rs.getString(2), rs.getString(1)); //se crea el objeto Prioridad
				resultados.add(Prioridad); //se agrega la Prioridad al arreglo
			}
		}
		catch(Exception e){
    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		}
		finally{
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		return resultados;
    }
}
