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


public class MEXMEDosis extends X_EXME_Dosis {
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEDosis.class);
	
	public MEXMEDosis (Properties ctx, int EXME_Dosis_ID, String trxName)
    {
      super (ctx, EXME_Dosis_ID, trxName);
    }

    /** Load Constructor */
    public MEXMEDosis (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
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

			sql.append(" SELECT Value || ' ' || Name AS label, EXME_Dosis_ID ")
			.append(" FROM EXME_Dosis ")
			.append(" WHERE IsActive = 'Y'")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Dosis"))
			.append(" ORDER BY Name ");

			if (WebEnv.DEBUG) {
				s_log.log(Level.FINE,"EXMEDosis.get() SQL : " + sql.toString());
			}

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					list.add(new LabelValueBean(rs.getString(1), String.valueOf(rs.getInt(2))));
				}

			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
				pstmt = null;
				rs = null;
			}
		
		return list;
	}
}
