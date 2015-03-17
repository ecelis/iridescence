package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author Asael Sepulveda
 */
public class MEXMERfc extends X_EXME_RFC {
    
    /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMERfc.class);
    
    /**
     * @param ctx
     * @param EXME_VentaSuspendida_ID
     * @param trxName
     */
    public MEXMERfc(Properties ctx, int EXME_RFC_ID, String trxName) {
        super(ctx, EXME_RFC_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMERfc(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
   
    /**
     * getByValue
     * 
     * Devuelve el registro de MEXMERfc correspondiente al Id proporcionado.
     * 
     * @param ctx
     * @param EXME_RFC_ID
     * @param trxName
     * @return
     */
    public static MEXMERfc getByValue(Properties ctx, String value, String trxName) {

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	
        MEXMERfc retValue = null;

        sql.append("SELECT * FROM EXME_RFC WHERE Upper(rfc) =Upper(?) AND isActive = 'Y' ");
        
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_RFC"));

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = DB.prepareStatement (sql.toString(), trxName);
            pstmt.setString(1, value);
            rs = pstmt.executeQuery ();
            
            if (rs.next ()) {
                retValue = new MEXMERfc(ctx, rs, null);
            }

        } catch (SQLException sqle) {
			s_log.log(Level.SEVERE, sql.toString(), sqle.getMessage());
		}finally {
			DB.close(rs, pstmt);
		}
        return retValue;
    }
    
    
    /**
     * existeRFC
     * 
     * Devuelve true si el RFC ya existe en un registro, false si no.
     * 
     * @param ctx
     * @param rfc
     * @param trxName
     * 
     * @return existe o no el rfc (true/false)
     */
    public static boolean existeRFC(Properties ctx, String rfc, String trxName) {

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        boolean retValue = true;

        sql.append("SELECT * FROM EXME_RFC WHERE rfc =? AND isActive = 'Y' ");
        
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_RFC"));

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = DB.prepareStatement (sql.toString(), trxName);
            pstmt.setString(1, rfc);
            rs = pstmt.executeQuery();
            
            if (!rs.next()) {
                retValue = false;
            }

        } catch (SQLException sqle) {
            s_log.log(Level.SEVERE, sql.toString(), sqle.getMessage());
        }finally {
        	DB.close(rs, pstmt);
        }
        return retValue;
    }

}
