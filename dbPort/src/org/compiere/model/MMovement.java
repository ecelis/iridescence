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

import static org.compiere.util.Utilerias.getAppMsg;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.bean.MovementLine;
import org.compiere.model.bpm.CreateCharge;
import org.compiere.model.bpm.GetPrice;
import org.compiere.model.bpm.Inventory;
import org.compiere.model.movements.MovementBean;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ExpertException;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Inventory Movement Model
 *
 * @author Jorg Janke
 * @version $Id: MMovement.java,v 1.14 2006/09/14 23:59:36 mrojas Exp $
 * Modificado por Lorena Lama (Marzo 2014)
 */
public class MMovement extends X_M_Movement implements DocAction {

	/** serialVersionUID */
	private static final long serialVersionUID = 2450216414144485612L;
	/** Static logger */
	private static CLogger slog = CLogger.getCLogger(MMovement.class);
	/** Cuenta paciente */
	private MEXMECtaPac ctaPac = null;
//	/** @deprecated Almacen Solicitante */
//	private MWarehouse mAlmSolic = null;
//	/** @deprecated Almacen que surte */
//	private MWarehouse mAlmSurt = null;
	/** Lines */
	private MMovementLine[] mLines = null;
	/** Confirmations */
	private MMovementConfirm[] mConfirms = null;
	/** Process Message */
	private String mProcessMsg = null;
	/** Just Prepared Flag */
	private boolean mJustPrepared = false;
	/** localizador */
	private MLocator mLocator = null;
	/** localizador */
	private MLocator mLocatorTo = null;
	/** Almacen */
	private String almacen = null;
	/** */
	public boolean isProdOrTray = false;
	/** numero de cargos generados */
	private int noRecordsCtaPacDet = 0;
	/** Mensaje de cargos generados */
	private String msgChargesOk = "";
	/** Bean para mostrar los datos en la ventanas */
	private MovementBean confirmationData = null;
	/** socio de negocio */
	private MBPartner mBPartner = null;
	/** movimiento inverso a la confirmacion */
	private MMovement returnMovement;
	/** listas de movimientos de devolucion, para el caso que sea de consigna */
	private List<MMovement> returnMovementList;
	/** Tipo de movimiento que ejecuta la devolucion */
	private int trxType = -1;
	/** Nombre del quirofano */
	private String operatingRoomName;
	/** enlistar los movimientos de consigna virtual */
//	private HashMap<Integer, List<MovementLine>> mapConsignment;
	private List<SubMovement> mapConsignment;

	/**
	 * Standard Constructor
	 *
	 * @param ctx context
	 * @param mMovementID  id
	 * @param trxName transaction
	 */
	public MMovement(final Properties ctx, final int mMovementID, final String trxName) {
		super(ctx, mMovementID, trxName);
		setDefaultValues();//lama
	} // MMovement

	/**
	 * Crea un nuevo encabezado a partir de 2 almacenes, obtiene la orgtrx de almacen solicitante
	 * @param ctx Contexto
	 * @param warehouseFromId almacen aplica (a quien se hara la salida de inventario)
	 * @param warehouseToId almacen solicitante/confirma destino a donde ira la existencia
	 */
	public MMovement(Properties ctx, int warehouseFromId, int warehouseToId) {
		this(ctx, 0, null);
		setApprovalAmt(BigDecimal.ZERO);
		setM_WarehouseTo_ID(warehouseToId);
		if(warehouseFromId > 0) {
			setM_Warehouse_ID(warehouseFromId);
			setAD_OrgTrx_ID(MEXMEEstServ.getEstServAlmOrgTrx(getCtx(), warehouseFromId));
		}
	}

	/**
	 * Asigna los valores por defecto para un objeto nuevo de M_Movement
	 * incluyendo el tipo de documento DOCBASETYPE_MaterialMovement MMM, en transito
	 */
	private final void setDefaultValues() {
		if (is_new()) {
			setIsDevol(false);
			setProcessing(false);
			setIsApproved(false);
			setIsInTransit(false);
			setPosted(false);
			setBackorder(false);
			super.setProcessed(false);
			setDocAction(DOCACTION_Complete); // CO
			setDocStatus(DOCSTATUS_Drafted); // DR
			setMovementDate(Env.getCurrentDate()); // @#Date@
			// doc base type: MMM, InTransit, Name Movement
			MDocType docType = MDocType.getTipoDoc(getCtx(), MDocType.DOCBASETYPE_MaterialMovement, Constantes.MOVEMENT, true, null);
			if (docType == null) {
				log.saveError("Error", Msg.parseTranslation(getCtx(), "@NotFound@ @C_DocType_ID@"));
			} else {
				setC_DocType_ID(docType.getC_DocType_ID());
			}
		}
	}

	/**
	 * Load Constructor
	 *
	 * @param ctx context
	 * @param rset result set
	 * @param trxName transaction
	 */
	public MMovement(final Properties ctx, final ResultSet rset, final String trxName) {
		super(ctx, rset, trxName);
	} // MMovement

	/**
	 * Get Lines
	 *
	 * @param requery requery
	 * @return array of lines
	 */
	public MMovementLine[] getLines(final boolean requery) {
		if (mLines != null && !requery) {
			return mLines;
		}

		final List<MMovementLine> list = getLines();

		mLines = new MMovementLine[list.size()];
		list.toArray(mLines);

		return mLines;
	} // getLines

	/**
	 * Líneas del movimiento
	 *
	 * @param ctx
	 *            Contexto
	 * @param movementId
	 *            Movimiento
	 * @param trxName
	 *            Nombre de trx
	 * @return Listado de líneas
	 */
	public static List<MovementLine> getLines(Properties ctx, int movementId, String trxName) {
		final List<MovementLine> lines = new ArrayList<MovementLine>();

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  l.m_movementline_id, ");
		sql.append("  l.confirmedqty, ");// Lo surtido despues de confirmar y se le resta scrappedQty para saber lo confirmado
		sql.append("  l.confirmedqty_vol, ");
		sql.append("  l.exme_ctapac_id, ");
		sql.append("  l.m_locator_id, ");
		sql.append("  l.m_locatorto_id, ");
		sql.append("  l.m_attributesetinstance_id, ");
		sql.append("  l.originalqty, ");// Lo solicitado
		sql.append("  l.originalqty_vol, ");
		sql.append("  l.m_product_id, ");
		sql.append("  l.targetqty, ");// Lo surtido
		sql.append("  l.targetqty_vol, ");
		sql.append("  l.c_uom_id, ");
		sql.append("  l.c_uomvolume_id, ");
		sql.append("  l.op_uom, ");
		sql.append("  l.description, ");
		sql.append("  l.returnQty, ");// La cantidad a devolver por transaccion (No es el acumulado de devolucion)
		sql.append("  l.returnQty_Vol, ");
		sql.append("  l.scrappedQty, ");// La diferencia entre lo surtido y confirmado por el usuario
		sql.append("  l.scrappedQty_Vol ");

		sql.append("FROM ");
		sql.append("  m_movementline l ");
		sql.append("WHERE ");
		sql.append("  m_movement_id = ? AND isActive = 'Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, movementId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MovementLine line = new MovementLine();
				line.setId(rs.getInt("m_movementline_id"));

				line.setConfirmedQty(rs.getBigDecimal("confirmedqty"));
				line.setConfirmedVolQty(rs.getBigDecimal("confirmedqty_vol"));
				int ctaPacId = rs.getInt("exme_ctapac_id");
				if(ctaPacId > 0){
					line.setCtaPac(new MEXMECtaPac(ctx, ctaPacId, null));
				}

				line.setLocatorFromId(rs.getInt("m_locator_id"));
				line.setLocatorToId(rs.getInt("m_locatorto_id"));
				line.setLotId(rs.getInt("m_attributesetinstance_id"));
				line.setMovementId(movementId);
				line.setOriginalQty(rs.getBigDecimal("originalqty"));
				line.setOriginalVolQty(rs.getBigDecimal("originalqty_vol"));
				line.setProduct(new MProduct(ctx, rs.getInt("m_product_id"), null));
				line.setSelectedUomId(rs.getInt("op_uom"));
				line.setTargetQty(rs.getBigDecimal("targetqty"));
				line.setTargetVolQty(rs.getBigDecimal("targetqty_vol"));
				line.setUomId(rs.getInt("c_uom_id"));
				line.setUomVolId(rs.getInt("c_uomvolume_id"));
				line.setDescription(rs.getString("description"));
				line.setReturnQty(rs.getBigDecimal("returnQty"));
				line.setReturnVolQty(rs.getBigDecimal("returnQty_Vol"));
				line.setScrappedQty(rs.getBigDecimal("scrappedQty"));
				line.setScrappedVolQty(rs.getBigDecimal("scrappedQty_Vol"));
				lines.add(line);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lines;
	}

	/**
	 * Get Confirmations
	 *
	 * @param requery
	 *            requery
	 * @return array of Confirmations
	 */
	public MMovementConfirm[] getConfirmations(final boolean requery) {
		if (mConfirms != null && !requery) {
			return mConfirms;
		}
		final List<MMovementConfirm> list = new Query(getCtx(), MMovementConfirm.Table_Name, "M_Movement_ID=?", get_TrxName())//
			.setParameters(getM_Movement_ID())//
			.list();
		mConfirms = new MMovementConfirm[list.size()];
		list.toArray(mConfirms);
		return mConfirms;
	} // getConfirmations

	/**
	 * Add to Description
	 *
	 * @param description
	 *            text
	 */
	private void addDescription(final String description) {
		final String desc = getDescription();
		if (desc == null){
			setDescription(description);
		}else{
			setDescription(desc + " | " + description);
		}
	} // addDescription

	/**
	 * Get Document Info
	 *
	 * @return document info (untranslated)
	 */
	public String getDocumentInfo() {
		final MDocType doctype = MDocType.get(getCtx(), getC_DocType_ID());
		return doctype.getName() + " " + getDocumentNo();
	} // getDocumentInfo

	/**
	 * Create PDF
	 *
	 * @return File or null
	 */
	public File createPDF() {
		try {
			final File temp = File.createTempFile(get_TableName() + get_ID()
					+ "_", ".pdf");
			return createPDF(temp);
		} catch (Exception e) {
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	} // getPDF

	/**
	 * Create PDF file
	 *
	 * @param file
	 *            output file
	 * @return file if success
	 */
	public File createPDF(final File file) {
		return null;
	} // createPDF

	/**
	 * Before Save
	 *
	 * @param newRecord
	 *            new
	 * @return true
	 */
	protected boolean beforeSave(final boolean newRecord) {
		if(newRecord && isActive()) {
			if(isHl7Warehouse(MWarehouse.getIdWarehouseDesc(getCtx(),getAlmacen()))){
				setIsInTransit(true);// Marcamos el movimiento como solicitud de Sangre
			}
		}

		if (getC_DocType_ID() <= 0) {
			final MDocType doctype = MDocType.getTipoDoc(getCtx(), MDocType.DOCBASETYPE_MaterialMovement, null, null, null);
			if(doctype == null) {
				log.saveError("Error", Msg.parseTranslation(getCtx(), "@NotFound@ @C_DocType_ID@"));
				return false;
			}
			setC_DocType_ID(doctype.getC_DocType_ID());
		}

		// guardamos el usuario que crea/surte el movimiento,
		// si es nuevo o el estatus es IP o es CO .- Lama
		if (newRecord) {
			setSoli_User_ID(Env.getAD_User_ID(getCtx()));
		} else if (is_ValueChanged("DocStatus")) {
			if (MMovement.DOCSTATUS_InProgress.equals(getDocStatus())) {
				setSurt_User_ID(Env.getAD_User_ID(getCtx()));
			} else if (MMovement.DOCSTATUS_Completed.equals(getDocStatus())) {
				setConf_User_ID(Env.getAD_User_ID(getCtx()));
			}
		}
		if (getAD_OrgTrx_ID() <= 0) {
			setAD_OrgTrx_ID(Env.getContextAsInt(getCtx(), "#AD_OrgTrx_ID"));
		}

		// Contabilizar o no contabilizar ?
		setNotPosted();

		return true;
	} // beforeSave

	/**
	 * Set Processed. Propergate to Lines/Taxes
	 *
	 * @param processed
	 *            processed
	 */
	public void setProcessed(final boolean processed) {
		super.setProcessed(processed);
		if (get_ID() == 0) {
			return;
		}
		final String sql = "UPDATE M_MovementLine SET Processed='"
				+ (processed ? "Y" : "N") + "' WHERE M_Movement_ID="
				+ getM_Movement_ID();
		final int noLine = DB.executeUpdate(sql, get_TrxName());
		mLines = null;
		log.fine("Processed=" + processed + " - Lines=" + noLine);
	} // setProcessed

	/**************************************************************************
	 * Process document
	 *
	 * @param processAction
	 *            document action
	 * @return true if performed
	 */
	public boolean processIt(final String processAction) {
		mProcessMsg = null;
		final DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(processAction, getDocAction());
	} // processIt

	/**
	 * Unlock Document.
	 *
	 * @return true if success
	 */
	public boolean unlockIt() {
		log.info(toString());
		setProcessing(false);
		return true;
	} // unlockIt

	/**
	 * Invalidate Document
	 *
	 * @return true if success
	 */
	public boolean invalidateIt() {
		log.info(toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	} // invalidateIt

	/**
	 * Prepare Document
	 *
	 * @return new status (In Progress or Invalid)
	 */

	public String prepareIt() {
		log.info(toString());
		mProcessMsg = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_BEFORE_PREPARE);
		if (mProcessMsg != null){
			return DocAction.STATUS_Invalid;
		}
		final MDocType doctype = MDocType.get(getCtx(), getC_DocType_ID());

		// Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateReceived(), doctype.getDocBaseType(),
				Env.getAD_Org_ID(getCtx()))) {
			mProcessMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		final MMovementLine[] lines = getLines(false);
		if (lines.length == 0) {
			mProcessMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		// Add up Amounts

		checkMaterialPolicy();

		// Confirmation
		if (doctype.isInTransit()) {
			createConfirmation();
		}

		mJustPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction())) {
			setDocAction(DOCACTION_Complete);
		}
		return DocAction.STATUS_InProgress;
	} // prepareIt

	/**
	 * Create Movement Confirmation
	 */
	private void createConfirmation() {
		final MMovementConfirm[] confirmations = getConfirmations(false);
		if (confirmations.length > 0) {
			return;
		}

		// Create Confirmation
		MMovementConfirm.create(this, false);
	} // createConfirmation

	/**
	 * Approve Document
	 *
	 * @return true if success
	 */
	public boolean approveIt() {
		log.info(toString());
		setIsApproved(true);
		return true;
	} // approveIt

	/**
	 * Reject Approval
	 *
	 * @return true if success
	 */
	public boolean rejectIt() {
		log.info(toString());
		setIsApproved(false);
		return true;
	} // rejectIt

	/**
	 * Complete Document
	 *
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt() {
		// Re-Check
		if (!mJustPrepared) {
			final String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status)){
				return status;
			}
		}

		// Outstanding (not processed) Incoming Confirmations ?
		final MMovementConfirm[] confirmations = getConfirmations(true);
		for (int i = 0; i < confirmations.length; i++) {
			final MMovementConfirm confirm = confirmations[i];
			if (!confirm.isProcessed()) {
				mProcessMsg = "Open: @M_MovementConfirm_ID@ - "
						+ confirm.getDocumentNo();
				return DocAction.STATUS_InProgress;
			}
		}

		// Implicit Approval
		if (!isApproved()) {
			approveIt();
		}
		log.info(toString());

		//
		final MMovementLine[] lines = getLines(false);
		for (int i = 0; i < lines.length; i++) {
			final MMovementLine line = lines[i];
			MTransaction trxFrom = null; // Expert:Hassan
			// Expert:Hassan- Para guardar la referencia de cada transaccion por SAcct
			final List<MTransaction> mtrxs = new ArrayList<MTransaction>();
			if (line.getM_AttributeSetInstance_ID() == 0) {
				final MMovementLineMA mas[] = MMovementLineMA.get(getCtx(),
						line.getM_MovementLine_ID(), get_TrxName());
				for (int j = 0; j < mas.length; j++) {
					final MMovementLineMA movLineMA = mas[j];
					// Localizador surte
					MStorage storageFrom = MStorage.get(getCtx(),
							line.getM_Locator_ID(), line.getM_Product_ID(),
							movLineMA.getM_AttributeSetInstance_ID(), get_TrxName());
					if (storageFrom == null){
						storageFrom = MStorage.getCreate(getCtx(),
								line.getM_Locator_ID(), line.getM_Product_ID(),
								movLineMA.getM_AttributeSetInstance_ID(),
								get_TrxName());
					}
					// Localizador solicita
					MStorage storageTo = MStorage.get(getCtx(),
							line.getM_LocatorTo_ID(), line.getM_Product_ID(),
							movLineMA.getM_AttributeSetInstance_ID(), get_TrxName());
					if (storageTo == null){
						storageTo = MStorage.getCreate(getCtx(),
								line.getM_LocatorTo_ID(),
								line.getM_Product_ID(),
								movLineMA.getM_AttributeSetInstance_ID(),
								get_TrxName());
					}

					// Noelia:Si un almacen se solicita y surte a si mismo no es
					// necesario (CEYE)
					//if (!(storageFrom.getM_Locator_ID() == storageTo.getM_Locator_ID())) {
					MMovementLineConfirm mlc = MMovementLineConfirm.getFromMovLine(getCtx(),line.getM_MovementLine_ID(), get_TrxName() );

					BigDecimal dif = BigDecimal.ZERO;
					BigDecimal difTotal = line.getTargetQty();

					//Remover la diferencia de cantidades, en el proceso de salida al gasto remuve la cant a la mano descartada
					if(mlc!=null){
						dif = mlc.getScrappedQty();
						if(MConfigEC.get(getCtx()).isCargaDiferAlm()) {
							// Al almacen que surte
							dif = mlc.getDifferenceQty();
							difTotal = line.getTargetQty().subtract(dif.abs());
						}
					}

					/*Remover la cantidad reservada y disminuir de la cant a la mano la cant solicitada*/
					storageFrom.setQtyOnHand(storageFrom.getQtyOnHand().subtract(difTotal));
					storageFrom.setQtyReserved(storageFrom.getQtyReserved().subtract(dif.abs()));

					if(MLocator.getWarehouse(getCtx(), line.getM_Locator_ID(), null).isControlExistencias()){
						if (!storageFrom.save(get_TrxName())) {
							mProcessMsg = "Storage From not updated (MA)";
							return DocAction.STATUS_Invalid;
						}
					}

					//
					storageTo.setQtyOnHand(storageTo.getQtyOnHand().add(movLineMA.getMovementQty()));

					if(MLocator.getWarehouse(getCtx(), line.getM_LocatorTo_ID(), null).isControlExistencias()){
						if (!storageTo.save(get_TrxName())) {
							mProcessMsg = "Storage To not updated (MA)";
							return DocAction.STATUS_Invalid;
						}
					}
					//}// Fin Noelia

					/*
					 * Expert:Hassan - Registramos una transaccion por cada
					 * Esquema contable del Cliente (Bloque Modificado)
					 */
					final MAcctSchema[] acctss = MAcctSchema
							.getClientAcctSchema(getCtx(), getAD_Client_ID());
					for (int h = 0; h < acctss.length; h++) {
						final MAcctSchema acctschema = acctss[h];
						// Create Transaction

						// Create Transaction
						trxFrom = new MTransaction(getCtx(),
								line.getProduct(),
								movLineMA.getM_AttributeSetInstance_ID(), acctschema,
								line.getAD_Org_ID(), line.getProduct().getCostingMethod(acctschema), movLineMA
										.getMovementQty().negate(), 0, false,
										MTransaction.MOVEMENTTYPE_MovementFrom,
								line.getM_Locator_ID(), getMovementDate(),
								get_TrxName());// Expert:Hassan - Se pasa el
												// esquema contable

						trxFrom.setM_MovementLine_ID(line
								.getM_MovementLine_ID());
						if (!trxFrom.save()) {
							mProcessMsg = "Could not create Material Transaction (MA)";
							return DocAction.STATUS_Invalid;
						}
						mtrxs.add(trxFrom);
					}/* Expert:Hassan - Fin del Bloque */


					/*
					 * Expert:Hassan - Registramos una transaccion por cada
					 * Esquema contable del Cliente (Bloque Modificado)
					 */
					for (int h = 0; h < acctss.length; h++) {
						final MAcctSchema mAcctSc = acctss[h];

						// Create Transaction
						final MTransaction trxTo = new MTransaction(
								getCtx(), line.getProduct(),
								movLineMA.getM_AttributeSetInstance_ID(), mAcctSc,
								line.getAD_Org_ID(), line.getProduct().getCostingMethod(mAcctSc),
								movLineMA.getMovementQty(), 0, false,
								MTransaction.MOVEMENTTYPE_MovementTo,
								line.getM_LocatorTo_ID(), getMovementDate(),
								get_TrxName());// Expert:Hassan - Se pasa el
												// esquema contable

						trxTo.setM_MovementLine_ID(line.getM_MovementLine_ID());
						// expert : gisela lee : el usuario es el que recibe
						trxTo.setUpdatedBy(getCreatedBy());
						if (!trxTo.save()) {
							mProcessMsg = "Could not create Material Transaction (MA)";
							return DocAction.STATUS_Invalid;
						}
						mtrxs.add(trxTo);
					}/* Expert:Hassan - Fin del Bloque */

				}
			}
			// Fallback - We have ASI
			if (trxFrom == null) {
				MStorage storageFrom = MStorage.get(getCtx(),
						line.getM_Locator_ID(), line.getM_Product_ID(),
						line.getM_AttributeSetInstance_ID(), get_TrxName());
				if (storageFrom == null) {
					storageFrom = MStorage.getCreate(getCtx(),
							line.getM_Locator_ID(), line.getM_Product_ID(),
							line.getM_AttributeSetInstance_ID(), get_TrxName());
				}
				//
				MStorage storageTo = MStorage.get(getCtx(),
						line.getM_LocatorTo_ID(), line.getM_Product_ID(),
						line.getM_AttributeSetInstanceTo_ID(), get_TrxName());
				if (storageTo == null) {
					storageTo = MStorage.getCreate(getCtx(),
							line.getM_LocatorTo_ID(), line.getM_Product_ID(),
							line.getM_AttributeSetInstanceTo_ID(),
							get_TrxName());
				}

				BigDecimal dif = BigDecimal.ZERO;
				BigDecimal difTotal = line.getTargetQty();

				//Remover la diferencia de cantidades, en el proceso de salida al gasto remuve la cant a la mano descartada
				MMovementLineConfirm mlc = MMovementLineConfirm.getFromMovLine(getCtx(),line.getM_MovementLine_ID(), get_TrxName() );
				if(mlc!=null){
					dif = mlc.getScrappedQty();
					if(MConfigEC.get(getCtx()).isCargaDiferAlm()) {
						// Al almacen que surte
						dif = mlc.getDifferenceQty();
						difTotal = line.getTargetQty().subtract(dif.abs());
					}
				}

				/*Remover la cantidad reservada y disminuir de la cant a la mano la cant solicitada*/
				storageFrom.setQtyOnHand(storageFrom.getQtyOnHand().subtract(difTotal));
				storageFrom.setQtyReserved(storageFrom.getQtyReserved().subtract(dif.abs()));
//				storageFrom.setQtyOnHand(storageFrom.getQtyOnHand().subtract(line.getMovementQty()));
				if (!storageFrom.save(get_TrxName())) {
					mProcessMsg = "Storage From not updated";
					return DocAction.STATUS_Invalid;
				}
				//
				storageTo.setQtyOnHand(storageTo.getQtyOnHand().add(line.getMovementQty()));

//				storageTo.setQtyOnHand(storageTo.getQtyOnHand().add(line.getMovementQty()));
				if (!storageTo.save(get_TrxName())) {
					mProcessMsg = "Storage To not updated";
					return DocAction.STATUS_Invalid;
				}


				/*
				 * Expert:Hassan - Registramos una transaccion por cada Esquema
				 * contable del Cliente (Bloque Modificado)
				 */
				final MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(
						getCtx(), getAD_Client_ID());
				for (int h = 0; h < acctss.length; h++) {
					final MAcctSchema as = acctss[h];

					// Create Transaction
					trxFrom = new MTransaction(getCtx(), line.getProduct(),
							line.getM_AttributeSetInstance_ID(), as,
							line.getAD_Org_ID(), line.getProduct().getCostingMethod(as), line
									.getMovementQty().negate(), 0, false,
									MTransaction.MOVEMENTTYPE_MovementFrom,
							line.getM_Locator_ID(), getMovementDate(),
							get_TrxName());// Expert:Hassan - Se pasa el esquema
											// contable

					trxFrom.setM_MovementLine_ID(line.getM_MovementLine_ID());
					if (!trxFrom.save()) {
						mProcessMsg = "Could not create Material Transaction (MA)";
						return DocAction.STATUS_Invalid;
					}
					mtrxs.add(trxFrom);
				}/* Expert:Hassan - Fin del Bloque */


				/*
				 * Expert:Hassan - Registramos una transaccion por cada Esquema
				 * contable del Cliente (Bloque Modificado)
				 */
				for (int h = 0; h < acctss.length; h++) {
					final MAcctSchema acctSch = acctss[h];

					// Create Transaction
					final MTransaction trxTo = new MTransaction(
							getCtx(), line.getProduct(),
							line.getM_AttributeSetInstance_ID(), acctSch,
							line.getAD_Org_ID(), line.getProduct().getCostingMethod(acctSch),
							line.getMovementQty(), 0, false,
							MTransaction.MOVEMENTTYPE_MovementTo,
							line.getM_LocatorTo_ID(), getMovementDate(),
							get_TrxName());// Expert:Hassan - Se pasa el esquema
											// contable

					trxTo.setM_MovementLine_ID(line.getM_MovementLine_ID());
					// expert : gisela lee : el usuario es el que recibe
					trxTo.setUpdatedBy(getCreatedBy());
					if (!trxTo.save()) {
						mProcessMsg = "Could not create Material Transaction (MA)";
						return DocAction.STATUS_Invalid;
					}
					mtrxs.add(trxTo);
				}/* Expert:Hassan - Fin del Bloque */

			} // Fallback

			// Expert: eruiz: Actualizamos FactorPre si es un movimiento de
			// surtido de un almacen en consigna
			// Tambien actualizamos M_Product_Costing
