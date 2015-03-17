package org.compiere.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.MessageResources;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.ExpertException;
import org.compiere.util.Utilerias;
import org.compiere.util.Validacion;

public class ValidaPaciente {

	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (ValidaPaciente.class);

	/** @ deprecated No hay referencias de codigo *
	public ActionErrors validateSave(Properties ctx, ActionErrors errors, 
			MEXMEPaciente paciente, 
			MConfigPre configPre, MConfigEC cec, MEXMEConfigDer configDer, MEXMEConfigFE configFE,
			String origen, String fechaNac, String rfcResp, String valueDeud, String fechaInicioLE, 
			String fechaRegistro, String fechaFin_Seg, String fechaIni_Seg,
			int  C_BPartner_ID, int diagnosticoID, 
			boolean esAsegurado, boolean configFe, boolean curpMandatory,
			boolean activo, boolean  isDerechohabiente, boolean isFactEspec, MessageResources msgs
	){
		try{

			//Configuraciones necesarias
			if (configPre == null)
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.configPre"));

			if (cec == null) 
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("consultaPac.error.deudor.req"));

			//Datos requeridos
			if(paciente.getName() == null || paciente.getName().trim().equals(""))
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("consultaPac.error.name.req"));

			if(paciente.getApellido1() == null || paciente.getApellido1().trim().equals(""))
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("consultaPac.error.apellido1.req"));

			if(paciente.getApellido2() == null || paciente.getApellido2().trim().equals(""))
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("consultaPac.error.apellido2.req"));

			if(paciente.getRfc() == null || paciente.getRfc().trim().equals(""))
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("consultaPac.error.rfc.req"));

			if(fechaNac == null)
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("consultaPac.error.fechaNac.req"));

			if(C_BPartner_ID <= 0 )
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("consultaPac.error.deudor.req"));

			if(valueDeud== null || valueDeud.trim().equals(""))
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("consultaPac.error.deudor.req"));

			if(esAsegurado == true && paciente.getC_BPartner_Seg_ID() <= 0)
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("consultaPac.error.aseguradora.req"));
			//lhernandez. Mantis #4408. Validar la direccion de la aseguradora al registrar un paciente. 
			if(esAsegurado && paciente.getC_BPartner_Seg_ID()>0){
				MEXMEBPartner socio = new MEXMEBPartner(ctx, paciente.getC_BPartner_Seg_ID(), null);
				MBPartnerLocation loc = null;
				try {
					loc = socio.getLocationPac();
				} catch (Exception e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("ingresoPac.error.save"));
				}
				if(loc==null){
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.factDirecta.facturar.noBpartnerLocationAseg"));
				}
			}//fin lhernandez

			if (esAsegurado && origen.equals("ambulatorio") && paciente.getDocumentoConvenio().trim().equals(""))
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("consultaPac.error.documentoConvenio.req"));

			if(!errors.isEmpty())
				return errors;

			//Fecha inicio y fecha final
			if(fechaIni_Seg!=null && !fechaIni_Seg.equals("") 
					&& fechaFin_Seg!=null && !fechaFin_Seg.equals("")
					&& !Validacion.validaFechas(fechaIni_Seg, fechaFin_Seg, errors).isEmpty())
				return errors;

			//Fecha de registro para el paciente, utilizado para vacunación
			if (fechaRegistro!=null && !fechaRegistro.equals("") 
					&& !Validacion.validaFecha(fechaRegistro, false, errors).isEmpty())
				return errors;

			//Valida Fecha de nacimiento
			if(!Validacion.validaFecha(fechaNac, false, errors).isEmpty())
				return errors;

			//validaciones cuando tenga configuracion de factura electronica .- Alejandro Garza
			if(configFe && !validaRFCCurp(ctx, errors, curpMandatory, paciente, rfcResp, configFE).isEmpty())
				return errors;

			if(!validaDerechohabiente(ctx, errors, isDerechohabiente,  configDer, paciente, msgs).isEmpty())
				return errors;
					
			//Se valida que el curp no exista para otro paciente..-Noelia: 
			if(curpMandatory && paciente.repiteCURP(ctx, paciente.getCurp(), paciente.get_TrxName()))
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.validacion.curpRepetido"));

			//Validaciones para inactivar un paciente. Alejandro.-
			if(paciente.getEXME_Paciente_ID()>0 && paciente.is_ValueChanged("IsActive") && !activo){
				try {
					MEXMEPaciente.validarPaciente(ctx,paciente.getEXME_Paciente_ID(), null);
				} catch (Exception e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(e.getMessage()));
				}
			}

			/*
			 * No puede haber dos pacientes con facturacion especial
			 * asociados a un mismo business partner
			 *

			//Asignar facturacion especial Si el paciente tiene facturacion especial
			if (isFactEspec){            	
				//Obtenemos un BPartner con el ID de la aseguradora del paciente
				MBPartner bPartnerSeg =  new MBPartner(ctx,paciente.getC_BPartner_Seg_ID(), null);

				if (!bPartnerSeg.isFactEspec())
					//Un paciente con facturacion especial no puede tener una aseguradora sin facturacion especial.
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("ingresoPaciente.error.SegNoFactEspec"));
				else if(paciente.factEspecAsegAsociada())
						//Verificamos que la aseguradora no tenga paciente asociado.
						//No puede haber dos pacientes con facturacion especial asociados a la misma aseguradora
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("ingresoPaciente.error.factEspec"));
			}	    

		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(e.getMessage()));
		}
		return errors;
	}*/

