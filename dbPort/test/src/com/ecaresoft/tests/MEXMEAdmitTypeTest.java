/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MEXMEAdmitType;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author Lorena Lama
 * 
 */
public class MEXMEAdmitTypeTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEAdmitType#getList(java.util.Properties, boolean, java.lang.String, boolean)}.
	 */
	public void testGetList() {
		assertNotNull(MEXMEAdmitType.getList(Env.getCtx(), true, " NAME ", true));
		assertNotNull(MEXMEAdmitType.getList(Env.getCtx(), true, null, false));

		final MEXMEAdmitType poModel = TestUtils.getRandomPO(MEXMEAdmitType.class, " isNewBorn='N' ", true);
		if (poModel == null) {
			assertNotNull(MEXMEAdmitType.getList(Env.getCtx(), false, null, false));
		} else {
			assertFalse(MEXMEAdmitType.getList(Env.getCtx(), false, null, false).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEAdmitType#copyFromSystem(java.util.Properties, java.lang.String)}.
	 */
	public void testCopyFromSystem() {
		fail("Not yet implemented"); // TODO
	}

}
