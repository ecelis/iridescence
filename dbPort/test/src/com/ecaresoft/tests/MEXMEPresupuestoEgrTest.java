/**
 * 
 */
package com.ecaresoft.tests;


import java.util.List;

import org.compiere.model.MEXMEPresupuestoDet;
import org.compiere.model.MEXMEPresupuestoEgr;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * @author twry
 *
 */
public class MEXMEPresupuestoEgrTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPresupuestoEgr#getPresupuestoDet(java.util.Properties, java.lang.String)}.
	 */
	@Test
	public void testGetPresupuestoDet() {
		List<MEXMEPresupuestoDet> lst = MEXMEPresupuestoEgr.getPresupuestoDet(Env.getCtx(), null);
		assertTrue(!lst.isEmpty());
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPresupuestoEgr#getNewVersion(java.util.Properties)}.
	 */
	@Test
	public void testGetNewVersion() {
		MEXMEPresupuestoEgr lst = MEXMEPresupuestoEgr.getNewVersion(Env.getCtx());
		assertTrue(lst!=null);
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPresupuestoEgr#getVersiones(java.util.Properties)}.
	 */
	@Test
	public void testGetVersiones() {
		List<MEXMEPresupuestoEgr> lst = MEXMEPresupuestoEgr.getVersiones(Env.getCtx());
		assertTrue(!lst.isEmpty());
	}

}
