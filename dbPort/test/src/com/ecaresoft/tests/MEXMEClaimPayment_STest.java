package com.ecaresoft.tests;

import org.compiere.model.MEXMEClaimPayment_S;
import org.compiere.util.Env;
import org.junit.Before;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * @author lherrera
 * 
 */
public class MEXMEClaimPayment_STest extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	// Valida que el metodo no llegue a una excepcion
	public void testgetAll() {
		assertNotNull(MEXMEClaimPayment_S.getAll(Env.getCtx(), null));
	}

}
