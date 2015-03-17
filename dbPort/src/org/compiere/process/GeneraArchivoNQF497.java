package org.compiere.process;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMEParamMetricas;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0497
 * Emergency Department (ED)-2
 * @author Rosy Velazquez
 */

public class GeneraArchivoNQF497{
	
	public GeneraArchivoNQF497(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
		this.numFiles = files;
	}
	private static CLogger log = CLogger.getCLogger(GeneraArchivoNQF497.class);
	private Properties ctx;
	private String trxName;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	private String ini = "";
	private String fin = "";
	
	private String optionRegistry="";
	private String version = "";	
	
	private int estacionUrgencias_ID = 0;
	private int inpatientType_ID = 0;
	private int mentalDiagnosisType_ID = 0;
	private int observationType_ID = 0;
	
	private int denominador1 = 0; //numero de pacientes
	private int denominador2 = 0;
	private int denominador3 = 0;
	
	private final static String PQRI_NUMBER = "0497"; 		
	
	private String archivo = "NQF_0497";
	private String measureGroup = "X"; //Sin grupo
		
	private BigDecimal mediana1 = BigDecimal.ZERO;
	private BigDecimal mediana2 = BigDecimal.ZERO;
	private BigDecimal mediana3 = BigDecimal.ZERO;
	
	private int numFiles = 1;
	private int numFile = 1;

	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	public File genera() throws Exception {		
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); //ya esta validado!
				
		//Llenar parametros de la tabla
		fechaIni = param.getFecha_Ini_Urgencia_ED();
		fechaFin = param.getFecha_Fin_Urgencia_ED();
				
