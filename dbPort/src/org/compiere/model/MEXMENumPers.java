package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMENumPers extends X_EXME_NumPers {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMENumPers.class);

	/**
	 * Constructor de MEXMENumPers
	 * @param ctx                Propiedades
	 * @param EXME_NumPers_ID
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMENumPers(Properties ctx, int EXME_NumPers_ID, String trxName) {
		super(ctx, EXME_NumPers_ID, trxName);
	}
	
	/**
	 * Constructor de MEXMENumPers
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMENumPers(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMENumPers[] getNumsPers(Properties ctx, String trxName) {
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MEXMENumPers> resultados = new ArrayList<MEXMENumPers>();
			try{
				sql = "SELECT * FROM EXME_NumPers WHERE IsActive = 'Y'";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_NumPers");
				
				pstmt = DB.prepareStatement(sql, trxName);
				
				rs = pstmt.executeQuery();
				while (rs.next()){
					MEXMENumPers numPers = new MEXMENumPers(ctx, rs, trxName);
					resultados.add(numPers);
				}
			}
			catch(Exception e){
	    		s_log.log(Level.SEVERE, sql.toString(), e);
			}
			finally{
				DB.close(rs, pstmt);
			}
			MEXMENumPers[] reenviar = new MEXMENumPers[resultados.size()];
			resultados.toArray(reenviar);
			return reenviar;
	    }
	    
}
