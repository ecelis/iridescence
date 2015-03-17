/**
 * 
 */
package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.util.logging.Level;

import org.compiere.Compiere;
import org.compiere.util.CLogMgt;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;
import org.compiere.util.UtilsDbPort;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author mrojas
 *
 */
public class TestLoginSuperUser {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		String connString = 
			"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=10.90.1.79,"
			+ "AppsPort=0,Profile=L,type=Oracle,DBhost=192.168.11.27"
			+ ",DBport=1521,DBname=ecaresof"
			+ ",BQ=false,FW=false,FWhost=,FWport=1630,UID=expert,PWD=expert]";
		
		Ini.setProperty(Ini.P_CONNECTION, connString);
		
		Compiere.startup(true);
		CLogMgt.setLoggerLevel(Level.WARNING, null);
		CLogMgt.setLevel(Level.WARNING);
		
		Ini.setProperty(Ini.P_UID, "SuperUser");
		Ini.setProperty(Ini.P_ROLE, "System Administrator");
		Ini.setProperty(Ini.P_CLIENT, "System");
		Ini.setProperty(Ini.P_ORG, "*");
		Ini.setProperty(Ini.P_WAREHOUSE,"");
		Ini.setProperty("#EXME_EstServ_ID","");
		Ini.setProperty(Ini.P_LANGUAGE, "English");
		//
	}

	/**
	 * Test method for {@link org.compiere.util.Login#batchLogin()}.
	 */
	@Test
	public void testBatchLoginOk() {
		
		
		Ini.setProperty(Ini.P_PWD,UtilsDbPort.encrypt("NOMBRECOMPLETO"));
		
		Login login = new Login(Env.getCtx());
        assertEquals(true, login.batchLogin());
	}
	
	/**
	 * Test method for {@link org.compiere.util.Login#batchLogin()}.
	 */
	@Test
	public void testBatchLoginNotOk() {
		
		Ini.setProperty(Ini.P_PWD,UtilsDbPort.encrypt("INCORRECTA"));
		
		Login login = new Login(Env.getCtx());
        assertEquals(false, login.batchLogin());
	}

}
