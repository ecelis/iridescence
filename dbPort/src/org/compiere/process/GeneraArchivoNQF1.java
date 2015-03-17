package org.compiere.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MActPacienteDiag;
import org.compiere.model.MEXMEParamMetricas;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 001
 * Asthma
 * 
 * @author Rosy Velazquez
 */

public class GeneraArchivoNQF1 {
	private Properties ctx;
	private String trxName;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	
	private String ini = "";
	private String fin = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER = "64"; 	//NQF-41	
	
	private String archivo = "NQF_0001";
	private String measureGroup = "X"; //Sin grupo
	
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	private int numFiles = 1;
	private int numFile = 1;
	
	private int asthmaDiagnosisType = 0;
	
	private int edadIni = 5;
	private int edadFin = 40;
	
	public GeneraArchivoNQF1(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
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
		fechaIni = param.getFecha_Ini_NQF1();
		fechaFin = param.getFecha_Fin_NQF1();
		this.asthmaDiagnosisType = param.getAsthma();
				
		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER , this.pacientesMedida(), this.pacientesDenominador()));						
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}	
	
	/**
	 * Numerador 
	 * 1. Edad >=5 y <=40
	 * 2. Diagnostico activo = Asthma
	 * 3. >=2 Visitas
	 * 4. ? 
	 * **/
	//TODO armar consultas
	protected int pacientesMedida(){
		int pacientes = 0;

		StringBuilder strb = new StringBuilder("SELECT count (DISTINCT(pac.exme_paciente_id)) from exme_paciente pac ");

		strb.append("INNER JOIN exme_ctapac cta on pac.exme_paciente_id = cta.exme_paciente_id ");
		strb.append("WHERE cta.exme_ctapac_id in ");
		strb.append("(SELECT DISTINCT(act.exme_ctapac_id) from exme_actpacientediag adiag ");
		strb.append("INNER JOIN exme_actpaciente act on adiag.exme_actpaciente_id = act.exme_actpaciente_id ");
		//1
		strb.append("WHERE adiag.exme_diagnostico_id IN (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ");
		strb.append(" and act.exme_ctapac_id is not null AND adiag.estatus <> ? ) ");
		// 2,3
		strb.append("AND  floor((to_date(?, 'dd/MM/yyyy') - Pac.FECHANAC)/365.25) >= ?  ");//5
		// 4,5
		strb.append("AND floor((to_date(?, 'dd/MM/yyyy') - Pac.FECHANAC)/365.25) <= ?  ");//40
		strb.append("AND  pac.exme_paciente_id in (select ids from (select pac.exme_paciente_id as ids, count(*) as num  from exme_paciente pac ");
		strb.append("INNER JOIN exme_ctapac cta on pac.exme_paciente_id = cta.exme_paciente_id ");
		strb.append("WHERE cta.exme_ctapac_id in  ");
		strb.append("(SELECT distinct(act.exme_ctapac_id) from exme_actpacientediag adiag ");
		strb.append("INNER JOIN exme_actpaciente act on adiag.exme_actpaciente_id = act.exme_actpaciente_id ");
		// 6
		strb.append("WHERE adiag.exme_diagnostico_id IN (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) "); 
		strb.append(" and act.exme_ctapac_id is not null AND adiag.estatus <> ? ) ");
		//7,8
		strb.append("AND  floor((to_date(?, 'dd/MM/yyyy') - Pac.FECHANAC)/365.25) >= ?  ");//5
		//9,10
		strb.append("AND floor((to_date(?, 'dd/MM/yyyy') - Pac.FECHANAC)/365.25) <= ?  ");//40
		strb.append("GROUP BY pac.exme_paciente_id ");
		strb.append("HAVING count(*) > 1 ");
		strb.append("ORDER BY num desc)) ");
		//11,12
		strb.append(" and cta.ad_client_id = ? AND cta.ad_Org_id = ?");

		pacientes = CQMeasures.executaSql(strb.toString(),asthmaDiagnosisType, MActPacienteDiag.ESTATUS_Inactive, this.ini, edadIni, this.fin, edadFin, 
				asthmaDiagnosisType, MActPacienteDiag.ESTATUS_Inactive, this.ini, edadIni, this.fin, edadFin, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
		
		return pacientes;
	}
	
	/**
	 * Denominador 
	 * 1. Edad >=5 y <=40
	 * 2. Diagnostico activo = Asthma
	 * 3. >=2 Visitas
	 * 
	 */
	//TODO armar consultas
	protected int pacientesDenominador() {
		int pacientes = 0;

		StringBuilder strb = new StringBuilder("SELECT count (DISTINCT(pac.exme_paciente_id)) from exme_paciente pac ");

		strb.append("INNER JOIN exme_ctapac cta on pac.exme_paciente_id = cta.exme_paciente_id ");
		strb.append("WHERE cta.exme_ctapac_id in ");
		strb.append("(SELECT DISTINCT(act.exme_ctapac_id) from exme_actpacientediag adiag ");
		strb.append("INNER JOIN exme_actpaciente act on adiag.exme_actpaciente_id = act.exme_actpaciente_id ");
		//1
		strb.append("WHERE adiag.exme_diagnostico_id IN (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) ");
		strb.append(" and act.exme_ctapac_id is not null AND adiag.estatus <> ? ) ");
		// 2,3
		strb.append("AND  floor((to_date(?, 'dd/MM/yyyy') - Pac.FECHANAC)/365.25) >= ?  ");//5
		// 4,5
		strb.append("AND floor((to_date(?, 'dd/MM/yyyy') - Pac.FECHANAC)/365.25) <= ?  ");//40
		strb.append("AND  pac.exme_paciente_id in (select ids from (select pac.exme_paciente_id as ids, count(*) as num  from exme_paciente pac ");
		strb.append("INNER JOIN exme_ctapac cta on pac.exme_paciente_id = cta.exme_paciente_id ");
		strb.append("WHERE cta.exme_ctapac_id in  ");
		strb.append("(SELECT distinct(act.exme_ctapac_id) from exme_actpacientediag adiag ");
		strb.append("INNER JOIN exme_actpaciente act on adiag.exme_actpaciente_id = act.exme_actpaciente_id ");
		// 6
		strb.append("WHERE adiag.exme_diagnostico_id IN (SELECT EXME_Diagnostico_ID FROM EXME_DiagnosisTypeDet WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y' ) "); 
		strb.append(" and act.exme_ctapac_id is not null AND adiag.estatus <> ? ) ");
		//7,8
		strb.append("AND  floor((to_date(?, 'dd/MM/yyyy') - Pac.FECHANAC)/365.25) >= ?  ");//5
		//9,10
		strb.append("AND floor((to_date(?, 'dd/MM/yyyy') - Pac.FECHANAC)/365.25) <= ?  ");//40
		strb.append("GROUP BY pac.exme_paciente_id ");
		strb.append("HAVING count(*) > 1 ");
		strb.append("ORDER BY num desc)) ");
		//11,12
		strb.append(" and cta.ad_client_id = ? AND cta.ad_Org_id = ?");

		pacientes = CQMeasures.executaSql(strb.toString(),asthmaDiagnosisType, MActPacienteDiag.ESTATUS_Inactive, this.ini, edadIni, this.fin, edadFin, 
				asthmaDiagnosisType, MActPacienteDiag.ESTATUS_Inactive, this.ini, edadIni, this.fin, edadFin, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
		
		return pacientes;
	}
}
