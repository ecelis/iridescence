package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MEXMEAdjustmentType;
import org.compiere.model.MEXMEClaimPayment;
import org.compiere.model.MEXMEClaimPayment.ClaimCharge;
import org.compiere.model.MEXMEClaimPayment_S;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMEClaimPaymentTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	@Test
	public void test() {
		MEXMEClaimPayment_S payS = TestUtils.getRandomPO(MEXMEClaimPayment_S.class);
		if (payS != null) {
			assertTrue(MEXMEClaimPayment.getCountEncounters(Env.getCtx(), payS.get_ID()) > 0);
		}
	}

	public void testGetClaimCharges() {
		// Prueba de query para obtener los ClaimCharge asociados a la cuenta de
		// un ClaimPayment con ctaPac
		MEXMEClaimPayment claimPayment = TestUtils.getRandomPO(MEXMEClaimPayment.class, " EXME_CTAPAC_ID > 0 ", true);
		List<ClaimCharge> chargeSummary = claimPayment.getClaimCharges();
		assertTrue(!chargeSummary.isEmpty());
	}
	
	public void testRandom() {
		assertEquals(true, MEXMEAdjustmentType.getAdjustmentType(Env.getCtx(), MEXMEAdjustmentType.Type.INFORMATION_ADJUSTMENT).size()> 0);
	}
}
