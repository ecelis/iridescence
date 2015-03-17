package org.compiere.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMEParamMetricas;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0043
 * Pneumococcal Vaccine
 * @author Rosy Velazquez
 */
public class GeneraArchivoNQF43 {
	private Properties ctx;
	private String trxName;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	
	private String ini = "";
	private String fin = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER = "111"; 	//NQF-43	
	
	private String archivo = "NQF_0043";
	private String measureGroup = "X"; //Sin grupo
	
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	private int numFiles = 1;
	private int numFile = 1;
	
	private int pneumoccocalMedication = 0;
	
	private int outpatient = 0;
	
	public GeneraArchivoNQF43(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
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
		fechaIni = param.getFecha_Ini_NQF43();
		fechaFin = param.getFecha_Fin_NQF43();	
				
		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);
		
		pneumoccocalMedication = param.getEXME_VacunaPneumococcal_ID();
		outpatient = param.getOutpatient();
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER , this.pacientesMedida(), this.pacientesDenominador()));						
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}	
	
	/**
	 * Numerador 
	 * 1. Edad >= 64
	 * 2. Date encounter entre ini-1 anio y fecha fin
	 * 3. Medication Pneumoccocal Vaccine
	 * 4. Paciente de tipo outpatient
	 */
	private int pacientesMedida(){
		int pacientes=0;		
		
		StringBuilder str = new StringBuilder("");
		str.append(" Select Count(distinct ctapac.Exme_Paciente_Id) \n")
			.append(" From Exme_Ctapac ctapac \n")
			.append(" Inner Join Exme_Paciente Pac On Pac.Exme_Paciente_Id = Ctapac.Exme_Paciente_Id \n")
			.append(" And edadmuse(Pac.Fechanac, to_date(?, 'dd/MM/yyyy')::date) >= 64 \n")
			.append(" Inner Join Exme_Hist_Vacuna Hist On Hist.Exme_Paciente_Id = Ctapac.Exme_Paciente_Id And Hist.Exme_Vacuna_Id = ? \n")
			.append(" Where ctapac.ad_org_id = ? and ctapac.ad_client_id = ? and Ctapac.Dateordered >= to_date(?,'dd/MM/yyyy') And Ctapac.Dateordered <= to_date(?,'dd/MM/yyyy') \n")
			.append(" AND CTAPAC.EXME_TIPOPACIENTE_ID = ? ");
		
		String fecha = f.format(CQMeasures.addMonth(fechaIni, -12));
		pacientes = CQMeasures.executaSql(str.toString(), ini, pneumoccocalMedication, Env.getAD_Org_ID(ctx),Env.getAD_Client_ID(ctx), fecha, fin,outpatient);
		
		return pacientes;
	}
	
	/**
	 * Denominador 
	 * 1. Edad >= 64
	 * 2. Date encounter entre ini-1 anio y fecha fin
	 * 3. Paciente de tipo outpatient
	 */
	private int pacientesDenominador(){
		int pacientes=0;
		
		StringBuilder str = new StringBuilder(" Select Count(distinct ctapac.Exme_Paciente_Id) \n");
		str.append(" From Exme_Ctapac ctapac \n")
			.append(" Inner Join Exme_Paciente Pac On Pac.Exme_Paciente_Id = Ctapac.Exme_Paciente_Id \n")
			.append(" And edadmuse(Pac.Fechanac, to_date(?, 'dd/MM/yyyy')::date) >= 64 \n")
			.append(" Where ctapac.ad_org_id = ? and ctapac.ad_client_id = ? \n")
			.append(" AND Ctapac.Dateordered >= to_date(?,'dd/MM/yyyy') And Ctapac.Dateordered <= to_date(?,'dd/MM/yyyy') \n")
			.append(" AND CTAPAC.EXME_TIPOPACIENTE_ID = ? \n");
		
		String fecha = f.format(CQMeasures.addMonth(fechaIni, -12));
		pacientes = CQMeasures.executaSql(str.toString(), ini, Env.getAD_Org_ID(ctx),Env.getAD_Client_ID(ctx), fecha, fin, outpatient);
		
		return pacientes;
	}
}