//			if (!isDevol()) {
//				final MLocator loc = new MLocator(getCtx(),
//						line.getM_Locator_ID(), null);
//				mAlmSurt = new MWarehouse(getCtx(), loc.getM_Warehouse_ID(),
//						null);
//				if (getMAlmSurt().isConsigna()) { //TODO: Aplica este codigo de consigna??
//					try {
//						final MConfigPre configPre = MConfigPre.get(getCtx(),
//								get_TrxName());
//						if (configPre != null) {
//							// Actualizamos M_ProductCosting.
//							StringBuilder sql = new StringBuilder(
//									Constantes.INIT_CAPACITY_ARRAY);
//
//							final int M_Product_ID = line.getM_Product_ID();
//							// Obtenemos los precios de la linea del movimiento
//							final BigDecimal priceListPP = line.getPriceList();
//							final BigDecimal priceActualPP = line
//									.getPriceList();
//
//							final MProduct prod = new MProduct(getCtx(),
//									line.getM_Product_ID(), null);
//
//							final BigDecimal priceList = MEXMEUOMConversion
//									.convertProductTo(getCtx(),
//											line.getM_Product_ID(),
//											prod.getC_UOM_ID(), priceListPP,
//											null);
//							final BigDecimal priceActual = MEXMEUOMConversion
//									.convertProductTo(getCtx(), M_Product_ID,
//											prod.getC_UOM_ID(), priceActualPP,
//											null);
//
//							// Si permite bajar los precios.
//							if (configPre.isBajarPrecioVta()) {
//								// Guardamos los precios tal y como esten.
//								sql.append(
//										"UPDATE M_Product_Costing pc "
//												+ "SET pc.PriceList = ")
//										.append(priceList.doubleValue())
//										.append(" ,pc.PriceActual = ")
//										.append(priceActual.doubleValue())
//										.append(" WHERE pc.C_AcctSchema_ID = ")
//										.append(getCtx().getProperty(
//												"$C_AcctSchema_ID"))
//										.append(" AND pc.M_Product_ID = ")
//										.append(M_Product_ID);
//
//								final int num = DB.executeUpdate(sql.toString(),
//										get_TrxName());
//								log.log(Level.INFO,
//										"M_Product_Costing - Updated PriceList & PriceActual, Registros Actualizados="
//												+ num);
//							} else {
//								// Guardamos los precios (costos) mas altos.
//
//								// Actualizamos PriceList siempre y cuando el
//								// nuevo precio venga mayor al actual.
//								sql.append(
//										"UPDATE M_Product_Costing pc "
//												+ "SET pc.PriceList = ")
//										.append(priceList.doubleValue())
//										.append(" WHERE pc.C_AcctSchema_ID = ")
//										.append(getCtx().getProperty(
//												"$C_AcctSchema_ID"))
//										.append(" AND pc.M_Product_ID = ")
//										.append(M_Product_ID)
//										.append(" AND pc.PriceList < ")
//										.append(priceList.doubleValue());
//
//								int num = DB.executeUpdate(sql.toString(),
//										get_TrxName());
//								log.log(Level.INFO,
//										"M_Product_Costing - Updated PriceList, Registros Actualizados="
//												+ num);
//
//								// Actualizamos PricepriceActual siempre y
//								// cuando el nuevo precio venga mayor al actual.
//								sql = new StringBuilder(
//										"UPDATE M_Product_Costing pc "
//												+ "SET pc.PriceActual = ")
//										.append(priceActual.doubleValue())
//										.append(" WHERE pc.C_AcctSchema_ID = ")
//										.append(getCtx().getProperty(
//												"$C_AcctSchema_ID"))
//										.append(" AND pc.M_Product_ID = ")
//										.append(M_Product_ID)
//										.append(" AND pc.PriceActual < ")
//										.append(priceActual.doubleValue());
//
//								num = DB.executeUpdate(sql.toString(),
//										get_TrxName());
//								log.log(Level.INFO,
//										"M_Product_Costing - Updated PriceActual, Registros Actualizados="
//												+ num);
//							}
//						}
//					} catch (Exception e) {
//						log.severe("Exception " + e);
//					}
//				}// Fin actualizaci�n de FactorPre, eruiz
//			}
		} // for all lines
		// User Validation
		final String valid = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null) {
			mProcessMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		//
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	} // completeIt

	/**
	 * Check Material Policy Sets line ASI
	 */
	private void checkMaterialPolicy() {
		final int no = MMovementLineMA.deleteMovementMA(getM_Movement_ID(),
				get_TrxName());
		if (no > 0) {
			log.config("Delete old #" + no);
		}
		final MMovementLine[] lines = getLines(false);

		final MClient client = MClient.get(getCtx());

		// Check Lines
		for (int i = 0; i < lines.length; i++) {
			final MMovementLine line = lines[i];
			boolean needSave = false;

			// Attribute Set Instance
			if (line.getM_AttributeSetInstance_ID() == 0) {
				final MProduct product = MProduct.get(getCtx(),
						line.getM_Product_ID());
				final MProductCategory pc = MProductCategory.get(getCtx(),
						product.getM_Product_Category_ID());
				String MMPolicy = pc.getMMPolicy();
				if (MMPolicy == null || MMPolicy.length() == 0){
					MMPolicy = client.getMMPolicy();
				}
				//
				final MStorage[] storages = new MStorage[]{};
//						MStorage.getAllWithASI(getCtx(),
//						line.getM_Product_ID(), line.getM_Locator_ID(),
//						MClient.MMPOLICY_FiFo.equals(MMPolicy), get_TrxName());
				BigDecimal qtyToDeliver = line.getMovementQty();
				for (int ii = 0; ii < storages.length; ii++) {
					final MStorage storage = storages[ii];
					if (ii == 0) {
						if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0) {
							line.setM_AttributeSetInstance_ID(storage
									.getM_AttributeSetInstance_ID());
							needSave = true;
							log.config("Direct - " + line);
							qtyToDeliver = BigDecimal.ZERO;
						} else {
							log.config("Split - " + line);
							final MMovementLineMA ma = new MMovementLineMA(
									line,
									storage.getM_AttributeSetInstance_ID(),
									storage.getQtyOnHand());
							if (!ma.save()){
								log.fine("##: " + ma);
							}
							qtyToDeliver = qtyToDeliver.subtract(storage
									.getQtyOnHand());
							log.fine("#" + ii + ": " + ma + ", QtyToDeliver="
									+ qtyToDeliver);
						}
					} else // create addl material allocation
					{
						final MMovementLineMA ma = new MMovementLineMA(line,
								storage.getM_AttributeSetInstance_ID(),
								qtyToDeliver);
						if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0){
							qtyToDeliver = BigDecimal.ZERO;
						}else {
							ma.setMovementQty(storage.getQtyOnHand());
							qtyToDeliver = qtyToDeliver.subtract(storage
									.getQtyOnHand());
						}
						if (!ma.save()){
							log.fine("##: " + ma);
						}
						log.fine("#" + ii + ": " + ma + ", QtyToDeliver="
								+ qtyToDeliver);
					}
					if (qtyToDeliver.signum() == 0){
						break;
					}
				} // for all storages

				// No AttributeSetInstance found for remainder
				if (qtyToDeliver.signum() != 0) {
					final MMovementLineMA ma = new MMovementLineMA(line, 0,
							qtyToDeliver);
					if (!ma.save()){
						log.fine("##: " + ma);
					}
					log.fine("##: " + ma);
				}
			} // attributeSetInstance
//			else {
//				final MAttributeSetInstance asi = new MAttributeSetInstance(getCtx(), line.getM_AttributeSetInstance_ID(), get_TrxName());
//				if (asi != null) {
//					final MProduct product = MProduct.get(getCtx(),
//							line.getM_Product_ID());
//					if (product.isLot()) {
//						asi.setM_AttributeSet_ID(0);
//						final String palabra = line.getLotInfo();
//						if (palabra != null) {
//							final String arr[] = MEXMELot
//									.getLotInformationArr(palabra);
//							asi.setLot(arr[0]);
//							asi.setDescription(arr[1]);
//							if (arr[2] != null && arr[2].length() > 0) {
//								try {
//									final Date fecha = Constantes.getSdfFecha()
//											.parse(arr[2]);
//									asi.setGuaranteeDate(new Timestamp(fecha
//											.getTime()));
//								} catch (ParseException e) {
////									e.printStackTrace();
//									log.severe("ParseException " + e);
//								}
//							}
//						}
//						if (!asi.save()) {
//							log.severe("NOT saved " + asi);
//						}
//					}
//				}
//			}

			if (needSave && !line.save()) {
				log.severe("NOT saved " + line);
			}
		} // for all lines

	} // checkMaterialPolicy

	/**
	 * Void Document.
	 *
	 * @return true if success
	 */
	public boolean voidIt() {
		log.info(toString());
//		if (DOCSTATUS_Closed.equals(getDocStatus())
//				|| DOCSTATUS_Reversed.equals(getDocStatus())
//				|| DOCSTATUS_Voided.equals(getDocStatus())) {
//			mProcessMsg = "Document Closed: " + getDocStatus();
//			return false;
//		}

		if (!DOCSTATUS_Drafted.equals(getDocStatus())){
			mProcessMsg = "Document Closed: " + getDocStatus();
			return false;
		}

		// Not Processed
//		if (DOCSTATUS_Drafted.equals(getDocStatus())
//				|| DOCSTATUS_Invalid.equals(getDocStatus())
//				|| DOCSTATUS_InProgress.equals(getDocStatus())
//				|| DOCSTATUS_Approved.equals(getDocStatus())
//				|| DOCSTATUS_NotApproved.equals(getDocStatus())) {
			// Set lines to 0
			final MMovementLine[] lines = getLines(false);
			for (int i = 0; i < lines.length; i++) {
				final MMovementLine line = lines[i];
				final BigDecimal old = line.getMovementQty();
				if (old.compareTo(BigDecimal.ZERO) != 0) {
					line.setMovementQty(BigDecimal.ZERO);
					line.addDescription("Void (" + old + ")");
					line.save(get_TrxName());
				}
			}
//		} else {
//			return reverseCorrectIt();
//		}

		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	} // voidIt

	/**
	 * Close Document.
	 *
	 * @return true if success
	 */
	public boolean closeIt() {
		log.info(toString());

		// Close Not delivered Qty
		setDocAction(DOCACTION_None);
		return true;
	} // closeIt

	/**
	 * Reverse Correction
	 *
	 * @return false
	 */

	public boolean reverseCorrectIt() {
		log.info(toString());

		if (!DOCSTATUS_Drafted.equals(getDocStatus())){
			mProcessMsg = "Document Closed: " + getDocStatus();
			return false;
		}

		final MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (!MPeriod.isOpen(getCtx(), getMovementDate(), dt.getDocBaseType(),
				Env.getAD_Org_ID(getCtx()))) {
			mProcessMsg = "@PeriodClosed@";
			return false;
		}

		// Deep Copy
		final MMovement reversal = new MMovement(getCtx(), 0, get_TrxName());
		copyValues(this, reversal, getAD_Client_ID(), getAD_Org_ID());
		reversal.setDocStatus(DOCSTATUS_Drafted);
		reversal.setDocAction(DOCACTION_Complete);
		reversal.setIsApproved(false);
		reversal.setIsInTransit(false);
		reversal.setPosted(false);
		reversal.setProcessed(false);
		reversal.addDescription("{->" + getDocumentNo() + ")");
		if (!reversal.save()) {
			mProcessMsg = "Could not create Movement Reversal";
			return false;
		}

		// Reverse Line Qty
		final MMovementLine[] oLines = getLines(true);
		for (int i = 0; i < oLines.length; i++) {
			final MMovementLine oLine = oLines[i];
			final MMovementLine rLine = new MMovementLine(getCtx(), 0,
					get_TrxName());
			copyValues(oLine, rLine, oLine.getAD_Client_ID(),
					oLine.getAD_Org_ID());
			rLine.setM_Movement_ID(reversal.getM_Movement_ID());
			//
			rLine.setMovementQty(rLine.getMovementQty().negate());
			rLine.setTargetQty(BigDecimal.ZERO);
			rLine.setScrappedQty(BigDecimal.ZERO);
			rLine.setConfirmedQty(BigDecimal.ZERO);
			rLine.setProcessed(false);
			if (!rLine.save()) {
				mProcessMsg = "Could not create Movement Reversal Line";
				return false;
			}
		}
		//
		if (!reversal.processIt(DocAction.ACTION_Complete)) {
			mProcessMsg = "Reversal ERROR: " + reversal.getProcessMsg();
			return false;
		}
		reversal.closeIt();
		reversal.setDocStatus(DOCSTATUS_Reversed);
		reversal.setDocAction(DOCACTION_None);
		reversal.save();
		mProcessMsg = reversal.getDocumentNo();

		// Update Reversed (this)
		addDescription("(" + reversal.getDocumentNo() + "<-)");
		setProcessed(true);
		setDocStatus(DOCSTATUS_Reversed); // may come from void
		setDocAction(DOCACTION_None);

		return true;
	} // reverseCorrectionIt

	/**
	 * Reverse Accrual - none
	 *
	 * @return false
	 */
	public boolean reverseAccrualIt() {
		log.info(toString());
		return false;
	} // reverseAccrualIt

	/**
	 * Re-activate
	 *
	 * @return false
	 */
	public boolean reActivateIt() {
		log.info(toString());
		return false;
	} // reActivateIt

	/*************************************************************************
	 * Get Summary
	 *
	 * @return Summary of Document
	 */
	public String getSummary() {
		final StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
		// : Total Lines = 123.00 (#1)
		sb.append(": ").append(Msg.translate(getCtx(), "ApprovalAmt"))
				.append("=").append(getApprovalAmt()).append(" (#")
				.append(getLines(false).length).append(")");
		// - Description
		if (getDescription() != null && getDescription().length() > 0) {
			sb.append(" - ").append(getDescription());
		}
		return sb.toString();
	} // getSummary

	/** @return requested by and requested to warehouses .- Lama Card #1173 */
	public String getDischargeDescription() {
		final StringBuilder sb = new StringBuilder();
		// Ticket #07692 - error de etiquetas
		if(isDevol()) {
			// almacen que devuelve
			if (getWarehouse() != null) {
				sb.append("<b>").append(Utilerias.getMsg(getCtx(), "reportes.lstWarehouseIni2")).append("</b>: ");
				sb.append(getWarehouse().getName()).append(". ");
			}
			// almacen que recibe
			if (getWarehouseTo() != null) {
				sb.append("<b>").append(Utilerias.getMsg(getCtx(), "reportes.lstWarehouseFin")).append("</b>: ");
				sb.append(getWarehouseTo().getName()).append(".");
			}
		} else {
			// solicitado por (almacen)
			if (getWarehouseTo() != null) {
				sb.append("<b>").append(Utilerias.getMsg(getCtx(), "msj.requestedFrom")).append("</b>: ");
				sb.append(getWarehouseTo().getName()).append(". ");
			}
			// almacen de surtido
			if (getWarehouse() != null) {
				sb.append("<b>").append(getMsgTranslate("AlmSurt")).append("</b>: ");
				sb.append(getWarehouse().getName()).append(".");
			}
		}
		
		// creado por
		if(sb.length()>0) {
			sb.append("<br>");
		}
		sb.append("<b>").append(getMsgTranslate("CreatedBy")).append("</b>: ");
		sb.append(MUser.getUserName(getCtx(), getSoli_User_ID())).append(". ");
		MUser.getUserName(getCtx(), getCreatedBy());
		if (StringUtils.isNotBlank(getDescription())) {
			sb.append("<b>").append(getMsgTranslate("Description")).append(":</b> ");
			sb.append(getDescription());
		}
		// se crea el registro cuando se surte -
		final MMovementConfirm confirm = MMovementConfirm.get(getCtx(), getM_Movement_ID(), get_TrxName());
		if (confirm != null && !DOCSTATUS_Voided.equals(confirm.getDocStatus())) {
			if(DOCSTATUS_Completed.equals(confirm.getDocStatus())) {
				sb.append("<b>").append(getMsgTranslate("FechaConfirm")).append("</b>: ");
				sb.append(Constantes.getSDFDateTime(getCtx()).formatConvert(confirm.getCreated())).append(".<br>");
			} else {
				sb.append("<br>>>").append(Utilerias.getMsg(getCtx(), "pacExt.lst.drafted")).append(".");
			}
		}
		return sb.toString();
	} // getDischargeDescription

	/**
	 * Get Process Message
	 *
	 * @return clear text error message
	 */
	public String getProcessMsg() {
		return mProcessMsg;
	} // getProcessMsg

	/**
	 * Get Document Owner (Responsible)
	 *
	 * @return AD_User_ID
	 */
	public int getDoc_User_ID() {
		return getCreatedBy();
	} // getDoc_User_ID

	/**
	 * Get Document Currency
	 *
	 * @return C_Currency_ID
	 */
	public int getC_Currency_ID() {
		// MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
		// return pl.getC_Currency_ID();
		return 0;
	} // getC_Currency_ID

	// GValdez se agrego un tercer parametro al metodo getDetal de MMovement
	// para controlar los detalles a devolver
	// dependiendo del entorno en el que se mande llamar el metodo
	// Por MedSys (true) por Interfaz entre Almacenes Virtuales(false)
	/**
	 * Obtenemos el detalle del movimiento.
	 *
	 * @param ctx
	 * @param M_Movement_ID
	 * @param isWeb
	 * @param trxName
	 * @return
	 * @throws SQLException
	 * @deprecated
	 */
	public static ArrayList<MMovementLine> getDetail(final Properties ctx,
			final int M_Movement_ID, final String restriccion,
			final boolean isWeb, final String trxName) throws SQLException {
		final StringBuffer sql = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MMovementLine> lstMov = null;

		try {

			sql.append("SELECT l.*, ").append(
					"p.Value, p.Name, p.M_Product_ID, u.Name Udm, u.C_UOM_ID ");
			// GValdez Si el metodo es llamado por interfaz agregamos los campos
			// necesarios al select
			((!isWeb) ? sql
					.append(", NVL(SS.Cantidad,0) cantidad, SS.Lot, SS.GuaranteeDate ")
					: sql.append(""))
					.append("FROM M_MovementLine l ")
					.append("INNER JOIN M_Product p ON l.M_Product_ID = p.M_Product_ID ")
					.append("INNER JOIN C_Uom u ON p.C_Uom_ID = u.C_Uom_ID ");
			// GValdez Si el metodo es llamado por interfaz agregamos la tabla
			// de interfaz
			if (isWeb) {
				sql.append(" ");
			} else {

				sql.append("INNER JOIN M_Movement MM on MM.M_MOVEMENT_ID = l.m_movement_id ")
				.append("INNER JOIN SM_Surtido SS ON SS.DocumentNo = MM.DocumentNo  ")
				.append("		AND SS.uom_value = u.name ");
			}

			sql.append("WHERE l.M_Movement_ID = ? AND ").append(
					"l.IsActive = 'Y' AND p.IsActive = 'Y' ");

			if (restriccion != null && restriccion.trim().length() > 0) {
				sql.append(restriccion);
			}

			sql.append(" ORDER BY l.Line");

			slog.finer("getDetail : " + sql);

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_Movement_ID);

			rset = pstmt.executeQuery();

			lstMov = new ArrayList<MMovementLine>();

			while (rset.next()) {
				final MMovementLine ml = new MMovementLine(ctx, rset.getInt("M_MovementLine_ID"), trxName);
				ml.setM_Product_ID(rset.getInt("M_Product_ID"));

				final MUOM uom = new MUOM(ctx, rset.getInt("C_UOM_ID"), trxName);
				ml.setUom(uom);

				// eruiz, asignamos un precio a el producto si surte un almacen
				// en consigna
				// FIXME: almacenes en consigna Medsys Aplica???
//				final MLocator loc = new MLocator(ctx, ml.getM_Locator_ID(), null);
//				final MWarehouse alm = new MWarehouse(ctx, loc.getM_Warehouse_ID(), null);
				if (ml.getMovement().getMAlmSurt().isConsigna()) { // Aplica???
					ml.setPrice(getProductPrice(ctx, ml.getMovement().getMAlmSurt().getC_BPartner_ID(), ml.getM_Product_ID()));
				}
				// GValdez Si el metodo es llamado por interfaz agregamos los
				// campos necesarios al MMovementLine
				// Y el targetQty pasa a ser lo especificado en interfaz
				if (isWeb) {
					// Pq le ponia la OriginalQty a el TargetQty? Jesus Cantu
					// ml.setTargetQty(ml.getOriginalQty());
					// Se corrige por RQM 2176.
					ml.setTargetQty(rset.getBigDecimal("TargetQty"));
				} else {
					// ml.setLote(rs.getString("Lot"));
					ml.setGuaranteeDate(rset.getString("GuaranteeDate"));
					ml.setTargetQty((rset.getBigDecimal("Cantidad").compareTo(BigDecimal.ZERO) > 0 ? rset.getBigDecimal("Cantidad") : BigDecimal.ZERO));
				}

				final MProduct p = new MProduct(ctx, ml.getM_Product_ID(), null);
				if (p.isLot()) {
					ml.setLote(true);
				}

				lstMov.add(ml);
			}

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "getDetail (" + sql + ")", e);
			throw e;
		} finally {
			DB.close(rset, pstmt);
		}

		return lstMov;
	}

	/**
	 * Obtenemos el encabezado de los movimientos pendientes de surtir
	 *
	 * @param movementID
	 *            el movimiento pendiente
	 * @return Un resultset con los movimientos pendientes de surtir
	 * @deprecated
	 * se comenta porque en las referencias no se utilizan mAlmSolic y mAlmSurt
	 * se cambia por el constructor
	 */
	public static MMovement get(final Properties ctx, final int movementID,
			final String trxName) throws SQLException {
//		// movimientos por id de movimiento
//		final StringBuilder sql = new StringBuilder(
//				"SELECT M_Movement.*, a.M_Warehouse_ID as almSol, M_Movement.soli_user_id, ")
//				.append("surte.M_Warehouse_ID AS almSurt ")
//				.append("FROM M_Movement ")
//				.append("INNER JOIN M_MovementLine l ON M_Movement.M_Movement_ID = l.M_Movement_ID ")
//				.append("INNER JOIN M_Locator ll ON l.M_Locator_ID = ll.M_Locator_ID ")
//				// surte
//				.append("INNER JOIN M_Locator ls ON l.M_LocatorTo_ID = ls.M_Locator_ID ")
//				// solicitante
//				.append("INNER JOIN M_Warehouse a ON ls.M_Warehouse_ID = a.M_Warehouse_ID ")
//				.append("INNER JOIN M_Warehouse surte ON ll.M_Warehouse_ID = surte.M_Warehouse_ID ")
//				.append("WHERE M_Movement.M_Movement_ID = ? AND ")
//				.append("M_Movement.IsActive = 'Y'")
//				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
//						"M_Movement"));
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		MMovement mov = null;
//
//		try {
//			// Echense otro de estos
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, movementID);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				mov = new MMovement(ctx, rs, trxName);
//				mov.mAlmSolic = new MWarehouse(ctx, rs.getInt("almSol"), trxName);
//				mov.mAlmSurt = new MWarehouse(ctx, rs.getInt("almSurt"), trxName);
////				mov.mUsuario = new MUser(ctx, rs.getInt("soli_user_id"), trxName);
////				if (mov.getEXME_CtaPac_ID() > 0) {
////					mov.setCtaPac(new MEXMECtaPac(ctx, mov.getEXME_CtaPac_ID(), trxName));
////				}
//			}
//		} catch (SQLException e) {
//			slog.log(Level.SEVERE, sql + "   movementID: " + movementID, e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
		return new MMovement(ctx, movementID, trxName);
	}

	/** @deprecated use {@link #getWarehouse()} */
	public MWarehouse getMAlmSolic() {
//		if(mAlmSolic==null){
//			getMEXMEMov();
//		} //Lama
//		if(mAlmSolic == null) {
//			final StringBuilder joins = new StringBuilder();
//			joins.append("INNER JOIN M_Locator locator ON locator.M_Warehouse_ID = M_Warehouse.M_Warehouse_ID ");
//			joins.append("INNER JOIN M_MovementLine movline ON movline.M_Locator_ID = locator.M_Locator_ID ");
//
//			mAlmSolic = new Query(getCtx(), MWarehouse.Table_Name, " movline.M_Movement_ID=? ", get_TrxName())//
//			.setJoins(joins)//
//			.setParameters(getM_Movement_ID())//
//			.setOnlyActiveRecords(true)//
//			.addAccessLevelSQL(true)//
//			.first();
//		}
		return getWarehouse();
	}

	/** @deprecated use {@link #getWarehouseTo()} */
	public MWarehouse getMAlmSurt() {
//		if(mAlmSurt==null){
//			getMEXMEMov();
//		} //Lama
//		if(mAlmSurt == null) {
//			final StringBuilder joins = new StringBuilder();
//			joins.append("INNER JOIN M_Locator locator ON locator.M_Warehouse_ID = M_Warehouse.M_Warehouse_ID ");
//			joins.append("INNER JOIN M_MovementLine movline ON movline.M_LocatorTo_ID = locator.M_Locator_ID ");
//
//			mAlmSurt = new Query(getCtx(), MWarehouse.Table_Name, " movline.M_Movement_ID=? ", get_TrxName())//
//			.setJoins(joins)//
//			.setParameters(getM_Movement_ID())//
//			.setOnlyActiveRecords(true)//
//			.addAccessLevelSQL(true)//
//			.first();
//		}
		return getWarehouseTo();//mAlmSurt;
	}

