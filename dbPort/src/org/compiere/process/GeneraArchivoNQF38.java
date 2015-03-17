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
 * Genera xml para NQF 0038
 * Childhood Immunization Status
 * @author Rosy Velazquez
 */
public class GeneraArchivoNQF38{
	
	public GeneraArchivoNQF38(String version, String optionRegistry, Properties ctx, String trxName, int file, int files){
		this.version = version;
		this.optionRegistry = optionRegistry;
		this.ctx = ctx;
		this.trxName = trxName;
		this.numFile = file;
		this.numFiles = files;
	}

	private Properties ctx;
	private String trxName;
	
	private int numFiles = 1;
	private int numFile = 1;	
	
	//Vacunas IDs
	private int diphtheria = 0; //DTaP	
	private int polio = 0;  //IPV
	private int mumps = 0; //MMR
	private int influenzaB = 0; //HiB
	private int hepatitisB = 0; //Hepatitis B
	private int chickenPox = 0; //VZV
	private int pneumococcal = 0; //pneumococcal
	private int hepatitisA = 0; //hepatitisA
	private int rotavirus = 0;  //rotavirus
	private int influenza = 0;  //influenza
	
	//Numero de Vacunas
	private int diphtheriaNum = 0;	
	private int polioNum = 0;	
	private int mumpsNum = 0;	
	private int influenzaBNum = 0;
	private int hepatitisBNum = 0;
	private int chickenPoxNum = 0; 
	private int pneumococcalNum = 0;
	private int hepatitisANum = 0;
	private int rotavirusNum = 0; 
	private int influenzaNum = 0;
		
	private Date fechaIni; //para busqueda en querie
	private Date fechaFin; //para busqueda en querie	
	private String strIni = "";
	private String strFin = "";
	
	private String optionRegistry="";
	private String version = "";		
	
	private final static String PQRI_NUMBER = "NQF-0038"; //Sin pqri number
	
	private String archivo = "NQF_0038";
	private String measureGroup = "X"; //Sin grupo
		
	private int outpatient = 0;
	private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");	
		
