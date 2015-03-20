/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MPlanMedLine;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author erick
 * 
 */
public class MPlanMedLineTest extends TestCase {

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#toString()}.
//	 */
//	public void testToString() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#afterSave(boolean, boolean)}.
//	 */
//	public void testAfterSave() {
//		fail("Not yet implemented");
//	}

//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MPlanMedLine#getPlanMedLine(java.util.Properties, java.lang.String, java.util.List, java.lang.String)}
//	 * .
//	 */
//	public void testGetPlanMedLine() {
//		fail("Not yet implemented");
//	}

//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MPlanMedLine#getCountPrescRX(java.util.Properties, int, java.util.Date, java.util.Date, java.lang.String[])}
//	 * .
//	 */
//	public void testGetCountPrescRX() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MPlanMedLine#getLinesPrescRX(java.util.Properties, int, java.util.Date, java.util.Date, java.lang.Boolean, java.lang.Integer[], boolean, java.lang.String[])}
//	 * .
//	 */
//	public void testGetLinesPrescRX() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MPlanMedLine#getSQLPrescRX(java.util.Properties, boolean, int, java.util.Date, java.util.Date, java.lang.Boolean, java.lang.Integer[], boolean, java.util.List, java.lang.String[])}
//	 * .
//	 */
//	public void testGetSQLPrescRX() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MPlanMedLine#getLastApliedDate(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetLastApliedDate() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MPlanMedLine#getLastDose(java.util.Properties, int, boolean, java.lang.String)}.
//	 */
//	public void testGetLastDose() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MPlanMedLine#getCountDoses(java.util.Properties, int, java.lang.String, java.lang.String)}
//	 * .
//	 */
//	public void testGetCountDoses() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#getPlanMed()}.
//	 */
//	public void testGetPlanMed() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#setPlanMed(org.compiere.model.MPlanMed)}.
//	 */
//	public void testSetPlanMed() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#hasData(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testHasData() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#getData(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetData() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#isSelectDoses()}.
//	 */
//	public void testIsSelectDoses() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#setSelectDoses(boolean)}.
//	 */
//	public void testSetSelectDoses() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#rejectVaccine()}.
//	 */
//	public void testRejectVaccine() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#applyVAccine(org.compiere.model.MEXMEHistVacuna)}.
//	 */
//	public void testApplyVAccine() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#getMedName()}.
//	 */
//	public void testGetMedName() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#getDoseRate()}.
//	 */
//	public void testGetDoseRate() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#getIsPRN()}.
//	 */
//	public void testGetIsPRN() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link org.compiere.model.MPlanMedLine#getIsPrnString()}.
	 */
	public void testGetIsPrnString() {

		MPlanMedLine line = TestUtils.getRandomPO(MPlanMedLine.class);
		if (line != null) {
			String prn = "No";
			if (line.getEXME_PlanMed().getEXME_PrescRXDet().isPRN()) {
				prn = "Yes";

			}
			assertEquals(prn, line.getIsPrnString());
		}

	}

	/**
	 * Test method for {@link org.compiere.model.MPlanMedLine#getUserName()}.
	 */
	public void testGetUserName() {

		MPlanMedLine line = TestUtils.getRandomPO(MPlanMedLine.class);
		if (line != null) {

			assertNotNull(line.getUserName());

		}

	}

//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#getRoute()}.
//	 */
//	public void testGetRoute() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#getFreq1()}.
//	 */
//	public void testGetFreq1() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#getFreq2()}.
//	 */
//	public void testGetFreq2() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link org.compiere.model.MPlanMedLine#getFreq()}.
	 */
	public void testGetFreq() {

		MPlanMedLine line = TestUtils.getRandomPO(MPlanMedLine.class);
		if (line != null) {

			assertNotNull(line.getFreq());

		}
	}

//	/**
//	 * Test method for {@link org.compiere.model.MPlanMedLine#getIndications()}.
//	 */
//	public void testGetIndications() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MPlanMedLine#createInsulinMedOrder(org.compiere.model.MEXMEEsqInsulinaLine, org.compiere.model.MEXMEFrequency2, java.lang.String)}
//	 * .
//	 */
//	public void testCreateInsulinMedOrder() {
//		fail("Not yet implemented");
//	}

}
