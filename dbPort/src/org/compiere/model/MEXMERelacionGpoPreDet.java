/* Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.*/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

/**
 * Modelo de Relacion de Grupo de Precios creada el 9 de Agosto de 2007
 * 
 * @author Rodrigo Montemayor
 * @version 1.0 
 */
public class MEXMERelacionGpoPreDet extends X_EXME_RelacionGpoPreDet {
	private static final long serialVersionUID = 1L;
    /**
     * Load Constructor
     *
     * @param ctx context
     * @param rs result set
     * @param trxName the transaction name
     */
    public MEXMERelacionGpoPreDet (Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    public MEXMERelacionGpoPreDet (Properties ctx, int EXME_RelacionGpoPreDet_ID, String trxName) {
        super (ctx, EXME_RelacionGpoPreDet_ID, trxName);
    }
    
    
    protected boolean beforeSave(boolean newRecord) {
        
        if (newRecord) {
            setLineNo(getNextLineNumber());
        }
        
        return true;
    }
    
    private Integer getNextLineNumber() {
        
        Integer lineNumber = new Integer(1);
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder sql = null;
        try{
            sql = new StringBuilder(" Select LineNo From EXME_RelacionGpoPreDet")
                            .append(" where EXME_RelacionGpoPre_ID = ").append(getEXME_RelacionGpoPre_ID());
            
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), "EXME_RelacionGpoPreDet"));
            
            
            sql.append(" order By LineNo Desc ");
                  
             pstmt = DB.prepareStatement(sql.toString(), null);
             rs = pstmt.executeQuery();
             if (rs.next()) {
                 lineNumber = rs.getInt("LineNo");
                 lineNumber++;
             }
             
            
        } catch (Exception e) {
            return null;
        } finally {
        	DB.close(rs, pstmt);
        }

        return lineNumber;
    }
    
}
