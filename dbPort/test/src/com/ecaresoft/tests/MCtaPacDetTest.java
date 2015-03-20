/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MCtaPacDet;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author mvrodriguez
 *
 */
public class MCtaPacDetTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MCtaPacDet#getTotalCargos(java.util.Properties, int, java.lang.String)}.
	 */
	@Test
	public final void testGetTotalCargos() {
		int ctapac = 1000034;// cambiar de cuenta segun sea el caso
		assertTrue(MCtaPacDet.getTotalCargos(Env.getCtx(), ctapac, null).compareTo(Env.ZERO)==0);
	}

}
