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
 * Genera xml para NQF 0041
 * Influenza Immunization during the flu season
 * @author Rosy Velazquez
 */
public class GeneraArchivoNQF41 {
	private Properties ctx;
	private String trxName;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	
	private String ini = "";
	private String fin = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER = "110"; 	//NQF-41	
	
	private String archivo = "NQF_0041";
	private String measureGroup = "X"; //Sin grupo
	
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	private int numFiles = 1;
	private int numFile = 1;
	
	private int influenzaVaccine = 0;
	private int outpatient = 0;
	private int age = 50;
	
	public GeneraArchivoNQF41(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
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
		fechaIni = param.getFecha_Ini_Influenza();
		fechaFin = param.getFecha_Fin_Influenza();	
		
		influenzaVaccine = param.getEXME_VacunaInfluenza_ID();
		//encounterInfluenza = param.getEncounterInfluenza(); //ProcedureType_ID
				
		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);
		
		outpatient = param.getOutpatient();
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER , this.pacientesMedida(), this.pacientesDenominador()));						
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}	
	
	/**
	 * Numerador 
	 * 1. Edad >=50
	 * 2. >=2 Visitas outpatient
	 * 3. Encounter influenza (CPT) (*NO*)
	 * 4. Medication for influenza vaccine
	 */
	private int pacientesMedida(){
		int pacientes=0;		
		
		StringBuilder str = new StringBuilder();
		str.append(" SELECT COUNT(DISTINCT PAC.EXME_PACIENTE_ID) \n");
		str.append(" FROM EXME_Paciente pac \n");
		str.append(" INNER JOIN EXME_CtaPac cta ON cta.exme_paciente_id = pac.exme_paciente_id \n");
		str.append(" INNER JOIN EXME_HIST_VACUNA vac ON vac.exme_paciente_id = pac.exme_paciente_id \n");
		str.append(" WHERE CTA.AD_CLIENT_ID = ? AND CTA.AD_ORG_ID = ? AND CTA.EXME_TIPOPACIENTE_ID = ? \n");
		str.append(" AND edadmuse(pac.fechanac, to_date(?, 'dd/MM/yyyy')::date) >= ? AND VAC.EXME_VACUNA_ID = ? \n");
		str.append(" AND ").append(DB.truncDate("VAC.FECHAAPLICA", "dd"));
		str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" AND NOT ").append(DB.truncDate("VAC.FECHAAPLICA", "dd"));
		str.append(" BETWEEN to_date('01/03/'||to_char(VAC.FECHAAPLICA, 'YYYY'), 'dd/MM/yyyy') AND to_date('31/08/'||to_char(VAC.FECHAAPLICA, 'YYYY'), 'dd/MM/yyyy') \n");
		str.append(" AND PAC.EXME_PACIENTE_ID IN ( \n");
		str.append("\t SELECT CTA.EXME_PACIENTE_ID \n");
		str.append("\t FROM EXME_CTAPAC CTA \n");
		str.append("\t WHERE CTA.AD_CLIENT_ID = ? AND CTA.AD_ORG_ID = ? AND CTA.EXME_TIPOPACIENTE_ID = ? \n");
		str.append("\t AND ").append(DB.truncDate("CTA.DATEORDERED", "dd"));
		str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
		str.append("\t GROUP BY CTA.EXME_PACIENTE_ID HAVING COUNT(CTA.EXME_PACIENTE_ID) >= 2) \n");

		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, ini, age, influenzaVaccine, this.ini, this.fin, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, ini, fin);
		
		return pacientes;
	}
	
	/**
	 * Denominador 
	 * 1. Edad >= 50
	 * 2. >= 2 outpatient encounter
	 * 3. encounter influenza (CPT) (*YA NO*)
	 */
	private int pacientesDenominador(){
		int pacientes=0;				
		
		StringBuilder str = new StringBuilder();
		str.append(" SELECT COUNT(DISTINCT PAC.EXME_PACIENTE_ID) \n");
		str.append(" FROM EXME_Paciente pac \n");
		str.append(" INNER JOIN EXME_CtaPac cta ON cta.exme_paciente_id = pac.exme_paciente_id \n");
		str.append(" WHERE CTA.AD_CLIENT_ID = ? AND CTA.AD_ORG_ID = ? AND CTA.EXME_TIPOPACIENTE_ID = ? \n");
		str.append(" AND edadmuse(pac.fechanac, to_date(?, 'dd/MM/yyyy')::date) >= ? AND PAC.EXME_PACIENTE_ID IN ( \n");
		str.append("\t SELECT CTA.EXME_PACIENTE_ID \n");
		str.append("\t FROM EXME_CTAPAC CTA \n");
		str.append("\t WHERE CTA.AD_CLIENT_ID = ? AND CTA.AD_ORG_ID = ? AND CTA.EXME_TIPOPACIENTE_ID = ? \n");
		str.append("\t AND ").append(DB.truncDate("CTA.DATEORDERED", "dd"));
		str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
		str.append("\t GROUP BY CTA.EXME_PACIENTE_ID  HAVING COUNT(CTA.EXME_PACIENTE_ID) >= 2)");
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, ini, age, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, ini, fin);
		
		return pacientes;
	}
}