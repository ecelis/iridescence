/**
 * 
 */
package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.ActPacienteIndView;
import org.compiere.model.MEXMEActPacienteInd;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author Lorena Lama
 *
 */
public class MEXMEActPacienteIndTest extends TestCase {

	/**@see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#toString()}.
//	 */
//	public void testToString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#beforeSave(boolean)}.
//	 */
//	public void testBeforeSave() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#afterSave(boolean, boolean)}.
//	 */
//	public void testAfterSave() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getAttachment()}.
//	 */
//	public void testGetAttachment() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getEstudiosObx()}.
//	 */
//	public void testGetEstudiosObx() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setEstudiosObx(java.util.List)}.
//	 */
//	public void testSetEstudiosObx() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getEstudios(boolean)}.
//	 */
//	public void testGetEstudiosBoolean() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getPrescriptionsLst(java.util.Properties, int, java.lang.String, java.lang.String, java.lang.Object[])}.
//	 */
//	public void testGetPrescriptionsLst() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getInstitucionesServicioAPInd(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetInstitucionesServicioAPInd() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getMProducto()}.
//	 */
//	public void testGetMProducto() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setMProducto(org.compiere.model.MEXMEProduct)}.
//	 */
//	public void testSetMProducto() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getBeanID()}.
//	 */
//	public void testGetBeanID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setBeanID(org.compiere.model.bpm.Bean4String)}.
//	 */
//	public void testSetBeanID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getBeanRevCode()}.
//	 */
//	public void testGetBeanRevCode() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setBeanRevCode(org.compiere.model.MEXMERevenueCodes)}.
//	 */
//	public void testSetBeanRevCode() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getInstrunctions(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetInstrunctions() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getInstActPac(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetInstActPac() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getDieta(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetDieta() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getOtherServices(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetOtherServices() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getStudies(java.util.Properties, int, boolean, int, java.lang.String)}.
//	 */
//	public void testGetStudies() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getLstSolicitud(java.util.Properties, int, java.lang.String, java.lang.String)}.
//	 */
//	public void testGetLstSolicitud() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getEstudiosObxRef()}.
//	 */
//	public void testGetEstudiosObxRef() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getEstudios(boolean, boolean)}.
//	 */
//	public void testGetEstudiosBooleanBoolean() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getFromCita(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetFromCita() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getLastService(java.util.Properties, int, int, java.lang.String)}.
//	 */
//	public void testGetLastService() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getServicesLst(java.util.Properties, int, java.lang.String, java.lang.String, java.lang.Object[])}.
//	 */
//	public void testGetServicesLst() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getSolicitud(int, int)}.
//	 */
//	public void testGetSolicitud() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getSavedMeds(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetSavedMeds() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getCountServices(java.util.Properties, int, java.lang.String[], java.util.Date, java.util.Date, java.lang.String[])}.
//	 */
//	public void testGetCountServices() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getServices(java.util.Properties, int, java.lang.String[], java.util.Date, java.lang.String[])}.
//	 */
//	public void testGetServicesPropertiesIntStringArrayDateStringArray() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getServices(java.util.Properties, int, java.lang.String[], java.util.Date, java.util.Date, java.lang.String[], java.lang.String, java.lang.Object[])}.
//	 */
//	public void testGetServicesPropertiesIntStringArrayDateDateStringArrayStringObjectArray() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getSQLServices(boolean, java.util.List, int, java.lang.String[], java.util.Date, java.util.Date, java.lang.String[])}.
//	 */
//	public void testGetSQLServices() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getPrefix()}.
//	 */
//	public void testGetPrefix() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getMsgText()}.
//	 */
//	public void testGetMsgText() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getDiagnosis1Text()}.
//	 */
//	public void testGetDiagnosis1Text() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setDiagnosis1Text(java.lang.String)}.
//	 */
//	public void testSetDiagnosis1Text() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getDiagnosis2Text()}.
//	 */
//	public void testGetDiagnosis2Text() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setDiagnosis2Text(java.lang.String)}.
//	 */
//	public void testSetDiagnosis2Text() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getDiagnosis3Text()}.
//	 */
//	public void testGetDiagnosis3Text() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setDiagnosis3Text(java.lang.String)}.
//	 */
//	public void testSetDiagnosis3Text() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setAttachment(org.compiere.model.MAttachment)}.
//	 */
//	public void testSetAttachment() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getMedico()}.
//	 */
//	public void testGetMedico() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getLstDiagnosis()}.
//	 */
//	public void testGetLstDiagnosis() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getDIFWKProductsValue(int, java.util.Date, java.util.Date, boolean)}.
//	 */
//	public void testGetDIFWKProductsValue() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#hasObx()}.
//	 */
//	public void testHasObx() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setActPacienteID(int)}.
//	 */
//	public void testSetActPacienteID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getActPacienteID()}.
//	 */
//	public void testGetActPacienteID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#saveDiag(boolean)}.
//	 */
//	public void testSaveDiag() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#isHasOBX()}.
//	 */
//	public void testIsHasOBX() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setHasOBX(boolean)}.
//	 */
//	public void testSetHasOBXBoolean() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setHasOBX()}.
//	 */
//	public void testSetHasOBX() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getUomName()}.
//	 */
//	public void testGetUomName() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setUomName(java.lang.String)}.
//	 */
//	public void testSetUomName() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getTotalImp()}.
//	 */
//	public void testGetTotalImp() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setTotalImp(java.math.BigDecimal)}.
//	 */
//	public void testSetTotalImp() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#isManejaHL7()}.
//	 */
//	public void testIsManejaHL7() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setManejaHL7(boolean)}.
//	 */
//	public void testSetManejaHL7() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getAlmacen()}.
//	 */
//	public void testGetAlmacen() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getAlmacenID()}.
//	 */
//	public void testGetAlmacenID() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getLineasServicio(java.util.Properties, long, java.lang.String)}.
	 */
	public void testGetLineasServicio() {
		try {
			// vacio si es nuevo
			assertTrue(MEXMEActPacienteInd.getLineasServicio(Env.getCtx(), 0, null).isEmpty());
			// random
			final MEXMEActPacienteIndH obj = TestUtils.getRandomPO(MEXMEActPacienteIndH.class);
			if (obj != null) {
				final List<ActPacienteIndView> lst = MEXMEActPacienteInd.getLineasServicio(Env.getCtx(), obj.getEXME_ActPacienteIndH_ID(), null);
				final List<MEXMEActPacienteInd> lst2 = obj.getLineas(" M_Product_ID > 0 ", null, false);
				assertEquals(lst.size(), lst2.size());
				for (ActPacienteIndView ind : lst) {
					assertEquals(ind.getModel().getEXME_ActPacienteInd_ID(), ind.getXXActPacienteIndID());
					assertEquals(ind.getModel().getEXME_ActPacienteIndH_ID(), ind.getXXActPacienteIndHID());
					break;//valida el primero si no es vacia la lista
				}
			}
		} catch (Exception e) {
			fail(e.toString());
		}
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#createLine(org.compiere.model.MCtaPacDet, org.compiere.model.MEXMEEstServ, org.compiere.model.MEXMEActPacienteIndH)}.
//	 */
//	public void testCreateLine() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setActPacienteIndH(org.compiere.model.MEXMEActPacienteIndH)}.
//	 */
//	public void testSetActPacienteIndH() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getActPacienteIndH()}.
//	 */
//	public void testGetActPacienteIndH() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getUom()}.
//	 */
//	public void testGetUom() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getActPacIndH()}.
//	 */
//	public void testGetActPacIndH() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getDescripcion()}.
//	 */
//	public void testGetDescripcion() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setDescripcion(java.lang.String)}.
//	 */
//	public void testSetDescripcion() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getGenProduct()}.
//	 */
//	public void testGetGenProduct() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getDiag1()}.
//	 */
//	public void testGetDiag1() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getDiag2()}.
//	 */
//	public void testGetDiag2() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getDiag3()}.
//	 */
//	public void testGetDiag3() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getProduct()}.
//	 */
//	public void testGetProduct() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getCPT()}.
//	 */
//	public void testGetCPT() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getNameServ()}.
//	 */
//	public void testGetNameServ() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setNameServ(java.lang.String)}.
//	 */
//	public void testSetNameServ() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#processIt(java.lang.String)}.
//	 */
//	public void testProcessIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#unlockIt()}.
//	 */
//	public void testUnlockIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#invalidateIt()}.
//	 */
//	public void testInvalidateIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#prepareIt()}.
//	 */
//	public void testPrepareIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#approveIt()}.
//	 */
//	public void testApproveIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#rejectIt()}.
//	 */
//	public void testRejectIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#closeIt()}.
//	 */
//	public void testCloseIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#reverseCorrectIt()}.
//	 */
//	public void testReverseCorrectIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#reverseAccrualIt()}.
//	 */
//	public void testReverseAccrualIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#reActivateIt()}.
//	 */
//	public void testReActivateIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getSummary()}.
//	 */
//	public void testGetSummary() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getDocumentNo()}.
//	 */
//	public void testGetDocumentNo() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getDocumentInfo()}.
//	 */
//	public void testGetDocumentInfo() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#createPDF()}.
//	 */
//	public void testCreatePDF() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getProcessMsg()}.
//	 */
//	public void testGetProcessMsg() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getDoc_User_ID()}.
//	 */
//	public void testGetDoc_User_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getApprovalAmt()}.
//	 */
//	public void testGetApprovalAmt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#completeIt()}.
//	 */
//	public void testCompleteIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#voidIt()}.
//	 */
//	public void testVoidIt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getConfigPre()}.
//	 */
//	public void testGetConfigPre() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setConfigPre(org.compiere.model.MEXMEConfigPre)}.
//	 */
//	public void testSetConfigPre() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#createCharge(java.util.Properties, int, org.compiere.model.BeanActPacienteInd, java.lang.String)}.
//	 */
//	public void testCreateCharge() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getmCreateCharge()}.
//	 */
//	public void testGetmCreateCharge() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setmCreateCharge(org.compiere.model.bpm.CreateCharge)}.
//	 */
//	public void testSetmCreateCharge() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#referenciar(java.util.Properties, org.compiere.model.ServicioView, org.compiere.model.MEXMEActPacienteIndH, java.lang.String)}.
//	 */
//	public void testReferenciar() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setDates(org.compiere.model.ServicioView)}.
//	 */
//	public void testSetDates() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setDescription(org.compiere.model.ServicioView, java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	public void testSetDescriptionServicioViewStringStringString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setPrices(org.compiere.model.MEXMEActPacienteIndH, org.compiere.model.ServicioView)}.
//	 */
//	public void testSetPrices() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getProdRevCode(org.compiere.model.ServicioView)}.
//	 */
//	public void testGetProdRevCode() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getLstErrors()}.
//	 */
//	public void testGetLstErrors() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setLstErrors(java.util.List)}.
//	 */
//	public void testSetLstErrors() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getCargo()}.
//	 */
//	public void testGetCargo() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getPatientName()}.
	 */
	public void testGetPatientName() {
		// vacio si es nuevo
		assertTrue(StringUtils.isEmpty(new MEXMEActPacienteInd(Env.getCtx(), 0, null).getPatientName()));
		// random
		final MEXMEActPacienteIndH obj = TestUtils.getRandomPO(MEXMEActPacienteIndH.class, " EXME_CtaPac_ID>0 ", true);
		if (obj != null && obj.getLineas() != null) {
			final MEXMEActPacienteInd ind = obj.getLineas()[0];
			assertTrue(StringUtils.isNotEmpty(ind.getPatientName()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getSummaryDetail()}.
	 */
	public void testGetSummaryDetail() {
		// vacio si es nuevo
		MEXMEActPacienteInd obj = new MEXMEActPacienteInd(Env.getCtx(), 0, null);
		obj.setEstatus(null);
		assertTrue(StringUtils.isEmpty(obj.getSummaryDetail()));
		// random
		obj = TestUtils.getRandomPO(MEXMEActPacienteInd.class);
		if (obj != null) {
			assertTrue(StringUtils.isNotEmpty(obj.getSummaryDetail()));
		}
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#setAction(java.lang.String)}.
//	 */
//	public void testSetAction() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getRecordType()}.
	 */
	public void testGetRecordType() {
		// vacio si es nuevo
		assertTrue(StringUtils.isEmpty(new MEXMEActPacienteInd(Env.getCtx(), 0, null).getRecordType()));
		// random
		final MEXMEActPacienteInd obj = TestUtils.getRandomPO(MEXMEActPacienteInd.class);
		if (obj != null) {
			if (obj.getM_Product_ID() > 0) {
				assertTrue(StringUtils.isNotEmpty(obj.getRecordType()));
			} else {
				assertTrue(StringUtils.isEmpty(obj.getRecordType()));
			}
		}
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getProductName()}.
	 */
	public void testGetProductName() {
		// vacio si es nuevo
		assertTrue(StringUtils.isEmpty(new MEXMEActPacienteInd(Env.getCtx(), 0, null).getProductName()));
		// random
		final MEXMEActPacienteInd obj = TestUtils.getRandomPO(MEXMEActPacienteInd.class);
		if (obj != null) {
			if (obj.getM_Product_ID() > 0) {
				assertTrue(StringUtils.isNotEmpty(obj.getProductName()));
			} else {
				assertTrue(StringUtils.isEmpty(obj.getProductName()));
			}
		}
	}
	
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getEstatusName()}.
	 */
	public void testGetEstatusName() {
		// vacio si es nuevo
		MEXMEActPacienteInd obj = new MEXMEActPacienteInd(Env.getCtx(), 0, null);
		obj.setEstatus(null);
		assertTrue(StringUtils.isEmpty(obj.getEstatusName()));
		// random
		obj = TestUtils.getRandomPO(MEXMEActPacienteInd.class);
		if (obj != null) {
			if (StringUtils.isBlank(obj.getEstatus())) {
				assertTrue(StringUtils.isEmpty(obj.getEstatusName()));
			} else {
				assertTrue(StringUtils.isNotEmpty(obj.getEstatusName()));
			}
		}
	}
	
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getExternalBillable()}.
	 */
	public void testGetExternalBillable() {
		// vacio si es nuevo
		assertTrue(StringUtils.isEmpty(new MEXMEActPacienteInd(Env.getCtx(), 0, null).getExternalBillable()));
		// random
		final MEXMEActPacienteInd obj = TestUtils.getRandomPO(MEXMEActPacienteInd.class);
		if (obj != null) {
			if (obj.isSurtir()) {
				assertTrue(StringUtils.isEmpty(obj.getExternalBillable()));
			} else {
				assertTrue(StringUtils.isNotEmpty(obj.getExternalBillable()));
			}
		}
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getPrimaryPayer()}.
	 */
	public void testGetPrimaryPayer() {
		// vacio si es nuevo
		assertTrue(StringUtils.isEmpty(new MEXMEActPacienteInd(Env.getCtx(), 0, null).getPrimaryPayer()));
		// random
		final MEXMEActPacienteInd obj = TestUtils.getRandomPO(MEXMEActPacienteInd.class);
		if (obj != null) {
			if (obj.getActPacienteIndH().getEXME_CtaPac_ID() > 0) {
				assertTrue(StringUtils.isNotEmpty(obj.getPrimaryPayer()));
			} else {
				assertTrue(StringUtils.isEmpty(obj.getPrimaryPayer()));
			}
		}
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getEncounter()}.
	 */
	public void testGetEncounter() {
		// vacio si es nuevo
		assertTrue(StringUtils.isEmpty(new MEXMEActPacienteInd(Env.getCtx(), 0, null).getEncounter()));
		// random
		final MEXMEActPacienteInd obj = TestUtils.getRandomPO(MEXMEActPacienteInd.class);
		if (obj != null) {
			if (obj.getActPacienteIndH().getEXME_CtaPac_ID() > 0) {
				assertTrue(StringUtils.isNotEmpty(obj.getEncounter()));
			} else {
				assertTrue(StringUtils.isEmpty(obj.getEncounter()));
			}
		}
	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getMrn()}.
	 */
	public void testGetMrn() {
		// vacio si es nuevo
		assertTrue(StringUtils.isEmpty(new MEXMEActPacienteInd(Env.getCtx(), 0, null).getMrn()));
		// random
		final MEXMEActPacienteInd obj = TestUtils.getRandomPO(MEXMEActPacienteInd.class);
		if (obj != null) {
			if (obj.getActPacienteIndH().getEXME_CtaPac_ID() > 0) {
				assertTrue(StringUtils.isNotEmpty(obj.getMrn()));
			} else {
				assertTrue(StringUtils.isEmpty(obj.getMrn()));
			}
		}
	}
	/**
	 * Test method for {@link org.compiere.model.MEXMEActPacienteInd#getMedicoName()}.
	 */
	public void testGetMedicoName() {
		// vacio si es nuevo
		assertTrue(StringUtils.isEmpty(new MEXMEActPacienteInd(Env.getCtx(), 0, null).getMedicoName()));
		// random
		final MEXMEActPacienteInd obj = TestUtils.getRandomPO(MEXMEActPacienteInd.class);
		if (obj != null) {
			if (obj.getEXME_Medico_ID() > 0) {
				assertTrue(StringUtils.isNotEmpty(obj.getMedicoName()));
			} else {
				assertTrue(StringUtils.isEmpty(obj.getMedicoName()));
			}
		}
	}
	

}
