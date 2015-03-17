package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEZona extends X_EXME_Zona {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEZona.class);

	/**
	 * Constructor de MEXMEZona
	 * @param ctx                Propiedades
	 * @param EXME_ClasCliente_ID
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMEZona(Properties ctx, int EXME_Zona_ID, String trxName) {
		super(ctx, EXME_Zona_ID, trxName);
	}
	
	/**
	 * Constructor de MEXMEZona
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMEZona(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMEZona[] getZonas(Properties ctx, String trxName) {
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MEXMEZona> resultados = new ArrayList<MEXMEZona>();
			try{
				sql = "SELECT * FROM EXME_Zona WHERE IsActive = 'Y'";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Zona");
				
				pstmt = DB.prepareStatement(sql, trxName);
				
				rs = pstmt.executeQuery();
				while (rs.next()){
					MEXMEZona zonas = new MEXMEZona(ctx, rs, trxName);
					resultados.add(zonas);
				}
			}
			catch(Exception e){
	    		s_log.log(Level.SEVERE, sql.toString(), e);
			}
			finally{
				DB.close(rs, pstmt);
			}
			MEXMEZona[] reenviar = new MEXMEZona[resultados.size()];
			resultados.toArray(reenviar);
			return reenviar;
	    }
	    
}
