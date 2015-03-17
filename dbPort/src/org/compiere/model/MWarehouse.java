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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.hospital.utils.Datos;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.ValueNamePair;

import com.ecaresoft.api.Generic;

/**
 * Warehouse Model
 * 
 * @author Jorg Janke
 * @version $Id: MWarehouse.java,v 1.3 2006/07/30 00:58:05 jjanke Exp $
 */
public class MWarehouse extends X_M_Warehouse {
	/** serialVersionUID */
	private static final long serialVersionUID = -5367914952987308780L;
	/** Cache */
	private static CCache<Integer, MWarehouse> s_cache = new CCache<Integer, MWarehouse>(
			"M_Warehouse", 5);
	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(MWarehouse.class);
	/** almacen seleccionado */
	private boolean selected = false;
	/** relacion clase de producto almacen */
	private int productClassWhsID = 0;
	/** Warehouse Locators */
	private MLocator[] mLocators = null;
	/** Warehouse Location */
	private MLocation mLocation = null;
	/** socio de negocio */
	private MBPartner mBPartner = null;
	
	/**
	 * Get from Cache
	 * 
	 * @param ctx
	 *            context
	 * @param mWarehouseID
	 *            id
	 * @return warehouse
	 */
	public static MWarehouse get(final Properties ctx, final int mWarehouseID) {
		final Integer key = new Integer(mWarehouseID);
		MWarehouse retValue = (MWarehouse) s_cache.get(key);
		if (retValue != null
		// Expert : Lama (verificar la sesion, para usar o no el objeto del
		// cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID") == Env
						.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))){ // lama
			return retValue;
		}
		//
		retValue = new MWarehouse(ctx, mWarehouseID, null);
		s_cache.put(key, retValue);
		return retValue;
	} // get

	/**
	 * Get Warehouses for Org
	 * 
	 * @param ctx
	 *            context
	 * @param AD_Org_ID
	 *            id
	 * @return warehouse
	 */
	public static List<MWarehouse> getForOrg(final Properties ctx,
			final int AD_Org_ID) {
		return new Query(ctx, MWarehouse.Table_Name, " AD_Org_ID = ? ", null)//
				.setOrderBy("Created") //
				.setParameters(AD_Org_ID) //
				.setOnlyActiveRecords(true) //
				.list();

		// List<MWarehouse> list = new ArrayList<MWarehouse>();
		// String sql =
		// "SELECT * FROM M_Warehouse WHERE AD_Org_ID=? ORDER BY Created";
		// PreparedStatement pstmt = null;
		// ResultSet rs = null;
		// try {
		// pstmt = DB.prepareStatement(sql, null);
		// pstmt.setInt(1, AD_Org_ID);
		// rs = pstmt.executeQuery();
		// while (rs.next())
		// list.add(new MWarehouse(ctx, rs, null));
		// } catch (Exception e) {
		// slog.log(Level.SEVERE, "getForOrg - sql = " + sql, e);
		// } finally {
		// DB.close(rs, pstmt);
		// }
		//
		// MWarehouse[] retValue = new MWarehouse[list.size()];
		// list.toArray(retValue);
		// return retValue;
	} // get

	/**
	 * Get Warehouses for Org
	 * 
	 * @param ctx
	 *            context
	 * @param AD_Org_ID
	 *            id
	 * @return warehouse
	 */
	public static List<MWarehouse> getForOrgList(final Properties ctx) {

		// ArrayList<MWarehouse> list = new ArrayList<MWarehouse>();
		// StringBuilder sql = new
		// StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// sql.append("SELECT * FROM M_Warehouse WHERE isActive = 'Y' ");
		// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
		// sql.toString(), "M_Warehouse"));
		// ResultSet rs = null;
		// PreparedStatement pstmt = null;
		//
		// try {
		// pstmt = DB.prepareStatement(sql.toString(), null);
		// rs = pstmt.executeQuery();
		// while (rs.next())
		// list.add(new MWarehouse(ctx, rs, null));
		// } catch (Exception e) {
		// slog.log(Level.SEVERE, "getForOrgList - sql = " + sql.toString(), e);
		// } finally {
		// DB.close(rs, pstmt);
		// }
		// return list;
		return MWarehouse.getForOrg(ctx, Env.getAD_Org_ID(ctx));
	} // get

	/**
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param mWarehouseID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MWarehouse(final Properties ctx, final int mWarehouseID,
			final String trxName) {
		super(ctx, mWarehouseID, trxName);
		if (mWarehouseID == 0) {
			// setValue (null);
			// setName (null);
			// setC_Location_ID (0);
			setSeparator("*"); // *
		}
	} // MWarehouse

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
	public MWarehouse(final Properties ctx, final ResultSet rset,
			final String trxName) {
		super(ctx, rset, trxName);
	} // MWarehouse

	/**
	 * Organization Constructor
	 * 
	 * @param org
	 *            parent
	 */
	public MWarehouse(final MOrg org) {
		this(org.getCtx(), 0, org.get_TrxName());
		setClientOrg(org);
		setValue(org.getValue());
		setName(org.getName());
		if (org.getInfo() != null){
			setC_Location_ID(org.getInfo().getC_Location_ID());
		}
	} // MWarehouse

	/**
	 * Get Locators
	 * 
	 * @param reload
	 *            if true reload
	 * @return array of locators
	 */
	public MLocator[] getLocators(final boolean reload) {
		if (!reload && mLocators != null){
			return mLocators;
		}

		final List<MLocator> list = getLocators();

		mLocators = new MLocator[list.size()];
		list.toArray(mLocators);

		return mLocators;
	} // getLocators
	
	public List<MLocator> getLocators(){
		return getLocators(getCtx(), getM_Warehouse_ID());
	}
	
	public static List<MLocator> getLocators(Properties ctx, int warehouseId){
		final List<MLocator> list = new Query(ctx, MLocator.Table_Name,
				" M_Warehouse_ID = ? ", null)//
				.setOrderBy("isDefault DESC,X,Y,Z") //
				.setParameters(warehouseId) //
				.setOnlyActiveRecords(true) //
				.list();
		
		return list;
	}

	/**
	 * Get Default Locator
	 * 
	 * @return (first) default locator
	 */
	public MLocator getDefaultLocator() {
		final MLocator[] locators = getLocators(false);
		for (int i = 0; i < locators.length; i++) {
			if (locators[i].isDefault() && locators[i].isActive()){
				return locators[i];
			}
		}
		// No Default - first one
		if (locators.length > 0) {
			log.warning("No default locator for " + getName());
			return locators[0];
		}
		// No Locator - create one
		final MLocator loc = new MLocator(this, "Standard");
		loc.setIsDefault(true);
		loc.save();
		log.info("Created default locator for " + getName());
		return loc;
	} // getLocators

	/**
	 * After Save
	 * 
	 * @param newRecord
	 *            new
	 * @param success
	 *            success
	 * @return success
	 */
	protected boolean afterSave(final boolean newRecord, final boolean success) {
		if (newRecord & success){
			insert_Accounting("M_Warehouse_Acct", "C_AcctSchema_Default", null);
		}
		return success;
	} // afterSave

	/**
	 * Before Delete
	 * 
	 * @return true
	 */
	protected boolean beforeDelete() {
		return delete_Accounting("M_Warehouse_Acct");
	} // beforeDelete

	// // TODO Documentar Metodo
	// public Vector<OptionItem> getWareHouses(int ad_org) {
	//
	// Vector<OptionItem> wareHouses = null;
	//
	// StringBuilder sb = new StringBuilder(
	// "SELECT M_WAREHOUSE_ID,NAME FROM M_WAREHOUSE where ad_org_id= ? and isactive='Y'");
	// StringBuilder sql = new StringBuilder(
	// MEXMELookupInfo.addAccessLevelSQL(getCtx(), sb.toString(),
	// this.get_TableName()));
	//
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	//
	// try {
	// pstmt = DB.prepareStatement(sql.toString(), null);
	// pstmt.setInt(1, ad_org);
	// rs = pstmt.executeQuery();
	// wareHouses = new Vector<OptionItem>();
	// wareHouses.add(new OptionItem("0", "            "));
	//
	// while (rs.next()) {
	// wareHouses
	// .add(new OptionItem(rs.getString(1), rs.getString(2)));
	// }
	//
	// } catch (SQLException e) {
	// log.log(Level.SEVERE, "getWareHouses - sql =  " + sql.toString(), e);
	// } finally {
	// DB.close(rs, pstmt);
	// }
	//
	// return wareHouses;
	// }

	// /**
	// * getIdWarehouse : metodo para obtener el ID del almacen a partir del
	// value
	// *
	// * @param value
	// * @return
	// */
	// public static long getIdWarehouse(String value) {
	//
	// long id = 0;
	//
	// StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	// // PreparedStatement con la sentencia sql
	// PreparedStatement pstmt = null;
	// // ResultSet utilizado para manipular los resultados
	// ResultSet rs = null;
	//
	// /*
	// * Se corrige forma de ejecutar queries y excepci�n de q no se cierran
	// * las conexiones Jesus Cantu el 16 de Nov del 2010, se aplicaron
	// * cambios a toda la clase
	// */
	// /*
	// * sqlvf = "SELECT M_Warehouse_ID FROM M_Warehouse WHERE Value = ? " +
	// * " AND IsActive = 'Y' ";
	// */
	//
	// sql.append("SELECT M_Warehouse_ID FROM M_Warehouse WHERE Value = ? ");
	// sql.append(" AND IsActive = 'Y' ");
	//
	// try {
	// pstmt = DB.prepareStatement(sql.toString(), null);
	// pstmt.setString(1, value);
	// rs = pstmt.executeQuery();
	//
	// if (rs.next()) {
	// id = rs.getLong("M_Warehouse_ID");
	// }
	// } catch (SQLException e) {
	// slog.log(Level.SEVERE, "getIdWarehouse", e);
	// } finally {
	// DB.close(rs, pstmt);
	// }
	//
	// return id;
	//
	// }

	public static int getIdWHByValue(final Properties ctx, final String value) {
		final MWarehouse mWarehouse = MWarehouse.getWHByValue(ctx, value);
		return mWarehouse == null ? 0 : mWarehouse.getM_Warehouse_ID();
	}

	public static MWarehouse getWHByValue(final Properties ctx,
			final String value) {

		return new Query(ctx, MWarehouse.Table_Name, " Value = ? ", null)//
				.setOrderBy("Created") //
				.setParameters(value) //
				.setOnlyActiveRecords(true) //
				.addAccessLevelSQL(true)//
				.first();
	}

	/**
	 * getIdWarehouseDesc : metodo para obtener el ID del almacen a partir del
	 * description
	 * 
	 * @param desc
	 * @return
	 */
	public static int getIdWarehouseDesc(final Properties ctx, final String desc) {
		// long id = 0;
		// StringBuilder sql = new
		// StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// // PreparedStatement con la sentencia sql
		// PreparedStatement pstmt = null;
		// // ResultSet utilizado para manipular los resultados
		// ResultSet rs = null;
		//
		// /*
		// * sqlvf = "SELECT M_Warehouse_ID FROM M_Warehouse WHERE Name = ? " +
		// * " AND IsActive = 'Y' ";
		// */
		//
		// sql.append("SELECT M_Warehouse_ID FROM M_Warehouse WHERE Name = ? ");
		// sql.append(" AND IsActive = 'Y' ");
		//
		// try {
		// pstmt = DB.prepareStatement(sql.toString(), null);
		// pstmt.setString(1, desc);
		// rs = pstmt.executeQuery();
		//
		// if (rs.next()) {
		// id = rs.getLong("M_Warehouse_ID");
		// }
		// } catch (SQLException e) {
		// slog.log(Level.SEVERE,
		// "getIdWarehouseDesc - sql = " + sql.toString(), e);
		//
		// } finally {
		// DB.close(rs, pstmt);
		// }
		//
		// return id;

		return new Query(ctx, MWarehouse.Table_Name, " Name = ? ", null)//
				.setOrderBy("Created") //
				.setParameters(desc) //
				.setOnlyActiveRecords(true) //
				.firstId();
	}

	/**
	 * beforeSave
	 */
	protected boolean beforeSave(final boolean newRecord) {
		boolean valid = true;
		if (isConsigna()) {
			if (getC_BPartner_ID() == 0) {
				valid = false;
			}
		} else {
			setC_BPartner_ID(0);
		}
		return valid;
	}

	// /**
	// * Nos trae la lista de almacenes que no estan registrados para un
	// producto,
	// * en el caso de que se mande productId con valor de cero nos traera todos
	// * los almacenes
	// *
	// * @param ctx
	// * Contexto
	// * @param active
	// * Estatus de los registros a consultar (Y/N), en caso de que se
	// * deseen todos los registros mandar null
	// * @param productId
	// * Id del producto para obtener los almacenes que tiene asignados
	// * al productos
	// * @return List<MWarehouse>
	// * @throws Exception
	// * @author mvrodriguez
	// */
	// public static List<MWarehouse> getWarehouseNotInReplenish(Properties ctx,
	// String active, int productId) throws Exception {
	// int paramNum = 1;
	// List<MWarehouse> list = new ArrayList<MWarehouse>();
	// StringBuilder sql = new StringBuilder();
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	//
	// sql.append("SELECT * ");
	// sql.append("  FROM M_WAREHOUSE ");
	//
	// if (productId != 0) {
	// sql.append(" WHERE M_WAREHOUSE.M_WAREHOUSE_ID NOT IN (SELECT M_REPLENISH.M_WAREHOUSE_ID ");
	// sql.append(" FROM M_REPLENISH ");
	// sql.append(" WHERE M_REPLENISH.ISACTIVE = 'Y' ");
	//
	// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
	// sql.toString(), "M_Replenish"));
	//
	// sql.append("   AND M_REPLENISH.M_PRODUCT_ID = ?) ");
	//
	// }
	//
	// if (active != null || !"".equals(active)) {
	//
	// if (productId == 0) {
	// sql.append(" WHERE ");
	// } else {
	// sql.append(" AND ");
	// }
	// sql.append(" M_WAREHOUSE.ISACTIVE = ? ");
	//
	// }
	//
	// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
	// sql.toString(), "M_Warehouse"));
	//
	// try {
	// pstmt = DB.prepareStatement(sql.toString(), null);
	// if (productId != 0) {
	// pstmt.setInt(paramNum++, productId);
	// }
	//
	// if (active != null || !"".equals(active)) {
	// pstmt.setString(paramNum, active);
	// }
	//
	// rs = pstmt.executeQuery();
	//
	// while (rs.next()) {
	// list.add(new MWarehouse(ctx, rs, null));
	// }
	// } catch (Exception e) {
	// slog.log(Level.SEVERE,
	// "getWarehouseNotInReplenish - sql = " + sql.toString(), e);
	// } finally {
	// DB.close(rs, pstmt);
	// }
	//
	// return list;
	//
	// }

	// /**
	// * Nos trae la lista de almacenes que estan relacionados a un producto
	// *
	// * @param ctx
	// * Contexto
	// * @param active
	// * Estatus de los registros a consultar (Y/N), en caso de que se
	// * deseen todos los registros mandar null
	// * @param productId
	// * Id del producto del que obtendremos los almacenes relacionados
	// * @return List<MWarehouse>
	// * @throws Exception
	// * @author mvrodriguez
	// */
	// public static List<MWarehouse> getWarehouseRelatedProduct(Properties ctx,
	// String active, int productId) throws Exception {
	//
	// List<MWarehouse> list = new ArrayList<MWarehouse>();
	// StringBuilder sql = new StringBuilder();
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	//
	// sql.append("SELECT * ");
	// sql.append("  FROM M_WAREHOUSE ");
	// sql.append(" WHERE M_WAREHOUSE.M_WAREHOUSE_ID IN (SELECT M_REPLENISH.M_WAREHOUSE_ID ");
	// sql.append(" FROM M_REPLENISH ");
	// sql.append(" WHERE M_REPLENISH.ISACTIVE = 'Y' ");
	//
	// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
	// sql.toString(), "M_Replenish"));
	//
	// sql.append("   AND M_REPLENISH.M_PRODUCT_ID = ? ) ");
	//
	// if (active != null || !"".equals(active)) {
	// sql.append(" AND M_WAREHOUSE.ISACTIVE = ? ");
	// }
	//
	// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
	// sql.toString(), "M_Warehouse"));
	//
	// try {
	// pstmt = DB.prepareStatement(sql.toString(), null);
	// pstmt.setInt(1, productId);
	//
	// if (active != null || !"".equals(active)) {
	// pstmt.setString(2, active);
	// }
	//
	// rs = pstmt.executeQuery();
	//
	// while (rs.next()) {
	// list.add(new MWarehouse(ctx, rs, null));
	// }
	//
	// } catch (Exception e) {
	// slog.log(Level.SEVERE,
	// "getWarehouseRelatedProduct - sql = " + sql.toString(), e);
	// } finally {
	// DB.close(rs, pstmt);
	// }
	// return list;
	// }

	// public static List<LabelValueBean> getWarehouse(Properties ctx)
	// throws Exception {
	// // lista con las motivos de cita
	// List<LabelValueBean> lstwh = new ArrayList<LabelValueBean>();
	// String sql = null;
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	//
	// // buscamos el motivo de cita
	// sql = "SELECT * FROM M_Warehouse where isActive = 'Y'";
	//
	// try {
	// pstmt = DB.prepareStatement(sql, null);
	// rs = pstmt.executeQuery();
	//
	// while (rs.next()) {
	// LabelValueBean wh = new LabelValueBean(rs.getString("Name"),
	// String.valueOf(rs.getInt("M_Warehouse_ID")));
	// lstwh.add(wh);
	// }
	//
	// } catch (Exception e) {
	// slog.log(Level.SEVERE, "getWarehouse - sql = " + sql, e);
	// } finally {
	// DB.close(rs, pstmt);
	// }
	//
	// return lstwh;
	// }

	/**
	 * @param ctx
	 * @param estacionId
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<Generic> getAlmacenesDeEstacion(final Properties ctx,
			final int estacionId, final String trxName) throws Exception {
		if (ctx == null) {
			return null;
		}
		final StringBuilder sql = new StringBuilder("");
		final List<Generic> resultados = new ArrayList<Generic>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {

			sql.append(" SELECT ES.M_WAREHOUSE_ID, WH.NAME AS ALMACEN ")
					.append(" FROM EXME_ESTSERVALM ES INNER JOIN M_WAREHOUSE WH ON WH.M_WAREHOUSE_ID = ES.M_WAREHOUSE_ID ")
					.append(" WHERE ES.EXME_ESTSERV_ID = ?")
					// .append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_EstServAlm"))
					.append(" ORDER BY wh.Name ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, estacionId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				final Generic resultado = new Generic(rset.getString(2),
						rset.getString(1));

				resultados.add(resultado);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE,
					"getAlmacenesDeEstacion - sql = " + sql.toString(), e);
			throw e;
		} finally {
			DB.close(rset, pstmt);
		}
		return resultados;
	}

	// public static boolean getExistPharmacyOnWarehouse(Properties ctx,
	// int almacenID) throws Exception {
	//
	// StringBuilder sql = new StringBuilder("");
	// boolean res = false;
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// try {
	// sql.append(" 	select r.*")
	// .append(" 	from exme_warehouserel r")
	// .append(" 	inner join m_warehouse  w on w.m_warehouse_id = r.M_WAREHOUSEREL_ID")
	// .append(" 	where ").append(" 	r.m_warehouse_id = ? ")
	// .append(" 	and w.IsActive = 'Y'")
	// .append(" 	and w.TipoAlmacen = 'I'");
	//
	// // sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
	// // sql.toString(), "EXME_WarehouseRel"));
	//
	// pstmt = DB.prepareStatement(sql.toString(), null);
	// pstmt.setInt(1, almacenID);
	// rs = pstmt.executeQuery();
	// if (rs.next()) {
	// res = true;
	// }
	// } catch (Exception e) {
	// slog.log(Level.SEVERE,
	// "getExistPharmacyOnWarehouse - sql = " + sql.toString(), e);
	// throw new Exception(e.getMessage());
	// } finally {
	// DB.close(rs, pstmt);
	// }
	// return res;
	// }

	// public static MWarehouse getMWarehouseByValue(Properties ctx, String
	// value) {
	// StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	// sql.append("	SELECT * FROM M_WareHouse WHERE isActive = 'Y' AND Value = ?	");
	//
	// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
	// sql.toString(), "M_Warehouse"));
	//
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// MWarehouse locator = null;
	// try {
	// pstmt = DB.prepareStatement(sql.toString(), null);
	// pstmt.setString(1, value);
	// rs = pstmt.executeQuery();
	//
	// while (rs.next()) {
	// locator = new MWarehouse(ctx, rs, null);
	// }
	// } catch (Exception e) {
	// slog.log(Level.SEVERE,
	// "getMWarehouseByValue - sql = " + sql.toString(), e);
	// } finally {
	// DB.close(rs, pstmt);
	// }
	//
	// return locator;
	// }

	public MLocation getLocation() {

		if (mLocation == null || mLocation.getC_Location_ID() == 0) {
			mLocation = new MLocation(getCtx(), getC_Location_ID(),
					get_TrxName());
		}

		return mLocation;
	}

	/**
	 * 
	 * @param hl7
	 *            -
	 *            C.Location.address1^^c_location.city^C_region.name^c_location
	 *            .zip^C_Country.hl7code^C_location.addresstype
	 * @return
	 */
	public String getLocationStr(final boolean hl7) {
		if (getLocation() == null) {
			return "";
		}
		final StringBuilder address = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		address.append(StringUtils.isBlank(mLocation.getNumIn()) ? ""
				: (mLocation.getNumIn() + " "));
		address.append(StringUtils.isBlank(mLocation.getNumExt()) ? ""
				: (mLocation.getNumExt()) + " ");
		address.append(StringUtils.isBlank(mLocation.getAddress1()) ? ""
				: mLocation.getAddress1());
		address.append(hl7 ? "^^" : address.length() > 0 ? ", " : "");
		// city
		address.append(StringUtils.isBlank(mLocation.getCity()) ? ""
				: mLocation.getCity());
		address.append(hl7 ? "^" : ", ");
		// region
		address.append(mLocation.getRegion() == null ? "" : mLocation
				.getRegion().getName());

		if (hl7) {
			// postal
			address.append("^");
			address.append(mLocation.getPostal() == null ? "" : mLocation
					.getPostal());
			address.append("^");
			// acountry hl7
			address.append(mLocation.getCountry() == null ? "" : mLocation
					.getCountry().getHL7Code());
			address.append("^");
			// address type
			address.append(StringUtils.isBlank(mLocation.getAddressType()) ? ""
					: mLocation.getAddressType());
		} else {
			// country
			address.append(", ");
			address.append(mLocation.getCountry() == null ? "" : mLocation
					.getCountry().getCountryCode());
		}
		return address.toString();
	}

	/**
	 * test
	 * 
	 * @param ctx
	 * @param warehouseID
	 * @param trxName
	 * @return
	 */
	public static boolean isManualTestResults(final Properties ctx,
			final int warehouseID, final String trxName) {
		final int no = DB
				.getSQLValue(
						trxName,
						"SELECT COUNT(*) FROM M_Warehouse WHERE AddTestResult = 'Y' AND Genera_HL7 = 'N' AND M_Warehouse_ID = ? ",
						warehouseID);
		return no > 0;
	}

	/**
	 * test
	 * 
	 * @return
	 */
	public boolean isManualTestResults() {
		return super.isAddTestResult() && !super.isGenera_HL7();
	}

	/**
	 * Org trx
	 * 
	 * @return
	 */
	public int getAD_OrgTrx_ID() {
		int index = 0;
		try {
			index = MEXMEEstServ
					.getEstServAlmOrgTrx(getCtx(), getM_Warehouse_ID());
		} catch (Exception e) {
			e.printStackTrace();
			index = 0;
		}
		return index;
	}
	
	/**
	 * getWarehouseByType
	 * 
	 * @param ctx
	 * @param type
	 * @return list
	 * @throws SQLException
	 */
	public static List<MWarehouse> getWarehouseByType(final Properties ctx, final String type, String trxName) {
		return new Query(ctx, Table_Name, " TYPE=? ", trxName)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setParameters(type)//
		.setOrderBy(" Name ")//
		.list();
	}

	/**
	 * getWarehouseByType
	 * 
	 * @param ctx
	 * @param type
	 * @return list
	 * @throws SQLException
	 * @deprecated use getWarehouseByType
	 */
	public static List<LabelValueBean> getWarehouseByType(final Properties ctx,
			final String type) throws SQLException {
		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT * FROM M_WAREHOUSE WHERE TYPE = ? AND ISACTIVE = 'Y' ");
		final String sq = MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), Table_Name);

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sq, null);
			pstmt.setString(1, type);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new LabelValueBean(rset.getString(COLUMNNAME_Name),
						String.valueOf(rset.getInt(COLUMNNAME_M_Warehouse_ID))));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getWarehouseByType", e);
		} finally {
			DB.close(rset, pstmt);
			pstmt = null;
			rset = null;
		}
		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
	public static List<KeyNamePair> getAll(final Properties ctx, String trxName) {
		List<KeyNamePair> list = new ArrayList<KeyNamePair>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT M_WAREHOUSE_ID, NAME FROM M_WAREHOUSE WHERE ISACTIVE = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" order by NAME ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getAll", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Obtiene las estacione los almacenes dependiendo de la estacion de
	 * servicio y el tipo de almacen proporcinado
	 * 
	 * @param ctx
	 * @param type
	 *            tipo de almacen
	 * @param serviceUnit
	 *            Estacion de servicio
	 * @return Lista de labelvaluebean con los almacenes encontrados.
	 * @throws SQLException
	 */
	public static List<LabelValueBean> getWHBySUAndType(final Properties ctx,
			final String type, final int serviceUnit) throws SQLException {
		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT WH.* FROM M_WAREHOUSE WH ")
				.append(" INNER JOIN EXME_ESTSERVALM SU ON (SU.M_WAREHOUSE_ID = WH.M_WAREHOUSE_ID AND SU.ISACTIVE = 'Y') ")
				.append(" INNER JOIN EXME_ESTSERV E ON (SU.EXME_ESTSERV_ID = E.EXME_ESTSERV_ID AND E.ISACTIVE = 'Y') ")
				.append(" WHERE WH.TYPE = ? AND WH.ISACTIVE = 'Y' ");
		if (serviceUnit > 0) {
			sql.append(" AND E.EXME_ESTSERV_ID = ? ");
		}

		sql.append(MEXMELookupInfo
				.addAccessLevelSQL(ctx, " ", Table_Name, "WH"));

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, type);
			if (serviceUnit > 0) {
				pstmt.setInt(2, serviceUnit);
			}

			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new LabelValueBean(rset.getString(COLUMNNAME_Name),
						String.valueOf(rset.getInt(COLUMNNAME_M_Warehouse_ID))));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getWHBySUAndType", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return list;
	}

	/**
	 * Regresa el intervalo del almacen
	 * 
	 * @param defaultWhenNull
	 *            si es true y el intervalo es menor o igual a 0 regresa el
	 *            valor por defecto de 30.
	 * @return
	 */
	public int getIntervalo(final boolean defaultWhenNull) {
		if (super.getIntervalo() <= 0 && defaultWhenNull) {
			return 30; // Lama
		}
		return super.getIntervalo();
	}

	/**
	 * Organizacion transaccional del almacen
	 * 
	 * @param ctx
	 * @param almacenId
	 * @return
	 * @throws Exception
	 */
	public static int getOrgTrxId(final Properties ctx, final int almacenId) {
//		int valreturn = 0;
		final StringBuilder sql = new StringBuilder()
				.append(" SELECT org.AD_Org_ID  ")
				.append(" FROM M_Warehouse w ")
				.append(" INNER JOIN EXME_EstServAlm esa ON esa.M_Warehouse_ID = w.M_Warehouse_ID  ")
				.append(" INNER JOIN EXME_EstServ     es ON es.EXME_EstServ_ID = esa.EXME_EstServ_ID ")
				.append(" INNER JOIN AD_Org          org ON org.AD_Org_ID      = es.AD_OrgTrx_ID ")
				.append(" WHERE w.M_Warehouse_ID = ? ")
				.append(" AND esa.IsActive = 'Y' ")
				.append(" AND es.IsActive = 'Y' ")
				.append(" AND org.IsActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Warehouse", "w"));

		return DB.getSQLValue(null, sql.toString(), almacenId);
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, almacenId);
//			rset = pstmt.executeQuery();
//			if (rset.next()) {
//				valreturn = rset.getInt(1);
//			}
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, "getAlmacenesxEst" + sql, e.getMessage());
////			throw new Exception("error.getWarehouseRel");
//		} finally {
//			DB.close(rset, pstmt);
//		}
//		return valreturn;
	}

	/**
	 * Listado de almacenes que pueden surtir una solicitud de producto
	 * 
	 * @param ctx
	 *            Contexto
	 * @return list Listado de Almacenes para surtir una solicitud
	 */
	public static List<MWarehouse> getWarehouseAss(final Properties ctx) {
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY).append(" TYPE IN ('")
				.append(TYPE_WarehouseSupply).append("','")
				.append(TYPE_SupplyPlusSterilization).append("') ");

		return new org.compiere.model.Query(ctx, MWarehouse.Table_Name,
				sql.toString(), null).setOnlyActiveRecords(true)
				.addAccessLevelSQL(true).list();
	}

	/**
	 * getSterilization
	 * 
	 * @param ctx
	 * @return
	 */
	public static int getSterilization(final Properties ctx) {
		int key = -1;
		// List<LabelValueBean> lstAlmacenes;
		try {
			final List<LabelValueBean> lstAlmacenes = Datos.getEstServAlm(ctx,
					Env.getEXME_EstServ_ID(ctx), true);
			for (int j = 0; j < lstAlmacenes.size(); j++) {
				final MWarehouse mWarehouse = MWarehouse.get(ctx,
						Integer.parseInt(lstAlmacenes.get(j).getValue()));
				if (MWarehouse.TYPE_Sterilization
						.equalsIgnoreCase(mWarehouse.getType())) {
					key = mWarehouse.getM_Warehouse_ID();
					break;
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		}

		return key;
	}

	/**
	 * Obtener id de almacen por medio del localizador
	 * @param ctx Contexto de la app
	 * @param M_Locator_ID Localizador de referencia
	 * @param trxName Trx
	 * @return ID del almacen o -1 de no tener
	 */
	public static int getIdFromLocator(final Properties ctx, final int M_Locator_ID, final String trxName) {
		return DB.getSQLValue(trxName, "select M_Warehouse_ID from M_Locator where M_Locator_ID = ?", M_Locator_ID);
	}

	/**
	 * Obtiene el almacen
	 * 
	 * @param ctx
	 * @param M_Movement_ID
	 * @param trxName
	 * @return
	 */
	public static MWarehouse getFromLocator(final Properties ctx,
			final int M_Locator_ID, final String trxName) {

		return new Query(ctx, MWarehouse.Table_Name,
				" M_Locator.M_Locator_ID = ? ", null)
				//
				.setJoins(
						new StringBuilder(
								" INNER JOIN M_Locator ON M_Locator.M_Warehouse_ID = M_Warehouse.M_Warehouse_ID "))
				.setOrderBy("Created") //
				.setParameters(M_Locator_ID) //
				.setOnlyActiveRecords(true) //
				.addAccessLevelSQL(true).first();
		//
		//
		// MWarehouse retValue = null;
		//
		// StringBuilder sql = new StringBuilder("SELECT M_Warehouse.* ")
		// .append("FROM M_Warehouse ")
		// .append("INNER JOIN M_Locator ON M_Locator.M_Warehouse_ID = M_Warehouse.M_Warehouse_ID ")
		// .append("WHERE M_Warehouse.IsActive = 'Y' ")
		// .append("AND M_Locator.M_Locator_ID = ? ");
		//
		// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
		// sql.toString(), "M_Warehouse"));
		//
		// PreparedStatement pstmt = null;
		// ResultSet rs = null;
		// try {
		// pstmt = DB.prepareStatement(sql.toString(), trxName);
		// pstmt.setInt(1, M_Locator_ID);
		// rs = pstmt.executeQuery();
		//
		// if (rs.next()) {
		// retValue = new MWarehouse(ctx, rs, trxName);
		// }
		//
		// } catch (Exception e) {
		// slog.log(Level.SEVERE, sql.toString(), e);
		// } finally {
		// DB.close(rs, pstmt);
		// }
		// return retValue;
	}

	/**
	 * Get Warehouses for Org
	 * 
	 * @param ctx
	 *            context
	 * @param AD_Org_ID
	 *            id
	 * @return warehouse
	 */
	public static List<LabelValueBean> getLstForOrgLVB(final Properties ctx,
			final boolean blanco) {
		final StringBuilder sql = new StringBuilder(100);
		sql.append(" SELECT Value||'-'||Name AS nombre, M_Warehouse_ID ")
				.append(" FROM M_Warehouse WHERE IsActive = 'Y' ")
				.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(
						ctx, " ", "M_Warehouse"))).append(" ORDER BY Value ");

		return MWarehouse.getLstForOrgLVB(ctx, sql.toString(), blanco, null);
	}

	/**
	 * 
	 * @param ctx
	 * @param almacen
	 * @param blanco
	 * @return
	 */
	public static List<LabelValueBean> getLstProductClassLVB(
			final Properties ctx, final int almacen, final boolean blanco) {
		final StringBuilder sql = new StringBuilder(100);
		sql.append(" SELECT w.Value||'-'||w.Name AS nombre, w.M_Warehouse_ID ")
				.append(" FROM M_Warehouse w ")
				// Almacenes que tenga una solicitud. Sin estatus para la
				// funcionalidad de editar
				.append(" INNER JOIN EXME_ActPacienteIndH apih ON apih.M_Warehouse_Sol_ID  = w.M_Warehouse_ID ")
				.append(" WHERE w.IsActive = 'Y' ")
				.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(
						ctx, " ", "M_Warehouse", "w")))
				// Almacen que aplica es el mismo de login
				.append(" AND apih.M_Warehouse_ID = ? ")
				.append(" GROUP BY w.Value, w.Name, w.M_Warehouse_ID ")
				.append(" ORDER BY w.Value ");
		final Object[] params = new Object[] { almacen };

		return MWarehouse.getLstForOrgLVB(ctx, sql.toString(), blanco, params);
	}
	
	/**
	 * Obtener el almacen relacionado en consigna que tenga el producto deseado
	 * 
	 * @param ctx
	 *            Contexto
	 * @param warehouseId
	 *            Almacen principal
	 * @param productId
	 *            Producto a buscar
	 * @param trxName
	 *            Nombre de trx
	 * @return Casilla 1 = Id del almacen o -1 si no hay, Casilla 2 = Localizador relacionado o -1
	 */
	public static int[] getWarehouseRel(Properties ctx, int warehouseId, int productId, String trxName) {
		int[] arr = new int[] { -1, -1 };

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  w.m_warehouse_id, wr.m_locator_id ");
		sql.append("FROM ");
		sql.append("  EXME_WarehouseRel wr ");
		sql.append("  INNER JOIN m_warehouse w ");
		sql.append("  ON (wr.M_WarehouseRel_ID = w.m_warehouse_id AND ");
		sql.append("  w.consigna ='Y' ) ");
		sql.append("  INNER JOIN m_replenish r ");
		sql.append("  ON (wr.M_WarehouseRel_ID = r.m_warehouse_id AND ");
		sql.append("  r.m_product_id = ? AND r.isActive = 'Y') "); 
		sql.append("WHERE ");
		sql.append("  wr.m_warehouse_id = ? ");// almacen origen (Almacen que va a pedir a .......??)

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
 			pstmt.setInt(2, warehouseId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				arr[0] = rs.getInt(1);
				arr[1] = rs.getInt(2);

				if (arr[1] >= 0) {
					arr[1] = MLocator.getLocatorID(Env.getCtx(), arr[0], null);
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString() + "\n Params productId: " + productId + " warehouseId: " + warehouseId, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return arr;
	}

	/**
	 * Get Warehouses for Org
	 * 
	 * @param ctx
	 *            context
	 * @param params
	 * @param AD_Org_ID
	 *            id
	 * @return warehouse
	 */
	public static List<LabelValueBean> getLstForOrgLVB(final Properties ctx,
			final String query, final boolean blanco, final Object[] params) {
		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(query, null);
			DB.setParameters(pstmt, params);
			rset = pstmt.executeQuery();

			if (blanco) {
				list.add(new LabelValueBean(" ", "0"));
			}

			while (rset.next()) {
				list.add(new LabelValueBean(rset.getString(1), String
						.valueOf(rset.getInt(2))));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, query, e);
		} finally {
			DB.close(rset, pstmt);
		}
		return list;
	} // get

	/**
	 * Obtiene y modifica el almacen y estacion de servicio en contexto
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MWarehouse getForOrg(final Properties ctx,
			final String trxName) {
		MWarehouse warehouse = null;
		final MEXMEEstServ estServ = MEXMEEstServ.getFirstEstServ(ctx, trxName);
		if (estServ != null) {
			final MWarehouse[] arr = estServ.getWarehouseRel(null);
			if (arr.length > 0) {
				warehouse = arr[0];
				Env.setContext(ctx, "#EXME_EstServ_ID",
						estServ.getEXME_EstServ_ID());
				Env.setContext(ctx, "#M_Warehouse_ID",
						warehouse.getM_Warehouse_ID());
			}
		}
		return warehouse;
	}

	public void setProductClassWhsID(final int productClassWhsID) {
		this.productClassWhsID = productClassWhsID;
	}

	public int getProductClassWhsID() {
		return productClassWhsID;
	}

	public void setSelected(final boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}
	
	public MBPartner getBPartner() {
		if(mBPartner==null){
			mBPartner = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		}
		return mBPartner;
	}

	/**
	 * Enlistar los almacenes por tipo
	 * @param ctx
	 * @param type
	 * @param where
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static List<ValueNamePair> getWarehouseByType(final Properties ctx,
			final String type,final String where,final List<?> params) throws SQLException {
		final List<ValueNamePair> list = new ArrayList<ValueNamePair>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append("SELECT * FROM M_Warehouse WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", Table_Name))
		.append(type ==null?StringUtils.EMPTY:" AND Type = ? ")
		.append(where==null?StringUtils.EMPTY:where)
		.append(" ORDER BY Name ");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new ValueNamePair(String.valueOf(rset.getInt(COLUMNNAME_M_Warehouse_ID))
						, rset.getString(COLUMNNAME_Name)
						));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getWarehouseByType", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return list;
	}
	
	/**
	 * Número de almacenes de consigna relacionados
	 * 
	 * @return Cantidad de almacenes relacionados de consigna
	 */
	public int getConsignationWarehouseCount() {
		return getConsignationWarehouseCount(getCtx(), getM_Warehouse_ID(), null);
	}
	
	/**
	 * Número de almacenes de consigna relacionados
	 * 
	 * @param ctx
	 *            Contexto
	 * @param warehouseId
	 *            Almacen
	 * @param trxName
	 *            Trx
	 * @return Cantidad de almacenes relacionados de consigna
	 */
	public static int getConsignationWarehouseCount(Properties ctx, int warehouseId, String trxName) {
		int count = 0;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  COUNT(*) ");
		sql.append("FROM ");
		sql.append("  Exme_WarehouseRel rel ");
		sql.append("  INNER JOIN m_warehouse w ");
		sql.append("  ON rel.m_warehouserel_id = w.m_warehouse_id ");
		sql.append("WHERE ");
		sql.append("  rel.m_warehouse_id = ? AND ");
		sql.append("  w.consigna = 'Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, warehouseId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return count;
	}
	
	/**
	 * Tiene al menos un almacen de consigna?
	 * 
	 * @param ctx
	 *            Contexto
	 * @param warehouseId
	 *            Almacen a revisar
	 * @param trxName
	 *            Trx
	 * @return Si/No
	 */
	public static boolean hasConsignmentWarehouse(Properties ctx, int warehouseId, String trxName) {
		boolean retValue = false;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  wr.m_warehouse_id ");
		sql.append("FROM ");
		sql.append("  exme_warehouserel wr ");
		sql.append("  INNER JOIN m_warehouse w ");
		sql.append("  ON wr.m_warehouserel_id = w.m_warehouse_id ");
		sql.append("WHERE ");
		sql.append("  wr.m_warehouse_id = ? AND ");
		sql.append("  w.consigna = 'Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, warehouseId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = true;
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}

} // MWarehouse