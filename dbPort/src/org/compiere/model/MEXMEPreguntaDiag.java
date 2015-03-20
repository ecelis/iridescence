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

public class MEXMEPreguntaDiag extends X_EXME_PreguntaDiag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** log de la clase*/
	private static CLogger log = CLogger.getCLogger(MEXMEPreguntaDiag.class);
	
	public MEXMEPreguntaDiag(Properties ctx, int EXME_PreguntaDiag_ID,
			String trxName) {
		super(ctx, EXME_PreguntaDiag_ID, trxName);
	}

	public MEXMEPreguntaDiag(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	
	public static List<MDiagnostico> getDiagnosis(final Properties ctx, final int EXME_Pregunta_ID, final String trxName){

		List<MDiagnostico> array = new ArrayList<MDiagnostico>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT EXME_PreguntaDiag.EXME_Diagnostico_ID ")
				.append("FROM EXME_PreguntaDiag ")
				.append("WHERE EXME_PreguntaDiag.EXME_Pregunta_ID = ? ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Pregunta_ID);
				
			result = pstmt.executeQuery();
			while(result.next()){
				array.add(new MDiagnostico(ctx, result.getInt(COLUMNNAME_EXME_Diagnostico_ID), null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result,pstmt);
		}

		return array;
	}
	
}
