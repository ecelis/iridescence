package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEHistVacuna;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMEHistVacunaTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpDemoMX();
	}
	
	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEHistVacunaTest#getVaccineValue()}.
	 */
	@Test
	public void testGetVaccineValue() {
		final MEXMEHistVacuna histVac = TestUtils.getRandomPO(MEXMEHistVacuna.class, null, true);
		if (histVac != null) {
			// False si es nulo o vacio
			System.out.print(histVac.getVaccineValue());
			assertTrue(histVac.getVaccineValue() != null && StringUtils.isNotBlank(histVac.getVaccineValue()));
		}
	}
	
	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEHistVacunaTest#getTypeCvx()}.
	 */
	@Test
	public void testGetTypeCvx() {
		final MEXMEHistVacuna histVac = TestUtils.getRandomPO(MEXMEHistVacuna.class, null, true);
		if (histVac != null) {
			// False si es nulo o vacio
			assertTrue(histVac.getTypeCvx() != null && StringUtils.isNotBlank(histVac.getTypeCvx()));
		}
	}
	
	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEHistVacunaTest#getVaccineValue()}.
	 */
	@Test
	public void testGetLabeler() {
		final MEXMEHistVacuna histVac = TestUtils.getRandomPO(MEXMEHistVacuna.class, null, true);
		if (histVac != null) {
			// False si es nulo o vacio
			assertTrue(histVac.getLabeler() != null && StringUtils.isNotBlank(histVac.getLabeler()));
		}
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEHistVacuna#getVaccines(java.util.Properties, int, int, String)}.
	 */
	@Test
	public void testGetVaccines() throws Exception {
		// consentimiento paciente
		final MEXMEHistVacuna poModel1 = new Query(Env.getCtx(), MEXMEHistVacuna.Table_Name, " EXME_Paciente_ID IS NOT NULL ", null)//
				.addAccessLevelSQL(true).setOnlyActiveRecords(true).first();
		final int patientId = poModel1 == null ? 0 : poModel1.getEXME_Paciente_ID();
		final List<MEXMEHistVacuna> list = MEXMEHistVacuna.getVaccines(Env.getCtx(), patientId, -1, null);
		assertNotNull(list);
		// si no existe ningun registro para esa organizacion, validar que la lista este vacia
		if (poModel1 == null) {
			assertTrue(list.isEmpty());
		} else {
			// valida que la lista no este vacia
			assertFalse(list.isEmpty());
		}
		// valida que la lista de vacunas no rechazas/rechazas no sea nula
		assertNotNull(MEXMEHistVacuna.getVaccines(Env.getCtx(), patientId, MEXMEHistVacuna.TYPE_Given, null));
		assertNotNull(MEXMEHistVacuna.getVaccines(Env.getCtx(), patientId, MEXMEHistVacuna.TYPE_Rejected, null));
	}
	
	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEHistVacunaTest#getSourceName()}.
	 */
	@Test
	public void testGetSourceName() {
		final MEXMEHistVacuna histVac = TestUtils.getRandomPO(MEXMEHistVacuna.class);
		if (histVac != null) {
			// False si es nulo o vacio
			assertTrue(histVac.getSource() != null && StringUtils.isNotBlank(histVac.getSourceName()));
		}
	}
	
	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEHistVacunaTest#getSiteName()}.
	 */
	@Test
	public void testGetSiteName() {
		final MEXMEHistVacuna histVac = TestUtils.getRandomPO(MEXMEHistVacuna.class);
		if (histVac != null) {
			// False si es nulo o vacio
			assertTrue(histVac.getSite() != null && StringUtils.isNotBlank(histVac.getSiteName()));
		}
	}
	
	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEHistVacunaTest#getRejectedReason()}.
	 */
	@Test
	public void testGetRejectedReason() {
		final MEXMEHistVacuna histVac = TestUtils.getRandomPO(MEXMEHistVacuna.class);
		if (histVac != null) {
			// False si es nulo o vacio
			assertTrue(histVac.getMotivoRechazo() != null && StringUtils.isNotBlank(histVac.getRejectedReason()));
		}
	}
	
	
}
