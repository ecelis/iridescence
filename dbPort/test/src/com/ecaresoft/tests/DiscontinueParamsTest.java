/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECuidadosPac;
import org.compiere.model.MEXMEDiscontinueOrder;
import org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * {@link MEXMEDiscontinueOrder.DiscontinueParams}
 * 
 * @author Lorena Lama
 */
public class DiscontinueParamsTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	@Override
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams#DiscontinueOrder(org.compiere.model.MEXMEDiscontinueOrder.IDiscontinueOrder, java.sql.Timestamp)}
	 * .
	 */
	public void testDiscontinueOrder() {
		try {
			new MEXMEDiscontinueOrder.DiscontinueParams(null, Env.getCurrentDate());
			fail("Must throw a NullPointerException!!");
		} catch (final Exception e) {
			assertTrue(true);
		}
		try {
			new MEXMEDiscontinueOrder.DiscontinueParams(new MEXMECuidadosPac(Env.getCtx(), 0, null), null);
			fail("Must throw a NullPointerException!!");
		} catch (final Exception e) {
			assertTrue(true);
		}
	}

	/** Test method for {@link org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams#getFrom()}. */
	public void testGetFrom() {
		final MEXMECuidadosPac order = TestUtils.getRandomPO(MEXMECuidadosPac.class);
		if (order != null) {
			// dummy parameters
			final DiscontinueParams dummyParams = new DiscontinueParams(order, Env.getCurrentDate());
			dummyParams.setMedicoId(order.getEXME_Medico_ID());
			// dummy order
			final MEXMEDiscontinueOrder discOrder = dummyParams.getFrom();
			assertEquals(dummyParams.getDate(), discOrder.getDiscontinuedDate());
			assertEquals(dummyParams.getMedicoId(), discOrder.getEXME_Medico_ID());
			assertEquals(order.get_Table_ID(), discOrder.getAD_Table_ID());
			assertEquals(order.get_ID(), discOrder.getRecord_ID());
		}
	}

	/** Test method for {@link org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams#startProcess(java.lang.String)}. */
	public void testStartProcess() {
		// encounter mandatory
		final MEXMECtaPac ctapac = TestUtils.getRandomPO(MEXMECtaPac.class);
		if (ctapac != null) {
			Trx trx = null;
			try {
				trx = Trx.get(Trx.createTrxName("testAuthenticateDisc"), true);

				// dummy order & reference
				final MEXMECuidadosPac dummyOrder = new MEXMECuidadosPac(Env.getCtx(), 0, trx.getTrxName());
				dummyOrder.setEXME_CtaPac_ID(ctapac.getEXME_CtaPac_ID());
				dummyOrder.setEXME_Medico_ID(ctapac.getEXME_Medico_ID());
				dummyOrder.setDescription(trx.getTrxName());
				if (!dummyOrder.save()) {
					throw new MedsysException();
				}
				// dummy parameters
				final DiscontinueParams dummyParams = new DiscontinueParams(dummyOrder, dummyOrder.getCreated());
				dummyParams.setMedicoId(dummyOrder.getEXME_Medico_ID());
				dummyParams.startProcess(trx.getTrxName());

				final MEXMEDiscontinueOrder discOrder = dummyParams.getFrom();
				assertEquals(dummyOrder.getCreated(), discOrder.getDiscontinuedDate());
				assertEquals(dummyOrder.getEXME_Medico_ID(), discOrder.getEXME_Medico_ID());
				assertEquals(dummyOrder.get_Table_ID(), discOrder.getAD_Table_ID());
				assertEquals(dummyOrder.get_ID(), discOrder.getRecord_ID());

				assertFalse(dummyOrder.isActive());
				assertNotNull(dummyOrder.getDiscontinuedDate());

			} catch (final Exception e) {
				Trx.rollback(trx, true);
				fail(e.toString());
			} finally {
				Trx.rollback(trx, true);
			}
		}
	}
}
