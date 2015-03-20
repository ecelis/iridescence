package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEOcupacionClas extends X_EXME_Ocupacion_Clas {

	/**
	 * Constructor de MEXMEOcupacionClas
	 * @param ctx                Propiedades
	 * @param EXME_Ocupacion_Clas
	 * @param trxName            Nombre de la transaccion
	 *
	 */
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEOcupacionClas.class);

	public MEXMEOcupacionClas(Properties ctx, int EXME_Ocupacion_Clas, String trxName) {
		super(ctx, EXME_Ocupacion_Clas, trxName);
	}
	
	/**
	 * Constructor de MEXMEOcupacionClas
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMEOcupacionClas(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	/**
	 * - jcruz -
	 * regresa una lista de ocupaciones
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MEXMEOcupacionClas[] getOcupaciones(Properties ctx, String trxName)
    {
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MEXMEOcupacionClas> resultados = new ArrayList<MEXMEOcupacionClas>();
		try{
			sql = "SELECT * FROM EXME_Ocupacion_Clas WHERE IsActive = 'Y'";
			
			sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Ocupacion_Clas");
			
			pstmt = DB.prepareStatement(sql, trxName);
			
			rs = pstmt.executeQuery();
			while (rs.next()){
				MEXMEOcupacionClas ocupacionClas = new MEXMEOcupacionClas(ctx, rs, trxName); //se crea el objeto
				resultados.add(ocupacionClas); //se agrega al arreglo
			}
		}
		catch(Exception e){
    		s_log.log(Level.SEVERE, sql.toString(), e);
		}
		finally{
			DB.close(rs, pstmt);
		}
		MEXMEOcupacionClas[] reenviar = new MEXMEOcupacionClas[resultados.size()];
		resultados.toArray(reenviar);
		return reenviar;
    }
}
