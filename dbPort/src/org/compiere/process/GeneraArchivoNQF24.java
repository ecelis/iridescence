package org.compiere.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MActPacienteDiag;
import org.compiere.model.MEXMEParamMetricas;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0024
 * Weight Assessment and Counseling for Children and Asolescents
 * 1.1,1.2,1.3
 * 2.1,2.2,2.3
 * 3.1,3.2,3.3
 * @author Rosy Velazquez
 */
public class GeneraArchivoNQF24{
	
	public GeneraArchivoNQF24(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
		this.numFiles = files;
	}

	private final int ageFrom1 = 2; //age from					
	private final int ageTo1 = 16; //age to
	
	private final int ageFrom2 = 2; //age from					
	private final int ageTo2 = 10; //age to
	
	private final int ageFrom3 = 11; //age from					
	private final int ageTo3 = 16; //age to
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie	
	private String strIni = "";
	private String strFin = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER_WEIGHT = "0024"; //Pendiente num ---NQF 0024	
	
	private String archivo = "NQF_0024";
	private String measureGroup = "X"; //Sin grupo
	
	private int pregnancy = 0;
	
	private Properties ctx;
	private String trxName;	
	
	private int numFiles = 1;
	private int numFile = 1;
	
	private int outpatient = 0; //TipoPaciente
	private int counselingNutrition = 0; //EXME_Procedure_Type
	private int physicalActivity = 0;    //EXME_DiagnosisType
		
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	public File genera() throws Exception {		
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName);		
		
		fechaIni = param.getFecha_Ini_Peso();
		fechaFin = param.getFecha_Fin_Peso();
		pregnancy = param.getPregnancy();
		outpatient = param.getOutpatient();
		counselingNutrition = param.getCounselingNutrition(); 
		physicalActivity = param.getPhysicalActivity();
		
		strIni = f.format(fechaIni);
		strFin = f.format(fechaFin);					
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
					
		//9 pqris
		//Mismo denominador 3 numeradores
		int denominador1 = this.executaQueryDenominator(this.ageFrom1, this.ageTo1);					
		int denominador2 = this.executaQueryDenominator(this.ageFrom2, this.ageTo2);					
		int denominador3 = this.executaQueryDenominator(this.ageFrom3, this.ageTo3);		
				
