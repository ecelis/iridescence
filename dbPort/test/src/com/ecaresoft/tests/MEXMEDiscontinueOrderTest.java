/**
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEAdmitSource;
import org.compiere.model.MEXMEAdmitType;
import org.compiere.model.MEXMEDiscontinueOrder;
import org.compiere.model.MEXMEMedico;
import org.compiere.model.MEXMEMedicoOrg;
import org.compiere.model.PO;
import org.compiere.model.X_EXME_DiscontinueOrder;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author Lorena Lama
 */
public class MEXMEDiscontinueOrderTest extends TestCase {

	private MEXMEDiscontinueOrder createDummyOrder(final PO dummyRefer, final int doctorId, final Trx trx) {
		final MEXMEDiscontinueOrder dummyOrder = new MEXMEDiscontinueOrder(Env.getCtx(), 0, trx.getTrxName());
		dummyOrder.setAD_Table_ID(dummyRefer.get_Table_ID());
		dummyOrder.setRecord_ID(dummyRefer.get_ID());
		dummyOrder.setEXME_Medico_ID(doctorId);
		dummyOrder.setDiscontinuedDate(Env.getCurrentDate());
		if (!dummyOrder.save()) {
			throw new MedsysException();
		}
		return dummyOrder;
	}

	private MEXMEAdmitSource createDummyRefer(final Trx trx) {
		final MEXMEAdmitSource dummyRefer = new MEXMEAdmitSource(Env.getCtx(), 0, trx.getTrxName());
		dummyRefer.setName(trx.getTrxName());
		if (!dummyRefer.save()) {
			throw new MedsysException();
		}
		return dummyRefer;
	}

	/** @see junit.framework.TestCase#setUp() */
	@Override
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	private void setUserDoctorCtx() {
		final int doctorId;
		final int authenticatedBy;
		if (Env.getEXME_Medico_ID(Env.getCtx()) > 0) {
			authenticatedBy = Env.getAD_User_ID(Env.getCtx());
			doctorId = Env.getEXME_Medico_ID(Env.getCtx());
		} else {
			final MEXMEMedicoOrg doctorOrg = TestUtils.getRandomPO(MEXMEMedicoOrg.class);
			if (doctorOrg == null) {// no hay ningun usuario de tipo medico
				authenticatedBy = -1;
				doctorId = TestUtils.getRandomPO(MEXMEMedico.class).getEXME_Medico_ID();
			} else {
				authenticatedBy = doctorOrg.getAD_User_ID();
				doctorId = doctorOrg.getEXME_Medico_ID();
			}
		}
		// temporalmente
		Env.setContext(Env.getCtx(), "#EXME_Medico_ID", -1);
		Env.setContext(Env.getCtx(), "$doctorId", doctorId);
		Env.setContext(Env.getCtx(), "$authenticatedBy", authenticatedBy);
	}

	/** Test method for {@link org.compiere.model.MEXMEDiscontinueOrder#authenticate(org.compiere.model.PO, java.lang.String)}. */
	public void testAuthenticatePOString() {
		// physician is mandatory
		setUserDoctorCtx();
		final int doctorId = Env.getContextAsInt(Env.getCtx(), "$doctorId");
		final int authenticatedBy = Env.getContextAsInt(Env.getCtx(), "$authenticatedBy");
		if (doctorId > 0) {
			Trx trx = null;
			try {
				trx = Trx.get(Trx.createTrxName("testAuthenticateDisc"), true);

				// dummy reference
				final MEXMEAdmitSource dummyRefer = createDummyRefer(trx);

				// dummy order
				createDummyOrder(dummyRefer, doctorId, trx);
				// sin order type (no autenticado)
				MEXMEDiscontinueOrder dummyOrder = MEXMEDiscontinueOrder.authenticate(dummyRefer, trx.getTrxName());
				assertFalse(dummyOrder.isAuthenticated());

				// order type != wo (no autenticado)
				dummyOrder.setOrderType(X_EXME_DiscontinueOrder.ORDERTYPE_VerbalOrder);
				if (!dummyOrder.save()) {
					throw new MedsysException();
				}
				dummyOrder = MEXMEDiscontinueOrder.authenticate(dummyRefer, trx.getTrxName());
				assertFalse(dummyOrder.isAuthenticated());

				// order type == wo (auto autenticado)
				dummyOrder.setOrderType(X_EXME_DiscontinueOrder.ORDERTYPE_WrittenOrder);
				if (!dummyOrder.save()) {
					throw new MedsysException();
				}
				dummyOrder = MEXMEDiscontinueOrder.authenticate(dummyRefer, trx.getTrxName());
				assertTrue(dummyOrder.isAuthenticated());
				assertEquals(authenticatedBy, dummyOrder.getAuthenticatedBy());

				// user as physician (auto autenticado)
				Env.setContext(Env.getCtx(), "#EXME_Medico_ID", doctorId);
				dummyOrder.setOrderType(null);
				dummyOrder.setAuthenticated_Date(null);
				if (!dummyOrder.save()) {
					throw new MedsysException();
				}
				dummyOrder = MEXMEDiscontinueOrder.authenticate(dummyRefer, trx.getTrxName());
				assertTrue(dummyOrder.isAuthenticated());
				assertEquals(authenticatedBy, dummyOrder.getAuthenticatedBy());

			} catch (final Exception e) {
				Trx.rollback(trx, true);
				fail(e.toString());
			} finally {
				Trx.rollback(trx, true);
			}
		}
	}

