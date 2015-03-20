package org.compiere.model;

import static org.compiere.util.Utilerias.getMessage;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueName;
import org.compiere.util.WebEnv;

public class MEXMECDS extends X_EXME_CDS {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
	private static CLogger      s_log = CLogger.getCLogger (MEXMECDS.class);
		
	public static final String TABLEID = "TableID";
	public static final String RECORDID = "RecordID";;
	public static final String TABLEPROCESSID = "TableProcessID";
	public static final String PACID = "PacID";
	public static final String CDSLIST = "CdsList";
	
	public MEXMECDS(Properties ctx, int EXME_CDS_ID, String trxName) {
		super(ctx, EXME_CDS_ID, trxName);
	}
	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECDS(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	/**
	 * Obtenemos 
	 * 
	 * @param ctx
	 * @return
	 */
	public static MEXMECDS[] getActiveCDS(Properties ctx, String trxName) {
		List<MEXMECDS> list = getCDSLst(ctx, trxName);
		MEXMECDS[] listCDS= new MEXMECDS[list.size()];
		list.toArray(listCDS);
		return listCDS;
	}
	public static List<MEXMECDS> getCDSLst(Properties ctx, String trxName) {
		return getCDSLst(ctx, true, trxName) ;
	}
	
	/**
	 * Retorna la lista de reglas que se tiene registradas en el sistema
	 * @param ctx contexto
	 * @param onlyIsActive Registro activo o inactivo
	 * @param typeRule Tipo de Regla
	 * @param trxName Transaccion
	 * @return
	 * @author mvrodriguez
	 */
	public static List<MEXMECDS> getCDSLst(Properties ctx, boolean onlyIsActive, String typeRule, String trxName) {
		if (ctx == null) {
			return new ArrayList<MEXMECDS>();
		}
		final List<Object> parameters = new ArrayList<Object>();
		String whereClause = null;
		if (StringUtils.isNotEmpty(typeRule)) {
			whereClause = " EXME_CDS.TYPE IN (?) ";
			parameters.add(typeRule);
		}
		return new Query(ctx, Table_Name, whereClause, trxName)//
			.setParameters(parameters)//
			.setOnlyActiveRecords(onlyIsActive) //
			.setOrderBy(" EXME_CDS.created DESC")//
			.addAccessLevelSQL(true)//
			.list();
	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 * @modify 28/10/2010 mvrodriguez El cuerpo del método se paso al metodo sobrecargado getCDSLst(ctx, typeRule, trxName) y 
	 * se puso la llamada al metodo antes mencionado
	 */
	public static List<MEXMECDS> getCDSLst(Properties ctx, boolean onlyIsActive, String trxName) {
		return  getCDSLst(ctx, onlyIsActive, null, trxName);
	}

	/**
	 * Devuelve Reglas cumplidas por el paciente
	 * 
	 * @param pacID Integer - Patient Identifier
	 * @param ctx   Properties - Apps Context
	 * @return List<Integer> - EXME_CDS_ID List
	 * @deprecated Se deprecia metodo ya que no tiene referencias. No se corrige TRUNC
	 *             para postgresql. Jesus Cantu. 25 Julio 2012.
	 *
	public static List<Integer> ruleCompliant(int pacID, Properties ctx) {

		List<MEXMECDS> list = MEXMECDS.getCDSLst(ctx, null);
		Iterator<MEXMECDS> iterCDS = list.iterator();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> cdsList = new ArrayList<Integer>();
		//banderas por tipo de regla
		boolean patientDemo = false;
		boolean medicalCond = false;
		boolean patientDiag = false;
		boolean medicationList = false;
		boolean testResult = false;
		//bandera entro regla PatientDemographics
		boolean demoClose = false;
		boolean medicalClose = false;
		boolean diagClose = false;
		boolean medicationClose = false;
		boolean testClose = false;
		//bandera cumplio condiciones para campo de PatientDemographics
		boolean demoField = false;
		//iteramos las CDS activas
		while (iterCDS.hasNext()){
			MEXMECDS cds = (MEXMECDS) iterCDS.next();

			//Obtenemos la lista de Reglas para el CDS que se itera
			List<MEXMECDSRules> listRules = MEXMECDSRules.getCDSRulesLst(ctx, null, cds.getEXME_CDS_ID());

			//iteramos solo si tiene reglas definidas para CDS
			if (listRules != null){

				StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				StringBuilder whereClause = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				sql.append("SELECT 1 ");
				sql.append("  FROM ");

				//Lista de Objetos para almacenar los SETTERS del sql
				List<Object> listaSetters = new ArrayList<Object>();

				//iteramos las reglas de CDS actual
				for (Iterator<MEXMECDSRules> iterRules = listRules.iterator(); iterRules.hasNext(); ){
					MEXMECDSRules cdsRules = (MEXMECDSRules) iterRules.next();
					BigDecimal valor = new BigDecimal(0);

					//Si existe registro pero sin tipo de regla salimos
					if (cdsRules.getRuleType() == null){
						break;
					}

					//Si la regla es de demograficos y de los campos Peso y Talla es necesario convertir el valor
					if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientDemographics)
							&& (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Weight)
									||cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Height))
					){
						if( MUser.convertirUnidades(ctx)){
							// convertimos el valor correspondiente
							valor = MUOM.convertirMedida(ctx, cdsRules.getC_UOM_ID(), cdsRules.getDoubleValue(), MUser.getSistMedicionUsuario(ctx));
						}else{
							valor = cdsRules.getDoubleValue();
						}
					}

					//Se itera ordenado por RuleType
					//Si RuleType actual es diferente de PatientDemographics
					//y ya se entro a una condicion de PatientDemographics
					//cerramos bloque sql para PatientDemographics
					String actualRule = cdsRules.getRuleType();
					if (!actualRule.equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientDemographics) && patientDemo && !demoClose){
						//Si entro a PatientDemographics
						//Pero no cumplio condiciones de DemoField
						//forzar el no retorno
						if (!demoField){
							sql.append(" AND 1=0 ");
						}
						sql.append(" ) DEMO, ");
						whereClause.append(" AND DEMO > 0 ");
						demoClose = true;
					}else if (!actualRule.equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicalCondition) && medicalCond && !medicalClose){
						sql.append(" ) ) CONDITION, ");
						whereClause.append(" AND CONDITION > 0 ");
						medicalClose = true;
					}else if (!actualRule.equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_SpecificPatientDiagnostic) && patientDiag && !diagClose){
						sql.append(" )) DIAGNOSTIC, ");
						whereClause.append(" AND DIAGNOSTIC > 0 ");
						diagClose = true;
					}else if (!actualRule.equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicationList) && medicationList && !medicationClose){
						sql.append(" )) MEDICATION, ");
						whereClause.append(" AND MEDICATION > 0 ");
						medicationClose = true;
					}else if (!actualRule.equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_LaboratoryTestResults) && testResult && !testClose ){
						sql.append(") WHERE EXME_ActPaciente.EXME_Paciente_ID = ? ");
						listaSetters.add(pacID);
						sql.append(" AND EXME_ActPacienteINDH.IsService = 'Y') OBSERVATION, ");	
						whereClause.append(" AND OBSERVATION > 0 ");
						testClose = true;
					}


					//Bloque Datos Demográficos del Paciente
					if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientDemographics)){
						//Si no se ha entrado en PatientDemographics
						//Se "inicializa" bloque sql
						if (!patientDemo){
							sql.append("       (SELECT COUNT(*) DEMO ");
							sql.append("          FROM EXME_Paciente ");
							sql.append("         WHERE EXME_Paciente.EXME_Paciente_ID = ? ");
							listaSetters.add(pacID);
							patientDemo = true;
						}
						//Si existe un registro de tipo PatientDemographics
						//Pero sin campo Demografico
						//Forzar no retorno de resultados
						if(cdsRules.getDemoField() == null ){
							//nos salimos sin regresar
							sql.append("         AND 1 = 0");
							break;
						}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Age) 
								&& cdsRules.getIntValue()>=0){
							demoField = true;
							sql.append("           AND TRUNC(months_between(").append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append(", EXME_Paciente.FECHANAC) / 12) ");
							sql.append(" ").append(cdsRules.getComparisonOperator()).append(" ? ");
							listaSetters.add(cdsRules.getIntValue());
						}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Nacionality) 
								&& cdsRules.getEXME_Nacionalidad_ID()>0){
							demoField = true;
							sql.append("           AND EXME_Paciente.EXME_Nacionalidad_id = ? ");
							listaSetters.add(cdsRules.getEXME_Nacionalidad_ID());
						}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Race) 
								&& cdsRules.getEXME_Razas_ID()>0){
							demoField = true;
							sql.append("           AND EXME_Paciente.EXME_Razas_ID = ? ");
							listaSetters.add(cdsRules.getEXME_Razas_ID());
						}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_EthnicGroup) 
								&& cdsRules.getEXME_GpoEtnico_ID()>0){
							demoField = true;
							sql.append("           AND EXME_Paciente.EXME_GpoEtnico_ID = ? ");
							listaSetters.add(cdsRules.getEXME_GpoEtnico_ID());
						}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Sex) 
								&& cdsRules.getCharValue()!= null){
							demoField = true;
							sql.append("           AND EXME_Paciente.sexo = ? ");
							listaSetters.add(cdsRules.getCharValue());
						}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Weight) 
								&& valor != null && valor.compareTo(BigDecimal.ZERO)>0 ){
							demoField = true;
							sql.append("           AND EXME_Paciente.PESO  ");						
							sql.append(" ").append(cdsRules.getComparisonOperator()).append(" ? ");
							listaSetters.add(valor);
						}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Height)
								&& valor != null && valor.compareTo(BigDecimal.ZERO)>0){
							demoField = true;
							sql.append("           AND EXME_Paciente.TALLA  ");
							sql.append(" ").append(cdsRules.getComparisonOperator()).append(" ? ");
							listaSetters.add(valor);
						}

						//Bloque Condición Médica del Paciente
					}else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicalCondition) 
							&& cdsRules.getEXME_DiagnosticoConMed_ID() > 0){
						if (!medicalCond){
							sql.append(" (SELECT COUNT(*) CONDITION ");
							sql.append("    FROM EXME_CondMedica ");
							sql.append("   WHERE EXME_CondMedica.EXME_Paciente_ID = ? ");
							listaSetters.add(pacID);
							sql.append("     AND EXME_CondMedica.EXME_Diagnostico_ID in(? ");
							medicalCond = true;
						}else{
							sql.append("  ,? ");
						}
						listaSetters.add(cdsRules.getEXME_DiagnosticoConMed_ID());
					}
					//Bloque Diagnósticos del Paciente
					else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_SpecificPatientDiagnostic) 
							&& cdsRules.getEXME_Diagnostico_ID() > 0){
						if (!patientDiag){
							patientDiag = true;
							sql.append(" (SELECT COUNT(*) DIAGNOSTIC ");
							sql.append("    FROM EXME_ActPacienteDiag ");
							sql.append("    INNER JOIN EXME_ActPaciente ");
							sql.append("       ON EXME_ActPaciente.EXME_ActPaciente_ID = ");
							sql.append("          EXME_ActPacienteDiag.EXME_ActPaciente_ID ");
							sql.append("    WHERE EXME_ActPaciente.EXME_Paciente_ID = ? ");
							listaSetters.add(pacID);
							//sql.append("           AND EXME_ActPacienteDiag.EXME_Diagnostico_ID = ?) DIAGNOSTIC, ");
							sql.append("      AND EXME_ActPacienteDiag.EXME_Diagnostico_ID in(? ");
						}else{
							sql.append("      ,? ");
						}
						listaSetters.add(cdsRules.getEXME_Diagnostico_ID());
					}
					//Bloque Medicamentos prescritos del Paciente
					else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicationList) 
							&& cdsRules.getM_Product_ID() > 0){
						if (!medicationList){
							medicationList = true;
							sql.append(" (SELECT COUNT(*) MEDICATION ");
							sql.append("    FROM EXME_ActPaciente ");
							sql.append("    INNER JOIN EXME_ActPacienteINDH ");
							sql.append("       ON EXME_ActPacienteINDH.EXME_ActPaciente_ID = ");
							sql.append("          EXME_ActPaciente.EXME_ActPaciente_ID ");
							sql.append("    INNER JOIN EXME_ActPacienteIND ");
							sql.append("       ON EXME_ActPacienteIND.EXME_ActPacienteINDH_ID = ");
							sql.append("          EXME_ActPacienteINDH.EXME_ActPacienteINDH_ID ");
							sql.append("    INNER JOIN M_Product ");
							sql.append("       ON M_Product.M_Product_ID = EXME_ActPacienteIND.M_Product_ID ");
							sql.append("    WHERE EXME_ActPaciente.EXME_Paciente_ID = ? ");
							listaSetters.add(pacID);
							sql.append("      AND EXME_ActPacienteINDH.IsService = 'N' ");
							sql.append("      AND EXME_ActPacienteINDH.EXME_Tratamientos_ID is null ");
							//sql.append("           AND M_Product.M_Product_ID = ?) MEDICATION, ");
							sql.append("      AND M_Product.M_Product_ID in(? ");
						}else{
							sql.append("      ,? ");
						}
						listaSetters.add(cdsRules.getM_Product_ID());
					}
					//Bloque observaciones de estudios del Paciente
					else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_LaboratoryTestResults) 
							&& cdsRules.getEXME_Loinc_ID()>0){

						if (!testResult){
							testResult = true;
							sql.append(" (SELECT COUNT(*) OBSERVATION ");
							sql.append("    FROM EXME_ActPaciente ");
							sql.append("   INNER JOIN EXME_ActPacienteINDH ");
							sql.append("      ON EXME_ActPacienteINDH.EXME_ActPaciente_ID = ");
							sql.append("         EXME_ActPaciente.EXME_ActPaciente_ID ");
							sql.append("   INNER JOIN EXME_ActPacienteIND ");
							sql.append("      ON EXME_ActPacienteIND.EXME_ActPacienteINDH_ID = ");
							sql.append("         EXME_ActPacienteINDH.EXME_ActPacienteINDH_ID ");
							sql.append("   INNER JOIN EXME_EstudiosOBX ");
							sql.append("      ON EXME_EstudiosOBX.AD_Client_ID = EXME_ActPacienteINDH.AD_Client_ID ");
							sql.append("     AND EXME_EstudiosOBX.AD_Org_ID = EXME_ActPacienteINDH.AD_Org_ID ");
							sql.append("     AND EXME_EstudiosOBX.EXME_ActPacienteIND_ID = ");
							sql.append("         EXME_ActPacienteIND.EXME_ActPacienteIND_ID ");
							sql.append("     AND ((EXME_EstudiosOBX.SystemCode = ? ");
							sql.append("     AND EXME_EstudiosOBX.CodeID = ? ");
							sql.append("     AND EXME_EstudiosOBX.Observation ").append(cdsRules.getComparisonOperator()).append(" ? ) ");
						}else{
							sql.append("     OR (EXME_EstudiosOBX.SystemCode = ? ");
							sql.append("     AND EXME_EstudiosOBX.CodeID = ? ");
							sql.append("     AND EXME_EstudiosOBX.Observation ").append(cdsRules.getComparisonOperator()).append(" ? ) ");
						}
						listaSetters.add(cdsRules.getStringValue());
						listaSetters.add(cdsRules.getEXME_Loinc_ID());
						listaSetters.add(cdsRules.getDoubleValue());					
					}

				}			
				
				if (!demoClose  && patientDemo){
					sql.append(" ) DEMO, ");
					whereClause.append(" AND DEMO > 0 ");
				}
				if (!medicalClose && medicalCond){
					sql.append(")) CONDITION, ");
					whereClause.append(" AND CONDITION > 0 ");
				}
				if (!diagClose && patientDiag){
					sql.append(")) DIAGNOSTIC, ");
					whereClause.append(" AND DIAGNOSTIC > 0 ");
				}
				if (!medicationClose && medicationList){
					sql.append(")) MEDICATION, ");
					whereClause.append(" AND MEDICATION > 0 ");
				}
				if (!testClose && testResult){
					sql.append(") WHERE EXME_ActPaciente.EXME_Paciente_ID = ? ");
					listaSetters.add(pacID);
					sql.append(" AND EXME_ActPacienteINDH.IsService = 'Y') OBSERVATION, ");	
					
					whereClause.append(" AND OBSERVATION > 0 ");
				}

				//Para evitar condicionar el imprimir WHERE o AND
				sql.append("   DUAL");
				sql.append("   WHERE 1=1 ");

				//Si por algun motivo no cayo en ninguna condicion forzamos a no regresar nada
				//de otro modo agregamos las condiciones creadas
				if (!patientDemo && !medicalCond && !patientDiag && !medicationList && !testResult){
					sql.append("   AND 1 = 0 ");
				}else{
					sql.append(whereClause);
				}
				
				//Revisamos si esta configurado y activo CDS
				MEXMEMejoras configMejoras = MEXMEMejoras.get(ctx, null);
				if (configMejoras!=null && configMejoras.isMDS()){
					//Si esta configurado lanzar la alerta por única vez
					if (configMejoras.isMDSRepResp()){
						sql.append(" AND (SELECT Distinct 1 ")
							         .append("FROM AD_Note ")
									 .append("WHERE NoticeType = ? ")
									 .append("AND EXME_Paciente_ID = ? ")
									 .append("AND Description = ? ) IS NULL ");
						listaSetters.add(X_AD_Note.NOTICETYPE_CDS);
						listaSetters.add(pacID);
						listaSetters.add(cds.getEXME_CDS_ID());
					}
					
				}else{
					sql.append("   AND 1 = 0 ");
				}
				try{
					pstmt = DB.prepareStatement(sql.toString(), null);
					//Iteramos la lista donde se almacenan
					//ordenadamente los SETTERs para la ejecucion de SQL
					//Evaluando el tipo de objeto para realizar
					//el set adecuado
					for (Integer i = 0; i <listaSetters.size(); i++){
						if (listaSetters.get(i) instanceof Integer){
							pstmt.setInt(i+1, (Integer)listaSetters.get(i));
						}else if (listaSetters.get(i) instanceof BigDecimal){
							pstmt.setBigDecimal(i+1, (BigDecimal)listaSetters.get(i) );
						}else if (listaSetters.get(i) instanceof String){
							pstmt.setString(i+1, String.valueOf(listaSetters.get(i)));
						}
					}
					
					rs = pstmt.executeQuery();
					//Si regresa resultado agregamos a la lista el EXME_CDS
					while (rs.next()) {
						cdsList .add(cds.getEXME_CDS_ID());
					}
				}catch(Exception e) {
					s_log.log(Level.SEVERE, "getCDSLst", e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}

				//Reiniciamos banderas
				patientDemo = false;
				medicalCond = false;
				patientDiag = false;
				medicationList = false;
				testResult = false;
				demoClose = false;
				medicalClose = false;
				diagClose = false;
				medicationClose = false;
				testClose = false;

				demoField = false;
			}
		}

		return cdsList;
	}*///Comentando al ser identificado como obsoleto desde Julio 2012.
	
	/**
	 * Busquede de pacientes por reglas
	 * 
	 * @param ctx
	 *            Contexto
	 * @param rules
	 *            Reglas
	 * @return Listado de pacientes que cumplen con las reglas
	 */
	public static List<MEXMEPaciente> searchPatientsByRule(final Properties ctx, final List<MEXMECDSRules> rules,
			final OrderSelection orderSelection, final Date startDate, final Date endDate) {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT distinct(EXME_Paciente.EXME_Paciente_ID)  [selects], ");
		sql.append(" EXME_CONFIGRECORDATORIO.iscorreo, EXME_CONFIGRECORDATORIO.issms, EXME_CONFIGRECORDATORIO.isemail ");
		sql.append(" FROM exme_paciente ");

		// Filtrar solo los que tengan MRN en la Organizacion
		sql.append(" INNER JOIN EXME_Hist_Exp exp on (EXME_Paciente.EXME_Paciente_ID = exp.EXME_Paciente_ID AND exp.AD_Org_ID=?)");
		// Filtrar solo los pacientes con encuentros en la organizacion .- Lama
		// ZMorton, sep 2012: show only patients with encounters in that organization but where the data could exist in encounters from any organization
		sql.append(" INNER JOIN EXME_CtaPac cp on (EXME_Paciente.EXME_Paciente_ID = cp.EXME_Paciente_ID AND cp.AD_Org_ID=exp.AD_Org_ID)");

		final List<Object> listParam = new ArrayList<Object>();
		listParam.add(Env.getAD_Org_ID(ctx));

		// Agregar que siempre traiga el correo,msn y correo electronico
		sql.append("  LEFT JOIN  EXME_CONFIGRECORDATORIO ON (EXME_Paciente.EXME_Paciente_ID = EXME_CONFIGRECORDATORIO.EXME_Paciente_ID ");
		sql.append(" AND EXME_CONFIGRECORDATORIO.ISACTIVE = 'Y') ");

		final StringBuilder whereClause = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		whereClause.append(" WHERE (EXME_Paciente.isActive = 'Y' ");

		final List<String> selects = new ArrayList<String>();
		final Map<String, String> joinRule = new TreeMap<String, String>();
		final Map<String, Map<String, String>> tableRuleType = chargeTableForRuleType();
		final Map<Object, Object> conditionRule = new TreeMap<Object, Object>();
		final TreeSet<ValueName> order = new TreeSet<ValueName>();

		boolean moreValuesIn = false;
		String ruleType = "";

		for (MEXMECDSRules rule : rules) {
			// Evaluamos que si hubo cambio en el tipo de regla de al
			// antepenultima a la ultima
			if (moreValuesIn && !"".equals(ruleType) && !ruleType.equals(rule.getRuleType())) {
				whereClause.append(") ");
			}
			// Concatenemos los Join con las tablas necesarios para la consulta
			sentenceJoins(sql, rule, tableRuleType.get(rule.getRuleType()), joinRule, startDate, endDate);

			moreValuesIn = conditions(whereClause, rule, listParam, conditionRule, ctx);

			order.add(getOrderBy(rule, ctx));

			selects.add(getSelect(rule, ctx));
			// Obtenemos el tipo de regla de la condicion
			ruleType = rule.getRuleType();
		}
		
		// Evaluamos que si en ultima condicion era un in, ya que en la ultima
		// vuelta ya no entro a evaluarlo
		if (moreValuesIn) {
			whereClause.append(") ");
		}
		
		// Cerramos el parentesis de la condicion
		whereClause.append(")");
		if (!selects.isEmpty()) {
			final StringBuilder sl = new StringBuilder();
			sl.append(", ");
			sl.append(StringUtils.join(selects, ", "));
			sql = new StringBuilder(StringUtils.replace(sql.toString(), "[selects]", sl.toString()));
		} else {
			sql = new StringBuilder(StringUtils.remove(sql.toString(), "[selects]"));
		}

		sql.append(whereClause);

		if (orderSelection != null) {
			orderSelection.show(order.toArray(new ValueName[] {}));
			sql.append(" ORDER BY ").append(orderSelection.getOrder().getValue());
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final List<MEXMEPaciente> lst = new ArrayList<MEXMEPaciente>();
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, listParam);
			rs = pstmt.executeQuery();
			final List<String> ids = new ArrayList<String>();
			while (rs.next()) {
				String id = rs.getString(1);
				if (!ids.contains(id)) {
					lst.add(new MEXMEPaciente(ctx, rs.getInt("EXME_Paciente_ID"), null));
					ids.add(id);
				}
			}
			if (WebEnv.DEBUG){
				s_log.config(sql.toString() + Constantes.NEWLINE + listParam.toString());
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString() + Constantes.NEWLINE + listParam.toString(), e);
		} finally {
			DB.close(rs, pstmt);
    		rs = null;
    		pstmt = null;
		}

		return lst	;
	}
	
	/**
	 * Obtiene los id de las reglas de Clinical Decision Support con las que el paciente cumple
	 * NOTA: Este debe de modificarse para que realice la funcion del metodo ruleComplianPatient
	 * @param patientId Paciente al que se evaluaran las reglas
	 * @param typeRule Tipo de Regla que se desean evaluar
	 * @param ctx Contexto
	 * @return List Lista reglas que se cumplieron
	 * @author mvrodriguez
	 */
	public static List<Integer> complyWithRule(int patientId, String typeRule, Properties ctx) {
		
		List<MEXMECDS> list = MEXMECDS.getCDSLst(ctx, true, typeRule, null);
		Iterator<MEXMECDS> iterCDS = list.iterator();
		List<Integer> cdsList = new ArrayList<Integer>();
		/*
		 *Aqui se registran los tipos de condiciones que ya se agregaron al WHERE de la consulta
		 *y que nos servirá para saber si se seguiran agregando elemento en el IN 
		 */
		Map<Object, Object> conditionRule;
		/*
		 * Aqui se regiraran las tablas que ya se agregaron como join y esta nos ayudara a validar
		 * que ya no se dupliquen las tablas en los joins
		 */
		Map<String, String> joinRule;
		//Nos indica si son varios valores para el in en caso de que aplique
		boolean moreValuesIn =  false;
		//Nos indica el tipo de regla antepenultima agregada a la condicion 
		String ruleType;
		//Nos idica el grupo al que pertenece la condicion
		int seqNo = 0;
		
		//iteramos las CDS activas
		while (iterCDS.hasNext()){
			
			MEXMECDS cds = (MEXMECDS) iterCDS.next();

			//Obtenemos la lista de Reglas para el CDS que se itera
			List<MEXMECDSRules> listRules = MEXMECDSRules.getCDSRulesLst(ctx, null, cds.getEXME_CDS_ID());

			//iteramos solo si tiene reglas definidas para CDS
			if (listRules != null){
				
				ruleType = "";
				seqNo = 0;
				conditionRule = new TreeMap<Object, Object>();
				joinRule = new TreeMap<String, String>();
				Map<String, Map<String, String>> tableRuleType = chargeTableForRuleType();
				//Lista de Objetos para almacenar los SETTERS del sql
				List<Object> listParam = new ArrayList<Object>();

				//Inicializamos para la siguiente regla
				moreValuesIn = false;
				
				StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				StringBuilder whereClause = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				
				//Consulta a tabla base
				sql.append("SELECT COUNT(EXME_Paciente.EXME_Paciente_ID) ");
				sql.append("  FROM EXME_Paciente ");
				
				//Concatenamos la condicion minima necesaria para una consulta
				whereClause.append(" WHERE (EXME_Paciente.EXME_Paciente_ID = ?");
				//Agregamos como el primer parametro el id del paciente
				listParam.add(patientId);
												
				//iteramos las reglas de CDS actual
				for (Iterator<MEXMECDSRules> iterRules = listRules.iterator(); iterRules.hasNext(); ){
	
					MEXMECDSRules cdsRules = (MEXMECDSRules) iterRules.next();
					
					//Evaluamos que si hubo cambio en el tipo de regla de al antepenultima a la ultima
					if(moreValuesIn && !"".equals(ruleType) && !ruleType.equals(cdsRules.getRuleType())) {
						
						whereClause.append(") ");
						/*
						 * Como ya hubo cambio de Tipo de Regla debemos de cerrar el parentesis
						 * del in, porque moreValuesIn es true
						 */
						moreValuesIn = false;
					}
					
					//En caso de que cambie el Grupo procedemos a poner el OR
					if(seqNo != cdsRules.getSeqNo()) {
						
						//Concatenamos la condicion minima necesaria para una consulta
						whereClause.append(") OR (EXME_Paciente.EXME_Paciente_ID = ? ");
						
						//Agregamos como el primer parametro el id del paciente
						listParam.add(patientId);
						
						//Se inicializa el conditionRule por ser un grupo nuevo de condiciones
						conditionRule = new TreeMap<Object, Object>();
						
						//Inicializamos para la siguiente regla por ser un grupo nuevo de condiciones
						moreValuesIn = false;
						
					} 
					
					//Si existe registro pero sin tipo de regla salimos
					if (cdsRules.getRuleType() == null){
						break;
					}

					//Concatenemos los Join con las tablas necesarios para la consulta
					sentenceJoins(sql,cdsRules, (Map<String, String>) tableRuleType.get(cdsRules.getRuleType()), joinRule,null,null);
					
					/*
					 * Concatenamos las condiciones necesarias para las consultas y nos retorna un true si hay mas de dos valores en un in
					 */
					moreValuesIn = conditions(whereClause, cdsRules, listParam, conditionRule, ctx);
					
					//Obtenemos el tipo de regla de la condicion
					ruleType = cdsRules.getRuleType();
					
					//Obtenemos el grupo al que pertenece la condicion
					seqNo = cdsRules.getSeqNo();
					
				}
				
				// Evaluamos que si en ultima condicion era un in, ya que en la ultima vuelta ya no entro a evaluarlo
				if (moreValuesIn) {
					whereClause.append(") ");
				}

				// Cerramos el parentesis de la condicion
				whereClause.append(")");

				sql.append(whereClause);

				// Validamos que la consulta nos regrese un registro
				if (DB.getSQLValue(null, sql.toString(), listParam) > 0) {// lama
					cdsList.add(cds.getEXME_CDS_ID());
				}
			}
		}
		return cdsList;
	}
	
	/**
	 * Se encarga de armar los join contras las tablas que apliquen segun el tipo de condicion por
	 * las que esta formada la regla
	 * @param sql StringBuilder Sentencia de Sql base a la que se le agregaran los join necesarios
	 * @param table Map<Object, Object> Tablas que se deben de agregar como joins
	 * @author mvrodriguez
	 */
	private static void sentenceJoins(StringBuilder sql, MEXMECDSRules rule, Map<String, String> table, Map<String, String> joinRule, Date startDate, Date endDate) {
		
		final String CONDMEDICA = "EXME_CondMedica";
		final String ACTPACIENTE = "EXME_ActPaciente";
		final String ACTPACIENTEDIAG = "EXME_ActPacienteDiag";
		final String ACTPACIENTEINDH = "EXME_ActPacienteINDH";
		final String ACTPACIENTEIND = "EXME_ActPacienteIND";	
		final String PRODUCT = "M_Product";
		final String ESTUDIOSOBX = "EXME_EstudiosOBX";
		final String PACIENTEALER = "EXME_PacienteAler";
		final String CTAPAC = "EXME_CTAPAC";
		final String PLANMED = "EXME_PLANMED";
		final String PRESCRX = "EXME_PrescRX";
		final String PRESCRXDET = "EXME_PrescRXDet";
		final StringBuilder fechas= new StringBuilder();// Se cambia de String a StringBuilder para manejo de concatenaciones.- Lama Sep 2012
		DateFormat dateFormat = Constantes.getSdfFecha();	
		if(table.containsKey(CONDMEDICA) && !joinRule.containsKey(CONDMEDICA)) {
			
			joinRule.put(CONDMEDICA, "true");
			
			if(startDate!=null && endDate!=null){		
				fechas.append("AND (EXME_CondMedica.fechaini between to_date('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy') AND to_date('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy') ");
				fechas.append("OR EXME_CondMedica.fechaini between to_date('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy') and to_date('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy')) ");
				fechas.append("OR (to_date('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy') between exme_condmedica.fechaini and exme_condmedica.fechafin AND ");
				fechas.append(" to_date('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy') between exme_condmedica.fechaini and exme_condmedica.fechafin )");
			}
			else if (startDate != null && endDate == null) {
				fechas.append(" AND (EXME_CondMedica.fechaini >= to_date ('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy') OR EXME_CondMedica.fechafin >= to_date ('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy')) ");
			}
			else if (startDate == null && endDate != null) {
				fechas.append(" AND (EXME_CondMedica.fechafin <= to_date ('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy') OR EXME_CondMedica.fechaini <= to_date ('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy')) ");
			}

			sql.append("  LEFT JOIN EXME_CondMedica ON (EXME_Paciente.EXME_Paciente_ID=EXME_CondMedica.EXME_Paciente_ID AND EXME_CondMedica.ISACTIVE='Y' ");
			sql.append(fechas.toString());
			sql.append(") ");// RULETYPE_MedicalCondition
			fechas.setLength(0);
		}

		if (table.containsKey(ACTPACIENTE) && !joinRule.containsKey(ACTPACIENTE)) {

			joinRule.put(ACTPACIENTE, "true");

			if (startDate != null) {
				fechas.append(" AND EXME_ActPaciente.fecha >= to_date ('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy') ");
			}
			if (endDate != null) {
				fechas.append(" AND EXME_ActPaciente.fecha <= to_date ('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy') ");
			}
			sql.append("  LEFT JOIN EXME_ActPaciente ON (EXME_Paciente.EXME_Paciente_ID = EXME_ActPaciente.EXME_Paciente_ID ");
			sql.append("  AND EXME_ActPaciente.ISACTIVE = 'Y' "); // RULETYPE_SpecificPatientDiagnostic,
																	// RULETYPE_MedicationList,
																	// RULETYPE_LaboratoryTestResults
//			if (ctx != null) {
//				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEActPaciente.Table_Name));
//			} // Zmorton (Sep 2012) Pendiente definir si la informacion debe filtrarse por hospital
			sql.append(fechas.toString());
			sql.append(") ");
			fechas.setLength(0);
		}

		if(table.containsKey(ACTPACIENTEDIAG) && !joinRule.containsKey(ACTPACIENTEDIAG)) {
			
			joinRule.put(ACTPACIENTEDIAG, "true");
			if (startDate != null && endDate != null) {
				fechas.append("AND (EXME_ActPacienteDiag.FECHA_DIAGNOSTICO between to_date('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy') AND to_date('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy') ");
				fechas.append("OR EXME_ActPacienteDiag.FECHA_FINAL between to_date('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy') and to_date('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy')) ");
				fechas.append("OR (to_date('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy') between EXME_ActPacienteDiag.FECHA_DIAGNOSTICO and EXME_ActPacienteDiag.FECHA_FINAL AND ");
				fechas.append(" to_date('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy') between EXME_ActPacienteDiag.FECHA_DIAGNOSTICO and EXME_ActPacienteDiag.FECHA_FINAL )");
			}
			else if (startDate != null && endDate == null) {
				fechas.append(" AND (EXME_ActPacienteDiag.FECHA_DIAGNOSTICO >= to_date ('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy') OR EXME_ActPacienteDiag.FECHA_FINAL >= to_date ('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy'))");
			}
			else if (startDate == null && endDate != null) {
				fechas.append(" AND (EXME_ActPacienteDiag.FECHA_FINAL <= to_date ('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy') OR EXME_ActPacienteDiag.FECHA_DIAGNOSTICO <= to_date ('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy') ) ");
			}

			sql.append(" LEFT JOIN EXME_ActPacienteDiag ON (EXME_ActPaciente.EXME_ActPaciente_ID = EXME_ActPacienteDiag.EXME_ActPaciente_ID ");
			sql.append(" AND EXME_ActPacienteDiag.ISACTIVE = 'Y' ");
			sql.append(" AND EXME_ActPacienteDiag.Estatus NOT IN ('R', 'I') "); // RULETYPE_SpecificPatientDiagnostic
//			if (ctx != null) {
//				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MActPacienteDiag.Table_Name));
//			} // Zmorton (Sep 2012) Pendiente definir si la informacion debe filtrarse por hospital
			sql.append(fechas.toString());
			sql.append(") ");
			fechas.setLength(0);
		}
		// MEDICAMENTOS .- Lama Sep 2012
		if (table.containsKey(PRESCRXDET) && !joinRule.containsKey(PRESCRXDET)) {
//		if (table.containsKey(ACTPACIENTEINDH) && !joinRule.containsKey(ACTPACIENTEINDH)
//			|| table.containsKey(ACTPACIENTEIND) && !joinRule.containsKey(ACTPACIENTEIND)) {

			/* Se agregan las dos tablas ya que se estan usando en la vista, se excluye la de ACTPACIENTE ya que esta se
			 * usa para otras reglas y debe
			 * de agregarse adicional a la vista en caso de ser necesario */
//			joinRule.put(ACTPACIENTEINDH, "true"); // FIXME: La vista no incluye ACTPACIND/H !! .- Lama Sep 2012
//			joinRule.put(ACTPACIENTEIND, "true");
			joinRule.put(CTAPAC, "true");
			joinRule.put(PLANMED, "true");
			joinRule.put(PRESCRX, "true");
			joinRule.put(PRESCRXDET, "true");

			// if(startDate!=null && endDate!=null){
			// fechas=" AND EXME_MEDICAMENTOPAC.fecha >= to_date('"+dateFormat.format(startDate) +"', 'dd/MM/yyyy') "+
			// "AND EXME_MEDICAMENTOPAC.fecha <= to_date('"+dateFormat.format(endDate) +"', 'dd/MM/yyyy')";
			// }
			// if(startDate !=null && endDate==null){
			// fechas=" AND EXME_MEDICAMENTOPAC.fecha >= to_date ('"+ dateFormat.format(startDate) +"', 'dd/MM/yyyy') ";
			// }
			// if(startDate ==null && endDate!=null){
			// fechas=" AND EXME_MEDICAMENTOPAC.fecha <= to_date ('"+ dateFormat.format(endDate) +"', 'dd/MM/yyyy') ";
			// }
			sql.append("LEFT JOIN exme_medication_ccd ON (EXME_PACIENTE.EXME_PACIENTE_ID = exme_medication_ccd.EXME_PACIENTE_ID ");
			// sql.append("  LEFT JOIN EXME_MEDICAMENTOPAC ON (EXME_Paciente.EXME_Paciente_ID = EXME_MEDICAMENTOPAC.EXME_Paciente_ID "+ fechas+ ")");
//			if (ctx != null) { TODO: La vista no tiene Cliente/Org correcto, esta tomando de exme_paciente (Client to System).- Lama Sep 2012
//				sql.append(" AND exme_medication_ccd.AD_Client_ID=").append(Env.getAD_Client_ID(ctx));
//				sql.append(" AND exme_medication_ccd.AD_Org_ID IN (0,").append(Env.getAD_Org_ID(ctx)).append(") ");
//			} // Zmorton (Sep 2012) Pendiente definir si la informacion debe filtrarse por hospital
			sql.append(") ");
		}
		
		if(table.containsKey(PRODUCT) && !joinRule.containsKey(PRODUCT)) {
			joinRule.put(PRODUCT, "true");
			sql.append("  LEFT JOIN M_Product ON (exme_medication_ccd.EXME_GENPRODUCT_ID = M_Product.EXME_GENPRODUCT_ID) "); //RULETYPE_MedicationList
		}

		if (table.containsKey(ESTUDIOSOBX) && !joinRule.containsKey(ESTUDIOSOBX)) {
			joinRule.put(ESTUDIOSOBX, "true");

			if (startDate != null) {
				fechas.append(" AND EXME_EstudiosOBX.OBSERVATIONDATE >= to_date ('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy') ");
			}
			if (endDate != null) {
				fechas.append(" AND EXME_EstudiosOBX.OBSERVATIONDATE <= to_date ('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy') ");
			}
			// los resultados de lab se deben filtrar para ese paciente, se agregan las tablas necesarias .- Lama Sep 2012
			if (!joinRule.containsKey(ACTPACIENTEINDH) && !joinRule.containsKey(ACTPACIENTEIND)) {
				joinRule.put(ACTPACIENTEINDH, "true");
				joinRule.put(ACTPACIENTEIND, "true");

				sql.append(" LEFT JOIN EXME_ActPacienteIndH ON (EXME_ActPaciente.EXME_ActPaciente_ID=EXME_ActPacienteIndH.EXME_ActPaciente_ID AND EXME_ActPacienteIndH.isActive='Y') ");
				sql.append(" LEFT JOIN EXME_ActPacienteInd  ON (EXME_ActPacienteIndH.EXME_ActPacienteIndH_ID=EXME_ActPacienteInd.EXME_ActPacienteIndH_ID AND EXME_ActPacienteInd.isActive='Y')");
			}
			sql.append(" LEFT JOIN EXME_EstudiosOBX ON (EXME_ActPacienteInd.EXME_ActPacienteInd_ID=EXME_EstudiosOBX.EXME_ActPacienteInd_ID ");
			sql.append(" AND EXME_EstudiosOBX.ISACTIVE = 'Y' ");// RULETYPE_LaboratoryTestResults
			
			sql.append(fechas.toString());
			sql.append(" ) ");
			rule.getStartDate();
			fechas.setLength(0);
		}

		if (table.containsKey(PACIENTEALER) && !joinRule.containsKey(PACIENTEALER)) {

			joinRule.put(PACIENTEALER, "true");

			if (startDate != null) {
				fechas.append(" AND EXME_PacienteAler.fecha >= to_date ('");
				fechas.append(dateFormat.format(startDate));
				fechas.append("', 'dd/MM/yyyy') ");
			}
			if (startDate == null && endDate != null) {
				fechas.append(" AND EXME_PacienteAler.fecha <= to_date ('");
				fechas.append(dateFormat.format(endDate));
				fechas.append("', 'dd/MM/yyyy') ");
			}
			// RULETYPE_Allergis
			sql.append("  LEFT JOIN EXME_PacienteAler ON (EXME_Paciente.EXME_Paciente_ID=EXME_PacienteAler.EXME_Paciente_ID AND EXME_PacienteAler.ISACTIVE='Y' "); 
			sql.append(fechas.toString());
			sql.append("  AND EXME_PacienteAler.Estatus NOT IN ('E', 'U')) ");
			
			fechas.setLength(0);
		}
		
	}
	/**
	 * Llena el combobox de orden
	 * @param cdsRules reglas que se agregaron
	 * @param ctx
	 * @return
	 */
	public static ValueName getOrderBy(MEXMECDSRules cdsRules, Properties ctx) {
		ValueName retValue = null;
		String name = MRefList.getListName(ctx, MEXMECDSRules.RULETYPE_AD_Reference_ID, cdsRules.getRuleType());
		if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientDemographics)) {
			if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Age)) {
				retValue = new ValueName(name + '-' + Msg.translate(ctx, MEXMEPaciente.COLUMNNAME_FechaNac), " EXME_Paciente.FechaNac ");
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Nacionality)) {
				retValue = new ValueName(name + '-' + Msg.translate(ctx, MEXMEPaciente.COLUMNNAME_EXME_Nacionalidad_ID), " EXME_Paciente.EXME_Nacionalidad_ID ");
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Race)) {
				/**
				 * Razas ya no tiene relacion directa con paciente ahora esta en EXME_Paciente_Raza
				 */
				//retValue = new ValueName(name + '-' + Msg.translate(ctx, MEXMEPaciente.COLUMNNAME_EXME_Razas_ID), " EXME_Paciente.EXME_Razas_ID ");
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_EthnicGroup)) {
				retValue = new ValueName(name + '-' + Msg.translate(ctx, MEXMEPaciente.COLUMNNAME_EXME_GpoEtnico_ID), " EXME_Paciente.EXME_GpoEtnico_ID ");
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Sex)) {
				retValue = new ValueName(name + '-' + Msg.translate(ctx, MEXMEPaciente.COLUMNNAME_Sexo), " EXME_Paciente.Sexo ");
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Weight)) {
				retValue = new ValueName(name + '-' + Msg.translate(ctx, MEXMEPaciente.COLUMNNAME_Peso), " EXME_Paciente.Peso ");
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Height)) {
				retValue = new ValueName(name + '-' + Msg.translate(ctx, MEXMEPaciente.COLUMNNAME_Talla), " EXME_Paciente.Talla ");
			}
		} else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_LaboratoryTestResults)) {
			retValue = new ValueName(name, " EXME_EstudiosOBX.SystemCode, EXME_EstudiosOBX.CodeID, EXME_EstudiosOBX.Observation ");
		} else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicalCondition)) {
			retValue = new ValueName(name, " EXME_CondMedica.EXME_Diagnostico_ID ");
		} else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_SpecificPatientDiagnostic)) {
			retValue = new ValueName(name, " EXME_ActPacienteDiag.EXME_Diagnostico_ID ");
		} else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicationList)) {
			retValue = new ValueName(name, " M_Product.M_Product_ID ");
		} else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientAllergies)) {
			retValue = new ValueName(name, " EXME_PacienteAler.EXME_SActiva_ID ");
		} else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_CommunicationPreferences)) {
			retValue = new ValueName(name, " CP.DocumentNo ");
		}
		return retValue;
	}
	
	/**
	 * Obtiene el campo para agregar al select
	 * 
	 * @param cdsRules
	 * @param ctx
	 * @return
	 */
	public static String getSelect(MEXMECDSRules cdsRules, Properties ctx) {
		String retValue = null;
		if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientDemographics)) {
			if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Age)) {
				retValue = " EXME_Paciente.FechaNac ";
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Nacionality)) {
				retValue = " EXME_Paciente.EXME_Nacionalidad_ID ";
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Race)) {
				retValue = " EXME_Paciente.EXME_Paciente_id in (SELECT EXME_Paciente_id from EXME_Paciente_Raza WHERE exme_razas_ID";
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_EthnicGroup)) {
				retValue = " EXME_Paciente.EXME_GpoEtnico_ID ";
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Sex)) {
				retValue = " EXME_Paciente.Sexo ";
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Weight)) {
				retValue = " EXME_Paciente.Peso ";
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Height)) {
				retValue = " EXME_Paciente.Talla ";
			}
		} else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_LaboratoryTestResults)) {
			retValue = " EXME_EstudiosOBX.SystemCode, EXME_EstudiosOBX.CodeID, EXME_EstudiosOBX.Observation ";
		} else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicalCondition)) {
			retValue = " EXME_CondMedica.EXME_Diagnostico_ID ";
		} else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_SpecificPatientDiagnostic)) {
			retValue = " EXME_ActPacienteDiag.EXME_Diagnostico_ID ";
		} else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicationList)) {
			retValue = " M_Product.M_Product_ID ";
		} else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientAllergies)) {
			retValue = " EXME_PacienteAler.EXME_SActiva_ID ";
		}else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_CommunicationPreferences)) {
			retValue = " CP.DocumentNo ";
		}
		return retValue;
	}
	
	/**
	 * Se encarga de armar la condicion Where de la sentencia Sql evaluanto el tipo de condicion
	 * @param sql Sentencia de Sql a la que se le agregaran las condiciones de filtrado
	 * @param cdsRules Condicion que se va agregar a la sentencia Sql
	 * @param listParam Lista en la que se almacenen los valores que se deben de mandar como parametros al query
	 * @param conditionRule Almacena los tipos de condiciones que ya se agregaron a la regla
	 * @param ctx Contexto de la aplicacion
	 * @return boolean True indica que la antepenultima y ultima condicion fueron del mismo tipo
	 * @author mvrodriguez
	 */
	private static boolean conditions(StringBuilder whereClause, MEXMECDSRules cdsRules, List<Object> listParam, Map<Object, Object> conditionRule, Properties ctx) {
		
		BigDecimal value = new BigDecimal(0);
		
		//Indica que en la condicion se esta aplicando un in
		boolean moreValuesIn =  false;
			
		if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientDemographics)) {
			
			//Si la regla es de demograficos y de los campos Peso y Talla es necesario convertir el valor
			if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientDemographics)
					&& (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Weight)
							||cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Height))
			){
				if( MUser.convertirUnidades(ctx)){
					// convertimos el valor correspondiente
					value = MUOM.convertirMedida(ctx, cdsRules.getC_UOM_ID(), cdsRules.getDoubleValue(), MUser.getSistMedicionUsuario(ctx));
				}else{
					value = cdsRules.getDoubleValue();
				}
			}

			if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Age) 
					&& cdsRules.getIntValue()>=0){
				
				if(DB.isOracle()){
					whereClause.append(addWhereOfAges(false, cdsRules.getAgeUom()));
					
				}else{
					whereClause.append(addWhereOfAges(true, cdsRules.getAgeUom()));
				}
				whereClause.append(cdsRules.getComparisonOperator()).append(" ? ");				
				listParam.add(cdsRules.getIntValue());
				
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Nacionality) 
					&& cdsRules.getEXME_Nacionalidad_ID()>0){

				whereClause.append("           AND EXME_Paciente.EXME_Nacionalidad_id ");
				whereClause.append(cdsRules.getComparisonOperator()).append(" ? ");
				
				listParam.add(cdsRules.getEXME_Nacionalidad_ID());
				
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Race) 
					&& cdsRules.getEXME_Razas_ID()>0){
				
				whereClause.append("           AND EXME_Paciente.EXME_Paciente_id in (SELECT EXME_Paciente_id from EXME_Paciente_raza WHERE exme_razas_ID");
				whereClause.append(cdsRules.getComparisonOperator()).append(" ? ").append(")");
				
				listParam.add(cdsRules.getEXME_Razas_ID());
				
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_EthnicGroup) 
					&& cdsRules.getEXME_GpoEtnico_ID()>0){
				
				whereClause.append("           AND EXME_Paciente.EXME_GpoEtnico_ID ");
				whereClause.append(cdsRules.getComparisonOperator()).append(" ? ");
				
				listParam.add(cdsRules.getEXME_GpoEtnico_ID());
				
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Sex) 
					&& cdsRules.getCharValue()!= null){
				
				whereClause.append("           AND EXME_Paciente.sexo ");
				whereClause.append(cdsRules.getComparisonOperator()).append(" ? ");
				
				listParam.add(cdsRules.getCharValue());
				
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Weight) 
					&& value != null && value.compareTo(BigDecimal.ZERO)>0 ){
				
				whereClause.append("           AND EXME_Paciente.PESO ");						
				whereClause.append(cdsRules.getComparisonOperator()).append(" ? ");
				
				listParam.add(value);
				
			} else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Height)
					&& value != null && value.compareTo(BigDecimal.ZERO)>0){
				
				whereClause.append("           AND EXME_Paciente.TALLA  ");
				whereClause.append(cdsRules.getComparisonOperator()).append(" ? ");
				
				listParam.add(value);
				
			}
			
		}else if(cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_CommunicationPreferences)){
			if(cdsRules.getPatientPreference() == 1){
				whereClause.append("     AND  EXME_ConfigRecordatorio.isemail = ? ");
			}else if(cdsRules.getPatientPreference() == 2){
				whereClause.append("     AND  EXME_ConfigRecordatorio.isphone = ? ");
			}
			listParam.add("Y");
			
		}else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_LaboratoryTestResults)){
			
			//Puede haber mas de una condicion medica en la regla
			if(!conditionRule.containsKey(X_EXME_CDS_Rules.RULETYPE_LaboratoryTestResults)) {
				
				conditionRule.put(X_EXME_CDS_Rules.RULETYPE_LaboratoryTestResults, "true");
				
				whereClause.append("     AND ((EXME_EstudiosOBX.SystemCode = ? ");
				whereClause.append("     AND EXME_EstudiosOBX.CodeID = ? ");
				whereClause.append("     AND EXME_EstudiosOBX.Observation ").append(cdsRules.getComparisonOperator()).append(" ? ) ");
				
			} else {
				
				whereClause.append("     OR (EXME_EstudiosOBX.SystemCode = ? ");
				whereClause.append("     AND EXME_EstudiosOBX.CodeID = ? ");
				whereClause.append("     AND EXME_EstudiosOBX.Observation ").append(cdsRules.getComparisonOperator()).append(" ? ) ");
				
			}
			
			moreValuesIn =  true;
			
			listParam.add(cdsRules.getStringValue());
			listParam.add(cdsRules.getEXME_Loinc_ID());
			listParam.add(cdsRules.getDoubleValue());
			
		} else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicalCondition)){
			/* Como en la consulta de las condiciones de la regla se esta ordenando al final por comparisonoperator
			 * para que siempre realice primero la concatenacion de las que sean diferentes y posteriormente
			 * realice en in */
			if ("<>".equals(cdsRules.getComparisonOperator())) {
				whereClause.append(" AND EXME_CondMedica.EXME_Diagnostico_ID ").append(cdsRules.getComparisonOperator()).append(" ? ");
			}
			else {
				if ("=".equals(cdsRules.getComparisonOperator())) {
					// Puede haber mas de una condicion medica en la regla
					if (!conditionRule.containsKey(X_EXME_CDS_Rules.RULETYPE_MedicalCondition)) {
						conditionRule.put(X_EXME_CDS_Rules.RULETYPE_MedicalCondition, "true");
						whereClause.append(" AND EXME_CondMedica.EXME_Diagnostico_ID in(?");
					}
					else {
						whereClause.append(", ? ");
					}
					moreValuesIn = true;
				}
			}
			listParam.add(cdsRules.getEXME_DiagnosticoConMed_ID());
		}
		else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_SpecificPatientDiagnostic)) {
			/*
			 * Como en la consulta de las condiciones de la regla se esta ordenando al final por comparisonoperator
			 * para que siempre realice primero la concatenacion de las que sean diferentes y posteriormente
			 * realice en in
			 */
			if ("<>".equals(cdsRules.getComparisonOperator())) {

				whereClause.append(" AND EXME_ActPacienteDiag.EXME_Diagnostico_ID ").append(cdsRules.getComparisonOperator()).append(" ? ");
			}
			else {
				if ("=".equals(cdsRules.getComparisonOperator())) {
					// Puede haber mas de un Diagnostico Especifico para el Paciente
					if (!conditionRule.containsKey(X_EXME_CDS_Rules.RULETYPE_SpecificPatientDiagnostic)) {
						conditionRule.put(X_EXME_CDS_Rules.RULETYPE_SpecificPatientDiagnostic, "true");
						whereClause.append(" AND EXME_ActPacienteDiag.EXME_Diagnostico_ID in(?");
					}
					else {
						whereClause.append(", ? ");
					}
					moreValuesIn = true;
				}
			}
			listParam.add(cdsRules.getEXME_Diagnostico_ID());
		}
		else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicationList)) {
			int genId = new MEXMEProduct(ctx, cdsRules.getM_Product_ID(), null).getEXME_GenProduct_ID();
			/*
			 * Como en la consulta de las condiciones de la regla se esta ordenando al final por comparisonoperator
			 * para que siempre realice primero la concatenacion de las que sean diferentes y posteriormente
			 * realice en in
			 */
			if ("<>".equals(cdsRules.getComparisonOperator())) {
				whereClause.append(" AND EXME_MEDICATION_CCD.EXME_GENPRODUCT_ID ").append(cdsRules.getComparisonOperator()).append(" ? ");
			} else {
				if ("=".equals(cdsRules.getComparisonOperator())) {
					// Puede ser mas de un medicamento para el paciente
					if (!conditionRule.containsKey(X_EXME_CDS_Rules.RULETYPE_MedicationList)) {
						conditionRule.put(X_EXME_CDS_Rules.RULETYPE_MedicationList, "true");
						whereClause.append(" AND EXME_MEDICATION_CCD.EXME_GENPRODUCT_ID in(?");
					} else {
						whereClause.append(", ? ");
					}
					moreValuesIn = true;
				}
			}
			listParam.add(genId);

		} else if(cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientAllergies)) {
			/*
			 * Como en la consulta de las condiciones de la regla se esta ordenando al final por comparisonoperator
			 * para que siempre realice primero la concatenacion de las que sean diferentes y posteriormente
			 * realice en in
			 */
			if ("<>".equals(cdsRules.getComparisonOperator())) {
				whereClause
					.append(" AND EXME_PacienteAler.EXME_SActiva_ID ").append(cdsRules.getComparisonOperator()).append(" ? ");
			}
			else {
				if ("=".equals(cdsRules.getComparisonOperator())) {
					// Puede ser mas de un medicamento para el paciente
					if (!conditionRule.containsKey(X_EXME_CDS_Rules.RULETYPE_PatientAllergies)) {
						conditionRule.put(X_EXME_CDS_Rules.RULETYPE_PatientAllergies, "true");
						whereClause.append(" AND EXME_PacienteAler.EXME_SActiva_ID in(?");
					}
					else {
						whereClause.append(", ? ");
					}
					moreValuesIn = true;
				}
			}
			listParam.add(cdsRules.getEXME_SActiva_ID());
		}
		return moreValuesIn;
	}
	
	/**
	 * Define las tablas que deben de consultarse para cada regla
	 * @return Map Map con la regla como llave y como value un Map con sus respectivas tablas
	 * @author mvrodriguez
	 */
	private static Map<String, Map<String, String>> chargeTableForRuleType() {
		
		Map<String, Map<String, String>> tableRuleType = new TreeMap<String, Map<String, String>>();
		Map<String, String> table = new TreeMap<String, String>();
		
		table.put("EXME_Paciente", "EXME_Paciente");
		tableRuleType.put(X_EXME_CDS_Rules.RULETYPE_PatientDemographics, table);
		
		table = new TreeMap<String, String>();
		table.put("EXME_Paciente", "EXME_Paciente");
		table.put("EXME_ActPaciente", "EXME_ActPaciente");
		table.put("EXME_ActPacienteINDH", "EXME_ActPacienteINDH");
		table.put("EXME_ActPacienteIND", "EXME_ActPacienteIND");
		table.put("EXME_EstudiosOBX", "EXME_EstudiosOBX");
		tableRuleType.put(X_EXME_CDS_Rules.RULETYPE_LaboratoryTestResults, table);
		
		table = new TreeMap<String, String>();
		table.put("EXME_Paciente", "EXME_Paciente");
		table.put("EXME_CondMedica", "EXME_CondMedica");
		tableRuleType.put(X_EXME_CDS_Rules.RULETYPE_MedicalCondition, table);
		
		table = new TreeMap<String, String>();
		table.put("EXME_Paciente", "EXME_Paciente");
		table.put("EXME_ActPaciente", "EXME_ActPaciente");
		table.put("EXME_ActPacienteDiag", "EXME_ActPacienteDiag");
		tableRuleType.put(X_EXME_CDS_Rules.RULETYPE_SpecificPatientDiagnostic, table);
		
		table = new TreeMap<String, String>();
		table.put("EXME_Paciente", "EXME_Paciente");
		table.put("EXME_ActPaciente", "EXME_ActPaciente");
		table.put("EXME_ActPacienteINDH", "EXME_ActPacienteINDH");
		table.put("EXME_ActPacienteIND", "EXME_ActPacienteIND");
		table.put("EXME_CTAPAC", "EXME_CTAPAC");
		table.put("EXME_PLANMED", "EXME_PLANMED");
		table.put("M_Product", "M_Product");
		table.put("EXME_PrescRX", "EXME_PrescRX");
		table.put("EXME_PrescRXDet", "EXME_PrescRXDet");
		tableRuleType.put(X_EXME_CDS_Rules.RULETYPE_MedicationList, table);
		
		table = new TreeMap<String, String>();
		table.put("EXME_Paciente", "EXME_Paciente");
		table.put("EXME_PacienteAler", "EXME_PacienteAler");
		tableRuleType.put(X_EXME_CDS_Rules.RULETYPE_PatientAllergies, table);
		
		table = new TreeMap<String, String>();
		table.put("EXME_ConfigRecordatorio", "EXME_ConfigRecordatorio");
		tableRuleType.put(X_EXME_CDS_Rules.RULETYPE_CommunicationPreferences, table);
		
		return tableRuleType;
		
	}
	/**
	 * Agrega el where correspondiente para la busqueda de edades
	 * @param isPostgres
	 * @param ageType valor seleccionado en la lista 1 year, 2 month, 3 days
	 * @return
	 */
	public static String addWhereOfAges(boolean isPostgres, int ageType){
		StringBuilder whereClause = new StringBuilder();
		if(isPostgres){
			switch (ageType) {
			//Years
			case 1:
				whereClause.append(" AND EXME_Paciente.FechaNac is not null ");
				whereClause.append(" AND (DATE_PART('year', AGE(").append(DB.TO_DATE(DB.getTSForOrg(Env.getCtx()))).append(", EXME_Paciente.FECHANAC))) ");
				break;
			
			//Months
			case 2:
				//Obtener años y multiplicar por la cantidad de meses(12) sumar los meses que faltan
				whereClause.append(" AND EXME_Paciente.FechaNac is not null ");
				whereClause.append(" AND DATE_PART('year', AGE((").append(DB.TO_DATE(DB.getTSForOrg(Env.getCtx()))).append(" ), EXME_Paciente.FECHANAC))*12 + ");
				whereClause.append(" DATE_PART('month', AGE((").append(DB.TO_DATE(DB.getTSForOrg(Env.getCtx()))).append(" ), EXME_Paciente.FECHANAC)) ");
				break;
				
			//Days
			case 3:
				whereClause.append(" AND EXME_Paciente.FechaNac is not null ");
				//60seg x 60min x24hrs = 86400; obtiene dias
				whereClause.append(" AND ((extract (epoch from (").append(DB.TO_DATE(DB.getTSForOrg(Env.getCtx()))).append("- EXME_Paciente.FECHANAC)))/86400)::integer ");
				break;
			}
		//Oracle 
		}else{
			//TODO meses y dias; no hay BD oracle 13/ene/2013
			whereClause.append(" AND EXME_Paciente.FechaNac is not null ");
			whereClause.append(" AND TRUNC(months_between(").append(DB.TO_DATE(DB.getTSForOrg(Env.getCtx()))).append(", EXME_Paciente.FECHANAC) / 12) ");

		}
		return whereClause.toString();
	}
	

	/**
	 * Metodo que retorna una lista de exme_paciente_id que cumplen 
	 * con una regla especifica
	 * @author Lizeth de la Garza
	 * @param cdsID
	 * @param ctx
	 * @return
	 */
	public static List<Integer> ruleComplianPatient(int cdsID, Properties ctx) {


		List<MEXMECDS> list = MEXMECDS.getCDSLst(ctx, null);
		@SuppressWarnings("unused")
		Iterator<MEXMECDS> iterCDS = list.iterator();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> patientList = new ArrayList<Integer>();
		//banderas por tipo de regla
		boolean patientDemo = false;
		boolean medicalCond = false;
		boolean patientDiag = false;
		boolean medicationList = false;
		boolean testResult = false;

		//bandera cumplio condiciones para campo de PatientDemographics
		boolean demoField = false;
		//Obtenemos la lista de Reglas para el CDS que se itera
		List<MEXMECDSRules> listRules = MEXMECDSRules.getCDSRulesLst(ctx, null, cdsID);

		boolean intersect = false;
		String actualRule = "";

		//iteramos solo si tiene reglas definidas para CDS
		if (listRules != null) {

			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT EXME_Paciente_ID ");
			sql.append("  FROM (");

			//Lista de Objetos para almacenar los SETTERS del sql
			List<Object> listaSetters = new ArrayList<Object>();

			//iteramos las reglas de CDS actual
			for (Iterator<MEXMECDSRules> iterRules = listRules.iterator(); iterRules.hasNext(); ) {
				MEXMECDSRules cdsRules = (MEXMECDSRules) iterRules.next();
				BigDecimal valor = new BigDecimal(0);

				//Si existe registro pero sin tipo de regla salimos
				if (cdsRules.getRuleType() == null){
					break;
				}

				//Si la regla es de demograficos y de los campos Peso y Talla es necesario convertir el valor
				if (cdsRules.getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_PatientDemographics)
						&& (cdsRules.getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Weight)
								|| cdsRules.getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Height))
				){
					if ( MUser.convertirUnidades(ctx)) {
						// convertimos el valor correspondiente
						valor = MUOM.convertirMedida(ctx, cdsRules.getC_UOM_ID(), cdsRules.getDoubleValue(), MUser.getSistMedicionUsuario(ctx));
					} else {
						valor = cdsRules.getDoubleValue();
					}
				}

				//Se itera ordenado por RuleType
				//Si RuleType actual es diferente de PatientDemographics
				//y ya se entro a una condicion de PatientDemographics
				//cerramos bloque sql para PatientDemographics
				actualRule = cdsRules.getRuleType();
				if (!actualRule.equalsIgnoreCase(MEXMECDSRules.RULETYPE_PatientDemographics) && patientDemo) {
					//Si entro a PatientDemographics
					//Pero no cumplio condiciones de DemoField
					//forzar el no retorno
					if (!demoField) {
						sql.append(" AND 1=0 ");
					}			
				}else if (!actualRule.equalsIgnoreCase(MEXMECDSRules.RULETYPE_MedicalCondition) && medicalCond){
					sql.append(" )");
					medicalCond = false;
				}else if (!actualRule.equalsIgnoreCase(MEXMECDSRules.RULETYPE_SpecificPatientDiagnostic) && patientDiag){
					sql.append(" )");
					patientDiag = false;
				}else if (!actualRule.equalsIgnoreCase(MEXMECDSRules.RULETYPE_MedicationList) && medicationList ){
					sql.append(" ) ");
					medicationList = false;
				}else if (!actualRule.equalsIgnoreCase(MEXMECDSRules.RULETYPE_LaboratoryTestResults) && testResult ){					
					sql.append(" AND EXME_ActPacienteINDH.IsService = 'Y')");
					testResult = false;
				}

				/*
				 * Bloque Datos Demográficos del Paciente
				 */
				if (cdsRules.getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_PatientDemographics)) {
					//Si no se ha entrado en PatientDemographics
					//Se "inicializa" bloque sql
					if (!patientDemo) {
						if (intersect) {
							sql.append(" INTERSECT ");
						}
						sql.append("       SELECT EXME_Paciente_ID ");
						sql.append("          FROM EXME_Paciente ");
						sql.append("         WHERE 1 = 1");
						patientDemo = true;
					}
					//Si existe un registro de tipo PatientDemographics
					//Pero sin campo Demografico
					//Forzar no retorno de resultados
					if (cdsRules.getDemoField() == null ) {
						//nos salimos sin regresar
						sql.append("         AND 1 = 0");
						break;
					} else if (cdsRules.getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Age) 
							&& cdsRules.getIntValue() >= 0) {
						demoField = true;
						sql.append("           AND TRUNC(months_between(").append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append(", EXME_Paciente.FECHANAC) / 12) ");
						sql.append(" ").append(cdsRules.getComparisonOperator()).append(" ? ");
						listaSetters.add(cdsRules.getIntValue());
					} else if (cdsRules.getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Nacionality) 
							&& cdsRules.getEXME_Nacionalidad_ID() > 0) {
						demoField = true;
						sql.append("           AND EXME_Paciente.EXME_Nacionalidad_id = ? ");
						listaSetters.add(cdsRules.getEXME_Nacionalidad_ID());
					} else if (cdsRules.getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Race) 
							&& cdsRules.getEXME_Razas_ID() > 0) {
						demoField = true;
						sql.append("           AND EXME_Paciente.EXME_Paciente_id in (SELECT EXME_Paciente_id from EXME_Paciente_Raza WHERE exme_razas_ID = ? ) ");
						listaSetters.add(cdsRules.getEXME_Razas_ID());
					} else if (cdsRules.getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_EthnicGroup) 
							&& cdsRules.getEXME_GpoEtnico_ID() > 0) {
						demoField = true;
						sql.append("           AND EXME_Paciente.EXME_GpoEtnico_ID = ? ");
						listaSetters.add(cdsRules.getEXME_GpoEtnico_ID());
					} else if (cdsRules.getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Sex) 
							&& cdsRules.getCharValue()!= null){
						demoField = true;
						sql.append("           AND EXME_Paciente.sexo = ? ");
						listaSetters.add(cdsRules.getCharValue());
					} else if (cdsRules.getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Weight) 
							&& valor.compareTo(BigDecimal.ZERO) >= 0 ) {
						demoField = true;
						sql.append("           AND EXME_Paciente.PESO  ");						
						sql.append(" ").append(cdsRules.getComparisonOperator()).append(" ? ");
						listaSetters.add(valor);
					} else if (cdsRules.getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Height)
							&& valor.compareTo(BigDecimal.ZERO) >= 0) {
						demoField = true;
						sql.append("           AND EXME_Paciente.TALLA  ");
						sql.append(" ").append(cdsRules.getComparisonOperator()).append(" ? ");
						listaSetters.add(valor);
					}

					/*
					 * Bloque Condición Médica del Paciente
					 */	
				} else if (cdsRules.getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_MedicalCondition) 
						&& cdsRules.getEXME_DiagnosticoConMed_ID() > 0) {
					if (!medicalCond) {
						if (intersect) {
							sql.append(" INTERSECT ");
						}
						sql.append(" SELECT EXME_Paciente_ID ");
						sql.append("    FROM EXME_CondMedica ");
						sql.append("   WHERE  EXME_CondMedica.EXME_Diagnostico_ID in(? ");
						medicalCond = true;
						intersect = true;
					} else {
						sql.append("  ,? ");
					}
					listaSetters.add(cdsRules.getEXME_DiagnosticoConMed_ID());
				}
				/*
				 * Bloque Diagnósticos del Paciente
				 */
				else if (cdsRules.getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_SpecificPatientDiagnostic) 
						&& cdsRules.getEXME_Diagnostico_ID() > 0) {
					if (!patientDiag) {
						if (intersect) {
							sql.append(" INTERSECT ");
						}
						patientDiag = true;
						sql.append(" SELECT EXME_ActPaciente.EXME_Paciente_ID ");
						sql.append("    FROM EXME_ActPacienteDiag ");
						sql.append("    INNER JOIN EXME_ActPaciente ");
						sql.append("       ON EXME_ActPaciente.EXME_ActPaciente_ID = ");
						sql.append("          EXME_ActPacienteDiag.EXME_ActPaciente_ID ");
						sql.append("    WHERE  EXME_ActPacienteDiag.EXME_Diagnostico_ID in(? ");
						intersect = true;
					} else {
						sql.append("      ,? ");
					}
					listaSetters.add(cdsRules.getEXME_Diagnostico_ID());
				}
				/*
				 * Bloque Medicamentos prescritos del Paciente
				 */
				else if (cdsRules.getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_MedicationList) 
						&& cdsRules.getM_Product_ID() > 0) {
					if (!medicationList) {
						if (intersect) {
							sql.append(" INTERSECT ");
						}
						medicationList = true;
						sql.append(" SELECT EXME_Paciente_ID ");
						sql.append("    FROM EXME_ActPaciente ");
						sql.append("    INNER JOIN EXME_ActPacienteINDH ");
						sql.append("       ON EXME_ActPacienteINDH.EXME_ActPaciente_ID = ");
						sql.append("          EXME_ActPaciente.EXME_ActPaciente_ID ");
						sql.append("    INNER JOIN EXME_ActPacienteIND ");
						sql.append("       ON EXME_ActPacienteIND.EXME_ActPacienteINDH_ID = ");
						sql.append("          EXME_ActPacienteINDH.EXME_ActPacienteINDH_ID ");
						sql.append("    INNER JOIN M_Product ");
						sql.append("       ON M_Product.M_Product_ID = EXME_ActPacienteIND.M_Product_ID ");
						sql.append("    WHERE  EXME_ActPacienteINDH.IsService = 'N' ");
						sql.append("      AND EXME_ActPacienteINDH.EXME_Tratamientos_ID is null ");
						sql.append("      AND M_Product.M_Product_ID in(? ");
						intersect = true;
					} else {
						sql.append("      ,? ");
					}
					listaSetters.add(cdsRules.getM_Product_ID());
				}
				/*
				 * Bloque observaciones de estudios del Paciente
				 */
				else if (cdsRules.getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_LaboratoryTestResults) 
						&& cdsRules.getEXME_Loinc_ID() > 0) {

					if (!testResult) {
						if (intersect) {
							sql.append(" INTERSECT ");
						}
						testResult = true;
						sql.append(" SELECT EXME_Paciente_ID");
						sql.append("    FROM EXME_ActPaciente ");
						sql.append("   INNER JOIN EXME_ActPacienteINDH ");
						sql.append("      ON EXME_ActPacienteINDH.EXME_ActPaciente_ID = ");
						sql.append("         EXME_ActPaciente.EXME_ActPaciente_ID ");
						sql.append("   INNER JOIN EXME_ActPacienteIND ");
						sql.append("      ON EXME_ActPacienteIND.EXME_ActPacienteINDH_ID = ");
						sql.append("         EXME_ActPacienteINDH.EXME_ActPacienteINDH_ID ");
						sql.append("   INNER JOIN EXME_EstudiosOBX ");
						sql.append("      ON EXME_EstudiosOBX.AD_Client_ID = EXME_ActPacienteINDH.AD_Client_ID ");
						sql.append("     AND EXME_EstudiosOBX.AD_Org_ID = EXME_ActPacienteINDH.AD_Org_ID ");
						sql.append("     AND EXME_EstudiosOBX.EXME_ActPacienteIND_ID = ");
						sql.append("         EXME_ActPacienteIND.EXME_ActPacienteIND_ID ");
						sql.append("     AND (EXME_EstudiosOBX.SystemCode = ? ");
						sql.append("     AND EXME_EstudiosOBX.CodeID = ? ");
						sql.append("     AND EXME_EstudiosOBX.Observation ").append(cdsRules.getComparisonOperator()).append(" ?) ");
						intersect = true;
					} else {
						sql.append("     OR (EXME_EstudiosOBX.SystemCode = ? ");
						sql.append("     AND EXME_EstudiosOBX.CodeID = ? ");
						sql.append("     AND EXME_EstudiosOBX.Observation ").append(cdsRules.getComparisonOperator()).append(" ?) ");
					}
					listaSetters.add(cdsRules.getStringValue());
					listaSetters.add(cdsRules.getEXME_Loinc_ID());
					listaSetters.add(cdsRules.getDoubleValue());					
				}
			}				

			//Si por algun motivo no cayo en ninguna condicion forzamos a no regresar nada
			//de otro modo agregamos las condiciones creadas
			if (!patientDemo && !medicalCond && !patientDiag && !medicationList && !testResult) {
				sql.append("   AND 1 = 0 ");
			} 
			sql.append(")");
			if (actualRule.equalsIgnoreCase(MEXMECDSRules.RULETYPE_SpecificPatientDiagnostic) || 
					actualRule.equalsIgnoreCase(MEXMECDSRules.RULETYPE_MedicationList) ||
						actualRule.equalsIgnoreCase(MEXMECDSRules.RULETYPE_MedicalCondition)) {
				sql.append(")");
			}
			try{
				pstmt = DB.prepareStatement(sql.toString(), null);
				//Iteramos la lista donde se almacenan
				//ordenadamente los SETTERs para la ejecucion de SQL
				//Evaluando el tipo de objeto para realizar
				//el set adecuado
				for (Integer i = 0; i < listaSetters.size(); i++) {
					if (listaSetters.get(i) instanceof Integer) {
						pstmt.setInt(i + 1, (Integer)listaSetters.get(i));
					} else if (listaSetters.get(i) instanceof BigDecimal) {
						pstmt.setBigDecimal(i + 1, (BigDecimal)listaSetters.get(i));
					}else if (listaSetters.get(i) instanceof String) {
						pstmt.setString(i + 1, String.valueOf(listaSetters.get(i)));
					}
				}
				rs = pstmt.executeQuery();
				//Si regresa resultado agregamos a la lista el EXME_CDS
				while (rs.next()) {
					patientList .add(rs.getInt("EXME_Paciente_ID"));
				}
			} catch(Exception e) {
				s_log.log(Level.SEVERE, "getCDSLst", e);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}

			demoField = false;
		}
		return patientList;
	}
	public static StringBuilder rulesLabels(int cdsID, Properties ctx) {
		
		StringBuilder labels = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		String saltoLinea  = "<br />";
		String iniListaIni = "<ul><li>";
		//String iniLista	   = "<ul>";
		String finLista    = "</ul>";
		String listaIni    = "<li>";
		String listaFin	   = "</li>";
		//String listaFinIni = "</li><li>";
		//String finListaFin = "</li></ul>";
		String iniNegrita  = "<b>";	
		String finNegrita  = "</b>";
		String noAplica	   = "N/A";	
		//banderas por tipo de regla
		boolean patientDemo = false;
		boolean medicalCond = false;
		boolean patientDiag = false;
		boolean medicationList = false;
		boolean testResult = false;
		//bandera entro regla PatientDemographics
		boolean demoClose = false;
		boolean medicalClose = false;
		boolean diagClose = false;
		boolean medicationClose = false;
		boolean testClose = false;
		//bandera cumplio condiciones para campo de PatientDemographics
		@SuppressWarnings("unused")
		boolean demoField = false;
		
		boolean allergy =  false;
		boolean allergyClose = false;
		
		int seqNoPrev = -1;
		int seqNo = 1;
		
		//iteramos las CDS activas
		
		//Obtenemos la lista de Reglas para el CDS que se itera
		List<MEXMECDSRules> listRules = MEXMECDSRules.getCDSRulesLst(ctx, null, cdsID);
		
		//iteramos solo si tiene reglas definidas para CDS
		if (listRules != null){
			
			StringBuilder label = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			//iteramos las reglas de CDS actual
			for (Iterator<MEXMECDSRules> iterRules = listRules.iterator(); iterRules.hasNext(); ){
				MEXMECDSRules cdsRules = (MEXMECDSRules) iterRules.next();
				BigDecimal valor = new BigDecimal(0);
				
				seqNo = cdsRules.getSeqNo();
				
				//Si RuleType actual es diferente cerramos bloque lista
				String actualRule = cdsRules.getRuleType();
				if (!actualRule.equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientDemographics) && patientDemo  && !demoClose){
					label.append(finLista);
					demoClose = true;
				}else if (!actualRule.equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicalCondition) && medicalCond && !medicalClose){
					label.append(finLista);
					medicalClose = true;
				}else if (!actualRule.equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_SpecificPatientDiagnostic) && patientDiag && !diagClose){
					label.append(finLista);
					diagClose = true;
				}else if (!actualRule.equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicationList) && medicationList && !medicationClose){
					label.append(finLista);
					medicationClose = true;
				}else if (!actualRule.equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_LaboratoryTestResults) && testResult && !testClose ){
					label.append(finLista);
					testClose = true;
				}// mvrodriguez 08/11/2010
				else if (!actualRule.equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientAllergies) && allergy && !allergyClose ){
					label.append(finLista);
					allergyClose = true;
				}
				
				if(seqNoPrev != seqNo) {
					
					demoField = false;
					medicalCond = false;
					patientDiag = false;
					medicationList = false;
					testResult = false;
					allergy = false;
					
					demoClose = false;
					medicalClose = false;
					diagClose = false;
					medicationClose = false;
					testClose = false;
					allergyClose = false;
					
				}
				
				//Si existe registro pero sin tipo de regla salimos
				if (cdsRules.getRuleType() == null){
					break;
				}
				//Si la regla es de demograficos y de los campos Peso y Talla es necesario convertir el valor
				if (cdsRules.getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_PatientDemographics)
						&& (cdsRules.getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Weight)
								|| cdsRules.getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Height))
				){
					if ( MUser.convertirUnidades(ctx)) {
						// convertimos el valor correspondiente
						valor = MUOM.convertirMedida(ctx, cdsRules.getC_UOM_ID(), cdsRules.getDoubleValue(), MUser.getSistMedicionUsuario(ctx));
					} else {
						valor = cdsRules.getDoubleValue();
					}
				}
				
				//Se itera ordenado por RuleType
				/*
				 * Bloque Datos Demográficos del Paciente
				 */
				if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientDemographics)){
					//Si no se ha entrado en PatientDemographics
					//Se "inicializa" bloque sql
					if (!patientDemo){
						label.append(iniNegrita);
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "PatientDemographic") );
						label.append(finNegrita);
						label.append(saltoLinea);
						label.append(iniListaIni);
						patientDemo = true;
					}else{
						label.append(listaIni);
					}
					//Si existe un registro de tipo PatientDemographics
					//Pero sin campo Demografico
					//Forzar no retorno de resultados
					if(cdsRules.getDemoField() == null ){
						//nos salimos sin regresar
						label.append(noAplica);
						label.append(listaFin);
						break;
					}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Age) 
							&& cdsRules.getIntValue()>=0){
						demoField = true;
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "Age"));
						label.append(" ");
						label.append(MRefList.getListName(ctx, 
																	MEXMECDSRules.COMPARISONOPERATOR_AD_Reference_ID, 
																	cdsRules.getComparisonOperator()));
						label.append(" ");
						label.append(cdsRules.getIntValue());
						label.append(listaFin);
					}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Nacionality) 
							&& cdsRules.getEXME_Nacionalidad_ID()>0){
						demoField = true;
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "Nacionality"));
						label.append(" ");
						label.append(new MEXMENacionalidad(ctx, cdsRules.getEXME_Nacionalidad_ID(), null).getValue());
						label.append(listaFin);
					}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Race) 
							&& cdsRules.getEXME_Razas_ID()>0){
						demoField = true;
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "Race"));
						label.append(" ");
						label.append(new X_EXME_Razas(ctx, cdsRules.getEXME_Razas_ID(), null).getName());
						label.append(listaFin);
					}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_EthnicGroup) 
							&& cdsRules.getEXME_GpoEtnico_ID()>0){
						demoField = true;
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "EthnicGroup"));
						label.append(" ");
						label.append(new X_EXME_GpoEtnico(ctx, cdsRules.getEXME_Razas_ID(), null).getName());
						label.append(listaFin);
					}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Sex) 
							&& cdsRules.getCharValue()!= null){
						demoField = true;
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "Sex"));
						label.append(" ");
						label.append(MRefList.getListName(ctx, 
								MEXMECDSRules.CHARVALUE_AD_Reference_ID, 
								cdsRules.getCharValue()));
						label.append(listaFin);
					}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Weight) 
							&& valor.compareTo(BigDecimal.ZERO)>=0 ){
						demoField = true;
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "Weight"));
						label.append(" ");
						label.append(MRefList.getListName(ctx, 
																	MEXMECDSRules.COMPARISONOPERATOR_AD_Reference_ID, 
																	cdsRules.getComparisonOperator()));
						label.append(" ");
						label.append(valor);
						label.append(listaFin);
					}else if (cdsRules.getDemoField().equalsIgnoreCase(X_EXME_CDS_Rules.DEMOFIELD_Height)
							&& valor.compareTo(BigDecimal.ZERO)>0){
						demoField = true;
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "Height"));
						label.append(" ");
						label.append(MRefList.getListName(ctx, 
																	MEXMECDSRules.COMPARISONOPERATOR_AD_Reference_ID, 
																	cdsRules.getComparisonOperator()));
						label.append(" ");
						label.append(valor);
						label.append(listaFin);
					}
					
				/*
				 * Bloque Condición Médica del Paciente
				 */	
				}else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicalCondition) 
						&& cdsRules.getEXME_DiagnosticoConMed_ID() > 0){
					if (!medicalCond){
						medicalCond = true;
						label.append(iniNegrita);
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "MedicalCondition") );
						label.append(finNegrita);
						label.append(saltoLinea);
						label.append(iniListaIni);
					}else{
						label.append(listaIni);
					}
					label.append(new MDiagnostico(ctx, cdsRules.getEXME_DiagnosticoConMed_ID(), null).getName());
					label.append(listaFin);
				}
				/*
				 * Bloque Diagnósticos del Paciente
				 */
				 else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_SpecificPatientDiagnostic) 
						 && cdsRules.getEXME_Diagnostico_ID() > 0){
					if (!patientDiag){
						patientDiag = true;
						label.append(iniNegrita);
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "PatientDiagnostic") );
						label.append(finNegrita);
						label.append(saltoLinea);
						label.append(iniListaIni);
					}else{
						label.append(listaIni);
					}
					label.append(new MDiagnostico(ctx, cdsRules.getEXME_Diagnostico_ID(), null).getName());
					label.append(listaFin);
				}
				/*
				 * Bloque Medicamentos prescritos del Paciente
				 */
				 else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_MedicationList) 
						 && cdsRules.getM_Product_ID() > 0){
					if (!medicationList){
					    medicationList = true;
					    label.append(iniNegrita);
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "Medication") );
						label.append(finNegrita);
						label.append(saltoLinea);
						label.append(iniListaIni);
					}else{
						label.append(listaIni);
					}
					label.append(new MProduct(ctx, cdsRules.getM_Product_ID(), null).getName());
					label.append(listaFin);			
				}
				/*
				 * Bloque observaciones de estudios del Paciente
				 */
				 else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_LaboratoryTestResults) 
						 && cdsRules.getEXME_Loinc_ID()>0){
					 
					 if (!testResult){
						testResult = true;
						label.append(iniNegrita);
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "DiagnosticTest") );
						label.append(finNegrita);
						label.append(saltoLinea);
						label.append(iniListaIni);
					}else{
						label.append(listaIni);
					}
					label.append(new X_EXME_Loinc(ctx, cdsRules.getEXME_Loinc_ID(), null).getShortName());
					label.append(" ");
					label.append(MRefList.getListName(ctx, 
							MEXMECDSRules.COMPARISONOPERATOR_AD_Reference_ID, 
							cdsRules.getComparisonOperator()));
					label.append(" ");
					label.append(cdsRules.getDoubleValue().doubleValue());
					label.append(listaFin);			
				}
				/*
				 * mvrodriguez 08/11/2010
				 * Bloque alergias de paciente
				 */
				 else if (cdsRules.getRuleType().equalsIgnoreCase(X_EXME_CDS_Rules.RULETYPE_PatientAllergies) 
						 && cdsRules.getEXME_SActiva_ID() > 0){
					if (!medicationList){
					    medicationList = true;
					    label.append(iniNegrita);
						label.append(Msg.getMsg(Env.getAD_Language(ctx), "PatientAllergy") );
						label.append(finNegrita);
						label.append(saltoLinea);
						label.append(iniListaIni);
					}else{
						label.append(listaIni);
					}
					label.append(new MSActiva(ctx, cdsRules.getEXME_SActiva_ID(), null).getName());
					label.append(listaFin);			
				}
				
				seqNoPrev = seqNo;
				
			}			
			//Si solo existe una regla evaluar 
			//para cerrar el bloque sql  que 
			//quedo sin cerrar
			if ((!demoClose  && patientDemo)||(!medicalClose && medicalCond)||(!diagClose && patientDiag)
				||(!medicationClose && medicationList)||(!testClose && testResult)||(!allergy && allergyClose)){
				label.append(finLista);
			}	
			
			//Reiniciamos banderas
			patientDemo = false;
			medicalCond = false;
			patientDiag = false;
			medicationList = false;
			testResult = false;
			demoClose = false;
			medicalClose = false;
			diagClose = false;
			medicationClose = false;
			testClose = false;
			demoField = false;
			labels.append(label);
			
			allergy = false;
			allergyClose = false;
			
		}
		
		return labels;
	}
	
	/**
	 *  Eval if not exists a detail
	 *  of the Rule
	 *  @return true (to avoid delete)
	 */
	@Override
	protected boolean beforeDelete() {
		List<MEXMECDSRules> rules = MEXMECDSRules.getCDSRulesLst(getCtx(), get_TrxName(), get_ID());//Debe llevar TrxName!!
		if (!rules.isEmpty()){
			log.saveError("CDSFailDelete",Msg.getMsg(Env.getAD_Language(getCtx()), "CDS"));
			return false;
		}
		return true;
	}
	
	private List<MEXMECDSRules> CDSRulesLst = null;
	
	public void setCDSRulesLst(List<MEXMECDSRules> rules) {
		this.CDSRulesLst = rules;
	}
	/**
	 * CDS Rules
	 * @return
	 */
	public List<MEXMECDSRules> getCDSRulesLst(boolean onlyIsActive) {
		return getCDSRulesLst(false, onlyIsActive);
	}
	/**
	 * CDS Rules
	 * @return
	 */
	public List<MEXMECDSRules> getCDSRulesLst(boolean requery, boolean onlyIsActive) {
		if (CDSRulesLst == null || requery) {
			setCDSRulesLst(MEXMECDSRules.getCDSRulesLst(getCtx(), get_TrxName(), getEXME_CDS_ID(), onlyIsActive));
		}
		return CDSRulesLst;
	}
	
	/**
	 * Guarda las anotaciones <br>
	 * Modified by @author lorena lama <br>
	 * Correccion de etiquetas, <br>
	 * se cambia el metodo para ser utilizado desde Action/ADForm <br>
	 * para quitar codigo duplicado.
	 */
	public static String save(Properties ctx, List<CDSView> views, int AD_Table_ID, int Record_ID,
			int AD_TableProcess_ID, int EXME_Paciente_ID, String trxName) {

		try {
			MMessage msg = MMessage.get(ctx, "CDS");
			int AD_Message_ID = msg == null ? 0 : msg.getAD_Message_ID();
			String AD_Language = Env.getAD_Language(ctx);

			for (CDSView view : views) {
				MEXMEMejoras configMejoras = MEXMEMejoras.get(ctx, null);
				if ((configMejoras != null && configMejoras.isMDS() && configMejoras.isReqMDSResp())
						&& (StringUtils.isBlank(view.getResponse()))) {
					return getMessage(ctx, null, "error.cds.comments", view.getCds().getDescription());
				}

				StringBuilder reference = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

				if (Env.getEXME_Medico_ID(ctx) > 0) {
					MEXMEMedico medico = new MEXMEMedico(ctx, Env.getEXME_Medico_ID(ctx), null);
					reference.append(Msg.translate(AD_Language, "EXME_Medico_ID")).append(": ").append(medico.getNombre_Med());
					medico = null;
					reference.append("\n");
				}
				if (Env.getEXME_Enfermeria_ID(ctx) > 0) {
					MEXMEEnfermeria enfermeria = new MEXMEEnfermeria(ctx, Env.getEXME_Enfermeria_ID(ctx), null);
					reference.append(Msg.translate(AD_Language, "EXME_Enfermeria_ID")).append(": ");
					reference.append(enfermeria.getNombre_Enf());
					/*reference.append(enfermeria.getName());
					reference.append(enfermeria.getNombre2() != null ? (" " + enfermeria.getNombre2()) : "");
					reference.append(" " + enfermeria.getApellido1());
					reference.append(enfermeria.getApellido2() != null ? (" " + enfermeria.getApellido2()) : "");*/
					enfermeria = null;
					reference.append("\n");
				}
				reference.append(Msg.translate(AD_Language, " Trigger Process")).append(" : ");
				reference.append(Msg.translate(AD_Language, MTable.getTableName(ctx, AD_TableProcess_ID)));

				MNote note = new MNote(ctx, AD_Message_ID, //
						Env.getAD_User_ID(ctx), AD_Table_ID, Record_ID, //
						reference.toString(), view.getResponse(), //
						EXME_Paciente_ID, view.getCds().getEXME_CDS_ID(), trxName);

				if (!note.save()) {
					return getMessage(ctx, null, "error.cds.save", view.getCds().getDescription());
				}
			}

		} catch (Exception ex) {
			s_log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
		}

		return null;

	}
	
	public static interface OrderSelection {
		public void show(ValueName... names);
		public ValueName getOrder();
	}
	
}
