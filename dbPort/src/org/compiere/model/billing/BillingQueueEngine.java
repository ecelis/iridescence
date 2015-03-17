package org.compiere.model.billing;

import java.util.Date;
import java.util.List;

import org.compiere.model.MActPacienteDiag;
import org.compiere.model.MBPartner;
import org.compiere.model.MColumn;
import org.compiere.model.MEXMEActPaciente;
import org.compiere.model.MEXMEBillingRule;
import org.compiere.model.MEXMEBillingRuleDet;
import org.compiere.model.MEXMEClaimCodes;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MRefList;
import org.compiere.model.MTable;
import org.compiere.util.Env;

/**
 * 
 * @author rsolorzano
 * 
 */
public class BillingQueueEngine {
	
	

	
	/**
	 * Forma el query de la consulta que se necesita para saber si la cuenta paciente es elegible para una excepcion.
	 * Toma en cuenta los detalles de las reglas que cuenten con los tipos de filtro de Paciente, estacion de servicio
	 * y aseguradora.
	 * @param rule
	 * @return
	 */
	public static StringBuilder getSearchEncounterQuery(final MEXMEBillingRule rule) {

		final StringBuilder selectStr = new StringBuilder();
		final StringBuilder queryStr = new StringBuilder();
		final List<MEXMEBillingRuleDet> listDet = MEXMEBillingRuleDet.getAllByID(Env.getCtx(), rule.getEXME_BillingRule_ID());

		if (listDet != null && !listDet.isEmpty()) {

			final StringBuilder joinStr = new StringBuilder();

			selectStr.append("SELECT EXME_CTAPAC.* FROM EXME_CTAPAC ");

			for (MEXMEBillingRuleDet ruleDet : listDet) {

				// CASO 1: EXME_TIPOPACIENTE o CASO 2: EXME_ESTSERV
				if (ruleDet.getEXME_BillingFilter().getAD_TableEngine_ID() == MEXMECtaPac.Table_ID) {

					joinStr.append(BillingQueueEngine.getJoinToCtaPacQuery(ruleDet));

				} else {
					// CASO 3: C_BPARTNER

					if (ruleDet.getEXME_BillingFilter().getAD_Table_ID() == MBPartner.Table_ID) {

						joinStr.append(BillingQueueEngine.getJoinCBPartnerQuery(ruleDet));

					}

				}

			} // for ruleDet

			queryStr.append(selectStr);
			queryStr.append(" ");
			queryStr.append(joinStr);
			queryStr.append(" WHERE EXME_CTAPAC.ISACTIVE = 'Y' AND EXME_CTAPAC.VALIDONQUEUE = 'N'");
			queryStr.append(" AND EXME_CTAPAC.AD_CLIENT_ID = ? AND EXME_CTAPAC.AD_ORG_ID = ? ");
			queryStr.append(" AND EXME_CTAPAC.EXME_CTAPAC_ID = ?  ");

		} // if listDet not empty

		return queryStr;
	}
	
	
	/**
	 * Regresa la cadena necesaria para complementar el query cuando el detalle de la regla tenga almacenado algun operador
	 * de include y exclude, asi como su correspondiente valor.
	 * @param ruleDet
	 * @return
	 */
	public static StringBuilder getIncludeExcludeQuery(final MEXMEBillingRuleDet ruleDet) {

		final StringBuilder ieQuery = new StringBuilder();

		if (MEXMEBillingRuleDet.IEOPERATOR_EX.equals(ruleDet.getIEOperator())) {
			ieQuery.append(" AND UPPER(");
			ieQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
			ieQuery.append(".");
			ieQuery.append(ruleDet.getEXME_BillingFilter().getAD_Column().getColumnName());
			ieQuery.append(" || ' - ' || ");
			ieQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
			ieQuery.append(".");
			ieQuery.append(MColumn.get(Env.getCtx(), ruleDet.getEXME_BillingFilter().getDisplayColumn_ID()).getColumnName());
			ieQuery.append(" ) NOT LIKE UPPER('%");
			ieQuery.append(ruleDet.getIEValue());
			ieQuery.append("%')");
		}

		if (MEXMEBillingRuleDet.IEOPERATOR_IN.equals(ruleDet.getIEOperator())) {
			ieQuery.append(" AND UPPER(");
			ieQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
			ieQuery.append(".");
			ieQuery.append(ruleDet.getEXME_BillingFilter().getAD_Column().getColumnName());
			ieQuery.append(" || ' - ' || ");
			ieQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
			ieQuery.append(".");
			ieQuery.append(MColumn.get(Env.getCtx(), ruleDet.getEXME_BillingFilter().getDisplayColumn_ID()).getColumnName());
			ieQuery.append(" ) LIKE UPPER('%");
			ieQuery.append(ruleDet.getIEValue());
			ieQuery.append("%')");
		}

		return ieQuery;

	}

