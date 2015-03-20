package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMENumHabts extends X_EXME_NumHabts {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMENumHabts.class);

	/**
	 * Constructor de MEXMENumHabts
	 * @param ctx                Propiedades
	 * @param EXME_NumHabts_ID
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMENumHabts(Properties ctx, int EXME_NumHabts_ID, String trxName) {
		super(ctx, EXME_NumHabts_ID, trxName);
	}
	
	/**
	 * Constructor de MEXMENumHabts
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMENumHabts(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMENumHabts[] getNumsHabts(Properties ctx, String trxName)
	    {
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<MEXMENumHabts> resultados = new ArrayList<MEXMENumHabts>();
			try{
				sql = "SELECT * FROM EXME_NumHabts WHERE IsActive = 'Y'";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_NumHabts");
				
				pstmt = DB.prepareStatement(sql, trxName);
				
				rs = pstmt.executeQuery();
				while (rs.next()){
					MEXMENumHabts numHabts = new MEXMENumHabts(ctx, rs, trxName);
					resultados.add(numHabts);
				}
			}
			catch(Exception e){
	    		s_log.log(Level.SEVERE, sql.toString(), e);
			}
			finally{
				DB.close(rs, pstmt);
			}
			MEXMENumHabts[] reenviar = new MEXMENumHabts[resultados.size()];
			resultados.toArray(reenviar);
			return reenviar;
	    }
	    
}
