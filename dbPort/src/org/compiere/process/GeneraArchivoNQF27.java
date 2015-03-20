package org.compiere.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMEParamMetricas;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0027
 * Smokers Tobacco users 
 * @author Rosy Velazquez
 */
public class GeneraArchivoNQF27 {
	private Properties ctx;
	private String trxName;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	
	private String ini = "";
	private String fin = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER = "115"; 	//NQF-31	
	
	private String archivo = "NQF_0027";
	private String measureGroup = "X"; //Sin grupo
	
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	private int numFiles = 1;
	private int numFile = 1;
	private Date fechanegativa1 = null;
	private Date fechanegativa2 = null;
	private int tobaccoCessationCounseling = 0;
	private int edad = 17;
	
	private int outpatient = 0;
	
	public GeneraArchivoNQF27(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
		this.numFiles = files;
	}
	
	
	public File genera() throws Exception {		
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); //ya esta validado!
				
		//Llenar parametros de la tabla
		fechaIni = param.getFecha_Ini_NQF27();
		fechaFin = param.getFecha_Fin_NQF27();
		outpatient = param.getOutpatient();
		fechanegativa1 = CQMeasures.addMonth(fechaIni, -12);
		fechanegativa2 = CQMeasures.addMonth(fechaIni, -24);
		tobaccoCessationCounseling = param.getTobaccoCessationCounseling();
				
