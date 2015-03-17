package com.ecaresoft.tests;


import org.compiere.model.MInvoice;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

public class MInvoiceTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		TestUtils.setEcare200("ecsmxVer3R2");
	}

	/**
	 * Test method for {@link org.compiere.model.MInvoice#getConversionRate(MInvoice)}.
	 */
	@Test
	public final void testGetConversionRate() {
		// Factura Pesos Mexicamos
		final MInvoice mInvoiceMX = TestUtils.getRandomPO(MInvoice.class
				, "  C_Currency_ID = 130 AND C_Invoice_ID IN (SELECT C_Invoice_ID from C_Invoice_Customer_V)"
				, true);
		final String rateMX = MInvoice.getConversionRate(mInvoiceMX);//MInvoice[10129142-425,GrandTotal=52840.32]

		// Factura Pesos Dolares
		final MInvoice mInvoiceUSD = TestUtils.getRandomPO(MInvoice.class
				, "  C_Currency_ID = 100 AND C_Invoice_ID IN (SELECT C_Invoice_ID from C_Invoice_Customer_V)"
				, true);
		final String rateUSD = MInvoice.getConversionRate(mInvoiceUSD);//MInvoice[10133286-426,GrandTotal=5800.00]

		// Nota de credito Dolares ó Refacturación
		final MInvoice mInvoiceNC = TestUtils.getRandomPO(MInvoice.class
				, "  C_Currency_ID = 100 AND Ref_Invoice_ID > 0 "
				, true);
		final String rateUSA_NC = MInvoice.getConversionRate(mInvoiceNC);//MInvoice[10132366-100007,GrandTotal=152.54]

		
		// Prueba
		final MInvoice mInvoice = new MInvoice(Env.getCtx(), 10133286, null);
		final String rate = MInvoice.getConversionRate(mInvoice);
		
		
		assertTrue(rateMX.isEmpty() && rateUSD.isEmpty() && rateUSA_NC.isEmpty() && rate.isEmpty());

	}
	
	/**
	 * Test method for {@link org.compiere.model.MInvoice#getConversionRate(MInvoice)}.
	 */
	@Test
	public final void testGetConversionRate1() {
		// Factura Pesos Mexicamos
		final MInvoice mInvoiceMX = TestUtils.getRandomPO(MInvoice.class
				, "  C_Currency_ID = 130 AND C_Invoice_ID IN (SELECT C_Invoice_ID from C_Invoice_Customer_V)"
				, true);
		final String rateMX = MInvoice.getConversionRate(mInvoiceMX);//MInvoice[10129142-425,GrandTotal=52840.32]
		assertTrue(rateMX.isEmpty() && rateMX.equals("1"));
	}
	
	
	/**
	 * Test method for {@link org.compiere.model.MInvoice#getConversionRate(MInvoice)}.
	 */
	@Test
	public final void testGetConversionRate2() {
		// Factura Pesos Dolares
		final MInvoice mInvoiceUSD = TestUtils.getRandomPO(MInvoice.class
				, "  C_Currency_ID = 100 AND C_Invoice_ID IN (SELECT C_Invoice_ID from C_Invoice_Customer_V)"
				, true);
		final String rateUSD = MInvoice.getConversionRate(mInvoiceUSD);//MInvoice[10133286-426,GrandTotal=5800.00]
		assertTrue(rateUSD.isEmpty() && !rateUSD.equals("1"));
	}
	
	/**
	 * Test method for {@link org.compiere.model.MInvoice#getConversionRate(MInvoice)}.
	 */
	@Test
	public final void testGetConversionRate3() {
		final MInvoice mInvoiceNC = TestUtils.getRandomPO(MInvoice.class
				, "  C_Currency_ID = 100 AND Ref_Invoice_ID > 0 "
				, true);
		final String rateUSA_NC = MInvoice.getConversionRate(mInvoiceNC);//MInvoice[10132366-100007,GrandTotal=152.54]
		assertTrue(rateUSA_NC.isEmpty() && !rateUSA_NC.equals("1"));

	}

	/**
	 * Test method for {@link org.compiere.model.MInvoice#getConversionRate(MInvoice)}.
	 */
	@Test
	public final void testGetConversionRate4() {
		// Prueba
		final MInvoice mInvoice = new MInvoice(Env.getCtx(), 10133286, null);
		final String rate = MInvoice.getConversionRate(mInvoice);
		assertTrue(rate.isEmpty() && rate.equals("12.9642"));

	}
}
