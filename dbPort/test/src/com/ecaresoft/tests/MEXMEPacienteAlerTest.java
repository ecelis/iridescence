/**
 * 
 */
package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.BasicoTresProps;
import org.compiere.model.MEXMEPacienteAler;
import org.compiere.model.MRefList;
import org.compiere.model.Query;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 * 
 */
public class MEXMEPacienteAlerTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteAler#getAlergiasPacienteFDBConceptId(java.util.Properties, int, java.lang.String)}.
	 */
	public void testGetAlergiasPacienteFDBConceptIdPropertiesIntString() {
		// fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteAler#getAlergiasFROMsACTIVA(java.util.Properties, int)}.
	 */
	public void testGetAlergiasFROMsACTIVA() {
		// fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteAler#esAlergico(java.util.Properties, long, long)}.
	 */
	public void testEsAlergico() {
		// fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteAler#validaAlergia(java.util.Properties, java.lang.String, long, long)}.
	 */
	public void testValidaAlergia() {
		// fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteAler#getHistoria(java.util.Properties, int)}.
	 */
	public void testGetHistoriaPropertiesInt() {
		// fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteAler#getHistoria(java.util.Properties, int, java.lang.String, java.lang.Object[])}.
	 */
	public void testGetHistoriaPropertiesIntStringObjectArray() {
		// fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteAler#getHistAlergia(java.util.Properties, int)}.
	 */
	public void testGetHistAlergia() {
		// fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteAler#getAllergies(java.util.Properties, int)}.
	 */
	public void testGetAllergies() {
		// fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteAler#getReaccionSustActiva(java.util.Properties, int, int)}.
	 */
	public void testGetReaccionSustActiva() {
		// fail("Not yet implemented"); // TODO
	}

	/** Test method for {@link org.compiere.model.MEXMEPacienteAler#getAlergias(java.util.Properties, int, java.lang.String)}. */
	public void testGetAlergias() {
		List<BasicoTresProps> lst = MEXMEPacienteAler.getAlergias(Env.getCtx(), 0, null);
		assertNotNull(lst);
		assertTrue(lst.isEmpty());

		final MEXMEPacienteAler obj =
			TestUtils.getRandomPO(MEXMEPacienteAler.class, " severidad<>? AND estatus <>? AND EXME_SActiva_ID IS NOT NULL ", false,
				MEXMEPacienteAler.SEVERIDAD_Unknow, MEXMEPacienteAler.ESTATUS_Inactive);

		if (obj != null) {
			lst = MEXMEPacienteAler.getAlergias(Env.getCtx(), obj.getEXME_Paciente_ID(), null);
			assertNotNull(lst);
			assertFalse(lst.isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteAler#getAlergiasPacienteFDBConceptId(java.util.Properties, int)}.
	 */
	public void testGetAlergiasPacienteFDBConceptIdPropertiesInt() {
		// fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteAler#getInfoAlergia(java.util.Properties, int)}.
	 */
	public void testGetInfoAlergia() {
		// fail("Not yet implemented"); // TODO
	}

	/** Test method for {@link org.compiere.model.MEXMEPacienteAler#getSeverityColor(java.util.Properties, int)}. */
	public void testGetSeverityColor() {
		String retValue = MEXMEPacienteAler.getSeverityColor(Env.getCtx(), 0);
		assertNotNull(retValue);
		assertEquals(retValue, "");

		final List<MRefList> list =
			new Query(Env.getCtx(), MRefList.Table_Name, " AD_Reference_ID=? AND colorHex IS NOT NULL ", null).setParameters(
				MEXMEPacienteAler.SEVERIDAD_AD_Reference_ID).list();

		// con alergia
		if (!list.isEmpty()) {
			final MEXMEPacienteAler obj =
				TestUtils.getRandomPO(MEXMEPacienteAler.class, " severidad<>? AND estatus NOT IN (?,?) AND EXME_SActiva_ID IS NOT NULL ", false,
					MEXMEPacienteAler.SEVERIDAD_Unknow, MEXMEPacienteAler.ESTATUS_Erroneous, MEXMEPacienteAler.ESTATUS_Inactive);

			if (obj != null) {
				retValue = MEXMEPacienteAler.getSeverityColor(Env.getCtx(), obj.getEXME_Paciente_ID());
				assertNotNull(retValue);
				assertTrue(StringUtils.contains(retValue, "#"));
			}
		}
	}

}
