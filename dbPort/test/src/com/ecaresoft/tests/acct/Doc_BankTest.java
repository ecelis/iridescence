/**
 *
 */
package com.ecaresoft.tests.acct;


import java.math.BigDecimal;

import org.compiere.acct.DocTax;
import org.compiere.acct.Doc_Bank;
import org.compiere.model.MPayment;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * @author mvrodriguez
 *
 */
public class Doc_BankTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.acct.Doc_Bank#loadTaxes(int, int, java.math.BigDecimal, java.lang.String)}.
	 */
	@Test
	public final void testLoadTaxes() {

		int C_Payment_ID = 1000442;//1000448;
		MPayment payment = new MPayment(Env.getCtx(), C_Payment_ID, null);
		int C_Invoice_ID = payment.getC_Invoice_ID();
		BigDecimal amount = payment.getPayAmt();

//		if(C_Invoice_ID>0){
//			// Lineas de impuesto de factura(s) relacionada(s) al pago
//			DocTax[] m_taxes = Doc_Bank.loadTaxes(C_Invoice_ID,
//					C_Payment_ID, amount, null);
//
//			assertTrue(m_taxes!=null && m_taxes.length>0);
//		} else {
			//	assertTrue("Falta el caso cuando no hay factura sino facturas", C_Invoice_ID<=0);
			C_Payment_ID = 1000448;
			payment = new MPayment(Env.getCtx(), C_Payment_ID, null);
			C_Invoice_ID = payment.getC_Invoice_ID();
			amount = payment.getPayAmt();

			// Lineas de impuesto de factura(s) relacionada(s) al pago
//			DocTax[] m_taxes = Doc_Bank.loadTaxes(Env.getCtx(), C_Invoice_ID,
//					C_Payment_ID, amount, null);

			//assertTrue(m_taxes!=null && m_taxes.length>0);
//		}
	}
}
