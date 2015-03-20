package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;





public class MEXMETipoExp extends X_EXME_TipoExped{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;



	public MEXMETipoExp(Properties ctx, int EXME_TipoExped_ID, String trxName) {
		super(ctx, EXME_TipoExped_ID, trxName);
	}
	
	public MEXMETipoExp(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
		private static CLogger s_log = CLogger.getCLogger(MEXMETipoExp.class);		
		
		
		
		public static List<LabelValueBean> getDocuments(Properties ctx) throws Exception {
			//lista con las motivos de cita
			List<LabelValueBean> lstdocs = new ArrayList<LabelValueBean>();
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			//buscamos el motivo de cita
			String sql = "SELECT * FROM EXME_TipoExped where isActive = 'Y'";
			

			try {
				pstmt = DB.prepareStatement(sql, null);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					LabelValueBean doc =
							new LabelValueBean(
							rs.getString("Name"),
							String.valueOf(rs.getInt("EXME_TipoExped_ID"))
							);
					lstdocs.add(doc);
				}
	               
			} catch (Exception e) {
	    		//s_log.log(Level.SEVERE, sql.toString(), e.getMessage()); //FIXME???
	    	}finally {
	    		DB.close(rs, pstmt);
	    	}


			return lstdocs;
		}
		
		
		public static MEXMETipoExp getFromID(Properties ctx, int EXME_TipoExped_ID, String trxName){
			MEXMETipoExp tipo = null;
			StringBuilder sql = new StringBuilder();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			sql.append(" SELECT * FROM EXME_TipoExped WHERE EXME_TipoExped_ID = ? ");
			
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_TipoExped"));

			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, EXME_TipoExped_ID);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
				    tipo = new MEXMETipoExp(ctx, rs, trxName);
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getFromID", e);
			} finally {
				DB.close(rs, pstmt);
			}
			return tipo;
		}
		
		public static MEXMETipoExp getFromName(Properties ctx, String Name, String trxName){
			MEXMETipoExp tipo = null;
			StringBuilder sql = new StringBuilder();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			sql.append(" SELECT * FROM EXME_TipoExped WHERE Name LIKE ? ");
			
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_TipoExped"));

			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setString(1, Name);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
				    tipo = new MEXMETipoExp(ctx, rs, trxName);
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getFromID", e);
			} finally {
				DB.close(rs, pstmt);
			}
			return tipo;
		}

}
