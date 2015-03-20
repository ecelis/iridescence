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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.bpm.GetCost;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.GenericModel;
import org.compiere.util.Msg;

/**
 * 	InOut Line
 *
 *  @author Jorg Janke
 *  @version $Id: MInOutLine.java,v 1.5 2006/07/30 00:51:03 jjanke Exp $
 */
public class MInOutLine extends X_M_InOutLine
{

	/** serialVersionUID */
	private static final long serialVersionUID = -5201884066057888720L;
	/** Referencia de la linea del Movimiento que origino el registro. Nota: No se guarda en BD. */
    private MMovementLine m_movementLine = null;
	private List<MMatchPO> lMatchPO = new ArrayList<MMatchPO>();
	private List<MTransaction> lTransaction = new ArrayList<MTransaction>();
	private GetCost mGetCost =null;
	private MInOut mInOut = null; 
	
	public MInOut getmInOut() {
		return mInOut;
	}

	public void setmInOut(MInOut mInOut) {
		this.mInOut = mInOut;
	}

	/**
	 * 	Get Ship lines Of Order Line
	 *	@param ctx context
	 *	@param C_OrderLine_ID line
	 *	@param where optional addition where clause
	 *  @param trxName transaction
	 *	@return array of receipt lines
	 */
	public static MInOutLine[] getOfOrderLine(final Properties ctx, final int C_OrderLine_ID, final String where, final String trxName) {
		final ArrayList<MInOutLine> list = new ArrayList<MInOutLine>();
		final StringBuffer sql = new StringBuffer("SELECT * FROM M_InOutLine WHERE C_OrderLine_ID=?");
		if (where != null && where.length() > 0) {
			sql.append(" AND ").append(where);
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_OrderLine_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MInOutLine(ctx, rs, trxName));
			}

		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		final MInOutLine[] retValue = new MInOutLine[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getOfOrderLine

	/**
	 * 	Get Ship lines Of Order Line
	 *	@param ctx context
	 *	@param C_OrderLine_ID line
	 *  @param trxName transaction
	 *	@return array of receipt lines2
	 */
	public static MInOutLine[] get(final Properties ctx, final int C_OrderLine_ID, final String trxName) {
		final ArrayList<MInOutLine> list = new ArrayList<MInOutLine>();
		final String sql = "SELECT * FROM M_InOutLine WHERE C_OrderLine_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_OrderLine_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MInOutLine(ctx, rs, trxName));
			}
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		final MInOutLine[] retValue = new MInOutLine[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getOfOrderLine

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MInOutLine.class);


	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_InOutLine_ID id
	 *	@param trxName trx name
	 */
	public MInOutLine (final Properties ctx, final int M_InOutLine_ID, final String trxName)
	{
		super (ctx, M_InOutLine_ID, trxName);
		if (M_InOutLine_ID == 0)
		{
		//	setLine (0);
		//	setM_Locator_ID (0);
		//	setC_UOM_ID (0);
		//	setM_Product_ID (0);
			setM_AttributeSetInstance_ID(0);
		//	setMovementQty (Env.ZERO);
			setConfirmedQty(Env.ZERO);
			setPickedQty(Env.ZERO);
			setScrappedQty(Env.ZERO);
			setTargetQty(Env.ZERO);
			setIsInvoiced (false);
			setIsDescription (false);
		}
	}	//	MInOutLine

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *  @param trxName transaction
	 */
	public MInOutLine (final Properties ctx, final ResultSet rs, final String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MInOutLine

	/**
	 *  Parent Constructor
	 *  @param inout parent
	 */
	public MInOutLine (final MInOut inout)
	{
		this (inout.getCtx(), 0, inout.get_TrxName());
		setClientOrg (inout);
		setM_InOut_ID (inout.getM_InOut_ID());
		setM_Warehouse_ID (inout.getM_Warehouse_ID());
		setC_Project_ID(inout.getC_Project_ID());
		m_parent = inout;
	}	//	MInOutLine

	/**	Product					*/
	private MProduct 		m_product = null;
	/** Warehouse				*/
	private int				m_M_Warehouse_ID = 0;
	/** Parent					*/
	private MInOut			m_parent = null;
	//expert : gisela lee : cambios de jjanke en version 26b
	/** Matched Invoices		*/
	private MMatchInv[] 	m_matchInv = null;
	/** Matched Purchase Orders	*/
	private MMatchPO[]	 	m_matchPO = null;
	//expert : gisela lee : cambios de jjanke en version 26b

	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MInOut getParent()
	{
		if (m_parent == null) {
			m_parent = new MInOut (getCtx(), getM_InOut_ID(), get_TrxName());
		}
		return m_parent;
	}	//	getParent

	/**
	 * 	Set Order Line.
	 * 	Does not set Quantity!
	 *	@param oLine order line
	 *	@param M_Locator_ID locator
	 * 	@param Qty used only to find suitable locator
	 */
	public void setOrderLine (final MOrderLine oLine, final int M_Locator_ID, final BigDecimal Qty)
	{
		setC_OrderLine_ID(oLine.getC_OrderLine_ID());
		setLine(oLine.getLine());
		setC_UOM_ID(oLine.getC_UOM_ID());
		final MProduct product = oLine.getProduct();
		if (product == null)
		{
			set_ValueNoCheck("M_Product_ID", null);
			set_ValueNoCheck("M_AttributeSetInstance_ID", null);
			set_ValueNoCheck("M_Locator_ID", null);
		}
		else
		{
			setM_Product_ID(oLine.getM_Product_ID());
			setM_AttributeSetInstance_ID(oLine.getM_AttributeSetInstance_ID());
			//
			if (product.isItem())
			{
				if (M_Locator_ID == 0) {
					setM_Locator_ID(Qty);	//	requires warehouse, product, asi
				} else {
					setM_Locator_ID(M_Locator_ID);
				}
			} else {
				set_ValueNoCheck("M_Locator_ID", null);
			}
		}
		setC_Charge_ID(oLine.getC_Charge_ID());
		setDescription(oLine.getDescription());
		setIsDescription(oLine.isDescription());
		//
		setC_Project_ID(oLine.getC_Project_ID());
		setC_ProjectPhase_ID(oLine.getC_ProjectPhase_ID());
		setC_ProjectTask_ID(oLine.getC_ProjectTask_ID());
		setC_Activity_ID(oLine.getC_Activity_ID());
		setC_Campaign_ID(oLine.getC_Campaign_ID());
		setAD_OrgTrx_ID(oLine.getAD_OrgTrx_ID());//Organizacion del almacen que surte
		setUser1_ID(oLine.getUser1_ID());
		setUser2_ID(oLine.getUser2_ID());
	}	//	setOrderLine

	/**
	 * 	Set Invoice Line.
	 * 	Does not set Quantity!
	 *	@param iLine invoice line
	 *	@param M_Locator_ID locator
	 *	@param Qty qty only fo find suitable locator
	 */
	public void setInvoiceLine (final MInvoiceLine iLine, final int M_Locator_ID, final BigDecimal Qty)
	{
		setC_OrderLine_ID(iLine.getC_OrderLine_ID());
		setLine(iLine.getLine());
		setC_UOM_ID(iLine.getC_UOM_ID());
		final int M_Product_ID = iLine.getM_Product_ID();
		if (M_Product_ID == 0)
		{
			set_ValueNoCheck("M_Product_ID", null);
			set_ValueNoCheck("M_Locator_ID", null);
			set_ValueNoCheck("M_AttributeSetInstance_ID", null);
		}
		else
		{
			setM_Product_ID(M_Product_ID);
			setM_AttributeSetInstance_ID(iLine.getM_AttributeSetInstance_ID());
			if (M_Locator_ID == 0) {
				setM_Locator_ID(Qty);	//	requires warehouse, product, asi
			} else {
				setM_Locator_ID(M_Locator_ID);
			}
		}
		setC_Charge_ID(iLine.getC_Charge_ID());
		setDescription(iLine.getDescription());
		setIsDescription(iLine.isDescription());
		//
		setC_Project_ID(iLine.getC_Project_ID());
		setC_ProjectPhase_ID(iLine.getC_ProjectPhase_ID());
		setC_ProjectTask_ID(iLine.getC_ProjectTask_ID());
		setC_Activity_ID(iLine.getC_Activity_ID());
		setC_Campaign_ID(iLine.getC_Campaign_ID());
		setAD_OrgTrx_ID(iLine.getAD_OrgTrx_ID());
		setUser1_ID(iLine.getUser1_ID());
		setUser2_ID(iLine.getUser2_ID());
	}	//	setInvoiceLine


	/**
	 * Obtenemos la lista de detalles del MInOutLine del producto del cargo.
	 * @param ctx
	 * @param M_Movement_ID
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
//	public static ArrayList<MInOutLine> getAllLotesProduct(final Properties ctx, final String loteInfo,
//			final String cFecha, final int m_Product_ID, final int m_UOM_ID, final int wareHouseID, final double ctdASurtir,
//			 final boolean esConfigurable, final boolean showLastGD, final String trxName) throws SQLException {
//
//		final StringBuffer sql = new StringBuffer(12000);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		final ArrayList<MInOutLine> lstMov = new ArrayList<MInOutLine>();
//
//		try {
//
////			if (showLastGD){
////				if(loteInfo.contains("%")){
////
////				}
////			}
//
//			BigDecimal ctdPendiente= new BigDecimal(ctdASurtir);
//			BigDecimal ctdEnMano = Env.ZERO;
//
//			sql.append(" SELECT  ")
//	        .append("    P.VALUE, ")
//	        .append("    P.NAME, ")
//	        .append("    P.M_PRODUCT_ID, ")
//	        .append("    U.NAME UDM,  ")
//	        .append("    U.C_UOM_ID, ")
//	        .append("    prodorg.ISLOT, ")
//	        .append("    SI.M_ATTRIBUTESETINSTANCE_ID, ")
//	        .append("    ST.QTYONHAND, ")
//	        .append("    ST.QTYRESERVED, ")
//	        .append("    ST.M_LOCATOR_ID, ")
//	        .append("    L.VALUE LOCATORINFO, ")
//	        .append("    W.DESCRIPTION WAREHOUSE, ")
//	        .append("    W.M_WAREHOUSE_ID, ")
//	        .append("    SI.LOT, ")
//	        .append("    SI.GUARANTEEDATE, ")
//	        .append("    SI.LOT || '|' || SI.DESCRIPTION || '|' || SI.GUARANTEEDATE LOTINFO ")
//			.append(" FROM M_STORAGE ST ")
//			.append("    INNER JOIN M_ATTRIBUTESETINSTANCE SI ON SI.M_ATTRIBUTESETINSTANCE_ID = ST.M_ATTRIBUTESETINSTANCE_ID AND SI.ISACTIVE='Y' ")
//			.append("    INNER JOIN M_PRODUCT P ON P.M_PRODUCT_ID = ST.M_PRODUCT_ID AND P.ISACTIVE='Y' ")
//			.append("    INNER JOIN C_UOM U ON P.C_UOM_ID = U.C_UOM_ID AND U.ISACTIVE='Y' ")
//			.append("    INNER JOIN M_LOCATOR L ON L.M_LOCATOR_ID = ST.M_LOCATOR_ID ")
//			.append("    INNER JOIN M_WAREHOUSE W ON W.M_WAREHOUSE_ID = L.M_WAREHOUSE_ID  ")
//			.append(" INNER JOIN exme_productoorg prodOrg on (P.m_product_id = prodorg.m_product_id AND prodorg.ad_org_id  = "+ Env.getAD_Org_ID(ctx)+" )  ")
//			.append("    WHERE ST.ISACTIVE='Y' ")
//			.append("    AND SI.LOT IS NOT NULL ")
//
//			// Permitir mostrar los lotes con fecha de caducidad vencida
//			.append("     AND( ('true' ='").append(showLastGD).append("') OR  SI.GUARANTEEDATE >= TO_DATE(?, 'DD/MM/YYYY'))")
//
//			/**
//			 * @TODO Pendiente Analizar funcionalidad la devolucion de lotes caducados y con almacen en cero"
//			 */
//			// Permitir mostrar los lotes con cantidad en mano en cero
//			.append("    AND( ('true' ='").append(showLastGD).append("') OR ST.QTYONHAND > 0 )")
//			.append("    AND ST.M_PRODUCT_ID = ? ")
//			.append("    AND P.C_UOM_ID=? ")
//			.append("    AND W.M_WAREHOUSE_ID=? ");
//
//			//Busqueda del lote ingresado, devoluciones de lotes
//			if (showLastGD){
//				sql.append(" 	AND ( SI.LOT=? OR SI.LOT LIKE ? )")
//				.append(" 	AND SI.GUARANTEEDATE IS NOT NULL ");
//			}
//
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","M_Storage", "ST"))
//			.append(" ORDER BY SI.GUARANTEEDATE, SI.LOT ");
//
//			s_log.finer("getDetail : " +  sql);
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setString(1,cFecha);
//			pstmt.setInt(2, m_Product_ID);
//			pstmt.setInt(3, m_UOM_ID);
//			pstmt.setInt(4, wareHouseID);
//
//			if (showLastGD){ // busqueda de lotes para devoluciones
//				pstmt.setString(5,loteInfo.contains("")?"":loteInfo);
//				pstmt.setString(6,!loteInfo.contains("")?"":loteInfo);
//			}
//
//
//			rs = pstmt.executeQuery();
//
//			int ln = 0;	//llevar el control de lineas
//
//			while (rs.next()) {
//
//				ln=+10;
//				final MInOutLine io = new MInOutLine(ctx, 0, trxName);
//
//				io.setLine(ln);
//				io.setC_OrderLine_ID(0);
//				io.setM_Locator_ID(rs.getInt("M_LOCATOR_ID"));
//				io.setM_Product_ID(rs.getInt("M_Product_ID"), rs.getInt("C_UOM_ID"));
//				io.setM_AttributeSetInstance_ID(rs.getInt("M_ATTRIBUTESETINSTANCE_ID"));
//				io.setLotInfo(rs.getString("LOTINFO"));
//
//				io.setProductDescribe(rs.getString("NAME"));		// descripcion del procducto
//				io.setWarehouseInf(rs.getString("WAREHOUSE"));		// descripicion del almacen
//				io.setM_Warehouse_ID(rs.getInt("M_WAREHOUSE_ID"));				// m_warehouse_id
//				io.setUomDescribe(rs.getString("UDM"));				// descripcion de la unidad de medida
//				io.setChecked(false);								// Si el procuto ha sido seleccionado
//				io.setLotInfo(rs.getString("LOTINFO"));				// descripcion del lote
//				io.setGuaranteeDate(Constantes.getSdfFecha().format(rs.getDate("GUARANTEEDATE")));	// Fecha de caducidad
//				io.setQtyonhand(rs.getBigDecimal("QTYONHAND"));			// Productos disponibles
//				io.setQtyReserved(rs.getBigDecimal("QTYRESERVED"));
//				io.setQtyonFree(io.getQtyonhand().subtract(io.qtyReserved));
//				ctdEnMano=rs.getBigDecimal("QTYONHAND");					// Determinar la cantidad que puede surtir
//
//
//				// Si es devolucion de lotes no sugerir cantidades
//				if (showLastGD){
//					io.setChecked(true);
//					io.setQtyonhandSurt(ctdPendiente);
//					lstMov.add(io);
//					continue;
//				}
//
//				// SUGERIR CANTIDADES A SURTIR
//				// Si la cantidad Pendiente es mayor que la cantidad total del lote. sugerir la cantidad diponible
//				if(ctdPendiente.compareTo(ctdEnMano)>0 && ctdEnMano.compareTo(Env.ZERO)>0){
//					io.setChecked(true);
//					io.setQtyonhandSurt(ctdEnMano);
//					ctdPendiente = ctdPendiente.subtract(ctdEnMano);
//					lstMov.add(io);
//
//				// Si la cantidad Pendiente es menor a la cantidad total del lote, sugerir la cantidad total del lote
//				}else if (ctdPendiente.compareTo(ctdEnMano)<=0 && ctdPendiente.compareTo(Env.ZERO)>=0){
//
//					io.setChecked(true);
//					io.setQtyonhandSurt(ctdPendiente);
//					ctdPendiente = Env.ONE.negate();
//
//					lstMov.add(io);
//					if(!esConfigurable) {
//						break;
//					}
//
//				// Si ya no hay cantidad pendiente no sugerir nada
//				}else if (ctdPendiente.compareTo(Env.ZERO)<0){
//					io.setChecked(false);
//					io.setQtyonhandSurt(Env.ZERO);
//					lstMov.add(io);
//				}
//			}
//
//		} catch (final SQLException e) {
//			s_log.log(Level.SEVERE, "getDetail (" + sql + ")", e);
//			throw e;
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return lstMov;
//	}



	/**
	 * Obtenemos las cantidades cargadas a una determina cuenta paciente para un producto para un attributesetinstance.
	 * @param ctx
	 * @param M_Movement_ID
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
//	public static int getQtyCharges(final Properties ctx, final int ctaPac_ID, final int product_ID,
//			final int wareHouseID, final int attSetInstanceID, final String trxName) throws SQLException {
//
//		int qtyCharges = 0; //total de cargos
//		final StringBuffer sql = new StringBuffer(1200);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			sql.append("  SELECT SUM(NVL(qtyCargos,0)) AS qtyCargos ")
//				.append(" FROM( ")
//				.append("   SELECT DISTINCT(cpd.EXME_CtaPacDet_id ), NVL(il.MOVEMENTQTY,0) qtyCargos ")
//				.append(" 	FROM EXME_CtaPacDet cpd ")
//				.append("    LEFT JOIN EXME_CtaPacExt cpe ")
//				.append("     ON ( cpd.EXME_CtaPacExt_ID  = cpe.EXME_CtaPacExt_ID AND cpe.IsActive   = 'Y' ) ")
//				.append("    LEFT JOIN M_inout io ON (cpd.m_inout_id = io.m_inout_id AND io.IsActive   = 'Y') ")
//				.append("    LEFT JOIN m_inoutline il ON (io.M_inout_id = il.M_inout_id  AND il.IsActive   = 'Y') ")
//
//				.append(" 	WHERE cpd.IsActive      = 'Y' ")
//				.append("  	 AND cpd.EXME_CtaPac_ID = ? ")
//				.append("  	 AND cpd.M_Product_ID   = ? ")
//				.append("  	 AND cpd.M_Warehouse_ID = ? ")
//				.append("  	 AND cpd.EXME_PaqBase_Version_ID IS NULL ")
//				.append("  	 AND cpe.Isinvoiced     = 'N' ")
//				.append("  	 AND cpe.C_Invoice_ID   IS NULL ")
//				.append("  	 AND il.M_AttributeSetInstance_ID    = ? ");
//
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_CtaPacDet", "cpd"));
//			sql.append(")");
//
//			s_log.finer("getDetail : " +  sql);
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1,ctaPac_ID);
//			pstmt.setInt(2, product_ID);
//			pstmt.setInt(3, wareHouseID);
//			pstmt.setInt(4, attSetInstanceID);
//
//			rs = pstmt.executeQuery();
//
//
//
//			if (rs.next()) {
//				qtyCharges = rs.getInt(1);
//			}
//
//		} catch (final SQLException e) {
//			s_log.log(Level.SEVERE, "getDetail (" + sql + ")", e);
//			throw e;
//		} finally {
//			DB.close(rs, pstmt);
//
//			rs = null;
//			pstmt = null;
//		}
//
//		return qtyCharges;
//	}


	/**
	 * 	Get Warehouse
	 *	@return Returns the m_Warehouse_ID.
	 */
	public int getM_Warehouse_ID()
	{
		if (m_M_Warehouse_ID == 0) {
			m_M_Warehouse_ID = getParent().getM_Warehouse_ID();
		}
		return m_M_Warehouse_ID;
	}	//	getM_Warehouse_ID

	/**
	 * 	Set Warehouse
	 *	@param warehouse_ID The m_Warehouse_ID to set.
	 */
	public void setM_Warehouse_ID (final int warehouse_ID)
	{
		m_M_Warehouse_ID = warehouse_ID;
	}	//	setM_Warehouse_ID

	/**
	 * 	Set M_Locator_ID
	 *	@param M_Locator_ID id
	 */
	@Override
	public void setM_Locator_ID (final int M_Locator_ID)
	{
		if (M_Locator_ID < 0) {
			throw new IllegalArgumentException ("M_Locator_ID is mandatory.");
		}
		//	set to 0 explicitly to reset
		set_Value ("M_Locator_ID", Integer.valueOf(M_Locator_ID));
	}	//	setM_Locator_ID

	/**
	 * 	Set (default) Locator based on qty.
	 * 	@param Qty quantity
	 * 	Assumes Warehouse is set
	 */
	public void setM_Locator_ID(final BigDecimal Qty)
	{
		//	Locator esatblished
		if (getM_Locator_ID() != 0) {
			return;
		}
		//	No Product
		if (getM_Product_ID() == 0)
		{
			set_ValueNoCheck("M_Locator_ID", null);
			return;
		}

		//	Get existing Location
		int M_Locator_ID = MStorage.getMLocatorID (getM_Warehouse_ID(),
				getM_Product_ID(), getM_AttributeSetInstance_ID(),
				Qty, get_TrxName());
		//	Get default Location
		if (M_Locator_ID == 0)
		{
			final MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
			M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
		}
		setM_Locator_ID(M_Locator_ID);
	}	//	setM_Locator_ID

	/**
	 * 	Set Movement/Movement Qty
	 *	@param Qty Entered/Movement Qty
	 */
	public void setQty (final BigDecimal Qty)
	{
		setQtyEntered(Qty);
		setMovementQty(getQtyEntered());
	}	//	setQtyInvoiced

	/**
	 * 	Set Qty Entered - enforce entered UOM
	 *	@param QtyEntered
	 */
	@Override
	public void setQtyEntered (BigDecimal QtyEntered)
	{
		if (QtyEntered != null && getC_UOM_ID() != 0)
		{
			final int precision = MUOM.getPrecision(getCtx(), getC_UOM_ID());
			QtyEntered = QtyEntered.setScale(precision, BigDecimal.ROUND_HALF_UP);
		}
		super.setQtyEntered (QtyEntered);
	}	//	setQtyEntered

	/**
	 * 	Set Movement Qty - enforce Product UOM
	 *	@param MovementQty
	 */
	@Override
	public void setMovementQty (BigDecimal MovementQty)
	{
		final MProduct product = getProduct();
		if (MovementQty != null && product != null)
		{
			final int precision = product.getUOMPrecision();
			MovementQty = MovementQty.setScale(precision, BigDecimal.ROUND_HALF_UP);
		}
		super.setMovementQty(MovementQty);
	}	//	setMovementQty

	/**
	 * 	Get Product
	 *	@return product or null
	 */
	public MProduct getProduct()
	{
		if (m_product == null && getM_Product_ID() != 0) {
			m_product = MProduct.get (getCtx(), getM_Product_ID());
		}
		return m_product;
	}	//	getProduct

	/**
	 * 	Set Product
	 *	@param product product
	 */
	public void setProduct (final MProduct product)
	{
		m_product = product;
		if (m_product == null)
		{
			setM_Product_ID(0);
			setC_UOM_ID (0);
		}
		else
		{
			setM_Product_ID(m_product.getM_Product_ID());
			setC_UOM_ID (m_product.getC_UOM_ID());
		}
		setM_AttributeSetInstance_ID(0);
	}	//	setProduct

	/**
	 * 	Set M_Product_ID
	 *	@param M_Product_ID product
	 *	@param setUOM also set UOM from product
	 */
	public void setM_Product_ID (final int M_Product_ID, final boolean setUOM)
	{
		if (setUOM) {
			setProduct(MProduct.get(getCtx(), M_Product_ID));
		} else {
			super.setM_Product_ID (M_Product_ID);
		}
		setM_AttributeSetInstance_ID(0);
	}	//	setM_Product_ID

	/**
	 * 	Set Product and UOM
	 *	@param M_Product_ID product
	 *	@param C_UOM_ID uom
	 */
	public void setM_Product_ID (final int M_Product_ID, final int C_UOM_ID)
	{
		if (M_Product_ID != 0) {
			super.setM_Product_ID (M_Product_ID);
		}
		super.setC_UOM_ID(C_UOM_ID);
		setM_AttributeSetInstance_ID(0);
		m_product = null;
	}	//	setM_Product_ID

	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (final String description)
	{
		final String desc = getDescription();
		if (desc == null) {
			setDescription(description);
		} else {
			setDescription(desc + " | " + description);
		}
	}	//	addDescription

	/**
	 * 	Get C_Project_ID
	 *	@return project
	 */
	@Override
	public int getC_Project_ID()
	{
		int ii = super.getC_Project_ID ();
		if (ii == 0) {
			ii = getParent().getC_Project_ID();
		}
		return ii;
	}	//	getC_Project_ID

	/**
	 * 	Get C_Activity_ID
	 *	@return Activity
	 */
	@Override
	public int getC_Activity_ID()
	{
		int ii = super.getC_Activity_ID ();
		if (ii == 0) {
			ii = getParent().getC_Activity_ID();
		}
		return ii;
	}	//	getC_Activity_ID

	/**
	 * 	Get C_Campaign_ID
	 *	@return Campaign
	 */
	@Override
	public int getC_Campaign_ID()
	{
		int ii = super.getC_Campaign_ID ();
		if (ii == 0) {
			ii = getParent().getC_Campaign_ID();
		}
		return ii;
	}	//	getC_Campaign_ID

	/**
	 * 	Get User2_ID
	 *	@return User2
	 */
	@Override
	public int getUser1_ID ()
	{
		int ii = super.getUser1_ID ();
		if (ii == 0) {
			ii = getParent().getUser1_ID();
		}
		return ii;
	}	//	getUser1_ID

	/**
	 * 	Get User2_ID
	 *	@return User2
	 */
	@Override
	public int getUser2_ID ()
	{
		int ii = super.getUser2_ID ();
		if (ii == 0) {
			ii = getParent().getUser2_ID();
		}
		return ii;
	}	//	getUser2_ID

	/**
	 * 	Get AD_OrgTrx_ID
	 *	@return trx org
	 */
	@Override
	public int getAD_OrgTrx_ID()
	{
		int ii = super.getAD_OrgTrx_ID();
		if (ii == 0) {
			ii = getParent().getAD_OrgTrx_ID();
		}
		return ii;
	}	//	getAD_OrgTrx_ID

	//expert : gisela lee : cambios de jjanke en version 26b
	/**
	 * 	Get Match POs
	 *	@return matched purchase orders
	 */
	public MMatchPO[] getMatchPO()
	{
		if (m_matchPO == null) {
			m_matchPO = MMatchPO.get (getCtx(), getM_InOutLine_ID(), get_TrxName());
		}
		return m_matchPO;
	}	//	getMatchPO

	/**
	 * 	Get Match PO Difference
	 *	@return not matched qty (positive not - negative over)
	 */
	public BigDecimal getMatchPODifference()
	{
		if (isDescription()) {
			return Env.ZERO;
		}
		BigDecimal retValue = getMovementQty();
		final MMatchPO[] po = getMatchPO();
		for (final MMatchPO matchPO : po) {
			retValue = retValue.subtract (matchPO.getQty());
		}
		log.finer("#" + retValue);
		return retValue;
	}	//	getMatchPODifference

	/**
	 * 	Is Match PO posted
	 *	@return true if posed
	 */
	public boolean isMatchPOPosted()
	{

		boolean retValue= true;
		final MMatchPO[] po = getMatchPO();
		for (final MMatchPO element : po) {
			final MMatchPO matchPO = element;
			if (!matchPO.isPosted() && retValue) {
				retValue = false;
				break;
			}
		}
		return retValue;
	}	//	isMatchPOposted

	/**
	 * 	Get Match Inv
	 *	@return matched invoices
	 */
	public MMatchInv[] getMatchInv()
	{
		if (m_matchInv == null) {
			m_matchInv = MMatchInv.get (getCtx(), getM_InOutLine_ID(), get_TrxName());
		}
		return m_matchInv;
	}	//	getMatchInv

	/**
	 * 	Get Match Inv Difference
	 *	@return not matched qty (positive not - negative over)
	 */
	public BigDecimal getMatchInvDifference()
	{
		if (isDescription()) {
			return Env.ZERO;
		}
		BigDecimal retValue = getMovementQty();
		final MMatchInv[] inv = getMatchInv();
		for (final MMatchInv matchInv : inv) {
			retValue = retValue.subtract (matchInv.getQty());
		}
		log.finer("#" + retValue);
		return retValue;
	}	//	getMatchInvDifference

	/**
	 * 	Is Match Inv posted
	 *	@return true if posed
	 */
	public boolean isMatchInvPosted() {
		boolean retValue = true;
		final MMatchInv[] inv = getMatchInv();
		for (final MMatchInv element : inv) {
			final MMatchInv matchInv = element;
			if (!matchInv.isPosted() && retValue) {
				retValue = false;
				break;
			}
		}
		return retValue;
	} // isMatchPOposted
	//expert : gisela lee : cambios de jjanke en version 26b

	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return save
	 */
	@Override
	protected boolean beforeSave (final boolean newRecord)
	{
		final boolean retValue = true;
		log.fine("");
		//	Get Line No
		if (getLine() == 0)
		{
			final String sql = "SELECT COALESCE(MAX(Line),0)+10 FROM M_InOutLine WHERE M_InOut_ID=?";
			final int ii = DB.getSQLValue (get_TrxName(), sql, getM_InOut_ID());
			setLine (ii);
		}
		//	UOM
		if (getC_UOM_ID() == 0) {
			setC_UOM_ID (Env.getContextAsInt(getCtx(), "#C_UOM_ID"));
		}
		if (getC_UOM_ID() == 0)
		{
			final int C_UOM_ID = MUOM.getDefault_UOM_ID(getCtx());
			if (C_UOM_ID > 0) {
				setC_UOM_ID (C_UOM_ID);
			}
		}
		//	Qty Precision
		if (newRecord || is_ValueChanged("QtyEntered")) {
			setQtyEntered(getQtyEntered());
		}
		if (newRecord || is_ValueChanged("MovementQty")) {
			setMovementQty(getMovementQty());
		}

		//	Order Line
		//No es obligatorio el valor del campo
//		if (getC_OrderLine_ID() == 0)
//		{
//			if (getParent().isSOTrx())
//			{
//				log.saveError("FillMandatory", Msg.translate(getCtx(), "C_Order_ID"));
//				//return false;
//			}
//		}

	//	if (getC_Charge_ID() == 0 && getM_Product_ID() == 0)
	//		;

		/**	 Qty on instance ASI
		if (getM_AttributeSetInstance_ID() != 0)
		{
			MProduct product = getProduct();
			int M_AttributeSet_ID = product.getM_AttributeSet_ID();
			boolean isInstance = M_AttributeSet_ID != 0;
			if (isInstance)
			{
				MAttributeSet mas = MAttributeSet.get(getCtx(), M_AttributeSet_ID);
				isInstance = mas.isInstanceAttribute();
			}
			//	Max
			if (isInstance)
			{
				MStorage storage = MStorage.get(getCtx(), getM_Locator_ID(),
					getM_Product_ID(), getM_AttributeSetInstance_ID(), get_TrxName());
				if (storage != null)
				{
					BigDecimal qty = storage.getQtyOnHand();
					if (getMovementQty().compareTo(qty) > 0)
					{
						log.warning("Qty - Stock=" + qty + ", Movement=" + getMovementQty());
						log.saveError("QtyInsufficient", "=" + qty);
						return false;
					}
				}
			}
		}	/**/

//		if(getProductsLine().contains(getM_Product_ID()) && is_new()){
//			final Object param[]={getM_Product().getName()};
//			log.saveError("Error", Utilerias.getAppMsg(getCtx(), "error.citas.existeMed", param));
//			retValue = false;
//		}

		// Clase de producto para el post
		if (getEXME_ProductClassFact_ID()<=0 && getM_Product_ID()>0){
			setEXME_ProductClassFact_ID(MEXMEProductClassFact.get(getCtx(), getM_Product_ID(), get_TrxName()));
		}

		// Solo cuando es una transaccion de compra y no es un almacen de consigna
//		if (retValue && !getParent().isSetpoint() && !getParent().isSOTrx()) {
//			final MEXMEProductoOrg pOrg = MEXMEProductoOrg.getProductoOrg(Env.getCtx(), getM_Product().getM_Product_ID(), Env.getAD_Client_ID(Env.getCtx()), Env.getAD_Org_ID(Env.getCtx()), null);
//			if (pOrg.isConsigna()) {
//				final Object param[]={getM_Product().getName()};
//				log.saveError("Error", Utilerias.getAppMsg(getCtx(), "material.prod.consig", param));
//				retValue = false;
//			}
//		}

		return retValue;
	}	//	beforeSave

	/**
	 * 	Before Delete
	 *	@return true if drafted
	 */
	@Override
	protected boolean beforeDelete ()
	{
		boolean retValue = false;
		if (getParent().getDocStatus().equals(X_M_InOut.DOCSTATUS_Drafted) && !retValue) {
			retValue = true;
		}
		if(!retValue){
		log.saveError("Error", Msg.getMsg(getCtx(), "CannotDelete"));
		}
		return retValue;
	}	//	beforeDelete

	/**
	 * 	String Representation
	 *	@return info
	 */
	@Override
	public String toString ()
	{
		final StringBuffer sb = new StringBuffer ("MInOutLine[").append (get_ID())
			.append(",M_Product_ID=").append(getM_Product_ID())
			.append(",QtyEntered=").append(getQtyEntered())
			.append(",MovementQty=").append(getMovementQty())
			.append(",M_AttributeSetInstance_ID=").append(getM_AttributeSetInstance_ID())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Base value for Cost Distribution
	 *	@param CostDistribution cost Distribution
	 *	@return base number
	 */
	public BigDecimal getBase (final String CostDistribution)
	{
		if (X_C_LandedCost.LANDEDCOSTDISTRIBUTION_Costs.equals(CostDistribution))
		{
			//	TODO Costs!
			log.severe("Not Implemented yet - Cost");
			return Env.ZERO;
		}
		else if (X_C_LandedCost.LANDEDCOSTDISTRIBUTION_Line.equals(CostDistribution)) {
			return Env.ONE;
		} else if (X_C_LandedCost.LANDEDCOSTDISTRIBUTION_Quantity.equals(CostDistribution)) {
			return getMovementQty();
		} else if (X_C_LandedCost.LANDEDCOSTDISTRIBUTION_Volume.equals(CostDistribution))
		{
			final MProduct product = getProduct();
			if (product == null)
			{
				log.severe("No Product");
				return Env.ZERO;
			}
			return getMovementQty().multiply(product.getVolume());
		}
		else if (X_C_LandedCost.LANDEDCOSTDISTRIBUTION_Weight.equals(CostDistribution))
		{
			final MProduct product = getProduct();
			if (product == null)
			{
				log.severe("No Product");
				return Env.ZERO;
			}
			return getMovementQty().multiply(product.getWeight());
		}
		//
		log.severe("Invalid Criteria: " + CostDistribution);
		return Env.ZERO;
	}	//	getBase

	/**
	 * 	Get Inout Lines All
	 *	@param Id_Inout
	 *	@return Lista de Lineas de Inout
	 */
	public ArrayList<GenericModel> getInoutLineAll(final int id_inout) {

		final ArrayList<GenericModel> lista = new ArrayList<GenericModel>();

		final String sql = "SELECT ml.m_inoutline_id, ml.line, ml.m_product_id, ml.qtyentered, ml.c_uom_id, mp.name, cu.name, ml.m_locator_id, ml.description, ml.c_project_id, ml.c_activity_id, ml.c_projectphase_id, ml.c_projecttask_id, ml.ad_orgtrx_id, ml.c_campaign_id, ml.lotinfo " +
				"FROM m_inoutline ml " +
				"INNER JOIN m_product mp on ml.m_product_id=mp.m_product_id " +
				"INNER JOIN c_uom cu on ml.c_uom_id=cu.c_uom_id " +
				"WHERE ml.isactive='Y' " +
				"AND ml.m_inout_id=? " +
				"ORDER BY ml.line ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, id_inout);
			rs = pstmt.executeQuery();
			//Obtenemos datos para mostrar en grilla
			GenericModel part = null;
			while (rs.next()) {

				part = new GenericModel();

				part.setM_inoutline_id(rs.getString(1));
				part.setNumLinea(rs.getLong(2));
				part.setM_prodcut_id(rs.getInt(3));
				part.setOriginalQty(rs.getLong(4));
				part.setC_uom_id(rs.getInt(5));
				part.setProdName(rs.getString(6));
				part.setUomName(rs.getString(7));
				part.setLocalizador(rs.getString(8));
				part.setDescripcion(rs.getString(9));
				part.setProyecto(rs.getString(10));
				part.setFuenteFinanciamiento(rs.getString(11));
				part.setFaseProyecto(rs.getString(12));
				part.setTareaProyecto(rs.getString(13));
				part.setOrgTrx(rs.getString(14));
				part.setPrograma(rs.getString(15));
				part.setLoteInfo(rs.getString(16));

				lista.add(part);
			}

		} catch (final Exception e) {
			log.log(Level.SEVERE, "getLines", e);
		} finally {
			DB.close(rs, pstmt);

		}
		return lista;
	}

	/** Set RMA Line.
	@param M_RMALine_ID
	Return Material Authorization Line
  */
	@Override
	public void setM_RMALine_ID (final int M_RMALine_ID)
	{
		if (M_RMALine_ID < 1) {
			set_Value ("M_RMALine_ID", null);
		} else {
			set_Value ("M_RMALine_ID", Integer.valueOf(M_RMALine_ID));
		}
	}



	  /**
		 * 	Set Indicaci�n Line.
		 * 	Does not set Quantity!
		 *	@param oLine order line
		 */
		public void setOrderLine (final MEXMEActPacienteInd aLine, final int M_Locator_ID)
		{
			//setC_OrderLine_ID();
			setLine(aLine.getLine());
			final MProduct product = aLine.getProduct();
			if (product == null)
			{
				set_ValueNoCheck("M_Product_ID", null);
				set_ValueNoCheck("M_AttributeSetInstance_ID", null);
				set_ValueNoCheck("M_Locator_ID", null);
			}
			else
			{
				setM_Product_ID(aLine.getM_Product_ID());
				setM_AttributeSetInstance_ID(0);
				setC_UOM_ID(product.getC_UOM_ID());
				setC_UOMVolume_ID(aLine.getC_UOM_ID());
				//
				if (product.isItem())
				{
					if (M_Locator_ID == 0) {
						setM_Locator_ID(Env.ZERO);	//	requires warehouse, product, asi
					} else {
						setM_Locator_ID(M_Locator_ID);
					}
				} else {
					set_ValueNoCheck("M_Locator_ID", null);
				}
			}
			setDescription(aLine.getDescription());
			setIsDescription(aLine.isDescription());
		}	//	setOrderLine

		/**
		 * 	Set Indicaci�n Line.
		 * 	Does not set Quantity!
		 *	@param oLine order line
		 */
		public void setOrderLine (final MCtaPacDet cLine, final int M_Locator_ID, final BigDecimal Qty)
		{
			//setC_OrderLine_ID();
			setEXME_CtaPacDet_ID(cLine.getEXME_CtaPacDet_ID());
			setLine(cLine.getLine());
			setC_UOM_ID(cLine.getC_UOM_ID());
			final MProduct product = cLine.getProduct();
			if (product == null)
			{
				set_ValueNoCheck("M_Product_ID", null);
				set_ValueNoCheck("M_AttributeSetInstance_ID", null);
				set_ValueNoCheck("M_Locator_ID", null);
			}
			else
			{
				setM_Product_ID(cLine.getM_Product_ID());
				setM_AttributeSetInstance_ID(0);
				//
				if (product.isItem())
				{
					if (M_Locator_ID == 0) {
						setM_Locator_ID(Qty);	//	requires warehouse, product, asi
					} else {
						setM_Locator_ID(M_Locator_ID);
					}
				} else {
					set_ValueNoCheck("M_Locator_ID", null);
				}
			}
			setC_Charge_ID(cLine.getC_Charge_ID());
			setDescription(cLine.getDescription());
			setIsDescription(cLine.isDescription());
			//
			/*setC_Project_ID(cLine.getC_Project_ID());
			setC_ProjectPhase_ID(cLine.getC_ProjectPhase_ID());
			setC_ProjectTask_ID(cLine.getC_ProjectTask_ID());
			setC_Activity_ID(cLine.getC_Activity_ID());
			setC_Campaign_ID(cLine.getC_Campaign_ID());*/
			setAD_OrgTrx_ID(cLine.getAD_OrgTrx_ID());//La configurada en el detalle del cargo diario
			/*setUser1_ID(cLine.getUser1_ID());
			setUser2_ID(cLine.getUser2_ID());*/

		}	//	setOrderLine

		/**
		 * 	Set Movimiento Line.
		 * 	Does not set Quantity!
		 *	@param oLine order line
		 */
		public void setOrderLine (final MMovementLine mLine, final int M_Locator_ID)
		{
			//setC_OrderLine_ID();
			setLine(mLine.getLine());
			final MProduct product = new MProduct(getCtx(), mLine.getM_Product_ID(), get_TrxName());
			// La UOM se toma la del producto. � Los movimeintos entre almacenes solo son en UOM de almacenamiento!.
			setC_UOM_ID(product.getC_UOM_ID());

//			if (product == null) //dead code
//			{
//				set_ValueNoCheck("M_Product_ID", null);
//				set_ValueNoCheck("M_AttributeSetInstance_ID", null);
//				set_ValueNoCheck("M_Locator_ID", null);
//			}
//			else
//			{
				setM_Product_ID(mLine.getM_Product_ID());
				setM_AttributeSetInstance_ID(0);
				//
				if (product.isItem())
				{
					if (M_Locator_ID == 0) {
						setM_Locator_ID(Env.ZERO);	//	requires warehouse, product, asi
					} else {
						setM_Locator_ID(M_Locator_ID);
					}
				} else {
					set_ValueNoCheck("M_Locator_ID", null);
				}
//			}
			setDescription(mLine.getDescription());
			setIsDescription(false);
		}	//	setOrderLine

	    public MMovementLine getMovementLine() {
	        return m_movementLine;
	    }
	    public void setMovementLine(final MMovementLine line) {
	        m_movementLine = line;
	    }

	    public MLocator getLocator(){
	        return locator;
	    }

	    /**
	     * Precios en inout deacuerdo al cargo de la cuenta paciente
	     * cuando exista un producto, Considera las unidades de medida
	     * @param mCtaPacDet
	     */
		public void setPrices(final MCtaPacDet mCtaPacDet) {
			if(mCtaPacDet==null || mCtaPacDet.getProduct()==null){
				return;
			}

			// Misma unidad de medida
			if(mCtaPacDet.getProduct().getC_UOM_ID() == mCtaPacDet.getProduct().getC_UOMVolume_ID()){
				setPriceActual(mCtaPacDet.getPriceActual());
				setPriceActual_Vol(mCtaPacDet.getPriceActual());
			} else {
				// minima
				if(getC_UOM_ID()==mCtaPacDet.getProduct().getC_UOM_ID()){
					setPriceActual(mCtaPacDet.getPriceActual());
					setPriceActual_Vol(Env.ZERO);
				}
				// Volumen
				if(getC_UOM_ID()==mCtaPacDet.getProduct().getC_UOMVolume_ID()){
					setPriceActual(Env.ZERO);
					setPriceActual_Vol(mCtaPacDet.getPriceActual());
				}
			}
		}

	private static BigDecimal tax = BigDecimal.ZERO;
	private BigDecimal subtotal = getQtyEntered_Vol().multiply(getPriceActual_Vol());
	private static BigDecimal total = BigDecimal.ZERO;
	private MUOM uomVol;
	private BigDecimal discountQty;
	private final MLocator locator = new MLocator(getCtx(), getM_Locator_ID(), get_TrxName());
	private MOrg org;
	private BigDecimal qtyReturn = null;
	
	/**
	 * @return the tax
	 */
	public BigDecimal getTax() {
		final MTax mTax = new MTax(Env.getCtx(), getC_Tax_ID(), null);
		final BigDecimal taxRate = mTax.getC_Tax_ID() > 0 ? mTax.getRate() : BigDecimal.ZERO;
		tax = subtotal.multiply(taxRate.divide(new BigDecimal("100")));
		return tax;
	}

	/**
	 * @return the subtotal
	 */
	public BigDecimal getSubtotal() {
		if(getPriceList_Vol().compareTo(BigDecimal.ZERO) ==  0) {
			subtotal = subtotal.subtract(getQtyEntered_Vol().multiply(getPriceActual_Vol()).multiply(getDiscount().divide(new BigDecimal("100"))));
		}
		return subtotal;
	}

	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {
		total = subtotal.add(tax);
		return total;
	}

	/**
	 * @return the uomVol
	 */
	public MUOM getUomVol() {
		if (uomVol == null) {
			uomVol = new MUOM(getCtx(), getC_UOMVolume_ID(), null);
		}
		return uomVol;
	}

	/**
	 * @return the discountQty
	 */
	public BigDecimal getDiscountQty() {
		if (getPriceList_Vol().compareTo(BigDecimal.ZERO) > 0) {
			discountQty = getPriceList_Vol().subtract(getPriceActual_Vol());
		} else {
			discountQty = getPriceActual_Vol().multiply(getDiscount().divide(new BigDecimal("100")));
		}
		return discountQty;
	}

	/**
	 * @return the org
	 */
	public MOrg getOrg() {
		org = new MOrg(getCtx(), getAD_OrgTrx_ID(), null);
		return org;
	}
	
	public BigDecimal getQtyOrdered_Vol() {
		return getMovementQty_Vol();
	}
	
	/** Cantidad devuelta previamente al proveedor,
	 *  en la unidad de medida en que se hizo la recepción */
	public BigDecimal getQtyReturn() {
		if(qtyReturn==null){
			qtyReturn = getMovementQtyRef(); 
		}
		return qtyReturn == null?Env.ZERO:qtyReturn;
	}
	
	/** Buscar la línea que ha sido devuelta */
	public BigDecimal getMovementQtyRef() {

		BigDecimal line = Env.ZERO;
		final StringBuilder sql = new StringBuilder(" SELECT SUM(COALESCE( line.MovementQty_Vol,0)) as qty ")
		.append(" FROM M_InOutLine line ")
		.append(" INNER JOIN M_InOut hdr ON hdr.M_InOut_ID = line.M_InOut_ID ")
		.append(" AND hdr.DocStatus NOT IN (").append(DB.TO_STRING(X_M_InOut.DOCSTATUS_Voided))
		.append(" ,                         ").append(DB.TO_STRING(X_M_InOut.DOCSTATUS_Reversed)).append(") ")
		.append(" WHERE line.IsActive       = 'Y' ")
		.append(" AND line.M_Inout_ID      <> ?   ")// Que no sea la devolucion que se esta creando
		.append(" AND line.Ref_InOutLine_ID = ?   ")// Hace referencia a la linea de la recepción
		.append(" AND line.M_Product_ID     = ?   ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getM_InOut_ID());
			pstmt.setInt(2, getM_InOutLine_ID());
			pstmt.setInt(3, getM_Product_ID());
			rs = pstmt.executeQuery();
			if (rs.next()){
				line = rs.getBigDecimal(1);
			}
		} catch (final SQLException ex) {
			log.log(Level.SEVERE, sql.toString(), ex);
		} finally {
			DB.close(rs,pstmt);
		}
		if(line==null){
			line = Env.ZERO;
		}
		//
		return line.abs();
	} // getMInOutLines
	
	public List<MMatchPO> getlMatchPO() {
		return lMatchPO;
	}

	public void setlMatchPO(List<MMatchPO> lMatchPO) {
		this.lMatchPO = lMatchPO;
	}

	public List<MTransaction> getlTransaction() {
		return lTransaction;
	}

	public void setlTransaction(List<MTransaction> lTransaction) {
		this.lTransaction = lTransaction;
	}
	
	/** Obtener los costos actuales de acuerdo al producto */ 
	public GetCost getCost(){
		if(mGetCost==null){
			mGetCost = new GetCost(getCtx(), getM_Product_ID()).setCosts();
		}
		return mGetCost;
		// Comentar para soportar multiples esquemas 
//		final GetCost mCost = line.getCost(); 
//		trxs.setCostAverage(mCost.getCostAverage());
//		trxs.setCostStandard(mCost.getCostStandard());
//		trxs.setPriceLastPO(mCost.getPriceLastPO());
	}
	
	/** Obtener el costo del embarque Card 1444*/
	public BigDecimal getTransactionalCost(final MAcctSchema as, final boolean isDevol){
		BigDecimal costo = Env.ZERO;
		
		// En caso de ser una devolución busca el embarque original
		final MInOutLine mInOutLine = isDevol
				? MInOutLine.getLineID(getCtx(), getRef_InOutLine_ID(), get_TrxName())
				: MInOutLine.getLineID(getCtx(), getM_InOutLine_ID(),   get_TrxName()); 
		
		// Solo los productos
		if((getProduct().isItem() && mInOutLine!=null) 
				|| (isDevol && getM_InOut().isSOTrx() && mInOutLine==null) ){
			
			if(mInOutLine==null){ //### para corregir datos viejitos
				final String costingMethod = getProduct().getCostingMethod(as);
				// Costo estandar
				mGetCost = getCost();
				if(mGetCost!=null){
					log.log(Level.INFO, "Costos calculado en MCost");
					if(X_C_AcctSchema.COSTINGMETHOD_AveragePO.equals(costingMethod)){
						costo = mGetCost.getCostAverage();
//					} else if(X_C_AcctSchema.COSTINGMETHOD_AverageInvoice.equals(costingMethod)){
//						costo = mGetCost.getCostAverageI();
					} else if(X_C_AcctSchema.COSTINGMETHOD_StandardCosting.equals(costingMethod)){
						costo = mGetCost.getCostStandard();
					} else if(X_C_AcctSchema.COSTINGMETHOD_LastPOPrice.equals(costingMethod)){
						costo = mGetCost.getPriceLastPO();
					}
				}
				
			} else {
				final MTransaction mTransaction = MTransaction.getTransactionInOutLine(mInOutLine);
				final String costingMethod = getProduct().getCostingMethod(as);
				log.log(Level.INFO, "El Costo costingMethod:"+costingMethod);
				
				if(mTransaction!=null ){
					
					log.log(Level.INFO, "Buscando el costo en la transacción :"+mTransaction.getM_Transaction_ID());
					if(X_C_AcctSchema.COSTINGMETHOD_AveragePO.equals(costingMethod)){
						costo = mTransaction.getCostAverage();
					} else if(X_C_AcctSchema.COSTINGMETHOD_AverageInvoice.equals(costingMethod)){
						costo = mTransaction.getCostAverageI();
					} else if(X_C_AcctSchema.COSTINGMETHOD_StandardCosting.equals(costingMethod)){
						costo = mTransaction.getCostStandard();
					} else if(X_C_AcctSchema.COSTINGMETHOD_LastPOPrice.equals(costingMethod)){
						costo = mTransaction.getPriceLastPO();
					}
					
				} else {
					
					// Costo estandar
					mGetCost = getCost();
					if(mGetCost!=null){
						log.log(Level.INFO, "Costos calculado en MCost");
						if(X_C_AcctSchema.COSTINGMETHOD_AveragePO.equals(costingMethod)){
							costo = mGetCost.getCostAverage();
	//					} else if(X_C_AcctSchema.COSTINGMETHOD_AverageInvoice.equals(costingMethod)){
	//						costo = mGetCost.getCostAverageI();
						} else if(X_C_AcctSchema.COSTINGMETHOD_StandardCosting.equals(costingMethod)){
							costo = mGetCost.getCostStandard();
						} else if(X_C_AcctSchema.COSTINGMETHOD_LastPOPrice.equals(costingMethod)){
							costo = mGetCost.getPriceLastPO();
						}
					}
				}
			}
			log.log(Level.INFO, "El Costo calculado :"+String.valueOf(costo==null?Env.ZERO:costo));
		} // sino es servicio y entonces es el costo estandar

		// Por defecto el costo estandar
		if(costo == null || costo.compareTo(Env.ZERO)==0){
			// Costo estandar
			mGetCost = getCost();
			if(mGetCost!=null){
				log.log(Level.INFO, "Costo estandar");
				costo = mGetCost.getCostStandard();
			}
		}
		log.log(Level.INFO, "El Costo final :"+String.valueOf(costo==null?Env.ZERO:costo));
		return costo==null?Env.ZERO:costo;
	}
	
	/** Obtener las lineas que no estan siendo utilizadas por una factura */
	public static List<MInOutLine> getLinesNotIninvoice(int mInOut){
		final String where = " isBeingUsedInvoice = ? and m_inout_id = ? ";
		return new Query(Env.getCtx(), Table_Name, where, null)
				.setOnlyActiveRecords(true)
				.setParameters("N", mInOut)
				.addAccessLevelSQL(true)
				.list();
	}
	

	/** Se requiere ya que pierde del contexto la organización en ciertas ocasiones
	 * por lo que se vuelve a buscar el objeto para que este tenga los datos correctos
	 * @param mInOutLine
	 * @param trxName
	 * @return
	 */
	public static MInOutLine getLineID(final Properties ctx, final int mInOutLine, final String trxName) {

		MInOutLine inOutLine = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append(" SELECT *  ")
			.append(" FROM M_InOutLine ")
			.append(" WHERE M_InOutLine.IsActive = 'Y' ")
			.append(" AND M_InOutLine.M_InOutLine_ID = ? ");
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, mInOutLine);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				inOutLine = new MInOutLine(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return inOutLine;
	}
}	//	MInOutLine