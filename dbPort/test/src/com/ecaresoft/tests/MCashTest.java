/**
 * 
 */
package com.ecaresoft.tests;


import org.compiere.model.MCash;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * @author mvrodriguez
 *
 */
public class MCashTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MCash#getCash(java.util.Properties, int, int, java.lang.String)}.
	 */
	@Test
	public void testGetCashPropertiesIntIntString() {
		int list = MCash.getCash(Env.getCtx(), 1000004 , 1000003, null);
		assertFalse(list<=0);
	}

	/**
	 * Test method for {@link org.compiere.model.MCash#getCash(java.util.Properties, int, java.lang.String)}.
	 */
	@Test
	public void testGetCashPropertiesIntString() {
		MCash list = MCash.getCash(Env.getCtx(), 1000004 , null);
		assertFalse(list==null);
	}
}
