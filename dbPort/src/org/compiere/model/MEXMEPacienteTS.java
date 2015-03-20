package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo de Paciente Trabajo Social
 * 
 * @author LLama
 * 
 */
public class MEXMEPacienteTS extends X_EXME_Paciente_TS {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
//    private static CLogger s_log = CLogger.getCLogger(MEXMEPacienteTS.class);
    
    /**
     * 
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEPacienteTS(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * 
     * @param ctx
     * @param EXME_Paciente_TS_ID
     * @param trxName
     */
    public MEXMEPacienteTS(Properties ctx, int EXME_Paciente_TS_ID, String trxName) {
        super(ctx, EXME_Paciente_TS_ID, trxName);
    }

//    private MEXMEPaciente m_paciente = null;

    /**
     * Actualiza la tabla de paciente, con los datos de trabajo social
     * 
     * @param newRecord
     * @param success
     *
    protected boolean afterSave(boolean newRecord, boolean success) {
    	if (!success){
			return success;
		}
        // Cuando en trabajo social se cambien datos del paciente se deberan ver reflejados
        // en el ingreso de paciente. Esto aplica si la institucion es de gobierno,
        // es decir, en la configuracion de EXME_configEC Privado = N
        MConfigEC configEC = MConfigEC.get(getCtx(), null);
        
        //System.out.println("configEC = " + configEC);

        if (configEC != null && !configEC.isPrivado()) {
        	//System.out.println("configEC is privado = " + configEC.isPrivado());

            if (m_paciente == null) {
                m_paciente = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());
            }

            m_paciente.setEXME_Nacionalidad_ID(getEXME_Nacionalidad_ID());
            if(getC_Location_Perm_ID()!=0)
            	m_paciente.setC_Location_ID(getC_Location_Perm_ID());
            m_paciente.setTelParticular(getTel_Perm());

            if (!m_paciente.save(get_TrxName())) {
                return false;
            }
        }
        return true;
    }*/
    
    /**
     * Obtiene el registro de trabajo social para un paciente
     * @param ctx
     * @param EXME_Paciente_ID
     * @param trxName
     * @return
     *
    public static MEXMEPacienteTS getFromPac(Properties ctx, int EXME_Paciente_ID, String trxName) {
        
        MEXMEPacienteTS retValue = null;
        //Correccion indices ttpr
        ResultSet rs = null;
        PreparedStatement psmt = null;
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append(" SELECT * FROM EXME_Paciente_TS WHERE ");
        sql= new StringBuilder( MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_Paciente_TS"))
        .append(" AND EXME_Paciente_ID = ? AND isActive = 'Y'  ");

        if(sql!=null)
        	s_log.log(Level.INFO, "sql", sql.toString());
        try {
        	
            psmt = DB.prepareStatement(sql.toString(), trxName);
            psmt.setInt(1,EXME_Paciente_ID);
            
            rs = psmt.executeQuery();
            
            if(rs.next()){
                retValue = new MEXMEPacienteTS(ctx, rs, trxName);
            }
            
        } catch (Exception e) {
            s_log.log(Level.SEVERE, "getFromPac", e);
        } finally {
			DB.close(rs, psmt);
		}

        return retValue;
    }*/
    
    /*protected boolean beforeSave(boolean newRecord) {
    	System.out.println("Entering beforeSave in MEXMEPacienteTS - Chuy Cantu");
    	boolean blResult = true; 
    	MEXMEPaciente objlPac = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());
    	
    	objlPac.setEXME_Nacionalidad_ID(getEXME_Nacionalidad_ID());
    	
    	if (objlPac.save(get_TrxName())) {
    		
    	} else {
    		s_log.log(Level.SEVERE, "Error al actualizar el PacienteTS", "No se pudo salvar, clase MEXMEPacienteTS.beforeSave()");
    		blResult=false;
    	}
    	
    	return blResult;
    }*/

}
