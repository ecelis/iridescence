package com.ecaresoft.tests;

import java.math.BigDecimal;
import java.util.Properties;

import junit.framework.TestCase;

import org.compiere.model.MEXMEUOMConversion;
import org.compiere.model.MProduct;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMEUOMConversionTest extends TestCase{

	private Properties ctx = Env.getCtx();
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	/**
	 * Metodo que prueba que exista una conversion de un producto
	 */
	@Test
	public void testValidateConversions() {
		//No deberia existir una conversion de la misma unidad de medida
		MProduct product = TestUtils.getRandomPO(MProduct.class,
				"c_uom_id in(SELECT c_uom_id FROM c_uom_conversion )", true);
		MUOMConversion rates = MEXMEUOMConversion.validateConversions(ctx,  product.getM_Product_ID(), product.getC_UOM_ID(), null);
		assertNull(rates);
		//Preguntamos por la conversion del producto
		product = TestUtils.getRandomPO(MProduct.class,
				"c_uomvolume_id <> c_uom_id", true);
		int uomVProd = product.getC_UOMVolume_ID();
//		MUOM uom = TestUtils.getRandomPO(MUOM.class,
//				"c_uom_id in(SELECT c_uom_id FROM c_uom_conversion WHERE c_uom_to_id = " + uomVProd + " )", true);
		rates = MEXMEUOMConversion.validateConversions(ctx,  product.getM_Product_ID(), uomVProd, null);
		assertNotNull(rates);
	}
	/**
	 * Metodo que prueba que las conversiones sean correctas
	 */
	@Test
	public void testconvertProductToFrom() {
		//
		BigDecimal qty = new BigDecimal(Math.random());
		MProduct product = TestUtils.getRandomPO(MProduct.class,
				"c_uom_id in(SELECT c_uom_id FROM c_uom_conversion ) and c_uomvolume_id <> c_uom_id", true);
		int uomVProd = product.getC_UOMVolume_ID();
		MUOM uom = TestUtils.getRandomPO(MUOM.class,
				"c_uom_id in(SELECT c_uom_id FROM c_uom_conversion WHERE c_uom_to_id = " + uomVProd + " )", false);
		
		//Buscamos la conversion existente
		MUOMConversion conv = TestUtils.getRandomPO(MUOMConversion.class,
				"c_uom_id = " + uom.getC_UOM_ID() + " and c_uom_to_id = " + uomVProd , false);
		
		//convertProductFrom es empaque a minima 
		BigDecimal value = MEXMEUOMConversion.convertProductFrom(ctx, product.getM_Product_ID(), uomVProd, qty, null, false);
		assertEquals(qty.divide(conv.getMultiplyRate()).setScale(0,BigDecimal.ROUND_HALF_UP), value.setScale(0,BigDecimal.ROUND_DOWN));
		
		//convertProductTo es minima a empaque
		value = MEXMEUOMConversion.convertProductTo(ctx, product.getM_Product_ID(), uomVProd, qty, null, false);
		assertEquals(qty.multiply(conv.getMultiplyRate()).setScale(0,BigDecimal.ROUND_DOWN), value.setScale(0,BigDecimal.ROUND_DOWN));
		
	}
}
