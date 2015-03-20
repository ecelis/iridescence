/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author arangel
 *
 */
public class MEXME_PedidoBaseDet extends X_EXME_PedidoBaseDet {
	
	private static final long serialVersionUID = 1L;
	/**	Static Logger
	 * 				*/
	private static CLogger s_log = CLogger.getCLogger (X_EXME_PedidoBaseDet.class);
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_PedidoBaseDet_ID
	 * @param trxName
	 */
	public MEXME_PedidoBaseDet(Properties ctx, int EXME_PedidoBaseDet_ID,
			String trxName) {
		
		super(ctx, EXME_PedidoBaseDet_ID, trxName);
	}
	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXME_PedidoBaseDet(Properties ctx, ResultSet rs, String trxName){
	      
		super (ctx, rs, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param pedBasId
	 * @param cuestId
	 * @param trxName
	 * @return
	 */
	public static List<MEXME_PedidoBaseDet> get(Properties ctx, int pedBasId, String trxName) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_pedidobasedet ");
		sql.append("WHERE ");
		sql.append("  exme_pedidobase_id = ? AND ");
		sql.append("  isactive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXME_PedidoBaseDet.Table_Name));
		sql.append("order by exme_pedidobasedet_id ");
		List<MEXME_PedidoBaseDet> list = new ArrayList<MEXME_PedidoBaseDet>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pedBasId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXME_PedidoBaseDet(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	public String getUomSel(){
		int uomID = getOp_Uom();
		MUOM uom = MUOM.get(Env.getCtx(), uomID);
		return uom==null?"":uom.getName();
	}
	
	public String getProdQty(){
		String qty = "";
		
		MProduct product = MProduct.get(getCtx(), getM_Product_ID());
		if(product.getC_UOM_ID() == product.getC_UOMVolume_ID()){
			qty = getMovementQty()==null?BigDecimal.ZERO.toString():getMovementQty().toString();
		}else{
			if(getOp_Uom() == product.getC_UOM_ID()){
				qty = getMovementQty()==null?BigDecimal.ZERO.toString():getMovementQty().toString();
			}else if (getOp_Uom() == product.getC_UOMVolume_ID()){
				qty = getMovementQty_Vol()==null?BigDecimal.ZERO.toString():getMovementQty_Vol().toString();
			}
		}
		
		return qty;
	}
	
	public String getProdName(){
		return new MEXMEProduct(getCtx(), getM_Product_ID(), null).getName();
	}
	
	public String getProdDesc(){
		return new MEXMEProduct(getCtx(), getM_Product_ID(), null).getDescription();
	}
	
	public BigDecimal quantityUser;


	public BigDecimal getQuantityUser() {
		return quantityUser;
	}

	public void setQuantityUser(BigDecimal quantityUser) {
		this.quantityUser = quantityUser;
	}
	
	boolean isNew = false;


	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
}
