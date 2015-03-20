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
 * Genera xml para NQF 0437
 * Stroke-4
 * @author Rosy Velazquez
 */

public class GeneraArchivoStroke4{
	
	public GeneraArchivoStroke4(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
		this.numFiles = files;
	}
			
	private Properties ctx;
	private String trxName;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	
	private String ini = "";
	private String fin = "";
	
	private String optionRegistry="";
	private String version = "";	
	
	private int terapiaThrombolyticId = 0;
	private int diagnosisTypeIschemic_ID = 0;
	
	private final static String PQRI_NUMBER = "0437"; 		
	
	private String archivo = "NQF_0437";
	private String measureGroup = "X"; //Sin grupo
	
	private int numFiles = 1;
	private int numFile = 1;
	private int inpatient = 0;
		
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	public File genera() throws Exception {		
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); //ya esta validado!
				
		//Llenar parametros de la tabla
		fechaIni = param.getFecha_Ini_Stroke4();
		fechaFin = param.getFecha_Fin_Stroke4();		

		terapiaThrombolyticId = param.getEXME_TerapiaThrombolitic_ID();
		diagnosisTypeIschemic_ID = param.getIschemic_Stroke();
		inpatient = param.getEXME_EstServ_Hosp_ID();
		
		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER, this.pacientesMedida(), this.pacientesDenominador()));				
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);		
				
		return generado;
	}
	
	/**
	  Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico principal de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte.
	 *					 que sea un diagnostico codificado 	 
	 *<p> -2. <i>(B)</i> que llegan al hospital 2 horas de haberse sentido mal (arrival_date - fecha_sintoma)
	 *<p> -3. <i>(B)</i> tuvieron un medicamento de antitrombosis en las 3 horas despues de haberse sentido mal. (<=180 minutos) (api.dateordered - pd.fechasintoma)
	 *<p> -4. <i>(B)</i> edad mayor a 18 anios
	 */
	//TODO Excluir Patients admitted for Elective Carotid Intervention
	protected int pacientesMedida(){
		int pacientes=0;		
		
		StringBuilder str = new StringBuilder("Select count(DISTINCT cp.exme_ctapac_id) " +
						" from exme_ctapac cp inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id  ")
						.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
						.append(" where cp.ad_client_id = ? " +
							    " and cp.ad_org_id = ?" +
							    " and cp.exme_tipopaciente_id = ? " +
					//1.
						" AND ep.exme_paciente_id in( ")
						.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id " +
								"Inner Join EXME_Diagnostico dm on (dm.Exme_Diagnostico_ID= diag.EXME_Diagnostico_id)")
						.append(" where diag.sequencediag = 1 ") //Dignostico Principal
							.append(" and diag.type = 'CF' ") 	 //Diagnosticos codificados
							.append(" and dm.value in (")
							.append(" SELECT dtd.code FROM EXME_DiagnosisTypeDet as dtd " +
									"inner join exme_diagnosisVersion dv on (dv.exme_diagnosisVersion_id = dtd.exme_diagnosisVersion_ID AND dv.stage = '1')" +
									"WHERE dv.EXME_DiagnosisType_ID = ? AND dv.IsActive = 'Y' and dtd.IsActive='Y' ) ")
						.append("  AND ");
		str.append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
						.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ? )") 
					//2.
						      .append(" and pd.fechasintoma is not null " +
//							  " and ((pd.arrivaldate - pd.fechasintoma)*24) <= 2 " +
						    " and extract('days' from (pd.arrivaldate - pd.fechasintoma)) * 24 + extract('hours' from (pd.arrivaldate - pd.fechasintoma)) <= 2  " +
					//3.	     					      
						      " and cp.exme_paciente_id in " + 
						      " (select distinct ap.exme_paciente_id " +       
						      " from exme_actpacienteind api inner join exme_actpacienteindh ph " + 
						                                              " inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id " +
						                                   " on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id " +
						      " where api.exme_genproduct_id in (SELECT EXME_GENPRODUCT_ID FROM EXME_TERAPIA_PRODUCTO " +
						      "INNER JOIN EXME_THERAPIESVERSION TV ON (TV.EXME_THERAPIESVERSION_ID = EXME_TERAPIA_PRODUCTO.EXME_THERAPIESVERSION_ID)" +
						      "WHERE TV.EXME_TERAPIA_ID = ? AND tv.stage='1') " +
						            " and ");
		str.append(DB.truncDate("ph.dateordered", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') " +
//						            " and (ph.dateordered - pd.fechasintoma)*24 <= 3 )"
						            " and extract('days' from (pd.arrivaldate - pd.fechasintoma)) * 24 + extract('hours' from (pd.arrivaldate - pd.fechasintoma)) <= 3)  ")
		            //4.
						  .append(" AND edadmuse( ep.fechanac, cp.dateordered) >= 18");          						 
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), inpatient, this.diagnosisTypeIschemic_ID, this.ini, this.fin, MActPacienteDiag.ESTATUS_Inactive, this.terapiaThrombolyticId, this.ini, this.fin);
		return pacientes;
	}
	
	/**
	 * Calcula los pacientes del denominador
	 * Ischemic stroke patients que llegaron 2 horas desde que se sintieron mal
	 * Igual al numerador sin la aplicacion de la terapia
	 * @return pacientes
	 */
	protected int pacientesDenominador(){
		int pacientes = 0;
		
		StringBuilder str = new StringBuilder("Select count(DISTINCT cp.exme_ctapac_id) " +
		" from exme_ctapac cp inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id  ")
		.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" where cp.ad_client_id = ? " +
			  " and cp.ad_org_id = ? " +
			  " and cp.exme_tipopaciente_id = ? " +
	//1.
		" AND ep.exme_paciente_id in( ")
		.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id " +
				"Inner Join EXME_Diagnostico dm on (dm.Exme_Diagnostico_ID= diag.EXME_Diagnostico_id) ")
		.append(" where diag.sequencediag = 1 ") //Dignostico Principal
			.append(" and diag.type = 'CF' ") 	 //Diagnosticos codificados
			//X_EXME_ActPacienteDiag.TYPE_CoderFinal
			.append(" and dm.value in (")
			.append(" SELECT dtd.code FROM EXME_DiagnosisTypeDet as dtd " +
					"inner join exme_diagnosisVersion dv on (dv.exme_diagnosisVersion_id = dtd.exme_diagnosisVersion_ID AND dv.stage = '1')" +
					"WHERE dv.EXME_DiagnosisType_ID = ? AND dv.IsActive = 'Y' and dtd.IsActive= 'Y' ) ")
		.append("  AND ");
		str.append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
		.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ? )") 
	//2.
		      .append(" and pd.fechasintoma is not null " +
//			  " and ((pd.arrivaldate - pd.fechasintoma)*24) <= 2 ") 
		      " and extract('days' from (pd.arrivaldate - pd.fechasintoma)) * 24 + extract('hours' from (pd.arrivaldate - pd.fechasintoma)) <= 2  ")
	//3.	     					      
		      
    //4.
		  .append(" AND edadmuse( ep.fechanac, cp.dateordered) >= 18");	  		 

		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), inpatient, this.diagnosisTypeIschemic_ID, this.ini, this.fin, MActPacienteDiag.ESTATUS_Inactive);
		return pacientes;
	}
	public static int testPacientesMedida(final boolean numerador) {
		final GeneraArchivoStroke4 bean = new GeneraArchivoStroke4(null, null, Env.getCtx(), null, 0, 0);
		int id;
		if (numerador) {
			id = bean.pacientesMedida();
		} else {
			id = bean.pacientesDenominador();
		}
		return id;
	}
}
