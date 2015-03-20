package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ExpertException;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;




public class MEXMECancelCharges extends X_EXME_CancelCharges {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(MEXMECancelCharges.class);
	/** productName */
	private String productName = null;
	
	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param EXME_ProductoOrg_ID
	 * @param trxName
	 */
	public MEXMECancelCharges(final Properties ctx, final int Exme_CancelCharges_ID, String trxName) {
		super(ctx, Exme_CancelCharges_ID, trxName);
	}

	/**
	 * Constructor
	 * @param ctx
	 * @param rs
	 */
	public MEXMECancelCharges(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Se realiza el llenado de la cancelacion de Auditoria
	 * 
	 * @param fromCharge Cargo original
	 * @param qty Cantidad
	 * @param warehouseAuditId Almacen de auditoria
	 * @param vCargos ? false: se Asignan Usuario y Fecha
	 * @return
	 *         Para reutilizar codigo de AbstractCharges
	 * Lama: cargos 2014
	 */
	public MEXMECancelCharges(MCtaPacDet fromCharge, final BigDecimal qty, int warehouseAuditId, final boolean vCargos) {
		this(fromCharge.getCtx(), 0, fromCharge.get_TrxName());
		setM_Product_ID(fromCharge.getM_Product_ID());
		setM_Warehouse_ID(warehouseAuditId);
		setM_Warehouse_Sol_ID(Env.getM_Warehouse_ID(getCtx()));
		setEXME_CtaPac_ID(fromCharge.getEXME_CtaPac_ID());
		setQty(qty);
		setC_UOM_ID(fromCharge.getC_UOM_ID());
		// Si solo es un cargo se Asignan Usurio y Fecha
		if (!vCargos) {
			setDateCanceled(Env.getCurrentDate());
			setUser2_ID(Env.getAD_User_ID(getCtx()));
		}
	}
	
	/**
	 * Devolvemos una lista de objetos MEXMECancelCharges
	 * @param ctx
	 * @param userId
	 * @param ctaPacId
	 * @param almacenSolId
	 * @param almacenCancel
	 * @param almacenSolId
	 * @param inicio
	 * @param fin
	 * @param trxName
	 * @return
	 */
   
	public static List<MEXMECancelCharges> getCancelSearch(Properties ctx,int userId,int ctaPacId, int almacenSolId,int almacenCancel,Timestamp inicio,Timestamp fin, String trxName) {
		List<MEXMECancelCharges> retvalue = new ArrayList<MEXMECancelCharges>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		sql.append("SELECT ");
		sql.append("  can.* ");
		sql.append("FROM ");
		sql.append("  Exme_CancelCharges can ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " WHERE ", Table_Name, "can"));

		List<Object> params = new ArrayList<Object>();

		if (userId > 0) {
			sql.append("  AND can.user2_id= ? ");
			params.add(userId);
		}

		if (almacenSolId > 0) {
			sql.append("  AND can.m_warehouse_sol_id = ? ");
			params.add(almacenSolId);
		}

		if (almacenCancel > 0) {
			sql.append("  AND can.m_warehouse_id = ? ");
			params.add(almacenCancel);
		}

		if (ctaPacId > 0) {
			sql.append("  AND can.exme_ctapac_id = ? ");
			params.add(ctaPacId);
		}

		if (inicio != null && fin != null) {
			sql.append(" AND can.date_created between (?) and (?) ");

			params.add(inicio);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(fin.getTime());
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			params.add(new Timestamp(cal.getTimeInMillis()));
		}
		sql.append(" AND can.processed = 'N' ");
		sql.append(" ORDER BY can.created desc ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
	
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retvalue.add(new MEXMECancelCharges(ctx, rs, trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);

		} finally {
			DB.close(rs, pstmt);
		}

		return retvalue;
	}
	/**
	 * Devolvemos EL nombre del Almacen de auditoria
	 */
	public String getWarehouseName()
	{
		MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
		if (wh.get_ID() == 0)
			return "<" + getM_Warehouse_ID() + ">";
		return wh.getName();
	}
	/**
	 * Devolvemos EL nombre del Almacen de original
	 */
	public String getWarehouseNameOrig()
	{
		MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_Sol_ID());
		if (wh.get_ID() == 0)
			return "<" + getM_Warehouse_ID() + ">";
		return wh.getName();
	}	
	/**
	 * Devolvemos EL nombre del Producto
	 */
	public String getProductName() {
		if (StringUtils.isEmpty(productName)) {
			if (getM_Product_ID() > 0) {
				productName = new MProduct(getCtx(), getM_Product_ID(), null).getName();
			} else {
				productName = StringUtils.EMPTY;
			}
		}
		return productName;
	}
	/**
	 * Devolvemos La cuenta Paciente
	 */
	public String getEncounter() {
            String enco = null;
			if (getEXME_CtaPac_ID() > 0) {
				MEXMECtaPac encounter = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID() , null);
				enco = encounter.getDocumentNo();
			} else {
				enco = StringUtils.EMPTY;
			}
			
			return enco;
	
	}
	/**
	 * Agregamos el nombre del producto
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	/**
	 * Proceso de Generar Salida al gasto
	 * @param lista
	 * @param c_charge_id
	 * @return
	 * FIXME: no debe haber transacciones en clases M
	 * @deprecated
	 */
	public boolean genararSalidaGasto(List<MEXMECancelCharges> lista, int c_charge_id ){

		// con el id  del almacen//
		Trx trx = null;
		MWarehouse wh = MWarehouse.get(getCtx(), lista.get(0).getM_Warehouse_ID());
        boolean success = true; 
		try {
			trx = Trx.get(Trx.createTrxName("salGasto"), true);

			//Se crea el encabezado de la  salida al gasto
			MInventory inventory = new MInventory(wh);
			MDocType types[] = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_InternalUseInventory);
			if (types.length > 0)	//	get first
			    inventory.setC_DocType_ID(types[0].getC_DocType_ID());
			else
			{
				log.saveError("Error", Msg.parseTranslation(getCtx(), "@NotFound@ @C_DocType_ID@"));
				return false;
			}
		
			inventory.setDescription(Utilerias.getMessage(getCtx(), null,"msj.salidaGasto"));
			if (!inventory.save(trx.getTrxName())) {
				log.saveError("Error", Utilerias.getMsg(getCtx(), "error.ts.noguardado")+" genararSalidaGasto");
				success = false;
				
			}

			//Se crean las lineas de la salida al gasto
			for (int i = 0; i < lista.size(); i++) {
				final MInventoryLine line = new MInventoryLine(inventory, wh
						.getDefaultLocator().getM_Locator_ID(), lista.get(i)
						.getM_Product_ID(), 0,  Env.ZERO, Env.ZERO);
				line.setDescription(lista.get(i).getDescription());
				line.setInventoryType(X_M_InventoryLine.INVENTORYTYPE_ChargeAccount);
				line.setC_Charge_ID(c_charge_id);
				line.setC_UOM_ID(line.getProduct().getC_UOM_ID());
				line.setC_UOMVolume_ID(line.getProduct().getC_UOMVolume_ID());
				
				if(lista.get(i).getC_UOM_ID()==line.getProduct().getC_UOM_ID()){
					line.setQtyInternalUse_Uom(lista.get(i).getQty()); 
				} else {
					line.setQtyInternalUse_Vol(lista.get(i).getQty());
				}
				if (!line.save(trx.getTrxName())) {
					log.saveError("SaveError",
							"No se logro crear el ajuste de inventario");
					success = false;
				}

			}
			
			String status = inventory.completeIt();
			if (!status.equals(DocAction.STATUS_Completed)) {
				if (trx != null) {
					trx.rollback();
					trx.close();
					trx = null;
				}
				log.saveError("GLOBAL_ERROR", Utilerias.getMsg(getCtx(), "error.order.complete")+" genararSalidaGasto");
				success = false;
			}
			inventory.setDocStatus(status);
			if (!inventory.save(trx.getTrxName())) {
				if (trx != null) {
					trx.rollback();
					trx.close();
					trx = null;
				}
				log.saveError("GLOBAL_ERROR", Utilerias.getMsg(getCtx(), "error.order.complete")+" genararSalidaGasto");
				success = false;
			}
			for (int i = 0; i < lista.size(); i++) {
				lista.get(i).setProcessed(true);
				success = lista.get(i).save(trx.getTrxName());	
			}

			if(success)
				Trx.commit(trx);
		} catch (Exception ex) {
			log.log(Level.SEVERE, null, ex);
			Trx.rollback(trx);
			success = false;
		} finally {
			Trx.close(trx);
		}

		return success;
	}
	/**
	 * Proceso de Generar Salida al gasto
	 * @param lista
	 * @param c_charge_id
	 * @return
	 */
	
