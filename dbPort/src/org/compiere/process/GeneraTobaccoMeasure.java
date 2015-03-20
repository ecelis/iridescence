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
 * Genera xml para NQF 0028
 * Preventive care and screening measure pair: 
 * a) tobacco use assessment 
 * b) tobacco cessation intervention 
 * @author Rosy Velazquez
 *
 */
public class GeneraTobaccoMeasure extends SvrProcess{

	private int age = 0; //age from					
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER_TOBACCO = "NQF 0028"; //Pendiente num ---NQF 0028		
	
	private String archivo = "NQF_0028";
	private String measureGroup = "X"; //Sin grupo
	
	Calendar hoy = new GregorianCalendar();
	String hoyStr = "";
	
	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	//Envio para armar xml	
		
	protected String doIt() throws Exception {
		
		String msj = "";		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(getCtx(), get_TrxName());
		if(param == null){
			msj = Msg.getMsg(this.getCtx(), "NoParam"); 
			return msj;
		}
		
		//Asignar parametros
		age = param.getAge_Tobacco();
		
		hoy = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		hoyStr = f.format(hoy.getTimeInMillis());
		
		asignaPeriodoMedida();					
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(getCtx());
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(pqriA());				
				
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
		fechaIni = menos24Meses();
		fechaFin = new Date(hoy.getTimeInMillis());		
	}
	
	/**
	 * Calcula el numerador para la medida 
	 * @return pacientes
	 */
	protected int executaQueryNumeratorA(){
		
		int pacientes=0;
		//24 meses antes a partir de la fecha de la medida.
		String ini = format.format(menos24Meses());
		String fin = format.format(new Date(hoy.getTimeInMillis()));				
		
		String sql = "SELECT COUNT(DISTINCT MF.exme_paciente_id) " +
				" FROM EXME_Metricas_Fumador MF inner join exme_paciente ep on ep.exme_paciente_id = mf.exme_paciente_id " +
				" WHERE MF.observaciones IS NOT NULL " +
				      " AND "+DB.truncDate("exme_edad_anomes(to_char(ep.fechanac,'dd/MM/yyyy'), '" + hoyStr + "')")+" >= " + this.age +
				      " AND MF.created BETWEEN to_date('" + ini + "','MM/dd/yyyy') AND to_date('" + fin + "', 'MM/dd/yyyy') ";
		
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
					" WHERE "+DB.truncDate("exme_edad_anomes(to_char(ep.fechanac,'dd/MM/yyyy'), '" + hoyStr + "')")+" >= " + this.age + 
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
	 * @return pqri
	 */
	protected String[] pqriA(){
		String[] pqri = new String[5];
		pqri[0] = PQRI_NUMBER_TOBACCO; //pqri_number
		int numerador = executaQueryNumeratorA();
		pqri[1] = String.valueOf(numerador); //numerador
		
		int denominador = executaQueryDenominatorA();
		pqri[2] = String.valueOf(denominador); //denominador
		pqri[3] = "0"; // exclusions
		pqri[4] = "0"; //not met
		
		return pqri;		
	}	
}
