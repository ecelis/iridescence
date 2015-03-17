package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMENotasTrabajador extends X_EXME_Notas_Trabajador{
	
	 /** Static Logger */
//    private static CLogger s_log = CLogger.getCLogger(MEXMEPacienteTS.class);

	/**
	 * 
	 * @param ctx
	 * @param EXME_Notas_Trabajador_ID
	 * @param trxName
	 */
	public MEXMENotasTrabajador(Properties ctx, int EXME_Notas_Trabajador_ID, String trxName) {
		super(ctx, EXME_Notas_Trabajador_ID, trxName);
	}	
	
	 /**
     * @param ctx
     * @param rs
     */
    public MEXMENotasTrabajador(Properties ctx, ResultSet rs, String trx) {
        super(ctx, rs, trx);
    }

	/**
	 * 
	 */
	private static final long serialVersionUID = -7291647688314770934L;
	
	 /**
     *  Before Save
     *  @param newRecord new
     *  @return true
     *
    protected boolean beforeSave(boolean newRecord) {		
                
			if (newRecord) {				
				//Validaciones antes de guardar
			}else{}
        
		return true;
    }
    
    
    /**
     *  After Save
     *  @param newRecord new
     *  @param success success
     *  @return success
     *
    protected boolean afterSave (boolean newRecord, boolean success)
    {
        if (!success)
            return success;                               
        
        return true;
    }
    
    /**
     * Obtiene el registro de notas del trabajador por Id
     * @param ctx
     * @param EXME_Notas_Trabajador_ID
     * @param trxName
     * @return
     *
    public static MEXMENotasTrabajador getByPaciente(Properties ctx, int pacienteId, String trxName) {
        
        MEXMENotasTrabajador retValue = null;
        
        ResultSet rs = null;
        PreparedStatement psmt = null;
        String sql = null;
        
        try {
            sql = MEXMELookupInfo.addAccessLevelSQL(ctx,
                    "SELECT * FROM EXME_Notas_Trabajador WHERE isActive = 'Y' AND EXME_Paciente_ID = ?", 
                    "EXME_Notas_Trabajador");
            
            sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Notas_Trabajador");
            
            psmt = DB.prepareStatement(sql, trxName);
            psmt.setInt(1,pacienteId);
            
            rs = psmt.executeQuery();
            
            if(rs.next()){
                retValue = new MEXMENotasTrabajador(ctx, rs, trxName);
            }
            
        } catch (Exception e) {
            s_log.log(Level.SEVERE, "getById", e);
        } finally {
        	DB.close(rs, psmt);
            rs = null;
            psmt = null;
            sql = null;
        }

        return retValue;
    }*/
}