		pqris.add(CQMeasures.pqri(PQRI_NUMBER_WEIGHT + "_1.1", this.executaQueryNumerator1(this.ageFrom1, this.ageTo1), denominador1));	
		pqris.add(CQMeasures.pqri(PQRI_NUMBER_WEIGHT + "_1.2", this.executaQueryNumerator2(this.ageFrom1, this.ageTo1), denominador1));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER_WEIGHT + "_1.3", this.executaQueryNumerator3(this.ageFrom1, this.ageTo1), denominador1));
		
		pqris.add(CQMeasures.pqri(PQRI_NUMBER_WEIGHT + "_2.1", this.executaQueryNumerator1(this.ageFrom2, this.ageTo2), denominador2));	
		pqris.add(CQMeasures.pqri(PQRI_NUMBER_WEIGHT + "_2.2", this.executaQueryNumerator2(this.ageFrom2, this.ageTo2), denominador2));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER_WEIGHT + "_2.3", this.executaQueryNumerator3(this.ageFrom2, this.ageTo2), denominador2));
		
		pqris.add(CQMeasures.pqri(PQRI_NUMBER_WEIGHT + "_3.1", this.executaQueryNumerator1(this.ageFrom3, this.ageTo3), denominador3));	
		pqris.add(CQMeasures.pqri(PQRI_NUMBER_WEIGHT + "_3.2", this.executaQueryNumerator2(this.ageFrom3, this.ageTo3), denominador3));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER_WEIGHT + "_3.3", this.executaQueryNumerator3(this.ageFrom3, this.ageTo3), denominador3));
		
		File file = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);
				
		return file;
	}
	
	
	/**
	 * Calcula 
	 * Denominador 
	 *    1)
	 * 		1. Edad >= 2 y <=16
	 * 	  2)
	 * 		1. Edad >= 2 y <=10
	 * 	  3)
	 * 		1. Edad >= 11 y <=16
	 * 2. encounter outpatient w/PCP & obgyn
	 * 3. No tengan diagnostico activo de Pregnancy
	 * @return pacientes
	 */
	private int executaQueryDenominator(int inicio, int fin){
		
		int pacientes = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(distinct ep.exme_paciente_id) \n");
		sql.append(" FROM EXME_Paciente ep \n");
		sql.append(" INNER JOIN exme_ctapac cta ON ep.exme_paciente_id = cta.exme_paciente_id \n");
		sql.append(" WHERE cta.exme_tipopaciente_id = ? AND cta.ad_client_id = ? AND cta.ad_org_id = ? \n");
		sql.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) BETWEEN ? AND ? AND ep.isactive = 'Y' AND cta.exme_ctapac_id NOT IN \n");
		sql.append(" (SELECT ap.exme_ctapac_id \n");
		sql.append(" FROM exme_actpacientediag diag \n");
		sql.append(" INNER JOIN exme_actpaciente ap ON ap.exme_actpaciente_id = diag.exme_actpaciente_id \n");
		sql.append(" INNER JOIN EXME_DiagnosisTypeDet DTD ON DTD.EXME_Diagnostico_id = diag.exme_diagnostico_id \n");
		sql.append(" WHERE DTD.EXME_DiagnosisType_ID = ? AND DTD.IsActive = 'Y' \n");
		sql.append(" AND ").append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		sql.append(" BETWEEN to_date(?,'dd/MM/yyyy' ) AND to_date(? ,'dd/MM/yyyy') \n");
		sql.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ?) ");
	
		pacientes = CQMeasures.executaSql(sql.toString(), outpatient, Env.getAD_Client_ID(ctx),   Env.getAD_Org_ID(ctx), this.strIni, inicio, fin, pregnancy, this.strIni, this.strFin, MActPacienteDiag.ESTATUS_Inactive);			
		
		return pacientes;
	}
	
	
	/**
	 * Calcula  
	 * Numerador 1 para la medida
	 * 1. Que tengan examen BMI percentil
	 * 2. Edades del parametro
	 * 3. encounter outpatient w/PCP & obgyn
	 * 4. No tengan diagnostico activo de Pregnancy 
	 * @return pacientes
	 */
	private int executaQueryNumerator1(int inicio, int fin){
		
		int pacientes=0;							
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(DISTINCT hv.exme_paciente_id) \n");
		sql.append(" FROM EXME_METRICAS_IMC hv \n");
		sql.append(" INNER JOIN exme_paciente ep ON ep.exme_paciente_id = hv.exme_paciente_id \n");
		sql.append(" INNER JOIN exme_ctapac ctapac ON ep.exme_paciente_id = ctapac.exme_paciente_id \n");
		sql.append(" WHERE ctapac.exme_tipopaciente_id = ? AND ctapac.ad_client_id = ? AND ctapac.ad_org_id = ? AND \n");
		sql.append(DB.truncDate ("hv.FECHA_IMC", "dd"));
		
		sql.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		sql.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) BETWEEN ? AND ? \n");
		sql.append(" AND hv.isactive = 'Y' AND ep.exme_paciente_id NOT IN \n");
		sql.append(" \t (SELECT ap.exme_paciente_id \n");
		sql.append(" \t FROM exme_actpacientediag diag \n");
		sql.append(" \t INNER JOIN exme_actpaciente ap ON ap.exme_actpaciente_id = diag.exme_actpaciente_id \n");
		sql.append(" \t WHERE diag.exme_diagnostico_id IN \n");
		sql.append(" \t\t (SELECT EXME_Diagnostico_ID \n");
		sql.append(" \t\t FROM EXME_DiagnosisTypeDet \n");
		sql.append(" \t\t WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y') AND \n");
		sql.append(DB.truncDate ("diag.fecha_diagnostico", "dd" ));
		
		sql.append(" \t BETWEEN to_date(? ,'dd/MM/yyyy') AND to_date(?,'dd/MM/yyyy') \n");
		sql.append(" \t	AND diag.IsActive = 'Y' AND diag.Estatus <> ?) \n");

		
		pacientes = CQMeasures.executaSql(sql.toString(), outpatient, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), this.strIni, this.strFin, this.strIni, inicio, fin, pregnancy, this.strIni, this.strFin, MActPacienteDiag.ESTATUS_Inactive);			
			
		return pacientes;
	}
	
	/**
	 * Calcula  
	 * Numerador 2 para la medida
	 * 1. Que tengan counseling for nutrition (CPT ?)
	 * 2. Edades del parametro
	 * 3. encounter outpatient w/PCP & obgyn
	 * 4. No tengan diagnostico activo de Pregnancy  
	 * @return pacientes
	 */
	private int executaQueryNumerator2(int inicio, int fin){
		
		int pacientes=0;							
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(DISTINCT hv.exme_paciente_id) \n");
		sql.append(" FROM EXME_METRICAS_IMC hv \n");
		sql.append(" INNER JOIN exme_paciente ep ON ep.exme_paciente_id = hv.exme_paciente_id \n");
		sql.append(" INNER JOIN exme_ctapac ctapac ON ep.exme_paciente_id = ctapac.exme_paciente_id \n");
		sql.append(" WHERE ctapac.exme_tipopaciente_id = ? AND ctapac.ad_client_id = ? AND ctapac.ad_org_id = ? AND \n"); 
		sql.append(DB.truncDate("hv.FECHA_IMC", "dd")); 
		
		sql.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		sql.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) BETWEEN ? AND ? \n");
		sql.append(" AND hv.isactive = 'Y' AND ep.exme_paciente_id NOT IN \n");
		sql.append(" \t	(SELECT ap.exme_paciente_id \n");
		sql.append(" \t	FROM exme_actpacientediag diag \n");
		sql.append(" \t	INNER JOIN exme_actpaciente ap ON ap.exme_actpaciente_id = diag.exme_actpaciente_id \n");
		sql.append(" \t	WHERE diag.exme_diagnostico_id IN \n");
		sql.append(" \t\t (SELECT EXME_Diagnostico_ID \n");
		sql.append(" \t\t FROM EXME_DiagnosisTypeDet \n");
		sql.append(" \t\t WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y') AND \n");
		sql.append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		
		sql.append(" \t	BETWEEN to_date(? ,'dd/MM/yyyy' ) AND to_date(? ,'dd/MM/yyyy' ) \n");
		sql.append(" \t	AND diag.IsActive = 'Y' AND diag.Estatus <> ?) \n");
		sql.append(" AND ep.exme_paciente_id IN \n");
		sql.append(" \t	(SELECT actp.exme_paciente_id \n");
		sql.append(" \t	FROM exme_actpacienteind ind \n");
		sql.append(" \t	INNER JOIN exme_actpacienteindh indh ON ind.exme_actpacienteindh_id = indh.exme_actpacienteindh_id \n");
		sql.append(" \t	INNER JOIN exme_actpaciente actp ON indh.exme_actpaciente_id = actp.exme_actpaciente_id \n");
		sql.append(" \t	WHERE ind.m_product_id IN \n");
		sql.append(" \t\t (SELECT i.m_product_id \n");
		sql.append(" \t\t FROM EXME_ProcedureType pt \n");
		sql.append(" \t\t INNER JOIN exme_proceduretypedet ptd ON pt.exme_proceduretype_id = ptd.exme_proceduretype_id \n");
		sql.append(" \t\t INNER JOIN exme_intervencion i ON ptd.exme_intervencion_id = i.exme_intervencion_id \n");
		sql.append(" \t\t WHERE pt.exme_proceduretype_id = ?)) \n");
		
		pacientes = CQMeasures.executaSql(sql.toString(), outpatient, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), this.strIni, this.strFin, this.strIni, inicio, fin, pregnancy, this.strIni, this.strFin, MActPacienteDiag.ESTATUS_Inactive, counselingNutrition);			
			
		return pacientes;
	}
	
	/**
	 * Calcula  
	 * Numerador 3 para la medida
	 * 1. Que tengan counseling for physical activity
	 * 2. Edades del parametro
	 * 3. encounter outpatient w/PCP & obgyn
	 * 4. No tengan diagnostico activo de Pregnancy  
	 * @return pacientes
	 */
	private int executaQueryNumerator3(int inicio, int fin){
		
		int pacientes=0;							
	
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(DISTINCT hv.exme_paciente_id) \n");
		sql.append(" FROM EXME_METRICAS_IMC hv \n");
		sql.append(" INNER JOIN exme_paciente ep ON ep.exme_paciente_id = hv.exme_paciente_id \n");
		sql.append(" INNER JOIN exme_ctapac ctapac ON ep.exme_paciente_id = ctapac.exme_paciente_id \n");
		sql.append(" WHERE ctapac.exme_tipopaciente_id = ? AND ctapac.ad_client_id = ? AND ctapac.ad_org_id = ? AND \n");
		sql.append(DB.truncDate("hv.FECHA_IMC", "dd"));
		
		sql.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		sql.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) BETWEEN ? AND ? \n");
		sql.append(" AND hv.isactive = 'Y' AND ep.exme_paciente_id NOT IN \n");

		sql.append("\t (SELECT ap.exme_paciente_id \n");
		sql.append("\t FROM exme_actpacientediag diag \n");
		sql.append("\t INNER JOIN exme_actpaciente ap ON ap.exme_actpaciente_id = diag.exme_actpaciente_id \n");
		sql.append("\t WHERE diag.exme_diagnostico_id IN \n");
		sql.append("\t\t (SELECT EXME_Diagnostico_ID \n");
		sql.append("\t\t FROM EXME_DiagnosisTypeDet \n");
		sql.append("\t\t WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y') AND \n");
		sql.append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		sql.append("\t BETWEEN to_date(?,'dd/MM/yyyy') AND to_date(? ,'dd/MM/yyyy') \n");
		sql.append("\t AND diag.IsActive = 'Y' AND diag.Estatus <> ?) \n");

		sql.append(" AND ep.exme_paciente_id IN \n");
		sql.append("\t (SELECT ap.exme_paciente_id \n");
		sql.append("\t FROM exme_actpacientediag diag \n");
		sql.append("\t INNER JOIN exme_actpaciente ap ON diag.exme_actpaciente_id = ap.exme_actpaciente_id AND diag.exme_diagnostico_id IN \n");
		sql.append("\t\t (SELECT EXME_Diagnostico_ID \n");
		sql.append("\t\t FROM EXME_DiagnosisTypeDet \n");
		sql.append("\t\t WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y')) \n");
				
		
		pacientes = CQMeasures.executaSql(sql.toString(), outpatient, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), this.strIni, this.strFin, this.strIni, inicio, fin, pregnancy, this.strIni, this.strFin, MActPacienteDiag.ESTATUS_Inactive, physicalActivity);			
			
		return pacientes;
	}
}