/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.process.GeneraArchivoVte4;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Pruebas unitarias para clase GeneraArchivoVte4
 * @author vperez
 */
public class GeneraArchivoVte4Test extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoVte4#pacientesMedida()}
	 */
	@Test
	public void testNumerador() {
		int count = GeneraArchivoVte4.testPacientesMedida(true);
		assertTrue(count >= 0);
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoVte4#pacientesDenominador()}
	 */
	@Test
	public void testDenominador() {
		int count = GeneraArchivoVte4.testPacientesMedida(false);
		assertTrue(count >= 0);
	}
}