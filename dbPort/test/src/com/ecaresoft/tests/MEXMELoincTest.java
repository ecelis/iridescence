/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MEXMELoinc;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MProduct;
import org.compiere.model.X_EXME_LoincRange;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 *
 */
public class MEXMELoincTest extends TestCase {

	/*** @see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMELoinc#get(java.util.Properties, java.lang.String, java.lang.Integer[], int, java.lang.String)}.
	 */
	public void testGet() {
//		fail("Not yet implemented"); //TODO
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMELoinc#getAllInfo(java.util.Properties, java.lang.String)}.
//	 */
//	public void testGetAllInfo() {
////		fail("Not yet implemented");  //TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMELoinc#getMaxLevel()}.
	 */
	public void testGetMaxLevel() {
//		fail("Not yet implemented");  //TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMELoinc#setMaxLevel(double)}.
	 */
	public void testSetMaxLevel() {
//		fail("Not yet implemented");  //TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMELoinc#getMinLevel()}.
	 */
	public void testGetMinLevel() {
//		fail("Not yet implemented");  //TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMELoinc#setMinLevel(double)}.
	 */
	public void testSetMinLevel() {
//		fail("Not yet implemented"); //TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMELoinc#onCompare(int)}.
	 */
	public void testOnCompare() {
//		fail("Not yet implemented"); //TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMELoinc#getLoincFromCPT(java.util.Properties, int, java.lang.String)}.
	 */
	public void testGetLoincFromCPT() {
		// si no existen registros el metodo debe regresar vacio
		assertTrue(MEXMELoinc.getLoincFromCPT(Env.getCtx(), 0, null).isEmpty());
		// para un producto random
		MProduct prod = TestUtils.getRandomPO(MProduct.class, " EXME_Intervencion_ID>0 AND ProductClass='"+MProduct.PRODUCTCLASS_Laboratory+"' ", false);
		if(prod != null){
			assertNotNull(MEXMELoinc.getLoincFromCPT(Env.getCtx(), prod.getM_Product_ID(), null).isEmpty());
		}		
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMELoinc#getRange(org.compiere.model.MEXMEPaciente)}.
	 */
	public void testGetRange() {
		// obtener un rango loinc random
		X_EXME_LoincRange range = TestUtils.getRandomPO(X_EXME_LoincRange.class);
		MEXMELoinc loinc;
		MEXMEPaciente paciente;
		if(range == null){ // si no hay rangos
			loinc = TestUtils.getRandomPO(MEXMELoinc.class);
		} else {
			loinc = new MEXMELoinc(Env.getCtx(), range.getEXME_Loinc_ID(), null);
		}
		if(loinc != null){
			// si no se envia un paciente, no debe regresar ningun rango
			assertNull(loinc.getRange(null));
			paciente = TestUtils.getRandomPO(MEXMEPaciente.class);
			// si no existen rangos, no debe haber pacientes
			if(range==null){
				assertNull(loinc.getRange(paciente));
			} else
			// forzar true
			if ((range.getSexo() == null || range.getSexo().equals(paciente.getSexo())) && //
				(range.getEdadMinima() == null || range.getEdadMinima().doubleValue() <= paciente.getAge().getAnios()) && //
				(range.getEdadMaxima() == null || range.getEdadMaxima().doubleValue() >= paciente.getAge().getAnios())//
			) {
				assertNotNull(loinc.getRange(paciente));
			} else {
				assertNull(loinc.getRange(paciente));
			}
		}
	}

}
