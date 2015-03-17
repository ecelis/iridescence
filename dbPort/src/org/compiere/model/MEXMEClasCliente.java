package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEClasCliente extends X_EXME_ClasCliente {
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEClasCliente.class);

	/**
	 * Constructor de MEXMEClasCliente
	 * @param ctx                Propiedades
	 * @param EXME_ClasCliente_ID
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMEClasCliente(Properties ctx, int EXME_ClasCliente_ID, String trxName) {
		super(ctx, EXME_ClasCliente_ID, trxName);
	}
	
	 /**
     * Obtener la clasificacion por paciente
     * @param ctx El contexto de la aplicacion
     * @param EXME_ClasCliente_ID El paciente
     * @param trxName el nombre de la transaccion 
     * @return El clasCliente 
     */
    public static MEXMEClasCliente getById(Properties ctx, int EXME_ClasCliente_ID,
			String trxName) {
		MEXMEClasCliente clas = null;

		String sql = "SELECT * FROM EXME_ClasCliente WHERE EXME_ClasCliente_ID = ? ";
        
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_ClasCliente");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
			pstmt = DB.prepareStatement(sql, trxName);
			//pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(1, EXME_ClasCliente_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				clas = new MEXMEClasCliente(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs,pstmt);
		}
        
        return clas;
    }
	
	/**
	 * Constructor de MEXMEClasCliente
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMEClasCliente(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMEClasCliente[] getClasClientes(Properties ctx, String trxName) {
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MEXMEClasCliente> resultados = new ArrayList<MEXMEClasCliente>();
			try{
				sql = "SELECT * FROM EXME_ClasCliente WHERE IsActive = 'Y'";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_ClasCliente");
				
				pstmt = DB.prepareStatement(sql, trxName);
				
				rs = pstmt.executeQuery();
				while (rs.next()){
					MEXMEClasCliente clasClientes = new MEXMEClasCliente(ctx, rs, trxName);
					resultados.add(clasClientes);
				}
			}
			catch(Exception e){
	    		s_log.log(Level.SEVERE, sql.toString(), e);
			}
			finally{
				DB.close(rs,pstmt);
			}
			MEXMEClasCliente[] reenviar = new MEXMEClasCliente[resultados.size()];
			resultados.toArray(reenviar);
			return reenviar;
	    }
	    
}
