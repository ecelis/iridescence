package org.compiere.process;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0142
 * @author vperez
 */
public class GeneraNQF142 {
	private final Properties ctx;
	private final CLogger s_log = CLogger.getCLogger(GeneraNQF142.class);
	private final String optionRegistry;
	private final String version;
	private final static String NUMBER = "0142";
	private static final String archivo = "NQF_0142";
	private static final String measureGroup = "X";
	private final int numFiles;
	private final int numFile;

	private int diagnosisMyocard = 0;
	private int terapieAspirin = 0;
	private int disOtherHospital = 0;
	private int disExpired = 0;
	private int disLeftAgainstMA = 0;
	private int disToHomeHospice = 0;
	private int disHealtHospcie = 0;

	private String fechaIni = null;
	private String fechaFin = null;
	private final String formato = "dd/MM/yyyy";

	/**
	 * Genera el archivo XML para la metrica NQF 0142
	 * @param version
	 * @param optionRegistry
	 * @param ctx
	 * @param trxName
	 * @param file
	 * @param files
	 */
	public GeneraNQF142(final String version, final String optionRegistry, final Properties ctx, final String trxName, final int file, final int files) {
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.numFile = file;
		this.numFiles = files;
		initParams();
	}

	/**
	 * Optiene los parametros de la metrica
	 */
	private void initParams() {
		final ParamBean param = new ParamBean();
		diagnosisMyocard = param.getdMyocard();
		terapieAspirin = param.gettAspirin();
		disOtherHospital = param.getDisToHomCare();
		disExpired = param.getDisExpired();
		disLeftAgainstMA = param.getDisLeftMedical();
		disToHomeHospice = param.getDisToHome();
		disHealtHospcie = param.getDisHealtFacili();

		final SimpleDateFormat format = new SimpleDateFormat(formato);
		try {
			fechaIni = format.format(param.getFechaIni() == null ? new Date() : param.getFechaIni());
			fechaFin = format.format(param.getFechaFin() == null ? new Date() : param.getFechaFin());
		} catch (final ParseException e) {
			s_log.log(Level.SEVERE, e.getLocalizedMessage());
		}

		param.getDisToHosCare();
		param.getDisOtherHospit();
	}

