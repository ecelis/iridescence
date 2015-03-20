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
 * Genera xml para NQF 0441
 * Stroke-10
 * @author Rosy Velazquez
 */

public class GeneraArchivoStroke10{
	
	public GeneraArchivoStroke10(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
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
		
	private final static String PQRI_NUMBER = "0441"; 		
	
	private String archivo = "NQF_0441";
	private String measureGroup = "X"; //Sin grupo
		
	private int diagnosisTypeIschemic_ID = 0;
	private int diagnosisTypeHemorragic_ID = 0;
	private int procedureRehabilitation = 0;
			
	private int numFiles = 1;
	private int numFile = 1;
	private int inpatient = 0;
		
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	public File genera() throws Exception {		
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); //ya esta validado!
				
		//Llenar parametros de la tabla
		fechaIni = param.getFecha_Ini_Stroke10();
		fechaFin = param.getFecha_Fin_Stroke10();	
		
		diagnosisTypeIschemic_ID = param.getIschemic_Stroke();
		diagnosisTypeHemorragic_ID = param.getHemorrhagic_Stroke();
		procedureRehabilitation = param.getRehab_Procedure();
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
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) o hemorragic stroke activo en las fechas definidas por el reporte.
	 *					 secuencia = 1 
	 *					 diagnostico codificado
	 *<p> -2. <i>(B)</i> Con un procedimiento de servicios de rehabilitacion
	 *<p> -3. Que este dado de alta
	 *<p> -4. Y que sean mayores de 18 anios
	 *<p> -5. No Confort Measures
	 *<p> -6. Estatus de alta diferente a (02,07,20,43,50,51)
	 */
	protected int pacientesMedida(){
		int pacientes=0;		
		
		StringBuilder str = new StringBuilder("select count(DISTINCT cp.exme_paciente_id) ");
		str.append(" from exme_ctapac cp  ")
		.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  ")
		.append(" inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id ")//
		.append(" where cp.ad_client_id = ? " +
					  " and cp.ad_org_id = ? " +	
					  " and cp.exme_tipopaciente_id = ? " +
					  " and cp.exme_paciente_id in " )
					  
		//1.
			.append(" (select ap.exme_paciente_id from exme_actpacientediag diag  ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" inner join exme_diagnostico ed on ed.exme_diagnostico_id= diag.exme_diagnostico_id ") // 
			.append(" where diag.sequencediag = 1 ") //Dignostico Principal
			.append(" and diag.type = 'CF' ") 	 //Diagnosticos codificados			
			.append(" and ed.value in  ")
				.append(" (SELECT code FROM EXME_DiagnosisTypeDet dtd ")
				.append(" inner join exme_diagnosisversion dv on dv.exme_diagnosisversion_id = dtd.exme_diagnosisversion_id ") // 
				.append(" WHERE dv.EXME_DiagnosisType_ID = ? AND dtd.IsActive = 'Y' AND dv.stage ='1' and dv.IsActive = 'Y' ) ")// 
			.append(" OR ed.value in ")
				.append(" (SELECT code FROM EXME_DiagnosisTypeDet dtd inner join exme_diagnosisversion dv on dv.exme_diagnosisversion_id = dtd.exme_diagnosisversion_id WHERE dv.EXME_DiagnosisType_ID = ? AND dtd.IsActive = 'Y' AND dv.stage ='1' and dv.IsActive = 'Y' ) ") //Cambio EXME_Diagnostico_ID prr code
			.append(" and ");
		
		
		str.append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y')")
			
		//2.
		.append(" and cp.exme_paciente_id in ")
		.append(" (select distinct ap.exme_paciente_id  from exme_actpacienteind api " )	
		.append(" inner join exme_actpacienteindh ph ON api.exme_actpacienteindh_id = ph.exme_actpacienteindh_id ")
		.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id " )
		.append(" Where api.m_product_id in ") //Duda
			.append(" (select m_product_id from m_product where exme_intervencion_id in ") 
				.append(" (select exme_intervencion_id from exme_proceduretypedet ptd " )
   				.append("inner join exme_ProceduresVersion pv ON ptd.exme_ProceduresVersion_ID = PV.exme_ProceduresVersion_ID AND PV.Stage='1' ")
				.append ("where pv.exme_proceduretype_id = ? AND pv.IsActive = 'Y'  AND ptd.IsActive = 'Y' )) ")
		.append(" and ");
		str.append(DB.truncDate("api.dateordered", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy'))")
			
		
		//3.
		.append(" AND cp.EncounterStatus not in (?, ?, ?) ")
		
		//4.
		.append(" AND edadmuse( ep.fechanac, cp.dateordered) >= 18 ")		
		
		.append(" AND c 	p.dateOrdered >= to_date(?, 'dd/MM/yyyy') AND cp.fechaAlta <= to_date(?, 'dd/MM/yyyy') ")
		
		//5.
		.append(" AND (cp.resstatus != ? or cp.resstatus is null) ")

		//6.
		.append(" AND cp.exme_dischargestatus_id not in ")
		.append(" (SELECT EXME_DischargeStatus_ID FROM EXME_DischargEstatus WHERE VALUE IN ('02','07','20','43','50','51'))");
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), inpatient, this.diagnosisTypeIschemic_ID, this.diagnosisTypeHemorragic_ID, this.ini, this.fin, MActPacienteDiag.ESTATUS_Inactive, this.procedureRehabilitation, this.ini, this.fin, MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission, MEXMECtaPac.ENCOUNTERSTATUS_Admission, MEXMECtaPac.ENCOUNTERSTATUS_Predischarge, this.ini, this.fin, MEXMECtaPac.RESSTATUS_ComfortCareOnly);
		
		return pacientes;
	}
	
	/**
	 * Calcula los pacientes del denominador
	 * Ischemic stroke or hemorragic stroke patients
	 * Mismas consideraciones que numerador sin los servicios de rehabilitacion
	 * @return pacientes
	 */
	protected int pacientesDenominador(){
		int pacientes = 0;
		
		StringBuilder str = new StringBuilder("select count(DISTINCT cp.exme_paciente_id) ");
		str.append(" from exme_ctapac cp  ")
		.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id ")
		.append(" inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id ") // 
		.append(" where cp.ad_client_id = ? " +
					  " and cp.ad_org_id = ? " +	
					  " and cp.exme_tipopaciente_id = ? " +
					  " and cp.exme_paciente_id in " )
				  
					  
		//1.
			.append(" (select ap.exme_paciente_id from exme_actpacientediag diag ")
			.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id ")
			.append(" inner join exme_diagnostico ed on ed.exme_diagnostico_id= diag.exme_diagnostico_id ") 
			.append(" where diag.sequencediag = 1 ") //Dignostico Principal
			.append(" and diag.type = 'CF' ") 	 //Diagnosticos codificados			
			.append(" and ed.value in   ")
				.append(" (SELECT code FROM EXME_DiagnosisTypeDet dtd inner join exme_diagnosisversion dv on dv.exme_diagnosisversion_id = dtd.exme_diagnosisversion_id WHERE dv.EXME_DiagnosisType_ID = ? AND dv.IsActive = 'Y' AND dv.stage = '1' AND dtd.IsActive = 'Y' ) ")
			.append(" OR  ed.value in ")
				.append(" (SELECT code FROM EXME_DiagnosisTypeDet dtd inner join exme_diagnosisversion dv on dv.exme_diagnosisversion_id = dtd.exme_diagnosisversion_id WHERE dv.EXME_DiagnosisType_ID = ? AND dv.IsActive = 'Y' AND dv.stage = '1' AND dtd.IsActive = 'Y') ")
			.append(" and ");
		str.append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ")
			.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y')")
			

			
			
		//2.
				
		//3.
		.append(" AND cp.EncounterStatus not in (?, ?, ?) ")
		
		//4.
		.append(" AND edadmuse( ep.fechanac, cp.dateordered) >= 18 ")
		
		.append(" AND cp.dateOrdered >= to_date(?, 'dd/MM/yyyy') AND cp.fechaAlta <= to_date(?, 'dd/MM/yyyy')")
		
		//5.
		.append(" AND (cp.resstatus != ? or cp.resstatus is null)")

		//6.
		.append(" AND cp.exme_dischargestatus_id not in ")
		.append(" (SELECT EXME_DischargeStatus_ID FROM EXME_DischargEstatus WHERE VALUE IN ('02','07','20','43','50','51'))");
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), inpatient, this.diagnosisTypeIschemic_ID, this.diagnosisTypeHemorragic_ID, this.ini, this.fin, MActPacienteDiag.ESTATUS_Inactive, MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission, MEXMECtaPac.ENCOUNTERSTATUS_Admission, MEXMECtaPac.ENCOUNTERSTATUS_Predischarge, this.ini, this.fin, MEXMECtaPac.RESSTATUS_ComfortCareOnly);
		
		return pacientes;
	}
	/**
	 * Metodo para validar que la consulta este funcionando (JUnit)
	 * @param numerador indica si se genera el numerador o el denominador
	 * @return indica el numero de pacientes obtenidos segun la metrica
	 */
	public static int testPacientesMedida(final boolean numerador) {
		final GeneraArchivoStroke10 bean = new GeneraArchivoStroke10(null, null, Env.getCtx(), null, 0, 0);
		int id;
		if (numerador) {
			id = bean.pacientesMedida();
		} else {
			id = bean.pacientesDenominador();
		}
		return id;
	}
}
