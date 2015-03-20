package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMETipoHerida extends X_EXME_TipoHerida{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMETipoHerida.class);

	
	/**
	 * Constructor de MEXMETipoHerida
	 * @param ctx                Propiedades
	 * @param EXME_TipoHeridaID  ID de Tipo de Herida
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMETipoHerida(Properties ctx, int EXME_TipoHerida_ID, String trxName) {
		super(ctx, EXME_TipoHerida_ID, trxName);
	}
	
	/**
	 * Constructor de EXME_TipoHerida
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMETipoHerida(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	/**
     * Regresa la lista de tipos de heridas que hay en la Base de Datos
     * @param ctx
     * @param trxName
     * @return
     * @author J Rodriguez
     */
	public static MEXMETipoHerida[] getHeridas(Properties ctx, String trxName) {
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MEXMETipoHerida> resultados = new ArrayList<MEXMETipoHerida>();
		try {
			sql = "SELECT * FROM EXME_TipoHerida WHERE IsActive = 'Y'";
			
			sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_TipoHerida");

			pstmt = DB.prepareStatement(sql, trxName);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMETipoHerida therida = new MEXMETipoHerida(ctx, rs, trxName); //se crea el objeto Tipo Herida
				resultados.add(therida); //se agrega la herida al arreglo
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}
		MEXMETipoHerida[] reenviar = new MEXMETipoHerida[resultados.size()];
		resultados.toArray(reenviar);
		return reenviar;
	}
	    
}
