/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MBPartner;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 *
 */
public class MBPartnerTest extends TestCase {


	/**@see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#toString()}.
//	 */
//	public void testToString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setClientOrg(int, int)}.
//	 */
//	public void testSetClientOrgIntInt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#beforeSave(boolean)}.
//	 */
//	public void testBeforeSave() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#afterSave(boolean, boolean)}.
//	 */
//	public void testAfterSave() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#beforeDelete()}.
//	 */
//	public void testBeforeDelete() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#afterDelete(boolean)}.
//	 */
//	public void testAfterDelete() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getAcqusitionCost()}.
//	 */
//	public void testGetAcqusitionCost() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setActualLifeTimeValue(java.math.BigDecimal)}.
//	 */
//	public void testSetActualLifeTimeValueBigDecimal() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getActualLifeTimeValue()}.
//	 */
//	public void testGetActualLifeTimeValue() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getC_Dunning_ID()}.
//	 */
//	public void testGetC_Dunning_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getC_InvoiceSchedule_ID()}.
//	 */
//	public void testGetC_InvoiceSchedule_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getC_PaymentTerm_ID()}.
//	 */
//	public void testGetC_PaymentTerm_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getDeliveryRule()}.
//	 */
//	public void testGetDeliveryRule() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getDeliveryViaRule()}.
//	 */
//	public void testGetDeliveryViaRule() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getFlatDiscount()}.
//	 */
//	public void testGetFlatDiscount() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getInvoiceRule()}.
//	 */
//	public void testGetInvoiceRule() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getInvoice_PrintFormat_ID()}.
//	 */
//	public void testGetInvoice_PrintFormat_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#isDiscountPrinted()}.
//	 */
//	public void testIsDiscountPrinted() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getM_DiscountSchema_ID()}.
//	 */
//	public void testGetM_DiscountSchema_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getM_PriceList_ID()}.
//	 */
//	public void testGetM_PriceList_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getPOReference()}.
//	 */
//	public void testGetPOReference() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getPO_DiscountSchema_ID()}.
//	 */
//	public void testGetPO_DiscountSchema_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getPO_PriceList_ID()}.
//	 */
//	public void testGetPO_PriceList_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getPaymentRule()}.
//	 */
//	public void testGetPaymentRule() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getPotentialLifeTimeValue()}.
//	 */
//	public void testGetPotentialLifeTimeValue() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setSOCreditStatus(java.lang.String)}.
//	 */
//	public void testSetSOCreditStatusString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getSOCreditStatus()}.
//	 */
//	public void testGetSOCreditStatus() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getSO_CreditLimit()}.
//	 */
//	public void testGetSO_CreditLimit() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setSO_CreditUsed(java.math.BigDecimal)}.
//	 */
//	public void testSetSO_CreditUsed() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getSO_CreditUsed()}.
//	 */
//	public void testGetSO_CreditUsed() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getSO_Description()}.
//	 */
//	public void testGetSO_Description() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getSalesRep_ID()}.
//	 */
//	public void testGetSalesRep_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getShareOfCustomer()}.
//	 */
//	public void testGetShareOfCustomer() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getShelfLifeMinPct()}.
//	 */
//	public void testGetShelfLifeMinPct() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setTotalOpenBalance(java.math.BigDecimal)}.
//	 */
//	public void testSetTotalOpenBalanceBigDecimal() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getTotalOpenBalance()}.
//	 */
//	public void testGetTotalOpenBalance() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getTemplate(java.util.Properties, int)}.
//	 */
//	public void testGetTemplate() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getBPartnerCashTrx(java.util.Properties, int)}.
//	 */
//	public void testGetBPartnerCashTrx() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#get(java.util.Properties, java.lang.String, java.lang.String)}.
//	 */
//	public void testGetPropertiesStringString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#get(java.util.Properties, java.lang.String)}.
//	 */
//	public void testGetPropertiesString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#get(java.util.Properties, int)}.
//	 */
//	public void testGetPropertiesInt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getForClaim(java.util.Properties, java.lang.String)}.
//	 */
//	public void testGetForClaim() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getForClaimByName(java.util.Properties, java.lang.String)}.
//	 */
//	public void testGetForClaimByName() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getNotInvoicedAmt(int)}.
//	 */
//	public void testGetNotInvoicedAmt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#MBPartner(java.util.Properties)}.
//	 */
//	public void testMBPartnerProperties() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#MBPartner(org.compiere.model.X_I_BPartner)}.
//	 */
//	public void testMBPartnerX_I_BPartner() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getContacts(boolean)}.
//	 */
//	public void testGetContacts(boolean param) {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getContact(int)}.
//	 */
//	public void testGetContact(int param) {
//		fail("Not yet implemented"); // TODO
//	}
	
