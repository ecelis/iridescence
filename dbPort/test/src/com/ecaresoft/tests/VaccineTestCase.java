package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.compiere.model.MDiagnostico;
import org.compiere.model.MEXMEVacuna;
import org.compiere.model.MEXMEVacunaDet;
import org.compiere.model.MEXMEVacunaProduct;
import org.compiere.model.MEXMEViasAdministracion;
import org.compiere.model.MProduct;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class VaccineTestCase {

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}
	
	@Test
	public void saveVaccine(){
		//Header
		MProduct product = TestUtils.getRandomPO(MProduct.class, null, true); 
		MEXMEVacuna vacuna = TestUtils.getRandomPO(MEXMEVacuna.class, null, true);
		MEXMEViasAdministracion viaAdmon = TestUtils.getRandomPO(MEXMEViasAdministracion.class, null, true);
		MDiagnostico diag = TestUtils.getRandomPO(MDiagnostico.class, null, true);

		Trx trx = null;
		trx = Trx.get(Trx.createTrxName("vaccine"), true);
		String trxName =  trx.getTrxName();

		MEXMEVacuna vaccineHdr = new MEXMEVacuna(Env.getCtx(), 0, trxName);

		vaccineHdr.setName("testVaccine");
		vaccineHdr.setValue("testVaccine");
		vaccineHdr.setDescription("testVaccine");
		vaccineHdr.setRel_Vacuna_ID(vacuna.getEXME_Vacuna_ID());
		vaccineHdr.setEXME_ViasAdministracion_ID(viaAdmon.getEXME_ViasAdministracion_ID());
		vaccineHdr.setSexo("M");
		vaccineHdr.setCantidad(new BigDecimal(1.1));
		vaccineHdr.setVIS_Date(new Timestamp(new Date().getTime()));
		vaccineHdr.setVia("Oral");
		vaccineHdr.setIncluyeCartilla(true);
		vaccineHdr.setC_UOM_ID(product.getC_UOM_ID());
		vaccineHdr.setM_Product_ID(product.getM_Product_ID());
		vaccineHdr.setEXME_Diagnostico_ID(diag.getEXME_Diagnostico_ID());		

		assertTrue(vaccineHdr.save(trxName));

		MEXMEVacunaProduct vProduct = new MEXMEVacunaProduct(Env.getCtx(), 0, trxName);
		MProduct product2 = TestUtils.getRandomPO(MProduct.class, null, true); 

		vProduct.setM_Product_ID(product2.getM_Product_ID());
		vProduct.setC_UOM_ID(product2.getC_UOM_ID());
		vProduct.setEXME_Vacuna_ID(vaccineHdr.getEXME_Vacuna_ID());

		assertTrue(vProduct.save(trxName));

		MEXMEVacunaDet vDet = new MEXMEVacunaDet(Env.getCtx(), 0, trxName);

		vDet.setEXME_Vacuna_ID(vaccineHdr.getEXME_Vacuna_ID());
		vDet.setSecuencia(1);
		vDet.setTipoDosis(MEXMEVacunaDet.TIPODOSIS_Preliminary);
		vDet.setEdadMinima(BigDecimal.ZERO);
		vDet.setEdadMaxima(BigDecimal.TEN);
		vDet.setIntervalo(BigDecimal.ONE);  	
		vDet.setVaccineType("IM");
		vDet.setC_UOM_ID(product2.getC_UOM_ID());

		assertTrue(vDet.save(trxName));

		trx.rollback();
		trx.close();
	}

}
