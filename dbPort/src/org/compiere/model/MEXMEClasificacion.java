package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Modelo para Clasificacion
 *
 * <b>Fecha:</b> 08/Febrero/2006<p>
 * <b>Modificado: </b> $Author: otorres $<p>
 * <b>En :</b> $Date: 2006/09/13 23:06:21 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.8 $
 */
public class MEXMEClasificacion extends X_EXME_Clasificacion {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (MEXMEClasificacion.class);
    
    public static String ESTATUS_Initial="IN";
    public static String ESTATUS_Reclasified="RE";
    public static String ESTATUS_Updated="AC";
    public static String ESTATUS_Temp="TE";
    //private boolean cambioClasificacion=false;
    /**
     * @param ctx
     * @param ID
     */
    public MEXMEClasificacion(Properties ctx, int ID, String trx) {
        super(ctx, ID, trx);
    }

    /**
     * @param ctx
     * @param rs
     */
    public MEXMEClasificacion(Properties ctx, ResultSet rs, String trx) {
        super(ctx, rs, trx);
    }
    
    /**
     * Obtenemos la clasificacion por paciente
     * @param ctx El contexto de la aplicacion
     * @param EXME_Paciente_ID El paciente
     * @param trxName el nombre de la transaccion 
     * @return La clasificacion para un paciente 
     */
    public static MEXMEClasificacion getByPaciente(Properties ctx, int EXME_Paciente_ID,
			String trxName) {
		MEXMEClasificacion clas = null;

		String sql = "SELECT * FROM EXME_Clasificacion WHERE EXME_Paciente_ID = ? ";
        
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Clasificacion");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
			pstmt = DB.prepareStatement(sql, trxName);
			//pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				clas = new MEXMEClasificacion(ctx, rs, trxName);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
        
        return clas;
    }
    
    
    
