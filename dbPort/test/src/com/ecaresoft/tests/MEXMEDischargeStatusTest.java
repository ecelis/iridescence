/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MEXMEDischargeStatus;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 * 
 */
public class MEXMEDischargeStatusTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEDischargeStatus#getList(java.util.Properties)}.
	 */
	public void testGetList() {
		final MEXMEDischargeStatus poModel = TestUtils.getRandomPO(MEXMEDischargeStatus.class);
		if (poModel == null) {
			assertTrue(MEXMEDischargeStatus.getList(Env.getCtx()).isEmpty());
		} else {
			assertFalse(MEXMEDischargeStatus.getList(Env.getCtx()).isEmpty());
		}
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEDischargeStatus#getList(java.util.Properties)}.
	 */
	public void testGetListValName() {
		final MEXMEDischargeStatus poModel = TestUtils.getRandomPO(MEXMEDischargeStatus.class);
		if (poModel == null) {
			assertTrue(MEXMEDischargeStatus.getListValName(Env.getCtx()).isEmpty());
		} else {
			assertFalse(MEXMEDischargeStatus.getListValName(Env.getCtx()).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEDischargeStatus#isExpiredfromID(java.util.Properties, int)}.
	 */
	public void testIsExpiredfromID() {
		fail("Not yet implemented"); // TODO
	}

}
