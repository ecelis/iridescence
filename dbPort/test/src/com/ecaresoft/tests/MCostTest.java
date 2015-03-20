/**
 *
 */
package com.ecaresoft.tests;


import java.math.BigDecimal;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * @author mvrodriguez
 */
public class MCostTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

//	@Test
//	public final void testGetCurrentCost() {
//		int org = 0;// Default cero
//		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
//		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
//		assertTrue(MCost.getCurrentCost(
//				product
//				, 0 //M_AttributeSetInstance_ID
//				, ass[0]
//				, org // Organizacion cero
//				, product.getCostingMethod(ass[0])
//				, Env.ONE
//				, 0 //C_OrderLine_ID
//				, false //zeroCostsOK
//				, (String)null).compareTo(Env.ZERO)!=0);
//	}

	@Test
	public final void testGetCurrentCostTest() {
		int org = 0;// Default cero
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
		assertTrue(MCost.getCurrentCostTest(
				product
				, 0 //M_AttributeSetInstance_ID
				, ass[0]
				, org // Organizacion cero
				, ass[0].getM_CostType_ID()
				, product.getCostingMethod(ass[0])
				, Env.ONE
				, 0 //C_OrderLine_ID
				, false //zeroCostsOK
				, null).compareTo(Env.ZERO)!=0);
	}

	@Test
	public final void testGetSeedCosts() {
		int org = 0;// Default cero
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
		assertTrue(MCost.getSeedCosts(product
				, 0
				, ass[0]
				, org
				, product.getCostingMethod(ass[0])
				, 0).compareTo(Env.ZERO)!=0);
	}


	/**
	 * Test method for {@link org.compiere.model.MCost#costProductPO(org.compiere.model.MProduct, org.compiere.model.MAcctSchema, int)}.
	 */
	@Test
	public final void testCostProductPO() {
		int org = 0;// Default cero
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
		assertTrue(MCost.costProductPO(product, ass[0], org).compareTo(Env.ZERO)!=0);
	}

	@Test
	public final void testGetLastInvoicePrice() {
		int org = 0;// Default cero
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		assertTrue(MCost.getLastInvoicePrice(product, 0, org, 130).compareTo(Env.ZERO)==0);
	}

	@Test
	public final void testGetLastPOPrice() {
		int org = 0;// Default cero
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		assertTrue(MCost.getLastPOPrice(product, 0, org, 130).compareTo(Env.ZERO)==0);
	}

	@Test
	public final void testGetPOPrice() {
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		assertTrue(MCost.getPOPrice(product, 1000177, 130).compareTo(Env.ZERO)!=0);
	}

	@Test
	public final void testCreate() {
		assertTrue(MCost.create(new MClient(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()), null)));
	}

	@Test
	public final void testCreateCost() {
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		assertTrue(MCost.createCost(product, Env.ONE));
	}

	@Test
	public final void testCalculateAverageInv() {
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
		assertTrue(MCost.calculateAverageInv(product
				, 0
				, ass[0]
				, 0).compareTo(Env.ZERO)!=0);
	}

	@Test
	public final void testCalculateAveragePO() {
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
		assertTrue(MCost.calculateAveragePO(product
				, 0
				, ass[0]
				, 0).compareTo(Env.ZERO)!=0);
	}

	@Test
	public final void testCalculateFiFo() {
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
		assertTrue(MCost.calculateFiFo(product
				, 0
				, ass[0]
				, 0).compareTo(Env.ZERO)!=0);
	}

	@Test
	public final void testCalculateLiFo() {
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
		assertTrue(MCost.calculateLiFo(product
				, 0
				, ass[0]
				, 0).compareTo(Env.ZERO)!=0);
	}

	@Test
	public final void testGet() {
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
		assertTrue(MCost.get(product
				, 0
				, ass[0]
				, 0
				, 0 //M_CostElement_ID
				, null)!=null);
	}

	@Test
	public final void testGet2() {
		MProduct product =  new MProduct(Env.getCtx(), 1474354, null);
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
		assertTrue(MCost.get(Env.getCtx()
				, Env.getAD_Client_ID(Env.getCtx())
				, 0
				, product.getM_Product_ID()
				, ass[0].getM_CostType_ID()
				, ass[0].getC_AcctSchema_ID()
				, 0 //M_CostElement_ID
				, 0 //M_AttributeSetInstance_ID
				, null
				, true)!=null);
	}
	/**
	 * Test method for {@link org.compiere.model.MCost#materialCostEachStd(MProduct, int, MAcctSchema, int, int, String, BigDecimal, int, boolean, String)}.
	 */
	@Test
	public final void testMaterialCostEachStd() {
		MProduct product =  new MProduct(Env.getCtx(), 1474340, null);
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
		int M_ASI_ID = 0;
		int M_CostType_ID = ass[0].getM_CostType_ID();
		int C_OrderLine_ID = 0;
		BigDecimal materialCostEach = MCost.materialCostEachStd(
				product,
				M_ASI_ID, //M_AttributeSetInstance_ID
				ass[0],
				0, //siempre debe ser cero Env.getAD_Org_ID(Env.getCtx()),
				M_CostType_ID,
				MCostElement.COSTINGMETHOD_StandardCosting,
				Env.ZERO,
				C_OrderLine_ID,
				true,  null);

		assertTrue(materialCostEach.compareTo(Env.ZERO)!=0);
	}
}