	public File genera() throws Exception {		
		
		MEXMEParamMetricas param = MEXMEParamMetricas.getParamMetricas(ctx, trxName);
		
		//fechas
		this.fechaIni = param.getFecha_Ini_Vacunas();
		this.fechaFin = param.getFecha_Fin_Vacunas();	
		
		strIni = f.format(fechaIni);
		strFin = f.format(fechaFin);
		outpatient = param.getOutpatient();
		
		//Cargar numero de dosis de vacunas de parametros
		diphtheriaNum = param.getNumDiphteria();		
		polioNum = param.getNumPolio();
		mumpsNum = param.getNumMumps();		
		influenzaBNum = param.getNumInfluenzaB();
		hepatitisBNum = param.getNumHepatitisB();
		chickenPoxNum = param.getNumChickenPox(); 
		pneumococcalNum = param.getNumPneumococcal();
		hepatitisANum = param.getNumHepatitisA();
		rotavirusNum = param.getNumRotavirus(); 
		influenzaNum = param.getNumFlu();
		
		//Cargar ids vacunas de parametros		
		diphtheria = param.getEXME_VacunaDiphteria_ID();		
		polio = param.getEXME_VacunaPolio_ID();		
		mumps = param.getEXME_VacunaMumps_ID();		
		influenzaB = param.getEXME_VacunaInfluenzaB_ID();
		hepatitisB = param.getEXME_VacunaHepatitisB_ID();
		chickenPox = param.getEXME_VacunaChickenPox_ID(); 
		pneumococcal = param.getEXME_VacunaPneumococcal_ID();
		hepatitisA = param.getEXME_VacunaHepatitisA_ID();
		rotavirus = param.getEXME_VacunaRotavirus_ID();
		influenza = param.getEXME_VacunaFlu_ID();
		
		GeneraXmlMetrica xml = new GeneraXmlMetrica(ctx);
		List<String[]> pqris = new ArrayList<String[]>();
		
		int denominador = this.executaQueryDenominator();
		
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_1_DTaP", this.executaQueryNumerator(diphtheria, diphtheriaNum), denominador));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_2_IPV", this.executaQueryNumerator(polio, polioNum), denominador));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_3_MMR", this.executaQueryNumerator(mumps, mumpsNum), denominador));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_4_HiB", this.executaQueryNumerator(influenzaB, influenzaBNum), denominador));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_5_HEPATITIS-B", this.executaQueryNumerator(hepatitisB, hepatitisBNum), denominador));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_6_VZV", this.executaQueryNumerator(chickenPox, chickenPoxNum), denominador));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_7_PNEUMOCOCCAL", this.executaQueryNumerator(pneumococcal, pneumococcalNum), denominador));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_8_HEPATITIS-A", this.executaQueryNumerator(hepatitisA, hepatitisANum), denominador));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_9_ROTAVIRUS", this.executaQueryNumerator(rotavirus, rotavirusNum), denominador));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_10_INFLUENZA", this.executaQueryNumerator(influenza, influenzaNum), denominador));
		
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_11", this.executaNum11(), denominador));
		pqris.add(CQMeasures.pqri(PQRI_NUMBER + "_12", this.executaNum12(), denominador));			
		
		File generado = xml.armaXml(archivo, measureGroup, fechaIni, fechaFin, pqris, optionRegistry, version, "1", numFile, numFiles);		
		
		return generado;
	}	
	
	/**
	 * Calcula el numerador para cada vacuna
	 * 1. Edad >=1 y <2
	 * que la fecha de aplicacion de las vacunas este en el periodo de la medida
	 * @return pacientes
	 */
	//FIXME oque cumpla los 2 años en el periodo de la medida: fecha_nac between ini-fin ?
	private int executaQueryNumerator(int vacunaID, int numVacunas){
		//dos anios cumplidos antes del periodo inicial.
		int pacientes=0;					
		
		StringBuilder str = new StringBuilder();
		str.append(" SELECT COUNT(*) \n");
		str.append(" FROM \n");
		str.append(" (SELECT hv.exme_paciente_id \n");
		str.append(" FROM EXME_Hist_Vacuna hv \n");
		str.append(" inner join exme_paciente ep ");
		str.append(" inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id on ep.exme_paciente_id = hv.exme_paciente_id \n");
		str.append(" WHERE hv.ad_client_id = ? AND hv.ad_org_id = ? AND hv.exme_vacuna_id = ? AND cta.exme_tipopaciente_id = ? \n");
		str.append(" AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1 AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND \n");
		str.append(DB.truncDate("hv.fechaaplica", "dd"));
		
		str.append(" between to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" GROUP BY hv.exme_paciente_id ");
		str.append(" having count(distinct hv.exme_hist_vacuna_id) >= ?) SBQ \n");

		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), vacunaID, outpatient, strIni, strIni,strIni, strFin, numVacunas);			
			
		return pacientes;
	}
	
	/**
	 * Calcula el denominador para la medida 
	 * 1. Edad >=1 y Edad<2
	 *  Edad calculada a la fecha inicial de la medida
	 * 2. Encounter outpatient w/PCP & obgyn
	 * @return
	 */
	private int executaQueryDenominator(){
		
		int pacientes = 0;
		StringBuilder str = new StringBuilder();
		str.append(" SELECT count(distinct ep.exme_paciente_id) \n");
		str.append(" FROM EXME_Paciente ep \n");
		str.append(" inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id \n");
		str.append(" WHERE cta.ad_client_id = ? AND cta.ad_org_id = ? AND cta.exme_tipopaciente_id = ? AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1  \n");
		str.append(" AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND ep.isactive = 'Y' \n");
		
		
		pacientes = CQMeasures.executaSql(str.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), outpatient, this.strIni, this.strIni);			
		
		return pacientes;
	}
	
	private int executaNum11(){
		int pacientes = 0;
		
		StringBuilder str = new StringBuilder();
		str.append(" SELECT COUNT(*) \n");
		str.append(" FROM \n");
		str.append(" \t	(SELECT hv.exme_paciente_id as paciente_id \n");
		str.append(" \t	FROM EXME_Hist_Vacuna hv \n");
		str.append(" \t	inner join exme_paciente ep \n");
		str.append(" \t	inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id  on ep.exme_paciente_id = hv.exme_paciente_id \n");
		str.append(" \t	WHERE hv.ad_client_id = ? AND hv.ad_org_id = ? AND hv.exme_vacuna_id = ? AND cta.exme_tipopaciente_id = ? \n");
		str.append(" \t	AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1 AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND \n");
		str.append(		DB.truncDate ("hv.fechaaplica", "dd"));
		
		str.append(" \t	between to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" \t	GROUP BY hv.exme_paciente_id ");
		str.append(" \t	having count(distinct hv.exme_hist_vacuna_id) >= ?) dtap \n");
		str.append(" inner join \n");
		str.append(" \t	(SELECT hv.exme_paciente_id as paciente_id \n");
		str.append(" \t	FROM EXME_Hist_Vacuna hv \n");
		str.append(" \t	inner join exme_paciente ep \n");
		str.append(" \t	inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id on ep.exme_paciente_id = hv.exme_paciente_id \n");
		str.append(" \t	WHERE hv.ad_client_id = ? AND hv.ad_org_id = ? AND hv.exme_vacuna_id = ? AND cta.exme_tipopaciente_id = ? \n");
		str.append(" \t	AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1 AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND \n");
		str.append( 	DB.truncDate("hv.fechaaplica", "dd"));
		
		str.append(" between to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" \t	GROUP BY hv.exme_paciente_id having count(distinct hv.exme_hist_vacuna_id) >= ?) ipv \n");
		str.append(" ON ipv.paciente_id = dtap.paciente_id \n");
		str.append(" inner join ");
		str.append(" \t	(SELECT hv.exme_paciente_id as paciente_id \n");
		str.append(" \t	FROM EXME_Hist_Vacuna hv \n");
		str.append(" \t	inner join exme_paciente ep \n");
		str.append(" \t	inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id  on ep.exme_paciente_id = hv.exme_paciente_id \n");
		str.append(" \t	WHERE hv.ad_client_id = ? AND hv.ad_org_id = ? AND hv.exme_vacuna_id = ? AND cta.exme_tipopaciente_id = ? \n");
		str.append(" \t	AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1 AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND \n");
		str.append( 	DB.truncDate("hv.fechaaplica", "dd") );
		
		str.append(" \t	between to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy')  \n");
		str.append(" \t	GROUP BY hv.exme_paciente_id \n");
		str.append(" \t	having count(distinct hv.exme_hist_vacuna_id) >= ?) mmr \n");
		str.append(" on mmr.paciente_id = ipv.paciente_id \n");
		str.append(" inner join \n");
		str.append(" \t	(SELECT hv.exme_paciente_id as paciente_id \n");
		str.append(" \t	FROM EXME_Hist_Vacuna hv \n");
		str.append(" \t	inner join exme_paciente ep \n");
		str.append(" \t	inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id on ep.exme_paciente_id = hv.exme_paciente_id \n");
		str.append(" \t	WHERE hv.ad_client_id = ? AND hv.ad_org_id = ? AND hv.exme_vacuna_id = ? AND cta.exme_tipopaciente_id = ? AND  \n");
		str.append(" \t	edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1 AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND \n");
		str.append( 	DB.truncDate("hv.fechaaplica", "dd" ) );
		
		str.append(" \t	between to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" \t	GROUP BY hv.exme_paciente_id \n");
		str.append(" \t	having count(distinct hv.exme_hist_vacuna_id) >= ?) vzv \n");
		str.append(" ON vzv.paciente_id = mmr.paciente_id \n");
		str.append(" inner join \n");
		str.append(" \t	(SELECT hv.exme_paciente_id as paciente_id \n");
		str.append(" \t	FROM EXME_Hist_Vacuna hv \n");
		str.append(" \t	inner join exme_paciente ep \n");
		str.append(" \t	inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id on ep.exme_paciente_id = hv.exme_paciente_id \n");
		str.append(" \t	WHERE hv.ad_client_id = ? AND hv.ad_org_id = ? AND hv.exme_vacuna_id = ? AND cta.exme_tipopaciente_id = ? \n");
		str.append(" \t	AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1 AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND \n");
		str.append( 	DB.truncDate ("hv.fechaaplica", "dd") );
		
		str.append(" \t	between to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" \t	GROUP BY hv.exme_paciente_id ");
		str.append(" \t	having count(distinct hv.exme_hist_vacuna_id) >= ?) hepatitisB \n");
		str.append(" ON hepatitisB.paciente_id = vzv.paciente_id \n");
											
		pacientes = CQMeasures.executaSql(str.toString(), 
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), diphtheria, outpatient, strIni, strIni,strIni, strFin, diphtheriaNum,
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), polio, outpatient, strIni, strIni,strIni, strFin, polioNum,
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), mumps, outpatient, strIni, strIni,strIni, strFin, mumpsNum,
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), chickenPox, outpatient, strIni, strIni,strIni, strFin, chickenPoxNum,
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), hepatitisB, outpatient, strIni, strIni,strIni, strFin, hepatitisBNum
		);		
		
		return pacientes;
	}
	
	private int executaNum12(){
		int pacientes = 0;
		
		StringBuilder str = new StringBuilder();
		str.append(" SELECT COUNT(*) \n");
		str.append(" FROM \n");
		str.append(" \t	(SELECT hv.exme_paciente_id as paciente_id \n");
		str.append(" \t	FROM EXME_Hist_Vacuna hv \n");
		str.append(" \t	inner join exme_paciente ep \n");
		str.append(" \t	inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id on ep.exme_paciente_id = hv.exme_paciente_id \n");
		str.append(" \t	WHERE hv.ad_client_id = ? AND hv.ad_org_id = ? AND hv.exme_vacuna_id = ? AND cta.exme_tipopaciente_id = ? \n");
		str.append(" \t	AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1 AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND \n");
		str.append( 	DB.truncDate("hv.fechaaplica", "dd") );
		
		str.append(" between to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" \t	GROUP BY hv.exme_paciente_id \n");
		str.append(" \t	having count(distinct hv.exme_hist_vacuna_id) >= ?) dtap \n");
		str.append(" inner join ");
		str.append(" \t	(SELECT hv.exme_paciente_id as paciente_id \n");
		str.append(" \t	FROM EXME_Hist_Vacuna hv \n");
		str.append(" \t	inner join exme_paciente ep \n");
		str.append(" \t	inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id  on ep.exme_paciente_id = hv.exme_paciente_id \n");
		str.append(" \t	WHERE hv.ad_client_id = ? AND hv.ad_org_id = ? AND hv.exme_vacuna_id = ? AND cta.exme_tipopaciente_id = ? \n");
		str.append(" \t	AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1 AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND \n");
		str.append( 	DB.truncDate("hv.fechaaplica", "dd") );
		
		str.append(" \t	between to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" \t	GROUP BY hv.exme_paciente_id ");
		str.append(" \t	having count(distinct hv.exme_hist_vacuna_id) >= ?) ipv \n");
		str.append(" ON ipv.paciente_id = dtap.paciente_id \n");
		str.append(" inner join \n");
		str.append(" \t	(SELECT hv.exme_paciente_id as paciente_id \n");
		str.append(" \t	FROM EXME_Hist_Vacuna hv \n");
		str.append(" \t	inner join exme_paciente ep \n");
		str.append(" \t	inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id on ep.exme_paciente_id = hv.exme_paciente_id \n");
		str.append(" \t	WHERE hv.ad_client_id = ? AND hv.ad_org_id = ? AND hv.exme_vacuna_id = ? AND cta.exme_tipopaciente_id = ? \n");
		str.append(" \t	AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1 AND edadmuse( EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND \n");
		str.append( 	DB.truncDate("hv.fechaaplica", "dd") );
		
		str.append(" \t	between to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" \t	GROUP BY hv.exme_paciente_id \n");
		str.append(" \t	having count(distinct hv.exme_hist_vacuna_id) >= ?) mmr \n");
		str.append(" on mmr.paciente_id = ipv.paciente_id \n");
		str.append(" inner join \n");
		str.append(" \t	(SELECT hv.exme_paciente_id as paciente_id \n");
		str.append(" \t	FROM EXME_Hist_Vacuna hv \n");
		str.append(" \t	inner join exme_paciente ep \n");
		str.append(" \t	inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id on ep.exme_paciente_id = hv.exme_paciente_id \n");
		str.append(" \t	WHERE hv.ad_client_id = ? AND hv.ad_org_id = ? AND hv.exme_vacuna_id = ? AND cta.exme_tipopaciente_id = ? \n");
		str.append(" \t	AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1  AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND \n");
		str.append( 	DB.truncDate("hv.fechaaplica", "dd") );
		
		str.append(" between to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" \t	GROUP BY hv.exme_paciente_id \n");
		str.append(" \t	having count(distinct hv.exme_hist_vacuna_id) >= ?) vzv \n");
		str.append(" ON vzv.paciente_id = mmr.paciente_id \n");
		str.append(" inner join ");
		str.append(" \t	(SELECT hv.exme_paciente_id as paciente_id \n");
		str.append(" \t	FROM EXME_Hist_Vacuna hv \n");
		str.append(" \t	inner join exme_paciente ep \n");
		str.append(" \t	inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id on ep.exme_paciente_id = hv.exme_paciente_id \n");
		str.append(" \t	WHERE hv.ad_client_id = ? AND hv.ad_org_id = ? AND hv.exme_vacuna_id = ? AND cta.exme_tipopaciente_id = ? \n");
		str.append(" \t	AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1 AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND \n");
		str.append( 	DB.truncDate("hv.fechaaplica", "dd"));
		
		str.append(" \t between to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy') \n");
		str.append(" \t	GROUP BY hv.exme_paciente_id \n");
		str.append(" \t	having count(distinct hv.exme_hist_vacuna_id) >= ?) hepatitisB \n");
		str.append(" ON hepatitisB.paciente_id = vzv.paciente_id \n");
		str.append(" inner join \n");
		str.append(" \t	(SELECT hv.exme_paciente_id as paciente_id \n");
		str.append(" \t	FROM EXME_Hist_Vacuna hv \n");
		str.append(" \t	inner join exme_paciente ep \n");
		str.append(" \t	inner join exme_ctapac cta on cta.exme_paciente_id = ep.exme_paciente_id on ep.exme_paciente_id = hv.exme_paciente_id \n");
		str.append(" \t	WHERE hv.ad_client_id = ? AND hv.ad_org_id = ? AND hv.exme_vacuna_id = ? AND cta.exme_tipopaciente_id = ? \n");
		str.append(" \t	AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) >=1  AND edadmuse(EP.FECHANAC, to_date(?, 'dd/MM/yyyy')::date) < 2 AND \n");
		str.append( 	DB.truncDate("hv.fechaaplica", "dd") );
		
		str.append(" \t	between to_date(?,'dd/MM/yyyy') AND to_date(?, 'dd/MM/yyyy')  \n");
		str.append(" \t	GROUP BY hv.exme_paciente_id \n");
		str.append(" \t	having count(distinct hv.exme_hist_vacuna_id) >= ?) pneumococcal \n");
		str.append(" ON pneumococcal.paciente_id = hepatitisB.paciente_id \n");
															
		pacientes = CQMeasures.executaSql(str.toString(), 
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), diphtheria, outpatient, strIni, strIni,strIni, strFin, diphtheriaNum,
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), polio, outpatient, strIni, strIni,strIni, strFin, polioNum,
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), mumps, outpatient, strIni, strIni,strIni, strFin, mumpsNum,
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), chickenPox, outpatient, strIni, strIni,strIni, strFin, chickenPoxNum,
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), hepatitisB, outpatient, strIni, strIni,strIni, strFin, hepatitisBNum,
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), pneumococcal, outpatient, strIni, strIni,strIni, strFin, pneumococcalNum
		);		
		
		return pacientes;
	}
}