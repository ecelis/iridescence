package org.compiere.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MEXMEBatchClaimD;
import org.compiere.model.MEXMEBatchClaimH;
import org.compiere.model.MEXMEClaimLog;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEPacienteAseg;
import org.compiere.model.MInvoice;
import org.compiere.model.X_EXME_ClaimLog;
import org.compiere.model.X_HL7_MessageConf;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Trx;

public class GeneraClaimLog {
	
	private static CLogger s_log = CLogger.getCLogger (GeneraClaimLog.class);
	private Trx trx = null;
	private boolean intTrx = Boolean.TRUE;
	
	private final String msgTrx_ClaimLog = "ClaimLog";
	private final String msgTrx_saveClaimLog = "saveLogData: ";
	private final String msgTrx_saveCtaPac = "saveCtaPac: ";
	
	//private final String msgSuccess_cLog = "Success";
	//private final String msgFailure_cLog = "Fail to create Log";
	
	private int getInvoiceID(Properties ctx, List <MInvoice> listInvoice, int ctapacID) {
		int invoiceID = 0;
		if (listInvoice == null) {
			return invoiceID;
		}
		for (MInvoice invoice : listInvoice) {
			MEXMECtaPacExt ctapacExt = new MEXMECtaPacExt(ctx, invoice.getEXME_CtaPacExt_ID(), null);
			if (ctapacExt.getEXME_CtaPac_ID() == ctapacID) {
				invoiceID = invoice.getC_Invoice_ID();
			}
		}
		return invoiceID;
	}
	
	public GeneraClaimLog() {
	}
	
	public GeneraClaimLog(Trx trx) {
		this.trx = trx;
	}

	public boolean generaLog(Properties ctx, int EXME_BatchClaimH_ID, List <MInvoice> listInvoice) 
			throws Exception {
		if (trx == null) {
			trx = Trx.get(Trx.createTrxName(msgTrx_ClaimLog), true);
		} else {
			intTrx = Boolean.FALSE;
		}
		
		boolean success = Boolean.TRUE;
		
		if (EXME_BatchClaimH_ID != 0) {
			MEXMEBatchClaimH batchH = new MEXMEBatchClaimH(ctx, EXME_BatchClaimH_ID, trx.getTrxName());
			MEXMEBatchClaimD[] batchD = MEXMEBatchClaimD.gets(ctx, EXME_BatchClaimH_ID, trx.getTrxName());
			
			ArrayList<MEXMEClaimLog> list = new ArrayList<MEXMEClaimLog>();
			ArrayList<MEXMECtaPacExt> listCtaPacExt = new ArrayList<MEXMECtaPacExt>();
			
			for (int i = 0; i < batchD.length; i++) {
				MEXMEClaimLog claimLog = new MEXMEClaimLog(ctx, 0, trx.getTrxName());
				
				/** Agregar informacion a un nuevo registro de Log del Claim **/
				
				claimLog.setEXME_CtaPac_ID(batchD[i].getEXME_CtaPac().getEXME_CtaPac_ID());
				claimLog.setEXME_CtaPacExt_ID(batchD[i].getEXME_CtaPacExt_ID());
				int invoiceID = getInvoiceID(ctx, listInvoice, batchD[i].getEXME_CtaPac().getEXME_CtaPac_ID());
				claimLog.setC_Invoice_ID(invoiceID); //TODO: billing_lama
				boolean updateStatus = false;
				if (X_HL7_MessageConf.CONFTYPE_PIStatement.equals(batchH.getConfType())) {
					MEXMEPaciente pac = new MEXMEPaciente(ctx, batchD[i].getEXME_CtaPac().getEXME_Paciente_ID(), null);
					claimLog.setC_BPartner_ID(pac.getC_BPartner_ID());
					claimLog.setStatus(batchH.getStatus());
					updateStatus = Boolean.TRUE;
				} else {
					MEXMEPacienteAseg aseg = MEXMEPacienteAseg.getBillingInsurance(ctx, batchD[i].getEXME_CtaPac_ID(), batchH.getConfType());
					if (aseg == null) {
						claimLog.setC_BPartner_ID(batchD[i].getC_BPartner_ID());
					} else {
						claimLog.setC_BPartner_ID(aseg.getC_BPartner_ID());
					}
					claimLog.setStatus(batchD[i].getStatus());
				}
				claimLog.setHL7_Dashboard_ID(batchH.getHL7_Dashboard_ID());
				claimLog.setConfType(batchH.getConfType());
				claimLog.setDate_Submitted(DB.getTimestampForOrg(ctx));
				
				/** Si el estatus del claim de acuerdo a la respuesta es de RECHAZADO entonces desactivara la bandera de IsGenerated del CtaPac para permitir el reenvio **/
				if (batchD[i].getStatus() != null) {
					if (batchD[i].getStatus().equals(X_EXME_ClaimLog.STATUS_M) || batchD[i].getStatus().equals(X_EXME_ClaimLog.STATUS_R) 
							|| batchD[i].getStatus().equals(X_EXME_ClaimLog.STATUS_W) || batchD[i].getStatus().equals(X_EXME_ClaimLog.STATUS_X)) {
						MEXMECtaPacExt ext = new MEXMECtaPacExt(ctx, claimLog.getEXME_CtaPacExt_ID(), trx.getTrxName());
//						ext.setIsGenerated(false);
						ext.setInstitutionalStatus(updateStatus 
								? MEXMECtaPacExt.INSTITUTIONALSTATUS_WaitingGuarantorPayments 
								: ext.getInstitutionalStatus());
						listCtaPacExt.add(ext);
					}
				}
				list.add(claimLog);
			}
			
			// EXME_ClaimLog Save
			try {
				for (MEXMEClaimLog log : list) {
					if (!log.save(trx.getTrxName())) {
						success = Boolean.FALSE;
						break;
					}
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, msgTrx_saveClaimLog, e);
				success = Boolean.FALSE;
			}
			
			if (success) {
				// EXME_CtaPacExt Save
				try {
					for (MEXMECtaPacExt ctaPacExt : listCtaPacExt) {
						if (!ctaPacExt.save(trx.getTrxName())) {
							success = Boolean.FALSE;
							break;
						}
					}
				} catch (Exception e) {
					s_log.log(Level.SEVERE, msgTrx_saveCtaPac, e);
					success = Boolean.FALSE;
				}
			}
		} else {
			success = Boolean.FALSE;
		}
		
		if (intTrx) {
			if (success) {
				Trx.commit(trx);
			} else {
				Trx.rollback(trx);
			}
			
			Trx.close(trx);
			trx = null;
		}
		
		return success;
	}

}
