package com.ecaresoft.tests;

import static org.junit.Assert.*;

import org.compiere.model.MAcctSchema;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MAcctSchemaTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MAcctSchema#getClientAcctSchema(java.util.Properties, int)}.
	 */
	@Test
	public void testGetClientAcctSchemaPropertiesInt() {
		int schemaId = MAcctSchema.getClientSchema(Env.getAD_Client_ID(Env.getCtx()), null);
		MAcctSchema schema = new MAcctSchema(Env.getCtx(), schemaId, null);
		assertNotNull(schema);
		assertTrue(schema.isActive());
		assertTrue(schema.getAD_Client_ID() == Env.getAD_Client_ID(Env.getCtx()));
	}	
}
