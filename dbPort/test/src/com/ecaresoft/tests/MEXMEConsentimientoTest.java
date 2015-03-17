/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MEXMEConsentimiento;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Test Class form {@link org.compiere.model.MEXMEConsentimiento}
 * 
 * @author lama
 */
public class MEXMEConsentimientoTest extends TestCase {

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEConsentimiento#getHistoria(java.util.Properties, int, int, java.lang.String)}
	 */
	@Test
	public void testGetHistoria() {
		// consentimiento paciente
		MEXMEConsentimiento poModel1 = new Query(Env.getCtx(), MEXMEConsentimiento.Table_Name, " EXME_Paciente_ID IS NOT NULL ", null)//
				.addAccessLevelSQL(true).setOnlyActiveRecords(true).first();
		// si no existe ningun registro para esa organizacion, validar que la lista este vacia
		if (poModel1 == null) {
			assertTrue(MEXMEConsentimiento.getHistoria(Env.getCtx(), 0, 0, false,null).isEmpty());
		} else {
			// valida que la lista no este vacia
			assertFalse(MEXMEConsentimiento.getHistoria(Env.getCtx(), poModel1.getEXME_Paciente_ID(), 0, false,null).isEmpty());
			// consentimiento por cuenta paciente
			poModel1 = new Query(Env.getCtx(), MEXMEConsentimiento.Table_Name, " EXME_Paciente_ID IS NOT NULL AND EXME_CtaPac_ID IS NOT NULL ", null)//
					.addAccessLevelSQL(true).setOnlyActiveRecords(true).first();
			if (poModel1 != null) {
				// si existen registros con cuenta paciente, validar que la lista no sea nula
				assertFalse(MEXMEConsentimiento.getHistoria(Env.getCtx(), poModel1.getEXME_Paciente_ID(), poModel1.getEXME_CtaPac_ID(), false,null)
						.isEmpty());
			}
		}
	}

}
