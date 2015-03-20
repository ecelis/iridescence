package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MHL7Note extends X_HL7_Note {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3329807360137351977L;
	private static CLogger log = CLogger.getCLogger(MHL7Note.class);

	public MHL7Note(Properties ctx, int HL7_Note_ID, String trxName) {
		super(ctx, HL7_Note_ID, trxName);
		setProcessed(false);
		setProcessing(false);
		setStatus(STATUS_InProgress);
		setTextMsg("Ok");
		setTextXML("Ok");
		setAD_User_ID(Env.getAD_User_ID(ctx));
		
	}

	public MHL7Note(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	protected boolean afterSave(boolean newRecord, boolean success) {

		return success;
	}

	/**
	 * 
	 * @param patientId
	 * @param filescda
	 */
	public static Map<Integer, String> getFilesExp(int patientId) {
		Map<Integer, String> filescda = new HashMap<Integer, String>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		StringBuilder query = new StringBuilder();
		query.append("SELECT N.HL7_NOTE_ID AS idReg, n.process || ' - '");
		query.append(" || n.description ||' - '||TO_CHAR(N.CREATED, ");
		query.append("'FMMON DD, YYYY - HH:MI:SS pm') AS FECHA ");
		query.append("FROM   HL7_NOTE N ");
		query.append("WHERE  N.PROCESS IN ('CRD', 'CSD', 'CCD', 'CCR') ");
		query.append(" AND (N.DESCRIPTION LIKE '%Received%' OR N.DESCRIPTION LIKE '%Uploaded%') ");
		query.append("AND    N.RECORD_ID = ? ");
		query.append("ORDER BY TO_CHAR(N.CREATED, 'DD-MM-YYYY - HH:MI:SS pm')");
		try {
			pstmt = DB.prepareStatement(query.toString(), null);
			pstmt.setInt(1, patientId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				filescda.put(rs.getInt("idReg"), rs.getString("fecha"));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "obteniendo notas de hl7", e);
			filescda = null;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				log.log(Level.SEVERE, "closing PreparedStatement or ResulSet",
						e);
			}
			pstmt = null;
			rs = null;
		}
		return filescda;
	}

	public static HashMap<String, String> buscaResp(String process, int idRegH) {
		HashMap<String,String> datos = new HashMap<String,String>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;	
		StringBuilder sqlSelect  = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        sqlSelect.append("SELECT HL7_NOTE_ID, TEXTMSG, DESCRIPTION FROM HL7_NOTE WHERE PROCESS = ? AND PROCESSED = 'N' AND RECORD_ID = ? ");
        try {
			pstmt = DB.prepareStatement(sqlSelect.toString(),null);
			pstmt.setString(1, process);
			pstmt.setInt(2, idRegH);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				datos.put("idReg", rs.getString("HL7_NOTE_ID"));
				datos.put("Status", rs.getString("TextMsg"));
				datos.put("Description", rs.getString("Description"));
			}
        }	catch (Exception e) {
        	log.log(Level.SEVERE, "obteniendo respuesta de proceso"+process, e);
			datos.clear();			
		} finally {
			DB.close(rs, pstmt);
	    }
		return datos;
	}
	
	/**
	 * Raul
	 * Se sobreescribe el metodo ya que PO no encripta datos de tipo CLOB, para
	 * cumplir con el test script "§170.302 (v) Encryption when exchanging
	 * electronic health information" de la certificacion ARRA
	 */
	/*
	@Override
	public void setTextXML(String TextXML) {
		super.setTextXML(SecureEngine.encrypt(TextXML));
	}*/

	/**
	 * Raul
	 * Se sobreescribe el metodo ya que PO no encripta datos de tipo CLOB, para
	 * cumplir con el test script §170.302 (v) Encryption when exchanging
	 * electronic health information de la certificacion ARRA
	 */
	/*
	@Override
	public String getTextXML() {
		return SecureEngine.decrypt(super.getTextXML());
	}
	*/

}
