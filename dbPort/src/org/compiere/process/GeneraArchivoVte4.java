package org.compiere.process;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMEParamMetricas;
import org.compiere.model.X_EXME_ActPacienteDiag;
import org.compiere.model.X_EXME_CtaPac;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0374
 * VTE - 4
 * @author Rosy Velazquez
 * Modificado por Lorena Lama (Revisión de SQL)
 * Modificado por Victor Perez (card 968)
 */

public class GeneraArchivoVte4 {
	public GeneraArchivoVte4(final String version, final String optionRegistry, final Properties ctx, final String trxName, final int file, final int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
		this.numFiles = files;
	}

	private final Properties ctx;
	private final String trxName;


	private String ini = "";
	private String fin = "";

	private final String optionRegistry;
	private final String version;

	private final static String PQRI_NUMBER = "0374";

	private static final String archivo = "NQF_0374";
	private static final String measureGroup = "X"; //Sin grupo

	private final int numFiles;
	private final int numFile;

	private int terapiaHeparin = 0;
	private int diagnosticoTypeVte = 0;
	private int plateletLoinc = 0;
	private int inpatient = 0;

	/**
	 * Genera el archivo XML de la metrica
	 * @return regresa el archivo XML
	 * @throws Exception
	 */
	public File genera() throws Exception {

		final MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); // ya esta validado!

		// Llenar parametros de la tabla
		final Date fechaIni = param.getFecha_Ini_Vte4() == null ? Env.getCurrentDate() : param.getFecha_Ini_Vte4();
		final Date fechaFin = param.getFecha_Fin_Vte4() == null ? Env.getCurrentDate() : param.getFecha_Fin_Vte4();

		terapiaHeparin = param.getEXME_TerapiaHeparin_ID();
		diagnosticoTypeVte = param.getVTE_Diagnostic();

		plateletLoinc = param.getPlatelet_Loinc();
		inpatient = param.getEXME_EstServ_Hosp_ID();

		// Asignar formato
		ini = Constantes.getSdfFecha().format(fechaIni);
		fin = Constantes.getSdfFecha().format(fechaFin);

