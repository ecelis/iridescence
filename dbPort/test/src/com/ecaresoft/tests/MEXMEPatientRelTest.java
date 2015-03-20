/**
 * 
 */
package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEPatientRel;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MEXMEPatientRelTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPatientRel#getByPatient(java.util.Properties, java.lang.String, int)}.
	 */
	@Test
	public void testGetByPatient() {
		MEXMEPaciente patient = TestUtils.getRandomPO(MEXMEPaciente.class);
		List<MEXMEPatientRel> lst = MEXMEPatientRel.getByPatient(Env.getCtx(), null, patient.getEXME_Paciente_ID());
		if(lst!=null && !lst.isEmpty()){
			for(MEXMEPatientRel patientRel:lst){
				assertTrue(patientRel.isActive());
				assertTrue(patientRel.getEXME_Paciente_ID() == patient.getEXME_Paciente_ID());
			}
		}
		
	}

}