		param.getTobaccoCessationCounseling();
		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		
		int denominador = this.pacientesDenominador();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_1", this.pacientesMedida(), denominador));				
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_2", this.pacientesMedida2(), denominador));
		//pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_3", this.pacientesMedida3(), this.pacientesDenominador()));
		
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}	
	
	/**
	 * Numerador 
	 * 1.Edad >= 17
	 * 2.dateordered de la cuenta paciente between fecha_ini - 2anios Y fecha_fin
	 * 3.Paciente sea tobacco user between fecha_ini -1 anio Y fecha_fin 
	 */
	private int pacientesMedida() {
		int pacientes = 0;					
		
		StringBuilder str = new StringBuilder(" SELECT COUNT(DISTINCT(EV.EXME_Paciente_ID)) \n");
		str.append(" FROM EXME_EstiloVidaPaciente EV \n");
		str.append(" INNER JOIN EXME_Paciente EP ON EP.EXME_Paciente_ID = EV.EXME_Paciente_ID \n");
		str.append(" INNER JOIN EXME_CtaPac CP ON CP.EXME_Paciente_ID = EP.EXME_Paciente_ID \n");
		str.append(" INNER JOIN EXME_EstiloVida EST ON EST.EXME_EstiloVida_ID = EV.EXME_EstiloVida_ID AND TRIM(EST.Value) IN ('1', '2', '3', '5') \n");
		str.append(" WHERE EV.AD_Client_ID = ? AND EV.AD_Org_ID = ? AND CP.EXME_TipoPaciente_ID = ? \n");
		str.append(" AND ").append(DB.truncDate("EV.Updated", "dd"));
		str.append(" BETWEEN TO_DATE(? ,'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy') \n");
		str.append(" AND edadmuse(EP.FechaNac, TO_DATE(?, 'dd/MM/yyyy')::date) >= ? \n");
		str.append(" AND ").append(DB.truncDate("CP.DateOrdered", "dd"));
		str.append(" BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy') ");

		pacientes = CQMeasures.executaSql(str.toString(),Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, f.format(fechanegativa1), this.fin, 
				this.ini, edad, f.format(fechanegativa2), this.fin);

		return pacientes;
	}
	
	/**
	 * Numerador 
	 * 1.Edad >= 17
	 * 2.dateordered de la cuenta paciente between fecha_ini - 2anios Y fecha_fin
	 * 3.Paciente sea tobacco user between fecha_ini -1 anio Y fecha_fin
	 * 4.tobacco use cessation counseling between fecha_ini - 1 anio Y fecha_fins
	 */
	private int pacientesMedida2(){
		int pacientes=0;		
				
		StringBuilder str = new StringBuilder(" SELECT COUNT(DISTINCT(EV.EXME_Paciente_ID)) \n");
		str.append(" FROM EXME_EstiloVidaPaciente EV  \n");
		str.append(" INNER JOIN EXME_EstiloVida est ON EST.EXME_EstiloVida_id = EV.EXME_EstiloVida_ID AND TRIM(EST.Value) IN ('1', '2', '3', '5') \n");
		str.append(" INNER JOIN EXME_Paciente EP ON EP.EXME_Paciente_ID = EV.EXME_Paciente_ID \n");
		str.append(" INNER JOIN EXME_CtaPac CP ON CP.EXME_Paciente_ID = EP.EXME_Paciente_ID \n");
		str.append(" INNER JOIN EXME_ActPaciente AC ON CP.EXME_Paciente_ID = AC.EXME_Paciente_ID \n");
		str.append(" INNER JOIN EXME_ActPacienteIndH API ON AC.EXME_ActPaciente_ID = API.EXME_ActPaciente_ID \n"); 
		str.append(" INNER JOIN EXME_ActPacienteInd AP ON API.EXME_ActPacienteIndH_ID = AP.EXME_ActPacienteIndH_ID \n");
		str.append(" INNER JOIN M_Product PR ON AP.M_Product_ID = PR.M_Product_ID \n");
		str.append(" INNER JOIN EXME_Intervencion INTER ON INTER.EXME_Intervencion_ID = PR.EXME_Intervencion_ID \n");
		str.append(" INNER JOIN EXME_ProcedureTypeDet PTD ON PTD.EXME_Intervencion_ID = INTER.EXME_Intervencion_ID \n");
		str.append(" WHERE EV.AD_Client_ID = ? AND EV.AD_Org_ID = ? \n");
		str.append(" AND CP.EXME_TipoPaciente_ID = ? \n");
		str.append(" AND ").append(DB.truncDate("EV.Updated", "dd"));
		str.append(" BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy') \n");
		str.append(" AND edadmuse(EP.FechaNac, TO_DATE(?, 'dd/MM/yyyy')::date) >= ? \n");
		str.append(" AND ").append(DB.truncDate("CP.DateOrdered", "dd"));
		str.append(" BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy') \n");
		str.append(" AND EXME_ProcedureType_ID = ? \n");
		str.append(" AND ").append(DB.truncDate("AP.DateOrdered", "dd"));
		str.append(" BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy') ");

        pacientes = CQMeasures.executaSql(str.toString(),Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, f.format(fechanegativa1), this.fin, 
				this.ini, edad, f.format(fechanegativa2), this.fin, tobaccoCessationCounseling,  f.format(fechanegativa1), this.fin);
		
		return pacientes;
	}
	
	/**
	 * Numerador 
	 * 1. No definida 
	 */
	private int pacientesMedida3(){
		int pacientes=0;		
		
		//StringBuilder str = new StringBuilder("");
		//pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
		
		return pacientes;
	}
	
	/**
	 * Denominador 
	 * 1.Edad >= 17
	 * 2.dateordered de la cuenta paciente between fecha_ini - 2anios Y fecha_fin
	 */
	private int pacientesDenominador() {
		int pacientes = 0;
		
		StringBuilder str = new StringBuilder();
		str.append(" SELECT COUNT(DISTINCT PAC.EXME_Paciente_ID) \n");
		str.append(" FROM EXME_Paciente PAC \n");
		str.append(" INNER JOIN EXME_CtaPac CTA ON CTA.EXME_Paciente_ID = PAC.EXME_Paciente_ID \n");
		str.append(" WHERE CTA.AD_Client_ID = ? \n");
		str.append(" AND CTA.AD_Org_ID = ? \n");
		str.append(" AND CTA.EXME_TipoPaciente_ID = ? \n");
		str.append(" AND ").append(DB.truncDate("CTA.DateOrdered", "dd"));
		str.append(" BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy') \n");
		str.append(" AND edadmuse(PAC.FechaNac, TO_DATE(?, 'dd/MM/yyyy')::date) >=17 ");
		      	
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, f.format(fechanegativa2), this.fin, ini);

		return pacientes;
	}
}