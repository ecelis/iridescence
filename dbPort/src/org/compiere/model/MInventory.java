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

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.bean.TransferBean;
import org.compiere.model.bpm.Inventariado;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.ErrorList;

/**
 *  Physical Inventory Model
 *
 *  @author Jorg Janke
 *  @version $Id: MInventory.java,v 1.8 2006/08/11 02:26:22 mrojas Exp $
 */
public class MInventory extends X_M_Inventory implements DocAction
{

	/** serialVersionUID */
	private static final long serialVersionUID = -658179283784198619L;
	/**	Logger			*/
	private static CLogger s_log = CLogger.getCLogger(MInventory.class);
	/** Permitir hacer inventario si el proceso es de cargos a ctapac paciente o fact. directa */
	private String process = null;
	/** Almacen */
	private MWarehouse mWarehouse = null;

	/**
	 * 	Get Inventory from Cache
	 *	@param ctx context
	 *	@param M_Inventory_ID id
	 *	@return MInventory
	 */
	public static MInventory get (Properties ctx, int M_Inventory_ID)
	{
		Integer key = new Integer (M_Inventory_ID);
		MInventory retValue = (MInventory) s_cache.get (key);
		if (retValue != null
				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) { //lama
			return retValue;
		}
		retValue = new MInventory (ctx, M_Inventory_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	} //	get

	/**	Cache						*/
	private static CCache<Integer,MInventory> s_cache = new CCache<Integer,MInventory>("M_Inventory", 5, 5);


	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_Inventory_ID id
	 */
	public MInventory (Properties ctx, int M_Inventory_ID, String trxName)
	{
		super (ctx, M_Inventory_ID, trxName);
		if (M_Inventory_ID == 0)
		{
			//	setName (null);
			//  setM_Warehouse_ID (0);		//	FK
			setMovementDate (DB.getTimestampForOrg(getCtx()));
			setDocAction (DOCACTION_Complete);	// CO
			setDocStatus (DOCSTATUS_Drafted);	// DR
			setIsApproved (false);
			//setMovementDate (new Timestamp(System.currentTimeMillis()));	// @#Date@
			setPosted (false);
			setProcessed (false);
		}
	}	//	MInventory

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MInventory (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MInventory

	/**
	 * 	Warehouse Constructor
	 *	@param wh warehouse
	 */
	public MInventory (MWarehouse wh)
	{
		this (wh.getCtx(), 0, wh.get_TrxName());
		setClientOrg(wh);
		setM_Warehouse_ID(wh.getM_Warehouse_ID());
	}	//	MInventory


	/**
	 * 	Warehouse Constructor
	 *	@param wh warehouse
	 */
	public MInventory (MWarehouse wh, final int facturasId, final String uProcess)
	{
		this (wh.getCtx(), 0, wh.get_TrxName());
		setClientOrg(wh);
		setM_Warehouse_ID(wh.getM_Warehouse_ID());
		set_TrxName(wh.get_TrxName());
		setDescription(facturasId + "_ " +Utilerias.getMessage(wh.getCtx(),null,"msj.ajustesInventario")
				+ " "
				+ Utilerias.getMessage(wh.getCtx(),null,"cDiarios.nomAlmac")
				+ " "
				+ wh.getName());
		process  = uProcess;
	}
	
	/**	Lines						*/
	private MInventoryLine[]	m_lines = null;

	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return array of lines
	 */
	public MInventoryLine[] getLines (boolean requery)
	{
		if (m_lines != null && !requery) {
			return m_lines;
		}
		//
		ArrayList<MInventoryLine> list = new ArrayList<MInventoryLine>();
		String sql = "SELECT * FROM M_InventoryLine WHERE M_Inventory_ID=? ORDER BY Line";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getM_Inventory_ID());
			rs = pstmt.executeQuery ();

			while (rs.next ()) {
				list.add (new MInventoryLine (getCtx(), rs, get_TrxName()));
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}


		m_lines = new MInventoryLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines

	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null) {
			setDescription(description);
		} else {
			setDescription(desc + " | " + description);
		}
	}	//	addDescription

