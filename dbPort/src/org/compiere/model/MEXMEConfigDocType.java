package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
/**
 * Clase para el Tipo de Documento Configurado
 * @author lhernandez
 *
 */
public class MEXMEConfigDocType extends X_EXME_ConfigDocType {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * CLogger
	 */
	private static CLogger      s_log = CLogger.getCLogger (MEXMEConfigDocType.class);
	
	public MEXMEConfigDocType(Properties ctx, int EXME_ConfigDocType_ID,
			String trxName) {
		super(ctx, EXME_ConfigDocType_ID, trxName);
	}

	public MEXMEConfigDocType(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}
	
    /**
     * Obtenemos la configuracion del tipo de documento.
     * (Cliente + Organizacion).
     * 
     * @param ctx
     * @param trxName
     * @return MEXMEConfigDocType retValue
     */
    public static MEXMEConfigDocType get(Properties ctx, String trxName){

       	MEXMEConfigDocType retValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
		sql.append(" SELECT * FROM EXME_ConfigDocType WHERE IsActive='Y' ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),"EXME_ConfigDocType"));
		sql.append(" ORDER BY AD_Org_ID DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			if (rs.next())
				retValue = new MEXMEConfigDocType(ctx, rs, trxName);

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get - closing", e);
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
			sql = null;
		}

		return retValue;
	}
}
