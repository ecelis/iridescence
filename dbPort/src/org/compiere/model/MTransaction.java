package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.db.DB_PostgreSQL;
import org.compiere.model.bean.TransactionLine;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.vo.Transaction;


/**
 * 	Material Transaction Model
 *
 *	@author Jorg Janke
 *	@version $Id: MTransaction.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MTransaction extends X_M_Transaction
{
private static final long serialVersionUID = -287678038008152158L;
	private static CLogger logger = CLogger.getCLogger(MTransaction.class);
	public static final int AD_REFERENCE_ID = 298;
	private transient MAcctSchema mAcctSchema = null;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_Transaction_ID id
	 *	@param trxName transaction
	 */
	public MTransaction (Properties ctx, int M_Transaction_ID, String trxName)
	{
		super (ctx, M_Transaction_ID, trxName);
		if (M_Transaction_ID == 0)
		{
		//	setM_Transaction_ID (0);		//	PK
		//	setM_Locator_ID (0);
		//	setM_Product_ID (0);
			setMovementDate (DB.getTimestampForOrg(ctx));
			setMovementQty (Env.ZERO);
		//	setMovementType (MOVEMENTTYPE_CustomerShipment);
		}
	}	//	MTransaction

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MTransaction (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MTransaction

	/**
	 * 	Detail Constructor
	 *	@param ctx context
	 *	@param AD_Org_ID org
	 * 	@param MovementType movement type
	 * 	@param M_Locator_ID locator
	 * 	@param M_Product_ID product
	 * 	@param M_AttributeSetInstance_ID attribute
	 * 	@param MovementQty qty
	 * 	@param MovementDate optional date
	 *	@param trxName transaction
	 */
	public MTransaction (final Properties ctx, final int AD_Org_ID,
			final String MovementType,
			final int M_Locator_ID, final int M_Product_ID, final int M_AttributeSetInstance_ID,
			final BigDecimal MovementQty, final Timestamp MovementDate, final String trxName)
	{
		super(ctx, 0, trxName);
		setAD_Org_ID(AD_Org_ID);
		setMovementType (MovementType);

		if (M_Locator_ID == 0) {
			throw new IllegalArgumentException("No Locator");
		}

		setM_Locator_ID (M_Locator_ID);

		if (M_Product_ID == 0) {
			throw new IllegalArgumentException("No Product");
		}
		setM_Product_ID (M_Product_ID);
		setM_AttributeSetInstance_ID (M_AttributeSetInstance_ID);
		//
		if (MovementQty != null) {		//	Can be 0
			setMovementQty (MovementQty);
		}

		if (MovementDate == null) {
			setMovementDate (DB.getTimestampForOrg(ctx));
		} else {
			setMovementDate(MovementDate);
		}
	}	//	MTransaction


	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sb.append("MTransaction[")
			.append(get_ID()).append(',').append(getMovementType())
			.append(",Qty=").append(getMovementQty())
			.append(",M_Product_ID=").append(getM_Product_ID())
			.append(",ASI=").append(getM_AttributeSetInstance_ID())
			.append (']');
		return sb.toString ();
	}	//	toString

	/**
	 * Obtener transacciones por rango de fecha
	 *
	 * @param ctx
	 *            Contexto
	 * @param warehouseId
	 *            Almacen o -1
	 * @param ini
	 *            Fecha Inicial
	 * @param end
	 *            Fecha Final
	 * @param trxName
	 *            Nombre de la trx
	 * @return Listado de transacciones registradas durante el periodo de tiempo
	 *         dado
	 */
	public static List<Transaction> getTransactions(Properties ctx, int warehouseId, Date ini, Date end, String trxName) {
		List<Transaction> lst = new ArrayList<Transaction>();

		boolean sqlChanged = false;

		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(
				"SELECT "
				+ "  T.movementdate, \n"
				+ "  T.MOVEMENTTYPE, \n"
				+ "  W.NAME \n"
				+ "  AS WAREHOUSE, \n"
				+ "  P.VALUE, \n"
				+ "  p.description, \n"
				+ "  t.MOVEMENTQTY, \n"
				+ "  col.linenetamt \n"
				+ "  AS ioamt, \n"
				+ "  cpd.linenetamt \n"
				+ "  AS cpdamt, \n"
				+ "  iol.m_inoutline_id \n"
				+ "FROM M_TRANSACTION T \n"
				+ "  INNER JOIN M_PRODUCT P ON t.m_product_id = p.m_product_id \n"
				+ "  INNER JOIN m_locator L ON t.m_locator_id = l.m_locator_id \n"
				+ "  INNER JOIN m_warehouse W ON l.m_warehouse_id = w.m_warehouse_id  \n"
				+ "  LEFT JOIN m_inoutline iol ON t.m_inoutline_id = iol.m_inoutline_id \n"
				+ "  LEFT JOIN c_orderline col ON iol.c_orderline_id = col.c_orderline_id \n"
				+ "  LEFT JOIN exme_ctapacdet cpd ON iol.exme_ctapacdet_id = cpd.exme_ctapacdet_id \n"
				+ "WHERE "
				+ "  t.movementdate BETWEEN  ? AND ? \n"
		);

		if(warehouseId > 0) {
			sql.append("  AND w.m_warehouse_id = ? \n");
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "t"))
		.append("\n ORDER BY t.movementdate");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setTimestamp(1, TimeUtil.getInitialRangeT(ctx, ini));
			pstmt.setTimestamp(2, TimeUtil.getFinalRangeT(ctx, end));
			pstmt.setInt(3, warehouseId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setMovementDate(rs.getTimestamp("movementdate"));
				transaction.setMovementQty(rs.getBigDecimal("MOVEMENTQTY"));
				transaction.setMovementType(rs.getString("MOVEMENTTYPE"));
				transaction.setProductDescription(rs.getString("description"));
				transaction.setProductValue(rs.getString("VALUE"));
				transaction.setWarehouseName(rs.getString("WAREHOUSE"));
				transaction.setStrMovementType(MRefList.getListasTraducidasValue(ctx, MOVEMENTTYPE_AD_Reference_ID, false, transaction.getMovementType()));

				if (rs.getInt("m_inoutline_id") > 0) {
					if (MTransaction.MOVEMENTTYPE_VendorReceipts.equals(transaction.getMovementType())
							|| MTransaction.MOVEMENTTYPE_VendorReturns.equals(transaction.getMovementType())) {
						transaction.setAmount(rs.getBigDecimal("ioamt"));
					} else if (MTransaction.MOVEMENTTYPE_CustomerReturns.equals(transaction.getMovementType())
							|| MTransaction.MOVEMENTTYPE_CustomerShipment.equals(transaction.getMovementType())) {
						MInOutLine line = new MInOutLine(ctx, rs.getInt("m_inoutline_id"), null);
						if (line.getC_OrderLine_ID() > 0) {
							transaction.setAmount(rs.getBigDecimal("ioamt"));
						} else if (line.getEXME_CtaPacDet_ID() > 0) {
							transaction.setAmount(rs.getBigDecimal("cpdamt"));
						} else {

							if(!sqlChanged) {
								sql = new StringBuffer("SELECT PriceActual FROM C_InvoiceLine WHERE AD_Client_ID = ? AND M_InoutLine_ID = ? ");

								if(DB.isPostgreSQL()) {
									sql.append(DB_PostgreSQL.NATIVE_MARKER).append("LIMIT")
									.append(DB_PostgreSQL.NATIVE_MARKER).append(" 1");
								} else {
									sql.append(" AND ROWNUM = 1");
								}

								sqlChanged = true;
							}

							BigDecimal priceActual =
									DB.getSQLValueBD(
											null,
											sql.toString(),
											new Object[]{
												line.getAD_Client_ID(),
												line.getM_InOutLine_ID()
											}
									);

							if(priceActual != null) {
								transaction.setAmount(priceActual);
							}
						}

					}
				}

				lst.add(transaction);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "SQL : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}

	/**
	 * Transacciones del producto
	 * @param ctx
	 * @param productID
	 * @param trxName
	 * @return lista de productos
	 **/
	public static List<MTransaction> getTransactions(Properties ctx, int productID, String trxName) {

		List<MTransaction> retValue = new ArrayList<MTransaction>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT  * FROM M_transaction ");
		sql.append(" WHERE M_transaction.M_Product_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY MovementDate desc");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MTransaction trn = new MTransaction(ctx, rs, trxName);
				retValue.add(trn);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * Transacciones del producto por nivel de acceso
	 *
	 * @param ctx
	 *            Contexto
	 * @param productID
	 *            Producto
	 * @param trxName
	 *            Trx
	 * @return
	 */
	public static int getTransactionsNo(Properties ctx, int productID, String trxName) {
		int no = 0;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT count(m_transaction_id) FROM M_transaction ");
		sql.append(" WHERE M_transaction.M_Product_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productID);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				no = rs.getInt(1);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return no;
	}

	/***************************************************************/

	private transient Properties mCtx = null;
    private transient MProduct mProduct = null;
//    private transient MAcctSchema accountingSchema = null;

	/**
	 * 	Detail Constructor
	 *	@param ctx context
	 * 	@param MovementType movement type
	 * 	@param M_Locator_ID locator
	 * 	@param M_Product_ID product
	 * 	@param M_AttributeSetInstance_ID attribute
	 * 	@param MovementQty qty
	 * 	@param MovementDate optional date
	 */
    public MTransaction (final Properties ctx, final MProduct product, final int M_AttributeSetInstance_ID,
            final MAcctSchema as, final int AD_Org_ID, final String costingMethod, final BigDecimal qty,
            final int C_OrderLine_ID, final boolean zeroCostsOK, final String MovementType, final int M_Locator_ID,
            final Timestamp MovementDate, final String trxName) {

		this (ctx, AD_Org_ID, MovementType, M_Locator_ID, product.getM_Product_ID(), M_AttributeSetInstance_ID, qty, MovementDate, trxName);
        mCtx = ctx;
        mProduct = product;
        mAcctSchema = as;
		if(as.getC_AcctSchema_ID() <= 0){
			throw new IllegalArgumentException("MTransaction - No C_AcctSchema_ID");
		}
		setC_AcctSchema_ID(as.getC_AcctSchema_ID());
		setCosts();
		setQtyOnHand(MStorage.getQtyOnHand(M_Locator_ID, product.getM_Product_ID(), 0, trxName));
	}	//	EXME_MTransaction

	/**
	 * 	Busca y establece al modelo los valores
	 *	para los costos del producto y esquema contable
	 *	@param ctx context
	 * 	@param M_Product_ID product
	 */
	private void setCosts()
	{
		if(mAcctSchema == null || mAcctSchema.getC_AcctSchema_ID()<=0){
			throw new IllegalArgumentException("MTransaction - No MAcctSchema");
		}
//		// Costo ultimo precio de compra
//		final MCost mCostLPO = MEXMECost.get(getCtx(), m_product,  X_M_CostElement.COSTINGMETHOD_LastPOPrice, m_as, get_TrxName());
//		setPriceLastPO(mCostLPO==null || mCostLPO.getM_Product_ID()<=0?Env.ZERO:mCostLPO.getCurrentCostPrice());
//
//		// Costo estandar
//		final MCost mCostStd = MEXMECost.get(getCtx(), m_product,  X_M_CostElement.COSTINGMETHOD_StandardCosting, m_as, get_TrxName());
//		setCostStandard(mCostStd==null || mCostStd.getM_Product_ID()<=0?Env.ZERO:mCostStd.getCurrentCostPrice());
//
//		// Costo promedio orden de compra
//		final MCost mCostAPO = MEXMECost.get(getCtx(), m_product,  X_M_CostElement.COSTINGMETHOD_AveragePO, m_as, get_TrxName());
//		setCostAverage(mCostAPO==null || mCostAPO.getM_Product_ID()<=0?Env.ZERO:mCostAPO.getCurrentCostPrice());
//
//		// Costo promedio factura
//		final MCost mCostAIn = MEXMECost.get(getCtx(), m_product,  X_M_CostElement.COSTINGMETHOD_AverageInvoice, m_as, get_TrxName());
//        setCostAverageI(mCostAIn==null || mCostAIn.getM_Product_ID()<=0?Env.ZERO:mCostAIn.getCurrentCostPrice());
		updateCost(mProduct,mAcctSchema);
	}	//	setCosts

	public void updateCost(final MProduct product, final MAcctSchema acctSchema){
		// Costo ultimo precio de compra
		final MCost mCostLPO = MEXMECost.get(getCtx(), product,  X_M_CostElement.COSTINGMETHOD_LastPOPrice, acctSchema, get_TrxName());
		setPriceLastPO(mCostLPO==null || mCostLPO.getM_Product_ID()<=0?Env.ZERO:mCostLPO.getCurrentCostPrice());

		// Costo estandar
		final MCost mCostStd = MEXMECost.get(getCtx(), product,  X_M_CostElement.COSTINGMETHOD_StandardCosting, acctSchema, get_TrxName());
		setCostStandard(mCostStd==null || mCostStd.getM_Product_ID()<=0?Env.ZERO:mCostStd.getCurrentCostPrice());

		// Costo promedio orden de compra
		final MCost mCostAPO = MEXMECost.get(getCtx(), product,  X_M_CostElement.COSTINGMETHOD_AveragePO, acctSchema, get_TrxName());
		setCostAverage(mCostAPO==null || mCostAPO.getM_Product_ID()<=0?Env.ZERO:mCostAPO.getCurrentCostPrice());

		// Costo promedio factura
		final MCost mCostAIn = MEXMECost.get(getCtx(), product,  X_M_CostElement.COSTINGMETHOD_AverageInvoice, acctSchema, get_TrxName());
		setCostAverageI(mCostAIn==null || mCostAIn.getM_Product_ID()<=0?Env.ZERO:mCostAIn.getCurrentCostPrice());
	}	//	setCosts

	/**
	 * Obtiene el nombre del tipo de movimiento
	 *
	 * @return Nombre del tipo de movimiento
	 */
	public String getNameMovementType() {
		String docBaseType = null;
		String sql = null;
		String devol = "N";


		//El tipo de movimiento de devolución a proveedor debe considerar lo siguiente:
		// Devolución será cuando :
		//   * DocBaseType.MaterialReceipt y MOVEMENTTYPE_VendorReturns
		//   * DocBaseType.MaterialReceipt y MOVEMENTTYPE_CustomerShipment
		// Recepción será cuando :
		//   * DocBaseType.MaterialReceipt y MOVEMENTTYPE_VendorReceipts

		// If it is a M_InOut, get the Doc Base Type
		if(getM_InOutLine_ID() > 0) {
			sql =
					"SELECT DocBaseType "
					+"FROM C_DocType d "
					+"	inner join M_InOut io on (io.C_DocType_ID = d.C_DocType_ID) "
					+"	inner JOIN M_InOutLine iol ON (iol.M_InOut_ID = io.M_InOut_ID) "
					+"WHERE iol.M_InOutLine_ID = ?";

			docBaseType =
					DB.getSQLValueString(null, sql, getM_InOutLine_ID());
		}

//		if(X_C_DocType.DOCBASETYPE_MaterialReceipt.equals(docBaseType) &&
//				(X_M_Transaction.MOVEMENTTYPE_VendorReturns.equals(getMovementType()) ||
//				X_M_Transaction.MOVEMENTTYPE_CustomerShipment.equals(getMovementType()))) {
//			retVal =
//					MRefList.getListName(
//							getCtx(),
//							X_M_Transaction.MOVEMENTTYPE_AD_Reference_ID,
//							X_M_Transaction.MOVEMENTTYPE_VendorReturns
//					);
//		} else {
//			retVal =
//					MRefList.getListName(
//							getCtx(),
//							X_M_Transaction.MOVEMENTTYPE_AD_Reference_ID,
//							getMovementType()
//					);
//		}

		if (getM_MovementLine_ID() > 0) {
			sql =
					"SELECT m.isdevol "
							+ "FROM M_Movement m "
							+ "	INNER JOIN M_MovementLine ml ON (ml.M_Movement_ID = m.M_Movement_ID) "
							+ "WHERE ml.M_MovementLine_ID = ?";

			devol = DB.getSQLValueString(null, sql, getM_MovementLine_ID());
		}

		return MTransaction.getMovementType(
				getMovementType(),
				docBaseType,
				devol,
				getMovementQty().intValue()
		);

//		return retVal;
	}

	/**
	 * Obtiene el nombre del localizador
	 *
	 * @return Nombre del localizador
	 */
	public String getLocationValue() {
		final MLocator loc = new MLocator(getCtx(), getM_Locator_ID(), null);
		return loc.getValue();
	}

	/**
	 * Metodo para obtener los movimientos de kardex
	 *
	 * @param ctx
	 * @param params
	 *            listado de paramtros en el orden: Client_ID, Org_ID, fechaIni,
	 *            fechaFin, Product_ID, Warehouse_ID, Product_Category_ID
	 * @param trxName
	 * @return
	 */
	public static List<ModelKardex> getKardexInfo(Properties ctx, List<Object> params, String trxName){

		List <ModelKardex> lista = new ArrayList<ModelKardex>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT * FROM fnc_getkardex(?,?, ")//cliente, organización
		.append("to_char(to_date(?,'YYYY-MM-DD hh24:mi:ss'), 'DD/MM/YYYY'), ") //fechaIni
		.append("to_char(to_date(?,'YYYY-MM-DD hh24:mi:ss'), 'DD/MM/YYYY'), ")//fechaFin
		.append("cast(? as numeric), ")//Product_ID
		.append("cast(? as numeric), ")//Warehouse_ID
		.append("?) ");//Product_Category_ID

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while(rs.next()){
				int movQty = rs.getInt("movementqty");
				int existence = rs.getInt("qtyonhand");
				
				BigDecimal qtybook = rs.getBigDecimal("qtybook")==null?Env.ZERO:rs.getBigDecimal("qtybook");
				BigDecimal cost    = rs.getBigDecimal("costo");
				BigDecimal priceTrx = null;
				BigDecimal purchasePrice =
						rs.getBigDecimal("PrecioCompra") == null ?
								BigDecimal.ZERO :
									rs.getBigDecimal("PrecioCompra");

				String movType =
						rs.getString("movementtype") == null ?
								StringUtils.EMPTY :
									rs.getString("movementtype");

				BigDecimal priceExistence =
						cost == null ?
								BigDecimal.ZERO :
									cost.multiply(new BigDecimal(existence));

				String devol =
						rs.getString("devolucion") == null ?
								StringUtils.EMPTY :
									rs.getString("devolucion");

				String documentno =
						rs.getString("oi_doc") == null ?
								rs.getString("i_doc") == null ?
										rs.getString("m_doc"):
											rs.getString("i_doc")
												: rs.getString("oi_doc");

				String docBaseType = rs.getString("docbasetype");

				String movement = getMovementType(movType, docBaseType, devol, movQty);

				// cuando es recepción de material o devolución al proveedor
				if(MInOut.isVendorReturn(docBaseType, movType)
						|| X_M_Transaction.MOVEMENTTYPE_VendorReceipts.equals(movType)) {

					priceTrx =
							rs.getBigDecimal("movementqty") == null ?
									BigDecimal.ZERO :
										rs.getBigDecimal("movementqty").multiply(purchasePrice);

				} else {
					priceTrx =
							rs.getBigDecimal("movementqty") == null ?
									BigDecimal.ZERO :
										rs.getBigDecimal("movementqty").multiply(cost);
				}


//						(typeMov.equalsIgnoreCase("I+") && movQty == 0) ? new String("Ajuste por Inventario Físico") :
//							(typeMov.equalsIgnoreCase("I-") && movQty == 0) ? new String("Ajuste por Inventario Físico") :
//										(typeMov.equalsIgnoreCase("I+") && movQty > 0) ? new String("Ajuste por Inventario Físico (Positivo)") :
//											(typeMov.equalsIgnoreCase("I+") && movQty < 0) ? new String("Ajuste por Inventario Físico (Negativo)") :
//												(typeMov.equalsIgnoreCase("I-") && movQty > 0) ? new String("Ajuste por Inventario Físico (Positivo)") :
//													(typeMov.equalsIgnoreCase("I-") && movQty < 0) ? new String("Ajuste por Inventario Físico (Negativo)") :
//														(typeMov.equalsIgnoreCase("M+") && devol.equalsIgnoreCase("N")) ? new String("Entrada por Traslado de Productos") :
//															(typeMov.equalsIgnoreCase("M-") && devol.equalsIgnoreCase("N")) ? new String("Salida por Traslado de Productos") :
//																(typeMov.equalsIgnoreCase("M+") && devol.equalsIgnoreCase("Y")) ? new String("Entrada por Traslado por Dev. de Productos") :
//																	(typeMov.equalsIgnoreCase("M-") && devol.equalsIgnoreCase("Y")) ? new String("Salida por Traslado por Dev. de Productos") :
//																		(typeMov.equalsIgnoreCase("C+")) ? new String("Devolucion de Venta") : (typeMov.equalsIgnoreCase("C-")) ? new String("Ventas") :
//																			(typeMov.equalsIgnoreCase("V+")) ? new String("Compras") : (typeMov.equalsIgnoreCase("V-")) ? new String("Devolucion de Compra") :
//																				(typeMov.equalsIgnoreCase("SG")) ? new String("Salida al Gasto") : new String("");


				ModelKardex kardex = new ModelKardex();

				kardex.setProdValue(rs.getString("p_value"));
				kardex.setProduct(rs.getString("p_name"));
				kardex.setUomName(rs.getString("uom_name"));
				kardex.setDate(rs.getString("confirmdate"));
				kardex.setTypeMov(movement);
				kardex.setDocumentNo(documentno);
				kardex.setWarehouse(rs.getString("w_name"));
				kardex.setLocator(rs.getString("estserv"));
				kardex.setCantIni(rs.getString("tot"));
				kardex.setCantTrx(String.valueOf(movQty));
				kardex.setExistence(String.valueOf(existence));
				kardex.setPriceUnit(cost.setScale(4,BigDecimal.ROUND_HALF_UP).toString());
				kardex.setPriceTrx(priceTrx.setScale(4,BigDecimal.ROUND_HALF_UP).toString());
				kardex.setPriceExistence(priceExistence.setScale(4,BigDecimal.ROUND_HALF_UP).toString());
				kardex.setUser(rs.getString(19));
				kardex.setPurchasePrice(purchasePrice.setScale(4,BigDecimal.ROUND_HALF_UP).toString());

				kardex.setMovDate(rs.getString("movDate"));
				kardex.setQtyBook(qtybook.setScale(4,BigDecimal.ROUND_HALF_UP).toString());
				
				lista.add(kardex);
			}



		} catch (SQLException e) {
			logger.log(Level.SEVERE, sql.toString(), e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			lista.clear();
		} finally {
			DB.close(rs,pstmt);
		}

		return lista;
	}

	/**
	 * Returns the movment type based on nature, if it's a returning and the
	 * movement quantity.
	 * @param movType Movement type from AD_Reference_ID=189.
	 * @param docBaseType The document base type to validate if it is a material
	 * receipt movement
	 * @param devol If it is a returning.
	 * @param movQty The movement quantity.
	 */
	public static String getMovementType(String movType, String docBaseType, String devol, int movQty) {
		String retVal = StringUtils.EMPTY;


		if(MInOut.isVendorReturn(docBaseType, movType)) {
			retVal = "Devolucion de Compra";
		} else {
			switch (movType) {
			case MOVEMENTTYPE_InventoryIn:			// I+
			case MOVEMENTTYPE_InventoryOut:			//I-
				if(movQty == 0) {
					retVal = "Ajuste por Inventario Físico";
				} else if (movQty < 0) {
					retVal = "Ajuste por Inventario Físico (Negativo)";
				} else {
					retVal = "Ajuste por Inventario Físico (Positivo)";
				}
				break;

			case MOVEMENTTYPE_MovementTo:			//M+
				if("N".equals(devol)) {
					retVal = "Entrada por Traslado de Productos";
				} else if("Y".equals(devol)) {
					retVal = "Entrada por Traslado por Dev. de Productos";
				}
				break;

			case MOVEMENTTYPE_MovementFrom:			//M-
				if("N".equals(devol)) {
					retVal = "Salida por Traslado de Productos";
				} else if("Y".equals(devol)) {
					retVal = "Salida por Traslado por Dev. de Productos";
				}
				break;

			case MOVEMENTTYPE_CustomerReturns:		//C+
				retVal = "Devolucion de Venta";
				break;

			case MOVEMENTTYPE_CustomerShipment:		//C-
				retVal = "Ventas";
				break;

			case MOVEMENTTYPE_VendorReceipts:		//V+
				retVal = "Compras";
				break;

			case MOVEMENTTYPE_VendorReturns:		//V-
				retVal = "Devolucion de Compra";
				break;

			case "SG":
				retVal = "Salida al Gasto";
				break;

			default:
				retVal = StringUtils.EMPTY;
				break;
			}
		}

		return retVal;
	}



	/** Instanciar el Esquema contable */
	public MAcctSchema getAcctSchema(){
		if(mAcctSchema==null){
			mAcctSchema = new MAcctSchema(getCtx(), getC_AcctSchema_ID(), get_TrxName());
		}
		return mAcctSchema;
	}

	/**
	 * Este metodo sirve para sacar el reporte de existencias de productos
	 * card #1407
	 *
	 * @param ctx
	 * @param product
	 *            id de producto
	 * @param almacenID
	 *            id del almacen
	 * @param filtro
	 *            filtro que indica si las existencias son mayores o iguales a 0
	 * @param date
	 *            fecha del movimiento
	 * @param client
	 *            id del cliente
	 * @param trxName
	 * @return
	 */

	public static List<TransactionLine> getStockReport(Properties ctx, int product,
			int almacenID, int filtro, Timestamp date, int client, String trxName) {

		List<TransactionLine> lst = new ArrayList<TransactionLine>();
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		params.add(client);
		params.add(client);
		params.add(client);
		params.add(almacenID);
		params.add(date);
		params.add(client);
		params.add(client);



		sql.append("with Mtransaction as ( ");
		sql.append("	select max(m_transaction_id) as idtrx, t.m_product_id, p.value, p.name ");
		sql.append("	from m_transaction t ");
		sql.append("		INNER JOIN EXME_ProductoOrg por ON por.M_Product_ID = t.M_Product_ID AND por.AD_Client_ID = ? ");
		sql.append("		INNER JOIN M_Product p ON (por.M_Product_ID = p.M_Product_ID) ");
		sql.append("		INNER JOIN M_Locator loc ON loc.M_Locator_ID = t.M_Locator_ID AND loc.AD_Client_ID = ? ");
		sql.append("		INNER JOIN M_Warehouse war ON (loc.M_Warehouse_ID = war.M_Warehouse_ID) ");
		sql.append("	WHERE t.IsActive = 'Y' ");
		sql.append("	AND por.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "t")).append('\n');
		sql.append(" AND p.AD_Client_ID IN (0,?) ");
		sql.append("	AND loc.M_Warehouse_ID IN (?) ");
		sql.append("	AND t.movementdate <= ? ");
		sql.append("	AND loc.IsActive = 'Y' ");
		sql.append("	group by t.m_product_id, p.value, p.name, loc.m_warehouse_id ");
		sql.append("	 ");
		sql.append(")select * from( ");
		sql.append("select trx.m_product_id, prod.value , prod.name, war.name as warehouse, loc.value as locator, cuom.name as uomu, cuomp.name as uomv, atri.lot as lote, atri.guaranteedate as guaranteeDate,  ");
		sql.append("( ");
		sql.append("  CASE ");
		sql.append("    WHEN (prod.c_uom_id = prod.c_uomvolume_id ) ");
		sql.append("    THEN 0 ");
		sql.append("    WHEN((trx.QtyOnHand))/COALESCE(con.MultiplyRate,con2.MultiplyRate) IS NULL ");
		sql.append("    THEN NULL ");
		sql.append("    WHEN (COALESCE(con.c_uom_to_id,con2.c_uom_to_id) = prod.c_uomvolume_id ) ");
		sql.append("    THEN (COALESCE(floor((trx.QtyOnHand)/COALESCE(con.dividerate,con2.dividerate)),((trx.QtyOnHand)))) ");
		sql.append("    ELSE (COALESCE(floor((trx.QtyOnHand)/COALESCE(con.MultiplyRate,con2.MultiplyRate)),((trx.QtyOnHand)))) ");
		sql.append("  END ) AS VolumeH, ");
		sql.append("  ( ");
		sql.append("  CASE ");
		sql.append("    WHEN (prod.c_uom_id = prod.c_uomvolume_id ) ");
		sql.append("    THEN ((trx.QtyOnHand)) ");
		sql.append("    WHEN mod(((trx.QtyOnHand)),COALESCE(con.MultiplyRate,con2.MultiplyRate)) IS NULL ");
		sql.append("    THEN NULL ");
		sql.append("    WHEN (COALESCE(con.c_uom_to_id,con2.c_uom_to_id) = prod.c_uomvolume_id ) ");
		sql.append("    THEN (floor(mod((trx.QtyOnHand),COALESCE(con.dividerate,con2.dividerate)))) ");
		sql.append("    ELSE (floor(mod((trx.QtyOnHand),COALESCE(con.MultiplyRate,con2.MultiplyRate)))) ");
		sql.append("  END ) AS UnitH ");
		sql.append("  from m_transaction trx ");
		sql.append("		INNER JOIN Mtransaction on trx.m_transaction_id = Mtransaction.idtrx ");
		sql.append("		INNER JOIN m_attributesetinstance atri on atri.m_attributesetinstance_id = trx.m_attributesetinstance_id");
		sql.append("		INNER JOIN EXME_ProductoOrg por ON por.M_Product_ID = trx.M_Product_ID AND por.AD_Client_ID = ? ");
		sql.append("		INNER JOIN M_Product prod ON (por.M_Product_ID = prod.M_Product_ID) ");
		sql.append("		INNER JOIN M_Locator loc ON loc.M_Locator_ID = trx.M_Locator_ID AND loc.AD_Client_ID = ? ");
		sql.append("		INNER JOIN M_Warehouse war ON (loc.M_Warehouse_ID = war.M_Warehouse_ID) ");
		sql.append("		LEFT JOIN C_UOM cuom ON (cuom.C_Uom_ID=prod.C_Uom_ID) ");
		sql.append("		LEFT JOIN C_UOM cuomp ON (cuomp.C_Uom_ID=prod.C_Uomvolume_ID) ");
		sql.append("		LEFT JOIN C_UOM_CONVERSION con ON (con.C_Uom_ID    =prod.C_Uom_ID AND con.C_Uom_To_ID =prod.C_UomVolume_ID) ");
		sql.append("		LEFT JOIN C_UOM_CONVERSION con2 ON (con2.C_Uom_ID    =prod.C_UomVolume_ID AND con2.C_Uom_To_ID =prod.C_Uom_ID) ");
		sql.append("		WHERE trx.IsActive = 'Y' ");
		if (product > 0) {
			params.add(product);
			sql.append(" AND prod.M_Product_ID = ? ");

		}
		params.add(almacenID);
		params.add(date);
		sql.append("		AND loc.M_Warehouse_ID IN (?) ");
		sql.append("		AND trx.movementdate <= ?  ");
		sql.append("		AND loc.IsActive = 'Y' ");
		sql.append("GROUP BY trx.m_product_id, prod.value, prod.name, war.name, loc.value, cuom.name, cuomp.name, prod.c_uom_id, prod.c_uomvolume_id, trx.qtyonhand, atri.lot, atri.guaranteedate, ");
		sql.append("con.MultiplyRate, con2.MultiplyRate, con.c_uom_to_id,con2.c_uom_to_id, con.dividerate, con2.dividerate, trunc (trx.movementdate, 'day') ");
		sql.append("order by trunc(trx.movementdate, 'day') desc) as exist ");
		if (filtro == 1) {
			sql.append("where unith + volumeh > 0 ");
		} else if (filtro == 2) {
			sql.append("where unith + volumeh = 0 ");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TransactionLine trans = new TransactionLine();

				trans.setProdValue(rs.getString("value"));
				trans.setProdName(rs.getString("name"));
				trans.setWareHouse(rs.getString("warehouse"));
				trans.setLocatorName(rs.getString("locator"));
				trans.setLotName(rs.getString("lote"));
				trans.setQty(rs.getBigDecimal("UnitH"));
				trans.setUdm(rs.getString("uomu"));
				trans.setQtyVol(rs.getBigDecimal("VolumeH"));
				trans.setUdmVol(rs.getString("uomv"));
				trans.setGuaranteeDate(rs.getTimestamp("guaranteeDate"));

				lst.add(trans);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;

	}

	/** Obtener la transaccion a partir de un la línea de embarque o recepción,
	 * no utilizar addAccessLevelSQL ya que la organización no viene en el contexto */
	public static MTransaction getTransactionInOutLine(final MInOutLine mInOutLine) {
//		logger.log(Level.INFO, "Linea buscar : "+mInOutLine.getM_InOutLine_ID());
//		final StringBuilder where = new StringBuilder();
//		where.append(" M_InoutLine_ID = ? ");
//
//		return new Query(mInOutLine.getCtx(),
//		X_M_Transaction.Table_Name, where.toString(), mInOutLine.get_TrxName())//
//		.setOnlyActiveRecords(true)//
//		.addAccessLevelSQL(true)//
//		.setParameters(mInOutLine.getM_InOutLine_ID())
//		.first();

		MTransaction inOutLine = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append(" SELECT *  ")
			.append(" FROM  M_Transaction ")
			.append(" WHERE M_Transaction.IsActive = 'Y' ")
			.append(" AND   M_Transaction.M_InOutLine_ID = ? ");
			pstmt = DB.prepareStatement(sql.toString(), mInOutLine.get_TrxName());

			pstmt.setInt(1, mInOutLine.getM_InOutLine_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				inOutLine = new MTransaction(mInOutLine.getCtx(), rs, mInOutLine.get_TrxName());
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return inOutLine;
	}
	
	@Override
	protected boolean afterSave(final boolean newRecord, boolean success) {
		if (!success){
			return success;
		}
		
		if (newRecord) {
			// 
			final StringBuilder sql = new StringBuilder() 
			.append(" UPDATE M_Transaction ")
			.append(" SET QtyBook = ( ")
			.append(" 		SELECT COALESCE(SUM(QtyOnHand),0) ")
			.append(" 		FROM M_Storage       ")
			.append(" 		WHERE IsActive = 'Y' ")
			.append(" 		AND AD_Client_ID = ? ")
			.append(" 		AND M_Product_ID = ? ")
			.append(" 		)                    ")
			.append(" WHERE M_Transaction_ID = ? ");
			
			final int num = DB.executeUpdate(sql.toString(), new Object[]{getAD_Client_ID(), getM_Product_ID(), getM_Transaction_ID()}, get_TrxName());

			if (num != 1){
				log.warning("1 #" + num);
			}
		}
		return true;
	} // beforeSave
	
	
	/** Obtener la transaccion a partir de un la línea del movimiento (inventario, translado, embarque, etc.)
	 *  no utilizar addAccessLevelSQL ya que la organización no viene en el contexto */
	public static MTransaction getTransactionByLine(final Properties ctx, final int idx, final String tabla, final String trxName) {
		MTransaction inOutLine = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append(" SELECT *  ")
			.append(" FROM  M_Transaction ")
			.append(" WHERE M_Transaction.IsActive = 'Y' ")
			.append(" AND   M_Transaction.").append(tabla).append("_ID = ? ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				inOutLine = new MTransaction(ctx, rs, trxName);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return inOutLine;
	}
	
	/**
	 * Obtener el costo a partir de la transacción que se se genero 
	 * para no recalcular costos
	 * @param as
	 * @param qty
	 * @param costs
	 * @param idx
	 * @param tabla
	 * @param mProduct
	 * @param trxName
	 * @return
	 */
	public static BigDecimal getCostTransaction(final MAcctSchema as, final BigDecimal qty, final BigDecimal costs 
			, final int idx , final String tabla, final MProduct mProduct, final String trxName) {
		BigDecimal costo = Env.ZERO;
		boolean isNeg = costs.signum()<0;
		if(mProduct.isItem()){

			final MTransaction mTransaction = MTransaction.getTransactionByLine(as.getCtx()
					, idx
					, tabla
					, trxName);

			if (mTransaction != null && mTransaction.getCostAverage()!=null){
				final String costingMethod = mProduct.getCostingMethod(as);
				if(X_C_AcctSchema.COSTINGMETHOD_AveragePO.equals(costingMethod)){
					costo = mTransaction.getCostAverage();
				} else if(X_C_AcctSchema.COSTINGMETHOD_AverageInvoice.equals(costingMethod)){
					costo = mTransaction.getCostAverageI();
				} else if(X_C_AcctSchema.COSTINGMETHOD_StandardCosting.equals(costingMethod)){
					costo = mTransaction.getCostStandard();
				} else if(X_C_AcctSchema.COSTINGMETHOD_LastPOPrice.equals(costingMethod)){
					costo = mTransaction.getPriceLastPO();
				}
			}


			if(costo == null || costo.compareTo(Env.ZERO)==0){
				costo = costs;

			} else {
				
				// El costo por la cantidad
				costo = costo.multiply(qty);
				
				// Si el costo original es negativo y el costo calculado no es negativo, debemos cambiarlo a negativo
				// ó si el costo original es positivo y el costo calculado es negativo, debemos cambiarlo a psoitivo
				if( (isNeg && !(costo.signum()<0)) || (!isNeg && costo.signum()<0) ){
					costo = costo.negate();
				}
			}
		}
		return costo;
	}
}	//	MTransaction

