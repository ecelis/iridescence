package com.ecaresoft.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MCostType;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MCostTypeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MCostType#getCostTypes(java.util.Properties, boolean)}.
	 */
	@Test
	public void testGetCostTypes() {
		List<ValueNamePair> costTypeList = new ArrayList<ValueNamePair>();
		//del cliente
		costTypeList = MCostType.getCostTypes(Env.getCtx(), true);
		if(costTypeList!=null && !costTypeList.isEmpty()){
			for(ValueNamePair vnp: costTypeList){
				int id = Integer.parseInt(vnp.getValue());
				MCostType type = new MCostType(Env.getCtx(), id, null);
				assertTrue(type.isActive());
				assertTrue(Env.getAD_Client_ID(Env.getCtx())== type.getAD_Client_ID());
				assertTrue(0 == type.getAD_Org_ID());
			}
		}
	}
}