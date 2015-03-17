package org.compiere.model.reports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MCharge;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMEPacienteAseg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPayment;
import org.compiere.model.SQLFinancialRpts;
import org.compiere.model.X_HL7_MessageConf;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class BillingReports {
	/** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (BillingReports.class);
    
    
    
	
	public static List<ReportInsCollection> getList(String supportBilling, String filter, Properties ctx, List<Object> list){
		
		final List<ReportInsCollection> array = new ArrayList<ReportInsCollection>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = SQLFinancialRpts.getCollectedAnalysis(supportBilling, Boolean.TRUE).toString();
		
		if(StringUtils.isNotEmpty(filter)){
			query += " where ";
			query += filter;
		}
		try{
			pstmt = DB.prepareStatement(query, null);
			int i = 1;
			DB.setParameter(pstmt, i++, Env.getAD_Org_ID(ctx));
			DB.setParameter(pstmt, i++, MEXMECtaPac.ENCOUNTERSTATUS_Discharge);
			DB.setParameter(pstmt, i++, Env.getAD_Org_ID(ctx));
			DB.setParameter(pstmt, i++, MEXMECtaPac.ENCOUNTERSTATUS_Discharge);
			DB.setParameter(pstmt, i++, MPayment.CONFTYPE_Technical);
			DB.setParameter(pstmt, i++, MEXMECtaPac.ENCOUNTERSTATUS_Discharge);
			DB.setParameter(pstmt, i++, MCharge.TYPE_Payment);
			DB.setParameter(pstmt, i++, MCharge.TYPE_InsurancePayments);
			DB.setParameter(pstmt, i++, MCharge.TYPE_Others);
			DB.setParameter(pstmt, i++, MCharge.TYPE_OthersPayment);
			DB.setParameter(pstmt, i++, MCharge.TYPE_DeductiblePayment);
			DB.setParameter(pstmt, i++, MCharge.TYPE_CopayPayment);
			DB.setParameter(pstmt, i++, MCharge.TYPE_CoinsurancePayment);
			DB.setParameter(pstmt, i++, Env.getAD_Org_ID(ctx));
			
			//Ticket 0102428 Mostrar siempre la primer Aseguradora
//			DB.setParameter(pstmt, i++, MOrgInfo.SUPPORTBILLING_Professional.equals(supportBilling)
//										? MEXMECtaPacExt.PROFESSIONALSTEP_Default
//										: MEXMECtaPacExt.INSTITUTIONALSTEP_Default);
//			DB.setParameter(pstmt, i++, MOrgInfo.SUPPORTBILLING_Professional.equals(supportBilling)
//					? MEXMECtaPacExt.PROFESSIONALSTEP_SelftPay
//					: MEXMECtaPacExt.INSTITUTIONALSTEP_SelftPay);
//			DB.setParameter(pstmt, i++, MOrgInfo.SUPPORTBILLING_Professional.equals(supportBilling)
//					? MEXMECtaPacExt.PROFESSIONALSTEP_Default
//					: MEXMECtaPacExt.INSTITUTIONALSTEP_Default);
//			DB.setParameter(pstmt, i++, MOrgInfo.SUPPORTBILLING_Professional.equals(supportBilling)
//					? MEXMECtaPacExt.PROFESSIONALSTEP_SelftPay
//					: MEXMECtaPacExt.INSTITUTIONALSTEP_SelftPay);
			DB.setParameter(pstmt, i++, MOrgInfo.SUPPORTBILLING_Professional.equals(supportBilling)
					? MEXMEPacienteAseg.SUPPORTBILLING_Professional
					: MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
			//Ticket 0102428 Mostrar siempre la primer Aseguradora	
//			DB.setParameter(pstmt, i++, MOrgInfo.SUPPORTBILLING_Professional.equals(supportBilling)
//					? MEXMECtaPacExt.PROFESSIONALSTEP_FirstInsurance
//					: MEXMECtaPacExt.INSTITUTIONALSTEP_FirstInsurance);
//			DB.setParameter(pstmt, i++, MOrgInfo.SUPPORTBILLING_Professional.equals(supportBilling)
//					? MEXMECtaPacExt.PROFESSIONALSTEP_SecondInsurance
//					: MEXMECtaPacExt.INSTITUTIONALSTEP_SecondInsurance);
//			DB.setParameter(pstmt, i++, MOrgInfo.SUPPORTBILLING_Professional.equals(supportBilling)
//					? MEXMECtaPacExt.PROFESSIONALSTEP_ThirdInsurance
//					: MEXMECtaPacExt.INSTITUTIONALSTEP_ThirdInsurance);
			
			DB.setParameter(pstmt, i++, MEXMECtaPacExt.CONFTYPE_ProfessionalClaim);
			DB.setParameter(pstmt, i++, MEXMEPacienteAseg.SUPPORTBILLING_Professional);
			DB.setParameter(pstmt, i++, MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
			DB.setParameter(pstmt, i++, MOrgInfo.SUPPORTBILLING_AD_Reference_ID);
			DB.setParameter(pstmt, i++, MOrgInfo.SUPPORTBILLING_Professional.equals(supportBilling)
					? MEXMEPacienteAseg.SUPPORTBILLING_Professional
					: MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
			DB.setParameter(pstmt, i++, Env.getAD_Org_ID(ctx));
			DB.setParameter(pstmt, i++, MOrgInfo.SUPPORTBILLING_Professional.equals(supportBilling)
					? MEXMECtaPacExt.CONFTYPE_ProfessionalClaim
					: MEXMECtaPacExt.CONFTYPE_InstitutionalClaim);
			
			if (MOrgInfo.SUPPORTBILLING_Both.equals(supportBilling)){
				//Ticket 0102428 Mostrar siempre la primer Aseguradora
//				DB.setParameter(pstmt, i++, MEXMECtaPacExt.PROFESSIONALSTEP_Default);
//				DB.setParameter(pstmt, i++, MEXMECtaPacExt.PROFESSIONALSTEP_SelftPay);
//				DB.setParameter(pstmt, i++, MEXMECtaPacExt.PROFESSIONALSTEP_Default);
//				DB.setParameter(pstmt, i++, MEXMECtaPacExt.PROFESSIONALSTEP_SelftPay);
				DB.setParameter(pstmt, i++, MEXMEPacienteAseg.SUPPORTBILLING_Professional);
				//Ticket 0102428 Mostrar siempre la primer Aseguradora				
//				DB.setParameter(pstmt, i++, MEXMECtaPacExt.PROFESSIONALSTEP_FirstInsurance);
//				DB.setParameter(pstmt, i++, MEXMECtaPacExt.PROFESSIONALSTEP_SecondInsurance);
//				DB.setParameter(pstmt, i++, MEXMECtaPacExt.PROFESSIONALSTEP_ThirdInsurance);
				DB.setParameter(pstmt, i++, MEXMECtaPacExt.CONFTYPE_ProfessionalClaim);
				DB.setParameter(pstmt, i++, MEXMEPacienteAseg.SUPPORTBILLING_Professional);
				DB.setParameter(pstmt, i++, MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
				DB.setParameter(pstmt, i++, MOrgInfo.SUPPORTBILLING_AD_Reference_ID);
				DB.setParameter(pstmt, i++, MEXMEPacienteAseg.SUPPORTBILLING_Professional);
				DB.setParameter(pstmt, i++, Env.getAD_Org_ID(ctx));
				DB.setParameter(pstmt, i++, MEXMECtaPacExt.CONFTYPE_ProfessionalClaim);
			}
			for(Object obj : list ){
				DB.setParameter(pstmt, i++, obj);
			}		
			rs = pstmt.executeQuery();
			while (rs.next()){
				array.add(new ReportInsCollection(rs, ctx));
			}

			
		} catch (Exception e) {
			log.log(Level.SEVERE, "SQL: " + query, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return array;
	}
	
	public static List<ReportEncBalancesDet> getListBalEncDet(String supportBilling, String filter, Properties ctx, List<Object> list){
		final List<ReportEncBalancesDet> array = new ArrayList<ReportEncBalancesDet>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder query = SQLFinancialRpts.getEncounterBalancesDetail();
		
		if(StringUtils.isNotEmpty(filter)){
			query.append(" where ");
			query.append(filter);
		}
		try{
			pstmt = DB.prepareStatement(query.toString(), null);
			int i = 1;
			

			DB.setParameter(pstmt, i++,Env.getAD_Org_ID(Env.getCtx()));
			DB.setParameter(pstmt, i++, MEXMECtaPac.ENCOUNTERSTATUS_Discharge);
			DB.setParameter(pstmt, i++,Env.getAD_Org_ID(Env.getCtx()));
			DB.setParameter(pstmt, i++, MEXMECtaPac.ENCOUNTERSTATUS_Discharge);
			
			DB.setParameter(pstmt, i++, X_HL7_MessageConf.CONFTYPE_InstitutionalClaim);
			DB.setParameter(pstmt, i++, MCharge.TYPE_Coinsurance);
			DB.setParameter(pstmt, i++, MCharge.TYPE_Deductible);
			DB.setParameter(pstmt, i++, MCharge.TYPE_CoPay);
			DB.setParameter(pstmt, i++,Env.getAD_Org_ID(Env.getCtx()));
			DB.setParameter(pstmt, i++, MEXMECtaPac.ENCOUNTERSTATUS_Discharge);
			
			DB.setParameter(pstmt, i++, X_HL7_MessageConf.CONFTYPE_InstitutionalClaim);
			DB.setParameter(pstmt, i++, MCharge.TYPE_Payment);
			DB.setParameter(pstmt, i++, MCharge.TYPE_InsurancePayments);
			DB.setParameter(pstmt, i++, MCharge.TYPE_Others);
			DB.setParameter(pstmt, i++, MCharge.TYPE_OthersPayment);
			DB.setParameter(pstmt, i++, MCharge.TYPE_DeductiblePayment);
			DB.setParameter(pstmt, i++, MCharge.TYPE_CopayPayment);
			DB.setParameter(pstmt, i++, MCharge.TYPE_CoinsurancePayment);
			DB.setParameter(pstmt, i++,Env.getAD_Org_ID(Env.getCtx()));
			DB.setParameter(pstmt, i++, MEXMECtaPac.ENCOUNTERSTATUS_Discharge);
			
			DB.setParameter(pstmt, i++, X_HL7_MessageConf.CONFTYPE_InstitutionalClaim);
			DB.setParameter(pstmt, i++, MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
			DB.setParameter(pstmt, i++, X_HL7_MessageConf.CONFTYPE_ProfessionalClaim);
			DB.setParameter(pstmt, i++, MEXMEPacienteAseg.SUPPORTBILLING_Professional);
			
			DB.setParameter(pstmt, i++, X_HL7_MessageConf.CONFTYPE_InstitutionalClaim);
			DB.setParameter(pstmt, i++, MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
			DB.setParameter(pstmt, i++, MEXMECtaPacExt.INSTITUTIONALSTEP_FirstInsurance);
			DB.setParameter(pstmt, i++, MEXMECtaPacExt.INSTITUTIONALSTEP_SecondInsurance);
			DB.setParameter(pstmt, i++, MEXMECtaPacExt.INSTITUTIONALSTEP_ThirdInsurance);
			
			DB.setParameter(pstmt, i++, X_HL7_MessageConf.CONFTYPE_ProfessionalClaim);
			DB.setParameter(pstmt, i++, MEXMEPacienteAseg.SUPPORTBILLING_Professional);
			DB.setParameter(pstmt, i++, MEXMECtaPacExt.PROFESSIONALSTEP_FirstInsurance);
			DB.setParameter(pstmt, i++, MEXMECtaPacExt.PROFESSIONALSTEP_SecondInsurance);
			DB.setParameter(pstmt, i++, MEXMECtaPacExt.PROFESSIONALSTEP_ThirdInsurance);
			
			DB.setParameter(pstmt, i++, MEXMECtaPacExt.CONFTYPE_AD_Reference_ID);
			DB.setParameter(pstmt, i++, MEXMECtaPacExt.INSTITUTIONALSTEP_AD_Reference_ID);
			DB.setParameter(pstmt, i++, X_HL7_MessageConf.CONFTYPE_InstitutionalClaim);
			DB.setParameter(pstmt, i++, X_HL7_MessageConf.CONFTYPE_ProfessionalClaim);
			
			DB.setParameter(pstmt, i++, MEXMECtaPacExt.INSTITUTIONALSTATUS_AD_Reference_ID);
			DB.setParameter(pstmt, i++, X_HL7_MessageConf.CONFTYPE_InstitutionalClaim);
			DB.setParameter(pstmt, i++, X_HL7_MessageConf.CONFTYPE_ProfessionalClaim);
			
			for(Object obj : list ){
				DB.setParameter(pstmt, i++, obj);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				array.add(new ReportEncBalancesDet(rs, ctx));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL : " + query.toString(), e);
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}finally {
			DB.close(rs, pstmt);
		}

		return array;
	
		
	}
	
	public static String returnWithFormat(double value){
		return NumberFormat.getCurrencyInstance(Locale.CANADA).format(value);
	}
	
	public static String returnWithPercentajeFormat(double value){
		return NumberFormat.getPercentInstance(Locale.CANADA).format(value);
		
	}

}
