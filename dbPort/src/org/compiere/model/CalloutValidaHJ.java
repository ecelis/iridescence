package org.compiere.model;

import java.sql.Timestamp;import java.text.SimpleDateFormat;import java.util.Properties;import javax.swing.JOptionPane;import org.compiere.util.Msg;/**
 * Callouts para Hoja de Reclasificacion
 *
 * <b>Fecha:</b> 20/Febrero/2006<p>
 * <b>Modificado: </b> $Author: mrojas $<p>
 * <b>En :</b> $Date: 2006/08/11 02:26:21 $<p>
 *
 * @author Omar Torres
 * @version $Revision: 1.3 $
 */
public class CalloutValidaHJ extends CalloutEngine {
    
    /**
     * Metodo para mostrar la clasificacion previa del paciente
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String validaHJ (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
    {    	        String msg = "";        if (value==null || mField.getOldValue()==mField.getValue())        {        	if (value==null && mField.getOldValue()==null )        	{        	        		mTab.setValue("Estatus", MEXMEClasificacion.ESTATUS_Initial);        		value= (Object)MEXMEEstatusClas.getEXME_Estatus_Clas_ID(ctx, MEXMEClasificacion.ESTATUS_Initial);        		mTab.setValue("EXME_Estatus_Clas_ID",  value);        		                		        	}        	return "";        }            MEXMEEstatusClas s= new MEXMEEstatusClas(ctx,Integer.parseInt(value.toString()),null);                value= (Object)s.getEstatus();                        if(value.equals(MEXMEClasificacion.ESTATUS_Temp) && mField.getOldValue()!=null && mField.getOldValue().equals(MEXMEClasificacion.ESTATUS_Initial))        {        	if(mField.getOldValue()!=null)        	    JOptionPane.showMessageDialog(null,"No es Posible Guardar el Estatus a Temporal","AVISO !!!",JOptionPane.ERROR_MESSAGE);        	        	  return "";        }	        	        if (value.equals(MEXMEClasificacion.ESTATUS_Updated))        {        SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");                java.util.Date fechaValida=   (Timestamp) mTab.getValue("DateVaidTo");        java.util.Date fechaSystem=   new java.util.Date();        try        {            if(formato.parse(formato.format(fechaSystem)).after(formato.parse(formato.format(fechaValida))))              {             	     ctx.setProperty("isValidaHoja","Y");        	     mTab.setValue("IsLocked", "N");              }            else              {        	    		    JOptionPane.showMessageDialog(null,"No es Posible Guardar el Estatus a Actualizado. No ha trascurrido el lapso de tiempo necesario para poder actualizarlo","AVISO !!!",JOptionPane.ERROR_MESSAGE);     		        		                }        }        catch(Exception e1)        {        	  JOptionPane.showMessageDialog(null,"No es Posible Guardar el Estatus a Actualizado. No ha trascurrido el lapso de tiempo necesario para poder actualizarlo","AVISO !!!",JOptionPane.ERROR_MESSAGE);	         }                       	              	   		  return "";        	        }        		        if(value.equals(MEXMEClasificacion.ESTATUS_Initial) && mField.getOldValue()!=null && mField.getOldValue().equals(MEXMEClasificacion.ESTATUS_Temp))        {        	  JOptionPane.showMessageDialog(null,"No es Posible Guardar el Estatus a Inicial","AVISO !!!",JOptionPane.ERROR_MESSAGE);        	        	  return "";          }                       if (value.equals(MEXMEClasificacion.ESTATUS_Reclasified ))        {                	        MEXMEHojaReclasificacion hoja = MEXMEHojaReclasificacion.getByIndice(                ctx, Integer.parseInt(mTab.getValue("EXME_Paciente_ID").toString()), null);                    String errMsg = "";             if(hoja==null)               {                errMsg = "SaveErrorNoHojaReclas";              }             else                    	             {                  if(hoja.isCancelada())                     errMsg = "SaveErrorHojaReclasCanc";                                   if(!hoja.isAprobada())                      errMsg = "SaveErrorHojaReclasNoAprob";                          if(!hoja.getTipo_Rclas().equalsIgnoreCase(MEXMEHojaReclasificacion.TIPO_RCLAS_Definitive))                     errMsg = "SaveErrorHojaReclasNoDef";              }                          if (errMsg.length() > 0)               {            	            		  JOptionPane.showMessageDialog(null,Msg.getMsg(ctx, errMsg),"AVISO !!!",JOptionPane.ERROR_MESSAGE);          		  return "";                                 }                    MEXMEHojaReclasificacion hj= MEXMEHojaReclasificacion.getByIndice(ctx, Integer.parseInt(mTab.getValue("EXME_Paciente_ID").toString()), null);          if(hj!=null)          {        	  if(hj.isAprobada()==true && hj.isCancelada()==false)        	  {        		      		        		  ctx.setProperty("isValidaHoja","Y");        		  mTab.setValue("IsLocked", "N");        		  setCalloutActive(false);        		          	  }        		          	            }
              
       }                       return msg;
    }
    
//	private int modificaBloqueo(String sql) {//		int num = 0;//		try {//			Statement state = DB.createStatement();//			num = state.executeUpdate(sql);//			state.close();//		} catch (Exception ex) {//		}//		return num;//	}
}