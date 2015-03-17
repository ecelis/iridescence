/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved. *
 * This program is free software; you can redistribute it and/or modify it *
 * under the terms version 2 of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. *
 * See the GNU General Public License for more details. *
 * You should have received a copy of the GNU General Public License along *
 * with this program; if not, write to the Free Software Foundation, Inc., *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA. *
 * For the text or an alternative of this public license, you may reach us *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA *
 * or via info@compiere.org or http://www.compiere.org/license.html *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Form Model
 *
 * @author Jorg Janke
 * @version $Id: MForm.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 *
 *          Modified by Lorena Lama
 */
public class MForm extends X_AD_Form {

	private static final long serialVersionUID = 1L;
	private static CLogger slog = CLogger.getCLogger(MForm.class);
	private static CCache<Integer, MForm> s_cache = new CCache<Integer, MForm>(Table_Name, 20);

	/**
	 * Default Constructor
	 *
	 * @param ctx
	 *            context
	 * @param AD_Form_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MForm(final Properties ctx, final int AD_Form_ID, final String trxName) {
		super(ctx, AD_Form_ID, trxName);
	} // MForm

	/**
	 * Load Constructor
	 *
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MForm(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	} // MForm

	/**
	 * After Save
	 *
	 * @param newRecord
	 *            new
	 * @param success
	 *            success
	 * @return success
	 */
	@Override
	protected boolean afterSave(final boolean newRecord, final boolean success) {
		if (newRecord) {
			final int AD_Role_ID = Env.getAD_Role_ID(getCtx());
			final MFormAccess pa = new MFormAccess(this, AD_Role_ID);
			pa.save();
		}
		return success;
	} // afterSave

	/**
	 * get the form id from the className
	 *
	 * @param className
	 * @param trxname
	 * @return
	 */
	public static int getForm_ID(final String className, final String trxName) {
		final String sql = "SELECT AD_Form_ID FROM AD_Form WHERE className=? AND isActive='Y'";
		return DB.getSQLValue(trxName, sql, className);
	}

	/**
	 * get the form id from the className and params
	 *
	 * @param className
	 * @param trxname
	 * @return
	 */
	public static int getForm_ID(final String className, final String params, final String trxName) {
		final String sql = "SELECT AD_Form_ID FROM AD_Form WHERE className=? and jspurl=? AND isActive='Y'";
		return DB.getSQLValue(trxName, sql, className, params);
	}

	/**
	 * get the form id from the className and name (Like)
	 *
	 * @param className
	 * @param trxname
	 * @return
	 */
	public static int getFormIdByName(final String className, final String name, final String trxName) {
		final String sql = "SELECT AD_Form_ID FROM AD_Form WHERE className=? and upper(name) LIKE ? AND isActive='Y'";
		return DB.getSQLValue(trxName, sql, className, name);
	}

	/**
	 * Obtener el titulo traducido
	 *
	 * @return
	 */
	public String getTitle() {
		return get_Translation(COLUMNNAME_Name);
	}

	/**
	 * Get form from cache
	 * 
	 * @param ctx
	 * @param AD_Form_ID
	 * @return
	 */
	public static MForm get(final Properties ctx, final int AD_Form_ID) {
		final Integer key = new Integer(AD_Form_ID);
		MForm retValue = s_cache.get(key);
		if (retValue != null) {
			return retValue;
		}
		retValue = new MForm(ctx, AD_Form_ID, null);
		if (retValue.get_ID() != 0) {
			s_cache.put(key, retValue);
		}
		return retValue;
	} // get

	/**
	 * Obtener el objeto de la forma a partir del nombre de la clase
	 *
	 * @param ctx
	 *            Contexto de la aplicacion
	 * @param className
	 *            Nombre de la clase
	 * @return
	 */
	public static MForm getFromClassName(final Properties ctx, final String className) {
		return new Query(ctx, Table_Name, " className=? ", null).setParameters(className)//
				.setOnlyActiveRecords(true)//
				.first();
	} // get

