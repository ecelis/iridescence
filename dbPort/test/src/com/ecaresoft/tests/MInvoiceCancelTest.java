package com.ecaresoft.tests;

import java.util.List;

import org.compiere.model.MCash;
import org.compiere.model.MInvoice;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;
import com.ecaresoft.util.ErrorList;

import junit.framework.TestCase;

public class MInvoiceCancelTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		TestUtils.setMonclova200("ecsmxProd14Jul");
	}

	/**
	 * Test method for {@link org.compiere.model.MInvoice#cancelInvoiceTest(MInvoice, MCash, String)}.
	 */
	@Test
	public final void testCancelInvoiceTest() {
		final ErrorList errorList = new ErrorList();
		boolean success = false;
		// Buscar un diario de caja abierto
		final MCash mCash = TestUtils.getRandomPO(MCash.class);
		// Buscar todas las facturas relacionadas
		final Trx trx = Trx.get(Trx.createTrxName("CF"), true);
		mCash.set_TrxName(trx.getTrxName());
		final List<MInvoice> lstInvoice = mCash.getFacturas(Env.getCtx(), trx.getTrxName());
		
		// Iterar cada factura para cancelarlas
		for (MInvoice mInvoice_o_Remision: lstInvoice) {
			
			System.out.println("INVOICE ID : "+mInvoice_o_Remision.getC_Invoice_ID());
			
			
			if(mInvoice_o_Remision.getRef_Invoice_Sales_ID()<=0){
				
				final MInvoice mInvoice = new MInvoice(Env.getCtx(), 0, trx.getTrxName());
				// TODO Falta generación de timbre
				success = mInvoice.processingPostmark(errorList, mCash);
				
				if(mInvoice!=null){
					mInvoice_o_Remision = mInvoice;
				}
			}
			
			if(success){
				success = MInvoice.cancelInvoiceTest(mInvoice_o_Remision, mCash,   trx.getTrxName());
			}
			
			if(!success){
				break;
			}
		}
			
		if(success){
		//	Trx.commit(trx, true); //cierre
		} else {
			Trx.rollback(trx, true);
		}
		
		assertTrue(success);
	}
}
