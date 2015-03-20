package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEEspecialidadTs extends X_EXME_Especialidad_TS {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3320939620138974130L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEEspecialidadTs.class);

	/**
	 * Constructor de MEXMEEspecialidadTs
	 * @param ctx                Propiedades
	 * @param EXME_Especialidad_TS_ID
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMEEspecialidadTs(Properties ctx, int EXME_Especialidad_TS_ID, String trxName) {
		super(ctx, EXME_Especialidad_TS_ID, trxName);
	}
	
	/**
	 * Constructor de MEXMEEspecialidadTs
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMEEspecialidadTs(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMEEspecialidadTs[] getEspecialidadesTs(Properties ctx, String trxName) {
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MEXMEEspecialidadTs> resultados = new ArrayList<MEXMEEspecialidadTs>();
			try{
				sql = "SELECT * FROM EXME_Especialidad_TS WHERE IsActive = 'Y'";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Especialidad_TS");
				
				pstmt = DB.prepareStatement(sql, trxName);
				
				rs = pstmt.executeQuery();
				while (rs.next()){
					MEXMEEspecialidadTs especialidadesTS = new MEXMEEspecialidadTs(ctx, rs, trxName);
					resultados.add(especialidadesTS);
				}
			}
			catch(Exception e){
	    		s_log.log(Level.SEVERE, sql.toString(), e);
			}
			finally{
				DB.close(rs,pstmt);
			}
			MEXMEEspecialidadTs[] reenviar = new MEXMEEspecialidadTs[resultados.size()];
			resultados.toArray(reenviar);
			return reenviar;
	    }
	    
}