	/**
	 * Obtener el AD_Form_ID a partir del nombre de la clase, con permisos para
	 * el rol actual
	 *
	 * @param ctx
	 *            Contexto de la aplicacion (obligatorio)
	 * @param clazz
	 *            Nombre de la clase (obligatorio)
	 * @return AD_Form_ID
	 */
	public static int getFormIdByRole(final Properties ctx, final String className) {
		return getFormID(ctx, className, Env.getAD_Role_ID(ctx));
	}

	/**
	 * Obtener el AD_Form_ID a partir del nombre de la clase, con permisos para
	 * el rol
	 *
	 * @param ctx
	 *            Contexto de la aplicacion (obligatorio)
	 * @param className
	 *            Nombre de la clase (obligatorio)
	 * @param AD_Role_ID
	 *            Id del Rol (obligatorio, mayor igual a cero)
	 * @return AD_Form_ID
	 */
	public static int getFormID(final Properties ctx, final String className, final int AD_Role_ID) {
		if (ctx == null || className == null || AD_Role_ID < 0) {
			return -1;
		}
		slog.finest("getFormID by Role: " + AD_Role_ID);
		return new Query(ctx, I_AD_Form.Table_Name, " AD_Form.className=? AND AD_Form_Access.AD_Role_ID=? ", null)
				.setJoins(new StringBuilder(" INNER JOIN AD_Form_Access ON (AD_Form.AD_Form_ID=AD_Form_Access.AD_Form_ID AND AD_Form_Access.IsActive='Y') ")).setOnlyActiveRecords(true)//
				.setParameters(className, AD_Role_ID)//
				.firstId();
	}

	/**
	 * Obtener url de ayuda de la clase
	 *
	 * @param ctx
	 *            Contexto
	 * @param classname
	 *            Nombre de clase
	 * @param trxName
	 *            Nombre de la transaccion
	 * @return Url de ayuda o nulo si no tiene
	 */
	public static String getHelp(final Properties ctx, final String classname, final String trxName) {
		final String sql = "SELECT Help_URI FROM AD_Form WHERE classname=?";
		return DB.getSQLValueString(trxName, sql, classname);
	}

	public static String getNameFromId(final Properties ctx, final int adFormId) {
		String retVal = null;

		final String select = "SELECT Name FROM AD_Form";
		final String where = " WHERE AD_Form_ID = ?";
		final StringBuilder sql = new StringBuilder();
		Object[] params = null;

		if (Env.getLoginLanguage(ctx).isBaseLanguage()) {
			sql.append(select).append(where);
			params = new Object[] { adFormId };
		} else {
			sql.append(select).append("_Trl").append(where).append(" AND AD_Language = ?");
			params = new Object[] { adFormId, Env.getAD_Language(ctx) };
		}

		retVal = DB.getSQLValueString(null, sql.toString(), params);

		return retVal;
	}

	/**
	 * Obtener el nombre a partir de la clase
	 * 
	 * @param ctx
	 * @param classname
	 * @param trxName
	 * @return
	 */
	public static String getNameFromClass(final Properties ctx, final String classname, final String trxName) {
		String retVal = null;

		final String from = " FROM AD_Form f ";
		final String where = " WHERE classname = ?";
		final StringBuilder sql = new StringBuilder();
		Object[] params = null;

		if (Env.getLoginLanguage(ctx).isBaseLanguage()) {
			sql.append("SELECT f.name ").append(from).append(where);
			params = new Object[] { classname };
		} else {
			sql.append("SELECT trl.Name ").append(from).append("INNER JOIN  AD_Form_Trl trl on f.ad_form_id = trl.ad_form_id").append(where).append(" AND AD_Language = ?");
			params = new Object[] { classname, Env.getAD_Language(ctx) };
		}

		retVal = DB.getSQLValueString(null, sql.toString(), params);

		return retVal;
	}

} // MForm