	/**
	 * Validacion del CURP y RFC apartir de un registro de la facturación
	 * electronica
	 * @param ctx
	 * @param errors
	 * @param curpMandatory
	 * @param paciente
	 * @param rfc1
	 * @param rfc2
	 * @param rfc3
	 * @return 
	 * @return
	 */
	public ActionErrors validaRFCCurp(Properties ctx, ActionErrors errors,
			boolean curpMandatory, 
			MEXMEPaciente paciente, 
			String rfcResp, MEXMEConfigFE cfe
	) throws ExpertException{

		//Solo se valida el RFC si existe configuracion de facturacion electronica.
		s_log.log(Level.INFO,"***** validaRFC *****");

		if(StringUtils.isNotBlank(rfcResp) && (rfcResp.length()<12 || rfcResp.length()>13 )){//msg.currentLenght
			throw new ExpertException(Utilerias.getLabel("error.validacion.longRfcResp",rfcResp,"12 or 13",String.valueOf(rfcResp.length())));      		        	
		}
		if(StringUtils.isNotBlank(paciente.getRFC_Fam()) &&	(paciente.getRFC_Fam().length()<12 || paciente.getRFC_Fam().length()>13 )){
			throw new ExpertException(Utilerias.getLabel("error.validacion.longRfcFam",paciente.getRFC_Fam(),"12 or 13",String.valueOf(paciente.getRFC_Fam().length())));    
		}
		
		if(cfe!=null){
			Validacion.isValido(paciente.getRfc(), cfe, Utilerias.getLabel("error.validacion.rfcPac"));			
			Validacion.isValido(rfcResp, cfe,Utilerias.getLabel("error.validacion.rfcResp"));			
			Validacion.isValido(paciente.getRFC_Fam(), cfe, Utilerias.getLabel("error.validacion.rfcFam"));			
			if(curpMandatory){
				Validacion.isValido(paciente.getCurp(), cfe,Utilerias.getLabel("error.validacion.caracCurp"));
			}
		}
		return errors;
	}

