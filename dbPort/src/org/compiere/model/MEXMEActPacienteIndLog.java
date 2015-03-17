/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author lama
 * 
 */
public class MEXMEActPacienteIndLog extends X_EXME_ActPacienteIndLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param ctx
	 * @param EXME_ActPacienteIndLog_ID
	 * @param trxName
	 */
	public MEXMEActPacienteIndLog(Properties ctx, int EXME_ActPacienteIndLog_ID, String trxName) {
		super(ctx, EXME_ActPacienteIndLog_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEActPacienteIndLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MEXMEActPacienteIndLog> get(Properties ctx, int actpacienteIndh, String whereClause, Object... params) {
		final List<MEXMEActPacienteIndLog> resultados = new ArrayList<MEXMEActPacienteIndLog>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT EXME_ActPacienteIndLog.* ");
		sql.append(" FROM EXME_ActPacienteIndLog  ");
		sql.append(" WHERE EXME_ActPacienteIndLog.isActive = 'Y' ");
		sql.append(" AND EXME_ActPacienteIndLog.EXME_ActPacienteIndH_ID = ?  ");
		sql.append(whereClause == null ? "" : whereClause);
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		sql.append(" ORDER BY EXME_ActPacienteIndLog.created desc, EXME_ActPacienteIndLog.exme_actpacienteind_id");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), null);

			int j = 1;
			pstmt.setInt(j++, actpacienteIndh);

			for (int i = 0; i < params.length; i++) {
				DB.setParameter(pstmt, j++, params[i]);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MEXMEActPacienteIndLog model = new MEXMEActPacienteIndLog(ctx, rs, null);// Lama
				resultados.add(model);
			}
		} catch (Exception e) {
			// s_log.log(Level.SEVERE, sql.toString(), e);
			resultados.clear();
			// throw new SQLException(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}

		return resultados;

	}

	private static MEXMEActPacienteIndLog save(final MEXMEActPacienteInd ind, 
			final String reference, final Object value1, final Object value2) {
		// actualiza el estatus, asigna el trxName (importante ERROR de bloqueo)
		ind.getActPacIndH().set_TrxName(ind.get_TrxName());
		ind.getActPacIndH().setStatus(MEXMEActPacienteIndH.STATUS_CorrectedResults);
		// registrar el log
		final MEXMEActPacienteIndLog log = new MEXMEActPacienteIndLog(ind.getCtx(), 0, ind.get_TrxName());
		log.setEXME_ActPacienteIndH_ID(ind.getActPacIndH().get_ID());
		log.setEXME_ActPacienteInd_ID(ind.get_ID());
		log.setReference(reference);
		if (MEXMEActPacienteDet.Table_Name.equals(reference)) {
			log.setFolio((Integer) value1);
		} else if (MEXMEActPacienteInd.Table_Name.equals(reference)) {
			if (value1 instanceof String && value1 != null) {
				log.setAnotaciones((String) value1);
			}
			if (value2 instanceof Integer && value2 != null) {
				log.setAD_Attachment_ID((Integer) value2);
			}
		} else if (MEstudiosOBX.Table_Name.equals(reference)) {
			log.setSeqNo((Integer) value1);
		}
		if (log.save() && ind.getActPacIndH().save()) {
			return log;
		} else {
			return null;
		}
	}

	public static MEXMEActPacienteIndLog saveInd(MEXMEActPacienteInd ind, String anotaciones, int attachmentID) {
		return save(ind, MEXMEActPacienteInd.Table_Name, anotaciones, attachmentID > 0 ? attachmentID : null);
	}

	public static MEXMEActPacienteIndLog saveObx(MEXMEActPacienteInd ind, int seqNo) {
		return save(ind, MEstudiosOBX.Table_Name, seqNo, null);
	}

	public static MEXMEActPacienteIndLog saveQuest(MEXMEActPacienteInd ind, int folio) {
		return save(ind, MEXMEActPacienteDet.Table_Name, folio, null);
	}

}
