package org.compiere.process;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MActPacienteDiag;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEParamMetricas;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0372
 * VTE - 2
 * @author Rosy Velazquez
 *  Modificado por Lorena Lama (Revisión de SQL)
 */

public class GeneraArchivoVte2{
	
	public GeneraArchivoVte2(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		this.version=version;
		this.optionRegistry=optionRegistry;
		this.ctx=ctx;
		this.trxName=trxName;
		this.numFile=file;
		this.numFiles=files;
	}
			
	private Properties ctx;
	private String trxName;		
	
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie
	
	private String ini="";
	private String fin="";
	
	private String optionRegistry="";
	private String version="";	
	
	private final static String PQRI_NUMBER="0372"; 		
	
	private String archivo="NQF_0372";
	private String measureGroup="X"; //Sin grupo	
		
	private int estacion_icu=0;
	private int terapiaVteProphylaxis=0;	
	private int diagnosisTypeVTE=0;
	private int diagnosisTypeObstretics=0;
	
	private int numFiles=1;
	private int numFile=1;
	private int inpatient=0;
	
	public File genera() throws Exception {

		final MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); // ya esta validado!

		// Llenar parametros de la tabla
		fechaIni = param.getFecha_Ini_Vte2() == null ? Env.getCurrentDate() : param.getFecha_Ini_Vte2();
		fechaFin = param.getFecha_Fin_Vte2() == null ? Env.getCurrentDate() : param.getFecha_Fin_Vte2();

		terapiaVteProphylaxis = param.getEXME_ProphylaxisVte_ID();
		estacion_icu = param.getEXME_EstServIcu_ID();
		diagnosisTypeVTE = param.getVTE_Diagnostic();
		diagnosisTypeObstretics = param.getObstretics();
		inpatient = param.getEXME_EstServ_Hosp_ID();

		// Asignar formato
		ini = Constantes.getSdfFecha().format(fechaIni);
		fin = Constantes.getSdfFecha().format(fechaFin);