	public boolean generarTraspasos(List<MEXMECancelCharges> lista, int mwarehouse_id) {
		boolean success = true;
		int mwarehouseAud_id = 0;
		
		List<MMovementLine> movementLines = new ArrayList<MMovementLine>();

		for (int i = 0; i < lista.size(); i++) {
			MEXMECancelCharges lineas = lista.get(i);
			MProduct product = new MProduct(getCtx(), lineas.getM_Product_ID(), null);
			final MMovementLine line = new MMovementLine(Env.getCtx(), 0, null);
			line.setM_Product_ID(product.getM_Product_ID());
			line.setOp_Uom(lineas.getC_UOM_ID());
			line.setDescription(lineas.getDescription());
			line.setEXME_CtaPac_ID(lineas.getEXME_CtaPac_ID());
			line.setC_UOMVolume_ID(product.getC_UOMVolume_ID());
			line.setC_UOM_ID(product.getC_UOM_ID());
			line.setNew(true);
			line.setQuantityUser(lineas.getQty());
			
			line.setTargetQty_Vol(Env.ZERO);
			line.setMovementQty_Vol(Env.ZERO);
			line.setTargetQty(Env.ZERO);
			line.setMovementQty(Env.ZERO);
			
			if(line.getC_UOM_ID()==product.getC_UOM_ID()){
				line.setTargetQty(lineas.getQty());
				line.setMovementQty(lineas.getQty());
				
			} else {
				line.setTargetQty_Vol(lineas.getQty());
				line.setMovementQty_Vol(lineas.getQty());
				
			}
			
			mwarehouseAud_id = lista.get(i).getM_Warehouse_ID();
			movementLines.add(line);
		}

		
		try {
			
			final MMovement movement = MMovement.generateMovement(getCtx(),
					mwarehouseAud_id, mwarehouse_id, "",
					0, "", movementLines,
					X_M_Movement.PRIORITYRULE_Medium, 0, false, true);

			if (movement == null) {
				success = false;
			} else {
				success = true;
				for (int i = 0; i < lista.size(); i++) {
					lista.get(i).setProcessed(true);
					lista.get(i).save();	
				}
				
			}
		} catch (final ExpertException e) {
			success = false;
			log.log(Level.SEVERE, null, e);
		}

		return success;

	}
	public String getUsuario() {
		String userName = "";
		if(getUser2_ID() > 0){
			userName = MUser.getUserName(getCtx(), getUser2_ID());
		}
		return userName;
	}

}
