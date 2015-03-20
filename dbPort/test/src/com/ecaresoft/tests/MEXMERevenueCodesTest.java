package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MEXMERevenueCodes;
import org.compiere.model.MEXMETaxCategory;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MEXMERevenueCodesTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMERevenueCodes#getKeyNameLst(java.util.Properties, java.lang.String)}.
	 */
	@Test
	public void testGetKeyNameLst() {
		List<KeyNamePair> lista = new ArrayList<KeyNamePair>();
		lista = MEXMERevenueCodes.getKeyNameLst(Env.getCtx(), null);
		if(lista!=null && !lista.isEmpty()){
			for(KeyNamePair kn: lista){
				if(kn.getKey()!=0){
					MEXMERevenueCodes rev = new MEXMERevenueCodes(Env.getCtx(), kn.getKey(), null);
					assertTrue(rev.isActive());
				}
			}
		}
	}

}