    /**
     *  Before Save
     *  @param newRecord new
     *  @return true
     */
    protected boolean beforeSave(boolean newRecord) {

		String pantalla = getCtx().getProperty("isDesbloqueaClas");

		if (pantalla != null && pantalla.equals("Y")) {
			getCtx().setProperty("isDesbloqueaClas", "N");

			MEXMEEstatusClas st = new MEXMEEstatusClas(getCtx(), getEXME_Estatus_Clas_ID(), null);
			setEstatus(st.getEstatus());

			return true;

		} else {
			pantalla = null;
			pantalla = getCtx().getProperty("isModificaFechas");

			if (pantalla != null && pantalla.equals("Y")) {
				getCtx().setProperty("isModificaFechas", "N");
				return true;
			}
		}
        
        MEXMEEstatusClas s = new MEXMEEstatusClas(getCtx(), this.getEXME_Estatus_Clas_ID(), null);

		if (s != null) {
			setEstatus(s.getEstatus());
		}

		//asignar la fecha de vigencia del estudio
		MEXMEVigencia vig = MEXMEVigencia.getByADClient(getCtx(), get_TrxName());

		if (vig != null) {

			Calendar cal = Calendar.getInstance();

			if (isEsDeConvenio()) {
				setEXME_ClasCliente_ID(MClasCliente.getEXME_ClasCliente_ID(getCtx(),"CONV"));
			}

			if (newRecord) {
				cal.setTime(getDateOrdered());
				cal.add(Calendar.YEAR, vig.getAnios_Vigencia());
				setDateVaidTo(new Timestamp(cal.getTime().getTime()));

				setFecha_Ult_Act(this.getCreated());

				if (getEstatus().equals(ESTATUS_Temp)) {
					setIsLocked("Y");
					setEXME_Vigencia_ID(vig.getEXME_Vigencia_ID());

				} else {

                setEstatus(ESTATUS_Initial);
                setEXME_Estatus_Clas_ID(	MEXMEEstatusClas.getEXME_Estatus_Clas_ID(getCtx(),ESTATUS_Initial));
                setIsLocked("Y");
                setEXME_Vigencia_ID(vig.getEXME_Vigencia_ID());
				}
			} 
			/*else if (get_ValueOld("EXME_Estatus_Clas_ID") == get_Value("EXME_Estatus_Clas_ID")) {
				setIsLocked("Y");
				return true;
			} else if (getEstatus().equals(ESTATUS_Updated)) {
				if (getDateVaidTo().before(cal.getTime())) {

					setDateOrdered(new Timestamp(cal.getTime().getTime()));
					cal.setTime(getDateOrdered());
					cal.add(Calendar.YEAR, vig.getAnios_Vigencia());
					setDateVaidTo(new Timestamp(cal.getTime().getTime()));
					setEstatus(ESTATUS_Updated);
					setEXME_Estatus_Clas_ID(MEXMEEstatusClas.getEXME_Estatus_Clas_ID(getCtx(),ESTATUS_Updated));
					setIsLocked("Y");
					setEXME_Vigencia_ID(vig.getEXME_Vigencia_ID());
				} else {
					setEstatus(get_ValueOld("Estatus").toString());
					setEXME_Estatus_Clas_ID(Integer.parseInt(get_ValueOld("EXME_Estatus_Clas_ID").toString()));
					setIsLocked("Y");
					log.saveError("Error","No es Posible Guardar el Estatus a Actualizado. No ha trascurrido el lapso de tiempo necesario para poder actualizarlo");
					return false;

				}
			} else if (getEstatus().equals(String.valueOf(ESTATUS_Reclasified))) {

				//validar que exista la hoja de reclasificacion
				MEXMEHojaReclasificacion hoja = MEXMEHojaReclasificacion.getByIndice(getCtx(),
						getEXME_Paciente_ID(), get_TrxName());

				String errMsg = "";
				if (hoja == null) {
					errMsg = "SaveErrorNoHojaReclas";
				} else {
					if (hoja.isCancelada())
						errMsg = "SaveErrorHojaReclasCanc";

					if (!hoja.isAprobada())
						errMsg = "SaveErrorHojaReclasNoAprob";

					if (!hoja.getTipo_Rclas().equalsIgnoreCase(
							MEXMEHojaReclasificacion.TIPO_RCLAS_Definitive))
						errMsg = "SaveErrorHojaReclasNoDef";
				}

				if (errMsg.length() > 0) {
					setEstatus(ESTATUS_Initial);
					setEXME_Estatus_Clas_ID(MEXMEEstatusClas.getEXME_Estatus_Clas_ID(getCtx(),ESTATUS_Initial));
					setIsLocked("Y");
					log.saveError("Error", Msg.getMsg(getCtx(), errMsg));
					return false;
				} else {
					setEstatus(ESTATUS_Reclasified);
					setEXME_Estatus_Clas_ID(MEXMEEstatusClas.getEXME_Estatus_Clas_ID(getCtx(),ESTATUS_Reclasified));
					setIsLocked("Y");
				}

			} else if (getEstatus().equals(ESTATUS_Initial)) {
				if (get_ValueOld("Estatus").equals(ESTATUS_Temp)) {
					setEstatus(ESTATUS_Initial);
					setEXME_Estatus_Clas_ID(MEXMEEstatusClas.getEXME_Estatus_Clas_ID(getCtx(),ESTATUS_Initial));
					setAD_User_ID(Integer.parseInt(getCtx().getProperty("#AD_User_ID")));
					setIsLocked("Y");
				} else {
					setEstatus(get_ValueOld("Estatus").toString());
					setEXME_Estatus_Clas_ID(Integer.parseInt(get_ValueOld("EXME_Estatus_Clas_ID").toString()));
					setIsLocked("Y");
					log.saveError("Error", "No es Posible Guardar el Estatus a Inicial");
					return false;
				}

			} Comentado mientras definen validaciones!!!
				else if (getEstatus().equals(ESTATUS_Temp)) {
				setEstatus(get_ValueOld("Estatus").toString());
				setEXME_Estatus_Clas_ID(Integer.parseInt(get_ValueOld("EXME_Estatus_Clas_ID").toString()));
				setIsLocked("Y");
				log.saveError("Error", "No es Posible Guardar el Estatus a Temporal");
				return false;
			}*/
		}
		
		//si no cuenta con hoja de reclasificacion, y se elige el estatus de reclasificado, enviar error
		if(is_ValueChanged("Estatus") && getEstatus().equals(ESTATUS_Reclasified)){
			//ana
			MEXMEHojaReclasificacion hoja = MEXMEHojaReclasificacion.getByIndice(getCtx(), getEXME_Paciente_ID(), null);

			if (hoja == null ) {
				return false;
			} 

		}
        
        
        //Inicio RMontemayor.- Asignar la fecha de estudio 
        if (getDateOrdered() == null) {
            setDateOrdered(DB.getTimestampForOrg(getCtx()));
        }
		//Fin RMontemayor.- Asignar la fecha de estudio	
        
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
     
//        int bPartnerOld = 0;
        Locale locale = Env.getLanguage(getCtx()).getLocale();//Utilerias.getLocale(Env.getCtx());
//        ActionErrors errors = new ActionErrors();
        
        //asignamos al paciente el socio de negocios de EXME_ClasCliente_ID
        MClasCliente cc = new MClasCliente(getCtx(), getEXME_ClasCliente_ID(), get_TrxName());
            
        log.fine("Socio relacionado a la clasificacion: " + cc.getValue() + " - " + cc.getName()); 
            
        MEXMEPaciente pac = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());
//        bPartnerOld = pac.getC_BPartner_ID();
        pac.setC_BPartner_ID(cc.getC_BPartner_ID());
        if(!pac.save(get_TrxName()))
        	throw new IllegalStateException(Utilerias.getMessage(getCtx(), locale,"consultaPac.error.refreshBPartnerDeud"));
        
