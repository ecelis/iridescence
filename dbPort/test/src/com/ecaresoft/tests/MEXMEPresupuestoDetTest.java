/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MEXMEPresupuestoDet;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author twry
 * 
 */
public class MEXMEPresupuestoDetTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEPresupuestoDet#consulta(java.util.Properties, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testConsulta() {
		final StringBuilder sql = MEXMEPresupuestoDet.consulta(Env.getCtx(),
				14, 0, null, null, null, null);
		assertTrue(sql.length() > 0);
	}

}
