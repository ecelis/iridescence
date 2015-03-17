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
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MEXMEGenProduct;
import org.compiere.model.MEXMEProductoOrg;
import org.compiere.model.MEXMEProductoPrecio;
import org.compiere.model.MProduct;
import org.compiere.model.X_I_Product;
import org.compiere.model.X_M_Product;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Import Products from I_Product
 * 
 * @author Jorg Janke
 * @version $Id: ImportProduct.java,v 1.12 2006/10/25 16:50:20 vgarcia Exp $
 */
public class ImportProduct extends SvrProcess {
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(ImportProduct.class);
	/** Client to be imported to */
	private int m_AD_Client_ID = 0;
	/** Delete old Imported */
	private boolean m_deleteOldImported = false;

	/** Organization to be imported to */
	private int m_AD_Org_ID = 0;
	/** Effective */
	private Timestamp m_DateValue = null;
	/** Pricelist to Update */
	// private int p_M_PriceList_Version_ID = 0;
	/** Es Interfaz **/
	private boolean m_Interfase = false;

	private String tax_Category_Name = null;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	
	protected void prepare() {
		final ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal) para[i].getParameter())
						.intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			// else if (name.equals("M_PriceList_Version_ID"))
			// p_M_PriceList_Version_ID = para[i].getParameterAsInt();
			else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal) para[i].getParameter()).intValue();
			else if (name.equals("Interfase"))
				m_Interfase = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

		if (m_DateValue == null) {
			m_DateValue = DB.getTimestampForOrg(Env.getCtx());
		}
	} // prepare

	/**
	 * Perrform process.
	 * 
	 * @return Message
	 * @throws Exception
	 */
	
	protected String doIt() throws java.lang.Exception {
		if (m_Interfase) {
			log.info("Ejecutando Importaci#n de Productos desde Interfaz");
			Env.setContext(getCtx(), "#AD_Client_ID", m_AD_Client_ID);
			Env.setContext(getCtx(), "#AD_Org_ID", m_AD_Org_ID);
		}

		StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;
		String errorMsg = DB.isPostgreSQL() ? " COALESCE(I_ErrorMsg, '') "
				: " I_ErrorMsg ";

		// asignamos el valor
		tax_Category_Name = getTaxCategory_Name();

		// **** Prepare ****

		// Delete Old Imported
		if (m_deleteOldImported) {
			sql = new StringBuffer("DELETE I_Product "
					+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.info("Delete Old Impored =" + no);
		}

		// Set Client, Org, IaActive, Created/Updated, ProductType
		sql = new StringBuffer("UPDATE I_Product "
				+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(
				m_AD_Client_ID).append(
				")," + " AD_Org_ID = COALESCE (AD_Org_ID, 0),"
						+ " IsActive = COALESCE (IsActive, 'Y'),"
						+ " Created = COALESCE (Created, "
						+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))
						+ ")," + " CreatedBy = COALESCE (CreatedBy, 0),"
						+ " Updated = COALESCE (Updated, "
						+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))
						+ ")," + " UpdatedBy = COALESCE (UpdatedBy, 0),"
						+ " ProductType = COALESCE (ProductType, 'I'),"
						+ " I_ErrorMsg = NULL," + " I_IsImported = 'N' "
						+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Reset=" + no);

		// Set Optional BPartner
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ " SET   C_BPartner_ID=(SELECT p.C_BPartner_ID FROM C_BPartner p "
						+ "          WHERE i.BPartner_Value=p.Value AND i.AD_Client_ID=p.AD_Client_ID) "
						+ " WHERE i.C_BPartner_ID IS NULL"
						+ " AND   i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("BPartner=" + no);
		//
		/*
		 * sql = new StringBuffer ("UPDATE I_Product " +
		 * "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid BPartner,' "
		 * + "WHERE C_BPartner_ID IS NULL" +
		 * " AND I_IsImported<>'Y'").append(clientCheck); no =
		 * DB.executeUpdate(sql.toString(), get_TrxName()); if (no != 0)
		 * log.warning("Invalid BPartner=" + no);
		 */

		// **** Find Product
		// EAN/UPC
		sql = new StringBuffer("UPDATE I_Product i "
				+ " SET   M_Product_ID=(SELECT p.M_Product_ID FROM M_Product p"
				+ "         WHERE i.UPC=p.UPC AND i.AD_Client_ID=p.AD_Client_ID) "
				+ " WHERE i.M_Product_ID IS NULL AND i.I_IsImported='N'  ")
				.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Product Existing UPC=" + no);

		// Value
		sql = new StringBuffer("UPDATE I_Product i "
				+ " SET   M_Product_ID=(SELECT p.M_Product_ID FROM M_Product p "
				+ "          WHERE i.Value=p.Value AND i.AD_Client_ID=p.AD_Client_ID) "
				+ " WHERE i.M_Product_ID IS NULL AND i.I_IsImported='N'")
				.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Product Existing Value=" + no);

		// BP ProdNo
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ " SET   M_Product_ID=(SELECT p.M_Product_ID FROM M_Product_po p"
						+ "         WHERE i.C_BPartner_ID=p.C_BPartner_ID"
						+ "         AND i.VendorProductNo=p.VendorProductNo AND i.AD_Client_ID=p.AD_Client_ID) "
						+ " WHERE i.M_Product_ID IS NULL "
						+ " AND   i.I_IsImported='N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Product Existing Vendor ProductNo=" + no);

		// Set Tax Category (requerido)
		/*sql = new StringBuffer(
				"UPDATE I_Product "
						+ "SET C_TaxCategory_Name='"
						+ tax_Category_Name
						+ "' WHERE C_TaxCategory_Name IS NULL AND C_TaxCategory_ID IS NULL"
						+ " AND I_IsImported<>'Y' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		s_log.log(Level.SEVERE, sql.toString());*/
		// System.out.println(sql.toString());
		//log.fine("Set Tax Category Default=" + no);
		//
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ " SET C_TaxCategory_ID=(SELECT c.C_TaxCategory_ID FROM C_TaxCategory c "
						+ "           WHERE TRIM(i.C_TaxCategory_Name)=TRIM(c.Name) AND i.AD_Client_ID=c.AD_Client_ID) " // expert
						+ " WHERE i.C_TaxCategory_Name IS NOT NULL AND i.C_TaxCategory_ID IS NULL "
						+ " AND   i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Set Tax Category=" + no);

		// Set Product Category
		sql = new StringBuffer(
				"UPDATE I_Product "
						+ " SET ProductCategory_Value=(SELECT Value FROM M_Product_Category"
						+ " WHERE IsDefault='Y' AND AD_Client_ID=")
				.append(m_AD_Client_ID);

		if (DB.isOracle()) {
			sql.append(" AND ROWNUM=1) ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuffer(DB.getDatabase().addPagingSQL(
					sql.toString(), 1, 1));
			sql.append(") ");
		}

		sql.append(
				"WHERE ProductCategory_Value IS NULL AND M_Product_Category_ID IS NULL"
						+ " AND M_Product_ID IS NULL" // set category only if
						// product not found
						+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Category Default Value=" + no);
		//
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ "SET    M_Product_Category_ID=(SELECT c.M_Product_Category_ID FROM M_Product_Category c "
						+ "          WHERE TRIM(c.Value)=TRIM(i.ProductCategory_Value) AND i.AD_Client_ID=c.AD_Client_ID) " // expert
						+ "WHERE  i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Set Category=" + no);

		// Copy From Product if Import does not have value
		final String[] strFields = new String[] { "Value", "Name", "Description",
				"DocumentNote", "Help", "UPC", "SKU", "Classification",
				"ProductType", "Discontinued", "DiscontinuedBy", "ImageURL",
				"DescriptionURL" };
		for (int i = 0; i < strFields.length; i++) {
			sql = new StringBuffer("UPDATE I_PRODUCT i " + "SET ")
					.append(strFields[i])
					.append(" = (SELECT ")
					.append(strFields[i])
					.append(" FROM M_Product p"
							+ " WHERE i.M_Product_ID=p.M_Product_ID AND i.AD_Client_ID=p.AD_Client_ID) "
							+ "WHERE M_Product_ID IS NOT NULL" + " AND ")
					.append(strFields[i])
					.append(" IS NULL" + " AND I_IsImported='N'")
					.append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0){
				log.fine(strFields[i] + " - default from existing Product="
						+ no);
		}}
		final String[] numFields = new String[] { "C_UOM_ID",
				"M_Product_Category_ID", "Volume", "Weight", "ShelfWidth",
				"ShelfHeight", "ShelfDepth", "UnitsPerPallet" };
		for (int i = 0; i < numFields.length; i++) {
			sql = new StringBuffer("UPDATE I_PRODUCT i " + "SET ")
					.append(numFields[i])
					.append(" = (SELECT ")
					.append(numFields[i])
					.append(" FROM M_Product p"
							+ " WHERE i.M_Product_ID=p.M_Product_ID AND i.AD_Client_ID=p.AD_Client_ID) "
							+ "WHERE M_Product_ID IS NOT NULL" + " AND (")
					.append(numFields[i]).append(" IS NULL OR ")
					.append(numFields[i])
					.append("=0)" + " AND I_IsImported='N'")
					.append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0){
				log.fine(numFields[i] + " default from existing Product=" + no);
			}
		}
		// Copy From Product_PO if Import does not have value
		final String[] strFieldsPO = new String[] { "UPC", "PriceEffective",
				"VendorProductNo", "VendorCategory", "Manufacturer",
				"Discontinued", "DiscontinuedBy" };
		for (int i = 0; i < strFieldsPO.length; i++) {
			sql = new StringBuffer("UPDATE I_PRODUCT i " + "SET ")
					.append(strFieldsPO[i])
					.append(" = (SELECT ")
					.append(strFieldsPO[i])
					.append(" FROM M_Product_PO p"
							+ " WHERE i.M_Product_ID=p.M_Product_ID AND i.C_BPartner_ID=p.C_BPartner_ID AND i.AD_Client_ID=p.AD_Client_ID) "
							+ "WHERE M_Product_ID IS NOT NULL AND C_BPartner_ID IS NOT NULL"
							+ " AND ").append(strFieldsPO[i])
					.append(" IS NULL" + " AND I_IsImported='N'")
					.append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0){
				log.fine(strFieldsPO[i] + " default from existing Product PO="
						+ no);
		}}
		final String[] numFieldsPO = new String[] { "C_UOM_ID", "C_Currency_ID",
				"PriceList", "PricePO", "RoyaltyAmt", "Order_Min",
				"Order_Pack", "CostPerOrder", "DeliveryTime_Promised" };
		for (int i = 0; i < numFieldsPO.length; i++) {
			sql = new StringBuffer("UPDATE I_PRODUCT i " + "SET ")
					.append(numFieldsPO[i])
					.append(" = (SELECT ")
					.append(numFieldsPO[i])
					.append(" FROM M_Product_PO p"
							+ " WHERE i.M_Product_ID=p.M_Product_ID AND i.C_BPartner_ID=p.C_BPartner_ID AND i.AD_Client_ID=p.AD_Client_ID) "
							+ "WHERE M_Product_ID IS NOT NULL AND C_BPartner_ID IS NOT NULL"
							+ " AND (").append(numFieldsPO[i])
					.append(" IS NULL OR ").append(numFieldsPO[i])
					.append("=0)" + " AND I_IsImported='N'")
					.append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0){
				log.fine(numFieldsPO[i] + " default from existing Product PO="
						+ no);
			}
		}
		// Invalid Category
		sql = new StringBuffer(
				" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
				.append(errorMsg)
				.append(" || 'ERR=Invalid ProdCategorty,'     ")
				.append(" WHERE M_Product_Category_ID IS NULL ")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("Invalid Category=" + no);
		}
		// Set UOM (System/own)
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ "SET X12DE355 = "
						+ "(SELECT u.X12DE355 FROM C_UOM u WHERE u.IsDefault='Y' AND u.AD_Client_ID IN (0,i.AD_Client_ID) ");

		if (DB.isOracle()) {
			sql.append(" AND ROWNUM=1) ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuffer(DB.getDatabase().addPagingSQL(
					sql.toString(), 1, 1));
			sql.append(") ");
		}

		sql.append(
				"WHERE i.X12DE355 IS NULL AND i.C_UOM_ID IS NULL"
						+ " AND i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set UOM Default=" + no);
		//
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ "SET C_UOM_ID = (SELECT u.C_UOM_ID FROM C_UOM u WHERE TRIM(u.X12DE355)=TRIM(i.X12DE355) "
						+ "AND u.AD_Client_ID IN (0,i.AD_Client_ID) "); // expert

		if (DB.isOracle()) {
			sql.append(" AND ROWNUM=1) ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuffer(DB.getDatabase().addPagingSQL(
					sql.toString(), 1, 1));
			sql.append(") ");
		}

		sql.append("WHERE i.C_UOM_ID IS NULL AND i.I_IsImported<>'Y'").append(
				clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Set UOM=" + no);
		//
		sql = new StringBuffer(
				" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
				.append(errorMsg)
				.append(" || 'ERR=Invalid UOM, ' WHERE C_UOM_ID IS NULL "
						+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("Invalid UOM=" + no);
		}
		// Set Currency
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ "SET ISO_Code=(SELECT ISO_Code FROM C_Currency c"
						+ " INNER JOIN C_AcctSchema a ON (a.C_Currency_ID=c.C_Currency_ID)"
						+ " INNER JOIN AD_ClientInfo ci ON (a.C_AcctSchema_ID=ci.C_AcctSchema1_ID)"
						+ " WHERE ci.AD_Client_ID=i.AD_Client_ID) "
						+ "WHERE C_Currency_ID IS NULL AND ISO_Code IS NULL"
						+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Currency Default=" + no);
		//
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ "SET C_Currency_ID=(SELECT C_Currency_ID FROM C_Currency c"
						+ " WHERE i.ISO_Code=c.ISO_Code AND c.AD_Client_ID IN (0,i.AD_Client_ID)) "
						+ "WHERE C_Currency_ID IS NULL"
						+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("doIt- Set Currency=" + no);
		//
		sql = new StringBuffer(
				" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
				.append(errorMsg)
				.append(" || 'ERR=Currency,' " + "WHERE C_Currency_ID IS NULL"
						+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("Invalid Currency=" + no);
		}
		// Verify ProductType
		sql = new StringBuffer(
				" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
				.append(errorMsg)
				.append(" || 'ERR=Invalid ProductType,' "
						+ " WHERE ProductType NOT IN ('I','S','P') "
						+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("Invalid ProductType=" + no);
		}
		// Unique UPC/Value
		sql = new StringBuffer(
				" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
				.append(errorMsg)
				.append(" || 'ERR=Value not unique,' "
						+ "WHERE I_IsImported<>'Y'"
						+ " AND Value IN (SELECT ii.Value FROM I_Product ii WHERE I_Product.AD_Client_ID=ii.AD_Client_ID GROUP BY ii.Value HAVING COUNT(*) > 1)")
				.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("Not Unique Value=" + no);
		}		//
		sql = new StringBuffer(
				" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
				.append(errorMsg)
				.append(" || 'ERR=UPC not unique,' "
						+ "WHERE I_IsImported<>'Y'"
						+ " AND UPC IN (SELECT ii.UPC FROM I_Product ii WHERE I_Product.AD_Client_ID=ii.AD_Client_ID GROUP BY ii.UPC HAVING COUNT(*) > 1)")
				.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("Not Unique UPC=" + no);
		}
		// Mandatory Value
		sql = new StringBuffer(
				" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
				.append(errorMsg)
				.append(" || 'ERR=No Mandatory Value,' "
						+ "WHERE Value IS NULL" + " AND I_IsImported<>'Y'")
				.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("No Mandatory Value=" + no);
		}
		// Vendor Product No
		// sql = new StringBuffer ("UPDATE I_Product i "
		// +
		// "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Mandatory VendorProductNo,' "
		// + "WHERE I_IsImported<>'Y'"
		// +
		// " AND VendorProductNo IS NULL AND (C_BPartner_ID IS NOT NULL OR BPartner_Value IS NOT NULL)").append(clientCheck);
		// no = DB.executeUpdate(sql.toString(), get_TrxName());
		// log.info(log.l3_Util, "No Mandatory VendorProductNo=" + no);

		sql = new StringBuffer("UPDATE I_Product "
				+ "SET VendorProductNo=Value "
				+ "WHERE C_BPartner_ID IS NOT NULL AND VendorProductNo IS NULL"
				+ " AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("VendorProductNo Set to Value=" + no);
		//
		sql = new StringBuffer(
				" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
				.append(errorMsg)
				.append(" || 'ERR=VendorProductNo not unique,' "
						+ "WHERE I_IsImported<>'Y'"
						+ " AND C_BPartner_ID IS NOT NULL"
						+ " AND (C_BPartner_ID, VendorProductNo) IN "
						+ " (SELECT ii.C_BPartner_ID, ii.VendorProductNo FROM I_Product ii WHERE I_Product.AD_Client_ID=ii.AD_Client_ID GROUP BY ii.C_BPartner_ID, ii.VendorProductNo HAVING COUNT(*) > 1)")
				.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("Not Unique VendorProductNo=" + no);
		}
		// Get Default Tax Category
		//int C_TaxCategory_ID = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		/*try {
			pstmt = DB.prepareStatement(
					"SELECT C_TaxCategory_ID FROM C_TaxCategory WHERE IsDefault='Y'"
							+ clientCheck, get_TrxName());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				C_TaxCategory_ID = rs.getInt(1);
			}
			// rs.close();
			// pstmt.close();
			// rs=null;
			// pstmt=null;
		} catch (SQLException e) {
			// try{
			// if(rs != null)
			// rs.close();
			// if(pstmt != null)
			// pstmt.close();
			// }catch (SQLException ex){}
			// rs=null;
			// pstmt=null;
			throw new Exception("TaxCategory", e);
		} finally {
			DB.close(rs, pstmt);
		}

		log.fine("C_TaxCategory_ID=" + C_TaxCategory_ID);*/

		// expert: miguel rojas : para campos exme_factorpre y exme_tipoprod
		// establece el factor precio opcional
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ " SET   EXME_FactorPre_ID = (SELECT p.EXME_FactorPre_ID FROM EXME_FactorPre p "
						+ " WHERE i.EXME_FactorPre_Value = p.Value AND i.AD_Client_ID = p.AD_Client_ID)   "
						+ " WHERE i.EXME_FactorPre_ID IS NULL "
						+ " AND   i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("FactorPre=" + no);

		/*
		 * sql = new StringBuffer ("UPDATE I_Product " +
		 * "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid FactorPre,' "
		 * + "WHERE EXME_FactorPre_ID IS NULL" +
		 * " AND I_IsImported<>'Y'").append(clientCheck); no =
		 * DB.executeUpdate(sql.toString()); if (no != 0)
		 * log.warning("Invalid FactorPre=" + no);
		 */

		// establece el factor subtipo de producto opcional
		// Actualizado por Jesus Cantu colocando el OR del AD_Client a 0 si no
		// encuentra tipo de producto
		// a nivel cliente, que despues lo busque por system.
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ " SET EXME_TipoProd_ID=(SELECT p.EXME_TipoProd_ID FROM EXME_TipoProd p "
						+ " WHERE i.EXME_TipoProd_Value=p.Value AND (i.AD_Client_ID=p.AD_Client_ID OR p.AD_Client_ID = 0)) "
						+ " WHERE i.EXME_TipoProd_ID IS NULL"
						+ " AND i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("TipoProd=" + no);
		/*
		 * sql = new StringBuffer ("UPDATE I_Product " +
		 * "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid TipoProd,' "
		 * + "WHERE EXME_TipoProd_ID IS NULL" +
		 * " AND I_IsImported<>'Y'").append(clientCheck); no =
		 * DB.executeUpdate(sql.toString()); if (no != 0)
		 * log.warning("Invalid TipoProd=" + no);
		 */
		// expert: miguel rojas : para campos exme_factorpre y exme_tipoprod

		// expert : miguel rojas : unidad de medida del proveedor
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ "SET X12DE355Vendor = "
						+ "(SELECT u.X12DE355 FROM C_UOM u WHERE u.IsDefault='Y' AND u.AD_Client_ID IN (0,i.AD_Client_ID) ");

		if (DB.isOracle()) {
			sql.append(" AND ROWNUM=1) ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuffer(DB.getDatabase().addPagingSQL(
					sql.toString(), 1, 1));
			sql.append(") ");
		}

		sql.append(
				" WHERE i.X12DE355 IS NULL AND i.C_UOM_ID IS NULL"
						+ " AND i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set UOM Default=" + no);
		//
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ "SET   C_UOM_Vendor_ID = (SELECT u.C_UOM_ID FROM C_UOM u WHERE TRIM(u.X12DE355)=TRIM(i.X12DE355Vendor) AND u.AD_Client_ID IN (0,i.AD_Client_ID)) " // expert
						+ "WHERE i.C_UOM_Vendor_ID IS NULL"
						+ " AND  i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Set UOM Vendor=" + no);
		/*
		 * sql = new StringBuffer ("UPDATE I_Product " +
		 * "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Vendor UOM, ' "
		 * + "WHERE C_UOM_Vendor_ID IS NULL" +
		 * " AND I_IsImported<>'Y'").append(clientCheck); no =
		 * DB.executeUpdate(sql.toString()); if (no != 0)
		 * log.warning("Invalid Vendor UOM=" + no);
		 */
		// expert : miguel rojas : unidad de medida del proveedor

		// expert : gisela lee : establece la familia de productos (NULL)

		// Actualizado por Jesus Cantu colocando el OR del AD_Client a 0 si no
		// encuentra la familia del producto
		// a nivel cliente, que despues lo busque por system.
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ " SET   EXME_ProductFam_ID=(SELECT p.EXME_ProductFam_ID FROM EXME_ProductFam p "
						+ "       WHERE TRIM(i.EXME_ProductFam_Value)=TRIM(p.Value) AND (i.AD_Client_ID=p.AD_Client_ID OR p.AD_Client_ID = 0) and p.isactive='Y') "
						+ " WHERE i.EXME_ProductFam_ID IS NULL "
						+ " AND   i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Product Fam =" + no);
		/*
		 * sql = new StringBuffer ("UPDATE I_Product " +
		 * "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid ProductFam,' "
		 * + "WHERE EXME_ProductFam_ID IS NULL" +
		 * " AND I_IsImported<>'Y'").append(clientCheck); no =
		 * DB.executeUpdate(sql.toString()); if (no != 0)
		 * log.warning("Invalid EXME_ProductFam_ID=" + no);
		 */
		// expert: gisela lee : establece la familia de productos

		// expert : gisela lee : establece el concepto fac (NULL)
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ " SET   EXME_ConceptoFac_ID=(SELECT p.EXME_ConceptoFac_ID FROM EXME_ConceptoFac p "
						+ "         WHERE TRIM(i.EXME_ConceptoFac_Value)=TRIM(p.Value) AND i.AD_Client_ID=p.AD_Client_ID) "
						+ " WHERE i.EXME_ConceptoFac_ID IS NULL"
						+ " AND   i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Concepto Fac =" + no);

		// LRHU
		sql = new StringBuffer(
				"UPDATE I_Product i "
						+ " SET   C_UOMWeight_ID = (SELECT u.C_UOM_ID FROM C_UOM u WHERE TRIM(u.X12DE355)=TRIM(i.C_UOMWeight_Value) AND u.AD_Client_ID IN (0,i.AD_Client_ID)) " // expert
						+ "         WHERE C_UOMWeight_ID IS NULL"
						+ " AND   i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Set UOM Weight=" + no);

		// LRHU
		sql = new StringBuffer(
				"UPDATE I_Product  i "
						+ " SET    C_UOMVolume_ID = (SELECT u.C_UOM_ID FROM C_UOM u WHERE TRIM(u.X12DE355)=TRIM(i.C_UOMVolume_Value) AND u.AD_Client_ID IN (0,i.AD_Client_ID)) " // expert
						+ "          WHERE C_UOMVolume_ID IS NULL"
						+ " AND    i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Set UOM Volume=" + no);

		sql = new StringBuffer(
				" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
				.append(errorMsg)
				.append(" || 'ERR=Invalid C_UOMVolume_ID,' ")
				.append(" WHERE C_UOMVolume_ID IS NULL     ")
				.append(" AND   I_IsImported<>'Y'          ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.warning("Invalid C_UOMVolume_ID=" + no);

		if (DB.commit(false, get_TrxName())) {
			log.info("I_Product commit");
		} else {
			log.warning("I_Product rollback");
		}

		// -------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;
		// int noInsertPO = 0;
		// int noUpdatePO = 0;

		// Env.setAutoCommit(Env.getCtx(), true);

		// Go through Records
		log.fine("start inserting/updating ...");
		sql = new StringBuffer(" SELECT * FROM I_Product WHERE I_IsImported='N' ")
				.append(clientCheck);

		PreparedStatement pstmt_setImported = null;
		PreparedStatement pstmt_updateProduct = null;
		try {
			// Insert Product from Import
			/*
			 * PreparedStatement pstmt_insertProduct = DB.prepareStatement
			 * ("INSERT INTO M_Product (M_Product_ID," +
			 * "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
			 * + "Value,Name,Description,DocumentNote,Help," +
			 * "UPC,SKU,C_UOM_ID,IsSummary,M_Product_Category_ID,C_TaxCategory_ID,"
			 * + "ProductType,ImageURL,DescriptionURL) " + "SELECT ?," +
			 * "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
			 * + "Value,Name,Description,DocumentNote,Help," +
			 * "UPC,SKU,C_UOM_ID,'N',M_Product_Category_ID," + C_TaxCategory_ID
			 * + "," + "ProductType,ImageURL,DescriptionURL " +
			 * "FROM I_Product " + "WHERE I_Product_ID=?", get_TrxName());
			 */

			// Update Product from Import
			/*
			 * PreparedStatement pstmt_updateProduct = DB.prepareStatement
			 * ("UPDATE M_PRODUCT " +
			 * "SET (Value,Name,Description,DocumentNote,Help," +
			 * "UPC,SKU,C_UOM_ID,C_UOMWeight_ID,C_UOMVolume_ID,M_Product_Category_ID,Classification,ProductType,EXME_TipoProd_ID,"
			 * //LRHU. Se agregan C_UOMWeight_ID y C_UOMVolume_ID +
			 * "Volume,Weight,ShelfWidth,ShelfHeight,ShelfDepth,UnitsPerPallet,"
			 * +
			 * "Discontinued,DiscontinuedBy,Updated,UpdatedBy, STRENGTH, STRENGTHUNITS, PMID, EXME_ROUTE_ID, EXME_LABELER_ID, EXME_DOSEFORM_ID)= "
			 * +
			 * "(SELECT Value,(SELECT DESCDISPLAY FROM FDB_DISPENSABLE where MEDID = ? ) as Name, (SELECT DESCRIPTION1 FROM FDB_GENERIC_DISPENSABLE where GCNSEQNO = ? ) as Description,DocumentNote,Help,"
			 * +
			 * "UPC,SKU,C_UOM_ID,C_UOMWeight_ID,C_UOMVolume_ID,M_Product_Category_ID,Classification,ProductType,EXME_TipoProd_ID,"
			 * +
			 * "Volume,Weight,ShelfWidth,ShelfHeight,ShelfDepth,UnitsPerPallet,"
			 * + "Discontinued,DiscontinuedBy,"+
			 * DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) +
			 * ",UpdatedBy, STRENGTH, STRENGTHUNITS, PMID," +
			 * "(select EXME_ROUTE_ID from EXME_ROUTE where RTID = I_Product.RTID) as EXME_ROUTE_ID,"
			 * +
			 * "(select EXME_LABELER_ID from EXME_LABELER where LABELERID = I_Product.LABELERID) as EXME_LABELER_ID,"
			 * +
			 * "(select EXME_DOSEFORM_ID from EXME_DOSEFORM where DFID = I_Product.DFID) as EXME_DOSEFORM_ID"
			 * + " FROM I_Product WHERE I_Product_ID=? "+ clientCheck +" ) " +
			 * "WHERE M_Product_ID=?  "+clientCheck+" ", get_TrxName());
			 */

			// Update Product from Import
			final StringBuilder sqlUpdateProduct = new StringBuilder(
					Constantes.INIT_CAPACITY_ARRAY);
			sqlUpdateProduct
					.append("UPDATE M_PRODUCT SET ")
					.append(" Value = I_PRODUCT.VALUE, ")
					.append(" Name  = I_PRODUCT.Name,  ")
					.append(" Description  = I_PRODUCT.Description,  ")
					.append(" DocumentNote = I_PRODUCT.DocumentNote, ")
					.append(" Help = I_PRODUCT.Help, ")
					.append(" UPC  = I_PRODUCT.UPC,  ")
					.append(" SKU  = I_PRODUCT.SKU,  ")
					.append(" C_UOM_ID       = I_PRODUCT.C_UOM_ID,       ")
					.append(" C_UOMWeight_ID = I_PRODUCT.C_UOMWeight_ID, ")
					.append(" C_UOMVolume_ID = I_PRODUCT.C_UOMVolume_ID, ")
					.append(" M_Product_Category_ID = I_PRODUCT.M_Product_Category_ID, ")
					.append(" Classification   = I_PRODUCT.Classification,    ")
					.append(" ProductType      = I_PRODUCT.ProductType,       ")
					.append(" EXME_TipoProd_ID = I_PRODUCT.EXME_TipoProd_ID,  ")
					.append(" Volume = I_PRODUCT.Volume, ")
					.append(" Weight = I_PRODUCT.Weight, ")
					.append(" ShelfWidth  = I_PRODUCT.ShelfWidth,  ")
					.append(" ShelfHeight = I_PRODUCT.ShelfHeight, ")
					.append(" ShelfDepth  = I_PRODUCT.ShelfDepth,  ")
					.append(" UnitsPerPallet = I_PRODUCT.UnitsPerPallet, ")
					.append(" Discontinued   = I_PRODUCT.Discontinued,   ")
					.append(" DiscontinuedBy = I_PRODUCT.DiscontinuedBy, ")
					.append(" Updated = ")
					.append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())))
					.append(", UpdatedBy = I_PRODUCT.UPDATEDBY, ")
					.append(" STRENGTH = I_PRODUCT.STRENGTH, ")
					.append(" EXME_ROUTE_ID    = null,   ")
					.append(" EXME_LABELER_ID  = null,   ")
					.append(" EXME_DOSEFORM_ID = null    ")
					.append("FROM I_PRODUCT              ")
					.append("WHERE I_PRODUCT.I_PRODUCT_ID = ? ")
					.append(" AND  I_PRODUCT.AD_Client_ID = ")
					.append(m_AD_Client_ID)
					.append(" AND  M_PRODUCT.M_PRODUCT_ID = ? ")
					.append(" AND  M_PRODUCT.AD_Client_ID = ")
					.append(m_AD_Client_ID)
					.append(" AND  I_PRODUCT.I_IsImported='N'");// QUE NO TENGA
			// ERRORES
			pstmt_updateProduct = DB.prepareStatement(
					sqlUpdateProduct.toString(), get_TrxName());

			// NO DEBERA ACTUALIZAR LOS PRECIOS, SE DEBERA UTILIZAR LA
			// IMPORTACIÓN DE PRECIOS

			// // Update Product_PO from Import
			//
			// StringBuilder sqlUpdateProductPO = new
			// StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			// sqlUpdateProductPO.append("UPDATE M_Product_PO ")
			// .append("SET ISCURRENTVENDOR = 'Y',C_UOM_ID = I_PRODUCT.C_UOM_VENDOR_ID,C_CURRENCY_ID = I_PRODUCT.C_CURRENCY_ID,")
			// .append("UPC = I_PRODUCT.UPC,PRICELIST = I_PRODUCT.PRICELIST,PRICEPO = I_PRODUCT.PRICEPO,ROYALTYAMT = I_PRODUCT.ROYALTYAMT,")
			// .append("PRICEEFFECTIVE = I_PRODUCT.PRICEEFFECTIVE,PRICELASTPO = I_PRODUCT.PRICELASTPO,VENDORPRODUCTNO = I_PRODUCT.VENDORPRODUCTNO,")
			// .append("VENDORCATEGORY = I_PRODUCT.VENDORCATEGORY,MANUFACTURER = I_PRODUCT.MANUFACTURER,DISCONTINUED = I_PRODUCT.DISCONTINUED,")
			// .append("DISCONTINUEDBY = I_PRODUCT.DISCONTINUEDBY,ORDER_MIN = I_PRODUCT.ORDER_MIN,ORDER_PACK = I_PRODUCT.ORDER_PACK,")
			// .append("COSTPERORDER = I_PRODUCT.COSTPERORDER,DELIVERYTIME_PROMISED = I_PRODUCT.DELIVERYTIME_PROMISED,UPDATED = ")
			// .append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())))
			// .append(", UPDATEDBY = I_PRODUCT.UPDATEDBY ")
			// .append(" FROM I_PRODUCT ")
			// .append(" WHERE I_PRODUCT.I_PRODUCT_ID = ? ")
			// .append("AND I_PRODUCT.AD_Client_ID= ")
			// .append(m_AD_Client_ID)
			// .append(" AND  M_PRODUCT_PO.M_PRODUCT_ID = ? ")
			// .append(" AND M_PRODUCT_PO.C_BPARTNER_ID=? ")
			// .append(" AND M_PRODUCT_PO.AD_Client_ID= ")
			// .append(m_AD_Client_ID);
			// PreparedStatement pstmt_updateProductPO =
			// DB.prepareStatement(sqlUpdateProductPO.toString(),
			// get_TrxName());
			//
			// // Insert Product_PO from Import
			// PreparedStatement pstmt_insertProductPO = DB.prepareStatement
			// ("INSERT INTO M_Product_PO (M_Product_ID,C_BPartner_ID, "
			// +
			// "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
			// + "IsCurrentVendor,C_UOM_ID,C_Currency_ID,UPC,"
			// +
			// "PriceList,PricePO,RoyaltyAmt,PriceEffective,PriceLastPO,PriceLastINV,"
			// + "VendorProductNo,VendorCategory,Manufacturer,"
			// + "Discontinued,DiscontinuedBy,Order_Min,Order_Pack,"
			// + "CostPerOrder,DeliveryTime_Promised) "
			// + "SELECT ?,?, "
			// + "AD_Client_ID,AD_Org_ID,'Y',"+
			// DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",CreatedBy,"+
			// DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",UpdatedBy,"
			// + "'Y',C_UOM_Vendor_ID,C_Currency_ID,UPC," //expert : miguel
			// rojas : C_UOM_Vendor_ID
			// +
			// "PriceList,PricePO,RoyaltyAmt,PriceEffective,PriceLastPO,PriceLastPO,"
			// + "VendorProductNo,VendorCategory,Manufacturer,"
			// + "Discontinued,DiscontinuedBy,Order_Min,Order_Pack,"
			// + "CostPerOrder,DeliveryTime_Promised "
			// + "FROM I_Product "
			// + "WHERE I_Product_ID=? "+ clientCheck, get_TrxName());
			//
			// Update ProductPrice from Import
			// PreparedStatement pstmt_updateProductPrice = null;
			// if (DB.isOracle()) {
			// pstmt_updateProductPrice = DB.prepareStatement
			// ("UPDATE M_PRODUCTPRICE SET (M_PRICELIST_VERSION_ID,AD_CLIENT_ID,AD_ORG_ID,"
			// +
			// "ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY, PRICELIST, PRICESTD, PRICELIMIT )= "
			// +
			// "(SELECT ?,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY, " +
			// "UPDATED,UPDATEDBY,PRICELIST," +
			// //"case when PRICESTD is null then 0 else PRICESTD end," +
			// "PRICELIST, "+
			// "PRICELIST " +
			// "FROM I_PRODUCT " +
			// "WHERE I_PRODUCT_ID=? "+ clientCheck +") " +
			// "WHERE M_PRODUCT_ID=? "+ clientCheck, get_TrxName());
			// } else if (DB.isPostgreSQL()) {
			// pstmt_updateProductPrice = DB.prepareStatement
			// ("UPDATE M_PRODUCTPRICE SET M_PRICELIST_VERSION_ID = ?, AD_CLIENT_ID = I_PRODUCT.AD_CLIENT_ID, "
			// +
			// " AD_ORG_ID = I_PRODUCT.AD_ORG_ID, ISACTIVE = I_PRODUCT.ISACTIVE, CREATED = I_PRODUCT.CREATED, "
			// +
			// " CREATEDBY = I_PRODUCT.CREATEDBY, UPDATED = I_PRODUCT.UPDATED, UPDATEDBY = I_PRODUCT.UPDATEDBY, "
			// +
			// " PRICELIST = I_PRODUCT.PRICELIST, PRICESTD = I_PRODUCT.PRICELIST, "
			// +
			// " PRICELIMIT = I_PRODUCT.PRICELIST " +
			// " FROM I_PRODUCT " +
			// " WHERE I_PRODUCT.I_PRODUCT_ID=? AND I_PRODUCT.AD_Client_ID=" +
			// m_AD_Client_ID +
			// " AND M_PRODUCTPRICE.M_PRODUCT_ID=? AND M_PRODUCTPRICE.AD_Client_ID="
			// + m_AD_Client_ID, get_TrxName());
			// }
			//
			// // Insert ProducPrice from Import
			// PreparedStatement pstmt_insertProductPrice = DB.prepareStatement
			// ("INSERT INTO M_PRODUCTPRICE (M_PRICELIST_VERSION_ID,M_PRODUCT_ID,AD_CLIENT_ID,AD_ORG_ID,"
			// +
			// "ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY, PRICELIST, PRICESTD, PRICELIMIT) "
			// +
			// "SELECT ?,?,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY, "
			// +
			// "UPDATED,UPDATEDBY,PRICELIST," +
			// //"case when PRICESTD is null then 0 else PRICESTD end," +
			// //"case when PRICELIMIT is null then 0 else PRICELIMIT end " +
			// "PRICELIST, PRICELIST " +
			// "FROM I_PRODUCT WHERE I_Product_ID=? "+clientCheck,
			// get_TrxName());
			//
			// PreparedStatement pstmt_Version = DB.prepareStatement(
			// "SELECT MAX(M_PRICELIST_VERSION_ID) as version FROM M_PRICELIST_VERSION where AD_CLIENT_ID = ?",
			// null);
			//
			// Set Imported = Y
			pstmt_setImported = DB.prepareStatement(
					"UPDATE I_Product SET I_IsImported='Y', M_Product_ID=?, "
							+ "Updated="
							+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))
							+ ", Processed='Y' WHERE I_Product_ID=?",
					get_TrxName());

			//

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();

			// pstmt_Version.setInt(1, Env.getAD_Client_ID(Env.getCtx()));
			// ResultSet rsv = pstmt_Version.executeQuery();
			// int version = 0;

			// while(rsv.next())
			// version = rsv.getInt(1);

			while (rs.next()) {
				// Registro de importación
				final X_I_Product imp = new X_I_Product(getCtx(), rs, get_TrxName());
				// id registro de importacion
				final int I_Product_ID = imp.getI_Product_ID();
				// id registro de producto
				int M_Product_ID = imp.getM_Product_ID();
				// int MEDID = imp.getMEDID();
				// int GCNSEQNO = imp.getGCNSEQNO();
				// socio de negocios-proveedor
				final int C_BPartner_ID = imp.getC_BPartner_ID();
				// String labelerId = imp.getLabelerID();
				// int rtId = imp.getRtID();
				// int dfid = imp.getDfID();
				// product class, Jesus Cantu
				final String productClass = imp.getProductClass();
				// Precio de Venta
				final BigDecimal precioVenta = imp.getPrecioVenta();
				// sabes si el producto es nuevo o es una actualización
				final boolean newProduct = M_Product_ID == 0;// !(((MProduct)MEXMEProduct.getProducto(getCtx(),imp.getValue(),null)).getM_Product_ID()
				// > 0);//M_Product_ID
				// == 0;

				// Objeto de producto (insert o update)
				MProduct product = null;
				log.fine("I_Product_ID=" + I_Product_ID + ", M_Product_ID="
						+ M_Product_ID + ", C_BPartner_ID=" + C_BPartner_ID);

				// Sistema de Control de Medicamentos SICOME de la DGIT de la
				// SEDENA
				final boolean isSicome = (imp.getDocumentNote() != null ? true
						: false);
				boolean updateSicome = false;
				if (isSicome) {
					updateSicome = imp.getDocumentNote().equalsIgnoreCase(
							"UPDATE");
				}

				// Product
				if (newProduct && !(isSicome && updateSicome)) // Insert new
				// Product
				{

					product = new MProduct(imp, isSicome);

					// Categoria de impuestos definida como IsDefault
					/*if (product.getC_TaxCategory_ID() == 0) {
						product.setC_TaxCategory_ID(C_TaxCategory_ID);
					}*/
					// SICOME para SEDENA
					if (isSicome) {
						product.setDocumentNote("READ");
					}

					// S etear productclass para medicamentos Jesus Cantu 4
					// Enero 2012
					// Se tiene que adecuar el layout para esperar el
					// productclass, ahorita se
					// coloco en duro.
					product.setProductClass(productClass);

					// Crear el GenProduct antes de crear el Producto para pasar
					// su ID requerido en M_Product
					// Jesus Cantu el 5 de Enero, solo si el productclass es
					// medicamento (DG).
					if (productClass != null
							&& productClass
									.equalsIgnoreCase(X_M_Product.PRODUCTCLASS_Drug)) {
						final MEXMEGenProduct objGenProd = new MEXMEGenProduct(
								getCtx(), 0, get_TrxName());
						objGenProd.setGeneric_Product_Name(imp.getName());

						// Colocamos el value del Producto
						objGenProd.setValue(imp.getValue());
						// Setear los datos Dummy para cargas de ecsMX. Jesus
						// Cantu
						// acid citric
						objGenProd.setDrug_ID("10016501");
						// genproduct_id, se coloco el mismo consecutivo ID de
						// la tabla de Importacion
						objGenProd.setGenProduct_ID(imp.getI_Product_ID());
						// exme_route_id, se coloco oral and injectable
						objGenProd.setEXME_Route_ID(10001222);
						// exme_doseform_id se coloco Tablet
						objGenProd.setEXME_DoseForm_ID(10001528);
						// EXME_ProductStrength_ID Vitamin B Complex with C,
						// Folic Acid and Zinc
						objGenProd.setEXME_ProductStrength_ID(10024920);
						// CSA_Code se coloca un cero.
						objGenProd.setCSA_Code("0");
						//
						if (objGenProd.save()) {

							noInsert++;
							log.finer("Insert Gen Product");
							product.setEXME_GenProduct_ID(objGenProd
									.getEXME_GenProduct_ID());

							// Si pude salvar el genproduct ahora salvar el
							// temporal, lo hace un aftersave
							// Insertar los registros en la tabla temporal que
							// utilizan las búsquedas
							/*
							 * MEXMETGenProdTrade objTempGenProd = new
							 * MEXMETGenProdTrade(getCtx(), 0, get_TrxName());
							 * objTempGenProd.setEXME_GenProduct_ID(objGenProd.
							 * getEXME_GenProduct_ID());
							 * objTempGenProd.setGeneric_Product_Name
							 * (imp.getName());
							 * objTempGenProd.setIsPrefer(false);
							 * 
							 * if (objTempGenProd.save()) {
							 * 
							 * noInsert++; log.finer("Insert Gen Temp Product");
							 * } else { StringBuffer sql0 = new StringBuffer
							 * ("UPDATE I_Product i " +
							 * "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||"
							 * ).append(DB.TO_STRING(
							 * "Insert Product failed saving genprod temp record"
							 * ))
							 * .append("WHERE I_Product_ID=").append(I_Product_ID
							 * ); DB.executeUpdate(sql0.toString(),
							 * get_TrxName()); continue; }
							 */

						} else {
							final StringBuffer sql0 = new StringBuffer(
									" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
									.append(errorMsg)
									.append(" || ")
									.append(DB
											.TO_STRING("Insert Product failed saving genprod record"))
									.append("WHERE I_Product_ID=")
									.append(I_Product_ID);
							DB.executeUpdate(sql0.toString(), get_TrxName());
							continue;
						}

					} // If de Product Class de medicamento

					// Setear generico a false Jesus Cantu 5 Enero 2012 revisado
					// con GC
					product.setGenerico(false);
					//
					if (product.save()) {
						M_Product_ID = product.getM_Product_ID();
						log.finer("Insert Product");
						noInsert++;
					} else {
						final StringBuffer sql0 = new StringBuffer(
								" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
								.append(errorMsg).append(" || ")
								.append(DB.TO_STRING("Insert Product failed"))
								.append("WHERE I_Product_ID=")
								.append(I_Product_ID);
						DB.executeUpdate(sql0.toString(), get_TrxName());
						continue;
					}

					// Insertar el EXME_ProductoPrecio que contendra el precio
					// de venta del producto
					// Jesus Cantu
					if (precioVenta != null) {
						final MEXMEProductoPrecio objProdPrecio = new MEXMEProductoPrecio(
								getCtx(), 0, get_TrxName());
						objProdPrecio.setM_Product_ID(M_Product_ID);
						objProdPrecio.setPriceList(precioVenta);
						objProdPrecio.setPriceStd(precioVenta);
						objProdPrecio.setPriceLimit(precioVenta);
						objProdPrecio.setValidFrom(DB.getTimestampForOrg(Env
								.getCtx()));

						if (objProdPrecio.save()) {
							log.finer("Insert Product Price in EXMEProductoPrecio table");
							noInsert++;
						} else {
							final StringBuffer sql0 = new StringBuffer(
									" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
									.append(errorMsg)
									.append(" || ")
									.append(DB
											.TO_STRING("Insert Product Price in EXMEProductoPrecio failed"))
									.append("WHERE I_Product_ID=")
									.append(I_Product_ID);
							DB.executeUpdate(sql0.toString(), get_TrxName());
							continue;
						}
					}
				} else {

					// Modificaci#n para SEDENA
					if (updateSicome) {
						product = new MProduct(getCtx(), imp.getI_Product_ID(),
								get_TrxName());
						product.setValue(imp.getValue());
						product.setName(imp.getName());
						product.setDescription(imp.getDescription());
						product.setM_Product_ID(imp.getI_Product_ID());

						if (product.save()) {
							M_Product_ID = product.getM_Product_ID();
							log.finer("Update Product SEDENA");
							noInsert++;
						} else {
							final StringBuffer sql0 = new StringBuffer(
									" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
									.append(errorMsg)
									.append(" || ")
									.append(DB
											.TO_STRING("Update Product failed"))
									.append("WHERE I_Product_ID=")
									.append(I_Product_ID);
							DB.executeUpdate(sql0.toString(), get_TrxName());
							continue;
						}

					} else {
						// Comentado por Jesus Cantu No se utiliza, revisado con
						// JCarranza el 4 Enero 2012
						// Erick Alvarez #04433 — Importación de productos - no
						// actualiza los datos de productos
						// Update Product

						// if(MEXMELabeler.validateLabeler(getCtx(), labelerId)
						// && MEXMERoute.validateRoute(getCtx(), rtId) &&
						// MEXMEDoseForm.validateDoseForm(getCtx(), dfid)){

						// pstmt_updateProduct.setInt(1, MEDID);
						// pstmt_updateProduct.setInt(2, GCNSEQNO);
						pstmt_updateProduct.setInt(1, I_Product_ID);
						pstmt_updateProduct.setInt(2, M_Product_ID);// 10062231
						try {
							no = pstmt_updateProduct.executeUpdate();
							log.finer("Update Product = " + no);
							noUpdate++;
						} catch (SQLException ex) {
							log.warning("Update Product - " + ex.toString());
							final StringBuffer sql0 = new StringBuffer(
									" UPDATE I_Product SET I_IsImported='E', I_ErrorMsg = ")
									.append(errorMsg)
									.append(" || ")
									.append(DB.TO_STRING("Update Product: "
											+ ex.toString()))
									.append("WHERE I_Product_ID=")
									.append(I_Product_ID);
							DB.executeUpdate(sql0.toString(), get_TrxName());
							continue;
						}
						// }else{
						// log.warning("Update Product - MEXMELabeler.validateLabeler");
						// StringBuffer sql0 = new StringBuffer
						// ("MEXMELabeler.validateLabeler").append(I_Product_ID);
						// DB.executeUpdate(sql0.toString(), get_TrxName());
						// continue;
						// }
					}
				}
				
				//Actualizamos el cargo en Producto ORG solo con Productos Nuevos.
				//Si es Actualizacion no.
				if (newProduct) { 
					MEXMEProductoOrg prodOrg = product.getProductoOrgNew();
					prodOrg.setC_TaxCategory_ID(product.getC_TaxCategory_ID());
					prodOrg.save(get_TrxName());
				}
				
				// Do we have PO Info
				no = 0;
				// If Product existed, Try to Update first
				// if (!newProduct )
				// {
				// if (!isSicome){
				// if (C_BPartner_ID != 0)
				// {
				// pstmt_updateProductPO.setInt(1, I_Product_ID);
				// pstmt_updateProductPO.setInt(2, M_Product_ID);
				// pstmt_updateProductPO.setInt(3, C_BPartner_ID);
				// }

				// Para Actualizar el registro en M_ProductPrice
				// pstmt_updateProductPrice.setInt(1, version);
				// Colocar la lista que se selecciono
				// pstmt_updateProductPrice.setInt(1, p_M_PriceList_Version_ID);
				// pstmt_updateProductPrice.setInt(2, I_Product_ID);
				// pstmt_updateProductPrice.setInt(3, M_Product_ID);

				// try
				// {
				// if (C_BPartner_ID != 0)
				// {
				// no = pstmt_updateProductPO.executeUpdate();
				// log.finer("Update Product_PO = " + no);
				// }
				// no = pstmt_updateProductPrice.executeUpdate();
				// log.finer("Update ProductPrice = " + no);
				// noUpdatePO++;
				// }
				// catch (SQLException ex)
				// {
				// log.warning("Update Product_PO - " + ex.toString());
				// noUpdate--;
				// rollback();
				// StringBuffer sql0 = new StringBuffer ("UPDATE I_Product i "
				// +
				// "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update Product_PO: "
				// + ex.toString()))
				// .append("WHERE I_Product_ID=").append(I_Product_ID);
				// DB.executeUpdate(sql0.toString(), get_TrxName());
				// continue;
				// }
				// }
				// }else{
				// Nuevos Registros
				// if (C_BPartner_ID != 0)
				// {
				// pstmt_insertProductPO.setInt(1, M_Product_ID);
				// pstmt_insertProductPO.setInt(2, C_BPartner_ID);
				// pstmt_insertProductPO.setInt(3, I_Product_ID);
				// } // C_BPartner_ID != 0

				// Para Insertar el registro en M_ProductPrice
				// pstmt_insertProductPrice.setInt(1, version);
				// Se coloca la lista de precios que se selecciona en el combo y
				// no
				// la primera que encuentra. Jesús Cantú
				// pstmt_insertProductPrice.setInt(1, p_M_PriceList_Version_ID);
				// pstmt_insertProductPrice.setInt(2,
				// product.getM_Product_ID());
				// pstmt_insertProductPrice.setInt(3, I_Product_ID);

				// try
				// {
				// if (C_BPartner_ID != 0)
				// {
				// no = pstmt_insertProductPO.executeUpdate();
				// log.finer("Insert Product_PO = " + no);
				// }
				//
				// no = pstmt_insertProductPrice.executeUpdate();
				// log.finer("Update ProductPrice = " + no);
				// noInsertPO++;
				// }
				// catch (SQLException ex)
				// {
				// log.warning("Insert Product_PO - " + ex.toString());
				// noInsert--; // assume that product also did not exist
				// rollback();
				// StringBuffer sql0 = new StringBuffer ("UPDATE I_Product i "
				// +
				// "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Insert Product_PO: "
				// + ex.toString()))
				// .append("WHERE I_Product_ID=").append(I_Product_ID);
				// DB.executeUpdate(sql0.toString(), get_TrxName());
				// continue;
				// }

				// Comentado por Jesus Cantu el 4 de Enero, no se usa ya
				// Labeler.

				/*
				 * if(MEXMELabeler.validateLabeler(getCtx(), labelerId) &&
				 * MEXMERoute.validateRoute(getCtx(), rtId) &&
				 * MEXMEDoseForm.validateDoseForm(getCtx(), dfid)){
				 * pstmt_updateProduct.setInt(1, MEDID);
				 * pstmt_updateProduct.setInt(2, GCNSEQNO);
				 * pstmt_updateProduct.setInt(3, I_Product_ID);
				 * pstmt_updateProduct.setInt(4, M_Product_ID); try { no =
				 * pstmt_updateProduct.executeUpdate();
				 * log.finer("Update Product = " + no); noUpdate++; } catch
				 * (SQLException ex) { log.warning("Update Product - " +
				 * ex.toString()); StringBuffer sql0 = new StringBuffer
				 * ("UPDATE I_Product i " +
				 * "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||"
				 * ).append(DB.TO_STRING("Update Product: " + ex.toString()))
				 * .append("WHERE I_Product_ID=").append(I_Product_ID);
				 * DB.executeUpdate(sql0.toString(), get_TrxName()); continue; }
				 * }else{
				 * log.warning("Update Product - MEXMELabeler.validateLabeler");
				 * StringBuffer sql0 = new StringBuffer
				 * ("MEXMELabeler.validateLabeler").append(I_Product_ID);
				 * DB.executeUpdate(sql0.toString(), get_TrxName()); continue; }
				 */
				// }

				// Price List
				// if (p_M_PriceList_Version_ID != 0)
				// {
				// BigDecimal PriceList = imp.getPriceList();
				// BigDecimal PriceStd = imp.getPriceStd();
				// BigDecimal PriceLimit = imp.getPriceLimit();
				// if (PriceStd.signum() != 0 && PriceLimit.signum() != 0 &&
				// PriceList.signum() != 0)
				// {
				// MProductPrice pp = MProductPrice.get(getCtx(),
				// p_M_PriceList_Version_ID, M_Product_ID, get_TrxName());
				// if (pp == null)
				// pp = new MProductPrice (getCtx(),
				// p_M_PriceList_Version_ID, M_Product_ID, get_TrxName());
				// pp.setPrices(PriceList, PriceStd, PriceLimit);
				// pp.save();
				// }
				// }

				// Update I_Product
				pstmt_setImported.setInt(1, M_Product_ID);
				pstmt_setImported.setInt(2, I_Product_ID);
				no = pstmt_setImported.executeUpdate();
				//
				DB.commit(false, get_TrxName());
			} // FIN WHILW for all I_Product
				// rs.close();
				// pstmt.close();

			//
			// pstmt_insertProduct.close();
			// pstmt_updateProduct.close();
			// pstmt_insertProductPO.close();
			// pstmt_updateProductPO.close();
			// pstmt_insertProductPrice.close();
			// pstmt_updateProductPrice.close();
			// pstmt_Version.close();
			// pstmt_setImported.close();
			//

		} catch (SQLException e) {
			// e.printStackTrace();
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			DB.close(pstmt_setImported);
			DB.close(pstmt_updateProduct);
			// Env.setAutoCommit(getCtx(), Ini.isPropertyBool(Ini.P_A_COMMIT));
		}

		// Set Error to indicator to not imported
		sql = new StringBuffer("UPDATE I_Product "
				+ "SET I_IsImported='N', Updated="
				+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " "
				+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog(0, null, new BigDecimal(no), "@Errors@");
		addLog(0, null, new BigDecimal(noInsert), "@M_Product_ID@: @Inserted@");
		addLog(0, null, new BigDecimal(noUpdate), "@M_Product_ID@: @Updated@");
		// addLog (0, null, new BigDecimal (noInsertPO),
		// "@M_Product_ID@ @Purchase@: @Inserted@");
		// addLog (0, null, new BigDecimal (noUpdatePO),
		// "@M_Product_ID@ @Purchase@: @Updated@");
		return "";
	} // doIt

	/**
	 * Metodo para obtener el TaxCategory_Name
	 * 
	 * @return
	 */
	public String getTaxCategory_Name() {

		String name = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT Name FROM C_TaxCategory WHERE IsDefault='Y' AND AD_Client_ID=? ");

		if (DB.isOracle()) {
			sql.append(" AND ROWNUM=1 ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(
					sql.toString(), 1, 1));
		}

		ps = DB.prepareStatement(sql.toString(), get_TrxName());
		try {

			ps.setInt(1, m_AD_Client_ID);

			rs = ps.executeQuery();

			while (rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.log(Level.SEVERE, "getTaxCategory_Name - sql: " + sql, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"getTaxCategory_Name: while closing objects", e);
			}

		}

		return name;
	}

} // ImportProduct
