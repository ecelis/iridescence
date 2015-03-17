package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MEXMEParamMetricas;
import org.compiere.util.DB;
import org.compiere.util.Msg;

/**
 * Genera xml para NQF 0024
 * Weight Assessment and Counseling for Children and Asolescents
 *   
 * @author Rosy Velazquez
 */
public class GeneraWeightMeasure extends SvrProcess{

	private int ageFrom = 0; //age from					
	private int ageTo = 0; //age to
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER_WEIGHT = "NQF 0024"; //Pendiente num ---NQF 0028		
	
	private String archivo = "NQF_0024";
	private String measureGroup = "X"; //Sin grupo
	
	Calendar hoy = new GregorianCalendar();
	String hoyStr = "";
	
	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");	
	SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	protected String doIt() throws Exception {
		
		String msj = "";		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(getCtx(), get_TrxName());
		if(param == null){
			msj = Msg.getMsg(this.getCtx(), "NoParam"); 
			return msj;
		}
		
		//Asignar parametros de tabla
		ageFrom = param.getAge_Weight();
		ageTo = param.getAge_WeightTo();
			
		hoy = Calendar.getInstance();	
		hoyStr = f.format(hoy.getTimeInMillis());
		
		asignaPeriodoMedida();					
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(getCtx());
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(pqri());				
				
		boolean generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version);
		
		if(generado){
			msj = Msg.getMsg(this.getCtx(), "ArchivoGenerado");
			return msj;
		}else		
			msj = Msg.getMsg(this.getCtx(), "ArchivoNoGenerado");
			return msj;
	}
	
	/**
	 * Recibe parametros
	 */
	protected void prepare() {
		//Recibir parametros del proceso
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;							
			else if (name.equals("Date"))
			    para[i].getParameter().toString();	
			else if (name.equals("OptionRegistry"))
				optionRegistry = para[i].getParameter().toString();
			else if (name.equals("Version"))
				version = para[i].getParameter().toString();
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}		
	}

	/**
	 * Valida los peridos a tomar en cuenta para la medida
	 * 24 meses atras de la fecha en que se genera la medida
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
		f.format(fechaIni);	
	}
	
	/**
	 * Calcula el numerador para la medida 
	 * @return pacientes
	 */
	protected int executaQueryNumerator(){
		
		int pacientes=0;
		
		String ini = format.format(fechaIni);
		String fin = format.format(fechaFin);				
		
		String sql = "SELECT COUNT(DISTINCT hv.exme_paciente_id) " +
						" FROM EXME_METRICAS_IMC hv inner join exme_paciente ep on ep.exme_paciente_id = hv.exme_paciente_id " +
						" WHERE hv.FECHA_IMC BETWEEN to_date('" + ini + "','MM/dd/yyyy') AND to_date('" + fin + "', 'MM/dd/yyyy') " +						
						  "    AND "+DB.truncDate("exme_edad_anomes(to_char(ep.fechanac,'dd/MM/yyyy'), '" + hoyStr + "')")+" BETWEEN " + ageFrom + " AND " + ageTo;
		
		pacientes = executaSql(sql);			
			
		return pacientes;
	}
	
	/**
	 * Calcula el denominador para la medida 
	 * @return pacientes
	 */
	protected int executaQueryDenominator(){
		
		int pacientes = 0;
		String sql = "SELECT count(exme_paciente_id) " +
					" FROM EXME_Paciente ep " +
					" WHERE "+DB.truncDate("exme_edad_anomes(to_char(ep.fechanac,'dd/MM/yyyy'), '" + hoyStr + "')")+" BETWEEN " + ageFrom + " AND " + ageTo +  
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
	 * Arma el pqri de la medida
	 * @return pqri
	 */
	protected String[] pqri(){
		String[] pqri = new String[5];
		pqri[0] = PQRI_NUMBER_WEIGHT; //pqri_number
		int numerador = executaQueryNumerator();
		pqri[1] = String.valueOf(numerador); //numerador
		
		int denominador = executaQueryDenominator();
		pqri[2] = String.valueOf(denominador); //denominador
		pqri[3] = "0"; // exclusions
		pqri[4] = "0"; //not met
		
		return pqri;		
	}	
}