		estacionUrgencias_ID = param.getEXME_EstServ_Urgencias_ID(); // Es el admit Source ER
		inpatientType_ID = param.getEXME_EstServ_Hosp_ID(); // Patient Type = Inpatient
		mentalDiagnosisType_ID = param.getMentalPatient();
		observationType_ID = param.getObsPatient();			
		
		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);
		
		executaQueryNumDen1(); //Asignar numerador y denominador 1.1
		this.pacientesObs(); //Asignr numerador y denominador 1.2
		this.pacientesMentales(); //Asignar numerador y denominador 1.3
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(pqri());		
		pqris.add(pqri2());
		pqris.add(pqri3());
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);		
				
		return generado;
	}	
	
	/**
	 * Calcula el numerador y denominador para la medida 1.1
	 * 		Pacientes con admit source ER
	 * 		Patient Type = Inpatient 
	 * 		Principal Diagnosis no mental disorders
	 * @return 
	 */
	private List<Object> executaQueryNumDen1(){		
		
		StringBuilder sqlbuild = new StringBuilder(
			"select count(cuentas) as denominador, median(minutos) as numerador  " + 
			" from " +
			" (select cp.ad_org_id as cuentas, ((extract (epoch from (cp.departuredate - cp.dateordered)))/60)::integer as minutos " +
			" from exme_ctapac cp inner join exme_admitsource eas on eas.exme_admitsource_id = cp.exme_admitsource_id " +
			                    " inner join exme_tipopaciente tp on tp.exme_tipopaciente_id = cp.exme_tipopaciente_id " +
			                    " inner join exme_actpaciente ap inner join exme_actpacientediag apd inner join exme_diagnostico ed " + 
			                    		" on ed.exme_diagnostico_id = apd.exme_diagnostico_id " +
			                            " on apd.exme_actpaciente_id = ap.exme_actpaciente_id  " +
			                    " on ap.exme_ctapac_id = cp.exme_ctapac_id " +
			     " where cp.ad_org_id = ? " + //10001017
			     " and cp.ad_client_id = ? " + //10001000
			     " and cp.exme_admitsource_id = (select exme_admitsource_id from exme_admitsource where claimcode = '7' and isactive = 'Y' ) " + // 1000006--- 'ER'
			     " and cp.exme_tipopaciente_id = ? " +// 10001007--- Inpatient    
			     " and cp.dateordered >= to_date(? ,'dd/MM/yyyy') " + //fecha inicial
			     " and cp.dateordered <= to_date(? ,'dd/MM/yyyy') " + //fecha final
			     " and cp.departuredate >= to_date(? ,'dd/MM/yyyy') " + //fecha inicial
			     " and cp.departuredate <= to_date(? ,'dd/MM/yyyy') " + //fecha final     
			     " and apd.sequencediag = 1 " +
			     " and apd.type = 'CF' " +
			     " and apd.isactive = 'Y' " +
			     " and ed.value not in (select dtd.code from exme_diagnosistypedet as dtd " +
			     " Inner Join Exme_DiagnosisVersion dv on (dv.Exme_DiagnosisVersion_id = dtd.Exme_DiagnosisVersion_ID" +
			     " And dv.Stage = '1')" +
			     " where dv.exme_diagnosistype_id = ? and dv.isactive = 'Y' and dtd.IsActive = 'Y')" +
			" ) SBQ"); //10001001 Mental Type
		
		List<Object> lista = ejecutaSQL(this.trxName, sqlbuild.toString(),Env.getAD_Org_ID(ctx) , Env.getAD_Client_ID(ctx), this.inpatientType_ID, this.ini, this.fin, this.ini, this.fin, this.mentalDiagnosisType_ID);		
		if(lista !=null && !lista.isEmpty()){
			denominador1 = ((Long) lista.get(0)).intValue();
			mediana1 = (BigDecimal) lista.get(1); 
			if(mediana1 == null){
				mediana1 = BigDecimal.ZERO;
			}
		}	
		return lista;
	}
	
	//Ejecuta sql para calculo de minutos	
	public List<Object> ejecutaSQL(String trxName, String sql, Object... params) {
		
		List<Object> listaMinutos = new ArrayList<Object>();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try	{
    		pstmt = DB.prepareStatement(sql, trxName);
    		DB.setParameters(pstmt, params);
    		rs = pstmt.executeQuery();

    		while (rs.next()) { 
    			listaMinutos.add(rs.getObject(1));
    			listaMinutos.add(rs.getObject(2));    			
    		}
    	} catch (SQLException e) {
    		listaMinutos=null;
    		log.severe("getSQLValueEx " + sql);
    	} finally {
    		DB.close(rs, pstmt);
    		rs = null; 
    		pstmt = null;
    	}    
    	    	
    	return listaMinutos;
    } 	
		
	/**
	 * Arma el pqri para la medida
	 *  numerador = mediana de tiempo
	 * 	denominador = pacientes ingresados por ER, patien type = inpatient
	 * 	exclusiones = pacientes en observacion y con diagnostico de mental disorders;
	 * @return pqri
	 */
	protected String[] pqri(){
		String[] pqri = new String[5];
		pqri[0] = PQRI_NUMBER + "_1.1"; //pqri_number
		
		BigDecimal num = this.mediana1;
		num = num.setScale(0, BigDecimal.ROUND_CEILING);
		pqri[1] = num.toString(); //numerador		
		
		pqri[2] = String.valueOf(denominador1); //denominador
		int exclusions = denominador2 + denominador3;
		pqri[3] = String.valueOf(exclusions); // exclusions
		pqri[4] = "0"; //not met
		
		return pqri;		
	}	
	
	/**
	 * Arma el pqri para el segundo inciso
	 * 	numerador = promedio de tiempo
	 * 	denominador = pacientes en observacion
	 * 	exclusiones = 0
	 * @return pqri
	 */
	protected String[] pqri2(){
		String[] pqri = new String[5];
		pqri[0] = PQRI_NUMBER + "_1.2"; //pqri_number
		
		BigDecimal num = mediana2;
		num = num.setScale(0, BigDecimal.ROUND_CEILING);
		pqri[1] = num.toString(); //numerador
		
		pqri[2] = String.valueOf(denominador2); //denominador
		pqri[3] = "0"; // exclusions
		pqri[4] = "0"; //not met
		
		return pqri;
	}
	
	/**
	 * Arma el pqri para el tercer inciso
	 * 	numerador = promedio de tiempo
	 * 	denominador = pacientes de psycriactric/Mental Health
	 * 	exclusiones = 0
	 * @return pqri
	 */
	protected String[] pqri3(){
		String[] pqri = new String[5];
		pqri[0] = PQRI_NUMBER + "_1.3"; //pqri_number
		
		BigDecimal num = mediana3;
		num = num.setScale(0, BigDecimal.ROUND_CEILING);
		pqri[1] = num.toString(); //numerador
		
		pqri[2] = String.valueOf(denominador3); //denominador
		pqri[3] = "0"; // exclusions
		pqri[4] = "0"; //not met
		
		return pqri;
	}
	
	/**
	 * Calcula numerador y denominador 1.2
	 * pacientes tipo observacion 
	 * Que no sea paciente mental
	 * admision source = ER
	 * 
	 * @return 
	 */
	private List<Object> pacientesObs(){				
		
		StringBuilder sqlbuild = new StringBuilder(
				"select count(cuentas) as denominador, median(minutos) as numerador  " + 
				" from " +
				"(select cp.ad_org_id as cuentas, ((extract (epoch from (cp.departuredate - cp.dateordered)))/60)::integer as minutos " +
				"from exme_ctapac cp inner join exme_admitsource eas on eas.exme_admitsource_id = cp.exme_admitsource_id " +
				                    " inner join exme_tipopaciente tp on tp.exme_tipopaciente_id = cp.exme_tipopaciente_id " +
				                    " inner join exme_actpaciente ap inner join exme_actpacientediag apd inner join exme_diagnostico ed " + 
				                    												" on ed.exme_diagnostico_id = apd.exme_diagnostico_id " +
				                                                   " on apd.exme_actpaciente_id = ap.exme_actpaciente_id  " +
				                    " on ap.exme_ctapac_id = cp.exme_ctapac_id " +
				" where cp.ad_org_id = ? " + //10001017
				     " and cp.ad_client_id = ? " + //10001000
				     " and cp.exme_admitsource_id = (select exme_admitsource_id from exme_admitsource where claimcode = '7' and isactive = 'Y' ) " + // 1000006--- 'ER'
				     " and cp.exme_tipopaciente_id = ? " +// --- Observation    
				     " and cp.dateordered >= to_date(? ,'dd/MM/yyyy') " + //fecha inicial
				     " and cp.dateordered <= to_date(? ,'dd/MM/yyyy') " + //fecha final
				     " and cp.departuredate >= to_date(? ,'dd/MM/yyyy') " + //fecha inicial
				     " and cp.departuredate <= to_date(? ,'dd/MM/yyyy') " + //fecha final     
				     " and apd.sequencediag = 1 " +
				     " and apd.type = 'CF' " +
				     " and apd.isactive = 'Y' " +
				     " and ed.value not in (select dtd.code from exme_diagnosistypedet as dtd" +
				     " Inner Join Exme_DiagnosisVersion dv on (dv.exme_diagnosisVersion_id = " +
				     " dtd.exme_diagnosisVersion_Id AND dv.Stage ='1') " +
				     " where dv.exme_diagnosistype_id = ? and dv.isactive = 'Y' and dtd.IsActive='Y' ) " +
				" ) SBQ"); //10001001 Mental Type
			
			List<Object> lista = ejecutaSQL(this.trxName, sqlbuild.toString(),Env.getAD_Org_ID(ctx) , Env.getAD_Client_ID(ctx), this.observationType_ID, this.ini, this.fin, this.ini, this.fin, this.mentalDiagnosisType_ID);		
			if(lista != null && !lista.isEmpty()){
				denominador2 = ((Long) lista.get(0)).intValue();
				mediana2 = (BigDecimal) lista.get(1); 
				if(mediana2 == null){
					mediana2 = BigDecimal.ZERO;
				}
			}
			return lista;
	}
	
	/**
	 * Calcula numerador y denominador para la medida 1.3
	 * Pacientes con diagnosticos de desordenes mentales
	 * Mental Patients 
	 * @return 
	 */
	private List<Object> pacientesMentales(){
		
		StringBuilder sqlbuild = new StringBuilder(
				"select count(cuentas) as denominador, median(minutos) as numerador  " + 
				" from " +
				" (select cp.ad_org_id as cuentas, ((extract (epoch from (cp.departuredate - cp.dateordered)))/60)::integer as minutos " +
				" from exme_ctapac cp inner join exme_admitsource eas on eas.exme_admitsource_id = cp.exme_admitsource_id " +
				                    " inner join exme_tipopaciente tp on tp.exme_tipopaciente_id = cp.exme_tipopaciente_id " +
				                    " inner join exme_actpaciente ap inner join exme_actpacientediag apd inner join exme_diagnostico ed " + 
				                    												" on ed.exme_diagnostico_id = apd.exme_diagnostico_id " +
				                                                   " on apd.exme_actpaciente_id = ap.exme_actpaciente_id  " +
				                    " on ap.exme_ctapac_id = cp.exme_ctapac_id " +
				" where cp.ad_org_id = ? " + //10001017
				     " and cp.ad_client_id = ? " + //10001000
				     " and cp.exme_admitsource_id = (select exme_admitsource_id from exme_admitsource where claimcode = '7' and isactive = 'Y' ) " + // 1000006--- 'ER'
				     " and cp.exme_tipopaciente_id = ? " +// 10001007--- Inpatient    
				     " and cp.dateordered >= to_date(? ,'dd/MM/yyyy') " + //fecha inicial
				     " and cp.dateordered <= to_date(? ,'dd/MM/yyyy') " + //fecha final
				     " and cp.departuredate >= to_date(? ,'dd/MM/yyyy') " + //fecha inicial
				     " and cp.departuredate <= to_date(? ,'dd/MM/yyyy') " + //fecha final     
				     " and apd.sequencediag = 1 " +
				     " and apd.type = 'CF' " +
				     " and apd.isactive = 'Y' " +
				     " and ed.value in (select dtd.code from exme_diagnosistypedet as dtd" +
				     " inner join exme_diagnosisVersion dv on (dv.exme_diagnosisVersion_id = dtd.exme_diagnosisVersion_ID AND dv.stage = '1') " +
				     " where dv.exme_diagnosistype_id = ? and dv.isactive = 'Y' and dtd.IsActive='Y') " + 
				" ) SBQ"); //10001001 Mental Type
			
			List<Object> lista = ejecutaSQL(this.trxName, sqlbuild.toString(),Env.getAD_Org_ID(ctx) , Env.getAD_Client_ID(ctx), this.inpatientType_ID, this.ini, this.fin, this.ini, this.fin, this.mentalDiagnosisType_ID);		
			if(lista != null && !lista.isEmpty()){
				denominador3 = ((Long) lista.get(0)).intValue();
				mediana3 = (BigDecimal) lista.get(1); 
				if(mediana3 == null){
					mediana3 = BigDecimal.ZERO;
				}
			}				
			return lista;
	}
	
	public static List<Object> testEjecutaQueryNum() {
		final GeneraArchivoNQF497 bean = new GeneraArchivoNQF497(null, null, Env.getCtx(), null, 0, 0);
		return bean.executaQueryNumDen1();
	}
	public static List<Object> testPacientesObs() {
		final GeneraArchivoNQF497 bean = new GeneraArchivoNQF497(null, null, Env.getCtx(), null, 0, 0);
		return bean.pacientesObs();
	}
	public static List<Object> testPacientesMentales() {
		final GeneraArchivoNQF497 bean = new GeneraArchivoNQF497(null, null, Env.getCtx(), null, 0, 0);
		return bean.pacientesMentales();
	}
}
