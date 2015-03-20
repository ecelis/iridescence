package org.compiere.process;
/**
 * Proceso para actualizar
 * el estatus de las Cuentas que
 * excedan el tiempo sin ser "Facturadas"
 * ESTA FUNCIONALIDAD NO DEBERIA ESTARSE USANDO YA.
 * @author gvaldez
 * @deprecated
 */


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMEClaimError;
import org.compiere.model.MEXMEClaimPaymentH;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

public class MoveToExceptions extends SvrProcess {
	
	private String 				confType = null;
	private Timestamp			dateFrom = null;
	private Timestamp			dateTo = null;
	
	public MoveToExceptions() {
	}
	
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String type = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (type.equals("Type")){
				confType = para[i].getParameter().toString();
			}else if (type.equals("Date")){
				//
				dateFrom = TimeUtil.getInitialRangeT(Env.getCtx(), new Date(((Timestamp)para[i].getParameter()).getTime()));
				dateTo = TimeUtil.getFinalRangeT(Env.getCtx(), new Date(((Timestamp)para[i].getParameter_To()).getTime()));
			}else
				log.log(Level.SEVERE, "Unknown Parameter: " + type);
		}
		
	}
	

	@Override
	protected String doIt() {
		StringBuilder msg = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		String retVal = "";
		String encounterNo = "";
		Trx mtrx = null;
		mtrx = Trx.get(Trx.createTrxName("CD"), true);
		int moved = 0;
		
		try{
		
			List<MEXMECtaPac> lstCtaPac = MEXMECtaPac.getCtaPacToExceptions(Env.getCtx(), confType, dateFrom, dateTo, mtrx.getTrxName());
			
			for (int i = 0; i < lstCtaPac.size(); i++ ){
				logActivity(lstCtaPac.get(i));
				encounterNo = lstCtaPac.get(i).getExtCero().getDocumentNo();
				if (MEXMEClaimPaymentH.BILLINGTYPE_Technical.equalsIgnoreCase(confType)){
					//Movemenos Encuentros con Step DEFAULT
					if (lstCtaPac.get(i).getExtCero().getInstitutionalStep().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTEP_Default)){
						//Si el Encuentro es NOT BILLED = 1
						if (lstCtaPac.get(i).getExtCero().getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_NotBilled)){
							MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
									lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.NOINS_1 , mtrx.getTrxName());
							lstCtaPac.get(i).getExtCero().setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							
						}
						//Si el Encuentro es NOT APPLICABLE = 2
						else if (lstCtaPac.get(i).getExtCero().getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_NotApplicable)){
							//solo si el Encuentro tiene cargos
							if (MCtaPacDet.getTotChargesByClaim(Env.getCtx(), lstCtaPac.get(i).getEXME_CtaPac_ID(), confType, mtrx.getTrxName())
									.compareTo(BigDecimal.ZERO)>0){
								MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
										lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.NOINS_2 , mtrx.getTrxName());
								lstCtaPac.get(i).getExtCero().setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							}
						}
						//Si el Encuentro es CODING INCOMPLETE = 3
						else if (lstCtaPac.get(i).getExtCero().getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingIncomplete)){
							MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
									lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.NOINS_3 , mtrx.getTrxName());
							lstCtaPac.get(i).getExtCero().setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
						}else{
							log.log(Level.WARNING, "Encuentro : " +lstCtaPac.get(i).getEXME_CtaPac_ID()  
									+ " |Se incluyo en Proceso pero no entro en movimientos ");
						}
							
					}// Para el STEP SELF PAY
					else if (lstCtaPac.get(i).getExtCero().getInstitutionalStep().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTEP_SelftPay)){
						//Si el Encuentro es NOT BILLED = 1
						if (lstCtaPac.get(i).getExtCero().getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_NotBilled)){
							MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
									lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.STAT_1 , mtrx.getTrxName());
							lstCtaPac.get(i).getExtCero().setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							
						}
						//Si el Encuentro es NOT APPLICABLE = 2
						else if (lstCtaPac.get(i).getExtCero().getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_NotApplicable)){
							//si el Encuentro tiene cargos
							if (MCtaPacDet.getTotChargesByClaim(Env.getCtx(), lstCtaPac.get(i).getEXME_CtaPac_ID(), confType, mtrx.getTrxName())
									.compareTo(BigDecimal.ZERO)>0){
								MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
										lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.STAT_21 , mtrx.getTrxName());
								lstCtaPac.get(i).getExtCero().setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							}else{
								MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
										lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.STAT_22 , mtrx.getTrxName());
								lstCtaPac.get(i).getExtCero().setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							}
						}
						//Si el Encuentro es CODING INCOMPLETE = 3
						else if (lstCtaPac.get(i).getExtCero().getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingIncomplete)){
							MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
									lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.STAT_3 , mtrx.getTrxName());
							lstCtaPac.get(i).getExtCero().setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
						}else{
							log.log(Level.WARNING, "Encuentro : " +lstCtaPac.get(i).getEXME_CtaPac_ID()  
									+ " |Se incluyo en Proceso pero no entro en movimientos ");
						}
							
					}
					//PARA EL STEP FIRST INSURANCE
					else if (lstCtaPac.get(i).getExtCero().getInstitutionalStep().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTEP_FirstInsurance)){
						//Si el Encuentro es NOT BILLED = 1
						if (lstCtaPac.get(i).getExtCero().getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_NotBilled)){
							MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
									lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.CLAIM_1 , mtrx.getTrxName());
							lstCtaPac.get(i).getExtCero().setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							
						}
						//Si el Encuentro es NOT APPLICABLE = 2
						else if (lstCtaPac.get(i).getExtCero().getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_NotApplicable)){
							//si el Encuentro tiene cargos
							if (MCtaPacDet.getTotChargesByClaim(Env.getCtx(), lstCtaPac.get(i).getEXME_CtaPac_ID(), confType, mtrx.getTrxName())
									.compareTo(BigDecimal.ZERO)>0){
								MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
										lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.CLAIM_21 , mtrx.getTrxName());
								lstCtaPac.get(i).getExtCero().setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							}else{
								MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
										lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.CLAIM_22 , mtrx.getTrxName());
								lstCtaPac.get(i).getExtCero().setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
							}
						}
						//Si el Encuentro es CODING INCOMPLETE = 3
						else if (lstCtaPac.get(i).getExtCero().getInstitutionalStatus().equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingIncomplete)){
							MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
									lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.CLAIM_3 , mtrx.getTrxName());
							lstCtaPac.get(i).getExtCero().setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
						}else{
							log.log(Level.WARNING, "Encuentro : " +lstCtaPac.get(i).getEXME_CtaPac_ID()  
									+ " |Se incluyo en Proceso pero no entro en movimientos ");
						}
							
					}
				}
				// Para Professional
				else if (MEXMEClaimPaymentH.BILLINGTYPE_Professional.equalsIgnoreCase(confType)){
					//Movemenos Encuentros con Step DEFAULT
					if (lstCtaPac.get(i).getExtCero().getProfessionalStep().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTEP_Default)){
						//Si el Encuentro es NOT BILLED = 1
						if (lstCtaPac.get(i).getExtCero().getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_NotBilled)){
							MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
									lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.NOINS_1 , mtrx.getTrxName());
							lstCtaPac.get(i).getExtCero().setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							
						}
						//Si el Encuentro es NOT APPLICABLE = 2
						else if (lstCtaPac.get(i).getExtCero().getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_NotApplicable)){
							//solo si el Encuentro tiene cargos
							if (MCtaPacDet.getTotChargesByClaim(Env.getCtx(), lstCtaPac.get(i).getEXME_CtaPac_ID(), confType, mtrx.getTrxName())
									.compareTo(BigDecimal.ZERO)>0){
								MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
										lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.NOINS_2 , mtrx.getTrxName());
								lstCtaPac.get(i).getExtCero().setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							}
						}
						//Si el Encuentro es CODING INCOMPLETE = 3
						else if (lstCtaPac.get(i).getExtCero().getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_CodingIncomplete)){
							MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
									lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.NOINS_3 , mtrx.getTrxName());
							lstCtaPac.get(i).getExtCero().setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
						}else{
							log.log(Level.WARNING, "Encuentro : " +lstCtaPac.get(i).getEXME_CtaPac_ID()  
									+ " |Se incluyo en Proceso pero no entro en movimientos ");
						}
							
					}// Para el STEP SELF PAY
					else if (lstCtaPac.get(i).getExtCero().getProfessionalStep().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTEP_SelftPay)){
						//Si el Encuentro es NOT BILLED = 1
						if (lstCtaPac.get(i).getExtCero().getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_NotBilled)){
							MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
									lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.STAT_1 , mtrx.getTrxName());
							lstCtaPac.get(i).getExtCero().setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							
						}
						//Si el Encuentro es NOT APPLICABLE = 2
						else if (lstCtaPac.get(i).getExtCero().getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_NotApplicable)){
							//si el Encuentro tiene cargos
							if (MCtaPacDet.getTotChargesByClaim(Env.getCtx(), lstCtaPac.get(i).getEXME_CtaPac_ID(), confType, mtrx.getTrxName())
									.compareTo(BigDecimal.ZERO)>0){
								MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
										lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.STAT_21 , mtrx.getTrxName());
								lstCtaPac.get(i).getExtCero().setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							}else{
								MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
										lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.STAT_22 , mtrx.getTrxName());
								lstCtaPac.get(i).getExtCero().setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							}
						}
						//Si el Encuentro es CODING INCOMPLETE = 3
						else if (lstCtaPac.get(i).getExtCero().getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_CodingIncomplete)){
							MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
									lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.STAT_3 , mtrx.getTrxName());
							lstCtaPac.get(i).getExtCero().setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
						}else{
							log.log(Level.WARNING, "Encuentro : " +lstCtaPac.get(i).getEXME_CtaPac_ID()  
									+ " |Se incluyo en Proceso pero no entro en movimientos ");
						}
							
					}
					//PARA EL STEP FIRST INSURANCE
					else if (lstCtaPac.get(i).getExtCero().getProfessionalStep().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTEP_FirstInsurance)){
						//Si el Encuentro es NOT BILLED = 1
						if (lstCtaPac.get(i).getExtCero().getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_NotBilled)){
							MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
									lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.CLAIM_1 , mtrx.getTrxName());
							lstCtaPac.get(i).getExtCero().setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							
						}
						//Si el Encuentro es NOT APPLICABLE = 2
						else if (lstCtaPac.get(i).getExtCero().getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_NotApplicable)){
							//si el Encuentro tiene cargos
							if (MCtaPacDet.getTotChargesByClaim(Env.getCtx(), lstCtaPac.get(i).getEXME_CtaPac_ID(), confType, mtrx.getTrxName())
									.compareTo(BigDecimal.ZERO)>0){
								MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
										lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.CLAIM_21 , mtrx.getTrxName());
								lstCtaPac.get(i).getExtCero().setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							}else{
								MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
										lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.CLAIM_22 , mtrx.getTrxName());
								lstCtaPac.get(i).getExtCero().setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
							}
						}
						//Si el Encuentro es CODING INCOMPLETE = 3
						else if (lstCtaPac.get(i).getExtCero().getProfessionalStatus().equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_CodingIncomplete)){
							MEXMEClaimError.createError(Env.getCtx(), confType, MEXMEClaimError.TYPE_MandatoryFields, 
									lstCtaPac.get(i).getEXME_CtaPac_ID(), Constantes.CLAIM_3 , mtrx.getTrxName());
							lstCtaPac.get(i).getExtCero().setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
						}else{
							log.log(Level.WARNING, "Encuentro : " +lstCtaPac.get(i).getEXME_CtaPac_ID()  
									+ " |Se incluyo en Proceso pero no entro en movimientos ");
						}
							
					}
				}
				if (lstCtaPac.get(i).getExtCero().is_Changed()) {
					if (lstCtaPac.get(i).getExtCero().save()){
						msg.append("<br>")
						   .append(Utilerias.getMessage(Env.getCtx(), null, "msj.cuentaPac"))
						   .append(" : ")
						   .append(lstCtaPac.get(i).getDocumentNo());
						moved++;
					}else{
						msg.append("");
					}
				}
			}
			Trx.commit(mtrx);
			// Si todo fue exitoso se almacena en el mensaje de retorno los datos de los encuentros que se movieron
			retVal = Utilerias.getMessage(Env.getCtx(), null, "billing.exception.moveToExceptions") + "=" + moved + msg.toString();
		}catch(Exception e){
			Trx.rollback(mtrx);
			log.log(Level.SEVERE, "Ocurrio un error en Moving to Exceptions. Iterando encuentro : " + encounterNo + e.getMessage(), e);
			// Si fallo algo, se lanza a pantalla que un encuentro provoco un error en el proceso.
			retVal = Utilerias.getMessage(Env.getCtx(), null, "error.find.ctapac") + " : " + encounterNo;
		}finally {
			Trx.close(mtrx);
			mtrx = null;
		}
		return retVal;
		
	}

	private void logActivity(MEXMECtaPac ctaPac) {
		log.log(Level.WARNING, "Encuentro : " +ctaPac.getEXME_CtaPac_ID()
				+ " |ConfType = " + confType
				+ " |Step = " + ctaPac.getExtCero().getInstitutionalStep()
				+ " |Status anterior = " + ctaPac.getExtCero().getInstitutionalStatus()
				+ " |Moviendo a Exceptions ");
	}



}
