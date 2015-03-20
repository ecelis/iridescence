package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.apache.struts.util.LabelValueBean;
import org.compiere.model.MEXMEOrderSet;
import org.compiere.model.MEXMEOrderSetDiag;
import org.compiere.model.MEXMEOrderSetProd;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.PO;
import org.compiere.model.X_EXME_OrderSet;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMEOrderSetTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link MEXMEOrderSet#getOrderSets(java.util.Properties, String, int, String, String, boolean, int)}
	 */
	@Test
	public void testGetOrderSets() {
		assertNotNull(MEXMEOrderSet.getOrderSets(Env.getCtx(), null, 0, null, null, true, 0));

		/**
		 * Verificar si el filtro por Organizacion funciona
		 */
		MEXMEOrderSet exmeOrderSet = TestUtils.getRandomPO(MEXMEOrderSet.class, 
				MEXMEOrderSet.COLUMNNAME_EXME_Medico_ID + ">0 AND " 
						+ MEXMEOrderSet.COLUMNNAME_AD_Org_ID + " = 0 AND "
						+ PO.COLUMN_AD_Client_ID+ " = 0", false);
		if(exmeOrderSet != null){
			//Si se aplica el filtro por org la lista debe de estar vacia
			List<LabelValueBean> orders = MEXMEOrderSet.getOrderSets(
					Env.getCtx(), MEXMEOrderSet.ORDERTYPE_MedicalAppointment, 0, X_EXME_OrderSet.TIPOAREA_Ambulatory, "Y", true, exmeOrderSet.getEXME_Medico_ID());
			assertEquals(true,orders.isEmpty());
			//Si se no aplica el filtro por org la lista NO debe de estar vacia
			orders = MEXMEOrderSet.getOrderSets(
					Env.getCtx(), MEXMEOrderSet.ORDERTYPE_MedicalAppointment, 0, X_EXME_OrderSet.TIPOAREA_Ambulatory, "Y", false, exmeOrderSet.getEXME_Medico_ID());
			assertEquals(false,orders.isEmpty());
		}
		
		/**
		 * Verificar que regrese resultados
		 */
		exmeOrderSet = TestUtils.getRandomPO(MEXMEOrderSet.class, MEXMEOrderSet.COLUMNNAME_EXME_Medico_ID + ">0", true);
		if(exmeOrderSet != null){
			List<LabelValueBean> orders = MEXMEOrderSet.getOrderSets(
					Env.getCtx(), MEXMEOrderSet.ORDERTYPE_MedicalAppointment, 0, X_EXME_OrderSet.TIPOAREA_Ambulatory, "Y", true, exmeOrderSet.getEXME_Medico_ID());
			assertEquals(false,orders.isEmpty());
			orders = MEXMEOrderSet.getOrderSets(
					Env.getCtx(), MEXMEOrderSet.ORDERTYPE_MedicalAppointment, 0, X_EXME_OrderSet.TIPOAREA_Ambulatory, "Y", false, exmeOrderSet.getEXME_Medico_ID());
			assertEquals(false,orders.isEmpty());
		}
		
		
		
	}
	/**
	 * Pruebas basicas para evitar sacar NullPointerExceptions en metodos usados en OrderSetChooser
	 * para {@link OrderSetChooser}
	 */
	@Test
	public void testOrderSetChooserCycle() {		
		int orderSetID = 0;
		assertEquals(0, MEXMEOrderSetProd.getRXNorm(Env.getCtx(), orderSetID, null).size());
		assertEquals(0, MEXMEOrderSetDiag.getDiagnosis(Env.getCtx(), orderSetID).size());
		assertEquals(0, MEXMEOrderSetProd.getProducts(Env.getCtx(), orderSetID).size());
		assertEquals(0, MEXMEOrderSetProd.getProcedures(Env.getCtx(), orderSetID).size());

		orderSetID = TestUtils.getRandomPO(MEXMEOrderSet.class).getEXME_OrderSet_ID();
		assertNotNull(MEXMEOrderSetProd.getRXNorm(Env.getCtx(), orderSetID, TestUtils.getRandomPO(MEXMEPaciente.class)));
		assertNotNull(MEXMEOrderSetDiag.getDiagnosis(Env.getCtx(), orderSetID));
		assertNotNull(MEXMEOrderSetProd.getProducts(Env.getCtx(), orderSetID));
		assertNotNull(MEXMEOrderSetProd.getProcedures(Env.getCtx(), orderSetID));
		
	}
}
