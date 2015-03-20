package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEMetricasIMC extends X_EXME_Metricas_IMC{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger (MEXMEMetricasIMC.class);

	public MEXMEMetricasIMC(Properties ctx, int EXME_Metricas_IMC_ID, String trxName) {
		super(ctx, EXME_Metricas_IMC_ID, trxName);
	}

	public MEXMEMetricasIMC(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	/**
	 *  Regresa el ID de la metrica dependiendo del ID del signo vital
	 *
	 *@param  ctx       	 Contexto
	 *@param  estiloID       ID del signo vital
	 *@return                MEXMEMetricasFumador
	 */
	public static MEXMEMetricasIMC getMetrica(Properties ctx, int signoVitalID) {
		
		MEXMEMetricasIMC metrica = null;
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

		sql.append(" SELECT * FROM EXME_Metricas_IMC WHERE EXME_SignoVitalDet_ID = ? ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, signoVitalID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				metrica = new MEXMEMetricasIMC(ctx, rs, null);
			} 
               
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
    	}finally {
    		DB.close(rs, pstmt);
    		rs = null;
    		pstmt = null;
    	}        
        return metrica;
	}

}
	