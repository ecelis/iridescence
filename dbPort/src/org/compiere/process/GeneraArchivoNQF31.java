package org.compiere.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEParamMetricas;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0031
 * Mammogram to screen to breast cancer
 * @author Rosy Velazquez
 */
public class GeneraArchivoNQF31 {
	private Properties ctx;
	private String trxName;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	
	private String ini = "";
	private String fin = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER = "112"; 	//NQF-31	
	
	private String archivo = "NQF_0031";
	private String measureGroup = "X"; //Sin grupo
	
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	private int numFiles = 1;
	private int numFile = 1;
	private int outpatient = 0;
	private int breastCancer;
	
	public GeneraArchivoNQF31(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
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
		fechaIni = param.getFecha_Ini_NQF31();
		fechaFin = param.getFecha_Fin_NQF31();	
				
		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);
		
		outpatient = param.getOutpatient();
		breastCancer = param.getM_Product_BreastCancer_ID();
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER , this.pacientesMedida(), this.pacientesDenominador()));						
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}	
	
	/**
	 * Numerador 
	 * 1. Mujeres
	 * 2. Edad >=41 y <=68
	 * 3. date encounter entre ini-2 anios y fechafin 
	 * 4. Mammogram to screen for breast cancer entre ini-2 anios y fechafin
	 */
	private int pacientesMedida() {
		int pacientes = 0;
		Date fechanegativa = CQMeasures.addMonth(fechaIni, -24);

		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT COUNT(DISTINCT ep.exme_paciente_id) \n");
		sql.append(" FROM exme_paciente ep \n");
		sql.append(" inner join exme_actpaciente ap ON ap.exme_paciente_id = ep.exme_paciente_id \n");
		sql.append(" inner join exme_actpacienteindh ph on ap.exme_actpaciente_id = ph.exme_actpaciente_id \n");
		sql.append(" inner join exme_actpacienteind api on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id \n");
		sql.append(" inner join M_Product mp on api.m_product_id = mp.m_product_id and mp.exme_intervencion_id is not null \n");
		sql.append(" inner join exme_procedureTypeDet ptd on ptd.exme_intervencion_id = mp.exme_intervencion_id \n");
		sql.append(" inner join exme_ctapac cta on cta.exme_paciente_id = ap.exme_paciente_id \n");
		sql.append(" WHERE cta.ad_client_id = ? AND cta.ad_org_id = ? AND cta.exme_tipopaciente_id = ? \n");
		sql.append(" AND EP.SEXO = ? AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) between 41 and 68 \n");
		sql.append(" AND ").append(DB.truncDate("ph.dateordered", "dd"));
		sql.append(" BETWEEN to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
		sql.append(" AND exme_procedureType_id = ? \n");
		sql.append(" AND ").append(DB.truncDate("cta.dateordered", "dd"));
		sql.append(" BETWEEN to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
		
		pacientes = CQMeasures.executaSql(sql.toString(),Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, MEXMEPaciente.SEXO_Female, ini, 
				f.format(fechanegativa), fin, breastCancer, f.format(fechanegativa), fin);
			
		return pacientes;
	}
	
	/**
	 * Denominador 
	 * 1. Mujeres
	 * 2. Edad >=41 y <=68
	 * 3. date encounter entre ini-2 anios y periodo medida 
	 */
	private int pacientesDenominador() {
		int pacientes = 0;

		Date fechanegativa = CQMeasures.addMonth(fechaIni, -24);
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT COUNT(DISTINCT ep.exme_paciente_id) \n");
		sql.append(" FROM exme_paciente ep \n");
		sql.append(" inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id \n");
		sql.append(" WHERE cta.ad_client_id = ? AND cta.ad_org_id = ? AND cta.exme_tipopaciente_id = ? \n");
		sql.append(" AND EP.SEXO = ? \n");
		sql.append(" AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) between 41 and 68 \n");
		sql.append(" AND ").append(DB.truncDate("cta.dateordered", "dd"));
		sql.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
		
		pacientes = CQMeasures.executaSql(sql.toString(),Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, MEXMEPaciente.SEXO_Female, ini,  f.format(fechanegativa), fin);		
		
		return pacientes;
	}
}