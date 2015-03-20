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
 * Genera xml para NQF 0013
 * Hypertension: Blood Pressure Measurement
 * @author Rosy Velazquez
 */

public class GeneraArchivoNQF13{
	
	public GeneraArchivoNQF13(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
		this.numFiles = files;
	}

	private int age = 18; //age from			
	private Properties ctx;
	private String trxName;
	
	//Signos vitales de presion arterial
	private int diastolic_id = 0;
	private int systolic_id = 0;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	private String iniStr = "";
	private String finStr = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER_HYPERTENSION = "NQF-0013"; //sin pqri num ---NQF 0013		
	
	private String archivo = "NQF_0013";
	private String measureGroup = "X"; //Sin grupo
	
	private int numFiles = 1;
	private int numFile = 1;
	
	private int hypertension = 0;
	private int outpatient = 0;
		
	SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	public File genera() throws Exception {		
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName);
				
		//Llenar parametros de la tabla
		systolic_id = param.getEXME_SignoVitalSystolic_ID();
		diastolic_id = param.getEXME_SignoVitalDiastolic_ID();
		
		hypertension = param.getHypertension(); 
		outpatient = param.getOutpatient();
		
		this.fechaIni = param.getFecha_Ini_Hypertension();
		this.fechaFin = param.getFecha_Fin_Hypertension();
		
		//Para querie
		iniStr = f.format(fechaIni);
		finStr = f.format(fechaFin);
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER_HYPERTENSION, this.executaQueryNumerator(), this.executaQueryDenominator()));				
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);		
				
		return generado;
	}	
	
	/**
	 * Numerador
	 *<p> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Edad >= 18 
	 *     Años calculados a la fecha inicial de la medida
	 *<p> -2. <i>(B)</i> Diagnosis Active = Hypertension
	 *<p> -3. <i>(B)</i> 2 o mas Visitas al hospital
	 *<p> -4. <i>(B)</i> Systolic Medida
	 *<p> -5. <i>(B)</i> Y Diastolic medida  
	 * @return pacientes
	 */
	private int executaQueryNumerator(){
		int pacientes=0;				
		StringBuilder str = new StringBuilder(" SELECT COUNT(DISTINCT EP.EXME_Paciente_id) \n");
		str.append(" FROM EXME_Paciente EP \n");
		str.append(" INNER JOIN EXME_CtaPac CTA ON CTA.EXME_Paciente_ID = EP.EXME_Paciente_ID \n");
		str.append(" INNER JOIN EXME_ActPaciente AP ON AP.EXME_CtaPac_ID = CTA.EXME_CtaPac_ID \n");
		str.append(" INNER JOIN EXME_ActPacienteDiag APD ON AP.EXME_ActPaciente_ID = APD.EXME_ActPaciente_ID \n");
		str.append(" INNER JOIN EXME_DiagnosisTypeDet DTD ON DTD.EXME_Diagnostico_ID = APD.EXME_Diagnostico_ID \n");
		str.append(" INNER JOIN EXME_SignoVitalDet SVD ON SVD.EXME_Paciente_ID = AP.EXME_Paciente_ID \n");
		str.append(" WHERE CTA.AD_Client_ID = ? AND CTA.AD_Org_ID = ? AND CTA.EXME_TipoPaciente_ID = ? \n");
		str.append(" AND edadmuse(EP.FechaNac, TO_DATE(?, 'dd/MM/yyyy')::date) >= ? \n");
		str.append(" AND DTD.EXME_DiagnosisType_ID = ? AND DTD.IsActive = 'Y' \n");
		str.append(" AND ").append(DB.truncDate("APD.fecha_diagnostico", "dd"));
		str.append(" BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy') \n");
		str.append(" AND APD.IsActive = 'Y' AND APD.Estatus <> ? \n");
		str.append(" AND SVD.EXME_SignoVital_ID IN (?, ?) \n");
		str.append(" AND ").append(DB.truncDate("svd.fecha", "dd"));
		str.append(" BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy') ");
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), this.outpatient, this.iniStr, this.age, this.hypertension, this.iniStr, this.finStr, MActPacienteDiag.ESTATUS_Inactive, this.systolic_id, this.diastolic_id, this.iniStr, this.finStr);				
		return pacientes;
	}
	
	/**
	 * Calcula el denominador para la medida 
	 * Numerador sin los signos vitales
	 * @return
	 */
	private int executaQueryDenominator() {
		int pacientes = 0;
		StringBuilder str = new StringBuilder();
		str.append(" SELECT COUNT(DISTINCT EP.EXME_PACIENTE_ID) \n");
		str.append(" FROM EXME_PACIENTE EP \n");
		str.append(" INNER JOIN EXME_CTAPAC CTA ON CTA.exme_paciente_id = EP.exme_paciente_id \n");
		str.append(" INNER JOIN EXME_ACTPACIENTE AP ON AP.EXME_PACIENTE_ID = EP.EXME_PACIENTE_ID \n");
		str.append(" INNER JOIN EXME_ACTPACIENTEDIAG APD ON APD.EXME_ACTPACIENTE_ID = AP.EXME_ACTPACIENTE_ID \n");
		str.append(" INNER JOIN EXME_DIAGNOSISTYPEDET DTD ON DTD.EXME_DIAGNOSTICO_ID = APD.EXME_DIAGNOSTICO_ID \n");
		str.append(" WHERE CTA.AD_Client_id = ? AND CTA.AD_Org_id = ? AND CTA.exme_tipopaciente_id = ? \n");
		str.append(" AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >= ? \n");
		str.append(" AND DTD.EXME_DiagnosisType_ID = ? AND DTD.IsActive = 'Y' \n");
		str.append(" AND ").append(DB.truncDate("APD.fecha_diagnostico", "dd"));
		str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" AND APD.IsActive = 'Y' AND APD.Estatus <> ?");
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), this.outpatient, this.iniStr, this.age, this.hypertension, this.iniStr, this.finStr, MActPacienteDiag.ESTATUS_Inactive);			
		return pacientes;
	}
}
