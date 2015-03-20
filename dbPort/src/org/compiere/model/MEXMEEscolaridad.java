package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEEscolaridad extends X_EXME_Escolaridad {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -1123107362410425044L;

	/**
     * @param ctx
     * @param EXME_Escolaridad_ID
     * @param trxName
     */
	public MEXMEEscolaridad(Properties ctx, int EXME_Escolaridad_ID, String trxName) {
        super(ctx, EXME_Escolaridad_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEEscolaridad(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Metodo que nos trae <strong> Description </strong> y <strong> EXME_Escolaridad_ID </strong>
     * de todas las nacionalidades existentes para poder desplegarlas en un combo
     * @param ctx
     * @param trxName
     * @return
     */
    public static List<LabelValueBean> getAllEscolaridades(Properties ctx, String trxName)  {
    
    	List<LabelValueBean> lstEscolaridades = new ArrayList<LabelValueBean>();

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT EXME_Escolaridad_ID, Description ")
			.append(" FROM EXME_Escolaridad WHERE isActive LIKE 'Y' ");		

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Escolaridad"));
		sql.append(" ORDER BY EXME_Escolaridad_ID");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean nac =
					new LabelValueBean(
					    rs.getString("Description"),
					    String.valueOf(rs.getLong("EXME_Escolaridad_ID"))
					);
			    lstEscolaridades.add(nac);
			}


		} catch (Exception e) {
        	e.printStackTrace();//FIXME
        } finally {
        	DB.close(rs,pstmt);
        }
    	return lstEscolaridades;	
    }
}
