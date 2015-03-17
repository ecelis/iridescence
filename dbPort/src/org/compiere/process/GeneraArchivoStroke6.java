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
 * Genera xml para NQF 0439
 * Stroke-6
 * @author Rosy Velazquez
 */

public class GeneraArchivoStroke6{
	
	public GeneraArchivoStroke6(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
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
	
	private int diagnosisTypeIschemic_ID = 0;
	private int diagnosisTypeArteriosclerosis = 0;
	private int terapiaEstatinaId = 0; //Estatin medication
	private int terapiaLipidos = 0;    // Lipid lowering medication
	
	//Para LDL
	private int loincLDL = 0;
	private int measureLDL = 0;
	//private int procedureLDL = 0;
	private int inpatient = 0;
	
	private final static String PQRI_NUMBER = "0439"; 		
	
	private String archivo = "NQF_0439";
	private String measureGroup = "X"; //Sin grupo
	
	private int numFiles = 1;
	private int numFile = 1;	
		
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	public File genera() throws Exception {		
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); //ya esta validado!
				
		//Llenar parametros de la tabla
		fechaIni = param.getFecha_Ini_Stroke6();
		fechaFin = param.getFecha_Fin_Stroke6();						
		
		diagnosisTypeIschemic_ID = param.getIschemic_Stroke();
		terapiaEstatinaId = param.getEXME_TerapiaStatin_ID();
		terapiaLipidos = param.getLipid_Medication();
		diagnosisTypeArteriosclerosis = param.getAteriosclerosis_Diag();
		inpatient = param.getEXME_EstServ_Hosp_ID();
		
		this.loincLDL = param.getLipid_Loinc();
		this.measureLDL = param.getLDL_Measure();
		//this.procedureLDL = param.getLipid_Procedure();		
		
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
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) activo en las fechas definidas por el reporte.
	 *					 secuencia = 1 		
	 *<p> -2. <i>(A)</i> Y que tengan terapia (Statin) al ser dados de alta. (discharge medications)
	 *<p> -3. Lipid-Lowering medications en Previos Prescription
	 *<p> -4. 	OR LDL > 100 Tomado 30 dias antes del arrival date o 2 dias despues.
	 *<p> -9.   OR Patients with Evidence of Atherosclerosis (en cualquier cuenta paciente)
	 *<p> -5. Que este dado de alta
	 *<p> -6. Y que sean mayores de 18 anios
	 *<p> -7. No Confort Measures
	 *<p> -8. Estatus de alta diferente a (02,07,20,43,50,51)
	 *
	 */
	//TODO Cambiar a medicamentos de Alta cuando este desarrollado.
	protected int pacientesMedida(){
		int pacientes=0;		

		StringBuilder str = new StringBuilder("SELECT count (DISTINCT cp.exme_paciente_id) FROM exme_ctapac cp ");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		   .append(" inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id ")
		//1.
		.append(" WHERE cp.ad_client_id = ? " +
				" and cp.ad_org_id = ? " +
				" and cp.exme_tipopaciente_id = ? " +
				" and ep.exme_paciente_id in ( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" inner join exme_diagnostico ed on ed.exme_diagnostico_id= diag.exme_diagnostico_id ")
			.append(" where diag.sequencediag = 1 ") //Dignostico Principal
			.append(" and diag.type = 'CF' ") 	 //Diagnosticos codificados
				//X_EXME_ActPacienteDiag.TYPE_CoderFinal
				.append(" and ed.value in (") 
				.append(" SELECT code FROM EXME_DiagnosisTypeDet dtd " )
				.append("inner join exme_diagnosisversion dv on dv.exme_diagnosisversion_id = dtd.exme_diagnosisversion_id ")
				.append(" WHERE dtd.EXME_DiagnosisType_ID = ? AND dtd.IsActive = 'Y'  AND dv.stage ='1' and dv.IsActive = 'Y' ) ")
			.append("  AND ");
		str.append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ? )") 
			
		//2.	
			
		.append(" and cp.exme_ctapac_id in (")
			//home medications
				.append(" select ep.exme_ctapac_id") 
				.append(" from exme_prescrx ep inner join  exme_prescrxdet epd on epd.exme_prescrx_id= ep.exme_prescrx_id ")
				.append(" where ep.tipo = 'DM' ")
				      .append(" and epd.exme_genproduct_id IN ") 
				      .append(" ( ")
				         .append(" SELECT EXME_GENPRODUCT_ID FROM EXME_TERAPIA_PRODUCTO TP ")
				         .append( " INNER JOIN EXME_THERAPIESVERSION TV ON TV.EXME_THERAPIESVERSION_ID = TP.EXME_THERAPIESVERSION_ID ") 
				         .append(" WHERE TV.EXME_TERAPIA_ID = ? AND TV.STAGE='1' ")      
				      .append(" )")
			.append(" )")		//Cambios Karla
			
			
			//Correccion para la tabla de EXME_TERAPIA_PRODUCTO agregar union con la tabla EXME_THERAPIESVERSION y filtrar por el campo stage = '1' de la tabla EXME_THERAPIESVERSION
			// asi mismo filtrar por el campo EXME_TERAPIA_ID de la tabla EXME_THERAPIESVERSION
		
			/*
			.append(" select distinct ap.exme_paciente_id ")
			.append(" from exme_actpacienteind api")
			.append(" inner join exme_actpacienteindh ph inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id ")
			.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
			.append(" where api.m_product_id in (")
				.append(" select m_product_id from exme_terapia_producto where exme_terapia_id = ? AND IsActive = 'Y')")
			.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')")
			.append(" and ph.prealta = 'Y' )")
			*/
		//3.			
		.append(" and cp.exme_paciente_id in (")
			//home medications
			.append(" (") 
				.append(" select cpa.exme_paciente_id ") 
				.append(" from exme_prescrx ep inner join  exme_prescrxdet epd on epd.exme_prescrx_id= ep.exme_prescrx_id ")
				.append(" inner join exme_ctapac cpa on cpa.exme_ctapac_id = ep.exme_ctapac_id ")
				.append(" where ep.tipo = 'HM' ")
				      .append(" and epd.exme_genproduct_id IN ") 
				       .append(" ( ")
				         .append(" SELECT EXME_GENPRODUCT_ID FROM EXME_TERAPIA_PRODUCTO TP ")
				         .append( " INNER JOIN EXME_THERAPIESVERSION TV ON TV.EXME_THERAPIESVERSION_ID = TP.EXME_THERAPIESVERSION_ID ") 
				         .append(" WHERE TV.EXME_TERAPIA_ID = ? AND TV.STAGE='1' ")      
				      .append(" )")
			.append(" )")
			.append(" UNION ")	
			
		/*	
		.append(" AND cp.EXME_CtaPac_ID in (")
				.append(" SELECT distinct prev.EXME_CtaPac_ID ")
				.append(" FROM exme_prescription_prev prev")
				.append(" where prev.m_product_id in (")
					.append(" select m_product_id from exme_terapia_producto where exme_terapia_id = ?)")
				.append(" AND cp.dateOrdered > prev.FechaToma)")
		*/	
		//4.
			.append(" (") 
		//.append(" OR cp.exme_paciente_id in (")
		.append(" Select distinct ap.exme_paciente_id  from exme_actpacienteind api " )	
				.append(" inner join exme_actpacienteindh ph ON api.exme_actpacienteindh_id = ph.exme_actpacienteindh_id ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id " )
				.append(" inner join exme_estudiosobx obx on obx.exme_actpacienteind_id = api.exme_actpacienteind_id" )
				.append(" where ")	
				
				
				//No es necesario el procedimiento, solo el resultado de laboratorio
				/*	
					"api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where te actype_id = ?)) ")
				.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
				*/
					.append(" obx.observation >= '100' and obx.C_UOM_ID = ? ")				
					.append(" AND obx.codeobx IN ")
						.append(" (SELECT value FROM EXME_LOINC WHERE EXME_LOINC_ID IN ")
							.append(" (SELECT EXME_Loinc_ID FROM EXME_LoincTypeDet td inner join EXME_LoincVersion lver on td.exme_loincversion_id = lver.exme_loincversion_id ") 
							.append(" WHERE lver.EXME_LoincType_ID = ? and lver.stage= '1'))")
					.append(" and obx.observationdate between (pd.arrivaldate-30) and (pd.arrivaldate + 2) ")		
			.append(" )")
			.append(" UNION ")	//Cambios karla
		//9.
				//.append(" OR ep.exme_paciente_id in( ")	
		 .append(" (") 
				.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
				.append ("inner join exme_diagnostico ed on diag.exme_diagnostico_id= ed.exme_diagnostico_id ")
				.append(" where ")
					.append(" ed.value in (")
					.append(" SELECT dtd.code FROM EXME_DiagnosisTypeDet dtd ")
					.append(" inner join exme_diagnosisversion dv on dv.exme_diagnosisversion_id = dtd.exme_diagnosisversion_id " )
					.append (" WHERE dv.EXME_DiagnosisType_ID = ? AND dv.stage = '1' AND dtd.isActive= 'Y' AND dv.isActive= 'Y' ) ")	
				.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ? )")	
				.append(" )") //Cambios Karla


