package com.ecaresoft.tests;

import java.util.List;
import org.compiere.model.MOrder;
import org.compiere.util.Env;
import com.ecaresoft.tests.utils.TestUtils;
import junit.framework.TestCase;

public class MOrderTest  extends TestCase{
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MOrder#getPurchaseOrders(java.util.Properties, String, String)}.
	 */
	public void testGetPurchaseOrders() {
		
		List<MOrder> orders = MOrder.getPurchaseOrders(Env.getCtx(), null, null);
		assertTrue(orders.size() >= 0);// no debe regresa null, debe regresa una lista vacia
		
		List<MOrder> orders2 = MOrder.getPurchaseOrders(Env.getCtx(), " AND PriorityRule = '3' ", null);
		assertTrue(orders2.size() >= 0);// no debe regresa null, debe regresa una lista vacia
	}

}
