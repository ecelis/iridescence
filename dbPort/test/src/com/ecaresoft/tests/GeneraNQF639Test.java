package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.process.GeneraNQF639;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Prueba unitaria para clase GeneraNQF639
 * @author vperez
 */
public class GeneraNQF639Test extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraNQF639#pacientesMedida()}
	 */
	@Test
	public void testNumerador() {
		int count = GeneraNQF639.testMeasure(true);
		assertTrue(count >= 0);
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraNQF639#pacientesDenominador()}
	 */
	@Test
	public void testDenominador() {
		int count = GeneraNQF639.testMeasure(false);
		assertTrue(count >= 0);
	}
}
