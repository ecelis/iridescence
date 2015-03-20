/**
 * 
 */
package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEDieta;
import org.compiere.model.MEXMEPrescDieta;
import org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 * 
 */
public class MEXMEPrescDietaTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEPrescDieta#get(java.util.Properties, int, java.lang.Boolean, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testGet() {
		final MEXMEPrescDieta model = MEXMEPrescDieta.get(Env.getCtx(), 0, true, 10, null);
		assertNull(model);
		final MEXMEPrescDieta prescDiet = TestUtils.getRandomPO(MEXMEPrescDieta.class);
		if (prescDiet != null) {
			assertNotNull(MEXMEPrescDieta.get(Env.getCtx(), prescDiet.getEXME_CtaPac_ID(), prescDiet.isVigente(),
					prescDiet.getEXME_EncounterForms_ID(), null));
		}
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEPrescDieta#getHistory(java.util.Properties, int, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetHistory() {
		final List<MEXMEPrescDieta> lst = MEXMEPrescDieta.getHistory(Env.getCtx(), 0, 10, null);
		assertNotNull(lst);
		assertTrue(lst.isEmpty());
		final MEXMEPrescDieta prescDiet = TestUtils.getRandomPO(MEXMEPrescDieta.class, " VIGENTE='N' ", true);
		if (prescDiet != null) {
			assertFalse(MEXMEPrescDieta.getHistory(Env.getCtx(), prescDiet.getEXME_CtaPac_ID(), prescDiet.getEXME_EncounterForms_ID(), null)
					.isEmpty());
		}
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEPrescDieta#getVigentes(java.util.Properties, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetVigentesPropertiesIntString() {
		final List<MEXMEPrescDieta> lst = MEXMEPrescDieta.getVigentes(Env.getCtx(), 0, null);
		assertNotNull(lst);
		assertTrue(lst.isEmpty());
		final MEXMEPrescDieta prescDiet = TestUtils.getRandomPO(MEXMEPrescDieta.class, " VIGENTE='Y' ", true);
		if (prescDiet != null) {
			assertFalse(MEXMEPrescDieta.getVigentes(Env.getCtx(), prescDiet.getEXME_CtaPac_ID(), null).isEmpty());
		}
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEPrescDieta#getVigentes(java.util.Properties, java.lang.String, java.lang.Object[], java.lang.String)}
	 * .
	 */
	@Test
	public void testGetVigentesPropertiesStringObjectArrayString() {
		final List<MEXMEPrescDieta> lst = MEXMEPrescDieta.getVigentes(Env.getCtx(), null, null, null);
		assertNotNull(lst);
		final MEXMEPrescDieta prescDiet = TestUtils.getRandomPO(MEXMEPrescDieta.class);
		if (prescDiet == null) {
			assertTrue(lst.isEmpty());
		} else {
			assertFalse(lst.isEmpty());
			assertFalse(MEXMEPrescDieta.getVigentes(Env.getCtx(), " EXME_PrescDieta_ID=? ", new Object[] {
				prescDiet.get_ID()
			}, null).isEmpty());
		}
	}
	/**
	 * Test method for {@link org.compiere.model.MEXMEPrescDieta#getNombreMed()}.
	 */
	public void testGetNombreMed() {        
		// vacio si es nuevo
		assertTrue(StringUtils.isEmpty(new MEXMEPrescDieta(Env.getCtx(), 0, null).getNombreMed()));
		// random
		final MEXMEPrescDieta obj = TestUtils.getRandomPO(MEXMEPrescDieta.class);
		if (obj != null) {
			if (obj.getEXME_Medico_ID() > 0) {
				assertTrue(StringUtils.isNotEmpty(obj.getNombreMed()));
			} else {
				assertTrue(StringUtils.isEmpty(obj.getNombreMed()));
			}
		}
	}
	/**
	 * Test method for {@link org.compiere.model.MEXMEPrescDieta#getIsAdatS()}.
	 */
	public void testGetIsAdatS() {  

		MEXMEPrescDieta line = TestUtils.getRandomPO(MEXMEPrescDieta.class);
		if (line != null) {
			String prn = "No";
			if (line.isADAT()) {
				prn = "Yes";

			}
			assertEquals(prn, line.getIsAdatS());
		}

	}
	/**
	 * Test method for {@link org.compiere.model.MEXMEPrescDieta#getNameDieta()}.
	 */
	public void testGetNameDieta() {  
		MEXMEPrescDieta line = TestUtils.getRandomPO(MEXMEPrescDieta.class);
		if (line != null) {
			assertNotNull(line.getNameDieta());
		}
	}
	
	
	/** Test method for {@link org.compiere.model.MEXMEPrescDieta#discontinue(String, org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams)}. */
	public void testDiscontinueStringDiscontinueOrder() {
		final MEXMEDieta dieta = TestUtils.getRandomPO(MEXMEDieta.class);
		final MEXMECtaPac ctapac = TestUtils.getRandomPO(MEXMECtaPac.class);
		if (dieta != null && ctapac != null) {
			Trx trx = null;
			try {
				trx = Trx.get(Trx.createTrxName("testDiscontinue"), true);
				final MEXMEPrescDieta dummyOrder = new MEXMEPrescDieta(Env.getCtx(), 0, trx.getTrxName());
				dummyOrder.setEXME_CtaPac_ID(ctapac.getEXME_CtaPac_ID());
				dummyOrder.setEXME_Medico_ID(ctapac.getEXME_Medico_ID());
				dummyOrder.setFechaInicio(Env.getCurrentDate());
				if (!dummyOrder.save()) {
					throw new MedsysException();
				}
				// sin parametro
				dummyOrder.discontinue(trx.getTrxName(), null);
				assertFalse(dummyOrder.isVigente());
				assertNotNull(dummyOrder.getDiscontinuedDate());
				
				dummyOrder.setVigente(true);
				
				final DiscontinueParams dummyParams = new DiscontinueParams(dummyOrder, Env.getCurrentDate());
				dummyOrder.discontinue(trx.getTrxName(), dummyParams);
				assertFalse(dummyOrder.isVigente());
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
