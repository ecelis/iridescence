/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECuidadosPac;
import org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Test Class form {@link org.compiere.model.MEXMECuidadosPac}
 * 
 * @author lama
 * 
 */
public class MEXMECuidadosPacTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMECuidadosPac#get(java.util.Properties, int, java.lang.Boolean, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetPropertiesIntBooleanIntString() {
		assertTrue(MEXMECuidadosPac.get(Env.getCtx(), 0, true, 10, null).isEmpty());

		final MEXMECuidadosPac cuidadoPac = TestUtils.getRandomPO(MEXMECuidadosPac.class);
		if (cuidadoPac != null) {
			// si no existen registros el metodo debe regresar vacio
			assertFalse(MEXMECuidadosPac.get(Env.getCtx(), cuidadoPac.getEXME_CtaPac_ID(), cuidadoPac.isActive(),
					cuidadoPac.getEXME_EncounterForms_ID(), null).isEmpty());
		}
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMECuidadosPac#getUserPINDate(int, java.sql.Timestamp)} .
	 */
	public void testGetUserPINDate() throws Exception {
		assertTrue(StringUtils.isNotBlank(MEXMECuidadosPac.getUserPINDate(Env.getAD_User_ID(Env.getCtx()), Env.getCurrentDate())));
	}
	/**
	 * Test method for {@link org.compiere.model.MEXMECuidadosPac#getNombreMed()}.
	 */
	public void testGetNombreMed() {        
		// vacio si es nuevo
		assertTrue(StringUtils.isEmpty(new MEXMECuidadosPac(Env.getCtx(), 0, null).getNombreMed()));
		// random
		final MEXMECuidadosPac obj = TestUtils.getRandomPO(MEXMECuidadosPac.class);
		if (obj != null) {
			if (obj.getEXME_Medico_ID() > 0) {
				assertTrue(StringUtils.isNotEmpty(obj.getNombreMed()));
			} else {
				assertTrue(StringUtils.isEmpty(obj.getNombreMed()));
			}
		}
	}
	
	
	/** Test method for {@link org.compiere.model.MEXMECuidadosPac#discontinue(String, org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams)}. */
	public void testDiscontinueStringDiscontinueOrder() {
		final MEXMECtaPac ctapac = TestUtils.getRandomPO(MEXMECtaPac.class);
		if (ctapac != null) {
			Trx trx = null;
			try {
				trx = Trx.get(Trx.createTrxName("testDiscontinue"), true);
				final MEXMECuidadosPac dummyOrder = new MEXMECuidadosPac(Env.getCtx(), 0, trx.getTrxName());
				dummyOrder.setEXME_CtaPac_ID(ctapac.getEXME_CtaPac_ID());
				dummyOrder.setEXME_Medico_ID(ctapac.getEXME_Medico_ID());
				dummyOrder.setDescription(trx.getTrxName());
				if (!dummyOrder.save()) {
					throw new MedsysException();
				}
				// sin parametro
				dummyOrder.discontinue(trx.getTrxName(), null);
				assertFalse(dummyOrder.isActive());
				assertNotNull(dummyOrder.getDiscontinuedDate());
				
				dummyOrder.setIsActive(true);
				dummyOrder.setDiscontinuedDate(null);
				
				final DiscontinueParams dummyParams = new DiscontinueParams(dummyOrder, Env.getCurrentDate());
				dummyOrder.discontinue(trx.getTrxName(), dummyParams);
				assertFalse(dummyOrder.isActive());
				assertEquals(dummyParams.getDate(), dummyOrder.getDiscontinuedDate());
				
			} catch (final Exception e) {
				Trx.rollback(trx, true);
				fail(e.toString());
			} finally {
				Trx.rollback(trx, true);
			}
		}
	}
}