		//5.	
			//La columna Estatusalta cambia a EncounterStatus Mejoras30/05/2011 GC 
		.append(" AND cp.EncounterStatus not in (?, ?, ?) ")
		//6.
		.append(" AND edadmuse( ep.fechanac, cp.dateordered) >= 18")
		//.append(" AND trunc(trunc((((86400*(cp.dateordered-ep.fechanac))/60)/60)/24)/365) >= 18")
		
		.append(" AND cp.dateOrdered >= to_date(?, 'dd/MM/yyyy') AND cp.fechaAlta <= to_date(?, 'dd/MM/yyyy')")
		//7.
		.append(" AND (cp.resstatus != ? or cp.resstatus is null )")
		//8.
		.append(" AND cp.exme_dischargestatus_id not in ")
		.append(" (SELECT EXME_DischargeStatus_ID FROM EXME_DischargEstatus WHERE VALUE IN ('02','07','20','43','50','51'))")
		; 
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), inpatient, this.diagnosisTypeIschemic_ID, this.ini, this.fin, MActPacienteDiag.ESTATUS_Inactive, this.terapiaEstatinaId, this.terapiaLipidos, this.measureLDL, this.loincLDL, this.diagnosisTypeArteriosclerosis, MActPacienteDiag.ESTATUS_Inactive, MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission, MEXMECtaPac.ENCOUNTERSTATUS_Admission, MEXMECtaPac.ENCOUNTERSTATUS_Predischarge, this.ini, this.fin, MEXMECtaPac.RESSTATUS_ComfortCareOnly);
		
		return pacientes;
	}
	
	/**
	 * Calcula los pacientes del denominador
	 * Ischemic stroke patients 
	 * Mismas consideraciones del numerador sin la medicacion de statina
	 * @return pacientes
	 */
	protected int pacientesDenominador(){
		int pacientes = 0;
		
		StringBuilder str = new StringBuilder(" SELECT count (DISTINCT cp.exme_paciente_id) FROM exme_ctapac cp ");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		   .append(" inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id ")
		//1.
		.append(" where cp.ad_client_id = ? " +
				" and cp.ad_org_id = ?" +
				" and cp.exme_tipopaciente_id = ? " +
				" and ep.exme_paciente_id in ( ")
			.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" inner join exme_diagnostico ed on ed.exme_diagnostico_id = diag.exme_diagnostico_id " )
			.append(" where diag.sequencediag = 1 ") //Dignostico Principal
				.append(" and diag.type = 'CF' ") 	 //Diagnosticos codificados
				//X_EXME_ActPacienteDiag.TYPE_CoderFinal
				.append(" and ed.value in (")
