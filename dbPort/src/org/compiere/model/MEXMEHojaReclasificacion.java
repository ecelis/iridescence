package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Msg;


/**
 * Modelo para Hoja de Reclasificacion
 *
 * <b>Fecha:</b> 13/Febrero/2006<p>
 * <b>Modificado: </b> $Author: otorres $<p>
 * <b>En :</b> $Date: 2006/10/03 00:20:43 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.3 $
 */
public class MEXMEHojaReclasificacion extends X_EXME_Hoja_Reclasificacion {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (MEXMEHojaReclasificacion.class);
    
    /**
     * @param ctx
     * @param ID
     */
    public MEXMEHojaReclasificacion(Properties ctx, int ID, String trx) {
        super(ctx, ID, trx);
    }

    /**
     * @param ctx
     * @param rs
     */
    public MEXMEHojaReclasificacion(Properties ctx, ResultSet rs, String trx) {
        super(ctx, rs, trx);
    }
    
    /**
     * Obtenemos la hoja de reclasificacion para un paciente 
     * @param ctx El contexto de la aplicacion
     * @param EXME_Paciente_ID El identificador del paciente
     * @param trxName El nombre de la transaccion 
     * @return La hoja de reclasificacion 
     */
    public static MEXMEHojaReclasificacion getByIndice(Properties ctx, int EXME_Paciente_ID, 
            String trxName){
        MEXMEHojaReclasificacion hoja = null;
        
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append(" SELECT * FROM EXME_Hoja_Reclasificacion WHERE IsActive = 'Y'");
        sql.append(" AND EXME_Paciente_ID = ? ");
                //"AD_Client_ID = ?  AND " +                
        
        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1 ");
        }
        
        sql.append(" ORDER BY CREATED DESC");
        
        if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        }
        
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            //pstmt.setInt(1, Env.getAD_Client_ID(ctx));
            pstmt.setInt(1, EXME_Paciente_ID);
            rs = pstmt.executeQuery();
        
            if (rs.next()) {
                hoja = new MEXMEHojaReclasificacion(ctx, rs, trxName);
            }
            
    	} catch (Exception e) {
    		log.log(Level.SEVERE, "MEXMEHojaReclasificacion.getByIndice - sql: " +
    				sql, e);
    	} finally {
			DB.close(rs, pstmt);
		}
        
        return hoja;
    }
    
    /**
     *  Before Save
     *  @param newRecord new
     *  @return true
     */
    protected boolean beforeSave (boolean newRecord)
    {
    	
        String errMsg = "";
        if(getEXME_Clas_Origen_ID()==getEXME_Clas_Destino_ID()) {
            errMsg = "SaveErrorMismaClas";
            log.saveError("Error", Msg.getMsg(getCtx(), errMsg)); 
            return false;
        }

         if(this.getTipo_Rclas().equals("D"))
         {
             MEXMEPreReclas preR= MEXMEPreReclas.getByPaciente( this.getCtx(),this.getEXME_Paciente_ID(),null);
        
            if(preR==null) {
                 errMsg = "SaveErrorPreClas";
               log.saveError("Error", Msg.getMsg(getCtx(), errMsg)); 
               return false;
            }
            
        }

        
        String usuario_ID =getCtx().getProperty("#AD_User_ID");
        X_AD_User usr= new X_AD_User(this.getCtx(),Integer.parseInt(usuario_ID),null);
        String msgC= "";
  	  
        if(newRecord && usr.getSupervisor_ID()==0)
        {
        	if(this.get_Value("Aprobada").equals(new Boolean(true)))
        	{
      		  msgC= "No tienes el nivel de permisos suficientes, no se guardar\u00E1 la aprobaci\u00F3n de la hoja de reclasificaci\u00F3n. \n Los dem\u00E1s datos si ser\u00E1n guardados";
      		  set_Value("Aprobada",new Boolean(false));
      		  JOptionPane.showMessageDialog(null,msgC,"AVISO !!!",JOptionPane.ERROR_MESSAGE);
    	     
        	}
        	
        	if(this.get_Value("Cancelada").equals(new Boolean(true)))
        	{
      		  msgC= "No tienes el nivel de permisos suficientes, no se guardar\u00E1 la aprobaci\u00F3n de la hoja de reclasificaci\u00F3n. \n Los dem\u00E1s datos si ser\u00E1n guardados";
      		  set_Value("Cancelada",new Boolean(false));
        	  JOptionPane.showMessageDialog(null,msgC,"AVISO !!!",JOptionPane.ERROR_MESSAGE);
    	     
        	}
        	
        	
        }
        else
    	if(((!this.get_Value("Aprobada").equals(this.get_ValueOld("Aprobada"))) ||
    			(!this.get_Value("Cancelada").equals(this.get_ValueOld("Cancelada")))) && 
    			usr.getSupervisor_ID()==0)
    	{
    		    	      	  
    	  if(!this.get_Value("Aprobada").equals(this.get_ValueOld("Aprobada")))
    	  {
    		  msgC= "No tienes el nivel de permisos suficientes, no se guardar\u00E1 la aprobaci\u00F3n de la hoja de reclasificaci\u00F3n. \n Los dem\u00E1s datos si ser\u00E1n guardados";
   		      set_Value("Aprobada",get_ValueOld("Aprobada"));
   		      JOptionPane.showMessageDialog(null,msgC,"AVISO !!!",JOptionPane.ERROR_MESSAGE);
    	  }
    	  
    	  

    	  
    	  if(!this.get_Value("Cancelada").equals(this.get_ValueOld("Cancelada")))
    	  {   
    		  msgC= "No tienes el nivel de permisos suficientes, no se guardar\u00E1 la cancelaci\u00F3n de la hoja de reclasificaci\u00F3n. \n Los dem\u00E1s datos si ser\u00E1n guardados";    		  
    		  JOptionPane.showMessageDialog(null,msgC,"AVISO !!!",JOptionPane.ERROR_MESSAGE);
    		    set_Value("Cancelada",get_ValueOld("Cancelada"));
    		  
    	  }

    			 

    	}
    	
    	  
  		if(get_Value("Cancelada").equals(new Boolean(true)))  		  
  		  set_Value("IsActive","N");
  		else   	    
  		 set_Value("IsActive","Y");
  		
    	
        if (errMsg.length() > 0)
        {
            log.saveError("Error", Msg.getMsg(getCtx(), errMsg)); 
            return false;
        }
        
        return true;
    }
    
    /**
     *  After Save
     *  @param newRecord new
     *  @param success success
     *  @return success
     */
    protected boolean afterSave (boolean newRecord, boolean success)
    {
        if (!success)
            return success;
     
        MEXMEHojaReclasificacionA aud = new MEXMEHojaReclasificacionA(this, get_TrxName());
        if (!aud.save(get_TrxName()))
            throw new IllegalStateException("Error al generar la auditoria");
        
        return true;
    }


}