	/**
	 * 	Overwrite Client/Org - from Import.
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID org
	 */
	public void setClientOrg (int AD_Client_ID, int AD_Org_ID)
	{
		super.setClientOrg(AD_Client_ID, AD_Org_ID);
	}	//	setClientOrg

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MInventory[");
		sb.append (get_ID())
		.append ('-').append (getDocumentNo())
		.append (",M_Warehouse_ID=").append(getM_Warehouse_ID())
		.append (']');
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
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
	public File createPDF (File file)
	{
		//	ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
		//	if (re == null)
		return null;
		//	return re.getPDF(file);
	}	//	createPDF


	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getC_DocType_ID() == 0)
		{
			MDocType types[] = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_MaterialPhysicalInventory);
			if (types.length > 0) {	//	get first
				setC_DocType_ID(types[0].getC_DocType_ID());
			} else {
				log.saveError("Error", Msg.parseTranslation(getCtx(), "@NotFound@ @C_DocType_ID@"));
				return false;
			}
		}

		// solo si es backoffice o importacion
		//				if(process == null){
		//					final MDocType mDocType = new MDocType(getCtx(), getC_DocType_ID(), null);
		//					// solo si es inventario fisico
		//					if(MDocType.DOCBASETYPE_MaterialPhysicalInventory.equals(mDocType.getDocBaseType())){
		//						final String msg = MStorage.getMovementInProgress(getCtx(), getM_Warehouse_ID());
		//						// solo si hay movimientos pendientes de confirmar
		//						if(StringUtils.isNotEmpty(msg)){
		//							log.saveError("Error", Utilerias.getLabel(Utilerias.getLabel(
		//									"error.factDirecta.facturar.noSaveInOut", msg.toString())));
		//							return false;
		//						}
		//					}
		//				}

		//expert : gisela lee : asignar por default la org trx logueada (est serv)
		if(getAD_OrgTrx_ID()==0) {
			setAD_OrgTrx_ID(Env.getContextAsInt(getCtx(), "#AD_OrgTrx_ID"));
		}
		//expert : gisela lee : asignar por default la org trx logueada (est serv)

		// Contabilizar o no contabilizar ?
		setNotPosted();
		
		return true;
	}	//	beforeSave


	/**
	 * 	Set Processed.
	 * 	Propergate to Lines/Taxes
	 *	@param processed processed
	 */
	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		if (get_ID() != 0) {
			String sql =
					"UPDATE M_InventoryLine SET Processed='"
					+ (processed ? "Y" : "N")
					+ "' WHERE M_Inventory_ID=" + getM_Inventory_ID();
			int noLine = DB.executeUpdate(sql, get_TrxName());
			m_lines = null;
			log.fine("Processed=" + processed + " - Lines=" + noLine);
		}
	}	//	setProcessed


	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt

	/**	Process Message 			*/
	private transient String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private transient boolean		m_justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success
	 */
	public boolean unlockIt()
	{
		log.info(toString());
		setProcessing(false);
		return true;
	}	//	unlockIt

	/**
	 * 	Invalidate Document
	 * 	@return true if success
	 */
	public boolean invalidateIt()
	{
		log.info(toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt

	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid)
	 */
	public String prepareIt()
	{
		String retVal = DocAction.STATUS_InProgress;

		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg == null) {
//			Std Period open?
			if (MPeriod.isOpen(getCtx(), getMovementDate(), MDocType.DOCBASETYPE_MaterialPhysicalInventory, getAD_Org_ID())) {
				MInventoryLine[] lines = getLines(false);
				if (lines.length == 0) {
					m_processMsg = "@NoLines@";
					retVal = DocAction.STATUS_Invalid;
				} else {
					// TODO: Add up Amounts
					//	setApprovalAmt();

					m_justPrepared = true;
					if (!DOCACTION_Complete.equals(getDocAction())) {
						setDocAction(DOCACTION_Complete);
					}
				}
			} else {
				m_processMsg = "@PeriodClosed@";
				retVal = DocAction.STATUS_Invalid;
			}
		} else {
			retVal = DocAction.STATUS_Invalid;
		}

		return retVal;
	}	//	prepareIt

	/**
	 * 	Approve Document
	 * 	@return true if success
	 */
	public boolean  approveIt()
	{
		log.info(toString());
		setIsApproved(true);
		return true;
	}	//	approveIt

	/**
	 * 	Reject Approval
	 * 	@return true if success
	 */
	public boolean rejectIt()
	{
		log.info(toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt

	/** No permitir postear por ser consigna */
	public void setNotPosted(){
		if(X_M_Inventory.DOCSTATUS_Completed.equals(getDocStatus())){
			if(getM_Warehouse().isConsigna()){
				setPost(X_M_Movement.POST_Consignment);
			}
		}
	}
	
	/** Saber si es un inventario fisico o no */
	public boolean isPhysicalInventory(){
		return super.isInventory();
	}
	
	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt()
	{
		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status)) {
				return status;
			}
		}
		//	Implicit Approval
		if (!isApproved()) {
			approveIt();
		}
		
		log.info(toString());
		//
		MInventoryLine[] lines = getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MInventoryLine line = lines[i];
			if (line.isActive()) {
				
				List<MTransaction> trxs = new ArrayList<MTransaction>();
				if (line.getM_AttributeSetInstance_ID() == 0)
				{
					BigDecimal qtyDiff = line.getQtyInternalUse().negate();
					if (qtyDiff.signum() == 0) {	//FIXME cconvierte la cantidad a la mano en negativa
						qtyDiff = line.getQtyCount().subtract(line.getQtyBook());
					}
					if (qtyDiff.signum() > 0)
					{
						//	Storage
						MStorage storage = MStorage.get(getCtx(), line.getM_Locator_ID(),
								line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), get_TrxName());
						if (storage == null) {
							storage = MStorage.getCreate(getCtx(), line.getM_Locator_ID(),
									line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), get_TrxName());
						}
						BigDecimal qtyNew = storage.getQtyOnHand().add(qtyDiff);
						log.fine("Diff=" + qtyDiff
								+ " - OnHand=" + storage.getQtyOnHand() + "->" + qtyNew);

						storage.setQtyOnHand(qtyNew);
						storage.setDateLastInventory(getMovementDate());
						if (!storage.save(get_TrxName()))
						{
							m_processMsg = "Storage not updated(1)";
							return DocAction.STATUS_Invalid;
						}
						log.fine(storage.toString());
						/* Expert:Hassan - Registramos una transaccion por cada Esquema contable del Cliente (Bloque Modificado)*/
						MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
						for(int h = 0; h < acctss.length; h++){
							MAcctSchema as = acctss[h];

							//Current Costs
							MProduct product = new MProduct(getCtx(), line.getM_Product_ID(), get_TrxName());
							//	Create Transaction
							MTransaction trx = new MTransaction (
									getCtx(), product,
									line.getM_AttributeSetInstance_ID(),
									as, line.getAD_Org_ID(), product.getCostingMethod(as),
									qtyDiff, 0, false, MTransaction.MOVEMENTTYPE_InventoryIn, line.getM_Locator_ID(),
									getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema contable

							trx.setM_InventoryLine_ID(line.getM_InventoryLine_ID());
							if (!trx.save())
							{
								m_processMsg = "Transaction not inserted(1)";
								return DocAction.STATUS_Invalid;
							}
							trxs.add(trx);
						}
					}
					else	//	negative qty
					{
						MInventoryLineMA mas[] = MInventoryLineMA.get(getCtx(),
								line.getM_InventoryLine_ID(), get_TrxName());
						for (int j = 0; j < mas.length; j++)
						{
							MInventoryLineMA ma = mas[j];
							//	Storage
							MStorage storage = MStorage.get(getCtx(), line.getM_Locator_ID(),
									line.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(), get_TrxName());
							if (storage == null) {
								storage = MStorage.getCreate(getCtx(), line.getM_Locator_ID(),
										line.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(), get_TrxName());
							}
							//
							BigDecimal maxDiff = qtyDiff;
							if (maxDiff.signum() < 0
									&& ma.getMovementQty().compareTo(maxDiff.negate()) < 0)
								maxDiff = ma.getMovementQty().negate();
							BigDecimal qtyNew = ma.getMovementQty().add(maxDiff);	//	Storage+Diff
							log.fine("MA Qty=" + ma.getMovementQty()
									+ ",Diff=" + qtyDiff + "|" + maxDiff
									+ " - OnHand=" + storage.getQtyOnHand() + "->" + qtyNew
									+ " {" + ma.getM_AttributeSetInstance_ID() + "}");
							//
							//BigDecimal tempRemove = storage.getQtyOnHand();//EXPERT.- RMontemayor - Guarda la cantidad que existia en cantidad a la mano
							storage.setQtyOnHand(qtyNew);
							storage.setDateLastInventory(getMovementDate());
							if (!storage.save(get_TrxName()))
							{
								m_processMsg = "Storage not updated (MA)";
								return DocAction.STATUS_Invalid;
							}
							log.fine(storage.toString());

							/* Expert:Hassan - Registramos una transaccion por cada Esquema contable del Cliente (Bloque Modificado)*/
							MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
							for(int h = 0; h < acctss.length; h++){
								MAcctSchema as = acctss[h];
								//Current Costs
								MProduct product = new MProduct(getCtx(), line.getM_Product_ID(), get_TrxName());
								//	Create Transaction
								MTransaction trx = new MTransaction (
										getCtx(), product,
										ma.getM_AttributeSetInstance_ID(),
										as, line.getAD_Org_ID(), product.getCostingMethod(as),
										maxDiff, 0, false, MTransaction.MOVEMENTTYPE_InventoryIn, line.getM_Locator_ID(),
										getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema contable

								trx.setM_InventoryLine_ID(line.getM_InventoryLine_ID());
								if (!trx.save())
								{
									m_processMsg = "Transaction not inserted (MA)";
									return DocAction.STATUS_Invalid;
								}
								trxs.add(trx);
							}/* Expert:Hassan - Fin del Bloque */
							//
							qtyDiff = qtyDiff.subtract(maxDiff);
							if (qtyDiff.signum() == 0)
								break;
						}
					}	//	negative qty
				}

				//	Fallback
				if (trxs == null || trxs.isEmpty()) //Expert:Hassan validar lista.
				{
					//	Storage
					MStorage storage = MStorage.get(getCtx(), line.getM_Locator_ID(),
							line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), get_TrxName());
					if (storage == null) {
						storage = MStorage.getCreate(getCtx(), line.getM_Locator_ID(),
								line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), get_TrxName());
					} //
					BigDecimal qtyDiff = line.getQtyInternalUse().negate();
					if (Env.ZERO.compareTo(qtyDiff) == 0)
						qtyDiff = line.getQtyCount().subtract(line.getQtyBook());
					BigDecimal qtyNew = storage.getQtyOnHand().add(qtyDiff);
					log.fine("Count=" + line.getQtyCount()
							+ ",Book=" + line.getQtyBook() + ", Difference=" + qtyDiff
							+ " - OnHand=" + storage.getQtyOnHand() + "->" + qtyNew);
					//
					storage.setQtyOnHand(qtyNew);
					storage.setDateLastInventory(getMovementDate());

					if (!storage.save(get_TrxName()))
					{
						m_processMsg = "Storage not updated(2)";
						return DocAction.STATUS_Invalid;
					}
					log.fine(storage.toString());

					/* Expert:Hassan - Registramos una transaccion por cada Esquema contable del Cliente (Bloque Modificado)*/
					MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
					for(int h = 0; h < acctss.length; h++){
						final MAcctSchema as = acctss[h];
						//Current Costs
						final MProduct product = new MProduct(getCtx(), line.getM_Product_ID(), get_TrxName());
						//	Create Transaction
						final MTransaction trx = new MTransaction (
								getCtx(), product,
								line.getM_AttributeSetInstance_ID(),
								as, line.getAD_Org_ID(), product.getCostingMethod(as),
								qtyDiff, 0, false, MTransaction.MOVEMENTTYPE_InventoryIn, line.getM_Locator_ID(),
								getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema contable

						trx.setM_InventoryLine_ID(line.getM_InventoryLine_ID());
						if (!trx.save())
						{
							m_processMsg = "Transaction not inserted(2)";
							return DocAction.STATUS_Invalid;
						}
					}/* Expert:Hassan - Fin del Bloque */
				}	//	Fallback
			}

		}	//	for all lines

		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		//		Inventory.updateInventory(getCtx(), lines, getM_Warehouse_ID(), get_TrxName());

		// Si el almacen es de consigna y virtual tanto para el inventario fisico como la salida al gasto
		// generan una orden de compra y una recepcion de material
		if(getM_Warehouse().isConsigna() 
				&& getM_Warehouse().isVirtual() 
				&& getM_Warehouse().getC_BPartner_ID() > 0
				&& isManual()){
			final ErrorList error = MOrder.createMaterialReceipt(this, getInventoryLineForRM());
			if(!error.isEmpty()){
				m_processMsg = error.toString();
				return DocAction.STATUS_Invalid;
			}
		}
		
		setProcessed(true);
		setDocAction(DOCACTION_Close);

		return DocAction.STATUS_Completed;
	}	//	completeIt


	/**
	 * 	Check Material Policy.
	 * 	(NOT USED)
	 * 	Sets line ASI
	 */
	@SuppressWarnings("unused")
	private void checkMaterialPolicy()
	{
		int no = MInventoryLineMA.deleteInventoryMA(getM_Inventory_ID(), get_TrxName());
		if (no > 0)
			log.config("Delete old #" + no);
		MInventoryLine[] lines = getLines(false);

		//	Incoming Trx
		MClient client = MClient.get(getCtx());

		//	Check Lines
		for (int i = 0; i < lines.length; i++)
		{
			MInventoryLine line = lines[i];
			boolean needSave = false;

			//	Attribute Set Instance
			if (line.getM_AttributeSetInstance_ID() == 0)
			{
				MProduct product = MProduct.get(getCtx(), line.getM_Product_ID());
				BigDecimal qtyDiff = line.getQtyInternalUse().negate();
				if (Env.ZERO.compareTo(qtyDiff) == 0)
					qtyDiff = line.getQtyCount().subtract(line.getQtyBook());
				log.fine("Count=" + line.getQtyCount()
						+ ",Book=" + line.getQtyBook() + ", Difference=" + qtyDiff);
				if (qtyDiff.signum() > 0 && product.isLot())	//	In
				{
//					MAttributeSetInstance asi = new MAttributeSetInstance(getCtx(), 0, get_TrxName());
//					asi.setClientOrg(getAD_Client_ID(), 0);
//					asi.setM_AttributeSet_ID(0);
//					String palabra = line.getLotInfo();
//					String arr[] = MEXMELot.getLotInformationArr(palabra);
//
//					asi.setLot(arr[0]);
//					asi.setDescription(arr[1]);
//					if(arr[2] != null && arr[2].length() > 0){
//						try {
//							Date fecha = Constantes.getSdfFecha().parse(arr[2]);
//							asi.setGuaranteeDate(new Timestamp(fecha.getTime()));
//						} catch (ParseException e) {
//							log.log(Level.WARNING, "", e);
//						}
//					}
//					if (asi.save())
//					{
//						line.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
//						needSave = true;
//					}
				}
				else	//	Outgoing Trx
				{
					MProductCategory pc = MProductCategory.get(getCtx(), product.getM_Product_Category_ID());
					String MMPolicy = pc.getMMPolicy();
					if (MMPolicy == null || MMPolicy.length() == 0)
						MMPolicy = client.getMMPolicy();
					//
					MStorage[] storages = MStorage.getAllWithASI(getCtx(),
							line.getM_Product_ID(),	line.getM_Locator_ID(),
							MClient.MMPOLICY_FiFo.equals(MMPolicy), get_TrxName());
					BigDecimal qtyToDeliver = qtyDiff.negate();
					for (int ii = 0; ii < storages.length; ii++)
					{
						MStorage storage = storages[ii];
						if (ii == 0)
						{
							if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0)
							{
								line.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
								needSave = true;
								log.config("Direct - " + line);
								qtyToDeliver = Env.ZERO;
							}
							else
							{
								log.config("Split - " + line);
								MInventoryLineMA ma = new MInventoryLineMA (line,
										storage.getM_AttributeSetInstance_ID(),
										storage.getQtyOnHand().negate());

								if (!ma.save()) {
									log.fine("#" + ii + ": " + ma + ", QtyToDeliver=" + qtyToDeliver);
								}
								qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
								log.fine("#" + ii + ": " + ma + ", QtyToDeliver=" + qtyToDeliver);
							}
						}
						else	//	 create addl material allocation
						{
							MInventoryLineMA ma = new MInventoryLineMA (line,
									storage.getM_AttributeSetInstance_ID(),
									qtyToDeliver.negate());
							if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0) {
								qtyToDeliver = Env.ZERO;
							} else {
								ma.setMovementQty(storage.getQtyOnHand().negate());
								qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
							}

							if (!ma.save()) {
								log.fine("#" + ii + ": " + ma + ", QtyToDeliver=" + qtyToDeliver);
							}
						}
						if (qtyToDeliver.signum() == 0)
							break;
					}	//	 for all storages

					//	No AttributeSetInstance found for remainder
					if (qtyToDeliver.signum() != 0)
					{
						MInventoryLineMA ma =
								new MInventoryLineMA (line, 0, qtyToDeliver.negate());
						if (!ma.save()) {
							log.fine("##: " + ma);
						}
					}
				}	//	outgoing Trx
			}	//	attributeSetInstance

			if (needSave && !line.save())
				log.severe("NOT saved " + line);
		}	//	for all lines

	}	//	checkMaterialPolicy

	/**
	 * 	Void Document.
	 * 	@return false
	 */
	public boolean voidIt()
	{
		log.info(toString());
		if (DOCSTATUS_Closed.equals(getDocStatus())
				|| DOCSTATUS_Reversed.equals(getDocStatus())
				|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			m_processMsg = "Document Closed: " + getDocStatus();
			return false;
		}

		//	Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus())
				|| DOCSTATUS_Invalid.equals(getDocStatus())
				|| DOCSTATUS_InProgress.equals(getDocStatus())
				|| DOCSTATUS_Approved.equals(getDocStatus())
				|| DOCSTATUS_NotApproved.equals(getDocStatus()) )
		{
			//	Set lines to 0
			MInventoryLine[] lines = getLines(false);
			for (int i = 0; i < lines.length; i++)
			{
				MInventoryLine line = lines[i];
				BigDecimal oldCount = line.getQtyCount();
				BigDecimal oldInternal = line.getQtyInternalUse();
				if (oldCount.compareTo(line.getQtyBook()) != 0
						|| oldInternal.signum() != 0)
				{
					line.setQtyInternalUse(Env.ZERO);
					line.setQtyCount(line.getQtyBook());
					line.addDescription("Void (" + oldCount + "/" + oldInternal + ")");
					line.save(get_TrxName());
				}
			}
		}
		else
		{
			return reverseCorrectIt();
		}

		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	}	//	voidIt

	/**
	 * 	Close Document.
	 * 	@return true if success
	 */
	public boolean closeIt()
	{
		log.info(toString());

		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt

	/**
	 * 	Reverse Correction
	 * 	@return false
	 */
	public boolean reverseCorrectIt()
	{
		boolean retVal = false;

		log.info(toString());
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());

		if (MPeriod.isOpen(getCtx(), getMovementDate(), dt.getDocBaseType(), getAD_Org_ID())) {
//			Deep Copy
			MInventory reversal = new MInventory(getCtx(), 0, get_TrxName());
			copyValues(this, reversal, getAD_Client_ID(), getAD_Org_ID());
			reversal.setDocStatus(DOCSTATUS_Drafted);
			reversal.setDocAction(DOCACTION_Complete);
			reversal.setIsApproved (false);
			reversal.setPosted(false);
			reversal.setProcessed(false);
			reversal.addDescription("{->" + getDocumentNo() + ")");
			reversal.setProcess("reverseCorrectIt");
			if (!reversal.save())
			{
				m_processMsg = "Could not create Inventory Reversal";
				return false;
			}

			//	Reverse Line Qty
			MInventoryLine[] oLines = getLines(true);
			for (int i = 0; i < oLines.length; i++)
			{
				MInventoryLine oLine = oLines[i];
				MInventoryLine rLine = new MInventoryLine(getCtx(), 0, get_TrxName());
				copyValues(oLine, rLine, oLine.getAD_Client_ID(), oLine.getAD_Org_ID());
				rLine.setM_Inventory_ID(reversal.getM_Inventory_ID());
				rLine.setParent(reversal);
				//
				rLine.setQtyBook (oLine.getQtyCount());		//	switch
				rLine.setQtyCount (oLine.getQtyBook());
				rLine.setQtyInternalUse (oLine.getQtyInternalUse().negate());
				if (!rLine.save())
				{
					m_processMsg = "Could not create Inventory Reversal Line";
					return false;
				}
			}
			//
			if (!reversal.processIt(DocAction.ACTION_Complete))
			{
				m_processMsg = "Reversal ERROR: " + reversal.getProcessMsg();
				return false;
			}
			reversal.closeIt();
			reversal.setDocStatus(DOCSTATUS_Reversed);
			reversal.setDocAction(DOCACTION_None);
			reversal.save();
			m_processMsg = reversal.getDocumentNo();

			//	Update Reversed (this)
			addDescription("(" + reversal.getDocumentNo() + "<-)");
			setProcessed(true);
			setDocStatus(DOCSTATUS_Reversed);	//	may come from void
			setDocAction(DOCACTION_None);

			retVal = true;
		} else {
			m_processMsg = "@PeriodClosed@";
		}

		return retVal;
	}	//	reverseCorrectionIt

	/**
	 * 	Reverse Accrual
	 * 	@return false
	 */
	public boolean reverseAccrualIt()
	{
		log.info(toString());
		return false;
	}	//	reverseAccrualIt

	/**
	 * 	Re-activate
	 * 	@return false
	 */
	public boolean reActivateIt()
	{
		log.info(toString());
		return false;
	}	//	reActivateIt


	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
		//	: Total Lines = 123.00 (#1)
		sb.append(": ")
		.append(Msg.translate(getCtx(),"ApprovalAmt")).append('=').append(getApprovalAmt())
		.append(" (#").append(getLines(false).length).append(')');
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary

	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg

	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
		return getUpdatedBy();
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Currency
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID()
	{
		//	MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
		//	return pl.getC_Currency_ID();
		return 0;
	}	//	getC_Currency_ID

	/**
	 * Obtiene una lista de MInventory
	 * @param where filtro para query inicia con "AND"
	 * @param ctx Contexto
	 * @return list
	 * @author rosy velazquez
	 * */
	public static List<MInventory> getMInventories(String where, Properties ctx){
		ArrayList<MInventory> list = new ArrayList<MInventory>();
		StringBuffer sql =
				new StringBuffer("SELECT * FROM M_Inventory WHERE isActive= 'Y' ");

		if(where != null){
			sql.append(where);
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add (new MInventory(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}//getMInventories

	/**
	 * Búsquead de inventario físico (Mantenimiento)
	 *
	 * @param ctx
	 *            Contexto de la App
	 * @param documentNo
	 *            Número de documento, opcional
	 * @param date
	 *            Fecha inicio
	 * @param date2
	 *            Fecha fin
	 * @param warehouseId
	 *            Almacen, opcional
	 * @param docStatus
	 *            Estado del documento, opcional
	 * @param docsStatus
	 *            Listado de estatus, opcional
	 * @param productId
	 *            Producto detalle
	 * @param type
	 *            Tipo de documento
	 * @param trxName
	 *            Nombre de trx
	 * @return Listado con movimientos que cumplen la búsqueda
	 */
	public static List<MInventory> get(Properties ctx, String documentNo, Timestamp date, Timestamp date2,
			int warehouseId, String docStatus, List<String> docsStatus, int productId, String type, String trxName) {
		List<MInventory> list = new ArrayList<MInventory>();

		List<Object> params = new ArrayList<Object>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT i.* FROM m_inventory i ");

		if (productId > 0) {
			sql.append(" INNER JOIN M_InventoryLine il on i.M_Inventory_ID = il.M_Inventory_ID ");
		}

		sql.append("WHERE i.inventory = ? ");
		
		if (X_M_InventoryLine.INVENTORYTYPE_InventoryDifference.equals(type)) {
			params.add("Y");
		} else {
			params.add("N");
		}

		if (StringUtils.isNotBlank(documentNo)) {
			sql.append("  AND lower(i.documentNo) = ? ");
			params.add(documentNo.toLowerCase());

			docsStatus = new ArrayList<String>();

			docsStatus.add(DB.TO_STRING(X_M_Inventory.DOCSTATUS_Voided));
			docsStatus.add(DB.TO_STRING(X_M_Inventory.DOCSTATUS_Completed));
			docsStatus.add(DB.TO_STRING(X_M_Inventory.DOCSTATUS_Closed));
			docsStatus.add(DB.TO_STRING(X_M_Inventory.DOCSTATUS_Drafted));
			docsStatus.add(DB.TO_STRING(X_M_Inventory.DOCSTATUS_InProgress));
			docsStatus.add(DB.TO_STRING(X_M_Inventory.DOCSTATUS_NotApproved));

			sql.append("  AND i.docstatus IN (");
			sql.append(StringUtils.join(docsStatus, ","));
			sql.append(") ");
		} else {
			sql.append("  AND i.movementdate BETWEEN ? AND ? ");

			if (date.after(date2)) {
				params.add(date2);
				params.add(date);
			} else {
				params.add(date);
				params.add(date2);
			}

			if (warehouseId > 0) {
				sql.append("  AND i.m_warehouse_id = ? ");
				params.add(warehouseId);
			}

			if (StringUtils.isNotBlank(docStatus)) {
				sql.append("  AND i.docstatus = ? ");

				params.add(docStatus);
			}

			if (docsStatus != null && !docsStatus.isEmpty()) {
				sql.append("  AND i.docstatus IN (");
				sql.append(StringUtils.join(docsStatus, ","));
				sql.append(") ");
			}

			if (productId > 0) {
				sql.append("  AND il.M_Product_ID = ? ");

				params.add(productId);
			}

		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "i"));

		sql.append(" ORDER BY i.documentno desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MInventory(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Líneas de inventario físico
	 *
	 * @param ctx
	 *            Contexto
	 * @param inventoryId
	 *            Id de inventario
	 * @param trxName
	 *            Trx
	 * @return Listado de líneas
	 */
	public static List<MInventoryLine> getLines(Properties ctx, int inventoryId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT *  FROM m_inventoryline WHERE m_inventory_id = ? ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MInventoryLine.Table_Name));

		sql.append(" ORDER BY created desc ");

		List<MInventoryLine> list = new ArrayList<MInventoryLine>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, inventoryId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MInventoryLine(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Actualiza el inventario físico con las unidades disponibles (onHand -
	 * reservada)
	 *
	 * @param ctx
	 *            Contexto
	 * @param inventoryId
	 *            Inventario físico
	 * @param trxName
	 *            Trx Name
	 * @return Si/No pudo actualizar
	 */
	public static boolean updateInventoryLines(Properties ctx, int inventoryId, String trxName) {
		boolean retValue = true;

		List<MInventoryLine> lines = MInventory.getLines(ctx, inventoryId, trxName);

		for (MInventoryLine line : lines) {
			MStorage storage =
					MStorage.get(
							ctx,
							line.getM_Locator_ID(),
							line.getM_Product_ID(),
							line.getM_AttributeSetInstance_ID(),
							null
					);

			if (storage == null) {
				line.setQtyBook(BigDecimal.ZERO);
				line.setQtyBook_Vol(BigDecimal.ZERO);
				line.setQtyBook_Uom(BigDecimal.ZERO);
			} else {
				// se regresa a como esta en el proceso original (indicaciones de GCB)
				//BigDecimal qty = storage.getQtyOnHand().subtract(storage.getQtyReserved());

				line.setQtyBook(storage.getQtyOnHand());

				if (line.getC_UOMVolume_ID() == line.getC_UOM_ID()) {
					line.setQtyBook_Vol(BigDecimal.ZERO);
					line.setQtyBook_Uom(storage.getQtyOnHand());
				} else {
					BigDecimal[] arr =
							MEXMEUOMConversion.calculateQtys(ctx, line.getProduct(), storage.getQtyOnHand());

					BigDecimal qtyVol = arr[0];

					BigDecimal qtyMin = arr[1];

					line.setQtyBook_Vol(qtyVol);
					line.setQtyBook_Uom(qtyMin);
				}
			}

			if (!line.save(trxName)) {
				retValue = false;
				break;
			}

		}

		return retValue;
	}

	/**
	 * Crea las línea de inventario basandose en los parámetros usados
	 *
	 * @param ctx
	 *            Contexto
	 * @param inventoryId
	 *            Id del inventario
	 * @param locatorId
	 *            Localizador para relacionar las líneas
	 * @param locatorValue
	 *            Valor del localizador
	 * @param productId
	 *            Id del producto
	 * @param productValue
	 *            Valor del producto
	 * @param prodCategoryId
	 *            Categoría del producto
	 * @param operator
	 *            Operador, se usa para validar exitencias (< 0, > 0, = 0, null)
	 * @param zero
	 *            Cantidad registrada a cero
	 * @param delete
	 *            Borrar registros previos
	 * @param trxName
	 *            Nombre de la trx
	 * @return Si/No pudo guardar todas las líneas
	 */
	public static int createInventoryLines(Properties ctx, int inventoryId, int locatorId, String locatorValue, int productId, String productValue, int prodCategoryId, String operator, boolean zero, boolean delete, String trxName) {
		int count = 0;

		if (delete) {
			StringBuilder sql = new StringBuilder("DELETE M_InventoryLine WHERE Processed='N' ");
			sql.append("AND M_Inventory_ID = ? ");
			DB.executeUpdate(sql.toString(), new Object[] { inventoryId }, trxName);
		}
		
		List<Integer> prodIds = new ArrayList<>();

		List<MStorage> list = MStorage.get(ctx, inventoryId, locatorId, locatorValue, productId, productValue, prodCategoryId, operator, !delete, trxName);

		for (MStorage storage : list) {
			MProduct product = new MProduct(ctx, storage.getM_Product_ID(), null);
			
			MEXMEProductoOrg po = product.getProdOrg();
			
			if (po != null && !po.isUnused()) {
				MInventoryLine line = new MInventoryLine(ctx, 0, null);
				line.setM_Inventory_ID(inventoryId);
	
				if (zero) {
					line.setQtyBook(BigDecimal.ZERO);
					line.setQtyBook_Uom(BigDecimal.ZERO);
					line.setQtyBook_Vol(BigDecimal.ZERO);
				} else {
					// se regresa a como era el proceso original : solamente se considera
					// la cantidad a la mano (GCB)
					// BigDecimal qty = storage.getQtyOnHand().subtract(storage.getQtyReserved());
	
					line.setQtyBook(storage.getQtyOnHand());
	
					if (product.getC_UOMVolume_ID() == product.getC_UOM_ID()) {
						line.setQtyBook_Vol(BigDecimal.ZERO);
						line.setQtyBook_Uom(storage.getQtyOnHand());
					} else {
						BigDecimal[] arr = MEXMEUOMConversion.calculateQtys(ctx, product, storage.getQtyOnHand());
	
						BigDecimal qtyVol = arr[0];
	
						BigDecimal qtyMin = arr[1];
	
						line.setQtyBook_Vol(qtyVol);
						line.setQtyBook_Uom(qtyMin);
					}
				}
	
				line.setM_Locator_ID(storage.getM_Locator_ID());
				line.setM_Product_ID(storage.getM_Product_ID());
				line.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
				line.setC_UOM_ID(product.getC_UOM_ID());
				line.setC_UOMVolume_ID(product.getC_UOMVolume_ID());
				
				prodIds.add(product.getM_Product_ID());
	
				if (line.save(trxName)) {
					count++;
				} else {
					count = 0;
					break;
				}
			}
		}
		
		if (productId <= 0 && (StringUtils.EMPTY.equals(operator) || "<".equals(operator) || "=".equals(operator))) {
			int warehouseId = MLocator.getWarehouse(ctx, locatorId, null).getM_Warehouse_ID();

			List<Integer> relations = MReplenish.getProductsByWarehouse(ctx, warehouseId, prodCategoryId, null);

			for (int id : relations) {
				if (!prodIds.contains(id)) {
					MProduct prod = new MProduct(ctx, id, null);
					
					MEXMEProductoOrg po = prod.getProdOrg();
					
					if (po != null && !po.isUnused()) {
						MInventoryLine line = new MInventoryLine(ctx, 0, null);
						line.setM_Inventory_ID(inventoryId);
						line.setQtyBook(BigDecimal.ZERO);
						line.setQtyBook_Uom(BigDecimal.ZERO);
						line.setQtyBook_Vol(BigDecimal.ZERO);

						line.setM_Locator_ID(locatorId);
						line.setM_Product_ID(id);
						line.setM_AttributeSetInstance_ID(0);
						line.setC_UOM_ID(prod.getC_UOM_ID());
						line.setC_UOMVolume_ID(prod.getC_UOMVolume_ID());

						if (line.save(trxName)) {
							count++;
						} else {
							count = 0;
							break;
						}
					}
				}
			}
		} else if (productId > 0 && list.isEmpty()) {
			MProduct product = new MProduct(ctx, productId, null);

			MEXMEProductoOrg productOrg = product.getProdOrg();

			if (productOrg != null) {

				MInventoryLine line = new MInventoryLine(ctx, 0, null);

				line.setM_Inventory_ID(inventoryId);
				line.setM_Product_ID(productId);
				line.setM_Locator_ID(locatorId);
				line.setC_UOM_ID(product.getC_UOM_ID());
				line.setC_UOMVolume_ID(product.getC_UOMVolume_ID());

				if (line.save(trxName)) {
					count++;
				}
			}
		}

		return count;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	
	 /** Listado de productos en movimiento */
	public List<TransferBean> getInventoryLineForRM() {
		final List<TransferBean> lines = new ArrayList<TransferBean>();

		final MInventoryLine[] invLine = getLines (true);
		for(final MInventoryLine line:invLine) {

			TransferBean mTransferBean=null;
			if(X_M_InventoryLine.INVENTORYTYPE_InventoryDifference.equals(line.getInventoryType())) {
				// Si cantidad contada < la cantidad en libros:
				if(line.getQtyCount().compareTo(line.getQtyBook())<0){
					mTransferBean = new TransferBean();
					mTransferBean.setQtyEntered(line.getQtyBook().subtract(line.getQtyCount()));
					mTransferBean.setQtyOrdered(line.getQtyBook().subtract(line.getQtyCount()));
				}
			} else {
				mTransferBean = new TransferBean();
				mTransferBean.setQtyEntered(line.getQtyInternalUse());
				mTransferBean.setQtyOrdered(line.getQtyInternalUse());
			}

			if(mTransferBean!=null){
				final MProductPrice mPrice = MProductPrice.getProductPrice(
						getM_Warehouse().getC_BPartner_ID()
						, 0
						, line.getM_Product_ID() 
						, false);
				
				if (mPrice == null 
						|| BigDecimal.ZERO.compareTo(mPrice.getPriceList_Vol()) == 0
						|| BigDecimal.ZERO.compareTo(mPrice.getPriceList()) == 0) {
					throw new MedsysException(Utilerias.getAppMsg(getCtx(), "error.factDirecta.findPrice", line.getProduct().getName()));
				}
				
				// NOTA1: Sucede que el inventario fisico de hace de la suma de las dos unidades de medida
				// Es decir un inventario fisico dice tengo 10 paquetes y 5 piezas
				mTransferBean.setQtyEntered_Vol(mTransferBean.getQtyEntered());
				mTransferBean.setQtyOrdered_Vol(mTransferBean.getQtyOrdered());
				mTransferBean.setM_Product_ID(line.getM_Product_ID());
				mTransferBean.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
				mTransferBean.setC_UOM_ID(line.getC_UOM_ID());
				mTransferBean.setPriceActual(mPrice.getPriceList());
				mTransferBean.setPriceList(mPrice.getPriceList());
				mTransferBean.setPriceEntered(mPrice.getPriceList());
				mTransferBean.setDescription(line.getDescription());
				mTransferBean.setDatePromised(new Timestamp(System.currentTimeMillis()));
				mTransferBean.setPriceActual_Vol(mPrice.getPriceList());// NOTA1:
				mTransferBean.setPriceList_Vol(mPrice.getPriceList());// NOTA1:
				mTransferBean.setPriceEntered_Vol(mPrice.getPriceList());// NOTA1:
				mTransferBean.setC_UOMVolume_ID(line.getC_UOM_ID());// NOTA1:

				lines.add(mTransferBean);
			}
		}

		return lines;
	}
	
	/** Objeto del almacen */
	public MWarehouse getWarehouse() {
		if(mWarehouse==null){
			mWarehouse = new MWarehouse(getCtx(), getM_Warehouse_ID(), get_TrxName());
		}
		return mWarehouse;
	}

	/** Si el documento es un inventario o no lo es */
	public boolean isInventory() {
		MDocType tipoDoc = new MDocType(Env.getCtx(), getC_DocType_ID(), null);
		return X_C_DocType.DOCBASETYPE_MaterialPhysicalInventory.equals(tipoDoc.getDocBaseType());
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		// Si el documento se cancela, poner todos los valores en 0, GC
		if (!newRecord && success && DOCSTATUS_Voided.equals(getDocStatus())) {
			for (MInventoryLine line : getLines(true)) {
				line.setQtyBook(BigDecimal.ZERO);
				line.setQtyBook_Uom(BigDecimal.ZERO);
				line.setQtyBook_Vol(BigDecimal.ZERO);
				line.setQtyCount(BigDecimal.ZERO);
				line.setQtyCount_Uom(BigDecimal.ZERO);
				line.setQtyCount_Vol(BigDecimal.ZERO);
				line.setQtyInternalUse(BigDecimal.ZERO);
				line.setQtyInternalUse_Uom(BigDecimal.ZERO);
				line.setQtyInternalUse_Vol(BigDecimal.ZERO);

				line.save(get_TrxName());
			}
		}
		return super.afterSave(newRecord, success);
	}
	
	public static String getPosted(int inoutId) {
		return
				DB.getSQLValueString(
						null,
						" SELECT Posted FROM M_Inventory WHERE M_Inventory_ID = ? ",
						new Object[]{inoutId}
				);
	}
	
	/** Crear un linea de inventario fisico para lograr la venta al publico
	 * Debe agregar la cantidad faltante y posterior en otros procesos 
	 * se le dara salida
	 * @param mInventariado
	 * @return
	 */
	public boolean addInventariadoLines(Inventariado mInventariado) {
		//boolean success = true;
		// Una linea por cada producto sin existencia suficiente
		// la cantidad sera la faltante
		BigDecimal qtyOnHand = MStorage.getQtyOnHand(
				mInventariado.getiCargo().getmLocatorSurID()
				,mInventariado.getiCargo().getM_Product_ID()
				,mInventariado.getiCargo().getM_AttributeSetInstance_ID()
				,null);

		final MInventoryLine line = new MInventoryLine(this
				, mInventariado.getiCargo().getmLocatorSurID()
				, mInventariado.getiCargo().getM_Product_ID()
				, mInventariado.getiCargo().getM_AttributeSetInstance_ID()
				, qtyOnHand
				, Env.ZERO);
		line.setDescription(mInventariado.getDescription());
		//Colocar las cantidades correctas en la linea
		line.setQtysBookAndCount(mInventariado.getQtyPedido()
				, mInventariado.getcUOMIDSurt()
				, mInventariado.getQtyAvailable());//llena la cantidad contada
		//line.set_TrxName(get_TrxName());
		if (!line.save(get_TrxName())) {
			throw new MedsysException();
		}
		return true;
	}

	/** Busca inventario fisico relacionado a la factura */
	public static String getInvoice(Properties ctx, int facturaID, int facturaID2) {
		String where = " ( Description like '"+facturaID+"_%' "+" OR Description like '"+facturaID2+"_%' ) ";
		final List<X_M_Inventory>  lst = new Query(ctx,
				X_M_Inventory.Table_Name, where, null)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.list();
		return lst.isEmpty()?StringUtils.EMPTY:Utilerias.getLabel("msj.existenciasVtaPublico");
	}
}	//	MInventory
