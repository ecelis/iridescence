package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Constantes;
import org.compiere.util.Utilerias;

public class MEXMELogMedicalDecision extends X_EXME_LogMedicalDecision {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public MEXMELogMedicalDecision(Properties ctx, int EXME_LogMedicalDecision_ID, String trxName) {
		super(ctx, EXME_LogMedicalDecision_ID, trxName);
	}

	public MEXMELogMedicalDecision(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static MEXMELogMedicalDecision getFrom(final PO poModel) {
		return new Query(poModel.getCtx(), Table_Name, " AD_Table_ID=? AND Record_ID=? ", null)//
			.setOnlyActiveRecords(true)//
			.setParameters(poModel.get_Table_ID(), poModel.get_ID())//
			.addAccessLevelSQL(true)//
			.first();
	}

	public void setMedicalComment(StringBuilder alertMsg, String comments) {
		if ((alertMsg == null || alertMsg.length() == 0) && StringUtils.isBlank(comments)) {
			return;
		}
		if (alertMsg == null) {
			alertMsg = new StringBuilder();
		} else if (alertMsg.length() > 0 && StringUtils.isNotBlank(comments)) {
			alertMsg.append(Constantes.NEWLINE);
			alertMsg.append("------------");
		}
		if (StringUtils.isNotBlank(comments)) {
			alertMsg.append(Utilerias.getMsg(getCtx(), "enfermeria.msg.cateteresSondas.comentarios"));
			alertMsg.append("------------");
			alertMsg.append(Constantes.NEWLINE);
			alertMsg.append(comments);
		}
		super.setMedicalComment(alertMsg.toString());
	}

	public MEXMELogMedicalDecision(Properties ctx,  PO poFrom, String trxName) {
		super(ctx, 0, trxName);
		if (poFrom != null) {
			super.setAD_Table_ID(poFrom.get_Table_ID());
			super.setRecord_ID(poFrom.get_ID());
		}
	}

}
