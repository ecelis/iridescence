package org.compiere.model;

import java.util.Properties;

/**
 * Callouts para Lista de Instituciones
 *
 * <b>Fecha:</b> 03/Julio/2006<p>
 * <b>Modificado: </b> $Author: mrojas $<p>
 * 
 *
 * @author Omar Torres
 
 */
public class CalloutDesbloqueaClas extends CalloutEngine {
    
    /**
     * Metodo para mostrar la clasificacion previa del paciente
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String desbloqueaClas (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
    {    	 String pantallaHj=null;    	     	 if(isCalloutActive() && ctx.getProperty("isValidaHoja")!=null)    		   return "";    	     	 setCalloutActive(true);    	     	 if(ctx.getProperty("isValidaHoja")!=null)    		  pantallaHj=ctx.getProperty("isValidaHoja").toString();    			 
    	if(value!=null && ( pantallaHj==null || pantallaHj.equals("N")))    	     	     ctx.setProperty("isDesbloqueaClas","Y");    	   	    	     	  ctx.remove("isValidaHoja");    	      	  setCalloutActive(false);    	      	
        return "";
    }    
    
    
    
    

   	
    
}