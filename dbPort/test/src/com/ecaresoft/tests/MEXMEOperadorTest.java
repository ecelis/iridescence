package com.ecaresoft.tests;


import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MCashBook;
import org.compiere.model.MEXMEOperador;
import org.compiere.model.X_EXME_Operador;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMEOperadorTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEOperador#get(java.util.Properties, int, String)}.
	 */
	@Test
	public void testGet() {
		MEXMEOperador list = TestUtils.getRandomPO(MEXMEOperador.class, null, true);
		assertFalse(list==null);
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEOperador#getCajasUsuario(java.util.Properties, int, String, String)}.
	 */
	@Test
	public void testGetCajasUsuario() {
		MCashBook[] list = MEXMEOperador.getCajasUsuario(Env.getCtx(), 1000129 , "Y", null);
		assertFalse(list.length==0);
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEOperador#getOperadorSelectCash(java.util.Properties, String)}.
	 */
	@Test
	public void testGetOperadorSelectCash() {
		List<MEXMEOperador> list = MEXMEOperador.getOperadorSelectCash(Env.getCtx(), X_EXME_Operador.TIPO_OPERADOR_Cashier);
		assertFalse(list.isEmpty());
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEOperador#getOperadorOpenCash(java.util.Properties, String)}.
	 */
	@Test
	public void testGetOperadorOpenCash() {
		List<MEXMEOperador> list = MEXMEOperador.getOperadorOpenCash(Env.getCtx(), X_EXME_Operador.TIPO_OPERADOR_Cashier);
		assertFalse(list.isEmpty());
	}
}
