/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.process.GeneraArchivoVte6;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Pruebas unitarias para clase GeneraArchivoVte6
 * @author vperez
 */
public class GeneraArchivoVte6Test extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoVte6#pacientesMedida()}
	 */
	@Test
	public void testNumerador() {
		int count = GeneraArchivoVte6.testPacientesMedida(true);
		assertTrue(count >= 0);
		
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoVte6#pacientesDenominador()}
	 */
	@Test
	public void testDenominador() {
		int count = GeneraArchivoVte6.testPacientesMedida(false);
		assertTrue(count >= 0);
	}
}
