package com.ecaresoft.tests;
import org.compiere.process.GeneraArchivoStroke5;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

public class GeneraArchivoStroke5Test extends TestCase {
	/**
	 * Pruebas unitarias para clase GeneraArchivoStroke5
	 * @author vperez
	 */

		@Before
		public void setUp() throws Exception {
			TestUtils.setUp();
		}
		
		/**
		 * Pruebas de funcionalidad para metodo {@link GeneraArchivoStroke5#pacientesMedida()}
		 */
		@Test
		public void testNumerador() {
			int count = GeneraArchivoStroke5.testPacientesMedida(true);
			assertTrue(count >= 0);
		}
		
		/**
		 * Pruebas de funcionalidad para metodo {@link GeneraArchivoStroke5#pacientesDenominador()}
		 */
		@Test
		public void testDenominador() {
			int count = GeneraArchivoStroke5.testPacientesMedida(false);
			assertTrue(count >= 0);
		}
	

}
