/**
 * 
 */
package com.ecaresoft.tests;


import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MEXMEPriceListVersion;
import org.compiere.model.MPriceList;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author mvrodriguez
 *
 */
public class MEXMEPriceListVersionTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPriceListVersion#getLastPriceListCreated(java.util.Properties, int, boolean)}.
	 */
	@Test
	public final void testGetLastPriceListCreated() {
		int priceListID = 1100030;// lista de precios de venta
		assertTrue(MEXMEPriceListVersion.getLastPriceListCreated(Env.getCtx(), priceListID, true)!=null);
		priceListID = 1100031;// lista de precios de compra
		assertTrue(MEXMEPriceListVersion.getLastPriceListCreated(Env.getCtx(), priceListID, false)!=null);
		
		MPriceList priceList = TestUtils.getRandomPO(MPriceList.class, null, true); //obtenemos una lista de precios
		List<MEXMEPriceListVersion> lstVersion = MEXMEPriceListVersion.getLstVersion(Env.getCtx(), priceList.getM_PriceList_ID(), null);
		if(lstVersion!=null && !lstVersion.isEmpty()){
			for(MEXMEPriceListVersion version: lstVersion){
				//verificamos que la version se encuentre activa
				assertTrue(version.isActive());
				//verificamos que la version corresponda a la lista de precios
				assertTrue(version.getM_PriceList_ID() == priceList.getM_PriceList_ID());
			}
		}
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEPriceListVersion#getLstVersion(java.util.Properties, int, java.lang.String)}.
	 */
	@Test
	public void testGetLstVersion() {
		MPriceList priceList = TestUtils.getRandomPO(MPriceList.class, null, true); //obtenemos una lista de precios
		List<MEXMEPriceListVersion> lstVersion = MEXMEPriceListVersion.getLstVersion(Env.getCtx(), priceList.getM_PriceList_ID(), null);
		if(lstVersion!=null && !lstVersion.isEmpty()){
			for(MEXMEPriceListVersion version: lstVersion){
				//verificamos que la version se encuentre activa
				assertTrue(version.isActive());
				//verificamos que la version corresponda a la lista de precios
				assertTrue(version.getM_PriceList_ID() == priceList.getM_PriceList_ID());
			}
		}
	}

}
