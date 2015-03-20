/**
 * 
 */
package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MDiagnostico;
import org.compiere.model.MEXMEPatientRel;
import org.compiere.model.MEXMEPatientRelDiag;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MEXMEPatientRelDiagTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPatientRelDiag#getByRelative(java.util.Properties, java.lang.String, int)}.
	 */
	@Test
	public void testGetByRelative() {
		MEXMEPatientRel relative = TestUtils.getRandomPO(MEXMEPatientRel.class);
		List<MEXMEPatientRelDiag> lst = MEXMEPatientRelDiag.getByRelative(Env.getCtx(), null, relative.getEXME_PatientRel_ID());
		if(lst!=null && !lst.isEmpty()){
			for(MEXMEPatientRelDiag relDiag: lst){
				assertTrue(relDiag.isActive());
				assertTrue(relDiag.getEXME_PatientRel_ID() == relative.getEXME_PatientRel_ID());
				
			}
		}
		
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEPatientRelDiag#getByDiag(java.util.Properties, java.lang.String, int, int)}.
	 */
	@Test
	public void testGetByDiag() {
		MEXMEPatientRel relative = TestUtils.getRandomPO(MEXMEPatientRel.class);
		MDiagnostico diag = TestUtils.getRandomPO(MDiagnostico.class);
		MEXMEPatientRelDiag obj = MEXMEPatientRelDiag.getByDiag(Env.getCtx(), null, relative.getEXME_PatientRel_ID(), diag.getEXME_Diagnostico_ID());
		if(obj!=null){
			assertTrue(obj.isActive());
			assertTrue(obj.getEXME_PatientRel_ID() == relative.getEXME_PatientRel_ID() && obj.getEXME_Diagnostico_ID() == diag.getEXME_Diagnostico_ID());
		}
	}

}
