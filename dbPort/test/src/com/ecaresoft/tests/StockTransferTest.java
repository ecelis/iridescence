/**
 * 
 */
package com.ecaresoft.tests;


import java.util.List;

import junit.framework.TestCase;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.MEXMEPrescRXDet;
import org.compiere.model.PharmacistModel;
import org.compiere.model.bpm.StockTransfer;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author twry
 *
 */
public class StockTransferTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.bpm.StockTransfer#prescRXDetTransfer(java.util.Properties, org.compiere.model.MEXMEPrescRXDet)}.
	 */
	@Test
	public void testPrescRXDetTransfer() {
		// 
		final PharmacistModel mPharmacistModel = PharmacistModel.getTabMeds(Env.getCtx(), true, false, false, false);
		assertNotNull(mPharmacistModel);
		
		// 
		final List<MEXMEPrescRXDet> list = mPharmacistModel.getQuery().list();
		assertNotNull(list);
		assertFalse(list.isEmpty());
		
		//
		for (int i = 0; i < list.size(); i++) {
			if(!list.get(i).isDelivered()
					&& list.get(i).getQtyAvailable().compareTo(Env.ZERO)>0){// Cantidad disponible por generico
				try{
					assertTrue(StockTransfer.prescRXDetTransfer(Env.getCtx(), list.get(i)));
				}catch(MedsysException a){
					assertTrue(true);//Invenatario vacio
				}
			}
		}
	}
}