/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.process.GeneraArchivoVte5;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Pruebas unitarias para clase GeneraArchivoVte5
 * @author vperez
 */
public class GeneraArchivoVte5Test extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoVte5#pacientesMedida()}
	 */
	@Test
	public void testNumerador() {
		int count = GeneraArchivoVte5.testPacientesMedida(true);
		assertTrue(count >= 0);
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoVte5#pacientesDenominador()}
	 */
	@Test
	public void testDenominador() {
		int count = GeneraArchivoVte5.testPacientesMedida(false);
		assertTrue(count >= 0);
	}
}
