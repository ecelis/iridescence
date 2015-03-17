package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.process.GeneraArchivoStroke10;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Pruebas unitarias para clase GeneraArchivoStroke10
 * @author kguerra
 */
public class GeneraArchivoVte10Test extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoVte10#pacientesMedida()}
	 */
	@Test
	public void testNumerador() {
		int count = GeneraArchivoStroke10.testPacientesMedida(true);
		assertTrue(count >= 0);
		
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoStroke10#pacientesDenominador()}
	 */
	@Test
	public void testDenominador() {
		int count = GeneraArchivoStroke10.testPacientesMedida(false);
		assertTrue(count >= 0);
	}
}

