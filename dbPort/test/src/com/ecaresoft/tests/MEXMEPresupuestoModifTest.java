/**
 * 
 */
package com.ecaresoft.tests;


import java.util.List;

import org.compiere.model.MEXMEPresupuestoModif;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * @author twry
 *
 */
public class MEXMEPresupuestoModifTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPresupuestoModif#getVersiones(java.util.Properties, int)}.
	 */
	@Test
	public void testGetVersiones() {
		List<MEXMEPresupuestoModif> lst = MEXMEPresupuestoModif.getVersiones(Env.getCtx(), 0);
		assertTrue(!lst.isEmpty());
	}
}