	/**
	 * Regresa la cadena necesaria para complementar el query para buscar sobre el rango de valores almacenados en el detalle
	 * de la regla.
	 * @param ruleDet
	 * @return
	 */
	public static StringBuilder getBetweenQuery(final MEXMEBillingRuleDet ruleDet) {

		final StringBuilder betweenQuery = new StringBuilder();

		betweenQuery.append(" AND UPPER (");
		betweenQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		betweenQuery.append(".");
		betweenQuery.append(ruleDet.getEXME_BillingFilter().getAD_Column().getColumnName());
		betweenQuery.append(" || ' - ' || ");
		betweenQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		betweenQuery.append(".");
		betweenQuery.append(MColumn.get(Env.getCtx(), ruleDet.getEXME_BillingFilter().getDisplayColumn_ID()).getColumnName());
		betweenQuery.append(" ) BETWEEN UPPER('");
		betweenQuery.append(ruleDet.getValorIni());
		betweenQuery.append("') AND UPPER('");
		betweenQuery.append(ruleDet.getValorFin() == null ? ruleDet.getValorIni() : ruleDet.getValorFin());
		betweenQuery.append("')");

		return betweenQuery;

	}

	/**
	 * Regresa la cadena necesaria para complementar el query cuando este necesite hacer un join
	 * a la tabla de cuentas pacientes
	 * @param ruleDet
	 * @return
	 */
	public static StringBuilder getJoinToCtaPacQuery(final MEXMEBillingRuleDet ruleDet) {

		final StringBuilder joinCtaQuery = new StringBuilder();

		joinCtaQuery.append(" INNER JOIN ");
		joinCtaQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		joinCtaQuery.append(" ON (");
		joinCtaQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		joinCtaQuery.append(".");
		joinCtaQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		joinCtaQuery.append("_ID = ");
		joinCtaQuery.append(MEXMECtaPac.Table_Name);
		joinCtaQuery.append(".");
		joinCtaQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		joinCtaQuery.append("_ID ");
		joinCtaQuery.append(BillingQueueEngine.getBetweenQuery(ruleDet));

		joinCtaQuery.append(BillingQueueEngine.getIncludeExcludeQuery(ruleDet));

		joinCtaQuery.append(" )");

		return joinCtaQuery;

	}

	/**
	 * Regresa la cadena necesaria para complementar el query cuando este necesite hacer un join
	 * a la tabla de aseguradoras
	 * @param ruleDet
	 * @return
	 */
	public static StringBuilder getJoinCBPartnerQuery(final MEXMEBillingRuleDet ruleDet) {

		final StringBuilder joinPartnerQuery = new StringBuilder();

		final MTable tableEngine = MTable.get(Env.getCtx(), ruleDet.getEXME_BillingFilter().getAD_TableEngine_ID());

		joinPartnerQuery.append(" INNER JOIN ");
		joinPartnerQuery.append(tableEngine.getTableName());
		joinPartnerQuery.append(" ON (");
		joinPartnerQuery.append(tableEngine.getTableName());
		joinPartnerQuery.append(".");
		joinPartnerQuery.append(MEXMECtaPac.COLUMNNAME_EXME_CtaPac_ID);
		joinPartnerQuery.append(" = ");
		joinPartnerQuery.append(MEXMECtaPac.Table_Name);
		joinPartnerQuery.append(".");
		joinPartnerQuery.append(MEXMECtaPac.COLUMNNAME_EXME_CtaPac_ID);

		joinPartnerQuery.append(" INNER JOIN ");
		joinPartnerQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		joinPartnerQuery.append(" ON (");
		joinPartnerQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		joinPartnerQuery.append(".");
		joinPartnerQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		joinPartnerQuery.append("_ID = ");
		joinPartnerQuery.append(tableEngine.getTableName());
		joinPartnerQuery.append(".");
		joinPartnerQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		joinPartnerQuery.append("_ID ");
		joinPartnerQuery.append(BillingQueueEngine.getBetweenQuery(ruleDet));

		joinPartnerQuery.append(BillingQueueEngine.getIncludeExcludeQuery(ruleDet));

		joinPartnerQuery.append(" )");

		return joinPartnerQuery;

	}