//	/**
//	 * Obtenemos una lista con los movimientos pendientes de surtir
//	 *
//	 * @param ctx
//	 * @param movementID
//	 *            El movimiento pendiente
//	 * @param ready
//	 *            Movimientos listos
//	 * @return Un resultset con los movimientos pendientes de surtir
//	 * @deprecated
//	 */
//	public static List<MMovement> getList(final Properties ctx, final int warehouseID,
//			final int ready, final String trxName) throws SQLException {
//		// Movimientos por id de movimiento y si estan o no listos
//		final StringBuilder sql = new StringBuilder();
//		sql.append("SELECT DISTINCT(m.M_Movement_ID), m.* ");//, a.M_Warehouse_ID as almSol, m.Soli_User_ID, ");
////		sql.append("surte.M_Warehouse_ID AS almSurt ");
//		sql.append("FROM M_Movement m ");
//		sql.append("INNER JOIN M_MovementLine l ON m.M_Movement_ID = l.M_Movement_ID ");
//		sql.append("INNER JOIN M_Locator ll ON l.M_Locator_ID = ll.M_Locator_ID "); // solicitante
////		sql.append("INNER JOIN M_Locator ls ON l.M_LocatorTo_ID = ls.M_Locator_ID "); // surte
////		sql.append("INNER JOIN M_Warehouse a ON ls.M_Warehouse_ID = a.M_Warehouse_ID ");
////		sql.append("INNER JOIN M_Warehouse surte ON ll.M_Warehouse_ID = surte.M_Warehouse_ID ");
//		sql.append("WHERE ll.M_Warehouse_ID = ? ");
//		sql.append("AND m.DocStatus = ? ");//'" + MMovement.STATUS_Drafted + "' ");
////		sql.append("AND m.AD_Client_ID = ? ");
////		sql.append("AND m.AD_Org_ID = ? ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "m"));
//		sql.append("AND m.IsActive = 'Y' ");
//		if (ready == 1) {
//			sql.append("AND m.Ready = 'Y' ");
//		} else if (ready == 2) {
//			sql.append("AND m.Ready = 'N' ");
//		}
//		sql.append("ORDER BY m.Created DESC");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<MMovement> lst = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, warehouseID);
//			pstmt.setString(2, MMovement.STATUS_Drafted);
////			pstmt.setInt(3, Env.getAD_Client_ID(ctx));
////			pstmt.setInt(4, Env.getAD_Org_ID(ctx));
//			rs = pstmt.executeQuery();
//
//			lst = new ArrayList<MMovement>();
//
//			while (rs.next()) {
//				final MMovement mov = new MMovement(ctx, rs, trxName);
////				mov.mAlmSolic = new MWarehouse(ctx, rs.getInt("almSol"), trxName);
////				mov.mAlmSurt = new MWarehouse(ctx, rs.getInt("almSurt"), trxName);
////				mov.mUsuario = new MUser(ctx, rs.getInt("soli_user_id"), trxName);
////				mov.isReady = rs.getString("ready").equals('Y') ? true : false;
//
////				if (mov.getEXME_CtaPac_ID() > 0) {
////					mov.setCtaPac(new MEXMECtaPac(ctx, mov.getEXME_CtaPac_ID(), trxName));
////				}
////				if (mov.getCtaPac() != null && mov.getCtaPac().get_ID() != 0) {
////					mov.setReleaseStatus(mov.getCtaPac().getEncounterStatus());
////				} else {
////					mov.setReleaseStatus("");
////				}
//				lst.add(mov);
//			}
//		} catch (SQLException e) {
//			slog.log(Level.SEVERE, "get : ", e);
//			slog.info(sql + "   movementID: " + warehouseID
//					+ ", AD_Client_ID: " + Env.getAD_Client_ID(ctx)
//					+ ", AD_Org_ID: " + Env.getAD_Org_ID(ctx) + "\n\n");
//			lst = null;
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lst;
//	}

//	/**
//	 * Obtenemos una lista con los movimientos pendientes de surtir
//	 *
//	 * @param ctx
//	 * @param warehouseID
//	 *            El almacen que surte esa solicitud
//	 * @param ready
//	 *            Movimientos listos
//	 * @return Un resultset con los movimientos pendientes de surtir
//	 * @deprecated
//	 */
//	public static List<MMovement> getMovements(final Properties ctx, final int warehouseID,
//			final String status, final String trxName) throws SQLException {
//
//		return MMovement.getMovements(ctx, warehouseID, status, X_M_Warehouse.TYPE_Sterilization, trxName);
//
////		// Movimientos por id de movimiento y si estan o no listos
////		final StringBuilder sql = new StringBuilder();
////		sql.append("SELECT DISTINCT(m.M_Movement_ID), m.*, a.M_Warehouse_ID as almSol, m.Soli_User_ID, ");
////		sql.append("surte.M_Warehouse_ID AS almSurt ");
////		sql.append("FROM M_Movement m ");
////		sql.append("INNER JOIN M_MovementLine l ON m.M_Movement_ID = l.M_Movement_ID ");
////		sql.append("INNER JOIN M_Locator ll ON l.M_Locator_ID = ll.M_Locator_ID "); // surte
////		sql.append("INNER JOIN M_Locator ls ON l.M_LocatorTo_ID = ls.M_Locator_ID "); // Solicitante
////		sql.append("INNER JOIN M_Warehouse a ON ls.M_Warehouse_ID = a.M_Warehouse_ID ");
////		sql.append("INNER JOIN M_Warehouse surte ON ll.M_Warehouse_ID = surte.M_Warehouse_ID ");
////		sql.append("WHERE ll.M_Warehouse_ID = ? ");
////		sql.append("AND m.DocStatus IN ('" + Status + "') ");
////		sql.append("AND m.AD_Client_ID = ? ");
////		sql.append("AND m.AD_Org_ID = ? ");
////		sql.append("AND m.IsActive = 'Y' ");
////		sql.append("ORDER BY m.Created DESC");
////
////		PreparedStatement pstmt = null;
////		ResultSet rs = null;
////		List<MMovement> lst = new ArrayList<MMovement>();
////
////		try {
////			pstmt = DB.prepareStatement(sql.toString(), null);
////			pstmt.setInt(1, warehouseID);
////			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
////			pstmt.setInt(3, Env.getAD_Org_ID(ctx));
////			rs = pstmt.executeQuery();
////
////			while (rs.next()) {
////				final MMovement mov = new MMovement(ctx, rs, trxName);
////				mov.mAlmSolic = new MWarehouse(ctx, rs.getInt("almSol"),
////						trxName);
////				mov.mAlmSurt = new MWarehouse(ctx, rs.getInt("almSurt"),
////						trxName);
////				mov.mUsuario = new MUser(ctx, rs.getInt("soli_user_id"),
////						trxName);
////				mov.isReady = rs.getString("ready").equals('Y') ? true : false;
////
////				if (mov.getEXME_CtaPac_ID() > 0) {
////					mov.mMCtaPac = new MEXMECtaPac(ctx,
////							mov.getEXME_CtaPac_ID(), trxName);
////				}
////				if (mov.mMCtaPac == null || mov.mMCtaPac.get_ID() == 0) {
////					mov.setReleaseStatus("");
////				} else {
////					mov.setReleaseStatus(mov.mMCtaPac.getEncounterStatus());
////				}
////				lst.add(mov);
////			}
////			rs.close();
////			pstmt.close();
////		} catch (SQLException e) {
////			slog.log(Level.SEVERE, "get : ", e);
////			slog.info(sql + "   almacen: " + warehouseID + ", AD_Client_ID: "
////					+ Env.getAD_Client_ID(ctx) + ", AD_Org_ID: "
////					+ Env.getAD_Org_ID(ctx) + "\n\n");
////			lst = null;
////		} finally {
////			DB.close(rs, pstmt);
////		}
////		return lst;
//	}

//	/**
//	 * Obtenemos una lista con los movimientos pendientes de surtir
//	 *
//	 * @param ctx
//	 * @param warehouseID
//	 *            El almacen que surte esa solicitud
//	 * @param ready
//	 *            Movimientos listos
//	 * @return Un resultset con los movimientos pendientes de surtir
//	 * @deprecated
//	 */
//	public static List<MMovement> getMovements(final Properties ctx, final int warehouseID,
//			final String status, final String typeWhs, final String trxName) throws SQLException {
//
//		// Quitar los de abasto por que se quieren ver los de charola
//		String onlyTrays = StringUtils.EMPTY;
//		if(X_M_Warehouse.TYPE_WarehouseSupply.equalsIgnoreCase(typeWhs)){
//			onlyTrays = " AND m.EXME_ProgQuiro_ID IS NULL ";
//		} else if(X_M_Warehouse.TYPE_Sterilization.equalsIgnoreCase(typeWhs)){
//			onlyTrays = " AND m.EXME_ProgQuiro_ID IS NOT NULL ";
//		}
//
//		// Movimientos por id de movimiento y si estan o no listos
//		final StringBuilder sql = new StringBuilder();
//		sql.append("SELECT DISTINCT(m.M_Movement_ID), m.* ");//, a.M_Warehouse_ID as almSol, m.Soli_User_ID, ");
////		sql.append("surte.M_Warehouse_ID AS almSurt ");
//		sql.append("FROM M_Movement m ");
//		sql.append("INNER JOIN M_MovementLine l ON m.M_Movement_ID = l.M_Movement_ID ");
//		sql.append("INNER JOIN M_Locator ll ON l.M_Locator_ID = ll.M_Locator_ID "); // surte
////		sql.append("INNER JOIN M_Locator ls ON l.M_LocatorTo_ID = ls.M_Locator_ID "); // Solicitante
////		sql.append("INNER JOIN M_Warehouse a ON ls.M_Warehouse_ID = a.M_Warehouse_ID ");
////		sql.append("INNER JOIN M_Warehouse surte ON ll.M_Warehouse_ID = surte.M_Warehouse_ID ");
//		sql.append("WHERE ll.M_Warehouse_ID = ? ");//#1
//		sql.append("AND m.DocStatus = ? ");//#2
////		sql.append("AND m.AD_Client_ID = ? ");//#3
////		sql.append("AND m.AD_Org_ID = ? ");//#4
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "m"));
//		sql.append("AND m.IsActive = 'Y' ");
////		if (ready == 1) {
////			sql.append("AND m.Ready = 'Y' ");
////		} else if (ready == 2) {
////			sql.append("AND m.Ready = 'N' ");
////		}
//		sql.append(onlyTrays);
//		sql.append("ORDER BY m.Created DESC");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<MMovement> lst = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, warehouseID);
//			pstmt.setString(2, status);
////			pstmt.setInt(3, Env.getAD_Client_ID(ctx));
////			pstmt.setInt(4, Env.getAD_Org_ID(ctx));
//			rs = pstmt.executeQuery();
//
//			lst = new ArrayList<MMovement>();
//
//			while (rs.next()) {
//				final MMovement mov = new MMovement(ctx, rs, trxName);
////				mov.mAlmSolic = new MWarehouse(ctx, rs.getInt("almSol"), trxName);
////				mov.mAlmSurt = new MWarehouse(ctx, rs.getInt("almSurt"), trxName);
////				mov.mUsuario = new MUser(ctx, rs.getInt("soli_user_id"), trxName);
////				mov.isReady = rs.getString("ready").equals('Y') ? true : false;
//
////				if (mov.getEXME_CtaPac_ID() > 0) {
////					mov.setCtaPac(new MEXMECtaPac(ctx, mov.getEXME_CtaPac_ID(), trxName));
////				}
//
////				if (mov.getCtaPac() == null || mov.getCtaPac().get_ID() == 0) {
////					mov.setReleaseStatus("");
////				} else {
////					mov.setReleaseStatus(mov.getCtaPac().getEncounterStatus());
////				}
//
//				lst.add(mov);
//			}
//			rs.close();
//			pstmt.close();
//		} catch (SQLException e) {
//			slog.log(Level.SEVERE, "get : ", e);
//			slog.info(sql + "   almacen: " + warehouseID + ", AD_Client_ID: "
//					+ Env.getAD_Client_ID(ctx) + ", AD_Org_ID: "
//					+ Env.getAD_Org_ID(ctx) + "\n\n");
//			lst = null;
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lst;
//	}

	/**
	 * Obtenemos el encabezado de los movimientos a partir del id de
	 * confirmacion
	 *
	 * @param movementID
	 *            el movimiento pendiente
	 * @return Un resultset con los movimientos pendientes de surtir
	 * @deprecated
	 */
	public static MMovement getFromConfirm(final Properties ctx,
			final int movConfirmID, final String trxName) {
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		// movimientos por id de movimiento
//		sql.append("SELECT M_Movement.* ");
//		sql.append("FROM M_Movement ");
//		sql.append(" INNER JOIN M_MovementConfirm c ON M_Movement.M_Movement_ID = c.M_Movement_ID ");
//		sql.append("WHERE c.M_MovementConfirm_ID = ? ");
//		sql.append("AND M_Movement.IsActive = 'Y' ");
//		sql.append("AND c.IsActive = 'Y' ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Movement"));
//
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//
//		MMovement mov = null;
//		try {
//			slog.fine(sql + "   movementConfirmID: " + movConfirmID + "\n\n");
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, movConfirmID);
//			rset = pstmt.executeQuery();
//			if (rset.next()) {
//				mov = new MMovement(ctx, rset, trxName);
//			}
//		} catch (SQLException e) {
//			slog.log(Level.SEVERE, "-- getFromConfirm : ", e);
//			throw e;
//		} finally {
//			DB.close(rset, pstmt);
//		}
		return new Query(ctx, Table_Name, " c.M_MovementConfirm_ID = ? ", trxName)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setJoins(new StringBuilder(" INNER JOIN M_MovementConfirm c ON M_Movement.M_Movement_ID=c.M_Movement_ID "))
		.setParameters(movConfirmID)//
		.first();
	}

	/**
	 * Creamos el backorder.
	 *
	 * @param movBackOrdr
	 * @param lstConfirmaDet
	 * @throws SQLException
	 * @deprecated - {@link #createBackorder(Properties, MMovement, List, String)}
	 */
	public static boolean createBackorder(final MMovement movBackOrdr,
			final List<ConfirmaDetView> lstConfirmaDet) throws Exception {

		try {

			for (int i = 0; i < lstConfirmaDet.size(); i++) {
				// recuperamos los valores de la forma
				final ConfirmaDetView linea = lstConfirmaDet.get(i);

				// Detalle del pedido confirmado
				final MMovementLine mov = new MMovementLine(
						movBackOrdr.getCtx(), (int) linea.getMovementLineID(),
						movBackOrdr.get_TrxName()); // .-Lama

				// si hubo faltantes insertar la linea (solicitado - enviado)
				// if((linea.getOriginalQty() - linea.getConfirmedQty())>0) {
				if ((linea.getOriginalQty().subtract(linea.getConfirmedQty()))
						.compareTo(BigDecimal.ZERO) == 1) { // (linea.getOriginalQty()
															// -
															// linea.getConfirmedQty())>0

					final MMovementLine ml = new MMovementLine(movBackOrdr.getCtx(), 0, movBackOrdr.get_TrxName());

					ml.setM_Movement_ID(movBackOrdr.getM_Movement_ID());
					ml.setPriceList(mov.getPriceList());
					ml.setM_Product_ID((int) linea.getProductID());
					ml.setOriginalQty(linea.getOriginalQty().subtract(linea.getTargetQty()));
					ml.setDescription(linea.getDescription());
					ml.setM_Locator_ID(mov.getM_Locator_ID());
					ml.setM_LocatorTo_ID(mov.getM_LocatorTo_ID());

					slog.finer("M_MovementLine.M_Product_ID : " + ml.getM_Product_ID());

					ml.setAD_OrgTrx_ID(mov.getAD_OrgTrx_ID());
					ml.setEXME_CtaPac_ID(mov.getEXME_CtaPac_ID());

					if (!ml.save()) {
						return false;
					}
				}
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "-- createBackorder", e);
			throw e;
		}

		return true;
	}

	public MLocator getmLocator() {
		if (mLocator == null) {
			mLocator = MMovement.getLocator(getCtx(), getM_Movement_ID(), get_TrxName());
		}
		return mLocator;
	}

//	public void setmLocator(final MLocator mLocator) {
//		this.mLocator = mLocator;
//	}

	public MLocator getmLocatorTo() {
		if (mLocatorTo == null) {
			mLocatorTo = MLocator.get(getCtx(), MMovement.getLocatorTo(getCtx(), getM_Movement_ID(), get_TrxName()));
		}
		return mLocatorTo;
	}

