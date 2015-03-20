/**
 * 
 */
package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEPrescRX;
import org.compiere.model.MEXMEPrescRXDet;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author aperez
 *
 */
public class MEXMEPrescRXDetTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}
	
	public void testSavedMeds() {
		MEXMEPrescRXDet det = TestUtils.getRandomPO(MEXMEPrescRXDet.class,
				" ISACTIVE = 'Y' AND EXME_PRESCRX_ID IN (SELECT RX.EXME_PRESCRX_ID FROM EXME_PRESCRX RX WHERE RX.TIPO ='" +MEXMEPrescRX.TIPO_DischargeMedication+"')",
				false);
		try {
			List<MEXMEPrescRXDet> lst = MEXMEPrescRXDet.getSavedMeds(Env.getCtx(), true, det.getEXME_PrescRX().getEXME_CtaPac().getEXME_Paciente_ID(), MEXMEPrescRX.TIPO_DischargeMedication, null);
			if (!lst.isEmpty()) {
				assertTrue(MEXMEPrescRX.TIPO_DischargeMedication.equals(lst.get(0).getEXME_PrescRX().getTipo()));
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** Test method for {@link org.compiere.model. MEXMEPrescRXDet#discontinue(String, org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams)}. */
	public void testDiscontinueStringDiscontinueOrder() {
		final MEXMECtaPac ctapac = TestUtils.getRandomPO(MEXMECtaPac.class);
		if (ctapac != null) {
			Trx trx = null;
			try {
				trx = Trx.get(Trx.createTrxName("testDiscontinue"), true);
				final MEXMEPrescRX header = new MEXMEPrescRX(Env.getCtx(), 0, trx.getTrxName());
				header.setEXME_CtaPac_ID(ctapac.getEXME_CtaPac_ID());
				header.setEXME_Medico_ID(ctapac.getEXME_Medico_ID());
				if (!header.save()) {
					throw new MedsysException();
				}

				final MEXMEPrescRXDet dummyOrder = header.newDetail();
				dummyOrder.setEXME_Medico_ID(ctapac.getEXME_Medico_ID());
				dummyOrder.setStartDate(Env.getCurrentDate());
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
	
	/**
	 * Prueba de listados de home medications
	 */
	public void testGetHomeMedByPac() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ");
		sql.append("  (pc.exme_paciente_id) ");
		sql.append("FROM ");
		sql.append("  exme_paciente pc ");
		sql.append("  INNER JOIN exme_ctapac cta ");
		sql.append("  ON pc.exme_paciente_id = cta.exme_paciente_id ");
		sql.append("  INNER JOIN EXME_PrescRX rx ");
		sql.append("  ON cta.exme_ctapac_id = rx.exme_ctapac_id ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), Constantes.SPACE, MEXMEPrescRX.Table_Name, "rx"));

		// Validamos que exista un paciente con home medications
		int id = DB.getSQLValue(null, sql.toString());

		if (id > 0) {
			// si existe ahora lo buscamos
			List<MEXMEPrescRX> list = MEXMEPrescRX.getHomeMedByPac(Env.getCtx(), id);
			// si está vacía la lista, hay error
			assertFalse(list.isEmpty());
		}

	}

}
