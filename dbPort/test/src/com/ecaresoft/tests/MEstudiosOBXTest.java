/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEstudiosOBX;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 * 
 */
public class MEstudiosOBXTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEstudiosOBX#hasOBX(java.util.Properties, int)}.
	 */
	public void testHasOBX() {
		// fail("Not yet implemented"); //TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEstudiosOBX#getEstudios(java.util.Properties, int, boolean, java.lang.String)}.
	 */
	public void testGetEstudiosPropertiesIntBooleanString() {
		// fail("Not yet implemented"); //TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEstudiosOBX#getEstudios(java.util.Properties, int, boolean, boolean, java.lang.String)}.
	 */
	public void testGetEstudiosPropertiesIntBooleanBooleanString() {
		// fail("Not yet implemented"); //TODO
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEstudiosOBX#getEstudios(java.util.Properties, int, boolean, boolean, java.lang.String, java.lang.String, boolean, java.lang.Object[])}
	 * .
	 */
	public void testGetEstudiosPropertiesIntBooleanBooleanStringStringBooleanObjectArray() {
		// fail("Not yet implemented"); //TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEstudiosOBX#getFromCtaPac(java.util.Properties, int, java.lang.String)}.
	 */
	public void testGetFromCtaPac() {
		// fail("Not yet implemented"); //TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEstudiosOBX#getFromPac(java.util.Properties, int, java.lang.String)}.
	 */
	public void testGetFromPac() {
		// fail("Not yet implemented"); //TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEstudiosOBX#getResultStatusStr()}.
	 */
	public void testGetResultStatusStr() {
		// fail("Not yet implemented"); //TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEstudiosOBX#getAbnormalFlagStr()}.
	 */
	public void testGetAbnormalFlagStr() {
		// para un producto random
		MEstudiosOBX obx = TestUtils.getRandomPO(MEstudiosOBX.class);
		if (obx != null) {
			if (obx.getAbnormalFlags() == null) {
				assertTrue(StringUtils.isEmpty(obx.getAbnormalFlagStr()));
			} else {
				assertNotNull(obx.getAbnormalFlagStr());
			}
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEstudiosOBX#getLoinc()}.
	 */
	public void testGetLoinc() {
		// para un producto random
		MEstudiosOBX obx = TestUtils.getRandomPO(MEstudiosOBX.class);
		if (obx != null) {
			assertNotNull(obx.getLoinc());
		}
	}

}
