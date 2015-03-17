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
 * Genera xml para NQF 0440
 * Stroke-8
 * @author Rosy Velazquez
 */

public class GeneraArchivoStroke8{
	
	public GeneraArchivoStroke8(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
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
	
	private final static String PQRI_NUMBER = "0440"; 		
	
	private String archivo = "NQF_0440";
	private String measureGroup = "X"; //Sin grupo	
	
	private int diagnosisTypeIschemic_ID = 0;
	private int diagnosisTypeHemorragic_ID = 0;
	private int inpatient = 0;
	
	private int numFiles = 1;
	private int numFile = 1;
		
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	public File genera() throws Exception {		
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); //ya esta validado!
				
		//Llenar parametros de la tabla
		fechaIni = param.getFecha_Ini_Stroke8();
		fechaFin = param.getFecha_Fin_Stroke8();				
		diagnosisTypeIschemic_ID = param.getIschemic_Stroke();
		diagnosisTypeHemorragic_ID = param.getHemorrhagic_Stroke();							
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
	
	
	private String getSQL(final boolean numerador, final List<Object> params) {
		final StringBuilder str = new StringBuilder();

		str.append("select count (DISTINCT cp.exme_paciente_id) \n");
		str.append(" from exme_ctapac cp  \n");
		str.append(" inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  \n");
		str.append(" where cp.ad_client_id = ? \n");
					  str.append(" and cp.ad_org_id = ? \n");
					  str.append(" and cp.exme_tipopaciente_id = ? \n");
					  str.append(" and cp.exme_paciente_id in \n" );
					  params.add(Env.getAD_Client_ID(ctx));
						params.add(Env.getAD_Org_ID(ctx));
						params.add(inpatient);
			//1.
			str.append(" (select ap.exme_paciente_id from exme_actpacientediag diag \n");
			str.append(" inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id \n");
			str.append("INNER JOIN EXME_DIAGNOSTICO DM ON (DM.EXME_DIAGNOSTICO_ID = DIAG.EXME_DIAGNOSTICO_ID) \n");
			str.append("INNER JOIN EXME_DiagnosisTypeDet dt ON (dt.code = DM.value \n");
			str.append(" AND dt.IsActive='Y') \n");
			str.append(" where diag.sequencediag = 1 \n"); //Dignostico Principal
			str.append(" and diag.type = 'CF' \n"); 	 //Diagnosticos codificados			
			str.append(" and diag.exme_diagnostico_id in \n ");
				str.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) \n");
			str.append(" OR diag.exme_diagnostico_id in \n");
				str.append(" (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) \n");
			str.append(" and \n");
		str.append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		
		str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
			str.append(" AND diag.Estatus <> ? AND diag.IsActive = 'Y') \n");
			
			
				params.add( this.diagnosisTypeIschemic_ID);
		params.add(this.diagnosisTypeHemorragic_ID);
		params.add(this.ini);
		params.add(this.fin);
		params.add(MActPacienteDiag.ESTATUS_Inactive);
		//2.
		if(numerador){
		str.append(" and cp.exme_paciente_id in \n");
			str.append(" (select distinct pac.exme_paciente_id from exme_recursoeducativopac pac \n" );
			  str.append(" where \n");
		str.append(DB.truncDate("pac.created", "dd"));
		
		str.append("\n between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ) \n");
			//.append(" where pac.exme_recursoeducativo_id in ")
			//	.append(" (select exme_recursoeducativo_id from exme_recursoeducativostr ")
			//	.append(" where type in (?, ?)))")
		params.add(this.ini);
		params.add(this.fin);
		}
		//3.
		str.append("\n AND cp.exme_dischargestatus_id in \n");
		str.append("\n (SELECT EXME_DischargeStatus_ID FROM EXME_DischargEstatus WHERE VALUE IN ('01','50')) \n");
		//4.
		str.append(" AND cp.dateOrdered >= to_date(?, 'dd/MM/yyyy') AND cp.fechaAlta <= to_date(?, 'dd/MM/yyyy')\n");
		str.append(" AND edadmuse( ep.fechanac, cp.dateordered) >= 18");		
		params.add(this.ini);
		params.add(this.fin);
		//5.	
		str.append(" AND (cp.resstatus != ? or cp.resstatus is null)");		
		params.add(MEXMECtaPac.RESSTATUS_ComfortCareOnly);
		//6.
		str.append(" AND cp.EncounterStatus not in (?,?,?) ");
		params.add(MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission);
		params.add( MEXMECtaPac.ENCOUNTERSTATUS_Admission);
		params.add( MEXMECtaPac.ENCOUNTERSTATUS_Predischarge);
		
		return str.toString();
	
	}
	
	/**
	* Numerador
	 * 
	 *<br> Calcula los pacientes 
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke) o hemorragic stroke activo en las fechas definidas por el reporte.
	 *					 secuencia = 1 
	 *<p> -2. <i>(B)</i> Que tengan asigndos los materiales educativos durante la estancia en el hospital.
	 *<p> -3. <i>(B)</i> discharge status = (01, 50) A Discharge to Home or Homecare
	 *<p> -4. <i>(B)</i> Edad > 18
	 *<p> -5. <i>(B)</i> Excluir pacientes with comfort measures 
	 *<p> -6. <i>(B)</i> Cuenta dada de alta
	 */
	protected int pacientesMedida(){		
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(false, params), params.toArray());
	}
	
	/**
	 * Calcula los pacientes del denominador
	 * Ischemic or hemorragic stroke patients
	 * Mismas consideraciones del numerador sin el material educativo
	 * @return pacientes
	 */
	protected int pacientesDenominador(){
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(true, params), params.toArray());
	}
	public static int testPacientesMedida(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		GeneraArchivoStroke8 bean = new GeneraArchivoStroke8(version, optionRegistry, ctx, trxName, file, files);
		int id = bean.pacientesMedida();
		return 1;
	}
	/**
	 * Metodo para validar que la consulta esta funcionando (JUnit)
	 * @param numerador indica si se genera el numerador o el denominador
	 * @return indica el nuemro de pacientes obtenidos segun la metrica
	 */
	public static int testPacientesMedida(final boolean numerador) {
		final GeneraArchivoStroke8 bean = new GeneraArchivoStroke8(null, null, Env.getCtx(), null, 0, 0);
		int id;
		if (numerador) {
			id = bean.pacientesMedida();
		} else {
			id = bean.pacientesDenominador();
		}
		return id;
	}

}
