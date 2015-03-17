package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MDiagnostico;
import org.compiere.model.MEXMEDiagnosisTypeDet;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMEDiagnosisTypeDetTest extends TestCase {
	
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	@Test
	public void testSave(){
		MEXMEDiagnosisTypeDet diag = TestUtils.getRandomPO(MEXMEDiagnosisTypeDet.class,null, false);
		MEXMEDiagnosisTypeDet newDiag = new MEXMEDiagnosisTypeDet(Env.getCtx(), 0, null);
		newDiag.setEXME_DiagnosisType_ID(diag.getEXME_DiagnosisType_ID());
		newDiag.setEXME_Diagnostico_ID(diag.getEXME_Diagnostico_ID());
		newDiag.save();
		
		MDiagnostico diagnostico = new MDiagnostico(Env.getCtx(), newDiag.getEXME_Diagnostico_ID(), null);
		assertTrue(newDiag.getCode().equals(diagnostico.getValue()));
	}
}