	/**
	 * Regresa la cadena necesaria para complementar el query cuando este necesite hacer un join
	 * a la tabla de diagnosticos
	 * @param ruleDet
	 * @return
	 */
	public static StringBuilder getJoinDiagQuery(final MEXMEBillingRuleDet ruleDet) {

		final StringBuilder joinDiagQuery = new StringBuilder();

		final MTable tableEngine = MTable.get(Env.getCtx(), ruleDet.getEXME_BillingFilter().getAD_TableEngine_ID());

		joinDiagQuery.append(" INNER JOIN ");
		joinDiagQuery.append(MEXMEActPaciente.Table_Name);
		joinDiagQuery.append(" ON (");
		joinDiagQuery.append(MEXMEActPaciente.Table_Name);
		joinDiagQuery.append(".");
		joinDiagQuery.append(MEXMECtaPac.COLUMNNAME_EXME_CtaPac_ID);
		joinDiagQuery.append(" = ");
		joinDiagQuery.append(MEXMECtaPac.Table_Name);
		joinDiagQuery.append(".");
		joinDiagQuery.append(MEXMECtaPac.COLUMNNAME_EXME_CtaPac_ID);
		joinDiagQuery.append(" )");

		joinDiagQuery.append(" INNER JOIN ");
		joinDiagQuery.append(tableEngine.getTableName());
		joinDiagQuery.append(" ON (");
		joinDiagQuery.append(tableEngine.getTableName());
		joinDiagQuery.append(".");
		joinDiagQuery.append(MActPacienteDiag.COLUMNNAME_EXME_ActPaciente_ID);
		joinDiagQuery.append(" = ");
		joinDiagQuery.append(MEXMEActPaciente.Table_Name);
		joinDiagQuery.append(".");
		joinDiagQuery.append(MEXMEActPaciente.COLUMNNAME_EXME_ActPaciente_ID);
		joinDiagQuery.append(" )");

		joinDiagQuery.append(" INNER JOIN ");
		joinDiagQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		joinDiagQuery.append(" ON (");
		joinDiagQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		joinDiagQuery.append(".");
		joinDiagQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		joinDiagQuery.append("_ID = ");
		joinDiagQuery.append(tableEngine.getTableName());
		joinDiagQuery.append(".");
		joinDiagQuery.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		joinDiagQuery.append("_ID ");
		joinDiagQuery.append(BillingQueueEngine.getBetweenQuery(ruleDet));

		joinDiagQuery.append(BillingQueueEngine.getIncludeExcludeQuery(ruleDet));

		joinDiagQuery.append(" )");

		return joinDiagQuery;

	}

	
	/**
	 * Forma el query de la consulta que se necesita para saber si la cuenta paciente (cuando ya es elegible para una excepcion)
	 * contiene los codigos configurados en el detalle de la regla, toma en cuenta los detalles de las reglas que cuenten 
	 * con el tipos de filtro de Revenue Code.
	 * @param ruleDet
	 * @return
	 */
	public static StringBuilder getRevCodesQuery(final MEXMEBillingRuleDet ruleDet) {

		final StringBuilder queryStr = new StringBuilder();
		final MTable tableEngine = MTable.get(Env.getCtx(), ruleDet.getEXME_BillingFilter().getAD_TableEngine_ID());
		
		if (MEXMEBillingRuleDet.OPERATION_NA.equals(ruleDet.getOperation())){
			ruleDet.setOperation(MEXMEBillingRuleDet.OPERATION_CT);
			ruleDet.setParameterValue(MEXMEBillingRuleDet.PARAMETERVALUE_VA);
			ruleDet.setComparisonValue(MEXMEBillingRuleDet.COMPARISONVALUE_EQ);
			ruleDet.setValue("0");
		}

		queryStr.append("SELECT ");
		
		final String valueOp = MRefList.getNameByReferenceID(null, MEXMEBillingRuleDet.OPERATION_AD_Reference_ID, ruleDet.getOperation());
		queryStr.append(valueOp);
		queryStr.append("(*) ");
		
		queryStr.append(" FROM ");
		queryStr.append(tableEngine.getTableName());
		queryStr.append(" INNER JOIN ");
		queryStr.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		queryStr.append(" ON (");
		queryStr.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		queryStr.append(".");
		queryStr.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		queryStr.append("_ID = ");
		queryStr.append(tableEngine.getTableName());
		queryStr.append(".");
		queryStr.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		queryStr.append("_ID  ");
		queryStr.append(BillingQueueEngine.getBetweenQuery(ruleDet));
		queryStr.append(")");

		queryStr.append(" WHERE ");
		queryStr.append(tableEngine.getTableName());
		queryStr.append(".");
		queryStr.append(MEXMECtaPac.COLUMNNAME_EXME_CtaPac_ID);
		queryStr.append(" = ? ");

		return queryStr;
	}
	
	
	/**
	 * Forma el query de la consulta que se necesita para saber si la cuenta paciente (cuando ya es elegible para una excepcion)
	 * contiene los codigos configurados en el detalle de la regla, toma en cuenta los detalles de las reglas que cuenten 
	 * con el tipos de filtro de Ocurrence Code, Value Code, Span Code. 
	 * @param ruleDet
	 * @return
	 */
	public static StringBuilder getCodesQuery(final MEXMEBillingRuleDet ruleDet) {

		final StringBuilder queryStr = new StringBuilder();
		final MTable tableEngine = MTable.get(Env.getCtx(), ruleDet.getEXME_BillingFilter().getAD_TableEngine_ID());

		if (MEXMEBillingRuleDet.OPERATION_NA.equals(ruleDet.getOperation())){
			ruleDet.setOperation(MEXMEBillingRuleDet.OPERATION_CT);
			ruleDet.setParameterValue(MEXMEBillingRuleDet.PARAMETERVALUE_VA);
			ruleDet.setComparisonValue(MEXMEBillingRuleDet.COMPARISONVALUE_EQ);
			ruleDet.setValue("0");
		}
		
		queryStr.append("SELECT ");
	
		final String valueOp = MRefList.getListName(Env.getCtx(), MEXMEBillingRuleDet.OPERATION_AD_Reference_ID, ruleDet.getOperation());
		queryStr.append(valueOp);
		queryStr.append("(*) ");
		

		queryStr.append(" FROM ");
		queryStr.append(tableEngine.getTableName());
		queryStr.append(" INNER JOIN ");
		queryStr.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		queryStr.append(" ON (");
		queryStr.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		queryStr.append(".");
		queryStr.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		queryStr.append("_ID");
		queryStr.append(" = ");
		queryStr.append(tableEngine.getTableName());
		queryStr.append(".");
		queryStr.append(MEXMEClaimCodes.COLUMNNAME_Record_ID);
		queryStr.append(BillingQueueEngine.getBetweenQuery(ruleDet));
		queryStr.append(")");
		queryStr.append(" WHERE ");
		queryStr.append(tableEngine.getTableName());
		queryStr.append(".");
		queryStr.append(MEXMECtaPac.COLUMNNAME_EXME_CtaPac_ID);
		queryStr.append(" = ? AND ");
		queryStr.append(tableEngine.getTableName());
		queryStr.append(".");
		queryStr.append(MTable.COLUMNNAME_AD_Table_ID);
		queryStr.append(" = ? ");

		return queryStr;
	}
	
