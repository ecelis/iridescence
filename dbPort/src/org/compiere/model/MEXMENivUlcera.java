package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMENivUlcera extends X_EXME_NivelUlcera{
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMENivUlcera.class);
	
	/**
	 * Constructor de MEXMENivUlcera
	 * @param ctx                Propiedades
	 * @param EXME_RiesgoUlcera_ID  ID de Nivel de Ulcera
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMENivUlcera(Properties ctx, int EXME_NivUlcera_ID, String trxName) {
		super(ctx, EXME_NivUlcera_ID, trxName);
	}
	
	/**
	 * Constructor de MEXMENivUlcera
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMENivUlcera(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Regresa la lista de niveles de ulcera que hay en la Base de Datos
	 * @param ctx
	 * @param trxName
	 * @return
	 * @author J Rodriguez
	 */
	public static MEXMENivUlcera[] getNiveles(Properties ctx, String trxName) {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MEXMENivUlcera> resultados = new ArrayList<MEXMENivUlcera>();
		
		try {
			sql.append("SELECT EXME_NivelUlcera.* " + "FROM EXME_NivelUlcera  ")
				.append("WHERE EXME_NivelUlcera.IsActive = 'Y'")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_NivelUlcera"));
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MEXMENivUlcera nivUlcera = new MEXMENivUlcera(ctx, rs, trxName); //se crea el objeto Nivel de Ulcera
				resultados.add(nivUlcera); //se agrega el nivel de ulcera al arreglo
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		MEXMENivUlcera[] reenviar = new MEXMENivUlcera[resultados.size()];
		resultados.toArray(reenviar);
		return reenviar;
	}
	    
}
