/**
 * 
 */
package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MEXMECtaPacPaq;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author mvrodriguez
 * 
 */
public class MCtaPacPaqTest extends TestCase {

	/* (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp() */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacPaq#get(java.util.Properties, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testGet() {
		// Lama: Corrección de ID en duro
		List<MEXMECtaPacPaq> list =
			MEXMECtaPacPaq.get(Env.getCtx(), " EXME_CtaPacPaq.EXME_CtaPac_ID=0 ", " EXME_CtaPacPaq.EXME_CtaPacPaq_ID DESC ", null);
		assertTrue(list.isEmpty());
		final MEXMECtaPacPaq paq = TestUtils.getRandomPO(MEXMECtaPacPaq.class);
		if (paq == null) {
			// Si no hay ninguno en la bd, la lista DEBE ser vacia sin condiciones
			list = MEXMECtaPacPaq.get(Env.getCtx(), null, null, null);
			assertTrue(list.isEmpty());
		} else {
			// si no, la lista no puede ser vacia para esa cuenta
			list =
				MEXMECtaPacPaq.get(Env.getCtx(), " EXME_CtaPacPaq.EXME_CtaPac_ID=? ", " EXME_CtaPacPaq.EXME_CtaPacPaq_ID DESC ", null,
					paq.getEXME_CtaPac_ID());
			assertFalse(list.isEmpty());
		}
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMECtaPacPaq#getFromCtaPac(java.util.Properties, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testGetFromCtaPac() {
		final MEXMECtaPacPaq paq = TestUtils.getRandomPO(MEXMECtaPacPaq.class);
		if (paq == null) {
			// Si no hay ninguno en la bd, la lista DEBE ser vacia sin condiciones
			assertTrue(MEXMECtaPacPaq.getFromCtaPac(Env.getCtx(), 0, null, null).isEmpty());
		} else {
			// si no, la lista no puede ser vacia para esa cuenta
			assertFalse(MEXMECtaPacPaq.getFromCtaPac(Env.getCtx(), paq.getEXME_CtaPac_ID(), null, null).isEmpty());
		}
	}

}
