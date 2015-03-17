package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEHistConsulta extends X_EXME_HistConsulta {

	private static final long serialVersionUID = -834303325875947520L;
	
	/** Static logger           */
    private static CLogger s_log = CLogger.getCLogger(MEXMEHistConsulta.class);

	public MEXMEHistConsulta(Properties ctx, int EXME_HistConsulta_ID,
			String trxName) {
		super(ctx, EXME_HistConsulta_ID, trxName);
	}

	public MEXMEHistConsulta(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param exmePacienteID
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
	public static List<MEXMEHistConsulta> getForPatient(Properties ctx, int exmecitaMedica, String trxName) throws SQLException {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEHistConsulta> cita = new ArrayList<MEXMEHistConsulta>();
		
		try {
			sql.append("select * from  EXME_HistConsulta where EXME_CITAMEDICA_ID = ?")
			.append(" AND EXME_HistConsulta.IsActive = 'Y' order by FECHACITA desc");
						
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exmecitaMedica);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				cita.add(new MEXMEHistConsulta(ctx, rs, trxName));
			}
			
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return cita;
	}
}
