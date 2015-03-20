package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMETipoVivienda extends X_EXME_TipoVivienda {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMETipoVivienda.class);

	/**
	 * Constructor de MEXMETipoVivienda
	 * @param ctx                Propiedades
	 * @param EXME_TipoVivienda_ID  ID de Vivienda
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMETipoVivienda(Properties ctx, int EXME_TipoVivienda_ID, String trxName) {
		super(ctx, EXME_TipoVivienda_ID, trxName);
	}
	
	/**
	 * Constructor de MEXMETipoVivienda
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMETipoVivienda(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMETipoVivienda[] getTiposVivenda(Properties ctx, String trxName)
	    {
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MEXMETipoVivienda> resultados = new ArrayList<MEXMETipoVivienda>();
			try{
				sql = "SELECT * FROM EXME_TipoVivienda WHERE IsActive = 'Y'";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_TipoVivienda");
				
				pstmt = DB.prepareStatement(sql, trxName);
				
				rs = pstmt.executeQuery();
				while (rs.next()){
					MEXMETipoVivienda tipoVivienda = new MEXMETipoVivienda(ctx, rs, trxName);
					resultados.add(tipoVivienda);
				}
			}
			catch(Exception e){
	    		s_log.log(Level.SEVERE, sql.toString(), e);
			}
			finally{
				DB.close(rs, pstmt);
			}
			MEXMETipoVivienda[] reenviar = new MEXMETipoVivienda[resultados.size()];
			resultados.toArray(reenviar);
			return reenviar;
	    }
	    
}
