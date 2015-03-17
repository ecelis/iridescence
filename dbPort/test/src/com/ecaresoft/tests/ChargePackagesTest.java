/**
 * 
 */
package com.ecaresoft.tests;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author mvrodriguez
 *
 */
public class ChargePackagesTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	@Test
	public final void testGetMatch() {
		// Hace el proceso de match y regresa true si todo ha sido correcto
		//assertTrue(ChargePackages.getMatch(Env.getCtx(), 1000492, null));
	}

}
