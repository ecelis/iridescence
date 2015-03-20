package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.billing.BillingQueueEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 
 * @author rsolorzano
 * 
 */
public class MEXMEBillingRule extends X_EXME_BillingRule {

	private static CLogger log = CLogger.getCLogger(MEXMEBillingRule.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 6389919706872152225L;

	/**
	 * Get all the headers of billing queue rules
	 * 
	 * @param ctx
	 * @param active
	 * @param all
	 * @return
	 */
	public static List<MEXMEBillingRule> getAll(Properties ctx, boolean active, boolean all) {

		final List<MEXMEBillingRule> retValue = new ArrayList<MEXMEBillingRule>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT * FROM EXME_BILLINGRULE ");
		if (!all) {
			if (active) {
				sql.append("WHERE ISACTIVE = 'Y'");
			} else {
				sql.append("WHERE ISACTIVE = 'N'");
			}

		} else {
			sql.append("WHERE 1=1 ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append("ORDER BY NAME");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MEXMEBillingRule(ctx, rs, null));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getAll", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * 
	 * @param ctx
	 * @param EXME_BillingRule_ID
	 * @param trxName
	 */
	public MEXMEBillingRule(Properties ctx, int EXME_BillingRule_ID, String trxName) {
		super(ctx, EXME_BillingRule_ID, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEBillingRule(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Valida si la regla se cumple o no para una cuenta paciente
	 * 
	 * @param ctaPacId
	 * @return
	 */
	public boolean isValidated(MEXMECtaPac ctaPac) {

		boolean isValidated = true;
		final Date admitDate = new Date(ctaPac.getDateOrdered().getTime());
		final Date dischargeDate = new Date(ctaPac.getFechaAlta().getTime());

		// revisa si la cuenta es elegible para excepciones, casos: tipo de paciente, estacion de servicio, aseguradora y diagnostico (con operador NA)
		final StringBuilder sqlEncounter = BillingQueueEngine.getSearchEncounterQuery(this);
		
		if (MEXMECtaPac.isEncounterException(Env.getCtx(), sqlEncounter, ctaPac.getEXME_CtaPac_ID(), null)) {

			isValidated = false;

			final List<MEXMEBillingRuleDet> listDet = MEXMEBillingRuleDet.getAllByID(Env.getCtx(), this.getEXME_BillingRule_ID());

			if (listDet != null && !listDet.isEmpty()) {
				for (MEXMEBillingRuleDet ruleDet : listDet) {

					// revisa si cae en alguna excepcion de tipo revenue code
					if (ruleDet.getEXME_BillingFilter().getAD_TableEngine_ID() == MCtaPacDet.Table_ID ) {

						final StringBuilder queryRevStr = BillingQueueEngine.getRevCodesQuery(ruleDet);

						final int revCode = MEXMECtaPac.operateCodeException(Env.getCtx(), queryRevStr, ctaPac.getEXME_CtaPac_ID(), ruleDet.getEXME_BillingFilter().getAD_TableEngine_ID(), null);

						if (BillingQueueEngine.hasCodeException(ruleDet, revCode, admitDate, dischargeDate)) {
							isValidated = false;
							break;
						}else{
							isValidated = true;
						}
					}// revisa si cae en alguna excepcion de tipo ocurrence code, value code o span code
					else if (ruleDet.getEXME_BillingFilter().getAD_TableEngine_ID() == MEXMEClaimCodes.Table_ID) {

						final StringBuilder queryStr = BillingQueueEngine.getCodesQuery(ruleDet);
						
						final int revCode = MEXMECtaPac.operateCodeException(Env.getCtx(), queryStr, ctaPac.getEXME_CtaPac_ID(), ruleDet.getEXME_BillingFilter().getAD_Table_ID(), null);

						if (BillingQueueEngine.hasCodeException(ruleDet, revCode, admitDate, dischargeDate)) {
							isValidated = false;
							break;
						}else{
							isValidated = true;
						}
					}//revisa si cae en una excepcion de tipo diagnostico
					else if (ruleDet.getEXME_BillingFilter().getAD_TableEngine_ID() == MActPacienteDiag.Table_ID) {

						final StringBuilder queryStr = BillingQueueEngine.getDiagQuery(ruleDet);
						
						final int valor = MEXMECtaPac.operateCodeException(Env.getCtx(), queryStr, ctaPac.getEXME_CtaPac_ID(), ruleDet.getEXME_BillingFilter().getAD_Table_ID(), null);

						if (BillingQueueEngine.hasCodeException(ruleDet, valor, admitDate, dischargeDate)) {
							isValidated = false;
							break;
						}else{
							isValidated = true;
						}
					}

					
				}

			}

		} else {
			isValidated = true;
		}
		
		return isValidated;
	}
}
