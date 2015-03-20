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
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0435
 * Stroke-2
 * @author Rosy Velazquez
 * Modificado por Victor Perez (card 971)
 */
public class GeneraArchivoStroke2 {

	public GeneraArchivoStroke2(final String version, final String optionRegistry, final Properties ctx, final String trxName, final int file, final int files){
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

	private final static String PQRI_NUMBER = "0435";

	private final static String archivo = "NQF_0435";
	private final static String measureGroup = "X"; //Sin grupo

	private int diagnosisTypeIschemic_ID = 0;
	private int terapiaAntitromboticId = 0;

	private final int numFiles;
	private final int numFile;
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
		final Date fechaIni = param.getFecha_Ini_Urgencia_S2();
		final Date fechaFin = param.getFecha_Fin_Urgencia_S2();

		diagnosisTypeIschemic_ID = param.getIschemic_Stroke();
		terapiaAntitromboticId = param.getEXME_TerapiaAntitrombotic_ID();
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
	 *<p>Calcula los pacientes
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke)
	 *					 como diagnostico principal.
	 *					 que sea un diagnostico codificado
	 *<p> -2. <i>(A)</i> Y que tengan terapia (antithrombolytic therapy) al ser dados de alta (discharge medications).
	 *<p> -3. Y que la cuenta paciente se haya dado de alta
	 *<p> -4. Y que sean mayores de 18 anios
	 *<p> -5. El resstatus de la cuenta paciente sea diferente de 'Comfort Measures Only'
	 *<p> -6. El Estatus de alta de la cuenta paciente sea diferente de values (02, 07, 20, 43, 50, 51)
	 * @return int numero de Pacientes
	 */
	//TODO agregar exclusion "Patients admitted for Elective Carotid Intervention"
	private int pacientesMedida() {
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(true, params), params.toArray());
	}

	/**
	 * Calcula los pacientes del denominador
	 * Numerador menos la Terapia aplicada
	 * @return pacientes
	 */
	private int pacientesDenominador() {
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(false, params), params.toArray());
	}
	
	/**
	 * Obtiene el String para el numerado o denominador
	 *<p>Calcula los pacientes
	 *<p> -1. <i>(B)</i> Que tengan un diagnostico de derrame cerebral (ischemicStroke)
	 *					 como diagnostico principal.
	 *					 que sea un diagnostico codificado
	 *<p> -2. <i>(A)</i> Y que tengan terapia (antithrombolytic therapy) al ser dados de alta (discharge medications).
	 *<p> -3. Y que la cuenta paciente se haya dado de alta
	 *<p> -4. Y que sean mayores de 18 anios
	 *<p> -5. El resstatus de la cuenta paciente sea diferente de 'Comfort Measures Only'
	 *<p> -6. El Estatus de alta de la cuenta paciente sea diferente de values (02, 07, 20, 43, 50, 51)
	 * @param numerador indica si se genera el numerador o el denominador
	 * @param params lista a la que le agrega los marametros no puede ser null
	 * @return regresala consulta para el numerador o denominador
	 */
	private String getSQL(final boolean numerador, final List<Object> params){
		final StringBuilder str = new StringBuilder();
		str.append("SELECT count(DISTINCT cp.exme_paciente_id) \n");
		str.append("FROM exme_ctapac cp \n");
		if (numerador) {
			str.append("inner join exme_prescrx rx on rx.exme_ctapac_id = cp.exme_ctapac_id and rx.tipo = 'DM' \n");
			str.append("inner join exme_prescrxdet rxd on rxd.exme_prescrx_id = rx.exme_prescrx_id \n");
			str.append("inner join exme_terapia_producto tp on tp.exme_genproduct_id = rxd.exme_genproduct_id \n");
			str.append("inner join exme_therapiesversion tv on tv.exme_therapiesversion_id = tp.exme_therapiesversion_id and tv.EXME_TERAPIA_ID = ? and tv.stage='1' \n");			//2
			params.add(terapiaAntitromboticId);
		}
		str.append("inner join exme_actpaciente ap on ap.exme_ctapac_id = cp.exme_ctapac_id \n");
		str.append("inner join exme_actpacientediag diag on diag.exme_actpaciente_id = ap.exme_actpaciente_id \n");
		str.append("inner join exme_diagnostico dm on dm.exme_diagnostico_id = diag.exme_diagnostico_id \n");
		str.append("inner join exme_diagnosistypedet dtd on (dtd.code = dm.value and dtd.IsActive = 'Y') \n");
		str.append("inner join exme_diagnosisversion dv on (dv.exme_diagnosisversion_id = dtd.exme_diagnosisversion_id and dv.EXME_DiagnosisType_ID = ? and dv.stage='1') \n");	//1
		str.append("inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id \n");
		str.append("where cp.exme_tipopaciente_id = ? AND cp.ad_client_id = ? AND cp.ad_org_id = ? \n");
		str.append("AND cp.exme_dischargestatus_id not in  ( \n");			//6
		str.append("	SELECT EXME_DischargeStatus_ID \n");
		str.append("	FROM EXME_DischargEstatus WHERE VALUE IN ('02','07','20','43','50','51')) \n");	
		str.append("AND cp.EncounterStatus not in (?,?,?) \n");				//3
		str.append("AND cp.dateOrdered >= to_date(?, 'dd/MM/yyyy') AND cp.fechaAlta <= to_date(?, 'dd/MM/yyyy') \n");
		str.append("AND (cp.resstatus != ? or cp.resstatus is null) \n");	//5
		str.append("AND diag.sequencediag = 1 and diag.type = 'CF' \n");
		str.append("AND ").append(DB.truncDate("diag.fecha_diagnostico", "dd")).append(" between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy') \n");
		str.append("AND diag.IsActive = 'Y' AND diag.Estatus <> ? AND edadmuse(ep.fechanac, cp.dateordered) >= 18 ");		//4
		
		params.add(diagnosisTypeIschemic_ID);
		params.add(inpatient);
		params.add(Env.getAD_Client_ID(ctx));
		params.add(Env.getAD_Org_ID(ctx));
		params.add(X_EXME_CtaPac.ENCOUNTERSTATUS_PreAdmission);
		params.add(X_EXME_CtaPac.ENCOUNTERSTATUS_Admission);
		params.add(X_EXME_CtaPac.ENCOUNTERSTATUS_Predischarge);
		params.add(ini);
		params.add(fin);
		params.add(X_EXME_CtaPac.RESSTATUS_ComfortCareOnly);
		params.add(ini);
		params.add(fin);
		params.add(X_EXME_ActPacienteDiag.ESTATUS_Inactive);
		
		return str.toString();
	}
	
	/**
	 * Metodo para validar que la consulta esta funcionando (JUnit)
	 * @param numerador indica si se genera el numerador o el denominador
	 * @return indica el nuemro de pacientes obtenidos segun la metrica
	 */
	public static int testPacientesMedida(final boolean numerador) {
		final GeneraArchivoStroke2 bean = new GeneraArchivoStroke2(null, null, Env.getCtx(), null, 0, 0);
		int id;
		if (numerador) {
			id = bean.pacientesMedida();
		} else {
			id = bean.pacientesDenominador();
		}
		return id;
	}
}