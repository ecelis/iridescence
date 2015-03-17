package com.ecaresoft.tests;

import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

import org.compiere.model.MProduct;
import org.compiere.model.MSubstitute;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MSubstituteTest extends TestCase {
	
	Properties ctx = Env.getCtx();

	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	@Test
	public void testGetSubstitutes() {
		MProduct product = TestUtils.getRandomPO(MProduct.class);
		List<MSubstitute> subsLst = MSubstitute.getSubstitutes(ctx, product.getM_Product_ID(), null);
		for(MSubstitute substitute: subsLst){
			//se verifica que el registro pertenezca al cliente y a la organizacion
			assertEquals(substitute.getAD_Client_ID(), Env.getAD_Client_ID(Env.getCtx()));
			assertEquals(substitute.getAD_Org_ID(), Env.getAD_Org_ID(Env.getCtx()));
		}
	}
}
