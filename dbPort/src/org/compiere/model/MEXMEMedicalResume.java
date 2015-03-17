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

public class MEXMEMedicalResume extends X_EXME_MedicalResume{

	private static CLogger s_log = CLogger.getCLogger (MEXMEMedicalResume.class);
	private static final long serialVersionUID = 1L;
	
	public MEXMEMedicalResume(Properties ctx, int EXME_MedicalResume_ID, String trxName) {
		super(ctx, EXME_MedicalResume_ID, trxName);
	}

	public MEXMEMedicalResume(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMEMedicalResume getFromActPaciente(Properties ctx, int EXME_ActPaciente_ID, String trxName){
		MEXMEMedicalResume resumen = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	sql.append(" select * from EXME_MEDICALRESUME where EXME_ACTPACIENTE_ID = ? ");
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_MedicalResume"));
        
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_ActPaciente_ID);
			
			rs = pstmt.executeQuery();
			if (rs.next()){
				resumen = new MEXMEMedicalResume(ctx, rs, trxName);
			}
		} catch (SQLException e) {
            s_log.log(Level.SEVERE, "getFromActPaciente (" + sql + ")", e);
            
        } finally {
        	DB.close(rs,pstmt);
            rs = null;
            pstmt = null;
        }
		return resumen;
	}
	
	public static List<MDiagnostico> getDiagnosticos(Properties ctx, int EXME_ActPaciente_ID, String trxName){
		List<MDiagnostico> diagnosticos = new ArrayList<MDiagnostico>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	sql.append(" SELECT DD.* ")
    	   .append(" FROM EXME_ACTPACIENTEDIAG DI, EXME_DIAGNOSTICO DD ")
    	   .append(" WHERE DI.EXME_ACTPACIENTE_ID = ? ")
    	   .append(" AND DI.EXME_DIAGNOSTICO_ID = DD.EXME_DIAGNOSTICO_ID ");
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ActPacienteDiag", "DI"));
        
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_ActPaciente_ID);
			
			rs = pstmt.executeQuery();
			if (rs.next()){
				diagnosticos.add(new MDiagnostico(ctx, rs, trxName));
			}
		} catch (SQLException e) {
            s_log.log(Level.SEVERE, "getDiagnosticos (" + sql + ")", e);
            
        } finally {
        	DB.close(rs,pstmt);
            rs = null;
            pstmt = null;
        }
		return diagnosticos;
	}

}
