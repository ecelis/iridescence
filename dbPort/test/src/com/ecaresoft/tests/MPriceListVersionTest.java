/**
 * 
 */
package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.compiere.model.MDiscountSchema;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class MPriceListVersionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	/**
	 * Test method for {@link org.compiere.model.MPriceListVersion#getValidName()}.
	 */
	@Test
	public void testGetValidName() {
		
		MPriceListVersion version = TestUtils.getRandomPO(MPriceListVersion.class, null, true); //obtenemos un producto
		
		MPriceList lista = TestUtils.getRandomPO(MPriceList.class, null, true); //obtenemos una lista
		MDiscountSchema schema = TestUtils.getRandomPO(MDiscountSchema.class, null, true); //obtenemos un esquema
		MPriceListVersion versionNew = new MPriceListVersion(Env.getCtx(),0,null);
		versionNew.setM_PriceList_ID(lista.getM_PriceList_ID());
		versionNew.setValidFrom(new Timestamp(System.currentTimeMillis()));
		versionNew.setM_DiscountSchema_ID(schema.getM_DiscountSchema_ID());
		versionNew.setName(version.getName()); //ponemos el nombre repetido
		try {
			assertFalse(versionNew.getValidName()); //no es un nombre valido
		} catch (Exception e) {
			e.printStackTrace();
		}
		versionNew.setName("NOMBRE DE PRUEBA NO REPETIDO"); //cambiamos el nombre
		try {
			assertTrue(versionNew.getValidName()); //es un nombre valido
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Test method for {@link org.compiere.model.MPriceListVersion#save()}.
	 */
	@Test
	public void save(){
		MPriceListVersion version = TestUtils.getRandomPO(MPriceListVersion.class, null, true); //obtenemos un producto
		
		Trx trx = null;
		trx = Trx.get(Trx.createTrxName("Version"), true);//creamos una transaccion
		
		MPriceList lista = TestUtils.getRandomPO(MPriceList.class, null, true); //obtenemos una lista
		MDiscountSchema schema = TestUtils.getRandomPO(MDiscountSchema.class, null, true); //obtenemos un esquema
		MPriceListVersion versionNew = new MPriceListVersion(Env.getCtx(),0,null);
		versionNew.setM_PriceList_ID(lista.getM_PriceList_ID());
		versionNew.setValidFrom(new Timestamp(System.currentTimeMillis()));
		versionNew.setM_DiscountSchema_ID(schema.getM_DiscountSchema_ID());
		versionNew.setName(version.getName()); //ponemos el nombre repetido
	
		assertFalse(versionNew.save(trx.getTrxName())); //no debe guardar la version
		
		versionNew.setName("NOMBRE DE PRUEBA NO REPETIDO"); //cambiamos el nombre
		assertTrue(versionNew.save(trx.getTrxName())); // si debe guardar la version
		
		trx.rollback();
	}

}
