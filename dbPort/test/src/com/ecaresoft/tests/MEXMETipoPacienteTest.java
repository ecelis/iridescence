/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MEXMETipoPaciente;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 * 
 */
public class MEXMETipoPacienteTest extends TestCase {

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPaciente#getList(java.util.Properties)}.
	 */
	public void testGetList() {
		final MEXMETipoPaciente poModel = TestUtils.getRandomPO(MEXMETipoPaciente.class);
		if (poModel == null) {
			assertTrue(MEXMETipoPaciente.getList(Env.getCtx()).isEmpty());
		} else {
			assertFalse(MEXMETipoPaciente.getList(Env.getCtx()).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPaciente#getPatientTypeByAreaType(java.util.Properties, java.lang.String)}.
	 */
	public void testGetPatientTypeByAreaTypePropertiesString() {
		final MEXMETipoPaciente poModel = TestUtils.getRandomPO(MEXMETipoPaciente.class);
		if (poModel == null) {
			assertNull(MEXMETipoPaciente.getPatientTypeByAreaType(Env.getCtx(), MEXMETipoPaciente.TIPOAREA_Hospitalization));
		} else {
			assertNotNull(MEXMETipoPaciente.getPatientTypeByAreaType(Env.getCtx(), poModel.getTipoArea()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPaciente#getPatientTypeByAreaType(java.util.Properties, java.lang.String, java.lang.String)}
	 * .
	 */
	public void testGetPatientTypeByAreaTypePropertiesStringString() {
		final MEXMETipoPaciente poModel = TestUtils.getRandomPO(MEXMETipoPaciente.class);
		if (poModel == null) {
			assertNull(MEXMETipoPaciente.getPatientTypeByAreaType(Env.getCtx(), MEXMETipoPaciente.TIPOAREA_Hospitalization, null));
		} else {
			assertNotNull(MEXMETipoPaciente.getPatientTypeByAreaType(Env.getCtx(), poModel.getTipoArea(), null));
		}
	}

//	/**//	/**
//	 * Test method for {@link org.compiere.model.MEXMETipoPaciente#copyFromSystem(java.util.Properties, java.lang.String)}.
//	 */
//	public void testCopyFromSystem() {
//		fail("Not yet implemented"); // TODO
//	}
//	 * Test method for {@link org.compiere.model.MEXMETipoPaciente#copyFromSystem(java.util.Properties, java.lang.String)}.
//	 */
//	public void testCopyFromSystem() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPaciente#get(java.util.Properties)}.
	 */
	public void testGetProperties() {
		final MEXMETipoPaciente poModel = TestUtils.getRandomPO(MEXMETipoPaciente.class);
		if (poModel == null) {
			assertTrue(MEXMETipoPaciente.get(Env.getCtx()).isEmpty());
		} else {
			assertFalse(MEXMETipoPaciente.get(Env.getCtx()).isEmpty());
		}
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMETipoPaciente#get(java.util.Properties, java.lang.String, java.util.List)}.
//	 */
//	public void testGetPropertiesStringListOfQ() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPaciente#get(java.util.Properties, java.lang.String)}.
	 */
	public void testGetPropertiesString() {
		assertNotNull(MEXMETipoPaciente.get(Env.getCtx(), "'H','A'"));
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPaciente#getAll(java.util.Properties)}.
	 */
	public void testGetAll() {
		assertNotNull(MEXMETipoPaciente.getAll(Env.getCtx()));
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMETipoPaciente#getDischargeSummaryRepor()}.
//	 */
//	public void testGetDischargeSummaryRepor() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPaciente#getTiposPacienteNotPOS(java.util.Properties, java.lang.String)}.
	 */
	public void testGetTiposPacienteNotPOS() {
		assertNotNull(MEXMETipoPaciente.getList(Env.getCtx()));
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMETipoPaciente#getBillType()}.
//	 */
//	public void testGetBillType() {
//		fail("Not yet implemented"); // TODO
//	}

}
