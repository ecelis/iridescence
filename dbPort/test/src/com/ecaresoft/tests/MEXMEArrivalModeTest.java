/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MEXMEArrivalMode;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 * 
 */
public class MEXMEArrivalModeTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEArrivalMode#getList(java.util.Properties, boolean)}.
	 */
	public void testGetList() {
		assertNotNull(MEXMEArrivalMode.getList(Env.getCtx(), true));
		final MEXMEArrivalMode poModel = TestUtils.getRandomPO(MEXMEArrivalMode.class, " isNewBorn='N' ", true);
		if (poModel == null) {
			assertNotNull(MEXMEArrivalMode.getList(Env.getCtx(), false));
		} else {
			assertFalse(MEXMEArrivalMode.getList(Env.getCtx(), false).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEArrivalMode#copyFromSystem(java.util.Properties, java.lang.String)}.
	 */
	public void testCopyFromSystem() {
		fail("Not yet implemented"); // TODO
	}

}
