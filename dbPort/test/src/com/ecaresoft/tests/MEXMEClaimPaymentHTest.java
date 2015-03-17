package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEClaimPayment;
import org.compiere.model.MEXMEClaimPaymentH;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMEClaimPaymentHTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	// Valida cuando la tabla super no tenga headers, solo aplica para la
	// pantalla {@link WpaymentsByEncounter}
	@Test
	public void testgetHeadersString() {
		MEXMEClaimPaymentH payH = TestUtils.getRandomPO(MEXMEClaimPaymentH.class, " EXME_ClaimPayment_S_ID > 0", true);
		if (payH != null) {
			assertTrue(StringUtils.isNotBlank(MEXMEClaimPaymentH.getHeaders(Env.getCtx(), payH.getEXME_ClaimPayment_S_ID(), null)));
		}
	}

	@Test
	public void testGetClaimPayments() {
		MEXMEClaimPayment pay = TestUtils.getRandomPO(MEXMEClaimPayment.class);
	}
}
