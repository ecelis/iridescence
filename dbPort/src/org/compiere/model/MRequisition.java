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

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DynamicModel;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.QuickSearchTables;
import org.compiere.util.Utilerias;

/**
 *	Requisition Model
 *
 *  @author Jorg Janke
 *  @version $Id: MRequisition.java,v 1.3 2006/09/05 18:24:13 mrojas Exp $
 */
public class MRequisition extends X_M_Requisition implements DocAction
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger (MRequisition.class);

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_Requisition_ID id
	 */
	public MRequisition (final Properties ctx, final int M_Requisition_ID, final String trxName)
	{
		super (ctx, M_Requisition_ID, trxName);
		if (M_Requisition_ID == 0)
		{
		//	setDocumentNo (null);
		//	setAD_User_ID (0);
		//	setM_PriceList_ID (0);
		//	setM_Warehouse_ID(0);
			setDateDoc(new Timestamp(System.currentTimeMillis()));
			setDateRequired (new Timestamp(System.currentTimeMillis()));
			setDocAction (DocAction.ACTION_Complete);	// CO
			setDocStatus (DocAction.STATUS_Drafted);		// DR
			setPriorityRule (PRIORITYRULE_Medium);	// 5
			setTotalLines (Env.ZERO);
			setIsApproved (false);
			setPosted (false);
			setProcessed (false);
		}
	}	//	MRequisition

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MRequisition (final Properties ctx, final ResultSet rs, final String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MRequisition

	/**
	 *
	 * @param ctx
	 * @param where
	 * @param params
	 * @param userId TODO
	 * @param docStatusLst TODO
	 * @param trxName
	 * @return
	 */
	public static List<MRequisition> getList(final Properties ctx, 
											 final StringBuilder where, 
											 final List<Object> params, 
											 final int userId, 
											 final List<String> docStatusLst, 
											 final String trxName) {
//		final List<MRequisition> list = new ArrayList<MRequisition>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		final StringBuilder join = new StringBuilder();
//		sql.append("SELECT DISTINCT ");
//		sql.append("  (M_Requisition.M_requisition_ID), ");
//		sql.append("  M_Requisition.* ");
//		sql.append("FROM ");
//		sql.append("  M_Requisition ");
//		join.append("  INNER JOIN AD_USER US ON M_Requisition.ad_user_id = us.ad_user_id ");
		join.append("  LEFT JOIN M_requisitionline reql ON M_Requisition.M_requisition_ID = reql.M_requisition_ID ");

//		if (StringUtils.isNotBlank(where)) {
//			sql.append(where);
//		}
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		if(userId > 0){
			where.append(" AND M_Requisition.createdby=? ");
			params.add(userId);
		}

		where.append(" AND M_Requisition.docStatus IN (");
		where.append(StringUtils.join(docStatusLst, ","));
		where.append(") ");

//		sql.append(" ORDER BY ");
//		sql.append("  M_Requisition.created Desc ");

//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			if (params != null && !params.isEmpty()) {
//				DB.setParameters(pstmt, params);
//			}
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				list.add(new MRequisition(ctx, rs, null));
//			}
//		} catch (final SQLException e) {
//			s_log.log(Level.SEVERE, "getMRequisitionSearch", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
		return new Query(ctx, MRequisition.Table_Name, where.toString(), trxName)//
		.setJoins(join)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setParameters(params)//
		.setOrderBy("M_Requisition.created Desc")
		.list();
	}

//	//TODO:Documentar Metodo.
//	public static List<OptionItem> getRequisitions(){
//		List<OptionItem> list = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		final String sql = "SELECT MR.M_REQUISITION_ID, MR.documentno,au.name FROM M_REQUISITION MR,AD_USER AU" +
//				" where au.ad_user_id = mr.ad_user_id and mr.docstatus='CO'" +
//				" ORDER BY mr.documentno DESC";
//		try{
//			pstmt = DB.prepareStatement(sql,null);
//			rs = pstmt.executeQuery();
//			list = new ArrayList<OptionItem>();
//			list.add(new OptionItem("0"," "));
//			while (rs.next()) {
//				list.add(new OptionItem(rs.getString(1),rs.getString(2)+"_"+rs.getString(3)));
//			}
//		}catch(final SQLException e){
//			s_log.log(Level.SEVERE, "getRequisitions", e);
//		}finally{
//			DB.close(rs, pstmt);
//		}
//		return list;
//	}


	/**
	 * Metodo que regresa una lista de M_Requisition_ID
	 * la cual se utiliza para el desplazamiento entre registros
	 *
	 * @return List<Integer> contiene M_Requisition_ID
	 * @deprecated no usado
	 * */
	public static List<Integer> getMRequisitionIDS(){
		List<Integer> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String sql = " select mr.m_requisition_id " +
 		 			 " from m_requisition mr " +
 		 			 " where mr.isactive='Y' and (mr.processed='N' OR mr.Updated > "+ DB.TO_DATE(new Timestamp(System.currentTimeMillis())) + "-1) order by mr.m_requisition_id desc ";
		try{
			pstmt = DB.prepareStatement(sql,null);
			rs = pstmt.executeQuery();
			list = new ArrayList<Integer>();
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		}catch(final SQLException e){
			s_log.log(Level.SEVERE, "getMRequisitionIDS", e);
		}finally{
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Metodo que regresa los items de MInvoice
	 * disponibles para mostrar
	 *
	 * @return List<DynamicModel> lista de objetos dinamicos
	 * @deprecated no usado
	 * */
	public static List<DynamicModel> getRequisitionAll (){
		List<DynamicModel> l = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		final String sql = " select mr.m_requisition_id,mr.documentno," +
					 " cd.name as c_doctype, mr.datedoc as datedoc, " +
					 " mr.daterequired as daterequired, mr.priorityrule  as priorityrule, " +
		      		 " mr.docstatus as docstatus, " +
		      		 " mw.name as m_warehouse, " +
		      		 " mr.istransfered as istransfered, " +
		      		 " mr.isapproved as isapproved, " +
		      		 " mr.totallines as totallines " +
		      		 " from m_requisition mr " +
		      		 " inner join m_warehouse mw on mr.m_warehouse_id = mw.m_warehouse_id " +
		      		 " inner join c_doctype cd on mr.c_doctype_id = cd.c_doctype_id " +
		      		 " where mr.isactive='Y' and (mr.processed='N' OR mr.Updated > "+ DB.TO_DATE(new Timestamp(System.currentTimeMillis())) + "-1) order by mr.m_requisition_id desc ";
		try{
			pstmt = DB.prepareStatement(sql,null);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			l = new ArrayList<DynamicModel>();

			while (rs.next()){
				final DynamicModel dynamicModel = new DynamicModel();
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					dynamicModel.setValue(rsmd.getColumnName(i), rs.getString(i), rsmd.getColumnClassName(i));
				}

				l.add(dynamicModel);

			}
		}catch(final SQLException e){
			s_log.log(Level.SEVERE, e.getMessage());
		}finally{
			DB.close(rs, pstmt);
		}
		return l;
	}

	/**
	 * Metodo que regresa los items de MRequisition
	 * disponibles para mostrar
	 *
	 * @return List<DynamicModel> lista de objetos dinamicos
	 * @deprecated no usado
	 * */
	public static List<DynamicModel> getRequisitionAllAut (final String AD_User_ID){
		List<DynamicModel> l = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		final String sql = " select mr.m_requisition_id,mr.documentno," +
					 " cd.name as c_doctype, mr.datedoc as datedoc, " +
					 " mr.daterequired as daterequired, mr.priorityrule  as priorityrule, " +
		      		 " mr.docstatus as docstatus, " +
		      		 " mw.name as m_warehouse, " +
		      		 " mr.istransfered as istransfered, " +
		      		 " mr.isapproved as isapproved, " +
		      		 " mr.totallines as totallines " +
		      		 " from m_requisition mr " +
		      		 " inner join m_warehouse mw on mr.m_warehouse_id = mw.m_warehouse_id " +
		      		 " inner join c_doctype cd on mr.c_doctype_id = cd.c_doctype_id " +
		      		 " INNER JOIN EXME_USERAUT EU ON EU.C_DOCTYPE_ID = mr.C_DOCTYPE_ID "+
		      		 " WHERE EU.AD_USER_ID = ? AND EU.AD_USERAUT_ID = mr.CREATEDBY "+
		      		 " AND mr.TOTALLINES BETWEEN EU.IMPORTEMIN AND EU.IMPORTEMAX "+
		      		 " AND mr.isactive='Y' and (mr.processed='N' OR mr.Updated > "+ DB.TO_DATE(new Timestamp(System.currentTimeMillis())) + "-1) order by mr.m_requisition_id desc ";
		try{
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setString(1, AD_User_ID);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			l = new ArrayList<DynamicModel>();

			while (rs.next()){
				final DynamicModel dynamicModel = new DynamicModel();
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					dynamicModel.setValue(rsmd.getColumnName(i), rs.getString(i), rsmd.getColumnClassName(i));
				}

				l.add(dynamicModel);

			}
		}catch(final SQLException e){
			s_log.log(Level.SEVERE, e.getMessage());
		}finally{
			DB.close(rs, pstmt);
		}
		return l;
	}

	/**
	 * Metodo que regresa los items de MInvoice
	 * en base a la busqueda que recibe como parametro
	 *
	 * @param pattern Patron a buscar
	 * @param field Campo en el cual realiza la busqueda
	 *
	 * @return List<DynamicModel> lista de objetos dinamicos
	 * @deprecated no usado
	 * */
	public static List<DynamicModel> getRequisitionSearch (final String pattern, final Integer field){
		List<DynamicModel> l = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		final StringBuilder sql = new StringBuilder(" SELECT mr.m_requisition_id,mr.documentno,")
			.append(" cd.name AS c_doctype, mr.datedoc AS datedoc, ")
			.append(" mr.daterequired AS daterequired, mr.priorityrule AS priorityrule, ")
			.append(" mr.docstatus AS docstatus, ")
			.append(" mw.name AS m_warehouse, ")
			.append(" mr.istransfered AS istransfered, ")
			.append(" mr.isapproved AS isapproved, ")
			.append(" mr.totallines AS totallines ")
			.append(" FROM m_requisition mr ")
			.append(" INNER JOIN m_warehouse mw ON mr.m_warehouse_id = mw.m_warehouse_id ")
			.append(" INNER JOIN c_doctype cd ON mr.c_doctype_id = cd.c_doctype_id ");

		if (field == 1){
			//document no
			sql.append( " WHERE (mr.isactive='Y') AND (mr.processed='N' OR mr.Updated > "+ DB.TO_DATE(new Timestamp(System.currentTimeMillis())) + "-1) AND (mr.documentno like ?) ORDER BY mr.m_requisition_id DESC ");
		}else if (field == 2){
			//doc date
			sql.append( " WHERE (mr.isactive='Y') AND (mr.processed='N' OR mr.Updated > "+ DB.TO_DATE(new Timestamp(System.currentTimeMillis())) + "-1) AND (mr.datedoc like ?) ORDER BY mr.m_requisition_id DESC ");
		}else if (field == 3){
			//date required
			sql.append( " WHERE (mr.isactive='Y') AND (mr.processed='N' OR mr.Updated > "+ DB.TO_DATE(new Timestamp(System.currentTimeMillis())) + "-1) AND (mr.daterequired like ?) ORDER BY mr.m_requisition_id DESC ");
		}else if (field == 4){
			//doc status
			sql.append( " WHERE (mr.isactive='Y') AND (mr.processed='N' OR mr.Updated > "+ DB.TO_DATE(new Timestamp(System.currentTimeMillis())) + "-1) AND (mr.docstatus like ?) ORDER BY mr.m_requisition_id DESC ");
		}else if (field == 5){
			//transfered
			sql.append( " WHERE (mr.isactive='Y') AND (mr.processed='N' OR mr.Updated > "+ DB.TO_DATE(new Timestamp(System.currentTimeMillis())) + "-1) AND (mr.istransfered like ?) ORDER BY mr.m_requisition_id DESC ");
		}else if (field == 6){
			//warehouse
			sql.append( " WHERE (mr.isactive='Y') AND (mr.processed='N' OR mr.Updated > "+ DB.TO_DATE(new Timestamp(System.currentTimeMillis())) + "-1) AND (mw.name like ?) ORDER BY mr.m_requisition_id DESC ");
		}



		try{
			pstmt = DB.prepareStatement(sql.toString(),null);
			pstmt.setString(1, pattern);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			l = new ArrayList<DynamicModel>();

			while (rs.next()){
				final DynamicModel dynamicModel = new DynamicModel();
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					dynamicModel.setValue(rsmd.getColumnName(i), rs.getString(i), rsmd.getColumnClassName(i));
				}

				l.add(dynamicModel);

			}
		}catch(final SQLException e){
			s_log.log(Level.SEVERE, e.getMessage());
		}finally{
			DB.close(rs, pstmt);
		}
		return l;
	}


	/** Lines						*/
	private MRequisitionLine[]		m_lines = null;

	/**
	 * 	Get Lines
	 *	@return array of lines
	 */
	public MRequisitionLine[] getLines(final boolean requery)
	{
		if (m_lines != null && !requery) {
			return m_lines;
		}

		final ArrayList<MRequisitionLine> list = new ArrayList<MRequisitionLine>();
		final String sql = "SELECT * FROM M_RequisitionLine WHERE M_Requisition_ID=? AND isActive='Y' ORDER BY C_BPartner_ID, Line";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getM_Requisition_ID());
			rs = pstmt.executeQuery ();
			while (rs.next ()) {
				list.add (new MRequisitionLine (getCtx(), rs, get_TrxName()));
			}
		}
		catch (final Exception e)
		{
			log.log(Level.SEVERE, "getLines", e);
		} finally {
			DB.close(rs, pstmt);
		}
		m_lines = new MRequisitionLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines

	/**
	 * 	String Representation
	 *	@return info
	 */
	@Override
	public String toString ()
	{
		final StringBuffer sb = new StringBuffer ("MRequisition[")
		.append(get_ID()).append("-").append(getDocumentNo())
			.append(",Status=").append(getDocStatus()).append(",Action=").append(getDocAction())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Document Info
	 *	@return document info
	 */
	@Override
	public String getDocumentInfo()
	{
		return Msg.getElement(getCtx(), "M_Requisition_ID") + " " + getDocumentNo();
	}	//	getDocumentInfo

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	@Override
	public File createPDF ()
	{
		try
		{
			final File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
			return createPDF (temp);
		}
		catch (final Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	}	//	getPDF

	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (final File file)
	{
	//	ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
	//	if (re == null)
			return null;
	//	return re.getPDF(file);
	}	//	createPDF

	/**
	 * 	Set default PriceList
	 */
	public void setM_PriceList_ID()
	{
		MPriceList defaultPL = MPriceList.getDefault(getCtx(), false);
		if (defaultPL == null) {
			defaultPL = MPriceList.getDefault(getCtx(), true);
		}
		if (defaultPL != null) {
			setM_PriceList_ID(defaultPL.getM_PriceList_ID());
		}
	}	//	setM_PriceList_ID()

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	@Override
	protected boolean beforeSave (final boolean newRecord)
	{
		//if (getM_PriceList_ID() == 0)
		//	setM_PriceList_ID();

		if (getIsTransfered() == null) {
			setIsTransfered(ISTRANSFERED_PendingTransfer);
		}


		return true;
	}	//	beforeSave


	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	@Override
	public boolean processIt (final String processAction)
	{
		m_processMsg = null;
		final DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	process

	/**	Process Message 			*/
	private String			m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean 		m_justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success
	 */
	@Override
	public boolean unlockIt()
	{
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}	//	unlockIt

	/**
	 * 	Invalidate Document
	 * 	@return true if success
	 */
	@Override
	public boolean invalidateIt()
	{
		log.info("invalidateIt - " + toString());
		return true;
	}	//	invalidateIt

	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid)
	 */
	@Override
	public String prepareIt()
	{
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null) {
			return DocAction.STATUS_Invalid;
		}
		final MRequisitionLine[] lines = getLines(false);

		if(!MEXMERequisitionLineDet.getLinesFromRequisition(getM_Requisition_ID()).isEmpty()){
			m_processMsg = Utilerias.getLabel("msj.productos.personalizados");
			return DocAction.STATUS_InProgress;
		}
		
		//	Invalid
		if (getAD_User_ID() == 0
			//|| getM_PriceList_ID() == 0
			|| getM_Warehouse_ID() == 0
			|| lines.length == 0) {
			return DocAction.STATUS_Invalid;
		}
		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateDoc(), X_C_DocType.DOCBASETYPE_PurchaseRequisition, 0))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}

		//	Add up Amounts
		 final MCurrency c = MCurrency.get(getCtx(), Env.getC_Currency_ID(getCtx()));
		final int precision = Integer.valueOf(c.getStdPrecision());
		//int precision = MPriceList.getStandardPrecision(getCtx(), getM_PriceList_ID());
		BigDecimal totalLines = Env.ZERO;
		for (final MRequisitionLine line : lines) {
			BigDecimal lineNet = line.getQty().multiply(line.getPriceActual());
			lineNet = lineNet.setScale(precision, BigDecimal.ROUND_HALF_UP);
			if (lineNet.compareTo(line.getLineNetAmt()) != 0)
			{
				line.setLineNetAmt(lineNet);
				line.save();
			}
			totalLines = totalLines.add (line.getLineNetAmt());
		}
		if (totalLines.compareTo(getTotalLines()) != 0)
		{
			setTotalLines(totalLines);
			save();
		}
		m_justPrepared = true;
		return DocAction.STATUS_InProgress;
	}	//	prepareIt

	/**
	 * 	Approve Document
	 * 	@return true if success
	 */
	@Override
	public boolean approveIt() {
		log.info("approveIt - " + toString());
//		setIsApproved(true);
		// Lama: aprobacion
		boolean approved = false;
		if (MEXMERequisitionLineDet.getLinesFromRequisition(getM_Requisition_ID()).isEmpty()) {
			if (MRequisitionLine.getLinesWithOutProvider(getM_Requisition_ID()) > 0) {
				m_processMsg = Utilerias.getLabel("msj.requisicion.proveedor");
			} else {
				approved = true;
			}
		} else { // tiene dummies
			m_processMsg = Utilerias.getLabel("msj.productos.personalizados");
		}
		setIsApproved(approved);
		return approved;
	} //	approveIt

	/**
	 * 	Reject Approval
	 * 	@return true if success
	 */
	@Override
	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt

	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	@Override
	public String completeIt()
	{

		// Check isTransfered
		// PENDIENTE
		//if(!getIsTransfered().equals("Y"))
		//	return MRequisition.STATUS_Invalid;

		//	Re-Check
		if (!m_justPrepared)
		{
			final String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status)) {
				return status;
			}
		}
		//	Implicit Approval
		if (!isApproved()) {
			if(!approveIt()) {
				return DOCSTATUS_InProgress;
			}
		}
		log.info(toString());
		// Se comenta porque se ejecuta en el approveIt .- Lama
		//if(!MEXMERequisitionLineDet.getLinesFromRequisition(getM_Requisition_ID()).isEmpty()){
		//	m_processMsg = Utilerias.getLabel("msj.productos.personalizados");
		//	return DocAction.STATUS_InProgress;
		//}
		//if(MRequisitionLine.getLinesWithOutProvider(getM_Requisition_ID())>0){
		//	//Faltan los proveedores para completar
		//	m_processMsg = "No se ha seleccionado el proveedor para todos los productos";
		//	return DocAction.STATUS_InProgress;
		//}

		//expert : Gisela Lee : validar control presupuestal
		//recuperamos el esquema contable para saber si maneja ctrl pptal
		final MAcctSchema as = MClient.get(getCtx(), getAD_Client_ID()).getAcctSchema();
		if(as.getCommitmentType().equalsIgnoreCase(X_C_AcctSchema.COMMITMENTTYPE_CommitmentReservation) ||
			as.getCommitmentType().equalsIgnoreCase(X_C_AcctSchema.COMMITMENTTYPE_CommitmentOnly)) {
		    //Obtenemos el periodo de la requisici�n
		    final MPeriod period = MPeriod.get(getCtx(), getDateDoc(), 0);

		    //acumulado de presupuesto
		    BigDecimal wppto = Env.ZERO;
		    //acumulado por comprometer
		    BigDecimal wxcomp = Env.ZERO;
		    //acumulado comprometido
		    BigDecimal wcomp = Env.ZERO;
		    //acumulado ejercido
		    BigDecimal wejerc = Env.ZERO;

		    //para cada linea de la requisicion
		    final MRequisitionLine[] lines = getLines(false);
		    for (final MRequisitionLine line : lines) {
			final MProduct prod = new MProduct(getCtx(), line.getM_Product_ID(), get_TrxName());
			final ProductCost cost = new ProductCost(getCtx(), prod.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), get_TrxName());

			//buscamos la cuenta contable del producto (P_Expense_Acct)
			final MAccount acct = cost.getAccount(ProductCost.ACCTTYPE_P_Expense, as);

			//traemos el acumulado del ejercicio para el periodo actual (postingType = B)
			wppto = FactAcctAcum.getFactAcctEjer(Env.getAD_Client_ID(getCtx()), line.getAD_Org_ID(),
			       as.getC_AcctSchema_ID(), acct.getAccount_ID(), X_Fact_Acct.POSTINGTYPE_Budget,
			       period, 0, get_TrxName());
			log.info("Presupuesto: " + wppto);

			//validar solo si el presupuesto es diferente de cero
			if(wppto.compareTo(Env.ZERO)==0) {
			    continue;
			}

			//traemos el acumulado por comprometer (postingType = R)
			wxcomp = FactAcctAcum.getFactAcctEjer(Env.getAD_Client_ID(getCtx()), line.getAD_Org_ID(),
				as.getC_AcctSchema_ID(), acct.getAccount_ID(), X_Fact_Acct.POSTINGTYPE_Reservation,
				period, 0, get_TrxName());
			log.info("Por comprometer: " + wxcomp);

			//traemos el acumulado comprometido (postingType = E)
			wcomp = FactAcctAcum.getFactAcctEjer(Env.getAD_Client_ID(getCtx()), line.getAD_Org_ID(),
			       as.getC_AcctSchema_ID(), acct.getAccount_ID(), X_Fact_Acct.POSTINGTYPE_Commitment,
			       period, 0, get_TrxName());
			log.info("Comprometido: " + wcomp);

			//traemos el acumulado ejercido (postingType = A)
			wejerc = FactAcctAcum.getFactAcctEjer(Env.getAD_Client_ID(getCtx()), line.getAD_Org_ID(),
				as.getC_AcctSchema_ID(), acct.getAccount_ID(), X_Fact_Acct.POSTINGTYPE_Actual,
				period, 0, get_TrxName());
			log.info("Ejercido: " + wejerc);
			log.info("Linea: " + line.getLineNetAmt());

			//excede el presupuesto para el producto ??
			if(wppto.compareTo(wejerc.add(wcomp).add(wxcomp).add(line.getLineNetAmt()))<0) {
				final BigDecimal excedido = wppto.subtract(wejerc.subtract(wcomp).subtract(wxcomp).subtract(line.getLineNetAmt())).negate();
			    m_processMsg = "Excede el presupuesto para el producto " + prod.getName() +
				" por " + excedido.setScale(2, BigDecimal.ROUND_HALF_UP) + ". Cuenta " + acct.getCombination();
			    return DocAction.STATUS_Invalid;
			}

		    }//for lines

		} //esquema contable maneja ctrl pptal
		//expert : Gisela Lee : validar control presupuestal

		//	User Validation
		final String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//
		setProcessed(true);
		setDocAction(ACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt

	/**
	 * 	Void Document.
	 * 	Same as Close.
	 * 	@return true if success
	 */
	@Override
	public boolean voidIt()
	{
		log.info("voidIt - " + toString());
		return closeIt();
	}	//	voidIt

	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success
	 */
	@Override
	public boolean closeIt()
	{
		log.info("closeIt - " + toString());
		//	Close Not delivered Qty
		final MRequisitionLine[] lines = getLines(false);
		BigDecimal totalLines = Env.ZERO;
		for (final MRequisitionLine line : lines) {
			BigDecimal finalQty = line.getQty();
			if (line.getC_OrderLine_ID() == 0) {
				finalQty = Env.ZERO;
			} else
			{
				final MOrderLine ol = new MOrderLine (getCtx(), line.getC_OrderLine_ID(), get_TrxName());
				finalQty = ol.getQtyOrdered();
			}
			//	final qty is not line qty
			if (finalQty.compareTo(line.getQty()) != 0)
			{
				String description = line.getDescription();
				if (description == null) {
					description = "";
				}
				description += " [" + line.getQty() + "]";
				line.setDescription(description);
				line.setQty(finalQty);
				line.setLineNetAmt();
				line.save();
			}
			totalLines = totalLines.add (line.getLineNetAmt());
		}
		if (totalLines.compareTo(getTotalLines()) != 0)
		{
			setTotalLines(totalLines);
			save();
		}
		m_processMsg = Utilerias.getLabel("egresos.reg.docstatus").concat(" ").concat(Utilerias.getLabel(getDocAction()));
		return true;
	}	//	closeIt

	/**
	 * 	Reverse Correction
	 * 	@return true if success
	 */
	@Override
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		return false;
	}	//	reverseCorrectionIt

	/**
	 * 	Reverse Accrual - none
	 * 	@return true if success
	 */
	@Override
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		return false;
	}	//	reverseAccrualIt

	/**
	 * 	Re-activate
	 * 	@return true if success
	 */
	@Override
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
	//	setProcessed(false);
		if (reverseCorrectIt()) {
			return true;
		}
		return false;
	}	//	reActivateIt

	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	@Override
	public String getSummary()
	{
		final StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
		//	 - User
		sb.append(" - ").append(getAD_User().getName());
		//	: Total Lines = 123.00 (#1)
		sb.append(": ").
			append(Msg.translate(getCtx(),"TotalLines")).append("=").append(getTotalLines())
			.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0) {
			sb.append(" - ").append(getDescription());
		}
		return sb.toString();
	}	//	getSummary

	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	@Override
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg

	/**
	 * 	Get Document Owner
	 *	@return AD_User_ID
	 */
	@Override
	public int getDoc_User_ID()
	{
		return getAD_User_ID();
	}

	/**
	 * 	Get Document Currency
	 *	@return C_Currency_ID
	 */
	@Override
	public int getC_Currency_ID()
	{
		//MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID(), get_TrxName());
		//return pl.getC_Currency_ID();
		return Env.getC_Currency_ID(getCtx());
	}

	/**
	 * 	Get Document Approval Amount
	 *	@return amount
	 */
	@Override
	public BigDecimal getApprovalAmt()
	{
		return getTotalLines();
	}

	private String buyerName = "";

	public String getBuyerName(){
		final MEXMEBuyer buyer = new MEXMEBuyer(getCtx(), getEXME_Buyer_ID(), null);

		if(buyer.getEXME_Buyer_ID() > 0){
			buyerName =	MEXMEBuyer.getUserFromExmeBuyer(buyer.getEXME_Buyer_ID(), buyer.getAD_User_ID());
		}
		return buyerName;
	}


	@Override
	protected boolean afterSave(final boolean newRecord, final boolean success) {

		if (!success){
			return success;
		}

		try {
			QuickSearchTables.checkTables(MRequisition.class, I_M_Requisition.Table_Name, getM_Requisition_ID(), get_TrxName() , p_ctx);

		} catch (final Exception ex) {
			log.log(Level.WARNING, "QuickSearchTables.checkTables", ex);
		}
		return true;
	}


	/**
	 * Metodo para Obrtener las Reqisicines
	 * que no esten relacionadas a una OC
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getReadyPO(final Properties ctx, final String trxName){
		final List<KeyNamePair> retList = new ArrayList<KeyNamePair>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final StringBuilder sql = new StringBuilder();

		sql.append("SELECT coalesce(documentno,'') || '-' || coalesce(description,''), M_Requisition_ID FROM M_Requisition req")
		.append(" WHERE req.M_Requisition_ID IN (SELECT M_REQUISITIONLINE.M_REQUISITION_ID FROM M_RequisitionLine ")
		.append("Where M_REQUISITIONLINE.C_ORDERLINE_ID is null AND M_REQUISITIONLINE.M_PRODUCT_ID is not null ) AND req.IsMostrado = 'Y' ")
		.append("AND req.DocStatus = 'CO' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "req"));

		sql.append(" ORDER BY req.CREATED DESC ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);


			rs = pstmt.executeQuery();

			while (rs.next()) {
				retList.add(new KeyNamePair(rs.getInt(2), rs.getString(1)));
			}

		} catch (final SQLException e) {
			s_log.log(Level.SEVERE, "getReadyPO", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retList;
	}

}	//	MRequisition
