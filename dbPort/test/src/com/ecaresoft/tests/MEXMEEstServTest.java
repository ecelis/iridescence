/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MEXMEEstServ;
import org.compiere.model.MEstServAlm;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 * 
 */
public class MEXMEEstServTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getTipoArea()}.
	 */
	public void testGetTipoArea() {
		MEXMEEstServ poModel = TestUtils.getRandomPO(MEXMEEstServ.class);
		if (poModel != null) {
			assertNotNull(poModel.getTipoArea());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getLstEstServTipoArea(java.util.Properties, java.lang.String, java.lang.String)}.
	 */
	public void testGetLstEstServTipoArea() {
		MEXMEEstServ poModel = TestUtils.getRandomPO(MEXMEEstServ.class);
		if (poModel == null) {
			assertTrue(MEXMEEstServ.getLstEstServTipoArea(Env.getCtx(), "", null).isEmpty());
		} else {
			assertFalse(MEXMEEstServ.getLstEstServTipoArea(Env.getCtx(), poModel.getTipoArea(), null).isEmpty());
		}
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEstServ#getLstLVBEstServTipoAreaUbicacion(java.util.Properties, java.util.List)}.
//	 */
//	public void testGetLstLVBEstServTipoAreaUbicacion() {
//		fail("Not yet implemented: deprecated"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getWarehouseRel(java.lang.String)}.
	 */
	public void testGetWarehouseRel() {
		MEXMEEstServ poModel = MEstServAlm.getEstServ(Env.getCtx(), Env.getM_Warehouse_ID(Env.getCtx()), null);
		if (poModel != null) {
			MWarehouse[] array = poModel.getWarehouseRel(null);
			assertNotNull(array);
			assertTrue(array.length > 0);
		}
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEstServ#getEstServOrg(java.util.Properties, boolean, java.lang.String)}.
//	 */
//	public void testGetEstServOrg() {
//		fail("Not yet implemented: deprecated"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEstServ#getEstServOrgGeneric(java.util.Properties, boolean, java.lang.String)}.
//	 */
//	public void testGetEstServOrgGeneric() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEEstServ#getEstServOrgObj(java.util.Properties, java.lang.String, java.lang.String, java.util.List, java.lang.String)}
	 * .
	 */
	public void testGetEstServOrgObj() {
		assertNotNull(MEXMEEstServ.getEstServOrgObj(Env.getCtx(), null, null, null, null).isEmpty());
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getEstaciones(java.util.Properties, java.lang.String)}.
	 */
	public void testGetEstaciones() {
		MEXMEEstServ poModel = TestUtils.getRandomPO(MEXMEEstServ.class);
		if (poModel == null) {
			assertTrue(MEXMEEstServ.getEstaciones(Env.getCtx(), null).length == 0);
		} else {
			assertTrue(MEXMEEstServ.getEstaciones(Env.getCtx(), null).length > 0);
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getFirstEstServ(java.util.Properties, java.lang.String)}.
	 */
	public void testGetFirstEstServ() {
		MEXMEEstServ poModel = TestUtils.getRandomPO(MEXMEEstServ.class);
		if (poModel == null) {
			assertNull(MEXMEEstServ.getFirstEstServ(Env.getCtx(), null));
		} else {
			assertNotNull(MEXMEEstServ.getFirstEstServ(Env.getCtx(), null));
		}
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEstServ#getEstServAlmOrgTrx(java.util.Properties, int)}.
//	 */
//	public void testGetEstServAlmOrgTrx() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEstServ#getEstServOrgTrx(java.util.Properties, int)}.
//	 */
//	public void testGetEstServOrgTrx() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEstServ#getAllEstServOrg(java.util.Properties, java.lang.String)}.
//	 */
//	public void testGetAllEstServOrg() {
//		fail("Not yet implemented: deprecated"); // TODO
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEEstServ#getLstEstServ(java.util.Properties, java.lang.StringBuilder, java.lang.StringBuilder, boolean, java.lang.String)}
//	 * .
//	 */
//	public void testGetLstEstServ() {
//		fail("Not yet implemented: deprecated"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getEstServAlmfromWh(java.util.Properties, int)}.
	 */
	public void testGetEstServAlmfromWh() {
		MEXMEEstServ poModel = MEstServAlm.getEstServ(Env.getCtx(), Env.getM_Warehouse_ID(Env.getCtx()), null);
		if (poModel == null) {
			assertNull(MEXMEEstServ.getEstServAlmfromWh(Env.getCtx(), 0));
		} else {
			assertNotNull(MEXMEEstServ.getEstServAlmfromWh(Env.getCtx(), Env.getM_Warehouse_ID(Env.getCtx())));
		}
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEstServ#getLstLVBEstServTipoArea(java.util.Properties, java.lang.String, java.lang.String)}.
//	 */
//	public void testGetLstLVBEstServTipoArea() {
//		fail("Not yet implemented: deprecated"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getFromValue(java.util.Properties, java.lang.String, java.lang.String)}.
	 */
	public void testGetFromValue() {
		MEXMEEstServ poModel = TestUtils.getRandomPO(MEXMEEstServ.class);
		if (poModel == null) {
			assertTrue(MEXMEEstServ.getFromValue(Env.getCtx(), "", null) <= 0);
		} else {
			assertTrue(MEXMEEstServ.getFromValue(Env.getCtx(), poModel.getValue(), null) > 0);
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getTipoAreaEstServ(java.util.Properties, long)}.
	 */
	public void testGetTipoAreaEstServ() {
		MEXMEEstServ poModel = TestUtils.getRandomPO(MEXMEEstServ.class);
		if (poModel == null) {
			assertNull(MEXMEEstServ.getTipoAreaEstServ(Env.getCtx(), 0));
		} else {
			assertNotNull(MEXMEEstServ.getTipoAreaEstServ(Env.getCtx(), poModel.getEXME_EstServ_ID()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getFullName()}.
	 */
	public void testGetFullName() {
		MEXMEEstServ poModel = TestUtils.getRandomPO(MEXMEEstServ.class);
		if (poModel != null) {
			assertNotNull(poModel.getFullName());
		}
	}

//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEEstServ#getEstServRole(java.util.Properties, java.lang.String, java.lang.String, java.util.List)}.
//	 */
//	public void testGetEstServRole() {
//		fail("Not yet implemented: deprecated"); // TODO
//	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEstServ#getEstServ(java.util.Properties, int, int, java.lang.String)}.
//	 */
//	public void testGetEstServ() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getEstServPorArea(java.util.Properties, int, java.lang.String)}.
	 */
	public void testGetEstServPorArea() {
		MEXMEEstServ poModel = TestUtils.getRandomPO(MEXMEEstServ.class);
		if (poModel == null) {
			assertTrue(MEXMEEstServ.getEstServPorArea(Env.getCtx(), 0, null).isEmpty());
		} else {
			assertFalse(MEXMEEstServ.getEstServPorArea(Env.getCtx(), poModel.getEXME_Area_ID(), null).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getLstLVBEstServTipoAreaUH(java.util.Properties)}.
	 */
	public void testGetLstLVBEstServTipoAreaUH() {
		MEXMEEstServ poModel = TestUtils.getRandomPO(MEXMEEstServ.class);
		if (poModel == null) {
			assertTrue(MEXMEEstServ.getLstLVBEstServTipoAreaUH(Env.getCtx()).isEmpty());
		} else {
			assertFalse(MEXMEEstServ.getLstLVBEstServTipoAreaUH(Env.getCtx()).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getEstServPorOrgGeneric(java.util.Properties, java.lang.String)}.
	 */
	public void testGetEstServPorOrgGeneric() {
		MEXMEEstServ poModel = TestUtils.getRandomPO(MEXMEEstServ.class);
		if (poModel == null) {
			assertTrue(MEXMEEstServ.getEstServPorOrgGeneric(Env.getCtx(), null).isEmpty());
		} else {
			assertFalse(MEXMEEstServ.getEstServPorOrgGeneric(Env.getCtx(), null).isEmpty());
		}
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEstServ#getEstServAgenda(java.util.Properties, java.lang.String, java.lang.String)}.
//	 */
//	public void testGetEstServAgenda() {
//		fail("Not yet implemented: deprecated"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEstServ#getFromCtx(java.util.Properties)}.
	 */
	public void testGetFromCtx() {
		assertEquals(Env.getEXME_EstServ_ID(Env.getCtx()), MEXMEEstServ.getFromCtx(Env.getCtx()).getEXME_EstServ_ID());
	}

}
