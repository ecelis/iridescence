package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEEncuesta extends X_EXME_Encuesta {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3734889022282648349L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEEncuesta.class);

	/**
	 * Constructor de MEXMEEncuesta
	 * @param ctx                Propiedades
	 * @param EXME_Encuesta_ID
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMEEncuesta(Properties ctx, int EXME_Encuesta_ID, String trxName) {
		super(ctx, EXME_Encuesta_ID, trxName);
	}
	
	/**
	 * Constructor de MEXMEEncuesta
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMEEncuesta(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
    
    
    public static MEXMEEncuesta getOfPaciente(Properties ctx, int pacienteId, String trxName) {
        String sql = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MEXMEEncuesta encuesta = null;
        try{
            sql = "SELECT * FROM EXME_Encuesta WHERE IsActive = 'Y' AND EXME_Paciente_ID = " + pacienteId;
            
            sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Encuesta");
            
            pstmt = DB.prepareStatement(sql, trxName);
            
            rs = pstmt.executeQuery();
            if (rs.next()){
                encuesta = new MEXMEEncuesta(ctx, rs, trxName);
            }
        }
        catch(Exception e){
            s_log.log(Level.SEVERE, sql.toString(), e);
        }
        finally{
        	DB.close(rs,pstmt);
        }

        return encuesta;
    }
    
	
	public static MEXMEEncuesta[] getEncuestas(Properties ctx, String trxName) {
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MEXMEEncuesta> resultados = new ArrayList<MEXMEEncuesta>();
			try{
				sql = "SELECT * FROM EXME_Encuesta WHERE IsActive = 'Y'";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Encuesta");
				
				pstmt = DB.prepareStatement(sql, trxName);
				
				rs = pstmt.executeQuery();
				while (rs.next()){
					MEXMEEncuesta encuestas = new MEXMEEncuesta(ctx, rs, trxName);
					resultados.add(encuestas);
				}
			}
			catch(Exception e){
	    		s_log.log(Level.SEVERE, sql.toString(), e);
			}
			finally{
				DB.close(rs,pstmt);
			}
			MEXMEEncuesta[] reenviar = new MEXMEEncuesta[resultados.size()];
			resultados.toArray(reenviar);
			return reenviar;
	    }
	    
}
