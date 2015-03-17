package org.compiere.model;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Callouts para Lista de Instituciones
 *
 * <b>Fecha:</b> 03/Julio/2006<p>
 * <b>Modificado: </b> $Author: mrojas $<p>
 * 
 *
 * @author Omar Torres
 
 */
public class CalloutListaInstitucion extends CalloutEngine {
    
    /**
     * Metodo para mostrar la clasificacion previa del paciente
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String listaInstitucion (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
    {
        Integer EXME_Institucion_ID =  null;
		if(value instanceof BigDecimal)
			EXME_Institucion_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			EXME_Institucion_ID = (Integer)value;
        
		if (EXME_Institucion_ID == null || EXME_Institucion_ID.intValue() == 0)
			return "";
		
        String msg = "";
        
        if (isCalloutActive() || value == null)
            return "";
        
        setCalloutActive(true);
        
        X_EXME_Institucion i = new X_EXME_Institucion(ctx,EXME_Institucion_ID.intValue(),null);
        
        String lista = null;
        
        try {
        	lista = ctx.getProperty (WindowNo+"|ListaInstitucion");
        }catch(Exception e){
		}       
        	
        StringTokenizer t = null;
        
        if(lista != null)
            t = new StringTokenizer(lista, ", ");
        
        boolean bandera = true;
        String descripcion = i.getDescription();
        
        if(descripcion.equals("(NINGUNA)"))
             mTab.setValue("ListaInstitucion", "");
       	else {       
           while(lista != null && t.hasMoreTokens()) {	
                 String token = t.nextToken();
                 if(token.equals(descripcion)) {
                       bandera = false;
                 }
              }  
           
            if(bandera)
            	mTab.setValue("ListaInstitucion", lista!=null && lista.length()>0? lista  + ", " + i.getDescription():i.getDescription());
       	}
        
        setCalloutActive(false);
                
        return msg;
    }    

    
    public String listaInst (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
    {
        String msg = "";
        
        if(isCalloutActive())
        	return "";
        
        if(value == null){
        	mTab.setValue("ListaInstitucion","");
        	return "";
        }
        
        setCalloutActive(true);
        
        //Cambios de Derechohabiencia para INP, ticket 621
        //Jesus Cantu, Marzo 11 2009.
        String descripcion = null;
        descripcion=MRefList.getListName(ctx, X_EXME_Paciente_TS.INSTITUCION_AD_Reference_ID, (String)value);
        // Fin cambios Jesus.
        
        if(descripcion.equals("(NINGUNA)"))
        	mTab.setValue("ListaInstitucion", "NINGUNA");
        else{     
    	    mTab.setValue("ListaInstitucion", descripcion);
        }
        
        setCalloutActive(false);
        
        return msg;
    }     
    
}