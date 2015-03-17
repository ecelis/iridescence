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

public class MEXMEPreguntaListaDiag extends X_EXME_PreguntaListaDiag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** log de la clase*/
	private static CLogger log = CLogger.getCLogger(MEXMEPreguntaDiag.class);
	
	public MEXMEPreguntaListaDiag(Properties ctx, int EXME_PreguntaListaDiag_ID,
			String trxName) {
		super(ctx, EXME_PreguntaListaDiag_ID, trxName);
	}

	public MEXMEPreguntaListaDiag(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	public static List<MDiagnostico> getDiagnosis(final Properties ctx, final int EXME_Pregunta_ID, final String trxName){

		List<MDiagnostico> array = new ArrayList<MDiagnostico>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT EXME_PreguntaListaDiag.EXME_Diagnostico_ID ")
				.append("FROM EXME_PreguntaListaDiag ")
				.append("WHERE EXME_PreguntaListaDiag.EXME_Pregunta_Lista_ID = ? ");
			
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
