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
import java.util.Vector;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.OptionItem;

/**
 * Document Type Model
 * 
 * @author Jorg Janke
 * @version $Id: MDocType.java,v 1.2 2006/09/14 21:45:54 mrojas Exp $
 */
public class MDocType extends X_C_DocType {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 7038664766046564917L;

	/**
	 * Get Client Document Type with DocBaseType
	 * 
	 * @param ctx
	 *            context
	 * @param DocBaseType
	 *            base document type
	 * @return array of doc types
	 */
	static public MDocType[] getOfDocBaseType(final Properties ctx, final String DocBaseType) {
		ArrayList<MDocType> list = new ArrayList<MDocType>();
		StringBuilder sql = new StringBuilder() 
		.append("SELECT * FROM C_DocType WHERE AD_Client_ID=? AND DocBaseType=?")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",X_C_DocType.Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;// Expert
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setString(2, DocBaseType);
			rs = pstmt.executeQuery();
			while (rs.next()){
				list.add(new MDocType(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		MDocType[] retValue = new MDocType[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getOfDocBaseType

	/**
	 * Get Client Document Types
	 * 
	 * @param ctx
	 *            context
	 * @return array of doc types
	 */
	static public MDocType[] getOfClient(Properties ctx) {
		ArrayList<MDocType> list = new ArrayList<MDocType>();
		String sql = "SELECT * FROM C_DocType WHERE AD_Client_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null; // Expert
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MDocType(ctx, rs, null));
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;// Expert
		}

		MDocType[] retValue = new MDocType[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getOfClient

	/**
	 * Get Document Type (cached)
	 * 
	 * @param ctx
	 *            context
	 * @param C_DocType_ID
	 *            id
	 * @return document type
	 */
	static public MDocType get(Properties ctx, int C_DocType_ID) {
		MDocType retValue = (MDocType) s_cache.get(C_DocType_ID);
		if (retValue == null
		// Expert : Lama (verificar la sesion, para usar o no el objeto del
		// cache)
				|| (Env.getContextAsInt(ctx, "#AD_Session_ID") != Env
						.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) // lama
		{
			retValue = new MDocType(ctx, C_DocType_ID, null);
			s_cache.put(C_DocType_ID, retValue);
		}
		return retValue;
	} // get

	/** Cache */
	static private CCache<Integer, MDocType> s_cache = new CCache<Integer, MDocType>(
			"C_DocType", 20);
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MDocType.class);

	/**************************************************************************
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param C_DocType_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MDocType(Properties ctx, int C_DocType_ID, String trxName) {
		super(ctx, C_DocType_ID, trxName);
		if (C_DocType_ID == 0) {
			// setName (null);
			// setPrintName (null);
			// setDocBaseType (null);
			// setGL_Category_ID (0);
			setDocumentCopies(0);
			setHasCharges(false);
			setIsDefault(false);
			setIsDocNoControlled(false);
			setIsSOTrx(false);
			setIsPickQAConfirm(false);
			setIsShipConfirm(false);
			setIsSplitWhenDifference(false);
			//
			setIsCreateCounter(true);
			setIsDefaultCounterDoc(false);
			setIsIndexed(true);
		}
	} // MDocType

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
	public MDocType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MDocType

	/**
	 * New Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param DocBaseType
	 *            document base type
	 * @param Name
	 *            name
	 * @param trxName
	 *            transaction
	 */
	public MDocType(Properties ctx, String DocBaseType, String Name,
			String trxName) {
		this(ctx, 0, trxName);
		setAD_Org_ID(0);
		setDocBaseType(DocBaseType);
		setName(Name);
		setPrintName(Name);
		setGL_Category_ID();
	} // MDocType

	/**
	 * Set Default GL Category
	 */
	public void setGL_Category_ID() {
		String sql = "SELECT * FROM GL_Category WHERE AD_Client_ID=? AND IsDefault='Y'";
		int GL_Category_ID = DB.getSQLValue(get_TrxName(), sql,
				getAD_Client_ID());
		if (GL_Category_ID == 0) {
			sql = "SELECT * FROM GL_Category WHERE AD_Client_ID=?";
			GL_Category_ID = DB.getSQLValue(get_TrxName(), sql,
					getAD_Client_ID());
		}
		setGL_Category_ID(GL_Category_ID);
	} // setGL_Category_ID

	/**
	 * Set SOTrx based on document base type
	 */
	public void setIsSOTrx() {
		boolean isSOTrx = DOCBASETYPE_SalesOrder.equals(getDocBaseType())
				|| DOCBASETYPE_MaterialDelivery.equals(getDocBaseType())
				|| getDocBaseType().startsWith("AR");
		super.setIsSOTrx(isSOTrx);
	} // setIsSOTrx

//	/**
//	 * Invocado desde OrderAction pasado a la forma Listado de documentos
//	 * 
//	 * @return Vector tipo OptionItem
//	 */
//	public static Vector<OptionItem> getDocTypes(Properties ctx) {
//		StringBuilder sql = new StringBuilder(" SELECT C_DOCTYPE_ID, NAME ")
//				.append(" FROM C_DOCTYPE where isactive='Y' ").append(
//						MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
//								X_C_DocType.Table_Name)).append(
//						" ORDER BY NAME ASC ");
//
//		Vector<OptionItem> docs = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.clearParameters();
//			rs = pstmt.executeQuery();
//			docs = new Vector<OptionItem>();
//			while (rs.next())
//				docs.add(new OptionItem(rs.getString(1), rs.getString(2)));
//
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getDocTypes", e);
//		} finally {
//			DB.close(rs, pstmt);
//			pstmt = null;
//			rs = null;// Expert
//		}
//		return docs;
//	}
	
//	public static List<LabelValueBean> getTypes(Properties ctx){
//		Vector<OptionItem> docs = getDocTypes(ctx);
//		List<LabelValueBean> retVal = new ArrayList<LabelValueBean>();
//		for(int i =0; i < docs.size(); i++){
//			retVal.add(new LabelValueBean(docs.get(i).getLabel(), docs.get(i).getId()));
//		}
//		return retVal;
//	}

//	// TODO Documentar Metodo
//	public Vector<OptionItem> getDocTypeTargets() {
//		Vector<OptionItem> targetName = null;
//		StringBuilder sb = new StringBuilder(
//				"SELECT C_DocType_ID,NAME FROM C_DocType where C_DocType.DocBaseType IN ('SOO','POO') AND C_DocType.issotrx = 'N' and isactive='Y' ORDER BY NAME ASC ");
//		StringBuilder sql = new StringBuilder(MEXMELookupInfo
//				.addAccessLevelSQL(getCtx(), sb.toString(), this
//						.get_TableName()));
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			rs = pstmt.executeQuery();
//			targetName = new Vector<OptionItem>();
//
//			while (rs.next()) {
//				targetName
//						.add(new OptionItem(rs.getString(1), rs.getString(2)));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DB.close(rs, pstmt);
//			pstmt = null;
//			rs = null;// Expert
//		}
//		return targetName;
//	}

	/**
	 * String Representation
	 * 
	 * @return info
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("MDocType[");
		sb.append(get_ID()).append("-").append(getName()).append(
				",DocNoSequence_ID=").append(getDocNoSequence_ID()).append("]");
		return sb.toString();
	} // toString

	/**
	 * Is this a Quotation (Binding)
	 * 
	 * @return true if Quotation
	 */
	public boolean isQuotation() {
		return DOCSUBTYPESO_Quotation.equals(getDocSubTypeSO())
				&& DOCBASETYPE_SalesOrder.equals(getDocBaseType());
	} // isQuotation

	/**
	 * Is this a Proposal (Not binding)
	 * 
	 * @return true if proposal
	 */
	public boolean isProposal() {
		return DOCSUBTYPESO_Proposal.equals(getDocSubTypeSO())
				&& DOCBASETYPE_SalesOrder.equals(getDocBaseType());
	} // isProposal

	/**
	 * Is this a Proposal or Quotation
	 * 
	 * @return true if proposal or quotation
	 */
	public boolean isOffer() {
		return (DOCSUBTYPESO_Proposal.equals(getDocSubTypeSO()) || DOCSUBTYPESO_Quotation
				.equals(getDocSubTypeSO()))
				&& DOCBASETYPE_SalesOrder.equals(getDocBaseType());
	} // isOffer

	/**
	 * Get Print Name
	 * 
	 * @param AD_Language
	 *            language
	 * @return print Name if available translated
	 */
	public String getPrintName(String AD_Language) {
		if (AD_Language == null || AD_Language.length() == 0)
			return super.getPrintName();
		String retValue = get_Translation("PrintName", AD_Language);
		if (retValue != null)
			return retValue;
		return super.getPrintName();
	} // getPrintName

	/**
	 * Before Save
	 * 
	 * @param newRecord
	 *            new
	 * @return true
	 */
	protected boolean beforeSave(boolean newRecord) {
		// Expert .. Twry se quito por que queremos guardar un
		// registro por organizacion
		// if (getAD_Org_ID() != 0)

		// setAD_Org_ID(0);
		// Expert .. fin
		return true;
	} // beforeSave

	// TODO Documentar Metodo
//	/**
//	 * Metodo que retorna Lista de Documento Objetivo
//	 * 
//	 */
//	public Vector<OptionItem> getCDoctypeTargetList() {
//		Vector<OptionItem> docTypetargets = null;
//		String sql = "select c_doctype_id,name from c_doctype where C_DocType.DocBaseType IN ('ARI', 'API','ARC','APC') AND C_DocType.IsSOTrx=?";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql, null);
//			pstmt.setString(1, (isSOTrx() ? "Y" : "N"));
//			rs = pstmt.executeQuery();
//			docTypetargets = new Vector<OptionItem>();
//			while (rs.next())
//				docTypetargets.add(new OptionItem(rs.getString(1), rs
//						.getString(2)));
//
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getCDoctypeTargetList", e);
//		} finally {
//			DB.close(rs, pstmt);
//			pstmt = null;
//			rs = null;// Expert
//		}
//		return docTypetargets;
//	}

	/**
	 * Metodo para traer la lista de tipo de documento objetivo
	 * 
	 * @return Vector<OptionItem>
	 * @param String
	 *            documenBaseType
	 */
	public Vector<OptionItem> getDocTypeTarget(String documentBaseType) {
		Vector<OptionItem> docslist = null;
		StringBuilder sb = new StringBuilder(
				"SELECT C_DocType_ID,NAME FROM C_DocType"
						+ " WHERE C_DocType.DocBaseType IN " + documentBaseType
						+ " AND C_DocType.issotrx = 'N' and isactive='Y'");
		StringBuilder sql = new StringBuilder(MEXMELookupInfo
				.addAccessLevelSQL(getCtx(), sb.toString(), this
						.get_TableName()));
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			docslist = new Vector<OptionItem>();

			while (rs.next()) {
				docslist.add(new OptionItem(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;// Expert
		}
		return docslist;
	}

//	/**
//	 * Metodo para traer el id de un tipo de documento
//	 * 
//	 * @return String
//	 * @param String
//	 *            Name
//	 */
//	public String getIdByName(String Name) {
//		String id = "";
//
//		String sql = "SELECT C_DocType_ID FROM C_DocType"
//				+ " WHERE isactive='Y' AND name=? ";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql, null);
//			pstmt.setString(1, Name);
//			rs = pstmt.executeQuery();
//
//			while (rs.next())
//				id = rs.getString(1);
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getCDoctypeTargetList", e);
//		} finally {
//			DB.close(rs, pstmt);
//			pstmt = null;
//			rs = null;// Expert
//		}
//		return id;
//	}

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
		if (newRecord && success) {
			// Add doctype/docaction access to all roles of client
			String sqlDocAction = "INSERT INTO AD_Document_Action_Access "
					+ "(AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
					+ "C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) "
					+ "(SELECT "
					+ getAD_Client_ID()
					+ ",0,'Y', SysDate,"
					+ getUpdatedBy()
					+ ", SysDate,"
					+ getUpdatedBy()
					+ ", doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID "
					+ "FROM AD_Client client "
					+ "INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) "
					+ "INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) "
					+ "INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) "
					+ "WHERE client.AD_Client_ID=" + getAD_Client_ID()
					+ " AND doctype.C_DocType_ID=" + get_ID()
					+ " AND rol.IsManual='N'" + ")";

			int docact = DB.executeUpdate(sqlDocAction, get_TrxName());
			log.fine("AD_Document_Action_Access=" + docact);
		}
		return success;
	} // afterSave

	/**
	 * Executed after Delete operation.
	 * 
	 * @param success
	 *            true if record deleted
	 * @return true if delete is a success
	 */
	protected boolean afterDelete(boolean success) {
		if (success) {
			// delete access records
			int docactDel = DB.executeUpdate(
					"DELETE FROM AD_Document_Action_Access WHERE C_DocType_ID="
							+ get_IDOld(), get_TrxName());
			log.fine("Delete AD_Document_Action_Access=" + docactDel
					+ " for C_DocType_ID: " + get_IDOld());
		}
		return success;
	} // afterDelete
	
	/**
	 * 
	 * @param ctx
	 * @param DocBaseType
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getfromDocBaseType(Properties ctx, String DocBaseType, String trxName) {
		String sql = "SELECT C_DocType_ID, Name FROM C_DocType WHERE DocBaseType=? AND AD_Client_ID=? AND AD_Org_ID=?";
		return Query.getListKN(sql, trxName, DocBaseType, Env.getAD_Client_ID(ctx),  Env.getAD_Org_ID(ctx));
	}
	
	/**
	 * Obtiene el tipo de documento segun su docBaseType y docSubTypeSo
	 * 
	 * @param ctx
	 *            Contexto
	 * @param docBaseType
	 *            Tipo de Documento
	 * @param docSubTypeSo
	 *            Subtipo de Documento
	 * @param trxName
	 *            Trx
	 * @return Primer resultado encontrado
	 */
	public static MDocType getDocType(Properties ctx, String docBaseType, String docSubTypeSo, String trxName) {
		Query query = new Query(ctx, Table_Name, " DocBaseType= ? and DocSubTypeSO = ? ", trxName);
		query.setParameters(new Object[] { docBaseType, docSubTypeSo });
		query.addAccessLevelSQL(true);
		query.setOnlyActiveRecords(true);
		return query.first();
	}

	/**
	 * Obtenemos el tipo de documento segun el DocBaseType
	 * @param ctx
	 * @param docBaseType DocBaseType
	 * @param name Nombre (opcional), 
	 * @param isInTransit (opcional: null)
	 * @param trxName transaccion
	 * @return
	 */
	public static MDocType getTipoDoc(Properties ctx, String docBaseType,  String name, Boolean isInTransit, String trxName) {
		final StringBuilder sql = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		sql.append(" DocBaseType = ? ");
		params.add(docBaseType);
		if (StringUtils.isNotBlank(name)) {
			sql.append(" AND UPPER(Name) LIKE ? ");
			params.add(new StringBuilder("%").append(name.toUpperCase()).append("%").toString());
		}
		if (isInTransit != null) {
			sql.append(" AND isInTransit =? ");
			params.add(DB.TO_STRING(isInTransit));
		}

		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(params)//
			.setOrderBy(" AD_Org_ID DESC ")//
			.first();
	}

} // MDocType
