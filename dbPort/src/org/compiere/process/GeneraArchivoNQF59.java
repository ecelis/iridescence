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
 * Genera xml para NQF 0059
 * Dx: Diabetes with HbA1c
 * @author Rosy Velazquez
 */

public class GeneraArchivoNQF59 {
	private Properties ctx;
	private String trxName;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	
	private String ini = "";
	private String fin = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER = "1"; 	//NQF-41	
	
	private String archivo = "NQF_0059";
	private String measureGroup = "X"; //Sin grupo
	
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	private int numFiles = 1;
	private int numFile = 1;	
	private int outpatient = 0;
	private int hba1c = 0;
	private int diabetes = 0;
	private int medDiabetes = 0;
	
	public GeneraArchivoNQF59(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
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
		fechaIni = param.getFecha_Ini_NQF59();
		fechaFin = param.getFecha_Fin_NQF59();	
				
		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);
		outpatient = param.getOutpatient();
		hba1c = param.getHBA1C();
		diabetes = param.getDiabetes();
		medDiabetes = param.getDiabetesMedication();
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER , this.pacientesMedida(), this.pacientesDenominador()));						
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}	
	
	/**
	 * Numerador 
	 * 1. Edad >=17 y <=74
	 * 2. Medicacion de Diabetes
	 * 	  OR Diagnostic active = Diabetes
	 * 3. Laboratory test result: HbA1c test > 9.0 % 
	 * 4. Paciente de tipo outpatient
	 */
	private int pacientesMedida(){
		int pacientes=0;		
		
		StringBuilder str = new StringBuilder();
		str.append(" SELECT COUNT(DISTINCT PAC.EXME_PACIENTE_ID) \n");
		str.append(" FROM EXME_Paciente pac \n");
		str.append(" INNER JOIN EXME_CtaPac cta ON cta.exme_paciente_id = pac.exme_paciente_id \n");
		str.append(" INNER JOIN EXME_ActPaciente ap ON ap.exme_paciente_id = pac.exme_paciente_id \n");
		str.append(" INNER JOIN exme_actpacienteindh indh ON ap.exme_actpaciente_id = indh.exme_actpaciente_id \n");
		str.append(" INNER JOIN exme_actpacienteind ind ON indh.exme_actpacienteindh_id = ind.exme_actpacienteindh_id \n");
		str.append(" INNER JOIN EXME_ESTUDIOSOBX obx ON ind.exme_actpacienteind_id = obx.exme_actpacienteind_id \n");
		str.append(" INNER JOIN EXME_LOINCTYPEDET LTD ON obx.codeId = ltd.exme_loinc_id \n");
		str.append(" LEFT JOIN exme_actpacientediag diag ON ap.exme_actpaciente_id = diag.exme_actpaciente_id \n");
		str.append(" LEFT JOIN EXME_DiagnosisTypeDet dtd ON dtd.exme_diagnostico_id = diag.exme_diagnostico_id \n");
		str.append(" LEFT JOIN EXME_TERAPIA_PRODUCTO TP ON tp.exme_genproduct_id = ind.exme_genproduct_id \n");
		str.append(" WHERE CTA.AD_CLIENT_ID = ? AND CTA.AD_ORG_ID = ? AND CTA.EXME_TIPOPACIENTE_ID = ? \n");
		str.append(" AND edadmuse(pac.fechanac, to_date(?, 'dd/MM/yyyy')::date) between 17 AND 74 \n");
		str.append(" AND EXME_LOINCTYPE_ID =? \n");
		str.append(" AND OBX.UOMVALUE = '%' AND TO_NUMBER(OBX.OBSERVATION) >= 9 \n");
		str.append(" AND ").append(DB.truncDate("OBX.OBSERVATIONDATE", "dd"));
		str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
		str.append(" AND ((dtd.EXME_DiagnosisType_ID = ? AND dtd.IsActive = 'Y' \n");
		str.append(" AND ").append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
		str.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> 'I')  \n");
		str.append(" OR \n");
		str.append(" (TP.EXME_TERAPIA_ID = ? \n");
		str.append(" AND ").append(DB.truncDate("indh.dateordered", "dd"));
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy'))) \n");

		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, ini, hba1c, ini, fin, diabetes, ini, fin, medDiabetes, ini, fin);
		
		return pacientes;
	}
	
	/**
	 * Denominador 
	 * 1. Edad >=17 y <=74
	 * 2. Medicacion de Diabetes
	 * 	  OR Diagnostic active = Diabetes
	 * 3. Paciente de tipo outpatient
	 */
	private int pacientesDenominador(){
		int pacientes=0;				
		
		StringBuilder str = new StringBuilder("");
		str.append(" SELECT COUNT(DISTINCT PAC.EXME_PACIENTE_ID) \n");
		str.append(" FROM EXME_Paciente pac \n");
		str.append(" INNER JOIN EXME_CtaPac cta ON cta.exme_paciente_id = pac.exme_paciente_id \n");
		str.append(" WHERE CTA.AD_CLIENT_ID = ? AND CTA.AD_ORG_ID = ? AND CTA.EXME_TIPOPACIENTE_ID = ? \n");
		str.append(" AND edadmuse( pac.fechanac, to_date(?, 'dd/MM/yyyy')::date) between 17 AND 74 \n");
		str.append(" AND pac.exme_paciente_id IN ( \n");
		str.append("\t (SELECT ap.exme_paciente_id as paciente_id \n");
		str.append("\t FROM exme_actpacientediag diag \n");
		str.append("\t inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id \n");
		str.append("\t WHERE diag.exme_diagnostico_id IN ( \n");
		str.append("\t\t SELECT EXME_Diagnostico_ID \n");
		str.append("\t\t FROM EXME_DiagnosisTypeDet \n");
		str.append("\t\t WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y') \n");
		str.append("\t AND ").append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
		str.append("\t AND diag.IsActive = 'Y' AND diag.Estatus <> 'I') \n");
		str.append(" UNION ( \n");
		str.append("\t Select distinct ap.exme_paciente_id as paciente_id \n");
		str.append("\t from exme_actpacienteind api \n");
		str.append("\t inner join exme_actpacienteindh ph on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id \n");
		str.append("\t inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id \n");
		str.append("\t where api.exme_genproduct_id in ( \n");
		str.append("\t\t SELECT EXME_GENPRODUCT_ID \n");
		str.append("\t\t FROM EXME_TERAPIA_PRODUCTO \n");
		str.append("\t\t WHERE EXME_TERAPIA_ID = ? ) \n");
		str.append("\t\t AND ").append(DB.truncDate("ph.dateordered", "dd"));
		str.append("\t\t between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy'))) \n");

		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, ini, diabetes, ini, fin, medDiabetes, ini, fin);
		return pacientes;
	}
}