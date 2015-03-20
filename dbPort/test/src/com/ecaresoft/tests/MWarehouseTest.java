package com.ecaresoft.tests;

import java.sql.SQLException;
import java.util.List;

import junit.framework.TestCase;

import org.apache.struts.util.LabelValueBean;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author Lorena Lama
 *
 */
public class MWarehouseTest extends TestCase {

	/**@see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#beforeSave(boolean)}.
//	 */
//	public void testBeforeSave() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#afterSave(boolean, boolean)}.
//	 */
//	public void testAfterSave() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#beforeDelete()}.
//	 */
//	public void testBeforeDelete() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#get(java.util.Properties, int)}.
//	 */
//	public void testGet() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getForOrg(java.util.Properties, int)}.
//	 */
//	public void testGetForOrg() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getForOrgList(java.util.Properties)}.
//	 */
//	public void testGetForOrgList() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getLocators(boolean)}.
//	 */
//	public void testGetLocators() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getDefaultLocator()}.
//	 */
//	public void testGetDefaultLocator() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getWareHouses(int)}.
//	 */
//	public void testGetWareHouses() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getIdWarehouse(java.lang.String)}.
//	 */
//	public void testGetIdWarehouse() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getIdWHByValue(java.util.Properties, java.lang.String)}.
//	 */
//	public void testGetIdWHByValue() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getIdWarehouseDesc(java.lang.String)}.
//	 */
//	public void testGetIdWarehouseDesc() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getWarehouseNotInReplenish(java.util.Properties, java.lang.String, int)}.
//	 */
//	public void testGetWarehouseNotInReplenish() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getWarehouseRelatedProduct(java.util.Properties, java.lang.String, int)}.
//	 */
//	public void testGetWarehouseRelatedProduct() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getWarehouse(java.util.Properties)}.
//	 */
//	public void testGetWarehouse() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getAlmacenesDeEstacion(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetAlmacenesDeEstacion() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getExistPharmacyOnWarehouse(java.util.Properties, int)}.
//	 */
//	public void testGetExistPharmacyOnWarehouse() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getMWarehouseByValue(java.util.Properties, java.lang.String)}.
//	 */
//	public void testGetMWarehouseByValue() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getLocation()}.
//	 */
//	public void testGetLocation() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getLocationStr(boolean)}.
//	 */
//	public void testGetLocationStr() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#isManualTestResults(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testIsManualTestResultsPropertiesIntString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#isManualTestResults()}.
//	 */
//	public void testIsManualTestResults() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getAD_OrgTrx_ID()}.
//	 */
//	public void testGetAD_OrgTrx_ID() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MWarehouse#getWarehouseByType(java.util.Properties, java.lang.String)}.
	 */
	public void testGetWarehouseByType() {
		try {
			MWarehouse mware= TestUtils.getRandomPO(MWarehouse.class);
			List<LabelValueBean> lst = MWarehouse.getWarehouseByType(Env.getCtx(), mware.getType());
			//Debe existir almenos un almacen con el tipo que se obtuvo del valor aleatorio
			assertFalse(lst.isEmpty());
			
			lst = MWarehouse.getWarehouseByType(Env.getCtx(), "ValorIncorrecto");
			assertTrue(lst.isEmpty());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	/**
//	 * Test method for {@link org.compiere.model.MWarehouse#getWHBySUAndType(java.util.Properties, java.lang.String, int)}.
//	 */
//	public void testGetWHBySUAndType() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MWarehouse#getIntervalo(boolean)}.
	 */
	public void testGetIntervaloBoolean() {
		final MWarehouse ware = new MWarehouse(Env.getCtx(), 0, null);
		assertEquals(0, ware.getIntervalo(false));
		assertEquals(30, ware.getIntervalo(true));
		// random
		final MWarehouse obj = TestUtils.getRandomPO(MWarehouse.class);
		if (obj != null) {
			assertEquals(obj.getIntervalo(), obj.getIntervalo(false));
			if (obj.getIntervalo() <= 0) {
				assertEquals(30, obj.getIntervalo(true));
			}
		}
	}

}
