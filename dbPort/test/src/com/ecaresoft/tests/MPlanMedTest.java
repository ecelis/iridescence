/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MPlanMed;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Test Class form {@link org.compiere.model.MPlanMed}
 * 
 * @author lama
 */
public class MPlanMedTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MPlanMed#getPlanMed(java.util.Properties, int, int, java.lang.String)}
//	 */
//	@Test
//	public void testGetPlanMed() {
//		// forzar vacio
//		assertNull(MPlanMed.getPlanMed(Env.getCtx(), 0, 0, null));
//		// validar no null
//		final MPlanMed poModel = TestUtils.getRandomPO(MPlanMed.class);
//		if (poModel != null) {
//			assertNotNull(MPlanMed.getPlanMed(Env.getCtx(), poModel.getEXME_CtaPac_ID(), poModel.getM_Product_ID(), null));
//		}
//	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MPlanMed#getForCtaPacId(java.util.Properties, int, java.lang.String)}
	 */
	@Test
	public void testGetForCtaPacId() {
		// forzar vacio
		assertFalse(MPlanMed.getForCtaPacId(Env.getCtx(), 0, null));
		// validar no null
		final MPlanMed poModel = TestUtils.getRandomPO(MPlanMed.class);
		if (poModel != null) {
			assertTrue(MPlanMed.getForCtaPacId(Env.getCtx(), poModel.getEXME_CtaPac_ID(), null));
		}
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MPlanMed#getTratamientosDetalle(java.util.Properties, int, java.lang.String)}
	 */
	@Test
	public void testGetTratamientosDetalle() {
		// forzar vacio
		assertTrue(MPlanMed.getTratamientosDetalle(Env.getCtx(), 0, null).isEmpty());
		// validar no null
		final MPlanMed poModel = TestUtils.getRandomPO(MPlanMed.class, " EXME_TRATAMIENTOS_SESION_ID IS NOT NULL ", true);
		if (poModel != null) {
			assertFalse(MPlanMed.getTratamientosDetalle(Env.getCtx(), poModel.getEXME_Tratamientos_Sesion_ID(), null).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MPlanMed#getFromRxId(java.util.Properties, int)}
	 */
	@Test
	public void testGetFromRxIdPropertiesInt() {
		MPlanMed plan = TestUtils.getRandomPO(MPlanMed.class);
		if (plan != null) {
			if (plan.getEXME_PrescRXDet_ID() <= 0) {
				assertTrue(MPlanMed.getFromRxId(plan.getCtx(), plan.getEXME_PrescRXDet_ID()) <= 0);
			} else {
				assertEquals(plan.getEXME_PlanMed_ID(), MPlanMed.getFromRxId(plan.getCtx(), plan.getEXME_PrescRXDet_ID()));
			}
		}
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MPlanMed#getFromRxId(java.util.Properties, int, java.lang.String)}
	 */
	@Test
	public void testGetFromRxIdPropertiesIntString() {
		MPlanMed plan = TestUtils.getRandomPO(MPlanMed.class);
		if (plan != null) {
			if (plan.getEXME_PrescRXDet_ID() <= 0) {
				assertNull(MPlanMed.getFromRxId(plan.getCtx(), plan.getEXME_PrescRXDet_ID(), null));
			} else {
				MPlanMed plan2 = MPlanMed.getFromRxId(plan.getCtx(), plan.getEXME_PrescRXDet_ID(), null);
				assertNotNull(plan2);
				assertEquals(plan.getEXME_PlanMed_ID(), plan2.getEXME_PlanMed_ID());
			}
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MPlanMed#isCompleted(int, java.lang.String)}
	 */
	@Test
	public void testIsCompleted() {
		MPlanMed plan = TestUtils.getRandomPO(MPlanMed.class);
		if (plan != null) {
			if (plan.getEXME_PrescRXDet_ID() <= 0) {
				assertFalse(MPlanMed.isCompleted(plan.getEXME_PrescRXDet_ID(), null));
			} else {
				assertEquals(MPlanMed.DOCSTATUS_Completed.equals(plan.getDocStatus()), MPlanMed.isCompleted(plan.getEXME_PrescRXDet_ID(), null));
			}
		}
	}

}
