package com.ecaresoft.tests;

import java.util.Random;

import junit.framework.TestCase;

import org.compiere.model.MEXMECDS;
import org.compiere.model.MEXMECDSRules;
import org.compiere.util.Env;
import org.compiere.util.ValueName;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMECDSTest extends TestCase {
	
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Verificamos que se llene correctamente el combo con un tipo obtenido de la BD
	 */
	@Test
	public void testGetOrderBy() {
		//Obtenemos registro
		MEXMECDSRules rules = TestUtils.getRandomPO(MEXMECDSRules.class, "", false);
		ValueName value = MEXMECDS.getOrderBy(rules, Env.getCtx());
		assertNotNull(value);
		
		boolean execption = false;
		try{
			//Debe mandar excepcion al no mandar un valor
			MEXMECDS.getOrderBy(null, Env.getCtx());
		}catch (NullPointerException e) {
			execption = true;
		}
		assertTrue(execption);
	}
	
	/**
	 * Obtenemos los filtros de fechas de postgres 
	 */
	@Test
	public void testAddWhereOfAges(){
		Random rand = new Random();
		int ramdonNo = rand.nextInt(4);
		String value = MEXMECDS.addWhereOfAges(true, ramdonNo);
		
		if(ramdonNo == 0){
			assertTrue(value.isEmpty());
		}else{
			assertFalse(value.isEmpty());
		}
	}
}