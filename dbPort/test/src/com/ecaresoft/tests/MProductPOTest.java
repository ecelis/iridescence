/**
 * 
 */
package com.ecaresoft.tests;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MBPartner;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author mvrodriguez
 *
 */
public class MProductPOTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MProductPO#getOfProduct(java.util.Properties, int, int, java.lang.String)}.
	 */
	@Test
	public final void testGetOfProductPropertiesIntIntString() {
		// obtener el costo por primera vez
		final int M_Product_ID=1474354; 
		final int AD_Client_ID=10001000;

		MProductPO[] mProductPO = MProductPO.getOfProduct (Env.getCtx(), 
				M_Product_ID, AD_Client_ID, null);

		assertTrue(mProductPO[0].getPricePO().compareTo(Env.ZERO)!=0);
	}
	
	/**
	 * Test method for {@link org.compiere.model.MProductPO#getPOLst(java.util.Properties, int, java.lang.String)}.
	 */
	@Test
	public void testGetPOLst() {
		MProduct product = TestUtils.getRandomPO(MProduct.class, null, true); //obtenemos un producto
		List<MProductPO> list = new ArrayList<MProductPO>();
		list = MProductPO.getPOLst(Env.getCtx(), product.getM_Product_ID(), true, null);
		if(list!=null && !list.isEmpty()){
			for(MProductPO po: list){
				assertTrue(po.isActive());
				assertTrue(po.getM_Product_ID() == product.getM_Product_ID());
			}
			
		}
	}
	
	/**
	 * Test method for {@link org.compiere.model.MProductPO#save(String trxName)}.
	 */
	@Test
	public void testSave() {
		
		MBPartner partner = TestUtils.getRandomPO( MBPartner.class, null, true); //obtenemos un socio de negocio
		MProduct product = TestUtils.getRandomPO(MProduct.class, "m_product_id not in " +
				"(select m_product_id from m_product_po where c_bpartner_id=10001001 and isactive = 'Y') ", true); //obtenemos producto

		
		MProductPO productPO = new MProductPO(Env.getCtx(), 0, null);
		
		Trx trx = null;
		try {
			trx = Trx.get(Trx.createTrxName("po"), true);//creamos una transaccion
			
			productPO.setC_BPartner_ID(partner.getC_BPartner_ID());
			productPO.setQualityRating(8);
			productPO.setUPC("TEST");
			productPO.setC_Currency_ID(Env.getC_Currency_ID(Env.getCtx()));
			productPO.setPriceList(new BigDecimal("25.20"));
			productPO.setPriceEffective(new Timestamp(System.currentTimeMillis()));
			productPO.setPricePO(new BigDecimal("25.20"));
			productPO.setPricePO(new BigDecimal("25.20"));
			productPO.setRoyaltyAmt(new BigDecimal("25.20"));
			productPO.setPriceLastPO(new BigDecimal("25.20"));
			productPO.setPriceLastInv(new BigDecimal("25.20"));
			productPO.setOrder_Min(new BigDecimal("25"));
			productPO.setOrder_Pack(new BigDecimal("35"));
			productPO.setDeliveryTime_Promised(15);
			productPO.setDeliveryTime_Actual(10);
			productPO.setCostPerOrder(new BigDecimal("35"));
			productPO.setVendorProductNo("PROVEEDOR");
			productPO.setVendorCategory("CATEGORIA");
			productPO.setManufacturer("FABRICANTE");
			productPO.setDiscontinued(false);
			productPO.setIsCurrentVendor(true);
			productPO.setIsActive(true);
			productPO.setM_Product_ID(product.getM_Product_ID());

			assertTrue(productPO.save(trx.getTrxName())); 
		} finally { // corrección: no se cerraba la transaccion .-  Lama
			Trx.rollback(trx, true); //cierre y rollback
		}
	}


}
