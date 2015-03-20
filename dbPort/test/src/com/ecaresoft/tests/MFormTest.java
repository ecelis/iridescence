/**
 * 
 */
package com.ecaresoft.tests;

import java.util.Properties;

import junit.framework.TestCase;

import org.compiere.model.MForm;
import org.compiere.model.MRole;
import org.compiere.util.DB;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author Lorena Lama
 *
 */
public class MFormTest extends TestCase {

	/**@see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}
	/**
	 * Obtener el nombre a partir de la clase
	 * @param ctx
	 * @param classname
	 * @param trxName
	 * @return
	 */
	public static String getNameFromClass(final Properties ctx, final String classname, final String trxName) {
		String retVal = null;

		
		
		String from = " FROM AD_Form f ";
		String where = " WHERE classname = ?";
		StringBuilder sql = new StringBuilder();
		Object[] params = null;

		if(Env.getLoginLanguage(ctx).isBaseLanguage()) {
			sql.append("SELECT f.name ").append(from).append(where);
			params = new Object[]{classname};
		} else {
			sql.append("SELECT trl.Name ").append(from).append("INNER JOIN  AD_Form_Trl trl on f.ad_form_id = trl.ad_form_id")
				.append(where).append(" AND AD_Language = ?");
			params = new Object[]{classname, Env.getAD_Language(ctx)};
		}

		retVal = DB.getSQLValueString(null, sql.toString(), params);

		return retVal;
	}
//	/**
//	 * Test method for {@link org.compiere.model.MForm#afterSave(boolean, boolean)}.
//	 */
//	public void testAfterSave() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MForm#getForm_ID(java.lang.String, java.lang.String)}.
//	 */
//	public void testGetForm_IDStringString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MForm#getForm_ID(java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	public void testGetForm_IDStringStringString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MForm#getFormIdByName(java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	public void testGetFormIdByName() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MForm#getTitle()}.
//	 */
//	public void testGetTitle() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MForm#get(java.util.Properties, int)}.
//	 */
//	public void testGet() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MForm#getFromClassName(java.util.Properties, java.lang.String)}.
//	 */
//	public void testGetFromClassName() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MForm#getFormID(java.util.Properties, java.lang.String, int)}.
	 */
	public void testGetFormID() {
		// force -1
		assertEquals(-1, MForm.getFormID(Env.getCtx(), "org.class.dummy", Env.getAD_Role_ID(Env.getCtx())));
		// random
		final MForm obj = TestUtils.getRandomPO(MForm.class, "  AD_Form_id in (select ad_form_id from AD_Form_Access where isActive = 'Y')", true);
		final MRole role = TestUtils.getRandomPO(MRole.class, "  AD_role_id in (select ad_role_id from AD_Form_Access where isActive = 'Y' and ad_form_id = ?) ", true, obj.getAD_Form_ID());
		if (obj != null) {
			assertEquals(obj.getAD_Form_ID(), MForm.getFormID(Env.getCtx(), obj.getClassname(), role.getAD_Role_ID()));
		}
	}
	
	public void testGetClassName() {
		assertEquals(null, MForm.getNameFromClass(Env.getCtx(), "org.class.dummy", null));
		
		final MForm obj = TestUtils.getRandomPO(MForm.class);
		if (obj != null) {
			assertEquals(obj.getName(), MForm.getNameFromClass(Env.getCtx(), obj.getClassname(), null));
		}
	}

}