	/**
	 * Test method for {@link org.compiere.model.MBPartner#getContacts(boolean)}.
	 */
	public void testGetContacts() {
		// vacio si es nuevo
		MBPartner nuevo = new MBPartner(Env.getCtx(), 0, null);
		assertTrue(nuevo.getContacts().size() == 0);
		// random
		final MBPartner obj = TestUtils.getRandomPO(MBPartner.class);
		if (obj != null) {
			assertTrue(obj.getContacts().size() >= 0); // no debe regresa null, debe regresa una lista vacia
		}
	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getLocations(boolean)}.
//	 */
//	public void testGetLocations() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getLocation(int)}.
//	 */
//	public void testGetLocation() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getBankAccounts(boolean)}.
//	 */
//	public void testGetBankAccounts() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setAD_OrgBP_ID(int)}.
//	 */
//	public void testSetAD_OrgBP_IDInt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getAD_OrgBP_ID_Int()}.
//	 */
//	public void testGetAD_OrgBP_ID_Int() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getPrimaryC_BPartner_Location_ID()}.
//	 */
//	public void testGetPrimaryC_BPartner_Location_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getPrimaryAD_User_ID()}.
//	 */
//	public void testGetPrimaryAD_User_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setPrimaryC_BPartner_Location_ID(int)}.
//	 */
//	public void testSetPrimaryC_BPartner_Location_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setPrimaryAD_User_ID(int)}.
//	 */
//	public void testSetPrimaryAD_User_ID() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setTotalOpenBalance()}.
//	 */
//	public void testSetTotalOpenBalance() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setActualLifeTimeValue()}.
//	 */
//	public void testSetActualLifeTimeValue() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getTotalOpenBalance(boolean)}.
//	 */
//	public void testGetTotalOpenBalanceBoolean() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setSOCreditStatus()}.
//	 */
//	public void testSetSOCreditStatus() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getSOCreditStatus(java.math.BigDecimal)}.
//	 */
//	public void testGetSOCreditStatusBigDecimal() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getCreditWatchRatio()}.
//	 */
//	public void testGetCreditWatchRatio() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#isCreditStopHold()}.
//	 */
//	public void testIsCreditStopHold() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getBPGroup()}.
//	 */
//	public void testGetBPGroup() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setBPGroup(org.compiere.model.MBPGroup)}.
//	 */
//	public void testSetBPGroup() {
//		fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getBillBPartner(int)}.
//	 */
//	public void testGetBillBPartner() {
//		fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getBillLocations(int)}.
//	 */
//	public void testGetBillLocations() {
//		fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#isCopy()}.
//	 */
//	public void testIsCopy() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setCopy(boolean)}.
//	 */
//	public void testSetCopy() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getId()}.
//	 */
//	public void testGetId() {
//		fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getListBPartnerDebit(java.util.Properties)}.
//	 */
//	public void testGetListBPartnerDebit() {
//		fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getCBPartnerList()}.
//	 */
//	public void testGetCBPartnerList() {
//		fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getCBPartnerList(java.util.Properties)}.
//	 */
//	public void testGetCBPartnerListProperties() {
//		fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getDefault(java.util.Properties, java.lang.String)}.
//	 */
//	public void testGetDefault() {
//		fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getAll(java.util.Properties, boolean, java.lang.String)}.
//	 */
//	public void testGetAll() {
//		fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#createBPGroupCte()}.
//	 */
//	public void testCreateBPGroupCte() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#setBPartnerCte()}.
//	 */
//	public void testSetBPartnerCte() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getPartnerCte(boolean)}.
//	 */
//	public void testGetPartnerCte() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#isMiscellaneous(java.util.Properties, int)}.
//	 */
//	public void testIsMiscellaneousPropertiesInt() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#isMiscellaneous(java.util.Properties)}.
//	 */
//	public void testIsMiscellaneousProperties() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getCtaPacByBPartner(java.util.Properties, int, int, java.lang.String)}.
//	 */
//	public void testGetCtaPacByBPartner() {
//		fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getTaxIDSearch(java.util.Properties, java.lang.String, java.lang.String)}.
//	 */
//	public void testGetTaxIDSearch() {
//		fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#getPartnerTree(java.util.Properties, int, java.lang.String)}.
//	 */
//	public void testGetPartnerTree() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#insertAcctClientEmpl()}.
//	 */
//	public void testInsertAcctClientEmpl() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#insertAcctClientCust()}.
//	 */
//	public void testInsertAcctClientCust() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MBPartner#insertAcctClientVndr()}.
//	 */
//	public void testInsertAcctClientVndr() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MBPartner#getSupportBillingStr()}.
	 */
	public void testGetSupportBillingStr() {
		// vacio si es nuevo
		assertTrue(StringUtils.isEmpty(new MBPartner(Env.getCtx(), 0, null).getSupportBillingStr()));
		// random
		final MBPartner obj = TestUtils.getRandomPO(MBPartner.class);
		if (obj != null) {
			if (StringUtils.isNotBlank(obj.getSupportBilling())) {
				assertTrue(StringUtils.isNotEmpty(obj.getSupportBillingStr()));
			} else {
				assertTrue(StringUtils.isEmpty(obj.getSupportBillingStr()));
			}
		}
	}

}