//				.append(" SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ")
				.append(" SELECT code FROM EXME_DiagnosisTypeDet dtd ")
				.append(" inner join exme_diagnosisversion dv on dv.exme_diagnosisversion_id = dtd.exme_diagnosisversion_id ")
				.append (" WHERE dv.EXME_DiagnosisType_ID = ? AND dv.IsActive = 'Y' AND dv.stage = '1' AND dtd.IsActive = 'Y'  ) ")
			.append("  AND ");
		str.append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ? )") 
//			//Cambios karla
			

		//2.						

		//3.			
		.append(" and cp.exme_paciente_id in (")
			//home medications
		  .append(" (") 
				.append(" select cpa.exme_paciente_id") 
				.append(" from exme_prescrx ep inner join  exme_prescrxdet epd on epd.exme_prescrx_id= ep.exme_prescrx_id ")
				.append(" inner join exme_ctapac cpa on cpa.exme_ctapac_id = ep.exme_ctapac_id ")
				.append(" where ep.tipo = 'HM' ")
				      .append(" and epd.exme_genproduct_id IN ") 
				      .append(" ( ")
				         .append(" SELECT EXME_GENPRODUCT_ID FROM EXME_TERAPIA_PRODUCTO " )
				         .append("INNER JOIN EXME_THERAPIESVERSION TV ON (TV.EXME_THERAPIESVERSION_ID = EXME_TERAPIA_PRODUCTO.EXME_THERAPIESVERSION_ID ) ") 
				         .append(" WHERE TV.EXME_TERAPIA_ID = ? AND TV.STAGE='1' )" )
				             		 .append(" )") 
			.append(" UNION ")  // Cambios 	Karla
			
