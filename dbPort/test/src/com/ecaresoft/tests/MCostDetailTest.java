/**
 * 
 */
package com.ecaresoft.tests;


import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostDetail;
import org.compiere.model.MProduct;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * @author mvrodriguez
 *
 */
public class MCostDetailTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

//	/**
//	 * Test method for {@link org.compiere.model.MCostDetail#createOrder(org.compiere.model.MAcctSchema, int, int, int, int, int, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testCreateOrder() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MCostDetail#createInvoice(org.compiere.model.MAcctSchema, int, int, int, int, int, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testCreateInvoice() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MCostDetail#createShipment(org.compiere.model.MAcctSchema, int, int, int, int, int, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, boolean, java.lang.String)}.
//	 */
//	@Test
//	public void testCreateShipment() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MCostDetail#createInventory(org.compiere.model.MAcctSchema, int, int, int, int, int, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testCreateInventory() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MCostDetail#createMovement(org.compiere.model.MAcctSchema, int, int, int, int, int, java.math.BigDecimal, java.math.BigDecimal, boolean, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testCreateMovement() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MCostDetail#createProduction(org.compiere.model.MAcctSchema, int, int, int, int, int, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testCreateProduction() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link org.compiere.model.MCostDetail#get(java.util.Properties, java.lang.String, int, int, int, java.lang.String)}.
	 */
	@Test
	public void testGet() {
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
		
		assertTrue(MCostDetail
				.get(Env.getCtx()
						, "C_OrderLine_ID=? "
						, 1000177
						, 0
						, ass[0].getC_AcctSchema_ID()
						, null)!=null);
	}

	/**
	 * Test method for {@link org.compiere.model.MCostDetail#processProduct(org.compiere.model.MProduct, java.lang.String)}.
	 */
	@Test
	public void testProcessProduct() {
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		assertTrue(MCostDetail
				.processProduct(product
						, null));
	}

}
