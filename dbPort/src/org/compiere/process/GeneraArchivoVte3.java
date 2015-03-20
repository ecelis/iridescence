package org.compiere.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MActPacienteDiag;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEParamMetricas;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Genera xml para NQF 0373
 * VTE - 3
 * @author Rosy Velazquez
 */

public class GeneraArchivoVte3{

	public GeneraArchivoVte3(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
		this.numFiles = files;
	}

	private Properties ctx;
	private String trxName;

	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie

	private String ini = "";
	private String fin = "";

	private String optionRegistry="";
	private String version = "";

	private final static String PQRI_NUMBER = "0373";

	private String archivo = "NQF_0373";
	private String measureGroup = "X"; //Sin grupo

	private int terapiaAnticoagulanteVte = 0;
	private int terapiaWarafina = 0;

	private int diagnosticoTypeVte = 0;

	private int numFiles = 1;
	private int numFile = 1;
	private int inpatient = 0;

	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

	public File genera() throws Exception {

		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName); //ya esta validado!

		//Llenar parametros de la tabla
		fechaIni = param.getFecha_Ini_Vte3();
		fechaFin = param.getFecha_Fin_Vte3();

		terapiaAnticoagulanteVte = param.getEXME_AnticoagulanteVte_ID();
		terapiaWarafina = param.getEXME_TerapiaWarafina_ID();
		diagnosticoTypeVte = param.getVTE_Diagnostic();
		inpatient = param.getEXME_EstServ_Hosp_ID();

