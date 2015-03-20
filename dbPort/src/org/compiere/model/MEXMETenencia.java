package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMETenencia extends X_EXME_Tenencia {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMETenencia.class);

	/**
	 * Constructor de MEXMETenencia
	 * @param ctx                Propiedades
	 * @param EXME_NumHabts_ID  ID de Vivienda
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMETenencia(Properties ctx, int EXME_Tenencia_ID, String trxName) {
		super(ctx, EXME_Tenencia_ID, trxName);
	}
	
	/**
	 * Constructor de MEXMETenencia
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMETenencia(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMETenencia[] getTenencias(Properties ctx, String trxName) {
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MEXMETenencia> resultados = new ArrayList<MEXMETenencia>();
			try{
				sql = "SELECT * FROM EXME_Tenencia WHERE IsActive = 'Y'";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Tenencia");
				
				pstmt = DB.prepareStatement(sql, trxName);
				
				rs = pstmt.executeQuery();
				while (rs.next()){
					MEXMETenencia tenencias = new MEXMETenencia(ctx, rs, trxName);
					resultados.add(tenencias);
				}
			}
			catch(Exception e){
	    		s_log.log(Level.SEVERE, sql.toString(), e);
			}
			finally{
	    		DB.close(rs,pstmt);
			}
			MEXMETenencia[] reenviar = new MEXMETenencia[resultados.size()];
			resultados.toArray(reenviar);
			return reenviar;
	    }
	    
}
