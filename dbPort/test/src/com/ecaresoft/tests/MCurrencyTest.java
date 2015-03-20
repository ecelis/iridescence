package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.compiere.model.MCurrency;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MCurrencyTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MCurrency#getActiveCurrencies(java.util.Properties)}.
	 */
	@Test
	public void testGetActiveCurrencies() {
		
		List<MCurrency> lstCurrency = MCurrency.getActiveCurrencies(Env.getCtx());
		assertNotNull(lstCurrency);
		for(MCurrency curr: lstCurrency){
			assertTrue(curr.isActive());
		}
	}

}