	/**
	 * Validaciones al grabar el paciente 
	 * considerado derechohabiente
	 * @param ctx
	 * @param errors
	 * @param isDerechohabiente
	 * @param configDer
	 * @param paciente
	 * @param msgs
	 * @return
	 */
	public ActionErrors validaDerechohabiente(Properties ctx, ActionErrors errors,
			boolean  isDerechohabiente,  MEXMEConfigDer configDer,
			MEXMEPaciente paciente, 
			MessageResources msgs
	) {

		Locale locale = Env.getLanguage(ctx).getLocale();

		if (configDer != null && configDer.isDerechohabiente() 
				&& configDer.isPermiteAltaD() && isDerechohabiente) {

			// Validar que el titular no venga nulo cuando sea derechohabiente .- GADC
			// Valida que se cuente con el parentesco del titular .- Lama
			// Se separan validaciones de titular y parentezco para mejor manejo de mensajes. Jesus Cantu.
			if (paciente.getTitular_ID() <= 0) // se llena desde mtto
				if (paciente.getTitular() == null || (paciente.getTitular() != null && paciente.getTitular().length() == 0)) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("ingresoPac.error.save", Utilerias.getMessage(ctx, locale,
							"ingresoPaciente.error.titular")));
				}

			if (paciente.getEXME_Parentesco_ID() <= 0) {
				errors.add(ActionErrors.GLOBAL_ERROR,
						new ActionError("ingresoPac.error.save", Utilerias.getMessage(ctx,locale, "ingresoPaciente.error.parentezco")));
			}
			if(!errors.isEmpty())
				return errors;

			boolean isValid = MEXMEPaciente.validateParent(ctx, paciente);

			if (isValid) {
				// Si el parentesco cambia , genera nuevamente la secuencia de la matricula
				if(paciente.is_ValueChanged("EXME_Parentesco_ID"))
					isValid = MEXMEPaciente.genValueFromParent(ctx, paciente);

				if (!isValid) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("ingresoPac.error.save", Utilerias.getMessage(ctx,locale,
					"ingresoPac.error.noHistoryValue")));
				}
			}
			else {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("ingresoPac.error.save", Utilerias.getMessage(ctx,locale,
				"ingresoPac.error.noParentHistory")));

			}
		}
		return errors;
	}
	
	/**
	 * Validaciones al grabar el paciente considerado derechohabiente
	 * @param ctx
	 * @param errors
	 * @param isDerechohabiente
	 * @param configDer
	 * @param paciente
	 * @return
	 */
	public List<String> validaDerechohabiente(Properties ctx, ActionErrors errors, boolean isDerechohabiente, MEXMEConfigDer configDer, MEXMEPaciente paciente) {
		return getErrores(ctx, validaDerechohabiente(ctx, errors, isDerechohabiente, configDer, paciente, null));
	}

	/**
	 * Validacion del CURP y RFC apartir de un registro de la facturacion electronica
	 * @param ctx
	 * @param errors
	 * @param curpMandatory
	 * @param paciente
	 * @param rfcResp
	 * @param cfe
	 * @return
	 * @throws ExpertException 
	 */
	public List<String> validaRFC_CURP(Properties ctx, ActionErrors errors, boolean curpMandatory, MEXMEPaciente paciente, String rfcResp, MEXMEConfigFE cfe) throws ExpertException {
		return getErrores(ctx, validaRFCCurp(ctx, errors, curpMandatory, paciente, rfcResp, cfe));
	}

	/**
	 * Regresa la lista de errores
	 * @param ctx
	 * @param errors
	 * @return
	 */
	public List<String> getErrores(Properties ctx, ActionErrors errors) {
		List<String> lstError = new ArrayList<String>();
		if (errors == null || errors.isEmpty())
			return lstError;
		Iterator<ActionError> iter = errors.get();
		while (iter.hasNext()) {
			ActionError error = iter.next();
			String errorStr = Utilerias.getMessage(ctx, null, error.getKey());
			Object[] params = error.getValues();
			if(params != null){
				for (int i = 0; i < params.length; i++) {
					Object value = params[i];
					if (errorStr.indexOf("{" + i + "}") > -1) {
						errorStr = errorStr.replace("{" + i + "}", value.toString());
					}
				}
			}
			lstError.add(errorStr);
		}
		return lstError;
	}
}
