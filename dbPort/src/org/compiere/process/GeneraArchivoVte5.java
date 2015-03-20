package org.compiere.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMEParamMetricas;
import org.compiere.model.X_EXME_CtaPac;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0375
 * VTE - 5
 * @author Rosy Velazquez
 * Modificado por Victor Perez (card 970)
 */

public class GeneraArchivoVte5{

	public GeneraArchivoVte5(final String version, final String optionRegistry, final Properties ctx, final String trxName, final int file, final int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
		this.numFiles = files;
	}

	private final Properties ctx;
	private final String trxName;

	private String ini;
	private String fin;

	private final String optionRegistry;
	private final String version;

	private final static String PQRI_NUMBER = "0375";

	private static final String archivo = "NQF_0375";
	private static final String measureGroup = "X"; //Sin grupo

	private int diagnostcTypeVte = 0;
	private int terapiaWarfarin = 0;

	private final int numFiles;
	private final int numFile;

	private int inpatient = 0;

	private final SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

	public File genera() throws Exception {

		final MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); //ya esta validado!

		//Llenar parametros de la tabla
		final Date fechaIni = param.getFecha_Ini_Vte5();
		final Date fechaFin = param.getFecha_Fin_Vte5();

		diagnostcTypeVte = param.getVTE_Diagnostic();
		terapiaWarfarin = param.getEXME_TerapiaWarafina_ID();
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
	 * Numerador
	 *<br> Calcula los pacientes
	 *<p> -1. Con un diagnostico de VTE confirmado
	 *		  Diagnostico principal o cualquiera
	 *		  Diagnostico codificado
	 *<p> -2. Warfarin Medications
	 *<p> -3. Con Instrucciones de Alta
	 *
	 *<p> -4. Pacientes dados de alta
	 *<p> -5. Discharge Status in (01, 06, 50)
	 *<p> -6. Edad > 18
	 *<p> -7. Estancia < 120 dias
	 *<p> -8. No patients Enrolled in clinical trial
	 */
	private int pacientesMedida(){
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(true, params), params.toArray());
	}

	/**
	 * Calcula los pacientes del denominador
	 * Consideraciones de numerador sin instrucciones de alta
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
	 *<p> -2. Warfarin Medications
	 *<p> -3. Con Instrucciones de Alta
	 *
	 *<p> -4. Pacientes dados de alta
	 *<p> -5. Discharge Status in (01, 06, 50)
	 *<p> -6. Edad > 18
	 *<p> -7. Estancia < 120 dias
	 *<p> -8. No patients Enrolled in clinical trial
	 */
	private String getSQL(final boolean numerador, final List<Object> params) {
		final StringBuilder str = new StringBuilder(" ");
		str.append("SELECT count (DISTINCT cp.exme_paciente_id) \n")
			.append("FROM exme_ctapac cp \n")
			.append("INNER JOIN EXME_PlanMed pm ON (pm.exme_ctapac_id = cp.exme_ctapac_id) \n")
			.append("INNER JOIN M_Product pro ON (pro.m_product_id = pm.m_product_id) \n")
			.append("INNER JOIN EXME_Terapia_Producto tp ON (tp.exme_genproduct_id = pro.exme_genproduct_id) \n")
			.append("INNER JOIN EXME_TherapiesVersion tv ON (tv.exme_therapiesversion_id = tp.exme_therapiesversion_id AND tv.exme_terapia_id = ? AND tv.stage='1' ")
			.append(" AND tv.isactive='Y' AND tp.isactive='Y' ) \n")
			.append("INNER JOIN EXME_paciente ep ON ep.exme_paciente_id = cp.exme_paciente_id \n")
			.append("INNER JOIN EXME_actpaciente ap ON ap.exme_ctapac_id = cp.exme_ctapac_id \n")
			.append("INNER JOIN EXME_actpacientediag diag ON ap.exme_actpaciente_id = diag.exme_actpaciente_id \n")
			.append("INNER JOIN EXME_DIAGNOSTICO DM ON (DM.EXME_DIAGNOSTICO_ID = DIAG.EXME_DIAGNOSTICO_ID) \n")
			.append("INNER JOIN EXME_diagnosistypeDet dtd ON (dtd.code = dm.value) \n")
			.append("INNER JOIN exme_diagnosisVersion dv ON (dv.exme_diagnosisversion_id = dtd.exme_diagnosisversion_id AND dv.stage='1' AND dv.EXME_DiagnosisType_id = ? ")
			.append(" AND dv.isactive='Y' AND dtd.isactive='Y' ) \n")
			.append("where cp.ad_client_id = ? and cp.ad_org_id = ? \n")
			.append("and cp.exme_tipopaciente_id = ? AND diag.IsActive = 'Y' \n")
			.append("AND DATE_TRUNC('day', diag.fecha_diagnostico) between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n")
			.append("AND diag.Estatus <> 'I' AND diag.IsActive = 'Y' \n")
			.append("AND DATE_TRUNC('day', pm.startDate) between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
		if (numerador) {
			str.append("AND (cp.instruccionalta is not null or cp.nombrearchivo is not null) \n");
		}
		str.append("AND cp.EncounterStatus not in (?, ?, ?) \n")
			.append("AND cp.exme_dischargestatus_id in ( \n")
			.append("	SELECT EXME_DischargeStatus_ID \n")
			.append("	FROM EXME_DischargEstatus WHERE VALUE IN ('01','06','50')) \n")
			.append("	AND edadmuse(ep.fechanac, cp.dateordered) >= 18 \n")
			.append("	AND extract(day from cp.fechaalta - cp.dateordered) < 120 ");

		params.add(terapiaWarfarin);
		params.add(diagnostcTypeVte);
		params.add(Env.getAD_Client_ID(ctx));
		params.add(Env.getAD_Org_ID(ctx));
		params.add(inpatient);
		params.add(ini);
		params.add(fin);
		params.add(ini);
		params.add(fin);
		params.add(X_EXME_CtaPac.ENCOUNTERSTATUS_PreAdmission);
		params.add(X_EXME_CtaPac.ENCOUNTERSTATUS_Admission);
		params.add(X_EXME_CtaPac.ENCOUNTERSTATUS_Predischarge);

		return str.toString();
	}

	/**
	 * Metodo para validar que la consulta este funcionando (JUnit)
	 * @param numerador indica si se genera el numerador o el denominador
	 * @return indica el nuemro de pacientes obtenidos segun la metrica
	 */
	public static int testPacientesMedida(final boolean numerador) {
		final GeneraArchivoVte5 bean = new GeneraArchivoVte5(null, null, Env.getCtx(), null, 0, 0);
		int id;
		if (numerador) {
			id = bean.pacientesMedida();
		} else {
			id = bean.pacientesDenominador();
		}
		return id;
	}
}