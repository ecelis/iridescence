/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MEXMEEstiloVidaPaciente;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Test Class form {@link org.compiere.model.MEXMEEstiloVidaPaciente}
 * 
 * @author lama
 */
public class MEXMEEstiloVidaPacienteTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEEstiloVidaPaciente#getHistoria(java.util.Properties, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetHistoria() {
		final MEXMEEstiloVidaPaciente poModel = new Query(Env.getCtx(), MEXMEEstiloVidaPaciente.Table_Name, null, null)//
				.addAccessLevelSQL(true).first();
		if (poModel == null) {
			assertTrue(MEXMEEstiloVidaPaciente.getHistoria(Env.getCtx(), 0, null).isEmpty());
		} else {
			assertFalse(MEXMEEstiloVidaPaciente.getHistoria(Env.getCtx(), poModel.getEXME_Paciente_ID(), null).isEmpty());
		}
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEEstiloVidaPaciente#getHistoria(java.util.Properties, int, boolean, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetHistoriaActive() {
		final MEXMEEstiloVidaPaciente poModel = new Query(Env.getCtx(), MEXMEEstiloVidaPaciente.Table_Name, null, null)//
				.addAccessLevelSQL(true).setOnlyActiveRecords(true).first();
		if (poModel == null) {
			assertTrue(MEXMEEstiloVidaPaciente.getHistoria(Env.getCtx(), 0, true, false,null).isEmpty());
		} else {
			assertFalse(MEXMEEstiloVidaPaciente.getHistoria(Env.getCtx(), poModel.getEXME_Paciente_ID(), true, false,null).isEmpty());
		}
	}

}
