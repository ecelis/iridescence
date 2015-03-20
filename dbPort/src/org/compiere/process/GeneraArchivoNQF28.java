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
 * Genera xml para NQF 0028
 * Preventive care and screening measure pair: 
 * a) tobacco use assessment 
 * b) tobacco cessation intervention 
 * @author Rosy Velazquez
  */
public class GeneraArchivoNQF28{

	public GeneraArchivoNQF28(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
		this.numFiles = files;
	}
	
	private int age = 18; //age from					
	
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
	
	private int numFiles = 1;
	private int numFile = 1;
	
	private int outpatient = 0;
	
	private int smokingCessationAgents = 0; //EXME_Terapia_ID
	private int cessationCounseling = 0; //CPT
	private Date menos24Meses;
	
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	//Envio para armar xml	
		
	public File genera() throws Exception {
				
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName);		
		
		//Asignar parametros
		this.fechaIni = param.getFecha_Ini_Tabaco();
		this.fechaFin = param.getFecha_Fin_Tabaco();
		smokingCessationAgents = param.getCessationAgents();
		outpatient = param.getOutpatient();
		cessationCounseling = param.getTobaccoCessationCounseling();
		
		this.strIni = f.format(fechaIni);
		this.strFin = f.format(fechaFin);			
		menos24Meses = CQMeasures.addMonth(fechaIni, -24);
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER_TOBACCO + "_a", this.executaQueryNumeratorA(), this.executaQueryDenominatorA()));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER_TOBACCO + "_b", this.executaQueryNumeratorB(), this.executaQueryDenominatorB()));
				
		File file = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);
		
		
		return file;
	}	
	
	/**
	 * Numerador a)
	 * 1- Edad >= 18
	 * 2- dos visitas al hospital
	 * 3- Se les pregunto sobre el uso del tabaco 24 meses atras
	 * @return pacientes
	 */
	private int executaQueryNumeratorA(){
		int pacientes=0;
		StringBuilder str = new StringBuilder();
		str.append(" SELECT COUNT(DISTINCT evp.exme_paciente_id) \n");
		str.append(" FROM exme_estilovidapaciente evp \n");
		str.append(" INNER JOIN exme_estilovida ev ON ev.exme_estilovida_id = evp.exme_estilovida_id \n");
		str.append(" INNER JOIN exme_paciente ep on ep.exme_paciente_id = evp.exme_paciente_id \n");
		str.append(" INNER JOIN exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id \n");
		str.append(" WHERE evp.ad_client_id = ? AND evp.ad_org_id = ? \n");
		str.append(" AND cta.exme_tipopaciente_id = ? AND ev.tipoestilo = 'SM' \n");
		str.append(" AND ").append(DB.truncDate("evp.updated", "dd"));
		str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) >= ? \n");
		str.append(" AND EVP.EXME_PACIENTE_ID IN ( \n");
		str.append("\t SELECT CTA.EXME_PACIENTE_ID \n");
		str.append("\t FROM EXME_CTAPAC CTA \n");
		str.append("\t WHERE CTA.AD_CLIENT_ID = ? AND CTA.AD_ORG_ID = ? AND CTA.EXME_TIPOPACIENTE_ID = ? \n");
		str.append("\t AND ").append(DB.truncDate("CTA.DATEORDERED", "dd"));
		str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append("\t GROUP BY CTA.EXME_PACIENTE_ID HAVING COUNT(CTA.EXME_PACIENTE_ID) >= 2)");
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, f.format(menos24Meses), this.strFin, this.strIni, this.age, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, this.strIni, this.strFin);			
		return pacientes;
	}
	
	/**
	 * Denominador a)
	 * 1- Edad >= 18
	 * 2- dos visitas al hospital
	 * @return
	 */
	private int executaQueryDenominatorA(){
		int pacientes = 0;
		StringBuilder str = new StringBuilder();
		str.append(" SELECT count(distinct ep.exme_paciente_id) \n");
		str.append(" FROM EXME_Paciente ep \n");
		str.append(" inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id \n");
		str.append(" WHERE cta.ad_client_id = ? AND cta.ad_org_id = ? AND cta.exme_tipopaciente_id = ? \n");
		str.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) >= ? \n");
		str.append(" AND ").append(DB.truncDate("CTA.DATEORDERED", "dd"));
		str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
		str.append(" AND ep.isactive = 'Y' AND ep.exme_paciente_id IN ( \n");
		str.append("\t SELECT CTA.EXME_PACIENTE_ID \n");
		str.append("\t FROM EXME_CTAPAC CTA \n");
		str.append("\t WHERE CTA.AD_CLIENT_ID = ? AND CTA.AD_ORG_ID = ? AND CTA.EXME_TIPOPACIENTE_ID = ? \n");
		str.append("\t AND ").append(DB.truncDate("CTA.DATEORDERED", "dd"));
		str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
		str.append("\t GROUP BY CTA.EXME_PACIENTE_ID  HAVING COUNT(CTA.EXME_PACIENTE_ID) >= 2) \n");
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, this.strIni, age, this.strIni, this.strFin, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, this.strIni, this.strFin);			
		return pacientes;
	}
	
	/**
	 * Numerador b)
	 * 1- Edad >= 18
	 * 2- dos visitas al hospital
	 * 3- Que sea Fumador
	 * 4- Que recibieron smoking cessation agents (medication)
	 *    OR recibieron   tobacco use cessation counseling<= 24 months
	 * @return pacientes
	 */
	private int executaQueryNumeratorB(){
		int pacientes=0;					
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(DISTINCT(EV.EXME_PACIENTE_ID)) \n");
		sql.append(" FROM EXME_ESTILOVIDAPACIENTE EV  \n");
		sql.append(" INNER JOIN EXME_ESTILOVIDA EEV ON EEV.EXME_ESTILOVIDA_ID = EV.EXME_ESTILOVIDA_ID \n");
		sql.append(" INNER JOIN EXME_PACIENTE EP ON EP.EXME_PACIENTE_ID = EV.EXME_PACIENTE_ID \n");
		sql.append(" INNER JOIN EXME_ACTPACIENTE AP ON AP.EXME_PACIENTE_ID = EP.EXME_PACIENTE_ID \n");
		sql.append(" INNER JOIN EXME_ACTPACIENTEINDH INDH ON INDH.EXME_ACTPACIENTE_ID = AP.EXME_ACTPACIENTE_ID \n");
		sql.append(" INNER JOIN EXME_ACTPACIENTEIND IND ON IND.EXME_ACTPACIENTEINDH_ID = INDH.EXME_ACTPACIENTEINDH_ID \n");
		sql.append(" LEFT JOIN EXME_TERAPIA_PRODUCTO ETP ON ETP.EXME_GENPRODUCT_ID = IND.EXME_GENPRODUCT_ID \n");
		sql.append(" LEFT JOIN EXME_INTERVENCION EI ON EI.M_PRODUCT_ID = IND.M_PRODUCT_ID \n");
		sql.append(" LEFT JOIN EXME_PROCEDURETYPEDET PTD ON PTD.EXME_INTERVENCION_ID = EI.EXME_INTERVENCION_ID \n");
		sql.append(" INNER JOIN EXME_CTAPAC CP ON CP.EXME_PACIENTE_ID = EP.EXME_PACIENTE_ID  \n");
		sql.append(" WHERE EV.AD_CLIENT_ID = ?  AND EV.AD_ORG_ID = ? \n");
		sql.append(" AND TRIM(EEV.VALUE) IN ('1', '2', '3', '5') \n");
		sql.append(" AND CP.EXME_TIPOPACIENTE_ID = ? \n");
		sql.append(" AND ").append(DB.truncDate("EV.UPDATED", "dd"));
		sql.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		sql.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) >= ? \n");
		sql.append(" AND (ETP.EXME_TERAPIA_ID = ? OR PTD.EXME_PROCEDURETYPE_ID = ?) \n");
		sql.append(" AND ").append(DB.truncDate("indh.dateordered", "dd"));
		sql.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		sql.append(" AND EV.EXME_PACIENTE_ID IN ( \n");
		sql.append("\t SELECT CTA.EXME_PACIENTE_ID \n");
		sql.append("\t FROM EXME_CTAPAC CTA \n");
		sql.append("\t WHERE CTA.AD_CLIENT_ID = ? AND CTA.AD_ORG_ID = ? AND CTA.EXME_TIPOPACIENTE_ID = ? \n");
		sql.append("\t AND ").append(DB.truncDate("CTA.DATEORDERED", "dd"));
		sql.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		sql.append("\t GROUP BY CTA.EXME_PACIENTE_ID  HAVING COUNT(CTA.EXME_PACIENTE_ID) >= 2) \n");
		
		pacientes = CQMeasures.executaSql(sql.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, strIni, strFin, strIni, age, smokingCessationAgents, cessationCounseling, f.format(menos24Meses), strFin, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, strIni, strFin);						
		return pacientes;
	}
	
	/**
	 * Denominador b)
	 * 1- Edad >= 18
	 * 2- dos visitas al hospital
	 * 3- Que sea Fumador
	 * @return
	 */
	protected int executaQueryDenominatorB(){
		int pacientes = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(DISTINCT(EV.EXME_PACIENTE_ID)) \n");
		sql.append(" FROM EXME_ESTILOVIDAPACIENTE EV \n");
		sql.append(" INNER JOIN EXME_PACIENTE EP ON EP.EXME_PACIENTE_ID = EV.EXME_PACIENTE_ID \n");
		sql.append(" INNER JOIN EXME_CTAPAC CP ON CP.EXME_PACIENTE_ID = EP.EXME_PACIENTE_ID \n");
		sql.append(" INNER JOIN EXME_ESTILOVIDA EEV ON EEV.EXME_ESTILOVIDA_ID = EV.EXME_ESTILOVIDA_ID \n");
		sql.append(" WHERE EV.AD_CLIENT_ID = ? AND EV.AD_ORG_ID = ? AND CP.EXME_TIPOPACIENTE_ID = ? \n");
		sql.append(" AND TRIM(EEV.VALUE) IN ('1', '2', '3', '5') \n");
		sql.append(" AND edadmuse(ep.fechanac, to_date(?, 'dd/MM/yyyy')::date) >= ? \n");
		sql.append(" AND ").append(DB.truncDate("EV.UPDATED", "dd"));
		sql.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		sql.append(" AND EV.EXME_PACIENTE_ID IN ( \n");
		sql.append("\t SELECT CTA.EXME_PACIENTE_ID \n");
		sql.append("\t FROM EXME_CTAPAC CTA \n");
		sql.append("\t WHERE CTA.AD_CLIENT_ID = ? AND CTA.AD_ORG_ID = ? AND CTA.EXME_TIPOPACIENTE_ID = ? \n");
		sql.append("\t AND ").append(DB.truncDate("CTA.DATEORDERED", "dd"));
		sql.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		sql.append("\t GROUP BY CTA.EXME_PACIENTE_ID HAVING COUNT(CTA.EXME_PACIENTE_ID) >= 2)");

		pacientes = CQMeasures.executaSql(sql.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, strIni, age, strIni, strFin, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, strIni, strFin);					
		return pacientes;
	}
}