package com.ecaresoft.tests;

import java.util.List;

import static org.junit.Assert.assertTrue;


import org.compiere.model.MCost;
import org.compiere.model.MEXMECost;
import org.compiere.model.MProduct;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MEXMECostTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECost#getCostProduct(java.util.Properties, int, java.lang.String)}.
	 */
	@Test
	public void testGetCostProduct() {
		MProduct product = TestUtils.getRandomPO(MProduct.class, null, true); //obtenemos un producto
		List<MCost> list = MEXMECost.getCostProduct(Env.getCtx(), product.getM_Product_ID(), null);
		if(list!=null && !list.isEmpty()){
			for(MCost cost: list){
				assertTrue(cost.isActive());
				assertTrue(cost.getM_Product_ID() == product.getM_Product_ID());
			}
		}
	}

}
