package org.compiere.process;


import static org.compiere.util.Utilerias.getMessage;
import static org.compiere.util.Utilerias.getMessageArgs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MEXMEActPacienteInd;
import org.compiere.model.MBPartner;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMEBatchClaimD;
import org.compiere.model.MEXMEClaimError;
import org.compiere.model.MEXMEClaimMessage;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEPacienteAseg;
import org.compiere.model.MHL7MessageConf;
import org.compiere.model.X_EXME_BatchClaimH;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

public class BatchClaimGenerator extends SvrProcess {
	
	private String confType;
	
	public BatchClaimGenerator() {
	}

	@Override
	protected String doIt() throws Exception {
		Trx mtrx = null;
		boolean success = Boolean.TRUE;
		int cont = 0;
		List<MEXMECtaPacExt> lstCtaPacExt = new ArrayList<MEXMECtaPacExt>(); 
		List<String> settetrLst = new ArrayList<String>();
		//Retrieve all the Accounts that are Ready To Bill 
	    StringBuilder sqlWhere = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    
	    
	    if (MEXMECtaPacExt.CONFTYPE_ProfessionalClaim.equals(confType)) {
	    	
	    	sqlWhere.append(" AND ((ext.ProfessionalStatus IN (?, ? , ?, ? ,?  ) ")
	    	.append(" AND ext.PROFESSIONALSTEP IN (?)  ")
	    	.append(" AND ExtensionNo = 0 AND ext.EXME_CtaPac_ID NOT IN  " )
	    	.append(" (SELECT Distinct(COALESCE(D.EXME_CtaPac_ID,0))  " )
	    	.append(" FROM EXME_BatchClaimH H " )
	    	.append(" INNER JOIN EXME_BatchClaimD D " )
	    	.append(" ON D.EXME_BatchClaimH_ID = H.EXME_BatchClaimH_ID " )
	    	.append(" WHERE H.Conftype = ? " )
	    	.append(" AND H.IsActive = 'Y' )");
	    	
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_CodingComplete);
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKOrdersComplete);
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKPricesInZero);
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKMandatoryFields);
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKMessage997);
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTEP_FirstInsurance);
	    	settetrLst.add(MEXMECtaPacExt.CONFTYPE_ProfessionalClaim);
	    	
	    	settetrLst.add(MBPartner.SUPPORTBILLING_Professional);
	    	settetrLst.add(MEXMECtaPacExt.CHARGESTATUS_ForBill);
	    	settetrLst.add(MEXMECtaPacExt.CHARGESTATUS_ForRebill);
	    	settetrLst.add(MEXMECtaPacExt.CONFTYPE_ProfessionalClaim);	    		    	
	    	
	    } else if (MEXMECtaPacExt.CONFTYPE_InstitutionalClaim.equals(confType)) {
	    	
	    	sqlWhere.append(" AND ((ext.InstitutionalStatus IN (?, ? , ?, ? ,?  ) ")
	    	.append(" AND ext.InstitutionalStep IN (?)  ")
	    	.append(" AND ExtensionNo = 0 AND ext.EXME_CtaPac_ID NOT IN  " )
	    	.append(" (SELECT Distinct(COALESCE(D.EXME_CtaPac_ID,0))  " )
	    	.append(" FROM EXME_BatchClaimH H " )
	    	.append(" INNER JOIN EXME_BatchClaimD D " )
	    	.append(" ON D.EXME_BatchClaimH_ID = H.EXME_BatchClaimH_ID " )
	    	.append(" WHERE H.Conftype = ? " )
	    	.append(" AND H.IsActive = 'Y' )");
	    	
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingComplete);
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKOrdersComplete);
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKPricesInZero);
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKMandatoryFields);
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKMessage997);
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTEP_FirstInsurance);
	    	settetrLst.add(MEXMECtaPacExt.CONFTYPE_InstitutionalClaim);
	    	settetrLst.add(MBPartner.SUPPORTBILLING_Institucional);
	    	settetrLst.add(MEXMECtaPacExt.CHARGESTATUS_ForBill);
	    	settetrLst.add(MEXMECtaPacExt.CHARGESTATUS_ForRebill);
	    	settetrLst.add(MEXMECtaPacExt.CONFTYPE_InstitutionalClaim);	
	    	
	    	
	    	
	    } else if (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType)) {
	    	//Si el Step de Professional o Institutional es Self Pay y el Estatus es Statements y los defaults
	    	sqlWhere.append("AND (")
	    	.append("    (InstitutionalStatus IN (?,?,?,?,?,?) AND InstitutionalStep IN (?) ) " )
	    	.append("OR (ProfessionalStatus  IN (?,?,?,?,?,?) AND ProfessionalStep  IN (?)) ) " );
	    	
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_SelfPay);
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingComplete);
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKOrdersComplete);
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKPricesInZero);
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKMandatoryFields);
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKMessage997);
	    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTEP_SelftPay);
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_SelfPay);
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_CodingComplete);
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKOrdersComplete);
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKPricesInZero);
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKMandatoryFields);
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKMessage997);
	    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTEP_SelftPay);
	    	
	    	//Se compara la fecha actual contra la fecha del ultimo envio (o fecha actual si es el primer envio
		    //esto debe de ser mayor o igual a la antiguedad configurada para Statements si se crea un Batch de Statements
		    sqlWhere.append("AND ").append(DB.TO_DATE(DB.convert(getCtx(), new Date())))
		    .append(" >= COALESCE( ")
	        .append(" (SELECT to_date(MAX(TO_char(DATE_SUBMITTED,'YYYY-MM-DD')),'YYYY-MM-DD') ")
	        .append(" + fnc_getStmtAge(ctaExt.EXME_CtaPac_ID) ")
	        .append(" FROM EXME_CLAIMLOG CL WHERE CL.EXME_CTAPACEXT_ID = ctaExt.EXME_CTAPACEXT_ID ")
	        .append(" AND CL.CONFTYPE = ? AND ? = '"+ MEXMECtaPacExt.CONFTYPE_PIStatement + "'), ")
	        .append(DB.TO_DATE(DB.convert(getCtx(), new Date()))).append(") ");
		   
	    	settetrLst.add(MEXMECtaPacExt.CONFTYPE_PIStatement);
	    	settetrLst.add(MEXMECtaPacExt.CONFTYPE_PIStatement);
	    	
	    	// Verifica si el balance de la cuenta es positivo, de lo contrario no lo agrega al batch
	    	sqlWhere.append("AND fnc_getBalance(ctaExt.EXME_CtaPac_ID) > 0 ");
	    	
	    	// Valida que el statement cumpla con reglas definidas para Tipo de Paciente, 
	    	//Organizacion y Estatus de Encounter (1 = Valido, 0 = No Valido)
	    	sqlWhere.append("AND fnc_stmtRules(ctaExt.EXME_CtaPac_ID) = 1 ");
	    	
	    }
	    
    	//Se compara la fecha de creacion contra el Delay por aseguradora si es un Batch de Claims
    	sqlWhere.append(" AND to_date((select to_char(cp.fechaalta,'YYYY-MM-DD') from exme_ctapac cp where cp.exme_Ctapac_id = ext.exme_ctapac_id), 'YYYY-MM-DD') ")
    	.append(" <= (").append(DB.TO_DATE(DB.convert(getCtx(), new Date())))
	    .append(" - (SELECT MAX(QueuingTime) " )
	    .append(" FROM EXME_PacienteAseg PA " )
	    .append(" INNER JOIN C_BPartner CB " )
	    .append(" ON CB.C_BPartner_ID = PA.C_BPartner_ID " )
	    .append(" WHERE PA.EXME_CtaPac_ID = ext.EXME_CtaPac_ID " )
	    .append(" AND PA.IsActive = 'Y' " )
	    .append(" AND PA.PRIORITY = 1 " )
	    .append(" AND PA.SupportBilling = ? ))) " );
    	//Agregamos las extensiones ya facturadas
    	sqlWhere.append(" OR (ext.ChargeStatus  IN (?,?) ")
    	.append(" AND ext.EXME_CtaPacExt_ID NOT IN ( ")
    	.append(" SELECT Distinct(COALESCE(D.EXME_CtaPacExt_ID,0)) ")
    	.append(" FROM EXME_BatchClaimH H ")
    	.append(" INNER JOIN EXME_BatchClaimD D ")
    	.append(" ON D.EXME_BatchClaimH_ID = H.EXME_BatchClaimH_ID ")
    	.append(" WHERE H.Conftype = ? ")
    	.append(" AND H.IsActive = 'Y' ))) ");

	    
	    if (MEXMECtaPacExt.CONFTYPE_ProfessionalClaim.equals(confType)) {
	    	lstCtaPacExt.addAll(MEXMECtaPacExt.getExtCtas(Env.getCtx(), sqlWhere.toString(), 
					null, settetrLst.toArray()));
	    } else if (MEXMECtaPacExt.CONFTYPE_InstitutionalClaim.equals(confType)) {
	    	lstCtaPacExt.addAll(MEXMECtaPacExt.getExtCtas(Env.getCtx(), sqlWhere.toString(), 
					null,  settetrLst.toArray()));
	    } else if (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType)) {
	    	lstCtaPacExt.addAll(MEXMECtaPacExt.getExtCtas(Env.getCtx(), sqlWhere.toString(), 
					null, settetrLst.toArray()));
	    }
	    X_EXME_BatchClaimH batchH = null;
		try{
			if (lstCtaPacExt.size()>0){
				mtrx = Trx.get(Trx.createTrxName("BatchClaimGenerator"), true);
				batchH = new X_EXME_BatchClaimH(Env.getCtx(), 0, mtrx.getTrxName());
				batchH.setDocumentNo(DB.getDocumentNo(Env.getAD_Client_ID(Env.getCtx()), X_EXME_BatchClaimH.Table_Name, mtrx.getTrxName()));
				batchH.setIsGenerated(false);
				batchH.setHL7_Dashboard_ID(0);
				batchH.setConfType(confType);
	
				if (batchH.save(mtrx.getTrxName())) {
					for (MEXMECtaPacExt ctaExt : lstCtaPacExt) {
						final int incompleteInst= MEXMEActPacienteIndH.getCount(getCtx(), ctaExt.getEXME_CtaPac_ID(), 0, null, 
								MEXMEActPacienteInd.ESTATUS_RequestedService, MHL7MessageConf.CONFTYPE_InstitutionalClaim, null);
						final int incompleteProf= MEXMEActPacienteIndH.getCount(getCtx(), ctaExt.getEXME_CtaPac_ID(), 0, null, 
								MEXMEActPacienteInd.ESTATUS_RequestedService, MHL7MessageConf.CONFTYPE_ProfessionalClaim, null);
						final int pendingInst  = MCtaPacDet.getCargosByClaim(getCtx(), ctaExt.getEXME_CtaPac_ID(), 
								MHL7MessageConf.CONFTYPE_InstitutionalClaim, null, Constantes.CLAIM_MISSINGPRICES ).size();
						final int pendingProf = MCtaPacDet.getCargosByClaim(getCtx(), ctaExt.getEXME_CtaPac_ID(), 
								MHL7MessageConf.CONFTYPE_ProfessionalClaim, null, Constantes.CLAIM_MISSINGPRICES).size();
						final int mfRevCodeInst = MCtaPacDet.getCargosByClaim(getCtx(), ctaExt.getEXME_CtaPac_ID(), 
								MHL7MessageConf.CONFTYPE_InstitutionalClaim, null, Constantes.CLAIM_MISSINGREVCODE).size();
						final int mfRevCodeProf = MCtaPacDet.getCargosByClaim(getCtx(), ctaExt.getEXME_CtaPac_ID(), 
								MHL7MessageConf.CONFTYPE_ProfessionalClaim, null, Constantes.CLAIM_MISSINGREVCODE).size();
						final int mfChargesInst = MCtaPacDet.getCargosByClaim(getCtx(), ctaExt.getEXME_CtaPac_ID(), 
								MHL7MessageConf.CONFTYPE_InstitutionalClaim, null, Constantes.CLAIM_MISSINGCHARGES).size();
						final int mfChargesProf = MCtaPacDet.getCargosByClaim(getCtx(), ctaExt.getEXME_CtaPac_ID(), 
								MHL7MessageConf.CONFTYPE_ProfessionalClaim, null, Constantes.CLAIM_MISSINGCHARGES).size();
						final int mfCoverageInst = MEXMEPacienteAseg.getByCtaPacSupport(getCtx(), ctaExt.getEXME_CtaPac_ID(), 
								MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
						final int mfCoverageProf = MEXMEPacienteAseg.getByCtaPacSupport(getCtx(), ctaExt.getEXME_CtaPac_ID(), 
								MEXMEPacienteAseg.SUPPORTBILLING_Professional);
						//First Edit Error OC
						//Servicios pendientes de completar
						if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_InstitutionalClaim)
							&& incompleteInst>0){
							ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorOrdersIncomplete);
							if (!ctaExt.save(mtrx.getTrxName())) {
								log.log(Level.SEVERE, "Error Saving Orders Institutional IncompleteStatus");
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_ProfessionalClaim)
								&& incompleteProf>0){
							ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorOrdersIncomplete);
							if (!ctaExt.save(mtrx.getTrxName())) {
								log.log(Level.SEVERE, "Error Saving Orders Professional IncompleteStatus");
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement) 
								&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_InstitutionalClaim) ? incompleteInst : 0)>0){
							ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorOrdersIncomplete);
							if (!ctaExt.save(mtrx.getTrxName())) {
								log.log(Level.SEVERE, "Error Saving Orders Institutional IncompleteStatus");
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement) 
								&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_ProfessionalClaim) ? incompleteProf : 0)>0){
							ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorOrdersIncomplete);
							if (!ctaExt.save(mtrx.getTrxName())) {
								log.log(Level.SEVERE, "Error Saving Orders Professional IncompleteStatus");
								success = Boolean.FALSE;
								break;
							}
						//Precios Pendientes ó 0 sin validar
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_InstitutionalClaim)
								&& pendingInst>0){
							ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorPricesInZero);
							if (!ctaExt.save(mtrx.getTrxName())) {
								log.log(Level.SEVERE, "Error saving Institional Prices in Zero");
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_ProfessionalClaim)
								&& pendingProf>0){
							ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorPricesInZero);
							if (!ctaExt.save(mtrx.getTrxName())) {
								log.log(Level.SEVERE, "Error saving Professional Prices in Zero");
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement) 
								&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_InstitutionalClaim) ? pendingInst : 0)>0){
							ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorPricesInZero);
							if (!ctaExt.save(mtrx.getTrxName())) {
								log.log(Level.SEVERE, "Error saving Institional Prices in Zero");
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement) 
								&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_ProfessionalClaim) ? pendingInst : 0)>0){
							ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorPricesInZero);
							if (!ctaExt.save(mtrx.getTrxName())) {
								log.log(Level.SEVERE, "Error saving Professional Prices in Zero");
								success = Boolean.FALSE;
								break;
							}
						//Cuenta sin cargos Institutional
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_InstitutionalClaim)
								&& mfChargesInst==0){
							ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							if (!(ctaExt.save(mtrx.getTrxName())
								  && MEXMEClaimError.createError(Env.getCtx(),confType, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
										  getMessage(getCtx(), null, "billing.exception.inst.missingCharges"), mtrx.getTrxName()))) {
								log.log(Level.SEVERE, getMessage(getCtx(), null, "billing.exception.inst.missingCharges"));
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_ProfessionalClaim)
								&& mfChargesProf==0){
							ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							if (!(ctaExt.save(mtrx.getTrxName())
								  && MEXMEClaimError.createError(Env.getCtx(),confType, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
										  getMessage(getCtx(), null, "billing.exception.prof.missingCharges"), mtrx.getTrxName()))) {
								log.log(Level.SEVERE, getMessage(getCtx(), null, "billing.exception.prof.missingCharges"));
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement) 
								&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_InstitutionalClaim) ? mfChargesInst : 1)==0){
							ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							if (!(ctaExt.save(mtrx.getTrxName())
								  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_InstitutionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
										  getMessage(getCtx(), null, "billing.exception.inst.missingCharges"), mtrx.getTrxName()))) {
								log.log(Level.SEVERE, getMessage(getCtx(), null, "billing.exception.inst.missingCharges"));
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement) 
								&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_ProfessionalClaim) ? mfChargesProf : 1)==0){
							ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							if (!(ctaExt.save(mtrx.getTrxName())
								  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_ProfessionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
										  getMessage(getCtx(), null, "billing.exception.prof.missingCharges"), mtrx.getTrxName()))) {
								log.log(Level.SEVERE, getMessage(getCtx(), null, "billing.exception.prof.missingCharges"));
								success = Boolean.FALSE;
								break;
							}
						//MISSING DATA - NO INSURANCE COVERAGE Prof / Inst
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_InstitutionalClaim)
								&& mfCoverageInst==0 && !ctaExt.getCtaPac().isNoInsuranceCoverage()){
							ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							if (!(ctaExt.save(mtrx.getTrxName())
								  && MEXMEClaimError.createError(Env.getCtx(),confType, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
										  getMessage(getCtx(), null, "billing.exception.inst.missingCoverage"), mtrx.getTrxName()))) {
								log.log(Level.SEVERE, getMessage(getCtx(), null, "billing.exception.inst.missingCoverage"));
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_ProfessionalClaim)
								&& mfCoverageProf==0 && !ctaExt.getCtaPac().isNoInsuranceCoverage()){
							ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							if (!(ctaExt.save(mtrx.getTrxName())
								  && MEXMEClaimError.createError(Env.getCtx(),confType, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
										  getMessage(getCtx(), null, "billing.exception.prof.missingCoverage"), mtrx.getTrxName()))) {
								log.log(Level.SEVERE, getMessage(getCtx(), null, "billing.exception.prof.missingCoverage"));
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement) 
								&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_InstitutionalClaim) ? mfCoverageInst : 1)==0
								&& !ctaExt.getCtaPac().isNoInsuranceCoverage()){
							ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							if (!(ctaExt.save(mtrx.getTrxName())
								  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_InstitutionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
										  getMessage(getCtx(), null, "billing.exception.inst.missingCoverage"), mtrx.getTrxName()))) {
								log.log(Level.SEVERE, getMessage(getCtx(), null, "billing.exception.inst.missingCoverage"));
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement) 
								&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_ProfessionalClaim) ? mfCoverageProf : 1)==0
								&& !ctaExt.getCtaPac().isNoInsuranceCoverage()){
							ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							if (!(ctaExt.save(mtrx.getTrxName())
								  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_ProfessionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
										  getMessage(getCtx(), null, "billing.exception.prof.missingCoverage"), mtrx.getTrxName()))) {
								log.log(Level.SEVERE, getMessage(getCtx(), null, "billing.exception.prof.missingCoverage"));
								success = Boolean.FALSE;
								break;
							}
						//MISSING DATA - CHARGES WHITOUT REV CODE
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_InstitutionalClaim) && mfRevCodeInst>0){
							ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							if (!(ctaExt.save(mtrx.getTrxName())
								  && MEXMEClaimError.createError(Env.getCtx(),confType, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
										  getMessage(getCtx(), null, "billing.exception.inst.missingRevCode"), mtrx.getTrxName()))) {
								log.log(Level.SEVERE, getMessage(getCtx(), null, "billing.exception.inst.missingRevCode"));
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_ProfessionalClaim) && mfRevCodeProf>0){
							ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							if (!(ctaExt.save(mtrx.getTrxName())
								  && MEXMEClaimError.createError(Env.getCtx(),confType, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
										  getMessage(getCtx(), null, "billing.exception.prof.missingRevCode"), mtrx.getTrxName()))) {
								log.log(Level.SEVERE, getMessage(getCtx(), null, "billing.exception.prof.missingRevCode"));
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement) 
								&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_InstitutionalClaim) ? mfRevCodeInst : 0)>0){
							ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							if (!(ctaExt.save(mtrx.getTrxName())
								  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_InstitutionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
										  getMessage(getCtx(), null, "billing.exception.inst.missingRevCode"), mtrx.getTrxName()))) {
								log.log(Level.SEVERE, getMessage(getCtx(), null, "billing.exception.inst.missingRevCode"));
								success = Boolean.FALSE;
								break;
							}
						}else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement) 
								&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_ProfessionalClaim) ? mfRevCodeProf : 0)>0){
							ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							if (!(ctaExt.save(mtrx.getTrxName())
								  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_ProfessionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
										  getMessage(getCtx(), null, "billing.exception.prof.missingRevCode"), mtrx.getTrxName()))) {
								log.log(Level.SEVERE, getMessage(getCtx(), null, "billing.exception.prof.missingRevCode"));
								success = Boolean.FALSE;
								break;
							}
						}else if ((MEXMEPaciente.getPatientBalance(Env.getCtx(), ctaExt.getCtaPac().getEXME_Paciente_ID(),
									ctaExt.getEXME_CtaPac_ID(), null, null).compareTo(BigDecimal.ZERO)<1)) {
							log.log(Level.WARNING, "Encounter ID = "+ctaExt.getEXME_CtaPac_ID()+" was removed from Batch because balances is less or equal to Zero.");
							
						}else{
							int idCtaPacExt = 0;
							// Get Payer
							int cBPartnerID= 0;
							if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement)){
								cBPartnerID = ctaExt.getCtaPac().getPaciente().getC_BPartner_ID();
							}else{
								cBPartnerID = MEXMEPacienteAseg.getBillingInsurance(Env.getCtx(), ctaExt, confType).getC_BPartner_ID();
							}
							
							// Crear nueva extension
							if (ctaExt.getExtensionNo() == 0 && !MHL7MessageConf.CONFTYPE_PIStatement.equals(confType)) {
								MEXMECtaPacExt newCtaExt = new MEXMECtaPacExt(ctaExt.getCtaPac(), mtrx.getTrxName());
								if (MHL7MessageConf.CONFTYPE_ProfessionalClaim.equalsIgnoreCase(confType)) {
									newCtaExt.setInstitutionalStatus(null);
									newCtaExt.setInstitutionalStep(null);
								} else if (MHL7MessageConf.CONFTYPE_InstitutionalClaim.equalsIgnoreCase(confType)) {
									newCtaExt.setProfessionalStatus(null);
									newCtaExt.setProfessionalStep(null);
								}
								newCtaExt.setConfType(confType);
								if (!newCtaExt.save(mtrx.getTrxName())) {
									success = Boolean.FALSE;
									break;
								}
								//Asigna cargos a nueva extension
								if (!MCtaPacDet.moveToExtension(Env.getCtx(), newCtaExt, confType, mtrx.getTrxName())) {
									success = Boolean.FALSE;
									break;
								}
								//Asigna pre-claim a nueva extension
								MEXMEClaimMessage preClaim = MEXMEClaimMessage.get(Env.getCtx(), ctaExt.getEXME_CtaPacExt_ID(), cBPartnerID, confType, mtrx.getTrxName());
								if (preClaim != null) {
									preClaim.setEXME_CtaPacExt_ID(newCtaExt.getEXME_CtaPacExt_ID());
									if (!preClaim.save(mtrx.getTrxName())) {
										success = Boolean.FALSE;
										break;
									}
								} else {
									success = Boolean.FALSE;
									break;
								}
								
								
								idCtaPacExt = newCtaExt.getEXME_CtaPacExt_ID();
							} else {
								idCtaPacExt = ctaExt.getEXME_CtaPacExt_ID();
							}
														
							MEXMEBatchClaimD batchD = new MEXMEBatchClaimD(Env.getCtx(), 0, mtrx.getTrxName());
							batchD.setEXME_BatchClaimH_ID(batchH.getEXME_BatchClaimH_ID());
							batchD.setEXME_CtaPac_ID(ctaExt.getEXME_CtaPac_ID());
							batchD.setEXME_CtaPacExt_ID(idCtaPacExt);
							
							if (cBPartnerID > 0 ) {
								batchD.setC_BPartner_ID(cBPartnerID);
							}
							
							//Si se esta generando statement y fue debido a solicitud en demanda
							//desactivar la bandera de solicitud en demanda para que vuelva a 
							// a transcurrir los dias configurados para proxima gen de statament
							//http://control.ecaresoft.com/card/127/
							
							if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement)){
								if (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_ProfessionalClaim)){
									ctaExt.setProfessionalStep(MEXMECtaPacExt.PROFESSIONALSTEP_SelftPay);
									ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_SelfPay);
								}
								if (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_InstitutionalClaim)){
									ctaExt.setInstitutionalStep(MEXMECtaPacExt.INSTITUTIONALSTEP_SelftPay);
									ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_SelfPay);
								}
								
								if (ctaExt.getCtaPac().isNoStatementAge()){
									ctaExt.getCtaPac().setNoStatementAge(Boolean.FALSE);
								}
								if (!(ctaExt.getCtaPac().save(mtrx.getTrxName()))) {
									log.log(Level.SEVERE, "Ocurrio un error al actualizar status " +
											"por Generacion d Statements. encounter:"+ctaExt.getCtaPac().getEXME_CtaPac_ID());
									success = Boolean.FALSE;
									break;
								}
							}
							
							if (!batchD.save(mtrx.getTrxName())) {
								success = Boolean.FALSE;
								break;
							}else{
								cont++;
							}
						}
					}
				} else {
					success = Boolean.FALSE;
				}		
			}
			
			if (success){
				if (cont==0){
					//Desactivamos el batch en caso de haberse creado.
					if (batchH!=null){
						batchH.setIsActive(Boolean.FALSE);
						batchH.save();
					}
				}
				Trx.commit(mtrx);
			}else{
				Trx.rollback(mtrx);
				log.log(Level.SEVERE, "BatchClaimGenerator - > Create");
			}
		}catch(Exception e){
			Trx.rollback(mtrx);
			log.log(Level.SEVERE, e.getMessage(), e);
		}finally {
			batchH = null;
			Trx.close(mtrx);
			mtrx = null;
		}
		String message = "";
		if (success && cont >0){
			message = getMessageArgs(Env.getCtx(), null, "billing.batch.success", cont) + (cont >1 ? "s" : ""); 
		}else{
			message = getMessageArgs(Env.getCtx(), null, "billing.batch.success.noAccounts");
		}
		return (success ? message : getMessage(Env.getCtx(), null, "billing.batch.error.creation"));
	}

	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String type = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (type.equals("ConfType"))
				confType = para[i].getParameter().toString();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + type);
		}

	}
	
	private boolean isValidStatement(MEXMECtaPacExt ctaExt, String confType){
		if (MHL7MessageConf.CONFTYPE_InstitutionalClaim.equalsIgnoreCase(confType)){
			if (ctaExt.getInstitutionalStep().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTEP_SelftPay)
					&& (ctaExt.getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_SelfPay)
						||ctaExt.getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingComplete)
						||ctaExt.getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKOrdersComplete)
						||ctaExt.getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKPricesInZero)
						||ctaExt.getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKMandatoryFields)
						||ctaExt.getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKMessage997))){
				return Boolean.TRUE;
			}else{
				return Boolean.FALSE;
			}
		}else if (MHL7MessageConf.CONFTYPE_ProfessionalClaim.equalsIgnoreCase(confType)){
			if (ctaExt.getProfessionalStep().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTEP_SelftPay)
					&& (ctaExt.getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_SelfPay)
							||ctaExt.getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_CodingComplete)
							||ctaExt.getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_OKOrdersComplete)
							||ctaExt.getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_OKPricesInZero)
							||ctaExt.getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_OKMandatoryFields)
							||ctaExt.getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_OKMessage997))){
				return Boolean.TRUE;
			}else{
				return Boolean.FALSE;
			}
		}else{
			return Boolean.FALSE;
		}
	}

}