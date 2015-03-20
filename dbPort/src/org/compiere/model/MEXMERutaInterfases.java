package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;


/**
 * <b>Prop&oacute;sito: <b> <p>
 * <b>Creado: </b> 29/06/2007<p>
 * <b>Modificado: </b> $Date$<p>
 * <b>Por : </b> $Author$<p>
 * 
 * @author Seam
 * @version $Revision$
 */
public class MEXMERutaInterfases extends X_EXME_Ruta_Interfases {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/

	private static CLogger		s_log = CLogger.getCLogger (MEXMERutaInterfases.class);

    /**
     * @param ctx
     * @param EXME_Ruta_Interfases_ID
     * @param trxName
     */
    public MEXMERutaInterfases(Properties ctx, int EXME_Ruta_Interfases_ID, String trxName) {
        super(ctx, EXME_Ruta_Interfases_ID, trxName);
    }

 /**
  * 
  * @param ctx
  * @param rs
  * @param trxName
  */
    public MEXMERutaInterfases(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

/**
 * 
 * @param ctx
 * @param trxName
 * @param whereClause
 * @param orderBy
 * @return
 */
 public static MEXMERutaInterfases[] get(Properties ctx, String trxName, String whereClause, String orderBy){
        ArrayList<MEXMERutaInterfases> list = new ArrayList<MEXMERutaInterfases>();
		String sql = "SELECT * FROM EXME_Ruta_Interfases WHERE isActive = 'Y' ";
					//AD_Client_ID = ? " +
		             //"AND AD_Org_ID IN (0,?) AND  ";
		
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Ruta_Interfases");

		if (whereClause != null)
			sql += whereClause;

		if (orderBy != null && orderBy.length() > 0)
			sql += " ORDER BY " + orderBy;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql, trxName);
			rs = pstmt.executeQuery();
			boolean primero = false;

			while (rs.next()) {
				MEXMERutaInterfases rutas = new MEXMERutaInterfases(ctx, rs, trxName);
				if (!primero) {
					list.add(0, rutas);
					primero = true;
				} else
					list.add(rutas);
			}


		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
		}

		//
		MEXMERutaInterfases[] rutas = new MEXMERutaInterfases[list.size()];
		list.toArray(rutas);
		return rutas;
	}

}