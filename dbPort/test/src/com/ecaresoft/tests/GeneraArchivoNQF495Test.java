package com.ecaresoft.tests;

import java.util.List;

import org.compiere.process.GeneraArchivoNQF495;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * Pruebas unitarias para clase GeneraArchivoNQF495
 * @author kguerra
 */

public class GeneraArchivoNQF495Test extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoNQF495#executaQueryNumDen1()}
	 */
	@Test
	public void testExecutaQueryNumDen1() {
		List<Object> lst= GeneraArchivoNQF495.testExecutaQueryNumDen1();
		assertTrue(lst != null );
		
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoNQF495#pacientesObs()}
	 */
	@Test
	public void testpacientesObs() {
		List<Object> lst= GeneraArchivoNQF495.testpacientesObs();
		assertTrue(lst != null );
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraArchivoNQF495#pacientesMentales()}
	 */
	@Test
	public void testpacientesMentales() {
		List<Object> lst= GeneraArchivoNQF495.testpacientesMentales();
		assertTrue(lst != null );
	}
}