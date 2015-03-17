/*
 * Created on 18-mar-2005
 *
 */
package org.compiere.model;




/**
 * Clase que extiende de MCashLine.
 * <p>
 * Basado en version: MCashLine,v 1.16 200O/02/18 13:16:29 vharcq Exp
 * 
 * <b>Modificado: </b> $Author: gisela $
 * <p>
 * <b>En :</b> $Date: 2006/08/30 00:58:02 $
 * <p>
 * 
 * @author Gisela Lee
 * @version $Revision: 1.64 $
 */
public class MCtaPacExt {
//	/**
//	 * Obtenemos la extension cero de un determinada cuenta paciente.
//	 * 
//	 * @param EXME_CtaPac_ID
//	 * @return
//	 */
//	
//	/** Logger */
//	private static CLogger s_log = CLogger.getCLogger(MCtaPacExt.class);
//	public static MEXMECtaPacExt getExtCero(Properties ctx, int EXME_CtaPac_ID,
//			String trxName) {
//
//		MEXMECtaPacExt retValue = null;
//
//	  	PreparedStatement pstmt = null;
//    	ResultSet rs = null;
//    	 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		try {
//
//			sql.append("SELECT * FROM EXME_CtaPacExt WHERE EXME_CtaPac_ID = ")
//			.append(EXME_CtaPac_ID)
//			.append(" AND Ref_CtaPacExt_ID IS NULL ");
//
//			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacExt"));
//			
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				retValue = new MEXMECtaPacExt(ctx, rs, trxName);
//			}
//		} catch (Exception e) {
//    		//sql = null;
//    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//    		try {
//    			if (pstmt != null)
//    				pstmt.close ();
//    			if (rs != null)
//    				rs.close ();
//    		} catch (Exception ex) {
//    			s_log.log(Level.SEVERE, sql.toString(), ex.getMessage());
//    		}
//    		pstmt = null;
//    		rs = null;
//    	}finally {
//    		try {
//    			if (pstmt != null) {
//    				pstmt.close();
//    			}
//    			if (rs != null) {
//    				rs.close();
//    			}
//    			pstmt = null;
//    			rs = null;
//    		} catch (Exception e) {
//    			s_log.log(Level.SEVERE, "Closing rs and pstmt", e.getMessage());
//    			pstmt = null;
//    			rs = null;
//    		}
//    	}
//
//		return retValue;
//
//	}

}