/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.process.GeneraNQF142;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Prueba unitaria para clase GeneraNQF142
 * @author vperez
 */
public class GeneraNQF142Test extends TestCase {
	
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraNQF142#pacientesMedida()}
	 */
	@Test
	public void testNumerador() {
		int count = GeneraNQF142.testMeasure(true);
		assertTrue(count >= 0);
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link GeneraNQF142#pacientesDenominador()}
	 */
	@Test
	public void testDenominador() {
		int count = GeneraNQF142.testMeasure(false);
		assertTrue(count >= 0);
	}
}