		final GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		final List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER, this.pacientesMedida(), this.pacientesDenominador()));

		final File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);

		return generado;
	}

	/**
	 * Numerador
	 *<br> Calcula los pacientes
	 *<p> -1. con un diagnostico de VTE confirmado
	 *		 Diagnostico principal o cualquiera
	 *		 Diagnostico codificado
	 *<p> -2. Heparin Medications ((IV) UFH therapy)
	 *<p> -3. Platelet COUNT Laboratory test result.
	 *<p> -4. Edad > 18
	 *<p> -5. Estancia < 120 dias
 	 *<p> -6. No pacientes Comfort Measures Only
 	 *<p> -7. Pacientes dados de alta
 	 *<p> -8. No pacientes Clinical Trial ***
 	 *Lama: Correccion excesiva anidacion de sub-selects
	 */
	private int pacientesMedida(){
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(true, params), params.toArray());
	}

	/**
	 * Calcula los pacientes del denominador
	 * Mismas consideraciones numerador
	 * sin platelet COUNT
	 * @return pacientes
	 * Lama: Correccion excesiva anidacion de sub-selects
	 */
	private int pacientesDenominador(){
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(false, params), params.toArray());
	}

	/**
	 * Calcula los pacientes
	 * -1. Con un diagnostico de VTE confirmado <br>
	 *     - Diagnostico principal o cualquiera <br>
	 *     - Diagnostico codificado
	 * -2. Heparin Medications ((IV) UFH therapy)
	 * -4. Edad > 18
	 * -5. Estancia < 120 dias
	 * -6. No pacientes Comfort Measures Only
	 * -7. Pacientes dados de alta
	 * -8. No pacientes Clinical Trial ***
	 * @param Numerador: si es true
	 *            -3. Platelet COUNT Laboratory test result.
	 * @param params agrega los parametros. (no nulo)
	 * @return
	 */
	private String getSQL(final boolean numerador, final List<Object> params) {
		final StringBuilder str = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		str.append("SELECT COUNT (DISTINCT cp.EXME_paciente_ID)  \n");
		str.append("FROM EXME_ctapac cp  \n");
		str.append("INNER JOIN EXME_actpaciente ap        ON (ap.EXME_CtaPac_ID=cp.EXME_CtaPac_ID)  \n");
		// 3.
		if (numerador) {
			str.append("INNER JOIN EXME_actpacienteindh ph    ON (ph.EXME_actpaciente_ID=ap.EXME_actpaciente_ID) \n");
			str.append("INNER JOIN EXME_actpacienteind api    ON (api.EXME_actpacienteindh_ID=ph.EXME_actpacienteindh_ID)  \n");
			str.append("INNER JOIN EXME_estudiosobx obx       ON (obx.EXME_actpacienteind_ID=api.EXME_actpacienteind_ID) \n");
			str.append("INNER JOIN EXME_LOINC         l       ON (obx.codeobx=l.value) \n");
			str.append("INNER JOIN EXME_LoincTypeDet lt       ON (l.EXME_LOINC_ID=lt.EXME_LOINC_ID)  \n");
			str.append("INNER JOIN EXME_LoincVersion lv ON (lv.EXME_LoincVersion_ID = lt.EXME_LoincVersion_ID AND lv.Stage='1' AND lv.EXME_LoincType_ID=?) \n");
		}
		// 1.
		str.append("INNER JOIN EXME_actpacientediag diag  ON (ap.EXME_actpaciente_ID=diag.EXME_actpaciente_ID) \n");
		str.append("INNER JOIN EXME_diagnostico dm ON (dm.EXME_diagnostico_ID = diag.EXME_diagnostico_ID) \n");
		str.append("INNER JOIN EXME_DiagnosisTypeDet dt   ON (dm.value = dt.code AND dt.IsActive='Y')  \n");
		str.append("INNER JOIN EXME_DiagnosisVersion dv ON (dv.EXME_DiagnosisVersion_Id = dt.EXME_DiagnosisVersion_Id AND dv.stage='1' AND dv.EXME_DiagnosisType_ID=?) \n");
		// 2.
		str.append("INNER JOIN EXME_PlanMed pm            ON (pm.EXME_ctapac_ID=cp.EXME_ctapac_ID) \n");
		str.append("INNER JOIN m_product p                ON (pm.m_product_ID=p.m_product_ID) \n");
		str.append("INNER JOIN EXME_TERAPIA_PRODUCTO  tp  ON (p.EXME_genproduct_ID=tp.EXME_genproduct_ID) \n");
		str.append("INNER JOIN EXME_THERAPIESVERSION TV ON (TV.EXME_THERAPIESVERSION_ID = tp.EXME_THERAPIESVERSION_ID AND tv.stage = '1' AND tv.EXME_TERAPIA_ID=?) \n");

		str.append("INNER JOIN EXME_paciente ep           ON (ep.EXME_paciente_ID = cp.EXME_paciente_ID)  \n");
		str.append("WHERE cp.AD_client_ID=? AND cp.AD_org_ID=? AND cp.EXME_tipopaciente_ID=? \n");
		// 1.
		str.append("AND diag.type=?  \n");// Diagnosticos codificados
		str.append("AND ").append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy')  \n");
		str.append("AND diag.Estatus<>? AND diag.IsActive='Y' \n");
		// 2.
		str.append("AND ").append(DB.truncDate("pm.startDate", "dd"));
		str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		// 3.
		if (numerador) {
			str.append("AND ").append(DB.truncDate("obx.observationdate", "dd"));
			str.append(" BETWEEN to_date(?, 'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		}
		// 4.
		if (DB.isOracle()) {
			str.append("AND edadmuse( ep.fechanac, cp.dateordered) >= 18 \n");
		} else if (DB.isPostgreSQL()) {
			str.append("AND edadmuse( ep.fechanac::date, cp.dateordered::date) >= 18 \n");
		}
		// 5.
		if (DB.isOracle()) {
			str.append("AND (cp.fechaalta - cp.dateordered) < 120 \n");
		} else if (DB.isPostgreSQL()) {
			str.append("AND extract(days from (cp.fechaalta - cp.dateordered)) < 120 \n");
		}
		// 7
		str.append("AND cp.EncounterStatus NOT IN (?,?,?)  \n");
		// 6.
		str.append("AND (cp.resstatus != ? OR cp.resstatus IS NULL)  \n");

		if (numerador) {
			params.add(this.plateletLoinc);
		}
		params.add(this.diagnosticoTypeVte);
		params.add(this.terapiaHeparin);
		params.add(Env.getAD_Client_ID(ctx));
		params.add(Env.getAD_Org_ID(ctx));
		params.add(inpatient);
		params.add(X_EXME_ActPacienteDiag.TYPE_CoderFinal);
		params.add(this.ini);
		params.add(this.fin);
		params.add(X_EXME_ActPacienteDiag.ESTATUS_Inactive);
		if (numerador) {
			params.add(this.ini);
			params.add(this.fin);
		}
		params.add(this.ini);
		params.add(this.fin);
		params.add(X_EXME_CtaPac.ENCOUNTERSTATUS_PreAdmission);
		params.add(X_EXME_CtaPac.ENCOUNTERSTATUS_Admission);
		params.add(X_EXME_CtaPac.ENCOUNTERSTATUS_Predischarge);
		params.add(X_EXME_CtaPac.RESSTATUS_ComfortCareOnly);
		return str.toString();
	}

	/**
	 * Metodo para validar que la consulta esta funcionando (JUnit)
	 * @param numerador indica si se genera el numerador o el denominador
	 * @return indica el nuemro de pacientes obtenidos segun la metrica
	 */
	public static int testPacientesMedida(final boolean numerador) {
		final GeneraArchivoVte4 bean = new GeneraArchivoVte4(null, null, Env.getCtx(), null, 0, 0);
		int id;
		if (numerador) {
			id = bean.pacientesMedida();
		} else {
			id = bean.pacientesDenominador();
		}
		return id;
	}
}
