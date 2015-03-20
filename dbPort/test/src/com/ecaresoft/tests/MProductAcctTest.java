package com.ecaresoft.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.compiere.model.MProduct;
import org.compiere.model.MProductAcct;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MProductAcctTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MProductAcct#get(java.util.Properties, int, int, java.lang.String)}.
	 */
	@Test
	public void testGet() {
		
		MProduct product = TestUtils.getRandomPO(MProduct.class, null, true); //obtenemos un producto
		
		List<MProductAcct> lstAcct = MProductAcct.get(Env.getCtx(), product.getM_Product_ID(), Env.getC_AcctSchema_ID(Env.getCtx()), null);
		
		for(MProductAcct acct: lstAcct){
			assertTrue(acct.getM_Product_ID() == product.getM_Product_ID());
			assertTrue(acct.getC_AcctSchema_ID() == Env.getC_AcctSchema_ID(Env.getCtx()));
		}
	}

}
