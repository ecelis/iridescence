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
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trace;

/**
 * Product Price Calculations
 * 
 * @author Jorg Janke
 * @version $Id: MProductPricing.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class MProductPricing {
	
	private final int mMProductID;
	private final int mCBPartnerID;
	private BigDecimal mQty = Env.ONE;
	private boolean misSOTrx = true;
	//
	private int mMPriceListID = 0;
	private int mMPriceListVerID = 0;
	private Timestamp mPriceDate;
	/** Precision -1 = no rounding */
	private int mprecision = -1;

	private boolean mcalculated = false;
	private Boolean mfound = null;

	private BigDecimal mPriceList = Env.ZERO;
	private BigDecimal mPriceStd = Env.ZERO;
	private BigDecimal mPriceLimit = Env.ZERO;
	private int mCCurrencyID = 0;
	private boolean menforcePLimit = false;
	private int mCUOMID = 0;
	private int mCUOMVolumeID = 0;
	private int mMProdCategoID;
	private boolean mdiscountSchema = false;
	private boolean misTaxIncluded = false;

	private boolean isForce = false;
	private boolean isUOMVol = false;
	// private boolean isSalePublic = false;
	/** Codigo de producto en la lista de precios */
	private String sProductValue = null;
	/** Descripción del producto en la lista de precios */
	private String sProductDesc = null;
	/** Logger */
	protected CLogger log = CLogger.getCLogger(getClass());
	private Properties ctx = Env.getCtx();

	
	/**
	 * Constructor
	 * 
	 * @param M_Product_ID
	 *            product
	 * @param C_BPartner_ID
	 *            partner
	 * @param Qty
	 *            quantity
	 * @param isSOTrx
	 *            SO or PO
	 */
	public MProductPricing(final int M_Product_ID, final int C_BPartner_ID,
			final BigDecimal Qty, final boolean isSOTrx) {
		mMProductID = M_Product_ID;
		mCBPartnerID = C_BPartner_ID;
		if (Qty != null && Env.ZERO.compareTo(Qty) != 0) {
			mQty = Qty;
		}
		misSOTrx = isSOTrx;
	} // MProductPricing

	/**
	 * Constructor
	 * 
	 * @param M_Product_ID
	 *            product
	 * @param C_BPartner_ID
	 *            partner
	 * @param Qty
	 *            quantity
	 * @param isSOTrx
	 *            SO or PO
	 */
	public MProductPricing(final int M_Product_ID, final int C_BPartner_ID,
			final BigDecimal Qty, final boolean isSOTrx, final int C_UOM_ID,
			final Properties context, final boolean force,
			final boolean cUomVolID) {
		mMProductID = M_Product_ID;
		mCBPartnerID = C_BPartner_ID;
		if (Qty != null && Env.ZERO.compareTo(Qty) != 0) {
			mQty = Qty;
		}
		misSOTrx = isSOTrx;
		mCUOMID = C_UOM_ID;
		isForce = force;
		// isSalePublic = salePublic;
		isUOMVol = cUomVolID;
		ctx = context; 
	} // MProductPricing

	/**
	 * Calculate Price
	 * 
	 * @return true if calculated
	 */
	public boolean calculatePrice() {
		if (mMProductID == 0 || (mfound != null && !mfound.booleanValue())) { // previously
																				// not
																				// found
			return false;
		}

		// Price List Version known
		if (!mcalculated) {
			mcalculated = calculatePLV();// Version
		}
		// Price List known
		if (!mcalculated) {
			mcalculated = calculatePL();// Lista de precios
		}

		// Price List por defecto
		if (isForce
		// Price list Sales Confg
				&& !mcalculated) {
			setM_PriceList_ID(MEXMEConfigPre.getPriceList(ctx, null)
					.getM_PriceList_ID());// 10001013
			mcalculated = calculatePL();// Lista de precios
		}

		// Set UOM, Prod.Category
		if (!mcalculated) {
			setBaseInfo();
		}
		// User based Discount
		if (mcalculated) {
			calculateDiscount();
		}
		setPrecision(); // from Price List
		//
		mfound = mcalculated;
		return mcalculated;
	} // calculatePrice

	/**
	 * Calculate Price based on Price List Version
	 * 
	 * @return true if calculated
	 */
	private boolean calculatePLV() {
		if (mMProductID == 0 || mMPriceListVerID == 0) {
			return false;
		}

		final StringBuilder sql = new StringBuilder(getQuery())
				.append(" INNER JOIN M_Pricelist pl ON (pv.M_PriceList_ID=pl.M_PriceList_ID) ")
				.append(" WHERE pv.IsActive='Y' ")
				.append(" AND p.M_Product_ID=? ") // #1
				.append(" AND pv.M_PriceList_Version_ID = ? "); // #2

		if (mCUOMID > 0) {
			if (isUOMVol) {
				sql.append(" AND pp.C_UOMVolume_ID = ? "); // #2
			} else {
				sql.append(" AND pp.C_UOM_ID = ? "); // #2
			}
		}

		mcalculated = false;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, mMProductID);
			pstmt.setInt(2, mMPriceListVerID);
			if (mCUOMID > 0) {
				pstmt.setInt(3, mCUOMID);
			}

			rset = pstmt.executeQuery();
			if (rset.next()) {
				// Prices
				mPriceStd = rset.getBigDecimal(1);
				if (rset.wasNull()) {
					mPriceStd = Env.ZERO;
				}
				mPriceList = rset.getBigDecimal(2);
				if (rset.wasNull()) {
					mPriceList = Env.ZERO;
				}
				mPriceLimit = rset.getBigDecimal(3);
				if (rset.wasNull()) {
					mPriceLimit = Env.ZERO;
				}
				//
				mCUOMID = rset.getInt("C_UOM_ID");
				mCCurrencyID = rset.getInt(6);
				mMProdCategoID = rset.getInt(7);
				menforcePLimit = "Y".equals(rset.getString(8));
				misTaxIncluded = "Y".equals(rset.getString(9));
				mCUOMVolumeID = rset.getInt("C_UOMVolume_ID");
				//
				sProductValue = rset.getString("productvalue");
				sProductDesc = rset.getString("productdescription");
				//
				log.fine("M_PriceList_Version_ID=" + mMPriceListVerID + " - "
						+ mPriceStd);
				mcalculated = true;
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			mcalculated = false;
		} finally {
			DB.close(rset, pstmt);
		}
		return mcalculated;
	} // calculatePLV

	/**
	 * Calculate Price based on Price List
	 * 
	 * @return true if calculated
	 */
	private boolean calculatePL() {
		if (mMProductID == 0) {
			return false;
		}

		if (mMPriceListID == 0) {
			log.log(Level.SEVERE, "No PriceList");
			Trace.printStack();
			return false;
		}

		final StringBuilder sql = new StringBuilder(getQuery())
				.append(" INNER JOIN M_Pricelist pl ON (pv.M_PriceList_ID=pl.M_PriceList_ID) ")
				.append(" WHERE pv.IsActive='Y' ")
				.append(" AND p.M_Product_ID = ? ") // #1
				.append(" AND pv.M_PriceList_ID = ? "); // #2

		if (mCUOMID > 0) {
			if (isUOMVol) {
				sql.append(" AND pp.C_UOMVolume_ID = ? "); // #2
			} else {
				sql.append(" AND pp.C_UOM_ID = ? "); // #2
			}
		}

		sql.append(" ORDER BY pv.ValidFrom DESC ");

		mcalculated = false;
		if (mPriceDate == null) {
			mPriceDate = DB.getTSForOrg(ctx);
		}
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, mMProductID);
			pstmt.setInt(2, mMPriceListID);
			if (mCUOMID > 0) {
				pstmt.setInt(3, mCUOMID);
			}

			rset = pstmt.executeQuery();
			while (!mcalculated && rset.next()) {
				final Timestamp plDate = rset.getTimestamp(5);
				// we have the price list
				// if order date is after or equal PriceList validFrom
				if (plDate == null || !mPriceDate.before(plDate)) {
					// Prices
					mPriceStd = rset.getBigDecimal(1);
					if (rset.wasNull()) {
						mPriceStd = Env.ZERO;
					}
					mPriceList = rset.getBigDecimal(2);
					if (rset.wasNull()) {
						mPriceList = Env.ZERO;
					}
					mPriceLimit = rset.getBigDecimal(3);
					if (rset.wasNull()) {
						mPriceLimit = Env.ZERO;
					}
					//
					mCUOMID = rset.getInt("C_UOM_ID");
					mCCurrencyID = rset.getInt(6);
					mMProdCategoID = rset.getInt(7);
					menforcePLimit = "Y".equals(rset.getString(8));
					mCUOMVolumeID = rset.getInt("C_UOMVolume_ID");
					//
					sProductValue = rset.getString("productvalue");
					sProductDesc = rset.getString("productdescription");
					//
					log.fine("M_PriceList_ID=" + mMPriceListID + "(" + plDate
							+ ")" + " - " + mPriceStd);
					mcalculated = true;
					break;
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			mcalculated = false;
		} finally {
			DB.close(rset, pstmt);
		}
		if (!mcalculated) {
			log.finer("Not found (PL)");
		}
		return mcalculated;
	} // calculatePL

	// /**
	// * Calculate Price based on Base Price List
	// * @return true if calculated
	// */
	// private boolean calculateBPL()
	// {
	// if (mMProductID == 0 || mMPriceListID == 0)
	// return false;
	//
	// final StringBuilder sql = new StringBuilder(getQuery())
	// .append(" INNER JOIN M_Pricelist bpl ON (pv.M_PriceList_ID=bpl.M_PriceList_ID)")
	// .append(" INNER JOIN M_Pricelist pl ON (bpl.M_PriceList_ID=pl.BasePriceList_ID) ")
	// .append(" WHERE pv.IsActive = 'Y'  ")
	// .append(" AND p.M_Product_ID = ?   ") // #1
	// .append(" AND pl.M_PriceList_ID = ? "); // #2
	//
	// if(mCUOMID>0){
	// if(isUOMVol){
	// sql.append(" AND pp.C_UOMVolume_ID = ? "); // #2
	// } else {
	// sql.append(" AND pp.C_UOM_ID = ? "); // #2
	// }
	// }
	//
	// sql.append(" ORDER BY pv.ValidFrom DESC");
	//
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// mcalculated = false;
	// if (mPriceDate == null){
	// mPriceDate = DB.convert(Env.getCtx(), mPriceDate);
	// }
	//
	// try
	// {
	// pstmt = DB.prepareStatement(sql.toString(), null);
	// pstmt.setInt(1, mMProductID);
	// pstmt.setInt(2, mMPriceListID);
	// if(mCUOMID>0){
	// pstmt.setInt(3, mCUOMID);
	// }
	//
	// rs = pstmt.executeQuery();
	// while (!mcalculated && rs.next())
	// {
	// final Timestamp plDate = rs.getTimestamp(5);
	// // we have the price list
	// // if order date is after or equal PriceList validFrom
	// if (plDate == null || !mPriceDate.before(plDate))
	// {
	// // Prices
	// mPriceStd = rs.getBigDecimal (1);
	// if (rs.wasNull ()){
	// mPriceStd = Env.ZERO;
	// }
	// mPriceList = rs.getBigDecimal (2);
	// if (rs.wasNull ()){
	// mPriceList = Env.ZERO;
	// }
	// mPriceLimit = rs.getBigDecimal (3);
	// if (rs.wasNull ()){
	// mPriceLimit = Env.ZERO;
	// }
	// //
	// mCUOMID = rs.getInt ("C_UOM_ID");
	// mCCurrencyID = rs.getInt (6);
	// mMProductCategoryID = rs.getInt(7);
	// menforcePriceLimit = "Y".equals(rs.getString(8));
	// misTaxIncluded = "Y".equals(rs.getString(9));
	// mCUOMVolumeID = rs.getInt ("C_UOMVolume_ID");
	// //
	// log.fine("M_PriceList_ID=" + mMPriceListID
	// + "(" + plDate + ")" + " - " + mPriceStd);
	// mcalculated = true;
	// break;
	// }
	// }
	// }
	// catch (Exception e)
	// {
	// log.log(Level.SEVERE, sql.toString(), e);
	// mcalculated = false;
	// } finally {
	// DB.close(rs, pstmt);
	// }
	// if (!mcalculated){
	// log.finer("Not found (BPL)");
	// }
	// return mcalculated;
	// } // calculateBPL

	/**
	 * Set Base Info (UOM)
	 */
	private void setBaseInfo() {
		if (mMProductID == 0) {
			return;
		}
		//
		final String sql = "SELECT C_UOM_ID, M_Product_Category_ID, C_UOMVOLUME_ID  FROM M_Product WHERE M_Product_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, mMProductID);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				mCUOMID = rset.getInt("C_UOM_ID");
				mMProdCategoID = rset.getInt(2);
				mCUOMVolumeID = rset.getInt("C_UOMVOLUME_ID");
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rset, pstmt);
		}
	} // setBaseInfo

	/**
	 * Is Tax Included
	 * 
	 * @return tax included
	 */
	public boolean isTaxIncluded() {
		return misTaxIncluded;
	} // isTaxIncluded

	/**************************************************************************
	 * Calculate (Business Partner) Discount
	 */
	private void calculateDiscount() {
		mdiscountSchema = false;
		if (mCBPartnerID == 0 || mMProductID == 0) {
			return;
		}

		int mDiscSchemaID = 0;
		BigDecimal FlatDiscount = null;
		final StringBuilder sql = new StringBuilder()
				.append("SELECT COALESCE(NVL(pcte.M_DiscountSchema_ID,p.M_DiscountSchema_ID),NVL(gcte.M_DiscountSchema_ID,g.M_DiscountSchema_ID)) as M_DiscountSchema_ID,")
				.append(" COALESCE(p.PO_DiscountSchema_ID,NVL(gcte.PO_DiscountSchema_ID,g.PO_DiscountSchema_ID)) as PO_DiscountSchema_ID, NVL(pcte.FlatDiscount,p.FlatDiscount) as FlatDiscount ")
				.append(" FROM C_BPartner p")
				// Lama - Partner Cte
				.append(" LEFT JOIN C_BPartner_Cte pcte ON (p.C_BPartner_ID=pcte.C_BPartner_ID AND pcte.AD_Client_ID = ? )") // #1
				.append(" LEFT JOIN C_BP_Group g ON (p.C_BP_Group_ID=g.C_BP_Group_ID) ")
				// Lama .- C_BP_Group_Cte
				.append(" LEFT JOIN C_BP_Group_Cte gcte ON (g.C_BP_Group_ID=gcte.C_BP_Group_ID AND gcte.AD_Client_ID = ? )") // #2
				.append(" WHERE p.C_BPartner_ID=?");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, mCBPartnerID);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				mDiscSchemaID = rset.getInt(misSOTrx ? 1 : 2);
				FlatDiscount = rset.getBigDecimal(3);
				if (FlatDiscount == null) {
					FlatDiscount = Env.ZERO;
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		// No Discount Schema
		if (mDiscSchemaID == 0) {
			return;
		}

		final MDiscountSchema discSchema = MDiscountSchema.get(ctx,
				mDiscSchemaID); // not correct
		if (discSchema.get_ID() == 0) {
			return;
		}
		//
		mdiscountSchema = true;
		mPriceStd = discSchema.calculatePrice(mQty, mPriceStd, mMProductID,
				mMProdCategoID, FlatDiscount);

	} // calculateDiscount

	/**************************************************************************
	 * Calculate Discount Percentage based on Standard/List Price
	 * 
	 * @return Discount
	 */
	public BigDecimal getDiscount() {
		BigDecimal Discount = Env.ZERO;
		if (mPriceList.intValue() != 0) {
			Discount = new BigDecimal(
					(mPriceList.doubleValue() - mPriceStd.doubleValue())
							/ mPriceList.doubleValue() * 100.0);
		}
		if (Discount.scale() > 2) {
			Discount = Discount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return Discount;
	} // getDiscount

	/**************************************************************************
	 * Get Product ID
	 * 
	 * @return id
	 */
	public int getM_Product_ID() {
		return mMProductID;
	}

	/**
	 * Get PriceList ID
	 * 
	 * @return pl
	 */
	public int getM_PriceList_ID() {
		return mMPriceListID;
	} // getM_PriceList_ID

	/**
	 * Set PriceList
	 * 
	 * @param M_PriceList_ID
	 *            pl
	 */
	public void setM_PriceList_ID(final int M_PriceList_ID) {
		mMPriceListID = M_PriceList_ID;
		mcalculated = false;
	} // setM_PriceList_ID

	/**
	 * Get PriceList Version
	 * 
	 * @return plv
	 */
	public int getM_PriceList_Version_ID() {
		return mMPriceListVerID;
	} // getM_PriceList_Version_ID

	/**
	 * Set PriceList Version
	 * 
	 * @param M_PriceList_Version_ID
	 *            plv
	 */
	public void setM_PriceList_Version_ID(final int M_PriceList_Version_ID) {
		mMPriceListVerID = M_PriceList_Version_ID;
		mcalculated = false;
	} // setM_PriceList_Version_ID

	/**
	 * Get Price Date
	 * 
	 * @return date
	 */
	public Timestamp getPriceDate() {
		return mPriceDate;
	} // getPriceDate

	/**
	 * Set Price Date
	 * 
	 * @param priceDate
	 *            date
	 */
	public void setPriceDate(final Timestamp priceDate) {
		mPriceDate = priceDate;
		mcalculated = false;
	} // setPriceDate

	/**
	 * Set Precision.
	 */
	private void setPrecision() {
		if (mMPriceListID != 0) {
			mprecision = MPriceList.getPricePrecision(ctx,
					getM_PriceList_ID());
		}
	} // setPrecision

	/**
	 * Get Precision
	 * 
	 * @return precision - -1 = no rounding
	 */
	public int getPrecision() {
		return mprecision;
	} // getPrecision

	/**
	 * Round
	 * 
	 * @param bigdec
	 *            number
	 * @return rounded number
	 */
	private BigDecimal round(final BigDecimal bigdec) {
		if (mprecision >= 0 // -1 = no rounding
				&& bigdec.scale() > mprecision) {
			return bigdec.setScale(mprecision, BigDecimal.ROUND_HALF_UP);
		}
		return bigdec;
	} // round

	/**************************************************************************
	 * Get C_UOM_ID
	 * 
	 * @return uom
	 */
	public int getC_UOM_ID() {
		if (!mcalculated) {
			calculatePrice();
		}
		return mCUOMID;
	}

	public int getC_UOMVolume_ID() {
		if (!mcalculated) {
			calculatePrice();
		}
		return mCUOMVolumeID;
	}

	/**
	 * Get Price List
	 * 
	 * @return list
	 */
	public BigDecimal getPriceList() {
		if (!mcalculated) {
			calculatePrice();
		}
		return round(mPriceList);
	}

	/**
	 * Get Price Std
	 * 
	 * @return std
	 */
	public BigDecimal getPriceStd() {
		if (!mcalculated) {
			calculatePrice();
		}
		return round(mPriceStd);
	}

	/**
	 * Get Price Limit
	 * 
	 * @return limit
	 */
	public BigDecimal getPriceLimit() {
		if (!mcalculated) {
			calculatePrice();
		}
		return round(mPriceLimit);
	}

	/**
	 * Get Price List Currency
	 * 
	 * @return currency
	 */
	public int getC_Currency_ID() {
		if (!mcalculated) {
			calculatePrice();
		}
		return mCCurrencyID;
	}

	/**
	 * Is Price List enforded?
	 * 
	 * @return enforce limit
	 */
	public boolean isEnforcePriceLimit() {
		if (!mcalculated) {
			calculatePrice();
		}
		return menforcePLimit;
	} // isEnforcePriceLimit

	/**
	 * Is a DiscountSchema active?
	 * 
	 * @return active Discount Schema
	 */
	public boolean isDiscountSchema() {
		return mdiscountSchema;
	} // isDiscountSchema

	/**
	 * Is the Price Calculated (i.e. found)?
	 * 
	 * @return calculated
	 */
	public boolean isCalculated() {
		return mcalculated;
	} // isCalculated

	/**
	 * Consulta para obtener precios de productos a partir de una lista de
	 * precios
	 */
	private String getQuery() {
		final StringBuilder sql = new StringBuilder(" SELECT ");
		if (mCUOMID > 0 && isUOMVol) {
			sql.append(
					" pp.PRICESTD_VOL AS PriceStd, pp.PRICELIST_VOL AS PriceList,  pp.PRICELIMIT_VOL AS PriceLimit, ")
					.append(" pp.C_UOMVOLUME_ID AS C_UOM_ID, ");
		} else {
			sql.append(
					" bomPriceStd(p.M_Product_ID,pv.M_PriceList_Version_ID) AS PriceStd, ") // 1
					.append(" bomPriceList(p.M_Product_ID,pv.M_PriceList_Version_ID) AS PriceList, ") // 2
					.append(" bomPriceLimit(p.M_Product_ID,pv.M_PriceList_Version_ID) AS PriceLimit, ") // 3
					.append(" pp.C_UOM_ID, ");
		}

		sql.append(
				" pv.ValidFrom,pl.C_Currency_ID,p.M_Product_Category_ID, pl.EnforcePriceLimit, ")
				// 4..8
				.append(" pl.IsTaxIncluded, p.C_UOMVolume_ID, p.C_UOM_ID  ")
				// 9
				.append(" , pp.productvalue, pp.productdescription  ")
				// 10/11
				.append(" FROM M_Product p ")
				.append(" INNER JOIN M_ProductPrice      pp ON p.M_Product_ID = pp.M_Product_ID AND pp.IsActive='Y' ")
				.append(" INNER JOIN M_PriceList_Version pv ON pp.M_PriceList_Version_ID=pv.M_PriceList_Version_ID  ");

		return sql.toString();
	}

	/** Codigo de producto cuando el socio requiere factura especial */
	public String getsProductValue() {
		return sProductValue;
	}

	/** Descripcion de producto cuando el socio requiere factura especial */
	public String getsProductDescription() {
		return sProductDesc;
	}

} // MProductPrice
