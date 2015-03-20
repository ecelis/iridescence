package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.WebEnv;

/**
 * Dosis del producto
 * @author Expert
 *
 */
public class MEXMEDoseForm extends X_EXME_DoseForm{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -7337675712804984227L;
	/** Logger **/
	private static CLogger log = CLogger.getCLogger(MEXMEDoseForm.class);
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_DoseForm_ID
	 * @param trxName
	 */
	public MEXMEDoseForm(Properties ctx, int EXME_DoseForm_ID, String trxName) {
		super(ctx, EXME_DoseForm_ID, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEDoseForm(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param DFID
	 * @return
	 */
	public static boolean validateDoseForm(Properties ctx, int DFID){
		boolean valido = true;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT d.* ")
		   .append("FROM EXME_DOSEFORM d ")
		   .append("WHERE d.DFID = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 pstmt.setInt(1, DFID);
			 result = pstmt.executeQuery();
			 
			 if(!result.next()){
				 valido = createDoseForm(ctx, DFID);		 
			 }
			 
		} catch (Exception e) {
    		log.log(Level.SEVERE, 
    				"getManufacturer(Properties ctx, int DFID)" + e.getMessage(),
    				e);
    	} finally {
    		DB.close(result,pstmt);
    	}
		return valido;
	}
	
	public static boolean createDoseForm(Properties ctx, int DFID){
		MEXMEDoseForm ret = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT  d.DFID, d.DESCRIPTION, d.ABBREV  ")
		   .append("FROM FDB_DOSEFORM d ")
		   .append("WHERE d.DFID = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 pstmt.setInt(1, DFID);
			 result = pstmt.executeQuery();
			 
			 if(result.next()){
				ret = new MEXMEDoseForm(ctx, 0, null);
				ret.setDfID(result.getInt(1));
				ret.setDescription(result.getString(2));
				ret.setAbrev(result.getString(3));
			 }
		} catch (Exception e) {
    		log.log(Level.SEVERE, 
    				"getManufacturer(Properties ctx, int DFID)" + e.getMessage(),
    				e);
    	} finally {
    		DB.close(result,pstmt);
    	}
		return ret.save();
	}

	  /**
     * Lista con las dosis deacuerdo a su nivel de acceso
     * @param ctx
     * @param trxName
     * @return
     * @throws Exception
     */
	public static List<LabelValueBean> get(Properties ctx, String trxName)
		throws Exception {

			List<LabelValueBean> list = new ArrayList<LabelValueBean>();
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			sql.append(" SELECT Description AS label, EXME_DoseForm_ID ")
			.append(" FROM EXME_DoseForm ")
			.append(" WHERE IsActive = 'Y'")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
			.append(" ORDER BY Name ");

			if (WebEnv.DEBUG) {
				log.log(Level.FINE,"EXME_DoseForm.get() SQL : " + sql.toString());
			}

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					list.add(new LabelValueBean(rs.getString(1), String.valueOf(rs.getInt(2))));
				}

			} catch (Exception e) {
				log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
			}
		
		return list;
	}

}
