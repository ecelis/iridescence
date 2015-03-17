package org.compiere.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MActPacienteDiag;
import org.compiere.model.MEXMEParamMetricas;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0421
 * BMI outside parameters with follow plan 
 * @author Rosy Velazquez
 */
public class GeneraArchivoNQF421 {
	
	private Properties ctx;
	private String trxName;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	
	private String iniMenosSeis = "";
	
	private String ini = "";
	private String fin = "";
	
	private String optionRegistry="";
	private String version = "";	
		
	private final static String PQRI_NUMBER = "128"; 	//NQF-421	
	
	private String archivo = "NQF_0421";
	private String measureGroup = "X"; //Sin grupo
	
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	private int numFiles = 1;
	private int numFile = 1;
	
	private final int edadIni = 18;
	private final int edadFin = 64;	
	private final int edadTope = 65;
	
	private int pregnancy = 0;
	private int outpatient = 0;
	private int followPlan = 0; //EXME_ProcedureType
	
	public GeneraArchivoNQF421(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
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
		fechaIni = param.getFecha_Ini_Imc();
		fechaFin = param.getFecha_Fin_Imc();
		pregnancy = param.getPregnancy();
		outpatient = param.getOutpatient();
		followPlan = param.getFollowPlan();
		
		iniMenosSeis = f.format(CQMeasures.addMonth(this.fechaIni, -6));

		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_1", this.pacientesMedidaA(edadTope), this.pacientesDenominadorA(edadTope)));				
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_2", this.pacientesMedidaB(edadIni, edadFin), this.pacientesDenominadorB(edadIni, edadFin)));
				
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);	
				