		//Asignar formato
		ini = f.format(fechaIni);
		fin = f.format(fechaFin);

		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		pqris.add(CQMeasures.pqri(PQRI_NUMBER, this.pacientesMedida(), this.pacientesDenominador()));

		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);

		return generado;
	}

	
	private String getSQL(final boolean numerador, final List<Object> params) {
		StringBuilder str = new StringBuilder();
		str.append("select count (DISTINCT cp.exme_paciente_id) ");
		str.append("\n from exme_ctapac cp \n ");
		str.append("  inner join exme_paciente ep on ep.exme_paciente_id = cp.exme_paciente_id  \n");
		str.append("  where cp.ad_client_id = ? \n " );
			str.append("   and cp.ad_org_id = ? \n" );
			str.append("   and cp.exme_tipopaciente_id = ? \n" );
			str.append("   and cp.exme_paciente_id in \n");
		
			params.add(Env.getAD_Client_ID(ctx));
			params.add( Env.getAD_Org_ID(ctx));
			params.add(inpatient);
			
			//1.
		str.append("(select ap.exme_paciente_id from exme_actpacientediag diag\n"); 
			str.append("  INNER JOIN EXME_actpaciente ap ON (ap.EXME_actpaciente_ID=diag.EXME_actpaciente_ID)\n"); 
str.append("INNER JOIN EXME_DIAGNOSTICO DM ON (DM.EXME_DIAGNOSTICO_ID = DIAG.EXME_DIAGNOSTICO_ID) \n");
str.append("INNER JOIN EXME_DiagnosisTypeDet dt ON (dt.code = DM.value \n");
str.append(" AND dt.IsActive='Y') \n");
		str.append(" AND dt.EXME_DiagnosisType_ID IN (?) \n");
		str.append(" 	  inner join exme_diagnosisVersion dv on (dv.exme_diagnosisVersion_id = dt.exme_diagnosisVersion_ID AND dv.stage = '1')\n");
		str.append("      WHERE diag.sequencediag=1 \n");
		str.append("        AND diag.type='CF' \n");
		str.append("        AND ").append(DB.truncDate("diag.fecha_diagnostico", "dd"));
		str.append("        BETWEEN to_date(?,'dd/MM/yyyy') AND to_date(?,'dd/MM/yyyy') \n");
		str.append("        AND diag.Estatus<>? AND diag.IsActive='Y')  \n");

		params.add( this.diagnosticoTypeVte);
		params.add(this.ini);
		params.add(  this.fin);
		params.add(MActPacienteDiag.ESTATUS_Inactive);

		//2.
					str.append("   AND cp.exme_ctapac_id in \n");
						str.append("    (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm\n"); 
						str.append("      inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID\n"); 
						str.append("      where pm.m_product_id in (select m_product_id from m_product where exme_genproduct_id in\n");
							str.append("       (SELECT EXME_GENPRODUCT_ID\n");
								str.append("        FROM EXME_TERAPIA_PRODUCTO\n"); 
								str.append("INNER JOIN EXME_THERAPIESVERSION TV ON (TV.EXME_THERAPIESVERSION_ID = EXME_TERAPIA_PRODUCTO.EXME_THERAPIESVERSION_ID)\n"); 
								str.append("WHERE TV.EXME_TERAPIA_ID = ? and TV.STAGE='1'))\n");
						str.append("      and\n      ");
						str.append(DB.truncDate("pm.startDate","dd"));
						str.append("\n between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')) \n");
	
						
						params.add(this.terapiaWarafina);
						params.add(this.ini);
						params.add(this.fin);	
		
			if(numerador){			
			//3.
					str.append("    AND cp.exme_ctapac_id in \n");
						str.append("     (select distinct pm.exme_ctapac_id from  EXME_PlanMed pm \n");
						str.append("     inner join EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID \n");
						str.append("     where pm.m_product_id in (select m_product_id from m_product where exme_genproduct_id in  \n");
							str.append("      (SELECT EXME_GENPRODUCT_ID	\n"); 
							str.append("       FROM EXME_TERAPIA_PRODUCTO\n") ;
							str.append("       INNER JOIN EXME_THERAPIESVERSION TV ON (TV.EXME_THERAPIESVERSION_ID = EXME_TERAPIA_PRODUCTO.EXME_THERAPIESVERSION_ID)\n");
							str.append("       WHERE TV.EXME_TERAPIA_ID = ? and TV.STAGE='1'))\n"); 
							str.append("      and\n      ");
						str.append(DB.truncDate("pm.startDate","dd"));
						str.append("\n between to_date(?, 'dd/MM/yyyy') and to_date(?, 'dd/MM/yyyy')) \n");
		
						params.add(this.terapiaAnticoagulanteVte);
						params.add(this.ini);
						params.add(this.fin);
			}
		//4.
		str.append("    AND edadmuse( ep.fechanac, cp.dateordered) >= 18 \n");

		//5.
		str.append("    AND extract(day from cp.fechaalta - cp.dateordered) < 120 ");
		//6.
		str.append("    AND cp.EncounterStatus not in (?,?,?) \n");
		//7.
		str.append("    AND (cp.resstatus != ? or cp.resstatus is null)\n");

		//verificar params 
		
		
		params.add(MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission);
		params.add(MEXMECtaPac.ENCOUNTERSTATUS_Admission);
		params.add(MEXMECtaPac.ENCOUNTERSTATUS_Predischarge);
		params.add(MEXMECtaPac.RESSTATUS_ComfortCareOnly);
		
		
		//pacientes = CQMeasures.executaSql(str.toString(),params.toArray());
		
		return str.toString();
	
		
	}
	
	public static int testPacientesMedida(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		GeneraArchivoVte3 bean = new GeneraArchivoVte3(version, optionRegistry, ctx, trxName, file, files);
		int id = bean.pacientesMedida();
		return 1;
	}
	
	/**
	 * Numerador
	 *<br> Calcula los pacientes
	 *<p> -1. con un diagnostico de VTE confirmado
	 *		  Diagnostico principal o cualquiera
	 *		  Diagnostico codificado
	 *<p> -2. Terapia de Warfarina durante su estancia en hospital
	 *<p> -3. Terapia Anticoagulante	***Numerator***
	 *<p> -4. Edad > 18
	 *<p> -5. Discharge date minus admission date < 120 dias
	 *<p> -6. Pacientes dados de alta
	 *<p> -7. No pacientes con Comfort Measures Only
	 */
	//TODO Revisar en que rangos de dias es el overlap
	protected int pacientesMedida(){
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(true, params), params.toArray());
		
	}

	/**
	 * Calcula los pacientes del denominador
	 * mismas consideraciones del numerador
	 * Sin terapia Anticoagulante
	 * @return pacientes
	 */
	protected int pacientesDenominador(){
		final List<Object> params = new ArrayList<Object>();
		return CQMeasures.executaSql(getSQL(false, params), params.toArray());
	}
	
	/**
	 * Metodo para validar que la consulta esta funcionando (JUnit)
	 * @param numerador indica si se genera el numerador o el denominador
	 * @return indica el nuemro de pacientes obtenidos segun la metrica
	 */
	public static int testPacientesMedida(final boolean numerador) {
		final GeneraArchivoVte3 bean = new GeneraArchivoVte3(null, null, Env.getCtx(), null, 0, 0);
		int id;
		if (numerador) {
			id = bean.pacientesMedida();
		} else {
			id = bean.pacientesDenominador();
		}
		return id;
	}
	
}
