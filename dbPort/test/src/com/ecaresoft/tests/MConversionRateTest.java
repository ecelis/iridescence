package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MConversionRate;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MConversionRateTest extends TestCase{

	
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	@Test
	public void testGetConversionRate() {
		MConversionRate convRate = TestUtils.getRandomPO(MConversionRate.class);
		
		if (convRate != null) {
			assertEquals(MConversionRate.getConversionRate(convRate.getC_Currency_ID(), convRate.getC_Currency_ID_To()), convRate) ;
		}
	}
}