//			
//			  .append(" SELECT EXME_GENPRODUCT_ID FROM EXME_TERAPIA_PRODUCTO TP ")
//				         .append( " INNER JOIN EXME_THERAPIESVERSION TV ON TV.EXME_THERAPIESVERSION_ID = TP.EXME_THERAPIESVERSION_ID ") 
//				         .append(" WHERE EXME_TERAPIA_ID = ? AND TV.STAGE='1' ")      
//				      .append(" )")
	
			//iReport
//			 select cpa.exme_paciente_id  
//				   from exme_prescrx ep inner join  exme_prescrxdet epd on epd.exme_prescrx_id= ep.exme_prescrx_id   
//				   inner join exme_ctapac cpa on cpa.exme_ctapac_id = ep.exme_ctapac_id
//				   where ep.tipo = 'HM'   
//				         and epd.exme_genproduct_id IN    
//				         (   
//				          SELECT EXME_GENPRODUCT_ID FROM EXME_TERAPIA_PRODUCTO WHERE EXME_TERAPIA_ID =  (select Lipid_Medication from exme_param_metricas)                                  
//				         )  
//			   )  	
//		UNION		
			
		/*	
		.append(" AND cp.EXME_CtaPac_ID in (")
				.append(" SELECT distinct prev.EXME_CtaPac_ID ")
				.append(" FROM exme_prescription_prev prev")
				.append(" where prev.m_product_id in (")
					.append(" select m_product_id from exme_terapia_producto where exme_terapia_id = ?)")
				.append(" AND cp.dateOrdered > prev.FechaToma)")
		*/	
		//4.
		//.append(" OR cp.exme_paciente_id in (")
	 .append(" (") 
		.append(" Select distinct ap.exme_paciente_id  from exme_actpacienteind api " )	
				.append(" inner join exme_actpacienteindh ph ")
				.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id " )
				.append(" on ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
				.append(" inner join exme_estudiosobx obx on obx.exme_actpacienteind_id = api.exme_actpacienteind_id" )
				.append(" where ")
				
				//No es necesario el procedimiento, solo el resultado de laboratorio
				/*	
					"api.m_product_id in ")
					.append(" (select m_product_id from m_product where exme_intervencion_id in ")
						.append(" (select exme_intervencion_id from exme_proceduretypedet where exme_proceduretype_id = ?)) ")			
				.append(" and api.dateordered between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
				*/
				.append(" obx.observation >= '100' and obx.C_UOM_ID = ?")				
				.append(" AND obx.codeobx IN ")
					.append(" (SELECT value FROM EXME_LOINC WHERE EXME_LOINC_ID IN ")
//						.append(" (SELECT EXME_Loinc_ID FROM EXME_LoincTypeDet inner join EXME_LoincVersion on " )
						.append(" (SELECT EXME_Loinc_ID FROM EXME_LoincTypeDet td inner join EXME_LoincVersion lver on td.exme_loincversion_id = lver.exme_loincversion_id ")
						.append(" WHERE lver.EXME_LoincType_ID = ? and lver.stage= '1'))")
				.append(" and obx.observationdate between (pd.arrivaldate-30) and (pd.arrivaldate + 2) ")		
				.append(" )")
				.append(" UNION ") //Cambios Karla	

				
		//9.
			//	.append(" OR ep.exme_paciente_id in( ")
				.append(" ( ") 
				.append(" select ap.exme_paciente_id from exme_actpacientediag diag inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
				.append(" inner join exme_diagnostico ed on ed.exme_diagnostico_id = diag.exme_diagnostico_id ")
				.append(" where ")
					.append(" ed.value in (")
					.append(" SELECT code FROM EXME_DiagnosisTypeDet dtd inner join exme_diagnosisversion dv " )
					.append(" on dtd.exme_diagnosisversion_id = dv.exme_diagnosisversion_id WHERE dv.EXME_DiagnosisType_ID = ? and dv.stage = '1' and dv.IsActive = 'Y' and dtd.IsActive = 'Y') ")		
				.append(" AND diag.IsActive = 'Y' AND diag.Estatus <> ?  )")	
				.append(" ) ") 
		//5.	
			//La columna Estatusalta cambia a EncounterStatus Mejoras30/05/2011 GC 
		.append(" AND cp.EncounterStatus not in (?, ?, ?) ")
		//6.
		.append(" AND edadmuse( ep.fechanac, cp.dateordered) >= 18")
		//.append(" AND trunc(trunc((((86400*(cp.dateordered-ep.fechanac))/60)/60)/24)/365) >= 18")
		
		.append(" AND cp.dateOrdered >= to_date(?, 'dd/MM/yyyy') AND cp.fechaAlta <= to_date(?, 'dd/MM/yyyy')")
		//7.
		.append(" AND (cp.resstatus != ? or cp.resstatus is null)")
		//8.
		.append(" AND cp.exme_dischargestatus_id not in ")
		.append(" (SELECT EXME_DischargeStatus_ID FROM EXME_DischargEstatus WHERE VALUE IN ('02','07','20','43','50','51'))")
		; 
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), inpatient, this.diagnosisTypeIschemic_ID, this.ini, this.fin, MActPacienteDiag.ESTATUS_Inactive, this.terapiaLipidos, this.measureLDL, this.loincLDL, this.diagnosisTypeArteriosclerosis, MActPacienteDiag.ESTATUS_Inactive, MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission, MEXMECtaPac.ENCOUNTERSTATUS_Admission, MEXMECtaPac.ENCOUNTERSTATUS_Predischarge, this.ini, this.fin, MEXMECtaPac.RESSTATUS_ComfortCareOnly);
		
		return pacientes;
	} 
	/**
	 * Metodo para validar que la consulta este funcionando (JUnit)
	 * @param numerador indica si se genera el numerador o el denominador
	 * @return indica el nuemro de pacientes obtenidos segun la metrica
	 */
	public static int testPacientesMedida(final boolean numerador) {
		final GeneraArchivoStroke6 bean = new GeneraArchivoStroke6(null, null, Env.getCtx(), null, 0, 0);
		int id;
		if (numerador) {
			id = bean.pacientesMedida();
		} else {
			id = bean.pacientesDenominador();
		}
		return id;
	}
}