	/** Test method for {@link org.compiere.model.MEXMEDiscontinueOrder#authenticate(java.lang.String)}. */
	public void testAuthenticateString() {
		// physician is mandatory
		setUserDoctorCtx();
		final int doctorId = Env.getContextAsInt(Env.getCtx(), "$doctorId");
		final int authenticatedBy = Env.getContextAsInt(Env.getCtx(), "$authenticatedBy");
		if (doctorId > 0) {
			Trx trx = null;
			try {
				trx = Trx.get(Trx.createTrxName("testAuthenticateDisc"), true);

				// dummy order & reference
				final MEXMEDiscontinueOrder dummyOrder = createDummyOrder(createDummyRefer(trx), doctorId, trx);

				// sin order type (no autenticado)
				dummyOrder.authenticate(trx.getTrxName());
				assertFalse(dummyOrder.isAuthenticated());

				// order type != wo (no autenticado)
				dummyOrder.setOrderType(X_EXME_DiscontinueOrder.ORDERTYPE_VerbalOrder);
				dummyOrder.authenticate(trx.getTrxName());
				assertFalse(dummyOrder.isAuthenticated());

				// order type == wo (auto autenticado)
				dummyOrder.setOrderType(X_EXME_DiscontinueOrder.ORDERTYPE_WrittenOrder);
				dummyOrder.authenticate(trx.getTrxName());
				assertTrue(dummyOrder.isAuthenticated());
				assertEquals(authenticatedBy, dummyOrder.getAuthenticatedBy());

				// user as physician (auto autenticado)
				Env.setContext(Env.getCtx(), "#EXME_Medico_ID", doctorId);

				dummyOrder.setOrderType(null);
				dummyOrder.setAuthenticated_Date(null);
				dummyOrder.authenticate(trx.getTrxName());
				assertTrue(dummyOrder.isAuthenticated());
				assertEquals(authenticatedBy, dummyOrder.getAuthenticatedBy());
			} catch (final Exception e) {
				Trx.rollback(trx, true);
				fail(e.toString());
			} finally {
				Trx.rollback(trx, true);
			}
		}
	}

	/** Test method for {@link org.compiere.model.MEXMEDiscontinueOrder#getFrom(org.compiere.model.PO)}. */
	public void testGetFromPO() {
		try {
			MEXMEDiscontinueOrder.getFrom(null);
			fail("Must throw a NullPointerException!!");
		} catch (final Exception e) {
			assertTrue(true);
		}
		final MEXMEDiscontinueOrder poModel = TestUtils.getRandomPO(MEXMEDiscontinueOrder.class);
		if (poModel != null) {
			final MEXMEDiscontinueOrder poFrom = MEXMEDiscontinueOrder.getFrom(poModel.getPoFrom());
			assertNotNull(poFrom);
			assertEquals(poModel.get_ID(), poFrom.get_ID());
		}
	}