	/**
	 * Forma el query de la consulta que se necesita para saber si la cuenta paciente (cuando ya es elegible para una excepcion)
	 * contiene los diagnosticos configurados en el detalle de la regla.
	 * @param ruleDet
	 * @return
	 */
	public static StringBuilder getDiagQuery(final MEXMEBillingRuleDet ruleDet) {

		final StringBuilder queryStr = new StringBuilder();
		final MTable tableEngine = MTable.get(Env.getCtx(), ruleDet.getEXME_BillingFilter().getAD_TableEngine_ID());

		if (MEXMEBillingRuleDet.OPERATION_NA.equals(ruleDet.getOperation())){
			ruleDet.setOperation(MEXMEBillingRuleDet.OPERATION_CT);
			ruleDet.setParameterValue(MEXMEBillingRuleDet.PARAMETERVALUE_VA);
			ruleDet.setComparisonValue(MEXMEBillingRuleDet.COMPARISONVALUE_EQ);
			ruleDet.setValue("0");
		}
		
		queryStr.append("SELECT ");
	
		final String valueOp = MRefList.getNameByReferenceID(null, MEXMEBillingRuleDet.OPERATION_AD_Reference_ID, ruleDet.getOperation());
		queryStr.append(valueOp);
		queryStr.append("(*) ");
		

		queryStr.append(" FROM ");
		queryStr.append(tableEngine.getTableName());
		
		queryStr.append(" INNER JOIN ");
		queryStr.append(MEXMEActPaciente.Table_Name);
		queryStr.append(" ON (");
		queryStr.append(MEXMEActPaciente.Table_Name);
		queryStr.append(".");
		queryStr.append(MEXMEActPaciente.Table_Name);
		queryStr.append("_ID = ");
		queryStr.append(tableEngine.getTableName());
		queryStr.append(".");
		queryStr.append(MEXMEActPaciente.Table_Name);
		queryStr.append("_ID ");
		queryStr.append(" AND ");
		queryStr.append(MEXMEActPaciente.Table_Name);
		queryStr.append(".");
		queryStr.append(MEXMECtaPac.COLUMNNAME_EXME_CtaPac_ID);
		queryStr.append(" = ? )");
		
		queryStr.append(" INNER JOIN ");
		queryStr.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		queryStr.append(" ON (");
		queryStr.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		queryStr.append(".");
		queryStr.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		queryStr.append("_ID");
		queryStr.append(" = ");
		queryStr.append(MActPacienteDiag.Table_Name);
		queryStr.append(".");
		queryStr.append(ruleDet.getEXME_BillingFilter().getAD_Table().getTableName());
		queryStr.append("_ID");
		queryStr.append(BillingQueueEngine.getBetweenQuery(ruleDet));
		queryStr.append(")");
		
		return queryStr;
	}
	
