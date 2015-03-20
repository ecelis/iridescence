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
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

/**
 *	Inventory Movement Confirmation Line
 *	
 *  @author Jorg Janke
 *  @version $Id: MMovementLineConfirm.java,v 1.1 2006/09/14 21:31:46 mrojas Exp $
 */
public class MMovementLineConfirm extends X_M_MovementLineConfirm {
	
	/**	 */
	private static final long	serialVersionUID	= 1L;
	/**	Movement Line			*/
	private MMovementLine 	m_line = null;
	private MProduct product;
	/** Static logger			*/
//	private static CLogger s_log = CLogger.getCLogger(MMovementLineConfirm.class);
	/** Lote para interfaz con Sistemas de Almacen*/
//	private String lote = null;
//	/** Fecha de garant�a para interfaz con Sistemas de Almacen*/
//	private String guaranteeDate = null;

//	public String getLote() {
//		return lote;
//	}
//
//	public void setLote(String lote) {
//		this.lote = lote;
//	}

//	public String getGuaranteeDate() {
//		return guaranteeDate;
//	}
//
//	public void setGuaranteeDate(String guaranteeDate) {
//		this.guaranteeDate = guaranteeDate;
//	}

	/**
	 * 	Standard Constructor
	 *	@param ctx ctx
	 *	@param M_MovementLineConfirm_ID id
	 *	@param trxName transaction
	 */
	public MMovementLineConfirm(Properties ctx, int M_MovementLineConfirm_ID, String trxName) {
		super(ctx, M_MovementLineConfirm_ID, trxName);
		if (is_new()) {
			// setM_MovementConfirm_ID (0); Parent
			// setM_MovementLine_ID (0);
			setConfirmedQty(Env.ZERO);
			setDifferenceQty(Env.ZERO);
			setScrappedQty(Env.ZERO);
			setTargetQty(Env.ZERO);
			setProcessed(false);
		}
	} // M_MovementLineConfirm

	/**
	 * 	M_MovementLineConfirm
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MMovementLineConfirm(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} //	M_MovementLineConfirm
	
//	/**
//	 * 	Parent Constructor
//	 *	@param parent parent
//	 *  @deprecated
//	 */
//	public MMovementLineConfirm(MMovementConfirm parent) {
//		this(parent.getCtx(), 0, parent.get_TrxName());
//		setClientOrg(parent);
//		setM_MovementConfirm_ID(parent.getM_MovementConfirm_ID());
//	}	//	MMovementLineConfirm

	
	
//	/**
//	 * 	Set Movement Line
//	 *	@param line line
//	 *  @deprecated
//	 */
//	public void setMovementLine(MMovementLine line) {
//		setM_MovementLine_ID(line.getM_MovementLine_ID());
//		setTargetQty(line.getMovementQty());
//		setConfirmedQty(getTargetQty()); // suggestion
//		// rsolorzano
//		// cambios unidades de medida
//		setTargetQty_Vol(line.getMovementQty_Vol());
//		setConfirmedQty_Vol(getTargetQty_Vol());
//		setAD_OrgTrx_ID(line.getAD_OrgTrx_ID()); // OrgTrx .-Lama
//		m_line = line;
//	} //	setMovementLine

	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 */
	public MMovementLineConfirm(MMovementConfirm parent, MMovementLine line) {
		this(parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setM_MovementConfirm_ID(parent.getM_MovementConfirm_ID());
		if(line != null) {
			setM_MovementLine_ID(line.getM_MovementLine_ID());
			// targetqty = movement, confirmed - target 
			// (requerido-func original compiere) - Lama
			setTargetQty(line.getMovementQty());
			setConfirmedQty(getTargetQty());	//	suggestion
			// rsolorzano cambios unidades de medida
			setTargetQty_Vol(line.getMovementQty_Vol());
			setConfirmedQty_Vol(getTargetQty_Vol());
			setAD_OrgTrx_ID(line.getAD_OrgTrx_ID()); // OrgTrx .-Lama
			m_line = line;
		}
	}	//	MMovementLineConfirm
	
	/**
	 * 	Get Movement Line
	 *	@return line
	 */
	public MMovementLine getLine() {
		if (m_line == null)
			m_line = new MMovementLine(getCtx(), getM_MovementLine_ID(), get_TrxName());
		return m_line;
	} // getLine
	
	/**
	 * 	Process Confirmation Line.
	 * 	- Update Movement Line
	 *	@return success
	 */
	public boolean processLine() {
		MMovementLine line = getLine();

		line.setTargetQty(getTargetQty());
		line.setMovementQty(getConfirmedQty());
		line.setConfirmedQty(getConfirmedQty());
		line.setScrappedQty(getScrappedQty());

		line.setTargetQty_Vol(getTargetQty_Vol());
		line.setMovementQty_Vol(getConfirmedQty_Vol());
		line.setConfirmedQty_Vol(getConfirmedQty_Vol());
		line.setScrappedQty_Vol(getScrappedQty_Vol());

		return line.save(get_TrxName());
	} // processConfirmation

	/**
	 * 	Is Fully Confirmed
	 *	@return true if Target = Confirmed qty
	 */
	public boolean isFullyConfirmed() { 
		// Expert: twry. Se modifico para cuando se desea cargar al almacen que solicita la diferencia
		boolean todoConfirmado = true;
		if (getDifferenceQty().compareTo(Env.ZERO) != 0 || getScrappedQty().compareTo(Env.ZERO) != 0)
			todoConfirmado = false;
		return todoConfirmado;

		// return getTargetQty().compareTo(getConfirmedQty()) == 0;
		// Expert: Fin twry
	} // isFullyConfirmed
	
	/**
	 * 	Before Delete - do not delete
	 *	@return false 
	 */
	protected boolean beforeDelete() {
		return false;
	}	//	beforeDelete
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	/*protected boolean beforeSave (boolean newRecord)
	{
		//	Calculate Difference = Target - Confirmed - Scrapped
		//BigDecimal difference = getTargetQty();
		//difference = difference.subtract(getConfirmedQty());
		//difference = difference.subtract(getScrappedQty());
		//setDifferenceQty(difference);
		//
		return true;
	}*/	//	beforeSave

	
	
	//expert
//	/**
//	 * Actualizamos los datos de la linea de confirmacion.
//	 * @param ctx
//	 * @param linea
//	 * @param diferencia
//	 * @param EXME_MovementConfirm_ID
//	 * 
//	 * @throws SQLException
//	 */
//	public static void updateLine(Properties ctx, ConfirmaDetView linea, String diferencia, 
//			int EXME_MovementConfirm_ID, String trxName) throws SQLException {
//
//		StringBuffer sql = new StringBuffer();
//
//		sql.append("UPDATE M_MovementLineConfirm SET ").
//		append("Updated = SYSDATE, UpdatedBy = ?, ").
//		append("ConfirmedQty = ?, DifferenceQty = ?, ").
//		append("ScrappedQty = ?, Description = ? ").
//		append("WHERE M_MovementConfirm_ID = ? ").
//		append("AND M_MovementLine_ID = ?");
//
//		PreparedStatement pstmt = null;
//		
//		try {
//			
//			pstmt = DB.prepareStatement(sql.toString(), trxName); 
//			//asignamos los valores a actualizar
//			pstmt.setInt(1, Env.getAD_User_ID(ctx));
//			pstmt.setBigDecimal(2, linea.getConfirmedQty());
//			if(diferencia.equalsIgnoreCase("Y")) {
//				//se le carga al que surte (DifferenceQty)
//				pstmt.setBigDecimal(3, linea.getScrappedQty());
//				pstmt.setInt(4, 0);
//			} else {
//				//se le carga al que solicita (ScrappedQty)
//				pstmt.setInt(3, 0);
//				pstmt.setBigDecimal(4, linea.getScrappedQty());
//			}
//			pstmt.setString(5, linea.getDescription());
//			pstmt.setInt(6, EXME_MovementConfirm_ID);
//			pstmt.setInt(7, (int)linea.getMovementLineID());
//			
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "-- updateLine : ", e);
//			throw e;
//		} finally {
//			DB.close(pstmt);
//		}
//	}
	
