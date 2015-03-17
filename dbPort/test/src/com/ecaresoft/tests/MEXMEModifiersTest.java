/**
 * 
 */
package com.ecaresoft.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MEXMEModifiers;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.api.Generic;
import com.ecaresoft.tests.utils.TestUtils;

/**
 * Clase de pruebas para {@link MEXMEModifiers}
 * @author vperez
 */
public class MEXMEModifiersTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link MEXMEModifiers#getLstModifiers()}
	 */
	@Test
	public void testGetModifiers() {
		MEXMEModifiers modif = TestUtils.getRandomPO(MEXMEModifiers.class, "ISACTIVE = 'Y' ", false);
		boolean val = false;
		for (Generic modifier : MEXMEModifiers.getLstModifiers()) {
			if (modifier.getValue().equals(String.valueOf(modif.getEXME_Modifiers_ID()))) {
				val = true;
				break;
			}
		}
		assertTrue(val);
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEModifiers#getLst()}.
	 */
	@Test
	public void testGetLst() {
		List<KeyNamePair> lstModifiers = new ArrayList<KeyNamePair>();
		lstModifiers = MEXMEModifiers.getLst();
		if(lstModifiers!=null && !lstModifiers.isEmpty()){
			for(KeyNamePair knp: lstModifiers){
				MEXMEModifiers mexme = new MEXMEModifiers(Env.getCtx(),knp.getKey(),null);
				assertTrue(mexme.isActive());
			}
		}
	}
}
