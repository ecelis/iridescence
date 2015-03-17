/**
 * 
 */
package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMETipoPacDestino;
import org.compiere.model.MEXMETipoPaciente;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 * 
 */
public class MEXMETipoPacDestinoTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPacDestino#getList(java.util.Properties, int)}.
	 */
	public void testGetListPropertiesInt() {
		assertTrue(MEXMETipoPacDestino.getList(Env.getCtx(), 0).isEmpty());
		final MEXMETipoPaciente tp = TestUtils.getRandomPO(MEXMETipoPaciente.class);
		final List<KeyNamePair> list = MEXMETipoPacDestino.getList(Env.getCtx(), tp == null ? 0 : tp.getEXME_TipoPaciente_ID());
		assertNotNull(list);
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPacDestino#getList(java.util.Properties, int, org.compiere.model.MEXMECtaPac)}.
	 */
	public void testGetListPropertiesIntMEXMECtaPac() {
		assertTrue(MEXMETipoPacDestino.getList(Env.getCtx(), 0, null).isEmpty());
		final MEXMECtaPac ctapac = TestUtils.getRandomPO(MEXMECtaPac.class);
		final MEXMETipoPaciente tp = TestUtils.getRandomPO(MEXMETipoPaciente.class);
		final List<KeyNamePair> list = MEXMETipoPacDestino.getList(Env.getCtx(), tp == null ? 0 : tp.getEXME_TipoPaciente_ID(), ctapac);
		assertNotNull(list);
		// el tipo de paciente de la cuenta debe estar incluido en la lista
		if (ctapac != null && ctapac.getEXME_TipoPaciente_ID() > 0) {
			assertTrue(list.contains(new KeyNamePair(ctapac.getEXME_TipoPaciente_ID(), ctapac.getEXME_TipoPaciente().getName())));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPacDestino#getRecord(java.util.Properties, int, int)}.
	 */
	public void testGetRecord() {
		assertNull(MEXMETipoPacDestino.getRecord(Env.getCtx(), 0, 0));
		final MEXMETipoPacDestino poModel = TestUtils.getRandomPO(MEXMETipoPacDestino.class);
		if (poModel != null) {
			assertNotNull(MEXMETipoPacDestino.getRecord(Env.getCtx(), poModel.getEXME_TipoPaciente_ID(), poModel.getEXME_TipoPacienteTo_ID()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPacDestino#getAlertMsg()}.
	 */
	public void testGetAlertMsg() {
		final MEXMETipoPacDestino poModel = TestUtils.getRandomPO(MEXMETipoPacDestino.class, " AlertMessage IS NOT NULL ", true);
		if (poModel == null) {
			assertTrue(StringUtils.isEmpty(new MEXMETipoPacDestino(Env.getCtx(), 0, null).getAlertMsg()));
		} else {
			assertTrue(StringUtils.isNotEmpty(poModel.getAlertMsg()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPacDestino#getTipoPaciente()}.
	 */
	public void testGetTipoPaciente() {
		final MEXMETipoPacDestino poModel = TestUtils.getRandomPO(MEXMETipoPacDestino.class);
		if (poModel == null) {
			assertNull(new MEXMETipoPacDestino(Env.getCtx(), 0, null).getTipoPaciente());
		} else {
			assertNotNull(poModel.getTipoPaciente());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPacDestino#getTipoPacienteStr()}.
	 */
	public void testGetTipoPacienteStr() {
		final MEXMETipoPacDestino poModel = TestUtils.getRandomPO(MEXMETipoPacDestino.class);
		if (poModel == null) {
			assertTrue(StringUtils.isEmpty(new MEXMETipoPacDestino(Env.getCtx(), 0, null).getTipoPacienteStr()));
		} else {
			assertTrue(StringUtils.isNotEmpty(poModel.getTipoPacienteStr()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPacDestino#getTipoPacienteTo()}.
	 */
	public void testGetTipoPacienteTo() {
		final MEXMETipoPacDestino poModel = TestUtils.getRandomPO(MEXMETipoPacDestino.class);
		if (poModel == null) {
			assertNull(new MEXMETipoPacDestino(Env.getCtx(), 0, null).getTipoPacienteTo());
		} else {
			assertNotNull(poModel.getTipoPacienteTo());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMETipoPacDestino#getTipoPacienteToStr()}.
	 */
	public void testGetTipoPacienteToStr() {
		final MEXMETipoPacDestino poModel = TestUtils.getRandomPO(MEXMETipoPacDestino.class);
		if (poModel == null) {
			assertTrue(StringUtils.isEmpty(new MEXMETipoPacDestino(Env.getCtx(), 0, null).getTipoPacienteToStr()));
		} else {
			assertTrue(StringUtils.isNotEmpty(poModel.getTipoPacienteToStr()));
		}
	}
}
