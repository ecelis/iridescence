/**
 *
 */
package com.ecaresoft.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MElementValue;
import org.compiere.model.MTree_Node;
import org.compiere.model.MValidCombination;
import org.compiere.model.Query;
import org.compiere.model.X_AD_TreeNode;
import org.compiere.model.X_C_AcctSchema_Default;
import org.compiere.model.X_C_AcctSchema_Element;
import org.compiere.model.X_C_BP_Customer_Acct;
import org.compiere.model.X_C_BankAccount_Acct;
import org.compiere.model.X_C_ElementValue;
import org.compiere.model.X_M_Product_Acct;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Clase de prueba de MElementValue
 *
 * @author rsolorzano, lama
 */
public class MElementValueTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Override
	@Before
	public void setUp() throws Exception {
		// TestUtils.setUp();
		TestUtils.setUp(10001056);// Lama: Hospitaria
	}

	// /**
	// * Test method for {@link org.compiere.model.MElementValue#afterDelete(boolean)}.
	// */
	// public void testAfterDelete() {}

	// /**
	// * Test method for {@link org.compiere.model.MElementValue#afterSave(boolean, boolean)}.
	// */
	// public void testAfterSave() {}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#beforeDelete()}.
	 */
	public void testBeforeDelete() {
		X_C_AcctSchema_Default sch2 = TestUtils.getRandomPO(X_C_AcctSchema_Default.class);
		if (sch2 != null) {
			Trx trx = null;
			try {// inactivate
				MValidCombination comb = new MValidCombination(Env.getCtx(), sch2.getB_Asset_Acct(), null);
				MElementValue elem = new MElementValue(Env.getCtx(), comb.getAccount_ID(), null);
				if (!elem.is_new()) {
					elem.setIsActive(false);
					trx = Trx.get(Trx.createTrxName("TstEV"), true);
					assertFalse(elem.save(trx.getTrxName()));
				}
			} catch (Exception e) {
				fail(e.getLocalizedMessage());
			} finally {
				Trx.rollback(trx, true);
			}
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#beforeSave(boolean)}.
	 */
	public void testBeforeSave() {
		MElementValue elem = TestUtils.getRandomPO(MElementValue.class, " Acct_Level<2 ", true);
		if (elem != null) {
			Trx trx = null;
			try {// duplicated key
				MElementValue elem2 = new MElementValue(Env.getCtx(), 0, null);
				MElementValue.copyValues(elem, elem2);
				trx = Trx.get(Trx.createTrxName("TstEV"), true);
				assertFalse(elem2.save(trx.getTrxName()));
			} catch (Exception e) {
				fail(e.getLocalizedMessage());
			} finally {
				Trx.rollback(trx, true);
			}
		}

		X_C_BankAccount_Acct sch2 = TestUtils.getRandomPO(X_C_BankAccount_Acct.class);
		if (sch2 != null) {
			Trx trx = null;
			try {// inactivate
				MValidCombination comb = new MValidCombination(Env.getCtx(), sch2.getB_Asset_Acct(), null);
				elem = new MElementValue(Env.getCtx(), comb.getAccount_ID(), null);
				if (!elem.is_new()) {
					elem.setIsActive(false);
					trx = Trx.get(Trx.createTrxName("TstEV"), true);
					assertFalse(elem.save(trx.getTrxName()));
				}
			} catch (Exception e) {
				fail(e.getLocalizedMessage());
			} finally {
				Trx.rollback(trx, true);
			}
		}

		elem = TestUtils.getRandomPO(MElementValue.class, " Acct_Level>1 ", true);
		if (elem != null) {//
			Trx trx = null;
			try {// contab elect
				elem.setEXME_GroupAcct_ID(0);
				elem.setDescription(elem.getDescription() + "hikl.");
				MAcctSchema sche = MAcctSchema.get(Env.getCtx(), Env.getC_AcctSchema_ID(Env.getCtx()));
				trx = Trx.get(Trx.createTrxName("TstEV"), true);
				assertEquals(elem.save(trx.getTrxName()), !sche.isElectronicAccounting());
			} catch (Exception e) {
				fail(e.getLocalizedMessage());
			} finally {
				Trx.rollback(trx, true);
			}
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#delete_Tree(java.lang.String)}.
	 */
	public void testDelete_Tree() {
		MElementValue elem = TestUtils.getRandomPO(MElementValue.class, " Acct_Level=? ", true, MElementValue.MAX_ACCT_LEVEL - 1);
		if (elem != null) {
			int treeId = elem.getC_Element().getAD_Tree_ID();
			if (treeId > 0) {
				Trx trx = null;
				try {
					MElementValue elem2 = new MElementValue(Env.getCtx(), 0, null);
					MElementValue.copyValues(elem, elem2);
					elem2.setParentElementValue_ID(elem.getC_ElementValue_ID());
					elem2.setValue(elem2.getNextChildAcct());
					elem2.setName("testDel" + System.currentTimeMillis());
					trx = Trx.get(Trx.createTrxName("TstEV"), true);

					assertTrue(elem2.save(trx.getTrxName()));
					// el arbol se inserta en el aftersave
					int nodeid = DB.getSQLValue(trx.getTrxName(), //
						" SELECT Node_ID FROM AD_TreeNode WHERE Node_ID=? AND AD_Tree_ID=? AND AD_Client_ID=? ", //
						elem2.get_ID(), treeId, elem2.getAD_Client_ID());
					if (nodeid > 0) {
						assertTrue(elem2.delete(false, trx.getTrxName()));
						// el arbol se elimina en el afterdelete
						X_AD_TreeNode node = new Query(Env.getCtx(), MTree_Node.Table_Name, //
							" Node_ID=? AND AD_Tree_ID=? AND AD_Client_ID=? ", trx.getTrxName())//
							.setParameters(elem2.get_ID(), treeId, elem2.getAD_Client_ID())//
							.firstOnly();

						assertNull(node);
					}

				} catch (Exception e) {
					fail(e.getLocalizedMessage());
				} finally {
					Trx.rollback(trx, true);
				}
			}
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#findLevelsAbove(int)}.
	 */
	public void testFindLevelsAbove() {
		MElementValue elem = new MElementValue(Env.getCtx(), 0, null);
		assertEquals(MElementValue.findLevelsAbove(elem.get_ID(), null), 1);
		elem = TestUtils.getRandomPO(MElementValue.class, " ParentElementValue_ID > 0 AND Acct_Level>1 ", true);
		if (elem != null) {
			assertEquals(MElementValue.findLevelsAbove(elem.get_ID(), null), elem.getAcct_Level());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#getAccounts(java.util.Properties, java.lang.StringBuilder, java.util.List)}.
	 */
	public void testGetAccounts() {

		List<MElementValue> list = MElementValue.getAccounts(Env.getCtx(), null, null);

		assertNotNull(list);

		final MElementValue elem = TestUtils.getRandomPO(MElementValue.class);
		if (elem == null) {
			assertTrue(list.isEmpty());
		} else {
			assertFalse(list.isEmpty());

			final MElementValue elemv = list.get(0);

			final StringBuilder sql = new StringBuilder();
			sql.append(" AND C_ElementValue_ID=? ");

			final List<Object> params = new ArrayList<>();
			params.add(elemv.getC_ElementValue_ID());

			list = MElementValue.getAccounts(Env.getCtx(), sql, params);
			assertNotNull(list);
			assertTrue(list.size() == 1);
			assertEquals(list.get(0).get_ID(), elemv.get_ID());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#getAccountSignName()}.
	 */
	public void testGetAccountSignName() {
		MElementValue elem = new MElementValue(Env.getCtx(), 0, null);
		elem.set_ValueOfColumn(MElementValue.COLUMNNAME_AccountSign, "");
		// cuando es null, debe regresar vacio
		assertTrue(StringUtils.isEmpty(elem.getAccountSignName()));
		elem = TestUtils.getRandomPO(MElementValue.class);
		if (elem != null) {
			// si existe debe regresar la traduccion
			final String value = elem.getAccountSignName();
			assertNotNull(value);
			assertNotSame(elem.getAccountSign(), value);
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#getAccountTypeName()}.
	 */
	public void testGetAccountTypeName() {
		MElementValue elem = new MElementValue(Env.getCtx(), 0, null);
		elem.set_ValueOfColumn(MElementValue.COLUMNNAME_AccountType, "");
		// cuando es null, debe regresar vacio
		assertTrue(StringUtils.isEmpty(elem.getAccountTypeName()));
		elem = TestUtils.getRandomPO(MElementValue.class);
		if (elem != null) {
			// si existe debe regresar la traduccion
			final String value = elem.getAccountTypeName();
			assertNotNull(value);
			assertNotSame(elem.getAccountType(), value);
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#getClientElementId(java.util.Properties)}.
	 */
	public void testGetClientElementId() {
		try {
			final MAcctSchemaElement schemaElement = MAcctSchemaElement.getAcctSchemaElementUserElement(Env.getCtx(),//
				Env.getC_AcctSchema_ID(Env.getCtx()),//
				X_C_AcctSchema_Element.ELEMENTTYPE_Account,//
				null);

			final int elementID = schemaElement.getC_Element_ID();

			final int elemId = MElementValue.getClientElementId(Env.getCtx());

			assertEquals(elementID, elemId);

		} catch (final Exception e) {
			fail(e.getLocalizedMessage());
		}

	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#getClientElementTreeId(java.util.Properties)}.
	 */
	public void testGetClientElementTreeId() {
		try {
			final MAcctSchemaElement schemaElement = MAcctSchemaElement.getAcctSchemaElementUserElement(Env.getCtx(),//
				Env.getC_AcctSchema_ID(Env.getCtx()),//
				X_C_AcctSchema_Element.ELEMENTTYPE_Account,//
				null);

			final int treeElementId = schemaElement.getC_Element().getAD_Tree_ID();

			final int treeId = MElementValue.getClientElementTreeId(Env.getCtx());

			assertEquals(treeElementId, treeId);

		} catch (final Exception e) {
			fail(e.getLocalizedMessage());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#getListaCuentas(java.util.Properties)}.
	 */
	public void testGetListaCuentas() {
		final List<MElementValue> list = MElementValue.getAccounts(Env.getCtx(), null, null);
		assertNotNull(list);

		final MElementValue elem =
			TestUtils.getRandomPO(MElementValue.class, " C_Element_ID=? ", true, MElementValue.getClientElementId(Env.getCtx()));
		if (elem == null) {
			assertTrue(list.isEmpty());
		} else {
			assertFalse(list.isEmpty());
			boolean included = false;
			for (final MElementValue value : list) {
				included = value.equals(elem);
				if (included) {
					break;
				}
			}
			assertTrue(included);
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#getLstbyElement(java.util.Properties, int)}.
	 */
	public void testGetLstbyElement() {
		int elementID = 0;
		try {
			final MAcctSchemaElement schemaElement = MAcctSchemaElement.getAcctSchemaElementUserElement(Env.getCtx(),//
				Env.getC_AcctSchema_ID(Env.getCtx()),//
				X_C_AcctSchema_Element.ELEMENTTYPE_Account,//
				null);
			elementID = schemaElement.getC_Element_ID();

		} catch (final Exception e) {
			fail(e.getLocalizedMessage());
		}

		final List<MElementValue> lstElement = MElementValue.getLstbyElement(Env.getCtx(), elementID);
		assertNotNull(lstElement);
		assertTrue(elementID <= 0 ? lstElement.isEmpty() : !lstElement.isEmpty());

		for (final MElementValue value : lstElement) {

			assertTrue(value.getAD_Client_ID() == Env.getAD_Client_ID(Env.getCtx())); // se revisa que sean del cliente autenticado
			assertTrue(value.isActive());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#getNextChildAcct()}.
	 */
	public void testGetNextChildAcct() {
		MElementValue elem = new MElementValue(Env.getCtx(), 0, null);
		assertEquals(elem.getNextChildAcct(), StringUtils.EMPTY);

		elem = TestUtils.getRandomPO(MElementValue.class);
		if (elem != null) {
			assertNotNull(elem.getNextChildAcct());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#getParentName()}.
	 */
	public void testGetParentName() {
		MElementValue elem = new MElementValue(Env.getCtx(), 0, null);
		assertNull(elem.getParentName());
		elem = TestUtils.getRandomPO(MElementValue.class, " ParentElementValue_ID IS NOT NULL", true);
		if (elem != null) {
			assertTrue(StringUtils.isNotBlank(elem.getParentName()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#insert_Tree(java.lang.String)}.
	 */
	public void testInsert_TreeString() {
		MElementValue elem = TestUtils.getRandomPO(MElementValue.class, " Acct_Level=? ", true, MElementValue.MAX_ACCT_LEVEL - 1);
		if (elem != null) {
			int treeId = elem.getC_Element().getAD_Tree_ID();
			if (treeId > 0) {
				Trx trx = null;
				try {
					MElementValue elem2 = new MElementValue(Env.getCtx(), 0, null);
					MElementValue.copyValues(elem, elem2);
					elem2.setParentElementValue_ID(elem.getC_ElementValue_ID());
					elem2.setValue(elem2.getNextChildAcct());
					elem2.setName("test" + System.currentTimeMillis());
					trx = Trx.get(Trx.createTrxName("TstEV"), true);

					assertTrue(elem2.save(trx.getTrxName()));
					// el arbol se inserta en el aftersave
					X_AD_TreeNode node = new Query(Env.getCtx(), MTree_Node.Table_Name, //
						" Node_ID=? AND AD_Tree_ID=? AND AD_Client_ID=? ", trx.getTrxName())//
						.setParameters(elem2.get_ID(), treeId, elem2.getAD_Client_ID())//
						.firstOnly();

					assertNotNull(node);
					assertEquals(node.getParent_ID(), elem2.getParentElementValue_ID());
				} catch (Exception e) {
					fail(e.getLocalizedMessage());
				} finally {
					Trx.rollback(trx, true);
				}
			}
		}

	}

	// /**
	// * Test method for {@link org.compiere.model.MElementValue#insert_Tree(java.lang.String, int)}.
	// */
	// public void testInsert_TreeStringInt() {
	// }

	/**
	 * Test method for {@link org.compiere.model.MElementValue#isActiva()}.
	 */
	public void testIsActiva() {
		final MElementValue elem = new MElementValue(Env.getCtx(), 0, null);
		assertFalse(elem.isActiva());
		// true
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Asset);
		assertTrue(elem.isActiva());
		// false
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Liability);
		assertFalse(elem.isActiva());
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_OwnerSEquity);
		assertFalse(elem.isActiva());
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Expense);
		assertFalse(elem.isActiva());
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Memo);
		assertFalse(elem.isActiva());
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Revenue);
		assertFalse(elem.isActiva());

	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#isBalanceSheet()}.
	 */
	public void testIsBalanceSheet() {
		final MElementValue elem = new MElementValue(Env.getCtx(), 0, null);
		assertFalse(elem.isBalanceSheet());
		// true
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Asset);
		assertTrue(elem.isBalanceSheet());
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Liability);
		assertTrue(elem.isBalanceSheet());
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_OwnerSEquity);
		assertTrue(elem.isBalanceSheet());

		// false
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Expense);
		assertFalse(elem.isBalanceSheet());
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Memo);
		assertFalse(elem.isBalanceSheet());
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Revenue);
		assertFalse(elem.isBalanceSheet());
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#isPassiva()}.
	 */
	public void testIsPassiva() {
		final MElementValue elem = new MElementValue(Env.getCtx(), 0, null);
		assertFalse(elem.isPassiva());
		// true
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Liability);
		assertTrue(elem.isPassiva());
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_OwnerSEquity);
		assertTrue(elem.isPassiva());

		// false
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Asset);
		assertFalse(elem.isPassiva());
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Expense);
		assertFalse(elem.isPassiva());
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Memo);
		assertFalse(elem.isPassiva());
		elem.setAccountType(X_C_ElementValue.ACCOUNTTYPE_Revenue);
		assertFalse(elem.isPassiva());
	}

//	/**
//	 * Test method for
//	 * {@link org.compiere.model.MElementValue#MElementValue(java.util.Properties, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, java.lang.String)}
//	 * .
//	 */
//	public void testMElementValuePropertiesStringStringStringStringStringBooleanBooleanString() {}
//
//	/**
//	 * Test method for {@link org.compiere.model.MElementValue#MElementValue(org.compiere.model.X_I_ElementValue)}.
//	 */
//	public void testMElementValueX_I_ElementValue() {}
//
//	/**
//	 * Test method for {@link org.compiere.model.MElementValue#set(org.compiere.model.X_I_ElementValue)}.
//	 */
//	public void testSet() {}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#toString()}.
	 */
	public void testToString() {
		MElementValue elem = new MElementValue(Env.getCtx(), 0, null);
		// vacio
		String str = elem.toString();
		assertNotNull(str);
		assertTrue(str.length() == 0);
		// solo value
		elem.setValue("123");
		assertTrue(elem.toString().length() == "123".length());
		// solo name
		elem.setValue("");
		elem.setName("123456");
		assertTrue(elem.toString().length() == "123456".length());

		elem = TestUtils.getRandomPO(MElementValue.class);
		if (elem != null) {
			str = elem.toString();
			final String value = elem.getValue();
			assertNotNull(str);
			// value + " - " + name
			assertTrue(str.length() == value.length() + elem.getName().length() + 3);
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#toStringX()}.
	 */
	public void testToStringX() {
		MElementValue elem = TestUtils.getRandomPO(MElementValue.class);
		if (elem == null) {
			elem = new MElementValue(Env.getCtx(), 0, null);
		}
		assertNotNull(elem.toStringX());
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#validateAccount(int)}.
	 */
	public void testValidateAccount() {
		X_C_BP_Customer_Acct sch2 = TestUtils.getRandomPO(X_C_BP_Customer_Acct.class);
		if (sch2 != null) {
			MValidCombination comb = null;
			if (sch2.getC_Receivable_Acct() > 0) {
				comb = new MValidCombination(Env.getCtx(), sch2.getC_Receivable_Acct(), null);
			} else if (sch2.getC_Prepayment_Acct() > 0) {
				comb = new MValidCombination(Env.getCtx(), sch2.getC_Prepayment_Acct(), null);
			}
			if (comb != null) {
				MElementValue elem = new MElementValue(Env.getCtx(), comb.getAccount_ID(), null);
				assertFalse(elem.validateAccount(sch2.get_Table_ID()));
			}
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MElementValue#validateAccountingTables()}.
	 */
	public void testValidateAccountingTables() {
		X_M_Product_Acct sch2 = TestUtils.getRandomPO(X_M_Product_Acct.class);
		if (sch2 != null) {
			MValidCombination comb = null;
			if (sch2.getP_Asset_Acct() > 0) {
				comb = new MValidCombination(Env.getCtx(), sch2.getP_Asset_Acct(), null);
			} else if (sch2.getP_CostAdjustment_Acct() > 0) {
				comb = new MValidCombination(Env.getCtx(), sch2.getP_CostAdjustment_Acct(), null);
			} else if (sch2.getP_Expense_Acct() > 0) {
				comb = new MValidCombination(Env.getCtx(), sch2.getP_Expense_Acct(), null);
			}
			if (comb != null) {
				MElementValue elem = new MElementValue(Env.getCtx(), comb.getAccount_ID(), null);
				assertFalse(elem.validateAccountingTables());
			}
		}
	}

}
