/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.ValueNamePair;

/**
 * Reference List Value
 * 
 * @author Jorg Janke
 * @version $Id: MRefList.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 * modified by @author Lorena Lama (January, 2012)
 */
public class MRefList extends X_AD_Ref_List {

	private static final long serialVersionUID = 8582714577298025147L;


	/**
	 * Get Reference List
	 * 
	 * @param ctx context
	 * @param AD_Reference_ID reference
	 * @param Value value
	 * @param trxName transaction
	 * @return List or null
	 */
	public static MRefList get(Properties ctx, int AD_Reference_ID, String Value, String trxName) {
		return new Query(ctx, Table_Name, " AD_Reference_ID=? AND Value=? ", trxName)//
			.setParameters(AD_Reference_ID, Value)//
			.first();
	} // get

	/**
	 * Get Reference List Value Name (cached)
	 * 
	 * @param ctx context
	 * @param AD_Reference_ID reference
	 * @param Value value
	 * @return List or null
	 */
	public static String getListName(Properties ctx, int AD_Reference_ID, String Value) {
		final String AD_Language = Env.getAD_Language(ctx);
		final String key = AD_Language + "_" + AD_Reference_ID + "_" + Value;

		String retValue = (String) s_cache.get(key);
		if (retValue != null) {
			return retValue;
		}
		boolean isBaseLanguage = Env.isBaseLanguage(AD_Language, "AD_Ref_List");

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT ");
		sql.append(isBaseLanguage ? "AD_Ref_List" : "t").append(".Name");
		sql.append(" FROM AD_Ref_List ");
		if (!isBaseLanguage) {
			sql.append("INNER JOIN AD_Ref_List_Trl t ON (AD_Ref_List.AD_Ref_List_ID=t.AD_Ref_List_ID )");
		}
		sql.append(" WHERE AD_Ref_List.isActive='Y' ");
		sql.append(" AND AD_Ref_List.AD_Reference_ID=? ");
		sql.append(" AND AD_Ref_List.Value=? ");
		if (!isBaseLanguage) {
			sql.append(" AND t.AD_Language=? ");
		}

		if (isBaseLanguage) {
			retValue = DB.getSQLValueString(null, sql.toString(), AD_Reference_ID, Value);
		} else {
			retValue = DB.getSQLValueString(null, sql.toString(), AD_Reference_ID, Value, AD_Language);
		}
		// Save into Cache
		if (retValue == null) {
			retValue = "";
			s_log.warning("getListName - Not found " + key);
		}
		s_cache.put(key, retValue);
		//
		return retValue;
	} // getListName

