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
 * Genera xml para NQF 0038
 * Childhood Immunization Status
 * @author Rosy Velazquez
 *
 */
public class GeneraVacunasMeasure extends SvrProcess{

	private int age = 0; //age from			
	private int vacunasTotal = 0;
	private int denominador = 0;
	
	//Vacunas IDs
	private int diphtheria = 0;
	private int tetanus = 0;
	private int acellular = 0;
	private int polio = 0;
	private int measles = 0;
	private int mumps = 0;
	private int rubella = 0;
	private int influenzaB = 0;
	private int hepatitisB = 0;
	private int chickenPox = 0; 
	private int pneumococcal = 0;
	private int hepatitisA = 0;
	private int rotavirus = 0;
	private int influenza = 0;
	
	//Numero de Vacunas
	private int diphtheriaNum = 0;
	private int tetanusNum = 0;
	private int acellularNum = 0;
	private int polioNum = 0;
	private int measlesNum = 0;
	private int mumpsNum = 0;
	private int rubellaNum = 0;
	private int influenzaBNum = 0;
	private int hepatitisBNum = 0;
	private int chickenPoxNum = 0; 
	private int pneumococcalNum = 0;
	private int hepatitisANum = 0;
	private int rotavirusNum = 0; //2 o 3 :S
	private int influenzaNum = 0;
	
	//Vacunas Count
	private int diphtheriaCount = 0;
	private int tetanusCount = 0;
	private int acellularCount = 0;
	private int polioCount = 0;
	private int measlesCount = 0;
	private int mumpsCount = 0;
	private int rubellaCount = 0;
	private int influenzaBCount = 0;
	private int hepatitisBCount = 0;
	private int chickenPoxCount = 0; 
	private int pneumococcalCount = 0;
	private int hepatitisACount = 0;
	private int rotavirusCount = 0;
	private int influenzaCount = 0;
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	private String optionRegistry="";
	private String version = "";	
		
	//private final static String PQRI_NUMBER_HYPERTENSION = "0038"; //Pendiente num ---NQF 0038	
	
	private String archivo = "NQF_0038";
	private String measureGroup = "X"; //Sin grupo
	
	Calendar hoy = new GregorianCalendar();
	String hoyStr = "";
	
	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	String iniStr = "";
	//Envio para armar xml	
		
	protected String doIt() throws Exception {
		String msj = "";
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(getCtx(), get_TrxName());
		if(param == null){
			msj = Msg.getMsg(this.getCtx(), "NoParam"); 
			return msj;
		}
		//Cargar numero de dosis de vacunas de parametros
		diphtheriaNum = param.getNumDiphteria();
		tetanusNum = param.getNumTetanus();
		acellularNum = param.getNumAcellular();
		polioNum = param.getNumPolio();
		measlesNum = param.getNumMeasles();
		mumpsNum = param.getNumMumps();
		rubellaNum = param.getNumRubella();
		influenzaBNum = param.getNumInfluenzaB();
		hepatitisBNum = param.getNumHepatitisB();
		chickenPoxNum = param.getNumChickenPox(); 
		pneumococcalNum = param.getNumPneumococcal();
		hepatitisANum = param.getNumHepatitisA();
		rotavirusNum = param.getNumRotavirus(); //2 o 3 :S
		influenzaNum = param.getNumFlu();
		//Cargar ids vacunas de parametros		
		diphtheria = param.getEXME_VacunaDiphteria_ID();
		tetanus = param.getEXME_VacunaTetanus_ID();
		acellular = param.getEXME_VacunaAcellular_ID();
		polio = param.getEXME_VacunaPolio_ID();
		measles = param.getEXME_VacunaMeasles_ID();
		mumps = param.getEXME_VacunaMumps_ID();
		rubella = param.getEXME_VacunaRubella_ID();
		influenzaB = param.getEXME_VacunaInfluenzaB_ID();
		hepatitisB = param.getEXME_VacunaHepatitisB_ID();
		chickenPox = param.getEXME_VacunaChickenPox_ID(); 
		pneumococcal = param.getEXME_VacunaPneumococcal_ID();
		hepatitisA = param.getEXME_VacunaHepatitisA_ID();
		rotavirus = param.getEXME_VacunaRotavirus_ID();
		influenza = param.getEXME_VacunaFlu_ID();
		//Cargar edad de parametros
		age = param.getAge_Childhood();
		
		hoy = Calendar.getInstance();		
		hoyStr = f.format(hoy.getTimeInMillis());
		
		asignaPeriodoMedida();					
		
		//Calcular porcentajes individuales
		calculaPorcentajes();
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(getCtx());
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(pqri("Diphtheria", this.diphtheriaCount));				
		pqris.add(pqri("Tetanus", this.tetanusCount));
		pqris.add(pqri("Acellular", this.acellularCount));
		pqris.add(pqri("Polio", this.polioCount));		
		pqris.add(pqri("Measles", this.measlesCount));
		pqris.add(pqri("Mumps", this.mumpsCount));
		pqris.add(pqri("Rubella", this.rubellaCount));
		pqris.add(pqri("InfluenzaB", this.influenzaBCount));
		pqris.add(pqri("HepatitisB", this.hepatitisBCount));
		pqris.add(pqri("ChickenPox", this.chickenPoxCount));		
		pqris.add(pqri("Pneumococcal", this.pneumococcalCount));
		pqris.add(pqri("HepatitisA", this.hepatitisACount));
		pqris.add(pqri("Rotavirus", this.rotavirusCount));	
		pqris.add(pqri("Influenza", this.influenzaCount));
		pqris.add(pqri("Global", this.vacunasTotal));
		
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
	 * Valida los periodos a tomar en cuenta para la medida
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
		f.format(fechaIni);
	}
	
