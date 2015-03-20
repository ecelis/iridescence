/**
 * 
 */
package com.ecaresoft.tests;


import java.util.Properties;

import junit.framework.TestCase;

import org.compiere.process.GeneraArchivoVte1;
import org.compiere.process.GeneraArchivoVte2;
import org.compiere.process.GeneraArchivoVte4;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author mvrodriguez
 *
 */
public class GeneraArchivoVte2Test extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUp();
	}

	/**
	 * Test method for {@link org.compiere.process.GeneraArchivoVte1#testPacientesMedida()}.
	 */
	@Test
	public void testTestPacientesMedida() {
		String version = null; 
		String optionRegistry = null; 
		Properties ctx  = Env.getCtx(); 
		String trxName = null; 
		int file = 0; 
		int files = 0;
	
		int num = GeneraArchivoVte2.testPacientesMedida( version,  optionRegistry,  ctx,  trxName,  file,  files);
		assertTrue(num ==1);
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoVte4#pacientesMedida()}
	 */
	@Test
	public void testNumerador() {
		int count = GeneraArchivoVte2.testPacientesMedida(true);
		assertTrue(count >= 0);
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoVte4#pacientesDenominador()}
	 */
	@Test
	public void testDenominador() {
		int count = GeneraArchivoVte2.testPacientesMedida(false);
		assertTrue(count >= 0);
	}
}
