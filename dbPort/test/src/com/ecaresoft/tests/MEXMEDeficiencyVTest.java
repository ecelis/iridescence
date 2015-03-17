package com.ecaresoft.tests;

import org.compiere.model.MEXMEDeficiencyV;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

public class MEXMEDeficiencyVTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEDeficiencyV#getMeds(java.util.Properties)}.
	 */
	@Test
	public void testgetMeds(){
		assertTrue(MEXMEDeficiencyV.getMeds(Env.getCtx()).size() > 0);
	} 

}
