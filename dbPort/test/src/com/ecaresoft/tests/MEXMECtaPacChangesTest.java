/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacChanges;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author Lorena Lama
 * 
 */
public class MEXMECtaPacChangesTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMECtaPacChanges#getList(java.util.Properties, java.lang.String, java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	public void testGetList() {
		assertNotNull(MEXMECtaPacChanges.getList(Env.getCtx(), null, null, null));
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getListByCtaPac(java.util.Properties, int, java.lang.String)}.
	 */
	public void testGetListByCtaPac() {
		final MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class);
		if(poModel == null){
			assertTrue(MEXMECtaPacChanges.getListByCtaPac(Env.getCtx(), 0, null).isEmpty());
		} else {
			assertFalse(MEXMECtaPacChanges.getListByCtaPac(Env.getCtx(), poModel.getEXME_CtaPac_ID(), null).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getListByDoctor(java.util.Properties, int)}.
	 */
	public void testGetListByDoctor() {
		final MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class, " Authenticated_Date IS NULL ", true);
		if(poModel == null){
			assertTrue(MEXMECtaPacChanges.getListByDoctor(Env.getCtx(), 0).isEmpty());
		} else {
			assertFalse(MEXMECtaPacChanges.getListByDoctor(Env.getCtx(), poModel.getEXME_Medico_ID()).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getPrevAdmitDate(java.util.Properties, int, int)}.
	 */
	public void testGetPrevAdmitDate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#authenticate()}.
	 */
	public void testAuthenticate() {
		final MEXMECtaPac ctapac = TestUtils.getRandomPO(MEXMECtaPac.class);
		if (ctapac != null) {
			final MEXMECtaPacChanges poModel = new MEXMECtaPacChanges(Env.getCtx(), 0, null);
			Trx trx = null;
			try {
				trx = Trx.get(Trx.createTrxName("testAuthenticate"), true);
				poModel.set_TrxName(trx.getTrxName());
				poModel.setCtapac(ctapac);
				poModel.setOrderType(MEXMECtaPacChanges.ORDERTYPE_WrittenOrder);
				poModel.authenticate();
				Trx.rollback(trx, true);// close
			} catch (Exception e) {
				Trx.rollback(trx, true);
			}
			assertNotNull(poModel.getAuthenticated_Date());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getAreaAct()}.
	 */
	public void testGetAreaAct() {
		MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class);
		if (poModel == null) {
			poModel = new MEXMECtaPacChanges(Env.getCtx(), 0, null);
		}
		assertNotNull(poModel.getAreaAct());
		assertEquals(poModel.getEXME_AreaAct_ID(), poModel.getAreaAct().getEXME_Area_ID());
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getAreaAnt()}.
	 */
	public void testGetAreaAnt() {
		MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class);
		if (poModel == null) {
			poModel = new MEXMECtaPacChanges(Env.getCtx(), 0, null);
		}
		assertNotNull(poModel.getAreaAnt());
		assertEquals(poModel.getEXME_AreaAnt_ID(), poModel.getAreaAnt().getEXME_Area_ID());
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getCtaPac()}.
	 */
	public void testGetCtaPac() {
		MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class);
		if (poModel == null) {
			poModel = new MEXMECtaPacChanges(Env.getCtx(), 0, null);
		}
		assertNotNull(poModel.getCtaPac());
		assertEquals(poModel.getEXME_CtaPac_ID(), poModel.getCtaPac().getEXME_CtaPac_ID());
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getPatientName()}.
	 */
	public void testGetPatientName() {
		MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class);
		if (poModel == null) {
			poModel = new MEXMECtaPacChanges(Env.getCtx(), 0, null);
		}
		assertNotNull(poModel.getPatientName());
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getRecordType()}.
	 */
	public void testGetRecordType() {
		MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class);
		if (poModel != null) {
			assertTrue(StringUtils.isNotEmpty(poModel.getPatientName()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getResStatusActStr()}.
	 */
	public void testGetResStatusActStr() {
		MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class);
		if (poModel != null) {
			assertEquals(StringUtils.isNotEmpty(poModel.getResStatusActStr()), poModel.getResStatusAct() != null);
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getResStatusAntStr()}.
	 */
	public void testGetResStatusAntStr() {
		MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class);
		if (poModel != null) {
			assertEquals(StringUtils.isNotEmpty(poModel.getResStatusAntStr()), poModel.getResStatusAnt() != null);
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getSummaryDetail()}.
	 */
	public void testGetSummaryDetail() {
		final MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class);
		if (poModel == null) {
			assertNotNull(new MEXMECtaPacChanges(Env.getCtx(), 0, null).getSummaryDetail());
		} else {
			assertNotNull(poModel.getSummaryDetail());
			assertTrue(poModel.getSummaryDetail().length() > 1);
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getTipoPacAct()}.
	 */
	public void testGetTipoPacAct() {
		MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class);
		if (poModel == null) {
			poModel = new MEXMECtaPacChanges(Env.getCtx(), 0, null);
		}
		assertNotNull(poModel.getTipoPacAct());
		assertEquals(poModel.getEXME_TipoPacAct_ID(), poModel.getTipoPacAct().getEXME_TipoPaciente_ID());
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getTipoPacAnt()}.
	 */
	public void testGetTipoPacAnt() {
		MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class);
		if (poModel == null) {
			poModel = new MEXMECtaPacChanges(Env.getCtx(), 0, null);
		}
		assertNotNull(poModel.getTipoPacAnt());
		assertEquals(poModel.getEXME_TipoPacAnt_ID(), poModel.getTipoPacAnt().getEXME_TipoPaciente_ID());
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#getUserName()}.
	 */
	public void testGetUserName() {
		MEXMECtaPacChanges poModel = TestUtils.getRandomPO(MEXMECtaPacChanges.class);
		if (poModel == null) {
			poModel = new MEXMECtaPacChanges(Env.getCtx(), 0, null);
		}
		assertNotNull(poModel.getUserName());
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMECtaPacChanges#setCtapac(org.compiere.model.MEXMECtaPac)}.
	 */
	public void testSetCtapac() {
		MEXMECtaPac poModel = TestUtils.getRandomPO(MEXMECtaPac.class);
		if (poModel != null) {
			MEXMECtaPacChanges changes = new MEXMECtaPacChanges(Env.getCtx(), 0, null);
			changes.setCtapac(poModel);
			assertEquals(poModel, changes.getCtaPac());
		}
	}

}
