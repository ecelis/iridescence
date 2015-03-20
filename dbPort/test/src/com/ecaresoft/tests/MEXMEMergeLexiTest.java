package com.ecaresoft.tests;

import org.compiere.model.MEXMEMergeLexi;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

public class MEXMEMergeLexiTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link MEXMEMergeLexi#getOrderSets(java.util.Properties, boolean, String)}
	 */
	@Test
	public void testgetLstMerge() {
		assertTrue(MEXMEMergeLexi.getLstMerge(Env.getCtx(), true, null).size() > 0);
		
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link MEXMEMergeLexi#getExistMerge(java.util.Properties, int, int, String)}
	 */
	@Test
	public void testgetExistMerge() {
		assertTrue(MEXMEMergeLexi.getExistMerge(Env.getCtx(), 10091095, 1399759, null));
		
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link MEXMEMergeLexi#getMergeProcess(java.util.Properties, boolean)}
	 */
	@Test
	public void testgetMergeProcess() {
		MEXMEMergeLexi.getMergeProcess(Env.getCtx(), true);
		assertTrue(MEXMEMergeLexi.getLstMerge(Env.getCtx(), true, null).size() == 0);
		
	}
			
	/**
	 * Pruebas de funcionalidad para metodo {@link MEXMEMergeLexi#getUsrs(String)}
	 */
	@Test
	public void testgetUsrs() {
		assertTrue(MEXMEMergeLexi.getUsrs(null).size() > 0);
		
	}
}
