package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEPrescriptionLog extends X_EXME_PrescriptionLog {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEPrescriptionLog.class);
	
	public MEXMEPrescriptionLog(Properties ctx, int EXME_PrescriptionLog_ID,
			String trxName) {
		super(ctx, EXME_PrescriptionLog_ID, trxName);
	}

	public MEXMEPrescriptionLog(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * consulta del log por cuenta paciente por tabla
	 * @param ctx Propiedades en el contexto
	 * @param EXME_CtaPac_ID  Cuenta paciente de donde se buscara el registro, uno por cuenta
	 * @param AD_Table_ID  Tabla relacionada 
	 * @param trxName Nombre de transaccion
	 * @return
	 */
	public static MEXMEPrescriptionLog get(Properties ctx,
			int EXME_CtaPac_ID, int AD_Table_ID, String trxName) {
		
		List<Integer> params = new ArrayList<Integer>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM  exme_PrescriptionLog ")
		.append(" WHERE IsActive = 'Y' AND EXME_CtaPac_ID = ?  ")
		.append(" AND AD_Table_ID = ?  ");
		
		params.add(new Integer(EXME_CtaPac_ID));
		params.add(new Integer(AD_Table_ID));
		return get( ctx, sql.toString(), params, trxName) ;
		
	}
	
	public static MEXMEPrescriptionLog get(Properties ctx,
			String sql, List<?> params, String trxName) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMEPrescriptionLog results = null;
		
		try {
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if(params!=null)
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				results = new MEXMEPrescriptionLog(ctx, rs, trxName); 
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return results;
	}
}
