/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Modelo de Links
 * Modificado: mgarcia
 * Date: 2007/06/22
 *
 * @author M Garcia
 * @version 1.0
 */
public class MEXMELinks extends X_EXME_Links{
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMELinks.class);

	public final static String  EXME_Links_ID_Key = "EXME_Links_ID";

	/**	Process Message 			*/
	
	private String trxName = null;
	
	/**
	 * @param ctx
	 * @param EXME_Links_ID
	 * @param trxName
	 */
	public MEXMELinks(Properties ctx, int EXME_Links_ID, String trxName) {
		super(ctx, EXME_Links_ID, trxName);
		setTrxName(trxName);
	}
	
	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMELinks(Properties ctx, ResultSet rs, String trxName) {
	    super(ctx, rs, trxName);
	    setTrxName(trxName);
	}
	
	/**
	 * Regresa un arreglo de objetos con el Name y URL de todos los Links activos en la DB
	 * @param ctx
	 * @param trxName
	 * @return MEXMELinks[]
	 */
	public static MEXMELinks[] getLinksNameURL(Properties ctx, String trxName) {

		ArrayList<MEXMELinks> lstLinks = new ArrayList<MEXMELinks>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT * FROM EXME_Links WHERE isactive = 'Y'");
	
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_Links"));
		
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			    MEXMELinks link = new MEXMELinks(ctx, rs, trxName);
			    lstLinks.add(link);
			}
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}
		MEXMELinks[] links = new MEXMELinks[lstLinks.size()];
		lstLinks.toArray(links);
		return links;
	}

	/**
	 * @return Returns the trxName.
	 */
	public String getTrxName() {
		return trxName;
	}

	/**
	 * @param trxName The trxName to set.
	 */
	public void setTrxName(String trxName) {
		this.trxName = trxName;
	}
}