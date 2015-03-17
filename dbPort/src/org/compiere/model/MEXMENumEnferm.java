package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMENumEnferm extends X_EXME_NumEnferm {
	
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMENumEnferm.class);

	/**
	 * Constructor de MEXMENumEnferm
	 * @param ctx                Propiedades
	 * @param EXME_ServPublico_ID
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMENumEnferm(Properties ctx, int EXME_NumEnferm_ID, String trxName) {
		super(ctx, EXME_NumEnferm_ID, trxName);
	}
	
	/**
	 * Constructor de MEXMENumEnferm
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMENumEnferm(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMENumEnferm[] getNumsEnferms(Properties ctx, String trxName) {
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MEXMENumEnferm> resultados = new ArrayList<MEXMENumEnferm>();
			try{
				sql = "SELECT * FROM EXME_NumEnferm WHERE IsActive = 'Y'";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_NumEnferm");
				
				pstmt = DB.prepareStatement(sql, trxName);
				
				rs = pstmt.executeQuery();
				while (rs.next()){
					MEXMENumEnferm numsEnferms = new MEXMENumEnferm(ctx, rs, trxName);
					resultados.add(numsEnferms);
				}
			}
			catch(Exception e){
	    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			}
			finally{
	    		DB.close(rs,pstmt);
			}
			MEXMENumEnferm[] reenviar = new MEXMENumEnferm[resultados.size()];
			resultados.toArray(reenviar);
			return reenviar;
	    }
	    
}
