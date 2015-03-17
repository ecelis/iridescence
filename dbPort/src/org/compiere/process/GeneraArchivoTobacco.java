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
import org.compiere.util.DB;

/**
 * Genera xml para NQF 0028
 * Preventive care and screening measure pair: 
 * a) tobacco use assessment 
 * b) tobacco cessation intervention 
 * @author Rosy Velazquez
 *
 */
public class GeneraArchivoTobacco{

	public GeneraArchivoTobacco(String version, String optionRegistry, Properties ctx, String trxName){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
	}
	
	private int age = 0; //age from					
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie	
	private String strIni = "";
	private String strFin = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER_TOBACCO = "0028"; //Pendiente num ---NQF 0028		
	
	private String archivo = "NQF_0028";
	private String measureGroup = "X"; //Sin grupo
	private Properties ctx;
	private String trxName;
	
	private Calendar hoy = new GregorianCalendar();
	
	//private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	//Envio para armar xml	
		
	public File genera() throws Exception {
				
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName);		
		
		//Asignar parametros
		age = param.getAge_Tobacco();
		this.fechaIni = param.getFecha_Ini_Tabaco();
		this.fechaFin = param.getFecha_Fin_Tabaco();
		
		this.strIni = f.format(fechaIni);
		this.strFin = f.format(fechaFin);
		
		hoy = Calendar.getInstance();		
		
		//asignaPeriodoMedida(); //Vienen por parametro las fechas!					
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(pqriA());
		pqris.add(pqriB());
				
		File file = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1");
		
		/*if(generado){
			msj = Msg.getMsg(this.getCtx(), "ArchivoGenerado");
			return msj;
		}else		
			msj = Msg.getMsg(this.getCtx(), "ArchivoNoGenerado");
			return msj;
			*/
		return file;
	}	

	/**
	 * Valida los peridos a tomar en cuenta para la medida
	 * 24 meses atras de la fecha en que se genera la medida
	 */
	protected void asignaPeriodoMedida(){		
		fechaIni = menos24Meses();
		fechaFin = new Date(hoy.getTimeInMillis());		
	}
	
	/**
	 * Calcula el numerador para la medida 
	 * @return pacientes
	 */
	protected int executaQueryNumeratorA(){
		
		int pacientes=0;						
		
		String sql = "SELECT COUNT(DISTINCT MF.exme_paciente_id) " +
				" FROM EXME_Metricas_Fumador MF inner join exme_paciente ep on ep.exme_paciente_id = mf.exme_paciente_id " +
				" WHERE MF.observaciones IS NOT NULL " +
				      " AND "+DB.truncDate("exme_edad_anomes(to_char(ep.fechanac,'dd/MM/yyyy'), '" + strIni + "')")+" >= " + this.age +
				      " AND MF.created BETWEEN to_date('" + strIni + "','dd/MM/yyyy') AND to_date('" + strFin + "', 'dd/MM/yyyy') ";
		
		pacientes = executaSql(sql);			
			
		return pacientes;
	}
	
	/**
	 * Calcula el denominador para la medida 
	 * @return
	 */
	protected int executaQueryDenominatorA(){
		
		int pacientes = 0;
		String sql = "SELECT count(exme_paciente_id) " +
					" FROM EXME_Paciente ep " +
					" WHERE "+DB.truncDate("exme_edad_anomes(to_char(ep.fechanac,'dd/MM/yyyy'), '" + strIni + "')")+" >= " + this.age + 
					"       AND ep.isactive = 'Y'";
		
		pacientes = executaSql(sql);			
		
		return pacientes;
	}
	
	/**
	 * Calcula el numerador para la medida 
	 * @return pacientes
	 */
	protected int executaQueryNumeratorB(){
		
		int pacientes=0;					
		
		String sql = "SELECT COUNT(DISTINCT MF.exme_paciente_id) " +
				" FROM EXME_Metricas_Fumador MF inner join exme_paciente ep on ep.exme_paciente_id = mf.exme_paciente_id " +
				" WHERE MF.observaciones IS NOT NULL " +
				      " AND "+DB.truncDate("exme_edad_anomes(to_char(ep.fechanac,'dd/MM/yyyy'), '" + strIni + "')")+" >= " + this.age +
				      " AND MF.created BETWEEN to_date('" + strIni + "','dd/MM/yyyy') AND to_date('" + strFin + "', 'dd/MM/yyyy') ";
		
		pacientes = executaSql(sql);			
			
		return pacientes;
	}
	
	/**
	 * Calcula el denominador para la medida 
	 * @return
	 */
	protected int executaQueryDenominatorB(){
		
		int pacientes = 0;
		String sql = "SELECT count(exme_paciente_id) " +
					" FROM EXME_Paciente ep " +
					" WHERE "+DB.truncDate("exme_edad_anomes(to_char(ep.fechanac,'dd/MM/yyyy'), '" + strIni + "')")+" >= " + this.age + 
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
	 * Arma el pqri de la medida
	 * a) tobacco use assessment
	 * @return pqri
	 */
	protected String[] pqriA(){
		String[] pqri = new String[5];
		pqri[0] = PQRI_NUMBER_TOBACCO + "_a"; //pqri_number
		int numerador = executaQueryNumeratorA();
		pqri[1] = String.valueOf(numerador); //numerador
		
		int denominador = executaQueryDenominatorA();
		pqri[2] = String.valueOf(denominador); //denominador
		pqri[3] = "0"; // exclusions
		pqri[4] = "0"; //not met
		
		return pqri;		
	}	
	
	/**
	 * Arma el pqri de la medida
	 * b) tobacco cessation intervention 
	 * @return pqri
	 */
	protected String[] pqriB(){
		String[] pqri = new String[5];
		pqri[0] = PQRI_NUMBER_TOBACCO + "_b"; //pqri_number
		int numerador = executaQueryNumeratorB();
		pqri[1] = String.valueOf(numerador); //numerador
		
		int denominador = executaQueryDenominatorB();
		pqri[2] = String.valueOf(denominador); //denominador
		pqri[3] = "0"; // exclusions
		pqri[4] = "0"; //not met
		
		return pqri;		
	}	
}
