/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams;
import org.compiere.model.MEXMEDocType;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author Lorena Lama
 */
public class MEXMEActPacienteIndHTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEActPacienteIndH#getIndHFact(java.util.Properties, boolean, int, java.lang.String, java.lang.String)}.
//	 */
//	public void testGetIndHFact() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#getPacSolPending(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetPacSolPending() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#getVisitsOfTreatment()}.
//	 */
//	public void testGetVisitsOfTreatment() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#getLastVisitOfTreatment()}.
//	 */
//	public void testGetLastVisitOfTreatment() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#getMedicationList()}.
//	 */
//	public void testGetMedicationList() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEActPacienteIndH#getLstPrescripciones(java.util.Properties, int, boolean, boolean, java.util.Date, java.lang.String)}
//	 * .
//	 */
//	public void testGetLstPrescripciones() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#isFactServicio(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testIsFactServicio() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#getTratamientosDetalle(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetTratamientosDetalle() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#getLVBPprescripciones(java.util.Properties, int)}.
//	 */
//	public void testGetLVBPprescripciones() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#getDetalleMed(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetDetalleMed() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEActPacienteIndH#get(java.util.Properties, int, java.util.Date, java.util.Date, java.lang.String)}.
//	 */
//	public void testGetPropertiesIntDateDateString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#getPacienteSolPending(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetPacienteSolPending() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#canMove(java.util.Date, java.util.Date)}.
//	 */
//	public void testCanMove() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#move(java.util.Date, java.util.Date)}.
//	 */
//	public void testMove() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#getStudiesRef(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetStudiesRef() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#get(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetPropertiesIntString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#get(java.util.Properties, int, int, int, java.lang.String)}.
//	 */
//	public void testGetPropertiesIntIntIntString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEActPacienteIndH#getList(java.util.Properties, java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	public void testGetListPropertiesStringStringString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEActPacienteIndH#getList(java.util.Properties, java.lang.String, java.lang.String, java.lang.String, java.util.List)}
//	 * .
//	 */
//	public void testGetListPropertiesStringStringStringListOfObject() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#get(java.util.Properties, java.lang.String, java.lang.String, java.lang.String)}
//	 * .
//	 */
//	public void testGetPropertiesStringStringString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEActPacienteIndH#getServicios(java.util.Properties, int, int, boolean, java.sql.Timestamp, java.sql.Timestamp, boolean, java.lang.String, java.lang.Object[])}
//	 * .
//	 */
//	public void testGetServiciosPropertiesIntIntBooleanTimestampTimestampBooleanStringObjectArray() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEActPacienteIndH#getSQLServicios(java.util.Properties, int, int, boolean, int, java.lang.String, boolean)}.
//	 */
//	public void testGetSQLServicios() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEActPacienteIndH#getServicios(java.util.Properties, long, long, java.lang.String, boolean, int, java.lang.String, java.lang.Object[])}
//	 * .
//	 */
//	public void testGetServiciosPropertiesLongLongStringBooleanIntStringObjectArray() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEActPacienteIndH#getCountServices(java.util.Properties, int, int, java.lang.String, java.lang.String, java.lang.Object[])}
//	 * .
//	 */
//	public void testGetCountServices() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEActPacienteIndH#getCount(java.util.Properties, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
//	 * .
//	 */
//	public void testGetCount() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#canDeliver(boolean)}.
//	 */
//	public void testCanDeliverBoolean() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#getServicios(int)}.
//	 */
//	public void testGetServiciosInt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEActPacienteIndH#getSQLServicesCtaPac(java.util.Properties, long, long, boolean, java.lang.String)}.
//	 */
//	public void testGetSQLServicesCtaPac() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#voidIt()}.
//	 */
//	public void testVoidIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#cancelDiag()}.
//	 */
//	public void testCancelDiag() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#approve()}.
//	 */
//	public void testApprove() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#completeIt()}.
//	 */
//	public void testCompleteIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#afterCompleteIt()}.
//	 */
//	public void testAfterCompleteIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#charges()}.
//	 */
//	public void testCharges() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#inventoryServices()}.
//	 */
//	public void testInventory() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MEXMEActPacienteIndH#setTotalsComplete(java.math.BigDecimal, java.math.BigDecimal, java.util.List, boolean)}.
//	 */
//	public void testSetTotalsComplete() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#getCtasSolPending(java.util.Properties, java.lang.String, java.lang.String)}.
//	 */
//	public void testGetCtasSolPending() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#scheduleEquipment(int)}.
//	 */
//	public void testScheduleEquipment() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEActPacienteIndH#discontinue(String, org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams)}.
	 */
	public void testDiscontinueStringDiscontinueOrder() {
		final MEXMECtaPac ctapac = TestUtils.getRandomPO(MEXMECtaPac.class);
		if (ctapac != null) {
			Trx trx = null;
			try {
				trx = Trx.get(Trx.createTrxName("testDiscontinue"), true);
				// dummy order. no detail
				final MEXMEActPacienteIndH dummyOrder = new MEXMEActPacienteIndH(Env.getCtx(), 0, trx.getTrxName());
				dummyOrder.setEXME_ActPaciente_ID(ctapac.getActividades().get(0).getEXME_ActPaciente_ID());
				dummyOrder.setEXME_CtaPac_ID(ctapac.getEXME_CtaPac_ID());
				dummyOrder.setEXME_Medico_ID(ctapac.getEXME_Medico_ID());
				dummyOrder.setC_BPartner_ID(ctapac.getC_BPartner_ID());
				int docType = MEXMEDocType.getOfName(Env.getCtx(), Constantes.SERVICIO, null);
				dummyOrder.setC_DocType_ID(docType);
				dummyOrder.setC_DocTypeTarget_ID(docType);
				dummyOrder.setC_Location_ID(ctapac.getPaciente().getC_Location_ID());
				dummyOrder.setDateOrdered(Env.getCurrentDate());
				if (!dummyOrder.save()) {
					throw new MedsysException();
				}
				// dummy parameters
				final DiscontinueParams dummyParams = new DiscontinueParams(dummyOrder, Env.getCurrentDate());
				dummyOrder.discontinue(trx.getTrxName(), dummyParams);
				assertEquals(MEXMEActPacienteIndH.DOCSTATUS_Voided, dummyOrder.getDocStatus());
				assertEquals(dummyParams.getDate(), dummyOrder.getDateCanceled());

			} catch (final Exception e) {
				Trx.rollback(trx, true);
				fail(e.toString());
			} finally {
				Trx.rollback(trx, true);
			}
		}
	}

}