	/**
	 * Obtiene el sql para numerador y denominador
	 * @return
	 */
	private String getSQL(final boolean numerador, final boolean isReport) {
		final StringBuilder sql = new StringBuilder();
		if (isReport) {
			sql.append("SELECT * FROM EXME_Paciente PA \n");
		} else {
			sql.append("SELECT COUNT(DISTINCT CP.EXME_Paciente_ID) FROM EXME_Paciente PA \n");
		}
		sql.append(" INNER JOIN EXME_CtaPac CP ON CP.EXME_Paciente_ID =PA.EXME_Paciente_ID \n")
		.append(" INNER JOIN EXME_ActPaciente AP ON AP.EXME_CtaPac_ID = CP.EXME_CtaPac_ID \n");
		if (numerador) {
			sql.append(" INNER JOIN EXME_PrescRx RX ON RX.EXME_CtaPac_ID = CP.EXME_CtaPac_ID AND RX.Tipo = 'DM' \n")
			.append(" INNER JOIN EXME_PrescRxDet RXD ON RXD.EXME_PrescRx_ID = RX.EXME_PrescRx_ID \n")
			.append(" INNER JOIN EXME_Terapia_Producto TP ON TP.EXME_GenProduct_ID = RXD.EXME_GenProduct_ID \n")
			.append(" INNER JOIN EXME_TherapiesVersion TV ON TV.EXME_TherapiesVersion_ID = TP.EXME_TherapiesVersion_ID \n")
			.append(" AND TV.Stage = '2' AND TV.EXME_Terapia_ID = ").append(terapieAspirin).append(" \n");
		}
		sql.append(" INNER JOIN EXME_ActPacienteDiag DIAG ON DIAG.EXME_ActPaciente_ID = AP.EXME_ActPaciente_ID \n")
		.append(" INNER JOIN EXME_Diagnostico DM ON DM.EXME_Diagnostico_ID = DIAG.EXME_Diagnostico_ID \n")
		.append(" INNER JOIN EXME_DiagnosisVersion DV ON DV.Stage = '2' AND DV.EXME_DiagnosisType_ID = ").append(diagnosisMyocard)
		.append(" INNER JOIN EXME_DiagnosisTypeDet DTD ON DTD.Code = DM.Value \n")
		.append(" AND DV.EXME_DiagnosisVersion_ID = DTD.EXME_DiagnosisVersion_ID \n")
		.append(" WHERE ")
		.append(" DATE_TRUNC('day', DIAG.Fecha_Diagnostico) BETWEEN ")
		.append(" TO_DATE('").append(fechaIni).append("', '").append(formato).append("') AND ")
		.append(" TO_DATE('").append(fechaFin).append("', '").append(formato).append("') \n")
		.append(" AND DIAG.Estatus <> 'I' \n")
		.append(" AND DIAG.SequenceDiag = 1 \n")
		.append(" AND DIAG.Type = 'CF' \n")
		.append(" AND DIAG.IsActive = 'Y' \n")
		.append(" AND edadmuse(PA.FechaNac, CP.DateOrdered) >= 18 \n")
		.append(" AND EXTRACT(DAY FROM CP.FechaAlta - CP.DateOrdered) < 120 \n")
		.append(" AND (CP.ResStatus != 'CO' OR CP.ResStatus IS NULL) \n")
		.append(" AND CP.EXME_DischargeStatus_ID NOT IN \n")
		.append(" 	(SELECT EXME_DischargeStatus_ID \n")
		.append(" 	FROM EXME_DischargEstatus \n")
		.append(" 	WHERE VALUE IN ( \n")
		.append("'").append(disOtherHospital).append("',")
		.append("'").append(disExpired).append("',")
		.append("'").append(disLeftAgainstMA).append("',")
		.append("'").append(disToHomeHospice).append("',")
		.append("'").append(disHealtHospcie).append("'")
		.append("))");
		return sql.toString();
	}

	/**
	 * Paciente con diagnostico principal AMI
	 * Pacientes que se les prescribio aspirin al momento de alta
	 * Paciente mayor de 18 anios
	 * Pacientes con estadia menor de 120 dias
	 * Pacientes que no tengan estudios clinicos
	 * Que no tenga alguno de los siguientes estatus to another hospital, Expired, Left against medical advice, Discharged to home for hospice care, Discharged to a health care facility for hospice care
	 * Pacientes que no tengan medidas de conformidad
	 * @return
	 */
	private int pacientesMedida() {
		return CQMeasures.executaSql(getSQL(true, false));
	}

	/**
	 * Igual que el numerador pero sin la terapia
	 */
	private int pacientesDenominador() {
		return CQMeasures.executaSql(getSQL(false, false));
	}

	/**
	 * Genera el arhivo XML para la metrica
	 * @return
	 * @throws Exception
	 */
	public File genera() throws Exception {
		final GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		final List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(NUMBER, pacientesMedida(), pacientesDenominador()));

		return xml.armaXml(archivo, measureGroup, Constantes.getSdfFecha().parse(fechaIni), Constantes.getSdfFecha().parse(fechaFin), pqris, optionRegistry, version, "1", numFile, numFiles);
	}

	/**
	 * Ejecuta las medidas de calidad para la pruebas unitarias
	 * @param numerador
	 * @return
	 */
	public static int testMeasure(final boolean numerador) {
		final GeneraNQF142 bean = new GeneraNQF142(null, null, Env.getCtx(), null, 0, 0);
		int i = 0;
		if (numerador) {
			i = bean.pacientesMedida();
		} else {
			i = bean.pacientesDenominador();
		}
		return i;
	}
}