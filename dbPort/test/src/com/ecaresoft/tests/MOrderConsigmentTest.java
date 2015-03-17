package com.ecaresoft.tests;


import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MInventory;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.bean.TransferBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MOrderConsigmentTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		TestUtils.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		//super.tearDown();
	}

	@Test
	public final void testCreateMaterialReceiptMMovement() {
		final String where = " M_Warehouse_ID IN (SELECT  M_Warehouse_ID FROM M_Warehouse WHERE Consigna = 'Y' ) ";
		final MMovement mMovement = TestUtils.getRandomPO(MMovement.class, where, true);
		if(mMovement!=null) {
			List<TransferBean> lines = new ArrayList<TransferBean>();//TODO
			assertTrue(MOrder.createMaterialReceipt(mMovement, lines).isEmpty());
//				(mMovement, mMovement.getMovementLineForRM())==null);
		}
	}

	@Test
	public final void testCreateMaterialReceiptMInventory() {
		final String where = " M_Warehouse_ID IN (SELECT  M_Warehouse_ID FROM M_Warehouse WHERE Consigna = 'Y' ) ";
		final MInventory mInventory = TestUtils.getRandomPO(MInventory.class, where, true);
		if(mInventory!=null)
			assertTrue(MOrder.createMaterialReceipt(mInventory, mInventory.getInventoryLineForRM())==null);
	}

}