		return generado;
	}	
	
	/**
	 * Numerador 
	 * 1. Pacientes con BMI Calculado (6 meses atras o en periodo de medida) y resultado fuera de rango
	 * 2. Edad del parametro
	 * 3. No incluir pacientes con diagnostico activo de Pregnancy
	 * 4. Plan de seguimiento documentado (ProcedureType)
	 *    OR Dietary Consultation Order
	 */
	private int pacientesMedidaA(int edadIni){
		int pacientes=0;		
		
		StringBuilder str = new StringBuilder();
		
			str.append(" SELECT COUNT(DISTINCT hv.exme_paciente_id) \n");
			str.append(" FROM EXME_METRICAS_IMC hv INNER JOIN exme_paciente ep\n");
			str.append(" INNER JOIN exme_ctapac cta ON cta.exme_paciente_id = ep.exme_paciente_id ON ep.exme_paciente_id = hv.exme_paciente_id\n");
			str.append(" WHERE cta.ad_client_id = ?  AND cta.ad_org_id = ? AND cta.exme_tipopaciente_id = ?\n");
			str.append(" AND ").append(DB.truncDate("hv.FECHA_IMC", "dd"));
			str.append(" BETWEEN to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy')\n");
			str.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) >= ? \n"); 
			str.append(" AND hv.exme_paciente_id NOT IN (\n");
			str.append("\t SELECT ap.exme_paciente_id \n");
			str.append("\t FROM exme_actpacientediag diag \n");
			str.append("\t INNER JOIN exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id\n");
			str.append("\t WHERE diag.type = 'CF' AND diag.exme_diagnostico_id IN (\n");
			str.append("\t\t SELECT EXME_Diagnostico_ID\n");
			str.append("\t\t FROM EXME_DiagnosisTypeDet\n");
			str.append("\t\t WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y')\n");
			str.append("\t AND ").append(DB.truncDate("diag.fecha_diagnostico", "dd"));
			str.append("\t BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy')\n");
			str.append("\t AND diag.IsActive = 'Y'\n ");
			str.append("\t AND diag.Estatus <> ? )\n");
			str.append(" AND hv.exme_paciente_id in (\n");
			str.append("\t SELECT DISTINCT ap.exme_paciente_id\n");
			str.append("\t FROM exme_actpacienteind api\n");
			str.append("\t INNER JOIN exme_actpacienteindh ph\n");
			str.append("\t INNER JOIN exme_actpaciente ap ON ap.exme_actpaciente_id = ph.exme_actpaciente_id\n");
			str.append("\t ON ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id\n");
			str.append("\t WHERE api.m_product_id IN (\n");
			str.append("\t\t SELECT m_product_id\n");
			str.append("\t\t FROM M_Product\n");
			str.append("\t\t WHERE exme_intervencion_id in (\n");
			str.append("\t\t\t select exme_intervencion_id\n");
			str.append("\t\t\t from exme_procedureTypeDet\n");
			str.append("\t\t\t where exme_procedureType_id = ?)) \n");
			str.append("\t\t\t AND ").append(DB.truncDate("ph.dateordered","dd"));
			str.append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy'))\n");
			
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, iniMenosSeis, this.fin, this.ini, edadIni,
				pregnancy, this.ini, this.fin, MActPacienteDiag.ESTATUS_Inactive, this.followPlan, this.ini, this.fin);
		
		return pacientes;
	}
	
	private int pacientesMedidaB(int edadIni, int edadFin){
		int pacientes = 0;

		StringBuilder str = new StringBuilder();
		str.append(" SELECT COUNT(DISTINCT hv.exme_paciente_id)\n");
		str.append(" FROM EXME_METRICAS_IMC hv\n");
		str.append(" INNER JOIN exme_paciente ep ON ep.exme_paciente_id = hv.exme_paciente_id\n");
		str.append(" INNER JOIN exme_ctapac cta ON cta.exme_paciente_id = ep.exme_paciente_id\n");
		str.append(" WHERE cta.ad_client_id = ? \n");
		str.append(" AND cta.ad_org_id = ? \n");
		str.append(" AND cta.exme_tipopaciente_id =? \n");
		str.append(" AND ").append(DB.truncDate("hv.FECHA_IMC","dd"));
		str.append(" BETWEEN to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy')\n");
		str.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) >= ? \n");
		str.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) <= ? \n");
		str.append(" AND hv.exme_paciente_id NOT IN ( \n");
		str.append("\t SELECT ap.exme_paciente_id\n");
		str.append("\t FROM exme_actpacientediag diag\n");
		str.append("\t INNER JOIN exme_actpaciente ap ON ap.exme_actpaciente_id = diag.exme_actpaciente_id\n");
		str.append("\t WHERE diag.type = 'CF' AND diag.exme_diagnostico_id IN (\n");
		str.append("\t\t SELECT EXME_Diagnostico_ID	\n");
		str.append("\t\t FROM EXME_DiagnosisTypeDet \n");
		str.append("\t\t WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y') \n");
		str.append("\t AND ").append(DB.truncDate("diag.fecha_diagnostico","dd"));
		str.append("\t between to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append("\t AND diag.IsActive = 'Y' AND diag.Estatus <> ?) \n");
		str.append(" AND hv.exme_paciente_id IN( \n");
		str.append("\t select distinct ap.exme_paciente_id \n");
		str.append("\t from exme_actpacienteind api\n");
		str.append("\t inner join exme_actpacienteindh ph ON ph.exme_actpacienteindh_id = api.exme_actpacienteindh_id\n");
		str.append("\t inner join exme_actpaciente ap ON ap.exme_actpaciente_id = ph.exme_actpaciente_id\n ");
		str.append("\t where api.m_product_id in( \n");
		str.append("\t\t select m_product_id \n");
		str.append("\t\t from M_Product\n");
		str.append("\t\t where exme_intervencion_id in ( \n");
		str.append("\t\t\t select exme_intervencion_id\n");
		str.append("\t\t\t from exme_procedureTypeDet\n");
		str.append("\t\t\t where exme_procedureType_id = ?\n))");
		str.append("\t AND ").append(DB.truncDate("ph.dateordered","dd"));
		str.append("\t between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy'))");
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx),
				Env.getAD_Org_ID(ctx), outpatient, iniMenosSeis, this.fin, this.ini, edadIni,
				this.ini, edadFin, pregnancy, this.ini, this.fin,
				MActPacienteDiag.ESTATUS_Inactive, this.followPlan, this.ini, this.fin);

		return pacientes;
	}
	
	/**
	 * Denominador 
	 * NO --- 1. Pacientes con BMI Calculado (6 meses atras o en periodo de medida) y resultado fuera de rango
	 * 2. Edad del parametro
	 * 3. No incluir pacientes con diagnostico activo de Pregnancy
	 */
	private int pacientesDenominadorA(int edadIni){
		int pacientes=0;				
		
		StringBuilder str = new StringBuilder();
		str.append(" SELECT COUNT(DISTINCT ep.exme_paciente_id)\n");
		str.append(" FROM exme_paciente ep\n");
		str.append(" INNER JOIN exme_ctapac cta ON cta.exme_paciente_id = ep.exme_paciente_id\n");
		str.append(" WHERE cta.ad_client_id = ?\n");
		str.append(" AND cta.ad_org_id = ?\n");
		str.append(" AND cta.exme_tipopaciente_id = ?\n");
		str.append(" AND ").append(DB.truncDate("cta.dateordered","dd")).append("BETWEEN to_date(?,'dd/MM/yyyy')");
		str.append(" AND to_date(?, 'dd/MM/yyyy')\n");
		str.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) >= ? \n");
		str.append(" AND ep.exme_paciente_id NOT IN( \n");
		str.append("\t SELECT ap.exme_paciente_id\n");
		str.append("\t FROM exme_actpacientediag diag\n");
		str.append("\t INNER JOIN exme_actpaciente ap ON ap.exme_actpaciente_id = diag.exme_actpaciente_id\n");
		str.append("\t WHERE diag.type = 'CF' \n");
		str.append("\t AND diag.exme_diagnostico_id IN ( \n");
		str.append("\t\t SELECT EXME_Diagnostico_ID\n");
		str.append("\t\t FROM EXME_DiagnosisTypeDet\n");
		str.append("\t\t WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y')\n");
		str.append("\t AND ").append(DB.truncDate("diag.fecha_diagnostico","dd")).append("between to_date(?, 'dd/MM/yyyy')");
		str.append("\t and to_date(?, 'dd/MM/yyyy')\n");
		str.append("\t AND diag.IsActive = 'Y' AND diag.Estatus <> ?)");
	
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, ini, this.fin, this.ini, edadIni, pregnancy, this.ini, this.fin, MActPacienteDiag.ESTATUS_Inactive);
		
		return pacientes;
	}
	
	private int pacientesDenominadorB(int edadIni, int edadFin) {
		int pacientes = 0;

		StringBuilder str = new StringBuilder();

		str.append(" SELECT COUNT(DISTINCT ep.exme_paciente_id)\n");
		str.append(" FROM exme_paciente ep\n");
		str.append(" inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id\n");
		str.append(" WHERE cta.ad_client_id = ? AND cta.ad_org_id = ? AND cta.exme_tipopaciente_id = ?\n");
		str.append(" AND ").append(DB.truncDate("cta.dateordered","dd")).append("BETWEEN to_date(?,'dd/MM/yyyy')");
		str.append(" AND to_date(?, 'dd/MM/yyyy')\n");
		str.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) >= ? \n");
		str.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) <= ? \n");
		str.append(" AND ep.exme_paciente_id NOT IN ( \n");
		str.append("\t SELECT ap.exme_paciente_id\n");
		str.append("\t FROM exme_actpacientediag diag\n");
		str.append("\t inner join exme_actpaciente ap on ap.exme_actpaciente_id = diag.exme_actpaciente_id\n");
		str.append("\t WHERE diag.type = 'CF' AND diag.exme_diagnostico_id IN(\n");
		str.append("\t\t SELECT EXME_Diagnostico_ID\n");
		str.append("\t\t FROM EXME_DiagnosisTypeDet\n");
		str.append("\t\t WHERE EXME_DiagnosisType_ID = ? AND IsActive = 'Y')\n");
		str.append("\t AND ").append(DB.truncDate("diag.fecha_diagnostico","dd")).append(" between to_date(?, 'dd/MM/yyyy')");
		str.append("and to_date(?, 'dd/MM/yyyy')\n");
		str.append("\t	AND diag.IsActive = 'Y' AND diag.Estatus <> ?)");

		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, ini, this.fin, this.ini, edadIni, this.ini, edadFin, pregnancy, this.ini, this.fin, MActPacienteDiag.ESTATUS_Inactive);

		return pacientes;
	}
}