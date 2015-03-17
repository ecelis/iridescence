/**
 * 
 */
package com.ecaresoft.tests;

import java.util.Random;

import junit.framework.TestCase;

import org.compiere.model.MEXMEPaciente;
import org.compiere.util.Trx;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author gvaldez
 *
 */
public class MEXMEPacienteTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteRel#afterSave()}.
	 */
	@Test
	public final void testAfterSave() {

		Trx mTrx = Trx.get(Trx.createTrxName("pt"), true);
		MEXMEPaciente patient = TestUtils.getRandomPO(MEXMEPaciente.class);
		patient.setName(TestUtils.generateString(new Random(), "GERA", 8));
		assertTrue(patient.save(mTrx.getTrxName()));
		Trx.rollback(mTrx, true);//Lama: se cierra la transaccion
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPaciente#getNombre_Pac(int, String)}.
	 */
	@Test
	public void testNombre_PacIntString() throws Exception {
		MEXMEPaciente patient = TestUtils.getRandomPO(MEXMEPaciente.class);
		assertNotNull(MEXMEPaciente.getNombre_Pac(patient.getEXME_Paciente_ID(), null));
	}
}
