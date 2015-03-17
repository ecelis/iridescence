/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.process.GeneraArchivoStroke3;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Pruebas unitarias para clase GeneraArchivoVte6
 * @author vperez
 */
public class GeneraArchivoStroke3Test extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoStroke3#pacientesMedida()}
	 */
	@Test
	public void testNumerador() {
		int count = GeneraArchivoStroke3.testPacientesMedida(true);
		assertTrue(count >= 0);
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoStroke3#pacientesDenominador()}
	 */
	@Test
	public void testDenominador() {
		int count = GeneraArchivoStroke3.testPacientesMedida(false);
		assertTrue(count >= 0);
	}
}
