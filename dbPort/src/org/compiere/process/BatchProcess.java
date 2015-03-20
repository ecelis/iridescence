package org.compiere.process;

import static org.compiere.util.Utilerias.getMessage;
import static org.compiere.util.Utilerias.getMessageArgs;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMEBatchClaimD;
import org.compiere.model.MEXMEBatchClaimH;
import org.compiere.model.MEXMEClaimError;
import org.compiere.model.MEXMEClaimMessage;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MEXMEPacienteAseg;
import org.compiere.model.MHL7MessageConf;
import org.compiere.model.MInvoice;
import org.compiere.model.X_EXME_BatchClaimH;
import org.compiere.model.billing.Billing;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.confHL7.MessageGenerator;

public class BatchProcess {
	
	private static CLogger log = CLogger.getCLogger(BatchProcess.class);
	private final String genLogStmt = "Generation of Log for Statement Sent.";
	private int totalBatch;
	private int totalDetail;
	private List<MEXMECtaPacExt> lstCtaPacExt = new ArrayList<MEXMECtaPacExt>();
	List<String> settetrLst = null;
	
	public String sendBatch(MEXMEBatchClaimH batchH) {
		boolean send = Boolean.FALSE;
		StringBuffer msg = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		try {
			MessageGenerator generator = new MessageGenerator(Env.getCtx(), true);
			
			for (MEXMEBatchClaimD det : MEXMEBatchClaimD.getListDetail(Env.getCtx(), " AND EXME_BatchClaimH_ID = ?", null, batchH.getEXME_BatchClaimH_ID())) {
				if (!generaClaim(batchH.getConfType(), new MEXMECtaPacExt(Env.getCtx(), det.getEXME_CtaPacExt_ID(), null), null)) {
					return getMessage(Env.getCtx(), null, "advancedDirectives.msj.error.generarArchivo");
				}
			}
			
			send = generator.generateMessage(batchH.getEXME_BatchClaimH_ID(), MEXMEBatchClaimH.Table_Name, batchH.getConfType(), null);
			if (MHL7MessageConf.CONFTYPE_PIStatement.equals(batchH.getConfType()) && send) {
				// Genera facturas de paciente
				List <MInvoice> lstInvoice = generaInvoice(Env.getCtx(), batchH.getEXME_BatchClaimH_ID());
				// Generar registro en ClaimLog
				generaLogStmt(batchH.getEXME_BatchClaimH_ID(), lstInvoice);
			}
			if (send) {
				msg.append(getMessage(Env.getCtx(), null, "msg.ordenServ.completadas"));
			} else {
				msg.append(getMessage(Env.getCtx(), null, "advancedDirectives.msj.error.generarArchivo"));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		
		return msg.toString();
	}
	
	private String getSql(String confType) {
		settetrLst = new ArrayList<String>();
		StringBuilder sqlWhere = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		// Init AND
		sqlWhere.append(" AND EXT.EXME_CTAPACEXT_ID IN ( ");
		
		// INSTITUTIONAL OR PROFESSIONAL
		if (MEXMECtaPacExt.CONFTYPE_InstitutionalClaim.equals(confType) 
				|| MEXMECtaPacExt.CONFTYPE_ProfessionalClaim.equals(confType)) {
			sqlWhere.append(" SELECT L1.EXME_CTAPACEXT_ID ")
					.append(" FROM ( ")
							.append(" SELECT CPE.EXME_CTAPACEXT_ID, CPE.EXME_CTAPAC_ID, CP.FECHAALTA ")
							.append(" FROM EXME_CTAPACEXT CPE INNER JOIN EXME_CTAPAC CP ON CP.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID ")
							.append(" WHERE CPE.ISACTIVE = 'Y' ");
			// Checking institutional fields and using institutional values
			if (MEXMECtaPacExt.CONFTYPE_InstitutionalClaim.equals(confType)) {
				sqlWhere.append(" AND CPE.INSTITUTIONALSTATUS IN (?, ?, ?, ?, ?) AND CPE.INSTITUTIONALSTEP IN (?) ");
				settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingComplete);
		    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKOrdersComplete);
		    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKPricesInZero);
		    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKMandatoryFields);
		    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKMessage997);
		    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTEP_FirstInsurance);
		    	settetrLst.add(MBPartner.SUPPORTBILLING_Institucional);
			} else { // Checking professional fields and using professional values
				sqlWhere.append(" AND CPE.PROFESSIONALSTATUS IN (?, ?, ?, ?, ?) AND CPE.PROFESSIONALSTEP IN (?) ");
				settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_CodingComplete);
		    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKOrdersComplete);
		    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKPricesInZero);
		    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKMandatoryFields);
		    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKMessage997);
		    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTEP_FirstInsurance);
		    	settetrLst.add(MBPartner.SUPPORTBILLING_Professional);
			}
			sqlWhere.append(" AND CPE.EXTENSIONNO = 0 ") // Extension 0
			        .append(" ) L1 ");
			if (DB.isPostgreSQL()) {
				sqlWhere.append(" WHERE L1.FECHAALTA::DATE "); 
			} else if (DB.isOracle()) {
				sqlWhere.append(" WHERE TRUNC(L1.FECHAALTA) ");
			} else {
				sqlWhere.append(" WHERE L1.FECHAALTA ");
			}
			// Condition: Minimum days required before being considered to a batch
			sqlWhere.append(" <= (").append(DB.TO_DATE(DB.convert(Env.getCtx(), new Date())))
		    .append(" - (SELECT MAX(QueuingTime) " )
		    .append(" FROM EXME_PacienteAseg PA " )
		    .append(" INNER JOIN C_BPartner CB " )
		    .append(" ON CB.C_BPartner_ID = PA.C_BPartner_ID " )
		    .append(" WHERE PA.EXME_CtaPac_ID = L1.EXME_CtaPac_ID " )
		    .append(" AND PA.IsActive = 'Y' " )
		    .append(" AND PA.PRIORITY = 1 " )
		    .append(" AND PA.SupportBilling = ? )) " );
			
			// Condition: The same encounter is not issued in another batch for the same configuration type (this only applies to extension 0)
			sqlWhere.append(" AND NOT EXISTS(SELECT D.EXME_BATCHCLAIMD_ID FROM EXME_BATCHCLAIMH H ")
					                .append(" INNER JOIN EXME_BATCHCLAIMD D ON D.EXME_BATCHCLAIMH_ID = H.EXME_BATCHCLAIMH_ID ")
					                .append(" WHERE H.ISACTIVE = 'Y' AND H.CONFTYPE = ? AND D.ISACTIVE = 'Y' ")
					                .append(" AND D.EXME_CTAPAC_ID = L1.EXME_CTAPAC_ID) ");
			settetrLst.add(confType); // (H.CONFTYPE = ?)
			
			// Verifica si el balance de la cuenta es positivo, de lo contrario no lo agrega al batch
	    	sqlWhere.append(" AND fnc_getBalance(L1.EXME_CtaPac_ID) > 0 ");
			
			sqlWhere.append(" UNION ") // Condition: Add all extension previously created and not added or created throught Late Charges
					.append(" SELECT L2.EXME_CTAPACEXT_ID ")
					.append(" FROM EXME_CTAPACEXT L2 ")
					.append(" WHERE L2.ISACTIVE = 'Y' ")
					.append(" AND L2.CONFTYPE = ? ")
					.append(" AND L2.CHARGESTATUS IN (?, ?) ");
			settetrLst.add(confType); // (L2.CONFTYPE = ?)
			settetrLst.add(MEXMECtaPacExt.CHARGESTATUS_ForBill); // (AND L2.CHARGESTATUS IN (?, ?))
	    	settetrLst.add(MEXMECtaPacExt.CHARGESTATUS_ForRebill);
	    	
	    	// Checking institutional fields and using institutional values
			if (MEXMECtaPacExt.CONFTYPE_InstitutionalClaim.equals(confType)) {
				sqlWhere.append(" AND L2.INSTITUTIONALSTATUS IN (?, ?, ?, ?, ?) AND L2.INSTITUTIONALSTEP IN (?) ");
				settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingComplete);
		    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKOrdersComplete);
		    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKPricesInZero);
		    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKMandatoryFields);
		    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_OKMessage997);
		    	settetrLst.add(MEXMECtaPacExt.INSTITUTIONALSTEP_FirstInsurance);
			} else { // Checking professional fields and using professional values
				sqlWhere.append(" AND L2.PROFESSIONALSTATUS IN (?, ?, ?, ?, ?) AND L2.PROFESSIONALSTEP IN (?) ");
				settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_CodingComplete);
		    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKOrdersComplete);
		    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKPricesInZero);
		    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKMandatoryFields);
		    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_OKMessage997);
		    	settetrLst.add(MEXMECtaPacExt.PROFESSIONALSTEP_FirstInsurance);
			}
	    	
	    	sqlWhere.append(" AND NOT EXISTS(SELECT D.EXME_BATCHCLAIMD_ID FROM EXME_BATCHCLAIMH H ")
	    			                .append(" INNER JOIN EXME_BATCHCLAIMD D ON D.EXME_BATCHCLAIMH_ID = H.EXME_BATCHCLAIMH_ID ")
	    			                .append(" WHERE H.ISACTIVE = 'Y' AND H.CONFTYPE = ? AND D.ISACTIVE = 'Y' ")
	    			                .append(" AND D.EXME_CTAPACEXT_ID = L2.EXME_CTAPACEXT_ID ")
	    			                .append(" AND (H.ISGENERATED = 'N' OR L2.CHARGESTATUS = ?)) ");
	    	settetrLst.add(confType); // (H.CONFTYPE = ?)
			settetrLst.add(MEXMECtaPacExt.CHARGESTATUS_ForBill);
			
			// Verifica si el balance de la cuenta es positivo, de lo contrario no lo agrega al batch
	    	sqlWhere.append(" AND fnc_getBalance(L2.EXME_CtaPac_ID) > 0 ");
			
			// End of Init AND
			sqlWhere.append(" ) ");
		}
		
		// STATEMENTS
		if (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType)) {
			sqlWhere.append(" SELECT L1.EXME_CTAPACEXT_ID ")
				    .append(" FROM ( ")
				          .append(" SELECT CPE.EXME_CTAPACEXT_ID, CPE.EXME_CTAPAC_ID ")
				          .append(" FROM EXME_CTAPACEXT CPE ")
				          .append(" WHERE CPE.ISACTIVE = 'Y' ")
				          .append(" AND (InstitutionalStatus IN (?,?,?,?,?,?) AND InstitutionalStep IN (?) ")
				               .append(" OR ProfessionalStatus  IN (?,?,?,?,?,?) AND ProfessionalStep  IN (?) ")
				              .append(" ) ")
				    .append(" ) L1 "); // Checking institutional and professional fields/values
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
		    sqlWhere.append(" WHERE ").append(DB.TO_DATE(DB.convert(Env.getCtx(), new Date())))
		    .append(" >= COALESCE( ")
	        .append(" (SELECT to_date(MAX(TO_char(DATE_SUBMITTED,'YYYY-MM-DD')),'YYYY-MM-DD') ")
	        .append(" + fnc_getStmtAge(L1.EXME_CtaPac_ID) ")
	        .append(" FROM EXME_CLAIMLOG CL WHERE CL.EXME_CTAPACEXT_ID = L1.EXME_CTAPACEXT_ID ")
	        .append(" AND CL.CONFTYPE = ?), ")
	        .append(DB.TO_DATE(DB.convert(Env.getCtx(), new Date()))).append(") ");
		    settetrLst.add(confType); // (CL.CONFTYPE = ?)
		    
		    // Verifica si el balance de la cuenta es positivo, de lo contrario no lo agrega al batch
	    	sqlWhere.append(" AND fnc_getBalance(L1.EXME_CtaPac_ID) > 0 ");
	    	
	    	// Valida que el statement cumpla con reglas definidas para Tipo de Paciente, 
	    	//Organizacion y Estatus de Encounter (1 = Valido, 0 = No Valido)
	    	// Actualmente se quitaron las validaciones en la funcion por solicitud de Kim, se deja la llamada a la funcion en caso de que se requieran a futuro 
	    	// regresar las validaciones o implementar nuevas (la funcion actualmente siempre regresa el valor de 1)
	    	sqlWhere.append(" AND fnc_stmtRules(L1.EXME_CtaPac_ID) = 1 ");
	    	
	    	// Condition: The same encounter is not issued in another batch for the same configuration type AND still not generated
	    	sqlWhere.append(" AND NOT EXISTS(SELECT D.EXME_BATCHCLAIMD_ID FROM EXME_BATCHCLAIMH H ")
	    							.append(" INNER JOIN EXME_BATCHCLAIMD D ON D.EXME_BATCHCLAIMH_ID = H.EXME_BATCHCLAIMH_ID ")
	    							.append(" WHERE H.ISACTIVE = 'Y' AND H.CONFTYPE = ? AND D.ISACTIVE = 'Y' ")
	    							.append(" AND H.ISGENERATED = 'N' AND D.EXME_CTAPAC_ID = L1.EXME_CTAPAC_ID) ");
	    	settetrLst.add(confType); // (H.CONFTYPE = ?)
	    	
	    	// End of Init AND
			sqlWhere.append(" ) ");
		}
		
		return sqlWhere.toString();
	}
	
	// Clase intermedia para proceso de validacion de extensiones en la creacion de batchs
	@SuppressWarnings("unused")
	private class ValidaExtVO {
		private int EXME_CtaPac_ID = 0;
		private int EXME_CtaPacExt_ID = 0;
		
		private int mfCoverageInst = 0;
		private int mfChargesInst = 0;
		private int incompleteInst = 0;
		private int mfRevCodeInst = 0;
		private int pendingInst = 0;
		
		private int mfCoverageProf = 0;
		private int mfChargesProf = 0;
		private int incompleteProf = 0;
		private int mfRevCodeProf = 0;
		private int pendingProf = 0;
		
		public ValidaExtVO(ResultSet rs) throws SQLException {
			setEXME_CtaPac_ID(rs.getInt("EXME_CtaPac_ID"));
			setEXME_CtaPacExt_ID(rs.getInt("EXME_CtaPacExt_ID"));
			setmfCoverageInst(rs.getInt("NC_Inst"));
			setmfCoverageProf(rs.getInt("NC_Prof"));
			setmfChargesInst(rs.getInt("MD_Chg_Inst"));
			setmfChargesProf(rs.getInt("MD_Chg_Prof"));
			setincompleteInst(rs.getInt("IC_Inst"));
			setincompleteProf(rs.getInt("IC_Prof"));
			setmfRevCodeInst(rs.getInt("MD_Rev_Inst"));
			setmfRevCodeProf(rs.getInt("MD_Rev_Prof"));
			setpendingInst(rs.getInt("MD_Price_Inst"));
			setpendingProf(rs.getInt("MD_Price_Prof"));
		}
		
		public void setEXME_CtaPac_ID(int EXME_CtaPac_ID) {
			this.EXME_CtaPac_ID = EXME_CtaPac_ID;
		}
		public int getEXME_CtaPac_ID() {
			return EXME_CtaPac_ID;
		}
		public void setEXME_CtaPacExt_ID(int EXME_CtaPacExt_ID) {
			this.EXME_CtaPacExt_ID = EXME_CtaPacExt_ID;
		}
		public int getEXME_CtaPacExt_ID() {
			return EXME_CtaPacExt_ID;
		}
		public void setmfCoverageInst(int mfCoverageInst) {
			this.mfCoverageInst = mfCoverageInst;
		}
		public int getmfCoverageInst() {
			return mfCoverageInst;
		}
		public void setmfCoverageProf(int mfCoverageProf) {
			this.mfCoverageProf = mfCoverageProf;
		}
		public int getmfCoverageProf() {
			return mfCoverageProf;
		}
		public void setmfChargesInst(int mfChargesInst) {
			this.mfChargesInst = mfChargesInst;
		}
		public int getmfChargesInst() {
			return mfChargesInst;
		}
		public void setmfChargesProf(int mfChargesProf) {
			this.mfChargesProf = mfChargesProf;
		}
		public int getmfChargesProf() {
			return mfChargesProf;
		}
		public void setincompleteInst(int incompleteInst) {
			this.incompleteInst = incompleteInst;
		}
		public int getincompleteInst() {
			return incompleteInst;
		}
		public void setincompleteProf(int incompleteProf) {
			this.incompleteProf = incompleteProf;
		}
		public int getincompleteProf() {
			return incompleteProf;
		}
		public void setmfRevCodeInst(int mfRevCodeInst) {
			this.mfRevCodeInst = mfRevCodeInst;
		}
		public int getmfRevCodeInst() {
			return mfRevCodeInst;
		}
		public void setmfRevCodeProf(int mfRevCodeProf) {
			this.mfRevCodeProf = mfRevCodeProf;
		}
		public int getmfRevCodeProf() {
			return mfRevCodeProf;
		}
		public void setpendingInst(int pendingInst) {
			this.pendingInst = pendingInst;
		}
		public int getpendingInst() {
			return pendingInst;
		}
		public void setpendingProf(int pendingProf) {
			this.pendingProf = pendingProf;
		}
		public int getpendingProf() {
			return pendingProf;
		}
	}
	
	private List<ValidaExtVO> getValidatedList(List<MEXMECtaPacExt> listExt) {
		List<ValidaExtVO> validatedList = new ArrayList<ValidaExtVO>();
		if (listExt.isEmpty()) {
			return validatedList;
		}
		StringBuilder sqlSelect = new StringBuilder();
		StringBuilder sqlLeft = new StringBuilder();
		StringBuilder sql = new StringBuilder();
		StringBuilder strListExt = new StringBuilder();
		
		sqlSelect.append(" SELECT EXT.EXME_CtaPac_ID, EXT.EXME_CtaPacExt_ID ");
		
		// Incomplete Services
		sqlSelect.append(" , SUM(CASE WHEN fnc_isprofessional(IC.EXME_PRODUCTOORG_ID, IC.AD_ORG_ID) = 'Y' THEN IC.TOTAL ELSE 0 END) AS IC_PROF ")
				 .append(" , SUM(CASE WHEN fnc_isprofessional(IC.EXME_PRODUCTOORG_ID, IC.AD_ORG_ID) = 'N' THEN IC.TOTAL ELSE 0 END) AS IC_INST ");
		sqlLeft.append(" LEFT JOIN ( ")
				          .append(" SELECT CTA.EXME_CTAPAC_ID, fnc_getProductOrg(I.M_PRODUCT_ID, I.AD_ORG_ID) AS EXME_PRODUCTOORG_ID ")
				          .append(" , I.AD_ORG_ID, COUNT(*) AS TOTAL ")
				          .append(" FROM EXME_CTAPAC CTA INNER JOIN EXME_ACTPACIENTEINDH H ON H.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID ")
				                              .append(" INNER JOIN EXME_ACTPACIENTEIND I ON I.EXME_ACTPACIENTEINDH_ID = H.EXME_ACTPACIENTEINDH_ID ")
				                              .append(" INNER JOIN EXME_CTAPACDET D ON D.EXME_ACTPACIENTEIND_ID = I.EXME_ACTPACIENTEIND_ID ")
				                              .append(" INNER JOIN EXME_CTAPACEXT E ON E.EXME_CTAPACEXT_ID = D.EXME_CTAPACEXT_ID AND E.EXTENSIONNO = 0 ")
				          .append(" WHERE I.ESTATUS = 'S' ")
				          .append(" GROUP BY CTA.EXME_CTAPAC_ID, I.M_PRODUCT_ID, I.AD_ORG_ID ")
			   .append(" ) IC ON IC.EXME_CTAPAC_ID = EXT.EXME_CTAPAC_ID ");
		
		// No Insurance Coverage
		sqlSelect.append(" , SUM(CASE WHEN NC.SUPPORTBILLING = 'PR' THEN NC.TOTAL ELSE 0 END) AS NC_PROF ")
				 .append(" , SUM(CASE WHEN NC.SUPPORTBILLING = 'IN' THEN NC.TOTAL ELSE 0 END) AS NC_INST ");
		sqlLeft.append(" LEFT JOIN ( ")
		                  .append(" SELECT PA.EXME_CTAPAC_ID, PA.SUPPORTBILLING, COUNT(*) AS TOTAL ")
		                  .append(" FROM EXME_PACIENTEASEG PA ")
		                  .append(" WHERE PA.ISACTIVE = 'Y' ")
		                  .append(" GROUP BY PA.EXME_CTAPAC_ID, PA.SUPPORTBILLING ")
		       .append(" ) NC ON NC.EXME_CTAPAC_ID = EXT.EXME_CTAPAC_ID ");
		
		// Missing Charges, Rev Codes, Prices
		sqlSelect.append(" , SUM(CASE WHEN fnc_isprofessional(MD.EXME_PRODUCTOORG_ID, MD.AD_ORG_ID) = 'Y' THEN MD.TOTAL ELSE 0 END) AS MD_CHG_PROF")
		         .append(" , SUM(CASE WHEN fnc_isprofessional(MD.EXME_PRODUCTOORG_ID, MD.AD_ORG_ID) = 'N' THEN MD.TOTAL ELSE 0 END) AS MD_CHG_INST")
		         .append(" , SUM(CASE WHEN fnc_isprofessional(MD.EXME_PRODUCTOORG_ID, MD.AD_ORG_ID) = 'Y' AND MD.EXME_REVENUECODE_ID IS NULL THEN MD.TOTAL ELSE 0 END) AS MD_REV_PROF")
		         .append(" , SUM(CASE WHEN fnc_isprofessional(MD.EXME_PRODUCTOORG_ID, MD.AD_ORG_ID) = 'N' AND MD.EXME_REVENUECODE_ID IS NULL THEN MD.TOTAL ELSE 0 END) AS MD_REV_INST")
		         .append(" , SUM(CASE WHEN fnc_isprofessional(MD.EXME_PRODUCTOORG_ID, MD.AD_ORG_ID) = 'Y' AND MD.CALCULARPRECIO = 'N' THEN MD.TOTAL ELSE 0 END) AS MD_PRICE_PROF")
		         .append(" , SUM(CASE WHEN fnc_isprofessional(MD.EXME_PRODUCTOORG_ID, MD.AD_ORG_ID) = 'N' AND MD.CALCULARPRECIO = 'N' THEN MD.TOTAL ELSE 0 END) AS MD_PRICE_INST");
		sqlLeft.append(" LEFT JOIN ( ")
		                  .append(" SELECT CPD.EXME_CTAPAC_ID, CPD.EXME_CTAPACEXT_ID, CPD.CALCULARPRECIO, PO.EXME_REVENUECODE_ID ")
		                  .append(" , CPD.AD_ORG_ID, PO.EXME_PRODUCTOORG_ID, COUNT(*) AS TOTAL ")
		                  .append(" FROM EXME_CTAPACDET CPD INNER JOIN EXME_PRODUCTOORG PO ON PO.EXME_PRODUCTOORG_ID = fnc_getproductorg(CPD.M_PRODUCT_ID, CPD.AD_ORG_ID) ")
		                  .append(" WHERE CPD.ISACTIVE = 'Y' ")
		                  .append(" GROUP BY CPD.EXME_CTAPAC_ID, CPD.EXME_CTAPACEXT_ID, CPD.CALCULARPRECIO, PO.EXME_REVENUECODE_ID ")
		                  .append(" , CPD.AD_ORG_ID, PO.EXME_PRODUCTOORG_ID ")
		       .append(" ) MD ON MD.EXME_CTAPACEXT_ID = EXT.EXME_CTAPACEXT_ID ");
		
		for (MEXMECtaPacExt ext : listExt) {
			strListExt.append(ext.getEXME_CtaPacExt_ID()).append(",");
		}
		sql.append(sqlSelect)
		   .append(" FROM EXME_CTAPACEXT EXT ").append(sqlLeft)
		   .append(" WHERE EXT.ISACTIVE = 'Y' ")
		   .append(" AND EXT.EXME_CTAPACEXT_ID IN (").append(strListExt.substring(0, strListExt.length()-1)).append(") ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ",MEXMECtaPacExt.Table_Name, "ext"));
		sql.append(" GROUP BY EXT.EXME_CTAPAC_ID, EXT.EXME_CTAPACEXT_ID ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ValidaExtVO validaExt = new ValidaExtVO(rs);
				validatedList.add(validaExt);
			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}	
		
		return validatedList;
	}
	
	private boolean getDetailList(String confType, String trxName) { 
		boolean success = Boolean.TRUE;
		lstCtaPacExt.clear();

    	List<MEXMECtaPacExt> tmpLstCtaPacExt = new ArrayList<MEXMECtaPacExt>(); 
    	List<ValidaExtVO> tmpLstValidated = new ArrayList<ValidaExtVO>();
    	
	    if (MEXMECtaPacExt.CONFTYPE_ProfessionalClaim.equals(confType) 
	    		|| MEXMECtaPacExt.CONFTYPE_InstitutionalClaim.equals(confType)) {
	    	tmpLstCtaPacExt = MEXMECtaPacExt.getExtCtas(Env.getCtx(), getSql(confType),  
					null, settetrLst.toArray());
	    } else if (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType)) {
	    	tmpLstCtaPacExt = MEXMECtaPacExt.getExtGroupCtas(Env.getCtx(), getSql(confType), 
					null, settetrLst.toArray());
	    }
	    
	    //log.log(Level.SEVERE, "Query ejecutado - > "+DB.convert(Env.getCtx(), new Date()).toString());
	    
	    // Validate Extensions
	    tmpLstValidated = getValidatedList(tmpLstCtaPacExt);
	    
	    //for (MEXMECtaPacExt ctaExt : tmpLstCtaPacExt) {
	    for (ValidaExtVO vExt : tmpLstValidated) {
	    	MEXMECtaPacExt ctaExt = new MEXMECtaPacExt(Env.getCtx(), vExt.getEXME_CtaPacExt_ID(), trxName);
	    	
	    	if (MEXMECtaPacExt.CONFTYPE_InstitutionalClaim.equals(confType) 
	    			|| MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType)) {
	    		
	    		//MISSING DATA - NO INSURANCE COVERAGE Inst
	    		//final int mfCoverageInst = MEXMEPacienteAseg.getByCtaPacSupport(Env.getCtx(), ctaExt.getEXME_CtaPac_ID(), 
				//		MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
	    		
	    		if ((MEXMECtaPacExt.CONFTYPE_InstitutionalClaim.equals(confType) 
	    				&& vExt.getmfCoverageInst()==0 && !ctaExt.getCtaPac().isNoInsuranceCoverage()) 
	    			|| (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType) 
	    					&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_InstitutionalClaim) ? vExt.getmfCoverageInst() : 1)==0
	    					&& !ctaExt.getCtaPac().isNoInsuranceCoverage())
	    			) {
	    			ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
					if (!(ctaExt.save(trxName)
						  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_InstitutionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
								  getMessage(Env.getCtx(), null, "billing.exception.inst.missingCoverage"), trxName))) {
						log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "billing.exception.inst.missingCoverage"));
						success = Boolean.FALSE;
						break;
					} else {
						continue;
					}
	    		}
	    		
	    		// Missing Charges (Cuenta sin cargos Institutional)
	    		//final int mfChargesInst = MCtaPacDet.getCargosByClaim(Env.getCtx(), ctaExt.getEXME_CtaPacExt_ID(), 
				//		MHL7MessageConf.CONFTYPE_InstitutionalClaim, null, Constantes.CLAIM_MISSINGCHARGES).size();
	    		
	    		if ((MEXMECtaPacExt.CONFTYPE_InstitutionalClaim.equals(confType) && vExt.getmfChargesInst()==0) 
	    				|| (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType) 
	    						&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_InstitutionalClaim) ? vExt.getmfChargesInst() : 1)==0 )
	    			) {
	    			ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
					if (!(ctaExt.save(trxName)
						  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_InstitutionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
								  getMessage(Env.getCtx(), null, "billing.exception.inst.missingCharges"), trxName))) {
						log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "billing.exception.inst.missingCharges"));
						success = Boolean.FALSE;
						break;
					} else {
						continue;
					}
	    		}
	    		
	    		// Incomplete Service (Servicios pendientes de completar)
	    		//final int incompleteInst= MEXMEActPacienteIndH.getCount(Env.getCtx(), ctaExt.getEXME_CtaPac_ID(), 0, null, 
				//		MEXMEActPacienteInd.ESTATUS_RequestedService, MHL7MessageConf.CONFTYPE_InstitutionalClaim, null);
	    		
	    		if ((MEXMECtaPacExt.CONFTYPE_InstitutionalClaim.equals(confType) && vExt.getincompleteInst() > 0)
	    				|| (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType) 
	    						&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_InstitutionalClaim) ? vExt.getincompleteInst() : 0)>0 )
	    			) {
	    			ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorOrdersIncomplete);
					if (!ctaExt.save(trxName)) {
						log.log(Level.SEVERE, "Error Saving Orders Institutional IncompleteStatus");
						success = Boolean.FALSE;
						break;
					} else {
						continue;
					}
	    		}
	    		
	    		// MISSING DATA - CHARGES WHITOUT REV CODE
	    		//final int mfRevCodeInst = MCtaPacDet.getCargosByClaim(Env.getCtx(), ctaExt.getEXME_CtaPacExt_ID(), 
				//		MHL7MessageConf.CONFTYPE_InstitutionalClaim, null, Constantes.CLAIM_MISSINGREVCODE).size();
	    		
	    		if ((MEXMECtaPacExt.CONFTYPE_InstitutionalClaim.equals(confType) && vExt.getmfRevCodeInst()>0)
	    				|| (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType) 
	    						&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_InstitutionalClaim) ? vExt.getmfRevCodeInst() : 0)>0 ) 
	    			) {
	    			ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
					if (!(ctaExt.save(trxName)
						  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_InstitutionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
								  getMessage(Env.getCtx(), null, "billing.exception.inst.missingRevCode"), trxName))) {
						log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "billing.exception.inst.missingRevCode"));
						success = Boolean.FALSE;
						break;
					} else {
						continue;
					}
	    		}
	    		
	    		// Missing Prices (Precios Pendientes ó 0 sin validar)
	    		//final int pendingInst  = MCtaPacDet.getCargosByClaim(Env.getCtx(), ctaExt.getEXME_CtaPacExt_ID(), 
				//		MHL7MessageConf.CONFTYPE_InstitutionalClaim, null, Constantes.CLAIM_MISSINGPRICES ).size();
	    		
	    		if ((MEXMECtaPacExt.CONFTYPE_InstitutionalClaim.equals(confType) && vExt.getpendingInst()>0) 
	    				|| (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType) 
	    						&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_InstitutionalClaim) ? vExt.getpendingInst() : 0)>0 )
	    			) {
	    			ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorPricesInZero);
					if (!ctaExt.save(trxName)) {
						log.log(Level.SEVERE, "Error saving Institional Prices in Zero");
						success = Boolean.FALSE;
						break;
					} else {
						continue;
					}
	    		}
	    	}
	    	
	    	if (MEXMECtaPacExt.CONFTYPE_ProfessionalClaim.equals(confType) 
	    			|| MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType)) {
	    		
	    		//MISSING DATA - NO INSURANCE COVERAGE Prof
	    		//final int mfCoverageProf = MEXMEPacienteAseg.getByCtaPacSupport(Env.getCtx(), ctaExt.getEXME_CtaPac_ID(), 
				//		MEXMEPacienteAseg.SUPPORTBILLING_Professional);
	    		
	    		if ((MEXMECtaPacExt.CONFTYPE_ProfessionalClaim.equals(confType) 
	    				&& vExt.getmfCoverageProf()==0 && !ctaExt.getCtaPac().isNoInsuranceCoverage()) 
	    			|| (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType) 
	    					&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_ProfessionalClaim) ? vExt.getmfCoverageProf() : 1)==0
	    					&& !ctaExt.getCtaPac().isNoInsuranceCoverage()) 
	    			) {
	    			ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
					if (!(ctaExt.save(trxName)
						  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_ProfessionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
								  getMessage(Env.getCtx(), null, "billing.exception.prof.missingCoverage"), trxName))) {
						log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "billing.exception.prof.missingCoverage"));
						success = Boolean.FALSE;
						break;
					} else {
						continue;
					}
	    		}
	    		
	    		// Missing Charges (Cuenta sin cargos Profesional)
	    		//final int mfChargesProf = MCtaPacDet.getCargosByClaim(Env.getCtx(), ctaExt.getEXME_CtaPacExt_ID(), 
				//		MHL7MessageConf.CONFTYPE_ProfessionalClaim, null, Constantes.CLAIM_MISSINGCHARGES).size();
	    		
	    		if ((MEXMECtaPacExt.CONFTYPE_ProfessionalClaim.equals(confType) && vExt.getmfChargesProf()==0) 
	    				|| (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType) 
	    						&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_ProfessionalClaim) ? vExt.getmfChargesProf() : 1)==0 )
	    			) {
	    			ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
					if (!(ctaExt.save(trxName)
						  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_ProfessionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
								  getMessage(Env.getCtx(), null, "billing.exception.prof.missingCharges"), trxName))) {
						log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "billing.exception.prof.missingCharges"));
						success = Boolean.FALSE;
						break;
					} else {
						continue;
					}
	    		}
	    		
	    		// Incomplete Service (Servicios pendientes de completar)
	    		//final int incompleteProf= MEXMEActPacienteIndH.getCount(Env.getCtx(), ctaExt.getEXME_CtaPac_ID(), 0, null, 
				//		MEXMEActPacienteInd.ESTATUS_RequestedService, MHL7MessageConf.CONFTYPE_ProfessionalClaim, null);
	    		
	    		if ((MEXMECtaPacExt.CONFTYPE_ProfessionalClaim.equals(confType) && vExt.getincompleteProf()>0) 
	    				|| (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType) 
	    						&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_ProfessionalClaim) ? vExt.getincompleteProf() : 0)>0)
	    			) {
	    			ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorOrdersIncomplete);
					if (!ctaExt.save(trxName)) {
						log.log(Level.SEVERE, "Error Saving Orders Professional IncompleteStatus");
						success = Boolean.FALSE;
						break;
					} else {
						continue;
					}
	    		}
	    		
	    		// MISSING DATA - CHARGES WHITOUT REV CODE
	    		//final int mfRevCodeProf = MCtaPacDet.getCargosByClaim(Env.getCtx(), ctaExt.getEXME_CtaPacExt_ID(), 
				//		MHL7MessageConf.CONFTYPE_ProfessionalClaim, null, Constantes.CLAIM_MISSINGREVCODE).size();
	    		
	    		if ((MEXMECtaPacExt.CONFTYPE_ProfessionalClaim.equals(confType) && vExt.getmfRevCodeProf()>0) 
	    				|| (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType) 
	    						&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_ProfessionalClaim) ? vExt.getmfRevCodeProf() : 0)>0)
	    			) {
	    			ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
					if (!(ctaExt.save(trxName)
						  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_ProfessionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
								  getMessage(Env.getCtx(), null, "billing.exception.prof.missingRevCode"), trxName))) {
						log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "billing.exception.prof.missingRevCode"));
						success = Boolean.FALSE;
						break;
					} else {
						continue;
					}
	    		}
	    		
	    		// Missing Prices (Precios Pendientes ó 0 sin validar)
	    		//final int pendingProf = MCtaPacDet.getCargosByClaim(Env.getCtx(), ctaExt.getEXME_CtaPacExt_ID(), 
				//		MHL7MessageConf.CONFTYPE_ProfessionalClaim, null, Constantes.CLAIM_MISSINGPRICES).size();
	    		
	    		if ((MEXMECtaPacExt.CONFTYPE_ProfessionalClaim.equals(confType) && vExt.getpendingProf()>0) 
	    				|| (MEXMECtaPacExt.CONFTYPE_PIStatement.equals(confType) 
	    						&& (isValidStatement(ctaExt, MHL7MessageConf.CONFTYPE_ProfessionalClaim) ? vExt.getpendingProf() : 0)>0)
	    			) {
	    			ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorPricesInZero);
					if (!ctaExt.save(trxName)) {
						log.log(Level.SEVERE, "Error saving Professional Prices in Zero");
						success = Boolean.FALSE;
						break;
					} else {
						continue;
					}
	    		}
	    	}
				
			//MISSING DATA - CANNOT DEFINE C_BPARTNER FROM THE STEP DEFINED
			if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_ProfessionalClaim)
						&& MEXMEPacienteAseg.getBillingInsurance(Env.getCtx(), ctaExt, confType) == null) {
				ctaExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
				if (!(ctaExt.save(trxName)
						  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_ProfessionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
								  getMessage(Env.getCtx(), null, "billing.exception.prof.insuranceNotDefined"), trxName))) {
						log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "billing.exception.prof.insuranceNotDefined"));
						success = Boolean.FALSE;
						break;
				}
			} else if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_InstitutionalClaim)
						&& MEXMEPacienteAseg.getBillingInsurance(Env.getCtx(), ctaExt, confType) == null) {
				ctaExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
				if (!(ctaExt.save(trxName)
						  && MEXMEClaimError.createError(Env.getCtx(),MHL7MessageConf.CONFTYPE_InstitutionalClaim, MEXMEClaimError.TYPE_MandatoryFields, ctaExt.getEXME_CtaPac_ID(), 
								  getMessage(Env.getCtx(), null, "billing.exception.inst.insuranceNotDefined"), trxName))) {
						log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "billing.exception.inst.insuranceNotDefined"));
						success = Boolean.FALSE;
						break;
				}
			} else {
				lstCtaPacExt.add(ctaExt);
			}
	    }
	    //log.log(Level.SEVERE, "Validaciones realizadas - > "+DB.convert(Env.getCtx(), new Date()).toString());
	    
	    return success;
	}
	
	public String createNewBatchs(String confType) {
		Trx mtrx = null;
		boolean success = Boolean.TRUE;
		//log.log(Level.SEVERE, "Inicio - > "+DB.convert(Env.getCtx(), new Date()).toString());
		totalBatch = 0;
		totalDetail = 0;
		MEXMEMejoras improv = MEXMEMejoras.get(Env.getCtx());
		int totalDetails = 0;
		if (improv != null) {
			if (MEXMECtaPacExt.CONFTYPE_PIStatement.equalsIgnoreCase(confType)) {
				totalDetails = improv.getMaxStmtBatch();
			} else {
				totalDetails = improv.getMaxClaimsBatch();
			}
		}
		
		if (totalDetails == 0) {
			if (MEXMECtaPacExt.CONFTYPE_PIStatement.equalsIgnoreCase(confType)) {
				totalDetails = 250;
			} else {
				totalDetails = 500;
			}
		}
		
		if (totalDetails > 0) {
			// Obtener lista de extensiones
			mtrx = Trx.get(Trx.createTrxName("BatchProcess"), true);
			try{
				success = getDetailList(confType, mtrx.getTrxName());
				if (lstCtaPacExt.size()>0 && success) {
					int fromIndex = 0;
					
					while (fromIndex < lstCtaPacExt.size() && success) {
						if (createNewBatchsList(confType, 
								                lstCtaPacExt.subList(fromIndex, 
								                					(fromIndex+totalDetails > lstCtaPacExt.size() ? lstCtaPacExt.size() : fromIndex+totalDetails)), 
								                					mtrx.getTrxName())) {
							fromIndex = fromIndex + totalDetails;
							//log.log(Level.SEVERE, "Detalles creados: "+fromIndex+" - > "+DB.convert(Env.getCtx(), new Date()).toString());
							success = Boolean.TRUE;
						} else {
							success = Boolean.FALSE;
							break;
						}
					}
				}
			}catch (Exception e){
				success = false;
				log.log(Level.SEVERE, "Fallo general Proceso BatchProcess", e);
			}finally{
				if (success){
					Trx.commit(mtrx);
				}else{
					Trx.rollback(mtrx);
					log.log(Level.SEVERE, "BatchClaimGenerator - > Create");
				}
				Trx.close(mtrx);
			}
		}
		
		String message = "";
		if (success && totalDetail>0){
			message = getMessageArgs(Env.getCtx(), null, "billing.batch.success", totalDetail) + (totalDetail >1 ? "s" : ""); 
		}else{
			message = getMessageArgs(Env.getCtx(), null, "billing.batch.success.noAccounts");
		}
		return (success ? message : getMessage(Env.getCtx(), null, "billing.batch.error.creation"));
	}
	
	private boolean createNewBatchsList(String confType, List<MEXMECtaPacExt> lstCtaPacExt, String trxName) {
		boolean success = Boolean.TRUE;
		int cont = 0;
		X_EXME_BatchClaimH batchH = null;
		try{
			if (lstCtaPacExt.size()>0){
				batchH = new X_EXME_BatchClaimH(Env.getCtx(), 0, null);
				batchH.setDocumentNo(DB.getDocumentNo(Env.getAD_Client_ID(Env.getCtx()), X_EXME_BatchClaimH.Table_Name, trxName));
				batchH.setIsGenerated(false);
				batchH.setHL7_Dashboard_ID(0);
				batchH.setConfType(confType);
	
				if (batchH.save(trxName)) {
					for (MEXMECtaPacExt ctaExt : lstCtaPacExt) {
						int idCtaPacExt = 0;
						int idAddCtaPacExt = 0;
						// Get Payer
						int cBPartnerID= 0;
						if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_PIStatement)){
							cBPartnerID = ctaExt.getCtaPac().getPaciente().getC_BPartner_ID();
						}else{
							cBPartnerID = MEXMEPacienteAseg.getBillingInsurance(Env.getCtx(), ctaExt, confType).getC_BPartner_ID();
						}
						
						// Crear nueva extension
						if (ctaExt.getExtensionNo() == 0 && !MHL7MessageConf.CONFTYPE_PIStatement.equals(confType)) {
							MEXMECtaPacExt newCtaExt = new MEXMECtaPacExt(ctaExt.getCtaPac(), trxName);
							if (MHL7MessageConf.CONFTYPE_ProfessionalClaim.equalsIgnoreCase(confType)) {
								newCtaExt.setInstitutionalStatus(null);
								newCtaExt.setInstitutionalStep(null);
							} else if (MHL7MessageConf.CONFTYPE_InstitutionalClaim.equalsIgnoreCase(confType)) {
								newCtaExt.setProfessionalStatus(null);
								newCtaExt.setProfessionalStep(null);
							}
							newCtaExt.setConfType(confType);
							newCtaExt.setChargeStatus(MEXMECtaPacExt.CHARGESTATUS_ForBill);
							if (!newCtaExt.save(trxName)) {
								success = Boolean.FALSE;
								break;
							}
							//Asigna cargos a nueva extension
							if (!MCtaPacDet.moveToExtension(Env.getCtx(), newCtaExt, confType, trxName)) {
									success = Boolean.FALSE;
									break;
							}
							
							if (MCtaPacDet.getClaimTypeByCharges(Env.getCtx(), " AND CPD.EXME_CTAPAC_ID = ? AND CPE.EXTENSIONNO = 0 ", null, newCtaExt.getEXME_CtaPac_ID()).size() == 0
									&& ctaExt.getChargeStatus().equalsIgnoreCase(MEXMECtaPacExt.CHARGESTATUS_LateCharge)) {
								ctaExt.setChargeStatus(MEXMECtaPacExt.CHARGESTATUS_Complete);
								if (!ctaExt.save(trxName)) {
									success = Boolean.FALSE;
									break;
								}
							}
								
							//Asignar cargos a extension adicional en caso de requerirse
							MEXMECtaPacExt addCtaExt = null;
							BigDecimal totalLines = BigDecimal.ZERO;
							for (MCtaPacDet det : MCtaPacDet.getCargosByExt(Env.getCtx(), newCtaExt.getEXME_CtaPacExt_ID(), null)) {
								if (MCtaPacDet.BILLINGTYPE_AdditionalClaim.equalsIgnoreCase(det.getBillingType())) {
									if (addCtaExt == null) {
										addCtaExt = new MEXMECtaPacExt(ctaExt.getCtaPac(), trxName);
										if (MHL7MessageConf.CONFTYPE_ProfessionalClaim.equalsIgnoreCase(confType)) {
											addCtaExt.setInstitutionalStatus(null);
											addCtaExt.setInstitutionalStep(null);
										} else if (MHL7MessageConf.CONFTYPE_InstitutionalClaim.equalsIgnoreCase(confType)) {
											addCtaExt.setProfessionalStatus(null);
											addCtaExt.setProfessionalStep(null);
										}
										addCtaExt.setConfType(confType);
										addCtaExt.setChargeStatus(MEXMECtaPacExt.CHARGESTATUS_ForBill);
										if (!addCtaExt.save(trxName)) {
								        	success = Boolean.FALSE;
											break;
								        }
									}
									det.setEXME_CtaPacExt_ID(addCtaExt.getEXME_CtaPacExt_ID());
									if (!det.save(trxName)) {
										success = Boolean.FALSE;
										break;
									}
									totalLines = totalLines.add(det.getLineNetAmt());
								}
							}
							if (success) {
								if (addCtaExt != null) {
									addCtaExt.setTotalLines(totalLines);
							        addCtaExt.setGrandTotal(totalLines);
							        newCtaExt.setTotalLines(newCtaExt.getTotalLines().subtract(totalLines));
							        newCtaExt.setGrandTotal(newCtaExt.getGrandTotal().subtract(totalLines));
							        if (!newCtaExt.save(trxName)) {
							        	success = Boolean.FALSE;
										break;
							        }
							        if (!addCtaExt.save(trxName)) {
							        	success = Boolean.FALSE;
										break;
							        }
							        idAddCtaPacExt = addCtaExt.getEXME_CtaPacExt_ID();
								}
							} else {
								break;
							}
									
							idCtaPacExt = newCtaExt.getEXME_CtaPacExt_ID();
						} else if (MHL7MessageConf.CONFTYPE_PIStatement.equals(confType)) { // Crear nuevas extensiones para Statement
							
							MEXMECtaPacExt ctaExtZero = MEXMECtaPacExt.getExtCero(Env.getCtx(), ctaExt.getEXME_CtaPac_ID(), null);
							idCtaPacExt = generaExtStmt(ctaExtZero, MEXMECtaPacExt.CONFTYPE_InstitutionalClaim, trxName);
							
							if (idCtaPacExt == -1) {
								success = Boolean.FALSE;
								break;
							}
							
							int tempID = generaExtStmt(ctaExtZero, MEXMECtaPacExt.CONFTYPE_ProfessionalClaim, trxName);
							
							if (tempID == -1) {
								success = Boolean.FALSE;
								break;
							}
							
							if (tempID > 0) {
								idCtaPacExt = tempID;
							}
							
							if (idCtaPacExt == 0) {
								idCtaPacExt = ctaExt.getEXME_CtaPacExt_ID();
							}
						}else {
							idCtaPacExt = ctaExt.getEXME_CtaPacExt_ID();
						}
													
						MEXMEBatchClaimD batchD = new MEXMEBatchClaimD(Env.getCtx(), 0, null);
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
							if (!(ctaExt.getCtaPac().save(trxName))) {
								log.log(Level.SEVERE, "Ocurrio un error al actualizar status " +
										"por Generacion d Statements. encounter:"+ctaExt.getCtaPac().getEXME_CtaPac_ID());
								success = Boolean.FALSE;
								break;
							}
						}
							
						if (!batchD.save(trxName)) {
							success = Boolean.FALSE;
							break;
						}else{
							cont++;
							totalDetail++;
							// Agrega detalle de batch adicional
							if (idAddCtaPacExt > 0) {
								MEXMEBatchClaimD batchDAdd = new MEXMEBatchClaimD(Env.getCtx(), 0, null);
								batchD.setEXME_BatchClaimH_ID(batchH.getEXME_BatchClaimH_ID());
								batchD.setEXME_CtaPac_ID(ctaExt.getEXME_CtaPac_ID());
								batchD.setEXME_CtaPacExt_ID(idAddCtaPacExt);
								
								if (cBPartnerID > 0 ) {
									batchD.setC_BPartner_ID(cBPartnerID);
								}
								if (!batchDAdd.save(trxName)) {
									success = Boolean.FALSE;
								} else {
									cont++;
									totalDetail++;
								}
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
						batchH.save(trxName);
					}
				} else {
					totalBatch++;
				}
			}
			
		}catch(Exception e){
			log.log(Level.SEVERE, e.getMessage(), e);
			success = Boolean.FALSE;
		}finally {
			batchH = null;
		}
		return success;
	}
	
	private boolean isValidStatement(MEXMECtaPacExt ctaExt, String confType){
		if (MHL7MessageConf.CONFTYPE_InstitutionalClaim.equalsIgnoreCase(confType) && (confType.equalsIgnoreCase(ctaExt.getConfType()) || ctaExt.getConfType() == null) ){
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
		}else if (MHL7MessageConf.CONFTYPE_ProfessionalClaim.equalsIgnoreCase(confType) && (confType.equalsIgnoreCase(ctaExt.getConfType()) || ctaExt.getConfType() == null) ){
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
	
	// Generacion de Extension para Statement
	private int generaExtStmt(MEXMECtaPacExt ext, String confType, String trxName) {
		if (isValidStatement(ext, confType)
				&& MEXMECtaPacExt.getExtCtas(Env.getCtx(), " AND EXME_CtaPac_ID = ? AND ConfType = ? AND ExtensionNo > 0 ", null, 
															ext.getEXME_CtaPac_ID(), confType).size() == 0) {
			MEXMECtaPacExt newExt = new MEXMECtaPacExt(ext.getCtaPac(), trxName);
			if (confType.equalsIgnoreCase(MEXMECtaPacExt.CONFTYPE_InstitutionalClaim)) {
				newExt.setProfessionalStatus(null);
				newExt.setProfessionalStep(null);
				newExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_SelfPay);
				newExt.setInstitutionalStep(MEXMECtaPacExt.INSTITUTIONALSTEP_SelftPay);
			} else if (confType.equalsIgnoreCase(MEXMECtaPacExt.CONFTYPE_ProfessionalClaim)) {
				newExt.setInstitutionalStatus(null);
				newExt.setInstitutionalStep(null);
				newExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_SelfPay);
				newExt.setProfessionalStep(MEXMECtaPacExt.PROFESSIONALSTEP_SelftPay);
			}
			newExt.setConfType(confType);
			if (newExt.save(trxName)) {
				//Asigna cargos a nueva extension
				return MCtaPacDet.moveToExtension(Env.getCtx(), newExt, confType, trxName) ? newExt.getEXME_CtaPacExt_ID() : -1;
			} else {
				return -1;
			}
		}
		return 0;
	}
	
	// Generacion de Facturas a Paciente por primera vez
	private List <MInvoice> generaInvoice(Properties ctx, int EXME_BatchClaimH_ID) {
		List <MInvoice> lstInvoice = new ArrayList <MInvoice>();
		MEXMEBatchClaimD[] listaD = MEXMEBatchClaimD.gets(Env.getCtx(), EXME_BatchClaimH_ID, null);
		Billing bill = new Billing();
		for (int i = 0; i < listaD.length; i++) {
			MEXMECtaPac ctaPac = new MEXMECtaPac(Env.getCtx(), listaD[i].getEXME_CtaPac_ID(), null);
			MInvoice invoice = bill.process(Env.getCtx(), ctaPac.getLstDetalle(null), ctaPac.getPaciente().getC_BPartner_ID());
			lstInvoice.add(invoice);
		}
		return lstInvoice;
	}
	
	// Generacion de log de envio de statement para paciente
	private void generaLogStmt(int EXME_BatchClaimH_ID, List<MInvoice> lstInvoice) {
		GeneraClaimLog claimLog = new GeneraClaimLog();
		try {
			claimLog.generaLog(Env.getCtx(), EXME_BatchClaimH_ID, lstInvoice);
		} catch (Exception e) {
			log.log(Level.SEVERE, genLogStmt);
		}
	}
	
	private boolean generaClaim(String confType, MEXMECtaPacExt ext, String trxName) {
		//MEXMECtaPac ctaPac = ext.getCtaPac();
		boolean success = Boolean.TRUE;
		if (MHL7MessageConf.CONFTYPE_InstitutionalClaim.equalsIgnoreCase(confType)) {
			if (!MEXMEClaimMessage.generateMessage(Env.getCtx(), ext, 
					MHL7MessageConf.CONFTYPE_InstitutionalClaim, trxName)) {
				success = Boolean.FALSE;
				log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "claim.error.CreatingClaim") 
						+ " Ext Encounter Id: " + ext.getEXME_CtaPacExt_ID());								
			}
			/*
			if (ctaPac.getTipoPaciente().getBillType().getCode() == 3) {
				if (!MEXMEClaimMessage.generateMessage(Env.getCtx(), ext, 
						MHL7MessageConf.CONFTYPE_OutpatientInstitutionalClaim, trxName)) {
					success = Boolean.FALSE;
					log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "claim.error.CreatingReportClaim") 
							+ " Ext Encounter Id: " + ext.getEXME_CtaPacExt_ID());								
				}
			}
			if (ctaPac.getTipoPaciente().getBillType().getCode() == 1) {
				if (!MEXMEClaimMessage.generateMessage(Env.getCtx(), ext, 
						MHL7MessageConf.CONFTYPE_InpatientClaim, trxName)) {
					success = Boolean.FALSE;
					log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "claim.error.CreatingReportClaim") 
							+ " Ext Encounter Id: " + ext.getEXME_CtaPacExt_ID());
				}
			}
			*/
		} else if (MHL7MessageConf.CONFTYPE_ProfessionalClaim.equalsIgnoreCase(confType)) {
			if (!MEXMEClaimMessage.generateMessage(Env.getCtx(), ext, 
					MHL7MessageConf.CONFTYPE_ProfessionalClaim, trxName)) {
				success = Boolean.FALSE;
				log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "claim.error.CreatingClaim") 
						+ " Encounter Id: " + ext.getEXME_CtaPacExt_ID());
			}
			/*
			if (ctaPac.getTipoPaciente().getBillType().getCode() == 3) {
				if (!MEXMEClaimMessage.generateMessage(Env.getCtx(), ext, 
						MHL7MessageConf.CONFTYPE_OutpatientProfessionalClaim, trxName)) {
					success = Boolean.FALSE;
					log.log(Level.SEVERE, getMessage(Env.getCtx(), null, "claim.error.CreatingReportClaim") 
							+ " Encounter Id: " + ext.getEXME_CtaPacExt_ID());
				}
			}
			*/
		}
		return success;
	}
}
