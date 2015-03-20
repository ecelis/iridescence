package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author Asael Sepulveda
 */
public class MEXMEVentaSuspendidaDet extends X_EXME_VentaSuspendidaDet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMEVentaSuspendidaDet.class);
    
    /**
     * @param ctx
     * @param EXME_VentaSuspendida_ID
     * @param trxName
     */
    public MEXMEVentaSuspendidaDet(Properties ctx, int EXME_VentaSuspendidaDet_ID, String trxName) {
        super(ctx, EXME_VentaSuspendidaDet_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEVentaSuspendidaDet(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * getByVentaSuspendidaId
     * 
     * Devuelve un ArrayList con el detalle de una venta suspendida dado su ID.
     * 
     * @param ctx
     * @param EXME_VentaSuspendida_ID	ID de la venta suspendida cuyo detalle se recuperar�
     * @param whereClause				Para agregar condiciones extras al query
     * @param orderBy					Para ordenar el resultado
     * @param trxName
     * @return
     */
    public static ArrayList <MEXMEVentaSuspendidaDet> getByVentaSuspendidaId(Properties ctx, int EXME_VentaSuspendida_ID, String whereClause, String orderBy, String trxName) {

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	
        ArrayList<MEXMEVentaSuspendidaDet> retValue = null;

        sql.append("SELECT * FROM EXME_VentaSuspendidaDet WHERE EXME_VentaSuspendida_ID =? AND isActive = 'Y' ");
        
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_VentaSuspendidaDet"));
        
        if (whereClause != null) {
        	sql.append(whereClause);
        }
        
        if (orderBy != null) {
        	sql.append("order By ").append(orderBy);
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = DB.prepareStatement (sql.toString(), trxName);
            pstmt.setInt(1, EXME_VentaSuspendida_ID);
            rs = pstmt.executeQuery ();
            
            retValue = new ArrayList<MEXMEVentaSuspendidaDet>();
            
            while (rs.next ()) {
                retValue.add(new MEXMEVentaSuspendidaDet(ctx, rs, trxName));
            }

        } catch (SQLException sqle) {
			s_log.log(Level.SEVERE, sql.toString(), sqle);
		}finally {
			DB.close(rs, pstmt);
		}
        return retValue;
    }
    
    /**
     * getByVentaSuspendidaId
     * 
     * Devuelve un ArrayList con el detalle de todas las ventas suspendidas.
     * 
     * @param ctx
     * @param EXME_VentaSuspendida_ID	ID de la venta suspendida cuyo detalle se recuperar�
     * @param whereClause				Para agregar condiciones extras al query
     * @param orderBy					Para ordenar el resultado
     * @param trxName
     * @return
     */
    public static ArrayList<MEXMEVentaSuspendidaDet> getAll(Properties ctx, String whereClause, String orderBy, String trxName) {

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	
        ArrayList<MEXMEVentaSuspendidaDet> retValue = null;

        sql.append("SELECT * FROM EXME_VentaSuspendidaDet WHERE isActive = 'Y' ");
        
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_VentaSuspendidaDet"));
        
        if (whereClause != null) {
        	sql.append(whereClause);
        }
        
        if (orderBy != null) {
        	sql.append("order By ").append(orderBy);
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = DB.prepareStatement (sql.toString(), trxName);
            rs = pstmt.executeQuery ();
            
            retValue = new ArrayList<MEXMEVentaSuspendidaDet>();
            
            while (rs.next ()) {
                retValue.add(new MEXMEVentaSuspendidaDet(ctx, rs, trxName));
            }

        } catch (SQLException sqle) {
			s_log.log(Level.SEVERE, sql.toString(), sqle);
		}finally {
			DB.close(rs, pstmt);
		}
        return retValue;
    }
    
}
