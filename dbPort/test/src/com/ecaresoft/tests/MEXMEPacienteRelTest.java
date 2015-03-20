/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEPacienteRel;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author gvaldez
 *
 */
public class MEXMEPacienteRelTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteRel#getGuarFromPatient()}.
	 */
	@Test
	public final void testGetGuarFromPatient() {

		MEXMEPaciente patient = TestUtils.getRandomPO(MEXMEPaciente.class, "isActive = 'Y' AND copyResponsible = 'Y'", true);
		
		if(patient != null){
			assertTrue(MEXMEPacienteRel.getGuarFromPatient(Env.getCtx(),null,
				new Object[] {MEXMEPacienteRel.TYPE_Responsible, patient.getEXME_Paciente_ID()}) >0);
		}
		
		patient = TestUtils.getRandomPO(MEXMEPaciente.class, "isActive = 'Y' AND copyResponsible <> 'Y'", true);
		
		if(patient != null){
			assertTrue(MEXMEPacienteRel.getGuarFromPatient(Env.getCtx(), null,new Object[] {MEXMEPacienteRel.TYPE_Responsible, patient.getEXME_Paciente_ID()}) <= 0);
		}
	
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteRel#afterSave()}.
	 */
	@Test
	public final void testAfterSave() {

		Trx mTrx = Trx.get(Trx.createTrxName("pt"), true);
		MEXMEPaciente patient = TestUtils.getRandomPO(MEXMEPaciente.class);
		MEXMEPacienteRel patRel = TestUtils.getRandomPO(MEXMEPacienteRel.class,"IsActive='Y' AND Type2='R'", true);
		patRel.setEXME_Paciente1_ID(patient.getEXME_Paciente_ID());
		assertTrue(patRel.save(mTrx.getTrxName()));
		mTrx.rollback();
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEPacienteRel#alreadyExists()}.
	 */
	@Test
	public final void testAlreadyExists() {

		
		MEXMEPaciente patient = TestUtils.getRandomPO(MEXMEPaciente.class);
		//Para Mexico no se prueba, el login del Test es del Cliente USA.
		//Siempre deberia regresar falso.
		assertFalse(MEXMEPacienteRel.alreadyExists(Env.getCtx(), patient.getEXME_Paciente_ID()));
		
	}

}