	/**
	 * Calcula el numerador para cada vacuna
	 * @return pacientes
	 */
	protected int executaQueryNumerator(int vacunaID, int numVacunas){
		//dos anios cumplidos antes del periodo inicial.
		int pacientes=0;					
		
		String sql = "SELECT COUNT(*) " + 
						" FROM " +
						" ( " +
						    " SELECT hv.exme_paciente_id " +
						    " FROM EXME_Hist_Vacuna hv inner join exme_paciente ep on ep.exme_paciente_id = hv.exme_paciente_id " +
						    " WHERE hv.exme_vacuna_id = " + vacunaID +
						          " AND "+DB.truncDate("exme_edad_anomes(to_char(ep.fechanac,'dd/MM/yyyy'), '" + iniStr + "')")+" = " + age +
						    " GROUP BY hv.exme_paciente_id " +
						    " having count(hv.exme_paciente_id) >= " + numVacunas +
						" ) ";
		
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
	
	protected void calculaPorcentajes(){				
		
		diphtheriaCount = executaQueryNumerator(diphtheria, this.diphtheriaNum);
		tetanusCount = executaQueryNumerator(tetanus, this.tetanusNum);
		acellularCount = executaQueryNumerator(acellular, this.acellularNum);
		polioCount = executaQueryNumerator(polio, this.polioNum);
		measlesCount = executaQueryNumerator(measles, this.measlesNum);
		mumpsCount = executaQueryNumerator(mumps, this.mumpsNum);
		rubellaCount = executaQueryNumerator(rubella, this.rubellaNum);
		influenzaBCount = executaQueryNumerator(influenzaB, this.influenzaBNum);
		hepatitisBCount = executaQueryNumerator(hepatitisB, this.hepatitisBNum); 
		chickenPoxCount = executaQueryNumerator(chickenPox, this.chickenPoxNum);
		pneumococcalCount = executaQueryNumerator(pneumococcal, this.pneumococcalNum);
		hepatitisACount = executaQueryNumerator(hepatitisA, this.hepatitisANum);
		rotavirusCount = executaQueryNumerator(rotavirus, this.rotavirusNum);
		influenzaCount = executaQueryNumerator(influenza, this.influenzaNum);
		
		vacunasTotal = diphtheriaCount + tetanusCount + acellularCount + polioCount + measlesCount + mumpsCount + rubellaCount + 
			influenzaBCount + hepatitisBCount + chickenPoxCount + pneumococcalCount + hepatitisACount + rotavirusCount + influenzaCount;
		
		denominador = executaQueryDenominator();			
		
	}
	
	/**
	 * Arma el pqri 
	 * @return pqri
	 */
	protected String[] pqri(String pqriNumber, int numerador){
		String[] pqri = new String[5];
		pqri[0] = pqriNumber; //pqri_number		
		pqri[1] = String.valueOf(numerador); //numerador				
		pqri[2] = String.valueOf(denominador); //denominador
		pqri[3] = "0"; // exclusions
		pqri[4] = "0"; //not met
		
		return pqri;		
	}	
}
