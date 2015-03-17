package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEProcEsp extends X_EXME_ProcEsp{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEProcEsp.class);

	
	/**
	 * Constructor de MEXMEProcEsp
	 * @param ctx                Propiedades
	 * @param EXME_Procedencia_ID  ID de Procedimientos Especiales
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMEProcEsp(Properties ctx, int EXME_ProcEsp_ID, String trxName) {
		super(ctx, EXME_ProcEsp_ID, trxName);
		
	}
	
	/**
	 * Constructor de MEXMEProcEsp
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMEProcEsp(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	
	/**
     * Regresa la lista de Procedimientos Espeeciales que hay en la Base de Datos
     * @param ctx
     * @param trxName
     * @return
     * @author J Rodriguez
     */
 	public static MEXMEProcEsp[] getProcEsp(Properties ctx, String trxName) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MEXMEProcEsp> resultados = new ArrayList<MEXMEProcEsp>();
		try {
			sql.append("SELECT * FROM EXME_ProcEsp WHERE IsActive = 'Y' ");

			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_ProcEsp"));

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			rs = pstmt.executeQuery();
				while (rs.next()) {
				MEXMEProcEsp procesp = new MEXMEProcEsp(ctx, rs, trxName); //se crea el objeto Procedimientos Especiales
				resultados.add(procesp); //se agrega el procedimiento al arreglo
			}
				
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		MEXMEProcEsp[] reenviar = new MEXMEProcEsp[resultados.size()];
		resultados.toArray(reenviar);
		return reenviar;
	}
	    
}
