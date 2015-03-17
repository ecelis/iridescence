package org.compiere.process;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MActPacienteDiag;
import org.compiere.model.MEXMECtaPac;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;

/**
 * Genera XML para la metrica NQF 0639
 * @author vperez
 */
public class GeneraNQF639 {
	private final Properties ctx;
	private final CLogger s_log = CLogger.getCLogger(GeneraNQF639.class);
	private final String optionRegistry;
	private final String version;
	private final static String NUMBER = "0639";
	private static final String archivo = "NQF_0639";
	private static final String measureGroup = "X";
	private final int numFiles;
	private final int numFile;

	private int diagAMI = 0;
	private int theraStatin = 0;
	private int loincLDL = 0;
	private int loincUOM = 0;
	private int disOtherHospital = 0;
	private int disExpired = 0;
	private int disLeftAgainstMA = 0;
	private int disToHomeHospice = 0;
	private int disHealtHospcie = 0;
	private String fechaIni = null;
	private String fechaFin = null;
	private final String formato = "dd/MM/yyyy";
	
	/**
	 * Genera el archivo XML para la metrica NQF 0639
	 * @param version
	 * @param optionRegistry
	 * @param ctx
	 * @param trxName
	 * @param file
	 * @param files
	 */
	public GeneraNQF639(final String version, final String optionRegistry, final Properties ctx, final String trxName, final int file, final int files) {
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
		diagAMI = param.getdMyocard();
		theraStatin = param.gettStatin();
		loincLDL = param.getlLDL();
		loincUOM = param.getLdlMedid();
		
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
	 * Obtiene consulta de Numerador y Denominador
	 * 1 Paciente con diagnostico principal AMI
	 * 2 Pacientes que se les prescribio statin medication al momento de alta
	 * 3 Paciente mayor de 18 anios
	 * 4 Pacientes con estadia menor de 120 dias
	 * 5 Pacientes que no tengan medidas de conformidad
	 * 6 Pacientes que no tengan estudios clinicos
	 * 7 Que no tenga alguno de los siguientes estatus to home for hospice care, to a health care facility for hospice care, expired, left against medical advice, to another hospital
	 * 8 Pacientes que no tengan LDL menor a 100 mg/dL dentro de las primeras 24 horas de llegar al hospital o 30 dias antes de llegar al hospital y dado de alta con statin
	 */
	private String getSQL(final boolean numerador, final boolean report) {
		StringBuilder sql = new StringBuilder();
		if (report) {
			sql.append("SELECT PA.* \n");
		} else {
			sql.append("SELECT COUNT(DISTINCT CP.EXME_Paciente_ID) \n");
		}
		
		sql.append(" FROM EXME_Paciente PA \n")
		.append(" INNER JOIN EXME_CtaPac CP ON CP.EXME_Paciente_id = PA.EXME_Paciente_ID \n")
		.append(" INNER JOIN EXME_CtaPacDatos CPD ON CP.EXME_CtaPac_ID = CPD.EXME_CtaPac_ID \n")
		//AMI patients and principal diagnosis
		.append(" INNER JOIN EXME_ActPaciente AP ON AP.EXME_CtaPac_ID = CP.EXME_CtaPac_ID \n")
		.append(" INNER JOIN EXME_ActPacienteDiag DIAG ON DIAG.EXME_ActPaciente_ID = AP.EXME_ActPaciente_ID \n")
		.append(" INNER JOIN EXME_Diagnostico DM ON DM.EXME_Diagnostico_ID = DIAG.EXME_Diagnostico_ID \n")
		.append(" INNER JOIN EXME_DiagnosisTypeDet DTD ON DTD.Code = DM.Value \n")
		.append(" INNER JOIN EXME_DiagnosisVersion DV ON DV.EXME_DiagnosisVersion_ID = DTD.EXME_DiagnosisVersion_ID \n")
		.append(" AND DV.Stage='2' AND DV.IsActive='Y' AND DV.EXME_DiagnosisType_ID = ").append(diagAMI).append(" \n");
		if (numerador) {
			//patients who are prescribed statin medication at hospital discharge.
			sql.append(" INNER JOIN EXME_PrescRx RX ON RX.EXME_CtaPac_ID = CP.EXME_CtaPac_ID AND RX.Tipo = 'DM' \n")
			.append(" INNER JOIN EXME_PrescRxDet RXD ON RXD.EXME_PrescRx_ID = RX.EXME_PrescRx_ID \n")
			.append(" INNER JOIN EXME_Terapia_Producto TP ON TP.EXME_GenProduct_ID = RXD.EXME_GenProduct_ID \n")
			.append(" INNER JOIN EXME_TherapiesVersion TV ON TV.EXME_TherapiesVersion_ID = TP.EXME_TherapiesVersion_ID \n")
			.append(" AND TV.Stage= '2' AND TV.IsActive = 'Y' AND TV.EXME_Terapia_ID = ").append(theraStatin).append(" \n");
		}
		sql.append(" LEFT JOIN EXME_ActPacienteINDH INDH ON INDH.EXME_ActPaciente_ID = AP.EXME_ActPaciente_ID \n")
		.append(" LEFT JOIN EXME_ActPacienteIND IND ON IND.EXME_ActPacienteINDH_ID = INDH.EXME_ActPacienteINDH_ID \n")
		.append(" LEFT JOIN EXME_EstudiosOBX OBX ON OBX.EXME_ActPacienteIND_ID = IND.EXME_ActPacienteIND_ID \n")
		.append(" AND OBX.Observation >= '100' AND OBX.ObservationDate BETWEEN (CPD.ArrivalDate-30) AND (CPD.ArrivalDate + 1) \n")
		.append(" AND OBX.C_UOM_ID = ").append(loincUOM).append(" \n")
		.append(" LEFT JOIN EXME_Loinc LC ON LC.Value = OBX.CodeOBX \n")
		.append(" LEFT JOIN EXME_LoincTypeDet LTD ON LTD.EXME_Loinc_ID = LC.EXME_Loinc_ID \n")
		.append(" LEFT JOIN EXME_LoincVersion LCV ON LCV.EXME_LoincVersion_ID = LTD.EXME_LoincVersion_ID \n")
		.append(" AND LCV.IsActive = 'Y' AND LCV.Stage= '2' AND LCV.EXME_LoincType_ID = ").append(loincLDL).append(" \n")

		.append(" LEFT JOIN EXME_DischargEstatus DE ON DE.EXME_DischargEstatus_ID = CP.EXME_DischargEstatus_ID AND DE.VALUE IN ('")
		.append(disOtherHospital).append("', '")
		.append(disExpired).append("', '")
		.append(disLeftAgainstMA).append("', '")
		.append(disToHomeHospice).append("', '")
		.append(disHealtHospcie).append("') \n")
		
		.append(" WHERE CP.EncounterStatus NOT IN ('")
		.append(MEXMECtaPac.ENCOUNTERSTATUS_Admission).append("', '")
		.append(MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission).append("', '")
		.append(MEXMECtaPac.ENCOUNTERSTATUS_Predischarge).append("')")
		//Que no tenga registros de resultados de laboratorio con los filtros indicados en los left join
		.append(" AND OBX.EXME_EstudiosOBX_ID IS NULL \n")
		.append(" AND DIAG.SequenceDiag = 1 AND DIAG.Type= '").append(MActPacienteDiag.TYPE_CoderFinal).append("' AND")
		.append(" DATE_TRUNC('day', DIAG.Fecha_Diagnostico) ")
		.append(" BETWEEN TO_DATE('").append(fechaIni).append("','dd/MM/yyyy') AND TO_DATE('").append(fechaFin).append("','dd/MM/yyyy') \n")
		.append(" AND DIAG.Estatus<>'").append(MActPacienteDiag.ESTATUS_Inactive).append("' AND DIAG.IsActive='Y' \n")
		//Comfort Measures Only documented
		.append(" AND (CP.ResStatus != '").append(MEXMECtaPac.RESSTATUS_ComfortCareOnly).append("' OR CP.ResStatus IS NULL) \n")
		.append(" AND CP.DateOrdered >= TO_DATE('").append(fechaIni).append("', 'dd/MM/yyyy') AND CP.FechaAlta <= TO_DATE('").append(fechaFin).append("', 'dd/MM/yyyy') \n")
		//Patients less than 18 years of age
		.append(" AND edadmuse(PA.fechanac, CP.dateordered) >= 18 \n")
		//No los Discharge Status indicados en el left join
		.append(" AND DE.EXME_DischargEstatus_ID IS NULL ");
		
		return sql.toString();
	}
	
	/**
	 * Obtiene consulta de Numerador y Denominador
	 * 1 Paciente con diagnostico principal AMI
	 * 2 Pacientes que se les prescribio statin medication al momento de alta
	 * 3 Paciente mayor de 18 anios
	 * 4 Pacientes con estadia menor de 120 dias
	 * 5 Pacientes que no tengan medidas de conformidad
	 * 6 Pacientes que no tengan estudios clinicos
	 * 7 Que no tenga alguno de los siguientes estatus to home for hospice care, to a health care facility for hospice care, expired, left against medical advice, to another hospital
	 * 8 Pacientes que no tengan LDL menor a 100 mg/dL dentro de las primeras 24 horas de llegar al hospital o 30 dias antes de llegar al hospital y dado de alta con statin
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
	 * Ejecuta las medidas de calidad para la pruebas unitarias
	 * @param numerador
	 * @return
	 */
	public static int testMeasure(final boolean numerador) {
		final GeneraNQF639 bean = new GeneraNQF639(null, null, Env.getCtx(), null, 0, 0);
		int i = 0;
		if (numerador) {
			i = bean.pacientesMedida();
		} else {
			i = bean.pacientesDenominador();
		}
		return i;
	}
}