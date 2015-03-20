/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.compiere.model.bean.StorageInfo;
import org.compiere.model.bpm.GetPrice;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;


/**
 * Product Model
 *
 * @author Jorg Janke
 * @version $Id: MProduct.java,v 1.9 2006/09/21 15:05:14 taniap Exp $
 */
public class MProduct extends X_M_Product {

	/** serialVersionUID */
	private static final long serialVersionUID = 2199637959189849608L;
	private int productID = 0;
	private boolean external = false;
	private int anioInicial = 0;
	private int anioFinal = 0;
	private int mesInicial = 0;
	private int mesFinal = 0;
	private int actPacienteIndId = 0;
	private boolean isTodayService = false;
	private ServicioView servView= null;
	private MEXMEProductoOrg prodOrg = null;

	/**
	 * Get MProduct from Cache
	 *
	 * @param ctx
	 *            context
	 * @param M_Product_ID
	 *            id
	 * @return MProduct
	 */
	public static MProduct get(Properties ctx, int M_Product_ID) {
		Integer key = new Integer(M_Product_ID);
		MProduct retValue = (MProduct) s_cache.get(key);
		if (retValue != null
		// Expert : Lama (verificar la sesion, para usar o no el objeto del
		// cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID") == Env
						.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) // lama
			return retValue;
		retValue = new MProduct(ctx, M_Product_ID, null);
		if (retValue.get_ID() != 0)
			s_cache.put(key, retValue);
		return retValue;
	} // get

	/**
	 * Get MProduct from Cache
	 *
	 * @param ctx
	 *            context
	 * @param whereClause
	 *            sql where clause
	 * @param trxName
	 *            trx
	 * @return MProduct
	 */
	public static MProduct[] get(Properties ctx, String whereClause,
			String trxName) {
		String sql = "SELECT * FROM M_Product";
		if (whereClause != null && whereClause.length() > 0)
			sql += " WHERE AD_Client_ID=? AND " + whereClause;
		ArrayList<MProduct> list = new ArrayList<MProduct>();
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, AD_Client_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MProduct(ctx, rs, trxName));

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		MProduct[] retValue = new MProduct[list.size()];
		list.toArray(retValue);
		return retValue;
	} // get

	/**
	 * Is Product Stocked
	 *
	 * @param ctx
	 *            context
	 * @param M_Product_ID
	 *            id
	 * @return true if found and stocked - false otherwise
	 */
	public static boolean isProductStocked(Properties ctx, int M_Product_ID) {
		MProduct product = get(ctx, M_Product_ID);
		return product.isStocked();
	} // isProductStocked

