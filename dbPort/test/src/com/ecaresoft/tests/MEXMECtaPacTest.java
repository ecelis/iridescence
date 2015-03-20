package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEPaciente;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Test Class form {@link org.compiere.model.MEXMECtaPac}
 * @author freyes
 *
 */
public class MEXMECtaPacTest extends TestCase {
	
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpDemoMX();
	}
	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPac#getRoomBed()}.
	 */
	@Test
	public void testgetRoomBed() {
		MEXMEPaciente patient = TestUtils.getRandomPO(MEXMEPaciente.class);
		List<MEXMECtaPac> lst = MEXMEPaciente.getAllCtaPac(Env.getCtx(), patient.getEXME_Paciente_ID(), null);
		for(MEXMECtaPac ctaPac : lst){
			assertTrue(ctaPac.getRoomBed() != null);
		}
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPac#getPatientType()}.
	 */
	@Test
	public void testgetPatientType() {
		MEXMEPaciente patient = TestUtils.getRandomPO(MEXMEPaciente.class);
		List<MEXMECtaPac> lst = MEXMEPaciente.getAllCtaPac(Env.getCtx(), patient.getEXME_Paciente_ID(), null);
		for(MEXMECtaPac ctaPac : lst){
			assertTrue(ctaPac.getPatientType() != null);
		}
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPac#getCurrentCtaPac}}
	 */
	@Test
	public void testgetCurrentCtaPac() {
		assertNull(MEXMECtaPac.getCurrentCtaPac(Env.getCtx(), 0, null));

		MEXMEPaciente patient = TestUtils.getRandomPO(MEXMEPaciente.class);
		List<MEXMECtaPac> lst = MEXMEPaciente.getAllCtaPac(Env.getCtx(),
				patient.getEXME_Paciente_ID(), null);
		boolean bandera = false;
		
		for (MEXMECtaPac ctaPac : lst) {
			// si encounter status sea I o P y que fecha cierre sea null
			// entonces valida que el metodo regrese la cta activa
			if ((MEXMECtaPac.ENCOUNTERSTATUS_Admission.equals(ctaPac
					.getEncounterStatus()) || MEXMECtaPac.ENCOUNTERSTATUS_Predischarge
					.equals(ctaPac.getEncounterStatus()))
					&& (ctaPac.getFechaCierre() == null)) {
				assertNotNull(MEXMECtaPac.getCurrentCtaPac(Env.getCtx(),
						ctaPac.getEXME_Paciente_ID(), null));
				bandera = true;
				break;
			}
		}

		if (!bandera) {
			// si no tiene ninguna cta activa el metodo debe regresar un null
			assertNull(MEXMECtaPac.getCurrentCtaPac(Env.getCtx(),
					patient.getEXME_Paciente_ID(), null));
		}
	}
}
