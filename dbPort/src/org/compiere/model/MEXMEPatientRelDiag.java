package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * 
 * @author rsolorzano
 *
 */
public class MEXMEPatientRelDiag extends X_EXME_PatientRel_Diag{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private static CLogger		slog = CLogger.getCLogger (MEXMEPatientRelDiag.class);

	public MEXMEPatientRelDiag(final Properties ctx, final int EXME_PatientRel_Diag_ID, final String trxName) {
		super(ctx, EXME_PatientRel_Diag_ID, trxName);
	}

	public MEXMEPatientRelDiag(final Properties ctx,final ResultSet rs,final String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * regresa las condiciones medicas de los parientes / familiares del paciente.
	 * @param ctx
	 * @param trxName
	 * @param patientID
	 * @return
	 */
	public static List<MEXMEPatientRelDiag> getByRelative(final Properties ctx, final String trxName, final int relativeID){
    	
		final List<MEXMEPatientRelDiag> lista = new ArrayList<MEXMEPatientRelDiag>();
		
    	final StringBuilder sql = new StringBuilder("");
    	sql.append("SELECT * FROM EXME_PATIENTREL_DIAG ")
    	.append(" WHERE EXME_PATIENTREL_ID = ? AND ISACTIVE = 'Y' ");
    	
    	sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, relativeID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				lista.add(new MEXMEPatientRelDiag(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {			
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lista;
    }
	
	/**
	 * regresa el diagnostico de un familiar o pariente
	 * @param ctx
	 * @param trxName
	 * @param relativeID
	 * @param diagID
	 * @return
	 */
	public static MEXMEPatientRelDiag getByDiag(final Properties ctx,final String trxName,final int relativeID,final int diagID){
    	
		MEXMEPatientRelDiag diag = null;
		
    	final StringBuilder sql = new StringBuilder("");
    	sql.append("SELECT * FROM EXME_PATIENTREL_DIAG ")
    	.append(" WHERE EXME_PATIENTREL_ID = ? AND EXME_DIAGNOSTICO_ID = ? AND ISACTIVE = 'Y' ");
    	
    	sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, relativeID);
			pstmt.setInt(2, diagID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				diag = new MEXMEPatientRelDiag(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {			
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return diag;
    }
	
	
	

}