//	/**
//	 * Method to obtain the Unit of Measure from the product selected
//	 *
//	 * @param int product_id
//	 * @return Vector<OptionItem>
//	 */
//	public Vector<OptionItem> getUOM(int product_id) {
//		StringBuilder sb = new StringBuilder("SELECT cu.C_Uom_id,cu.Name ");
//		sb.append("FROM M_Product mp, C_Uom cu ");
//		sb.append("WHERE mp.C_Uom_id= cu.C_Uom_id AND mp.M_Product_id = ?");
//		StringBuilder sql = new StringBuilder(
//				MEXMELookupInfo.addAccessLevelSQL(getCtx(), sb.toString(),
//						this.get_TableName()));
//		Vector<OptionItem> udm = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.clearParameters();
//			pstmt.setInt(1, product_id);
//			rs = pstmt.executeQuery();
//			udm = new Vector<OptionItem>();
//			while (rs.next()) {
//				udm.add(new OptionItem(rs.getString(1), rs.getString(2)));
//			}
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return udm;
//	}

	/** Cache */
	private static CCache<Integer, MProduct> s_cache = new CCache<Integer, MProduct>(
			"M_Product", 40, 5); // 5 minutes
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MProduct.class);

	/**************************************************************************
	 * Standard Constructor
	 *
	 * @param ctx
	 *            context
	 * @param M_Product_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MProduct(Properties ctx, int M_Product_ID, String trxName) {
		super(ctx, M_Product_ID, trxName);
		if (M_Product_ID == 0) {
			// setValue (null);
			// setName (null);
			// setM_Product_Category_ID (0);
			// setC_TaxCategory_ID (0);
			// setC_UOM_ID (0);
			//
			setProductType(PRODUCTTYPE_Item); // I
			setIsBOM(false); // N
			setIsInvoicePrintDetails(false);
			setIsPickListPrintDetails(false);
			setIsPurchased(true); // Y
			setIsSold(true); // Y
			setIsStocked(true); // Y
			setIsSummary(false);
			setIsVerified(false); // N
			setIsWebStoreFeatured(false);
			setIsSelfService(true);
			setIsExcludeAutoDelivery(false);
			setProcessing(false); // N
		}
	} // MProduct

	/**
	 * Load constructor
	 *
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MProduct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MProduct

	/**
	 * Parent Constructor
	 *
	 * @param et
	 *            parent
	 */
	public MProduct(MExpenseType et) {
		this(et.getCtx(), 0, et.get_TrxName());
		setProductType(MProduct.PRODUCTTYPE_ExpenseType);
		setExpenseType(et);
	} // MProduct

	/**
	 * Parent Constructor
	 *
	 * @param resource
	 *            parent
	 * @param resourceType
	 *            resource type
	 */
	public MProduct(MResource resource, MResourceType resourceType) {
		this(resource.getCtx(), 0, resource.get_TrxName());
		setProductType(MProduct.PRODUCTTYPE_Resource);
		setResource(resource);
		setResource(resourceType);
	} // MProduct

	/**
	 * Import Constructor
	 *
	 * @param impP
	 *            import
	 */
	public MProduct(X_I_Product impP, boolean interfaz) {
		this(impP.getCtx(), 0, impP.get_TrxName());
		if (interfaz) {
			setProductID(impP.getI_Product_ID());
		}
		setClientOrg(impP);
		setUpdatedBy(impP.getUpdatedBy());
		setValue(impP.getValue());
		setName(impP.getName());
		setDescription(impP.getDescription());
		setDocumentNote(impP.getDocumentNote());
		setHelp(impP.getHelp());
		setUPC(impP.getUPC());
		setSKU(impP.getSKU());
		setC_UOM_ID(impP.getC_UOM_ID());
		setM_Product_Category_ID(impP.getM_Product_Category_ID());
		setProductType(impP.getProductType());
		setImageURL(impP.getImageURL());
		setDescriptionURL(impP.getDescriptionURL());
		// expert
		if (impP.getEXME_TipoProd_ID() != 0)
			setEXME_TipoProd_ID(impP.getEXME_TipoProd_ID());
		if (impP.getEXME_FactorPre_ID() != 0)
			setEXME_FactorPre_ID(impP.getEXME_FactorPre_ID());
		if (impP.getEXME_ProductFam_ID() != 0)
			setEXME_ProductFam_ID(impP.getEXME_ProductFam_ID());
		if (impP.getEXME_ConceptoFac_ID() != 0)
			setEXME_ConceptoFac_ID(impP.getEXME_ConceptoFac_ID());
		if (impP.getC_TaxCategory_ID() != 0)
			setC_TaxCategory_ID(impP.getC_TaxCategory_ID());
		// LRHU. se agrega la UOM del Peso y del Volumen.
		setC_UOMWeight_ID(impP.getC_UOMWeight_ID());
		setC_UOMVolume_ID(impP.getC_UOMVolume_ID());

		// expert

	} // MProduct

	/**
	 *
	 * @param ctx
	 * @param id
	 * @param trxName
	 * @param adClientID
	 */
	public MProduct(Properties ctx, int id, String trxName, int adClientID) {
		this(ctx, id, trxName);
		setAD_Client_ID(adClientID);
	}



	/**
	 * Creates a product instance from the charge master import data.
	 */
	public MProduct(X_I_EXME_ProductoOrg ipo) {
		super(ipo.getCtx(), 0, ipo.get_TrxName());

		setValue(ipo.getOther());
		setName(ipo.getCharge());
		setDescription(ipo.getDescription());
		setIsSummary(false);
		setIsPurchased(false);
		setIsSold(true);
		setIsBOM(false);
		setIsInvoicePrintDetails(false);
		setIsPickListPrintDetails(false);
		setIsVerified(false);
		setM_Product_Category_ID(ipo.getM_Product_Category_ID());
		setC_TaxCategory_ID(ipo.getC_TaxCategory_ID());
		setDiscontinued(false);
		setProcessing(false);
		setProductType(ipo.getProductType());
		setIsWebStoreFeatured(false);
		setIsSelfService(false);
		setIsDropShip(false);
		setIsExcludeAutoDelivery(false);
		setRequiereContraste(false);
		setIsGI(false);
		setProductClass(ipo.getProductClass());
		setEXME_TipoProd_ID(ipo.getEXME_TipoProd_ID());
		setEXME_ProductFam_ID(ipo.getEXME_ProductFam_ID());
		if (StringUtils.isNotBlank(ipo.getUPC())) {
			setUPC(ipo.getUPC());
		}
	}


	/** Additional Downloads */
	private MProductDownload[] m_downloads = null;

	/**
	 * Set Expense Type
	 *
	 * @param parent
	 *            expense type
	 * @return true if changed
	 */
	public boolean setExpenseType(MExpenseType parent) {
		boolean changed = false;
		if (!PRODUCTTYPE_ExpenseType.equals(getProductType())) {
			setProductType(PRODUCTTYPE_ExpenseType);
			changed = true;
		}
		if (parent.getS_ExpenseType_ID() != getS_ExpenseType_ID()) {
			setS_ExpenseType_ID(parent.getS_ExpenseType_ID());
			changed = true;
		}
		if (parent.isActive() != isActive()) {
			setIsActive(parent.isActive());
			changed = true;
		}
		//
		if (!parent.getValue().equals(getValue())) {
			setValue(parent.getValue());
			changed = true;
		}
		if (!parent.getName().equals(getName())) {
			setName(parent.getName());
			changed = true;
		}
		if ((parent.getDescription() == null && getDescription() != null)
				|| (parent.getDescription() != null && !parent.getDescription()
						.equals(getDescription()))) {
			setDescription(parent.getDescription());
			changed = true;
		}
		if (parent.getC_UOM_ID() != getC_UOM_ID()) {
			setC_UOM_ID(parent.getC_UOM_ID());
			changed = true;
		}
		if (parent.getM_Product_Category_ID() != getM_Product_Category_ID()) {
			setM_Product_Category_ID(parent.getM_Product_Category_ID());
			changed = true;
		}
		// if (parent.getC_TaxCategory_ID() != getC_TaxCategory_ID())
		// {
		// setC_TaxCategory_ID(parent.getC_TaxCategory_ID());
		// changed = true;
		// }//Lama .- Product_Cte
		//
		return changed;
	} // setExpenseType

	/**
	 * Set Resource
	 *
	 * @param parent
	 *            resource
	 * @return true if changed
	 */
	public boolean setResource(MResource parent) {
		boolean changed = false;
		if (!PRODUCTTYPE_Resource.equals(getProductType())) {
			setProductType(PRODUCTTYPE_Resource);
			changed = true;
		}
		if (parent.getS_Resource_ID() != getS_Resource_ID()) {
			setS_Resource_ID(parent.getS_Resource_ID());
			changed = true;
		}
		if (parent.isActive() != isActive()) {
			setIsActive(parent.isActive());
			changed = true;
		}
		//
		if (!parent.getValue().equals(getValue())) {
			setValue(parent.getValue());
			changed = true;
		}
		if (!parent.getName().equals(getName())) {
			setName(parent.getName());
			changed = true;
		}
		if ((parent.getDescription() == null && getDescription() != null)
				|| (parent.getDescription() != null && !parent.getDescription()
						.equals(getDescription()))) {
			setDescription(parent.getDescription());
			changed = true;
		}
		//
		return changed;
	} // setResource

	/**
	 * Set Resource Type
	 *
	 * @param parent
	 *            resource type
	 * @return true if changed
	 */
	public boolean setResource(MResourceType parent) {
		boolean changed = false;
		if (PRODUCTTYPE_Resource.equals(getProductType())) {
			setProductType(PRODUCTTYPE_Resource);
			changed = true;
		}
		//
		if (parent.getC_UOM_ID() != getC_UOM_ID()) {
			setC_UOM_ID(parent.getC_UOM_ID());
			changed = true;
		}
		if (parent.getM_Product_Category_ID() != getM_Product_Category_ID()) {
			setM_Product_Category_ID(parent.getM_Product_Category_ID());
			changed = true;
		}
		// if (parent.getC_TaxCategory_ID() != getC_TaxCategory_ID())
		// {
		// setC_TaxCategory_ID(parent.getC_TaxCategory_ID());
		// changed = true;
		// }//Lama .- Product_Cte
		//
		return changed;
	} // setResource

	/** UOM Precision */
	private Integer m_precision = null;

	/**
	 * Get UOM Standard Precision
	 *
	 * @return UOM Standard Precision
	 */
	public int getUOMPrecision() {
		if (m_precision == null) {
			int C_UOM_ID = getC_UOM_ID();
			if (C_UOM_ID == 0)
				return 0; // EA
			m_precision = new Integer(MUOM.getPrecision(getCtx(), C_UOM_ID));
		}
		return m_precision.intValue();
	} // getUOMPrecision

	/**
	 * Create Asset Group for this product
	 *
	 * @return asset group id
	 */
	public int getA_Asset_Group_ID() {
		MProductCategory pc = MProductCategory.get(getCtx(),
				getM_Product_Category_ID());
		return pc.getA_Asset_Group_ID();
	} // getA_Asset_Group_ID

	/**
	 * Create Asset for this product
	 *
	 * @return true if asset is created
	 */
	public boolean isCreateAsset() {
		MProductCategory pc = MProductCategory.get(getCtx(),
				getM_Product_Category_ID());
		return pc.getA_Asset_Group_ID() != 0;
	} // isCreated

	/**
	 * Get Attribute Set
	 *
	 * @return set or null
	 */
	public MAttributeSet getAttributeSet() {
		if (getM_AttributeSet_ID() != 0)
			return MAttributeSet.get(getCtx(), getM_AttributeSet_ID());
		return null;
	} // getAttributeSet

	/**
	 * Has the Product Instance Attribute
	 *
	 * @return true if instance attributes
	 */
	public boolean isInstanceAttribute() {
		if (getM_AttributeSet_ID() == 0)
			return false;
		MAttributeSet mas = MAttributeSet.get(getCtx(), getM_AttributeSet_ID());
		return mas.isInstanceAttribute();
	} // isInstanceAttribute

	/**
	 * Create One Asset Per UOM
	 *
	 * @return individual asset
	 */
	public boolean isOneAssetPerUOM() {
		MProductCategory pc = MProductCategory.get(getCtx(),
				getM_Product_Category_ID());
		if (pc.getA_Asset_Group_ID() == 0)
			return false;
		MAssetGroup ag = MAssetGroup.get(getCtx(), pc.getA_Asset_Group_ID());
		return ag.isOneAssetPerUOM();
	} // isOneAssetPerUOM

	/**
	 * Product is Item
	 *
	 * @return true if item
	 */
	public boolean isItem() {
		return PRODUCTTYPE_Item.equals(getProductType());
	} // isItem

	/**
	 * Product is an Item and Stocked
	 *
	 * @return true if stocked and item
	 */
	public boolean isStocked() {
		return super.isStocked() && isItem();
	} // isStocked

	/**
	 * Is Service
	 *
	 * @return true if service (resource, online)
	 */
	public boolean isService() {
		return !isItem(); //
	} // isService

	/**
	 * Get UOM Symbol
	 *
	 * @return UOM Symbol
	 */
	public String getUOMSymbol() {
		int C_UOM_ID = getC_UOM_ID();
		if (C_UOM_ID == 0)
			return "";
		return MUOM.get(getCtx(), C_UOM_ID).getUOMSymbol();
	} // getUOMSymbol

	/**
	 * Get Active(!) Product Downloads
	 *
	 * @param requery
	 *            requery
	 * @return array of downloads
	 */
	public MProductDownload[] getProductDownloads(boolean requery) {
		if (m_downloads != null && !requery)
			return m_downloads;
		//
		ArrayList<MProductDownload> list = new ArrayList<MProductDownload>();
		String sql = "SELECT * FROM M_ProductDownload "
				+ "WHERE M_Product_ID=? AND IsActive='Y' ORDER BY Name";
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getM_Product_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MProductDownload(getCtx(), rs, get_TrxName()));

		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		//
		m_downloads = new MProductDownload[list.size()];
		list.toArray(m_downloads);
		return m_downloads;
	} // getProductDownloads

	/**
	 * Does the product have downloads
	 *
	 * @return true if downloads exists
	 */
	public boolean hasDownloads() {
		getProductDownloads(false);
		return m_downloads != null && m_downloads.length > 0;
	} // hasDownloads

	/**
	 * String Representation
	 *
	 * @return info
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("MProduct[");
		sb.append(get_ID()).append("-").append(getValue()).append("]");
		return sb.toString();
	} // toString

	/**
	 * Es medicamento
	 *
	 * @return
	 */
	public boolean isMedicine() {
		boolean retValue = false;

		//TODO : agregar validaciones necesarias
		if(PRODUCTCLASS_Drug.equals(getProductClass())) {
			retValue = true;
		}

		return retValue;
	}

	/**
	 * Before Save
	 *
	 * @param newRecord
	 *            new
	 * @return true
	 */
	protected boolean beforeSave(boolean newRecord) {
		// Check Storage
		if (!newRecord && //
				((is_ValueChanged("IsActive") && !isActive()) // now not active
						|| (is_ValueChanged("IsStocked") && !isStocked()) // now
																			// not
																			// stocked
				|| (is_ValueChanged("ProductType") // from Item
				&& PRODUCTTYPE_Item.equals(get_ValueOld("ProductType"))))) {
			MStorage[] storages = MStorage.getOfProduct(getCtx(), get_ID(),
					get_TrxName());
			BigDecimal OnHand = Env.ZERO;
			BigDecimal Ordered = Env.ZERO;
			BigDecimal Reserved = Env.ZERO;
			for (int i = 0; i < storages.length; i++) {
				OnHand = OnHand.add(storages[i].getQtyOnHand());
				Ordered = Ordered.add(storages[i].getQtyOrdered());
				Reserved = Reserved.add(storages[i].getQtyReserved());
			}
			String errMsg = "";
			if (OnHand.signum() != 0)
				errMsg = "@QtyOnHand@: " + OnHand;
			if (Ordered.signum() != 0)
				errMsg += " @QtyOrdered@: " + Ordered;
			if (Reserved.signum() != 0)
				errMsg += " @QtyReserved@: " + Reserved;
			if (errMsg.length() > 0) {
				log.saveError("Error", Utilerias.getMessage(getCtx(), null, "msj.error.prodExist") + " " + Msg.parseTranslation(getCtx(), errMsg));
				return false;
			}
		}

		// Reset Stocked if not Item
		if (isStocked() && !PRODUCTTYPE_Item.equals(getProductType()))
			setIsStocked(false);

		// UOM reset
		if (m_precision != null && is_ValueChanged("C_UOM_ID")) {
			m_precision = null;
		}

		if (getAD_Client_ID() >0 && getAD_Org_ID()<=0){
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@Org0NotAllowed@"));//
			return false;
		}

		if( isMedicine() && getAD_Org_ID()!=0 && getAD_Client_ID()!=0){
			log.saveError("Error", Utilerias.getAppMsg(getCtx(), "error.registrarMed.noautorizacion"));
			return false;
		}

		MProduct prodTmp = MProduct.existsInSystem(getCtx(), getValue(), null);
		if (prodTmp!=null && prodTmp.getM_Product_ID() != getM_Product_ID()) {
			log.saveError("Error", Utilerias.getAppMsg(getCtx(), "error.duplicate.key"));
			return false;
		} else {
			if (Env.getAD_Client_ID(getCtx()) == 0 && Env.getAD_Org_ID(getCtx()) == 0) {
				prodTmp = MProduct.getIdProdByValue(getCtx(), getValue(), false);
				if(prodTmp!=null && prodTmp.getM_Product_ID() != getM_Product_ID()){
					log.saveError("Error", Utilerias.getAppMsg(getCtx(), "msj.allergy.error"));
					return false;
				}
			} else {
				prodTmp = MProduct.getIdProdByValue(getCtx(), getValue(), true);
				if(prodTmp!=null && prodTmp.getM_Product_ID() != getM_Product_ID()){
					log.saveError("Error", Utilerias.getAppMsg(getCtx(), "msj.allergy.error"));
					return false;
				}
			}
		}

		//rsolorzano 20-agosto-2012
		if(getC_UOMVolume_ID() == 0){
			setC_UOMVolume_ID(getC_UOM_ID());
			setUnitsPerPack(1);
		}
		//Si las UDM son diferentes preguntamos si existe una conversion
		if(getC_UOMVolume_ID() != getC_UOM_ID()){
			final MUOMConversion rates = MEXMEUOMConversion.getUomsConversion(getCtx(), getC_UOM_ID(),
					getC_UOMVolume_ID(),null);
			if(rates == null){
				log.saveError("Error",  Utilerias.getAppMsg(getCtx(),"error.udm.noExisteConversion")+ getC_UOM_ID() + getC_UOMVolume_ID());
				return false;
			}
		}

		return true;
	} // beforeSave

	/**
	 * After Save
	 *
	 * @param newRecord
	 *            new
	 * @param success
	 *            success
	 * @return success
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success){
			return success;
		}
		// Value/Name change in Account
		if (!newRecord && (is_ValueChanged("Value") || is_ValueChanged("Name")))
			MAccount.updateValueDescription(getCtx(), "M_Product_ID="
					+ getM_Product_ID(), get_TrxName());

		// Name/Description Change in Asset MAsset.setValueNameDescription
		if (!newRecord
				&& (is_ValueChanged("Name") || is_ValueChanged("Description"))) {
			String sql = "UPDATE A_Asset a "
				+ "SET (Name, Description)="
				+ "(SELECT SUBSTR(bp.Name || ' - ' || p.Name,1,60), p.Description "
				+ "FROM M_Product p, C_BPartner bp "
				+ "WHERE p.M_Product_ID=a.M_Product_ID AND bp.C_BPartner_ID=a.C_BPartner_ID) "
				+ "WHERE IsActive='Y'"
				// + " AND GuaranteeDate > SysDate"
				+ "  AND M_Product_ID=" + getM_Product_ID();
			int no = DB.executeUpdate(sql, get_TrxName());
			log.fine("Asset Description updated #" + no);
		}

		// Si se cambia la categoria de impuesto, se actualiza el id en el registro del cliente
		if (!newRecord && getAD_Client_ID() > 0 && is_ValueChanged("C_TaxCategory_ID")) {
			X_M_Product_Cte prodCte = MProduct.getProductCte(
					getCtx(), getM_Product_ID(), getAD_Client_ID(), getAD_Org_ID());
			if(prodCte!=null){
				prodCte.setC_TaxCategory_ID(super.getC_TaxCategory_ID());
				boolean exito = prodCte.save(get_TrxName());
				log.fine("Update M_Product_Cte.C_TaxCategory_ID #" + exito);
			}
		}

		// New - Acct, Tree, Old Costing
		if (newRecord) {
			// Lama .- Product_Cte
			if (!createProductCte()) {
				log.severe("M_Product_Cte not saved");
				return false;
			}

//				insert_Accounting("M_Product_Acct", "M_Product_Category_Acct",
//						"p.M_Product_Category_ID=" + getM_Product_Category_ID());
			insert_Tree(MTree_Base.TREETYPE_Product);
			//
			MAcctSchema[] mass = MAcctSchema.getClientAcctSchema(getCtx(),
					getAD_Client_ID(), get_TrxName());
			for (int i = 0; i < mass.length; i++) {
				// Old
				MProductCosting pcOld = new MProductCosting(this,
						mass[i].getC_AcctSchema_ID());
				pcOld.save();
			}
		}

		// New Costing
//			if (newRecord || is_ValueChanged("M_Product_Category_ID")) {
//				// mrojas : tenia M_Product_Category solamente
//				try {
////					MCost.create(this); //Ya no aplica 21/nov/2012 GC
//				} catch (Exception ex) {
//					log.log(Level.WARNING, "MProduct.afterSave.MCost.create", ex);
//				}
//			}

		// Lama: actualización de índices Card #618
		if(success){
			MEXMEProductInterv.checkTable(this, newRecord);
		}
		
		if (success && (is_ValueChanged(COLUMNNAME_Value) || is_ValueChanged(COLUMNNAME_Name) 
				|| is_ValueChanged(COLUMNNAME_Description) || is_ValueChanged(COLUMNNAME_Help) 
				|| is_ValueChanged("isActive") || is_ValueChanged(COLUMNNAME_ProductClass)
				|| is_ValueChanged(COLUMNNAME_UPC) || is_ValueChanged(COLUMNNAME_IsStocked)
				|| newRecord)) {
			MEXMEProductV.checkIndexByProduct(getCtx(), get_ID(), get_TrxName());
		}

		return success;
	} // afterSave

	/**
	 * Before Delete
	 *
	 * @return true if it can be deleted
	 */
	protected boolean beforeDelete() {
		// Check Storage
		if (isStocked() || PRODUCTTYPE_Item.equals(getProductType())) {
			MStorage[] storages = MStorage.getOfProduct(getCtx(), get_ID(),
					get_TrxName());
			BigDecimal OnHand = Env.ZERO;
			BigDecimal Ordered = Env.ZERO;
			BigDecimal Reserved = Env.ZERO;
			for (int i = 0; i < storages.length; i++) {
				OnHand = OnHand.add(storages[i].getQtyOnHand());
				Ordered = OnHand.add(storages[i].getQtyOrdered());
				Reserved = OnHand.add(storages[i].getQtyReserved());
			}
			String errMsg = "";
			if (OnHand.signum() != 0)
				errMsg = "@QtyOnHand@ = " + OnHand;
			if (Ordered.signum() != 0)
				errMsg += " - @QtyOrdered@ = " + Ordered;
			if (Reserved.signum() != 0)
				errMsg += " - @QtyReserved@" + Reserved;
			if (errMsg.length() > 0) {
				log.saveError("Error", Msg.parseTranslation(getCtx(), errMsg));
				return false;
			}

		}
		// delete costing
		MProductCosting[] costings = MProductCosting.getOfProduct(getCtx(),
				get_ID(), get_TrxName());
		for (int i = 0; i < costings.length; i++)
			costings[i].delete(true, get_TrxName());

		// [ 1674225 ] Delete Product: Costing deletion error
		MAcctSchema[] mass = MAcctSchema.getClientAcctSchema(getCtx(),
				getAD_Client_ID(), get_TrxName());
		for(int i=0; i<mass.length; i++)
		{
			// Get Cost Elements
			MCostElement[] ces = MCostElement.getCostingMethods(this);
			MCostElement ce = null;
			for(int j=0; j<ces.length; j++)
			{
				if(MCostElement.COSTINGMETHOD_StandardCosting.equals(ces[i].getCostingMethod()))
				{
					ce = ces[i];
					break;
				}
			}

			if(ce == null)
				continue;

			MCost mcost = MCost.get(this, 0, mass[i], 0, ce.getM_CostElement_ID(), get_TrxName());
			mcost.delete(true, get_TrxName());
		}

		MEXMEProductoOrg exmeProductoOrg =  MEXMEProductoOrg.getProductoOrg(getCtx(), getM_Product_ID(), getAD_Client_ID(), getAD_Org_ID(), get_TrxName());
		if(exmeProductoOrg!=null)
			exmeProductoOrg.delete(true, get_TrxName());
		//
		return delete_Accounting("M_Product_Acct");
	} // beforeDelete

	/**
	 * After Delete
	 *
	 * @param success
	 * @return deleted
	 */
	protected boolean afterDelete(boolean success) {
		if (success) {
			delete_Tree(MTree_Base.TREETYPE_Product);
			//Lama: actualizamos el archivo de busqueda para productos (items)
			if (PRODUCTTYPE_Item.equals(getProductType())
					|| (is_ValueChanged(COLUMNNAME_ProductType) && PRODUCTTYPE_Item.equals(get_ValueOld(COLUMNNAME_ProductType)))) {
				MEXMETGenProdTrade.deleteNotPrefGenProdTrade(getCtx(), getEXME_GenProduct_ID(), get_TrxName());
			}
			// Lama: actualización de índices
			if(success){
				MEXMEProductInterv.checkTable(this, true);
				MEXMEProductV.checkIndexByProduct(getCtx(), get_ID(), get_TrxName());
			}
		}
		return success;
	} // afterDelete

	// expert
	/*
	 * public static MProduct getMProduct(Properties ctx, String value, String
	 * trxName) throws Exception {
	 *
	 * String sqllvb = null; //PreparedStatement con la sentencia sql
	 * PreparedStatement pstmtlvb = null; //ResultSet utilizado para manipular
	 * los resultados ResultSet rslbv = null;
	 *
	 * MProduct resultado = null;
	 *
	 *
	 * //value = value.replaceAll("\\*", "%");
	 *
	 * sqllvb = " SELECT * " + " FROM M_Product WHERE UPPER( M_Product." +
	 * "Value) LIKE UPPER(?) AND " + " M_Product.IsActive = 'Y'";
	 *
	 * sqllvb = MEXMELookupInfo.addAccessLevelSQL(ctx, sqllvb, "M_Product");
	 *
	 * pstmtlvb = null; rslbv = null;
	 *
	 * if (WebEnv.DEBUG) { s_log.log(Level.SEVERE,"SQL : " + sqllvb +
	 * "  value: " + value); //System.out.println("SQL : " + sqllvb +
	 * "  value: " + value); }
	 *
	 * try { pstmtlvb = DB.prepareStatement(sqllvb, null); pstmtlvb.setString(1,
	 * value);
	 *
	 * rslbv = pstmtlvb.executeQuery();
	 *
	 * if (rslbv.next()) resultado = new MProduct(ctx,
	 * rslbv.getInt("M_product_ID"), trxName);
	 *
	 * } catch (SQLException e) { e.printStackTrace(); throw new
	 * Exception(e.getMessage()); } finally { rslbv = null; pstmtlvb = null;
	 * sqllvb = null;; }
	 *
	 * return resultado; }
	 */
	public MTipoProd getTipoProd() {
		return new MTipoProd(getCtx(), getEXME_TipoProd_ID(), get_TrxName());
	}

	public MUOM getUom() {
		return new MUOM(getCtx(), getC_UOM_ID(), get_TrxName());
	}


	/**
	 * Returns the product name for the specific product id.
	 * @param mProductID The product id to look for.
	 * @return The product name or an empty string.
	 */
	public static String getNameById(int mProductID) {
		String name =
				DB.getSQLValueString(
						null,
						"SELECT name FROM M_Product WHERE M_Product_ID = ?",
						mProductID
				);

		if(name == null) {
			name = StringUtils.EMPTY;
		}

		return name;
	}

	/**
	 * Get Id By Value
	 *
	 * @param value
	 * @return Id
	 */
	public static int getIdFromValue(String value, int adOrgId, boolean isActive) {
		StringBuffer sql = new StringBuffer("SELECT m_product_id FROM m_product WHERE value = ?");

		List<Object> params = new ArrayList<Object>();
		params.add(value);

		if(adOrgId > 0) {
			sql.append(" AND AD_Org_ID = ?");
			params.add(adOrgId);
		}

		if(isActive) {
			sql.append(" AND IsActive = 'Y'");
		}

		return DB.getSQLValue(null, sql.toString(), params.toArray());
	}
	
	/**
	 * 
	 * @param ctx
	 * @param value
	 * @param isActive
	 * @param client
	 * 	True: Toma en cuenta de 0 y el cliente y org 
	 * 	False: toma en cuenta solo el cliente y org
	 * @return
	 */
	public static int getIdFromValueOrg(Properties ctx, String value, boolean client) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  p.m_product_id ");
		sql.append("FROM ");
		sql.append("  m_product p ");
		sql.append("  INNER JOIN exme_productoorg o ");
		sql.append("  ON p.m_product_id = o.m_product_id ");
		sql.append("WHERE ");
		sql.append("  p.value = ? AND ");
		sql.append("  p.IsActive = 'Y' ");
		if(client){
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, MEXMEProductoOrg.Table_Name, "o"));
		}else{
		sql.append(" AND o.AD_Client_ID = "); 
		sql.append(Env.getAD_Client_ID(ctx));
		sql.append(" AND o.AD_Org_ID = ");
		sql.append(Env.getAD_Org_ID(ctx));
		}
		return DB.getSQLValue(null, sql.toString(), new Object[]{value});
	}

	public static MProduct getIdProdByValue(Properties ctx, String value, boolean isByOrg) {

		// int id = 0;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	SELECT * FROM m_product WHERE value = ? AND isactive = 'Y'	");

		if(isByOrg) {
			sql.append("AND M_Product.AD_Client_ID = ? AND M_Product.AD_Org_ID = ? ");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MProduct producto = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, value);
			if (isByOrg) {
				pstmt.setInt(2, Env.getAD_Client_ID(ctx));
				pstmt.setInt(3, Env.getAD_Org_ID(ctx));
			}
			rs = pstmt.executeQuery();
			if (rs.next())
				producto = new MProduct(ctx, rs, null);

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getIdByValue", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return producto;
	}

	// Expert :Twry Medicamentos Controlados
	public int nivelControl() {
		if (getItemClass() != null) {
			if (getItemClass().equalsIgnoreCase(ITEMCLASS_Schedule_II)
					|| getItemClass().equalsIgnoreCase(
							ITEMCLASS_Misc_Controlled_2))
				return 2;
			if (getItemClass().equalsIgnoreCase(ITEMCLASS_Schedule_III))
				return 3;
			if (getItemClass().equalsIgnoreCase(ITEMCLASS_Misc_Controlled_1))
				return 1;
		}

		return 6;
	}

	public String nivelControlStr() {
		if (getItemClass() != null) {
			if (nivelControl() == 3 || nivelControl() == 2) {
				return "2";
			}
			if (nivelControl() == 1) {
				return "1";
			}
		}

		return "6";
	}

