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
 * Genera xml para NQF 0052
 * Dx Low Back Pain with imaging study
 * 
 * @author Rosy Velazquez
 */


public class GeneraArchivoNQF52 {
	private Properties ctx;
	private String trxName;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	
	private String ini = "";
	private String fin = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER = "NQF-0052"; 	//NQF-52 no pari number	
	
	private String archivo = "NQF_0052";
	private String measureGroup = "X"; //Sin grupo
	
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	private int numFiles = 1;
	private int numFile = 1;
	
	private int lowBackPainDiagnosisType = 0;
	private int studySpinal = 0;
	private int outpatient = 0;
	
	public GeneraArchivoNQF52(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
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
		fechaIni = param.getFecha_Ini_NQF52();
		fechaFin = param.getFecha_Fin_NQF52();	
				
		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);
		
		lowBackPainDiagnosisType = param.getLowBackPain();
		studySpinal = param.getStudySpinal();
		
		outpatient = param.getOutpatient();
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER , this.pacientesMedida(), this.pacientesDenominador()));						
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}	
	
	/**
	 * Numerador 
	 * 1. Edad >=18 y <=49
	 * 2. Diagnostico activo = low back pain
	 * 3. No se le haya hecho un studySpinal en 28 dias despues del diagnostico
	 * 4. Paciente de tipo outpatient
	 * 
	 * **/

	protected int pacientesMedida(){
		int pacientes=0;		
				
		StringBuilder sql = new StringBuilder(" SELECT count (DISTINCT cp.EXME_Paciente_ID) FROM exme_ctapac cp ");
		sql.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		//1.
		.append(" where cp.ad_client_id = ? " +
				" and cp.ad_org_id = ? " +
				" and cp.exme_tipopaciente_id = ? " +
				" AND trunc(trunc((((86400*( "+DB.truncInterval("to_date(?, 'dd/MM/yyyy') - ep.fechanac")+"))/60)/60)/24)/365) between 18 and 49 " +
				" AND ep.exme_paciente_id in" +
				"( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" and diag.exme_diagnostico_id in (")
			.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append("  AND diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> 'I' " +
					" AND ap.exme_paciente_id NOT IN " +										 
					  " (" +
					  " select distinct ap.exme_paciente_id " +       
				      " from exme_actpacienteind api inner join exme_actpacienteindh ph " + 
				                                              " inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id " +
				                                   " on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id " +
				      " where api.m_product_id in (select m_product_id from M_Product where exme_intervencion_id in (select exme_intervencion_id from exme_procedureTypeDet where exme_procedureType_id = ?) ) " +
				            " and ph.dateordered between diag.fecha_diagnostico and (diag.fecha_diagnostico  + INTERVAL '28' DAY) " +
				      " ) " +
					
				" )" ); 
		
		pacientes = CQMeasures.executaSql(sql.toString(), Env.getAD_Client_ID(ctx),Env.getAD_Org_ID(ctx), outpatient, this.ini, this.lowBackPainDiagnosisType, ini, fin,  studySpinal);		
		
		return pacientes;
	}
	
	/**
	 * Denominador 
	 * 1. Edad >=18 y <=49
	 * 2. Diagnostico activo = low back pain
	 * 3. Paciente de tipo outpatient
	 */

	protected int pacientesDenominador(){
		int pacientes=0;				
		
		StringBuilder sql = new StringBuilder(" SELECT count (DISTINCT cp.EXME_Paciente_ID) FROM exme_ctapac cp ");
		sql.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		//1.
		.append(" where cp.ad_client_id = ? " +
				" and cp.ad_org_id = ? " +
				" and cp.exme_tipopaciente_id = ? " +
				" AND trunc(trunc((((86400*( "+DB.truncInterval("to_date(?, 'dd/MM/yyyy') - ep.fechanac")+"))/60)/60)/24)/365) between 18 and 49 " +
				" AND ep.exme_paciente_id in" +
				"( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" and diag.exme_diagnostico_id in (")
			.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
			.append("  AND diag.fecha_diagnostico between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> 'I' " +										
				" )" ); 
		
		pacientes = CQMeasures.executaSql(sql.toString(), Env.getAD_Client_ID(ctx),Env.getAD_Org_ID(ctx), outpatient, this.ini, this.lowBackPainDiagnosisType, ini, fin);
				
		return pacientes;
	}
}
