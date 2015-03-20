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
public class MEXMEPatientRel extends X_EXME_PatientRel{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private static CLogger		slog = CLogger.getCLogger (MEXMEPatientRelDiag.class);
	
	public MEXMEPatientRel(final Properties ctx, final int EXME_PatientRel_ID, final String trxName) {
		super(ctx, EXME_PatientRel_ID, trxName);
	}

	public MEXMEPatientRel(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}
	
	
	/**
	 * regresa los familiares /parientes de un paciente
	 * @param ctx
	 * @param trxName
	 * @param patientID
	 * @return
	 */
	public static List<MEXMEPatientRel> getByPatient(final Properties ctx, final String trxName, final int patientID){
    	
		final List<MEXMEPatientRel> lista = new ArrayList<MEXMEPatientRel>();
		
    	final StringBuilder sql = new StringBuilder("");
    	sql.append("SELECT * FROM EXME_PATIENTREL")
    	.append(" WHERE EXME_PACIENTE_ID = ?  AND ISACTIVE = 'Y' ");
    	
    	sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, patientID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				lista.add(new MEXMEPatientRel(ctx, rs, trxName));
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

	
	

}
