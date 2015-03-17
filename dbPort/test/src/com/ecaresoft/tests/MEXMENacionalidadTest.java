/**
 * 
 */
package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MEXMENacionalidad;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 * 
 */
public class MEXMENacionalidadTest extends TestCase {

	/*** @see junit.framework.TestCase#setUp() */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUp200("ecsmxProd14Jul");
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMENacionalidad#getAll(java.util.Properties, java.lang.String)}.
	 */
	@Test
	public void testGetAll() {
		final int total = new Query(Env.getCtx(), MEXMENacionalidad.Table_Name, "", null)//
			.setOnlyActiveRecords(true).addAccessLevelSQL(true).count();
		final List<KeyNamePair> list = MEXMENacionalidad.getAll(Env.getCtx(), null);
		assertNotNull(list);
		assertEquals(list.size(), total);
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMENacionalidad#getDefault(java.util.Properties, java.lang.String)}.
	 */
	@Test
	public void testGetDefault() {
		final MEXMENacionalidad nac = TestUtils.getRandomPO(MEXMENacionalidad.class, " isDefault='Y' ", true);
		if (nac == null) {
			assertTrue(MEXMENacionalidad.getDefault(Env.getCtx(), null) <= 0);
		} else {
			final int nacId = MEXMENacionalidad.getDefault(nac.getCtx(), null);
			assertEquals(nac.getEXME_Nacionalidad_ID(), nacId);
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMENacionalidad#getByCountry(java.util.Properties, int, java.lang.String)}.
	 */
	@Test
	public void testGetByCountry() {
		final MEXMENacionalidad nac = TestUtils.getRandomPO(MEXMENacionalidad.class, " C_Country_ID > 0 ", true);
		if (nac == null) {
			assertTrue(MEXMENacionalidad.getByCountry(Env.getCtx(), Env.getC_Country_ID(Env.getCtx()), null) <= 0);
		} else {
			final int nacId = MEXMENacionalidad.getByCountry(nac.getCtx(), nac.getC_Country_ID(), null);
			assertEquals(nac.getEXME_Nacionalidad_ID(), nacId);
		}
	}
	
}
