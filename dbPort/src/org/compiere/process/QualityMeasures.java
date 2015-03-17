package org.compiere.process;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MActPacienteDiag;
import org.compiere.model.MDiagnostico;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEGenQualityMeasure;
import org.compiere.model.MEXMEGenQualityMeasureDet;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEParamMetricas;
import org.compiere.model.MPlanMed;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Utilerias;

/**
 * 
 * @author Lizeth de la Garza
 *
 */
public class QualityMeasures {
	
	private MEXMEParamMetricas params = null;
	private Properties ctx;
	
	private final static String PQRI_NUMBERSTK2 = "0435";	
	private final static String FILESTK2 = "NQF_0435";
	private final static String PQRI_NUMBERSTK3 = "0436";	
	private final static String FILESTK3 = "NQF_0436";
	private final static String PQRI_NUMBERSTK4 = "0437";	
	private final static String FILESTK4 = "NQF_0437";
	private final static String PQRI_NUMBERSTK5 = "0438";	
	private final static String FILESTK5 = "NQF_0438";
	private final static String PQRI_NUMBERSTK6 = "0439";	
	private final static String FILESTK6 = "NQF_0439";
	private final static String PQRI_NUMBERSTK8 = "0440";	
	private final static String FILESTK8 = "NQF_0440";
	private final static String PQRI_NUMBERSTK10 = "0441";	
	private final static String FILESTK10 = "NQF_0441";
	private final static String PQRI_NUMBERVTE1 = "0371";	
	private final static String FILEVTE1 = "NQF_0371";
	private final static String PQRI_NUMBERVTE2 = "0372";	
	private final static String FILEVTE2 = "NQF_0372";
	private final static String PQRI_NUMBERVTE3 = "0373";	
	private final static String FILEVTE3 = "NQF_0373";
	private final static String PQRI_NUMBERVTE4 = "0374";	
	private final static String FILEVTE4 = "NQF_0373";
	private final static String PQRI_NUMBERVTE5 = "0375";	
	private final static String FILEVTE5 = "NQF_0375";
	private final static String PQRI_NUMBERVTE6 = "0376";	
	private final static String FILEVTE6 = "NQF_0376";
	private final static String PQRI_NUMBERED1A = "0495_1.1";
	private final static String PQRI_NUMBERED1B = "0495_1.2";
	private final static String PQRI_NUMBERED1C = "0495_1.3";
	private final static String FILEED1 = "NQF_0495";
	private final static String PQRI_NUMBERED2A = "0497_1.1";
	private final static String PQRI_NUMBERED2B = "0497_1.2";
	private final static String PQRI_NUMBERED2C = "0497_1.3";
	private final static String FILEED2 = "NQF_0497";
	
	private String measureGroup = "X";
	private int numFiles = 1;
	private int numFile = 1;
	private String optionRegistry="";
	private String version = "";
	private String trxName = null;
	private int folio = 0;
	
	private static CLogger log = CLogger.getCLogger(QualityMeasures.class);
	
	public QualityMeasures(Properties ctx, String trxName) {
		this.ctx = ctx;
		params = MEXMEParamMetricas.getParamMetricas(ctx, null);
		this.trxName = trxName;
		folio = nextFolio();
	}
	