//	private static final String servicio = "SE%";// TODO

	/**
	 * Busca los servicios relacionados a un almacen y agrupador donde :
	 * ProductType = 'S', isEstudio = 'Y'
	 *
	 * @param ctx
	 * @param warehouseID
	 * @param freightCategoryID
	 * @param trxName
	 * @author Lorena Lama
	 * @return
	 * @deprecated
	 */
	public static List<org.compiere.util.mo.ServiciosMO> getEstudiosByAlmacen(Properties ctx,
			int warehouseID, int freightCategoryID, String trxName, String like) {
//		return getProducts(ctx, warehouseID, freightCategoryID,
//				PRODUCTTYPE_Service, servicio, true, trxName, like);
		List<org.compiere.util.mo.ServiciosMO> listaServicios = new ArrayList<org.compiere.util.mo.ServiciosMO>();
		String productType= PRODUCTTYPE_Service;
		Boolean  isEstudio = true;
		StringBuilder sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String in = " (" + DB.TO_STRING(MProduct.PRODUCTCLASS_Laboratory) + ", " + DB.TO_STRING(MProduct.PRODUCTCLASS_XRay) + ")";

		try {

			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT p.m_product_id as prodId,")
					.append(" p.value, p.name,p.description, p.c_uom_id as uom,")
					.append(" r.m_warehouse_id as alm,p.m_freightcategory_id as fcat,")
					.append(" p.m_product_category_id as pcat, c.uomsymbol as simbol,")
					.append(" c.name as nameuom ")
					.append("FROM m_product p")
					.append(" INNER JOIN m_replenish r ON ( p.m_product_id= r.m_product_id  AND r.m_warehouse_id = ? ")
					.append(" AND p.IsActive='Y' ")
					// activo,
					.append(productType != null ? " AND  p.producttype ="
							+ DB.TO_STRING(productType) : "")
					// tipo de producto
					.append(isEstudio != null ? " AND  p.ProductClass " + (isEstudio ?  " in " + in :
						" not " + in)
							: "")

					// estudio
					.append(" ) ")
					.append(" INNER JOIN c_uom c ON ( c.c_uom_id = p.c_uom_id )");

			if (like != null && like.length() > 0) {
				sql.append(" WHERE  UPPER(p.name) LIKE ?  ");
			}

			// si es por tipoProd // Lama: se quita validacion en duro de mtto
			// de tipo de producto.
			// if(tipoProducto != null){
			// sql.append(" INNER JOIN EXME_TipoProd tp ON (p.EXME_TipoProd_ID = tp.EXME_TipoProd_ID ")
			// .append(" AND  tp.value LIKE " + DB.TO_STRING(tipoProducto))//
			// tipo de producto
			// .append(" ) ")
			if (freightCategoryID >= 0) {
				if (like != null && like.length() > 0) {
					sql.append(" AND ");
				} else {
					sql.append(" WHERE ");
				}
				sql.append(" NVL(p.m_freightcategory_id,0) = ? ");
			}

			// agregamos el nivel de acceso
			if (ctx != null) {
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						Table_Name, "p"));
			}

			// Ticket 1237: Orden_alfabetico en servicios. Jesus Cantu.
			sql.append(" ORDER BY p.name");

			pstmt = DB.prepareStatement(sql.toString(), null);
			int i = 0;
			pstmt.setLong(++i, warehouseID);

			if (like != null && like.length() > 0) {
				pstmt.setString(++i, like);
			}
			if (freightCategoryID >= 0) {
				pstmt.setInt(++i, freightCategoryID);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				org.compiere.util.mo.ServiciosMO servicio = new org.compiere.util.mo.ServiciosMO();
				servicio.setM_freightcategory_id(rs.getInt("fcat"));
				servicio.setC_uom_id(rs.getInt("uom"));
				servicio.setM_product_id(rs.getInt("prodid"));
				servicio.setName(rs.getString("Name"));
				servicio.setValue(rs.getString("Value"));
				// servicio.setDescripcion(rs.getString("Description"));
				servicio.setM_productcategory_id(rs.getInt("pcat"));
				servicio.setM_warehouse_id(rs.getInt("alm"));
				servicio.setUnidadMedidaId(rs.getInt("uom"));
				servicio.setUnidadMedidaTxt(rs.getString("nameuom"));
				servicio.setSimbolo(rs.getString("simbol"));

				listaServicios.add(servicio);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return listaServicios;
	}


