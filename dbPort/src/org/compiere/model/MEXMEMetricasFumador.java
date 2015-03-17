package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEMetricasFumador extends X_EXME_Metricas_Fumador{

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger (MEXMEMetricasFumador.class);

	public MEXMEMetricasFumador(Properties ctx, int EXME_Metricas_Fumador_ID, String trxName) {
		super(ctx, EXME_Metricas_Fumador_ID, trxName);
	}
	
	public MEXMEMetricasFumador(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 *  Regresa el ID de la metrica dependiendo del ID del estilo de Vida
	 *
	 *@param  ctx       	 Contexto
	 *@param  estiloID       ID del estilo de vida
	 *@return                MEXMEMetricasFumador
	 */
	public static MEXMEMetricasFumador getMetrica(Properties ctx, int estiloID) {
		
		MEXMEMetricasFumador metrica = null;
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

		sql.append(" SELECT * FROM EXME_Metricas_Fumador WHERE EXME_EstiloVidaPaciente_ID = ? ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, estiloID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				metrica = new MEXMEMetricasFumador(ctx, rs, null);
			} 
               
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
    	}finally {
    		DB.close(rs, pstmt);
    	}        
        return metrica;
	}
	
}
