/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author twry
 *
 */
public class MEXMEProductClassFact extends X_EXME_ProductClassFact {

	/** serialVersionUID */
	private static final long serialVersionUID = -5960335772917642413L;
	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(MEXMEProductClassFact.class);
	
	/**
	 * Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEProductClassFact(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * get
	 * @param ctx
	 * @param m_Product_ID
	 * @param trxName
	 * @return
	 */
	public static int get(Properties ctx, int m_Product_ID, String trxName) {
		StringBuilder sql = new StringBuilder()
		.append(" SELECT pcf.* FROM EXME_ProductClassFact pcf ")
		.append(" INNER JOIN M_Product p ON p.ProductClass = pcf.Value AND p.m_Product_ID = ? ")
		.append(" WHERE pcf.IsActive = 'Y'   ");
		MEXMEProductClassFact dPCF = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, m_Product_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dPCF = new MEXMEProductClassFact(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			slog.severe(sql.toString() +" "+ e);
		} finally {
			DB.close(rs, pstmt);
		}
		return dPCF ==null?0:dPCF.getEXME_ProductClassFact_ID();
	}
	
	/**
	 * beforeSave
	 */
	protected boolean beforeSave(final boolean newRecord) {
		log.fine("MEXMEProductClassFact.beforeSave");

		MRefList rlist = null;
		if(getAD_Ref_List_ID()<=0){
			rlist = MRefList.get(getCtx(), X_M_Product.PRODUCTCLASS_AD_Reference_ID, getValue(), get_TrxName());
			if(rlist==null){
				return false;
			} else {
				setAD_Ref_List_ID(rlist.getAD_Ref_List_ID());
			}
		}
		
		if(getName()==null && getName().isEmpty() && rlist!=null){
			setName(rlist.getName());
		}
				
		return true;
	} // beforeSave

}
