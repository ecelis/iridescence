package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.confHL7.MessageGenerator;

public class MEXMEClaimMessage extends X_EXME_ClaimMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3105927266746785660L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEClaimMessage.class);
	private static String msgSuccess = "Claim Message succesfully created.";
	private static String msgFailure = "Claim Message could not be created.";
	private static String msgStatus = "Claim Process: ";

	public MEXMEClaimMessage(Properties ctx, int EXME_ClaimMessage_ID, String trxName) {
		super(ctx, EXME_ClaimMessage_ID, trxName);
	}
	
	public MEXMEClaimMessage(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMEClaimMessage get(Properties ctx, int EXME_CtaPacExt_ID, String confType, String trxName) {
		return get(ctx, EXME_CtaPacExt_ID, 0, confType, trxName);
	}
	
	public static MEXMEClaimMessage get(Properties ctx, int EXME_CtaPacExt_ID, int C_BPartner_ID, String confType, String trxName) {
		MEXMEClaimMessage res = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT * FROM EXME_CLAIMMESSAGE CM ")
		   .append(" WHERE IsActive = 'Y' AND EXME_CTAPACEXT_ID = ? ");
		if (C_BPartner_ID == 0) {
			sql.append(" AND C_BPARTNER_ID IS NULL ");
		} else {
			sql.append(" AND C_BPARTNER_ID = ? ");
		}
		if (confType != null) {
			sql.append(" AND CM.CONFTYPE = ? ");
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			int i = 0;
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(++i, EXME_CtaPacExt_ID);
			if (C_BPartner_ID != 0) {
				pstmt.setInt(++i, C_BPartner_ID);
			}
			if (confType != null) {
				pstmt.setString(++i, confType);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				res = new MEXMEClaimMessage(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return res;
	}
	
	public static String getMessage(Properties ctx, int EXME_CtaPacExt_ID, String confType, String trxName) {
		return getMessage(ctx, EXME_CtaPacExt_ID, 0, confType, trxName);
	}
	
	public static String getMessage(Properties ctx, int EXME_CtaPacExt_ID, int C_BPartner_ID, String confType, String trxName) {
		String res = null;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT D.message FROM EXME_CLAIMMESSAGE CM LEFT JOIN HL7_DASHBOARD D ON D.HL7_DASHBOARD_ID = CM.HL7_DASHBOARD_ID ")
		   .append(" WHERE CM.IsActive = 'Y' AND CM.EXME_CtaPacExt_ID = ? ");
		if (C_BPartner_ID == 0) {
			sql.append(" AND CM.C_BPARTNER_ID IS NULL ");
		} else {
			sql.append(" AND CM.C_BPARTNER_ID = ? ");
		}
		if (confType != null) {
			sql.append(" AND CM.CONFTYPE = ? ");
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPacExt_ID);
			if (C_BPartner_ID != 0) {
				pstmt.setInt(2, C_BPartner_ID);
			}
			if (confType != null) {
				pstmt.setString(3, confType);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				//Clob clob = rs.getClob(1);
				//res = clob.toString();
				res = rs.getString(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getMessage: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return res;
	}
	
	
	
	public static boolean generateMessage(Properties ctx, MEXMECtaPacExt ctaPacExt, String confType, int C_BPartner_ID, String trxName) {
		
		boolean success = false;
		int recordId = 0;
		MEXMEClaimMessage claim = null;
		
		try {
			s_log.log(Level.INFO, "Generando mensaje");
			MessageGenerator mg = new MessageGenerator(ctx, false);
			recordId= mg.getMessage(String.valueOf(ctaPacExt.getEXME_CtaPacExt_ID())
					, MEXMECtaPacExt.Table_Name, confType, null);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, msgStatus, e);
		}
		
		if (recordId > 0) {
			if (C_BPartner_ID == 0) {
				claim = MEXMEClaimMessage.get(ctx, ctaPacExt.getEXME_CtaPacExt_ID(), confType, trxName);
				s_log.log(Level.INFO, "Obteniendo statement a actualizar: ctapacextid =  "+ctaPacExt.getEXME_CtaPacExt_ID());
			} else {
				claim = MEXMEClaimMessage.get(ctx, ctaPacExt.getEXME_CtaPacExt_ID(), C_BPartner_ID, confType, trxName);
				s_log.log(Level.INFO, "Obteniendo claim a actualizar: ctapacextid =  "+ctaPacExt.getEXME_CtaPacExt_ID());
			}
			
			if (claim == null) {
				s_log.log(Level.INFO, "Se almacena el mensaje por primera vez");
				claim = new MEXMEClaimMessage(ctx, 0, trxName);
			}
				
			claim.setEXME_CtaPac_ID(ctaPacExt.getEXME_CtaPac_ID());
			claim.setEXME_CtaPacExt_ID(ctaPacExt.getEXME_CtaPacExt_ID());
			//Si no tiene Aseguradora se guarda el C_BPartner del paciente
			claim.setC_BPartner_ID(C_BPartner_ID == 0 ? ctaPacExt.getCtaPac().getPaciente().getC_BPartner_ID() : C_BPartner_ID);
			claim.setHL7_Dashboard_ID(recordId);
			claim.setStatus(X_EXME_ClaimMessage.STATUS_Generated);
			claim.setConfType(confType);
			
			if (!claim.save(trxName)) {
				s_log.log(Level.SEVERE, "saveClaimMessage: ");
			} else {
				success = true;
			}
			
			StringBuilder msg = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			if (success) {
				msg.append(msgSuccess);
				s_log.log(Level.INFO, msgStatus, msg.toString());
			} else {
				msg.append(msgFailure);
				s_log.log(Level.INFO, msgStatus, msg.toString());
			}
		}
		
		return success;
	}
	
	public static boolean generateMessage(Properties ctx, MEXMECtaPacExt ctaPacExt, String confType, String trxName) {
		
		boolean success = false;
		int recordId = 0;
		MEXMEClaimMessage claim = null;
		
		try {
			s_log.log(Level.INFO, "Generando mensaje");
			MessageGenerator mg = new MessageGenerator(ctx, false);
			recordId= mg.getMessage(String.valueOf(ctaPacExt.getEXME_CtaPacExt_ID())
					, MEXMECtaPacExt.Table_Name, confType, null);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, msgStatus, e);
		}
		
		if (recordId > 0) {
			MEXMEPacienteAseg aseg = MEXMEPacienteAseg.getBillingInsurance(ctx, ctaPacExt.getEXME_CtaPacExt_ID(), confType);
			if (aseg == null) {
				claim = MEXMEClaimMessage.get(ctx, ctaPacExt.getEXME_CtaPacExt_ID(), confType, trxName);
				s_log.log(Level.INFO, "Obteniendo statement a actualizar: ctapacextid =  "+ctaPacExt.getEXME_CtaPacExt_ID());
			} else {
				claim = MEXMEClaimMessage.get(ctx, ctaPacExt.getEXME_CtaPacExt_ID(), aseg.getC_BPartner_ID(), confType, trxName);
				s_log.log(Level.INFO, "Obteniendo claim a actualizar: ctapacextid =  "+ctaPacExt.getEXME_CtaPacExt_ID());
			}
			
			if (claim == null) {
				s_log.log(Level.INFO, "Se almacena el mensaje por primera vez");
				claim = new MEXMEClaimMessage(ctx, 0, trxName);
			}
				
			claim.setEXME_CtaPac_ID(ctaPacExt.getEXME_CtaPac_ID());
			claim.setEXME_CtaPacExt_ID(ctaPacExt.getEXME_CtaPacExt_ID());
			//Si no tiene Aseguradora se guarda el C_BPartner del paciente
			claim.setC_BPartner_ID(aseg == null ? ctaPacExt.getCtaPac().getPaciente().getC_BPartner_ID() : aseg.getC_BPartner_ID());
			claim.setHL7_Dashboard_ID(recordId);
			claim.setStatus(X_EXME_ClaimMessage.STATUS_Generated);
			claim.setConfType(confType);
			
			if (!claim.save(trxName)) {
				s_log.log(Level.SEVERE, "saveClaimMessage: ");
			} else {
				success = true;
			}
			
			StringBuilder msg = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			if (success) {
				msg.append(msgSuccess);
				s_log.log(Level.INFO, msgStatus, msg.toString());
			} else {
				msg.append(msgFailure);
				s_log.log(Level.INFO, msgStatus, msg.toString());
			}
		}
		
		return success;
	}
}
