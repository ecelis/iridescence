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
 * Genera xml para NQF 0371
 * VTE-1
 * @author Rosy Velazquez
 */

public class GeneraArchivoVte1{
	
	public GeneraArchivoVte1(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
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
	private int estacion_icu=0;
	private String optionRegistry="";
	private String version = "";		
	
	private final static String PQRI_NUMBER = "0371"; 		
	
	private String archivo = "NQF_0371";
	private String measureGroup = "X"; //Sin grupo
	
	private int numFiles = 1;
	private int numFile = 1;	
	
	private int terapiaVteProphylaxis = 0;	
	
	private int diagnosisTypeMental = 0;
	private int diagnosisTypeHemorragic = 0;
	private int diagnosisTypeIschemic = 0;
	private int diagnosisTypeVTE = 0;
	private int diagnosisTypeObstretics = 0;
	private int inpatient = 0;
	
	private int estacionICU = 0;
		
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	public File genera() throws Exception {		
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); //ya esta validado!
				
		//Llenar parametros de la tabla
		fechaIni = param.getFecha_Ini_Vte1();
		fechaFin = param.getFecha_Fin_Vte1();		
		
		terapiaVteProphylaxis = param.getEXME_ProphylaxisVte_ID();		
		//Diagnosis Type
		diagnosisTypeMental = param.getMentalPatient();
		diagnosisTypeHemorragic = param.getHemorrhagic_Stroke();
		diagnosisTypeIschemic = param.getIschemic_Stroke();
		diagnosisTypeVTE = param.getVTE_Diagnostic();
		diagnosisTypeObstretics = param.getObstretics();
		inpatient = param.getEXME_EstServ_Hosp_ID();
		estacion_icu = param.getEXME_EstServIcu_ID();
		
		this.estacionICU = param.getEXME_EstServIcu_ID();
		
		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER, this.pacientesMedida(), this.pacientesDenominador()));				
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);		
				
		return generado;
	}	
	
	public static int testPacientesMedida(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		GeneraArchivoVte1 bean = new GeneraArchivoVte1(version, optionRegistry, ctx, trxName, file, files);
		int id = bean.pacientesMedida();
		id = bean.pacientesDenominador();
		return 1;
	}
	
	
	
	

