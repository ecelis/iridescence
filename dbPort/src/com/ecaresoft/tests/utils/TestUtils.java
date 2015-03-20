package com.ecaresoft.tests.utils;

import java.lang.reflect.Constructor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.Compiere;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MSession;
import org.compiere.model.PO;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;
import org.compiere.util.Utilerias;
import org.compiere.util.WebEnv;

/**
 * Clase con metodos de utilidad para las pruebas con JUnit
 * @author Pedro Mendoza
 * Modificado por Lorena Lama
 */
public class TestUtils {
	
	/**
	 * Crea la sentencia SQL en Oracle para obtener un registro aleatorio de una consulta. <br/>
	 * la estructura de la sentencia es la siguiente:  <br/>
	 * SELECT column FROM
	 *	( SELECT column FROM table
	 *	ORDER BY dbms_random.value )
     *  WHERE rownum = 1
     *
	 * @param ctx
	 * @param tableName
	 * @param whereClause
	 * @return
	 */	
	private static String getOracleRNDSentence(Properties ctx, String tableName, String whereClause, boolean addAccessLevel){
		StringBuffer sqlBase = new StringBuffer("SELECT * FROM ");
		sqlBase.append(tableName).append(" WHERE isActive = 'Y' ");	
		if (whereClause != null && whereClause.length() > 0){
			sqlBase.append(" AND ");
			sqlBase.append(whereClause);
		}
		StringBuffer sql = new StringBuffer("Select * FROM ( " );
		if(addAccessLevel){
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, sqlBase.toString(), tableName));
		}else{
			sql.append(sqlBase.toString());
		}
		
		sql.append(" ORDER BY dbms_random.value")
		.append( " ) WHERE rownum = 1");		
		return sql.toString();		
	}
	
	/**
	 * Crea la sentencia SQL en Postgres para obtener un registro aleatorio de una consulta. <br/>
	 * la estructura de la sentencia es la siguiente:  <br/>
	 * SELECT column FROM table
	 * ORDER BY RANDOM()
	 * LIMIT 1
	 * @param ctx
	 * @param tableName
	 * @param whereClause
	 * @return
	 */
	private static String getPostgreRNDSentence(Properties ctx, String tableName, String whereClause, boolean addAccessLevel){
		StringBuffer sql = new StringBuffer("SELECT * FROM ");
		sql.append(tableName).append(" WHERE isActive = 'Y' ");
		if (whereClause != null && whereClause.length() > 0){
			sql.append(" AND ");
			sql.append(whereClause);
		}
		if(addAccessLevel){
			sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), tableName));
		}
		sql.append(" ORDER BY RANDOM()");
		return sql.toString();
	}
	
	/**
	 * Obtiene un objecto aleatorio de clase T que extienda de PO. <br/>
	 * Toma la suposicion que el contexto ya esta inicializado, no realiza ningun filtro en el resultado salvo en nivel de acceso  
	 * @param clazz Class<T extends PO> clase del modelo que extienda de PO ejemplo MEXMEPaciente.class 
	 * @return
	 */
	public static <T extends PO> T getRandomPO(Class<T> clazz){
		return getRandomPO(clazz, null,true);
	}
	
	/**
	 * Obtiene un objecto aleatorio de clase T que extienda de PO. <br/>
	 * Toma la suposicion que el contexto ya esta inicializado.
	 * @param clazz Class<T extends PO> clase del modelo que extienda de PO ejemplo MEXMEPaciente.class 
	 * @param whereClause Consulta where. Suponer que el "Where" ya esta incluido en la sentacia
	 * @param addAccessLevel Agregar nivel de accesso
	 * @return
	 */
	public static <T extends PO> T getRandomPO(Class<T> clazz, String whereClause, boolean addAccessLevel, Object... params){
		T poObject = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			String tableName = clazz.getField("Table_Name").get(0).toString();
			if(DB.isOracle()){
				sql = getOracleRNDSentence(Env.getCtx(), tableName, whereClause,addAccessLevel);	
			}else if (DB.isPostgreSQL()){
				sql = getPostgreRNDSentence(Env.getCtx(), tableName, whereClause,addAccessLevel);
			}
			pstmt = DB.prepareStatement(sql, null);
			if(params != null && params.length>0){
				DB.setParameters(pstmt, params);
			}
			rs = pstmt.executeQuery();
			if (rs.next()){
				try {
					Constructor<T> constructor = clazz.getDeclaredConstructor(new Class[] { Properties.class, ResultSet.class, String.class });
					poObject =  (T) constructor.newInstance(new Object[] { Env.getCtx(), rs, null });
				} catch (Exception e) {
					System.out.println("(rs) ,Class=" + clazz);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			if(sql != null) {
				System.out.println("SQL = " + sql);
			}
			throw new MedsysException(sql, e1);
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return poObject;
		
	}
	public static void setUp() throws Exception {
		String connString = 
				"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=10.90.1.79,"
				+ "AppsPort=0,Profile=L,type=PostgreSQL,DBhost=192.168.11.27"
				+ ",DBport=5432,DBname=ecaresoftdev"
				+ ",BQ=false,FW=false,FWhost=,FWport=1521,UID=ecaresoft,PWD=ecaresoft]";
			
			Ini.setProperty(Ini.P_CONNECTION, connString);
			
			Compiere.startup(true);
			CLogMgt.setLoggerLevel(Level.WARNING, null);
			CLogMgt.setLevel(Level.WARNING);
			Ini.setProperty(Ini.P_UID, "SuperUser");
			Ini.setProperty(Ini.P_PWD,"7AiVqr7weaQYSLb3yF1j9Q==");
			Ini.setProperty(Ini.P_ROLE, "ECMD Physician");
			Ini.setProperty(Ini.P_CLIENT, "Erasto Canales M.D. P.A");
			Ini.setProperty(Ini.P_ORG, " Erasto Canales M.D. P.A");
			Ini.setProperty(Ini.P_WAREHOUSE,"Almacen de abasto");
			Ini.setProperty("#EXME_EstServ_ID","Physician Office");
			Ini.setProperty(Ini.P_LANGUAGE, "English");
			
		Login login = new Login(Env.getCtx());
		if (!login.batchLogin(null)){
			System.exit(1);
		}
		MSession.get(Env.getCtx(), "", "", "", null);

	}
	
	/**
	 * Org Lake Whitney Medical Center
	 * @throws Exception
	 */
	public static void setUpLake() throws Exception {
		String connString = 
				"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=10.90.1.79,"
				+ "AppsPort=0,Profile=L,type=PostgreSQL,DBhost=192.168.11.27"
				+ ",DBport=5432,DBname=ecaresoftdev"
				+ ",BQ=false,FW=false,FWhost=,FWport=1630,UID=ecaresoft,PWD=ecaresoft]";
			
			Ini.setProperty(Ini.P_CONNECTION, connString);
			
			Compiere.startup(true);
			CLogMgt.setLoggerLevel(Level.WARNING, null);
			CLogMgt.setLevel(Level.WARNING);
			
			Ini.setProperty(Ini.P_UID, "SuperUser");
			Ini.setProperty(Ini.P_PWD,"7AiVqr7weaQYSLb3yF1j9Q==");
			Ini.setProperty(Ini.P_ROLE, "Admin Mahmood Medical Group");
			Ini.setProperty(Ini.P_CLIENT, "Mahmood Medical Group");
			Ini.setProperty(Ini.P_ORG, "Lake Whitney Medical Center");
			Ini.setProperty(Ini.P_WAREHOUSE,"LWMC General Nursing");
			Ini.setProperty("#EXME_EstServ_ID","LWMC General Nursing");
			Ini.setProperty(Ini.P_LANGUAGE, "English");
			
			Login login = new Login(Env.getCtx());
			if (!login.batchLogin(null)){
				System.exit(1);
			}
			MSession.get(Env.getCtx(), "", "", "", null);
	}
	
	public static void setUpMx() throws Exception {
		String connString = 
				"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=192.168.11.100,"
				+ "AppsPort=0,Profile=L,type=PostgreSQL,DBhost=192.168.11.200"
				+ ",DBport=5432,DBname=ecsmxProd14Jul"
				+ ",BQ=false,FW=false,FWhost=,FWport=1630,UID=ecaresoft,PWD=ecaresoft]";
			
			Ini.setProperty(Ini.P_CONNECTION, connString);
			
			Compiere.startup(true);
			CLogMgt.setLoggerLevel(Level.WARNING, null);
			CLogMgt.setLevel(Level.WARNING);
			
			Ini.setProperty(Ini.P_UID, "SuperUser");
			Ini.setProperty(Ini.P_PWD,"7AiVqr7weaQYSLb3yF1j9Q==");
			Ini.setProperty(Ini.P_ROLE,"Admin eCS Grupo Hospitaria");
			Ini.setProperty(Ini.P_CLIENT, "Grupo Hospitaria");
			Ini.setProperty(Ini.P_ORG,"Operadora Hospitaria S.A.P.I. de C.V.");
			Ini.setProperty(Ini.P_WAREHOUSE,"Admisión");
			Ini.setProperty("#EXME_EstServ_ID","");
			Ini.setProperty(Ini.P_LANGUAGE,"English");
			
			Login login = new Login(Env.getCtx());
			if (!login.batchLogin(null)){
				System.exit(1);
			}
			MSession.get(Env.getCtx(), "", "", "", null);
	}
	
	/**
	 * Org Lake Whitney Medical Center
	 * @throws Exception
	 */
	public static void setUp5433() throws Exception {
		String connString = 
				"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=10.90.1.79,"
				+ "AppsPort=0,Profile=L,type=PostgreSQL,DBhost=192.168.11.26"
				+ ",DBport=5433,DBname=ecs_prod_usa"
				+ ",BQ=false,FW=false,FWhost=,FWport=1630,UID=ecaresoft,PWD=ecaresoft]";
			
			Ini.setProperty(Ini.P_CONNECTION, connString);
			
			Compiere.startup(true);
			CLogMgt.setLoggerLevel(Level.WARNING, null);
			CLogMgt.setLevel(Level.WARNING);
			
			Ini.setProperty(Ini.P_UID, "SuperUser");
			Ini.setProperty(Ini.P_PWD,"7AiVqr7weaQYSLb3yF1j9Q==");
			Ini.setProperty(Ini.P_ROLE, "Mahmood Medical Group Admin");
			Ini.setProperty(Ini.P_CLIENT, "Mahmood Medical Group");
			Ini.setProperty(Ini.P_ORG, "Lake Whitney Medical Center");
			Ini.setProperty(Ini.P_WAREHOUSE,"LWMC General Nursing");
			Ini.setProperty("#EXME_EstServ_ID","LWMC General Nursing");
			Ini.setProperty(Ini.P_LANGUAGE, "English");
			
			Login login = new Login(Env.getCtx());
			if (!login.batchLogin(null)){
				System.exit(1);
			}
			MSession.get(Env.getCtx(), "", "", "", null);
	}
	
	/**
	 * Org Lake Whitney Medical Center
	 * @throws Exception
	 */
	public static void setUpAustin() throws Exception {
		String connString = 
				"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=10.90.1.79,"
				+ "AppsPort=0,Profile=L,type=PostgreSQL,DBhost=192.168.11.27"
				+ ",DBport=5432,DBname=ecaresoftdev"
				+ ",BQ=false,FW=false,FWhost=,FWport=1630,UID=ecaresoft,PWD=ecaresoft]";
			
			Ini.setProperty(Ini.P_CONNECTION, connString);
			
			Compiere.startup(true);
			CLogMgt.setLoggerLevel(Level.WARNING, null);
			CLogMgt.setLevel(Level.WARNING);
			
			Ini.setProperty(Ini.P_UID,"SUPERUSER");
			Ini.setProperty(Ini.P_PWD,"7AiVqr7weaQYSLb3yF1j9Q==");
			Ini.setProperty(Ini.P_ROLE,"Texas Health Network Admin");
			Ini.setProperty(Ini.P_CLIENT, "Texas Health Network");
			Ini.setProperty(Ini.P_ORG,"Austin Congress Hospital");
			Ini.setProperty(Ini.P_WAREHOUSE,"10001034 ACH Nursing");
			Ini.setProperty("#EXME_EstServ_ID","10001034 CGH Nursing");
			Ini.setProperty(Ini.P_LANGUAGE,"English");
			Login login = new Login(Env.getCtx());
			if (!login.batchLogin(null)){
				System.exit(1);
			}
			MSession.get(Env.getCtx(), "", "", "", null);
	}
	
	public static void setUpDemoMX() throws Exception {
		final String connString = 
				"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=192.168.11.70," +
				"AppsPort=0,Profile=L,type=PostgreSQL,DBhost=192.168.11.31,DBport=5432,DBname=ecs_hospudm_mx," +
				"BQ=false,FW=false,FWhost=,FWport=1630,UID=ecaresoft,PWD=ecaresoft]";

			Ini.setProperty(Ini.P_CONNECTION, connString);

			Compiere.startup(true);
			CLogMgt.setLoggerLevel(Level.INFO, null);
			CLogMgt.setLevel(Level.INFO);
			
			Ini.setProperty(Ini.P_UID,"HELIO");
			Ini.setProperty(Ini.P_PWD,"7Pyd9gOqDfvD6UMuzYIO6g==");
			Ini.setProperty(Ini.P_ROLE,"Admin eCS Grupo Hospitaria");
			Ini.setProperty(Ini.P_CLIENT, "Grupo Hospitaria");
			Ini.setProperty(Ini.P_ORG,"Operadora Hospitaria S.A.P.I. de C.V.");
			Ini.setProperty(Ini.P_WAREHOUSE,"Admisión");
			Ini.setProperty("#EXME_EstServ_ID","");
			Ini.setProperty(Ini.P_LANGUAGE,"English");

			final Login login = new Login(Env.getCtx());

			if (!login.batchLogin(null)) {
				System.exit(1);
			}
			MSession.get(Env.getCtx(), "", "", "", null);
	}

	public static String generateString(Random rng, String characters, int length)
	{
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	
	/**
	 * Org cozby
	 * @throws Exception
	 */
	public static void setUpCozby() throws Exception {
		String connString = 
				"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=10.90.1.79,"
				+ "AppsPort=0,Profile=L,type=PostgreSQL,DBhost=192.168.11.27"
				+ ",DBport=5432,DBname=ecaresoftdev"
				+ ",BQ=false,FW=false,FWhost=,FWport=1630,UID=ecaresoft,PWD=ecaresoft]";
			
			Ini.setProperty(Ini.P_CONNECTION, connString);
			
			Compiere.startup(true);
			CLogMgt.setLoggerLevel(Level.WARNING, null);
			CLogMgt.setLevel(Level.WARNING);
			
			Ini.setProperty(Ini.P_UID, "SuperUser");
			Ini.setProperty(Ini.P_PWD,"7AiVqr7weaQYSLb3yF1j9Q==");
			Ini.setProperty(Ini.P_ROLE, "Admin Mahmood Medical Group");
			Ini.setProperty(Ini.P_CLIENT, "Mahmood Medical Group");
			Ini.setProperty(Ini.P_ORG, "Cozby Germany Hospital");
			Ini.setProperty(Ini.P_WAREHOUSE,"Cozby General Nursing");
			Ini.setProperty("#EXME_EstServ_ID","General Nursing");
			Ini.setProperty(Ini.P_LANGUAGE, "English");
			
			Login login = new Login(Env.getCtx());
			if (!login.batchLogin(null)){
				System.exit(1);
			}
			MSession.get(Env.getCtx(), "", "", "", null);
	}
	
	
	/**
	 * Org cozby
	 * @throws Exception
	 */
	public static void setUp200(final String dBname) throws Exception {
		String connString = 
				"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=10.90.1.79,"
				+ "AppsPort=0,Profile=L,type=PostgreSQL,DBhost=192.168.11.200"
				+ ",DBport=5432,DBname="+ dBname
				+ ",BQ=false,FW=false,FWhost=,FWport=1630,UID=ecaresoft,PWD=ecaresoft]";
			
			Ini.setProperty(Ini.P_CONNECTION, connString);
			
			Compiere.startup(true);
			CLogMgt.setLoggerLevel(Level.WARNING, null);
			CLogMgt.setLevel(Level.WARNING);
			
			Ini.setProperty(Ini.P_UID,         "SuperUser");
			Ini.setProperty(Ini.P_PWD,         "7AiVqr7weaQYSLb3yF1j9Q==");
			Ini.setProperty(Ini.P_ROLE,        "Admin Swiss Hospital S.A. de C.V.");
			Ini.setProperty(Ini.P_CLIENT,      "Swiss Hospital S.A. de C.V.");
			Ini.setProperty(Ini.P_ORG,         "Swiss Hospital S.A. de C.V.");
			Ini.setProperty(Ini.P_WAREHOUSE,   "ALMACEN GENERAL");
			Ini.setProperty("#EXME_EstServ_ID","ALMACEN GENERAL");
			Ini.setProperty(Ini.P_LANGUAGE, "English");
			
			Login login = new Login(Env.getCtx());
			if (!login.batchLogin(null)){
				System.exit(1);
			}
			MSession.get(Env.getCtx(), "", "", "", null);
	}
	
	/**
	 * Org monclova
	 * @throws Exception
	 */
	public static void setMonclova200(final String dBname) throws Exception {
		String connString = 
				"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=10.90.1.79,"
				+ "AppsPort=0,Profile=L,type=PostgreSQL,DBhost=192.168.11.200"
				+ ",DBport=5432,DBname="+ dBname
				+ ",BQ=false,FW=false,FWhost=,FWport=1630,UID=ecaresoft,PWD=ecaresoft]";
			
			Ini.setProperty(Ini.P_CONNECTION, connString);
			
			Compiere.startup(true);
			CLogMgt.setLoggerLevel(Level.WARNING, null);
			CLogMgt.setLevel(Level.WARNING);
			
			Ini.setProperty(Ini.P_UID,         "SuperUser");
			Ini.setProperty(Ini.P_PWD,         "7AiVqr7weaQYSLb3yF1j9Q==");
			Ini.setProperty(Ini.P_ROLE,        "Admin Hospital San José de Monclova, S.A. de C.V. ");
			Ini.setProperty(Ini.P_CLIENT,      "Hospital San José de Monclova, S.A. de C.V.");
			Ini.setProperty(Ini.P_ORG,         "Hospital San José de Monclova, S.A. de C.V.");
			Ini.setProperty(Ini.P_WAREHOUSE,   "HOSPITALIZACIÓN");
			Ini.setProperty("#EXME_EstServ_ID","Hospitalización");
			Ini.setProperty(Ini.P_LANGUAGE,    "Spanish (Mexico)");
			
			Login login = new Login(Env.getCtx());
			if (!login.batchLogin(null)){
				System.exit(1);
			}
			MSession.get(Env.getCtx(), "", "", "", null);
	}
	
	/**
	 * Org ecare
	 * @throws Exception
	 */
	public static void setEcare200(final String dBname) throws Exception {
		String connString = 
				"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=10.90.1.79,"
				+ "AppsPort=0,Profile=L,type=PostgreSQL,DBhost=192.168.11.200"
				+ ",DBport=5432,DBname="+ dBname
				+ ",BQ=false,FW=false,FWhost=,FWport=1630,UID=ecaresoft,PWD=ecaresoft]";
			
			Ini.setProperty(Ini.P_CONNECTION, connString);
			
			Compiere.startup(true);
			CLogMgt.setLoggerLevel(Level.WARNING, null);
			CLogMgt.setLevel(Level.WARNING);
			
			Ini.setProperty(Ini.P_UID,         "SuperUser");
			Ini.setProperty(Ini.P_PWD,         "7AiVqr7weaQYSLb3yF1j9Q==");
			Ini.setProperty(Ini.P_ROLE,        "Admin ECARESOFT MEXICO, S.A. DE C.V.");
			Ini.setProperty(Ini.P_CLIENT,      "ECARESOFT MEXICO, S.A. DE C.V.");
			Ini.setProperty(Ini.P_ORG,         "ECARESOFT MEXICO, S.A. DE C.V.");
			Ini.setProperty(Ini.P_WAREHOUSE,   "CONTABILIDAD");
			Ini.setProperty("#EXME_EstServ_ID","CONTABILIDAD");
			Ini.setProperty(Ini.P_LANGUAGE,    "Spanish (Mexico)");
			
			Login login = new Login(Env.getCtx());
			if (!login.batchLogin(null)){
				System.exit(1);
			}
			MSession.get(Env.getCtx(), "", "", "", null);
	}
//	select * from ad_client where name like '%Moncl%'
//	select * from ad_org where name like '%Moncl%'
//	select * from ad_role where name like '%Moncl%'
//	select * from M_Warehouse where name like '%HOS%' and ad_org_id = 10001326
//	select * from exme_estserv where value like '%HO%' and ad_org_id = 10001326
//	select * from ad_language  where ad_language like 'es_M%'
	
	/**
	 * Org ecare
	 * @throws Exception
	 */
	public static void setConnectionBD(final String dBname, final String dBhost, final int clientId) throws Exception {
		String connString = 
				"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=10.90.1.79,"
				+ "AppsPort=0,Profile=L,type=PostgreSQL,DBhost=192.168.11."+dBhost
				+ ",DBport=5432,DBname="+ dBname
				+ ",BQ=false,FW=false,FWhost=,FWport=1630,UID=ecaresoft,PWD=ecaresoft]";
			
			Ini.setProperty(Ini.P_CONNECTION, connString);
			
			Compiere.startup(true);
			CLogMgt.setLoggerLevel(Level.WARNING, null);
			CLogMgt.setLevel(Level.WARNING);
			
			Ini.setProperty(Ini.P_UID,         "SuperUser");
			Ini.setProperty(Ini.P_PWD,         "7AiVqr7weaQYSLb3yF1j9Q==");
			
			String value = DB.getSQLValueString(null, "select Name from ad_role where ad_client_id = ? AND name like 'Admin %'", clientId);
			Ini.setProperty(Ini.P_ROLE,        value);
			
			value = DB.getSQLValueString(null, "select Name from ad_client where ad_client_id = ? ", clientId);
			Ini.setProperty(Ini.P_CLIENT,      value);
			
			value = DB.getSQLValueString(null, "select Name from ad_org where ad_client_id = ? And name like '%"+value+"%'", clientId);
			Ini.setProperty(Ini.P_ORG,         value);
			
			value = DB.getSQLValueString(null, " SELECT  MAX( NAME) from M_Warehouse where ad_client_id = "+clientId+ " And ISACTIVE = 'Y'  "
                                                + " AND (UPPER(NAME) LIKE '%EN GENERAL' or UPPER(NAME) LIKE 'HOSPITALIZACI%' or UPPER(NAME) = 'FARMACIA') "
                                                + "  ");
			Ini.setProperty(Ini.P_WAREHOUSE,   value);
			
			value = DB.getSQLValueString(null, " SELECT  MAX( NAME) from exme_estserv where ad_client_id = "+clientId+ " And ISACTIVE = 'Y'  "
							                    + " AND (UPPER(NAME) LIKE '%EN GENERAL' or UPPER(NAME) LIKE 'HOSPITALIZACI%' or UPPER(NAME) = 'FARMACIA') "
							                    + "  ");
			Ini.setProperty("#EXME_EstServ_ID",value);
			
			Ini.setProperty(Ini.P_LANGUAGE,    "Spanish (Mexico)");
			
			Login login = new Login(Env.getCtx());
			if (!login.batchLogin(null)){
				System.exit(1);
			}
			MSession.get(Env.getCtx(), "", "", "", null);
	}

	
	/**
	 * Toma la informacion de conneccion y usuario del Compiere.properties
	 * Toma el primer perfil con el nombre "Admin %" para el cliente del la organizacion del parametro
	 * @param orgId Organizacion para realizar las pruebas
	 * 
	 */
	public static void setUp(final int orgId){
//		String bdName = Utilerias.getPropertiesFromEnv("COMPIERE_DB_NAME");
//		String bdHost = Utilerias.getPropertiesFromEnv("COMPIERE_DB_SERVER");
//		String appHost = Utilerias.getPropertiesFromEnv("COMPIERE_APPS_SERVER");
//
//		final StringBuilder connString = new StringBuilder();
//		connString.append("CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},");
//		connString.append("AppsHost=").append(appHost).append(",");
//		connString.append("AppsPort=0,Profile=L,type=PostgreSQL,");
//		connString.append("DBhost=").append(bdHost).append(",");
//		connString.append("DBport=5432,DBname=").append(bdName).append(",");
//		connString.append("BQ=false,FW=false,FWhost=,FWport=1630,UID=ecaresoft,PWD=ecaresoft]");

		Properties properties = WebEnv.getProperties("Compiere.properties");
		final String connString = Utilerias.getProperties(properties, "Connection");
		final String userName = "SUPERUSER";
		Ini.setProperty(Ini.P_CONNECTION, connString.toString());

		Compiere.startup(true);
		CLogMgt.setLoggerLevel(Level.INFO, null);
		CLogMgt.setLevel(Level.INFO);

		String pass = DB.getSQLValueString(null, "SELECT password FROM AD_User WHERE NAME=UPPER(?)", userName);
		String orgName = DB.getSQLValueString(null, "SELECT name FROM AD_Org WHERE AD_Org_ID=? ", orgId);
		int clientId = DB.getSQLValue(null, "SELECT AD_Client_id FROM AD_Org WHERE AD_Org_ID=? ", orgId);
		String clientName = DB.getSQLValueString(null, "SELECT name FROM AD_Client WHERE AD_Client_ID=? ", clientId);
		String roleName = DB.getSQLValueString(null, "SELECT name FROM ad_role WHERE name LIKE 'Admin %' AND ad_client_id=?", clientId);
		String warehouse = DB.getSQLValueString(null, "SELECT name FROM m_warehouse WHERE ad_client_id=? ", clientId);

		Ini.setProperty(Ini.P_UID, userName);
		Ini.setProperty(Ini.P_PWD, pass);
		Ini.setProperty(Ini.P_ROLE, roleName);
		Ini.setProperty(Ini.P_CLIENT, clientName);
		Ini.setProperty(Ini.P_ORG, orgName);
		Ini.setProperty(Ini.P_WAREHOUSE, warehouse);
		Ini.setProperty("#EXME_EstServ_ID", "");
		Ini.setProperty(Ini.P_LANGUAGE, "Spanish");

		// Ini.setProperty(Ini.P_UID,"DLUNA");
		// Ini.setProperty(Ini.P_PWD,"8UckBialPUsUr/CdsKMltg==");
		// Ini.setProperty(Ini.P_ROLE,"Admin  Zánitas Clínica + Hospital, S.A. de C.V.");
		// Ini.setProperty(Ini.P_CLIENT, "Zánitas Clínica + Hospital, S.A. de C.V.");
		// Ini.setProperty(Ini.P_ORG,"Zánitas Clínica + Hospital, S.A. de C.V.");
		// Ini.setProperty(Ini.P_WAREHOUSE,"CONSULTA EXTERNA");
		// Ini.setProperty("#EXME_EstServ_ID","");
		// Ini.setProperty(Ini.P_LANGUAGE,"Spanish");

		final Login login = new Login(Env.getCtx());

		if (!login.batchLogin(null)) {
			System.exit(1);
		}
		MSession.get(Env.getCtx(), "", "", "", null);
	}
}
