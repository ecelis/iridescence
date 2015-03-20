package com.ecaresoft.tests;

import org.compiere.model.MEXMETax;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.compiere.model.X_C_Tax;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

public class MEXMETaxTest  extends TestCase{
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETax#getTax(java.util.Properties, int, String, String)}.
	 */
	public void testGetTax() {
		
		final MTaxCategory obj = TestUtils.getRandomPO(MTaxCategory.class);
		MTax tax = null;
		if (obj != null) {
			tax = MEXMETax.getTax(Env.getCtx(), obj.getC_TaxCategory_ID(), X_C_Tax.SOPOTYPE_All, null);
		}
		assertTrue(tax != null);// no debe regresa null, debe regresa una lista vacia
	}


}