private String getSQL(final boolean numerador, final List<Object> params) {
		final StringBuilder str = new StringBuilder();
		str.append("WITH ICU_DATES AS ( \n");
str.append("SELECT cp.exme_ctapac_id, \n");
		str.append("       COALESCE ((SELECT ch.created \n");
		str.append("                  FROM exme_ctapacchanges ch \n");
		str.append("	              INNER JOIN exme_estserv es ON (es.exme_area_id=ch.exme_areaact_id \n");
		str.append("                                             AND es.exme_estserv_id=?  \n");
		str.append("                                             AND ch.exme_ctapac_id=cp.exme_ctapac_id) \n");
		str.append("                  WHERE ch.exme_areaact_id IS NOT NULL \n");
		str.append("                  ORDER BY ch.created DESC \n");
		str.append(DB.getDatabase().addPagingSQL(" ", 1, 1));//Limit 1
		str.append("                  ), cp.dateordered) AS icudate \n");
		str.append("FROM EXME_ctapac cp  \n");
		
		str.append("WHERE cp.AD_client_ID=? \n"); //1
		str.append("AND cp.AD_org_ID=? \n"); // 2
		str.append("AND cp.EXME_tipopaciente_ID=? \n"); //3
		str.append("AND (cp.exme_estserving_id=? OR cp.exme_estserv_id=?) \n");
		str.append(") \n");
		
		params.add(estacion_icu);
		params.add(Env.getAD_Client_ID(ctx));
		params.add(Env.getAD_Org_ID(ctx));
		params.add(inpatient);
		params.add(estacion_icu);
		params.add(estacion_icu);
		
		str.append("select COUNT(DISTINCT cp.exme_paciente_id) \n");
		str.append("\t from exme_ctapac cp 	\n");
		str.append("\t inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  \n");  
		
		if(numerador){
		str.append("\t inner join EXME_PlanMed pm on cp.exme_ctapac_id = pm.exme_ctapac_id \n");
		str.append("\t inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID \n") ;
		str.append("\t inner join m_product pro on pm.m_product_id = pro.m_product_id	\n");		
		str.append("\t inner join EXME_TERAPIA_PRODUCTO tp ON tp.exme_genproduct_id = pro.exme_genproduct_id	\n");
		str.append("\t INNER JOIN EXME_THERAPIESVERSION TV ON (TV.EXME_THERAPIESVERSION_ID = tp.EXME_THERAPIESVERSION_ID AND TV.EXME_TERAPIA_ID = ? and TV.STAGE='1')		\n");
		params.add(terapiaVteProphylaxis);
		}
		str.append("\t where cp.ad_client_id = ? and cp.ad_org_id = ?		\n");
		str.append("\t and cp.exme_tipopaciente_id = ? 	\n");
		
				
			
				params.add(Env.getAD_Client_ID(ctx));
				params.add(Env.getAD_Org_ID(ctx));
				params.add(inpatient);
				
			//1.1
		if(numerador){
					str.append("\t\t and \n\t\t");
					str.append(DB.truncDate("pm.startDate", "dd"));
					str.append("\n\t\t between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') ");
//			str.append(" and (pml.apliedDate - cp.dateOrdered) <= 2 )");// twry actualizacion 
			str.append("\t\t and extract('days' from (pml.apliedDate - cp.dateOrdered))  <= 2 \n");	
			
		
			params.add(this.ini);
			params.add(this.fin);
			
			
			//1.2.     
			str.append("\t OR (cp.exme_ctapac_id in (select distinct ph.exme_ctapac_id from  exme_actpacienteindh ph \n");			
			str.append("\t inner join exme_actpaciente ap on ap.exme_actpaciente_id = ph.exme_actpaciente_id   \n");
			str.append("\t inner join exme_progquiro pq on pq.exme_paciente_id = ap.exme_paciente_id  ");
			str.append("\n where \t");
			str.append(DB.truncDate("pm.startDate", "dd"));		
			str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n ");
			str.append("\n and \t\t");
			str.append(DB.truncDate("pq.fechaInicio","dd"));
			 str.append("\n\t\t between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy'\n)"); 
//			str.append(" and (pml.apliedDate - cp.dateOrdered) <= 2 ");	// twry actualizacion 
			str.append("\n\t\t and extract('days' from (pml.apliedDate - cp.dateOrdered)) <= 2\n ");	
//			str.append(" and (pq.fechainicio - cp.dateOrdered) <= 2 ) ");// twry actualizacion 
			str.append("\t\t and extract('days' from (pq.fechainicio - cp.dateOrdered)) <= 2 )) \n ");
			
		
	
			params.add(this.ini);
			params.add(this.fin);
			params.add(this.ini);
			params.add(this.fin);
		}
			//2.
		str.append("\n AND edadmuse(ep.fechanac, cp.dateordered) >= 18\n");		
		
		//3.
//		str.append(" AND (cp.fechaalta - cp.dateordered) > 2  " )
		str.append("AND extract('days' from (cp.fechaalta - cp.dateordered)) > 2  \n" );
//		str.append(" AND (cp.fechaalta - cp.dateordered) < 120 ");
		str.append("AND extract('days' from (cp.fechaalta - cp.dateordered)) < 120\n ");
		//4.
		str.append("AND (cp.resstatus != ? or cp.resstatus is null)\n");
		params.add(MEXMECtaPac.RESSTATUS_ComfortCareOnly);
		
		//5.
		str.append(" AND cp.exme_ctapac_id not in (select ICU_DATES.exme_ctapac_id from ICU_DATES)\n");
		//str.append("AND cp.exme_estserving_id != ? \n");
		//str.append("AND cp.exme_estserv_id != ? \n");
		
		//6.
		str.append(  "AND cp.EXME_CtaPac_ID NOT IN \n");
			str.append("     (SELECT ap.EXME_CtaPac_ID \n");
			str.append("      FROM EXME_actpacientediag diag \n");
			str.append("       INNER JOIN EXME_actpaciente ap ON (ap.EXME_actpaciente_ID=diag.EXME_actpaciente_ID)  \n");
			str.append("	  INNER JOIN EXME_DIAGNOSTICO DM ON (DM.EXME_DIAGNOSTICO_ID = DIAG.EXME_DIAGNOSTICO_ID)\n");
			str.append("      INNER JOIN EXME_DiagnosisTypeDet dt ON (dt.code = DM.value \n");
			str.append("                                          AND dt.IsActive='Y' \n");
			str.append("                                          AND dt.EXME_DiagnosisType_ID IN (?,?,?,?,?)) \n");
			str.append(" 	  inner join exme_diagnosisVersion dv on (dv.exme_diagnosisVersion_id = dt.exme_diagnosisVersion_ID AND dv.stage = '1')\n");
			str.append("      WHERE diag.sequencediag=1 \n");
			str.append("        AND diag.type='CF' \n");
			str.append("        AND \n");
			str.append(DB.truncDate("diag.fecha_diagnostico", "dd"));
			str.append("        BETWEEN to_date(?,'dd/MM/yyyy') AND to_date(?,'dd/MM/yyyy') \n");
			str.append("        AND diag.Estatus<> ? AND diag.IsActive='Y'  \n");
		
		params.add(this.diagnosisTypeMental);
		params.add(this.diagnosisTypeHemorragic);
		params.add(this.diagnosisTypeIschemic);
		params.add(this.diagnosisTypeVTE);
		params.add(this.diagnosisTypeObstretics);
		params.add(this.ini);
		params.add(this.fin);
		params.add(MActPacienteDiag.ESTATUS_Inactive);
		
		

		
		//11.
		str.append("AND cp.EncounterStatus not in (?,?,?)) \n");		
		
		params.add(MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission);
		params.add(MEXMECtaPac.ENCOUNTERSTATUS_Admission);
		params.add(MEXMECtaPac.ENCOUNTERSTATUS_Predischarge);
	 
		//pacientes = CQMeasures.executaSql(str.toString(),params.toArray());
		
		return str.toString();
	}
	
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. Con medicamento de VTE_PROPHYLAXIS 
	 *		  1.1. el dia de su ingreso o el segundo dia de su admision.
	 *		  1.2. O con medicamento de VTE_PROPHYLAXIS al final del dia de una cirugia 
	 *		  	   cuando la cirugia fue el dia de la admision o el dia siguiente. 		
	 *<p> -2. Edad > 18
	 *<p> -3. Discharge date minus admission date > 2 dias y < 120 dias
	 *<p> -4. No incluir pacientes Comfort Measures Only 
	 *<p> -5. No incluir pacientes de ICU
	 *<p> -6. No pacientes con diagnostico principal = Mental Disorders
	 *<p> -7. 										 = Hemorragic Stroke 
	 *<p> -8. 										 = Ischemic Stroke
	 *<p> -9.										 = VTE
	 *<p> -10.										 = Obstetrics
	 *<p> -11. Pacientes dados de alta
	 *
	 * 	 */
	protected int pacientesMedida(){	
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(true, params), params.toArray());

	}
	
	/**
	 * Calcula los pacientes del denominador
	 * Todos los pacientes del numerador sin la terapia
	 * @return pacientes
	 */
	protected int pacientesDenominador(){ 
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(false, params), params.toArray());
			}
	

	/**
	 * Metodo para validar que la consulta esta funcionando (JUnit)
	 * @param numerador indica si se genera el numerador o el denominador
	 * @return indica el nuemro de pacientes obtenidos segun la metrica
	 */
	public static int testPacientesMedida(final boolean numerador) {
		final GeneraArchivoVte1 bean = new GeneraArchivoVte1(null, null, Env.getCtx(), null, 0, 0);
		int id;
		if (numerador) {
			id = bean.pacientesMedida();
		} else {
			id = bean.pacientesDenominador();
		}
		return id;
	}
	
}
	


