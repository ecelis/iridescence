/**
 * 
 */
package org.compiere.model.bpm;

import java.util.Properties;

import org.compiere.model.X_EXME_InOutStop;

/**
 * @author twry
 * @deprecated
 */
public class MEXMEInOutStop extends X_EXME_InOutStop {

	/** serialVersionUID */
	private static final long serialVersionUID = 8751391972939259050L;

	/**
	 * @param ctx
	 * @param EXME_InOutStop_ID
	 * @param trxName
	 */
	public MEXMEInOutStop(final Properties ctx, final int iInOutStopID, final String trxName) {
		super(ctx, iInOutStopID, trxName);
	}

//	/**
//	 * @param ctx
//	 * @param rset
//	 * @param trxName
//	 */
//	public MEXMEInOutStop(final Properties ctx, final ResultSet rset, final String trxName) {
//		super(ctx, rset, trxName);
//	}
	
	
//	 public static List<MEXMEInOutStop> find(Properties ctx,
//			 int productid,
//			 String lote,
//			 String trxName){
//	        
//		 List<MEXMEInOutStop> retValue = new ArrayList<MEXMEInOutStop>();
//	        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//	        
//	        sql.append("SELECT ios.* FROM exme_inoutstop ios WHERE ios.IsActive='Y' ");
//	        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name, "ios"));
//	        sql.append(" and ios.status = 'IP' and ios.m_product_id = ? ");
//	        
//	        if(lote!=null && !lote.isEmpty())
//	        	sql.append(" and ios.lot = ? ");
//	        else
//	        	 sql.append(" and trim(ios.lot) is null ");
//	        
//	        PreparedStatement pstmt = null;
//	        ResultSet rs = null;
//	
//    try {
//		pstmt = DB.prepareStatement(sql.toString(), trxName);
//		pstmt.setInt(1, productid);
//		if(lote!=null && !lote.isEmpty())
//		pstmt.setString(2, lote);
//		rs = pstmt.executeQuery();
//		if (rs.next()) {
//			retValue .add( new MEXMEInOutStop(ctx, rs, trxName));
//		}
//		
//    }catch (Exception e) {
//	} finally {
//		DB.close(rs,pstmt);
//		rs = null;
//		pstmt = null;
//	}
//	return retValue;
//}

}
