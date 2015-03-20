package com.ecaresoft.tests;
import junit.framework.TestCase;

import org.compiere.model.MEXMEAdmitSource;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * 
 */

/**
 * @author lama
 *
 */
public class MEXMEAdmitSourceTest extends TestCase {

	/**@see junit.framework.TestCase#setUp()*/
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEAdmitSource#getList(java.util.Properties, boolean)}.
	 */
	public void testGetList() {
		assertNotNull(MEXMEAdmitSource.getList(Env.getCtx(), true));
		final MEXMEAdmitSource poModel = TestUtils.getRandomPO(MEXMEAdmitSource.class, " isNewBorn='N' ", true);
		if(poModel == null){
			assertNotNull(MEXMEAdmitSource.getList(Env.getCtx(), false));
		} else {
			assertFalse(MEXMEAdmitSource.getList(Env.getCtx(), false).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEAdmitSource#copyFromSystem(java.util.Properties, java.lang.String)}.
	 */
	public void testCopyFromSystem() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEAdmitSource#getfromClaimCode(java.util.Properties, java.lang.String)}.
	 */
	public void testGetfromClaimCode() {
		fail("Not yet implemented"); // TODO
	}

}
