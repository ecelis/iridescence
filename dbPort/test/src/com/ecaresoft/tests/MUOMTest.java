package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.compiere.model.MProduct;
import org.compiere.model.MUOM;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MUOMTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	/**
	 * Test method for {@link org.compiere.model.MUOM#getUOMbyProd(java.util.Properties, org.compiere.model.MProduct, java.lang.String)}.
	 */
	@Test
	public void testGetUOMbyProd() {
		MProduct product = TestUtils.getRandomPO(MProduct.class, null, true); //obtenemos un producto aleatoriamente
		List<ValueNamePair> lstUDM = MUOM.getUOMbyProd(Env.getCtx(), product, Env.getAD_Language(Env.getCtx())); //obtenemos sus udm
		assertNotNull(lstUDM); //verificamos que la lista no se encuentre vacia.
		for(ValueNamePair vnp: lstUDM){
			int udm = Integer.parseInt(vnp.getValue());
			assertTrue(udm == product.getC_UOM_ID() || udm == product.getC_UOMVolume_ID()); //verificamos que la udm pertenezca al producto

		}
	}

}
