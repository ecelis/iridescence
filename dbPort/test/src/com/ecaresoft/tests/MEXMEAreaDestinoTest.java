/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEAreaDestino;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author Lorena Lama
 */
public class MEXMEAreaDestinoTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEAreaDestino#getRecord(java.util.Properties, int, int)}.
	 */
	public void testGetRecord() {
		assertNull(MEXMEAreaDestino.getRecord(Env.getCtx(), 0, 0));
		final MEXMEAreaDestino poModel = TestUtils.getRandomPO(MEXMEAreaDestino.class);
		if (poModel != null) {
			assertNotNull(MEXMEAreaDestino.getRecord(Env.getCtx(), poModel.getEXME_Area_ID(), poModel.getEXME_AreaTo_ID()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEAreaDestino#getArea()}.
	 */
	public void testGetArea() {
		final MEXMEAreaDestino poModel = TestUtils.getRandomPO(MEXMEAreaDestino.class);
		if (poModel == null) {
			assertNull(new MEXMEAreaDestino(Env.getCtx(), 0, null).getArea());
		} else {
			assertNotNull(poModel.getArea());
		}
	}

	/** Test method for {@link org.compiere.model.MEXMEAreaDestino#getAreaTo()} */
	public void testGetAreaTo() {
		final MEXMEAreaDestino poModel = TestUtils.getRandomPO(MEXMEAreaDestino.class);
		if (poModel == null) {
			assertNull(new MEXMEAreaDestino(Env.getCtx(), 0, null).getAreaTo());
		} else {
			assertNotNull(poModel.getAreaTo());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEAreaDestino#getAreaStr()}.
	 */
	public void testGetAreaStr() {
		final MEXMEAreaDestino poModel = TestUtils.getRandomPO(MEXMEAreaDestino.class);
		if (poModel == null) {
			assertTrue(StringUtils.isEmpty(new MEXMEAreaDestino(Env.getCtx(), 0, null).getAreaStr()));
		} else {
			assertTrue(StringUtils.isNotEmpty(poModel.getAreaStr()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEAreaDestino#getAreaToStr()}.
	 */
	public void testGetAreaToStr() {
		final MEXMEAreaDestino poModel = TestUtils.getRandomPO(MEXMEAreaDestino.class);
		if (poModel == null) {
			assertTrue(StringUtils.isEmpty(new MEXMEAreaDestino(Env.getCtx(), 0, null).getAreaToStr()));
		} else {
			assertTrue(StringUtils.isNotEmpty(poModel.getAreaToStr()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEAreaDestino#getAlertMsg()}.
	 */
	public void testGetAlertMsg() {
		final MEXMEAreaDestino poModel = TestUtils.getRandomPO(MEXMEAreaDestino.class, " AlertMessage IS NOT NULL ", true);
		if (poModel == null) {
			assertTrue(StringUtils.isEmpty(new MEXMEAreaDestino(Env.getCtx(), 0, null).getAlertMsg()));
		} else {
			assertTrue(StringUtils.isNotEmpty(poModel.getAlertMsg()));
		}
	}

}