        //generamos la auditoria
        MEXMEClasificacionA aud = new MEXMEClasificacionA(this, get_TrxName());
        if (!aud.save(get_TrxName()))
            throw new IllegalStateException("Error al generar la auditoria");
        /*
		if( bPartnerOld!=pac.getC_BPartner_ID()){
			
			MConfigEC cec = MConfigEC.get(getCtx(), null);
			MConfigPre cp = MConfigPre.get(getCtx(), null);
			MEXMEMejoras mejora = MEXMEMejoras.get(getCtx(), null);
			
			if (cec != null && cec.isClasCliente() && cp!=null && mejora!=null) {
				
				//Alex: Actualizamos las extensiones segun el socio de la clasificacion..
				MCtaPac[] ctaPac = null;
				MEXMECtaPacExt[] extension = null;

				ctaPac = MCtaPac.getOfPaciente(getCtx(), pac.getEXME_Paciente_ID(),MCtaPac.ENCOUNTERSTATUS_Admission, null);
				if(ctaPac !=null && ctaPac.length >0){
					extension = MCtaPacExt.get(getCtx(), ctaPac[0].getEXME_CtaPac_ID()," AND EXME_CtaPacExt.isInvoiced <> 'Y' ", get_TrxName());			
					if (extension != null && extension.length >0) {
						for (int i = 0; i < extension.length; i++) {
							
							String error = pac.cambioSocio(extension[i]);
							if(error != null){
								throw new IllegalStateException(Utilerias.getMessage(getCtx(), locale, error));
							}
							if (!extension[i].save(get_TrxName())) {
								throw new IllegalStateException(Utilerias.getMessage(getCtx(), locale,"factExtension.error.refreshExtension"));
							}	
							
							
							try{								
								extension[i].cargarLineasDeExtension(false);
								extension[i].getTotales();
								//Actualizamos la extension, permitir el recalculo de precios Expert:aaranda 29102010
								errors = extension[i].calculoPrecios(errors, cp, cec, extension[i].isDiscountPrinted()?2:1, ctaPac[0], mejora, true, get_TrxName());
								if(!errors.isEmpty()){
									throw new IllegalStateException(Utilerias.getMessage(getCtx(), locale,"factExtension.error.refreshExtension"));
								}
							}catch (Exception e) {
								throw new IllegalStateException(Utilerias.getMessage(getCtx(), locale,"factExtension.error.refreshExtension"));
							}
						}
					}
				}
			}//Fin Alex
			
			//Noelia: Actualizamos las solicitudes de servicio de CE y las solicitudes de producto de CE y las Solicitudes de Servicio de ambulatorio			
			try{
				MEXMEActPacienteIndH.recalculoPreciosInd(getCtx(), pac, cec.isReqFactIndH(), get_TrxName());
			}catch (Exception e){
				throw new IllegalStateException(Utilerias.getMessage(getCtx(), locale, e.getMessage()));
			}//Fin Noelia
						
		}*/
		return true;
    }
}