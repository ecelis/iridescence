/**
 * 
 */
package com.ecaresoft.tests;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.model.MEXMEFrequency1;
import org.compiere.model.MEXMEFrequency2;
import org.compiere.model.MEXMEFrequency3;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Test Class form {@link org.compiere.model.MEXMEFrequency2}
 * 
 * @author lama
 */
public class MEXMEFrequency2Test extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFrequency2#getFrequencies3()}.
	 */
	@Test
	public void testGetFrequencies3() {
		MEXMEFrequency2 poModel2 = new MEXMEFrequency2(Env.getCtx(), 0, null);
		assertTrue(poModel2.getFrequencies3().isEmpty());
		// TODO< cambiar al crear clase MEXMEFrequency3Test
		final MEXMEFrequency3 poModel = TestUtils.getRandomPO(MEXMEFrequency3.class);
		if (poModel != null) {
			poModel2 = new MEXMEFrequency2(Env.getCtx(), poModel.getEXME_Frequency2_ID(), null);
			assertFalse(poModel2.getFrequencies3().isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFrequency2#getAll(java.util.Properties, int)}.
	 */
	@Test
	public void testGetAllPropertiesInt() {
		assertTrue(MEXMEFrequency2.getAll(Env.getCtx(), 0).isEmpty());
		final MEXMEFrequency2 poModel = TestUtils.getRandomPO(MEXMEFrequency2.class);
		if (poModel != null) {
			assertFalse(MEXMEFrequency2.getAll(Env.getCtx(), poModel.getEXME_Frequency1_ID()).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFrequency2#getAll(java.util.Properties, int, java.lang.String, java.lang.Object[])}.
	 */
	@Test
	public void testGetAllPropertiesIntStringObjectArray() {
		List<Object> lst =new ArrayList<Object>(); 
		lst.add(0);
		assertTrue(MEXMEFrequency2.getAll(Env.getCtx(), 0, " EXME_Frequency2.Quantity>=? ",lst).isEmpty());
		final MEXMEFrequency2 poModel = TestUtils.getRandomPO(MEXMEFrequency2.class);
		if (poModel != null) {
			lst.clear();
			lst.add(poModel.getQuantity());
			assertFalse(MEXMEFrequency2.getAll(poModel.getCtx(), poModel.getEXME_Frequency1_ID(), " COALESCE(EXME_Frequency2.Quantity,0)>=? ", lst).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEFrequency2#getOnceFrequency(java.util.Properties, boolean, java.lang.String)}.
	 */
	@Test
	public void testGetOnceFrequency() {
		// el metodo puede regresar o no un nulo, es correcto cualquiera de las dos
		// se prueban las variantes de parametros, el metodo utiliza Query, y no tiene un try/catch,
		// por lo que de ocurrir una excepcion, el metodo arrojara un SQLException
		MEXMEFrequency2.getOnceFrequency(Env.getCtx(), true, MEXMEFrequency1.TYPE_Medication);
		MEXMEFrequency2.getOnceFrequency(Env.getCtx(), false, MEXMEFrequency1.TYPE_Medication);
		MEXMEFrequency2.getOnceFrequency(Env.getCtx(), true, MEXMEFrequency1.TYPE_Service);
		MEXMEFrequency2.getOnceFrequency(Env.getCtx(), false, MEXMEFrequency1.TYPE_Service);
		MEXMEFrequency2.getOnceFrequency(Env.getCtx(), true, MEXMEFrequency1.TYPE_Vitals);
		MEXMEFrequency2.getOnceFrequency(Env.getCtx(), false, MEXMEFrequency1.TYPE_Vitals);
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEFrequency2#createFrequencies3(java.util.Date)}
	 * @throws Exception
	 */
	public void testCreateFrequencies3() throws Exception {
		final MEXMEFrequency2 freq2 = TestUtils.getRandomPO(MEXMEFrequency2.class, " Quantity>1 AND C_UOM_ID>0 ", true);
		if(freq2 == null) {
			return;
		}
		Trx trx = null;
		try {
			// inicio 2:00am
			final Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 2);
			cal.set(Calendar.MINUTE, 0);
			//trx name
			trx = Trx.get(Trx.createTrxName("CPOE"), true);
			freq2.set_TrxName(trx.getTrxName());
			// run process
			final List<MEXMEFrequency3> lst = freq2.createFrequencies3(cal.getTime());
			Trx.rollback(trx, true);//rollback,close
			assertFalse(lst.isEmpty());// at least create one
		} catch (Exception e) {
			Trx.rollback(trx, true);//rollback,close
			CLogger.get().log(Level.SEVERE, "testCreateFrequencies3", e);
			assertTrue(false);//Force errror;
		} finally {
			Trx.rollback(trx, true);//rollback,close
		}
	}

}
