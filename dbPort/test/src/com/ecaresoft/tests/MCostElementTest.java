package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MTable;
import org.compiere.model.POInfo;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MCostElementTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MCostElement#getCostElementCbo(java.util.Properties, java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	public void testGetCostElementCbo() {
		List<ValueNamePair> elements = new ArrayList<ValueNamePair>();
		//registros activos y solo registros del cliente
		elements = MCostElement.getCostElementCbo(Env.getCtx(), Constantes.REG_ACTIVE, 
				"(CostingMethod IS NULL OR CostingMethod='S') AND isCalculated = 'N'", true);
		if(elements!=null && !elements.isEmpty()){
			for(ValueNamePair vnp: elements){
				MCostElement cost = new MCostElement(Env.getCtx(), vnp.getIntValue(), null);
				assertTrue(cost.isActive());
				assertTrue(cost.getAD_Client_ID() == Env.getAD_Client_ID(Env.getCtx()));
			}
		}

	}

}
