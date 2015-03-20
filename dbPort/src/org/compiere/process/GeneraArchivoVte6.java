package org.compiere.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMEParamMetricas;
import org.compiere.model.X_EXME_ActPacienteDiag;
import org.compiere.model.X_EXME_CtaPac;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0376
 * VTE - 6
 * @author Rosy Velazquez
 * Modificado por Victor Perez (card 970)
 */
public class GeneraArchivoVte6{

	public GeneraArchivoVte6(final String version, final String optionRegistry, final Properties ctx, final String trxName, final int file, final int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
		this.numFiles = files;
	}

	private final Properties ctx;
	private final String trxName;

	private String ini ="";
	private String fin ="";

	private final String optionRegistry;
	private final String version;

	private final static String PQRI_NUMBER = "0376";

	private static final String archivo = "NQF_0376";
	private static final String measureGroup = "X"; //Sin grupo

	private final int numFiles;
	private final int numFile;

	private int diagnostcTypeVte = 0;
	private int terapiaProphylaxisVte = 0;
	private int inpatient = 0;

	private final SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Genera el archivo XML de la metrica
	 * @return regresa el archivo XML
	 * @throws Exception
	 */
	public File genera() throws Exception {

		final MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); //ya esta validado!

		//Llenar parametros de la tabla
		final Date fechaIni = param.getFecha_Ini_Vte6();
		final Date fechaFin = param.getFecha_Fin_Vte6();

