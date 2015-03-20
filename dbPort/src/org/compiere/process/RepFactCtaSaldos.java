package org.compiere.process;

import java.util.HashMap;
import java.util.Properties;

import org.compiere.util.Env;

public class RepFactCtaSaldos extends SvrProcess{
    @SuppressWarnings("unused")
	private int			p_Ejercicio = 0;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		//ProcessInfoParameter[] para = getParameter();
//		for (int i = 0; i < para.length; i++)
//		{
//			log.log(Level.FINE, "prepare - " + para[i]);
//			String name = para[i].getParameterName();
//			if (para[i].getParameter() == null)
//				;
//			else if (name.equals("Ejercicio"))
//				p_Ejercicio = Integer.parseInt(para[i].getParameter().toString());
//			else
//				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
//		}
		
		// Los valores para Cliente y Organizacion ya vienen implicitos ver metodo viewReport() 
	}	//	prepare

	/**
	 * 	DoIt
	 *	@return Message
	 *	@throws Exception
	 */
	protected String doIt() throws Exception
	{
	    
		try{
			
			Properties ctx = Env.getCtx();
			// Agregar los atributos de la forma que habran de usarse para el reporte
			HashMap<String, Object> mParams = new HashMap<String, Object> (30);
			mParams.put("FechaIni", "01/04/2009");
			mParams.put("FechaFin", "01/04/2009");
			mParams.put("CtaPacIniValue", "0");
			mParams.put("CtaPacFinValue", "999999");
			mParams.put("documentIni", "0");
			mParams.put("documentFin", "999999");
			mParams.put("PFechaIni", "01/04/2009");
			mParams.put("PFechaFin", "01/04/2009");
			mParams.put("AD_Client_ID", String.valueOf(Env.getContextAsInt(ctx, "#AD_Client_ID")));
			mParams.put("AD_Org_ID", String.valueOf(Env.getContextAsInt(ctx, "#AD_Org_ID")));
			mParams.put("SessionId", String.valueOf(Env.getContextAsInt(ctx, "#AD_Session_ID")));
			
			//if(!repCtaPacForm.isActivo()) mParams.put("pSaldo", " and ts.saldo <> 0");
			
			// Executar proceso de consulta de saldos
			//MCtaPacDetCargos detCargos = new MCtaPacDetCargos();
			//String msgResult = detCargos.procDetCtaPac(ctx, mParams, false, false, true, false);
			
			//si regresa mensaje no se proceso la ctapac .- Lama
			//if(msgResult != null)
			//	addLog (0, null, null, "@Errors@ - "+ msgResult);
			
		}catch (Exception e) {
		  e.printStackTrace();
		  return "El Reporte no se pudo generar.";
        }
		
		return "Reporte Generado.";
	}
	
	
	
}