	/** Test method for {@link org.compiere.model.MEXMEDiscontinueOrder#getFrom(java.util.Properties, int, int, java.lang.String)}. */
	public void testGetFromPropertiesIntIntString() {
		assertNull(MEXMEDiscontinueOrder.getFrom(Env.getCtx(), 0, 0, null));
		final MEXMEDiscontinueOrder poModel = TestUtils.getRandomPO(MEXMEDiscontinueOrder.class);
		if (poModel != null) {
			final MEXMEDiscontinueOrder poFrom =
				MEXMEDiscontinueOrder.getFrom(poModel.getCtx(), poModel.getAD_Table_ID(), poModel.getRecord_ID(), null);
			assertNotNull(poFrom);
			assertEquals(poModel.get_ID(), poFrom.get_ID());
		}
	}

	/** Test method for {@link org.compiere.model.MEXMEDiscontinueOrder#getPatientName()}. */
	public void testGetPatientName() {
		MEXMEDiscontinueOrder poModel = TestUtils.getRandomPO(MEXMEDiscontinueOrder.class);
		if (poModel == null) {
			poModel = new MEXMEDiscontinueOrder(Env.getCtx(), 0, null);
		}
		assertNotNull(poModel.getPatientName());
	}

	/** Test method for {@link org.compiere.model.MEXMEDiscontinueOrder#getPoFrom()}. */
	public void testGetPoFrom() {
		assertNull(new MEXMEDiscontinueOrder(Env.getCtx(), 0, null).getPoFrom());
		final MEXMEDiscontinueOrder poModel = TestUtils.getRandomPO(MEXMEDiscontinueOrder.class);
		if (poModel != null) {
			assertNotNull(poModel.getPoFrom());
			assertEquals(poModel.getAD_Table_ID(), poModel.getPoFrom().get_Table_ID());
			assertEquals(poModel.getRecord_ID(), poModel.getPoFrom().get_ID());
		}
	}

	/** Test method for {@link org.compiere.model.MEXMEDiscontinueOrder#getReadBackStr()}. */
	public void testGetReadBackStr() {
		assertTrue(StringUtils.isBlank(new MEXMEDiscontinueOrder(Env.getCtx(), 0, null).getReadBackStr()));
		final MEXMEDiscontinueOrder poModel = TestUtils.getRandomPO(MEXMEDiscontinueOrder.class);
		if (poModel != null) {
			final String readback = poModel.getReadBackStr();
			assertTrue(StringUtils.isNotBlank(readback));
			assertEquals(X_EXME_DiscontinueOrder.READBACK_Yes.equals(poModel.getReadBack()) ? "Yes" : "No", readback);
		}
	}

	/** Test method for {@link org.compiere.model.MEXMEDiscontinueOrder#getRecordType()}. */
	public void testGetRecordType() {
		MEXMEDiscontinueOrder poModel = TestUtils.getRandomPO(MEXMEDiscontinueOrder.class);
		if (poModel == null) {
			poModel = new MEXMEDiscontinueOrder(Env.getCtx(), 0, null);
		}
		assertNotNull(poModel.getRecordType());
	}

	/** Test method for {@link org.compiere.model.MEXMEDiscontinueOrder#getSummaryDetail()}. */
	public void testGetSummaryDetail() {
		MEXMEDiscontinueOrder poModel = TestUtils.getRandomPO(MEXMEDiscontinueOrder.class);
		if (poModel == null) {
			poModel = new MEXMEDiscontinueOrder(Env.getCtx(), 0, null);
		}
		final String summary = poModel.getSummaryDetail();
		assertNotNull(summary);
		assertTrue(poModel.is_new() ? summary.length() == 0 : summary.length() > 1);
	}

	/** Test method for {@link org.compiere.model.MEXMEDiscontinueOrder#isAuthenticatedDisc(org.compiere.model.PO)}. */
	public void testIsAuthenticatedDisc() {
		assertTrue(MEXMEDiscontinueOrder.isAuthenticatedDisc(new MEXMEAdmitType(Env.getCtx(), 0, null)));
		try {
			MEXMEDiscontinueOrder.isAuthenticatedDisc(null);
			fail("Must throw a NullPointerException!!");
		} catch (final Exception e) {
			assertTrue(true);
		}
		final MEXMEDiscontinueOrder poModel = TestUtils.getRandomPO(MEXMEDiscontinueOrder.class);
		if (poModel != null) {
			final PO poFrom = poModel.getPoFrom();
			if (poFrom != null) {
				assertEquals(poModel.getAuthenticated_Date() != null, MEXMEDiscontinueOrder.isAuthenticatedDisc(poFrom));
			}
		}
	}
}