	/**
	 * Recuperamos la linea de confirmacion relacionada a una
	 * linea de movimiento.
	 * @param ctx
	 * @param movementLineID
	 * @param trxName
	 * @return
	 */
	public static MMovementLineConfirm getFromMovLine(Properties ctx, int movementLineID, String trxName)  {
//		StringBuffer sql = new StringBuffer();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		MMovementLineConfirm mlc = null;
//		try {
//			sql.append("SELECT * FROM M_MovementLineConfirm ").append("WHERE M_MovementLine_ID = ?");
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, movementLineID);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				mlc = new MMovementLineConfirm(ctx, rs, trxName);
//			}
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getFromMovLine (" + sql + ")", e);
//			throw e;
//		} finally {
//			DB.close(rs, pstmt);
//		}
		return new Query(ctx, Table_Name, " M_MovementLine_ID=? ", trxName)//
		.setParameters(movementLineID)//
		.first();
	}
	
	/**
	 * Recuperamos el Id de confirmacion relacionada a una
	 * linea de movimiento.
	 * @param ctx
	 * @param movementLineID
	 * @param trxName
	 * @return
	 */
	public static int getFromMovLineId(Properties ctx, int movementLineID, String trxName)  {
		return new Query(ctx, Table_Name, " M_MovementLine_ID=? ", trxName)//
		.setParameters(movementLineID)//
		.firstId();
	}
	//expert
	
	public I_M_Movement getMovement(){
		return getM_MovementConfirm().getM_Movement();
	}

	
	/** Seleccionar la cantidad que sera transferida en la recepcion */
	public BigDecimal getTransferQty(){
		return getMovement().isDevol()
				?(getConfirmedQtyAddScrappedQty()).negate()
				:getConfirmedQtyAddScrappedQty();
	}
	
	/** Seleccionar la cantidad que sera transferida en la recepcion */
	public BigDecimal getTransferQtyVol(){
		BigDecimal minQty =	Env.ZERO;
		if(isVolumeSelected()){
			minQty = getConfirmedQty_Vol().add(getScrappedQty_Vol());
		} else {
			minQty = getConfirmedQtyAddScrappedQty();
		}
		return getMovement().isDevol()?minQty.negate():minQty;
	}
	
	/** Cantidad confirmada en minima */
	public BigDecimal getConfirmedQtyAddScrappedQty(){
		return this.getConfirmedQty().add(this.getScrappedQty());
	}
	
	/**
	 * Si la unidad minima y de volumen son diferentes
	 * @return true: si la udm de la linea es la misma que la de volumen del producto
	 */
	public boolean isVolumeSelected() {
		boolean retValue = false;
		if (getProduct().getC_UOM_ID() != getProduct().getC_UOMVolume_ID() && getProduct().getC_UOMVolume_ID() == getM_MovementLine().getOp_Uom()) {
			retValue = true;
		}
		return retValue;
	}
	
	/**
	 * Get Product
	 * 
	 * @return product or null if not defined
	 */
	public MProduct getProduct() {
		if (getM_MovementLine().getM_Product_ID() != 0 && product == null) {
			product = MProduct.get(getCtx(), getM_MovementLine().getM_Product_ID());
		}
		return product;
	} // getProduct
}	//	M_MovementLineConfirm
