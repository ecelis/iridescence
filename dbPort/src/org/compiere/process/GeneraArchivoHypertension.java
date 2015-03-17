package org.compiere.process;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMEParamMetricas;
import org.compiere.model.X_EXME_Diagnostico;
import org.compiere.util.DB;
import org.compiere.util.Msg;

/**
 * Genera xml para NQF 0013
 * Hypertension: Blood Pressure Measurement
 * @author Rosy Velazquez
 */

public class GeneraArchivoHypertension{
	
	public GeneraArchivoHypertension(String version, String optionRegistry, Properties ctx, String trxName){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
	}

	private int age = 0; //age from			
	private Properties ctx;
	private String trxName;
	//Signos vitales de presion arterial
	private int diastolic_id = 0;
	private int systolic_id = 0;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	private String iniStr = "";
	private String finStr = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER_HYPERTENSION = "0013"; //Pendiente num ---NQF 0013		
	
	private String archivo = "NQF_0013";
	private String measureGroup = "X"; //Sin grupo
	
	private int hypertensionIni = 0;
	private int hypertensionFin = 0;
	String cptHypertensionIni = "";
	String cptHypertensionFin = "";
	
	Calendar hoy = new GregorianCalendar();
	String hoyStr = "";
	
	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	public File genera() throws Exception {
		String msj = "";
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName);
		if(param == null){
			msj = Msg.getMsg(ctx, "NoParam"); 
			//return msj;
		}
		
		//Llenar parametros de la tabla
		age = param.getAge_Hypertension();
		systolic_id = param.getEXME_SignoVitalSystolic_ID();
		diastolic_id = param.getEXME_SignoVitalDiastolic_ID();
		
		hypertensionIni = param.getEXME_HypertensionIni_ID();
		hypertensionFin = param.getEXME_HypertensionFin_ID();
		
		X_EXME_Diagnostico diagIni = new X_EXME_Diagnostico(ctx, hypertensionIni, null);
		X_EXME_Diagnostico diagFin = new X_EXME_Diagnostico(ctx, hypertensionFin, null);
		
		cptHypertensionIni = diagIni.getCodOms();
		cptHypertensionFin = diagFin.getCodOms();
		
		hoy = Calendar.getInstance();
		hoyStr = f.format(hoy.getTimeInMillis());
		
		//asignaPeriodoMedida(); //de parametros
		this.fechaIni = param.getFecha_Ini_Hypertension();
		this.fechaFin = param.getFecha_Fin_Hypertension();
		
		//Para querie
		iniStr = f.format(fechaIni);
		finStr = f.format(fechaFin);
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(pqri());				
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1");		
		
