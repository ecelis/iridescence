/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MCost;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.model.bpm.GetCost;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author twry
 *
 */
public class EnterChargeTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		TestUtils.setUpLake();
//		TestUtils.setUpCozby();
	}

	/**
	 * Test method for {@link org.compiere.model.MActPacienteDiag#getFromCtaPac(java.util.Properties, int, java.lang.String)}.
	 */
	@Test
	public final void testGetFromCtaPac() {
		/* Lake */
		
		int producto = 1230422;// PRod 00093-4150-73	amoxicillin 125 mg/5 mL oral liquid 
		MProduct product = new MProduct(Env.getCtx(), producto, null);
		int cta = 1000012;
//		assertFalse( EnterCharge.crearCargoTest(Env.getCtx(), Env.getM_Warehouse_ID(Env.getCtx()), Env.getEXME_EstServ_ID(Env.getCtx()), 
//				cta, producto, Env.ONE, product.getC_UOMVolume_ID(), null, false, null, null));//FALSO POR QUE NO HAY EXISTENCIAS
//		
////		// ahora con un servicio 
//		producto = 1474369; // Prod ServAlCuarto	servicio al 1/4
//		product = new MProduct(Env.getCtx(), producto, null);
//		cta = 1000492;
//		assertTrue( EnterCharge.crearCargoTest(Env.getCtx(), Env.getM_Warehouse_ID(Env.getCtx()), Env.getEXME_EstServ_ID(Env.getCtx()), 
//				cta, producto, Env.ONE, product.getC_UOMVolume_ID(), null, false, null, null));
////		
//		// ahora con medicamento 
//		producto = 1474331; // Prod AMETROCLOPRAMIDASolIny	METROCLOPRAMIDASol Iny c/6
//		product = new MProduct(Env.getCtx(), producto, null);
//		cta = 1000492;
//		assertTrue( EnterCharge.crearCargoTest(Env.getCtx(), Env.getM_Warehouse_ID(Env.getCtx()), Env.getEXME_EstServ_ID(Env.getCtx()), 
//				cta, producto, Env.ONE, product.getC_UOMVolume_ID(), null, false, null, null));
		 /* OTRA ORGANIZACION
		// ahora con medicamento 
		int producto = 1407622; // Prod 00004-2002-78	cefTRIAXone 1 g/50 mL intravenous solution
		int cta = 10031325;
		assertTrue( EnterCharge.crearCargoTest(Env.getCtx(), Env.getM_Warehouse_ID(Env.getCtx()), Env.getEXME_EstServ_ID(Env.getCtx()), 
				cta, producto, Env.ONE, 100, null, false, null, null));
		*/
	}
	
	/**
	 * Test method for {@link org.compiere.model.GetCost#setCosts()}.
	 */
	@Test
	public final void testSetCosts() {
		try {
			TestUtils.setUp200("ecsmxProd14Jul");
			final StringBuilder sql = new StringBuilder();
			sql.append(" M_CostElement_ID IN (");
			sql.append(" SELECT ce.M_CostElement_ID FROM m_costelement ce ");
			sql.append(" WHERE ce.costingmethod IN ('A','S','p')) ");
			sql.append(" AND currentcostprice > 0 ");
			final MCost mcost = new Query(Env.getCtx(), MCost.Table_Name, sql.toString(), null)//
				.setOnlyActiveRecords(true).addAccessLevelSQL(true).setOrderBy(" RANDOM() ").first();
			if (mcost != null) {
				GetCost cost = new GetCost(Env.getCtx(), mcost.getM_Product_ID());
				cost.setCosts();
				assertFalse(cost.getCostAverage() == null && cost.getCostStandard() == null && cost.getPriceLastPO() == null);
			}
		} catch (Exception e) {
			fail(e.toString());
		}
	}
}
