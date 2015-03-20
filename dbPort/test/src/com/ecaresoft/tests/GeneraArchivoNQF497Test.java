package com.ecaresoft.tests;

import java.util.List;

import org.compiere.process.GeneraArchivoNQF497;

import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

public class GeneraArchivoNQF497Test extends TestCase {
	/**
	 * Pruebas unitarias para clase GeneraArchivoNQF497
	 * @author vperez
	 */

		@Before
		public void setUp() throws Exception {
			TestUtils.setUp();
		}
		
		/**
		 * Pruebas de funcionalidad para metodo {@link GeneraArchivoNQF497#executaQueryNumDen1()}
		 */
		@Test
		public void testEjecutaQueryNum() {
			List <Object> lista = GeneraArchivoNQF497.testEjecutaQueryNum();
			assertTrue(lista != null);
		}
		
		/**
		 * Pruebas de funcionalidad para metodo {@link GeneraArchivoNQF497#pacientesObs()}
		 */
		@Test
		public void testPacientesObs() {
			List <Object> lista = GeneraArchivoNQF497.testPacientesObs();
			assertTrue( lista != null);
		}
		
		/**
		 * Pruebas de funcionalidad para metodo {@link GeneraArchivoNQF497#pacientesMentales()}
		 */
		@Test
		public void testPacientesMentales() {
			List <Object> lista = GeneraArchivoNQF497.testPacientesMentales();
			assertTrue(lista != null);
		}
}
