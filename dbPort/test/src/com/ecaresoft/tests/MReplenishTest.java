package com.ecaresoft.tests;

import java.util.Properties;

import junit.framework.TestCase;

import org.compiere.model.MProduct;
import org.compiere.model.MReplenish;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MReplenishTest extends TestCase {

	Properties ctx = Env.getCtx();
	
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}
	/**
	 * Metodo de prueba para obtenr la llave primaria de m_replenish
	 */
	@Test
	public void testGet() {
		MWarehouse warehouse = TestUtils.getRandomPO(MWarehouse.class);
		MProduct product = TestUtils.getRandomPO(MProduct.class, 
				"m_product_id in(SELECT m_product_id FROM M_Warehouse where M_Warehouse_id = "
				+ warehouse.getM_Warehouse_ID() + " )", true);
		MReplenish replenish = MReplenish.get(ctx, warehouse.getM_Warehouse_ID() , product.getM_Product_ID(), null);
		assertNull(replenish);
		
		warehouse = TestUtils.getRandomPO(MWarehouse.class,
				"m_warehouse_id in(SELECT m_warehouse_id FROM m_replenish)", false);
		product = TestUtils.getRandomPO(MProduct.class, 
				"m_product_id in(SELECT m_product_id FROM m_replenish where M_Warehouse_id = "
				+ warehouse.getM_Warehouse_ID() + ")", false);
		replenish = MReplenish.get(ctx, warehouse.getM_Warehouse_ID() , product.getM_Product_ID(), null);
		assertNotNull(replenish);
	}

}
