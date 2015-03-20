package org.compiere.model;

import java.math.BigDecimal;import java.util.Properties;

/**
 * Callouts para Hoja de Reclasificacion
 *
 * <b>Fecha:</b> 20/Febrero/2006<p>
 * <b>Modificado: </b> $Author: mrojas $<p>
 * <b>En :</b> $Date: 2006/08/11 02:26:21 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.4 $
 */
public class CalloutHoja extends CalloutEngine {
    
    /**
     * Metodo para mostrar la clasificacion previa del paciente
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String clasifPrevia (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
    {
        if (isCalloutActive() || value == null)
            return "";
        setCalloutActive(true);
        String msg = "";
        int clasOrig = 0;
        
        Integer EXME_Paciente_ID= null;		if(value instanceof BigDecimal)			EXME_Paciente_ID = Integer.valueOf(value.toString());		else if(value instanceof Integer)			EXME_Paciente_ID = (Integer)value;
        if(EXME_Paciente_ID!=null && EXME_Paciente_ID!=0) {
            //buscar la clasificacion original
            MEXMEClasificacion clas = MEXMEClasificacion.getByPaciente(ctx, EXME_Paciente_ID, null);
            
            CalloutPaciente  p= new CalloutPaciente(); 
            CalloutPaciente.setCalloutActive(false);
            p.nombrePaciente (ctx,  WindowNo,  mTab,  mField, value);
            
            if(clas==null)
                msg = "No existe clasificacion previa";
            else
                clasOrig = clas.getEXME_ClasCliente_ID();
        }
        
        if(clasOrig!=0)
           mTab.setValue("EXME_Clas_Origen_ID", clasOrig);
        
        setCalloutActive(false);
        return msg;
    }    
}