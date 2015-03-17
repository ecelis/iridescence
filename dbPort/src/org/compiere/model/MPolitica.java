package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

public class MPolitica extends X_EXME_Politica{

    public MPolitica(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public MPolitica(Properties ctx, int EXME_Politica_ID, String trxName) {
        super(ctx, EXME_Politica_ID, trxName);
    }
    
    
    /**
     *  Obtenemos el sobreprecio (%) de un tipo de producto.
     *
     *@param  ctx
     *@param  whs_pide_ID
     *@param  whs_surte_ID
     *@param  tipo_Producto_ID  Description of the Parameter
     *@return
     */
    public static BigDecimal getSobrePrecio(Properties ctx, int whs_pide_ID, 
            int whs_surte_ID, int tipo_Producto_ID, String trxName) {
        
        //variable string con la sentencia sql
        String sql = null;
        
        //PreparedStatement con la sentencia sql
        PreparedStatement pstmt = null;
        
        //ResultSet utilizado para manipular los resultados
        ResultSet rs = null;

        BigDecimal sobrePrecio = Env.ZERO;
       // String aplicaPorc = null;

        
        // Revisamos si el tipo de producto se encuentra como EXCLUIDO para el sobreprecio.
        sql = " SELECT EXME_Politica.Porcentaje, pd.AplicaPorc FROM EXME_Politica  " +
                " LEFT JOIN EXME_PoliticaDet pd ON (EXME_Politica.EXME_Politica_ID = pd.EXME_Politica_ID AND pd.EXME_TipoProd_ID = ? " +
                " AND pd.AplicaPorc = 'N' ) " +
                " WHERE EXME_Politica.IsActive = 'Y' " +
                " AND EXME_Politica.WhsSolicita = ? AND EXME_Politica.WhsSurte = ? ";

         sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Politica");
         
        try {

            pstmt = DB.prepareStatement(sql, trxName);
            
            pstmt.setInt(1, tipo_Producto_ID);
            pstmt.setInt(2, whs_pide_ID);
            pstmt.setInt(3, whs_surte_ID);
            

            rs = pstmt.executeQuery();

            if (rs.next()) {

                sobrePrecio = rs.getBigDecimal(1);
                //aplicaPorc = rs.getString(2);

                if (rs.wasNull()) {
                    rs.close();
                    pstmt.close();
                    // Si no existe relacion con el tipo de producto (como EXCLUIDO).
                    // No se excluye.
                    return sobrePrecio;
                }
                // Los excluidos es cero.
                sobrePrecio = Env.ZERO;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            pstmt = null;
            sql = null;
            rs = null;
        }

        return sobrePrecio;
    }
}
