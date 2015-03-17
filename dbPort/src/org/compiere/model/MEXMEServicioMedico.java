package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEServicioMedico extends X_EXME_Servicio_Medico{
	
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEServicioMedico.class);
	
	private static final long serialVersionUID = -8016114507601642204L;
	
	public MEXMEServicioMedico(Properties ctx, int EXME_Servicio_Medico_ID,
			String trxName) {
		super(ctx, EXME_Servicio_Medico_ID, trxName);
	}

	public MEXMEServicioMedico(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}		
	
	public static MEXMEServicioMedico[] getServiciosMedicos(Properties ctx, String trxName) {
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MEXMEServicioMedico> resultados = new ArrayList<MEXMEServicioMedico>();
		try{
			sql = "SELECT * FROM EXME_Servicio_Medico WHERE IsActive = 'Y'";
			
			sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Servicio_Medico");
			
			pstmt = DB.prepareStatement(sql, trxName);
			
			rs = pstmt.executeQuery();
			while (rs.next()){
				MEXMEServicioMedico servicios = new MEXMEServicioMedico(ctx, rs, trxName);
				resultados.add(servicios);
			}
		}
		catch(Exception e){
    		s_log.log(Level.SEVERE, sql.toString(), e);
		}
		finally{
			DB.close(rs, pstmt);
		}
		
		MEXMEServicioMedico[] reenviar = new MEXMEServicioMedico[resultados.size()];
		resultados.toArray(reenviar);
		return reenviar;
    }
}
