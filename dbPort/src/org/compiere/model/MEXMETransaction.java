package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Clase que extiende de MTransaction.<p>
 *
 * <b>Modificado: </b> $Author: mrojas $<p>
 * <b>En :</b> $Date: 2006/08/11 02:26:21 $<p>
 *
 * @author Hassan Reyes
 * @version $Revision: 1.10 $
 */
public class MEXMETransaction  
//extends MTransaction
{
//	/** serialVersionUID */
//	private static final long serialVersionUID = 1874575782434303631L;
//
//	/**
//	 * 	Load Constructor
//	 *	@param ctx context
//	 *	@param rs result set
//	 *	@param trxName transaction
//	 */
//	public MEXMETransaction (final Properties ctx, ResultSet rs, String trxName)
//	{
//		super(ctx, rs, trxName);
//	}	//	MTransaction

//	Properties m_ctx = null;
//    private MProduct m_product = null;
//    private MAcctSchema m_as = null;
//    private int m_M_AttributeSetInstance_ID = 0;
//    private int m_AD_Org_ID = 0;
////    private String m_CostingMethod = null;
////    private BigDecimal m_qty = null;
//    private int m_C_OrderLine_ID = 0;
//	private boolean m_zeroCostOK = false;
//	// private String trx_Name = null;
//    
//    private static CLogger		s_log = CLogger.getCLogger (MEXMETransaction.class);  
//	
//	/**
//	 * 	Detail Constructor
//	 *	@param ctx context
//	 * 	@param MovementType movement type
//	 * 	@param M_Locator_ID locator
//	 * 	@param M_Product_ID product
//	 * 	@param M_AttributeSetInstance_ID attribute
//	 * 	@param MovementQty qty
//	 * 	@param MovementDate optional date
//	 */
//    public MEXMETransaction (Properties ctx, MProduct product, int M_AttributeSetInstance_ID,
//            MAcctSchema as, int AD_Org_ID, String costingMethod, BigDecimal qty, 
//            int C_OrderLine_ID, boolean zeroCostsOK, String MovementType, int M_Locator_ID, 
//            Timestamp MovementDate, String trxName) 
//	{
//		super (ctx, AD_Org_ID, MovementType, M_Locator_ID, product.getM_Product_ID(), M_AttributeSetInstance_ID
//			, qty, MovementDate, trxName);
//
//        m_ctx = ctx;
//        m_product = product;
//        m_as = as;
//        m_M_AttributeSetInstance_ID = M_AttributeSetInstance_ID;
//        m_AD_Org_ID = AD_Org_ID;
////        m_CostingMethod = costingMethod;
////        m_qty = qty;
//        m_C_OrderLine_ID = C_OrderLine_ID;
//        m_zeroCostOK = zeroCostsOK;
////        trx_Name = trxName;
//        
//		//Expert:Hassan - Establecemos el Esquema Contable y Llamamos a setCosts() y setQtyOnHand()
//		if(as.getC_AcctSchema_ID() <= 0)
//			throw new IllegalArgumentException("MTransaction - No C_AcctSchema_ID");
//		System.out.println(">>>>>>>>>>>>> C_AcctSchema_ID - " + as.getC_AcctSchema_ID());
//		
//		setC_AcctSchema_ID(as.getC_AcctSchema_ID());
//		setCosts();
//		setQtyOnHand(MStorage.getQtyOnHand(M_Locator_ID, product.getM_Product_ID(), 0, trxName));
//		//Expert:Hassan - Fin del Bloque
//	}	//	EXME_MTransaction
//	
//	
//	/*Expert:Hassan -- Campos nuevos para guardar el historico de
//	costos, existencias y manejar las tranzacciones por esquema contable*/
//	/** Set Price Last PO.
//	Ultimo Costo del Producto */
//	public void setPriceLastPO (BigDecimal priceLastPO)
//	{
//		set_ValueNoCheck ("PriceLastPO", priceLastPO);
//	}
//	/** Get Price Last PO.
//	Ultimo Costo del Producto */
//	public BigDecimal getPriceLastPO()
//	{
//		BigDecimal bd = (BigDecimal)get_Value("PriceLastPO");
//		if (bd == null) return Env.ZERO;
//		return bd;
//	}
//	/** Set Cost Standar.
//	Costo Estandar del Producto */
//	public void setCostStandard (BigDecimal costStandard)
//	{
//		set_ValueNoCheck ("CostStandard", costStandard);
//	}
//	/** Get Cost Standar
//	Costo Estandar del Producto */
//	public BigDecimal getCostStandard()
//	{
//		BigDecimal bd = (BigDecimal)get_Value("CostStandard");
//		if (bd == null) return Env.ZERO;
//		return bd;
//	}
//	/** Set Cost Average.
//	Costo Promedio del Producto */
//	public void setCostAverage (BigDecimal costAverage)
//	{
//		set_ValueNoCheck ("CostAverage", costAverage);
//	}
//	/** Get Cost Average
//	Costo Promedio del Producto */
//	public BigDecimal getCostAverage()
//	{
//		BigDecimal bd = (BigDecimal)get_Value("CostAverage");
//		if (bd == null) return Env.ZERO;
//		return bd;
//	}
//	/** Set Qty On Hand.
//	Cantidad a la Mano (Existencias) del Producto */
//	public void setQtyOnHand (BigDecimal qtyOnHand)
//	{
//		set_ValueNoCheck ("QtyOnHand", qtyOnHand);
//	}
//	/** Get Qty On Hand.
//	Cantidad a la Mano (Existencias) del Producto */
//	public BigDecimal getQtyOnHand()
//	{
//		BigDecimal bd = (BigDecimal)get_Value("QtyOnHand");
//		if (bd == null) return Env.ZERO;
//		return bd;
//	}
//	/** Set Acct Schema ID.
//	Esquema Contable */
//	public void setC_AcctSchema_ID (int c_AcctSchema_ID)
//	{
//		if (c_AcctSchema_ID <= 0) throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory");
//		set_ValueNoCheck ("C_AcctSchema_ID", new Integer(c_AcctSchema_ID));
//	}
//	/** Get Acct Schema ID.
//	Esquema Contable */
//	public int getC_AcctSchema_ID()
//	{
//		Integer i = (Integer)get_Value("C_AcctSchema_ID");
//		if(i == null) return 0;
//		return i.intValue();
//	}
//	/*Expert:Hassasn - Fin del codigo */
//
//	/* Expert:Hassan - Metodo que busca y establece al modelo los valores
//	 para los costos del producto y esquema contable y busca las existencias actuales*/
//	/**
//	 * 	Busca y establece al modelo los valores 
//	 *	para los costos del producto y esquema contable
//	 *	@param ctx context
//	 * 	@param M_Product_ID product
//	 */
//	private void setCosts()
//	{
//		// Obtenemos la informacion del producto
//		/*ProductInfo pi = ProductInfo(M_Product_ID,null);
//		if(pi == null)
//			throw new IllegalArgumentException("MTransaction - No ProductInfo");
//		*/
//		// Obtenemos el Esquema
//		MAcctSchema as = MAcctSchema.get(m_ctx, m_as.getC_AcctSchema_ID());
//		if(as == null)
//			throw new IllegalArgumentException("MTransaction - No MAcctSchema");
//
//		BigDecimal priceLastPO = getProductItemCost(getCtx(),m_product.getM_Product_ID(), as, MAcctSchema.COSTINGMETHOD_LastPOPrice);
//        
//        
//        //BigDecimal costAverage = getProductItemCost(m_product.getM_Product_ID(), as, MAcctSchema.COSTINGMETHOD_AveragePO);
//        BigDecimal costAverage = MCost.getCurrentCost (m_product, m_M_AttributeSetInstance_ID, 
//                m_as, m_AD_Org_ID, MAcctSchema.COSTINGMETHOD_AveragePO, Env.ONE, m_C_OrderLine_ID, m_zeroCostOK, false,get_TrxName());
//
//        
//		//BigDecimal costStandar = getProductItemCost(m_product.getM_Product_ID(), as, null);
//        BigDecimal costStandar = MCost.getCurrentCost (m_product, m_M_AttributeSetInstance_ID, 
//                m_as, m_AD_Org_ID, MAcctSchema.COSTINGMETHOD_StandardCosting, Env.ONE, m_C_OrderLine_ID, m_zeroCostOK, false,get_TrxName());
//        
//        
//        //expert : gisela lee : get cost average invoice
//        BigDecimal costAverageI = MCost.getCurrentCost (m_product, m_M_AttributeSetInstance_ID, 
//                m_as, m_AD_Org_ID, MAcctSchema.COSTINGMETHOD_AverageInvoice, Env.ONE, m_C_OrderLine_ID, m_zeroCostOK, false,get_TrxName());
//        
//		//System.out.println(">>>>>>>>>>>>> priceLastPO - " + priceLastPO.doubleValue());
//		//System.out.println(">>>>>>>>>>>>> costStandad - " + costStandar.doubleValue());
//		//System.out.println(">>>>>>>>>>>>> costAverage - " + costAverage.doubleValue());
//        //System.out.println(">>>>>>>>>>>>> costAverageI - " + costAverageI.doubleValue());
//		setPriceLastPO(priceLastPO);
//		setCostStandard(costStandar);
//		setCostAverage(costAverage);
//        //expert : gisela lee : set cost average invoice
//        setCostAverageI(costAverageI);
//
//	}	//	setCosts
//
//	/**
//	 * 	Busca y establece las existencias del producto
//	 *	@param ctx context
//	 * 	@param M_Product_ID product
//	 * 	@param M_Locator_ID locator
//	 *
//	private void setQtyOnHand(Properties ctx, int M_Product_ID, int M_Locator_ID,
//	                          int C_AcctSchema_ID, int M_AttributeSetInstance_ID)
//	{
//		BigDecimal qtyOnHand = null;
//
//		MStorage ms = MStorage.get(ctx, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID, trx_Name);
//		if(ms == null)
//			throw new IllegalArgumentException("MTransaction - No MStorage");
//		
//		qtyOnHand = ms.getQtyOnHand();
//		
//		System.out.println("******** EXME_MTransaction : qtyOnHand - " + qtyOnHand.doubleValue());
//		setQtyOnHand(qtyOnHand);
//		ms = null;
//
//	}	//	setQtyOnHand*/
//
//
//	/**
//	 * COPIA DE LA CLASE ProductInfo en ServerRoot
//	 *  Get Product Costs per UOM for Accounting Schema in Accounting Schema Currency.
//	 *  - if costType defined - cost
//	 *  - else CurrentCosts
//	 *  @param as accounting schema
//	 *  @param costType - if null uses Accounting Schema Costs - see AcctSchema.COSTING_* � PrecioLista, PrecioActual.
//	 *  @return product costs
//	 */
//	public static BigDecimal getProductItemCost(Properties ctx, int M_Product_ID, MAcctSchema as, String costType)
//	{
//		BigDecimal current = null;
//		BigDecimal cost = null;
//		String cm = as.getCostingMethod();
//		StringBuffer sql = new StringBuffer("SELECT CurrentCostPrice,");	//	1
//		//
//		if ((costType == null && MAcctSchema.COSTINGMETHOD_AveragePO.equals(cm))
//		                || MAcctSchema.COSTINGMETHOD_AveragePO.equals(costType))
//			sql.append("COSTAVERAGE");										//	2
//		//	else if (AcctSchema.COSTING_FIFO.equals(cm))
//		//		sql.append("COSTFIFO");
//		//	else if (AcctSchema.COSTING_LIFO.equals(cm))
//		//		sql.append("COSTLIFO");
//		else if ((costType == null && MAcctSchema.COSTINGMETHOD_LastPOPrice.equals(cm))
//		                || MAcctSchema.COSTINGMETHOD_LastPOPrice.equals(costType))
//			sql.append("PRICELASTPO");
//		else if ((costType == null && MAcctSchema.COSTINGMETHOD_LastPOPrice.equals(cm))
//                || MEXMEConfigPre.PRICELIST_COST.equals(costType))
//		    sql.append("PRICELIST");
//		else if ((costType == null && MAcctSchema.COSTINGMETHOD_LastPOPrice.equals(cm))
//                || MEXMEConfigPre.PRICEACTUAL_COST.equals(costType))
//		    sql.append("PRICEACTUAL");
//		else    //  AcctSchema.COSTING_STANDARD
//			sql.append("COSTSTANDARD");
//		sql.append(" FROM M_Product_Costing WHERE M_Product_ID=? AND C_AcctSchema_ID=?");
//		
//		sql =  new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Product_Costing"));
//		PreparedStatement pstmt= null;
//		ResultSet rs = null;
//		try
//		{
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, M_Product_ID);
//			pstmt.setInt(2, as.getC_AcctSchema_ID());
//			rs = pstmt.executeQuery();
//			if (rs.next())
//			{
//				current = rs.getBigDecimal(1);
//				cost = rs.getBigDecimal(2);
//			}
//			
//		}
//		catch (SQLException e)
//		{
//			throw new IllegalArgumentException("MTransaction - getProductItemCost - " + e.getMessage());
//			
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		//  Return Costs
//		if (costType != null && cost != null && !cost.equals(Env.ZERO))
//		{
//			System.out.println("DEBUG: cost = 0");
//			return cost;
//		}
//		else if (current != null && !current.equals(Env.ZERO))
//		{
//			System.out.println("DEBUG: current = 0");
//			return current;
//		}
//
//		return cost;
//	}   //  getProductCost
//	/* Expert:Hassan - Fin del bloque */
//	
//	/**
//	 * Genera la valuacion de inventario entre un almacen y otro con una fecha dada
//	 * @param ctx
//	 * @param M_WarehouseIni_ID
//	 * @param M_WarehouseFin_ID
//	 * @param fecha
//	 * @param trxName
//	 */
//	public static void generaValuacion(Properties ctx, String M_WarehouseIni, String M_WarehouseFin, String fecha, String trxName){
//		
//		List <MEXMETValuacion> lista = new ArrayList<MEXMETValuacion>();
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		ResultSet rs = null;
//		BigDecimal TempCosto = null;
//		sql.append("SELECT p.VALUE, w.M_Warehouse_ID, w.name as almacen, p.NAME as producto, " )
//		   .append("(t.QTYONHAND - t.MOVEMENTQTY) as c_inicial, t.MOVEMENTQTY as c_mov, t.QTYONHAND as c_final, ")
//		   .append("case pacct.COSTINGMETHOD ")
//		   .append("when 'S' then (t.COSTSTANDARD) ")
//		   .append("when 'A' then (t.COSTAVERAGE) ")
//		   .append("when 'P' then (t.PRICELASTPO) ")
//		   .append("when null then (")
//		   .append("case acs.COSTINGMETHOD ")
//		   .append("when 'S' then (t.COSTSTANDARD) ")
//		   .append("when 'A' then (t.COSTAVERAGE) ")
//		   .append("when 'P' then (t.PRICELASTPO) ")
//		   .append("end) ")
//		   .append("end as costo ")
//		   .append("FROM M_TRANSACTION t ")
//		   .append("INNER JOIN M_Product p ON (p.m_product_id = t.m_product_id) ")
//		   .append("INNER JOIN M_PRODUCT_CATEGORY_ACCT pacct ON(pacct.M_PRODUCT_CATEGORY_ID = p.M_PRODUCT_CATEGORY_ID) ")
//		   .append("INNER JOIN M_Locator l ON (t.m_locator_id = l.m_locator_id) ")
//		   .append("INNER JOIN M_Warehouse w ON (l.m_warehouse_id = w.m_warehouse_id) ")
//		   .append("INNER JOIN AD_ClientInfo ci ON (w.AD_Client_ID=ci.AD_Client_ID) ")
//		   .append("INNER JOIN C_AcctSchema acs ON (ci.C_AcctSchema1_ID=acs.C_AcctSchema_ID) ")
//		   .append("WHERE t.MOVEMENTDATE <= TO_DATE('").append(fecha).append("','DD/MM/YYYY') ")
//		   .append("AND w.name BETWEEN ").append(DB.TO_STRING(M_WarehouseIni)).append("AND ")
//		   .append(DB.TO_STRING(M_WarehouseFin)).append(" ");
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Transaction", "t"));
//		sql.append(" ORDER BY p.NAME, p.VALUE, t.CREATED, t.M_TRANSACTION_ID ");
//		PreparedStatement pstmt = null;
//        try {
//        	pstmt = DB.prepareStatement(sql.toString(), trxName);
//        	
//        	rs = pstmt.executeQuery();
//        	
//        	while(rs.next()){
//        		TempCosto = rs.getBigDecimal("costo");
//
//        		MEXMETValuacion linea = new MEXMETValuacion(ctx,0,trxName);
//        		linea.setCantidad(rs.getBigDecimal("c_final"));
//        		linea.setName(rs.getString("producto"));
//        		
//        		//Alejandro se valida  cuando  el costo viene  null
//                if (TempCosto != null) {
//                	linea.setPrecio(TempCosto);
//                } else {
//                	linea.setPrecio(new BigDecimal(0.0));
//                }
//
//        		linea.setValue(rs.getString("value"));
//        		linea.setTotal(linea.getPrecio().multiply(linea.getCantidad()));
//        		linea.setAD_Session_ID(Env.getAD_Session_ID(ctx));
//        		linea.setM_Warehouse_ID(rs.getInt("M_Warehouse_ID"));
//        		linea.setAlmacen(rs.getString("almacen"));
//        		lista.add(linea);
//        	}
//        	MEXMETValuacion.prepara(lista, trxName);
//          
//
//        }catch (SQLException e) {
//        	s_log.log(Level.SEVERE, sql.toString(), e);
//        } catch (Exception e) {
//        	s_log.log(Level.SEVERE, e.getMessage(), e);
//			lista = null;
//		} finally {
//			DB.close(rs,pstmt);
//		}
//	}
//	
//	/* Hassan:reyes - EXME_MTransaction */
//	/**
//	 * Clase que extiende de MTransaction.<p>
//	 *
//	 * <b>Modificado: </b> $Author: mrojas $<p>
//	 * <b>En :</b> $Date: 2006/08/11 02:26:21 $<p>
//	 *
//	 * @author Hassan Reyes
//	 * @version $Revision: 1.10 $
//	 */
//	//	public class EXME_MTransaction extends MTransaction
//	/**
//	 * 	Detail Constructor
//	 *	@param ctx context
//	 * 	@param MovementType movement type
//	 * 	@param M_Locator_ID locator
//	 * 	@param M_Product_ID product
//	 * 	@param M_AttributeSetInstance_ID attribute
//	 * 	@param MovementQty qty
//	 * 	@param MovementDate optional date
//	 */
//	public MEXMETransaction (Properties ctx, int AD_Org_ID, String MovementType,
//	                     int M_Locator_ID, int M_Product_ID, int M_AttributeSetInstance_ID,
//	                     BigDecimal MovementQty, Timestamp MovementDate, int C_AcctSchema_ID, String trx_Name){
//
//		super(ctx, AD_Org_ID, MovementType, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID, MovementQty, MovementDate, trx_Name);
//
//		// Expert:Hassan - Establecemos el Esquema Contable y Llamamos a setCosts() y setQtyOnHand()
//		if (C_AcctSchema_ID <= 0)
//			throw new IllegalArgumentException("MTransaction - No C_AcctSchema_ID");
//		// System.out.println(">>>>>>>>>>>>> C_AcctSchema_ID - " + C_AcctSchema_ID);
//		// this.trx_Name = trx_Name;
//		setC_AcctSchema_ID(C_AcctSchema_ID);
//		setCosts(ctx, M_Product_ID, C_AcctSchema_ID);
//		setQtyOnHand(ctx, M_Product_ID, M_Locator_ID, C_AcctSchema_ID, M_AttributeSetInstance_ID);
//		// Expert:Hassan - Fin del Bloque
//	} // EXME_MTransaction
//	
//	/* Expert:Hassan - Metodo que busca y establece al modelo los valores
//	 para los costos del producto y esquema contable y busca las existencias actuales*/
//	/**
//	 * 	Busca y establece al modelo los valores 
//	 *	para los costos del producto y esquema contable
//	 *	@param ctx context
//	 * 	@param M_Product_ID product
//	 */
//	private void setCosts(Properties ctx, int M_Product_ID, int C_AcctSchema_ID) {
//		// Obtenemos la informacion del producto
//		//ProductInfo pi = ProductInfo(M_Product_ID,null); if(pi == null) throw new IllegalArgumentException("MTransaction - No ProductInfo");
//		// Obtenemos el Esquema
//		MAcctSchema as = MAcctSchema.get(ctx, C_AcctSchema_ID);
//		if (as == null)
//			throw new IllegalArgumentException("MTransaction - No MAcctSchema");
//		BigDecimal costAverage = getProductItemCost(M_Product_ID, as, MAcctSchema.COSTINGMETHOD_AveragePO);
//		BigDecimal priceLastPO = getProductItemCost(M_Product_ID, as, MAcctSchema.COSTINGMETHOD_LastPOPrice);
//		BigDecimal costStandar = getProductItemCost(M_Product_ID, as, null);
//		// System.out.println(">>>>>>>>>>>>> priceLastPO - " + priceLastPO.doubleValue());
//		// System.out.println(">>>>>>>>>>>>> costStandad - " + costStandar.doubleValue());
//		// System.out.println(">>>>>>>>>>>>> costAverage - " + costAverage.doubleValue());
//		setPriceLastPO(priceLastPO);
//		setCostStandard(costStandar);
//		setCostAverage(costAverage);
//
//	} // setCosts
//	/**
//	 * 	Busca y establece las existencias del producto
//	 *	@param ctx context
//	 * 	@param M_Product_ID product
//	 * 	@param M_Locator_ID locator
//	 */
//	private void setQtyOnHand(Properties ctx, int M_Product_ID, int M_Locator_ID,
//	                          int C_AcctSchema_ID, int M_AttributeSetInstance_ID) {
//		BigDecimal qtyOnHand = null;
//		MStorage ms = MStorage.get(ctx, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID, get_TrxName());
//		if (ms == null)
//			throw new IllegalArgumentException("MTransaction - No MStorage");
//		qtyOnHand = ms.getQtyOnHand();
//		// System.out.println("******** EXME_MTransaction : qtyOnHand - " + qtyOnHand.doubleValue());
//		setQtyOnHand(qtyOnHand);
//		ms = null;
//	} // setQtyOnHand
//
//	/**
//	 * COPIA DE LA CLASE ProductInfo en ServerRoot
//	 *  Get Product Costs per UOM for Accounting Schema in Accounting Schema Currency.
//	 *  - if costType defined - cost
//	 *  - else CurrentCosts
//	 *  @param as accounting schema
//	 *  @param costType - if null uses Accounting Schema Costs - see AcctSchema.COSTING_* � PrecioLista, PrecioActual.
//	 *  @return product costs
//	 */
//	public static BigDecimal getProductItemCost(int M_Product_ID, MAcctSchema as, String costType) {
//		BigDecimal current = null;
//		BigDecimal cost = null;
//		String cm = as.getCostingMethod();
//		StringBuffer sql = new StringBuffer("SELECT CurrentCostPrice,"); // 1
//		//
//		if ((costType == null && MAcctSchema.COSTINGMETHOD_AveragePO.equals(cm)) || MAcctSchema.COSTINGMETHOD_AveragePO.equals(costType))
//			sql.append("COSTAVERAGE"); // 2
//		// else if (AcctSchema.COSTING_FIFO.equals(cm))
//		// sql.append("COSTFIFO");
//		// else if (AcctSchema.COSTING_LIFO.equals(cm))
//		// sql.append("COSTLIFO");
//		else if ((costType == null && MAcctSchema.COSTINGMETHOD_LastPOPrice.equals(cm)) || MAcctSchema.COSTINGMETHOD_LastPOPrice.equals(costType))
//			sql.append("PRICELASTPO");
//		else if ((costType == null && MAcctSchema.COSTINGMETHOD_LastPOPrice.equals(cm)) || MEXMEConfigPre.PRICELIST_COST.equals(costType))
//			sql.append("PRICELIST");
//		else if ((costType == null && MAcctSchema.COSTINGMETHOD_LastPOPrice.equals(cm)) || MEXMEConfigPre.PRICEACTUAL_COST.equals(costType))
//			sql.append("PRICEACTUAL");
//		else
//			// AcctSchema.COSTING_STANDARD
//			sql.append("COSTSTANDARD");
//		sql.append(" FROM M_Product_Costing WHERE M_Product_ID=? AND C_AcctSchema_ID=?");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, M_Product_ID);
//			pstmt.setInt(2, as.getC_AcctSchema_ID());
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				current = rs.getBigDecimal(1);
//				cost = rs.getBigDecimal(2);
//			}
//			
//		} catch (SQLException e) {
//			throw new IllegalArgumentException("MTransaction - getProductItemCost - " + e.getMessage());
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		// Return Costs
//		if (costType != null && cost != null && !cost.equals(Env.ZERO)) {
//			// System.out.println("DEBUG: cost = 0");
//			return cost;
//		} else if (current != null && !current.equals(Env.ZERO)) {
//			// System.out.println("DEBUG: current = 0");
//			return current;
//		}
//
//		return cost;
//	} // getProductCost
//	//}/* Expert:Hassan - Fin del bloque (EXME_MTransaction) */
	
}	//	MTransaction
