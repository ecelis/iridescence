/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.process.GeneraArchivoStroke2;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Pruebas unitarias para clase GeneraArchivoVte4
 * @author vperez
  */
public class GeneraArchivoStroke2Test extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoStroke2#pacientesMedida()}
	 */
	@Test
	public void testNumerador() {
		int count = GeneraArchivoStroke2.testPacientesMedida(true);
		assertTrue(count >= 0);
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoStroke2#pacientesDenominador()}
	 */
	@Test
	public void testDenominador() {
		int count = GeneraArchivoStroke2.testPacientesMedida(false);
		assertTrue(count >= 0);
	}
}
