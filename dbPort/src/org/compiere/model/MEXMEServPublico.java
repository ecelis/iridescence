package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEServPublico extends X_EXME_ServPublico {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEServPublico.class);

	/**
	 * Constructor de MEXMEServPublico
	 * @param ctx                Propiedades
	 * @param EXME_ServPublico_ID  
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMEServPublico(Properties ctx, int EXME_ServPublico_ID, String trxName) {
		super(ctx, EXME_ServPublico_ID, trxName);
	}
	
	/**
	 * Constructor de MEXMEServPublico
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMEServPublico(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMEServPublico[] getServsPublicos(Properties ctx, String trxName) {
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MEXMEServPublico> resultados = new ArrayList<MEXMEServPublico>();
			try{
				sql = "SELECT * FROM EXME_ServPublico WHERE IsActive = 'Y'";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_ServPublico");
				
				pstmt = DB.prepareStatement(sql, trxName);
				
				rs = pstmt.executeQuery();
				while (rs.next()){
					MEXMEServPublico servsPublicos = new MEXMEServPublico(ctx, rs, trxName);
					resultados.add(servsPublicos);
				}
			}
			catch(Exception e){
	    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			}
			finally{
				DB.close(rs, pstmt);
			}
			MEXMEServPublico[] reenviar = new MEXMEServPublico[resultados.size()];
			resultados.toArray(reenviar);
			return reenviar;
	    }
	    
}
