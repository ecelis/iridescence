package com.ecaresoft.tests;

import java.util.Properties;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.model.MEXMEProduct;
import org.compiere.model.MProduct;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMEProductTest extends TestCase {
	
	Properties ctx = Env.getCtx();
	CLogger slog = CLogger.getCLogger(MEXMEProductTest.class);

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	@Test
	public void testGetProductoValueID() {
		MProduct product = TestUtils.getRandomPO(MProduct.class);
		try {
			
			MProduct productvalue =  MEXMEProduct.getProductoValueID(ctx, product.getValue() , null);
			assertEquals(product, productvalue);
			
			productvalue =  MEXMEProduct.getProductoValueID(ctx, "" , null);
			assertNotSame(product, productvalue);
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage());
		}
	}

}
