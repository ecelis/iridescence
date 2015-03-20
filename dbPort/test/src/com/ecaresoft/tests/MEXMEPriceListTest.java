/**
 * 
 */
package com.ecaresoft.tests;


import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MEXMEPriceList;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author mvrodriguez
 *
 */
public class MEXMEPriceListTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();// 
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPriceList#getDefaultPL(java.util.Properties, boolean)}.
	 */
	@Test
	public final void testGetDefaultPL() {
		// Tambien es parte de la configuracion del sistema
		assertTrue(MEXMEPriceList.getDefaultPL(Env.getCtx(), true)!=null);
		assertTrue(MEXMEPriceList.getDefaultPL(Env.getCtx(), false)!=null);
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEPriceList#getList(java.util.Properties, boolean)}.
	 */
	@Test
	public void testGetList() {
		//se verifican las listas de precios de venta
		List<MEXMEPriceList> listVenta = MEXMEPriceList.getList(Env.getCtx(), true);
		if(listVenta!=null && !listVenta.isEmpty()){
			//se verifica que regrese registros activos
			for(MEXMEPriceList priceList: listVenta){
				assertTrue(priceList.isActive());
			}
			//se verifica que regrese listas de venta
			for(MEXMEPriceList priceList: listVenta){
				assertTrue(priceList.isSOPriceList());
			}
		}
		
		//se verifican las listas de precios de compra
		List<MEXMEPriceList> listCompra = MEXMEPriceList.getList(Env.getCtx(), false);
		if(listCompra!=null && !listCompra.isEmpty()){
			//se verifica que regrese registros activos
			for(MEXMEPriceList priceList: listCompra){
				assertTrue(priceList.isActive());
			}
			//se verifica que regrese listas de compra
			for(MEXMEPriceList priceList: listCompra){
				assertFalse(priceList.isSOPriceList());
			}
		}
	}
}