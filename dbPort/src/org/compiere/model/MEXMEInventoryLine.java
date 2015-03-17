/**
 * 
 */
package org.compiere.model;

import java.util.Properties;

/**
 * @author Expert
 * @deprecated
 */
public class MEXMEInventoryLine extends MInventoryLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4729511333313874681L;
//	private static CLogger slog = CLogger.getCLogger(MEXMEInventoryLine.class);
	/**
	 * @param ctx
	 * @param M_InventoryLine_ID
	 * @param trxName
	 */
	public MEXMEInventoryLine(Properties ctx, int M_InventoryLine_ID,
			String trxName) {
		super(ctx, M_InventoryLine_ID, trxName);
	}

//	/**
//	 * @param ctx
//	 * @param rs
//	 * @param trxName
//	 */
//	public MEXMEInventoryLine(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	}

//	/**
//	 * @param inventory
//	 * @param M_Locator_ID
//	 * @param M_Product_ID
//	 * @param M_AttributeSetInstance_ID
//	 * @param QtyBook
//	 * @param QtyCount
//	 */
//	public MEXMEInventoryLine(MInventory inventory, int M_Locator_ID,
//			int M_Product_ID, int M_AttributeSetInstance_ID,
//			BigDecimal QtyBook, BigDecimal QtyCount) {
//		super(inventory, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID,
//				QtyBook, QtyCount);
//		
//		/*
//		//setM_Inventory_ID(M_Inventory_ID);
//		//setM_LOCATOR_ID
//		//M_PRODUCT_ID
//		//LINE // before //@SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_InventoryLine WHERE M_Inventory_ID=@M_Inventory_ID@
//		//QTYBOOK
//		//QTYCOUNT
//		DESCRIPTION
//		//M_ATTRIBUTESETINSTANCE_ID
//		C_CHARGE_ID
//		INVENTORYTYPE
//		PROCESSED
//		QTYINTERNALUSE
//		LOTINFO
//		QTYCSV
//		REVERSALLINE_ID*/
//		
//		setInventoryType(X_M_InventoryLine.INVENTORYTYPE_InventoryDifference);
//	}
//
//	/**
//	 * 
//	 * @param M_AttributeSetInstance_ID
//	 * @param M_Product_ID
//	 * @param M_Locator_ID
//	 * @return
//	 */
//	public static BigDecimal calQtyBook(int M_AttributeSetInstance_ID, int M_Product_ID, int M_Locator_ID ){
//		String sql = CalloutInventory.qtySql(M_AttributeSetInstance_ID);
//		BigDecimal bd = Env.ZERO;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try
//		{
//			pstmt = DB.prepareStatement(sql, null);
//			pstmt.setInt(1, M_Product_ID );
//			pstmt.setInt(2, M_Locator_ID );
//			
//			if (M_AttributeSetInstance_ID != 0)
//				pstmt.setInt(3, M_AttributeSetInstance_ID);
//			
//			rs = pstmt.executeQuery();
//			
//			if (rs.next())
//			{
//				bd = rs.getBigDecimal(1);
//				
//			}
//		}
//		catch (SQLException e)
//		{
//			slog.log(Level.SEVERE, sql, e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		
//		//
//		slog.info("M_Product_ID=" + M_Product_ID 
//			+ ", M_Locator_ID=" + M_Locator_ID
//			+ ", M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID
//			+ " - QtyBook=" + bd);
//		return bd;
//	}   //  product
}
