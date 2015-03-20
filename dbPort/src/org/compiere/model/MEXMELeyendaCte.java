package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMELeyendaCte extends X_EXME_LeyendaCte {
	private static final long serialVersionUID = 1L;

    /** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMELeyendaCte.class);
    
    public MEXMELeyendaCte(Properties ctx, int EXME_LeyendaCte_ID,
            String trxName) {
        super(ctx, EXME_LeyendaCte_ID, trxName);
    }

    public MEXMELeyendaCte(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Devolvemos una leyenda en base a la descripcion
     *  
     * @param ctx
     * @param documentNo
     * @param trxName
     * @return
     * @throws SQLException
     */
    public static MEXMELeyendaCte getByDescription(Properties ctx, String description, String trxName) 
    throws SQLException {
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MEXMELeyendaCte leyenda = null;

        try {
            //description = description.replaceAll("\\*", "%");//Lama: comodin estandar %

            sql.append("SELECT * FROM EXME_LeyendaCte WHERE UPPER(Description) LIKE UPPER(")
            .append(description).append(") ");
            
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_LeyendaCte"));

            pstmt = DB.prepareStatement(sql.toString(), trxName);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                leyenda = new MEXMELeyendaCte(ctx, rs, trxName);
            }

        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "getByDescription (" + sql + ")", e);
            throw e;
        } finally {
        	DB.close(rs,pstmt);
            rs = null;
            pstmt = null;
        }

        return leyenda;
    }
    
}
