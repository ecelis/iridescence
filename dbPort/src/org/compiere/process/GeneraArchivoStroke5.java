package org.compiere.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MActPacienteDiag;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEParamMetricas;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0438
 * Stroke-5
 * @author Rosy Velazquez
 */

public class GeneraArchivoStroke5{
	
	public GeneraArchivoStroke5(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
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
	
	private int terapiaAntitromboticId = 0;
	private int diagnosisTypeIschemic_ID = 0;
	private int terapiaTrombolitic = 0;
	
	private final static String PQRI_NUMBER = "0438"; 		
	
	private String archivo = "NQF_0438";
	private String measureGroup = "X"; //Sin grupo	
	
	private int numFiles = 1;
	private int numFile = 1;	
	
	private int inpatient = 0;
		
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	public File genera() throws Exception {		
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); //ya esta validado!
				
		//Llenar parametros de la tabla
		fechaIni = param.getFecha_Ini_Stroke5();
		fechaFin = param.getFecha_Fin_Stroke5();		
					
		terapiaAntitromboticId = param.getEXME_TerapiaAntitrombotic_ID();
		diagnosisTypeIschemic_ID = param.getIschemic_Stroke();
		terapiaTrombolitic = param.getEXME_TerapiaThrombolitic_ID(); 
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
	* Numerador
	 * 
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> con un diagnostico de derrame cerebral (ischemic stroke)
	 *					secuencia = 1 
	 *			  		diagnostico codificado
	 *<p> -2. <i>(B)</i> que tengan un medicamento de terapia antitrombolitic al dia 2 de hospitalizacion.
	 *<p> -3. <i>(B)</i> pacientes dados de alta 
	 *<p> -4. <i>(B)</i> edad > 18
	 *<p> -5. <i>(B)</i> no tenga estatus de Comfort Measure Only
	 *<p> -6. <i>(B)</i> no tenga terapia Thrombolitic administrada  
	 * 
	 */
	protected int pacientesMedida(){
		int pacientes=0;		
		
		StringBuilder str = new StringBuilder(" SELECT count (DISTINCT cp.exme_paciente_id) FROM exme_ctapac cp ");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		//1.
		.append(" where cp.ad_client_id = ? " +
				" and cp.ad_org_id = ? " +
				" and cp.exme_tipopaciente_id = ? " +
				" AND ep.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id " +
					"Inner Join EXME_Diagnostico dm on (dm.Exme_Diagnostico_ID= diag.EXME_Diagnostico_id)")
			.append(" where diag.sequencediag = 1 ") //Dignostico Principal
				.append(" and diag.type = 'CF' ") 	 //Diagnosticos codificados
				.append(" and dm.value in (")
				.append(" SELECT dtd.code FROM EXME_DiagnosisTypeDet as dtd " +
						"inner join exme_diagnosisVersion dv on (dv.exme_diagnosisVersion_id = dtd.exme_diagnosisVersion_ID AND dv.stage = '1')" +
						"WHERE dv.EXME_DiagnosisType_ID = ? AND dv.IsActive = 'Y' AND dtd.IsActive = 'Y' ) ")
			.append("  AND ");
		str.append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ? )" ) 
		//2.	
		.append(" AND cp.exme_ctapac_id in ")
		.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
		.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
		.append(" where pm.m_product_id in (select m_product_id from m_product where exme_genproduct_id in  ")
			.append(" (SELECT EXME_GENPRODUCT_ID FROM EXME_TERAPIA_PRODUCTO " +
					"INNER JOIN EXME_TherapiesVersion tv ON (tv.EXME_TherapiesVersion_ID = " +
					"EXME_TERAPIA_PRODUCTO.EXME_TherapiesVersion_ID)" +
					"WHERE tv.EXME_TERAPIA_ID = ? and tv.stage ='1'  ) ) ")
		.append(" and ");
		str.append(DB.truncDate("pm.startDate", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
//		.append(" and (pml.apliedDate - cp.dateOrdered) >= 2 )")
		.append("and extract('days' from (pml.apliedDate - cp.dateOrdered)) >= 2 )")
		
		//3.
		.append(" AND cp.EncounterStatus not in (?, ?, ?) ")
		//4.
		.append(" AND edadmuse( ep.fechanac, cp.dateordered) >= 18")		
		//5.
		.append(" AND (cp.resstatus != ? or cp.resstatus is null )")
		//6.
		.append(" AND cp.exme_ctapac_id not in ")
		.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
		.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
		.append(" where pm.m_product_id in (select m_product_id from m_product where exme_genproduct_id in ")
			.append(" (SELECT EXME_GENPRODUCT_ID FROM EXME_TERAPIA_PRODUCTO " +
					"INNER JOIN EXME_TherapiesVersion tv on (tv.EXME_TherapiesVersion_Id =" +
					"EXME_TERAPIA_PRODUCTO.EXME_THERAPIESVERSION_ID)" +
					"WHERE tv.EXME_TERAPIA_ID = ?  and TV.Stage='1') ) ")
		.append(" and pm.startDate between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
		.append(" and (pml.apliedDate > cp.dateOrdered OR pml.apliedDate between (cp.dateordered-2) and cp.dateordered) )");
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), inpatient, this.diagnosisTypeIschemic_ID, this.ini, this.fin, MActPacienteDiag.ESTATUS_Inactive, this.terapiaAntitromboticId, this.ini, this.fin, MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission, MEXMECtaPac.ENCOUNTERSTATUS_Admission, MEXMECtaPac.ENCOUNTERSTATUS_Predischarge, MEXMECtaPac.RESSTATUS_ComfortCareOnly, this.terapiaTrombolitic, this.ini, this.fin);
		
		return pacientes;
	}
	
	/**
	 * Calcula los pacientes del denominador
	 * Ischemic Stroke 
	 * Mismas consideraciones sin terapia Antihrombolitic
	 * @return pacientes
	 */
	protected int pacientesDenominador(){
		int pacientes = 0;
		
		StringBuilder str = new StringBuilder(" SELECT count (DISTINCT cp.exme_paciente_id) FROM exme_ctapac cp ");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		//1.
		.append(" where cp.ad_client_id = ? " +
				" and cp.ad_org_id = ? " +
				" and cp.exme_tipopaciente_id = ? " +
				" AND ep.exme_paciente_id in( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id " +
					"Inner Join EXME_Diagnostico as dm on (dm.Exme_Diagnostico_ID= diag.EXME_Diagnostico_id)")
			.append(" where diag.sequencediag = 1 ") //Dignostico Principal
				.append(" and diag.type = 'CF' ") 	 //Diagnosticos codificados
				.append(" and dm.value in ( ")
				.append(" SELECT dtd.code FROM EXME_DiagnosisTypeDet as dtd " +
						"inner join exme_diagnosisVersion dv on (dv.exme_diagnosisVersion_id = dtd.exme_diagnosisVersion_ID AND dv.stage = '1')" +
						" WHERE dv.EXME_DiagnosisType_ID = ? AND dv.IsActive = 'Y' AND dtd.IsActive='Y' ) ")
			.append("  AND ");
		str.append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ? )" ) 
		//2.			
		
		//3.
		.append(" AND cp.EncounterStatus not in (?, ?, ?) ")
		//4.
		.append(" AND edadmuse( ep.fechanac, cp.dateordered) >= 18")		
		//5.
		.append(" AND (cp.resstatus != ? or cp.resstatus is null) ")
		//6.
		.append(" AND cp.exme_ctapac_id not in ")
		.append(" (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm ")
		.append(" inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
		.append(" where pm.m_product_id in (select m_product_id from m_product where exme_genproduct_id in ")
			.append(" (SELECT EXME_GENPRODUCT_ID FROM EXME_TERAPIA_PRODUCTO " +
					"INNER JOIN EXME_THERAPIESVERSION TV ON (TV.EXME_THERAPIESVERSION_ID = " +
					" EXME_TERAPIA_PRODUCTO.EXME_THERAPIESVERSION_ID) " +
					" WHERE TV.EXME_TERAPIA_ID = ? and TV.stage='1')) ")
		.append(" and ");
		str.append(DB.truncDate("pm.startDate", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
		.append(" and (pml.apliedDate > cp.dateOrdered OR pml.apliedDate between (cp.dateordered-2) and cp.dateordered) )");
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), inpatient, this.diagnosisTypeIschemic_ID, this.ini, this.fin, MActPacienteDiag.ESTATUS_Inactive, MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission, MEXMECtaPac.ENCOUNTERSTATUS_Admission, MEXMECtaPac.ENCOUNTERSTATUS_Predischarge, MEXMECtaPac.RESSTATUS_ComfortCareOnly, this.terapiaTrombolitic, this.ini, this.fin);
		
		return pacientes;
	}
	public static int testPacientesMedida(final boolean numerador) {
		final GeneraArchivoStroke5 bean = new GeneraArchivoStroke5(null, null, Env.getCtx(), null, 0, 0);
		int id;
		if (numerador) {
			id = bean.pacientesMedida();
		} else {
			id = bean.pacientesDenominador();
		}
		return id;
	}
}
