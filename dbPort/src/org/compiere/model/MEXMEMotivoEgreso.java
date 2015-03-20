/** 
 * Created on January 31, 2006
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

/**
 * Discharge Motive
 * 
 * @author Omar Torres <br>
 *         Modified by @author Lorena Lama on March, 2011
 */
public class MEXMEMotivoEgreso extends X_EXME_MotivoEgreso {

	private static final long serialVersionUID = 1L;

	/** Static Logger */
	private static CLogger log = CLogger.getCLogger(MEXMEMotivoEgreso.class);

	/**
	 * 
	 * @param ctx
	 * @param EXME_MotivoEgreso_ID
	 * @param trxName
	 */
	public MEXMEMotivoEgreso(Properties ctx, int EXME_MotivoEgreso_ID, String trxName) {
		super(ctx, EXME_MotivoEgreso_ID, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEMotivoEgreso(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> get(Properties ctx, String trxName){
		final List<KeyNamePair> retValue = new ArrayList<KeyNamePair>();
		final  StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			sql.append("SELECT EXME_MotivoEgreso_ID, Name ");
			sql.append(" FROM EXME_MotivoEgreso ");
			sql.append(" WHERE IsActive='Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()){
				retValue.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		}
		catch(Exception e){
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally{
			DB.close(rs, pstmt);
		}

		return retValue;

	}

}