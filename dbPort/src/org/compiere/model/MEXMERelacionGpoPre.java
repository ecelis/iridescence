/* Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.*/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Modelo de Relacion de Grupo de Precios creada el 9 de Agosto de 2007
 * 
 * @author Rodrigo Montemayor
 * @version 1.0 
 */
public class MEXMERelacionGpoPre extends X_EXME_RelacionGpoPre {
    
    /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	@SuppressWarnings("unused")
	private static CLogger      s_log = CLogger.getCLogger (MEXMERelacionGpoPre.class);
    
    /**
     * Load Constructor
     *
     * @param ctx context
     * @param rs result set
     * @param trxName the transaction name
     */
    public MEXMERelacionGpoPre (Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    public MEXMERelacionGpoPre (Properties ctx, int EXME_RelacionGpoPre_ID, String trxName) {
        super (ctx, EXME_RelacionGpoPre_ID, trxName);
    }
    
    /**
     * Obtener los registros de relacion de grupo de precios ordenados por value (linea)
     * @return
     */
    public static MEXMERelacionGpoPre[] getRelations(Properties ctx, String trxName) {
        
        List<MEXMERelacionGpoPre> relations = new ArrayList<MEXMERelacionGpoPre>();
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder sql = null;
        try {
			sql = new StringBuilder("SELECT * FROM EXME_RelacionGpoPre WHERE IsActive = 'Y' ");

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_RelacionGpoPre"));
			sql.append(" ORDER BY Value ASC");

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				relations.add(new MEXMERelacionGpoPre(ctx, rs, trxName));
			}
			
            
        } catch (Exception e) {
            return null;
        } finally {
        	DB.close(rs, pstmt);
        }

        MEXMERelacionGpoPre[] retValue = new MEXMERelacionGpoPre[relations.size()];
        relations.toArray(retValue);
        return retValue;
    }
    

    /**
     * Obtener la suma de importes de producto segun una relacion de grupo de precios y una factura.
     * @return
     */
    public static BigDecimal getImporte(Properties ctx, int C_Invoice_ID, int EXME_RelacionGpoPre_ID, String trxName) {
        
        BigDecimal importe = Env.ZERO;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder sql = null;
        try{
            sql = new StringBuilder(" select sum(line.linenetamt) as net, sum(line.linetotalamt) as total ")
                            .append(" from EXME_RelacionGpoPreDet ")
                            .append(" left join exme_relaciongpopre rel on (rel.exme_relaciongpopre_id = EXME_RelacionGpoPreDet.exme_relaciongpopre_id) ")
                            .append(" left join m_product_category cat on (cat.m_product_category_id = EXME_RelacionGpoPreDet.m_product_category_id) ")
                            .append(" left join m_product prod on (prod.m_product_category_id = cat.m_product_category_id) ")
                            .append(" left join c_invoiceline line on (line.m_product_id = prod.m_product_id) ")
                            .append(" where line.c_invoice_id = ").append(C_Invoice_ID)
                            .append(" and rel.EXME_RelacionGpoPre_ID = ").append(EXME_RelacionGpoPre_ID);
            
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_RelacionGpoPreDet"));
                  
             pstmt = DB.prepareStatement(sql.toString(), null);
             rs = pstmt.executeQuery();
             if(rs.next()) {
                 if (rs.getBigDecimal("total")==Env.ZERO) {
                     importe = rs.getBigDecimal("net");
                 } else {
                     importe = rs.getBigDecimal("total");
                 }
                 if(importe==null) {
                     importe=Env.ZERO;
                 }
             }
          
            
        } catch (Exception e) {
            return null;
        } finally {
        	DB.close(rs, pstmt);
        }

        return importe;
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
        try {
			sql = new StringBuilder("Select LineNo From EXME_RelacionGpoPre Order By LineNo Desc ");

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(),"EXME_RelacionGpoPre"));

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