		/*if(generado){
			msj = Msg.getMsg(this.getCtx(), "ArchivoGenerado");
			return msj;
		}else		
			msj = Msg.getMsg(this.getCtx(), "ArchivoNoGenerado");
			return msj;
			*/
		return generado;
	}
		
	/**
	 * Valida los peridos a tomar en cuenta para la medida
	 * 
	 */
	protected void asignaPeriodoMedida(){
		Calendar hoy = Calendar.getInstance();

		//1 year (January 1 - December 31)
		Calendar iniRango1 = new GregorianCalendar();
		iniRango1.set(hoy.get(Calendar.YEAR), Calendar.JANUARY, 1); //January 1, same year
		Calendar finRango1 = new GregorianCalendar();
		finRango1.set(hoy.get(Calendar.YEAR), Calendar.DECEMBER, 31); //December 31, same year
		
		fechaIni = new Date(iniRango1.getTimeInMillis());
		fechaFin = new Date(finRango1.getTimeInMillis());					
	}
	
	/**
	 * Calcula el numerador para la medida
	 * Años calculados a la fecha inicial de la medida 
	 * @return pacientes
	 */
	protected int executaQueryNumerator(){
		
		int pacientes=0;				
		String sql = "SELECT COUNT(DISTINCT EP.exme_paciente_id) " +
						" FROM exme_actpacienteind api INNER JOIN EXME_ACTPACIENTEINDH PH INNER JOIN exme_actpaciente AP INNER JOIN EXME_PACIENTE EP " + 
						                                                                                               " ON EP.EXME_PACIENTE_ID = AP.EXME_PACIENTE_ID " +
						                                                                " ON AP.exme_actpaciente_id = PH.exme_actpaciente_id " +
						                             " ON ph.exme_actpacienteindh_id= api.exme_actpacienteindh_id " +
						" WHERE (api.exme_diagnostico_id IN (select exme_diagnostico_id from exme_diagnostico where codoms >='" + cptHypertensionIni + "' and codoms <='" + cptHypertensionFin + "') " +
						        " OR api.exme_diagnostico2_id IN (select exme_diagnostico_id from exme_diagnostico where codoms >='" + cptHypertensionIni + "' and codoms <='" + cptHypertensionFin + "') " +
						        " OR api.exme_diagnostico3_id IN (select exme_diagnostico_id from exme_diagnostico where codoms >='" + cptHypertensionIni + "' and codoms <='" + cptHypertensionFin + "') " +
						     " ) " +
						      " AND "+DB.truncDate("exme_edad_anomes(to_char(ep.fechanac,'dd/MM/yyyy'), '" + this.iniStr + "')")+" >=  " + this.age +
						      " AND EP.exme_paciente_id in ( " +
						                                    " SELECT distinct ep.exme_paciente_id " +
						                                    " FROM exme_signovitaldet svd INNER JOIN exme_paciente ep on ep.exme_paciente_id = svd.exme_paciente_id " +
						                                                                " INNER JOIN exme_signovital sv on sv.exme_signovital_id = svd.exme_signovital_id " +
						                                    " WHERE sv.exme_signovital_id in (" + this.systolic_id + ", " + this.diastolic_id + ") " +
						                                          " AND "+DB.truncDate("exme_edad_anomes(to_char(ep.fechanac,'dd/MM/yyyy'), '" + iniStr + "')")+" >= " + this.age +
						                                    " )  " + 
						      " AND api.dateordered between to_date('" + this.iniStr + "', 'dd/MM/yyyy') and to_date('" + this.finStr + "', 'dd/MM/yyyy')";
		
		pacientes = executaSql(sql);			
			
		return pacientes;
	}
	
	/**
	 * Calcula el denominador para la medida 
	 * @return
	 */
	protected int executaQueryDenominator(){
		
		int pacientes = 0;
		String sql = "SELECT count(exme_paciente_id) " +
					" FROM EXME_Paciente ep " +
					" WHERE "+DB.truncDate("exme_edad_anomes(to_char(ep.fechanac,'dd/MM/yyyy'), '" + iniStr + "')")+" >= " + this.age + 
					"       AND ep.isactive = 'Y'";
		
		pacientes = executaSql(sql);			
		
		return pacientes;
	}
	
	/**
	 * Executa un query
	 * @param sql
	 * @return pacientes
	 */
	protected int executaSql(String sql){
		int pacientes = 0;
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, null);				
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				pacientes = rs.getInt(1);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//if (pstmt != null) pstmt.close();
			} catch (Exception e) {}
			pstmt = null;
		}	
		return pacientes;
	}
	
	/**
	 * Calcular veinticuatro meses atras a la fecha actual
	 * @return Date
	 */
	protected Date menos24Meses(){
		Calendar today = Calendar.getInstance();
		today.add(Calendar.MONTH, -24);
		
		return new Date(today.getTimeInMillis());
	}
	
	/**
	 * Arma el pqri para la medida
	 * @return pqri
	 */
	protected String[] pqri(){
		String[] pqri = new String[5];
		pqri[0] = PQRI_NUMBER_HYPERTENSION; //pqri_number o NQF
		int numerador = executaQueryNumerator();
		pqri[1] = String.valueOf(numerador); //numerador
		
		int denominador = executaQueryDenominator();
		pqri[2] = String.valueOf(denominador); //denominador
		pqri[3] = "0"; // exclusions
		pqri[4] = "0"; //not met
		
		return pqri;		
	}	
}
