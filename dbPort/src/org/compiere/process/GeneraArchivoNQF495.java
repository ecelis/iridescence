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
 * Genera xml para NQF 0495
 * Emergency Department (ED)-1
 * @author Rosy Velazquez
 *
 */

//TODO Cambiar dateordered por departuredate en consultas

public class GeneraArchivoNQF495{

	public GeneraArchivoNQF495(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
	}

	private static CLogger log = CLogger.getCLogger(GeneraArchivoNQF495.class);

	private Properties ctx;
	private String trxName;

	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	private String ini = "";
	private String fin = "";

	private String optionRegistry="";
	private String version = "";

	private int inpatientType_ID = 0;
	private int mentalDiagnosisType_ID = 0;
	private int observationType_ID = 0;

	private int denominador1=0; //numero de pacientes
	private int denominador2=0;
	private int denominador3=0;

	private final static String PQRI_NUMBER = "0495";

	private String archivo = "NQF_0495";
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
		fechaIni = param.getFecha_Ini_Urgencia();
		fechaFin = param.getFecha_Fin_Urgencia();

		//estacionUrgencias_ID = param.getEXME_EstServ_Urgencias_ID(); // Es el admit Source ER

		inpatientType_ID = param.getEXME_EstServ_Hosp_ID(); // Patient Type = Inpatient
		mentalDiagnosisType_ID = param.getMentalPatient();
		observationType_ID = param.getObsPatient();

		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);

		executaQueryNumDen1(); //Asignar numerador y denominador 1.1
		pacientesObs(); //Asignar numerador y denominador 1.2
		pacientesMentales(); //Asignar numerador y denominador 1.3

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

		String sqlbuild =
			" select count(cuentas) as denominador, median(minutos) as numerador \n" +
			" from " +
			" (select cp.ad_org_id as cuentas, \n" + //((cp.departuredate - pd.arrivaldate) * 24 * 60) as minutos " +
			" ((extract (epoch from (cp.departuredate - pd.arrivaldate)))/60)::integer as minutos \n" +
			"from exme_ctapac cp inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id \n" +
								" inner join exme_admitsource eas on eas.exme_admitsource_id = cp.exme_admitsource_id \n" + //duda
			                    " inner join exme_tipopaciente tp on tp.exme_tipopaciente_id = cp.exme_tipopaciente_id \n" +
			                    " inner join exme_actpaciente ap on ap.exme_ctapac_id = cp.exme_ctapac_id \n" +
			                    " inner join exme_actpacientediag apd on apd.exme_actpaciente_id = ap.exme_actpaciente_id  \n	" +
			                    " inner join exme_diagnostico ed on ed.exme_diagnostico_id = apd.exme_diagnostico_id \n" +
			                    												
			                                                   	                                                   			
			" where cp.ad_org_id = ? \n" + //10001017
			     " and cp.ad_client_id = ? \n" + //10001000
			     " and cp.exme_admitsource_id = (select exme_admitsource_id from exme_admitsource where claimcode = '7' and isactive = 'Y' ) \n" + // 1000006--- 'ER'
			     " and cp.exme_tipopaciente_id = ? \n" +// 10001007--- Inpatient
			     " and cp.departuredate >= to_date(? ,'dd/MM/yyyy') \n" + //fecha inicial
			     " and cp.departuredate <= to_date(? ,'dd/MM/yyyy') \n" + //fecha final
			     " and pd.arrivaldate >= to_date(? ,'dd/MM/yyyy') \n" + //fecha inicial
			     " and pd.arrivaldate <= to_date(? ,'dd/MM/yyyy') \n" + //fecha final
			     " and apd.sequencediag = 1 \n" +
			     " and apd.type = 'CF' \n" +
			     " and apd.isactive = 'Y' \n" +
			     " and ed.value not in (select dtd.code from exme_diagnosistypedet dtd" +
			     " inner join exme_diagnosisversion dv on dv.exme_diagnosisversion_id = dtd.exme_diagnosisversion_id" + //Cambios
			     " where dv.exme_diagnosistype_id = ? AND dv.stage = '1' AND dtd.isActive= 'Y' AND dv.isActive= 'Y') \n" +
			  " ) SBQ"; //10001001 Mental Type
		 	
		
		
		List<Object> lista =
				ejecutaSQL(
						this.trxName,
						sqlbuild,
						Env.getAD_Org_ID(ctx) ,
						Env.getAD_Client_ID(ctx),
						this.inpatientType_ID,
						this.ini,
						this.fin,
						this.ini,
						this.fin,
						this.mentalDiagnosisType_ID
				);
		if(lista != null && !lista.isEmpty()){
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
		//BigDecimal[] minutos;
		List<Object> listaMinutos = new ArrayList<Object>();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

    	try	{

    		log.severe("SQL : " + sql);

    		pstmt = DB.prepareStatement(sql, trxName);
    		DB.setParameters(pstmt, params);
    		rs = pstmt.executeQuery();

    		while (rs.next()) {
    			listaMinutos.add(rs.getObject(1));
    			listaMinutos.add(rs.getObject(2));
    		}
    	} catch (SQLException e) {
    		listaMinutos=null;
    		log.severe("getSQLValueEx " + sql); //entra
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

		String sqlbuild =
				" select count(cuentas) as denominador, median(minutos) as numerador \n" +
				" from \n" +
				" (select cp.ad_org_id as cuentas, \n"+
				" ((extract (epoch from (cp.departuredate - pd.arrivaldate)))/60)::integer as minutos \n" +
				" from exme_ctapac cp inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id \n" +
									"\t inner join exme_admitsource eas on eas.exme_admitsource_id = cp.exme_admitsource_id \n" +
				                    "\t inner join exme_tipopaciente tp on tp.exme_tipopaciente_id = cp.exme_tipopaciente_id \n" +
				                    "\t inner join exme_actpaciente ap on ap.exme_ctapac_id = cp.exme_ctapac_id \n" +
				                    "\t inner join exme_actpacientediag apd on apd.exme_actpaciente_id = ap.exme_actpaciente_id \n" +
				                    "\t inner join exme_diagnostico ed on ed.exme_diagnostico_id = apd.exme_diagnostico_id \n" + 


				" where cp.ad_org_id = ? \n" + //10001017
				     "\t and cp.ad_client_id = ? \n" + //10001000
				     "\t and cp.exme_admitsource_id = (select exme_admitsource_id from exme_admitsource where claimcode = '7' and isactive = 'Y' ) \n" + // 1000006--- 'ER'
				     "\t and cp.exme_tipopaciente_id = ? \n" +// --- Observation
				     "\t and cp.departuredate >= to_date(? ,'dd/MM/yyyy') \n" + //fecha inicial
				     "\t and cp.departuredate <= to_date(? ,'dd/MM/yyyy') \n" + //fecha final
				     "\t and pd.arrivaldate >= to_date(? ,'dd/MM/yyyy') \n" + //fecha inicial
				     "\t and pd.arrivaldate <= to_date(? ,'dd/MM/yyyy') \n" + //fecha final
				     "\t and apd.sequencediag = 1 \n" +
				     "\t and apd.type = 'CF' \n" +
				     "\t and apd.isactive = 'Y' \n" +
				     "\t and ed.value not in (select dtd.code from exme_diagnosistypedet dtd" + //Cambios
				     " inner join exme_diagnosisversion dv on dv.exme_diagnosisversion_id = dtd.exme_diagnosisversion_id " +
				     " where dv.exme_diagnosistype_id = ? AND dv.stage = '1' AND dtd.isActive= 'Y' AND dv.isActive= 'Y')\n" +
				 " ) SBQ"; //10001001 Mental Type

		
		
			List<Object> lista =
					ejecutaSQL(
							this.trxName,
							sqlbuild,Env.getAD_Org_ID(ctx) ,
							Env.getAD_Client_ID(ctx),
							this.observationType_ID,
							this.ini,
							this.fin,
							this.ini,
							this.fin,
							this.mentalDiagnosisType_ID
					);
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
				"select count(cuentas) as denominador, median(minutos) as numerador \n" +
				" from \n" +
				"\t (select cp.ad_org_id as cuentas, \n" +
				"\t ((extract (epoch from (cp.departuredate - pd.arrivaldate)))/60)::integer as minutos \n" +
				"\t\tfrom exme_ctapac cp inner join exme_ctapacdatos pd on pd.exme_ctapac_id = cp.exme_ctapac_id \n" +
									"\t\t inner join exme_admitsource eas on eas.exme_admitsource_id = cp.exme_admitsource_id \n" +
				                    "\t\t inner join exme_tipopaciente tp on tp.exme_tipopaciente_id = cp.exme_tipopaciente_id \n" +
				                    "\t\t inner join exme_actpaciente ap on ap.exme_ctapac_id = cp.exme_ctapac_id \n" +
				                    "\t\t inner join exme_actpacientediag apd on apd.exme_actpaciente_id = ap.exme_actpaciente_id \n" +		
				                    "\t\t inner join exme_diagnostico ed on ed.exme_diagnostico_id = apd.exme_diagnostico_id \n" + 				                    												
                                   
				                                                					
					 "\t\t where cp.ad_org_id = ? \n" + //10001017
				     "\t\t and cp.ad_client_id = ? \n" + //10001000
				     "\t\t and cp.exme_admitsource_id = (select exme_admitsource_id from exme_admitsource where claimcode = '7' and isactive = 'Y') \n" + // 1000006--- 'ER'
				     "\t\t and cp.exme_tipopaciente_id = ? \n" +// 10001007--- Inpatient
				     "\t\t and cp.departuredate >= to_date(? ,'dd/MM/yyyy') \n" + //fecha inicial
				     "\t\t and cp.departuredate <= to_date(? ,'dd/MM/yyyy') \n" + //fecha final
				     "\t\t and pd.arrivaldate >= to_date(? ,'dd/MM/yyyy') \n" + //fecha inicial
				     "\t\t and pd.arrivaldate <= to_date(? ,'dd/MM/yyyy') \n" + //fecha final
				     "\t\t and apd.sequencediag = 1 \n" +
				     "\t\t and apd.type = 'CF' \n" +
				     "\t\t and apd.isactive = 'Y' \n" +
				     "\t\t and ed.value in (select dtd.code from exme_diagnosistypedet dtd" +
				     " inner join exme_diagnosisversion dv on dv.exme_diagnosisversion_id = dtd.exme_diagnosisversion_id" +
				     " where dv.exme_diagnosistype_id = ? AND dv.stage = '1' AND dtd.isActive= 'Y' AND dv.isActive= 'Y')\n" +
				     "\t\t ) SBQ"); //10001001 Mental Type

		
		
			List<Object> lista =
					ejecutaSQL(
							this.trxName,
							sqlbuild.toString(),
							Env.getAD_Org_ID(ctx) ,
							Env.getAD_Client_ID(ctx),
							this.inpatientType_ID,
							this.ini,
							this.fin,
							this.ini,
							this.fin,
							this.mentalDiagnosisType_ID
					);

			if(lista != null && !lista.isEmpty()){
				denominador3 = ((Long) lista.get(0)).intValue();
				mediana3 = (BigDecimal) lista.get(1);
				if(mediana3 == null){
					mediana3 = BigDecimal.ZERO;
				}
			}
			return lista;
	}
	
	/**
	 * Metodo para validar que la consulta este funcionando (JUnit)
	 * @param numerador indica si se genera el numerador o el denominador
	 * @return indica el nuemro de pacientes obtenidos segun la metrica
	 */
	public static List<Object> testExecutaQueryNumDen1() {
		final GeneraArchivoNQF495 bean = new GeneraArchivoNQF495(null, null, Env.getCtx(), null, 0, 0);
		return bean.executaQueryNumDen1();
		
	}
	public static List<Object> testpacientesObs() {
		final GeneraArchivoNQF495 bean = new GeneraArchivoNQF495(null, null, Env.getCtx(), null, 0, 0);
		return bean.pacientesObs();
	}		

	public static List<Object> testpacientesMentales() {
		final GeneraArchivoNQF495 bean = new GeneraArchivoNQF495(null, null, Env.getCtx(), null, 0, 0);
		return bean.pacientesMentales();
}
}

