package com.ecaresoft.tests;

import org.compiere.process.GeneraArchivoStroke4;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

public class GeneraArchivoStroke4Test extends TestCase {
	/**
	 * Pruebas unitarias para clase GeneraArchivoStroke4
	 * @author vperez
	 */

		@Before
		public void setUp() throws Exception {
			TestUtils.setUp();
		}
		
		/**
		 * Pruebas de funcionalidad para metodo {@link GeneraArchivoStroke4#pacientesMedida()}
		 */
		@Test
		public void testNumerador() {
			int count = GeneraArchivoStroke4.testPacientesMedida(true);
			assertTrue(count >= 0);
		}
		
		/**
		 * Pruebas de funcionalidad para metodo {@link GeneraArchivoStroke4#pacientesDenominador()}
		 */
		@Test
		public void testDenominador() {
			int count = GeneraArchivoStroke4.testPacientesMedida(false);
			assertTrue(count >= 0);
		}
	

}
