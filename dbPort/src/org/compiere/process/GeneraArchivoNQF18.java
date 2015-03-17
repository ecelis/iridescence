package org.compiere.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMEParamMetricas;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0018
 * Hypertension: BP was adecuately controlled during the measurement year
 * @author Rosy Velazquez
 */

public class GeneraArchivoNQF18 {
	private Properties ctx;
	private String trxName;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	
	private String ini = "";
	private String fin = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER = "NQF-0018"; 	//sin pqri number
	
	private String archivo = "NQF_0018";
	private String measureGroup = "X"; //Sin grupo
	
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	private int numFiles = 1;
	private int numFile = 1;
	
	private int hypertensionDiagnosisType = 0;
	private int pregnancyDiagnosisType = 0;
	private int esrdDiagnosisType = 0;
	
	private int sistolic = 0;
	private int diastolic = 0;
	private int outpatient = 0;
	
	public GeneraArchivoNQF18(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
		this.numFiles = files;
	}
	
	public File genera() throws Exception {		
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); //ya esta validado!
				
		//Llenar parametros de la tabla		
		fechaIni = param.getFecha_Ini_NQF18();
		fechaFin = param.getFecha_Fin_NQF18();	
				
		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);
		hypertensionDiagnosisType = param.getHypertension();
		pregnancyDiagnosisType = param.getPregnancy();
		esrdDiagnosisType = param.getESRD();
		diastolic = param.getEXME_SignoVitalDiastolic_ID();
		sistolic = param.getEXME_SignoVitalSystolic_ID();
		outpatient = param.getOutpatient();
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER , this.pacientesMedida(), this.pacientesDenominador()));						
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}	
	
	/**
	 * Numerador 
	 * 1 -Pacientes Edad>=17 y Edad<=84
	 * 2- Con diágnostico de Hypertension
	 * 3- No tenga diágnostico de Pregnancy
	 * 4- No tenga diágnostico de ESRD
	 * 5- Diastolic <90
	 * 6- Y sistolic <140
	 */
	private int pacientesMedida() {
		int pacientes = 0;
		
		StringBuilder str = new StringBuilder(" SELECT COUNT(DISTINCT ep.exme_paciente_id) \n");
				str.append(" FROM exme_ctapac cta \n");
				str.append(" INNER JOIN EXME_paciente ep on cta.exme_paciente_id = ep.exme_paciente_id \n");
				str.append(" INNER JOIN EXME_actpaciente apH on apH.exme_paciente_id = ep.exme_paciente_id \n");
				str.append(" INNER JOIN EXME_actpacientediag apdH on apdH.exme_actpaciente_id = apH.exme_actpaciente_id \n");
				str.append(" INNER JOIN EXME_diagnosistypedet hyper on hyper.exme_diagnostico_id = apdH.exme_diagnostico_id \n");
				str.append(" INNER JOIN EXME_signovitaldet diast on diast.exme_paciente_id = ep.exme_paciente_id \n");
				str.append(" INNER JOIN EXME_signovitaldet sist on sist.exme_paciente_id = ep.exme_paciente_id \n");
				str.append(" WHERE cta.ad_client_id = ? AND cta.ad_org_id = ? AND cta.exme_tipopaciente_id = ? \n");
				str.append(" AND edadmuse(EP.FECHANAC, TO_DATE(?, 'dd/MM/yyyy')::date) BETWEEN 17 AND 84 \n");
				str.append(" AND hyper.EXME_DiagnosisType_ID = ? AND hyper.IsActive = 'Y' \n");
				str.append(" AND ").append(DB.truncDate("apdH.fecha_diagnostico", "dd"));
				str.append(" BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy') \n");
				str.append(" AND apdH.IsActive = 'Y' AND apdH.Estatus <> 'I' \n");
				str.append(" AND (sist.exme_signovital_id = ? AND sist.valor < 90) AND (diast.exme_signovital_id = ? AND diast.valor < 140) \n");
				str.append(" AND ").append(DB.truncDate("sist.fecha", "dd"));
				str.append(" BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy') \n");
				str.append(" AND ").append(DB.truncDate("diast.fecha", "dd"));
				str.append(" BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy') \n");
				str.append(" AND ep.exme_paciente_id not in ( \n");
				str.append("\t SELECT ap.exme_paciente_id  \n");
				str.append("\t FROM exme_actpaciente ap \n");
				str.append("\t INNER JOIN exme_actpacientediag apd on ap.exme_actpaciente_id = apd.exme_actpaciente_id \n");
				str.append("\t INNER JOIN exme_diagnosistypedet dtd on dtd.exme_diagnostico_id = apd.exme_diagnostico_id  \n");
				str.append("\t WHERE dtd.EXME_DiagnosisType_ID in (?, ?) AND dtd.IsActive = 'Y' \n");
				str.append("\t AND ").append(DB.truncDate("apd.fecha_diagnostico", "dd"));
				str.append("\t BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy') \n");
				str.append("\t AND apd.IsActive = 'Y' AND apd.Estatus <> 'I') ");

		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, ini, hypertensionDiagnosisType, ini, fin, diastolic, sistolic, ini, fin, ini, fin, this.pregnancyDiagnosisType, this.esrdDiagnosisType, ini, fin);
		
		return pacientes;
	}
	
	/**
	 * Denominador 
	 * 1 -Pacientes Edad>=17 y Edad<=84
	 * 2- Con diágnostico de Hypertension
	 * 3- No tenga diágnostico de Pregnancy	ni ESRD * 
	 */
	private int pacientesDenominador() {
		int pacientes=0;
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT COUNT(DISTINCT ep.exme_paciente_id) \n");
		sql.append(" FROM exme_paciente ep \n");
		sql.append(" inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id \n");
		sql.append(" WHERE cta.ad_client_id = ? AND cta.ad_org_id = ? AND cta.exme_tipopaciente_id = ? \n");
		sql.append(" AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) between 17 and 84 \n");
		sql.append(" AND ep.exme_paciente_id IN ( \n");
		sql.append("\t SELECT ap.exme_paciente_id \n");
		sql.append("\t FROM exme_actpacientediag diag \n");
		sql.append("\t inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id \n");
		sql.append("\t WHERE diag.exme_diagnostico_id IN( \n");
		sql.append("\t\t SELECT EXME_Diagnostico_ID \n");
		sql.append("\t\t FROM EXME_DiagnosisTypeDet \n");
		sql.append("\t\t WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y') \n");
		sql.append("\t AND ").append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		sql.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		sql.append("\t AND diag.IsActive = 'Y' AND diag.Estatus <> 'I') \n");
		sql.append(" AND ep.exme_paciente_id NOT IN( \n");
		sql.append("\t SELECT ap.exme_paciente_id \n");
		sql.append("\t FROM exme_actpacientediag diag \n");
		sql.append("\t inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id \n");
		sql.append("\t WHERE diag.exme_diagnostico_id IN( \n");
		sql.append("\t\t SELECT EXME_Diagnostico_ID \n");
		sql.append("\t\t FROM EXME_DiagnosisTypeDet \n");
		sql.append("\t\t WHERE EXME_DiagnosisType_ID in (?, ?) AND IsActive = 'Y') \n");
		sql.append("\t AND ").append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		sql.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		sql.append("\t AND diag.IsActive = 'Y' AND diag.Estatus <> 'I') ");

		pacientes = CQMeasures.executaSql(sql.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, ini, 
				hypertensionDiagnosisType, ini, fin, pregnancyDiagnosisType, esrdDiagnosisType, ini, fin);
			
		return pacientes;
	}
}