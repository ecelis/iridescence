package com.ecaresoft.tests;

import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.InformationResponse;
import org.compiere.util.InformationUtils;
import org.junit.Test;

/**
 * Pruebas de búsqueda de información
 * 
 * @author odelarosa
 * 
 */
public class InfoButtonTest extends TestCase {

	private static CLogger s_log = CLogger.getCLogger(InfoButtonTest.class);

	@Test
	public void testICD9() {
		try {
			InformationResponse response = InformationUtils.getICD9Response(Env.getCtx(), "496");
			assertNotNull(response);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
			assertNotNull(null);
		}
	}

	@Test
	public void testLoinc() {
		try {
			InformationResponse response = InformationUtils.getLOINCResponse(Env.getCtx(), "3187-2");
			assertNotNull(response);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
			assertNotNull(null);
		}
	}

	@Test
	public void testNDC() {
		try {
			InformationResponse response = InformationUtils.getNDCResponse(Env.getCtx(), "55111015778");
			assertNotNull(response);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
			assertNotNull(null);
		}
	}

	@Test
	public void testSNOMED() {
		try {
			InformationResponse response = InformationUtils.getSNOMEDResponse(Env.getCtx(), "13645005");
			assertNotNull(response);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
			assertNotNull(null);
		}
	}
}