	/**
	 * Get Reference List
	 * 
	 * @param AD_Reference_ID reference
	 * @param optional if true add "",""
	 * @return List or null
	 * FIXME: siempre son en AD_Language base.
	 */
	public static ValueNamePair[] getList(int AD_Reference_ID, boolean optional) {
		String sql = "SELECT Value, Name FROM AD_Ref_List "
				+ "WHERE AD_Reference_ID=? AND IsActive='Y' ORDER BY 1";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ValueNamePair> list = new ArrayList<ValueNamePair>();
		if (optional)
			list.add(new ValueNamePair("", ""));
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Reference_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new ValueNamePair(rs.getString(1), rs.getString(2)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		ValueNamePair[] retValue = new ValueNamePair[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getList

	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MRefList.class);
	/** Value Cache */
	private static CCache<String, String> s_cache = new CCache<String, String>(
			"AD_Ref_List", 20);

	/**************************************************************************
	 * Persistency Constructor
	 * 
	 * @param ctx context
	 * @param AD_Ref_List_ID id
	 * @param trxName transaction
	 */
	public MRefList(Properties ctx, int AD_Ref_List_ID, String trxName) {
		super(ctx, AD_Ref_List_ID, trxName);
		if (AD_Ref_List_ID == 0) {
			setEntityType(ENTITYTYPE_UserMaintained); // U
		}
	} // MRef_List

	/**
	 * Load Contructor
	 * 
	 * @param ctx context
	 * @param rs result
	 * @param trxName transaction
	 */
	public MRefList(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MRef_List

	/**
	 * String Representation
	 * 
	 * @return Name
	 */
	public String toString() {
		return getName();
	} // toString

	/**
	 * Get Reference List
	 * 
	 * @param AD_Reference_ID reference
	 * @param optional if true add "",""
	 * @return List or null
	 */
	public static ValueNamePair[] getList(Properties ctx, int AD_Reference_ID,
			boolean optional) {
		final List<ValueNamePair> list = getList(ctx, AD_Reference_ID, optional, null);
		ValueNamePair[] retValue = new ValueNamePair[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getList
	
	
	/**
	 * Get Reference List
	 * 
	 * @param AD_Reference_ID reference
	 * @param optional if true add " "," "
	 * @return List or null
	 */
	public static List<ValueNamePair> getList(Properties ctx, int AD_Reference_ID, boolean optional, String where) {
		final String AD_Language = Env.getAD_Language(ctx);
		final boolean isBaseLanguage = Env.isBaseLanguage(AD_Language, Table_Name);

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		if (isBaseLanguage) {
			sql.append("SELECT l.Value, l.Name FROM AD_Ref_List l ");
			sql.append("WHERE l.AD_Reference_ID=? AND l.IsActive='Y' ");
			sql.append(StringUtils.defaultIfEmpty(where, ""));
			sql.append(" ORDER BY l.Name ");
		} else {
			sql.append("SELECT l.Value, t.Name FROM AD_Ref_List_Trl t ");
			sql.append("INNER JOIN AD_Ref_List l ON (l.AD_Ref_List_ID=t.AD_Ref_List_ID) ");
			sql.append("WHERE l.AD_Reference_ID=? AND t.AD_Language=? ");
			sql.append("AND l.IsActive='Y' ");
			sql.append(StringUtils.defaultIfEmpty(where, ""));
			sql.append(" ORDER BY t.Name");
		}
		final List<ValueNamePair> retValue = new ArrayList<ValueNamePair>();
		if (optional) {
			retValue.add(new ValueNamePair(" ", " "));
		}
		if (isBaseLanguage) {
			retValue.addAll(Query.getListVN(sql.toString(), null, AD_Reference_ID));
		} else {
			retValue.addAll(Query.getListVN(sql.toString(), null, AD_Reference_ID, AD_Language));
		}
		return retValue;
	} // getList

	
	/**
	 * Lizeth de la Garza Regresa el listado de una referencia de acuerdo al ID
	 * de la lista
	 * 
	 * @param AD_Reference_ID
	 *            - ID de la lista de referencia
	 * @param AD_Language
	 *            - AD_Language del contexto
	 * @return
	 * @throws Exception
	 * @deprecated : usar {@link #getList(Properties, int, boolean, String)}
	 */
	public static List<LabelValueBean> getListasTraducidas(int AD_Reference_ID, String AD_Language) {
		return MRefList.getListasOrdPorLlave(AD_Reference_ID, AD_Language, false);
	}

	/**
	 * Regresa el nombre traducido de la referencia pasada como
	 * parametro y el value 
	 * @param language
	 * @param referenceID
	 * @param value
	 * @return
	 * @deprecated use {@link #getListName(Properties, int, String)} instead
	 */
	public static String getNameByReferenceID(String language, int referenceID, String value) {
//		StringBuilder sql = new StringBuilder();
//		// es el AD_Language base??
//		boolean base = Language.isBaseLanguage(language);
//		if (base) {
//			sql.append(" SELECT AD_Ref_List.name").append(" FROM AD_Ref_List ").append(" INNER JOIN AD_Reference AR ")
//				.append(" ON AR.AD_Reference_ID = AD_Ref_List.AD_Reference_ID ")
//				.append(" WHERE AR.AD_Reference_ID = ? AND ad_ref_list.value = ?");
//		} else {
//			sql.append(" SELECT trl.Name ").append(" FROM AD_Ref_List ")
//				.append(" INNER JOIN AD_Ref_List_Trl trl ")
//				.append(" ON AD_Ref_List.AD_Ref_List_ID=trl.AD_Ref_List_ID ").append(" INNER JOIN AD_Reference AR ")
//				.append(" ON AR.AD_Reference_ID = AD_Ref_List.AD_Reference_ID ").append(" AND trl.AD_Language=? ")
//				.append(" WHERE AR.AD_Reference_ID = ? AND ad_ref_list.value = ?");
//		}
//		if (base) {
//			return DB.getSQLValueString(null, sql.toString(), referenceID, value);
//		} else {
//			return DB.getSQLValueString(null, sql.toString(), language, referenceID, value);
//		}
		return MRefList.getListName(Env.getCtx(), referenceID, value);
	}

	
	/**
	 * 
	 * @param ctx
	 * @param AD_Reference_ID
	 * @return
	 */
	public static List<MRefList> getList(Properties ctx, int AD_Reference_ID) {
		return new Query(ctx, Table_Name, " AD_Ref_List.AD_Reference_ID=? ", null)//
			.setParameters(AD_Reference_ID)//
			.setOnlyActiveRecords(true)//
			.list();
	}
	
	/**
	 * Get Reference List
	 * @param ctx context
	 * @param AD_Reference_ID reference
	 * @param Value  AD_Ref_List.Value= ?
	 * @return
	 */
	public static String getDescription(Properties ctx, int AD_Reference_ID, String Value) {
		final MRefList retValue = new Query(ctx, Table_Name, " AD_Ref_List.AD_Reference_ID=? AND AD_Ref_List.Value=? ", null)//
			.setParameters(AD_Reference_ID, Value)//
			.first();
		return retValue == null ? null : retValue.get_Translation(COLUMNNAME_Description, Env.getAD_Language(ctx));
	}

	/*** @deprecated use {@link #getListName(Properties, int, String)} instead */
	public static String getListasTraducidasValue(Properties ctx, int AD_Reference_ID, boolean optional, String value) {
		return getListName(ctx, AD_Reference_ID, value);
	}
	
	/**
	 * Regresa la lista de referencia ordenada por llave de busqueda
	 * 
	 * @param excluir
	 * @param AD_Language
	 * @return
	 * @throws Exception
	 * @deprecated : usar {@link #getList(Properties, int, boolean, String)}
	 */
	public static List<LabelValueBean> getListasOrdPorLlave(int AD_Reference_ID, String AD_Language, boolean blanco) {
		return getListasTraducidas(AD_Reference_ID, AD_Language, blanco, null, "l.Value", null);
	}

	/**
	 * 
	 * @param AD_Reference_ID
	 * @param AD_Language
	 * @param blanco
	 * @param where
	 * @param orderby
	 * @param referenceName
	 * @return
	 * @deprecated : usar {@link #getList(Properties, int, boolean, String)}
	 */
	public static List<LabelValueBean> getListasTraducidas(int AD_Reference_ID, String AD_Language, //
		boolean blanco, String where, String orderby, String referenceName) {
		final List<LabelValueBean> resultados = new ArrayList<LabelValueBean>();

		// es el AD_Language base??
		boolean base = Language.isBaseLanguage(AD_Language);

		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT l.Value, ").append(base ? "l.Name" : "t.Name");
		sql.append(" FROM AD_Ref_List l ");
		sql.append(" INNER JOIN AD_Reference r ON (l.AD_Reference_ID = r.AD_Reference_ID AND ");
		sql.append(AD_Reference_ID > 0 ? "r.AD_Reference_ID" : "upper(r.name)").append("=? )");// #1
		if (!base) {
			// Si no es el AD_Language base => cargar traducciones
			sql.append(" INNER JOIN AD_Ref_List_Trl t ON (l.AD_Ref_List_ID = t.AD_Ref_List_ID ");
			sql.append(" AND t.AD_Language=?) ");// #2
		}
		sql.append(" WHERE l.isActive = 'Y' ");
		sql.append(where == null ? "" : where);
		sql.append(" ORDER BY ");
		if (StringUtils.isNotEmpty(orderby)) {
			sql.append(orderby);
		} else {
			sql.append(base ? "l.Name" : "t.Name");
		}
		final Object[] params = new Object[base ? 1 : 2];
		params[0] = AD_Reference_ID > 0 ? AD_Reference_ID : referenceName;
		if (!base) {
			params[1] = AD_Language;
		}
		if (blanco) {
			resultados.add(new LabelValueBean("", ""));
		}
		resultados.addAll(Query.getList(sql.toString(), null, params));
		return resultados;
	}
	
	/**
	 * Regresa el listado de una lista de referencia, dependiendo su idioma (en ValueNamePair)
	 * @param AD_Reference_ID
	 * @param AD_Language
	 * @param blanco
	 * @param where
	 * @param orderby
	 * @param referenceName
	 * @return
	 */
	public static List<ValueNamePair> getVNPListasTraducidas(int AD_Reference_ID, String AD_Language, 
			boolean blanco, String where, String orderby, String referenceName) {
			final List<ValueNamePair> resultados = new ArrayList<ValueNamePair>();

			final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
			
			// es el AD_Language base??
			boolean base = Language.isBaseLanguage(AD_Language);

			sql.append(" SELECT l.Value, ").append(base ? "l.Name" : "t.Name");
			sql.append(" FROM AD_Ref_List l ");
			sql.append(" INNER JOIN AD_Reference r ON (l.AD_Reference_ID = r.AD_Reference_ID AND ");
			sql.append(AD_Reference_ID > 0 ? "r.AD_Reference_ID" : "upper(r.name)").append("=? )");// #1
			if (!base) {
				// Si no es el AD_Language base => cargar traducciones
				sql.append(" INNER JOIN AD_Ref_List_Trl t ON (l.AD_Ref_List_ID = t.AD_Ref_List_ID ");
				sql.append(" AND t.AD_Language=?) ");// #2
			}
			sql.append(" WHERE l.isActive = 'Y' ");
			sql.append(where == null ? "" : where);
			sql.append(" ORDER BY ");
			if (StringUtils.isNotEmpty(orderby)) {
				sql.append(orderby);
			} else {
				sql.append(base ? "l.Name" : "t.Name");
			}
			
			final Object[] params = new Object[base ? 1 : 2];
			params[0] = AD_Reference_ID > 0 ? AD_Reference_ID : referenceName;
			if (!base) {
				params[1] = AD_Language;
			}
			
			if (blanco) {
				resultados.add(new ValueNamePair("", ""));
			}
			
			resultados.addAll(Query.getListVN(sql.toString(), null, params));
			
			return resultados;
		}
	
	/**
	 * getListasTraducidas
	 * 
	 * @param ctx
	 * @param AD_Reference_ID
	 * @param optional
	 * @return
	 * @throws Exception
	 * @deprecated : usar {@link #getList(Properties, int, boolean, String)}
	 */
	public static List<LabelValueBean> getListasTraducidas(Properties ctx, int AD_Reference_ID, boolean optional) {
		return getListasTraducidas(AD_Reference_ID, Env.getAD_Language(ctx), optional, null, null, null);
	}

	/**
	 * Listas de referencia - AD_Language
	 * 
	 * @param AD_Reference_ID
	 * @param AD_Language
	 * @param blanco
	 * @return
	 * @throws Exception
	 * @deprecated : usar {@link #getList(Properties, int, boolean, String)}
	 */
	public static List<LabelValueBean> getListasTraducidas(int AD_Reference_ID, String AD_Language, boolean blanco) {
		return MRefList.getListasTraducidas(AD_Reference_ID, AD_Language, blanco, null, null, null);
	}

//	/** @deprecated	use {@link #get(Properties, int, String, String)} */
//	public static MRefList getListName(Properties ctx, int AD_Reference_ID, String Value, String trxName) {
//		return get(ctx, AD_Reference_ID, Value, trxName);
//	} // getListName
	
	public String getNameStr() {
		return get_Translation(COLUMNNAME_Name);
	}
	
} // MRef_List
