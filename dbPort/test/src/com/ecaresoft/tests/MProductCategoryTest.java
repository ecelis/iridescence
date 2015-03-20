package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MProductCategory;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MProductCategoryTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MProductCategory#getLstCategories(java.util.Properties, java.lang.String)}.
	 */
	@Test
	public void testGetLstCategories() {
		List<MProductCategory> lista = new ArrayList<MProductCategory>();
		lista = MProductCategory.getLstCategories(Env.getCtx(), null);
		assertNotNull(lista);
		for(MProductCategory cat: lista){
			assertTrue(cat.isActive());
		}
	}

}
