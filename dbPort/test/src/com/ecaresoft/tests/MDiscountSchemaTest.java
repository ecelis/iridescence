package com.ecaresoft.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.compiere.model.MDiscountSchema;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MDiscountSchemaTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MDiscountSchema#getList(java.util.Properties, java.lang.String)}.
	 */
	@Test
	public void testGetList() {
		List<MDiscountSchema> list = MDiscountSchema.getList(Env.getCtx(), null);
		if(list!=null && !list.isEmpty()){
			for(MDiscountSchema schema:list){
				//se verifica que regrese registros activos
				assertTrue(schema.isActive());
				//se verifica que el registro pertenezca al cliente y a la organizacion
				assertEquals(schema.getAD_Client_ID(), Env.getAD_Client_ID(Env.getCtx()));
				assertEquals(schema.getAD_Org_ID(), Env.getAD_Org_ID(Env.getCtx()));
			}
		}
	}

}