		diagnostcTypeVte = param.getVTE_Diagnostic();
		terapiaProphylaxisVte = param.getEXME_ProphylaxisVte_ID();
		inpatient = param.getEXME_EstServ_Hosp_ID();

		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);

		final GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		final List<String[]> pqris = new ArrayList<String[]>();

		pqris.add(CQMeasures.pqri(PQRI_NUMBER, this.pacientesMedida(), this.pacientesDenominador()));

		final File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);

		return generado;
	}

	/**
	 * Obitne la consulta para el numerador o denominador
	 */
	private String getSQL(final boolean numerador, final List<Object> params) {
		final StringBuilder sql = new StringBuilder();
		if (numerador) {
			sql.append("with plan_pac as ( \n")
			.append("	select pm.exme_ctapac_id, pm.startdate \n")
			.append("	from  EXME_PlanMed pm \n")
			.append("		inner join m_product prod on pm.m_product_id = prod.m_product_id \n")
			.append("		inner join exme_terapia_producto tp on prod.exme_genproduct_id = tp.exme_genproduct_id \n")
			.append("		INNER JOIN EXME_THERAPIESVERSION TV ON (TV.EXME_THERAPIESVERSION_ID = tp.EXME_THERAPIESVERSION_ID AND tv.stage = '1') \n")
			.append("	where tv.exme_terapia_id = ?  \n") 		// this.terapiaProphylaxisVte
			.append("		and pm.ad_client_id = ?  \n") 		// ad_client
			.append("		and pm.ad_org_id = ?  \n") 			// ad_org
			.append(") \n");
			params.add(terapiaProphylaxisVte);
			params.add(Env.getAD_Client_ID(ctx));
			params.add(Env.getAD_Org_ID(ctx));
		}
		sql.append("select \n")
		.append("count (DISTINCT cp.exme_paciente_id) \n")
		.append("from exme_ctapac cp \n")
		.append("	inner join exme_actpaciente ap on ap.exme_ctapac_id = cp.exme_ctapac_id \n")
		.append("	inner join exme_actpacientediag diag on ap.exme_actpaciente_id = diag.exme_actpaciente_id \n")
		.append(" INNER JOIN EXME_diagnostico dm ON (dm.EXME_diagnostico_ID = diag.EXME_diagnostico_ID) \n")
		.append(" INNER JOIN EXME_DiagnosisTypeDet dtd   ON (dm.value = dtd.code AND dtd.IsActive='Y')  \n") // this.diagnostcTypeVte
		.append(" INNER JOIN EXME_DiagnosisVersion dv ON (dv.EXME_DiagnosisVersion_Id = dtd.EXME_DiagnosisVersion_Id AND dv.stage='1' AND dv.EXME_DiagnosisType_ID=?) \n")
		.append("	inner join exme_paciente ep on cp.exme_paciente_id = ep.exme_paciente_id \n")
		.append("where cp.ad_client_id = ? \n") 			// ad_client
		.append("	and cp.ad_org_id = ? \n")				// ad_org
		.append("	and cp.exme_tipopaciente_id = ? \n")		// inpatient
		.append("	and diag.type = 'CF' \n")
		.append("	and \n")
		.append("	DATE_TRUNC('day', diag.fecha_diagnostico) between  \n")
		.append("		to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n") 	// this.ini, this.fin
		.append("	AND diag.Estatus <> ? \n")				// MActPacienteDiag.ESTATUS_Inactive
		.append("	AND diag.IsActive = 'Y' \n")
		.append("	AND cp.EncounterStatus not in ( ?, ?, ? ) \n")	// MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission,
																	//MEXMECtaPac.ENCOUNTERSTATUS_Admission,
																	//MEXMECtaPac.ENCOUNTERSTATUS_Predischarge
		.append("	AND edadmuse( ep.fechanac, cp.dateordered) >= 18 \n")
		.append("	AND extract(day from cp.fechaalta - cp.dateordered) < 120 \n")
		.append("	AND (cp.resstatus != ? or cp.resstatus is null) \n");	// MEXMECtaPac.RESSTATUS_ComfortCareOnly
		params.add(diagnostcTypeVte);
		params.add(Env.getAD_Client_ID(ctx));
		params.add(Env.getAD_Org_ID(ctx));
		params.add(inpatient);
		params.add(ini);
		params.add(fin);
		params.add(X_EXME_ActPacienteDiag.ESTATUS_Inactive);
		params.add(X_EXME_CtaPac.ENCOUNTERSTATUS_PreAdmission);
		params.add(X_EXME_CtaPac.ENCOUNTERSTATUS_Admission);
		params.add(X_EXME_CtaPac.ENCOUNTERSTATUS_Predischarge);
		params.add(X_EXME_CtaPac.RESSTATUS_ComfortCareOnly);
		if (numerador) {
			sql.append("	and cp.exme_ctapac_id not in \n")
			.append("		(select distinct exme_ctapac_id \n")
			.append("		from plan_pac \n")
			.append("		where startdate between cp.dateordered and (diag.fecha_diagnostico - 1)) \n");
		}

		return sql.toString();
	}

	/**
	 * Calcula los pacientes del denominador
	 * Mismas consideraciones sin VTE prophilaxis
	 * @return pacientes
	 */
	private int pacientesDenominador(){
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(false, params), params.toArray());
	}

	/**
	 * Numerador
	 *<br> Calcula los pacientes
	 *<p> -1. Con un diagnostico de VTE confirmado
	 *		  Diagnostico principal o cualquiera
	 *		  Diagnostico codificado
	 *<p> -2. No recibieron VTE prophylaxis Medications (aplicados 1  dia antes del diagnostico)
	 *<p> -3. Pacientes dados de alta
	 *<p> -4. Edad > 18
	 *<p> -5. Estancia < 120 dias
	 *<p> -6. No patients with Comfort measures only
	 *<p> -7. No patients with enrolled in clinical trial
	 */
	private int pacientesMedida() {
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(true, params), params.toArray());
	}

	/**
	 * Metodo para validar que la consulta este funcionando (JUnit)
	 * @param numerador indica si se genera el numerador o el denominador
	 * @return indica el nuemro de pacientes obtenidos segun la metrica
	 */
	public static int testPacientesMedida(final boolean numerador) {
		final GeneraArchivoVte6 bean = new GeneraArchivoVte6(null, null, Env.getCtx(), null, 0, 0);
		int id;
		if (numerador) {
			id = bean.pacientesMedida();
		} else {
			id = bean.pacientesDenominador();
		}
		return id;
	}
}