package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEEjecucionCuest extends X_EXME_Ejecucion_Cuest implements DocAction {

	private static CLogger s_log = CLogger.getCLogger(MEXMEEjecucionCuest.class);
	private static final long serialVersionUID = -405186056132215126L;

	/**
	 * Obtiene/Crea una ejecución de cuestionario para la tabla, registro y cuestionario inficado
	 * 
	 * @param ctx
	 *            Contexto
	 * @param tableId
	 *            Tabla
	 * @param recordId
	 *            Registro de Tabla
	 * @param cuestId
	 *            Cuestionario
	 * @param trxName
	 *            Trx
	 * @return
	 */
	public static MEXMEEjecucionCuest get(Properties ctx, int tableId, int recordId, int cuestId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_ejecucion_cuest ");
		sql.append("WHERE ");
		sql.append("  exme_cuestionario_id = ? AND ");
		sql.append("  ad_table_id = ? AND ");
		sql.append("  record_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEEjecucionCuest.Table_Name));

		MEXMEEjecucionCuest ejecucionCuest = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestId);
			pstmt.setInt(2, tableId);
			pstmt.setInt(3, recordId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ejecucionCuest = new MEXMEEjecucionCuest(ctx, rs, null);
			} else {
				ejecucionCuest = new MEXMEEjecucionCuest(ctx, 0, null);
				ejecucionCuest.setAD_Table_ID(tableId);
				ejecucionCuest.setRecord_ID(recordId);
				ejecucionCuest.setEXME_Cuestionario_ID(cuestId);
				ejecucionCuest.setEXME_EstServ_ID(Env.getEXME_EstServ_ID(ctx));
				ejecucionCuest.setDocStatus(MEXMEEjecucionCuest.DOCSTATUS_Drafted);
				ejecucionCuest.setDocAction(MEXMEEjecucionCuest.DOCACTION_Approved);
				ejecucionCuest.setIsApproved(false);
				ejecucionCuest.setIsInTransit(false);
				ejecucionCuest.setIsPrinted(false);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
			ejecucionCuest = null;
		} finally {
			DB.close(rs, pstmt);
		}
		return ejecucionCuest;
	}

	public MEXMEEjecucionCuest(Properties ctx, int EXME_Ejecucion_Cuest_ID, String trxName) {
		super(ctx, EXME_Ejecucion_Cuest_ID, trxName);
	}

	public MEXMEEjecucionCuest(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	public boolean approveIt() {
		return false;
	}

	@Override
	public boolean closeIt() {
		return false;
	}

	@Override
	public String completeIt() {
		return null;
	}

	@Override
	public File createPDF() {
		return null;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		return null;
	}

	@Override
	public int getC_Currency_ID() {
		return 0;
	}

	@Override
	public int getDoc_User_ID() {
		return 0;
	}

	@Override
	public String getDocumentInfo() {
		return null;
	}

	@Override
	public String getProcessMsg() {
		return null;
	}

	@Override
	public String getSummary() {
		return null;
	}

	@Override
	public boolean invalidateIt() {
		return false;
	}

	@Override
	public String prepareIt() {
		return null;
	}

	@Override
	public boolean processIt(String action) throws Exception {
		return false;
	}

	@Override
	public boolean reActivateIt() {
		return false;
	}

	@Override
	public boolean rejectIt() {
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		return false;
	}

	@Override
	public boolean reverseCorrectIt() {
		return false;
	}

	@Override
	public boolean unlockIt() {
		return false;
	}

	@Override
	public boolean voidIt() {
		return false;
	}

}
