/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author LLama
 *
 */
public class MEXMEFechaReporte extends X_EXME_FechaReporte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3936491581406445037L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEFechaReporte.class);
	
	/**
	 * @param ctx
	 * @param EXME_FechaReporte_ID
	 * @param trxName
	 */
	public MEXMEFechaReporte(Properties ctx, int EXME_FechaReporte_ID, String trxName) {
		super(ctx, EXME_FechaReporte_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEFechaReporte(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Regresa la ultima fecha de reporte de paciente para transplante
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 */
	public static Date getLastFecha(Properties ctx, int EXME_Paciente_ID, String trxName) {
		
		Date retValue = null;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT MAX(EXME_FechaReporte.fechaReporte) as fechaReporte ")
				.append("FROM EXME_FechaReporte ")
				.append("WHERE EXME_FechaReporte.isActive = 'Y' ")
				.append("AND EXME_FechaReporte.EXME_Paciente_ID = ? ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_FechaReporte"));
		
			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, EXME_Paciente_ID);

			rs = psmt.executeQuery();
		
			if (rs.next()) {
				retValue =  rs.getDate("fechaReporte");
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
			rs = null;
			psmt = null;
			sql = null;
		}
		
		return retValue;
	}

}