/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MEXMEEsqInsulina;
import org.compiere.model.MEXMEEsqInsulinaLine;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Test Class form {@link org.compiere.model.MEXMEEsqInsulinaLine}
 * 
 * @author lama
 */
public class MEXMEEsqInsulinaLineTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEEsqInsulinaLine#setEXME_EsqInsulina(org.compiere.model.MEXMEEsqInsulina)}
	 */
	@Test
	public void testSetEXME_EsqInsulina() {
		final MEXMEEsqInsulina insulin = TestUtils.getRandomPO(MEXMEEsqInsulina.class);
		final MEXMEEsqInsulinaLine detail = new MEXMEEsqInsulinaLine(Env.getCtx(), 0, null);
		detail.setEXME_EsqInsulina(insulin);
		assertEquals(insulin, detail.getEXME_EsqInsulina());
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEEsqInsulinaLine#getDetail(java.util.Properties, int, java.lang.String)}
	 */
	@Test
	public void testGetDetail() {
		assertTrue(MEXMEEsqInsulinaLine.getDetail(Env.getCtx(), 0, null).isEmpty());
		final MEXMEEsqInsulinaLine insulin = TestUtils.getRandomPO(MEXMEEsqInsulinaLine.class);
		if (insulin != null) {
			assertFalse(MEXMEEsqInsulinaLine.getDetail(Env.getCtx(), insulin.getEXME_EsqInsulina_ID(), null).isEmpty());
		}
	}

}
