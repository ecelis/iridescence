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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

/**
 * Price List Model
 * 
 * @author Jorg Janke
 * @version $Id: MPriceList.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MPriceList extends X_M_PriceList {
	/** serialVersionUID */
	private static final long serialVersionUID = -7077582019730043556L;

	/**
	 * Get Price List (cached)
	 * 
	 * @param ctx
	 *            context
	 * @param M_PriceList_ID
	 *            id
	 * @param trxName
	 *            transaction
	 * @return PriceList
	 */
	public static MPriceList get(Properties ctx, int M_PriceList_ID,
			String trxName) {
		Integer key = new Integer(M_PriceList_ID);
		MPriceList retValue = (MPriceList) s_cache.get(key);
		if (retValue == null
		// Expert : Lama (verificar la sesion, para usar o no el objeto del
		// cache)
				|| (Env.getContextAsInt(ctx, "#AD_Session_ID") != Env
						.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) // lama
		{
			retValue = new MPriceList(ctx, M_PriceList_ID, trxName);
			s_cache.put(key, retValue);
		}
		return retValue;
	} // get

	/**
	 * Get Default Price List for Client (cached)
	 * 
	 * @param ctx
	 *            context
	 * @param IsSOPriceList
	 *            SO or PO
	 * @return PriceList or null
	 */
	public static MPriceList getDefault(Properties ctx, boolean IsSOPriceList) {
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		MPriceList retValue = null;
		// Search for it in cache
		Iterator<?> it = s_cache.values().iterator();
		while (it.hasNext()) {
			retValue = (MPriceList) it.next();
			if (retValue.isDefault()
					&& retValue.getAD_Client_ID() == AD_Client_ID)
				return retValue;
		}

		/** Get from DB **/
		retValue = null;
		String sql = "SELECT * FROM M_PriceList " + "WHERE AD_Client_ID=?"
				+ " AND IsDefault='Y'" + " AND IsSOPriceList='Y'"
				+ "ORDER BY M_PriceList_ID";
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MPriceList(ctx, rs, null);
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}
		// Return value
		if (retValue != null) {
			Integer key = new Integer(retValue.getM_PriceList_ID());
			s_cache.put(key, retValue);
		}
		return retValue;
	} // getDefault

	/**
	 * Get Standard Currency Precision
	 * 
	 * @param ctx
	 *            context
	 * @param M_PriceList_ID
	 *            price list
	 * @return precision
	 */
	public static int getStandardPrecision(Properties ctx, int M_PriceList_ID) {
		MPriceList pl = MPriceList.get(ctx, M_PriceList_ID, null);
		return pl.getStandardPrecision();
	} // getStandardPrecision

	/**
	 * Get Price Precision
	 * 
	 * @param ctx
	 *            context
	 * @param M_PriceList_ID
	 *            price list
	 * @return precision
	 */
	public static int getPricePrecision(Properties ctx, int M_PriceList_ID) {
		MPriceList pl = MPriceList.get(ctx, M_PriceList_ID, null);
		return pl.getPricePrecisionInt();
	} // getPricePrecision

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MPriceList.class);
	/** Cache of Price Lists */
	private static CCache<Integer, MPriceList> s_cache = new CCache<Integer, MPriceList>(
			"M_PriceList", 5);

	/**************************************************************************
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param M_PriceList_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MPriceList(Properties ctx, int M_PriceList_ID, String trxName) {
		super(ctx, M_PriceList_ID, trxName);
		if (M_PriceList_ID == 0) {
			setEnforcePriceLimit(false);
			setIsDefault(false);
			setIsSOPriceList(false);
			setIsTaxIncluded(false);
			setPricePrecision(2); // 2
			// setName (null);
			// setC_Currency_ID (0);
		}
	} // MPriceList

	/**
	 * Load Cosntructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MPriceList(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MPriceList

	/** Cached PLV */
	private MPriceListVersion m_plv = null;
	/** Cached Precision */
	private Integer m_precision = null;

	/**
	 * Get Price List Version
	 * 
	 * @param valid
	 *            date where PLV must be valid or today if null
	 * @return PLV
	 */
	public MPriceListVersion getPriceListVersion(Timestamp valid) {
		if (valid == null)
			valid = DB.getTimestampForOrg(getCtx());
		// Assume there is no later
		if (m_plv != null && m_plv.getValidFrom().before(valid))
			return m_plv;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM M_PriceList_Version");
		sql.append(" WHERE M_PriceList_ID=? ");
		if (DB.isOracle()) {
			sql.append(" AND TRUNC(ValidFrom)<=? AND IsActive='Y'");
		} else if (DB.isPostgreSQL()) {
			sql.append(" AND DATE_TRUNC('day', ValidFrom)<=? AND IsActive='Y'");
		}
		sql.append(" ORDER BY ValidFrom DESC");
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getM_PriceList_ID());
			pstmt.setTimestamp(2, valid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				m_plv = new MPriceListVersion(getCtx(), rs, get_TrxName());
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}
		if (m_plv == null)
			log.warning("None found M_PriceList_ID=" + getM_PriceList_ID()
					+ " - " + valid + " - " + sql);
		else
			log.fine(m_plv.toString());
		return m_plv;
	} // getPriceListVersion

	// //TODO:Documentar Metodo
	// public Vector<OptionItem> getPriceList (){
	// Vector<OptionItem> priceList=null;
	// StringBuilder sb= new
	// StringBuilder("select m_pricelist_id,name from m_pricelist where isactive='Y'");
	// StringBuilder sql = new
	// StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sb.toString(),
	// this.get_TableName()));
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	//
	// try{
	//
	// pstmt = DB.prepareStatement(sql.toString(), null);
	//
	// rs = pstmt.executeQuery();
	// priceList = new Vector<OptionItem>();
	//
	// while (rs.next()){
	// priceList.add(new OptionItem(rs.getString(1),rs.getString(2)));
	// }
	// }catch(SQLException e){
	// e.printStackTrace();
	// }
	// finally{
	// try{
	// if(pstmt!=null)
	// pstmt.close();
	// if(rs!=null)
	// rs.close();
	// }catch(SQLException e){
	// log.log(Level.SEVERE, "getPriceList", e);
	// }
	// }
	//
	// return priceList;
	// }

	/**
	 * Get Standard Currency Precision
	 * 
	 * @return precision
	 */
	public int getStandardPrecision() {
		if (m_precision == null) {
			MCurrency c = MCurrency.get(getCtx(), getC_Currency_ID());
			m_precision = new Integer(c.getStdPrecision());
		}
		return m_precision.intValue();
	} // getStandardPrecision

	/**
	 * Set Price Precision
	 * 
	 * @param PricePrecision
	 *            precision
	 */
	public void setPricePrecision(int PricePrecision) {
		setPricePrecision(new BigDecimal(PricePrecision));
	} // setPricePrecision

	/**
	 * Get Price Precision as int
	 * 
	 * @return precision - -1 for none
	 */
	public int getPricePrecisionInt() {
		BigDecimal bd = getPricePrecision();
		if (bd == null)
			return -1;
		return bd.intValue();
	} // getPricePrecisionInt

	// /**
	// * Metodo que retorna los nombres de las listas de precios disponibles
	// * @return Vector<OptionItem>
	// */
	// public Vector<OptionItem> getPriceLists (){
	//
	// Vector<OptionItem> priceLists = null;
	// StringBuilder sb = new StringBuilder("SELECT M_Pricelist_ID,Name ");
	// sb.append("FROM M_Pricelist ORDER BY Name ASC");
	// StringBuilder sql = new
	// StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sb.toString(),
	// this.get_TableName()));
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// try{
	// pstmt = DB.prepareStatement(sql.toString(), null);
	// rs = pstmt.executeQuery();
	// priceLists = new Vector<OptionItem>();
	// priceLists.add(new OptionItem("-1",""));
	// while (rs.next()){
	// priceLists.add(new OptionItem(rs.getString(1), rs.getString(2)));
	// }
	// }catch(Exception e){
	// s_log.log(Level.SEVERE, "getPriceLists", e);
	// }finally{
	// try{
	// if (pstmt != null)
	// pstmt.close();
	// if (rs != null)
	// rs.close();
	// }catch (SQLException e){
	// s_log.log(Level.SEVERE, "getPriceLists", e);
	// }
	// }
	// return priceLists;
	// }

	public static int get(Properties ctx, String name, String trxName) {
		StringBuilder st = new StringBuilder();
		st.append("select M_PriceList_ID from M_PriceList where name = ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				st.toString(), "M_PriceList"));
		int id = DB.getSQLValue(trxName, st.toString(), name);
		return id;
	}

	// /**
	// * Obtiene la lista de precios
	// * @return MPriceList
	// */
	// public static MPriceList getPriceList(Properties ctx, MEXMEPaciente
	// paciente){
	//
	// if (paciente == null) {
	// return null;
	// }
	//
	// int precioLista = 0;
	// MPriceList listaPrecios = null;
	//
	// // buscamos la lista de precios para el BPartner del paciente
	// if (paciente.getC_BPartner_Seg_ID() > 0) {
	// precioLista = new MBPartner(ctx, paciente.getC_BPartner_Seg_ID(),
	// null).getM_PriceList_ID();
	// } else {
	// precioLista = new MBPartner(ctx, paciente.getC_BPartner_ID(),
	// null).getM_PriceList_ID();
	// }
	//
	// // si no tiene configurada una lista, busca la configuracion de precios
	// if (precioLista <= 0) {
	// listaPrecios = MEXMEConfigPre.getPriceList(ctx, null);
	// if (listaPrecios == null || (listaPrecios != null &&
	// listaPrecios.getM_PriceList_ID() <= 0)) {// si no esta configurada,
	// aplica la lista
	// // que tiene por default
	// precioLista = Env.getM_PriceList_ID(ctx);
	// if (precioLista > 0) {
	// listaPrecios = MPriceList.get(ctx, precioLista, null);
	// } else {
	// listaPrecios = null;
	// }
	// }
	// } else {
	// listaPrecios = MPriceList.get(ctx, precioLista, null);
	// }
	//
	// return listaPrecios;
	//
	// }

	// /**
	// * VERSIONES DE LISTA DE PRECIOS POS
	// * @param ctx
	// * @param M_PriceList_ID
	// * @return
	// */
	// public static List<LabelValueBean> getListaPrecios(Properties ctx, int
	// M_PriceList_ID){
	// List<LabelValueBean> priceList=new ArrayList<LabelValueBean>();
	// StringBuilder sql= new
	// StringBuilder(" SELECT M_PriceList_Version_ID, Name ");
	// sql.append(" FROM m_pricelist_Version where isactive='Y'");
	// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
	// "M_PriceList_Version"));
	// sql.append(" AND M_PriceList_ID = ? order by validfrom desc ");
	//
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	//
	// try{
	//
	// pstmt = DB.prepareStatement(sql.toString(), null);
	// pstmt.setInt(1, M_PriceList_ID);
	// rs = pstmt.executeQuery();
	//
	// while (rs.next()){
	// priceList.add(new LabelValueBean(rs.getString(2),
	// String.valueOf(rs.getInt(1))));
	// }
	// }catch(SQLException e){
	// e.printStackTrace();
	// }
	// finally{
	// DB.close(rs,pstmt);
	// }
	//
	// return priceList;
	// }

	/**
	 * Metodo que aplica a los precios de venta el % de aumento del factor
	 * precio elegido
	 * 
	 * @param ctx
	 * @param factorID
	 *            factor precio a aplicar
	 * @param priceListID
	 *            lista de precios sobre la que se generara la version
	 * @param discountID
	 *            esquema de descuento a utilizar en caso de que se requiera
	 *            crear una nueva version
	 * @param versionID
	 *            identificador de la version a utilizar, si es 0 se creara una
	 *            nueva version
	 * @param categoryID
	 *            identificador de la categoria de los productos a afectar
	 *            (puede ser 0)
	 * @param productID
	 *            identificador del producto especifico a afectar (puede ser 0)
	 * @return
	 */
	public static boolean aplicarFactorPrecio(Properties ctx, int factorID,
			int priceListID, int discountID, int versionID, int categoryID,
			int productID) {

		boolean success = true;
		Trx m_trx = null;

		try {

			m_trx = Trx.get(Trx.createTrxName("FP"), true);
			String trxName = m_trx.getTrxName();

			// obtenemos los productos a afectar
			List<MProduct> lstProducts = MProduct.getProductosFP(ctx,
					versionID, categoryID, productID, null);

			// obtenemos o creamos la version a utilizar
			MPriceListVersion version;
			if (versionID > 0) { // se obtiene la version existente
				version = new MPriceListVersion(ctx, versionID, null);

			} else {// se crea una version nueva (no habra productos)
				version = new MPriceListVersion(ctx, 0, trxName);
				version.setM_PriceList_ID(priceListID);
				version.setName(Env.getCurrentDate().toString());
				version.setValidFrom(Env.getCurrentDate());
				version.setM_DiscountSchema_ID(discountID);

				if (!version.save()) {
					success = false;
					Object[] args = { version.getName() };
					s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null,
							"error.version.guardar", args));

				} else {
					versionID = version.getM_PriceList_Version_ID();
				}

			}

			if (success) {
				// se obtiene el factor precio a aplicar y su detalle
				List<MEXMEFactorPreDet> lstFactorPreDet = MEXMEFactorPre
						.factorPrecioDetalle(Env.getCtx(), factorID, null);

				// iteramos los productos
				for (MProduct product : lstProducts) {

					// revisamos si el producto tiene registros en PO
					MProductPO productPO = MProductPO.getLastPO(ctx,
							product.getM_Product_ID(), trxName);

					if (productPO == null) {
						Object[] args = { product.getName() };
						s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null,
								"error.producto.precioCompra", args));
						continue;
					}

					BigDecimal priceLst = BigDecimal.ZERO;
					BigDecimal priceLstVol = BigDecimal.ZERO;

					// revisamos si el producto existe en la version
					MProductPrice productPrice = MEXMEProductPrice.get(ctx,
							product.getM_Product_ID(), versionID, trxName);

					if (productPrice == null) { // si no existe se crea el
												// producto en la version
						productPrice = new MProductPrice(ctx, versionID,
								product.getM_Product_ID(), trxName);
						productPrice.setC_UOM_ID(product.getC_UOM_ID());
						productPrice.setC_UOMVolume_ID(product
								.getC_UOMVolume_ID());
					}

					// obtenemos los precios de compra del producto
					// priceLstVol = productPO.getPriceLastPO();
					priceLst = productPO.getPriceLastPO();

					if (product.getC_UOM_ID() == product.getC_UOMVolume_ID()) {
						priceLstVol = productPO.getPriceLastPO();
					} else {
						MUOMConversion rates = MEXMEUOMConversion
								.validateConversions(ctx,
										product.getM_Product_ID(),
										product.getC_UOMVolume_ID(), null);

						if (rates == null) {
							priceLstVol = productPO.getPriceLastPO();
						} else {
							priceLstVol = priceLstVol
									.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO
									: MEXMEUOMConversion.convertProductFrom(
											ctx, product.getM_Product_ID(),
											product.getC_UOMVolume_ID(),
											priceLstVol, null, false);
						}
					}

					// verificamos si el precio de empaque cae en un factor
					BigDecimal nivelInferior = new BigDecimal(0);
					BigDecimal percentageVol = new BigDecimal(0);
					BigDecimal percentageMin = new BigDecimal(0);
					boolean hasFactorVol = false;
					boolean hasFactorMin = false;

					// obtenemos el porcentaje de incremento para el precio de
					// volumen del producto
					for (MEXMEFactorPreDet det : lstFactorPreDet) {
						// si es mayor que el nivel inferior y ademas
						// es igual o menor que el nivel superior
						// se encuentra dentro del rango
						if (priceLstVol.compareTo(nivelInferior) == 1
								&& (priceLstVol.compareTo(det
										.getNivelSuperior()) == 0 || priceLstVol
										.compareTo(det.getNivelSuperior()) == -1)) {
							percentageVol = det.getPorcentaje();
							hasFactorVol = true;
							break;

						}
						nivelInferior = det.getNivelSuperior();
					}

					nivelInferior = new BigDecimal(0);

					// obtenemos el porcentaje de incremento para el precio
					// minimo del producto
					for (MEXMEFactorPreDet det : lstFactorPreDet) {
						// si es mayor que el nivel inferior y ademas
						// es igual o menor que el nivel superior
						// se encuentra dentro del rango
						if (priceLst.compareTo(nivelInferior) == 1
								&& (priceLst.compareTo(det.getNivelSuperior()) == 0 || priceLst
										.compareTo(det.getNivelSuperior()) == -1)) {
							percentageMin = det.getPorcentaje();
							hasFactorMin = true;
							break;

						}
						nivelInferior = det.getNivelSuperior();
					}

					// establecemos el precio de venta del paquete
					if (hasFactorVol) {

						// si cuenta con un porcentaje, se calcula el nuevo
						// precio del producto
						BigDecimal percentagemultiplyFactor = percentageVol
								.divide(new BigDecimal(100));
						percentagemultiplyFactor = percentagemultiplyFactor
								.add(new BigDecimal(1));
						BigDecimal newPrice_Vol = priceLstVol
								.multiply(percentagemultiplyFactor);

						productPrice.setPriceList_Vol(newPrice_Vol);
						productPrice.setPriceStd_Vol(newPrice_Vol);
						productPrice.setPriceLimit_Vol(newPrice_Vol);

					} else {
						// si no cae en ningun rango del factor precio, se deja
						// el mismo precio de compra
						productPrice.setPriceList_Vol(priceLstVol);
						productPrice.setPriceStd_Vol(priceLstVol);
						productPrice.setPriceLimit_Vol(priceLstVol);

					}

					// establecemos el precio de venta de la unidad minima
					if (hasFactorMin) {

						// si cuenta con un porcentaje, se calcula el nuevo
						// precio del producto
						BigDecimal percentagemultiplyFactor = percentageMin
								.divide(new BigDecimal(100));
						percentagemultiplyFactor = percentagemultiplyFactor
								.add(new BigDecimal(1));
						BigDecimal newPriceMin = priceLst
								.multiply(percentagemultiplyFactor);

						productPrice.setPriceList(newPriceMin);
						productPrice.setPriceStd(newPriceMin);
						productPrice.setPriceLimit(newPriceMin);

					} else {
						// si no cae en ningun rango del factor precio, se deja
						// el mismo precio de compra
						productPrice.setPriceList_Vol(priceLst);
						productPrice.setPriceStd_Vol(priceLst);
						productPrice.setPriceLimit_Vol(priceLst);

					}

					if (!productPrice.save()) {
						success = false;
						Object[] args = { product.getName() };
						s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null,
								"error.precio.guardar", args));
					}

				}

				if (success) {
					Trx.commit(m_trx);
				} else {
					Trx.rollback(m_trx);
				}

			}

		} catch (Exception ex) {
			if (m_trx != null) {
				m_trx.rollback();
			}
		} finally {
			if (m_trx != null) {
				m_trx.close();
			}
		}

		return success;

	}
} // MPriceList

