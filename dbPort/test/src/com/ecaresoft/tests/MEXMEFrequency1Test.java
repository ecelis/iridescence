/**
 * 
 */
package com.ecaresoft.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEFrequency1;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 * 
 */
public class MEXMEFrequency1Test extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFrequency1#getAll(java.util.Properties)}.
	 */
	@Test
	public void testGetAllProperties() {
		List<KeyNamePair> lst = MEXMEFrequency1.getAll(Env.getCtx());
		final MEXMEFrequency1 poModel = TestUtils.getRandomPO(MEXMEFrequency1.class);
		if (poModel == null) {
			assertTrue(lst.isEmpty());
		} else {
			assertFalse(lst.isEmpty());
		}
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEFrequency1#getByType2(java.util.Properties, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetByType2() {
		// el metodo utiliza Query, y no tiene un try/catch,
		// por lo que de ocurrir una excepcion, el metodo arrojara un SQLException
		assertTrue(MEXMEFrequency1.getByType2(Env.getCtx(), MEXMEFrequency1.TYPE_Medication) != null);
		assertTrue(MEXMEFrequency1.getByType2(Env.getCtx(), MEXMEFrequency1.TYPE_Service) != null);
		assertTrue(MEXMEFrequency1.getByType2(Env.getCtx(), MEXMEFrequency1.TYPE_Vitals) != null);
		final MEXMEFrequency1 poModel = TestUtils.getRandomPO(MEXMEFrequency1.class, " Type IS NOT NULL ", true);
		if (poModel != null) {
			assertFalse(MEXMEFrequency1.getByType2(poModel.getCtx(), poModel.getType()).isEmpty());
		}
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEFrequency1#getAll(java.util.Properties, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public void testGetAllPropertiesStringObjectArray() {
		// forzamos que sea empty
		assertTrue(MEXMEFrequency1.getAll(Env.getCtx(), " AND EXME_Frequency1_ID=? ", 0).isEmpty());
		final MEXMEFrequency1 poModel = TestUtils.getRandomPO(MEXMEFrequency1.class, " Type IS NOT NULL ", true);
		if (poModel != null) {
			assertFalse(MEXMEFrequency1.getAll(Env.getCtx(), " AND Type=? ", poModel.getType()).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFrequency1#getFreq2()}.
	 */
	@Test
	public void testGetFreq2() {
		final MEXMEFrequency1 poModel = TestUtils.getRandomPO(MEXMEFrequency1.class);
		if (poModel != null) {
			// el metodo utiliza Query, y no tiene un try/catch,
			// por lo que de ocurrir una excepcion, el metodo arrojara un SQLException
			assertTrue(poModel.getFreq2() != null);
		}
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEFrequency1#getAllFreq1(java.util.Properties, java.lang.String, java.util.List)}
	 * .
	 */
	@Test
	public void testGetAllFreq1() {
		// forzamos que sea empty
		final List<Object> params = new ArrayList<Object>();
		params.add(0);
		assertTrue(MEXMEFrequency1.getAllFreq1(Env.getCtx(), " EXME_Frequency1_ID=? ", params).isEmpty());
		final MEXMEFrequency1 poModel = TestUtils.getRandomPO(MEXMEFrequency1.class, " Type IS NOT NULL ", true);
		if (poModel != null) {
			params.clear();
			params.add(poModel.getType());
			assertFalse(MEXMEFrequency1.getAllFreq1(Env.getCtx(), " Type=? ", params).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFrequency1#getTypeStr()}.
	 */
	@Test
	public void testGetTypeStr() {
		// Debe ser blanco
		assertTrue(StringUtils.isBlank(new MEXMEFrequency1(Env.getCtx(), 0, null).getTypeStr()));
		final MEXMEFrequency1 poModel = TestUtils.getRandomPO(MEXMEFrequency1.class);
		if (poModel != null) {
			assertTrue(StringUtils.isNotBlank(poModel.getTypeStr()));
		}
	}

}
