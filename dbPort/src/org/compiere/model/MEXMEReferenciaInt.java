package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEReferenciaInt extends X_EXME_Referencia_Int {
    
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
     * @param ctx
     * @param EXME_Referencia_Int_ID
     * @param trxName
     */
	public MEXMEReferenciaInt(Properties ctx, int EXME_Referencia_Int_ID, String trxName) {
        super(ctx, EXME_Referencia_Int_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEReferenciaInt(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Metodo que nos trae <strong> Description </strong> y <strong> EXME_Referencia_Int_ID </strong>
     * de todas las ReferenciaInts existentes para poder desplegarlas en un combo
     * @param ctx
     * @param trxName
     * @return
     */
    public static List<LabelValueBean> getAllReferenciasInt(Properties ctx, String trxName)  {
    
    	List<LabelValueBean> lstReferenciasInt = new ArrayList<LabelValueBean>();

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT EXME_Referencia_Int_ID, Description ")
			.append(" FROM EXME_Referencia_Int WHERE isActive LIKE 'Y' ");		

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Referencia_Int"));
		sql.append(" ORDER BY EXME_Referencia_Int_ID");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean nac =
					new LabelValueBean(
					    rs.getString("Description"),
					    String.valueOf(rs.getLong("EXME_Referencia_Int_ID"))
					);
			    lstReferenciasInt.add(nac);
			}

			
		} catch (Exception e) {
        	e.printStackTrace();//FIXME
        } finally {
        	DB.close(rs, pstmt);
        }
    	return lstReferenciasInt;	
    }
}
