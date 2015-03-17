package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MEXMETaxCategory;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMETaxCategoryTest {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETaxCategory#getList(java.util.Properties, boolean, java.lang.String)}.
	 */
	@Test
	public void testGetList() {
		List<KeyNamePair> lista = new ArrayList<KeyNamePair>();
		
		//probamos una lista sin registro en blanco
		lista = MEXMETaxCategory.getList(Env.getCtx(), false, null);
		assertNotNull(lista);
		for(KeyNamePair kn: lista){
			assertTrue(kn.getKey()!=0);
			MEXMETaxCategory tax = new MEXMETaxCategory(Env.getCtx(), kn.getKey(), null);
			assertTrue(tax.isActive());
		}
		
		//probamos una lista con registro en blanco
		lista = MEXMETaxCategory.getList(Env.getCtx(), true, null);
		assertNotNull(lista);
		KeyNamePair kn0 = (KeyNamePair) lista.get(0);
		assertTrue(kn0.getKey()==0);
		for(KeyNamePair kn: lista){
			if(kn.getKey()!=0){
				MEXMETaxCategory tax = new MEXMETaxCategory(Env.getCtx(), kn.getKey(), null);
				assertTrue(tax.isActive());
			}
		}
	}

}