//	/**
//	 * Obtiene una lista de productos que son servicios (name y value)
//	 *
//	 * @param ctx
//	 * @return lst
//	 **/
//	public static List<LabelValueBean> getMProductList(Properties ctx)
//			throws Exception {
//
//		List<LabelValueBean> lst = new ArrayList<LabelValueBean>();
//		String sql = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql = "SELECT upper(mp.name), upper(mp.value) FROM M_Product mp INNER JOIN exme_tipoprod tp ON tp.exme_tipoprod_id=mp.exme_tipoprod_id AND tp.name LIKE 'SE%' ORDER BY mp.name";
//		pstmt = null;
//		rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql, null);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				LabelValueBean partner = new LabelValueBean(rs.getString(1),
//						rs.getString(2));
//				lst.add(partner);
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lst;
//	}

	/**
	 * Override Method from PO
	 *
	 * @author GValdez
	 * @return ID to be used or 0 for fedault logic
	 */
	protected int saveNew_getID() {
		return productID;
	} // saveNew_getID

	protected void setProductID(int productID) {
		this.productID = productID;
	}

	/**
	 * Obtiene los documentos de la tabla de productos
	 *
	 * @param ctx
	 *            Contexto
	 * @param w
	 *            IndexWriter al que perteneceran los documentos
	 */
	public static void getDocuments(Properties ctx, IndexWriter w) {
		StringBuilder st = new StringBuilder(
				"select m.M_Product_ID, m.value, m.name, m.productClass from M_Product m");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Document doc = new Document();
				if (rs.getString("productClass").equals(MProduct.PRODUCTCLASS_Laboratory) ||
						rs.getString("productClass").equals(MProduct.PRODUCTCLASS_XRay)){
					doc.add(new Field("label", Utilerias.getMessage(ctx, null,
							"msj.servicio"), Field.Store.YES,
							Field.Index.NOT_ANALYZED));
				} else {
					doc.add(new Field("label", Utilerias.getMessage(ctx, null,
							"msj.producto"), Field.Store.YES,
							Field.Index.NOT_ANALYZED));
				}
				doc.add(new Field("tabla", Table_Name, Field.Store.YES,
						Field.Index.NOT_ANALYZED));
				doc.add(new Field("id", rs.getString("M_Product_ID"),
						Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("value", rs.getString("value").toUpperCase(),
						Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("name", rs.getString("name").toUpperCase(),
						Field.Store.YES, Field.Index.ANALYZED));
				w.addDocument(doc);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
	}

	public static void getDocuments(Properties ctx, IndexWriter w, String query) {
		StringBuilder st = new StringBuilder(query);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Document doc = new Document();
				if (rs.getString("productClass").equals(MProduct.PRODUCTCLASS_Laboratory) ||
						rs.getString("productClass").equals(MProduct.PRODUCTCLASS_XRay) ) {
					doc.add(new Field("label", Utilerias.getMessage(ctx, null,
							"msj.servicio"), Field.Store.YES,
							Field.Index.NOT_ANALYZED));
				} else {
					doc.add(new Field("label", Utilerias.getMessage(ctx, null,
							"msj.producto"), Field.Store.YES,
							Field.Index.NOT_ANALYZED));
				}
				doc.add(new Field("tabla", Table_Name, Field.Store.YES,
						Field.Index.NOT_ANALYZED));
				doc.add(new Field("id", rs.getString("M_Product_ID"),
						Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("value", rs.getString("value").toUpperCase(),
						Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("name", rs.getString("name").toUpperCase(),
						Field.Store.YES, Field.Index.ANALYZED));
				w.addDocument(doc);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
	}

	/**
	 * insertAccounting
	 * @return
	 */
	public boolean insertAccounting() {
		boolean status = insert_Accounting_Client("M_Product_Acct",
				"M_Product_Category_Acct", "p.M_Product_Category_ID="
						+ getM_Product_Category_ID());
		return status;
	}

	/**
	 * insertAccounting
	 * @return
	 * @throws Exception
	 */
	public void insertAccounting(final int adClientID) throws Exception {
		if(!existeAcct()){
			/*insert_Accounting_Client("M_Product_Acct",
					"M_Product_Category_Acct", "p.M_Product_Category_ID="
							+ getM_Product_Category_ID(), adClientID);*/
			final MProductCategoryAcct bean = MProductCategoryAcct.get (getCtx(),
					getM_Product_Category_ID(), Env.getC_AcctSchema_ID(getCtx()), get_TrxName());
			if(bean==null){
				MProductCategory other = new MProductCategory(getCtx(), getM_Product_Category_ID(), get_TrxName());
				other.insertAccounting(adClientID);
			}

			insert_Accounting_Client("M_Product_Acct",
					"M_Product_Category_Acct", "p.M_Product_Category_ID="
							+ getM_Product_Category_ID(), adClientID);

		}
	}

//	// 19/07/2010 mvrodriguez
//	/**
//	 * Obtiene una Lista de Productos
//	 *
//	 * @param ctx
//	 * @param active
//	 *            Registro Activo o Inactivo
//	 * @param productTypeId
//	 *            Tipo de Producto requerido
//	 * @return Lista de objetos de tipo LabelValueBean
//	 */
//	public static List<LabelValueBean> getProductList(Properties ctx,
//			String active, int productTypeId) {
//		List<LabelValueBean> lst = new ArrayList<LabelValueBean>();
//		StringBuilder sql = new StringBuilder();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql.append("SELECT M_PRODUCT.M_PRODUCT_ID, M_PRODUCT.DESCRIPTION ");
//		sql.append("  FROM M_PRODUCT ");
//		sql.append(" WHERE M_PRODUCT.ISACTIVE = ? ");
//
//		if (DB.isOracle()) {
//			sql.append("   AND ROWNUM <= 100 "); // M_PRODUCT.EXME_TIPOPROD_ID =
//		} else if (DB.isPostgreSQL()) {
//			 sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 100));
//		}
//												// 1200024 AND
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setString(1, active);
//			// pstmt.setInt(2, productTypeId);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				LabelValueBean partner = new LabelValueBean(rs.getString(2),
//						String.valueOf(rs.getString(1)));
//				lst.add(partner);
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "getProductList - sql: " + sql, e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lst;
//	}

//	/**
//	 *
//	 * @param ctx el contexto de la aplicacion
//	 * @param like la clausula like en caso de existir
//	 * @return una lista de objetos de tipo
//	 * @throws Exception
//	 */
//	public static List<LabelValueBean> getMProductLst(Properties ctx,
//			String like) throws Exception {
//
//		List<LabelValueBean> lst = new ArrayList<LabelValueBean>();
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql.append("SELECT UPPER(mp.name), mp.M_Product_id FROM M_Product mp WHERE mp.ad_client_id = 0 ");
//
//		if (like != null) {
//			sql.append("AND UPPER(mp.Name) LIKE  ? ");
//		}
//
//		if (DB.isOracle()) {
//			sql.append(" AND rownum < 100 ");
//		}
//
//		sql.append(" ORDER BY mp.name ");
//
//		if (DB.isPostgreSQL()) {
//			 sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 99));
//		}
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			if (like != null) {
//				pstmt.setString(1, like);
//			}
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				LabelValueBean partner = new LabelValueBean(rs.getString(1),
//						rs.getString(2));
//				lst.add(partner);
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "getMProductLst - sql: " + sql, e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lst;
//	}

	/**
	 * Obtener el producto relacionado con la intervencion(servicio)
	 * seleccionada
	 *
	 * @param ctx
	 * @param interventionId
	 * @param clientId
	 * @return MProduct
	 * @author lhernandez
	 */
	public static List<MProduct> getProductsByInterventionId(Properties ctx,
			int interventionId, int clientID, int orgID) {

		List<MProduct> list = new ArrayList<MProduct>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT * ")
		   .append("FROM M_Product ")
		   .append("WHERE AD_Client_ID = ?  ")
		   .append("AND AD_ORG_ID = ?  ")
		   .append("AND EXME_Intervencion_ID = ? ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, clientID);
			pstmt.setInt(2, orgID);
			pstmt.setInt(3, interventionId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MProduct(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			if (list.size()==0 && clientID>0 && orgID>0){
				list = getProductsByInterventionId(ctx, interventionId, 0, 0);
			}
			DB.close(rs, pstmt);
		}
		return list;
	}

//	/**
//	 * Busca los productos relacionados a un almacen, agrupador, tipos de
//	 * producto
//	 *
//	 * @param ctx
//	 * @param warehouseID
//	 *            - almacen de surtido/aplicacion de producto
//	 * @param freightCategoryID
//	 *            - agrupador de productos (nulo = 0)
//	 * @param productType
//	 *            - tipo de producto (ProductType)
//	 * @param < tipoProducto - Nombre del tipo de Producto (EXME_TipoProd.Value)
//	 * @deprecated: Se quita validacion en duro para servicios />
//	 * @param isEstudio
//	 *            - si es estudio o no (puede ser nulo)
//	 * @param trxName
//	 * @author Lorena Lama
//	 * @return
//	 */
//	public static List<ServiciosMO> productSearch(Properties ctx,
//			int warehouseID, int freightCategoryID, String productType,
//			Boolean isEstudio, String trxName, String value, int results,
//			String... colsNames) {
//		List<ServiciosMO> listaServicios = new ArrayList<ServiciosMO>();
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String in = " (" + DB.TO_STRING(MProduct.PRODUCTCLASS_Laboratory) + ", " + DB.TO_STRING(MProduct.PRODUCTCLASS_XRay) + ")";
//		try {
//			sql.append("SELECT p.m_product_id as prodId,")
//					.append(" p.value, p.name,p.description, p.c_uom_id as uom,")
//					//.append(" r.m_warehouse_id as alm,")
//					.append(" p.m_freightcategory_id as fcat,")
//					.append(" p.m_product_category_id as pcat, c.uomsymbol as simbol,")
//					.append(" c.name as nameuom ")
//					.append(" FROM m_product p")
//					.append(" INNER JOIN c_uom c ON ( c.c_uom_id = p.c_uom_id )")
//					//.append(" INNER JOIN m_replenish r ON ( p.m_product_id= r.m_product_id  AND r.m_warehouse_id = ? ")
//					.append(" WHERE p.IsActive='Y' AND P.AD_Org_Id = ? ")
//					// activo,
//					.append(productType != null ? " AND  p.producttype ="
//							+ DB.TO_STRING(productType) : "")
//					// tipo de producto
//					.append(isEstudio != null ? " AND  p.ProductClass " + (isEstudio ?  " in " + in :
//						" not " + in)
//							: "");
//					// estudio
//					//.append(" ) ")
//					//.append(" INNER JOIN c_uom c ON ( c.c_uom_id = p.c_uom_id )");
//			//sql.append(" WHERE 1 = 1 ");
//			if (freightCategoryID >= 0) {
//				sql.append(" AND NVL(p.m_freightcategory_id,0) = ? ");
//			}
//			// agregamos el nivel de acceso
//			if (ctx != null) {
//				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
//						Table_Name, "p"));
//			}
//
//			String[] txt = StringUtils.split(value);
//			Object[] params2 = new Object[colsNames.length * txt.length];
//			int x = 0;
//			for (int j = 0; j < txt.length; j++) {
//				sql.append(" AND ( ");
//				// agregar las columnas
//				for (int i = 0; i < colsNames.length; i++) {
//					if (i > 0) {
//						sql.append(" OR ");
//					}
//					sql.append(" UPPER(TRANSLATE(");
//					sql.append(colsNames[i]).append(", ")
//							.append(Constantes.TRANSLATE_VOCALS).append("))");
//					sql.append(" LIKE UPPER(TRANSLATE(?, ")
//							.append(Constantes.TRANSLATE_VOCALS).append("))");
//
//					params2[x++] = StringUtils.isBlank(txt[j]) ? txt[j]
//							: ("%"
//									+ StringUtils.remove(txt[j].toUpperCase(),
//											"*") + "%");
//				}
//				sql.append(") ");
//			}
//
//			if (DB.isOracle()) {
//				sql.append(" AND rownum <=  ").append(results);
//			}
//
//			sql.append(" ORDER BY p.name");
//
//			if (DB.isPostgreSQL()) {
//				sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, results));
//			}
//
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			int i = 0;
//			pstmt.setLong(++i, warehouseID);
//			if (freightCategoryID >= 0) {
//				pstmt.setInt(++i, freightCategoryID);
//			}
//			if (params2 != null && params2.length > 0) {
//				for (int j = 0; j < params2.length; j++) {
//					DB.setParameter(pstmt, ++i, params2[j]);
//				}
//			}
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				ServiciosMO servicio = new ServiciosMO();
//				servicio.setM_freightcategory_id(rs.getInt("fcat"));
//				servicio.setC_uom_id(rs.getInt("uom"));
//				servicio.setM_product_id(rs.getInt("prodid"));
//				servicio.setName(rs.getString("Name"));
//				servicio.setValue(rs.getString("Value"));
//				servicio.setDescripcion(rs.getString("Description"));
//				servicio.setM_productcategory_id(rs.getInt("pcat"));
//				//servicio.setM_warehouse_id(rs.getInt("alm"));
//				servicio.setUnidadMedidaId(rs.getInt("uom"));
//				servicio.setUnidadMedidaTxt(rs.getString("nameuom"));
//				servicio.setSimbolo(rs.getString("simbol"));
//
//				listaServicios.add(servicio);
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "productSearch - sql: " + sql, e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return listaServicios;
//
//	}

	/*** TODO **/

	/**
	 *
	 */
	public boolean createProductCte() {
		X_M_Product_Cte cte = new X_M_Product_Cte(getCtx(), 0, get_TrxName());

		cte.setM_Product_ID(getM_Product_ID());
		cte.setIsLot(false);

		cte.setEXME_FactorPre_ID(super.getEXME_FactorPre_ID());
		cte.setEXME_ConceptoFac_ID(super.getEXME_ConceptoFac_ID());

		// mandatory
		/*int chargeID = super.getC_Charge_ID();
		if (chargeID <= 0) {
			// Expert: Twry ... obtenemos el cargo por defecto
			if (getM_Product_Category_ID() > 0) {
				String sql = " SELECT C_Charge_ID FROM M_Product_Category WHERE M_Product_Category_ID = ? ";
				chargeID = DB.getSQLValue(get_TrxName(), sql,
						getM_Product_Category_ID());
			}
			if (chargeID <= 0) {
				// prices configuration
				MEXMEConfigPre config = MEXMEConfigPre.get(getCtx(), null);
				chargeID = config != null ? config.getC_Charge_ID() : -1;
				if (chargeID <= 0) {
					// LAMA
					String sql = " SELECT c.C_Charge_ID FROM C_Charge c INNER JOIN C_TaxCategory t ON t.C_TaxCategory_ID = c.C_TaxCategory_ID"
							+ " WHERE t.isDefault = 'Y' AND c.AD_Client_ID = ? ";
					chargeID = DB.getSQLValue(get_TrxName(), sql,
							getAD_Client_ID());
					if (chargeID <= 0) {
						chargeID = Env.getContextAsInt(getCtx(), "#C_Charge");
						if (chargeID <= 0) {
							chargeID = MCharge.getCharge(getCtx())
									.getC_Charge_ID();
						}
					}
				}
			}
		}
		cte.setC_Charge_ID(chargeID);*/
		// mandatory
		int taxCategoryID = super.getC_TaxCategory_ID();
		if (taxCategoryID <= 0) {
			taxCategoryID = Env.getContextAsInt(getCtx(), "#C_TaxCategory");
			if (taxCategoryID <= 0) {
				taxCategoryID = Env.getContextAsInt(getCtx(),
						"#C_TaxCategory_ID");
				if (taxCategoryID <= 0) {
					taxCategoryID = DB.getSQLValue(
							null,
							"SELECT C_TaxCategory_ID FROM C_TaxCategory WHERE isDefault = 'Y' AND AD_Client_ID = ? "
									+ MEXMELookupInfo.addAccessLevelSQL(
											getCtx(), " ",
											MTaxCategory.Table_Name),Env.getAD_Client_ID(getCtx()));
				}
			}
		}
		cte.setC_TaxCategory_ID(taxCategoryID);

		return cte.save();
	}

	// 'C_CHARGE_ID','EXME_CONCEPTOFAC_ID','ISLOT','EXME_FACTORPRE_ID','C_TAXCATEGORY_ID'
	/*public int getC_Charge_ID() {
		// super.getC_Charge_ID();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT C_Charge_ID FROM M_Product_Cte WHERE M_Product_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",
				"M_Product_Cte"));
		int val = DB.getSQLValue(get_TrxName(), sql.toString(),
				getM_Product_ID());
		return val <= 0 ? super.getC_Charge_ID() : val;
	}*/

	public int getEXME_ConceptoFac_ID() {
		// super.getEXME_ConceptoFac_ID();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_ConceptoFac_ID FROM M_Product_Cte WHERE M_Product_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",
				"M_Product_Cte"));
		int val = DB.getSQLValue(get_TrxName(), sql.toString(),
				getM_Product_ID());
		return val <= 0 ? super.getEXME_ConceptoFac_ID() : val;
	}

	public boolean isLot() {
		if(getProdOrg()!=null)
			return getProdOrg().isLot();
		else
			return false;
	}
/*
	public int getEXME_FactorPre_ID() {
		// super.getEXME_FactorPre_ID();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_FactorPre_ID FROM M_Product_Cte WHERE M_Product_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",
				"M_Product_Cte"));
		int val = DB.getSQLValue(get_TrxName(), sql.toString(),
				getM_Product_ID());
		return val <= 0 ? super.getEXME_FactorPre_ID() : val;
	}
*/
	public int getC_TaxCategory_ID() {
		// super.getC_TaxCategory_ID();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT C_TaxCategory_ID FROM M_Product_Cte WHERE M_Product_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",
				"M_Product_Cte"));
		int val = DB.getSQLValue(get_TrxName(), sql.toString(),
				getM_Product_ID());
		return val <= 0 ? super.getC_TaxCategory_ID() : val;
	}

	private BigDecimal quantity = Env.ZERO;
	private String comment = null;

	//indica a que version de paquete pertenece (si esque esta dentro de uno)
	private int EXME_PaqBase_Version_ID = 0;

	public int getEXME_PaqBase_Version_ID() {
		return EXME_PaqBase_Version_ID;
	}

	public void setEXME_PaqBase_Version_ID(int eXME_PaqBase_Version_ID) {
		EXME_PaqBase_Version_ID = eXME_PaqBase_Version_ID;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Regresa una lista de aquellos productos realcionados a el RxNorm elegido
	 * por el medico en la ejecucion de cita
	 */
	public static List<KeyNamePair> getProductsFromGenProd(Properties ctx,
			int exmeGenProductId) {
		final List<KeyNamePair> prods = new ArrayList<KeyNamePair>();

		StringBuilder sql = new StringBuilder(
				"SELECT p.value||' '|| p.description||'/'||l.MFGNAME as label, p.m_product_id as value")
				.append(" FROM M_PRODUCT P inner join exme_labeler l on l.exme_labeler_id = p.exme_labeler_id")
				.append(" WHERE P.ISACTIVE='Y' AND P.EXME_GENPRODUCT_ID=? ");

		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
		ResultSet rs = null;

		try {
			pstmt.setInt(1, exmeGenProductId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				prods.add(new KeyNamePair(rs.getInt("value"),rs.getString("label")));
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return prods;
	}


	/**
	 * Regresa una lista de aquellos productos realcionados a el RxNorm elegido
	 * por el medico en la ejecucion de cita
	 */
	public static List<MProduct> getNDCs(Properties ctx, String where) {
		List<MProduct> prods = new ArrayList<MProduct>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT M_PRODUCT.* FROM M_PRODUCT ")
		.append(where);


		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
		ResultSet rs = null;

		try {

			rs = pstmt.executeQuery();

			while (rs.next()) {
				prods.add(new MProduct(ctx, rs, null));
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return prods;
	}
	/**
	 * Regresa el id del producto realcionado a el RxNorm elegido
	 * solo cuando la relacion es de 1 - 1 @author Lorena Lama
	 * - Se deja el primero encontrado por defecto (20110928)
	 */
	public static int getNDC(final Properties ctx, final int genProdId) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT M_PRODUCT.M_PRODUCT_ID FROM M_PRODUCT ");
		sql.append(MEXMEPrescRX.getSqlMeds(ctx, -1, true));
		final int prod= DB.getSQLValue(null, sql.toString(), genProdId);
		return prod;
	}

	/** @deprecated {@link #getNDC(int)} */
	public static int getNDC(Properties ctx, String where, Object... params) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT M_PRODUCT.M_PRODUCT_ID FROM M_PRODUCT ").append(where);
		return DB.getSQLValue(null, sql.toString(), params);
	}

	/**
	 *
	 * @param external
	 */
	public static int getIDByValue(String name){
		int mProductId = 0;


		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT M_Product_ID FROM M_Product WHERE value = ? ");
		sql.append(" AND isactive = 'Y' ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), sql.toString(), Table_ID));

		sql.append("ORDER BY AD_Client_ID, AD_Org_ID DESC");


		mProductId = DB.getSQLValue(null, sql.toString(), name);
//		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
//		ResultSet rs = null;
//
//		try {
//			pstmt.setString(1, name);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				MProduct prod = new MProduct(Env.getCtx(), rs, null);
//				mProductId = prod.getM_Product_ID();
//			}
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}

		return mProductId;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}

	public boolean isExternal() {
		return external;
	}

	public void setAnioInicial(int anioInicial) {
		this.anioInicial = anioInicial;
	}

	public int getAnioInicial() {
		return anioInicial;
	}

	public void setAnioFinal(int anioFinal) {
		this.anioFinal = anioFinal;
	}

	public int getAnioFinal() {
		return anioFinal;
	}

	public void setMesInicial(int mesInicial) {
		this.mesInicial = mesInicial;
	}

	public int getMesInicial() {
		return mesInicial;
	}

	public void setMesFinal(int mesFinal) {
		this.mesFinal = mesFinal;
	}

	public int getMesFinal() {
		return mesFinal;
	}

	/**
	 * String Representation
	 *
	 * @return info
	 */
	public String getFullName() {
		StringBuffer sb = new StringBuffer();
		sb.append(getValue());
		sb.append(" - ");
		if(StringUtils.isNotEmpty(getDescription())){
			sb.append(getDescription());
		} else {
			sb.append(getName());
		}
		return sb.toString();
	} // toString

	public void setActPacienteIndId(int actPacienteIndId) {
		this.actPacienteIndId = actPacienteIndId;
	}

	public int getActPacienteIndId() {
		return actPacienteIndId;
	}
	public boolean isTodayService() {
		return isTodayService;
	}
	public void setTodayService(boolean isTodayService) {
		this.isTodayService = isTodayService;
	}

	/**
	 * Solo crear el producto a nivel organizacion
	 * Si el producto ya existe no se vuelve a crear solo se actualiza
	 * @return <MEXMEProductoOrg>
	 */
	public MEXMEProductoOrg getProductoOrgNew() {
		if(prodOrg == null && getM_Product_ID()>0){
			prodOrg = getProdOrg();
			if(prodOrg==null){
				prodOrg = new MEXMEProductoOrg(getCtx(), 0, null);
				prodOrg.setM_Product_ID(getM_Product_ID());
				prodOrg.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
				prodOrg.save(get_TrxName());
			}
		}
		return prodOrg;
	}

	/**
	 * Obtiene la configuracion activa a nivel de organizacion del producto
	 * @return null o <MEXMEProductoOrg>
	 */
	public MEXMEProductoOrg getProdOrg() {
		if(prodOrg == null){
			prodOrg = MEXMEProductoOrg.getProductOrg(getCtx()
					, getM_Product_ID()
					, Env.getAD_Org_ID(getCtx())
					, 1);//GetRevCode.calcularRevCode(getCtx(), getM_Product_ID());
		}
		return prodOrg;
	}

	/**
	 * Obtiene la configuracion (activa o inactiva) a nivel de organizacion del producto,
	 * dando por hecho que solo puede haber un registro a la vez
	 * @return null o <MEXMEProductoOrg>
	 */
	public MEXMEProductoOrg getProductoOrg() {
		if(prodOrg == null){
			prodOrg = MEXMEProductoOrg.getProductOrg(getCtx()
					, getM_Product_ID()
					, Env.getAD_Org_ID(getCtx())
					, -1);//	prodOrg = GetRevCode.getProductoOrg(getCtx(), getM_Product_ID(),false);
		}
		return prodOrg;
	}


	/**
	 * CPT
	 */
	private MIntervencion intevencion = null;

	public void setIntevencion(MIntervencion intevencion) {
		this.intevencion = intevencion;
	}

	public MIntervencion getIntevencion() {
		if(intevencion==null ){
			intevencion = new MIntervencion(getCtx(), getEXME_Intervencion_ID(), null);
		}
		return this.intevencion;
	}

	/**
	 * AttributeSet
	 */
	public int getM_AttributeSet_ID ()
	{
		int id = 0;
		if(getProdOrg()!=null)
			id = getProdOrg().getM_AttributeSet_ID();

		return id;
	}

	/**
	 * El producto enviado como parametro
	 * puede ser parte de los cargos de un paciente?
	 * @param ctx
	 * @param M_Product_ID
	 * @return
	 */
	public boolean isProduct() {
		return MProduct.isProduct(getCtx(), getM_Product_ID());
	}

	/**
	 * Evalua que el producto no sea un producto comodin
	 * @param ctx
	 * @param M_Product_ID
	 * @return
	 */
	public static boolean isProduct(Properties ctx, int M_Product_ID){
		// Que no sea un NDC temporal ni un NDC no disponible
		final MEXMEMejoras mej = MEXMEMejoras.get(ctx);
		if (mej != null && (M_Product_ID == mej.getTemporaryNDC() || M_Product_ID == mej.getNotAvailableNDC())) {
			return false;
		}
		final MEXMEConfigPre pre = MEXMEConfigPre.get(ctx, null);
		if (pre != null && (//
			M_Product_ID == pre.getDeducible_ID() // Que no sea deducible
				|| M_Product_ID == pre.getCoaseguro_ID() // Que no sea coaseguro
				|| M_Product_ID == pre.getCoaseguroMed_ID() // Que no sea coaseguro medico
			|| M_Product_ID == pre.getCoPago_ID()// Que no sea copago
			)) {
			return false;
		}
		return true;
	}

	/**
	 * Evalua que el producto no sea un producto comodin
	 * @param ctx
	 * @param M_Product_ID
	 * @return
	 */
	public boolean isCCCmD(){
		MEXMEConfigPre pre = MEXMEConfigPre.get(getCtx(), null);
		// Que no sea deducible, ni coaseguro, ni coaseguro medico, ni copago
		return pre!=null && ( getM_Product_ID() == pre.getDeducible_ID() || getM_Product_ID() == pre.getCoaseguro_ID() || getM_Product_ID() == pre.getCoaseguroMed_ID() || getM_Product_ID() == pre.getCoPago_ID());
	}

	/**
	 * Producto a los que se les haga una actividad paciente
	 * @return
	 */
	public boolean isProductActPac(){
		return
		   !getProductClass().equalsIgnoreCase(MProduct.PRODUCTCLASS_Rooms)
		&& !getProductClass().equalsIgnoreCase(MProduct.PRODUCTCLASS_Others)
		&& !getProductClass().equalsIgnoreCase(MProduct.PRODUCTCLASS_MagneticResonance)
		&& !getProductClass().equalsIgnoreCase(MProduct.PRODUCTCLASS_ComputedTomography)
		&& !getProductClass().equalsIgnoreCase(MProduct.PRODUCTCLASS_MedicalEquipment)
		&& !getProductClass().equalsIgnoreCase(MProduct.PRODUCTCLASS_PhysicalTherapy);
	}

	public void setServView(ServicioView servView) {
		this.servView = servView;
	}

	public ServicioView getServView() {
		return servView;
	}

	/**
	 * ID de impuesto del producto
	 *
	 * @return
	 */
	public int getC_Tax_ID() {
		if (mTax == null) {
			mTax = getTax();
		}
		return mTax != null ? mTax.getC_Tax_ID() : 0;
	}

	/**
	 * Tasa de impuesto
	 */
	private MTax mTax = null;

	/**
	 * Obj tasa de impuesto
	 * @return <MTax>
	 */
	public MTax getmTax() {
		return mTax;
	}

	/**
	 * Obj tasa de impuesto
	 * @return <MTax>
	 */
	public void setmTax() {
		mTax = null;
	}

	/**
	 * Objeto de impuesto del producto
	 *
	 * @return <MTax> tasa de impuesto
	 */

	public MTax getTax() {
		return MEXMETax.getImpuestoProducto(getCtx(), getM_Product_ID(), get_TrxName());
	}

	/**
	 * Busca que el producto se encuentre agregado en EXME_ProductOrg (Charge Master) para
	 * verificar que este disponible para la Org con la que se esta logeado (Ctx)
	 * @param ctx Contexto
	 * @param mProductId Id del Producto a validar
	 * @return Si se encuentra o no se encuentra disponible
	 */
	public static boolean isAvailableForLoggedOrg(Properties ctx, int mProductId){
		return MEXMEProductoOrg.getObj(ctx, mProductId, null) != null;
	}

	/**
	 *
	 */
	public MEXMEStopPolicy getAutoStopPolicy(boolean throwException) {
		// familia de productos, para buscar si existe una configuración de duración de aplicación de medicamento
		// para la familia del producto seleccionado
		// final MEXMEProductFam pFam = new MEXMEProductFam(ctx, getM_Product().getEXME_ProductFam_ID(), null);
		// verificar si hay una configuración del numero de dias durante los cuales se aplicará el médicamento
		final MEXMEStopPolicy policy = MEXMEStopPolicy.getFromProdFamId(getCtx(), getEXME_ProductFam_ID());
		// debe existir la configuracion de Automatic Stop Policy
		if (policy == null && throwException) {
			String fam = DB.getSQLValueString(null, "SELECT NAME From EXME_ProductFam WHERE EXME_ProductFam_ID=?", getEXME_ProductFam_ID());
			throw new MedsysException(Utilerias.getAppMsg(getCtx(), "error.stopPolicy.family", fam, getName()));
		}
		return policy;
	}


	/** Set Is Covered By Insurance.
		@param IsCoveredByInsurance Is Covered By Insurance	  */
	public void setIsCoveredByInsurance (boolean IsCoveredByInsurance)
	{
		if(getProdOrg()!=null) {
			getProdOrg().setIsCoveredByInsurance(IsCoveredByInsurance);
		}
	}

	/** Get Is Covered By Insurance.
		@return Is Covered By Insurance	  */
	public boolean isCoveredByInsurance ()
	{
		if(getProdOrg()!=null) {
			return getProdOrg().isCoveredByInsurance();
		} else {
			return false;
		}
	}


	/**
	 * Regresa el id del producto
	 */
	public static int get(Properties ctx, String where, String trxName, Object[] params) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT M_PRODUCT.M_PRODUCT_ID FROM M_PRODUCT ").append(where);
		return DB.getSQLValue(null, sql.toString(), params);
	}

	private int EXME_OrderSet_ID = 0;

	public int getEXME_OrderSet_ID() {
		return EXME_OrderSet_ID;
	}

	public void setEXME_OrderSet_ID(int eXME_OrderSet_ID) {
		EXME_OrderSet_ID = eXME_OrderSet_ID;
	}

	/**
	 * Retorna MProduct de Coaseguro, Coaseguro medico, copago o deducible
	 * @param concept
	 * @return
	 */
	public static MProduct getCCCmD(final String concept){
		int productId = 0;
		MEXMEConfigPre conf = MEXMEConfigPre.get(Env.getCtx(), null);

		// Ids
		if(conf!=null){
			if(X_EXME_CtaPacDet.TIPOLINEA_Coaseguro.equals(concept)){
				productId = conf.getCoaseguro_ID();
			} else if(X_EXME_CtaPacDet.TIPOLINEA_CoaseguroMedico.equals(concept)){
				productId = conf.getCoaseguroMed_ID();
			} else if(X_EXME_CtaPacDet.TIPOLINEA_Copago.equals(concept)){
				productId = conf.getCoPago_ID();
			} else if(X_EXME_CtaPacDet.TIPOLINEA_Deducible.equals(concept)){
				productId = conf.getDeducible_ID();
			}
		}
		return new MProduct(Env.getCtx(), productId, null);
	}


	/**
	 * Obtener el producto relacionado con la intervencion(servicio)
	 * seleccionada
	 *
	 * @param ctx
	 * @param interventionId
	 * @param clientId
	 * @return MProduct
	 * @author lhernandez
	 */
	public static X_M_Product_Cte getProductCte(Properties ctx,
			int mProductID, int clientId, int orgId) {

		X_M_Product_Cte product = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" select * from m_product_cte where isActive = 'Y' AND M_Product_ID = ? and ad_client_id = ? and aD_org_id in (0,?) ");
		sql.append(" order by ad_org_id desc ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, mProductID);
			pstmt.setInt(2, clientId);
			pstmt.setInt(3, orgId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				product = new X_M_Product_Cte(ctx, rs, null);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return product;
	}
	/**
	 * Listado de unidades de medida Volumen y Minima
	 * @return List<KeyNamePair>
	 * @deprecated Mal nombrado, use getUomsProduct
	 */
	public List<KeyNamePair> uomsProduct(){
		return getUomsProduct();
	}

	/**
	 * Listado de unidades de medida Volumen y Minima
	 * @return List<KeyNamePair>
	 */
	public List<KeyNamePair> getUomsProduct(){
		List<KeyNamePair> lst = new ArrayList<KeyNamePair>();
		// Unidad de medida minima
		MUOM uomVol = new MUOM(getCtx(), getC_UOM_ID(), get_TrxName());
		KeyNamePair unitMin = new KeyNamePair(getC_UOM_ID(), uomVol.getTrlName());
		lst.add(unitMin);

		// Unidad de medida de volument
		if(getC_UOM_ID() != getC_UOMVolume_ID()){
			uomVol = new MUOM(getCtx(), getC_UOMVolume_ID(), get_TrxName());
			KeyNamePair unitVol = new KeyNamePair(uomVol.getC_UOM_ID(), uomVol.getTrlName());
			lst.add(unitVol);
		}
		return lst;
	}

	/**
	 * Conversion de cantidades
	 * @param qty
	 * @param uomId
	 * @return
	 */
	public BigDecimal[] qtyConversionTo(final BigDecimal qty, final int uomId){
		BigDecimal[] qtys = new BigDecimal[2];

		// Si las unidades de volumen y minima son las mismas
		if(getC_UOM_ID() == getC_UOMVolume_ID()){
			//se establece la precision
			BigDecimal qtyTmp = qty.setScale(MUOM.getPrecision(getCtx(), getC_UOM_ID()), BigDecimal.ROUND_HALF_UP);
			qtys[0]=qtyTmp;// Min
			qtys[1]=qtyTmp;// Vol

		}else{

			// Si la unidad seleccionada es la unidad minima
			if(uomId == getC_UOM_ID()){

				BigDecimal qtyVol = qty == null
						? BigDecimal.ZERO
						: MEXMEUOMConversion.convertProductTo (getCtx(), getM_Product_ID(),getC_UOMVolume_ID(), qty, null);

				if(qtyVol==null){
					qtyVol = Env.ZERO;
				}

				//se establece la precision
				qtyVol = qtyVol.setScale(MUOM.getPrecision(getCtx(), getC_UOMVolume_ID()), BigDecimal.ROUND_HALF_UP);
				BigDecimal qtyTmp = qty.setScale(MUOM.getPrecision(getCtx(), getC_UOM_ID()), BigDecimal.ROUND_HALF_UP);

				qtys[0]=qtyTmp;// Min
				qtys[1]=qtyTmp;// Vol

			}else if(uomId == getC_UOMVolume_ID()){

				// Si la unidad seleccionada es la de volumen
				BigDecimal qtyMin = qty==null
						? BigDecimal.ZERO
						: MEXMEUOMConversion.convertProductFrom(getCtx(), getM_Product_ID(),getC_UOMVolume_ID(), qty, null);

				if(qtyMin==null){
					qtyMin = Env.ZERO;
				}

				//se establece la precision
				qtyMin = qtyMin.setScale(MUOM.getPrecision(getCtx(), getC_UOM_ID()), BigDecimal.ROUND_HALF_UP);
				BigDecimal qtyTmp = qty.setScale(MUOM.getPrecision(getCtx(), getC_UOMVolume_ID()), BigDecimal.ROUND_HALF_UP);

				qtys[0]=qtyTmp;// Min
				qtys[1]=qtyMin;// Vol
			}
		}

		return qtys;
	}


	/**
	 * Conversion de cantidades
	 * @param qty : cantidad a convertir a la unidad de medida de parametro
	 * @param uomId : unidad a convertir
	 * @return
	 */
	public BigDecimal[] qtyConversion(final BigDecimal qty, final int uomId){
		BigDecimal[] qtys = new BigDecimal[2];

		// Si las unidades de volumen y minima son las mismas
		if(getC_UOM_ID() == getC_UOMVolume_ID()){
			//se establece la precision
			BigDecimal qtyTmp = qty.setScale(MUOM.getPrecision(getCtx(), getC_UOM_ID()), BigDecimal.ROUND_HALF_UP);
			qtys[0]=qtyTmp;// Min
			qtys[1]=qtyTmp;// Vol

		}else{

			// Si la unidad seleccionada es la unidad minima
			if(uomId == getC_UOM_ID()){

				BigDecimal qtyVol = qty == null
						? BigDecimal.ZERO
						: MEXMEUOMConversion.convertProductTo (getCtx(), getM_Product_ID(),getC_UOMVolume_ID(), qty, null);

				if(qtyVol==null){
					qtyVol = Env.ZERO;
				}

				//se establece la precision
				qtyVol = qtyVol.setScale(MUOM.getPrecision(getCtx(), getC_UOMVolume_ID()), BigDecimal.ROUND_HALF_UP);
				BigDecimal qtyTmp = qty.setScale(MUOM.getPrecision(getCtx(), getC_UOM_ID()), BigDecimal.ROUND_HALF_UP);

				qtys[0]=qtyTmp;// Min
				qtys[1]=qtyVol;// Vol

			}else if(uomId == getC_UOMVolume_ID()){

				// Si la unidad seleccionada es la de volumen
				BigDecimal qtyMin = qty==null
						? BigDecimal.ZERO
						: MEXMEUOMConversion.convertProductFrom(getCtx(), getM_Product_ID(),getC_UOMVolume_ID(), qty, null);

				if(qtyMin==null){
					qtyMin = Env.ZERO;
				}

				//se establece la precision
				qtyMin = qtyMin.setScale(MUOM.getPrecision(getCtx(), getC_UOM_ID()), BigDecimal.ROUND_HALF_UP);
				BigDecimal qtyTmp = qty.setScale(MUOM.getPrecision(getCtx(), getC_UOMVolume_ID()), BigDecimal.ROUND_HALF_UP);

				qtys[0]=qtyMin;// Min
				qtys[1]=qtyTmp;// Vol
			}
		}

		return qtys;
	}

	public BigDecimal convertToMinimum(BigDecimal amount, boolean standardPrecision) {
		return MEXMEUOMConversion.convertProductFrom(getCtx(), getM_Product_ID(), getC_UOMVolume_ID(), amount, null, standardPrecision);
	}

	/**
	 * Encuentra los productos activos almacenados en exmeproductoorg
	 * @param ctx
	 * @param orgID
	 * @param trxName
	 * @return
	 */
	public static List<MProduct> getProductsByOrg(Properties ctx, int orgID, String trxName) {

		List<MProduct> lst = new ArrayList<MProduct>();

		PreparedStatement psmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT M_PRODUCT.* FROM M_PRODUCT ")
		.append("INNER JOIN EXME_PRODUCTOORG ON EXME_PRODUCTOORG.M_PRODUCT_ID = M_PRODUCT.M_PRODUCT_ID ")
		.append("WHERE EXME_PRODUCTOORG.AD_ORG_ID = ? AND M_PRODUCT.ISACTIVE = 'Y' ");

		try {
			psmt = DB.prepareStatement(sql.toString(), null);
			psmt.setInt(1, orgID);
			rs = psmt.executeQuery();

			while (rs.next()) {
				lst.add(new MProduct(ctx, rs, trxName) );
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return lst;
	}


	/**
	 * Regresa el listado de productos existentes en una version de lista de precios, productos a
	 * nivel organizacion
	 * @param ctx
	 * @param versionID
	 * @param orgID
	 * @return
	 */
	public static List<MProduct> getProductsByVersion(Properties ctx, int versionID, int orgID, String trxName) {

		List<MProduct> lst = new ArrayList<MProduct>();

		PreparedStatement psmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT M_PRODUCT.* FROM M_PRODUCT ")
		.append("INNER JOIN M_PRODUCTPRICE ON M_PRODUCTPRICE.M_PRODUCT_ID = M_PRODUCT.M_PRODUCT_ID ")
		.append("INNER JOIN EXME_PRODUCTOORG ON EXME_PRODUCTOORG.M_PRODUCT_ID = M_PRODUCT.M_PRODUCT_ID ")
		.append("WHERE M_PRODUCTPRICE.M_PRICELIST_VERSION_ID= ? AND  EXME_PRODUCTOORG.AD_ORG_ID = ? AND M_PRODUCT.ISACTIVE = 'Y' ");

		try {
			psmt = DB.prepareStatement(sql.toString(), null);
			psmt.setInt(1, versionID);
			psmt.setInt(2, orgID);
			rs = psmt.executeQuery();

			while (rs.next()) {
				lst.add(new MProduct(ctx, rs, trxName) );
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return lst;
	}

	/**
	 * Regresa el listado de productos existentes que correspondan a cierta categoria, productos a
	 * nivel organizacion
	 * @param ctx
	 * @param categoryID
	 * @param orgID
	 * @param trxName
	 * @return
	 */
	public static List<MProduct> getProductsByCategory(Properties ctx, int categoryID, int orgID, String trxName) {

		List<MProduct> lst = new ArrayList<MProduct>();

		PreparedStatement psmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT M_PRODUCT.* FROM M_PRODUCT ")
		.append("INNER JOIN EXME_PRODUCTOORG ON EXME_PRODUCTOORG.M_PRODUCT_ID = M_PRODUCT.M_PRODUCT_ID ")
		.append("WHERE M_PRODUCT.M_PRODUCT_CATEGORY_ID = ? AND  EXME_PRODUCTOORG.AD_ORG_ID = ? AND M_PRODUCT.ISACTIVE = 'Y' ");

		try {
			psmt = DB.prepareStatement(sql.toString(), null);
			psmt.setInt(1, categoryID);
			psmt.setInt(2, orgID);
			rs = psmt.executeQuery();

			while (rs.next()) {
				lst.add(new MProduct(ctx, rs, trxName) );
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return lst;
	}

	/**
	 * se obtiene el listado de productos a afectar por el proceso de incremento de precios (factor precio)
	 * @param ctx
	 * @param versionID
	 * @param categoryID
	 * @param productID
	 * @param trxName
	 * @return
	 */
	public static List<MProduct> getProductosFP(Properties ctx, int versionID, int categoryID, int productID, String trxName){

		List<MProduct> lstProducts = new ArrayList<MProduct>();

		if(categoryID>0 && productID == 0){ // se obtienen todos los productos de la categoria

			lstProducts = MProduct.getProductsByCategory(ctx, categoryID, Env.getAD_Org_ID(Env.getCtx()), null);


		}else if(productID>0){ //se obtiene el producto seleccionado

			MProduct product = MProduct.get(ctx, productID);
			lstProducts.add(product);


		} else {

			if(versionID > 0) {//se obtienen los productos existentes en la version

				lstProducts = MProduct.getProductsByVersion(ctx, versionID, Env.getAD_Org_ID(Env.getCtx()), null);

			}else{ //se obtienen todos los productos activos de MProductOrg

				lstProducts = MProduct.getProductsByOrg(ctx, Env.getAD_Org_ID(Env.getCtx()), null);

			}

		}



		return lstProducts;
	}

	/**
	 * Precio en unidad de medida minima
	 * sin hubicacion de la solicitud del precio
	 * @return
	 */
	public MPrecios pricesUom(){
		MPrecios precios = GetPrice.getPriceVta(Env.getCtx()
				, getM_Product_ID()
				, 0
				, getC_UOM_ID()
				, DB.convert(Env.getCtx(), new Timestamp(System.currentTimeMillis()))
				, X_EXME_TipoPaciente.TIPOAREA_Ambulatory);
		return precios;
	}

	/**
	 * Precio en unidad de medida de empaque
	 * sin la hubicacion de la solicitud del precio
	 * @return
	 */
	public MPrecios pricesUomVol(){
		MPrecios precios = GetPrice.getPriceVta(Env.getCtx()
				, getM_Product_ID()
				, 0
				, getC_UOMVolume_ID()
				, DB.convert(Env.getCtx()
				, new Timestamp(System.currentTimeMillis()))
				, X_EXME_TipoPaciente.TIPOAREA_Ambulatory);
		return precios;
	}

	/**
	 * Tiene asiento contable por cliente
	 * @return
	 * @throws Exception
	 */
	public boolean existeAcct() throws Exception {

		log.info("Generating for products ...");

		Properties ctx = Env.getCtx();

		String sql =
			" SELECT *               " +
			" FROM M_Product_Acct    " +
			" WHERE AD_Client_ID = ? " +
			" AND IsActive = 'Y'     " +
		    " AND M_Product_ID  =  ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, getM_Product_ID());

			log.fine(
					"SQL : " +
					sql +
					" - AD_Client_ID = " +
					Env.getAD_Client_ID(ctx)
			);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				return true;
			}
		} catch(SQLException e) {
			log.log(Level.SEVERE, "Inserting accounting data for products", e);
			throw new Exception(e);
		} finally {
			DB.close(rs, pstmt);
		}
		return false;
	}

	/**
	 * valida si el value ya se utiliza en algun producto de system.
	 * @param ctx
	 * @param value
	 * @param trxName
	 * @return
	 */
	public static MProduct existsInSystem(Properties ctx, String value, String trxName){

		Query query = new Query(ctx, Table_Name, "Value = ? and AD_Org_ID = 0 and AD_Client_ID = 0 ", trxName).setParameters(value);
		MProduct result = (MProduct) query.first();

		return result;
	}

	public static String[] getProductColumns(){
		return new String[] { I_M_Product.COLUMNNAME_Name, I_M_Product.COLUMNNAME_Description };
	}
	
	/** 
	 * Is it a valid product for the organization?
	 * @param ctx Contex
	 * @param productId Key product
	 * @return true: Is valid product
	 */
	public static String isValidProductOrg(final Properties ctx, final int productId, final boolean isStocked){
		String error = null;
		if(productId>0){
			
			// Validar que este dentro del charge master solo si no es Estados Unidos
			if(MCountry.isUSA(ctx)){
				if(isStocked){
					final MProduct product = new MProduct(ctx, productId, null);
					if(!product.isStocked()){
						error = Utilerias.getLabel("msj.errorNoAlmacenable", product.getName());
					}
				}
				
			} else {
				final MProduct product = new MProduct(ctx, productId, null);
				if (product.getProdOrg() == null 
						|| product.getProdOrg().getAD_Org_ID() < 1 ) {
					error = Utilerias.getLabel("msj.ligarProducto");
					
				} else if(isStocked
						&& !product.isStocked()){
					error = Utilerias.getLabel("msj.errorNoAlmacenable", product.getName());
				} 
			}
		}
		return error;
	}
	
	/** Listado de clases de productos */
	public static List<String> getListProductClass(final String... prodClass){
		final List<String> str = new ArrayList<String>();
		for (int i = 0; i < prodClass.length; i++) {
			str.add(prodClass[i]);
		}
		return str;
	}
	
	/**
	 * Get Product Costing Level
	 * @param as accounting schema
	 * @return product costing level
	 */
	public String getCostingLevel(final MAcctSchema as)
	{
		final MProductCategoryAcct pca = MProductCategoryAcct.get(getCtx(), getM_Product_Category_ID(), as.get_ID(), get_TrxName());
		String costingLevel = pca==null?null:pca.getCostingLevel();
		if (costingLevel == null && as!=null)
		{
			costingLevel = as.getCostingLevel();
		}
		return costingLevel;
	}
	
	/**
	 * Get Product Costing Method
	 * @param C_AcctSchema_ID accounting schema ID
	 * @return product costing method
	 */
	public String getCostingMethod(final MAcctSchema as)
	{
		final MProductCategoryAcct pca = MProductCategoryAcct.get(getCtx(), getM_Product_Category_ID(), as.get_ID(), get_TrxName());
		String costingMethod = pca==null?null:pca.getCostingMethod();
		if (costingMethod == null && as!=null)
		{
			costingMethod = as.getCostingMethod();
		}
		return costingMethod;
	}

	
	/**
	 * Metodo que obtiene los JOINS que se se utilizan en el sql
	 *
	 * @return string con todos los JOINS necesarios
	 */
	public static List<StorageInfo> getStorageInfoWindow(Properties ctx, String value, String name, String upc, String sku, int warehouseId, int productCategoryId, int productId, String where, boolean group, String trxName) {
		final List<StorageInfo> list = new ArrayList<StorageInfo>();

		final List<Object> params = new ArrayList<Object>();

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		if (group) {
			sb.append("DISTINCT");
		}
		sb.append("  M_PRODUCT.M_PRODUCT_ID, ");
		sb.append("  M_PRODUCT.VALUE, ");
		sb.append("  M_PRODUCT.NAME as prod, ");
		sb.append("  WH.NAME as ware, ");
		sb.append("  LOC.VALUE as LOC, ");
		if (group) {
			sb.append("sum(ST.QTYONHAND) over (W) as onhand, ");
			sb.append("sum(ST.QTYRESERVED)over (W) as RES, ");
			sb.append("sum(ST.QTYORDERED) over (W)as REQ  ");
		} else {
			sb.append("  ATT.LOT, ");
			sb.append("  ST.QTYONHAND as onhand, ");
			sb.append("  ST.QTYRESERVED as RES, ");
			sb.append("  ST.QTYORDERED as REQ ");
		}
		sb.append("FROM  M_Product ");
		if(MCountry.isUSA(Env.getCtx())){
			sb.append(" INNER JOIN EXME_ProductoOrg org ON (M_PRODUCT.M_Product_ID=org.M_Product_ID AND org.AD_CLIENT_ID IN (0,?))\n ");
		} else {
			sb.append(" INNER JOIN EXME_ProductoOrg org ON (M_PRODUCT.M_Product_ID=org.M_Product_ID AND org.AD_CLIENT_ID=?)\n ");
		}
		params.add(Env.getAD_Client_ID(ctx));//Lama
		sb.append(" LEFT JOIN M_Storage ST ON (M_PRODUCT.M_Product_ID = ST.M_Product_ID And ST.ad_client_id=?)\n ");
		params.add(Env.getAD_Client_ID(ctx));//Lama
		sb.append(" LEFT       JOIN M_Locator         LOC ON (ST.M_Locator_ID = LOC.M_Locator_ID) \n");
		sb.append(" LEFT       JOIN M_Warehouse        WH ON (LOC.M_Warehouse_ID = WH.M_Warehouse_ID)\n");
		sb.append(" LEFT       JOIN M_AttributeSetInstance ATT ON (ST.M_AttributeSetInstance_ID = ATT.M_AttributeSetInstance_ID)  \n");
		sb.append("WHERE 1 = 1");
		if (warehouseId > 0) {
			sb.append(" AND  WH.M_WAREHOUSE_ID = ? ");
			params.add(warehouseId);
		}
		if (StringUtils.isNotBlank(value)) {
			sb.append("  AND Upper(M_PRODUCT.value) like ? ");
			params.add("%" + StringUtils.defaultString(value).toUpperCase() + "%");
		}
		if (StringUtils.isNotBlank(name)) {
			sb.append("  AND Upper(M_PRODUCT.name) like ? ");
			params.add("%" + StringUtils.defaultString(name).toUpperCase() + "%");
		}
		if (StringUtils.isNotBlank(upc)) {
			sb.append("  AND Upper(M_PRODUCT.upc) like ? ");
			params.add("%" + StringUtils.defaultString(upc).toUpperCase() + "%");
		}
		if (StringUtils.isNotBlank(sku)) {
			sb.append("  AND Upper(M_PRODUCT.sku) like ? ");
			params.add("%" + StringUtils.defaultString(sku).toUpperCase() + "%");
		}
		if (productCategoryId > 0) {
			sb.append("  AND M_PRODUCT.M_Product_Category_ID = ? ");
			params.add(productCategoryId);
		}
		if (productId > 0) {
			sb.append("  AND M_PRODUCT.M_Product_ID = ? ");
			params.add(productId);
		}
		if (StringUtils.isNotBlank(where)) {
			sb.append(" AND ").append(where);
		}
		if (group) {
			sb.append("window w as (partition by M_PRODUCT.m_product_id,LOC.VALUE) ");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sb.toString(), trxName);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				StorageInfo storageInfo = new StorageInfo();
				storageInfo.setValue(rs.getString("value"));
				storageInfo.setName(rs.getString("prod"));
				storageInfo.setWarehouse(rs.getString("ware"));
				storageInfo.setLocator(rs.getString("loc"));
				storageInfo.setProductId(rs.getInt("M_PRODUCT_ID"));

				if (!group) {
					storageInfo.setLot(rs.getString("lot"));
				}

				BigDecimal qtyOnHand = rs.getBigDecimal("onHand");
				BigDecimal qtyRes = rs.getBigDecimal("res");
				BigDecimal qtyOrdered = rs.getBigDecimal("req");
				
				storageInfo.setOnHand(qtyOnHand);
				storageInfo.setReserved(qtyRes);
				storageInfo.setOrdered(qtyOrdered);

				list.add(storageInfo);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	
	public static List<StorageInfo> getStorageInfo(Properties ctx, String value, String name, String upc, String sku, int warehouseId, int productCategoryId, int productId, String where, boolean group, String trxName) {
		List<StorageInfo> list = new ArrayList<StorageInfo>();

		List<Object> params = new ArrayList<Object>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		if (group) {
			sql.append("DISTINCT");
		}
		sql.append("  M_PRODUCT.M_PRODUCT_ID, ");
		sql.append("  M_PRODUCT.VALUE, ");
		sql.append("  M_PRODUCT.NAME as prod, ");
		sql.append("  WH.NAME as ware, ");
		sql.append("  LOC.VALUE as LOC, ");
		if (group) {
			sql.append("sum(ST.QTYONHAND) over (W) as onhand, ");
			sql.append("sum(ST.QTYRESERVED)over (W) as RES, ");
			sql.append("sum(ST.QTYORDERED) over (W)as REQ  ");
		} else {
			sql.append("  ATT.LOT, ");
			sql.append("  ST.QTYONHAND as onhand, ");
			sql.append("  ST.QTYRESERVED as RES, ");
			sql.append("  ST.QTYORDERED as REQ ");
		}
		sql.append("FROM ");
		sql.append("  M_STORAGE ST ");
		sql.append("  INNER JOIN M_LOCATOR LOC ");
		sql.append("  ON ST.M_LOCATOR_ID = LOC.M_LOCATOR_ID ");
		sql.append("  INNER JOIN M_WAREHOUSE WH ");
		sql.append("  ON LOC.M_WAREHOUSE_ID = WH.M_WAREHOUSE_ID ");
		sql.append("  INNER JOIN M_PRODUCT M_PRODUCT ");
		sql.append("  ON ST.M_PRODUCT_ID = M_PRODUCT.M_PRODUCT_ID ");
		sql.append("  INNER JOIN M_ATTRIBUTESETINSTANCE ATT ");
		sql.append("  ON ST.M_ATTRIBUTESETINSTANCE_ID = ATT.M_ATTRIBUTESETINSTANCE_ID ");
//		sql.append("WHERE 1 = 1");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " WHERE ", MStorage.Table_Name, "ST"));//LAMA
		if (warehouseId > 0) {
			sql.append(" AND  WH.M_WAREHOUSE_ID = ? ");
			params.add(warehouseId);
		}
		if (StringUtils.isNotBlank(value)) {
			sql.append("  AND Upper(M_PRODUCT.value) like ? ");
			params.add("%" + StringUtils.defaultString(value).toUpperCase() + "%");
		}
		if (StringUtils.isNotBlank(name)) {
			sql.append("  AND Upper(M_PRODUCT.name) like ? ");
			params.add("%" + StringUtils.defaultString(name).toUpperCase() + "%");
		}
		if (StringUtils.isNotBlank(upc)) {
			sql.append("  AND Upper(M_PRODUCT.upc) like ? ");
			params.add("%" + StringUtils.defaultString(upc).toUpperCase() + "%");
		}
		if (StringUtils.isNotBlank(sku)) {
			sql.append("  AND Upper(M_PRODUCT.sku) like ? ");
			params.add("%" + StringUtils.defaultString(sku).toUpperCase() + "%");
		}
		if (productCategoryId > 0) {
			sql.append("  AND M_PRODUCT.M_Product_Category_ID = ? ");
			params.add(productCategoryId);
		}
		if (productId > 0) {
			sql.append("  AND M_PRODUCT.M_Product_ID = ? ");
			params.add(productId);
		}
		if (StringUtils.isNotBlank(where)) {
			sql.append(" AND ").append(where);
		}
		if (group) {
			sql.append("window w as (partition by M_PRODUCT.m_product_id,LOC.VALUE) ");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();

//			long time = System.currentTimeMillis();
			
			while (rs.next()) {
				StorageInfo storageInfo = new StorageInfo();
				storageInfo.setValue(rs.getString("value"));
				storageInfo.setName(rs.getString("prod"));
				storageInfo.setWarehouse(rs.getString("ware"));
				storageInfo.setLocator(rs.getString("loc"));
				storageInfo.setProductId(rs.getInt("M_PRODUCT_ID"));

				if (!group) {
					storageInfo.setLot(rs.getString("lot"));
				}
				
				BigDecimal qtyOnHand = rs.getBigDecimal("onHand");
				BigDecimal qtyRes = rs.getBigDecimal("res");
				BigDecimal qtyOrdered = rs.getBigDecimal("req");
				
				storageInfo.setOnHand(qtyOnHand);
				storageInfo.setReserved(qtyRes);
				storageInfo.setOrdered(qtyOrdered);

				list.add(storageInfo);
			}
			
			// System.out.println((System.currentTimeMillis() - time) + " ms en " + list.size() + " registros");
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	private MEXMEGenProduct mexmeGenProduct;

	public MEXMEGenProduct getEXME_GenProduct() {
		if (getEXME_GenProduct_ID() > 0 && mexmeGenProduct == null) {
			mexmeGenProduct = new MEXMEGenProduct(getCtx(), getEXME_GenProduct_ID(), null);
		}

		return mexmeGenProduct;
	}
	
} // MProduct