	/**
	 * Obtiene el dato de Lenght of Stay
	 * @param admitDate
	 * @param dischargeDate
	 * @return
	 */
	public static long getLOS(final Date admitDate, final Date dischargeDate){
		
		long diference = 0;
		final Date today = new Date();
		
		if(dischargeDate == null){
			diference = ( today.getTime() - admitDate.getTime() )/ (1000 * 60 * 60 * 24);
		}else{
			diference = ( dischargeDate.getTime() - admitDate.getTime() )/ (1000 * 60 * 60 * 24);
		}
		
		if(diference == 0){
			diference = 1;
		}
		
		
		return diference;
		
	}


	/**
	 * Verifica si dependiendo de un detalle de regla, y un valor dado se cae en una excepcion.
	 * Toma en cuenta los datos de comparacion y valor.
	 * @param ruleDet
	 * @param code
	 * @return
	 */
	public static boolean hasCodeException(final MEXMEBillingRuleDet ruleDet, final int code, final Date admitDate, final Date dischargeDate) {

		boolean hasException = false;

		if (MEXMEBillingRuleDet.OPERATION_SM.equals(ruleDet.getOperation()) || MEXMEBillingRuleDet.OPERATION_CT.equals(ruleDet.getOperation())) {

			if (MEXMEBillingRuleDet.COMPARISONVALUE_EQ.equals(ruleDet.getComparisonValue())) {
				if (ruleDet.getParameterValue().equals(MEXMEBillingRuleDet.PARAMETERVALUE_VA)) {
					if (code == Integer.parseInt(ruleDet.getValue())) {
						hasException = true;
					}
				} else {
					
					if (code == BillingQueueEngine.getLOS(admitDate, dischargeDate)) {
						hasException = true;
					}
				}

			} else if (MEXMEBillingRuleDet.COMPARISONVALUE_GT.equals(ruleDet.getComparisonValue())) {
				if (ruleDet.getParameterValue().equals(MEXMEBillingRuleDet.PARAMETERVALUE_VA)) {
					if (code > Integer.parseInt(ruleDet.getValue())) {
						hasException = true;
					}
				} else {
					
					if (code > BillingQueueEngine.getLOS(admitDate, dischargeDate)) {
						hasException = true;
					}
				}
			} else if (MEXMEBillingRuleDet.COMPARISONVALUE_LT.equals(ruleDet.getComparisonValue())) {
				if (ruleDet.getParameterValue().equals(MEXMEBillingRuleDet.PARAMETERVALUE_VA)) {
					if (code < Integer.parseInt(ruleDet.getValue())) {
						hasException = true;
					}
				} else {
					
					if (code < BillingQueueEngine.getLOS(admitDate, dischargeDate)) {
						hasException = true;
					}
				}
			}

		}

		return hasException;

	}
	

}
