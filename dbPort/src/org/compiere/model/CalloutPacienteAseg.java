package org.compiere.model;

import java.math.BigDecimal;
import java.util.Properties;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Clase callout de la tabla EXME_PacienteAseg
 * @author rsolorzano
 * org.compiere.model.CalloutPacienteAseg.validateUnaAseg
 */
public class CalloutPacienteAseg extends CalloutEngine{
	
	/**
	 * Metodo para validar que exista al menos una aseguradora principal
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return void
	 * @author rsolorzano
	 */
	public String validateMain(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		String msg = "";
		
		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			
			setCalloutActive(true);
			
			int exmePacienteID = 0;
			exmePacienteID = Integer.parseInt(String.valueOf(mTab.getValue("EXME_Paciente_ID")));
			boolean exists = MEXMEPacienteAseg.validateMain(ctx, exmePacienteID);

			if (mTab.getValue(MEXMEPacienteAseg.COLUMNNAME_IsMain) != null && mTab.getValue(MEXMEPacienteAseg.COLUMNNAME_IsMain).equals(true) && exists) {
				msg = Utilerias.getMessage(ctx, null, "msj.valida.asegPrincipal");
				mTab.setValue(MEXMEPacienteAseg.COLUMNNAME_IsMain, false);
			}
			
			setCalloutActive(false); // inactivamos el callout
		}
		
		return msg;
	}

	public String validateUnaAseg(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		String msg = "";
		
		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			
			setCalloutActive(true);
			
			int exmeSocioID = 0;
			if(mTab.getValue(X_EXME_PacienteAseg.COLUMNNAME_EXME_Paciente_ID)!=null)
				exmeSocioID = Integer.parseInt(String.valueOf(mTab.getValue(X_EXME_PacienteAseg.COLUMNNAME_EXME_Paciente_ID)));
			
			BigDecimal prioridad = Env.ONE; 
			if(value!=null && value instanceof BigDecimal)
				prioridad = (BigDecimal)value;
			
			int prio = 1;
			if(prioridad!=null)
				prio = prioridad.intValue();
			
			int idAsig = 0;
			if(mTab.getValue(X_EXME_PacienteAseg.COLUMNNAME_EXME_PacienteAseg_ID)!=null)
				idAsig = Integer.parseInt(mTab.getValue(X_EXME_PacienteAseg.COLUMNNAME_EXME_PacienteAseg_ID).toString());
			
			
			LabelValueBean exists =  MEXMEPacienteAseg.validatePrioridad(ctx, exmeSocioID, prio,
					idAsig);

			if(exists!=null){
				int mains = 0;
				if(exists.getLabel()!=null)
					mains = Integer.valueOf(exists.getLabel());

				int equals = 0;
				if(exists.getValue()!=null)
					equals = Integer.valueOf(exists.getValue());

				if((equals>1 && idAsig>0) || (equals>0 && idAsig<=0))
					msg = Utilerias.getMessage(ctx, null, "msj.valida.asegPrincipal");

				if((mains>1 && idAsig>0) || (equals>0 && idAsig<=0))
					msg = Utilerias.getMessage(ctx, null, "msj.valida.asegPrincipal");
			}
				
			setCalloutActive(false); // inactivamos el callout
		}
		
		return msg;
	}

}
