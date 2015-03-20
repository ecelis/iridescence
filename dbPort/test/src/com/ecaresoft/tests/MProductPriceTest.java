package com.ecaresoft.tests;


import java.math.BigDecimal;
import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MEXMEPriceListVersion;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author mvrodriguez
 *
 */
public class MProductPriceTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();// 
	}

	/**
	 * Test method for {@link org.compiere.model.MProductPrice#getProductPrice(int, int, int, boolean)}.
	 */
	@Test
	public final void testGetProductPrice() {
		
		int idSocio = 1204667; 
		int idPriceList=0; 
		int productId=1474364; //ACECLOFENACO100 a nivel de org lake
		boolean Venta=false;// caso true esta en otra clase :P
		assertTrue(MProductPrice.getProductPrice(idSocio, idPriceList, productId, Venta)==null);
		
		idSocio = 0;
		idPriceList = 1100029; 
		assertTrue(MProductPrice.getProductPrice(idSocio, idPriceList, productId, Venta)==null);
		
		assertTrue(MProductPrice.getProductPrice(0, 0, productId, Venta)==null);
		
		//Regresa nulo si no se envian parametros
		MProductPrice pPriceTNull = MProductPrice.getProductPrice(0, 0, 0, true);
		assertTrue(pPriceTNull == null);
		
		//Buscamos el producto y la version para obtener el producto precio, debe regresar un valor
		MEXMEPriceListVersion version = TestUtils.getRandomPO(MEXMEPriceListVersion.class, 
				"m_pricelist_version_id in(select m_pricelist_version_id from m_productprice)", false);
		MProduct product = TestUtils.getRandomPO(MProduct.class, "m_product_id in (select m_product_id from m_productprice)", false);

		MProductPrice pPriceFNull = MProductPrice.getProductPrice(0, version.getM_PriceList_ID(), product.getM_Product_ID(), false);
		assertFalse(pPriceFNull == null);		
	}
	
	/**
	 * Test method for {@link org.compiere.model.MProductPrice#getByProduct(java.util.Properties, int, java.lang.String)}.
	 */
	@Test
	public void testGetByProduct() {
		MProduct product = TestUtils.getRandomPO(MProduct.class, null, true); //obtenemos un producto
		List<MProductPrice> lstPrices = MProductPrice.getByProduct(Env.getCtx(), product.getM_Product_ID(), true, null);
		if(lstPrices!=null && !lstPrices.isEmpty()){
			//verificamos que los precios correspondan al producto y sean activos
			for(MProductPrice price: lstPrices){
				assertTrue(price.isActive());
				assertTrue(price.getM_Product_ID() == product.getM_Product_ID());
			}
			
		}
	}
	
	/**
	 * Test method for {@link org.compiere.model.MProductPrice#save()}.
	 */
	@Test
	public void testSave() {
		
		MPriceList lista = TestUtils.getRandomPO(MPriceList.class, " M_PriceList_ID IN (" +
				"SELECT M_PriceList_ID FROM M_PriceList_Version WHERE IsActive = 'Y') ", true); //obtenemos una lista de precios
		MPriceListVersion version = TestUtils.getRandomPO(MPriceListVersion.class, "M_PriceList_ID = " + lista.getM_PriceList_ID(), true); //obtenemos una version
		
		Trx trx = null;
		trx = Trx.get(Trx.createTrxName("price"), true);//creamos una transaccion
		
		//precio nuevo, no existente en la lista
		MProduct product = TestUtils.getRandomPO(MProduct.class, " C_UOM_Id = C_UOMVolume_ID AND M_Product_ID NOT IN (" +
				"SELECT M_Product_ID FROM M_ProductPrice WHERE M_PriceList_Version_ID = " + 
				version.getM_PriceList_Version_ID() + " ) ", true); //obtenemos un producto, para el cual sus dos unidades sean iguales y no tenga un precio configurado
		
		MProductPrice price = new MProductPrice(Env.getCtx(), 0, null);
		
		price.setM_PriceList_Version_ID(version.getM_PriceList_Version_ID());
		price.setM_Product_ID(product.getM_Product_ID());
		price.setC_UOM_ID(product.getC_UOM_ID());
		price.setC_UOMVolume_ID(product.getC_UOMVolume_ID());
		price.setIsActive(true);
		
		price.setPriceList(new BigDecimal(15.50));
		price.setPriceList_Vol(new BigDecimal(15.50));
		
		assertTrue(price.save(trx.getTrxName())); // si debe guardar el precio
		
		trx.rollback();
		
	}
}
