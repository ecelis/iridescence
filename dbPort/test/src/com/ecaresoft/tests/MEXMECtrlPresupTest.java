/**
 * 
 */
package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MEXMECtrlPresup;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author twry
 *
 */
public class MEXMECtrlPresupTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtrlPresup#getModAutirizado(java.util.Properties, int, int, java.lang.String)}.
	 */
	@Test
	public void testGetModAutirizado() {
		List<MEXMECtrlPresup> lst = MEXMECtrlPresup.getModAutirizado(Env.getCtx(), 0,0,null);
		assertTrue(!lst.isEmpty());
	}
}