//	public void setmLocatorTo(final MLocator mLocatorTo) {
//		this.mLocatorTo = mLocatorTo;
//	}

	/**
	 * Devolvemos el locatorTo relacionado a un movimiento en particular.
	 *
	 * @param ctx
	 * @param M_Movement_ID
	 * @param trxName
	 * @return
	 */
	public static int getLocatorTo(final Properties ctx, final int M_Movement_ID, final String trxName) {
		int locatorTo = DB.getSQLValue(trxName, "SELECT M_LocatorTo_ID FROM M_MovementLine WHERE M_Movement_ID=?", M_Movement_ID);
		return locatorTo;
	}

	/**
	 * Devolvemos el locator relacionado a un movimiento en particular.
	 *
	 * @param ctx
	 * @param M_Movement_ID
	 * @param trxName
	 * @return
	 */
	public static MLocator getLocator(final Properties ctx, final int M_Movement_ID, final String trxName) {
		MLocator locator = null;
		int locatorId = DB.getSQLValue(trxName, "SELECT M_Locator_ID FROM M_MovementLine WHERE M_Movement_ID=?", M_Movement_ID);
		if(locatorId > 0) {
			locator = MLocator.get(ctx, locatorId);
		}
		return locator;
	}

	private MUser requestUser;

	public MUser getRequestUser() {
		if (requestUser == null) {
			requestUser = new MUser(getCtx(), getSoli_User_ID(), null);
		}
		return requestUser;
	}

	private MUser supplyUser;

	public MUser getSupplyUser() {
		if (supplyUser == null) {
			supplyUser = new MUser(getCtx(), getSurt_User_ID(), null);
		}
		return supplyUser;
	}

	/**
	 * Obtenemos el precio del producto
	 *
	 * @param ctx
	 * @param partnerID
	 * @return
	 * @author eruiz
	 * FIXME: se usa para almacen en consigna sigue aplicando ??
	 * @deprecated
	 */
	private static double getProductPrice(Properties ctx, int partnerID, int productID) {
		double price = 0;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		final StringBuilder sql = new StringBuilder();
		try {
			final MBPartner proveedor = new MBPartner(ctx, partnerID, null);
			// Si el proveedor tiene una lista de precios asignada, usar esa lista

			if (proveedor.getM_PriceList_ID() > 0){ //buscamos en M_ProductPrice
				sql.append("select M_ProductPrice.PriceStd from EXME_ProductoPrecio M_ProductPrice ")
					.append("where M_ProductPrice.isActive = 'Y' ")
					.append("and M_ProductPrice.M_Product_ID = ? ");//" + cond1);
			// .append(" and M_ProductPrice.m_pricelist_version_id = " + cond2);
//				psmt = DB.prepareStatement(getSql(true, productID,plv.getM_PriceList_Version_ID()), null);
				psmt = DB.prepareStatement(sql.toString(), null);
				psmt.setInt(1, productID);

//				final MPriceList pl = new MPriceList(ctx, proveedor.getM_PriceList_ID(), null);
//				final MPriceListVersion plv = pl.getPriceListVersion(null);
//				psmt.setInt(2, plv.getM_PriceList_Version_ID());

			// Sino usar la ultima orden de compra
			}else{ // M_Product_PO
				sql.append( "select M_Product_PO.PriceLastPO from M_Product_PO M_Product_PO ")
					.append("inner join C_BPartner bp on(bp.C_BPartner_ID = M_Product_PO.C_BPartner_ID) ")
					.append("where M_Product_PO.isActive = 'Y' and bp.isActive = 'Y' ")
					.append("and M_Product_PO.M_Product_ID = ? ") // + cond1
					.append("and bp.C_BPartner_ID = ? ");// + cond2);
//				psmt = DB.prepareStatement(getSql(false, productID, partnerID), null);
				psmt = DB.prepareStatement(sql.toString(), null);
				psmt.setInt(1, productID);
				psmt.setInt(2, partnerID);
			}
			rs = psmt.executeQuery();
			if (rs.next()){
				price = rs.getDouble(1);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getLines: "+sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return price;
	}

	@Override
	protected boolean afterSave(final boolean newRecord, final boolean success) {
		// si es nuevo registro y esta activo
		if (success && newRecord && isActive()) {
			final int deliveryWarehouse = MWarehouse.getIdWarehouseDesc(getCtx(), getAlmacen());
			if(isHl7Warehouse(deliveryWarehouse)) {
				insertHl7Order("NW", deliveryWarehouse);
			}
		}
		return true;
	}
	/**
	 * @param warehouseId Almacen
	 * @return TRUE: si el almacen esta configurado como hl7orm
	 */
	private boolean isHl7Warehouse( int warehouseId) {
		final int count = DB.getSQLValue(null, //
			"SELECT Count(*) FROM M_Warehouse WHERE hl7orm='Y' AND m_warehouse_id=? ",//
			warehouseId);
		return count > 0;
	}

	/**
	 * Inserta en EXME_I_ORDER (HL7)
	 * @param event
	 * @param almacenSurte
	 */
	private void insertHl7Order(String event, int almacenSurte) {
		try {
			final StringBuilder sqlInsert = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sqlInsert
					.append("INSERT INTO EXME_I_ORDER (exme_I_ORDER_id,exme_actpacienteindh_id,EVENT,value,interfaz_hl7,M_WAREHOUSE_ID, M_MOVEMENT_ID ) ")
					.append(" values ( ")
					.append("(SELECT NVL(MAX (EXME_I_order_ID) + 1,1000000) FROM EXME_I_order)")
					.append(",0,?")//1
//					.append(event)
					.append(",'0'")
					.append(",(SELECT interfaz_hl7 FROM M_WAREHOUSE WHERE M_WAREHOUSE_ID=?)")//2
					.append(",?").append(almacenSurte)//3
					.append(",?")//.append(getM_Movement_ID())4
					.append(")");
			// System.out.println(sqlInsert.toString());
			boolean ok = false;
			try {
				int no = DB.executeUpdate(sqlInsert.toString(), //
					new Object[] { event, almacenSurte, almacenSurte, getM_Movement_ID() }, //
					get_TrxName());
				ok = no == 1;
				if (!ok) {
					final MEXMEInterfacesLog mil = new MEXMEInterfacesLog(getCtx(), 0, get_TrxName());
					mil.setInterfase("OMB");
					mil.setOperation("I");
					mil.setReferenceNo(String.valueOf(getM_Movement_ID()));
					mil.setMessage("(Error) --> " + sqlInsert.toString());
					mil.setFileName("--");
					mil.save(get_TrxName());
				}
			} catch (Exception e) {
//				e.printStackTrace();
				log.log(Level.SEVERE, "getLines", e);
			}
		} catch (Exception e1) {
//			e1.printStackTrace();
			log.log(Level.SEVERE, "getLines", e1);
		}
	}

	public String getAlmacen() {
		return almacen;
	}

	public void setAlmacen(final String warehouse) {
		almacen = warehouse;
	}

	/**
	 * Obtiene una lista de MMovement
	 *
	 * @param where
	 *            filtro para query inicia con "AND"
	 * @param ctx
	 *            Contexto
	 * @return list
	 * @author rosy velazquez
	 * @deprecated no concatenar parametros en el where, agregar parametro List<Object> params
	 * */
	public static List<MMovement> getMovements(final String where, final Properties ctx) {
//		final ArrayList<MMovement> list = new ArrayList<MMovement>();
//		final StringBuilder sql = new StringBuilder()
//		.append(" SELECT * FROM M_Movement ")
//		.append(" WHERE isActive= 'Y' ")
//		.append(where == null?"":where);
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				list.add(new MMovement(ctx, rs, null));
//			}
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}

		return new Query(ctx, Table_Name, " 1=1 "+where, null).setOnlyActiveRecords(true).list();
	}

	/**
	 *
	 * @param ctx
	 * @param warehouseId
	 * @param docStatus
	 * @param selection
	 * @param date
	 * @param date2
	 * @return
	 */
	public static List<ReportColumns> getMovementsRep(Properties ctx, int warehouseId, String docStatus, boolean selection, Timestamp date, Timestamp date2, int productId ) {
		final ArrayList<ReportColumns> list = new ArrayList<ReportColumns>();

		List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder();

		if(warehouseId > 0){
			params.add(warehouseId);
			params.add(warehouseId);

		}
		if(productId > 0){
			params.add(productId);
		}
		params.add(date);
		params.add(date2);
		params.add(docStatus);

		sql.append("SELECT DISTINCT ");
		sql.append("  m.movementdate, ");
		sql.append("  m.documentno, ");
		sql.append("  w1.name ");
		sql.append("  AS surte, ");
		sql.append("  w2.name ");
		sql.append("  AS solicita, ");
		sql.append("  u.name ");
		sql.append("  AS usuario_solicita ");
		sql.append("FROM ");
		sql.append("  m_movement m ");
		sql.append("  INNER JOIN m_movementline line ");
		sql.append("  ON m.m_movement_id = line.m_movement_id ");
		sql.append("  INNER JOIN m_locator loc ");
		sql.append("  ON line.m_locator_id = loc.m_locator_id ");
		sql.append("  INNER JOIN m_warehouse w1 ");
		sql.append("  ON (loc.m_warehouse_id = w1.m_warehouse_id) ");
		sql.append("  INNER JOIN m_locator locto ");
		sql.append("  ON line.m_locatorto_id = locto.m_locator_id ");
		sql.append("  INNER JOIN m_warehouse w2 ");
		sql.append("  ON (locto.m_warehouse_id = w2.m_warehouse_id) ");
		sql.append("  INNER JOIN ad_user u ");
		sql.append("  ON (m.soli_user_id = u.ad_user_id) left ");
		sql.append("  JOIN m_movementconfirm conf ");
		sql.append("  ON m.m_movement_id = conf.m_movement_id ");
		sql.append("WHERE ");
		sql.append("  m.isActive= 'Y'  ");
		if(warehouseId > 0){
		sql.append("  and (loc.m_warehouse_id = ? OR ");
		sql.append("  locto.m_warehouse_id = ? )  ");
		}
		if(productId > 0){
			sql.append(" and line.m_product_id = ? ");
		}
		sql.append("  AND m.movementdate BETWEEN ");
		sql.append(" ? AND ");
		sql.append(" ?  ");
		sql.append(" and m.docstatus = ?  ");
		sql.append(" and m.isdevol = ? ");
		params.add(DB.TO_STRING(selection));

//		sql.append(" select distinct m.* from m_movement m ");
//		sql.append(" inner join m_movementline line on m.m_movement_id = line.m_movement_id ");
//		sql.append(" inner join m_locator loc on line.m_locator_id = loc.m_locator_id ");
//		sql.append(" inner join m_locator locto on line.m_locatorto_id = locto.m_locator_id ");
//		sql.append(" left join m_movementconfirm conf on m.m_movement_id = conf.m_movement_id ");
//		sql.append("  where (loc.m_warehouse_id = ? or locto.m_warehouse_id = ?) AND ");
//		sql.append("  m.movementdate BETWEEN ? AND ? AND ");
//		sql.append(" m.isActive= 'Y' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "m"));

		sql.append(" ORDER BY ");
		sql.append("  w2.name, m.movementdate desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				ReportColumns columns = new ReportColumns();
				columns.setMovementDate(rs.getTimestamp("movementdate"));
				columns.setDocumentNo(rs.getString("documentNo"));
				columns.setSolicita(rs.getString("solicita"));
				columns.setSurte(rs.getString("surte"));
				columns.setUsuarioSolicita(rs.getString("usuario_Solicita"));
				list.add(columns);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Obtenemos una lista de movimientos segun la fecha de movimiento
	 *
	 * @param ctx
	 * @param quiroID
	 *            El quirofano en donde se hizo el movimiento
	 * @param start
	 *            Parametro de fecha inicial
	 * @param end
	 *            Parametro de fecha final
	 * @param ready
	 *            Movimientos listos
	 * @return Un resultset con los movimientos segun la fecha de movimiento
	 */
	public static List<MMovement> getProgQuiroMovements(final Properties ctx,
			final int quiroId, final Date start, Date end, final int ready, final String trxName) {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final List<MMovement> lst = new ArrayList<MMovement>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT DISTINCT ");
			sql.append("  (m.m_movement_id), ");
			sql.append("  m.* ");
			sql.append("FROM ");
			sql.append("  M_Movement m ");
			sql.append("  INNER JOIN EXME_ProgQuiro pg ");
			sql.append("  ON m.EXME_ProgQuiro_ID = pg.EXME_ProgQuiro_ID ");
			sql.append("  INNER JOIN EXME_Quirofano q ");
			sql.append("  ON pg.EXME_Quirofano_ID = q.EXME_Quirofano_ID ");
			sql.append("  INNER JOIN m_movementline ml ");
			sql.append("  ON m.m_movement_id = ml.m_movement_id ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " WHERE ",
					MMovement.Table_Name, "m"));
			sql.append(" AND m.movementDate BETWEEN TO_DATE(?, 'DD/MM/YYYY HH24:MI') AND TO_DATE(?, 'DD/MM/YYYY HH24:MI') ");

			if (quiroId != -1) {
				sql.append("AND pg.EXME_Quirofano_ID = ? ");
			}
			sql.append(" AND ml.M_Locator_ID = "
					+ MLocator.getLocatorID(ctx,
							MWarehouse.getSterilization(ctx), trxName));
			sql.append(" AND m.EXME_ProgQuiro_ID IS NOT NULL ");

			if (ready == 1) {
				sql.append("AND m.Ready = 'Y' ");
			} else if (ready == 2) {
				sql.append("AND m.Ready = 'N' ");
			}

			sql.append("ORDER BY m.DocumentNo");
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setString(1, sdf.format(start) + " 00:00");
			pstmt.setString(2, sdf.format(end) + " 23:59");

			if (quiroId != -1) {
				pstmt.setInt(3, quiroId);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MMovement(ctx, rs, trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lst;
	}

	public String getNombreMed() {
		I_EXME_ProgQuiro prog = getEXME_ProgQuiro();
		return prog == null ? "": new MEXMEMedico(getCtx(),
				prog.getEXME_Medico_ID(), null).getNombre_Med() ;
	}

	public String getNombreQuiro() {
		I_EXME_ProgQuiro prog = getEXME_ProgQuiro();
		return prog == null ? "": new MEXMEQuirofano(getCtx(), prog.getEXME_Quirofano_ID(), null).getName();
	}

	public String getPrepared() {
		return isReady() ? Utilerias.getAppMsg(getCtx(), "msg.si") : Utilerias.getAppMsg(getCtx(), "msg.no");
	}

	/**
	 *
	 * @return
	 */
	public List<MMovementLine> getLines() {
		return new Query(getCtx(), MMovementLine.Table_Name, " M_Movement_ID = ? ", get_TrxName())
		.setOnlyActiveRecords(true)
		.setParameters(getM_Movement_ID()).list();
	}

	/**
	 * @deprecated
	 */
	public void setWarehouseIds() {
		try {//Lama -> se comenta proque Para llenar los dato se hacia 5 accesos a la BD
			// Solicita
//			MLocator locator = new MLocator(getCtx(), getLocatorTo(getCtx(), getM_Movement_ID(), get_TrxName()), get_TrxName());
//			setMAlmSolic(new MWarehouse(getCtx(), locator.getM_Warehouse_ID(), get_TrxName()));
//			// Surte
//			locator = getLocator(getCtx(), getM_Movement_ID(), get_TrxName());
//			setMAlmSurt(new MWarehouse(getCtx(), locator.getM_Warehouse_ID(), get_TrxName()));
			getMAlmSolic();
			getMAlmSurt();
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Obtenemos el encabezado de los movimientos a partir del id de
	 * confirmacion
	 *
	 * @param movementID
	 *            el movimiento pendiente
	 * @return Un resultset con los movimientos pendientes de surtir
	 */
	public static MMovement getPrescRXDet(final Properties ctx, final int pPrescRXDetID, final String trxName) {
		return new Query(ctx, X_M_Movement.Table_Name,
				" EXME_PrescRXDet_ID = ? ", null)//
				.setOrderBy("Created DESC") //EL MAS NUEVO
				.setParameters(pPrescRXDetID) //
				.setOnlyActiveRecords(true) //
				.addAccessLevelSQL(true).first();
	}

	/**
	 * Obtenemos el encabezado de los movimientos a partir del id de
	 * confirmacion
	 *
	 * @param movementID
	 *            el movimiento pendiente
	 * @return Un resultset con los movimientos pendientes de surtir
	 */
	public static List<MMovement> getPrescRXDetPending(final Properties ctx, final int pPrescRXDetID, final String trxName) {
		return new Query(ctx, X_M_Movement.Table_Name,
				" EXME_PrescRXDet_ID = ? AND DocStatus IN ('DR', 'IP') ", null)//
				.setOrderBy(" Created DESC ") //
				.setParameters(pPrescRXDetID) //
				.setOnlyActiveRecords(true) //
				.addAccessLevelSQL(true).list();
	}

	/**
	 * Obtenemos la cuenta paciente
	 *
	 * @return
	 */
	public MEXMECtaPac getCtaPac() {
		if (ctaPac == null && getEXME_CtaPac_ID() > 0) {
			ctaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return ctaPac;
	}

	/**
	 * Número de cuenta paciente
	 *
	 * @return No. de Cuenta paciente o vacío si no tiene
	 */
	public String getNoCtaPac() {
		if (getCtaPac() != null) {
			return getCtaPac().getDocumentNo();
		} else {
			return StringUtils.EMPTY;
		}
	}

	/**
	 * Verificamos si la cuenta tiene al menos 1 linea
	 *
	 * @return True si tiene al menos 1 linea, false si no.
	 */

	public static boolean getForCtaPacId(Properties ctx, int ctaPacId, String trxName) {
		return new Query(ctx, Table_Name, " EXME_CtaPac_ID=? ", trxName)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setParameters(ctaPacId)//
				.firstId() > 0;
	}

	/**
	 * Crear objeto MMovement
	 *
	 * @param ctx
	 * @param devol
	 * @param orgTrx
	 * @param ctaPacId
	 * @param description
	 * @param warehouseName
	 * @param backOrder
	 * @param trxName
	 * @return
	 * @throws ExpertException
	 * @deprecated
	 */
	public static MMovement insertMovement(final Properties ctx,
			final boolean devol, final int orgTrx, final int ctaPacId,
			final int progQuiroId, final String description,
			final String warehouseName, final String priorityRule,
			final boolean backOrder, final String trxName)
			throws ExpertException {
		slog.log(Level.INFO, "MEXMEMovement.insertMovement");

		final MMovement mov = new MMovement(ctx, 0, trxName);
		mov.setIsDevol(devol);
//		mov.setMovementDate(Env.getCurrentDate());
//		mov.setProcessing(false);
		mov.setAD_OrgTrx_ID(orgTrx);
		mov.setBackorder(backOrder);
//		mov.setDocStatus(MMovement.STATUS_Drafted);
//		mov.setDocAction(MMovement.ACTION_Complete);
		mov.setApprovalAmt(BigDecimal.ZERO);
		mov.setEXME_CtaPac_ID(ctaPacId);
		mov.setEXME_ProgQuiro_ID(progQuiroId);
		if (warehouseName != null) {
			mov.setAlmacen(warehouseName);
		}
		mov.setDescription(description);
//		mov.setIsDevol(devol);
		mov.setPriorityRule(priorityRule);

		if (!mov.save(trxName)) {
			throw new ExpertException("error.traspasos.noInsertMov");
		}
		return mov;
	}

	/**
	 * Metodo utilizado para guardar la solicitud de charolas y/o productos
	 * tomando en cuenta ambas unidades de medida y recibiendo como parametro la
	 * clase de modelo
	 *
	 * @param ctx
	 * @param htLineas
	 * @param devol
	 * @param ctaPacId
	 * @param warehouseID
	 * @param warehouseToID
	 * @param trxName
	 * @return
	 * @throws Exception
	 * @deprecated
	 */
	public String insertMEXMEMovementLine(final List<MMovementLine> htLineas,
			final boolean devol, final int ctaPacId, final int warehouseID,
			final int warehouseToID, final String trxName) throws Exception {
		slog.log(Level.INFO, "MEXMEMovement.insertMEXMEMovementLine");

		if (htLineas == null || htLineas.isEmpty()) {
			throw new ExpertException("cancelaServ.msg.noLineas");
		}

		// Valor de retorno
		String error = null;
		// Secuencia de lineas
		int n = 10;

		for (MMovementLine line : htLineas) {

			// Validar que si el producto elegido utiliza numero de serie será
			// necesario hacer que la cantidad maxima de producto sea en
			// cantidad uno.
			final MProduct prod = new MProduct(getCtx(),
					line.getM_Product_ID(), trxName);

			if (line.getM_Product_ID() > 0 && prod.isNumSerie() && ctaPacId > 0
				&& line.getQuantityUser().compareTo(BigDecimal.ONE) == 1) {
					slog.log(Level.ALL, Utilerias.getMsg(getCtx(),
							"error.traspasos.noInsertMovLine"));
					error = "error.traspasos.noInsertMovLine";
			}

			// Si ha pasado las validaciones
			if (error == null) {
				line.setLine(n);
				line.setM_Movement_ID(getM_Movement_ID());
				line.setAD_OrgTrx_ID(getAD_OrgTrx_ID());
				line.setEXME_CtaPac_ID(ctaPacId);// Puede ser dif a nivel de
													// linea

				// buscamos el localizador de default del almacen resurtido
				int localizador = MLocator.getLocatorID(getCtx(), warehouseID, trxName);
				if (devol) {
					// si es una devolucion invertir los localizadores
					line.setM_LocatorTo_ID(localizador);
				} else {
					line.setM_Locator_ID(localizador);
				}

				// buscamos el localizador de default del almacen solicitante
				localizador = MLocator.getLocatorID(getCtx(), warehouseToID, trxName);
				if (devol) {
					// si es una devolucion invertir los localizadores
					line.setM_Locator_ID(localizador);
				} else {
					line.setM_LocatorTo_ID(localizador);
				}

				// Precio de los productos
				if (line.getM_Product_ID() > 0) {
					final MPrecios precios = GetPrice.getPriceMov(getCtx(), line, trxName);// time
					if (precios != null) {
						line.setPriceList(precios.getPriceStd());
					}
				}

				if (line.save(trxName)) {
					String retValue;
					if (devol) {
						retValue = MDevolucion.devAlmacenVirtual(getCtx(), line, trxName);
					} else {
						retValue = MSolicitud.solAlmacenVirtual(getCtx(), line, trxName);
					}
					if (retValue != null) {
						slog.log(Level.ALL, Utilerias.getMsg(getCtx(),
								"error.traspasos.noInsertMovLine"));
						error = "error.traspasos.noInsertMovLine";
						break;
					}
				} else {
					throw new ExpertException("error.traspasos.noInsertMovLine");
				}
				n += 10;
			}// si valido
		}// fin for
		return error;
	}

	/**
	 * Atributos por movimiento
	 * para la administración de medicamento
	 * @param ctx : Contexto
	 * @param mPrescRXDetID : Prescripción
	 * @param trxName : Nombre de transacción
	 * @return  List<MAttributeSetInstance> con los lotes
	 */
	public static List<MAttributeSetInstance> getFromProduct(final Properties ctx,
			final int mPrescRXDetID, final String trxName) {
		final List<MAttributeSetInstance> htLineas = new ArrayList<MAttributeSetInstance>();

		// movimientos por id de movimiento
		final StringBuilder sql = new StringBuilder(
				"SELECT s.M_AttributeSetInstance_ID ")
				.append(" FROM M_Movement ")
				.append(" INNER JOIN M_MovementLine l ON  M_Movement.M_Movement_ID = l.M_Movement_ID ")
				.append(" INNER JOIN M_Storage      s ON  s.M_Product_ID = l.M_Product_ID ")
				.append("                             AND s.M_AttributeSetInstance_ID = l.M_AttributeSetInstance_ID ")
				.append("                             AND s.QtyOnHand > 0 ")
				.append(" WHERE M_Movement.IsActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						"M_Movement"))
				.append(" AND   M_Movement.EXME_PrescRXDet_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mPrescRXDetID);
			rs = pstmt.executeQuery();
//FIXME: hace 2 accesos a la BD 1 para la consulta y otro para crear el objeto PO
			while (rs.next()) {
				htLineas.add(new MAttributeSetInstance(ctx, rs.getInt(1),
						trxName));
			}
		} catch (SQLException e) {
			slog.log(Level.SEVERE, sql
					+ " MEXMEMovement.getFromProduct. exme_PrescRXDet_ID: "
					+ mPrescRXDetID, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return htLineas;
	}

//	/**
//	 * Charolas
//	 *
//	 * @param nuevoMovId
//	 * @param isDevol
//	 * @param isSterilization
//	 * @return
//	 * FIXME: refactorizar
//	 * @deprecated
//	 */
//	public HashMap<Integer, MMovementLine> createDevolCharges(
//			final int nuevoMovId, final boolean isDevol,
//			final boolean isSterilization) {
//		//
//		final MMovement destino = new MMovement(getCtx(), nuevoMovId, null);
//		final List<MMovementLine> linesOrig = getLines();
//		final List<MMovementLine> linesNew = destino.getLines();
//		//FIXME: evitar usar Mapa de esta manera, puede haber erroes en caso de que se repita el producto
//		final HashMap<Integer, MMovementLine> map = new HashMap<Integer, MMovementLine>();
//
//		for (MMovementLine line : linesOrig) {
//
//			boolean encontrado = false;
//
//			// Iterar la lista del nuevo
//			for (MMovementLine line2 : linesNew) {
//
//				if (line.getM_Product_ID() == line2.getM_Product_ID()) {
//
//					BigDecimal num = BigDecimal.ZERO;
//
//					if (line.getOp_Uom() == 0 || line.getOp_Uom() == line.getProduct().getC_UOM_ID()) {
//						num = line.getConfirmedQty().subtract(line2.getTargetQty());
//
//					} else if (line.getProduct().getC_UOMVolume_ID() == line.getOp_Uom()) {
//						num = line.getConfirmedQty_Vol().subtract(line2.getTargetQty_Vol());
//					}
//
//					// BigDecimal num =
//					// line.getConfirmedQty().subtract(line2.getTargetQty());
//					// Cuando se hace devolucion de productos, se cancela el
//					// cargo
//					if (isDevol && !isSterilization) {
//						num = num.negate();
//					}
//					if (num.intValue() > 0) {
//						line.setBeanView(num);
//						map.put(line.getM_Product_ID(), line);
//					}
//					encontrado = true;
//					break;
//				}
//			}
//
//			// si hay una coincidencia
//			if (!encontrado) {
//				BigDecimal qty = BigDecimal.ZERO;
//
//				if (line.getOp_Uom() == 0 || line.getOp_Uom() == line.getProduct().getC_UOM_ID()) {
//					qty = line.getConfirmedQty();
//
//				} else if (line.getProduct().getC_UOMVolume_ID() == line.getOp_Uom()) {
//					qty = line.getConfirmedQty_Vol();
//				}
//				line.setBeanView(qty);
//				map.put(line.getM_Product_ID(), line);
//			}
//		}
//		return map;
//	}


//	/**
//	 * copiar lineas
//	 *
//	 * @param innerList
//	 * @return
//	 * @deprecated
//	 */
//	public static List<MMovementLine> getCopyMEXMEMovementList(
//			final Properties ctx, final List<MMovementLine> innerList) {
//		final List<MMovementLine> list = new ArrayList<MMovementLine>();
//		for (final MMovementLine oldMov : innerList) {
//			// nuevo registro que sera de la devolucion
//			final MMovementLine newMov = new MMovementLine(ctx, 0, null);
//			MMovementLine.copyValues(oldMov, newMov);
//
//			newMov.setOriginalQty(oldMov.getOriginalQty());
//			newMov.setTargetQty(oldMov.getTargetQty());
//
//			newMov.setOriginalQty_Vol(oldMov.getOriginalQty_Vol());
//			newMov.setTargetQty_Vol(oldMov.getTargetQty_Vol());
//
//			newMov.setConfirmedQty(BigDecimal.ZERO);
//			newMov.setConfirmedQty_Vol(BigDecimal.ZERO);
//
//			list.add(newMov);
//		}
//		return list;
//	}

//	/**@deprecated */
//	public List<ConfirmaDetView> getConfirmaList(final List<?> innerList) {
//		List<ConfirmaDetView> list = new ArrayList<ConfirmaDetView>();
//		for (Object obj : innerList) {
//			if (obj instanceof ConfirmaDetView) {
//				list.add((ConfirmaDetView) obj);
//			}
//		}
//		return list;
//	}

//	/**
//	 * Valida si hay existencias
//	 *
//	 * @param warehouse
//	 *            : Almacen en que se requiere ver si hay existencias
//	 * @param productId
//	 *            : Producto a verificar
//	 * @param mAttribSetInstID
//	 *            : Atributo del producto
//	 * @param targetQty
//	 *            : Cantidad a mover del inventario
//	 * @return true : Hay existencias
//	 * @deprecated
//	 */
//	public static boolean inStock(final Properties ctx, final int warehouse,
//			final int productId, final int mAttribSetInstID,
//			final BigDecimal targetQty) {
//		boolean inStock = true;
//		// Verifica que me maneje existencias a nivel organización
//		if (MEXMEMejoras.get(ctx).isControlExistencias()) {
//			// Verifica la existencia del producto en el almacen
//			BigDecimal QtyAvailable = MStorage.getQtyAvailable(warehouse,
//					productId, mAttribSetInstID, null);
//
//			if (QtyAvailable == null) {
//				QtyAvailable = BigDecimal.ZERO;
//			}
//			if (targetQty.compareTo(QtyAvailable) > 0) {
//				slog.saveError(
//						"SaveError",
//						"La cantidad de uso interno es mayor que la cantidad disponible para el almacen ("
//								+ QtyAvailable + ")");
//				inStock = false;
//			}
//		}
//		return inStock;
//	}

	/**
	 * Cancelar el movimientos,
	 * considerando que este movimientos pudiera tener consigna y consigna pueda tener backorder
	 * @param errorList: Listado de errores de motivos por lo que no se cancelo
	 * @return true: si se cancelo; false: si no se hizo ninguna cancelacion
	 */
	public boolean cancelMovement(final ErrorList errorList) {
		boolean isCanceled = false;
		if(X_M_Movement.DOCSTATUS_Drafted.equals(getDocStatus()) || getDocStatus()==null){

			// CONSIGNA: Como los movimientos en consigna ya fueron solicitados talvez surtidos y talvez confirmados
			final List<MMovement> movConsignment = getParentMovementConsignment();
			// Consigna
			for (final MMovement mMovConsignment : movConsignment) {
				cancelBackorder(errorList, mMovConsignment);
				cancelSubMovements(errorList, mMovConsignment);
				if(!errorList.isEmpty()){
					break;
				}
			}// finForMovimientosDeConsigna


			// Cancelar movimiento
			if(errorList.isEmpty()){
				startWorkflow(errorList, DocAction.ACTION_Void, DocAction.STATUS_Voided, get_TrxName());
			}
			isCanceled = errorList.isEmpty();
		}
		return isCanceled;
	}

	/**
	 * Cancelar los pedidos de backorder relacionados a un movimiento
	 * @param errorList:  Listado de errores de motivos por lo que no se cancelo
	 * @param mSubMovement: Movimiento a cancelarle sus backorder
	 */
	private void cancelBackorder(final ErrorList errorList, final MMovement mMovConsignment){
		// Cancelar backorder de movimientos en consigna
		final List<MMovement> backs  = mMovConsignment.getBackorders();
		for (final MMovement mMovBackorder : backs) {
			cancelSubMovements(errorList, mMovBackorder);
		}
	}

	/**
	 * Cancelar los movimientos de acuerdo al estatus del documento
	 * @param errorList:  Listado de errores de motivos por lo que no se cancelo
	 * @param mSubMovement: Movimiento a cancelar
	 */
	private void cancelSubMovements(final ErrorList errorList, final MMovement mMovCancel){

		if (X_M_Movement.DOCSTATUS_Drafted.equals(mMovCancel.getDocStatus()) || mMovCancel.getDocStatus()==null) {
			// Si solo esta solicitado se cancela en estatus VO
			if(!mMovCancel.cancelMovement(errorList)){//FIXME mensaje correcto!
				errorList.add(Error.VALIDATION_ERROR, //Utilerias.getAppMsg(mMovCancel.getCtx(), "error.traspasos.ctaPac")
					"No fue posible la cancelaci\u00F3n");
			}
		} else if (X_M_Movement.DOCSTATUS_InProgress.equals(mMovCancel.getDocStatus())) {
			// Si esta en progreso se envia mensaje de que no es posible hacer la cancelación //FIXME mensaje correcto!
			errorList.add(Error.VALIDATION_ERROR, //Utilerias.getAppMsg(mMovCancel.getCtx(), "error.traspasos.ctaPac"
				"El documento est\u00E1 en progreso");

		} else if (X_M_Movement.DOCSTATUS_Completed.equals(mMovCancel.getDocStatus())
				|| X_M_Movement.DOCSTATUS_Closed.equals(mMovCancel.getDocStatus())){
			// Si ya ha sido confirmado se crea una devolución
			errorList.getList().addAll(returnProd(mMovCancel.getCtx()
					, mMovCancel
					, mMovCancel.getLinesReturn()
					, true
					, mMovCancel.get_TrxName()).getList());
		}
	}

	/**
	 *
	 * @param ctx
	 * @param progQuiroId
	 * @param trxName
	 * @return
	 */
	public static List<MMovement> getFromProgQuirofano(final Properties ctx, final int progQuiroId, final String trxName) {
		return new Query(ctx, Table_Name, " EXME_ProgQuiro_ID = ? and DocStatus not in (?) ", trxName)//
			.setParameters(progQuiroId, DOCSTATUS_Voided)//
			.addAccessLevelSQL(true)//
			.list();
	}

	public String getMProcessMsg() {
		return mProcessMsg;
	}

	public void setMProcessMsg(String msg) {
		mProcessMsg = msg;
	}

	public boolean isProdOrTray() {
		return isProdOrTray;
	}

	public void setProdOrTray(boolean isProdOrTray) {
		this.isProdOrTray = isProdOrTray;
	}

	/**
	 * @param ctx
	 * @param reorderWHId
	 * @param almacenLogId
	 * @param documentNo
	 * @param ctaPacId
	 * @param description
	 * @param movementLines
	 * @param priorityRule
	 * @param progQuiroId
	 * @param isDevol
	 * @param allProcess
	 * @return
	 * @throws ExpertException
	 * @deprecated codigo copiado de WAlmacenSave
	 * NOTA; no debe haber creacion de transacciones en clases M
	 */
	public static MMovement generateMovement(Properties ctx, int reorderWHId, int almacenLogId, String documentNo
			, int ctaPacId, String description, List<MMovementLine>  movementLines, String priorityRule
			, int progQuiroId, boolean isDevol, boolean allProcess) throws ExpertException {

		Trx m_trx = null;
		MMovement mov = null;
		try {
			m_trx = Trx.get(Trx.createTrxName("Sol"), true);
			String trxName = m_trx.getTrxName();

			MEXMEEstServ est = MEstServAlm.getEstServ(ctx, (int) (isDevol ? almacenLogId : reorderWHId), trxName);

			String almacenV = new X_M_Warehouse(ctx, almacenLogId, null).getName();

			mov = AlmacenesDB.insertMovement(ctx, documentNo, isDevol, 0, 0, 0, est.getAD_OrgTrx_ID(), 0, 0, ctaPacId, 0, description, trxName, almacenV);

//			List<MovementLine> htLineas = new ArrayList<MovementLine>();
//			for (int i = 0; i < movementLines.size(); i++) {
//
//				MovementLine lineas = movementLines.get(i);
//				lineas.setLine((i + 1) * 10);
//				htLineas.add(lineas);
//			}

			// insertamos el detalle del movimiento
			//boolean success1 = AlmacenesDB.insertLine(getCtx(), htLineas, isDevol, mov.getM_Movement_ID(), reorderWHId, almacenLogId, ctaPacId, trxName);
			boolean success = AlmacenesDB.insertMEXMEMovementLine(
					ctx, movementLines, isDevol, mov, reorderWHId, almacenLogId, ctaPacId, true, trxName);

			List<String> error = new ArrayList<String>();
			if (success) {
				if (mov != null) {
					mov.setWarehouseIds();
					mov.setPriorityRule(priorityRule);
					if (progQuiroId > 0) {
						mov.setEXME_ProgQuiro_ID(progQuiroId);
					}
					if (!mov.save(trxName)) {
						mov = null;
						throw new ExpertException("error.traspasos.noUpdateMovPriority");
					} else if (isDevol || allProcess) {
						// Surte el movimiento
						error = mov.complete(ctx, mov.getLines(), m_trx.getTrxName());

						if(error.isEmpty()){
							Trx.commit(m_trx);
							String errormsg = mov.confirm();// Confirma el movimiento
							if(errormsg.isEmpty())
								error.add(errormsg);
						}
					}
				}
				if (error.isEmpty()) {
					Trx.commit(m_trx);
				} else {
					mov = null;
					Trx.rollback(m_trx);
				}
			} else {
				mov = null;
				throw new ExpertException("error.solicitudCharolas");
			}

		} catch (Exception e) {
			mov = null;
			if (m_trx != null) {
				m_trx.rollback();
			}
			if (e instanceof ExpertException) {
				throw (ExpertException) e;
			}
		} finally {
			Trx.close(m_trx, true);
		}

		return mov;
	}

	/**
	 * @param ctx
	 * @param lines
	 * @param trxName
	 * @return
	 * @deprecated codigo copiado de WAlmacenSave
	 * FIXME Porque hace validaciones y todos los erroes estan comentados???!!
	 */
	public List<String> complete(Properties ctx, List<MMovementLine> lines, String trxName) {
		List<String> errores = new ArrayList<String>();
//		setWarehouseIds();
//		// Validamos que no halla sido surtido.
//		// LEscamilla
//		if (getDocStatus().equalsIgnoreCase(MMovement.DOCSTATUS_InProgress)) {
//			//errores.add(Utilerias.getMsg(getCtx(), "error.traspasos.isSurtido2", mov.getDocumentNo()));
//			return errores;
//		}// LEscamilla
//
//		errores = validate(ctx, this, lines);
//
//		if (errores.isEmpty()) {
//
//			try {
//				SurtidoDet surtidoDet = new SurtidoDet(ctx, getDocumentNo(), lines, getEXME_CtaPac_ID(), false, get_ID(), getMAlmSurt().getM_Warehouse_ID(), trxName);
//
//				List<String[]> complete = surtidoDet.complete();
//
//				if (complete.size() > 0) {
//					for (int i = 0; i < complete.size(); i++) {
//
//						if (complete.get(i)[0].equalsIgnoreCase("THROW_EXCEPTION")) {
//						//	throw new Exception(Utilerias.getMsg(getCtx(), complete.get(i)[1], complete.get(i)[2]));
//						}
//						else {
//							if (!"GLOBAL_MESSAGE".equals(complete.get(i)[0])) {
//							//	errores.add(Utilerias.getMsg(getCtx(), complete.get(i)[1], complete.get(i)[2]));
//							}
//						}
//					}
//					complete = null;
//					surtidoDet = null;
//					return errores;
//				}
//				surtidoDet = null;
//			}
//			catch (Exception e) {
//				//log.log(Level.SEVERE, "-- complete : ", e);
//				//errores.add(Utilerias.getMsg(getCtx(), "error.traspasos.completando", e.getMessage()));
//			}
//		}

		return errores;
	}
//	/**
//	 * Validamos la forma
//	 *  @deprecated codigo copiado de WAlmacenSave
//	 *  FIXME Porque hace validaciones y todos los erroes estan comentados???!!
//	 */
//	public static List<String> validate(Properties ctx, MMovement mov, List<MMovementLine> lines) {
//		//log.log(Level.INFO, "******* validate ****************");
//		boolean controlaStock = MEXMEMejoras.get(ctx).isControlExistencias();
//		// el conjunto de errores
//		List<String> errores = new ArrayList<String>();
//
//		try {
//			// validamos que la cuenta paciente este activa en caso de que exista
//			if (mov.getEXME_CtaPac_ID() > 0) {
//				/*if (!MEXMECtaPac.ENCOUNTERSTATUS_Admission.equals(new MEXMECtaPac(ctx, mov
//					.getEXME_CtaPac_ID(), null).getEncounterStatus())) {
//					errores.add(Utilerias.getMsg(getCtx(), "error.traspasos.ctaPac"));
//				}*/
//				if(new MEXMECtaPac(ctx, mov.getEXME_CtaPac_ID(), null).getFechaCierre() != null){
//					//errores.add(Utilerias.getMsg(getCtx(), "error.traspasos.ctaPac"));
//				}
//			}
//
//			double totalQty = 0.0;
//
//			// validar que exista al menos una linea
//			if (lines.isEmpty()) {
//				//errores.add(Utilerias.getMsg(getCtx(), "error.traspasos.lines"));
//			}else{
//
//				for (MMovementLine linea : lines) {
//					// Solo para Card #1038
//					linea.setTargetQty(linea.getOriginalQty());
//					linea.setTargetQty_Vol(linea.getOriginalQty_Vol());//Solo para Card #1038
//					// validamos que la cantidad surtida no sea negativa
//					if (linea.getTargetQty().compareTo(BigDecimal.ZERO)<0) {
//						//errores.add(Utilerias.getMsg(getCtx(), "error.traspasos.surtCero", String.valueOf(linea.getLine())));
//
//					}
//					else if (linea.getTargetQty().compareTo(linea.getOriginalQty())>0) {
//
//						// validamos q la cant. surtida(target) sea menor o igual a la solicitada(original)
//						//errores.add(Utilerias.getMsg(getCtx(), "error.traspasos.surtSolic", String.valueOf(linea.getLine())));
//
//					}
//					else if (linea.getTargetQty().compareTo(BigDecimal.ZERO) > 0
//						&& controlaStock
//						&& MStorage.puedeSurtir(
//								(int) linea.getProduct().getM_Product_ID(),
//								MWarehouse.getFromLocator(ctx, linea.getM_Locator_ID(), null).getM_Warehouse_ID(),//mov.getM_AlmSurt().getM_Warehouse_ID(),
//								linea.getTargetQty()).compareTo(BigDecimal.ZERO) < 0 )
//					{
//						// LRHU. Si controla stock se valida que existan productos
//						// validar que tenga existencias
//						//errores.add(Utilerias.getMsg(getCtx(), "error.encCtaPac.noExistenProd", linea.getProduct().getName()));
//					}
//
//					//validacion de conversiones de UDM
//					if(linea.getProduct().getC_UOM_ID()!= linea.getProduct().getC_UOMVolume_ID()){
//						MUOMConversion rates = MEXMEUOMConversion.validateConversions(ctx, linea.getProduct().getM_Product_ID(), linea.getProduct().getC_UOMVolume_ID(),null);
//						if(rates == null){
//							//errores.add(Utilerias.getMsg(getCtx(), "error.udm.noExisteConversion", linea.getProduct().getName()));
//						//	log.saveError(Utilerias.getMsg(getCtx(), "error.udm.noExisteConversion"), linea.getProduct().getName());
//						}
//					}
//
//					// Vamos sumando la cantidad a surtir.
//					totalQty += linea.getTargetQty().doubleValue();
//				}
//			}
//
//
//			if (totalQty <= 0.0){
//				//errores.add(Utilerias.getMsg(getCtx(), "error.traspasos.linesSurtir0"));
//			}
//
//// Lama: se comenta porque YA lleva tipo de documento el M_Movement no es necesario validar esto
////			final List<org.apache.struts.util.LabelValueBean> tipoDoc = MEXMEDocType.getTipoDocMov(ctx, null);
////			/* DatosTraspasos.getTipoDocMov(
////			 * Env.getContextAsInt(ctx, "#AD_Client_ID"),
////			 * Env.getContextAsInt(ctx, "#AD_Org_ID"),
////			 * Env.getContext(ctx, "#AD_Language")); */
////
////			MDocType docType = null;
////
////			if (!tipoDoc.isEmpty()) {
////				// asignamos el id del almacen (el primero de la lista)
////				docType = new MDocType(ctx, Integer.valueOf(tipoDoc.get(0).getValue()), null);
////			}
////
////			// validar que el tipo de documento este en transito
////			if (docType == null) {
////				//errores.add(Utilerias.getMsg(getCtx(), "error.traspasos.noTipoDoc"));
////			}
////			else if (!docType.isInTransit()) {
////			//	errores.add(Utilerias.getMsg(getCtx(), "error.traspasos.tipoDoc"));
////			}
//
//			// validar que el periodo se encuentre abierto
//			boolean isOpen =
//				MPeriod.isOpen(ctx, mov.getMovementDate(), MDocType.DOCBASETYPE_MaterialMovement, Env.getAD_Org_ID(ctx));
//
//			if (!isOpen) {
//			//	errores.add(Utilerias.getMsg(getCtx(), "error.traspasos.perNoAbierto"));
//			}
//
//		}
//		catch (Exception e) {
//			//log.log(Level.SEVERE, "-- validate", e);
//			//errores.add(Utilerias.getMsg(getCtx(), "errores.login.novalido"));
//		}
//
//		return errores;
//	}

	/**
	 * @deprecated
	 */
	public List<MMovementLine> getMovementList(List<MMovementLine> list) {

		List<MMovementLine> listResult = new ArrayList<MMovementLine>();

		try {
			for (final MMovementLine obj : list) {
//				if (obj instanceof MMovementLine) {
					processMovementLine(listResult, obj);
					// Lama: si la lista es de tipo MMovementLine no puede ser mexme_pedidobasedet
//				} else if(obj instanceof MEXME_PedidoBaseDet){
//					processPedidoBase(listResult, (MEXME_PedidoBaseDet)obj);
//				}
			}
		} catch (final IllegalArgumentException e) {
			//FDialog.error(0, e.getMessage());
			slog.warning(e.getMessage());
			list = null;
		} catch (final Exception e) {
			slog.log(Level.WARNING, "", e);
		}

		return list;
	}


	/**
	 *
	 * @param list
	 * @param movLine
	 * @throws IllegalArgumentException
	 * @deprecated
	 */
	private void processMovementLine(final List<MMovementLine> list, final MMovementLine movLine)
			throws IllegalArgumentException {

		final MMovementLine line = movLine;
		final MProduct prod = MProduct.get(getCtx(), line.getM_Product_ID());
		String errMsg = null;

		//se establecen las demas cantidades en cero pq no se trata de una devolucion
		line.setTargetQty_Vol(BigDecimal.ZERO);
		line.setMovementQty_Vol(BigDecimal.ZERO);
		line.setTargetQty(BigDecimal.ZERO);
		line.setMovementQty(BigDecimal.ZERO);

		if (line.getC_UOM_ID() == line.getC_UOMVolume_ID()) {
			line.setOriginalQty_Vol(line.getQuantityUser());
			line.setOriginalQty(line.getQuantityUser());
		} else {
			//verificamos si la cantidad se encuentra en unidad minima o de volumen
			if (line.getOp_Uom() == line.getC_UOMVolume_ID()) { //se encuentra en unidad de volumen

				line.setOriginalQty_Vol(line.getQuantityUser());

				//calculamos la cantidad minima
				BigDecimal qtyMin = MEXMEUOMConversion.convertProductFrom (getCtx(), prod.getM_Product_ID(),
						line.getC_UOMVolume_ID(), line.getQuantityUser(), null);

				if (qtyMin == null) {
					errMsg = Utilerias.getMsg(getCtx(), "error.udm.noExisteConversion") + " " + prod.getName();
					throw new IllegalArgumentException(errMsg);
				} else {
					qtyMin =
							qtyMin.setScale(
									MUOM.getPrecision(getCtx(), prod.getC_UOM_ID()),
									BigDecimal.ROUND_HALF_UP
									);
					line.setOriginalQty(qtyMin);
				}

			} else { //se encuentra en unidad minima

				line.setOriginalQty(line.getQuantityUser());

				//calculamos la cantidad de volumen
				BigDecimal qtyMVol =
						MEXMEUOMConversion.convertProductTo (
								getCtx(),
								prod.getM_Product_ID(),
								line.getC_UOMVolume_ID(),
								line.getQuantityUser(),
								null
								);

				if (qtyMVol == null) {
					errMsg = Utilerias.getMsg(getCtx(), "error.udm.noExisteConversion") + " " + prod.getName();
					throw new IllegalArgumentException(errMsg);
				} else {
					qtyMVol =
							qtyMVol.setScale(
									MUOM.getPrecision(getCtx(), prod.getC_UOMVolume_ID()),
									BigDecimal.ROUND_HALF_UP
									);
					line.setOriginalQty_Vol(qtyMVol);
				}
			}
		}

//		if (searchCtaPac.getSelectedId() > 0) {
//			final MPrecios precios = GetPrice.getPriceMov(getCtx(), line, null);
//
//			if(!MEXMEConfigPre.aplicarCargoSinPrecio(precios.getPriceList())){
//				errMsg =
//						Utilerias.getMsg(getCtx(),
//								Utilerias.getMsg(getCtx(),
//										"error.servicios.noPrice",
//										prod.getValue() + " - " + prod.getName()
//										)
//								);
//				throw new IllegalArgumentException(errMsg);
//			}
//		}

		list.add(line);
	}

	/**
	 * Metodo para realizar la confirmacion
	 * de la solicitud de productos al almacén
	 * solicitante
	 * @see EXME_ConfigEC.surteconfirma = 'Y'
	 *  @deprecated codigo copiado de WSurtidoProdDet
	 **/
	private String confirm() {
		final StringBuilder msgCharge = new StringBuilder();
//		try {
//			MConfigEC config = MConfigEC.get(getCtx());
//			MMovementConfirm confirma = MMovementConfirm.get(getCtx(), getM_Movement_ID(), null);
//			MMovement movement = new MMovement(getCtx(), getM_Movement_ID(), null);
//			final Traspasos traspaso = new Traspasos(getCtx(), getM_Movement_ID());
//
////			boolean success = true;
//			String line = "\n ";
//
//			final List<String> errors = traspaso.confirmComplete(
//					movement.getConfirmaList(
//							getMovementConfirmDet(
//							confirma.getM_MovementConfirm_ID(), true)
//
//							),
//					confirma.getM_MovementConfirm_ID());
//
//			if (errors.isEmpty()) {
//				msgCharge.append(Utilerias.getMsg(getCtx(), "confirma.procesada"))
//				.append(line);
//				if (traspaso.getMov().getEXME_CtaPac_ID() > 0) {
//					if (config.isAplicaPedCtaPac()) {
//						slog.info("Surtido/Confirmacion con cuenta paciente: "
//								+ traspaso.getMov().getCtaPac().getDocumentNo());
//						// Principio de mensaje Cargos a la Cuenta Paciente
//						msgCharge.append(Utilerias.getMsg(getCtx(), "msj.cargos"))
//						.append(": ")
//						.append(traspaso.getMov().getCtaPac().getDocumentNo())
//						.append(line);
//						// Crear los cargos
//						if (traspaso.crearCargos(traspaso.getMov(),false)) {
//							// Exito, Los cargos al paciente han sido generados
//							msgCharge.append(Utilerias.getMsg(getCtx(), "msj.cargosGenerados"));
//						} else {
//							// Error, cargos no generados
//							msgCharge.append(Utilerias.getMsg(getCtx(), "cargosDiarios.cargosNoGenerated"));
//							msgCharge.append(": ").append(line);
//							msgCharge.append(
//									getMessages(traspaso.getErrores()));
////							success = false;
//						}
//
////						if (success) {
////							Utils.info(msgCharge.toString());
////						} else {
////							Utils.error(msgCharge.toString(), null);
////						}
//
//					}
//
//				} else {
//					slog.info("Es una CONFIRMACION DocMov: "+ traspaso.getMov().getDocumentNo()
//							+ " No hay cuenta paciente ");
//				}
//
//			}else {
////				final StringBuilder msgError = new StringBuilder();
//				msgCharge.append(Utilerias.getMsg(getCtx(), "error.pedidoAuto.noUpdateMovLineConfirm	"))
//				.append(line);
//				for (String error : errors) {
//					msgCharge.append(error);
//					msgCharge.append(Constantes.NEWLINE);
//				}
////				Utils.error(msgError.toString(), null);
//			}
//
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, e.getLocalizedMessage());
//		}
		return msgCharge.toString();
	}
//	 /** @deprecated codigo copiado de ViewErrors */
//	public StringBuilder getMessages(List<ModelError> lstModel) {
//		// Mesaje por tipo de error
//		StringBuilder errorMsg = new StringBuilder();
//		try {
//			if (lstModel != null) {
//				// Itera la lista
//				for (ModelError error : lstModel) {
//					errorMsg.append(getMsg(error)).append("\n ");
//				}
//			}
//		} catch (Exception e) {
//			//error(e);
//		} finally {
//			lstModel.clear();
//		}
//		return errorMsg;
//	}

//	/** @deprecated codigo copiado de ViewErrors */
//	public String getMsg(ModelError error){
//		Object[] errorArray = null;
//		if (error.getParam() != null && error.getParam().length > 0) {
//			errorArray = error.getParam();
//		}
//		// Mensaje
//		String msg = null;
//		if (error.isRequiereTraduccion()) {
//			msg = Utilerias.getMsg(getCtx(), error.getMensaje());
//		} else {
//			msg = error.getMensaje();
//		}
//
//		// Damos formato al mensaje
//		MessageFormat formatter = new MessageFormat(msg);
//		formatter.setLocale(org.compiere.util.Env.getLanguage(getCtx()).getLocale());
//		msg = formatter.format(errorArray);
//
//		return msg;
//	}


//	/**
//	 *  Obtenemos el detalle para un movimiento pendiente de confirmar
//	 *
//	 *  @param movementConfirmID el movimiento pendiente
//	 *  @param isWeb si el entorno de llamado es a traves de MedSys
//	 *  @return Una lista con los movimientos pendientes de confirmaro
//	 *  @deprecated codigo copiado de ZkConfirmaDetView
//	 */
//	public static List<ConfirmaDetView> getMovementConfirmDet(long movementConfirmID, boolean isWeb) throws Exception {
//		List<ConfirmaDetView> lista = new ArrayList<ConfirmaDetView>();
//		String sql = MMovementConfirm.queryMovementConfirmDet(isWeb);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql, null);
//			pstmt.setLong(1, movementConfirmID);
//			rs = pstmt.executeQuery();
//
//			slog.fine(sql + "   movementConfirmID: " + movementConfirmID + "\n\n");
//
//			int i=1;
//			while (rs.next()){
//				ConfirmaDetView confirma = new ConfirmaDetView();
//				confirma.setRSet(rs, isWeb, i);
//				lista.add(confirma);
//				i++;
//			}
//		} catch (SQLException e) {
//			slog.log(Level.SEVERE, "", e);
//			throw e;
//		} finally {
//			DB.close(rs,pstmt);
//			rs = null;
//			pstmt = null;
//		}
//		return lista;
//	}

//	/**
//	 * Confirma el movimiento entre almacenes,
//	 * si esta configurado que la confirmación genere cargos, los crea y hace la salida de inventario de los mismo
//	 * @param movementConfirmId
//	 * @param isSterilization
//	 * @param innerList
//	 * @return
//	 * @throws Exception
//	 * @deprecated
//	 * Rediseño: card #1211 {@link MMovementConfirm#confirm(Properties, MMovement, List, String)}
//	 */
//	public StringBuilder confirm(final int movementConfirmId, final boolean isSterilization, final List<?> innerList) throws Exception{
//		final StringBuilder msgError = new StringBuilder();
////		MConfigEC config = MConfigEC.get(getCtx(), null);
//		final Traspasos traspaso = new Traspasos(getCtx(), getM_Movement_ID());
//
//		if (movementConfirmId > 0) {
//			// Clase de proceso de traspasos entre almacenes
//
//			if(traspaso.getMov()==null){
//				msgError.append(" El movimiento aun no ha sido surtido o ya esta confirmado").append(Constantes.NEWLINE);
//
//
//			} else {
//				// Crear la confirmacion y si tiene cuenta los cargos
//				final List<String> errors = traspaso.confirmComplete(
//						getConfirmaList(innerList)
//						, movementConfirmId);
//
//				// si todo es correcto hacer los cargos a la cuenta paciente
//				if (errors.isEmpty()) {
//					//si el pedido NO es de charolas
//					if (!isSterilization//
//						&& !isDevol()//
//						&& getEXME_CtaPac_ID() > 0 //
//						&& getEXME_PrescRXDet_ID() < 1//
//						&& !getLines().isEmpty() //
//						&& MConfigEC.get(getCtx()).isAplicaPedCtaPac()) {
//						// Hacer los cargos a la cuenta paciente
//						// Insertar cargos a la cuenta paciente bajo las condiciones
//						// de la confirmación de traspasos entre almacenes
//						String error = insertCharges(null, updateQtyCharge());
//						if (StringUtils.isNotEmpty(error)) {
//							msgError.append(error).append(Constantes.NEWLINE).append(Utilerias.getMsg(getCtx(), "imposible generar cargos"));
//
//						} else if (noRecordsCtaPacDet > 0) {
//							msgChargesOk = Utilerias.getMsg(getCtx(), "msj.cargosGenerados");
//						}
//					}
//				} else {
//					for (final String error : errors) {
//						msgError.append(error);
//						msgError.append(Constantes.NEWLINE);
//					}
//				}
//			}
//		} else {
//			msgError.append(" El movimiento aun no ha sido surtido").append(Constantes.NEWLINE);
//		}
//
//		return msgError;
//	}

	/** Insertar cargos a la cuenta paciente bajo las condiciones de traspasos
	 * @param destino Nuevo movimiento
	 * @param map Mapa de lineas
	 * @return mensajes de error
	 * @throws Exception
	 * @deprecated  No debe usarse un Map, ya que en caso de existir 2 MovementLine con el mismo producto habria error
	 */
	public String insertCharges(final MMovement destino, final HashMap<Integer, MMovementLine> map) throws Exception{
		boolean success = true;
		noRecordsCtaPacDet = 0;//Lama: cargos 2014
		final CreateCharge mCreateCharge = new CreateCharge(getCtx(), //
			getMAlmSolic().getM_Warehouse_ID(), //
			MEstServAlm.getEstServId(getCtx(), getMAlmSolic().getM_Warehouse_ID(), null),//
			0);
		try {
			setProdOrTray(true);
//			mCreateCharge.setWarehouseID(getMAlmSolic().getM_Warehouse_ID());
//			mCreateCharge.setEstservID(MEstServAlm.getEstServ(getCtx(), getMAlmSolic().getM_Warehouse_ID(), null).getEXME_EstServ_ID());
			noRecordsCtaPacDet = mCreateCharge.insertMovementCharges(map,isProdOrTray);

		} catch (Exception ee) {
			success = false;
			throw ee;

		} finally {
			if(destino!=null && (!success || !mCreateCharge.getErrores().isEmpty())){
				destino.voidIt();
				destino.save();
			}
		}
		return ModelError.getMsgError(getCtx(), mCreateCharge.getErrores());
	}

	/** Actualiza la cantidad de la linea a cargar solo confirmación de productos
	 * @throws Exception si no hay suficiente existencia
	 * @deprecated
	 * - No debe usarse un Map, ya que en caso de existir 2 MovementLine con el mismo producto habria error
	 * - El objeto BeanView no cuenta con documentacion
	 * - Este codigo esta duplicado en {@link org.compiere.model.bpm.ConfirmTransferOrder#crearCargos(MConfigEC, boolean, MMovement, List)}
	 * - Porque se validan las existencias, pero al crear el cargo no ??
	 * */
	private HashMap<Integer, MMovementLine> updateQtyCharge() throws Exception {
		final HashMap<Integer, MMovementLine> map = new HashMap<Integer, MMovementLine>();
		for (final MMovementLine line : getLines()) {

			BigDecimal qty = BigDecimal.ZERO;
			if (line.getOp_Uom() == 0 || line.getOp_Uom() == line.getProduct().getC_UOM_ID()) {
				qty = line.getConfirmedQty().add(line.getScrappedQty());// qty = line.getTargetQty();

			} else if (line.getProduct().getC_UOMVolume_ID() == line.getOp_Uom()) {
				qty = line.getTargetQty_Vol();// qty = line.getConfirmedQty_Vol().add(line.getScrappedQty_Vol());//TODO SERA ESTA LA QUE DEBE ESTAR
												// COMENTADA??

			}
			// Validamos la cantidad reservada del almacen que surtio, puesto que ya no esta disponible
			final BigDecimal qtyAvailable =
				MStorage.getQtyAvailable(MWarehouse.getFromLocator(getCtx(), line.getM_LocatorTo_ID(), null).getM_Warehouse_ID(), line
					.getProduct().getM_Product_ID(), line.getM_AttributeSetInstance_ID(), null);

			// Cantidad disponible es menor a la cantidad de
			if (qtyAvailable == null || qtyAvailable.compareTo(line.getTargetQty()) < 0) {
				throw new MedsysException(Utilerias.getAppMsg(getCtx(), "error.encCtaPac.noExistenProd", String.valueOf(line.getLine())));
			}

			line.setBeanView(qty);
			if (qty.compareTo(BigDecimal.ZERO) > 0) {
				map.put(line.getM_Product_ID(), line);
			}
		}
		return map;
	}

	/** Mensajes desde cargos a cuenta paciente */
	public String getMsgChargesOk(){
		return msgChargesOk;
	}

	/** Numero de cargos creados */
	public int getNoRecordsCtaPacDet() {
		return noRecordsCtaPacDet;
	}
	public void setNoRecordsCtaPacDet(int noRecordsCtaPacDet) {
		this.noRecordsCtaPacDet = noRecordsCtaPacDet;
	}
	/**
	 * TODO avance pedidos internos
	 * @param ctx
	 * @param warehouseId
	 * @param docNo
	 * @param date
	 * @param date2
	 * @param status
	 * @return
	 */
	public static List<MMovement> getRequest(Properties ctx, int warehouseId, String docNo, Timestamp date, Timestamp date2, String status) {
		List<MMovement> list = new ArrayList<MMovement>();

		List<Object> params = new ArrayList<Object>();

		params.add(warehouseId);

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  m.* ");
		sql.append("FROM ");
		sql.append("  m_movement m ");
		sql.append("WHERE ");
		sql.append("  m.m_warehouseto_id = ? AND ");

		if (StringUtils.isNotBlank(docNo)) {
			sql.append("  lower(m.documentno) = ? AND ");
			params.add(docNo.toLowerCase());

			sql.append("  m.docstatus in (?,?,?) AND ");

			params.add(X_M_Inventory.DOCSTATUS_Drafted);
			params.add(X_M_Inventory.DOCSTATUS_InProgress);
			params.add(X_M_Inventory.DOCSTATUS_Voided);
		} else {
			params.add(date);
			params.add(date2);
			sql.append("  m.movementdate BETWEEN ? AND ? AND ");

			if (StringUtils.isNotBlank(status)) {
				if ("XXX".equals(status)) {
					sql.append("  m.requested = ? AND ");
					params.add("Y");

					status = DOCSTATUS_Drafted;
				} else if (DOCSTATUS_Drafted.equals(status)) {
					sql.append("  m.requested = ? AND ");
					params.add("N");
				}

				sql.append("  m.docstatus = ? AND ");
				params.add(status);
			}
		}

		sql.append("  m.isactive = 'Y' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "m"));

		sql.append(" ORDER BY ");
		sql.append("  m.documentno desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MMovement(ctx, rs, null));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 *
	 * @param ctx
	 * @param warehouseId
	 * @param docNo
	 * @param date
	 * @param date2
	 * @return
	 */
	public static List<MMovement> getRequested(Properties ctx, int warehouseId, String docNo,boolean historicos, Timestamp date, Timestamp date2) {
		List<Object> params = new ArrayList<Object>();

		params.add(warehouseId);
		if (historicos) {
			params.add(DOCSTATUS_InProgress);
			params.add(DOCSTATUS_Completed);
		} else {
			params.add(DOCSTATUS_Drafted);
		}
		StringBuilder sql = new StringBuilder();

		sql.append("  m_movement.m_warehouse_id = ? AND ");
		sql.append("  m_movement.requested = 'Y' AND ");
		if(historicos){
			sql.append("  m_movement.docstatus in (?,?) ");
		}else{
			sql.append("  m_movement.docstatus = ? ");	
		}
		if (StringUtils.isNotBlank(docNo)) {
			sql.append("  AND lower(m_movement.documentno) = ? ");
			params.add(docNo.toLowerCase());
		}else{
			params.add(date);
			params.add(date2);
			sql.append(" AND m_movement.movementdate BETWEEN ? AND ?  ");
		}

		Query query = new Query(ctx, MMovement.Table_Name, sql.toString(), null);
		query.setParameters(params);
		query.setOnlyActiveRecords(true);
		query.addAccessLevelSQL(true);
		query.setOrderBy(" m_movement.documentno desc ");
		return query.list();
	}

	private MWarehouse warehouseTo;

	public MWarehouse getWarehouseTo() {
		if (warehouseTo == null) {
			warehouseTo = new MWarehouse(getCtx(), getM_WarehouseTo_ID(), null);
		}
		return warehouseTo;
	}

	private MWarehouse warehouse;

	public MWarehouse getWarehouse() {
		if (warehouse == null) {
			warehouse = new MWarehouse(getCtx(), getM_Warehouse_ID(), null);
		}
		return warehouse;
	}

	/**
	 * Prioridad en entero para ordenado en listas
	 *
	 * @return
	 */
	public int getPriority() {
		return Integer.parseInt(getPriorityRule());
	}

	/**
	 * Surtir pedido
	 *
	 * @param ctx
	 *            Contexto de app
	 * @param movement
	 *            Movimiento (Encabezado)
	 * @param lines
	 *            Líneas de movimiento
	 * @param trxName
	 *            Nombre de trx
	 * @return ErrorList
	 */
	public static ErrorList deliver(Properties ctx, MMovement movement, List<MovementLine> lines, String trxName) {
		ErrorList errorList = new ErrorList();

		if (MMovement.DOCSTATUS_InProgress.equalsIgnoreCase(movement.getDocStatus())) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(ctx, "error.traspasos.isSurtido2", movement.getDocumentNo()));

		} else {
			MConfigEC configEC = MConfigEC.find(ctx, null);

			// validamos que la cuenta paciente este activa en caso de que exista
			if (movement.getEXME_CtaPac_ID() > 0) {
				if (new MEXMECtaPac(ctx, movement.getEXME_CtaPac_ID(), null).getFechaCierre() != null) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(ctx, "error.traspasos.ctaPac"));
				}
			}

			if (errorList.isEmpty()) {
				if (lines.isEmpty()) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(ctx, "error.traspasos.lines"));
				} else {
					boolean isOpen = MPeriod.isOpen(ctx, Env.getCurrentDate(), MDocType.DOCBASETYPE_MaterialMovement, Env.getAD_Org_ID(ctx));

					if (!isOpen) {
						errorList.add(Error.CONFIGURATION_ERROR, Utilerias.getAppMsg(ctx, "error.traspasos.perNoAbierto"));
					} else {
						if (MMovement.DOCSTATUS_Voided.equalsIgnoreCase(movement.getDocStatus())) {
							errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(ctx, "error.traspasos.isCancelado", movement.getDocumentNo()));
						} else {
							boolean stockControl = MEXMEMejoras.inventories(ctx);

							// en caso que requiera backorder
							final List<MovementLine> backorderLines = new ArrayList<MovementLine>();

							double qty = 0;

							for (MovementLine movementLine : lines) {
								qty += movementLine.getTargetQty().doubleValue();
								int productId = movementLine.getProduct().getM_Product_ID();
								int attSetInstId = movementLine.getLotId();
								BigDecimal targetQty = movementLine.getTargetQty();
								int warehouseId = movement.getM_Warehouse_ID();
								int locatorId = movementLine.getLocatorFromId();
								int lotId = movementLine.getLotId();

								if (stockControl && movement.getWarehouse().isControlExistencias()) {
									if (movementLine.getTargetQty().compareTo(BigDecimal.ZERO) > 0) {
										if (!MStorage.canSupply(ctx, productId, locatorId, lotId, targetQty, trxName)) {
											errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(ctx, "msj.noHayExistenciasExt", //
													movementLine.getProduct().getName(), //
													movementLine.getLocatorFromName(), //
													StringUtils.defaultIfEmpty(movementLine.getLotName(), "(" + Utilerias.getMsg(ctx, "msj.sinLote") + ")")));
										}
									}

									if (errorList.isEmpty()) {
										// MStorage para almacen que surte. Solamente se le suma la cantidad surtida a la columna qtyReserved
										MStorage.add(ctx, warehouseId, locatorId, productId, attSetInstId, attSetInstId, BigDecimal.ZERO, targetQty, BigDecimal.ZERO, trxName);
									}
								}

								if (configEC.isSurteConfirma() || (movement.getM_Warehouse_ID() == movement.getM_WarehouseTo_ID())) {
									movementLine.setConfirmedQty(movementLine.getTargetQty());
									movementLine.setConfirmedVolQty(movementLine.getTargetVolQty());
								}

								// si Genera Back Order y si hay diferencias entre la cantidad surtida y la cantidad conformada

								// Si no hay errores, está configurado que genere BO, el que surte no es consigna y hay diferencias
								if (errorList.isEmpty()
									&& configEC.isGeneraBO()
									&& !movement.isBackorder()
									&& !movement.isDevol()
									&& !movement.getWarehouse().isConsigna()
									&& movementLine.getDifferenceQty().compareTo(BigDecimal.ZERO) > 0) {
									backorderLines.add(movementLine);
								}
							}

							if (qty <= 0) {
								errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(ctx, "error.traspasos.linesSurtir0"));
							}
                            
							movement.setDeliveryDate(Env.getCurrentDate());
							movement.setDateReceived(Env.getCurrentDate());//
							movement.startWorkflow(errorList, DocAction.ACTION_Prepare, DocAction.STATUS_InProgress, trxName);

							if (errorList.isEmpty() && !movement.isDevol()
									&& (configEC.isSurteConfirma() || (movement.getM_Warehouse_ID() == movement.getM_WarehouseTo_ID()))) {
								errorList.getList().addAll(
										MMovementConfirm.confirm(ctx, movement, lines, trxName).getList());
							}

							// Generar backorder
							if (errorList.isEmpty() && !backorderLines.isEmpty()) {
								errorList.getList().addAll(createBackorder(ctx, movement, backorderLines, trxName).getList());
							}
						}
					}
				}
			}
		}

		return errorList;
	}

	/**
	 * Solicitar pedido
	 *
	 * @param ctx
	 *            Contexto
	 * @param movement
	 *            Movimiento (Encabezado)
	 * @param lines
	 *            Líneas del movimiento
	 * @param trxName
	 *            Nombre de trx
	 * @return ErrorList
	 */
	public static ErrorList request(Properties ctx, MMovement movement, List<MovementLine> lines, String trxName) {
		ErrorList errorList = new ErrorList();

		// Si el movimiento no fue previamente solicitado
		if (!movement.isRequested()) {
			if (lines == null || lines.isEmpty()) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(ctx, "error.traspasos.lines"));
			} else {
				movement.setRequested(true);

				// guardamos el encabezado
				if (movement.save(trxName)) {

					HashMap<Integer, List<MovementLine>> consigna = new HashMap<Integer, List<MovementLine>>();

					// Si el almacen que surte no es consigna
					if (!movement.getWarehouse().isConsigna()) {
						// navegar por las lineas
						for (MovementLine line : lines) {
							MEXMEProductoOrg productoOrg = MEXMEProductoOrg.getProductoOrg(ctx, line.getProduct().getM_Product_ID(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), null);

							// si el producto esta en la organización y es de consigna
							if (productoOrg != null && productoOrg.isConsigna()) {
								int[] arr = MWarehouse.getWarehouseRel(ctx, movement.getM_Warehouse_ID(), line.getProduct().getM_Product_ID(), null);
								int almacenConsigna = arr[0];
								// Si encontró almacen de consigna
								if (almacenConsigna > 0) {
									MWarehouse ware = MWarehouse.get(ctx, almacenConsigna);
									try {
										BigDecimal qtyRequestedMin = line.getOriginalQty();
										// Si el almacen que no es consigna tiene la cantidad a surtir, continua
										// Ver primero existencias en el almacen que surte (Se manda el lote por si viene de facturación)
										BigDecimal qtyAv =
											MStorage.getQtyAvailable(movement.getM_Warehouse_ID(), //
												line.getProduct().getM_Product_ID(),//
												// si el producto es lote, y tiene 0, buscar en cualquier lote
												line.getLotId() == 0 && productoOrg.isLot() ? -1 : line.getLotId(), //
												null);

										// Si la cantidad disponible es diferente de nula y mayor a 0
										if (qtyAv != null && qtyAv.compareTo(BigDecimal.ZERO) > 0) {
											// Si la cantidad disponible es igual o mayor a la solicitada no pedir
											if(qtyAv.compareTo(qtyRequestedMin) >= 0) {
												continue;
											} else {
												// Si la disponible es menor, sacar la diferencia
												qtyRequestedMin = qtyRequestedMin.subtract(qtyAv);
											}
										}

										List<MovementLine> newLines;
										if (consigna.containsKey(almacenConsigna)) {
											newLines = consigna.get(almacenConsigna);
										} else {
											newLines = new ArrayList<MovementLine>();
											consigna.put(almacenConsigna, newLines);
										}
										// sies virtual y controla existencias
										if (ware.isVirtual() && ware.isControlExistencias()) {
											// revisamos los localizadores para la cantidad restante
											List<MStorage> storageLines = MStorage.getList(ctx, almacenConsigna,//
												line.getProduct().getM_Product_ID(),//
												line.getLotId() == 0 && productoOrg.isLot() ? -1 : line.getLotId(), //
												null);
											// buscamos los localizadores que tienen las existencias para
											// dividir las lineas
											for (MStorage locator : storageLines) {
												BigDecimal qtyAvailable = locator.getQtyAvailable();
												BigDecimal qtyRequested;
												// si el localizador tiene menos
												if (qtyAvailable.compareTo(qtyRequestedMin) < 0) {
													qtyRequested = qtyAvailable;
												} else {// mas o igual
													qtyRequested = new BigDecimal(qtyRequestedMin.intValue());
												}
												final MovementLine newLine = createLine(ctx, line, qtyRequested);
												newLine.setLocatorFromId(locator.getM_Locator_ID());// virtual
												newLines.add(newLine);
												newLine.setLotId(locator.getM_AttributeSetInstance_ID());
												// nueva cantidad pendiente
												qtyRequestedMin = qtyRequestedMin.subtract(qtyAvailable);
												if (qtyRequestedMin.compareTo(BigDecimal.ZERO) <= 0) {
													break;
												}
											}// finFor Storage:Lines

											// si aun hay pendientes, se hace la solicitud
											if (qtyRequestedMin.compareTo(BigDecimal.ZERO) > 0) {
												final MovementLine newLine = createLine(ctx, line, qtyRequestedMin);
												newLine.setLocatorFromId(arr[1]);// virtual - def
												newLines.add(newLine);
											}
										} else {
											// si el almacen es consigna fisico se hace la solicitud y espera el surtido manual.
											// si el almacen es consigna virtual y no controla existencias
											// se hace la solicitud al localizador default
											final MovementLine newLine = createLine(ctx, line, qtyRequestedMin);
											final int locatorId;
											if (line.getLotId() > 0) { // TODO??? ¿sucede este caso? VALIDAR!
												List<MLocator> list = MLocator.getLocators(ctx, // entro aki es correcto ? VALIDAR?!
													almacenConsigna, line.getProduct().getM_Product_ID(), line.getLotId(), null);
												if (list.isEmpty()) {
													locatorId = arr[1];// default
												} else { // el primer localizador para ese lote
													locatorId = list.get(0).getM_Locator_ID();
												}
											} else {
												locatorId = arr[1];// default
											}
											newLine.setLocatorFromId(locatorId);// default
											newLines.add(newLine);
										}
									} catch (Exception e) {
										errorList.add(Error.EXCEPTION_ERROR, Utilerias.getAppMsg(ctx, "error.locatorID") + Constantes.SPACE + ware.getName());
										slog.log(Level.SEVERE, null, e);
										break;
									}
								} // finIf almacenConsigna > 0
							} // finIf isConsigna
						}
					}

					// Si no hay errores
					if (errorList.isEmpty()) {
						Set<Integer> set = consigna.keySet();

						// Recorrer líneas para generar nuevos pedidos
						for (int almacenConsigna : set) {//10001606
							MWarehouse ac = new MWarehouse(ctx, almacenConsigna, null);

							MMovement newMovement = new MMovement(ctx, almacenConsigna, movement.getM_Warehouse_ID());
							newMovement.setDescription(movement.getDescription());
							newMovement.setPriorityRule(movement.getPriorityRule());
							newMovement.setRequested(false);
							newMovement.setParent_Movement_ID(movement.getM_Movement_ID());

							if (newMovement.save(trxName)) {
								List<MovementLine> list = consigna.get(almacenConsigna);

								for (MovementLine line : list) {
									line.setMovementId(newMovement.getM_Movement_ID());

									MMovementLine newLine = new MMovementLine(newMovement);

									MovementLine.fillRequest(line, newLine);

									// llenar targetQty y targetQtyVol si es almacen en consigna virtual
									if (ac.isVirtual()) {
										newLine.setTargetQty(line.getOriginalQty());
										newLine.setTargetQty_Vol(line.getOriginalVolQty());
										newLine.setConfirmedQty(line.getOriginalQty());
										newLine.setConfirmedQty_Vol(line.getOriginalVolQty());
										newLine.setMovementQty(line.getOriginalQty());// Necesarias para surtir en automatico
										newLine.setMovementQty_Vol(line.getOriginalVolQty());// Necesarias para surtir en automatico
									}

									if (newLine.save(trxName)) {
										line.setId(newLine.getM_MovementLine_ID());
									} else {
										errorList.add(Error.EXCEPTION_ERROR, Utilerias.getAppMsg(ctx, "error.solicitudProducto", line.getProduct().getName()));
										String msgSave = new MedsysException().getLocalizedMessage();
										if(StringUtils.isNotEmpty(msgSave)) {
											errorList.add(Error.EXCEPTION_ERROR, msgSave);
										}
										break;
									}

								}

								if (errorList.isEmpty()) {
									list = getLines(ctx, newMovement.getM_Movement_ID(), trxName);

									errorList = MMovement.request(ctx, newMovement, list, trxName);

									if (errorList.isEmpty() && ac.isVirtual()) {
										errorList = deliver(ctx, newMovement, list, trxName);

										if (errorList.isEmpty()&& DocAction.STATUS_InProgress.equals(newMovement.getDocStatus())) {
											errorList = MMovementConfirm.confirm(ctx, newMovement, list, trxName);
										}
									}
								}
								// no debe continuar si hay errores.
								if(!errorList.isEmpty()) {
									break;
								}
							}
						}// finfor
					}
				}
			}
		}
		return errorList;
	}

	private static MovementLine createLine(final Properties ctx, final MovementLine from, final BigDecimal qtyRequested) {
		MovementLine newLine = from.clone();
		newLine.setMovementId(0);
		newLine.setId(0);
		newLine.setLocatorToId(from.getLocatorFromId());//Gral
		newLine.setOriginalQty(qtyRequested);
		// Solamente si es de volumen
		if (newLine.isVolumeSelected()) {
			BigDecimal qtyVol = MEXMEUOMConversion.convertProductTo(ctx,
				from.getProduct().getM_Product_ID(),
				from.getProduct().getC_UOMVolume_ID(),
				newLine.getOriginalQty(), null, true);

			// Si la conversion a volumen es nula o igual a 0, se hace 1
			if (qtyVol == null || qtyVol.compareTo(BigDecimal.ZERO) == 0) {
				qtyVol = BigDecimal.ONE;
			} else {
				// Se redondea al mayor porque no se pueden pedir fracciones
				qtyVol = qtyVol.setScale(0, BigDecimal.ROUND_CEILING);
			}
			newLine.setOriginalVolQty(qtyVol);
			// el metodo MovementLine.fillRequest asume que en cantidad original
			// va el número que el usuario ingreso, por eso se hace el cambio
			//NOTA: Caso de prueba solicito 1 caja/5, tengo 2 piezas en almacen propio
			// si la cantidad Pendiente (3), se cambia a 1,
			// en fillrequest la actualiza a 5, solicitando la cantidad original
			newLine.setOriginalQty(newLine.getOriginalVolQty());
		}
		return newLine;
	}

	public static void main(String[] args){
		BigDecimal diff = new BigDecimal(0);

		if(diff == null || diff.compareTo(BigDecimal.ZERO) == 0){
			diff = BigDecimal.ONE;
		}

		diff = diff.setScale(0, BigDecimal.ROUND_CEILING);

		System.out.println(diff);
	}

	/**
	 * Crea la solicitud de servicios BackOrder
	 * crea el nuevo M_Movement y M_MovementLine
	 * @param ctx Contexto
	 * @param originalMovement Solicitud original M_Movement
	 * @param lines Detalle
	 * @param trxName nombre de transaccion
	 * @return Listado de errores
	 */
	public static ErrorList createBackorder(final Properties ctx,
		final MMovement originalMovement, final List<MovementLine> lines, String trxName) {
		ErrorList errorList = new ErrorList();
		try {
			// codigo tomado de ConfirmaDet
			if (MConfigEC.get(ctx).isGeneraBO() //
				&& !originalMovement.isDevol() // el movto no es una devolucion
				&& !originalMovement.isBackorder() // el movto no es un back order
			) {
				// create header
				final MMovement movBackOrdr = new MMovement(ctx, 0, null);
				movBackOrdr.setDescription(Utilerias.getMsg(ctx, "msj.backorder") + " = " + originalMovement.getDocumentNo());
				movBackOrdr.setC_Project_ID(originalMovement.getC_Project_ID());
				movBackOrdr.setC_Activity_ID(originalMovement.getC_Activity_ID());
				movBackOrdr.setC_Campaign_ID(originalMovement.getC_Campaign_ID());
				movBackOrdr.setAD_OrgTrx_ID(originalMovement.getAD_OrgTrx_ID());
				movBackOrdr.setUser1_ID(originalMovement.getUser1_ID());
				movBackOrdr.setUser2_ID(originalMovement.getUser2_ID());
				movBackOrdr.setApprovalAmt(BigDecimal.ZERO);
				movBackOrdr.setEXME_CtaPac_ID(originalMovement.getEXME_CtaPac_ID());
				movBackOrdr.setBackorder(true);
				movBackOrdr.setRef_Movement_ID(originalMovement.getM_Movement_ID());
				movBackOrdr.setM_Warehouse_ID(originalMovement.getM_Warehouse_ID());
				movBackOrdr.setM_WarehouseTo_ID(originalMovement.getM_WarehouseTo_ID());

				// Guardamos el usuario que solicita el backorder - Jesus Cantu
				// TODO:Checar funcionamientos de backOrder (movBackOrdr.setIsApproved(true);)
				final List<MovementLine> linesBo = new ArrayList<MovementLine>();
				if (movBackOrdr.save(trxName)) {
					for (MovementLine line : lines) {
						// si la diferencia es entre lo solicitado y lo surtido //TODO:Checar UOM Min / Vol
						final BigDecimal difference = line.getDifferenceQty();
						if (difference.compareTo(BigDecimal.ZERO) <= 0) {
							continue;
						}
						// si hubo faltantes insertar la linea (solicitado - enviado) o (enviado - confirmado)
						final MovementLine lineBO = new MovementLine();
						final MMovementLine movlineBO = new MMovementLine(movBackOrdr);

						lineBO.setMovementId(movBackOrdr.getM_Movement_ID());
						lineBO.setProduct(line.getProduct());
						lineBO.setSelectedUomId(line.getSelectedUomId());
						lineBO.setOriginalQty(difference);
						lineBO.setDescription(line.getDescription());
						lineBO.setLocatorFromId(line.getLocatorFromId());
						lineBO.setLocatorToId(line.getLocatorToId());

						MovementLine.fillRequest(lineBO, movlineBO);
						movlineBO.setAD_OrgTrx_ID(movBackOrdr.getAD_OrgTrx_ID());
						movlineBO.setEXME_CtaPac_ID(movBackOrdr.getEXME_CtaPac_ID());

						// valida la solicitud
						errorList.getList().addAll(lineBO.validateRequest().getList());
						if (errorList.isEmpty() && !movlineBO.save()) {
							errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(ctx, "error.traspasos.noInsertMov")));
						}
						// si no hubo errores se agrega a la lista
						if (errorList.isEmpty()) {
							linesBo.add(lineBO);
						} else {
							linesBo.clear();
							break;
						}
					}
					// debe de haber lineas
					if (linesBo.isEmpty()) {
						errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(ctx, "error.traspasos.lines")));
					} else { // crea la solicitud
						errorList.getList().addAll(MMovement.request(ctx, movBackOrdr, linesBo, trxName).getList());
					}
				} else {
					errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(ctx, "error.traspasos.noInsertMov")));
				}
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "-- createBackorder", e);
			errorList.add(Error.EXCEPTION_ERROR, e.getMessage());
		}
		return errorList;
	}

	/**
	 * Crea el cargo para el M_MovmentLine
	 *
	 * @param errorList Listado de errores
	 * @param movementLineId Linea
	 * @param trxName Transaccion (si es null, el proceso de cargos creara una)
	 */
	public MCtaPacDet createMovementLineCharge(ErrorList errorList, MovementLine line, String trxName) {
		//Valida que el M_Movement tenga cuenta paciente y no sea devolucion
		MCtaPacDet charge = null;
		if (getEXME_CtaPac_ID() > 0) {
			try {
				setProdOrTray(true);
				final CreateCharge mCreateCharge = new CreateCharge(getCtx(), //
					getM_WarehouseTo_ID(), // El que esta confirmando
					MEstServAlm.getEstServId(getCtx(), getM_WarehouseTo_ID(), null), //
					getEXME_CtaPac_ID());

				final org.compiere.model.bpm.EnterCharge.CtaPacDet det = mCreateCharge.new CtaPacDet();
				if (line.isVolumeSelected()) {
					det.setQtyOrdered(line.getChargeVolQty());
					det.setQtyDelivered(line.getChargeVolQty());
					det.setUoMSale(line.getUomVolId());
				} else {
					det.setQtyOrdered(line.getChargeQty());
					det.setQtyDelivered(line.getChargeQty());
					det.setUoMSale(line.getUomId());

				}
				final MMovementLine movLine = new MMovementLine(getCtx(), line.getId(), trxName);
				movLine.setMovement(this);//Lama: cargos 2014
				det.setAttributeSetInstanceID(movLine.getM_AttributeSetInstance_ID());
				det.setWarehouseId(getM_WarehouseTo_ID());// El que esta confirmando
				if(line.getLocatorToId() > 0) { //Toma el localizador seleccionado en la solicitud
					det.setLocatorId(line.getLocatorToId());
				} else {
					det.setLocatorId(MLocator.getLocatorID(getCtx(), det.getWarehouseId(), null));
				}
				charge = mCreateCharge.getMovementLineCharge(movLine, det, true);
				if (charge != null) {
					final List<MCtaPacDet> lst = new ArrayList<MCtaPacDet>();
					lst.add(charge);//Lama: cargos 2014
					final String str = mCreateCharge.insertMovementCharges(lst, true, trxName);
					if (StringUtils.isNotBlank(str)) {
						errorList.add(new Error(Error.EXCEPTION_ERROR, str));
					}


					if (errorList.isEmpty() && !mCreateCharge.getLstCharges().isEmpty()) {
						final Inventory mInventory = new Inventory(getCtx(), getEXME_CtaPac_ID(), X_EXME_ActPacienteInd.Table_ID);
						if (!mInventory.createLinesInOut(lst, false, trxName)) {
							errorList.add(Error.EXCEPTION_ERROR, mInventory.getErrors().toString());
						} else if(!mInventory.getErrores().isEmpty()) {
							errorList.add(Error.EXCEPTION_ERROR, mInventory.getErrors().toString());
						}
					}
				}
			} catch (Exception e) {
				slog.log(Level.SEVERE, "-- confirm : ", e);
				errorList.add(Error.EXCEPTION_ERROR, e.getMessage());
			}
		}
		return charge;
	}

	/**
	 * Reporte de título para reportes
	 *
	 * @return
	 */
	public String getReportTitle() {
		StringBuilder str = new StringBuilder();

		str.append(Utilerias.getMsg(getCtx(), "citas.serv.prioridad"));
		str.append(": ");
		str.append(MRefList.getListName(getCtx(), X_M_Movement.PRIORITYRULE_AD_Reference_ID, getPriorityRule()));
		str.append("\n ");

		str.append(Utilerias.getMsg(getCtx(), "pedidoAuto.noDoc")).append(": ");
		str.append(getDocumentNo()).append("\n");

		str.append(Utilerias.getMsg(getCtx(), "cancelaServ.msg.fechaOrd"));
		str.append(Utilerias.getMsg(getCtx(), ": "));
		str.append(Constantes.getShortDate(getCtx()).format(DB.convert(getCtx(), getMovementDate())));
		str.append(Utilerias.getMsg(getCtx(), "\n "));

		str.append(Utilerias.getMsg(getCtx(), "msj.almacenTo"));
		str.append(Utilerias.getMsg(getCtx(), ": "));
		str.append(getWarehouseTo().getName());
		str.append(Utilerias.getMsg(getCtx(), Constantes.SPACE));

		str.append(" - ");

		str.append(Utilerias.getMsg(getCtx(), "msj.almacenRes"));
		str.append(Utilerias.getMsg(getCtx(), ": "));
		str.append(getWarehouse().getName());
		str.append(Utilerias.getMsg(getCtx(), "\n "));

		if (getEXME_ProgQuiro_ID() > 0) {
			str.append(Utilerias.getMsg(getCtx(), "ece.quirofano"));
			str.append(Utilerias.getMsg(getCtx(), ": "));
			str.append(getOperatingRoomName());
			str.append(Utilerias.getMsg(getCtx(), "\n "));
		}

		str.append(Utilerias.getMsg(getCtx(), "msj.user.solicita"));
		str.append(Utilerias.getMsg(getCtx(), ": "));
		str.append(getRequestUser().getName());

		if (getSurt_User_ID() > 0) {
			str.append(" - ");
			str.append(Utilerias.getMsg(getCtx(), "msj.user.surte"));
			str.append(Utilerias.getMsg(getCtx(), ": "));
			str.append(getSupplyUser().getName());
		}

		if (getEXME_CtaPac_ID() > 0) {
			str.append(Utilerias.getMsg(getCtx(), "caja.nombrePac"));
			str.append(": ");
			str.append(getCtaPac().getNombre_Pac());
			str.append("\n ");
		}

		return str.toString();
	}

	public static class ReportColumns {
		private String documentNo;
		private Timestamp movementDate;
		private String surte;
		private String solicita;
		private String usuarioSolicita;
		private Timestamp fechaSurtido;


		public String getDocumentNo() {
			return documentNo;
		}
		public void setDocumentNo(String documentNo) {
			this.documentNo = documentNo;
		}
		public Timestamp getMovementDate() {
			return movementDate;
		}
		public void setMovementDate(Timestamp movementdate) {
			this.movementDate = movementdate;
		}
		public String getSurte() {
			return surte;
		}
		public void setSurte(String surte) {
			this.surte = surte;
		}
		public String getSolicita() {
			return solicita;
		}
		public void setSolicita(String solicita) {
			this.solicita = solicita;
		}
		public String getUsuarioSolicita() {
			return usuarioSolicita;
		}
		public void setUsuarioSolicita(String usuarioSolicita) {
			this.usuarioSolicita = usuarioSolicita;
		}
		
		public void getFechaSurtido() {

		}
		public void setFechaSurtido(Timestamp fechaSurtido){
			
		}
	}

	/** No permitir postear el movimiento cuando es traspaso entre almacenes en posteo */
	public void setNotPosted(){
		if(X_M_Movement.DOCSTATUS_Completed.equals(getDocStatus())){
			if(getWarehouse().isConsigna() || getWarehouseTo().isConsigna()){
				setPost(X_M_Movement.POST_Consignment);
			}
		}
	}

	/** Buscar la información para mostrar en pantallas puede regresar un vacio */
	public MovementBean getConfirmationData() {
		if(confirmationData==null){
			final List<MMovement> lst = MMovementConfirm.getMovementsConfirm(getCtx(), getM_Movement_ID());
			if(!lst.isEmpty()){
				confirmationData = lst.get(0).getConfirmationData();
			}
		}
		return confirmationData;
	}

	public void setConfirmationData(final MovementBean mMovementBean) {
		this.confirmationData = mMovementBean;
	}

	public void setReturnMovement(MMovement devolMovement) {
		this.returnMovement = devolMovement;
	}

	public MMovement getReturnMovement() {
		return returnMovement;
	}
	
	public void setReturnMovementList(List<MMovement> returnMovementList) {
		this.returnMovementList = returnMovementList;
	}
	
	public List<MMovement> getReturnMovementList() {
		if (returnMovementList == null) {
			returnMovementList = new ArrayList<>();
		}
		return returnMovementList;
	}

	/** Prioridad traducida */
	public String getPriorityRuleTrl(){
		return MRefList.getListName(getCtx(), X_M_Movement.PRIORITYRULE_AD_Reference_ID, getPriorityRule());
	}

	public MBPartner getBPartner() {
		if(mBPartner==null){
			mBPartner = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		}
		return mBPartner;
	}

	public String getOperatingRoomName() {
		if (getEXME_ProgQuiro_ID() > 0) {
			MEXMEQuirofano quir = new MEXMEQuirofano(getCtx(), getEXME_ProgQuiro().getEXME_Quirofano_ID(), null);
			operatingRoomName = quir.getName();
		}
		return operatingRoomName;
	}


	/**
	 * Devolucion de productos y charolas
	 * @param ctx: Contexto
	 * @param movement: Nuevo movimiento
	 * @param lines: Lineas del nuevo movimiento
	 * @param trxName: Nombre de transaccion
	 * @return ErrorList errores
	 */
	public static ErrorList returnProd(final Properties ctx, final MMovement oldMovement, final List<MovementLine> oldLines, final String trxName) {
		oldMovement.initMapConsigment();
		oldMovement.set_TrxName(trxName);

		// Devolver el movimiento original, es decir de acuerdo a lo que el usuario solicito
		ErrorList errorList = MMovement.returnProd(ctx, oldMovement, oldLines, false, trxName);
		// Devolver material de consigna virtual
		if(errorList.isEmpty() && !oldMovement.getMapConsigment().isEmpty()){
			for (SubMovement key : oldMovement.getMapConsigment()) {

				final MMovement subMov = new MMovement(ctx, key.getMovementId(), trxName);
				subMov.setTrxType(oldMovement.getTrxType());// Pasar el dato de cargos
				errorList = MMovement.returnProd(ctx
						, subMov
						, key.getMapLines()
						, true
						, trxName);
				if (errorList.isEmpty()) {
					if(subMov.getReturnMovement() != null 
						// el almacen que surte es el mismo que devuelve
						&& subMov.getReturnMovement().getM_Warehouse_ID() == oldMovement.getM_WarehouseTo_ID()){
						oldMovement.getReturnMovementList().add(subMov.getReturnMovement());
					}
				} else {
					break;
				}
			}
		}
		return errorList;
	}

	/** Copiar el encabezado del movimiento anterior
	 * @param oldMovement movimiento que se esta copiando
	 */
	public MMovement copyReturnMovement(final String trxName){
		final MMovement newMovement = new MMovement(getCtx(), 0, null);
		PO.copyValues(this, newMovement);
		newMovement.set_ValueNoCheck(X_M_Movement.COLUMNNAME_M_Movement_ID, I_ZERO);
		newMovement.set_ValueNoCheck(X_M_Movement.COLUMNNAME_DocumentNo, null);

		newMovement.setMovementDate(new Timestamp(System.currentTimeMillis()));//Fecha actual
		newMovement.setProcessing(false);
		newMovement.setDocStatus(MMovement.STATUS_Drafted);
		newMovement.setDocAction(MMovement.ACTION_Complete);
		newMovement.setApprovalAmt(BigDecimal.ZERO);
		newMovement.setSoli_User_ID(Env.getAD_User_ID(getCtx()));
		newMovement.setSurt_User_ID(Env.getAD_User_ID(getCtx()));
		newMovement.setConf_User_ID(0);
		newMovement.setIsDevol(true);// Es una devolucion
		newMovement.setAD_OrgTrx_ID(Env.getAD_OrgTrx_ID(getCtx()));

		newMovement.setM_Warehouse_ID(getM_WarehouseTo_ID());// Como es devolucion es alreves
		newMovement.setM_WarehouseTo_ID(getM_Warehouse_ID());// Como es devolucion es alreves

		newMovement.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
		newMovement.setRequested(true);
		newMovement.setBackorder(false);
		return newMovement.save(trxName)?newMovement:null;
	}

	/** Copiar lineas del movimiento anterior
	 * @param innerList Lineas del movimiento original
	 * @return copia de las lineas del nuevo movimiento
	 */
	public final List<MovementLine> copyReturnLines(ErrorList errorList, final MMovement newMovement, final List<MovementLine> innerList) {

		final List<MovementLine> aMovLine = new ArrayList<MovementLine>();
		for (final MovementLine bean : innerList) {

			// Saber si se devolvera
			if(bean.getReturnQty().compareTo(BigDecimal.ZERO)>0){

				// Tiene consigna virtual relacionado
				if(!newMovement.isConsigVirtual() && bean.getRefMovementLine(get_TrxName())!=null && bean.getRefMovementLine(get_TrxName()).getId()>0){
					this.addMovementOnConsignment(bean, bean.getRefMovementLine(get_TrxName()));

				} else {
					// Validar la linea
					ErrorList errorList2 = bean.validateReturn(false, bean.getReturnQty(), !isTrxTypeCharges() && !newMovement.isConsigVirtual() );
					if(errorList2.isEmpty()){

						// Crear registro que sera de la devolucion
						final MMovementLine oldMovLine = new MMovementLine(getCtx(), bean.getId(), get_TrxName());
						if(oldMovLine.copyReturnLines(bean, newMovement)){

							// Inicializar las lineas
							oldMovLine.setReturnQty(BigDecimal.ZERO);
							oldMovLine.setReturnQty_Vol(BigDecimal.ZERO);
							if(oldMovLine.save(get_TrxName())){
								aMovLine.add(bean);
							} else {
								errorList.add(Error.EXCEPTION_ERROR,  new MedsysException().getLocalizedMessage());
							}
						} else {
							errorList.add(Error.EXCEPTION_ERROR,  new MedsysException().getLocalizedMessage());
						}
					} else {
						errorList.getList().addAll(errorList2.getList());
					}
				}
			}//finvalidateline
		}//finfor
		return aMovLine;
	}

	/**
	 * Obtener los movimientos entre almacen propio y almacen de consigna
	 * para generar devoluciones
	 * @param movLine linea del movimiento origina
	 * @param smovLine linea del movimiento al almacen de consigna
	 * @return Mapa para los pedidos que se hacen al almacen virtual
	 */
	public void addMovementOnConsignment(final MovementLine movLine, final MovementLine smovLine){
		// Movimiento original NOTA:El orden importa
		addMapConsigment(movLine);

		// Submovimiento NOTA:El orden importa
		addMapConsigment(smovLine);
	}

	private void addMapConsigment(final MovementLine mLine){
		final int parent = mLine.getMovementId();
		if(mapConsignment.contains(parent)){
			((SubMovement) mapConsignment.get(mapConsignment.indexOf(parent))).getMapLines().add(mLine);

		} else {
			final SubMovement mSubMovement = new SubMovement();
			mSubMovement.setMovementId(parent);
			mSubMovement.getMapLines().add(mLine);
			mapConsignment.add(mSubMovement);
		}
	}

	/**
	 * Devolucion de productos y charolas
	 * @param ctx: Contexto
	 * @param movement: Nuevo movimiento
	 * @param lines: Lineas del nuevo movimiento
	 * @param consigVirtual: true Movimiento en consigna
	 * @param trxName: Nombre de transaccion
	 * @return ErrorList errores
	 */
	public static ErrorList returnProd(final Properties ctx, final MMovement oldMovement, final List<MovementLine> oldLines, final boolean consigVirtual, final String trxName) {

		ErrorList errorList = new ErrorList();
		int count = 0 ;
		slog.log(Level.INFO, "INIT product return process Mov:" + oldMovement.getM_Movement_ID());
		if (oldMovement.validateReturn(errorList, oldLines)) {

			// Crear movimiento de devolucion a partir del movimiento original
			MMovement newMovement = oldMovement.copyReturnMovement(trxName);
			if (newMovement == null) {// FIXME mensaje
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(ctx, "error.caja.anticipo.noSave"));

			} else {
				newMovement.setConsigVirtual(consigVirtual);
				if (oldMovement.copyReturnLines(errorList, newMovement, oldLines).isEmpty()) {
					// No hay lineas que devolver, pero si talvez cargos que hacer
					if (newMovement.delete(true)) {
						newMovement = null;// 
					}
				} else {
					// SURTIR: Crear la solicitud como primer paso de la devolucion
					if (errorList.isEmpty()) {
						errorList = deliver(ctx, newMovement, MMovement.getLines(ctx, newMovement.getM_Movement_ID(), trxName), trxName);

						// CONFIRMAR: Hacer la confirmacion
						if(errorList.isEmpty() && newMovement.isConsigVirtual()) {
							errorList.getList().addAll(MMovementConfirm.confirm(ctx, newMovement, MMovement.getLines(ctx, newMovement.getM_Movement_ID(), trxName), trxName).getList());
						}
					}
				}// FinCopiarLineas

				if (errorList.isEmpty() ){
					// CARGAR: Hacer los cargos a la cuenta paciente si es que es una programacion y no proviene de cargos
					if( oldMovement.getEXME_ProgQuiro_ID()>0
							&& oldMovement.getEXME_CtaPac_ID()>0
							&& !oldMovement.isTrxTypeCharges()
							&& !consigVirtual){
						// No todas las lineas del movimiento original se cargan aunque no tengan cantidad a devolver
						count = oldMovement.insertChargesReturn(errorList, oldMovement, oldLines);

						// Si es una charola se podra cerrar el movimiento original
						// para que no hagan mas devoluciones sobre la misma charola
						if (errorList.isEmpty()) {
							oldMovement.processIt(DocAction.ACTION_Close);
							oldMovement.save();
						}
					} else {
						// Si el movimiento ha sido totalmente devuelto
						if(oldMovement.isReturnedItAll(/*newMovement==null?0:newMovement.getM_Movement_ID(),*/trxName)){
							oldMovement.processIt(DocAction.ACTION_Close);
							oldMovement.save();
						}
					}
				}// FinHacerCargos

				// Resultado
				if (errorList.isEmpty()) {
					oldMovement.setNoRecordsCtaPacDet(count);
					oldMovement.setReturnMovement(newMovement);
					slog.log(Level.INFO, "END product return process Mov new:" //
						+ (newMovement == null ? 0 : newMovement.getM_Movement_ID())
						+ (count > 0 ? " Cargos Generados #" + count : " Sin generacion de cargos "));
				}// FinEnviarMensajes
			}
		}// fin validacion encabezados
		return errorList;
	}

	/** Generar cargos por devolucion de charola
	 * @param errorList: Listado de errore
	 * @param oldMovement: Movimiento original del que saldran los cargos
	 * @param oLines: Lineas a cargar
	 * @return Regresa el numero de cargos generados
	 */
	private int insertChargesReturn(final ErrorList errorList, final MMovement oldMovement, final List<MovementLine> oLines){
		int count = 0;

		for (final MovementLine oline: oLines) {// Puede no devolver nada, pero si se harian los cargos
			// Actualizar cantidades
			if (oline.isVolumeSelected()) {
				oline.setChargeVolQty(oline.getConfirmedVolQty()
						.add(oline.getScrappedVolQty())
						.subtract(oline.getReturnVolQty()));
			} else {
				oline.setChargeQty(oline.getConfirmedQty()
						.add(oline.getScrappedQty())
						.subtract(oline.getReturnQty()));
			}

			// Hacer el cargo
			if(oline.getChargeQty().compareTo(BigDecimal.ZERO)>0 || oline.getChargeVolQty().compareTo(BigDecimal.ZERO)>0){
				oldMovement.createMovementLineCharge(errorList, oline, oldMovement.get_TrxName());
				if (errorList.isEmpty()) {
					count++;
				} else {
					break;
				}
			}
		}// finfor
		return count;
	}

	/** Validar el documento de devolver */
	private boolean validateReturn(final ErrorList errorList, final List<MovementLine> lines) {
		//
		String docStatus = getDocStatus();
		if (is_new()) {
			// Este documento aun no existe como tal
			errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.traspasos.noMovement", getDocumentNo())));

		} else if (MMovement.DOCSTATUS_Voided.equals(docStatus) || MMovement.DOCSTATUS_Invalid.equals(docStatus)) {
			// Este documento ha sido cancelado
			errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.traspasos.isCancelado", getDocumentNo())));

		} else if (!MMovement.DOCSTATUS_Completed.equals(docStatus) && !MMovement.DOCSTATUS_Closed.equals(docStatus)) {
			// Aun no esta confirmado
			errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.traspasos.isConfirmado", getDocumentNo())));

		} else if (getEXME_ProgQuiro_ID()>0 && getEXME_CtaPac_ID() <= 0 ) {
			// Cuenta paciente esta cerrada y es una charola
			errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "enfermeria.msg.diarioEnfermeria.cuenta")));

		} else if (getEXME_CtaPac_ID() > 0 && getEXME_ProgQuiro_ID()>0
				&& DB.getSQLValueTS(null, "SELECT FechaCierre FROM EXME_CtaPac WHERE EXME_CtaPac_ID=?", getEXME_CtaPac_ID()) != null) {
			// Cuenta paciente esta cerrada y es una charola
			errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.actualizaCama.ctapac.closed")));
		} else {
			// validar que el periodo para la fecha del movimiento y la fecha del confirmacion se encuentre abierto
			String docBaseType = MDocType.DOCBASETYPE_MaterialMovement;
			boolean openPeriod = MPeriod.isOpen(getCtx(), new Timestamp(System.currentTimeMillis()), docBaseType, Env.getAD_Org_ID(getCtx())); //
			if (!openPeriod) {
				errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.traspasos.perNoAbierto")));
			}

			String stError  = validateDocType();
			if(!StringUtils.isEmpty(stError)) {
				errorList.add(new Error(Error.VALIDATION_ERROR, stError));
			}
		}

		if(errorList.isEmpty()){
			if (lines.isEmpty()) {
				errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.traspasos.lines")));

			} else if(isDevol() && getEXME_ProgQuiro_ID()<=0 ){
				final BigDecimal total = DB.getSQLValueBD(null
						, "SELECT SUM(COALESCE(ReturnQty,0)) FROM M_MovementLine WHERE M_Movement_ID = ? "
						, getM_Movement_ID() );
				if(total == null || total.compareTo(BigDecimal.ZERO)<=0) {
					errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.traspasos.linesSurtir0")));
				}
			}
		}
		return errorList.isEmpty();
	}

	/** Valida el tipo de documento tipo de documento sea en transito */
	public String validateDocType(){
		String errores = StringUtils.EMPTY;
		final MDocType docType = new MDocType(getCtx(), getC_DocType_ID(), null);
		if (docType.getC_DocType_ID()<=0) {
			errores = Utilerias.getMsg(getCtx(), "error.traspasos.noTipoDoc");
		} else if (!docType.isInTransit()) {
			errores = Utilerias.getMsg(getCtx(), "error.traspasos.tipoDoc");
		}
		return errores;
	}

	/** Tipo de transaccion para hacer la devolución */
	public void setTrxType(int trxType) {
		if(Constantes.CHARGES==trxType){
			this.trxType = trxType;
		} else {
			this.trxType = -1;
		}
	}

	/** Si la transacción es por cargos a cuenta paciente */
	public boolean isTrxTypeCharges() {
		return Constantes.CHARGES==trxType;
	}

	public int getTrxType() {
		return trxType;
	}

	/** Obtener el almacen de consigna del movimiento
	 * @return null si ningun almacen es consigna
	 */
	public MWarehouse getWarehouseCosigna(){
		MWarehouse mwarehouse = null;
		if (getWarehouse().isConsigna()) {	//
			mwarehouse = getWarehouse();
		} else if (getWarehouseTo().isConsigna()) {
			mwarehouse = getWarehouseTo();
		}
		return mwarehouse;
	}

	/** Si la suma de las cantidades de todo el movimiento original ha sido devuelto regresa verdadero */
	private boolean isReturnedItAll(/*final int newMovementId, */final String trxName){
		boolean all = false;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		// Cantidad confirmada
		.append("SELECT          SUM(COALESCE( (COALESCE(line.ConfirmedQty,0) + COALESCE(line.scrappedQty,0)),0)) AS QTYORG")
		// Cantidad devuelta confirmada
		.append(", SUM(  (SELECT SUM(COALESCE( (COALESCE(slin.ConfirmedQty,0) + COALESCE(slin.scrappedQty,0)),0)) ")
		.append("	FROM M_MovementLine slin      ")
		.append("	INNER JOIN M_Movement m ON (m.M_Movement_ID = slin.M_Movement_ID) ")
		.append("	AND m.IsActive  = 'Y'         ")
		.append("	AND m.IsDevol   = 'Y'         ")
		.append("	AND m.DocStatus IN ('CO','CL')")
		.append("	WHERE slin.IsActive = 'Y'     ")
		.append("	AND slin.Ref_MovementLine_ID = line.M_MovementLine_ID)  ")
		.append("     )                             AS QTYCONF ")
		// Cantidad devuelta sin confirmar
		.append(", SUM(  (SELECT SUM(COALESCE(slin.TargetQty,0)) ")
		.append("	FROM M_MovementLine slin      ")
		.append("	INNER JOIN M_Movement m ON (m.M_Movement_ID = slin.M_Movement_ID) ")
		.append("	AND m.IsActive  = 'Y'         ")
		.append("	AND m.IsDevol   = 'Y'         ")
		.append("	AND m.DocStatus IN ('IP')     ")
		.append("	WHERE slin.IsActive = 'Y'     ")
		.append("	AND slin.Ref_MovementLine_ID = line.M_MovementLine_ID)  ")
		.append("     )                             AS QTYSURT ")
		// Movimiento original
		.append("FROM M_MovementLine line          ")
		.append("WHERE line.M_Movement_ID = ?      ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, getM_Movement_ID());

			rs = pstmt.executeQuery();
			if(rs.next()) {
				BigDecimal QTYCONF = rs.getBigDecimal("QTYCONF");
				BigDecimal QTYSURT = rs.getBigDecimal("QTYSURT");

				BigDecimal _QTYSURT = (QTYCONF==null?BigDecimal.ZERO:QTYCONF).add(QTYSURT==null?BigDecimal.ZERO:QTYSURT);

				if(rs.getBigDecimal("QTYORG").compareTo(_QTYSURT)<=0){
					all = true;
				}

			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "SQL : " + sql.toString() + " - ID : " + getM_Movement_ID(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return all;
	}

	/**
	 * Manda un pedido, lógica usada desde solicitud de productos, charolas, pedidos automáticos
	 *
	 * @param ctx
	 *            Contexto
	 * @param movementId
	 *            Movimiento
	 * @param lines
	 *            Líneas
	 * @param trxName
	 *            Trx
	 * @return {@link ErrorList}
	 */
	public static ErrorList request(final Properties ctx, final int movementId, final List<MovementLine> lines, final String trxName) {
		ErrorList errorList = new ErrorList();

		final MMovement movement = new MMovement(ctx, movementId, trxName);

		Trx trx = null;
		try {
			if (trxName == null) {
				trx = Trx.get(Trx.createTrxName("solProd"), true);
			}

			errorList = request(ctx, movement, lines, trxName == null ? trx.getTrxName() : trxName);

			if (errorList.isEmpty()) {
				Trx.commit(trx);
			} else {
				Trx.rollback(trx);
			}
		} catch (final Exception ex) {
			errorList.add(Error.EXCEPTION_ERROR, Utilerias.getMsg(ctx, "error.traspasos.noInsertMov"));
			slog.log(Level.SEVERE, null, ex);
			Trx.rollback(trx);
		} finally {
			Trx.close(trx);
		}

		return errorList;
	}

	private boolean consigVirtual = false;

	public void setConsigVirtual(final boolean consigVirtual) {
		this.consigVirtual = consigVirtual;
	}

//	public boolean isForceConfirm() {
//		return consigVirtual /*|| (MConfigEC.find(getCtx(), null).isSurteConfirma() && !consigVirtual)*/;
//	}

	public boolean isConsigVirtual() {
		return consigVirtual;
	}

	public List<SubMovement> getMapConsigment() {
		return mapConsignment;
	}

	public void initMapConsigment(){
		mapConsignment = new ArrayList<SubMovement>();
	}

	/**
	 * Para consigna - virtual, hace la solicitud/surtido y confirmacion
	 * utilizado para cargos y factura directa
	 * Crea el m_movement, y los m_movementline, ejecuta los metodos Request/Deliver/Confirm
	 *
	 * @param ctx Contexto
	 * @param movement objeto nuevo de movimiento
	 * @param lines lista de productos
	 * @param trxName
	 * @return
	 */
	public static ErrorList requestVirtualCharges(final Properties ctx, final MMovement movement, final List<MovementLine> lines, final String trxName) {
		ErrorList errorList = new ErrorList();
		if(movement != null && movement.getWarehouse().isConsigna() && movement.getWarehouse().isVirtual()) {
			if (!movement.save(trxName)) {
				throw new MedsysException();
			}
			for (MovementLine line : lines) {
				final MMovementLine mLine = new MMovementLine(movement);
				line.setMovementId(movement.getM_Movement_ID());

				MovementLine.fillRequest(line, mLine);

				line.setTargetQty(line.getOriginalQty());
				line.setTargetVolQty(line.getOriginalVolQty());
				line.setConfirmedQty(line.getOriginalQty());
				line.setConfirmedVolQty(line.getOriginalVolQty());

				mLine.setMovementQty(line.getOriginalQty());
				mLine.setMovementQty_Vol(line.getOriginalVolQty());
				mLine.setTargetQty(line.getOriginalQty());
				mLine.setTargetQty_Vol(line.getOriginalVolQty());
				mLine.setConfirmedQty(line.getOriginalQty());
				mLine.setConfirmedQty_Vol(line.getOriginalVolQty());

				if (!mLine.save(trxName)) {
					throw new MedsysException();
				}
				line.setId(mLine.getM_MovementLine_ID());
			}
			// solicitud/surtido/confirmacion
			// si es virtual se hace en automatico (surtido/confirmacion)
			errorList = MMovement.request(ctx, movement, lines, trxName);

			if (errorList.isEmpty() && DocAction.STATUS_Drafted.equals(movement.getDocStatus())) {
				errorList = MMovement.deliver(ctx, movement, lines, trxName);
				if (errorList.isEmpty() && DocAction.STATUS_InProgress.equals(movement.getDocStatus())) {
					errorList = MMovementConfirm.confirm(ctx, movement, lines, trxName);
				}
			}
		}
		return errorList;
	}

	/**
	 * Cancelacion de ventas de productos de consigna
	 * @param ctx
	 * @param invoiceId
	 * @param trxName
	 */
	public static void returnInvoiceProd(Properties ctx, int invoiceId, String trxName) {
		List<MMovement> movements = new Query(ctx, Table_Name, " C_Invoice_ID=? ", trxName)
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setParameters(invoiceId)//
		.setOrderBy("M_Movement.C_Invoice_ID")//
		.list();

		for (MMovement mMovement : movements) {
			List<MovementLine> lines = getLines(ctx, mMovement.getM_Movement_ID(), trxName);
			for (MovementLine line : lines) {
				// copia la cantidad confirada a la cantidad a devolver
				line.setReturnVolQty(line.getConfirmedVolQty());
				line.setReturnQty(line.getConfirmedQty());
			}
			ErrorList error = MMovement.returnProd(ctx, mMovement, lines, true, trxName);
			if (!error.isEmpty()) {
				throw new MedsysException(error.toString());
			}
		}
	}


	public class SubMovement {
		private int movementId = 0;
		private List<MovementLine> mapLines;

		public int getMovementId() {
			return movementId;
		}
		public void setMovementId(int movementId) {
			this.movementId = movementId;
		}
		public List<MovementLine> getMapLines() {
			if(mapLines==null){
				mapLines = new ArrayList<MovementLine>();
			}
			return mapLines;
		}
		public void setMapLines(List<MovementLine> mapLines) {
			this.mapLines = mapLines;
		}

		@Override
		public  boolean equals(Object o){
			if(o!=null && o instanceof SubMovement){
				return ((SubMovement)o).toString().equals(this.toString());
			} else return false;
		}

		@Override
		public String toString(){
			return String.valueOf(movementId);
		}
	}

	/**
	 * Obtener los registros hijos del movimiento entre consigna y propio
	 * @param movementLineId Id de la linea del movimiento
	 * @return Puede devolver un null
	 */
	public List<MMovement> getParentMovementConsignment(){
		final List<MMovement> all = new ArrayList<MMovement>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT submov.M_Movement_ID ")
		.append(" FROM M_MovementLine line ")
		.append(" INNER JOIN M_Movement      submov ON submov.Parent_Movement_ID = line.M_Movement_ID  ")
		.append("	                               AND submov.IsActive = 'Y'                           ")
		.append(" INNER JOIN M_Warehouse       alm1 ON alm1.M_Warehouse_ID = submov.M_Warehouse_ID AND alm1.Consigna = 'Y' ")
		.append(" INNER JOIN M_MovementLine   sline ON sline.M_Movement_ID = submov.M_Movement_ID      ")
		.append("	                               AND sline.IsActive = 'Y' ")
		.append("	                               AND sline.M_Product_ID              = line.M_Product_ID     ")
		.append(" 				                   AND sline.M_AttributeSetInstance_ID = line.M_AttributeSetInstance_ID ")
		.append(" WHERE line.IsActive = 'Y'         ")
		.append(" AND   line.M_Movement_ID = ?      ")
		.append(" GROUP BY submov.M_Movement_ID     ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getM_Movement_ID());

			rs = pstmt.executeQuery();
			while(rs.next()) {
				final MMovement line = new MMovement(getCtx(), rs.getInt("M_Movement_ID"), get_TrxName());
				all.add(line);
			}//FINWHILE
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return all;
	}

	/** Listado de pedidos generados como backorder */
	public List<MMovement> getBackorders() {
		return new Query(getCtx(), MMovement.Table_Name, " Backorder = 'Y' AND Ref_Movement_ID=? ", get_TrxName())//
			.addAccessLevelSQL(true)
			.setOnlyActiveRecords(true)
			.setParameters(getM_Movement_ID())//
			.list();
	}

	/** Lineas del movimiento con cantidad para devolver igual a la confirmada */
	public List<MovementLine> getLinesReturn() {
		 final List<MovementLine> bLines = MMovement.getLines(getCtx(), getM_Movement_ID(), get_TrxName());
		 for (MovementLine movLine: bLines) {
			// copia la cantidad confirada a la cantidad a devolver
			 movLine.setReturnVolQty(movLine.getConfirmedVolQty());
			 movLine.setReturnQty(movLine.getConfirmedQty());
		}
		return bLines;
	}
	
}	//	MMovement