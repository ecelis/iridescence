/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author vperez
 * Clase para guardar notas por paciente
 */
public class MEXMENote extends X_EXME_Note {
	private static final long serialVersionUID = -3692343616801481508L;
	private static CLogger s_log = CLogger.getCLogger(MEXMENote.class);
	
	/**
	 * Obtiene los mensajes correspondientes segun el tableId y el recordId
	 * @return regresa el mensaje
	 */
	public static MEXMENote getNote(int adTableId, int recordId, int pacienteId) {
		MEXMENote notes = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT * FROM EXME_NOTE ")
				.append(" WHERE ")
				.append(" ISACTIVE = 'Y' AND ");
			if (adTableId == 0) {
				sql.append(" AD_TABLE_ID IS NULL AND RECORD_ID IS NULL ");
			} else {
				sql.append(" AD_TABLE_ID = ? AND RECORD_ID = ? ");
			}
				sql.append("  AND EXME_PACIENTE_ID = ?");

			pstmt = DB.prepareStatement(sql.toString(), null);
			if (adTableId == 0) {
				pstmt.setInt(1, pacienteId);
			} else {
				pstmt.setInt(1, adTableId);
				pstmt.setInt(2, recordId);
				pstmt.setInt(3, pacienteId);
			}
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				notes = new MEXMENote(Env.getCtx(), rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMENote.getNotes - sql: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return notes;
	}
	private String tabName = null;
	
	private String title = null;

	/**
	 * @param ctx
	 * @param EXME_Note_ID
	 * @param trxName
	 */
	public MEXMENote(Properties ctx, int EXME_Note_ID, String trxName) {
		super(ctx, EXME_Note_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMENote(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public String getTabName() {
		return tabName;
	}

	public String getTitle() {
		return title;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Obtiene la nota guardada en la base de datos, si no la encuentra devuelve una nota preparada para guardar
	 * @param tableId 
	 * @param recordId
	 * @param pacienteId
	 * @param titulo
	 * @param tabName
	 * @return
	 */
	public static MEXMENote getNotes(int tableId, int recordId, int pacienteId, String titulo, String tabName) {
		MEXMENote note = MEXMENote.getNote(tableId, recordId, pacienteId);
		if (note == null) {
			note = new MEXMENote(Env.getCtx(), 0, null);
			note.setAD_Table_ID(tableId);
			note.setRecord_ID(recordId);
			if(pacienteId > 0){
				note.setEXME_Paciente_ID(pacienteId);
			}
		}
		note.setTabName(tabName);
		note.setTitle(titulo);
		return note;
	}
}
