package com.ecaresoft.tests;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.MEXMEUOMConversion;
import org.compiere.model.MProduct;
import org.compiere.model.MUOMConversion;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * Casos de prueba enviados por Carlos Guzman en el ticket 4928 de México. Reportó
 * que se estaba modificando el total de la línea de la orden de compra al momento
 * de guardar el registro, esto era debido a como se manejaba el proceso previo
 * al cálculo del monto ya que el producto que utilizó tenía configuradas dos unidades
 * de medida. En esta batería contiene el casos para un producto que maneja
 * Pieza y caja con 10 y otro que solo es pieza. Al convertir el precio del empaque
 * a precio por pieza y viceversa debe quedar el original.
 * @author mrojas
 *
 */
public class TicketMx4928 extends TestCase {

	
	private Properties ctx = Env.getCtx();

	BigDecimal priceUnit = BigDecimal.ONE;
	// this product has a conversion from Piece to Box with 10
	MProduct product = null;
	BigDecimal priceEntered = null;
	BigDecimal units = null;
	BigDecimal amount = null;

	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	
	@Test
	public void testCase1() {
		setValues(1474327);
		priceEntered = new BigDecimal("13525.00");
		calculate();
		assertEquals(amount.setScale(2), priceEntered.setScale(2));
	}

	@Test
	public void testCase2() {
		setValues(1474327);
		priceEntered = new BigDecimal("13317.43");
		calculate();
		assertEquals(amount.setScale(2), priceEntered.setScale(2));
	}
	
	@Test
	public void testCase3() {
		setValues(1474327);
		priceEntered = new BigDecimal("13317.00");
		calculate();
		assertEquals(amount.setScale(2), priceEntered.setScale(2));
	}
	
	@Test
	public void testCase4() {
		setValues(1474327);
		priceEntered = new BigDecimal("25.50");
		calculate();
		assertEquals(amount.setScale(2), priceEntered.setScale(2));
	}
	
	@Test
	public void testCase5() {
		setValues(1474327);
		priceEntered = new BigDecimal("25.34");
		calculate();
		assertEquals(amount.setScale(2), priceEntered.setScale(2));
	}
	
	@Test
	public void testCase6() {
		setValues(1474327);
		priceEntered = new BigDecimal("25.34");
		calculate();
		assertEquals(amount.setScale(2), priceEntered.setScale(2));
	}
	
	@Test
	public void testCase7() {
		setValues(1474327);
		priceEntered = new BigDecimal("2564.34");
		calculate();
		assertEquals(amount.setScale(2), priceEntered.setScale(2));
	}
	
	@Test
	public void testCase8() {
		setValues(1474327);
		priceEntered = new BigDecimal("2220.00");
		calculate();
		assertEquals(amount.setScale(2), priceEntered.setScale(2));
	}
	
	@Test
	public void testCase9() {
		setValues(1474327);
		priceEntered = new BigDecimal("2220.45");
		calculate();
		assertEquals(amount.setScale(2), priceEntered.setScale(2));
	}
	
	@Test
	public void testCase10() {
		setValues(1474327);
		priceEntered = new BigDecimal("22.00");
		calculate();
		assertEquals(amount.setScale(2), priceEntered.setScale(2));
	}
	
	
	@Test
	public void testCase11() {
		setValues(1230562);
		priceEntered = new BigDecimal("247.10");
		calculate();
		assertEquals(amount.setScale(2), priceEntered.setScale(2));
	}
	
	@Test
	public void testCase12() {
		setValues(1230562);
		priceEntered = new BigDecimal("15.00");
		calculate();
		assertEquals(amount.setScale(2), priceEntered.setScale(2));
	}
	
	
	
	/**
	 * Sets product id and units to be used for price.
	 * @param mProductId The producto to use for calculations.
	 */
	private void setValues(int mProductId) {
		product = new MProduct(Env.getCtx(), mProductId, null);
		units = 
				MUOMConversion.convert(
						product.getC_UOMVolume_ID(), 
						product.getC_UOM_ID(), 
						BigDecimal.ONE, 
						true
				);
	}
	
	/**
	 * Do the calculations
	 */
	private void calculate() {
		priceUnit = 
				MEXMEUOMConversion.convertProductTo(
					ctx, 
					product.getM_Product_ID(), 
					product.getC_UOMVolume_ID(), 
					priceEntered, 
					null, 
					false
				);
		
		amount = priceUnit.multiply(units);
	}
}
