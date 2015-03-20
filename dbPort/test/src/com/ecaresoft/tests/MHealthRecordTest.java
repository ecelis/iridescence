/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MHealthRecord;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Test Class form {@link org.compiere.model.MHealthRecord}
 * 
 * @author lama
 */
public class MHealthRecordTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MHealthRecord#getExams()}.
	 */
	@Test
	public void testGetExams() {
		// random patient
		final MEXMECtaPac ctapac = TestUtils.getRandomPO(MEXMECtaPac.class);
		final MHealthRecord rec = new MHealthRecord(Env.getCtx(), ctapac.getEXME_Paciente_ID());
		assertNotNull(rec.getExams());
		rec.setIcCtaPacId(ctapac.getEXME_CtaPac_ID());
		assertNotNull(rec.getExams());
		rec.setEXME_Medico_ID(ctapac.getEXME_Medico_ID());
		assertNotNull(rec.getExams());
	}

	/**
	 * Test method for {@link org.compiere.model.MHealthRecord#getDraftExams()}.
	 */
	@Test
	public void testGetDraftExams() {
		final MEXMECtaPac ctapac = TestUtils.getRandomPO(MEXMECtaPac.class);
		final MHealthRecord rec = new MHealthRecord(Env.getCtx(), ctapac.getEXME_Paciente_ID());
		assertNotNull(rec.getDraftExams());
		rec.setIcCtaPacId(ctapac.getEXME_CtaPac_ID());
		assertNotNull(rec.getDraftExams());
		rec.setEXME_Medico_ID(ctapac.getEXME_Medico_ID());
		assertNotNull(rec.getDraftExams());
	}
	/**
	 * Test method for {@link org.compiere.model.MHealthRecord#getCancelledExams()}.
	 */
	@Test
	public void testGetCancelledExams() {
		// random patient
		final MEXMECtaPac ctapac = TestUtils.getRandomPO(MEXMECtaPac.class);
		final MHealthRecord rec = new MHealthRecord(Env.getCtx(), ctapac.getEXME_Paciente_ID());
		assertNotNull(rec.getCancelledExams());
		rec.setIcCtaPacId(ctapac.getEXME_CtaPac_ID());
		assertNotNull(rec.getCancelledExams());
		rec.setEXME_Medico_ID(ctapac.getEXME_Medico_ID());
		assertNotNull(rec.getCancelledExams());
	}


	/**
	 * Test method for {@link org.compiere.model.MHealthRecord#getExams(boolean)}.
	 */
	@Test
	public void testGetExamsBoolean() {
		final MEXMECtaPac ctapac = TestUtils.getRandomPO(MEXMECtaPac.class);
		final MHealthRecord rec = new MHealthRecord(Env.getCtx(), ctapac.getEXME_Paciente_ID());
		assertNotNull(rec.getExams(null));
		rec.setIcCtaPacId(ctapac.getEXME_CtaPac_ID());
		assertNotNull(rec.getExams(null));
		rec.setEXME_Medico_ID(ctapac.getEXME_Medico_ID());
		assertNotNull(rec.getExams(null));
	}

	/**
	 * Test method for {@link org.compiere.model.MHealthRecord#getTreatments()}.
	 */
	@Test
	public void testGetTreatments() {
		final MEXMECtaPac ctapac = TestUtils.getRandomPO(MEXMECtaPac.class);
		final MHealthRecord rec = new MHealthRecord(Env.getCtx(), ctapac.getEXME_Paciente_ID());
		assertNotNull(rec.getTreatments());
		rec.setIcCtaPacId(ctapac.getEXME_CtaPac_ID());
		assertNotNull(rec.getTreatments());
		rec.setEXME_Medico_ID(ctapac.getEXME_Medico_ID());
		assertNotNull(rec.getTreatments());
	}

	/**
	 * Test method for {@link org.compiere.model.MHealthRecord#getLifeStyle()}.
	 */
	@Test
	public void testGetLifeStyle() {
		new MEXMEEstiloVidaPacienteTest().testGetHistoriaActive();
	}

	/**
	 * Test method for {@link org.compiere.model.MHealthRecord#getlstConsentimiento()}.
	 */
	@Test
	public void testGetlstConsentimiento() {
		new MEXMEConsentimientoTest().testGetHistoria();
	}

	/**
	 * Test method for {@link org.compiere.model.MHealthRecord#getVaccines()}.
	 */
	@Test
	public void testGetVaccines() throws Exception {
		new MEXMEHistVacunaTest().testGetVaccines();
	}

}
