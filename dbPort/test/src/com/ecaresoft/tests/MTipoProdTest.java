package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MTipoProd;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MTipoProdTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MTipoProd#getList(java.util.Properties, java.lang.String)}.
	 */
	@Test
	public void testGetList() {
		List<KeyNamePair> lista = new ArrayList<KeyNamePair>();
		lista = MTipoProd.getList(Env.getCtx(), null);
		if(lista!=null && !lista.isEmpty()){
			for(KeyNamePair kn: lista){
				MTipoProd obj = new MTipoProd(Env.getCtx(), kn.getKey(), null);
				assertTrue(obj.isActive()); //verificamos que sean registros activos.
			}
		}
	}

}