	public static List<Integer> getSQLValue (String trxName, String sql, Object... params) {
		List<Integer> retValue = new ArrayList<Integer>();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try	{
    		pstmt = DB.prepareStatement(sql, trxName);
    		DB.setParameters(pstmt, params);
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			retValue.add(rs.getInt(1));
    		}
    	} catch (SQLException e) {
    		log.severe("getSQLValueEx " + sql);

    	} finally {
    		DB.close(rs, pstmt);
    		rs = null; 
    		pstmt = null;
    	}
    	return retValue;
    }
	
	private void saveDetail (List<Integer> patientsList, int genQM, String reportType) throws Exception {
		for (Integer patient : patientsList) {
			MEXMEGenQualityMeasureDet detail = new MEXMEGenQualityMeasureDet(ctx, 0, trxName);
			detail.setEXME_CtaPac_ID(patient);
			detail.setEXME_GenQualityMeasure_ID(genQM);
			detail.setTipo_Reporte(reportType);
			if (!detail.save(trxName)) {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");
			}
		}
	}
	
	public int nextFolio() {
		StringBuilder sql = new StringBuilder("SELECT COALESCE(MAX(folio),0) + 1 FROM EXME_GenQualityMeasure WHERE");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, "", MEXMEGenQualityMeasure.Table_Name));
		return DB.getSQLValue(trxName, sql.toString());
	}
	
	/**
	 * Arma el pqri para la medida
	 *  numerador = pacientes que cumplen con la medida
	 * 	denominador = pacientes en general
	 * 	exclusiones = 
	 * @return pqri
	 */
	protected String[] pqri(String number, List<Integer> numerator, List<Integer> denominator, List<Integer> exclusions){
		String[] pqri = new String[5];
		pqri[0] = number; //pqri_number of the report
		pqri[1] = String.valueOf(denominator != null ? denominator.size(): 0);//denominador			
		pqri[2] = String.valueOf(numerator != null ? numerator.size(): 0); //numerador
		pqri[3] = String.valueOf(exclusions != null ? exclusions.size(): 0); // exclusions
		pqri[4] = "0"; //not met
		
		return pqri;		
	}	
	
	protected String[] pqri(String number, int numerator, List<Integer> denominator, List<Integer> exclusions){
		String[] pqri = new String[5];
		pqri[0] = number; //pqri_number of the report
		pqri[1] = String.valueOf(denominator != null ? denominator.size(): 0);//denominador			
		pqri[2] = String.valueOf(numerator); //numerador
		pqri[3] = String.valueOf(exclusions != null ? exclusions.size(): 0); // exclusions
		pqri[4] = "0"; //not met
		
		return pqri;		
	}	
	
	/**
	 * PROCESOS GENERALES
	 * 
	 * <p>(A) (El paciente debe tener asignados medicamentos (M_Product) pertenecientes a la terapia)
	 * <br>		(Relacion paciente-producto en EXME_ActPacienteInd)
	 * <br>		(Relacion producto-terapia en EXME_TerapiaProducto)
	 * <p>(B) (Relacion paciente-diagnostico en EXME_ActPacienteDiag)
	 * <br>		(El diagnostico se engloba en diversos diagnosticos, relacion en EXME_DiagnosisType)
	 * <p>(C) 	(El paciente debe tener asociados los procedimientos englobados por el procedimiento padre)
	 * <br>		(Relacion paciente-procedimiento en EXME_ActPacienteInd dados los productos relacionados al procedimiento))
	 * <br>		(Relacion intervencion-procedimiento en EXME_ProcedureType)
	 * <p>(D) (Comparacion entre fecha de ingreso de la cuenta paciente contra la fecha de alta # en su defecto la fecha actual)
	 * 
	 */
	
	/**
	 * Numerador
	 *<p>Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte. 		
	 *<p> -2. <i>(A)</i> Y que tengan terapia (antithrombolytic therapy) al ser dados de alta. 		
	 *<p> -3. Y que la cuenta paciente se haya dado de alta        
	 *<p> -4. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke2 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql
	 */

	protected List<Integer> numeratorStroke2(String initDate, String finishDate, int ischemicStroke, int antithrombolyticTherapy){
		List<Integer> patients = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID FROM exme_ctapac cp ");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		//1.
		.append(" where ep.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append("  AND diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ? )")
		//2.	
		.append(" and cp.exme_paciente_id in (")
			.append(" select distinct ap.exme_paciente_id ")
			.append(" from exme_actpacienteind api")
			.append(" inner join exme_actpacienteindh ph inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
			.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
			.append(" where api.m_product_id in (")
				.append(" select m_product_id from exme_terapia_producto where exme_terapia_id = ? AND IsActive = 'Y')")
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
			.append(" and ph.prealta = 'Y' )")
		//3.	
			//La columna Estatusalta cambia a EncounterStatus Mejoras30/05/2011 GC 
		.append(" AND cp.EncounterStatus = ? ");
		//.append(" AND cp.statusAlta = ?  ")
		//4.
		
		if (DB.isOracle()) {
			str.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		} else if (DB.isPostgreSQL()) {
			str.append(" AND trunc('day', trunc('day', (((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		}
		
		str.append(" AND cp.dateOrdered >= to_date(?, 'dd/MM/yyyy') AND cp.fechaAlta <= to_date(?, 'dd/MM/yyyy')");
		
		patients = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive
				, antithrombolyticTherapy, initDate, finishDate, MEXMECtaPac.ENCOUNTERSTATUS_Discharge, initDate, finishDate);		

		return patients;
	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte 
	 *<p> -2. Y que la cuenta paciente se haya dado de alta 
	 * @return int numero de Pacientes
	 */
	protected List<Integer> denominatorStroke2(String initDate, String finishDate, int ischemicStroke){
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		//1.
		.append(" where ep.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")		
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
			.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")
		//2.
				// Ren. EncounterStatus StatusAlta
		.append(" AND cp.EncounterStatus = ? ")
		//.append(" AND cp.statusAlta = ?")
		
		.append(" AND cp.dateOrdered >= to_date(?, 'dd/MM/yyyy') AND cp.fechaAlta <= to_date(?, 'dd/MM/yyyy')");
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive,
				MEXMECtaPac.ENCOUNTERSTATUS_Discharge, initDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes 
	 *<p> - 1.<i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte	
	 *<p> - 2.Y que la cuenta paciente se haya dado de alta        
	 *<p> - 3.Y  ( Que sean menores de 18 anios
	 *<p>	-4. <i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p> 	-5. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 *<p>	-6. <i>(C)</i> # (Motivo de ingreso Elective (cat#logo EXME_MotivoCita) # que tenga un procedimiento de tipo Carotid Intervention en la fecha del reporte 			
	 *<p>	-7. # se haya dado de alta con un estatus definido (catalogo EXME_DischargeStatus)
	 *<p>	-8. # haya fallecido 
	 *<p>	-9. <i>(C)</i> # se le haya iniciado el procedimiento Warfarin
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke2 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql
	 */
	protected List<Integer> exclusionStroke2(String initDate, String finishDate, int ischemicStroke, int warfarinProcedure
			,int trialReason, int electiveReason, int carotidIntervention){
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		//1.
		.append(" where ep.exme_paciente_id in( ")
		.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
		.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
			.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")		
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")
		//2.
		// Ren. EncounterStatus StatusAlta
		.append(" AND cp.EncounterStatus = ? ")
		//.append(" AND cp.statusAlta = ? ")
		
		//3.
		.append(" AND (trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
			//4.
			.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
				.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
			//5.
			.append(" OR cp.EXME_MotivoCita_ID  = ? ")
			//6.
			.append(" OR (cp.EXME_MotivoCita_ID = ? OR cp.exme_paciente_id in (")
				.append(" SELECT distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
				.append(" AND api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ))")
			//7.	
			.append(" OR cp.EXME_DischargeStatus_ID IN (SELECT EXME_DischargeStatus_ID FROM EXME_DischargeStatus ")
				.append(" WHERE IsExclude = 'Y')")
			//8.
			.append(" OR ep.fecha_muerte <= ").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" ")
			//9.
			.append(" OR cp.exme_paciente_id in (")
					.append(" SELECT distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
					.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
					.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
					.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
						.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')))")
			
			.append(" AND cp.dateOrdered >= to_date(?, 'dd/MM/yyyy') AND cp.fechaAlta <= to_date(?, 'dd/MM/yyyy')");

		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive,
				MEXMECtaPac.ENCOUNTERSTATUS_Discharge, trialReason, electiveReason, carotidIntervention, initDate, finishDate,
				warfarinProcedure, initDate, finishDate, initDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia ya que no tiene referencias. Jesus Cantu
	 */
	public File generaStroke2() throws Exception {		
		
		Date fechaIni = params.getFecha_Ini_Urgencia_S2();
		Date fechaFin = params.getFecha_Fin_Urgencia_S2();		
		
		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		
		//TODO Agregar parametro de ischemic stroke, warfarinProcedure, trialReason, electiveReason, carotidIntervention
		List<Integer> numerator = numeratorStroke2(initDate, finishDate, params.getIschemic_Stroke(), params.getEXME_TerapiaAntitrombotic_ID());
		List<Integer> denominator = denominatorStroke2(initDate, finishDate, params.getIschemic_Stroke());
		List<Integer> exclusions = exclusionStroke2(initDate, finishDate, params.getIschemic_Stroke(), params.getWarfarin_Procedure(),
				params.getTrial_Reason(), params.getElective_Reason(), params.getCarotid_Intervention());
		
		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_STROKE2);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Urgencia_S2());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Urgencia_S2());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);
		
		pqris.add(pqri(PQRI_NUMBERSTK2, numerator, denominator, exclusions));				
				
		File generado = xml.armaXml(FILESTK2, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte. 		
	 *<p> -2. <i>(A)</i> Y que tengan terapia (anticoagulation therapy) al ser dados de alta. 		
	 *<p> -3. Y que la cuenta paciente se haya dado de alta        
	 *<p> -4. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke3 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu
	 */
	protected List<Integer> numeratorStroke3(String initDate, String finishDate, int ischemicStroke, int anticoagulationTeraphy) {
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		//1.
		.append(" where ep.exme_paciente_id in( ")
		.append(" select ap.exme_paciente_id from exme_actpacientediag diag ")
		.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in( ")
			.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
		.append(" and diag.Estatus <> ? and diag.IsActive = 'Y' )" )
		//2.
		.append(" and cp.exme_paciente_id in ")
			.append(" (select distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
			.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
			.append("  where api.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and ph.prealta = 'Y')")
		//3.	
			// Ren. EncounterStatus StatusAlta
		.append(" AND cp.EncounterStatus = ? ")
		//.append(" AND cp.statusAlta = ?  ")
		//4.
		.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18")
		
		.append(" AND cp.dateOrdered >= to_date(?, 'dd/MM/yyyy') AND cp.fechaAlta <= to_date(?, 'dd/MM/yyyy')");
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive,			
				anticoagulationTeraphy, initDate, finishDate, MEXMECtaPac.ENCOUNTERSTATUS_Discharge, initDate, finishDate );		

		return pacientes;
	}
	
	/**
	 *Denominador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte. 		
	 *<p> -2. <i>(B)</i> Y que tengan diagnostico de atrial fibrillation/flutter.
	 * @return int numero de Pacientes
	 */
	protected List<Integer> denominatorStroke3(String initDate, String finishDate, int ischemicStroke, int atrialFibrillation) {
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		//1.
		.append(" where ep.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" where diag.exme_diagnostico_id in( ")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') " )
			.append(" and diag.Estatus <> ? and diag.IsActive = 'Y' )" )
		//2.	
		.append(" AND ep.exme_paciente_id in(")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append("  AND diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ? )")
			
		.append(" AND cp.dateOrdered >= to_date(?, 'dd/MM/yyyy')");	
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive
				, atrialFibrillation, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive, initDate);		
	
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte. 		
	 *<p> -2. <i>(A)</i> Y que tengan diagnostico de atrial fibrillation/flutter.
	 *<p> - 3.Y  (Que sean menores de 18 anios
	 *<p>	- 4. <i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p>	-5. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 *<p>	-6. <i>(C)</i> # (Motivo de ingreso Elective (cat#logo EXME_MotivoCita) # que tenga un procedimiento de tipo Carotid Intervention en la fecha del reporte 			
	 *<p>	-7. # se haya dado de alta con un estatus definido (catalogo EXME_DischargeStatus)
	 *<p>	-8. # haya fallecido 
	 *<p>	-9. <i>(C)</i> # se le haya iniciado el procedimiento Warfarin.
	 * @return pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke3 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu 
	 */
	protected List<Integer> exclusionStroke3(String initDate, String finishDate, int ischemicStroke, int atrialFibrillation,
			int warfarinProcedure ,int trialReason, int electiveReason, int carotidIntervention) {
		
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		//1.
		.append(" where ep.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" where diag.exme_diagnostico_id in( ")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') " )
			.append(" and diag.Estatus <> ? and diag.IsActive = 'Y' )" )
			 // Ren. EncounterStatus StatusAlta La columna Estatusalta cambia a EncounterStatus Mejoras30/05/2011 GC 
		.append(" AND cp.EncounterStatus = ? ")
			//.append(" AND cp.statusAlta = ? ")
		
		//2.
		.append(" AND ep.exme_paciente_id in(")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append("  AND diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ? )")
		//3.
		.append(" AND (trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
			//4.
			.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
				.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
			//5.
			.append(" OR cp.EXME_MotivoCita_ID  = ? ")
			//6.
			.append(" OR (cp.EXME_MotivoCita_ID = ? OR cp.exme_paciente_id in (")
				.append(" SELECT distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
				.append(" AND api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ))")
			//7.	
			.append(" OR cp.EXME_DischargeStatus_ID IN (SELECT EXME_DischargeStatus_ID FROM EXME_DischargeStatus ")
				.append(" WHERE IsExclude = 'Y')")
			//8.
			.append(" OR ep.fecha_muerte <= ").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" ")
			//9.
			.append(" OR cp.exme_paciente_id in (")
					.append(" SELECT distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
					.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
					.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
					.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
						.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')))");;
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive,
				MEXMECtaPac.ENCOUNTERSTATUS_Discharge, atrialFibrillation, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive,
				trialReason, electiveReason, carotidIntervention, initDate, finishDate,	warfarinProcedure, initDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia ya que no tiene referencias. Jesus Cantu              
	 */
	public File generaStroke3() throws Exception {		
		
		Date fechaIni = params.getFecha_Ini_Urgencia_S3();
		Date fechaFin = params.getFecha_Fin_Urgencia_S3();		
		
		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
	
		//TODO Agregar parametro de ischemic stroke, warfarinProcedure, trialReason, electiveReason, carotidIntervention, atrial fibrillation
		List<Integer> numerator = numeratorStroke3(initDate, finishDate, params.getIschemic_Stroke(), params.getEXME_TerapiaAnticoagulante_ID());
		List<Integer> denominator = denominatorStroke3(initDate, finishDate, params.getIschemic_Stroke(), params.getAtrial_Diagnostic());
		List<Integer> exclusions = exclusionStroke3(initDate, finishDate, params.getIschemic_Stroke(), params.getAtrial_Diagnostic(), 
				params.getWarfarin_Procedure(), params.getTrial_Reason(), params.getElective_Reason(), params.getCarotid_Intervention());
		
		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_STROKE3);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Urgencia_S3());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Urgencia_S3());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);
		
		pqris.add(pqri(PQRI_NUMBERSTK3, numerator, denominator, exclusions));				
				
		File generado = xml.armaXml(FILESTK3, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}
	
	
	/**
	 * Numerador
	 *<br>Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte. 		
	 *<p> -2. <i>(A)</i> Y que se le haya iniciado la terapia de IV thrombolytic en el hospital dentro de las 3 primeras horas de los sintomas. 		
	 *<p> -3. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke4 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> numeratorStroke4(String initDate, String finishDate, int thrombolyticTeraphy, int ischemicStroke) {
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID");
		str.append(" FROM exme_ctapac cp inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id ")
		.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" WHERE pd.fechasintoma is not null ")
		//1.
		.append(" AND cp.exme_paciente_id in  " )
			.append(" (select ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" where diag.exme_diagnostico_id in ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')" )
			.append(" and diag.Estatus <> ? and diag.IsActive = 'Y')" )
		//2.	
		/*.append(" AND cp.exme_paciente_id in ")
			.append(" (select distinct ap.exme_paciente_id from exme_actpacienteind api ")
			.append(" inner join exme_actpacienteindh ph inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
			.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id where api.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (api.dateordered - pd.fechasintoma) <= 3 )")*/
		//2.	
		.append(" AND cp.exme_ctapac_id in ")
			.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (pml.apliedDate - pd.fechasintoma) <= 0.125 )")	
		//3.
		.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive
				, thrombolyticTeraphy, initDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes 
	 *<p> -1. Que hayan llegado al hospital dentro de las dos primeras horas de los sintomas
	 *<p> -2. <i>(B)</i> Y Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte al ser dado de alta. 	
	 * @return pacientes
	 */
	protected List<Integer> denominatorStroke4(String initDate, String finishDate, int ischemicStroke) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID");
		str.append(" FROM exme_ctapac cp inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id ")
		//1.
		.append(" where pd.fechasintoma is not null and (cp.dateOrdered - pd.fechasintoma) < 2 ")
		//2.
		.append("  and cp.exme_paciente_id in ")
			.append(" (SELECT ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" where diag.exme_diagnostico_id in ")
				.append("( SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and diag.Estatus <> ? and diag.IsActive = 'Y') ")
		.append(" AND cp.EncounterStatus = ?  "); // Ren. EncounterStatus StatusAlta
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, 
				MActPacienteDiag.ESTATUS_Inactive, MEXMECtaPac.ENCOUNTERSTATUS_Discharge);		
		
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes 	
	 *<p> -1. <i>(B)</i> Y Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte al ser dado de alta. 	
	 *<p> -2. Y (Que sean menores de 18 anios
	 *<p>	-3.<i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p>	-4. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 *<p>	-5. <i>(C)</i> # (Motivo de ingreso Elective (cat#logo EXME_MotivoCita) # que tenga un procedimiento de tipo Carotid Intervention en la fecha del reporte 
	 *<p>	-6. <i>(C)</i> # se le haya iniciado el procedimiento Warfarin
	 * @return pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke4 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> exclusionStroke4(String initDate, String finishDate, int ischemicStroke, int trialReason, 
			int electiveReason, int carotidIntervention, int warfarinProcedure) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id")		
		//1.
		.append(" WHERE ep.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")
		.append(" AND cp.EncounterStatus = ? ") // Ren. EncounterStatus StatusAlta
		//2.
		.append(" AND (trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
			//3.
			.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
				.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
			//4.
			.append(" OR cp.EXME_MotivoCita_ID  = ? ")
			//5.
			.append(" OR (cp.EXME_MotivoCita_ID = ? OR cp.exme_paciente_id in (")
				.append(" SELECT distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
				.append(" AND api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ))")
			//6.
			.append(" OR cp.exme_paciente_id in (")
					.append(" SELECT distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
					.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
					.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
					.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
						.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')))");

		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive,
				MEXMECtaPac.ENCOUNTERSTATUS_Discharge, trialReason, electiveReason, carotidIntervention, initDate, finishDate,	
				warfarinProcedure, initDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia ya que no tiene referencias. Jesus Cantu
	 */
	public File generaStroke4() throws Exception {		
		
		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Stroke4();
		Date fechaFin = params.getFecha_Fin_Stroke4();		
		
		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
			
		List<Integer> numerator = numeratorStroke4(initDate, finishDate, params.getEXME_TerapiaThrombolitic_ID(), params.getIschemic_Stroke());
		List<Integer> denominator = denominatorStroke4(initDate, finishDate, params.getIschemic_Stroke());
		List<Integer> exclusions = exclusionStroke4(initDate, finishDate, params.getIschemic_Stroke(), params.getTrial_Reason(), params.getElective_Reason()
				, params.getCarotid_Intervention(), params.getWarfarin_Procedure());
		
		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_STROKE4);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Stroke4());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Stroke4());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);
		
		pqris.add(pqri(PQRI_NUMBERSTK4, numerator, denominator, exclusions));				
				
		File generado = xml.armaXml(FILESTK4, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}
	
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte. 		
	 *<p> -2. <i>(A)</i> Y que tengan terapia (antithrombolytic therapy) al ser dados de alta.
	 *<p> -3. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke5 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> numeratorStroke5(String initDate, String finishDate, int antitromboticTeraphy, int ischemicStroke) {
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("select DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp ")
		.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		//1.
		.append(" where cp.exme_paciente_id in ")
			.append(" (select ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" WHERE diag.exme_diagnostico_id in  ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') " )
			.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")
		//2.	
		/*.append(" AND cp.exme_paciente_id in ")
			.append(" (select distinct ap.exme_paciente_id  from exme_actpacienteind api " )	
			.append(" inner join exme_actpacienteindh ph ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id " )
			.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id where api.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (api.dateordered - cp.dateOrdered) <= 2 )")*/
		//2.	
		.append(" AND cp.exme_ctapac_id in ")
			.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (pml.apliedDate - cp.dateOrdered) <= 2 )")		
		//3.
		.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, 
				initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive, antitromboticTeraphy, initDate, finishDate);		

		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte al ser dado de alta. 	
	 * @return pacientes
	 */
	protected List<Integer> denominatorStroke5(String initDate, String finishDate, int ischemicStroke) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("select DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp where cp.exme_paciente_id in ")
			//1.
			.append(" (SELECT ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" WHERE diag.exme_diagnostico_id in  ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y')")
		.append(" AND cp.EncounterStatus = ? "); // Ren. EncounterStatus StatusAlta
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive,
				MEXMECtaPac.ENCOUNTERSTATUS_Discharge);		
		
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes 	
	 *<p> -1. <i>(B)</i> Y Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte al ser dado de alta. 	
	 *<p> -2. Y (Que sean menores de 18 anios
	 *<p>	-3. <i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p>	-4. # motivo una estancia menor de 2 dias en el hospital
	 *<p>	-5. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 *<p>	-6. <i>(C)</i> # (Motivo de ingreso Elective (cat#logo EXME_MotivoCita) # que tenga un procedimiento de tipo Carotid Intervention en la fecha del reporte 
	 *<p>	-7. <i>(C)</i> # se le haya administrado la terapia thrombolytic dentro de las primeras 24 horas de haber llegado al hospital
	 *<p>	-8. <i>(C)</i> # se le haya iniciado el procedimiento Warfarin	
	 * @return pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke5 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> exclusionStroke5(String initDate, String finishDate, int ischemicStroke, int trialReason, 
			int electiveReason, int carotidIntervention, int warfarinProcedure, int thrombolyticTherapy) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id")		
		//1.
		.append(" WHERE ep.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")
		.append(" AND cp.EncounterStatus = ? ") // Ren. EncounterStatus StatusAlta
		//2.
		.append(" AND (trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
			//3.
			.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
				.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
			//4.	
			.append(" OR (cp.fechaAlta - cp.dateordered) < 2 ")
			//5.
			.append(" OR cp.EXME_MotivoCita_ID  = ? ")
			//6.
			.append(" OR (cp.EXME_MotivoCita_ID = ? OR cp.exme_paciente_id in (")
				.append(" SELECT distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
				.append(" AND api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ))")
			//7.
			/*.append(" and cp.exme_paciente_id in ")
				.append(" (select distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append("  where api.m_product_id in ")
					.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
				.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
				.append(" AND (api.dateordered - cp.dateordered) < 1)")*/
			//7.
			.append(" OR cp.exme_ctapac_id in ")
				.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
				.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
				.append(" where pm.m_product_id in ")
					.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
				.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
				.append(" and (pml.apliedDate - cp.dateOrdered) <= 1 )")	
			//8.	
			.append(" OR cp.exme_paciente_id in (")
					.append(" SELECT distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
					.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
					.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
					.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
						.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')))");	

		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive,
				MEXMECtaPac.ENCOUNTERSTATUS_Discharge, trialReason, electiveReason, carotidIntervention, initDate, finishDate,	
				thrombolyticTherapy, initDate, finishDate, warfarinProcedure, initDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia al no tener referencias. Jesus Cantu
	 */
	public File generaStroke5() throws Exception {		
		
		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Urgencia_S3();
		Date fechaFin = params.getFecha_Fin_Urgencia_S3();		
		
		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
			
		List<Integer> numerator = numeratorStroke5(initDate, finishDate, params.getEXME_TerapiaAntitrombotic_ID(), params.getIschemic_Stroke());
		List<Integer> denominator = denominatorStroke5(initDate, finishDate, params.getIschemic_Stroke());
		List<Integer> exclusions = exclusionStroke5(initDate, finishDate, params.getIschemic_Stroke(), params.getTrial_Reason(), params.getElective_Reason()
				, params.getCarotid_Intervention(), params.getWarfarin_Procedure(), params.getEXME_TerapiaThrombolitic_ID());
		
		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_STROKE5);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Stroke5());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Stroke5());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);
		
		pqris.add(pqri(PQRI_NUMBERSTK5, numerator, denominator, exclusions));				
				
		File generado = xml.armaXml(FILESTK5, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte. 		
	 *<p> -2. <i>(A)</i> Y que tengan terapia (Statin) al ser dados de alta.
	 *<p> -3. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke6 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> numeratorStroke6(String initDate, String finishDate, int statinTherapy, int ischemicStroke) {
		List<Integer> pacientes = new ArrayList<Integer>();	
		
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp ")
		.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		//1.
		.append(" WHERE cp.exme_paciente_id in ")	
			.append(" (SELECT ap.exme_paciente_id ")
			.append(" from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append("  AND diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ? )")
		//2.	
		.append(" and cp.exme_paciente_id in (")
			.append(" select distinct ap.exme_paciente_id ")
			.append(" from exme_actpacienteind api")
			.append(" inner join exme_actpacienteindh ph inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
			.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
			.append(" where api.m_product_id in (")
				.append(" select m_product_id from exme_terapia_producto where exme_terapia_id = ?)")
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
			.append(" and ph.prealta = 'Y')")
		//3.
		.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive,
				statinTherapy, initDate, finishDate);		
		
		
		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte . 
	 *<p> -2. Y ( <i>(C)</i> Que tengan un estudio de LDL con una medida mayor de 100 mg/dL
	 *<p>	-3. <i>(C)</i> # que no se le haya tomado el estudio de LDL en los 30 dias de haber llegado al hospital
	 *<p>	-4. <i>(A)</i> # que estuvieron en una medicaci#n de Lipid Lowering antes de entrar al hospital)
	 * @return pacientes
	 */
	protected List<Integer> denominatorStroke6(String initDate, String finishDate, int ischemicStroke, int lipidProcedure
			, int ldlMeasure, int lipidLoweringMed, int lipidLoinc) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("select DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp where cp.exme_paciente_id in ")
			//1.
			.append(" (select ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append( " where diag.exme_diagnostico_id in  ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') " )
			.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")
		
		.append(" AND ( ")
			//2.
			.append(" cp.exme_paciente_id in ")
				.append(" (select distinct ap.exme_paciente_id  from exme_actpacienteind api " )	
				.append(" inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id " )
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append(" inner join exme_estudiosobx obx on obx.exme_actpacienteind_id = api.exme_actpacienteind_id" )
				.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
				.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
				.append("and obx.observation >= '100' and obx.C_UOM_ID = ?")
				.append(" AND obx.codeobx IN ")
					.append(" (SELECT value FROM EXME_LOINC WHERE EXME_LOINC_ID IN ")
						.append(" (SELECT EXME_Loinc_ID FROM EXME_LoincTypeDet WHERE EXME_LoincType_ID = ?)))")
			//3.		
			.append(" OR cp.exme_paciente_id NOT IN")
			.append(" (select distinct ap.exme_paciente_id  from exme_actpacienteind api " )	
				.append(" inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id " )
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append(" inner join exme_estudiosobx obx on obx.exme_actpacienteind_id = api.exme_actpacienteind_id" )
				.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
					.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
					.append(" AND (api.dateordered - cp.dateordered)  < 30 )")
			//4.
			.append(" OR cp.EXME_CtaPac_ID in (")
				.append(" SELECT distinct prev.EXME_CtaPac_ID ")
				.append(" FROM exme_prescription_prev prev")
				.append(" where prev.m_product_id in (")
					.append(" select m_product_id from exme_terapia_producto where exme_terapia_id = ?)")
				.append(" AND cp.dateOrdered > prev.FechaToma))");
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive, 
				lipidProcedure, initDate, finishDate, ldlMeasure, lipidLoinc, lipidProcedure, initDate, finishDate, lipidLoweringMed);
			
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes 	
	 *<p> -1. <i>(B)</i> Y Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte. 	
	 *<p> -2. Y ( <i>(C)</i> Que tengan un estudio de LDL con una medida mayor de 100 mg/dL
	 *<p>	-3. <i>(C)</i> # que no se le haya tomado el estudio de LDL en los 30 dias de haber llegado al hospital
	 *<p>	-4. <i>(A)</i> # que estuvieron en una medicaci#n de Lipid Lowering antes de entrar al hospital)
	 *<p> -5. Y (Que sean menores de 18 anios
	 *<p>	-6. <i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p>	-7. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 *<p>	-8. <i>(C)</i> # (Motivo de ingreso Elective (cat#logo EXME_MotivoCita) # que tenga un procedimiento de tipo Carotid Intervention en la fecha del reporte
	 *<p>	-9. # que no tengan evidencia de atherosclerosis
	 *<p>	-10. # se haya dado de alta con un estatus definido (catalogo EXME_DischargeStatus)
	 *<p>	-11. # haya fallecido
	 * @return pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke6 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> exclusionStroke6(String initDate, String finishDate, int ischemicStroke, int trialReason, 
			int electiveReason, int carotidIntervention, int atherosclerosis, int lipidProcedure, int ldlMeasure, int lipidLoweringMed) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id")		
		//1.
		.append(" WHERE ep.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")
		.append(" AND cp.EncounterStatus = ? ") // Ren. EncounterStatus StatusAlta
		.append(" AND ( ")
			//2.
			.append(" cp.exme_paciente_id in ")
				.append(" (select distinct ap.exme_paciente_id  from exme_actpacienteind api " )	
				.append(" inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id " )
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append(" inner join exme_estudiosobx obx on obx.exme_actpacienteind_id = api.exme_actpacienteind_id" )
				.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
				.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
				.append("and obx.observation >= '100' and obx.C_UOM_ID = ?)")
			//3.		
			.append(" OR cp.exme_paciente_id NOT IN")
			.append(" (select distinct ap.exme_paciente_id  from exme_actpacienteind api " )	
				.append(" inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id " )
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append(" inner join exme_estudiosobx obx on obx.exme_actpacienteind_id = api.exme_actpacienteind_id" )
				.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
					.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
					.append(" AND (api.dateordered - cp.dateordered)  < 30 )")
			//4.
			.append(" OR cp.EXME_CtaPac_ID in (")
				.append(" SELECT distinct prev.EXME_CtaPac_ID ")
				.append(" FROM exme_prescription_prev prev")
				.append(" where prev.m_product_id in (")
					.append(" select m_product_id from exme_terapia_producto where exme_terapia_id = ?)")
				.append(" AND cp.dateOrdered > prev.FechaToma))")		
		//5.
		.append(" AND (trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
			//6.
			.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
				.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
			//7.
			.append(" OR cp.EXME_MotivoCita_ID  = ? ")
			//8.
			.append(" OR (cp.EXME_MotivoCita_ID = ? OR cp.exme_paciente_id in (")
				.append(" SELECT distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
				.append(" AND api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ))")
			//9.
			.append(" OR ep.exme_paciente_id NOT IN( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
				.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
				.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")
			//10.	
			.append(" OR cp.EXME_DischargeStatus_ID IN (SELECT EXME_DischargeStatus_ID FROM EXME_DischargeStatus ")
				.append(" WHERE IsExclude = 'Y')")
			//11.
			.append(" OR ep.fecha_muerte <= ").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" )")	
			/*//12.				
			.append(" OR ep.exme_paciente_id  IN( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
				.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
				.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y')) ")*/;	
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive,
				MEXMECtaPac.ENCOUNTERSTATUS_Discharge, lipidProcedure, initDate, finishDate, ldlMeasure, lipidProcedure, initDate, finishDate, lipidLoweringMed,
				trialReason, electiveReason, carotidIntervention, initDate, finishDate, atherosclerosis, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia ya que este no tiene referencias. Jesus Cantu  
	 */
	public File generaStroke6() throws Exception {		

		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Stroke6();
		Date fechaFin = params.getFecha_Fin_Stroke6();		

		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);

		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();

		List<Integer> numerator = numeratorStroke6(initDate, finishDate, params.getEXME_TerapiaStatin_ID(), params.getIschemic_Stroke());
		List<Integer> denominator = denominatorStroke6(initDate, finishDate, params.getIschemic_Stroke(), params.getLipid_Procedure(), 
				params.getLDL_Measure(), params.getLipid_Medication(), params.getLipid_Loinc());
		List<Integer> exclusions = exclusionStroke6(initDate, finishDate, params.getIschemic_Stroke(), params.getTrial_Reason(), params.getElective_Reason()
				, params.getCarotid_Intervention(), params.getAteriosclerosis_Diag(), params.getLipid_Procedure(), 
				params.getLDL_Measure(), params.getLipid_Medication());

		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_STROKE6);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Stroke6());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Stroke6());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);

		pqris.add(pqri(PQRI_NUMBERSTK6, numerator, denominator, exclusions));				

		File generado = xml.armaXml(FILESTK6, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	

		return generado;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de ischemicStroke # hemorragicStroke activo en las fechas definidas por el reporte.		
	 *<p> -2. Y que tengan asociados los recursos educativos definidos
	 *<p> -3. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke8 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> numeratorStroke8(String initDate, String finishDate, int ischemicStroke, int hemorragicStroke) {
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("select DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp  ")
		.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" where cp.exme_paciente_id in" )
			//1.
			.append(" (select ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" where diag.exme_diagnostico_id in  ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" OR diag.exme_diagnostico_id in ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y')")
		//2.	
		.append(" and cp.exme_paciente_id in ")
			.append(" (select distinct pac.exme_paciente_id from exme_recursoeducativopac pac " )
			.append(" where pac.exme_recursoeducativo_id in ")
				.append(" (select exme_recursoeducativo_id from exme_recursoeducativostr ")
				.append(" where type in (?, ?)))")
		//3.
		.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, hemorragicStroke, initDate, finishDate, 
				MActPacienteDiag.ESTATUS_Inactive, MDiagnostico.STROKE_IschemicStroke, MDiagnostico.STROKE_HemorragicStroke);
			
		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico ischemicStroke # hemorragicStroke activo en las fechas definidas por el reporte . 
	 * @return pacientes
	 */
	protected List<Integer> denominatorStroke8(String initDate, String finishDate, int ischemicStroke, int hemorragicStroke) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp where cp.exme_paciente_id in ")
			//1.
			.append(" (select ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" where diag.exme_diagnostico_id in  ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" OR diag.exme_diagnostico_id in ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y')");
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, hemorragicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive);		
		
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes 	
	 *<p> -1. <i>(B)</i> Y Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte. 	
	 *<p> -2. Y (Que sean menores de 18 anios
	 *<p>	-3. <i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p>	-4. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 *<p>	-5. <i>(C)</i> # (Motivo de ingreso Elective (cat#logo EXME_MotivoCita) # que tenga un procedimiento de tipo Carotid Intervention en la fecha del reporte
	 * @return pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke8 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> exclusionStroke8(String initDate, String finishDate, int ischemicStroke, int hemorragicStroke, int trialReason, 
			int electiveReason, int carotidIntervention) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id")		
		//1.
		.append(" WHERE ep.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" OR diag.exme_diagnostico_id in ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")
		.append(" AND cp.EncounterStatus = ? ") // Ren. EncounterStatus StatusAlta
		.append(" AND ( ")			
			//2.
			.append(" trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
			//3.
			.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
				.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
				//4.
				.append(" OR cp.EXME_MotivoCita_ID  = ? ")
				//5.
				.append(" OR (cp.EXME_MotivoCita_ID = ? OR cp.exme_paciente_id in (")
				.append(" SELECT distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
				.append(" AND api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') )))");
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, hemorragicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive,
				MEXMECtaPac.ENCOUNTERSTATUS_Discharge, trialReason, electiveReason, carotidIntervention, initDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia ya que este no tiene referencias. Jesus Cantu
	 */
	public File generaStroke8() throws Exception {		

		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Stroke8();
		Date fechaFin = params.getFecha_Fin_Stroke8();		

		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);

		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();

		List<Integer> numerator = numeratorStroke8(initDate, finishDate, params.getIschemic_Stroke(), params.getHemorrhagic_Stroke());
		List<Integer> denominator = denominatorStroke8(initDate, finishDate, params.getIschemic_Stroke(), params.getHemorrhagic_Stroke());
		List<Integer> exclusions = exclusionStroke8(initDate, finishDate, params.getIschemic_Stroke(), params.getHemorrhagic_Stroke(), 
				params.getTrial_Reason(), params.getElective_Reason(), params.getCarotid_Intervention());

		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_STROKE8);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Stroke8());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Stroke8());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);

		pqris.add(pqri(PQRI_NUMBERSTK8, numerator, denominator, exclusions));				

		File generado = xml.armaXml(FILESTK8, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	

		return generado;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de ischemicStroke # hemorragicStroke activo en las fechas definidas por el reporte al ser dados de alta.		
	 *<p> -2. Y que hayan sido evaluados para servicios de rehabilitacion
	 *<p> -3. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke10 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> numeratorStroke10(String initDate, String finishDate, int ischemicStroke, int hemorragicStroke, int rehabProcedure) {
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("select DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp ")
		.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" where cp.exme_paciente_id in" )
			//1.
			.append(" (select ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append( " where diag.exme_diagnostico_id in  ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" OR diag.exme_diagnostico_id in ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') " )
			.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")
		.append(" AND cp.EncounterStatus = ?  ")	 // Ren. EncounterStatus StatusAlta
		//2.
		.append(" and cp.exme_paciente_id in ")
			.append(" (select distinct ap.exme_paciente_id  from exme_actpacienteind api " )	
			.append(" inner join exme_actpacienteindh ph ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id " )
			.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id where api.m_product_id in ")
				.append(" (select m_product_id from m_product where exme_intervencion_id in ")
					.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy'))")
		//3.
		.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, hemorragicStroke,
				initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive, MEXMECtaPac.ENCOUNTERSTATUS_Discharge, rehabProcedure, initDate, finishDate);		
		
		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico ischemicStroke # hemorragicStroke activo en las fechas definidas por el reporte al ser dados de alta. 
	 * @return pacientes
	 */
	protected List<Integer> denominatorStroke10(String initDate, String finishDate, int ischemicStroke, int hemorragicStroke) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("select DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp where cp.exme_paciente_id in ")
			.append(" (select ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" where diag.exme_diagnostico_id in  ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" OR diag.exme_diagnostico_id in ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y')")
		.append(" AND cp.EncounterStatus = ?  ");  // Ren. EncounterStatus StatusAlta
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, hemorragicStroke, initDate, finishDate, 
				MActPacienteDiag.ESTATUS_Inactive, MEXMECtaPac.ENCOUNTERSTATUS_Discharge);
		
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes 	
	 *<p> -1. <i>(B)</i> Y Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte. 	
	 *<p> -2. Y (Que sean menores de 18 anios
	 *<p>	-3. <i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p>	-4. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 *<p>	-5. <i>(C)</i> # (Motivo de ingreso Elective (cat#logo EXME_MotivoCita) # que tenga un procedimiento de tipo Carotid Intervention en la fecha del reporte
	 * @return pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaStroke10 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> exclusionStroke10(String initDate, String finishDate, int ischemicStroke, int hemorragicStroke, int trialReason, 
			int electiveReason, int carotidIntervention) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id")		
		//1.
		.append(" WHERE ep.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append(" OR diag.exme_diagnostico_id in ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")
		.append(" AND cp.EncounterStatus = ? ") // Ren. EncounterStatus StatusAlta
		.append(" AND ( ")			
			//2.
			.append(" trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
			//3.
			.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
				.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
			//4.
			.append(" OR cp.EXME_MotivoCita_ID  = ? ")
			//5.
			.append(" OR (cp.EXME_MotivoCita_ID = ? OR cp.exme_paciente_id in (")
				.append(" SELECT distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append(" where api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
				.append(" AND api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ))")
			//6.	
			.append(" OR cp.EXME_DischargeStatus_ID IN (SELECT EXME_DischargeStatus_ID FROM EXME_DischargeStatus ")
				.append(" WHERE IsExclude = 'Y')")
			//7.
			.append(" OR ep.fecha_muerte <= ").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(") ");
		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, hemorragicStroke, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive,
				MEXMECtaPac.ENCOUNTERSTATUS_Discharge, trialReason, electiveReason, carotidIntervention, initDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia ya que este no tiene referencias. Jesus Cantu
	 */
	public File generaStroke10() throws Exception {		

		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Stroke10();
		Date fechaFin = params.getFecha_Fin_Stroke10();		

		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);

		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();

		List<Integer> numerator = numeratorStroke10(initDate, finishDate, params.getIschemic_Stroke(), params.getHemorrhagic_Stroke(), params.getRehab_Procedure());
		List<Integer> denominator = denominatorStroke10(initDate, finishDate, params.getIschemic_Stroke(), params.getHemorrhagic_Stroke());
		List<Integer> exclusions = exclusionStroke10(initDate, finishDate, params.getIschemic_Stroke(), params.getHemorrhagic_Stroke(), params.getTrial_Reason()
				, params.getElective_Reason(), params.getCarotid_Intervention());
		
		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_STROKE10);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Stroke10());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Stroke10());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);
	

		pqris.add(pqri(PQRI_NUMBERSTK10, numerator, denominator, exclusions));				

		File generado = xml.armaXml(FILESTK10, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	

		return generado;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(A)</i> Que recibieron VTE prophylaxis el dia de o el dia despues de la admision en el hospital.		
	 *<p> -2. <i>(A)</i> Que recibieron VTE prophylaxis el dia de o el dia despues de cirugia para las cirugias
	 *					que empezaron el dia o el dia despues de la admision en el hospital 
	 *<p> -3. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaVTE1 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> numeratorVTE1(String initDate, String finishDate, int vteProphylaxis, int icu) {
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("select DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp ")
		.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" where cp.exme_paciente_id in" )
			//1.
		    /* .append(" (select distinct ap.exme_paciente_id ")      
		     .append(" from exme_actpacienteind api inner join exme_actpacienteindh ph ") 
		     .append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
		     .append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
		     .append(" where api.m_product_id in ")
		     	.append("(SELECT m_product_id from exme_terapia_producto where exme_terapia_id = ?)")
		     .append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")*/
			//1.
			.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (pml.apliedDate - cp.dateOrdered) <= 1 )")	
		//2.     
		.append(" OR cp.exme_paciente_id in ")
			/*.append(" (select distinct ap.exme_paciente_id ")
			.append(" from exme_actpacienteind api inner join exme_actpacienteindh ph ")
			.append(" inner join exme_actpaciente ap inner join exme_progquiro pq on pq.exme_paciente_id = ap.exme_paciente_id ")
			.append(" on ap.exme_actpaciente_id = ph.exme_actpaciente_id  ")
			.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")                                
			.append(" where api.m_product_id in  " )
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = = ?)") 	
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (api.dateordered - pq.fechafin) < 2 ")*/
			.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" inner join exme_actpacienteindh ph")
			.append(" inner join exme_actpaciente ap inner join exme_progquiro pq on pq.exme_paciente_id = ap.exme_paciente_id ")
			.append(" on ap.exme_actpaciente_id = ph.exme_actpaciente_id  ")
			.append(" on ph.exme_ctapac_id = pm.exme_ctapac_id")
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and pq.fechaInicio between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (pml.apliedDate - cp.dateOrdered) <= 2 ")	
			.append(" and (pq.fechafin - ") 
				.append(" (SELECT enf.Fecha_Ingreso FROM EXME_DiarioEnf enf ")
				.append(" WHERE enf.EXME_CtaPac_ID = cp.EXME_CtaPac_ID AND enf.EXME_EstServ_ID = ? )) < 2")
			.append(" and (pq.fechainicio - cp.dateOrdered) <= 2 )")
		//3.
		.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		
		pacientes = getSQLValue(null, str.toString(), vteProphylaxis, initDate, finishDate, vteProphylaxis, initDate,
				finishDate, initDate, finishDate, icu);		
		
		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula todos los pacientes 
	 * @return pacientes
	 */
	protected List<Integer> denominatorVTE1() {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("select cp.EXME_CtaPac_ID from exme_ctapac cp");;
		
		pacientes = getSQLValue(null, str.toString());
		
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes 	
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de ischemicStroke. 
	 *<p> -2. <i>(B)</i> # Que tengan un diagnostico de hemorragicStroke.
	 *<p> -3. <i>(B)</i> # Que tengan un diagnostico de VTE.
	 *<p> -4. # Que sean menores de 18 anios
	 *<p> -5. <i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p> -6. <i>(D)</i> # Una estancia menor de 2 dias en el hospital
	 *<p> -7. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 *<p> -8. # Que hayan ingresado por la estacion de servicio de ICU
	 *<p> -9. # Que hayan sido transferidos a la estacion de servicio de ICU  en menos de un dia de haber sido admitido
	 * @return pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaVTE1 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> exclusionVTE1(String initDate, String finishDate, int ischemicStroke, int hemorragicStroke, int trialReason, 
			int vteProphylaxis, int icu) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id")		
		.append(" WHERE ep.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				//1.
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")			
			.append(" OR diag.exme_diagnostico_id in ")
				//2.
				.append(" (SELECT exme_diagnostico_id FROM EXME_Group_Diag WHERE STROKE = ? AND IsActive = 'Y') ")
			.append(" OR diag.exme_diagnostico_id in ")
				//3.
				.append(" (SELECT exme_diagnostico_id FROM EXME_Group_Diag WHERE STROKE = ? AND IsActive = 'Y') ")	
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")	
		//4.
		.append(" OR trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
		//5.
		.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
			.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
		//6.
		.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
			.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END < 2) ")	
		//7.
		.append(" OR cp.EXME_MotivoCita_ID  = ? ")
		//8.
		.append(" OR (cp.EXME_EstServ_ID  = ?  AND (cp.fechaAlta - cp.dateOrdered) < 1)")
		//9.
		.append(" OR (")
			.append(" (SELECT enf.Fecha_Ingreso FROM EXME_DiarioEnf enf ")
				.append(" WHERE enf.EXME_CtaPac_ID = cp.EXME_CtaPac_ID AND enf.EXME_EstServ_ID = ? )")
			.append(" - cp.dateOrdered) < 1"); 	

		
		pacientes = getSQLValue(null, str.toString(), ischemicStroke, hemorragicStroke, vteProphylaxis,  initDate, finishDate, 
				MActPacienteDiag.ESTATUS_Inactive, trialReason, icu, icu);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia ya que este no tiene referencias. Jesus Cantu
	 */
	public File generaVTE1() throws Exception {		

		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Vte1();
		Date fechaFin = params.getFecha_Fin_Vte1();		

		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);

		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();

		List<Integer> numerator = numeratorVTE1(initDate, finishDate, params.getEXME_ProphylaxisVte_ID(), params.getEXME_EstServIcu_ID());
		List<Integer> denominator = denominatorVTE1();
		List<Integer> exclusions = exclusionVTE1(initDate, finishDate, params.getIschemic_Stroke(), params.getHemorrhagic_Stroke(), params.getTrial_Reason(), 
				params.getEXME_ProphylaxisVte_ID(), params.getEXME_EstServIcu_ID());

		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_VENOUSTHROMBOEMBOLISMVTE_1);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Vte1());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Vte1());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);

		pqris.add(pqri(PQRI_NUMBERVTE1, numerator, denominator, exclusions));				

		File generado = xml.armaXml(FILEVTE1, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	

		return generado;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(A)</i> Que recibieron VTE prophylaxis el dia de o el dia despues de la admision en el hospital.		
	 *<p> -2. <i>(A)</i> Que recibieron VTE prophylaxis el dia de o el dia despues de cirugia para las cirugias
	 *					que empezaron el dia o el dia despues de la admision en el hospital 
	 *<p> -3. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaVTE2 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> numeratorVTE2(String initDate, String finishDate, int vteProphylaxis, int icu) {
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("select DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp ")
		.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" where cp.exme_paciente_id in" )
			//1.
		     /*.append(" (select distinct ap.exme_paciente_id ")      
		     .append(" from exme_actpacienteind api inner join exme_actpacienteindh ph ") 
		     .append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
		     .append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
		     .append(" where api.m_product_id in ")
		     	.append("(SELECT m_product_id from exme_terapia_producto where exme_terapia_id = ?)")
		     .append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")*/
			.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (pml.apliedDate - cp.dateOrdered) <= 1 ")	
		     .append("AND ((cp.EXME_EstServ_ID  = ? AND (cp.fechaAlta - cp.dateOrdered) < 1)")
		     .append(" OR ((SELECT enf.Fecha_Ingreso FROM EXME_DiarioEnf enf ")
				.append(" WHERE enf.EXME_CtaPac_ID = cp.EXME_CtaPac_ID AND enf.EXME_EstServ_ID = ? ) - cp.dateOrdered) < 1 ))")		     
		//2.     
		.append(" OR cp.exme_paciente_id in ")
			/*.append(" (select distinct ap.exme_paciente_id ")
			.append(" from exme_actpacienteind api inner join exme_actpacienteindh ph ")
			.append(" inner join exme_actpaciente ap inner join exme_progquiro pq on pq.exme_paciente_id = ap.exme_paciente_id ")
			.append(" on ap.exme_actpaciente_id = ph.exme_actpaciente_id  ")
			.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")                                
			.append(" where api.m_product_id in  " )
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = = ?)") 	
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (api.dateordered - pq.fechafin) < 2 ")*/
				.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" inner join exme_actpacienteindh ph ")
			.append(" inner join exme_actpaciente ap inner join exme_progquiro pq on pq.exme_paciente_id = ap.exme_paciente_id ")
			.append(" on ap.exme_actpaciente_id = ph.exme_actpaciente_id  ")
			.append(" on ph.exme_ctapac_id = pm.exme_ctapac_id ")
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and pq.fechaInicio between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (pml.apliedDate - cp.dateOrdered) <= 2 ")	
			.append(" AND ( (pq.fechafin - ") 
				.append(" (SELECT enf.Fecha_Ingreso FROM EXME_DiarioEnf enf ")
				.append(" WHERE enf.EXME_CtaPac_ID = cp.EXME_CtaPac_ID AND enf.EXME_EstServ_ID = ? )) < 1")
			.append(" OR (cp.EXME_EstServ_ID  = ? AND (pq.fechafin - cp.dateOrdered) < 1) ))")
		//3.
		.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		
		pacientes = getSQLValue(null, str.toString(), vteProphylaxis, initDate, finishDate, icu, icu, vteProphylaxis, initDate, finishDate,
				initDate, finishDate , icu, icu);		
		
		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula todos los pacientes 
	 * @return pacientes
	 */
	protected List<Integer> denominatorVTE2() {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("select cp.EXME_CtaPac_ID from exme_ctapac cp");;
		
		pacientes = getSQLValue(null, str.toString());
		
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes 	
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de VTE. 
	 *<p> -2. # Que sean menores de 18 anios
	 *<p> -3. <i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p> -4. <i>(D)</i> # Una estancia menor de 2 dias en el hospital
	 *<p> -5. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 *<p> -6. # Que hayan ingresado por la estacion de servicio de ICU
	 *<p> -7. # Que hayan sido transferidos a la estaciond e servicio de ICU  en menos de un dia de haber sido admitido
	 * @return pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaVT2 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> exclusionVTE2(String initDate, String finishDate, int trialReason, int vteProphylaxis, int icu) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id")		
		.append(" WHERE ep.exme_paciente_id in( ")
			//1.
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")	
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")	
		//2.
		.append(" OR trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
		//3.
		.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
			.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
		//4.
		.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
			.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END < 2) ")	
		//5.
		.append(" OR cp.EXME_MotivoCita_ID  = ? ")
		//6.
		.append(" OR (cp.EXME_EstServ_ID  = ?  AND (cp.fechaAlta - cp.dateOrdered) <= 1)")
		//7.
		.append(" OR (")
			.append(" (SELECT enf.Fecha_Ingreso FROM EXME_DiarioEnf enf ")
				.append(" WHERE enf.EXME_CtaPac_ID = cp.EXME_CtaPac_ID AND enf.EXME_EstServ_ID = ? )")
			.append(" - cp.dateOrdered) < 1"); 	

		
		pacientes = getSQLValue(null, str.toString(), vteProphylaxis, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive, trialReason, icu, icu);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia ya que este no tiene referencias. Jesus Cantu
	 */
	public File generaVTE2() throws Exception {		

		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Vte2();
		Date fechaFin = params.getFecha_Fin_Vte2();		

		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);

		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();

		List<Integer> numerator = numeratorVTE2(initDate, finishDate, params.getEXME_ProphylaxisVte_ID(), params.getEXME_EstServIcu_ID());
		List<Integer> denominator = denominatorVTE2();
		List<Integer> exclusions = exclusionVTE2(initDate, finishDate, params.getTrial_Reason(), params.getEXME_ProphylaxisVte_ID(), params.getEXME_EstServIcu_ID());

		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_VENOUSTHROMBOEMBOLISMVTE_2);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Vte2());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Vte2());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);

		pqris.add(pqri(PQRI_NUMBERVTE2, numerator, denominator, exclusions));				

		File generado = xml.armaXml(FILEVTE2, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	

		return generado;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(A)</i> Que recibieron warfarin y parental anticoagulation 5 o mas dias, con un INR >= 2 al descontinuarse el parental anticoagulation.		
	 *<p> -2. <i>(A)</i> # Que recibieron warfarin y parental anticoagulation 5 o mas dias, con un INR < 2 y dados de alta con overlap therapy.
	 *<p> -3. <i>(A)</i> # Que recibieron warfarin y parental anticoagulation en menos de 5 dias y dados de alta con overlap therapy.					
	 *<p> -4. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaVT3 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> numeratorVTE3(String initDate, String finishDate, int warfarin, int anticoagulation, int inr, int inrUom, int inrLoinc) {
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("select DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp ")
		.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" where cp.exme_paciente_id in" )
			//1.
			.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" inner join exme_actpacienteindh ph  inner join exme_actpacienteind api ")
			.append("  on api.exme_actpacienteindh_id = ph.exme_actpacienteindh_id " )
			.append("  on ph.exme_ctapac_id = pm.exme_ctapac_id")
			.append(" inner join exme_estudiosobx obx on obx.exme_actpacienteind_id = api.exme_actpacienteind_id" )
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" AND pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")	
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (pm.endDate - pm.startDate) >= 5 AND pm.docStatus = ?")
			.append(" AND pml.apliedDate IS NOT NULL ")
			.append("AND api.m_product_id in ")
			.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and obx.observation >= '2' and obx.C_UOM_ID = ? ")
			.append(" AND obx.codeobx IN ")
				.append(" (SELECT value FROM EXME_LOINC WHERE EXME_LOINC_ID IN ")
					.append(" (SELECT EXME_Loinc_ID FROM EXME_LoincTypeDet WHERE EXME_LoincType_ID = ?)))")
		//2.
		.append(" OR cp.exme_paciente_id in ")		
			.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" inner join exme_actpacienteindh ph  inner join exme_actpacienteind api ")
			.append(" on api.exme_actpacienteindh_id = ph.exme_actpacienteindh_id" )
			.append(" on ph.exme_ctapac_id = pm.exme_ctapac_id ")
			.append(" inner join exme_estudiosobx obx on obx.exme_actpacienteind_id = api.exme_actpacienteind_id" )
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" AND pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")	
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (pm.endDate - pm.startDate) >= 5 ")
			.append(" AND pml.apliedDate IS NOT NULL ")
			.append("AND api.m_product_id in ")
			.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and obx.observation < '2' and obx.C_UOM_ID = ? ")
			.append(" and pm.exme_ctapac_id in ")
				.append(" (select distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append("  where api.m_product_id in ")
					.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
				.append(" AND api.m_product_id in ")
					.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")	
				.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
				.append(" and ph.prealta = 'Y')")
			.append(" AND obx.codeobx IN ")
				.append(" (SELECT value FROM EXME_LOINC WHERE EXME_LOINC_ID IN ")
					.append(" (SELECT EXME_Loinc_ID FROM EXME_LoincTypeDet WHERE EXME_LoincType_ID = ?)))")	
		//3.     
		.append(" OR cp.exme_paciente_id in ")		
			.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" AND pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")	
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and (pm.endDate - pm.startDate) < 5 ")
			.append(" AND pml.apliedDate IS NOT NULL ")
			.append(" and pm.exme_ctapac_id in ")
				.append(" (select distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append("  where api.m_product_id in ")
					.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
				.append(" AND api.m_product_id in ")
					.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")	
				.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
				.append(" and ph.prealta = 'Y'))")
		//4.
		.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		
		pacientes = getSQLValue(null, str.toString(), warfarin, anticoagulation, initDate, finishDate, MPlanMed.DOCSTATUS_Voided,
				 inr, initDate, finishDate, inrUom, inrLoinc, warfarin, anticoagulation, initDate, finishDate, inr, initDate, finishDate, inrUom,
				 warfarin, anticoagulation, initDate, finishDate, inrLoinc, warfarin, anticoagulation, initDate, finishDate,
				 warfarin, anticoagulation, initDate, finishDate);		
		
		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tienen un diagnostico de VTE al ser dados de alta.		
	 *<p> -2. <i>(A)</i> Y que recibieron terapia de warfarin.					
	 * @return int numero de Pacientes
	 */
	protected List<Integer> denominatorVTE3(String initDate, String finishDate, int warfarin, int vte) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" WHERE cp.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				//1.
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")			
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")	
		.append(" AND cp.EncounterStatus = ? ")  // Ren. EncounterStatus StatusAlta
		.append(" AND cp.exme_paciente_id in")
		//2.
		.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')) ");
		
		pacientes = getSQLValue(null, str.toString(), vte,  initDate, finishDate, 
				MActPacienteDiag.ESTATUS_Inactive, MEXMECtaPac.ENCOUNTERSTATUS_Discharge, warfarin, initDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que no tengan diagnostico de VTE
	 *<p> -2. <i>(A)</i> # Que no se le haya recetado la terapia warfarin
	 *<p> -3. <i>(A)</i> # Que no se le haya administrado la terapia warfarin	
	 *<p> -4. # Que sean menores de 18 anios
	 *<p> -5. <i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p> -6. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 * @return pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaVT3 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> exclusionVTE3(String initDate, String finishDate, int warfarin, int vte, int clinicalTrial) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" INNER JOIN  EXME_Paciente ep ON (ep.EXME_Paciente_ID = cp.EXME_Paciente_ID)")
		.append(" WHERE cp.exme_paciente_id NOT IN( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				//1.
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")			
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")	
		.append(" AND cp.EncounterStatus = ? ")  // Ren. EncounterStatus StatusAlta
			//2.
		.append(" OR cp.exme_paciente_id NOT IN ")
			.append(" (select distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
			.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
			.append("  where api.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and ph.prealta = 'Y')")
		//3.
		.append(" OR cp.exme_paciente_id NOT IN" )	
			.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")	
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')) ")
		//4.
		.append(" OR trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
		//5.
		.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
			.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
		//6.
		.append(" OR cp.EXME_MotivoCita_ID  = ? ");

		
		pacientes = getSQLValue(null, str.toString(), vte,  initDate, finishDate, 
				MActPacienteDiag.ESTATUS_Inactive, MEXMECtaPac.ENCOUNTERSTATUS_Discharge, warfarin, initDate, finishDate, 
				warfarin, initDate, finishDate, clinicalTrial);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia ya que este no tiene referencias. Jesus Cantu
	 */
	public File generaVTE3() throws Exception {		

		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Vte3();
		Date fechaFin = params.getFecha_Fin_Vte3();		

		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);

		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();

		List<Integer> numerator = numeratorVTE3(initDate, finishDate, params.getEXME_TerapiaWarafina_ID(), params.getEXME_TerapiaAnticoagulante_ID(), 
				params.getINR_Procedure(), params.getINR_Measure(), params.getINR_Loinc());
		List<Integer> denominator = denominatorVTE3(initDate, finishDate, params.getEXME_TerapiaWarafina_ID(), params.getVTE_Diagnostic());
		List<Integer> exclusions = exclusionVTE3(initDate, finishDate, params.getEXME_TerapiaWarafina_ID(), params.getVTE_Diagnostic(), params.getTrial_Reason());
		
		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_VENOUSTHROMBOEMBOLISMVTE_3);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Vte3());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Vte3());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);

		pqris.add(pqri(PQRI_NUMBERVTE3, numerator, denominator, exclusions));				

		File generado = xml.armaXml(FILEVTE3, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	

		return generado;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(A)</i> Que recibieron terapia de UFH.		
	 *<p> -2. Y que tuvieron un monitoreo de platelet en una fecha menor a la toma de medicamentos.
	 *<p> -4. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaVTE4 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> numeratorVTE4(String initDate, String finishDate, int ufh, int platelet, int plateletUom, int plateletLoinc) {
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("select DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp ")
		.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" where cp.exme_paciente_id in" )
			//1.
			.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" inner join exme_actpacienteindh ph  inner join exme_actpacienteind api ")
			.append(" on api.exme_actpacienteindh_id = ph.exme_actpacienteindh_id" )
			.append(" on ph.exme_ctapac_id = pm.exme_ctapac_id ")
			.append(" inner join exme_estudiosobx obx on obx.exme_actpacienteind_id = api.exme_actpacienteind_id" )
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")	
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			//2.
			.append("AND api.m_product_id in ")
			.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and obx.C_UOM_ID = ? and obx.observationDate < pml.apliedDate")
			.append(" AND obx.codeobx IN ")
				.append(" (SELECT value FROM EXME_LOINC WHERE EXME_LOINC_ID IN ")
					.append(" (SELECT EXME_Loinc_ID FROM EXME_LoincTypeDet WHERE EXME_LoincType_ID = ?)))")
		//3.
		.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		
		pacientes = getSQLValue(null, str.toString(), ufh, initDate, finishDate, platelet, initDate, finishDate, plateletUom, plateletLoinc);		
		
		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tienen un diagnostico de VTE al ser dados de alta.		
	 *<p> -2. <i>(A)</i> Y que recibieron terapia de warfarin.					
	 * @return int numero de Pacientes
	 */
	protected List<Integer> denominatorVTE4(String initDate, String finishDate, int ufh, int vte) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" WHERE cp.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				//1.
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")			
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")	
		.append(" AND cp.EncounterStatus = ? ") // Ren. EncounterStatus StatusAlta
		.append(" AND cp.exme_paciente_id in")
		//2.
		.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')) ");
		
		pacientes = getSQLValue(null, str.toString(), vte,  initDate, finishDate, 
				MActPacienteDiag.ESTATUS_Inactive, MEXMECtaPac.ENCOUNTERSTATUS_Discharge, ufh, initDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes
	 *<p> -1. <i>(B)</i> Que no tengan diagnostico de VTE
	 *<p> -2. <i>(A)</i> Que no se le haya administrado la terapia UFH
	 *<p> -3. # Que sean menores de 18 anios
	 *<p> -4. <i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p> -5. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 * @return pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaVTE4 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> exclusionVTE4(String initDate, String finishDate, int ufh, int vte, int clinicalTrial) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" INNER JOIN  EXME_Paciente ep ON (ep.EXME_Paciente_ID = cp.EXME_Paciente_ID)")
		.append(" WHERE cp.exme_paciente_id NOT IN( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				//1.
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")			
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")	
			//2.
		.append(" OR cp.exme_paciente_id NOT IN ")
			.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" WHERE pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")	
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')) ")
		//3.
		.append(" OR trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
		//4.
		.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
			.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
		//5.
		.append(" OR cp.EXME_MotivoCita_ID  = ? ");
		
		pacientes = getSQLValue(null, str.toString(), vte, initDate, finishDate, 
				MActPacienteDiag.ESTATUS_Inactive, ufh, initDate, finishDate, clinicalTrial);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia ya que este no tiene referencias. Jesus Cantu
	 */
	public File generaVTE4() throws Exception {		

		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Vte4();
		Date fechaFin = params.getFecha_Fin_Vte4();		

		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);

		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();

		List<Integer> numerator = numeratorVTE4(initDate, finishDate, params.getEXME_TerapiaHeparin_ID(), params.getPlatelet_Procedure(), 
				params.getPlatelet_Measure(), params.getPlatelet_Loinc());
		List<Integer> denominator = denominatorVTE4(initDate, finishDate, params.getEXME_TerapiaHeparin_ID(), params.getVTE_Diagnostic());
		List<Integer> exclusions = exclusionVTE4(initDate, finishDate, params.getEXME_TerapiaHeparin_ID(), params.getVTE_Diagnostic(), params.getTrial_Reason());
		
		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_VENOUSTHROMBOEMBOLISMVTE_4);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Vte4());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Vte4());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);

		pqris.add(pqri(PQRI_NUMBERVTE4, numerator, denominator, exclusions));				

		File generado = xml.armaXml(FILEVTE4, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	

		return generado;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. Que tienen una instruccion de alta.		
	 *<p> -2. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaVTE5 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu 
	 */
	protected List<Integer> numeratorVTE5(String initDate, String finishDate) {
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("select DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp ")
		.append(" INNER JOIN  EXME_Paciente ep ON (ep.EXME_Paciente_ID = cp.EXME_Paciente_ID) ")
		.append(" WHERE cp.EncounterStatus = ? ")	 // Ren. EncounterStatus StatusAlta	
		.append(" AND cp.fechaAlta between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")		
		//1.
		.append(" AND (cp.instruccionalta is not null or cp.nombrearchivo is not null)")
		//2.
		.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		
		pacientes = getSQLValue(null, str.toString(), MEXMECtaPac.ENCOUNTERSTATUS_Discharge, initDate, finishDate );		
		
		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tienen un diagnostico de VTE al ser dados de alta.		
	 *<p> -2. <i>(A)</i> Y (que recibieron terapia de warfarin.				
	 * *<p> -3. <i>(A)</i> # que se dieron de alta con receta de terapia warfarin)					
	 * @return int numero de Pacientes
	 */
	protected List<Integer> denominatorVTE5(String initDate, String finishDate, int warfarin, int vte) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" WHERE cp.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				//1.
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")			
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")	
		.append(" AND cp.EncounterStatus = ? ") // Ren. EncounterStatus StatusAlta
		//2.
		.append(" AND (cp.exme_paciente_id in")		
			.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
			.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
			.append(" where pm.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			//3.
			.append(" OR cp.exme_paciente_id in ")
				.append(" (select distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append("  where api.m_product_id in ")
					.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
				.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
				.append(" and ph.prealta = 'Y')))");
		
		pacientes = getSQLValue(null, str.toString(), vte,  initDate, finishDate, 
				MActPacienteDiag.ESTATUS_Inactive, MEXMECtaPac.ENCOUNTERSTATUS_Discharge, warfarin, initDate, finishDate,
				warfarin, initDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que no tengan diagnostico de VTE
	 *<p> -2. <i>(A)</i> # Que no se le haya recetado la terapia warfarin	
	 *<p> -3. # Que sean menores de 18 anios
	 *<p> -4. <i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p> -5. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 * @return pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaVTE5 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> exclusionVTE5(String initDate, String finishDate, int warfarin, int vte, int clinicalTrial) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" INNER JOIN  EXME_Paciente ep ON (ep.EXME_Paciente_ID = cp.EXME_Paciente_ID)")
		.append(" WHERE cp.exme_paciente_id NOT IN( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				//1.
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")			
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")	
		.append(" AND cp.EncounterStatus = ? ") // Ren. EncounterStatus StatusAlta
			//2.
		.append(" OR cp.exme_paciente_id NOT IN ")
			.append(" (select distinct ap.exme_paciente_id from exme_actpacienteind api inner join exme_actpacienteindh ph ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
			.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
			.append("  where api.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" and ph.prealta = 'Y')")
		//3.
		.append(" OR trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
		//4.
		.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
			.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
		//5.
		.append(" OR cp.EXME_MotivoCita_ID  = ? ");
		
		pacientes = getSQLValue(null, str.toString(), vte,  initDate, finishDate, 
				MActPacienteDiag.ESTATUS_Inactive, MEXMECtaPac.ENCOUNTERSTATUS_Discharge, warfarin, initDate, finishDate, clinicalTrial);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia ya que este no tiene referencias. Jesus Cantu  
	 */
	public File generaVTE5() throws Exception {		

		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Vte5();
		Date fechaFin = params.getFecha_Fin_Vte5();		

		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);

		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();

		List<Integer> numerator = numeratorVTE5(initDate, finishDate);
		List<Integer> denominator = denominatorVTE5(initDate, finishDate, params.getEXME_TerapiaWarafina_ID(), params.getVTE_Diagnostic());
		List<Integer> exclusions = exclusionVTE5(initDate, finishDate, params.getEXME_TerapiaWarafina_ID(), params.getVTE_Diagnostic(), params.getTrial_Reason());
		
		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_VENOUSTHROMBOEMBOLISMVTE_5);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Vte5());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Vte5());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);

		pqris.add(pqri(PQRI_NUMBERVTE5, numerator, denominator, exclusions));				

		File generado = xml.armaXml(FILEVTE5, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	

		return generado;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(A)</i> Con diagnostico de VTE quienes no recibieron un VTE Prophilaxis entre la admision 
	 *<br>				y la fecha antes de la solicitud de la orden de servicio de laboratorio.	
	 *<p> -2. Y que sean mayores de 18 anios
	 * @return int numero de Pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaVTE6 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> numeratorVTE6(String initDate, String finishDate, int vte, int vteProphylaxis) {
		List<Integer> pacientes = new ArrayList<Integer>();		
		
		StringBuilder str = new StringBuilder("select DISTINCT cp.EXME_CtaPac_ID");
		str.append(" from exme_ctapac cp  ")
		.append(" INNER JOIN EXME_Paciente ep ON (ep.EXME_Paciente_ID = cp.EXME_Paciente_ID)")
		//1.
		.append(" where cp.exme_paciente_id in")
		.append(" (select ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append( " where diag.exme_diagnostico_id in  ")
				.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")		
			.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') " )
			.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")
		.append(" and cp.exme_paciente_id NOT IN ")
			.append(" (select distinct ap.exme_paciente_id  from exme_actpacienteind api " )	
			.append(" inner join exme_actpacienteindh ph ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id " )
			.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id where api.m_product_id in ")
				.append(" (select m_product_id from exme_terapia_producto where exme_terapia_id = ? ) ")
			.append(" and api.dateordered > cp.dateOrdered )")
		//2.
		.append(" AND trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) >= 18");
		
		pacientes = getSQLValue(null, str.toString(), vte, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive, vteProphylaxis);		
		
		return pacientes;

	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tienen un diagnostico de VTE desarrollado durante la estancia en el hospital.		
	 * @return int numero de Pacientes
	 */
	protected List<Integer> denominatorVTE6(String initDate, String finishDate, int vte) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" WHERE cp.fechaAlta IS NOT NULL ")
			.append(" AND cp.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				//1.
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")			
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y' ")
		.append(" AND diag.fecha_diagnostico between cp.dateOrdered and cp.fechaAlta)");
		
		pacientes = getSQLValue(null, str.toString(), vte,  initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive);
		
		return pacientes;
	}
	
	/**
	 * Exclusiones
	 *<br> Calcula los pacientes
	 *<p> -1. <i>(B)</i> Que tengan diagnostico de VTE al llegar al hospital
	 *<p> -2. # Que sean menores de 18 anios
	 *<p> -3. <i>(D)</i> # Una estancia mayor de 120 dias en el hospital
	 *<p> -4. # motivo de ingreso por Clinical Trial (cat#logo EXME_MotivoCita)
	 * @return pacientes
	 * @deprecated Se deprecia ya que solo es llamado de generaVTE6 y este no tiene referencias.
	 *             No se corrige lo del DATE_TRUNC para postgresql. Jesus Cantu  
	 */
	protected List<Integer> exclusionVTE6(String initDate, String finishDate, int vte, int clinicalTrial) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.EXME_CtaPac_ID  FROM exme_ctapac cp");
		str.append(" INNER JOIN  EXME_Paciente ep ON (ep.EXME_Paciente_ID = cp.EXME_Paciente_ID)")
		.append(" WHERE cp.exme_paciente_id  IN( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap ")
			.append(" on ap.exme_actpaciente_id = diag.exme_actpaciente_id where diag.exme_diagnostico_id in (")
				//1.
				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")			
		.append(" and diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
		.append(" AND diag.fecha_diagnostico < cp.dateOrdered")
		.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') ")	
		//2.
		.append(" OR trunc(trunc((((86400*(").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append("-ep.fechanac))/60)/60)/24)/365) < 18")
		//3.
		.append(" OR (CASE WHEN cp.fechaAlta IS NOT NULL THEN (cp.fechaAlta - cp.dateordered) ")
			.append(" ELSE (").append(DB.TO_DATE(DB.getTimestampForOrg(this.ctx))).append(" - cp.dateordered) END >= 120) ")
		//4.
		.append(" OR cp.EXME_MotivoCita_ID  = ? ");
		
		pacientes = getSQLValue(null, str.toString(), vte, initDate, finishDate, MActPacienteDiag.ESTATUS_Inactive, clinicalTrial);
		
		return pacientes;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated Se deprecia ya que este no tiene referencias. Jesus Cantu
	 */
	public File generaVTE6() throws Exception {		

		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Vte6();
		Date fechaFin = params.getFecha_Fin_Vte6();		

		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);

		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();

		List<Integer> numerator = numeratorVTE6(initDate, finishDate, params.getVTE_Diagnostic(), params.getEXME_ProphylaxisVte_ID());
		List<Integer> denominator = denominatorVTE6(initDate, finishDate, params.getVTE_Diagnostic());
		List<Integer> exclusions = exclusionVTE6(initDate, finishDate, params.getVTE_Diagnostic(), params.getTrial_Reason());
		
		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_VENOUSTHROMBOEMBOLISMVTE_6);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Vte6());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Vte6());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Denominator);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);

		pqris.add(pqri(PQRI_NUMBERVTE6, numerator, denominator, exclusions));				

		File generado = xml.armaXml(FILEVTE6, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	

		return generado;
	}
	
	/**
	 * Numerador
	 *<br> Calcula 
	 *<p> -1. Los pacientes que entraron por la estacion de servicio
	 *<br> de emergencias y que salieron por la misma estacion
	 * @return minutos
	 */
	protected List<Integer> numeratorED1(String initDate, String finishDate, int emergency) {
		List<Integer> pacientes = new ArrayList<Integer>();

		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.exme_ctapac_id as patients");
		str.append(" FROM EXME_CTAPAC CP ")
		.append(" WHERE CP.exme_estserving_id = ? ")
		.append(" AND CP.exme_estserv_id = ? ")
		.append(" AND CP.dateordered between to_date(? ,'dd/MM/yyyy') AND to_date(? ,'dd/MM/yyyy') " )
		.append(" AND cp.fechacierre IS NOT NULL ")
		.append(" AND cp.fechacierre <= to_date(?,'dd/MM/yyyy') " )	;

		pacientes = getSQLValue(null, str.toString(), emergency, emergency, initDate, finishDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * Numerador
	 *<br> Calcula 
	 *<p> -1. El tiempo medio en minutos que estuvieron los pacientes desde que entraron por la estacion de servicio
	 *<br> de emergencias hasta que salieron por la misma estacion
	 * @return minutos
	 */
	protected List<Integer> numeratorED2(String initDate, String finishDate, int emergency, int hosp) {
		List<Integer> pacientes = new ArrayList<Integer>();

		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.exme_ctapac_id as patients");
		str.append(" FROM EXME_CTAPAC CP ")
		.append(" WHERE CP.exme_estserving_id = ? ")
		.append(" AND CP.exme_estserv_id = ? ")
		.append(" AND CP.dateordered between to_date(? ,'dd/MM/yyyy') AND to_date(? ,'dd/MM/yyyy') " )
		.append(" AND cp.fechaTraslado IS NOT NULL ")
		.append(" AND cp.fechaTraslado <= to_date(?,'dd/MM/yyyy') " )	;

		pacientes = getSQLValue(null, str.toString(), hosp, emergency, initDate, finishDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * Numerador
	 *<br> Calcula 
	 *<p> -1. El tiempo medio en minutos que estuvieron los pacientes desde que entraron por la estacion de servicio
	 *<br> de emergencias hasta que salieron por la misma estacion
	 * @return minutos
	 */
	protected int numeratorMinED1(String initDate, String finishDate, int emergency) throws Exception{
		int min = 0;

		StringBuilder str = new StringBuilder(" SELECT COUNT(DISTINCT cp.exme_ctapac_ID),");
		str.append(" SUM(cp.fechatraslado - cp.dateordered) as days ")
		.append(" FROM EXME_CTAPAC CP ")
		.append(" WHERE CP.exme_estserving_id = ? ")
		.append(" AND CP.exme_estserv_id = ? ")
		.append(" AND CP.dateordered between to_date(? ,'dd/MM/yyyy') AND to_date(? ,'dd/MM/yyyy') " )
		.append(" AND cp.fechacierre IS NOT NULL ")
		.append(" AND cp.fechacierre <= to_date(?,'dd/MM/yyyy') " )	;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		int patients = 0;
		int days = 0;
		try {
			pstmt = DB.prepareStatement(str.toString(), null);
			
			pstmt.setInt(1, emergency);
			pstmt.setInt(2, emergency);
			pstmt.setString(3, initDate);
			pstmt.setString(4, finishDate);
			pstmt.setString(5, finishDate);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				patients = rs.getInt(1);
				days = rs.getInt(2);				
			}

			double diasDouble = Double.valueOf(days);
			double minutosDouble = 0;

			minutosDouble = (diasDouble * 24) * 60;			
			//Validar division entre cero
			BigDecimal minutes = patients == 0 ? BigDecimal.ZERO : new BigDecimal(minutosDouble / patients);
			minutes = minutes.setScale(2, BigDecimal.ROUND_CEILING);
			min = minutes.intValue();

		} catch (Exception e) {
			log.log(Level.SEVERE, str.toString(), e.getMessage());
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "closing rs / pstmt", e);
				throw e;
			}
		}
		
		return min;
	}
	
	/**
	 * Numerador
	 *<br> Calcula 
	 *<p> -1. El tiempo medio en minutos que estuvieron los pacientes desde que entraron por la estacion de servicio
	 *<br> de emergencias hasta que salieron por la misma estacion
	 * @return minutos
	 */
	protected int numeratorMinED2(String initDate, String finishDate, int emergency, int hosp) throws Exception{
		int min = 0;

		StringBuilder str = new StringBuilder("SELECT COUNT( DISTINCT cp.exme_ctapac_id) as patients, ");
		str.append(" SUM(cp.fechatraslado - cp.dateordered) as days")
		.append(" FROM EXME_CTAPAC CP ")
		.append(" WHERE CP.exme_estserving_id = ? ")
		.append(" AND CP.exme_estserv_id = ? ")
		.append(" AND CP.dateordered between to_date(? ,'dd/MM/yyyy') AND to_date(? ,'dd/MM/yyyy') " )
		.append(" AND cp.fechaTraslado IS NOT NULL ")
		.append(" AND cp.fechaTraslado <= to_date(?,'dd/MM/yyyy') " )	;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		int patients = 0;
		int days = 0;
		try {
			pstmt = DB.prepareStatement(str.toString(), null);
			
			pstmt.setInt(1, hosp);
			pstmt.setInt(2, emergency);
			pstmt.setString(3, initDate);
			pstmt.setString(4, finishDate);
			pstmt.setString(5, finishDate);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				patients = rs.getInt(1);
				days = rs.getInt(2);			
			}

			double diasDouble = Double.valueOf(days);
			double minutosDouble = 0;

			minutosDouble = (diasDouble * 24) * 60;			
			//Validar division entre cero
			BigDecimal minutes = patients == 0 ? BigDecimal.ZERO : new BigDecimal(minutosDouble / patients);
			minutes = minutes.setScale(2, BigDecimal.ROUND_CEILING);
			min = minutes.intValue();

		} catch (Exception e) {
			log.log(Level.SEVERE, str.toString(), e.getMessage());
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "closing rs / pstmt", e);
				throw e;
			}
		}
		
		return min;
	}
	
	/**
	 * Exclusion
	 *<br> Calcula los pacientes
	 *<p> -1. Que entren por observacion
	 *<p> -2. O que sean pacientes mentales
	 * @return pacientes
	 */
	protected List<Integer> exclusionED(String initDate, String finishDate, int obsPatient, int mentalPatient) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.exme_ctapac_id as patients ");
		str.append(" FROM EXME_CtaPac cp ")
		.append("inner join exme_paciente ep  on (ep.exme_paciente_id = cp.exme_paciente_id )")
		.append(" inner join exme_tipopaciente tp on (tp.exme_tipopaciente_id = ep.exme_tipopaciente_id)")
		//1.
		.append(" where cp.exme_motivocita_id = ? ")
		//2.	
		.append(" OR tp.exme_tipopaciente_id = ? ")	
		.append(" AND cp.dateordered between to_date(? ,'dd/MM/yyyy') AND to_date(? ,'dd/MM/yyyy') " )
		.append(" AND cp.fechacierre <= to_date(?,'dd/MM/yyyy') " )	;
		
		pacientes = getSQLValue(null, str.toString(), obsPatient, mentalPatient, initDate, finishDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes
	 *<p> -1. Que hayan ingresado por emergencias
	 * @return pacientes
	 */
	protected List<Integer> denominatorEDA(String initDate, String finishDate, int emergency) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.exme_ctapac_id as patients ");
		str.append(" FROM EXME_CtaPac cp ")
		.append(" WHERE cp.exme_estserv_id = ? ")
		.append(" AND cp.dateordered between to_date(? ,'dd/MM/yyyy') AND to_date(? ,'dd/MM/yyyy') " )
		.append(" AND cp.fechacierre <= to_date(?,'dd/MM/yyyy') " )	;
		
		pacientes = getSQLValue(null, str.toString(), emergency, initDate, finishDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes
	 *<p> -1. En observacion que hayan ingresado por emergencias
	 * @return pacientes
	 */
	protected List<Integer> denominatorEDB(String initDate, String finishDate, int emergency, int obsPatient) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.exme_ctapac_id as patients ");
		str.append(" FROM EXME_CtaPac cp ")
		.append(" WHERE cp.exme_estserv_id = ? ")
		.append(" AND cp.exme_motivocita_id = ? ")
		.append(" AND cp.dateordered between to_date(? ,'dd/MM/yyyy') AND to_date(? ,'dd/MM/yyyy') " )
		.append(" AND cp.fechacierre <= to_date(?,'dd/MM/yyyy') " )	;
		
		pacientes = getSQLValue(null, str.toString(), emergency, obsPatient, initDate, finishDate, finishDate);
		
		return pacientes;
	}
	
	/**
	 * Denominador
	 *<br> Calcula los pacientes
	 *<p> -1. De tipo mental que hayan ingresado por emergencias
	 * @return pacientes
	 */
	protected List<Integer> denominatorEDC(String initDate, String finishDate, int emergency, int mentalPatient) {
		List<Integer> pacientes = new ArrayList<Integer>();
		
		StringBuilder str = new StringBuilder("SELECT DISTINCT cp.exme_ctapac_id as patients ");
		str.append(" FROM EXME_CtaPac cp ")
		.append("inner join exme_paciente ep  on (ep.exme_paciente_id = cp.exme_paciente_id )")
		.append(" inner join exme_tipopaciente tp on (tp.exme_tipopaciente_id = ep.exme_tipopaciente_id)")
		.append(" WHERE cp.exme_estserv_id = ? ")
		.append(" AND tp.exme_tipoPaciente_ID = ? ")	
		.append(" AND cp.dateordered between to_date(? ,'dd/MM/yyyy') AND to_date(? ,'dd/MM/yyyy') " )
		.append(" AND cp.fechacierre <= to_date(?,'dd/MM/yyyy') " )	;
		
		pacientes = getSQLValue(null, str.toString(), emergency, mentalPatient, initDate, finishDate, finishDate);
		
		return pacientes;
	}
	
	public File generaED1() throws Exception {		

		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Urgencia_ED();
		Date fechaFin = params.getFecha_Fin_Urgencia_ED();		

		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);

		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		
		int min = numeratorMinED1(initDate, finishDate, params.getEXME_EstServ_Urgencias_ID());
		List<Integer> numerator = numeratorED1(initDate, finishDate, params.getEXME_EstServ_Urgencias_ID());
		List<Integer> denominatorA = denominatorEDA(initDate, finishDate, params.getEXME_EstServ_Urgencias_ID());
		List<Integer> denominatorB = denominatorEDB(initDate, finishDate, params.getEXME_EstServ_Urgencias_ID(), params.getObsPatient());
		List<Integer> denominatorC = denominatorEDC(initDate, finishDate, params.getEXME_EstServ_Urgencias_ID(), params.getMentalPatient());
		List<Integer> exclusions = exclusionED(initDate, finishDate, params.getObsPatient(), params.getMentalPatient());
		
		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_EmergencyDepartment1);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Urgencia_ED());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Urgencia_ED());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominatorA, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_DenominatorA);
		saveDetail(denominatorB, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_DenominatorB);
		saveDetail(denominatorC, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_DenominatorC);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);

		pqris.add(pqri(PQRI_NUMBERED1A, min, denominatorA, exclusions));
		pqris.add(pqri(PQRI_NUMBERED1B, min, denominatorB, null));
		pqris.add(pqri(PQRI_NUMBERED1C, min, denominatorC, null));

		File generado = xml.armaXml(FILEED1, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	

		return generado;
	}	
	
	public File generaED2() throws Exception {		

		//Llenar parametros de la tabla
		Date fechaIni = params.getFecha_Ini_Urgencia_S3();
		Date fechaFin = params.getFecha_Fin_Urgencia_S3();		

		String initDate = Constantes.getSdfFecha().format(fechaIni);
		String finishDate = Constantes.getSdfFecha().format(fechaFin);

		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		
		int min = numeratorMinED2(initDate, finishDate, params.getEXME_EstServ_Urgencias_ID(), params.getEXME_EstServ_Hosp_ID());
		List<Integer> numerator = numeratorED2(initDate, finishDate, params.getEXME_EstServ_Urgencias_ID(), params.getEXME_EstServ_Hosp_ID());
		List<Integer> denominatorA = denominatorEDA(initDate, finishDate, params.getEXME_EstServ_Urgencias_ID());
		List<Integer> denominatorB = denominatorEDB(initDate, finishDate, params.getEXME_EstServ_Urgencias_ID(), params.getObsPatient());
		List<Integer> denominatorC = denominatorEDC(initDate, finishDate, params.getEXME_EstServ_Urgencias_ID(), params.getMentalPatient());
		List<Integer> exclusions = exclusionED(initDate, finishDate, params.getObsPatient(), params.getMentalPatient());

		MEXMEGenQualityMeasure qualityMeas = new MEXMEGenQualityMeasure(ctx, 0, trxName);
		qualityMeas.setReport(MEXMEGenQualityMeasure.REPORT_EmergencyDepartment2);
		qualityMeas.setFolio(folio);
		qualityMeas.setFecha_Ini_Urgencia(params.getFecha_Ini_Urgencia_ED());
		qualityMeas.setFecha_Fin_Urgencia(params.getFecha_Fin_Urgencia_ED());
		if (!qualityMeas.save(trxName)) {
			try {
				log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "error.guardar"));
				throw new Exception("error.guardar");					
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		saveDetail(numerator, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Numerator);
		saveDetail(denominatorA, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_DenominatorA);
		saveDetail(denominatorB, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_DenominatorB);
		saveDetail(denominatorC, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_DenominatorC);
		saveDetail(exclusions, qualityMeas.getEXME_GenQualityMeasure_ID(), MEXMEGenQualityMeasureDet.TIPO_REPORTE_Exclusion);

		pqris.add(pqri(PQRI_NUMBERED2A, min, denominatorA, exclusions));
		pqris.add(pqri(PQRI_NUMBERED2B, min, denominatorB, null));
		pqris.add(pqri(PQRI_NUMBERED2C, min, denominatorC, null));

		File generado = xml.armaXml(FILEED2, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	

		return generado;
	}	
}
