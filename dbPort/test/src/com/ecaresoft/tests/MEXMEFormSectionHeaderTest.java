/**
 * 
 */
package com.ecaresoft.tests;

import org.compiere.model.MEXMEFormSectionHeader;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * @author jcarranza
 *
 */
public class MEXMEFormSectionHeaderTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFormSectionHeader#getEncunterForms(java.util.Properties, int, int, java.lang.String)}.
	 */
	@Test
	public void testGetEncunterFormsPropertiesIntIntString() {
		assertTrue(MEXMEFormSectionHeader.getEncunterForms(Env.getCtx(), 1000014, 1000131, null).size() > 0);
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFormSectionHeader#getEncunterForms(java.util.Properties, int, java.lang.String)}.
	 */
	@Test
	public void testGetEncunterFormsPropertiesIntString() {
		assertTrue(MEXMEFormSectionHeader.getEncunterForms(Env.getCtx(), 1000131, null).size() > 0);
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFormSectionHeader#getLst(java.util.Properties, java.lang.String, int, boolean, java.lang.String)}.
	 */
	@Test
	public void testGetLst() {
		assertTrue(MEXMEFormSectionHeader.getLst(Env.getCtx(), "", 1000131, true, null).size() > 0);
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFormSectionHeader#isDischarge()}.
	 */
	@Test
	public void testIsDischarge() {
		final MEXMEFormSectionHeader aux = new MEXMEFormSectionHeader(Env.getCtx(), 1000014, null);
		assertTrue(aux.isDischarge());
	}

}
