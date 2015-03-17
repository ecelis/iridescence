/**
 * 
 */
package com.ecaresoft.tests;


import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEFrequency2;
import org.compiere.model.MEXMEFrequency3;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * @author lama
 *
 */
public class MEXMEFrequency3Test extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFrequency3#MEXMEFrequency3(org.compiere.model.MEXMEFrequency2)}.
	 */
	@Test
	public void testMEXMEFrequency3MEXMEFrequency2() {
		final MEXMEFrequency2 freq2 = TestUtils.getRandomPO(MEXMEFrequency2.class);
		if(freq2 == null) {
			return;
		} else {
			final MEXMEFrequency3 freq3 = new MEXMEFrequency3(freq2);
			assertEquals(freq2.getEXME_Frequency1_ID(), freq3.getEXME_Frequency1_ID());
			assertEquals(freq2.getEXME_Frequency2_ID(), freq3.getEXME_Frequency2_ID());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFrequency3#getFrequencies3(java.util.Properties, int)}.
	 */
	@Test
	public void testGetFrequencies3() {
		assertTrue(MEXMEFrequency3.getFrequencies3(Env.getCtx(), 0).isEmpty());//force empty
		final MEXMEFrequency3 poModel = TestUtils.getRandomPO(MEXMEFrequency3.class);
		if (poModel != null) {
			assertFalse(MEXMEFrequency3.getFrequencies3(Env.getCtx(), poModel.getEXME_Frequency2_ID()).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFrequency3#getHourFormated()}.
	 */
	@Test
	public void testGetHourFormated() {
		// force blank
		assertTrue(StringUtils.isEmpty(new MEXMEFrequency3(Env.getCtx(), 0, null).getHourFormated()));
		final MEXMEFrequency3 poModel = TestUtils.getRandomPO(MEXMEFrequency3.class);
		if (poModel != null) {
			assertTrue(StringUtils.isNotBlank(poModel.getHourFormated()));
		}
	}

}