		final List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER, this.pacientesMedida(), this.pacientesDenominador()));

		return new GeneraXmlMetrica(ctx).armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);
	}
	
	
	public static int testPacientesMedida(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		GeneraArchivoVte2 bean = new GeneraArchivoVte2(version, optionRegistry, ctx, trxName, file, files);
		int id = bean.pacientesMedida();
		id = bean.pacientesDenominador();
		return 1;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. Con medicamento de VTE_PROPHYLAXIS 
	 *		 1.1. el mismo dia o al dia siguiente de la admision o transferencia a la ICU
	 *		 1.2. o medicamento aplicado en la fecha final de la cirugia cuando la cirugia el dia 
	 *            o al dia siguiente de la admision a la ICU	
	 *<p> -2. Edad > 18
	 *<p> -3. Discharge date minus admission date > 2 dias y < 120 dias
	 *<p> -4. No incluir pacientes Comfort Measures Only 
	 *<p> -5. No pacientes con diagnostico principal=VTE 
	 *<p> -6.										=Obstetrics
	 *<p> -7. Pacientes dados de alta
	 *<p> -8. Cuentas con estacion de ingreso ICU o estacion actual (tranferidas)=ICU
	 *Lama: Correccion excesiva anidacion de sub-selects 
	 */
	protected int pacientesMedida() {
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(true, params), params.toArray());
	}
	
	/**
	 * Calcula los pacientes del denominador
	 * Pacientes que estan en una estacion de ICU
	 * 
	 * @return pacientes
	 * Lama: Correccion excesiva anidacion de sub-selects 
	 */
	protected int pacientesDenominador() {
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(false, params), params.toArray());
	}
	
	/**
	 * Metodo para validar que la consulta esta funcionando (JUnit)
	 * @param numerador indica si se genera el numerador o el denominador
	 * @return indica el nuemro de pacientes obtenidos segun la metrica
	 */
	public static int testPacientesMedida(final boolean numerador) {
		final GeneraArchivoVte2 bean = new GeneraArchivoVte2(null, null, Env.getCtx(), null, 0, 0);
		int id;
		if (numerador) {
			id = bean.pacientesMedida();
		} else {
			id = bean.pacientesDenominador();
		}
		return id;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes 
	 *<p> -1. Con medicamento de VTE_PROPHYLAXIS 
	 *		 1.1. el mismo dia o al dia siguiente de la admision o transferencia a la ICU
	 *		 1.2. o medicamento aplicado en la fecha final de la cirugia cuando la cirugia el dia 
	 *            o al dia siguiente de la admision a la ICU	
	 *<p> -2. Edad > 18
	 *<p> -3. Discharge date minus admission date > 2 dias y < 120 dias
	 *<p> -4. No incluir pacientes Comfort Measures Only 
	 *<p> -5. No pacientes con diagnostico principal=VTE 
	 *<p> -6.										=Obstetrics
	 *<p> -7. Pacientes dados de alta
	 *<p> -8. Cuentas con estacion de ingreso ICU o estacion actual (tranferidas)=ICU
	 */
	private String getSQL(boolean numerador, final List<Object> params){
		final StringBuilder str = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// 8. cuentas pacientes de ICU
		if(numerador){
			str.append("WITH ICU_DATES AS ( \n");
			str.append("SELECT cp.exme_ctapac_id, \n");
			str.append("       COALESCE ((SELECT ch.created \n");
			str.append("                  FROM exme_ctapacchanges ch \n");
			str.append("	              INNER JOIN exme_estserv es ON (es.exme_area_id=ch.exme_areaact_id \n");
			str.append("                                             AND es.exme_estserv_id=?  \n"); 
			str.append("                                             AND ch.exme_ctapac_id=cp.exme_ctapac_id) \n");
			str.append("                  WHERE ch.exme_areaact_id IS NOT NULL \n");
			str.append("                  ORDER BY ch.created DESC \n");
			str.append(DB.getDatabase().addPagingSQL(" ", 1, 1));//Limit 1
			str.append("                  ), cp.dateordered) AS icudate \n");
			str.append("FROM EXME_ctapac cp  \n");
			str.append("WHERE cp.AD_client_ID=? \n"); //1
			str.append("AND cp.AD_org_ID=? \n"); // 2
			str.append("AND cp.EXME_tipopaciente_ID=? \n"); //3
			str.append("AND (cp.exme_estserving_id=? OR cp.exme_estserv_id=?) \n"); //4-5
			str.append(") \n");
			// 1 -5
			params.add(estacion_icu);
			params.add(Env.getAD_Client_ID(ctx));
			params.add(Env.getAD_Org_ID(ctx));
			params.add(inpatient);
			params.add(estacion_icu);
			params.add(estacion_icu);
		}
		str.append("SELECT count(DISTINCT cp.EXME_paciente_ID)   \n");
		str.append("  FROM EXME_ctapac cp \n");
		// 8.
		if (numerador) {
			str.append("INNER JOIN ICU_DATES                ON (ICU_DATES.EXME_CtaPac_ID=cp.EXME_CtaPac_ID) \n");
		}
		str.append("INNER JOIN EXME_paciente ep ON (ep.EXME_paciente_ID=cp.EXME_paciente_ID \n");
		if (!numerador) {
			str.append("                AND cp.AD_client_ID=? \n"); // 1
			str.append("                AND cp.AD_org_ID=? \n"); // 2
			str.append("                AND cp.EXME_tipopaciente_ID=? \n"); // 3
			str.append("                AND (cp.EXME_estserving_ID=? OR cp.EXME_estserv_ID=?) \n"); // 4 - 5
			// 1 -5
			params.add(Env.getAD_Client_ID(ctx));
			params.add(Env.getAD_Org_ID(ctx));
			params.add(inpatient);
			params.add(estacion_icu);
			params.add(estacion_icu);
		}
		// 2.
		str.append("                    AND edadmuse(ep.fechanac::date, cp.dateordered::date) >= 18) \n");
		//1.  Con medicamento de VTE_PROPHYLAXIS
		if (numerador) {
			str.append("INNER JOIN EXME_PlanMed pm          ON (pm.EXME_ctapac_ID=cp.EXME_ctapac_ID \n");
			str.append("                                       AND ").append(DB.truncDate("pm.startDate", "dd"));
			str.append("                                       BETWEEN to_date(?,'dd/MM/yyyy') AND to_date(?,'dd/MM/yyyy') \n");
			str.append("                                       ) \n");
			str.append("INNER JOIN EXME_PlanMedLine pml     ON (pm.EXME_PlanMed_ID=pml.EXME_PlanMed_ID  \n");
			str.append("                                       AND pml.Estatus='AD')   \n");
			str.append("INNER JOIN m_product P              ON (pm.m_product_ID=p.m_product_id) \n");
			str.append("INNER JOIN EXME_TERAPIA_PRODUCTO tp ON (p.EXME_GENPRODUCT_ID=tp.EXME_GENPRODUCT_ID AND EXME_TERAPIA_ID=?) \n");
			str.append("LEFT JOIN  EXME_progquiro pq        ON (pq.EXME_ctapac_ID=pm.EXME_ctapac_ID \n");
			str.append("                                       AND ").append(DB.truncDate("pq.fechaInicio", "dd"));
			str.append("                                       BETWEEN to_date(?,'dd/MM/yyyy') AND to_date(?,'dd/MM/yyyy') \n");
			str.append("                                       ) \n");
			
			params.add(this.ini);
			params.add(this.fin);
			params.add(this.terapiaVteProphylaxis);
			params.add(this.ini);
			params.add(this.fin);
		}
		// 7.
		str.append("WHERE cp.EncounterStatus NOT IN (?,?,?) \n");
		str.append("  AND (cp.resstatus IS NULL OR cp.resstatus!=?) \n");
		// 3.
		str.append("  AND extract(days from (cp.fechaalta - cp.dateordered)) > 2 \n");
		str.append("  AND extract(days from (cp.fechaalta - cp.dateordered)) < 120 \n");
		// 1.
		if (numerador) {
			str.append("AND ( \n");
			// que los medicamentos se hayan aplicado dentro de los primeros 2 dias
			str.append("      extract(days from (pml.apliedDate-ICU_DATES.icudate)) <= 2 \n");
			// o que los medicamentos se hayan aplicado despues de los primeros 2 dias de la fecha final de la cirugia
			str.append("      OR (    extract(days from (pq.fechainicio-ICU_DATES.icudate)) <= 2 \n");
			str.append("          AND extract(days from (pq.fechafin-ICU_DATES.icudate)) <= 2  \n");
			str.append("         )");
			str.append("    )  \n");
		}
		// 5. 6.
		str.append(  "AND cp.EXME_CtaPac_ID NOT IN \n");
		str.append("     (SELECT ap.EXME_CtaPac_ID \n");
		str.append("      FROM EXME_actpacientediag diag \n");
		str.append("      INNER JOIN EXME_actpaciente ap      ON (ap.EXME_actpaciente_ID=diag.EXME_actpaciente_ID) \n");
		str.append("      INNER JOIN EXME_DIAGNOSTICO DM ON (DM.EXME_DIAGNOSTICO_ID = DIAG.EXME_DIAGNOSTICO_ID) \n");
		str.append("	  INNER JOIN EXME_DiagnosisTypeDet dt ON (dt.code = DM.value");
		str.append("                                          AND dt.IsActive='Y' \n");
		str.append("                                          AND dt.EXME_DiagnosisType_ID IN (?,?)) \n");
		str.append("      WHERE diag.sequencediag=1 \n");
		str.append("        AND diag.type=? \n");
		str.append("        AND ").append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		str.append("        BETWEEN to_date(?,'dd/MM/yyyy') AND to_date(?,'dd/MM/yyyy') \n");
		str.append("        AND diag.Estatus<>? AND diag.IsActive='Y' ) \n");
		
		// where
		params.add(MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission);
		params.add(MEXMECtaPac.ENCOUNTERSTATUS_Admission);
		params.add(MEXMECtaPac.ENCOUNTERSTATUS_Predischarge);
		params.add(MEXMECtaPac.RESSTATUS_ComfortCareOnly);
		// subselect
		params.add(this.diagnosisTypeVTE);
		params.add(this.diagnosisTypeObstretics);
		params.add(MActPacienteDiag.TYPE_CoderFinal);
		params.add(this.ini);
		params.add(this.fin);
		params.add(MActPacienteDiag.ESTATUS_Inactive);
		
		return str.toString();
	}
}
