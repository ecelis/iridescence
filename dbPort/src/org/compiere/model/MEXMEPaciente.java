/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.compiere.db.CConnection;
import org.compiere.util.CLogger;
import org.compiere.util.CalcularEdadAMD;
import org.compiere.util.ConfirmException;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.Env;
import org.compiere.util.ExpertException;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.QuickSearchTables;
import org.compiere.util.SecureEngine;
import org.compiere.util.Utilerias;
import org.compiere.util.WebEnv;
import org.compiere.util.confHL7.MessageGenerator;

import com.ecaresoft.util.PasswordHandler;

/**
 * Modelo de Pacientes
 * <b>Modificado: </b> $Author: taniap $<p>
 * <b>En :</b> $Date: 2006/11/02 21:34:31 $<p>
 *
 * @author Hassan Reyes
 * @version $Revision: 1.17 $
 */
public class MEXMEPaciente extends X_EXME_Paciente implements PatientRelData {

	/** serialVersionUID */
	private static final long serialVersionUID = 7557545749724765546L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEPaciente.class);
	/** */
	public static final String IMG_PROFILE = "imgProfile";
	/** */
//	public static final String EXME_Paciente_ID = COLUMNNAME_EXME_Paciente_ID;
	/** */
	public static final String COLUMNNAME_Historia = "Historia";
	/** UOM_WEIGHT = UOMWeight*/
	public static final String UOM_WEIGHT ="UOMWeight";
	/** UOM_WEIGHT = UOMHeight*/
	public static final String UOM_HEIGHT ="UOMHeight";
	/** */
	public static final String PERSONA_RESP = "persona_Resp";
	/** */
	public static final String ASEGURADORA = "aseguradora";
	/** */
	public static final String DEUDODOR_SOL = "deudor_Sol";
	/** */
//	public static final String PACIENTE = "paciente";
	/** */
	public static String[] patientCols = new String[] { MEXMEPaciente.COLUMNNAME_Value, MEXMEPaciente.COLUMNNAME_Nombre_Pac,
			MEXMEPaciente.COLUMNNAME_Sexo, MEXMEPaciente.COLUMNNAME_FechaNac, MEXMEPaciente.COLUMNNAME_SuffixNSS, MEXMEPaciente.COLUMNNAME_EMail,MEXMEPaciente.COLUMNNAME_TelParticular };
	/**
	 * Para WHistPrescription
	 * WPrescription
	 * WTreatmentsCtaPac
	 */
	public static String[] patient = new  String[]{MEXMEPaciente.COLUMNNAME_Value, MEXMEPaciente.COLUMNNAME_Nombre_Pac,
			MEXMEPaciente.COLUMNNAME_Imss, MEXMEPaciente.COLUMNNAME_Sexo, MEXMEPaciente.COLUMNNAME_FechaNac };
	/** */
	private boolean existePerfilPaciente = true;
	/** */
	private MConfigEC configEC = null;
	/** Objeto Regionalizacion para saber si se valida o no el telefono particular para Mexico
	 * Ya que esas validaciones las hace en la forma del paciente y aqui las repite para surescript
	 */
	//Jesus Cantu
	private MEXMEI18N objcReg = null;
	/** */
	private MEXMEBPartner bPartner = null;
	/** */
	public List<String> msgError = new ArrayList<String>();
	/** */
	private MEXMEHistExp histExp = null;
	/** Hoja Clinica de Promocion Militar (SEDENA)  Tipo de paciente  @author Lorena Lama */
	private String tipoPaciente = null; // Tipo de paciente //FIXME!!
	/** */
	private String sex = null;
	/** */
	private CalcularEdadAMD age = null;
	/** Trae el tipo de sangre usando solo el id del Paciente */
	private String tipoSangre = "";
	/** Direccion */
	private MBPartnerLocation mBPartnerLoc = null;
	/** direccion del paciente. */
	private MLocation mLocation = null;
	/** */
	private String nacionalidad = "";
	/** */
	private String grupoEtnico = "";
	/** */
	@SuppressWarnings("unused")
	private boolean createRoles = true;
	//lhernandez. mantis #6898
	/** se utiliza en la pantalla "Create Patient" llamada desde "Newborn Registration" */
	private boolean isNewborn = false;
	/** */
	private boolean validarMenorEdad =  true;
	/** */
	private String message = null;
	/** */
	private String race = "";
	/** */
	private List<BasicoTresProps> allergies = null;
	/** Datos de facturacion del paciente */
	private BeanDatosFacturacion datosFacturacion;

	/**
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 */
	public MEXMEPaciente(Properties ctx, int EXME_Paciente_ID, String trxName) {
		super(ctx, EXME_Paciente_ID, trxName);

		// Establecemos el valor por defecto de Pais de Nacimiento.
		if (getC_Country_Nac_ID() == 0) {
			MCountry defaultCountry = MCountry.getDefault(getCtx());
			setC_Country_Nac_ID(defaultCountry.getC_Country_ID());
		}
		// Establecemos el valor por defecto de Estado de Nacimiento.
		if (getC_Region_Nac_ID() == 0) {
			MRegion defaultRegion = MRegion.getDefault(getCtx());
			if (defaultRegion != null && defaultRegion.getC_Country_ID() == getC_Country_Nac_ID())
				setC_Region_Nac_ID(defaultRegion.getC_Region_ID());
		}
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPaciente(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 *
	 * @param object
	 * @return
	 */
	public static MEXMEPaciente copyFrom(MEXMEPaciente object) {
		MEXMEPaciente newObject = new MEXMEPaciente(object.getCtx(), 0, object.get_TrxName());
		copyValues(object, newObject);
		return newObject;
	}

	/**
	 * Retornamos el nombre completo del paciente.
	 * Apellido1 + Apellido2 + Appellido3 + Name + Nombre2
	 * @return Nombre completo del paciente.
	 */
	public String getFullName() {
		StringBuffer fullName = new StringBuffer();

		fullName.append((getApellido1() == null ? "" : getApellido1() + " "));
		fullName.append((getApellido2() == null ? "" : getApellido2() + " "));

		fullName.append((getName() == null ? "" : getName() + " "));
		fullName.append((getNombre2() == null ? "" : getNombre2() + " "));

		return fullName.toString();
	}

	/**
	 * Obtenemos le Socio de Negocios.
	 * que deberia ser uno a nivel de system
	 * @return
	 */
//	private MEXMEBPartner getBPartner() {
//		if (bPartner == null){
//			int socio = getC_BPartner_ID();
//
//			// socio
//			if (socio <= 0) {
//				return null;
//			}
//
//			bPartner = new MEXMEBPartner(getCtx(),socio, get_TrxName());
//		}
//		return bPartner;
//	}

	/**
	 * Obtenemos el responsable del paciente (C_BPartener_ID del paciente o guarantor)
	 * El socio esta a nivel System, por lo que no corresponde al de la cuenta paciente
	 * @return
	 */
	public MEXMEBPartner getPatientBPartner() {
//			if (iscopyResponsible()) {
//				return getBPartner();
//			} else {
//				// else guarantor (FIXME / TODO: institution / physician (MEXMEPacienteRel)
//				return getBPartner();
//			}

		if (bPartner == null){
			// en la bd
			int socio = getC_BPartner_ID();

			// socio
			if (socio <= 0) {
				// busca el de system
				socio = setC_BPartner_ID();
				if (socio <= 0) {
					return null;
				}
			}
			bPartner = new MEXMEBPartner(getCtx(),socio, get_TrxName());
		}
		return bPartner;
	}

//	/**
//	 * Obtenemos le Socio de Negocios de la Aseguradora si tiene asignada una.
//	 * @return
//	 */
//	public MEXMEBPartner getBPartnerSeg() {
//		if (getC_BPartner_Seg_ID() <= 0)
//			return null;
//		return new MEXMEBPartner(getCtx(), getC_BPartner_Seg_ID(), get_TrxName());
//	}

//	/**
//	 * Obtenemos le Socio de Negocios.
//	 * El socio de negocios del paciente esta a nivel de system
//	 * @return
//	 *         se quitagetC_BPartner_Location_ID(). lhernandez
//	 */
//	public MBPartnerLocation getBPartnerLoc() {
//		if (getC_BPartner_ID() <= 0)
//			return null;
//		return getLocationPac(); // Lama
//	}

//	/**
//	 * Obtenemos el Socio de Negocios en el siguiente orden.
//	 * 1.- Aseguradora, si esta asegurado.
//	 * 2.- Si No, Particular.
//	 * 3.- Si No, Null.
//	 * @return Socio de Negocios o null si error
//	 */
//	public MEXMEBPartner getCliente() {
//		if (isEsAsegurado()) {
//			return getBPartnerSeg();
//		}
//		return getBPartner();
//	}

	/**
	 * Obtenemos los pacientes a partir de ciertos criterios.
	 * @param ctx
	 * @param where
	 * @param all Indica si se retornan tambien inactivos o solo activos.
	 * @return
	 */
	public static MEXMEPaciente[] get(Properties ctx, String whereClause, String orderBy, String trxName, boolean all) {

		Properties prop = WebEnv.getXPT_Properties();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ArrayList<MEXMEPaciente> list = new ArrayList<MEXMEPaciente>();
		//RMontemayor.- 0=0, quito la validacion de facturacion especial ya que no se utiliza,
		//y como no se puede quitar el where por el isactive o por el addaccesslevelsql,
		//agrego el where 0=0
		sql.append("SELECT * FROM (SELECT * FROM EXME_Paciente WHERE  0=0 ");

		if (!all) {
			sql.append(" AND isActive = 'Y' ");
		}

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));

		if (whereClause != null)
			sql.append(whereClause);

		if (orderBy != null && orderBy.length() > 0)
			sql.append(" ORDER BY ").append(orderBy);
		else
			sql.append(" ORDER BY EXME_Paciente_ID DESC ");

		// Limitamos el numero de registros regresados.
		if (DB.isOracle()) {
			sql.append(") WHERE ROWNUM <= ").append(prop.getProperty(WebEnv.NoRegistros));
		} else if (DB.isPostgreSQL()) {
			sql.append(") AS paciente ");
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1,
					Integer.parseInt(prop.getProperty(WebEnv.NoRegistros))));
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			// pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_Client_ID"));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEPaciente pac = new MEXMEPaciente(ctx, rs, trxName);
				list.add(pac);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMEPaciente.get - sql: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		//
		MEXMEPaciente[] pacs = new MEXMEPaciente[list.size()];
		list.toArray(pacs);
		return pacs;
	}

	/**
	 * Verifica si el CURP lo tiene algun otro paciente
	 * @param ctx
	 * @param curp
	 * @param trxName
	 * @return true El curp es de otro paciente
	 * @throws Exception
	 */
	public boolean repiteCURP(Properties ctx, String curp, String trxName) throws Exception {
		// Correccion indices ttpr
		boolean bandera = false;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_Paciente_ID FROM EXME_Paciente WHERE IsActive=IsActive ")//
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))//
			.append(" AND CURP = ? ")//
			.append(" AND EXME_Paciente_ID <> ? ")//
			.append(" ORDER BY EXME_Paciente_ID DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if (sql != null)
			s_log.log(Level.INFO, sql.toString());

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, curp);
			pstmt.setInt(2, getEXME_Paciente_ID());
			rs = pstmt.executeQuery();

			if (rs.next())
				bandera = true;

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
			throw new Exception("error.listaPrecios.excepcion");
		} finally {
			DB.close(rs, pstmt);
		}
		return bandera;
	}

	/**
	 * Retornamos la direccion donde vive el paciente.
	 * @return
	 */
	public MLocation getLocation() {
		if (mLocation == null
				|| mLocation.getC_Location_ID() == 0
				|| mLocation.getC_Location_ID() != getC_Location_ID()) {
			mLocation = new MLocation(getCtx(), getC_Location_ID(), get_TrxName());
		}
		return mLocation;
	}

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord) {
		//lhernandez. se comenta debido a que ya no se usan
		//String errMsg = "";
		//MBPartnerLocation bPLocation = null;

		if (getC_Location_ID() <= 0){
			return false;
		}

		setAD_Org_ID(0);// Pacientes - solo cliente
		// Lama: si el paciente es nuevo, reset AD_User a cero
		if (newRecord){
			setAD_User_ID(0);// user cero
		}

		format();// quitar espacios, cambiar a mayusculas

		if(configEC==null){
			configEC = MConfigEC.get(getCtx());
		}

		// ingreso de pacientes - si no viene de struts
		if(!savePatient(newRecord)){
			return false;
		}

		// validate email .- Lama
		if(configEC != null && configEC.isCreateUserPatient()){
			if (newRecord || is_ValueChanged(COLUMNNAME_EMail)) {
				if (!validateUniqueEmail(newRecord)) {
					//setEMail(null);
					// Lama: se guarda el warning para mostrar msj en mtto de ingreso pacientes. Mantis #0004698
					log.saveError(Utilerias.getMsg(getCtx(), "error.email.repeated"),getEMail());//
					return false;
				}
			}
		}
		//Ticket #0102101
//		if (isNSS()) {
//			setImss(null);
//			setSuffixNSS(null);
//		}
		if (StringUtils.isNotEmpty(getImss())) {
			String suff = null;
			if (getImss().length() >= 4) {
				int index = getImss().length() - 4;
				suff = StringUtils.substring(getImss(), index);
			} else {
				suff = getImss();
			}
			setSuffixNSS(StringUtils.leftPad(suff, 7, "."));
		}

//		CalcularEdadAMD age;
		if (getConfirmPanel() != null) {
			try {
//				age = CalcularEdadAMD.getEdadHrs(getCtx(), getFechaNac());
				if (getAge().getAnios() <= 18 && validarMenorEdad) {
					setMessage("confirm.responsible.age");
					throw new ConfirmException(Msg.getMsg(getCtx(), "confirm.responsible.age"));
					// The patient is younger than 18 years old.Do you want to continue ?
				}
			} catch (ConfirmException e) {
				throw e;
			} catch (Exception e) {
				s_log.log(Level.WARNING, "", e);
			}
		}

		//RQM 2434. No validar tel particular para Mexico, esto solo se ocupa
		//para surescript, se reviso con Jcarranza. Jesus Cantu.

		//Creamos el objeto regionalizacion.
		objcReg = MEXMEI18N.getFromCountry(getCtx(), Env.getC_Country_ID(getCtx()), null);

		// Si no es la forma de mexico la que se esta usando, entonces que valide el tel
		// particular para USA como normalmente se hace para surescript. Si es para Mexico
		// entonces que no se haga la validacion del particular. Jesus Cantu.
		if (!(objcReg != null && objcReg.ispatientFormMexico())) {

			if (!Ini.APP_MODE_DEMO.equals(Ini.getProperty(Ini.P_APPLICATION_MODE))) {
				if (newRecord || is_ValueChanged(COLUMNNAME_TelParticular)) {
					String msg = Utilerias.validateTelephone(getCtx(), getTelParticular());
					if (msg != null) {
						log.saveError(" ", msg);
						return false;
					}
					try {
						validatePhone(getTelParticular());
					} catch (Exception e) {
						log.saveError(" ", e.getMessage());
						return false;
					}
				}
			}
		} //termina if de patientform
		
		if(getC_Country_Nac_ID() <= 0){
			setC_Country_Nac_ID(Env.getC_Country_ID(getCtx()));
		}

		return true;
	} // beforeSave

	/**
	 * Validacion de telefono deacuerdo a estandares de ePrescribing
	 * <ul>
	 * <li>El telefono debe ser obligatorio</li>
	 * <li>El telefono no puede tener mas de 10 digitos</li>
	 * <li>Deben ser solo numeros</li>
	 * <li>No puede haber mas de 7 digitos repetidos</li>
	 * <li>El area del telefono (los primeros 3 digitos)  no puede tener 555</li>
	 * <li>El area (los primer digito)  no puede empezar con 0 o 1 </li>
	 * </ul>
	 * @param phone Numero de telefono
	 * @return true si cumplio las condiciones
	 * @throws Exception Excepcion con el mensaje de error apropiado dependiendo donde se encontro
	 */
	public static boolean validatePhone(String phone) throws Exception{
		int p;		// Length of Telephone Number
		if(phone == null || phone.equals("")) { // El telefono debe ser obligatorio
			throw new Exception(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.homePhone"));
		} else {
			p = phone.length();
		}

		if (p != 10) { // El telefono no puede tener mas de 10 digitos
			throw new Exception(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.homePhoneDigit"));
		}

		if(!StringUtils.isNumeric(phone)){
			throw new Exception(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.homePhoneNumeric"));
		}

		if (MEXMEMedico.maximoRepetido(phone) > 7) { // No puede haber mas de 7 digitos repetidos en un numero telefonico
			throw new Exception(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.homePhoneRepeat"));
		}

		/** Validacion de su area */

		if (phone.startsWith("555")) { // El area del telefono no puede tener 555
			throw new Exception(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.homePhoneArea"));
		}

		if (phone.startsWith("0") || phone.startsWith("1")) { // El area no puede empezar con 0 o 1
			throw new Exception(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.homePhoneAreaStart"));
		}

		return true;
	}

	/**
	 * Validacion de fax de acuerdo a estandares de ePrescribing.
	 *
	 * <ul>
	 * <li>El fax debe ser obligatorio</li>
	 * <li>El fax no puede tener mas de 10 digitos</li>
	 * <li>Deben ser solo numeros</li>
	 * </ul>
	 * @param fax Numero de fax
	 * @return true si cumplio las condiciones
	 * @throws Excepcion con el mensaje de error apropiado dependiendo donde se encontro
	 */
	public static boolean validateFax(String fax) throws Exception {

		/** Validacion del Fax */

		int f;		// Length of Fax Number

		if (fax == null || fax.equals("")) { // El fax debe ser obligatorio
			throw new Exception(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.fax"));
		} else {
			f = fax.length();
		}

		if (f != 10) { // El fax no puede tener mas de 10 digitos
			throw new Exception(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.faxDigit"));
		}


		if (!StringUtils.isNumeric(fax)) {
			throw new Exception(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.faxNumeric"));
		}

		return true;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {

		if (!success){
			return success;
		}

		try {
			QuickSearchTables.checkTables(MEXMEPacienteExpedienteV.class,MEXMEPacienteExpedienteV.Table_Name, getEXME_Paciente_ID(), get_TrxName() , p_ctx);
//  		Si alguien ocupa la vista MEXMEPacienteCtaV y crea indices con subfijo descomentar lineas siguientes
//			QuickSearchTables.deleteIndexes(getCtx(), MEXMEPacienteCtaV.Table_Name, null);

		} catch (Exception ex) {
			log.log(Level.WARNING, "QuickSearchTables.checkTables", ex);
		}

		// Se regenera la vista EXME_Paciente_Cta_V solo para los campos que se muestran.
		if (newRecord
			|| is_ValueChanged(COLUMNNAME_Value)
			|| is_ValueChanged(COLUMNNAME_Name)
			|| is_ValueChanged(COLUMNNAME_Nombre2)
			|| is_ValueChanged(COLUMNNAME_Apellido1)
			|| is_ValueChanged(COLUMNNAME_Nombre_Pac)
			|| is_ValueChanged(COLUMNNAME_Sexo)
			|| is_ValueChanged(COLUMNNAME_Imss)
			|| is_ValueChanged(COLUMNNAME_EMail)
			|| is_ValueChanged(COLUMNNAME_TelParticular)
			|| is_ValueChanged(COLUMNNAME_FechaNac)){
			MEXMEPacienteCtaV.updateSearchPac(p_ctx, getEXME_Paciente_ID(), get_TrxName());

			// Solo cuando no sea nuevo registro y se hayan actualizado las columnas nombre
			if (!newRecord &&(is_ValueChanged(COLUMNNAME_Name)
			|| is_ValueChanged(COLUMNNAME_Nombre2)
			|| is_ValueChanged(COLUMNNAME_Apellido1))){
				updatePacienteRel();
			}
		}

		//Actualizar indice de EXME_CtaPac_V
		if(success && !newRecord && (is_ValueChanged(COLUMNNAME_Nombre_Pac)
				|| is_ValueChanged(COLUMNNAME_Name)
				|| is_ValueChanged(COLUMNNAME_Nombre2)
				|| is_ValueChanged(COLUMNNAME_Apellido1)
				|| is_ValueChanged(COLUMNNAME_Imss)
				|| is_ValueChanged(COLUMNNAME_EMail)
				|| is_ValueChanged(COLUMNNAME_TelParticular)
				|| is_ValueChanged(COLUMNNAME_FechaNac))) {
			MEXMECtaPacV.updateIndex(p_ctx, getEXME_Paciente_ID(), -1, get_TrxName());
		}

		// Si el paciente no es nuevo y su nombre cambio, debe actualizar
		if (!newRecord && is_ValueChanged(COLUMNNAME_Nombre_Pac)) {
			MEXMECtaPayer.checkIndex(p_ctx, getEXME_Paciente_ID(), get_TrxName());
		}

				//Crear los roles si ya es un usuario paciente
		if (getAD_User_ID() > 0){
			createUser(null);// update user ...
			if (createPatientRoles()) {
				if (newRecord) {
					s_log.saveWarning(Utilerias.getMsg(getCtx(), "msg.save.createUser"), getValue());
				}
			} else {
				return false;
			}
		}
		else {
			//Crear el usuario paciente desde el mantenimiento de ingreso paciente - Lama
			if (configEC != null && configEC.isCreateUserPatient()) {
				// creamos usuario
				if (MRole.validateFormRole(getCtx(), "com.ecaresoft.web.forms.WUserPatient", null)) {
					final String msg = createUser();
					if (msg.equals(EMail.SENT_OK)) {
						// Guardar el id del ad_user
						StringBuilder sb = new StringBuilder();
						sb.append(" UPDATE EXME_PACIENTE SET AD_USER_ID = ");
						sb.append(getAD_User_ID());
						sb.append(" WHERE EXME_PACIENTE_ID = ? ");
						DB.executeUpdate(sb.toString(), new Object[]{getEXME_Paciente_ID()}, false, get_TrxName());
						// Crearle los roles al usuario paciente
						if (createPatientRoles()) {
							s_log.saveWarning(Utilerias.getMsg(getCtx(), "msg.save.createUser"), getValue());
						} else {
							return false;
						}
					}
				}
			}
		}

		MEXMEI18N objcReg = MEXMEI18N.getFromCountry(getCtx(), Env.getC_Country_ID(getCtx()), null);
		boolean isFromPatientForm = objcReg != null && objcReg.ispatientFormMexico();
		if(!isFromPatientForm){
			MEXMEParentesco parentesco = MEXMEParentesco.get(getCtx(), Constantes.SELF_, null);
			if (parentesco == null || parentesco.getEXME_Parentesco_ID() <= 0) {
				s_log.saveError(Utilerias.getMsg(getCtx(), "patient.error.parentSelfNotExists"), getValue());
				return false;
			}
			int parenID = MEXMEParentesco.get(getCtx(), Constantes.SELF_, null).getEXME_Parentesco_ID();
			int pacRelID = -1;
			boolean crearGuarantor = Boolean.FALSE;
			// Manejo de Guarantor
			if ((newRecord && iscopyResponsible())) {
				// Creamos el registro en PacienteRel
				crearGuarantor = Boolean.TRUE;
				pacRelID = MEXMEPacienteRel.createFromPatient(this, parenID, get_TrxName());

				// Si el campo responsable cambio y ahora es el mismo paciente
			} else if (is_ValueChanged(COLUMNNAME_copyResponsible) && iscopyResponsible()) {
				// Desactivamos el PacienteRelCatalog que ya exista de este
				// paciente.
				// if (!MEXMEPacienteRelCatalog.deactivate(getCtx(),
				// getEXME_Paciente_ID(),
				// MEXMEPacienteRelCatalog.TYPE_Responsible,
				// Boolean.FALSE, 0, get_TrxName())){
				// s_log.log(Level.INFO,
				// "Fallo al desactivar PacienteRelCatalog para PacienteID = "+getEXME_Paciente_ID());
				// return false;
				// }
				crearGuarantor = Boolean.TRUE;
				// Buscamos primero si ya existe el guarantor (pacienterel) de
				// este paciente
				pacRelID = MEXMEPacienteRel.getGuarFromPatient(getCtx(), null, new Object[] { MEXMEPacienteRel.TYPE_Responsible,
						getEXME_Paciente_ID() });
				if (pacRelID < 0) {
					// Si es la primera vez que se define al paciente como
					// Guarantor
					s_log.log(Level.INFO, "Creando PacienteRel para PacienteID = " + getEXME_Paciente_ID());
					pacRelID = MEXMEPacienteRel.createFromPatient(this, parenID, get_TrxName());
				}
			}

			if (crearGuarantor) {
				if (pacRelID > 0) {
					MEXMEPacienteRelCatalog relCat = MEXMEPacienteRelCatalog.get(
							getCtx(),
							getEXME_Paciente_ID(),
							MEXMEPacienteRelCatalog.TYPE_Responsible,
							Boolean.FALSE,
							0,
							get_TrxName());
					if (relCat == null) {
						// Si no existe relCatLog para el paciente se crea.
						return MEXMEPacienteRelCatalog.createFromPatient(getEXME_Paciente_ID(), parenID, pacRelID, get_TrxName());
					} else {
						// Si existe se actualiza
						relCat.setEXME_PacienteRel_ID(pacRelID);
						relCat.setEXME_Parentesco_ID(parenID);
						relCat.setIsDefault(Boolean.TRUE);
						relCat.setIsPatient(Boolean.TRUE);
						if (!relCat.save()) {
							s_log.log(Level.SEVERE, "Fallo al actualizar PacienteRelCatalog para PacienteID = " + getEXME_Paciente_ID());
						}
					}
				} else {
					s_log.log(Level.SEVERE, "Fallo al crear PacienteRel para PacienteID = " + getEXME_Paciente_ID());
					return false;
				}
			}
		}

		// Recargar indice
		// createIndex(getCtx());
		
		// Card #1231 - ADT A08 Hl7 message
		if(!is_new() && !isCreateHL7_A08()) {
			setCreateHL7_A08();
		}
		return true;
	}

	/**
	 *
	 */
	private void updatePacienteRel() {
		int pacRelID = MEXMEPacienteRel.getGuarFromPatient(getCtx(), null,
				new Object []{MEXMEPacienteRel.TYPE2_Responsible, getEXME_Paciente_ID()});
		if (pacRelID>0){
			MEXMEPacienteRel pacRel = new MEXMEPacienteRel(getCtx(), pacRelID, null);
			pacRel.setName(getName());
			pacRel.setNombre2(getNombre2());
			pacRel.setLast_Name(getApellido1());
			if (!pacRel.save(get_TrxName())){
				s_log.severe("Fallo al actualizar pacienteRel = "+pacRelID
						+" del PacienteID = "+getEXME_Paciente_ID());
			}
		}else{
			if (iscopyResponsible()){
				s_log.severe("Paciente que es su mismo guarantor sin pacienteRel"
						+". Del PacienteID = "+getEXME_Paciente_ID());
			}else{
				s_log.info("Paciente sin Guarantor de el mismo, PacienteID = "+getEXME_Paciente_ID());

			}
		}


	}

	/**
	 * Crea los roles al usuario paciente
	 */
	private boolean createPatientRoles() {
		MRole[] roles = MRole.getOf(getCtx(), " isForPatientAccess = 'Y' ");
		int AD_Role_ID = 0;
		if (roles != null && roles.length > 0) {
			this.existePerfilPaciente = true;
			AD_Role_ID = roles[0].getAD_Role_ID();
			if (MUserRoles.getOfUserRole(getCtx(), getAD_User_ID(), AD_Role_ID, get_TrxName()) == null) {
				MUserRoles newrole = new MUserRoles(getCtx(), 0, get_TrxName());
				newrole.setAD_User_ID(getAD_User_ID());
				newrole.setAD_Role_ID(AD_Role_ID);
				newrole.setAD_Client_ID(getAD_Client_ID());
				newrole.setAD_Org_ID(getAD_Org_ID());
				if (!newrole.save()) {
					return false;
				}
			}
		} else {
			this.existePerfilPaciente = false;
			s_log.log(Level.WARNING,
					"No se ha definido un perfil para pacientes");
		}
		return true;

	}

	/**
	 * Retornamos el nombre completo de la Persona Responsable.
	 * Apellido1_Fam + Apellido2_Fam + Nombre_Fam
	 * @return Nombre completo de la Persona Responsable
	 */
	public String getRespFullName() {

		StringBuffer fullName = new StringBuffer();

		fullName.append((getApellido1_Fam() == null ? "" : getApellido1_Fam() + " "));
		fullName.append((getApellido2_Fam() == null ? "" : getApellido2_Fam() + " "));
		fullName.append((getNombre_Fam() == null ? "" : getNombre_Fam() + " "));

		return fullName.toString();
	}

	/**
	 * getRespFullNamexName
	 * @return
	 */
	public String getRespFullNamexName() {

		StringBuffer fullName = new StringBuffer();
		fullName.append((getNombre_Fam() == null ? "" : getNombre_Fam() + " "));
		fullName.append((getApellido1_Fam() == null ? "" : getApellido1_Fam() + " "));
		fullName.append((getApellido2_Fam() == null ? "" : getApellido2_Fam() + " "));

		return fullName.toString();
	}

//	/**
//	 *  Asael
//	 *  Busca si una aseguradora ya est� asociada a un paciente de facturacion especial.
//	 *  @return asociada
//	 */
//	public boolean factEspecAsegAsociada() {
//		boolean asociada = false;
//
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT C_BPartner_Seg_ID FROM EXME_Paciente ")//
//			.append(" WHERE IsFactEspec = 'Y' AND C_BPartner_Seg_ID = ? ");
//			//.append(getC_BPartner_Seg_ID());//Lama
//
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), Table_Name));
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null; //Lama
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
//			pstmt.setInt(1, getC_BPartner_Seg_ID());// Lama
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				asociada = true;
//			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, sql.toString(), e);
//		} finally { // lama
//			DB.close(rs, pstmt);
//		}
//		return asociada;
//	}

	/**
	 * getFromCURP
	 * @param ctx
	 * @param curp
	 * @param all
	 * @return
	 */
	public static MEXMEPaciente[] getFromCURP(Properties ctx, String curp, boolean all) {

		MEXMEPaciente[] paciente = null;
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(" and upper(curp) = ").append("'").append(curp.toUpperCase()).append("'");
		paciente = MEXMEPaciente.get(ctx, whereClause.toString(), null, null, all);
		return paciente;
	}

	/**
	 * Determine if the parent history exists and that it's not a duplicated
	 * dependant.
	 * @param ctx The application context.
	 * @param paciente The patient to be validated.
	 * @return True if the parent exists and is not a duplicated dependant,
	 * false if
	 */
	public static boolean validateParent(Properties ctx, MEXMEPaciente paciente) {
		boolean valid = false;
		StringBuilder sql = null;
		if(paciente.getTitular_ID() <= 0){ //Lama
			int titularId = 0;
			sql = new StringBuilder ("SELECT EXME_Paciente_ID FROM EXME_Paciente ")
						//.append("WHERE AD_Client_ID = ?")
						.append(" WHERE Value = ? ")
						.append("AND IsActive = 'Y'")
						.append(" AND EsTitular = 'Y' ");
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			titularId = DB.getSQLValue(null, sql.toString(), Env.getAD_Client_ID(ctx), paciente.getTitular());
			paciente.setTitular_ID(titularId);
		}
		if (paciente.getTitular_ID() > 0) { //Lama
			//paciente.setTitular_ID(titularId);
			// Correccion indices ttpr
			sql = new StringBuilder
					   ("SELECT EXME_Paciente_ID FROM EXME_Paciente WHERE ")
				//.append(" AD_Client_ID = ? ")
				.append(" Titular_ID = ? ")
				.append(" AND EXME_Paciente_ID <> ? ") // Excluirse a si mismo en la validacion .- Lama (Ticket: 1510)
				.append(" AND UPPER(Name) = ?")
				.append(" AND UPPER(Apellido1) = ? ")
				.append(" AND UPPER(Apellido2) = ? ");
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);

				//pstmt.setInt(1, Env.getAD_Client_ID(ctx));
				pstmt.setInt(1, paciente.getTitular_ID());
				pstmt.setInt(2, paciente.getEXME_Paciente_ID()); // Lama (Ticket: 1510)
				pstmt.setString(3, paciente.getName().toUpperCase());
				pstmt.setString(4, paciente.getApellido1().toUpperCase());
				pstmt.setString(5, paciente.getApellido2().toUpperCase());

				int pacienteId = 0;

				rs = pstmt.executeQuery();
				if (rs.next()) {
					pacienteId = rs.getInt("EXME_Paciente_ID");
				}

				if (pacienteId == 0)
					valid = true;
			} catch (SQLException e) {
				s_log.log(Level.SEVERE, "Validating kinship ....", e);
			} finally {
				DB.close(rs, pstmt);
			}
		}

		return valid;
	}

	/**
	 * Returns the value to be asigned to the new history based on the parent
	 * history. Initially this works according to the guidelines for the SEDENA,
	 * and should be customized to work for several clients.
	 * @param ctx The application context.
	 * @param paciente The patient to whom assign the value.
	 * @return True if the value could be generated.
	 */
	public static boolean genValueFromParent(Properties ctx, MEXMEPaciente paciente) {
		boolean generated = false;

		String value = null;
		int next = 0;
		DecimalFormat df = new DecimalFormat("00");

		// Valida el parentesco .- Lama
		if (paciente.getEXME_Parentesco_ID() <= 0)
			return generated;

		MEXMEParentesco parentesco = new MEXMEParentesco(ctx, paciente.getEXME_Parentesco_ID(), null);

		if (parentesco.getEXME_Parentesco_ID() <= 0)
			return generated;

		StringBuilder sql =
			new StringBuilder("SELECT COUNT(*)+1 nextValue ")
			.append("FROM EXME_PACIENTE EP ")
			.append("LEFT JOIN EXME_PARENTESCO EPP ON EPP.EXME_PARENTESCO_ID = ? ")
			//.append("WHERE EP.AD_CLIENT_ID = ? ")
			.append("WHERE EP.TITULAR = ?");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name, "EP"));


		PreparedStatement pstmt = null;
		ResultSet rs = null;

		pstmt = DB.prepareStatement(sql.toString(), null);

		try {
			pstmt.setInt(1, paciente.getEXME_Parentesco_ID());
			//pstmt.setInt(2, paciente.getAD_Client_ID());
			pstmt.setString(2, paciente.getTitular());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				next = rs.getInt("nextValue");
				value = paciente.getTitular() + "/" + parentesco.getValue() + df.format(next);
				paciente.setValue(value);
				generated = true;
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "Generating value from parent history", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return generated;
	}

	/**
	 * createTemplateDefault
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MEXMEPaciente createTemplateDefault(Properties ctx, String trxName) {
		MEXMEPaciente paciente = null;

		paciente = new MEXMEPaciente(ctx, 0, trxName);

		// Los pacientes siempre seran org *
		// Establecemos los valores por defecto.
		paciente.setAD_Org_ID(0);
		paciente.setDirPersResp("-");
		paciente.setEsAsegurado(false);
		paciente.setcopyResponsible(true);
		return paciente;
	}

	/**
	 *  Devuelve un objeto apartir del value del paciente dado
	 *
	 *@param  c         Contexto
	 *@param  tabla       Tabla para buscar
	 *@param  value       value del registro (LIKE)
	 *@return             un objeto LabenValueBean con el ID y el Value
	 *@throws  Exception  en caso de ocurrir un error en la consulta
	 */
	public static MEXMEPaciente getObjPacienteFromValue(Properties ctx, String value) throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//value = value.replaceAll("\\*", "%");//Lama: comodin estandar %

		sql.append(" SELECT EXME_Paciente.* ")
		.append(" FROM EXME_Paciente ")
		.append(" WHERE UPPER(EXME_Paciente.Value ) LIKE UPPER( ? ) ")
		.append(" AND EXME_Paciente.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		s_log.log(Level.FINE, "SQL : " + sql.toString() + "  value: " + value);

		MEXMEPaciente paciente = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, value.trim());

			rs = pstmt.executeQuery();

			// La primera coincidencia
			if (rs.next()) {
				paciente = new MEXMEPaciente(ctx, rs, null);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return paciente;
	}


	/**
	 * Noelia: Obtenemos el Hist_Expediente.
	 * @return
	 */
	public MEXMEHistExp getHistExpediente() {
		try {
			if(histExp == null)
				histExp = MEXMEHistExp.get(getCtx(), getEXME_Paciente_ID(), true, null);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getHistExpediente", e);
		}
		return histExp;
	}


	/**
	 * en_USA.UTF8
	 * Method made to get the corresponding row number when a resumenMedico has
	 * data, so when it has data, medsys send to the Telemedicina System the
	 * corresponding pdf with this info, when this result is null or zero then
	 * the pdf isn't generated, the name of the PDF the resuult of resumenMedico.xml in
	 * /util/jasper/xml
	 *
	 * es_MX.UTF8
	 * Metodo hecho para recuperar el numero de registros obtenidos de la consulta
	 * de este sql, se obtiene para saber si se debe o no enviar a telemedicina el
	 * reporte de resumenMedico, este reporte y este sql corresponden al sql
	 * que reside en el reporte resumenMedico.jasper o xml de /util/jasper/xml
	 */
	public static boolean tieneResumenMedico(int pacienteID) {

		StringBuilder sql = new StringBuilder(100);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cont = 0;

		sql.append(" SELECT count(*) as resultado ")
		.append(" FROM EXME_MEDICALRESUME MR, EXME_PACIENTE P, AD_ORG O, EXME_ACTPACIENTE AP, ")
		.append(" EXME_MEDICO D, EXME_ESPECIALIDAD DE, EXME_CITAMEDICA M, EXME_CTAPAC H  ")
		.append(" WHERE MR.EXME_PACIENTE_ID = ? ")
		.append("   AND MR.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID ")
		.append("   AND P.AD_ORG_ID = O.AD_ORG_ID ")
		.append("  AND MR.EXME_ACTPACIENTE_ID = AP.EXME_ACTPACIENTE_ID ")
		.append("  AND AP.EXME_MEDICO_ID = D.EXME_MEDICO_ID ")
		.append("  AND AP.EXME_ESPECIALIDAD_ID = DE.EXME_ESPECIALIDAD_ID ")
		.append("  AND AP.EXME_CITAMEDICA_ID = M.EXME_CITAMEDICA_ID(+) ")
		.append("  AND AP.EXME_CTAPAC_ID = H.EXME_CTAPAC_ID(+) ")
		.append(" ORDER BY AP.FECHA DESC, MR.EXME_MEDICALRESUME_ID");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, pacienteID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cont = Integer.parseInt(rs.getString(1));
				if (cont > 0)
					return true;
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return false;
	}

	/**
	 *
	 * @return
	 */
	public List<String> getMsgError() {
		return msgError;
	}

	/**
	 *
	 * @param msgError
	 */
	public void setMsgError(String msgError) {
		this.msgError.add(msgError);
	}

//	/**
//	 * Cuando el paciente no tiene un Id Location de la persona responsable
//	 * se puede obtener los datos de la persona responsable
//	 * @param C_BPartner_ID
//	 * @return
//	 */
//	public MLocation dirPaciente(int C_BPartner_ID) {
//		// Persona responsable
//		MLocation retValue = new MLocation(this.getCtx(), 0, this.get_TrxName());
//		retValue.setBPartner(C_BPartner_ID);
//		retValue.setAddress1(this.getDirPersResp());// calle1
//		retValue.setAddress2(this.getColoniaPersResp());
//		retValue.setCity(this.getCiudadPersResp());
//		retValue.setPostal(this.getCpPersResp());
//
//		if (this.getC_Country_PersResp_ID() > 0)
//			retValue.setC_Country_ID(this.getC_Country_PersResp_ID());
//
//		if (this.getC_Region_PersResp_ID() > 0)
//			retValue.setC_Region_ID(this.getC_Region_PersResp_ID());
//
//		if (this.getC_Region_PersResp_ID() > 0) {
//			MRegion region = new MRegion(this.getCtx(), this.getC_Region_PersResp_ID(), this.get_TrxName());
//			retValue.setRegionName(region.getName());
//		}
//
//		return retValue;
//	}

	/**
	 *
	 * @return
	 */
	public String getTipoPaciente() {
		return tipoPaciente;
	}

	/**
	 *
	 * @param tipoPaciente
	 */
	public void setTipoPaciente(String tipoPaciente) {
		this.tipoPaciente = tipoPaciente;
	}

//	/**
//	 *
//	 */
//	public static void validarPaciente(Properties ctx, int pacienteId, String trxName) throws Exception {
//		if (pacienteId <= 0) {
//			throw new Exception("consultaPac.error.find.noValue");
//		}
//		// Alejandro.- Validar que el paciente no tenga cuenta paciente activa.
//		MEXMECtaPac[] ctaPac = MEXMECtaPac.getOfPaciente(ctx, pacienteId, MEXMECtaPac.ENCOUNTERSTATUS_Admission, null);
//
//		if (ctaPac.length > 0) {
//			throw new Exception("consultaPac.error.CtaPacAbierta");
//		}
//
//		MEXMEPaciente paciente = new MEXMEPaciente(ctx, pacienteId, null);
//		// Alejandro.- Validar que el paciente no tenga una cita
//		MEXMECitaMedica cita = MEXMECitaMedica.getUltimaCita(ctx, pacienteId, 0, false, true, trxName);
//		if (cita != null) {
//			throw new Exception("ingresoPac.error.desactivar");
//		}
//
//		paciente.setIsActive(false);
//
//		if (!paciente.save()) {
//			throw new Exception("error.pacExt.noUpdatePac");
//		}
//	}

	/**
	 * Create user and send email
	 * @author Lorena Lama
	 * @return
	 */
	public String createUser() {

		if (getEXME_Paciente_ID() <= 0 || getValue() == null) {
			s_log.saveError("odontologia.msj.Paciente", Msg.getElement(getCtx(), "EXME_Paciente_ID"));
			return Utilerias.getMsg(getCtx(), "odontologia.msj.Paciente");
		}

		if (getEMail() == null || getEMail().equals("")) {
			s_log.saveError("error.notifica.notMail.email.pac", Msg.getElement(getCtx(), "EMail"));
			return Utilerias.getMsg(getCtx(), "error.notifica.notMail.email.pac");
		}

		/*if (is_ValueChanged(COLUMNNAME_EMail) && save) {
			if (!save()) {
				s_log.saveError("error.pacExt.noUpdatePac", Msg.getElement(getCtx(), "EXME_Paciente_ID"));
				return Utilerias.getMsg(getCtx(), "odontologia.msj.Paciente");
			}
			else {
				ValueNamePair vp = CLogger.retrieveError();
				if (vp != null && vp.getName().equals(COLUMNNAME_EMail))
					return Utilerias.getMsg(getCtx(), vp.getValue());
			}
		}*/
		// password aleatorio
//		String rdm_pass;// = RandomStringUtils.randomAlphanumeric(8);
//		Random r = new Random();
//		rdm_pass = Long.toString(Math.abs(r.nextLong()), 36);

		PasswordHandler ph = new PasswordHandler(getCtx());
		String passwd = ph.generatePassword();

		if (!createUser(passwd)) {
			s_log.saveError("error.save.createUser", "CreateUser");
			return Utilerias.getMsg(getCtx(), "error.save.createUser");
		}

		/*if (is_ValueChanged(COLUMNNAME_AD_User_ID) && !save()) {
			s_log.saveError("error.pacExt.noUpdatePac", Msg.getElement(getCtx(), "EXME_Paciente_ID"));
			return Utilerias.getMsg(getCtx(), "odontologia.msj.Paciente");
		}*/

		if (!sendPassword(passwd)) {
			s_log.saveError("error.egresos.reporder.mail", "SendPassword");
			return Utilerias.getMsg(getCtx(), "error.egresos.reporder.mail");
		}

		if(!this.existePerfilPaciente){
			s_log.saveError("error.pacExt.noUpdatePac", Msg.getElement(getCtx(), "EXME_Paciente_ID"));
			return "existePerfilPaciente";
		}

		return EMail.SENT_OK;
	}

	/**
	 * Send email
	 * @author Lorena Lama
	 * @param password
	 * @return
	 */
	public boolean sendPassword(String password) {
		boolean retValue = false;
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		MMailText mailtemp = null;
		try {
			String html_msg = Msg.translate(getCtx(), "msj.sendPassword");
			String subject = Msg.translate(getCtx(), "msj.creacionCuenta");

//			sql.append("SELECT ");
//			sql.append("  R_MailText_ID ");
//			sql.append("FROM ");
//			sql.append("  R_MailText ");
//			sql.append("WHERE ");
//			sql.append("  name = 'patient account' ");
//			int no2 = DB.getSQLValue(null, sql.toString());
//			mailtemp = new MMailText(getCtx(), no2, null);
//			String html_msg = mailtemp.getMailText();
//			String subject = mailtemp.getMailHeader();

			CConnection conn = CConnection.get();
			StringBuilder urlAccess = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			urlAccess.append(Env.getContext(getCtx(), Env.REQUEST_SCHEMA));
			urlAccess.append(Inet4Address.getByName(conn.getAppsHost()).getHostAddress());
			urlAccess.append(':');
			urlAccess.append(Utilerias.getPropertiesFromEnv("COMPIERE_WEB_PORT"));
			urlAccess.append(Env.getContext(getCtx(), Env.CONTEXT_PATH));
			if(getAD_User()!= null){
				html_msg = StringUtils.replace(html_msg, "{0}", getAD_User().getName());
			}
			html_msg = StringUtils.replaceEach(html_msg, new String[]{"{1}","{2}"}, new String[]{password,urlAccess.toString()});
			// html_msg = StringUtils.replace(html_msg, "{1}", password);
			// html_msg = StringUtils.replace(html_msg, "{2}", urlAccess.toString());

			String fecha = new String();
			if (getAD_User().getDateTo() != null) {
				SimpleDateFormat sdfFechaHoraAmPm = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
				fecha = sdfFechaHoraAmPm.format(getAD_User().getDateTo());
			}
			html_msg = StringUtils.replace(html_msg, "{3}", fecha);

			retValue = Utilerias.sendMail(getCtx(), html_msg.toString(), true, subject, getEMail());
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "sendPassword" + ex.getMessage(), ex);
		}
		return retValue;
	}

	/**
	 * Create patient's user
	 * @author Lorena Lama
	 * @param password
	 * @return
	 */
	public boolean createUser(String password) {
		MUser user = new MUser(getCtx(), getAD_User_ID(), get_TrxName());

		/*Evitar usuarios repetidos en la tabla AD_User
		 * si ya existe un usuario le concatena un numero al nombre*/
		String name = null;
		if(user.is_new()){ //Lama: solo si es nuevo, no se debe editar la cuenta de usuario
			name = getEMail();
//			for(int i = 0;MUser.getFromName(getCtx(), name) != null; i++){
//				name = name+i;
//			}
		} else {
			name = user.getName();
		}
		user.setBirthday(getFechaNac());
		user.setName(name);
		user.setEMail(getEMail());
		// si se esta actualizando el paciente
		if (password == null && user.is_new()) {
			return false;
		} else {
			user.setPassword(password);
		}
		if (!user.save()) {
			return false;
		}
		// set patient's user
		if (getAD_User_ID() <= 0) {
			setAD_User_ID(user.getAD_User_ID());
		}

		return true;
	}

	/**
	 * Send password by email
	 * @author Lorena Lama
	 * @return
	 */
	public String sendUserPass() {
		if (getAD_User_ID() <= 0) {
			log.saveError("odontologia.msj.Paciente", Msg.getElement(getCtx(), COLUMNNAME_EXME_Paciente_ID));
			return Utilerias.getMsg(getCtx(), "odontologia.msj.Paciente");
		}
		return createUser();
	}

	/**
	 *
	 * @param newRecord
	 * @return
	 */
	public boolean validateUniqueEmail(boolean newRecord) {
		boolean retValue = true;
		if (StringUtils.isNotBlank(getEMail())) {
			final int count;
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT EXME_Paciente_ID FROM EXME_Paciente WHERE email=? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", Table_Name));
			if (newRecord) {
				count = DB.getSQLValue(get_TrxName(), sql.toString(), getEMail());
			} else {
				sql.append(" AND EXME_Paciente_ID <> ? ");
				count = DB.getSQLValue(get_TrxName(), sql.toString(), getEMail(), getEXME_Paciente_ID());
			}
			retValue = count <= 0;
			// log.saveError("error.repetido", Msg.getElement(getCtx(),
			// COLUMNNAME_EXME_Paciente_ID));
		}
		return retValue;
	}

//	/**
//	 * Valida el value del paciente
//	 * @param newRecord
//	 * @return
//	 */
//	public boolean validateUniqueValue(boolean newRecord) {
//		boolean retValue = true;
//		if (StringUtils.isNotBlank(getValue())) {
//			final int count;
//			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//			sql.append("SELECT EXME_Paciente_ID FROM EXME_Paciente WHERE Value=? ");
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", Table_Name));
//			if (newRecord) {
//				count = DB.getSQLValue(get_TrxName(), sql.toString(), getValue());
//			} else {
//				sql.append(" AND EXME_Paciente_ID <> ? ");
//				count = DB.getSQLValue(get_TrxName(), sql.toString(), getValue(), getEXME_Paciente_ID());
//			}
//			retValue = count >= 0;
//			// log.saveError("error.repetido", Msg.getElement(getCtx(),
//			// COLUMNNAME_EXME_Paciente_ID));
//		}
//		return retValue;
//	}

	/**
	 * set aditional info for the patient (weight, blood type, height ...)
	 * @param ctx
	 * @param trxName
	 * @param EXME_Paciente_ID
	 */
	public MEXMEPaciente(Properties ctx, String trxName, int EXME_Paciente_ID) {
		this(ctx, EXME_Paciente_ID, trxName);
//		MHealthRecord.setPatientBIO(this, trxName);
	}

	/**
	 *
	 */
	public String getTipoSangre() {
//		if (StringUtils.isEmpty(tipoSangre)) {
			String sql = "SELECT TipoSangre FROM EXME_Paciente WHERE TipoSangre IS  NOT NULL AND EXME_Paciente_ID = ?";
			tipoSangre = StringUtils.defaultIfEmpty(DB.getSQLValueString(null, sql, getEXME_Paciente_ID()), "");
//		}
		return tipoSangre;
	}

	/**
	 *
	 * @return
	 */
	public double getWeight() {
//		if (weight == null)
//			weight = MHealthRecord.getWeight(getEXME_Paciente_ID(), get_TrxName());
		final BigDecimal weight = MEXMESignoVitalDet.getPesoPatient(getCtx(), getEXME_Paciente_ID());
		return weight == null ? 0.0 : weight.doubleValue();
	}

	/**
	 *
	 * @param weight
	 */
//	public void setWeight(double weight) {
//		this.weight = weight;
//	}

	/**
	 *
	 * @return
	 */
	public double getHeight() {
//		if (height == null)
//			height = MHealthRecord.getHeight(getEXME_Paciente_ID(), get_TrxName());
		final BigDecimal height = MEXMESignoVitalDet.getTallaPatient(getCtx(), getEXME_Paciente_ID());
		return height == null ? 0.0 : height.doubleValue();
	}

	/**
	 *
	 * @param height
	 */
//	public void setHeight(double height) {
//		this.height = height;
//	}

	/**
	 * age of patient (hours, days, months or years)
	 * @return
	 */
	public String getAgeStr() {
		String edad = "";
		if (getFechaNac() != null && getAge() != null) {
			edad = getAge().getAgeSimple();
		}
		return edad;
	}

	/**
	 * List name of patient's sex
	 * @return
	 */
	public String getSex() {
		if (sex == null && getSexo() != null)
			sex = MRefList.getListName(getCtx(), SEXO_AD_Reference_ID, getSexo());
		return sex;
	}

	/**
	 *
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Patient age in Years, Months, Days, Hours
	 * @return
	 */
	public CalcularEdadAMD getAge() {
		if (age == null && getFechaNac() != null)
			try {
				age = CalcularEdadAMD.getEdadHrs(getCtx(),getFechaNac());
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "Error calculating patient age", e);
			}
		return age;
	}

	/**
	 *
	 * @param age
	 */
	public void setAge(CalcularEdadAMD age) {
		this.age = age;
	}

	/**
	 * File name of patient's photo
	 * @return
	 */
//	public String getPhotoName() {
//		if (photoName == null)
//			loadPatientPhoto(true);
//		return photoName;
//	}

	/**
	 * loads the images (attachment) into a server folder
	 * @param fstImgFound - loads only the first attachment found
	 */
//	public void loadPatientPhoto(boolean fstImgFound) {
//
//		photoName = "";
////		photoSrc = "";
//
//		if (get_ID() <= 0)
//			return;
//
//		String directoryPath = Ini.getCompiereHome() + File.separator + Utilerias.getPropertiesFromXPT("IMGPacientesFilePath");
//		InputStream inStrm = null;
//
//		try {
//			File file = Utilerias.getFile(directoryPath, getValue());
//			// if img already exists
//			if (file != null) {
//				photoName = file.getName();
////				photoSrc = directoryPath;
//				return;
//			}
//			// load attachment for patient
//			MAttachment attachment = MAttachment.get(getCtx(), Table_ID, get_ID());
//			if (attachment == null)
//				return;
//
//			MAttachmentEntry[] entries = attachment.getEntries();
//			if (entries.length <= 0)
//				return;
//
//			for (int i = 0; i < entries.length; i++) {
//
//				MAttachmentEntry entry = entries[i];
//				if (entry == null || entry.getData() == null)
//					return;
//
//				inStrm = entry.getInputStream();
//				String fileExt = "";// file extension
//				String fileName = (i > 0 ? i : "") + getValue();
//
//				if (entry.getContentType().startsWith("image/")) {
//					fileExt = entry.getName().substring(entry.getName().indexOf("."));
//					file = Utilerias.createFromInputStream(inStrm, directoryPath + fileName + fileExt);
//				} else {
//					ZipInputStream zip = new ZipInputStream(inStrm);
//					ZipEntry entryZip;
//					while ((entryZip = zip.getNextEntry()) != null) {
//						if (!entryZip.isDirectory()) {
//							fileExt = entryZip.toString().substring(entryZip.toString().indexOf("."));
//							file = Utilerias.createFromInputStream(zip, directoryPath + fileName + fileExt);
//							// if fstImgFound loads only the first attachment
//							if (fstImgFound)
//								break;
//						}
//					}
//					if (zip != null)
//						zip.close();
//				}
//				if (file == null)
//					continue;
//
//				photoName = file.getName();
////				photoSrc = directoryPath;
//
//				// if fstImgFound loads only the first attachment
//				if (fstImgFound)
//					break;
//			}
//
//		} catch (FileNotFoundException e) {
//			s_log.log(Level.SEVERE, "FileNotFoundException while getting patient's attachment ", e);
//		} catch (IOException e) {
//			s_log.log(Level.SEVERE, "IOException while getting patient's attachment ", e);
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "Error while getting patient's attachment ", e);
//		} finally {
//			try {
//				if (inStrm != null)
//					inStrm.close();
//			} catch (IOException e) {
//				s_log.log(Level.SEVERE, "Error closing inStream", e);
//			}
//		}
//	}

	/**
	 * Cambio de socio de negocios
	 * @param ext
	 * @return
	 */
	public String cambioSocio(MEXMECtaPacExt ext) {
		if (ext == null) {
			return null;
		}
//		// Datos de aseguradora // NO EXISTEN LAS ASEGURADORAS EN EL PACIENTE
//		if (isEsAsegurado() && getC_BPartner_Seg_ID() > 0) {
////			final MEXMEBPartner socio = new MEXMEBPartner(getCtx(), getC_BPartner_Seg_ID(), get_TrxName());
//			final MEXMEBPartner socio = new MEXMEBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
//			MBPartnerLocation loc = null;
//			try {
//				loc = socio.getLocationPac();
//			} catch (Exception e) {
//				return "ingresoPac.error.save";
//			}
//			// Noelia. No se encontro direccion de facturacion (isremitto = 'N') para la aseguradora, validado por Twry
//			if (loc == null) {
//				return "error.factDirecta.facturar.noBpartnerLocationAseg";
//			}
//			ext.setC_BPartner_ID(getC_BPartner_Seg_ID());
//			ext.setC_BPartner_Location_ID(loc.getC_BPartner_Location_ID());
//			ext.setC_Location_ID(loc.getC_Location_ID());
//			ext.setDescription(socio.getName());
//			ext.setRfc(socio.getTaxID());
//		}
//		else {
		// Datos de persona Responsable, los datos de la extension son por organizacion
		if(ext.getEXME_CtaPac_ID()>0){
			ext.setC_BPartner_ID(ext.getCtaPac().getC_BPartner_ID());
			if (getLocationPac() != null)//FIXME : La busqueda se hace dos veces
				ext.setC_BPartner_Location_ID(getLocationPac().getC_BPartner_Location_ID());// lhernandez. se quita getC_BPartner_Location_ID()

			if (getC_LocationPerResp_ID() > 0){
				ext.setC_Location_ID(getC_LocationPerResp_ID());
			} else {
				ext.setC_Location_ID(getC_Location_ID());
			}
			ext.setDescription(getRespFullName());
			ext.setRfc(getRFC_Resp());
		}
		//
		return null;
	}


	/**
	 * Get bpartner address (isRemitTo = 'N')
	 * @return MBPartnerLocation
	 * @throws SQLException
	 * @deprecated El socio de negocios esta a nivel de system
	 */
	public MBPartnerLocation getLocationPac() {
		if (mBPartnerLoc == null){
			mBPartnerLoc = MEXMEBPartner.getLocationPac(getCtx(),
					getAD_Client_ID(), getC_BPartner_ID(), get_TrxName());
		}
		return mBPartnerLoc;
	} // getLocations


	/**
	 * Validaciones para el guardado de pacientes
	 *
	 * @param newRecord
	 * @author lorena lama
	 * @return
	 */
	private boolean savePatient(boolean newRecord) {
		boolean success = true;

		try {
			// SE valida directo en la pantalla WPatientForm
			// if (!StringUtils.isBlank(getTelParticular()) && (newRecord || is_ValueChanged(COLUMNNAME_TelParticular)) && !isNewborn()) {// se agrega que no sea recien nacido, mantis #6898
			// String msg = Utilerias.validateTelephone(getCtx(), getTelParticular());
			// if (msg != null) {
			// log.saveError(" ", msg);
			// return false;
			// }
			// }
			// if ( !StringUtils.isBlank(getTelFamiliar()) && ( newRecord || is_ValueChanged(COLUMNNAME_TelFamiliar)) && !isNewborn()) {// se agrega que no sea recien nacido, mantis #6898
			// String msg = Utilerias.validateTelephone(getCtx(), getTelFamiliar());
			// if (msg != null) {
			// log.saveError(" ", msg);
			// return false;
			// }
			// }
			// si se desactiva el paciente
			if (is_ValueChanged("IsActive") && !isActive()) {
				if (!deactivatePatient())
					return false;
			}
			if (!validateConfig()){// configuraciones obligatorias del sistema
				return false;
			}
			// deudor solidario por defecto - Lama
			if (getC_BPartner_ID() <= 0 && newRecord) {
//				MEXMEConfigPre configPre = MEXMEConfigPre.get(getCtx(), null);
//				if (configPre.getC_BPartner_ID() > 0) {
//					setC_BPartner_ID(configPre.getC_BPartner_ID());
//				} else {
//					setC_BPartner_ID(configEC.getC_BPartner_ID());
//				}
				setC_BPartner_ID(); // socio de negocios a nivel de system en el paciente
			}
//			if (!insuredPatient()){
//				return false;
//			}
			MEXMEMejoras mejora = null;
			if(newRecord || is_ValueChanged(COLUMNNAME_Curp)){
				mejora = MEXMEMejoras.get(getCtx(), null);
				if (!validateCURP(mejora))
					return false;
			}
			if (newRecord
					|| (is_ValueChanged(COLUMNNAME_Rfc) || is_ValueChanged(COLUMNNAME_RFC_Fam) || is_ValueChanged(COLUMNNAME_RFC_Resp) || is_ValueChanged(COLUMNNAME_Curp))) {
				mejora = mejora == null ? MEXMEMejoras.get(getCtx(), null) : mejora;
				if (!electronicInvoice(mejora))
					return false;
			}
			if (newRecord
					|| (is_ValueChanged(COLUMNNAME_Titular) || is_ValueChanged(COLUMNNAME_Titular_ID) || is_ValueChanged(COLUMNNAME_EXME_Parentesco_ID))) {
				if (!rightHolder())
					return false;
			}
//			if(getC_BPartnerPac_ID() == 0){
//				if(!asignaBPartner(true)){
//					return false;
//				}
//			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "createUser", e);
			return false;
		}
		return success;
	}

	/**
	 * Valida antes de inactivar un paciente
	 *
	 * @author lorena lama
	 * @return
	 */
	public boolean deactivatePatient() {
		// Alejandro.- Validar que el paciente no tenga cuenta paciente activa.
		final MEXMECtaPac[] ctaPac = MEXMECtaPac.getOfPaciente(getCtx(), getEXME_Paciente_ID(), MEXMECtaPac.ENCOUNTERSTATUS_Admission, null);

		if (ctaPac.length > 0) {
			s_log.saveError("", Utilerias.getMsg(getCtx(), "consultaPac.error.CtaPacAbierta"));
			return false;
		}
		// Alejandro.- Validar que el paciente no tenga una cita
		final MEXMECitaMedica cita = MEXMECitaMedica.getUltimaCita(getCtx(), getEXME_Paciente_ID(), 0, false, true, null);
		if (cita != null) {
			s_log.saveError("", Utilerias.getMsg(getCtx(), "ingresoPac.error.desactivar"));
			return false;
		}
		return true;
	}

	/**
	 * Valida que existan las configuraciones necesarias
	 *
	 * @author lorena lama
	 * @return
	 */
	public boolean validateConfig() {
		// Configuraciones necesarias
		final MEXMEConfigPre configPre = MEXMEConfigPre.get(getCtx(), null);
		if (configPre == null) {
			s_log.saveError("", Utilerias.getMsg(getCtx(), "error.configPre"));
			return false;
		}
		if(configEC==null){
			configEC= MConfigEC.get(getCtx());
		}
		if (configEC == null) {
			s_log.saveError("", Utilerias.getMsg(getCtx(), "consultaPac.error.deudor.req"));
			return false;
		}
		return true;
	}

	/**
	 * Cambia a mayusculas y elimina espacios los campos para el nombre, rfc y curp
	 * Arma el nombre completo del paciente
	 *
	 * @author lorena lama
	 */
	public void format() {
		if (is_ValueChanged(COLUMNNAME_Name)) {
			setName(getUpperCaseTrim(getName()));
		}
		if (is_ValueChanged(COLUMNNAME_Nombre2)) {
			setNombre2(getUpperCaseTrim(getNombre2()));
		}
		if (is_ValueChanged(COLUMNNAME_Apellido1)) {
			setApellido1(getUpperCaseTrim(getApellido1()));
		}
		if (is_ValueChanged(COLUMNNAME_Apellido2)) {
			setApellido2(getUpperCaseTrim(getApellido2()));
		}
		if (is_ValueChanged(COLUMNNAME_Apellido3)) {
			setApellido3(getUpperCaseTrim(getApellido3()));
		}
		// Crea el nombre del paciente
		if (is_ValueChanged(COLUMNNAME_Name) //
			|| is_ValueChanged(COLUMNNAME_Nombre2) //
			|| is_ValueChanged(COLUMNNAME_Apellido1)//
			|| is_ValueChanged(COLUMNNAME_Apellido2)) {
			final StringBuffer fullName = new StringBuffer();
			if (StringUtils.isNotEmpty(getApellido1())) {
				fullName.append(getApellido1());
			}
			// valida que el apellido materno no sea solo "." Card #1166
			if (StringUtils.isNotEmpty(getApellido2()) && !".".equals(getApellido2())) {
				if (fullName.length() > 0) {
					fullName.append(" ");
				}
				fullName.append(getApellido2());
			}
			if (StringUtils.isNotEmpty(getName())) {
				if (fullName.length() > 0) {
					fullName.append(" ");
				}
				fullName.append(getName());
			}
			if (StringUtils.isNotEmpty(getNombre2())) {
				if (fullName.length() > 0) {
					fullName.append(" ");
				}
				fullName.append(getNombre2());
			}
			setNombre_Pac(fullName.toString());
		}
		if (is_ValueChanged(COLUMNNAME_Curp)) {
			setCurp(getUpperCaseTrim(get_ValueAsString(COLUMNNAME_Curp)));
		}
		if (is_ValueChanged(COLUMNNAME_Rfc)) {
			setRfc(getUpperCaseTrim(getRfc()));
		}
		if (is_ValueChanged(COLUMNNAME_RFC_Fam)) {
			setRFC_Fam(getUpperCaseTrim(getRFC_Fam()));
		}
		if (is_ValueChanged(COLUMNNAME_RFC_Resp)) {
			setRFC_Resp(getUpperCaseTrim(getRFC_Resp()));
		}
		if (is_ValueChanged(COLUMNNAME_Value)) {
			setValue(StringUtils.trimToEmpty(getValue()));
		}
	}
	
	/** Cambia a mayusculas y elimina espacios */
	private static String getUpperCaseTrim(String val) {
		return StringUtils.upperCase(StringUtils.trimToEmpty(val));
	}

	/**
	 * Validaciones de pacientes asegurados
	 *
	 * @return
	 * @deprecated pueden existir muchas aseguradoras a un mismo paciente
	 */
//	public boolean insuredPatient() {
//		if (!isEsAsegurado())
//			return true;
//		// Datos requeridos
//
//		/*if (getC_BPartner_Seg_ID() <= 0) {
//			s_log.saveError("", Utilerias.getMsg(getCtx(), "consultaPac.error.aseguradora.req"));
//			return false;
//		}*/
//
//		MEXMEBPartner bPartnerSeg = null;
//		if (is_ValueChanged(COLUMNNAME_C_BPartner_Seg_ID) && getC_BPartner_Seg_ID()>0) {
//			// lhernandez. Mantis #4408. Validar la direccion de la aseguradora al registrar un paciente.
//			bPartnerSeg = new MEXMEBPartner(getCtx(), getC_BPartner_Seg_ID(), null);
//			MBPartnerLocation loc = null;
//			try {
//				loc = bPartnerSeg.getLocationPac();
//			} catch (Exception e) {
//				s_log.saveError("", Utilerias.getMsg(getCtx(), "ingresoPac.error.save"));
//				return false;
//			}
//			if (loc == null) {
//				s_log.saveError("", Utilerias.getMsg(getCtx(), "error.factDirecta.facturar.noBpartnerLocationAseg"));
//				return false;
//			}// fin lhernandez
//		}
//		// Fecha inicio y fecha final
//		if (is_ValueChanged(COLUMNNAME_FechaIni_Seg) || is_ValueChanged(COLUMNNAME_FechaFin_Seg)) {
//			if (StringUtils.isBlank(String.valueOf(getFechaIni_Seg())) && StringUtils.isBlank(String.valueOf(getFechaFin_Seg())))
//				if (getFechaIni_Seg().after(getFechaFin_Seg()))
//					return false;
//		}
//		// No puede haber dos pacientes con facturacion especial asociados a un mismo business partner
//		if ((is_ValueChanged(COLUMNNAME_IsFactEspec) || is_ValueChanged(COLUMNNAME_C_BPartner_Seg_ID))
//		// Asignar facturacion especial Si el paciente tiene facturacion especial
//				&& isFactEspec()) {
//			if (bPartnerSeg == null)
//				bPartnerSeg = new MEXMEBPartner(getCtx(), getC_BPartner_Seg_ID(), null);
//			// Obtenemos un BPartner con el ID de la aseguradora del paciente
//			if (!bPartnerSeg.isFactEspec()) {
//				// Un paciente con facturacion especial no puede tener una aseguradora sin facturacion especial.
//				s_log.saveError("", Utilerias.getMsg(getCtx(), "ingresoPaciente.error.SegNoFactEspec"));
//				return false;
//			}
//			// Verificamos que la aseguradora no tenga paciente asociado.
//			else if (factEspecAsegAsociada()) {
//				// No puede haber dos pacientes con facturacion especial asociados a la misma aseguradora
//				s_log.saveError("", Utilerias.getMsg(getCtx(), "ingresoPaciente.error.factEspec"));
//				return false;
//			}
//		}
//		return true;
//	}

	/**
	 * Validaciones de CURP
	 *
	 * @param mejora
	 * @author lorena lama
	 * @return
	 */
	private boolean validateCURP(MEXMEMejoras mejora) {
		if (mejora == null)
			return true;
		// Se valida que el curp no exista para otro paciente..-Noelia:
		if (mejora.isCURPMandatory()) {
			try {
				if (getCurp() == null || getCurp().equals("")) {
					s_log.saveError("", Utilerias.getMsg(getCtx(), "error.validacion.curpRepetido"));
					return false;
				}
				if (is_ValueChanged(COLUMNNAME_Curp) && repiteCURP(getCtx(), getCurp(), get_TrxName())) {
					s_log.saveError("", Utilerias.getMsg(getCtx(), "error.validacion.curpRepetido"));
					return false;
				}
			} catch (Exception e) {
				s_log.saveError("", Utilerias.getMsg(getCtx(), "error.validacion.curpRepetido"));
				return false;
			}
		}
		else {
			// si no es longitud 18, o guarda completo o nada (cuando no es obligatorio)
			/* if(getCurp().length()!=18) setCurp(null); */
		}
		/*
		 * Si esta configurado en EXME_Mejoras el curp como obligatorio,
		 * y Copiar Curp en historia.
		 * se asigna el curp como value del paciente, siempre y cuando sea diferente
		 * el curp capturado al que tiene asignado actualmente el paciente.( Noelia/Alejandro )
		 */
		if (mejora.isCurpHist()) { // validar nulos .- Lama
			if (!getValue().equals(getCurp())) {
				setValue(getCurp());
			}
		}
		return true;
	}

	/**
	 * Validaciones de Facturacion electronica
	 *
	 * @param mejora
	 * @author lorena lama
	 * @return
	 */
	public boolean electronicInvoice(MEXMEMejoras mejora) {
		// validaciones cuando tenga configuracion de factura electronica .- Alejandro Garza
		final ValidaPaciente valida = new ValidaPaciente();
		final MEXMEConfigFE configFE = MEXMEConfigFE.get(getCtx(), null);
		if (configFE == null){
			return true;
		}
		try{
			valida.validaRFC_CURP(getCtx(), new ActionErrors(), mejora.isCURPMandatory(), this, getRFC_Resp(), configFE);
		}catch(ExpertException ee){
			s_log.saveError("", ee);
			return false;
		}
		return true;
	}

	/**
	 * Validaciones de derechohabiente
	 *
	 * @author lorena lama
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean rightHolder() {
		final MEXMEConfigDer configDer = MEXMEConfigDer.get(getCtx(), null);

		if (configDer == null)
			return true;

		if (!configDer.isDerechohabiente() || !configDer.isPermiteAltaD()) {
			// los pacientes que ingresan por Medsys no deben ser ni titulares ni derechohabientes
			// si es que existe una configuracion de derechohabiencia
			setEsTitular(false);
			setDerechohabiente(false);
		}
		/*
		 * Noelia: Un paciente no puede ser derechohabiente y titular al mismo tiempo,
		 * estas propiedades son excluyentes.
		 * Si el paciente no es titutal y no es derechohabiente se considera que es Civil
		 */
		final ValidaPaciente valida = new ValidaPaciente();
		List<String> lstError = valida.validaDerechohabiente(getCtx(), new ActionErrors(), isDerechohabiente(), configDer, this);
		if (!lstError.isEmpty()) {
			for (String error : lstError) {
				s_log.saveError("", error);
			}
			return false;
		}
		return true;
	}

	/**
	 *
	 * @return
	 */
	public String getTable_Name() {
		return Table_Name;
	}

	/**
	 *
	 * @return
	 */
	public String getNacionalidad() {
		if (StringUtils.isEmpty(nacionalidad)) {
			final MEXMENacionalidad nac = new MEXMENacionalidad(getCtx(), getEXME_Nacionalidad_ID(), null);
			if (nac != null) {
				nacionalidad = StringUtils.defaultIfEmpty(nac.getDescription(), "");
			}
		}
		return nacionalidad;
	}


	/**
	 *
	 * @return
	 */
	public String getGrupoEtnico() {
		if (StringUtils.isEmpty(grupoEtnico)) {
			String sql = "select name from EXME_GpoEtnico where EXME_GpoEtnico_ID = ? ";
			grupoEtnico = StringUtils.defaultIfEmpty(DB.getSQLValueString(null, sql, getEXME_GpoEtnico_ID()), "");
		}
		return grupoEtnico;
	}


	/**
	 *
	 * @param createRoles
	 */
	public void setCreateRoles(boolean createRoles) {
		this.createRoles = createRoles;
	}



//	/**
//	 * Validates similar patient's name to avoid duplication<br>
//	 * shows matched patients asking confirmation.
//	 *
//	 * @return
//	 */
//	public static StringBuilder validateDuplicity(Properties ctx,MQuickSearch search, String value) {
//		StringBuilder error = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		List<Document> docs = search.search(value, false);
//		if (!docs.isEmpty()) {
//			error.append(Utilerias.getMessage(ctx, null, "advertencia.pacienteExiste", ""));
//			error.append("<br><br>");
//			error.append("<table border=\"1\" style=\"font-size:9px;\"><tr>");
//			error.append("<td><b>").append(Msg.translate(Env.getAD_Language(ctx), MEXMEPaciente.COLUMNNAME_Historia)).append("</b></td>");
//			error.append("<td><b>").append(Msg.translate(Env.getAD_Language(ctx), MEXMEPaciente.COLUMNNAME_Nombre_Pac)).append("</b></td>");
//			error.append("<td><b>").append(Msg.translate(Env.getAD_Language(ctx), MEXMEPaciente.COLUMNNAME_FechaNac)).append("</b></td>");
//			error.append("<td><b>").append(Msg.translate(Env.getAD_Language(ctx), MEXMEPaciente.COLUMNNAME_Imss)).append("</b></td>");
//			error.append("</tr>");
//
//			for (Document doc : docs) {
//				error.append("<tr>");
//				error.append("<td nowrap=\"nowrap\">").append(doc.get(MEXMEPaciente.COLUMNNAME_Value)).append("</td>");
//				error.append("<td nowrap=\"nowrap\">").append(doc.get(MEXMEPaciente.COLUMNNAME_Nombre_Pac)).append("</td>");
//				error.append("<td nowrap=\"nowrap\">").append(
//						doc.get(MEXMEPaciente.COLUMNNAME_FechaNac).substring(0, doc.get(MEXMEPaciente.COLUMNNAME_FechaNac).indexOf(" "))).append(
//						"</td>");
//				error.append("<td nowrap=\"nowrap\">").append(doc.get(MEXMEPaciente.COLUMNNAME_Imss)).append("</td>");
//				error.append("</tr>");
//			}
//			error.append("</table>");
//			error.append("<br>");
//			error.append(Utilerias.getMessage(ctx, null, "msg.paciente.continuar"));
//			error.append("<br><table>");
//		}
//		return error;
//	}

	/**
	 * I18N
	 *
	 * @y@ as years <br>
	 * @m@ as months <br>
	 * @d@ as days
	 */
	public String getEdad(){
		return getAgeStr();
//		return Utilerias.getAgeFromSubstrings(getCtx(), super.getEdad());
	}

	/**
	 * Obtiene todos las cuentas de un paciente
	 *
	 * @return Listado de cuentas paciente
	 */
	public static List<MEXMECtaPac> getAllCtaPac(final Properties ctx, final int EXME_Paciente_ID, final String trxName) {
//		final StringBuilder st = new StringBuilder("select cta.* from EXME_CtaPac cta where cta.EXME_Paciente_ID = ? ");
//		st.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPac", "cta"));
//		st.append(" order by cta.created desc");
//		final List<MEXMECtaPac> lst = new ArrayList<MEXMECtaPac>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(st.toString(), trxName);
//			pstmt.setInt(1, EXME_Paciente_ID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				final MEXMECtaPac cta = new MEXMECtaPac(ctx, rs, trxName);
//				lst.add(cta);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, st.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
		return new Query(ctx, MEXMECtaPac.Table_Name, " EXME_Paciente_ID=? ", trxName)//
		.setParameters(EXME_Paciente_ID)//
		.addAccessLevelSQL(true)//
		.setOrderBy(" Created DESC ")//
		.list();
	}

	/**
	 * @return the isNewborn
	 */
	public boolean isNewborn() {
		return isNewborn;
	}

	/**
	 * @param isNewborn the isNewborn to set
	 */
	public void setNewborn(boolean isNewborn) {
		this.isNewborn = isNewborn;
	}

	/**
	 *
	 * @return
	 */
	public MEXMEPacienteAseg getMain() {
		MEXMEPacienteAseg id = MEXMEPacienteAseg.getMain(getCtx(), getEXME_Paciente_ID());
		if(id == null)
			id = new  MEXMEPacienteAseg(getCtx(), 0, null);
		return id;
	}

	/**
	 *
	 * @return
	 */
	public boolean isValidarMenorEdad() {
		return validarMenorEdad;
	}

	/**
	 *
	 * @param validarMenorEdad
	 */
	public void setValidarMenorEdad(boolean validarMenorEdad) {
		this.validarMenorEdad = validarMenorEdad;
	}

	/**
	 *
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 *
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 *
	 * @return
	 */
	public String getMRN() {
		if (getHistExpediente() == null) {
			return "";
		} else {
			return histExp.getDocumentNo();
		}
	}

	/**
	 *
	 * @return
	 */
	public String getRace() {
		if (StringUtils.isEmpty(race)) {
			race = StringUtils.join(MEXMEPacienteRaza.getPatientRaces(getCtx(), getEXME_Paciente_ID(), null),", ");
		}
		return race;
	}

	/**
	 * Busca al contacto responsable que se encentre activo
	 * @return MEXMEPacienteRel - Resposible
	 */
	public MEXMEPacienteRel getGuarantor(){
		StringBuilder sqlWhere = new StringBuilder();
		int id = 0;
		MEXMEI18N objcReg = MEXMEI18N.getFromCountry(getCtx(), Env.getC_Country_ID(getCtx()), null);
		if (objcReg != null && objcReg.isResponsibleMexico()) {
			sqlWhere.append("EXME_PACIENTE1_ID = ? ")
			.append("AND ").append(MEXMEPacienteRel.COLUMNNAME_Type2).append(" = '").append(MEXMEPacienteRel.TYPE2_Responsible).append("'");
			id = new org.compiere.model.Query(getCtx(), MEXMEPacienteRel.Table_Name, sqlWhere.toString(), null)
			.setParameters(getEXME_Paciente_ID())
			.setOnlyActiveRecords(true)
			.setOrderBy(" type2 asc, isActive desc").firstIdOnly();
		} else {
			MEXMEPacienteRelCatalog relCat = getPacienteResCatGuar();
			id = relCat.getEXME_PacienteRel_ID();
		}
		// Este metodo no es utilizado por lo que no se agrego
		// al momento de corregir esto para el ticket 101047
		// manejo de Paciente como Guarantor
		if (id >0){
			return new MEXMEPacienteRel(getCtx(), id, null);
		}else{
			return null;
		}
	}

	/**
	 * Busca al registro que une el paciente con el contacto deudor solidario
	 * @return MEXMEPacienteRel - deudor solidario
	 */
	public MEXMEPacienteRel getPacienteGuarantor(){
		StringBuilder sqlWhere = new StringBuilder(" EXME_PacienteRel.EXME_PACIENTE1_ID = ? AND ")
		.append(MEXMEPacienteRel.COLUMNNAME_Type2)
		.append(" IN (null,")
		.append(DB.TO_STRING(MEXMEPacienteRel.TYPE2_PolicyHolder))
		.append(", ")
		.append(DB.TO_STRING(MEXMEPacienteRel.TYPE2_Responsible))
		.append(", ")
		.append(DB.TO_STRING(MEXMEPacienteRel.TYPE2_ResponsiblePolicyHolder))
		.append(") ");

		int id = new org.compiere.model.Query(getCtx(), MEXMEPacienteRel.Table_Name, sqlWhere.toString(), null)
		.setParameters(getEXME_Paciente_ID())
		.setOnlyActiveRecords(true)
		.setOrderBy(" type2 asc, isActive desc").firstIdOnly();

		return new MEXMEPacienteRel(getCtx(), id, null);
	}

	/**
	 * Busca al registro que une el paciente con el contacto responsable
	 * @return MEXMEPacienteRelCatalog - Resposible
	 */
	public MEXMEPacienteRelCatalog getPacienteResCatGuar(){
		StringBuilder sqlWhere = new StringBuilder();
		int id = 0;
		sqlWhere.append("EXME_PACIENTE_ID = ? ")
		.append("AND ").append(MEXMEPacienteRelCatalog.COLUMNNAME_Type).append(" = '")
		.append(MEXMEPacienteRelCatalog.TYPE_Responsible).append("'");
		sqlWhere.append(" AND EXME_CtaPac_ID is NULL ");
		id = new org.compiere.model.Query(getCtx(), MEXMEPacienteRelCatalog.Table_Name, sqlWhere.toString(), null)
		.setParameters(getEXME_Paciente_ID())
		.setOnlyActiveRecords(true)
		.setOrderBy(" type asc, isActive desc").firstIdOnly();
		return new MEXMEPacienteRelCatalog(getCtx(), id, null);
	}

	/**
	 * Busca registros que relacionan a un paciente con el contacto de emergencia
	 *
	 * @return MEXMEPacienteRelCatalog - Emergency contacts
	 */
	public List<MEXMEPacienteRelCatalog> getPacienteResCatEmer(int maxContacts){
		StringBuilder sqlWhere = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		StringBuilder slorderBy = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sqlWhere.append("EXME_PACIENTE_ID = ? ")
		.append("AND ").append(MEXMEPacienteRelCatalog.COLUMNNAME_Type).append(" = '").append(MEXMEPacienteRelCatalog.TYPE_Emergency).append("'");

		if (DB.isPostgreSQL()) {
			//Se adecua el orderby para dejar al final la palabra Limit.
			slorderBy.append(" Created Asc, Updated desc ");
			slorderBy = new StringBuilder(DB.getDatabase().addPagingSQL(slorderBy.toString(), 1, 2));
					//LIMIT ").append(maxContacts).append(" ");
		} else if (DB.isOracle()) {
			sqlWhere.append("and rownum <= ").append(maxContacts).append(" ");

			//Se adecua el order by para Oracle.
			slorderBy.append(" Created Asc, Updated desc ");
		}
		return new org.compiere.model.Query(getCtx(), MEXMEPacienteRelCatalog.Table_Name, sqlWhere.toString(), null)
		.setParameters(getEXME_Paciente_ID())
		.setOnlyActiveRecords(true)
		.setOrderBy(slorderBy.toString()).list();
	}

	/**
	 * Busca a los contactos de emergencia que se encuentren activos
	 * @params maxContacts, numero maximo de contactos que se buscaran
	 * @return List<MEXMEPacienteRel> - Emergency contacts
	 */
	public List<MEXMEPacienteRel> getEmergencyContacts(int maxContacts){
		StringBuilder sqlWhere = new StringBuilder();
		sqlWhere.append("EXME_PACIENTE1_ID = ? ")
		.append("AND ").append(MEXMEPacienteRel.COLUMNNAME_Type2).append(" = '").append(MEXMEPacienteRel.TYPE2_Emergency).append("'")
		.append("and rownum <= ").append(maxContacts).append(" ");
		return new org.compiere.model.Query(getCtx(), MEXMEPacienteRel.Table_Name, sqlWhere.toString(), null)
		.setParameters(getEXME_Paciente_ID())
		.setOnlyActiveRecords(true)
		.setOrderBy(" Created Asc, Updated desc ").list();
	}
	/**
	 * Busca los pacientes relaciones al paciente
	 * @param where - No es necesario incluir "AND"
	 * @param onlyActive
	 * @return
	 */
	public List<MEXMEPacienteRel> getRelatedPatiens(String where, boolean onlyActive){
		StringBuilder sqlWhere = new StringBuilder();
		if( where != null && !where.isEmpty() ){
			sqlWhere.append(where).append(" AND ");
		}
		sqlWhere.append("EXME_PACIENTE1_ID = ? ");

		return new org.compiere.model.Query(getCtx(), MEXMEPacienteRel.Table_Name, sqlWhere.toString(), null)
		.setParameters(getEXME_Paciente_ID())
		.setOnlyActiveRecords(onlyActive)
		.setOrderBy(" type2 asc, isActive desc").list();
	}
	/**
	 *
	 * @param where
	 * @param onlyActive
	 * @return Lista de MEXMEPacienteAseg a nivel paciente (EXME_CtaPac_ID = 0)
	 */
	public List<MEXMEPacienteAseg> getPatiensAseg(String where, boolean onlyActive){
		StringBuilder sqlWhere = new StringBuilder();
		if( where != null && !where.isEmpty() ){
			sqlWhere.append(where).append(" AND ");
		}
		sqlWhere.append("EXME_PACIENTE_ID = ? and exme_CtaPac_ID = 0");

		return new org.compiere.model.Query(getCtx(), MEXMEPacienteAseg.Table_Name, sqlWhere.toString(), null)
		.setParameters(getEXME_Paciente_ID())
		.setOnlyActiveRecords(onlyActive)
		.setOrderBy(" isActive desc, Priority asc").list();
	}

	/**
	 * Get the Patient Balance in Organization
	 * @param Properties the Properties encapsultaing the context
	 * @param int the int representing the Patient Identifier
	 * @param int the int representing the Encounter ID
	 * @param String the transaction name
	 * @return Double the Patient Balance
	 * @author:gvaldez
	 */
	public static Double getPatientBalance(Properties ctx, int patientID, int ctaPacID, String trxName) {
		BigDecimal val = MEXMEPaciente.getPatientBalance( ctx,  patientID,  ctaPacID, null, trxName);
		return val.doubleValue();
	}

	/**
	 * Get the Patient Balance in Organization
	 * @param Properties the context
	 * @param int the int representing the patient ID
	 * @param int the int representing the encounter ID
	 * @param String - it is required to include "AND"
	 * @param trxName - Transaction
	 * @return Double - Patient Balance
	 * @author:gvaldez
	 */
	public static BigDecimal getPatientBalance(Properties ctx, int patientID, int ctaPacID, String ref , String trxName) {

		BigDecimal retVal = Env.ZERO;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM ( ")
		   .append("(SELECT NVL(SUM(CPD.LineNetAmt),0) ")
		   .append(" FROM EXME_CtaPacDet CPD ")
		   .append(" WHERE CPD.EXME_CtaPac_ID = CP.EXME_CtaPac_ID and CPD.IsActive = 'Y') - ")
		   .append("(SELECT NVL(SUM(P.PayAmt),0) ")
		   .append(" FROM C_Payment P ")
		   .append(" INNER JOIN C_CHARGE C ON C.C_CHARGE_ID = P.C_CHARGE_ID AND C.TYPE NOT IN (?,?,?)")
		   .append(" WHERE P.EXME_CtaPac_ID = CP.EXME_CtaPac_ID and P.IsActive = 'Y') ")
		   .append(" ) Balance ")
		   .append("FROM EXME_CtaPac CP ")
		   .append("INNER JOIN AD_Ref_List ARL ")
		   .append("ON ARL.AD_Reference_Id = ? ")
		   .append("AND ARL.Value = CP.EncounterStatus ")
		   .append("WHERE CP.EXME_Paciente_ID = ? ");
		   //.append("AND ARL.ItemName >= ? ");
		if (ctaPacID>0) {
			sql.append("AND CP.EXME_CtaPac_ID = ? ");
		}

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMECtaPac.Table_Name, "CP"));
		if (ctaPacID>0) {
			//Se agrega el Group By por excepcion de Postgresql no afecta oracle. Jesus Cantu
			sql.append(" Group By CP.EXME_CtaPac_ID ");
			sql.append(" Order By CP.EXME_CtaPac_ID ");
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, MCharge.TYPE_CoPay);
			pstmt.setString(2, MCharge.TYPE_Deductible);
			pstmt.setString(3, MCharge.TYPE_Coinsurance);
			pstmt.setInt(4, MEXMECtaPac.ENCOUNTERSTATUS_AD_Reference_ID);
			pstmt.setInt(5, patientID);
			if (ctaPacID>0) {
				pstmt.setInt(6, ctaPacID);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal = rs.getBigDecimal(1);
			}
		} catch (Exception e) {
   			s_log.log(Level.SEVERE, "sql = " + sql
   					+ " -- ctapacid = " + ctaPacID + " --Paciente = " +
   					patientID + " ---EncounterReference = " +
   					MEXMECtaPac.ENCOUNTERSTATUS_AD_Reference_ID, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal==null ?BigDecimal.ZERO:retVal;
	}

	/**
	 * get Patient Balance
	 * @param ctx      Requerido
	 * @param ctaPacID Requerido
	 * @param confType Requerido
	 * @param trxName
	 * @return
	 */
	public static BigDecimal getPatientBalance(Properties ctx, int ctaPacID, final String confType , String trxName) {

		if (StringUtils.isEmpty(confType)) {
			return Env.ZERO;
		}

		BigDecimal retVal = Env.ZERO;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM ( ")
		// Los cargos de la cuenta paciente
		   .append("(SELECT COALESCE(SUM(CPD.LineNetAmt),0) ")
		   .append(" FROM EXME_CtaPacDet CPD ")
		   .append(" INNER JOIN EXME_ProductoOrg PO ON PO.exme_productoorg_id = fnc_getproductorg(CPD.M_Product_ID,CPD.AD_Org_ID) ")
		   .append(" WHERE CPD.EXME_CtaPac_ID = CP.EXME_CtaPac_ID and CPD.IsActive = 'Y' ")
		   .append(" AND fnc_IsProfessional(PO.EXME_ProductoOrg_ID, CPD.AD_Org_ID) = ?  ");
//		   .append(confType.equals(X_HL7_MessageConf.CONFTYPE_InstitutionalClaim)?" NOT ( ? ":" ( ? ")
//		   .append(" IN (COALESCE(PO.EXME_Modifier1_id,0), COALESCE(PO.EXME_Modifier2_id,0), COALESCE( PO.EXME_Modifier3_id,0), COALESCE( PO.EXME_Modifier4_id,0))) ");
//
		// Los pagos C_Payment
		sql.append(") - ")
		   .append("(SELECT COALESCE(SUM(P.PayAmt),0) ")
		   .append(" FROM C_Payment P ")
		   // que los ajustes no sean informativos
		   .append(" INNER JOIN C_Charge      ON P.C_Charge_ID        = C_Charge.C_Charge_ID AND C_Charge.Type NOT IN ('C','D','I') ")
		   .append(" WHERE P.EXME_CtaPac_ID = CP.EXME_CtaPac_ID and P.IsActive = 'Y' ");

		// Que sean por tipo de factura
		if(StringUtils.isNotEmpty(confType) && X_HL7_MessageConf.CONFTYPE_ProfessionalClaim.equals(confType)) {
    		sql.append("      AND P.C_Payment_ID     IN (                           ")
    		.append("                                  select sal.C_Payment_id   ")
    		.append("                                  from C_AllocationLine sal ")
    		.append("                                  inner join C_Invoice si on sal.C_Invoice_id = si.C_Invoice_id and si.IsActive = 'Y'  ")
    		.append("                                  and si.ConfType = "+DB.TO_STRING(X_HL7_MessageConf.CONFTYPE_ProfessionalClaim))
    		.append("                                  where sal.IsActive = 'Y' and sal.C_Payment_id is not null ")
    		.append("                             ) ");
    	} else if(StringUtils.isNotEmpty(confType) && X_HL7_MessageConf.CONFTYPE_InstitutionalClaim.equals(confType)) {
    		sql.append("      AND P.C_Payment_ID NOT IN (                           ")
    		.append("                                  select sal.C_Payment_id   ")
    		.append("                                  from C_AllocationLine sal ")
    		.append("                                  inner join C_Invoice si on sal.C_Invoice_id = si.C_Invoice_id and si.IsActive = 'Y' ")
    		.append("                                  and si.ConfType = "+DB.TO_STRING(X_HL7_MessageConf.CONFTYPE_ProfessionalClaim))
    		.append("                                  where sal.IsActive = 'Y' and sal.C_Payment_id is not null ")
    		.append("                                 ) ");
    	}

		sql.append(") ")
		   .append(" ) Balance ")

		   .append("FROM EXME_CtaPac CP ")
		   .append("WHERE CP.EXME_CtaPac_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMECtaPac.Table_Name, "CP"));
		if (ctaPacID>0) {
			sql.append(" group By CP.EXME_CtaPac_ID ");
			sql.append(" Order By CP.EXME_CtaPac_ID ");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
           	pstmt.setString(1, confType.equals(MHL7MessageConf.CONFTYPE_InstitutionalClaim)
        			? Constantes.REG_NOT_ACTIVE : Constantes.REG_ACTIVE);
			pstmt.setInt(2, ctaPacID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal = rs.getBigDecimal(1) == null ? Env.ZERO : rs.getBigDecimal(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	/**
	 *
	 * @param requery
	 * @return
	 */
	public List<BasicoTresProps> getAllergies(boolean requery){
		if (allergies == null || requery) {
			allergies = MEXMEPacienteAler.getAlergias(getCtx(), get_ID(), null);
		}
		return allergies;
	}

	/**
	 * Obtiene las alergias del paciente
	 * @return
	 */
	public StringBuilder getAllergies(){
		return getAllergies(getAllergies(true));
		//getAllergies(MEXMEPacienteAler.getAlergias(getCtx(), getEXME_Paciente_ID(), null));
	}

	/**
	 * Obtiene las alergias del paciente
	 * @return
	 */
	public StringBuilder getAllergies(final List<BasicoTresProps> list) {
		final StringBuilder allergies = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		if (list.isEmpty() && isNoAlergiasMed()) {
			allergies.append(Utilerias.getMsg(getCtx(), "msj.noDrugAllergy"));
		} else {
			for (BasicoTresProps btp : list) {
				if (allergies.length() > 0) {
					allergies.append(Constantes.NEWLINE);
				}
				allergies.append(btp.getNombre());
			}
		}
		return allergies;
	}

	/**
	 *
	 * @return
	 */
	public byte[] getPatientImageInBytes(){
		final MAttachment att = MAttachment.get(getCtx(), MEXMEPaciente.Table_ID, getEXME_Paciente_ID());
		if (att == null) {
		} else {
			final MAttachmentEntry[] ent = att.getEntries();
			for (MAttachmentEntry entry : ent) {
				final String name = entry.getName();
				if (StringUtils.startsWith(name, IMG_PROFILE)) {
					File img = entry.getFile();
					 byte[] arr = null;
				     try {
	                        arr = IOUtils.toByteArray(new FileInputStream(img));
	                } catch (FileNotFoundException e) {
	                        e.printStackTrace();
	                } catch (IOException e) {
	                        e.printStackTrace();
	                }
					return arr;
				}
			}
		}
		return null;
	}

	/**
	 * Peso y estatura
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, Object> getWeightAndHeight(final int patientID) throws Exception{
		final HashMap<String, Object> map = new HashMap<String, Object>();
		final List<MEXMESignoVitalDet> lstVitSigns = MEXMESignoVitalDet.getLastSignoVital(Env.getCtx(), patientID, null);

		for(MEXMESignoVitalDet svd : lstVitSigns){
			final String sign = svd.getSignoVital().getValue().toUpperCase();
			if (!Constantes.PESO.equals(sign) && !Constantes.TALLA.equals(sign)) {
				continue;
			} else if (Constantes.MASA.equalsIgnoreCase(svd.getSignoVital().getValue())) {
				map.put(Constantes.MASA, svd.getValorTo());
			}
			final BigDecimal valorTo = svd.getValorTo();
			final BigDecimal value;
			if (valorTo == null) {
				value = Env.ZERO;
			}
			else if (valorTo.scale() > 1) {
				value = valorTo.setScale(valorTo.intValue() <= 0 ? 2 : valorTo.scale(), BigDecimal.ROUND_HALF_UP);
			}
			else {
				value = valorTo;
			}
			map.put(sign, value);
			map.put(Constantes.PESO.equals(sign) ? UOM_WEIGHT : UOM_HEIGHT, svd.getUomTo(true).getUOMSymbol());
		}
		return map;
	}

	/**
	 * getPatientHeight
	 * @return String
	 */
	public String getPatientHeight(){
		final HashMap<String, Object> map;
		try {
			map = getWeightAndHeight(getEXME_Paciente_ID());
			String height = StringUtils.EMPTY;
			if(!map.isEmpty()){
				height = (map.get(Constantes.TALLA) == null ? StringUtils.EMPTY :
					(map.get(Constantes.TALLA).toString() + " " + (map.get(UOM_HEIGHT) == null ? StringUtils.EMPTY : map.get(UOM_HEIGHT))));
			}
			return height;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * getPatientWeight
	 * @return String
	 */
	public String getPatientWeight(){
		HashMap<String, Object> map;
		try {
			map = getWeightAndHeight(getEXME_Paciente_ID());
			String weight = StringUtils.EMPTY;
			if(!map.isEmpty()){
				weight = (map.get(Constantes.PESO) == null ? StringUtils.EMPTY :
					(map.get(Constantes.PESO).toString() + " " + (map.get(UOM_WEIGHT) == null ? StringUtils.EMPTY : map.get(UOM_WEIGHT))));
			}
			return weight;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * getPatientBMI
	 * @return String
	 */
	public String getPatientBMI(){
		HashMap<String, Object> map;
		try {
			map = getWeightAndHeight(getEXME_Paciente_ID());
			String bmi = StringUtils.EMPTY;
			if(!map.isEmpty()){
				bmi = (map.get(Constantes.TALLA) == null ? StringUtils.EMPTY : map.get(Constantes.TALLA).toString() );
			}
			return bmi;
		} catch (Exception e) {
			return "";
		}
	}

//
//    /** Retornamos la direccion del paciente de acuerdo a las siguientes reglas:
//        * 1. Si es asegurado y tiene socio de negocios de aseguradora y se esta evaluando la aseguradora,
//        *    retornamos la direccion de la aseguradora.
//        * 2. Si no cumple con la 1 y el socio del paciente se modifica en factura entonces:
//        *    2.a Si el paciente tiene direccion de la persona responsable, retornamos la direccion de la persona responsable.
//        *    2.b Si el paciente no tiene direccion de la persona responsable, retornamos un registro nuevo para la direccion de la persona responsable.
//        * 3. Si no cumple con la 1 y el socio del paciente no se modifica en factura entonces retornamos la direccion del paciente.
//        * 4. Si el socio de negocios del paciente es diferente al C_BPartner_ID y el C_BPartner_ID se modifica en factura se siguen las reglas 2.a y 2.b
//        * @return MLocation
//        * @param MEXMEPaciente
//        * @param boolean buscarTambienAseguradora
//        * @param int C_BPartner_ID
//        * @throws SQLException
//        */
//       public static MLocation getDireccion(MEXMEPaciente paciente, boolean buscarTambienAseguradora) throws SQLException {
//               if (paciente == null)
//                       return null;
//
//               MLocation retValue = null;
//
//               if (paciente != null && paciente.getEXME_Paciente_ID() > 0) {
//                       if (paciente.isEsAsegurado() && paciente.getC_BPartner_Seg_ID() > 0 && buscarTambienAseguradora) {// Nunca entra aqui porque evalAseg siempre es false
//                               if (paciente.getBPartnerSeg() == null)
//                                       return null;
//
//                               MBPartnerLocation bplocation = MEXMEBPartner.getLocations(paciente.getCtx(), paciente.getC_BPartner_Seg_ID(), paciente.get_TrxName());
//                               if (bplocation != null) {
//                                       retValue = bplocation.getLocation(false);
//                                       retValue.setEditable(paciente.getBPartnerSeg().isModificaEnFactura());
//                                       retValue.setBPartner(paciente.getC_BPartner_Seg_ID());
//                                       retValue.setBPartnerLocation(bplocation.getC_BPartner_Location_ID());
//                                       retValue.setCliente(ASEGURADORA);
//                               }
//                       }
//                       else {// Deudor solidario
//
//                               if (paciente.getPatientBPartner() == null)
//                                       return null;
//
//                               //Si el deudor especifica que no modifica datos en factura
//                               //se obtendran los datos configurados en el catalogo de deudor solidario
//                               //en caso contrario traera los datos de la persona responsable
//                               if (paciente.getPatientBPartner().isModificaEnFactura()) {
//
//                                       // Sacamos los datos del c_location de la personas responsable (Omar)
//                                       if (paciente.getC_LocationPerResp_ID() > 0) {
//                                               MLocation locacion = new MLocation(paciente.getCtx(), paciente.getC_LocationPerResp_ID(), paciente.get_TrxName());
//                                               // Persona responsable
//                                               retValue = new MLocation(paciente.getCtx(), 0, paciente.get_TrxName());
//                                               retValue.setAddress1(locacion.getAddress1());// + " " + locacion.getNumExt());//calle1
//                                               retValue.setAddress2(locacion.getAddress2());
//                                               retValue.setAddress3(locacion.getAddress3());
//                                               retValue.setCity(locacion.getCity());
//                                               retValue.setPostal(locacion.getPostal());
//                                               if (locacion.getC_Region_ID() > 0) {
//                                                       retValue.setC_Region_ID(locacion.getC_Region_ID());
//                                                       MRegion region = new MRegion(paciente.getCtx(), locacion.getC_Region_ID(), paciente.get_TrxName());
//                                                       retValue.setRegionName(region.getName());
//                                               }
//                                               if (locacion.getEXME_TownCouncil_ID() > 0)
//                                                       retValue.setEXME_TownCouncil_ID(locacion.getEXME_TownCouncil_ID());
//                                               if (locacion.getNumExt() != null)
//                                                       retValue.setNumExt(locacion.getNumExt());
//                                               if (locacion.getNumIn() != null)
//                                                       retValue.setNumIn(locacion.getNumIn());
//                                               if (locacion.getC_Country_ID() > 0 ) {
//                                            	   retValue.setC_Country_ID(locacion.getC_Country_ID());
//                                               }
//                                               // Fin Omar
//                                       } else {
//                                    	   //Datos del paciente
//                                    	   retValue = paciente.getLocation();
//                                       }
//                                       retValue.setCliente(PERSONA_RESP);
//                               } else {
//
//                                       // Direccion del paciente
//                                       retValue = paciente.getLocation();
//                                       retValue.setCliente(DEUDODOR_SOL);
//                               }
//
//                               retValue.setBPartner(paciente.getC_BPartner_ID());
//
//                               MBPartnerLocation bplocation = paciente.getPatientBPartner().getLocationPac();
//                               retValue.setEditable(paciente.getPatientBPartner().isModificaEnFactura());
//                               if (bplocation != null)
//                                       retValue.setBPartnerLocation(bplocation.getC_BPartner_Location_ID());
//                               else
//                                       return null;
//
//                       }
//                       /*
//                       if(C_BPartner_ID > 0){//Nunca entra aqui porque siempre el C_BPartner_ID del parametro es 0
//                               //evaluar el socio de negocios de si el socio del retValue es != al socio del parametro  && el socio del parametro tenga que se modifique en factura
//                               MEXMEBPartner socioParam = new MEXMEBPartner(paciente.getCtx(), C_BPartner_ID, paciente.get_TrxName());
//                               if (paciente.getC_BPartner_ID() != C_BPartner_ID && socioParam.isModificaEnFactura()){
//                                       //Omar
//                                       if(paciente.getC_LocationPerResp_ID() > 0){
//                                               //Persona responsable
//                                               MLocation locacion = new MLocation(paciente.getCtx(), paciente.getC_LocationPerResp_ID(), paciente.get_TrxName());
//                                               retValue = new MLocation(paciente.getCtx(), 0, paciente.get_TrxName());
//                                               retValue.setBPartner(C_BPartner_ID);
//                                               retValue.setAddress1(locacion.getAddress1()+ " " + locacion.getNumExt());//calle1
//                                               retValue.setAddress2(locacion.getAddress2());
//                                               retValue.setCity(locacion.getCity());
//                                               retValue.setPostal(locacion.getPostal());
//
//                                               if(locacion.getC_Region_ID()>0)
//                                                       retValue.setC_Country_ID(locacion.getC_Country_ID());
//                                               if(locacion.getC_Region_ID()>0){
//                                                       retValue.setC_Region_ID(locacion.getC_Region_ID());
//                                                       MRegion region = new MRegion(paciente.getCtx(), locacion.getC_Region_ID(), paciente.get_TrxName());
//                                                       retValue.setRegionName(region.getName());
//                                               }
//                                               if(locacion.getEXME_TownCouncil_ID() > 0)
//                                                       retValue.setEXME_TownCouncil_ID(locacion.getEXME_TownCouncil_ID());
//                                               if(locacion.getNumExt() != null)
//                                                       retValue.setNumExt(locacion.getNumExt());
//                                               if(locacion.getNumIn() != null)
//                                                       retValue.setNumIn(locacion.getNumIn());
//
//                                               //Fin Omar
//                                       }else{
//                                               //Persona responsable
//                                               retValue = new MLocation(paciente.getCtx(), 0, paciente.get_TrxName());
//                                               retValue.setBPartner(C_BPartner_ID);
//                                               retValue.setAddress1(paciente.getDirPersResp());//calle1
//                                               retValue.setAddress2(paciente.getColoniaPersResp());
//                                               retValue.setCity(paciente.getCiudadPersResp());
//                                               retValue.setPostal(paciente.getCpPersResp());
//
//                                               if(paciente.getC_Country_PersResp_ID()>0)
//                                                       retValue.setC_Country_ID(paciente.getC_Country_PersResp_ID());
//
//                                               if(paciente.getC_Region_PersResp_ID()>0)
//                                                       retValue.setC_Region_ID(paciente.getC_Region_PersResp_ID());
//
//                                               if(paciente.getC_Region_PersResp_ID()>0)
//                                               {
//                                                       MRegion region = new MRegion(paciente.getCtx(), paciente.getC_Region_PersResp_ID(), paciente.get_TrxName());
//                                                       retValue.setRegionName(region.getName());
//                                               }
//                                       }
//                               }
//                       }
//
//                       /*        if(socio!=null && socio.getC_BPartner_ID()>0){
//                               MBPartnerLocation bplocation = MEXMEBPartner.getLocations (socio.getCtx(),socio.getC_BPartner_ID(), socio.get_TrxName());
//                               retValue = new MLocation(socio.getCtx(), bplocation.getC_Location_ID(), socio.get_TrxName());
//                               retValue.setEditable(socio.isModificaEnFactura());
//                       } NO BORRAR
//                        */
//               }
//               return retValue;
//       }

	/**
	 * patient's active prescriptions
	 * @param trxName
	 * @param active
	 */
	public void updatePatientActivePresc(String trxName, boolean active) {
		if (!active) {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(EXME_PrescRx.EXME_PrescRx_ID) ");
			sql.append("FROM EXME_PrescRx ");
			sql.append("INNER JOIN EXME_CtaPac ON (EXME_PrescRx.EXME_CtaPac_ID=EXME_CtaPac.EXME_CtaPac_ID ");
			sql.append("AND EXME_CtaPac.EXME_Paciente_ID=?) ");
			sql.append("WHERE trim(EXME_PrescRx.Tipo)=? ");
			sql.append("AND EXME_PrescRx.isActive='Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPrescRX.Table_Name));
			active = DB.getSQLValue(trxName, sql.toString(), getEXME_Paciente_ID(), MEXMEPrescRX.TIPO_MedicalPrescription) > 0;
		}
		if (active != isPrescActivas()) {
			setPrescActivas(active);
			save(trxName);
		}
	}

	/**
	 *  patient's no allergies
	 * @param trxName
	 * @param active
	 */
	public void updateNoAllergies(String trxName, boolean active) {
		if (isNoAlergiasMed() != active) { // if value changed
			setNoAlergiasMed(active);
			save(trxName);
		}
	}

//	/**
//	 * Obtiene el saldo del paciente
//	 *
//	 * @param ctx
//	 * @param patientID
//	 * @param ctaPacID
//	 * @param trxName
//	 * @return
//	 */
//   	public static Double getSaldo(Properties ctx, int patientID, int ctaPacID, String trxName) {
//   		BigDecimal val = MEXMEPaciente.getSaldo( ctx,  patientID,  ctaPacID, null, trxName);
//   		return val.doubleValue();
//   	}
//
//	/**
//	 * Obtiene el saldo del paciente
//	 *
//	 * @param ctx
//	 * @param patientID
//	 * @param ctaPacID
//	 * @param ref
//	 * @param trxName
//	 * @return
//	 */
//   	public static BigDecimal getSaldo(Properties ctx, int patientID, int ctaPacID, String ref , String trxName) {
//
//   		BigDecimal retVal = Env.ZERO;
//   		StringBuilder sql = new StringBuilder();
//   		sql.append("SELECT SUM ( ")
//   		   .append("(SELECT NVL(SUM(CPD.LineNetAmt),0) ")
//   		   .append(" FROM EXME_CtaPacDet CPD ")
//   		   .append(" WHERE CPD.EXME_CtaPac_ID = CP.EXME_CtaPac_ID and CPD.IsActive = 'Y' and CPD.isFacturado = 'N') - ")
//   		   .append("(SELECT NVL(SUM(A.total),0) ")
//   		   .append(" FROM EXME_Anticipo A ")
//   		   .append(" WHERE A.EXME_CtaPac_ID = CP.EXME_CtaPac_ID and A.IsActive = 'Y') ")
//   		   .append(" ) Balance ")
//   		   .append("FROM EXME_CtaPac CP ")
//   		   .append("INNER JOIN AD_Ref_List ARL ")
//   		   .append("ON ARL.AD_Reference_Id = ? ")
//   		   .append("AND ARL.Value = CP.EncounterStatus ")
//   		   .append("WHERE CP.EXME_Paciente_ID = ? ");
//   		if (ctaPacID>0) {
//   			sql.append("AND CP.EXME_CtaPac_ID = ? ");
//   		}
//
//   		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMECtaPac.Table_Name, "CP"));
//   		if (ctaPacID>0) {
//   			//Se agrega el Group By por excepcion de Postgresql no afecta oracle. Jesus Cantu
//   			sql.append(" Group By CP.EXME_CtaPac_ID ");
//   			sql.append(" Order By CP.EXME_CtaPac_ID ");
//   		}
//   		PreparedStatement pstmt = null;
//   		ResultSet rs = null;
//   		try {
//   			pstmt = DB.prepareStatement(sql.toString(), null);
//   			pstmt.setInt(1, MEXMECtaPac.ENCOUNTERSTATUS_AD_Reference_ID);
//   			pstmt.setInt(2, patientID);
//   			if (ctaPacID>0) {
//   				pstmt.setInt(3, ctaPacID);
//   			}
//   			rs = pstmt.executeQuery();
//   			while (rs.next()) {
//   				retVal = rs.getBigDecimal(1);
//   			}
//   		} catch (Exception e) {
//   			s_log.log(Level.SEVERE, "sql = " + sql
//   					+ " -- ctapacid = " + ctaPacID + " --Paciente = " +
//   					patientID + " ---EncounterReference = " +
//   					MEXMECtaPac.ENCOUNTERSTATUS_AD_Reference_ID, e);
//   		} finally {
//   			DB.close(rs, pstmt);
//   		}
//   		return retVal;
//   	}

	/**
	 * Obtiene el saldo del paciente
	 *
	 * @param ctx
	 * @param patientID
	 * @param ctaPacID
	 * @param ref
	 * @param trxName
	 * @return
	 */
	public static BigDecimal getSaldo(Properties ctx, int ctaPacID, String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ( ");
		// Facturas (Lama)
		sql.append(" ((SELECT sum(COALESCE(i.grandtotal,0.0)) as total    \n");
		sql.append("   FROM EXME_CtaPacExt ext                            \n");
		sql.append("   INNER JOIN C_Invoice i ON                          \n");
		sql.append("              i.C_Invoice_ID=ext.C_Invoice_ID         \n");
		sql.append("   WHERE  ext.EXME_CtaPac_ID=CP.EXME_CtaPac_ID        \n");
		sql.append("   AND    ext.IsActive   = 'Y'                        \n");
		sql.append("  ) +                                                 \n");
		// Cargos
		sql.append("  (SELECT COALESCE(SUM(CPD.LineNetAmt),0)             \n");
		sql.append("          + COALESCE(SUM(CPD.TaxAmt),0) as total      \n");
		sql.append("   FROM   EXME_CtaPacDet CPD                          \n");
		sql.append("   INNER JOIN EXME_CtaPacExt ext ON (                 \n");
		sql.append("       ext.EXME_CtaPacExt_ID=CPD.EXME_CtaPacExt_ID    \n");
		sql.append("        AND ext.C_Invoice_ID IS NULL   )              \n");
		sql.append("   WHERE  CPD.EXME_CtaPac_ID=CP.EXME_CtaPac_ID        \n");
		sql.append("   AND    CPD.IsActive   = 'Y'                        \n");// activos
		sql.append("   AND    CPD.sedevolvio = 'N'                        \n");// no devoluciones
		sql.append("   AND    CPD.tipolinea <> ?                          \n");// sin cargos dentro de paquete
		sql.append("  )) -                                                \n");
		// Devolucion de Anticipos (Lama)
		sql.append("  ((SELECT COALESCE(SUM(p.Amount),0)                  \n");
		sql.append("    FROM   C_Payment p                                \n");
		sql.append("    WHERE  p.EXME_CtaPac_ID=CP.EXME_CtaPac_ID         \n");
		sql.append("    AND    p.c_payment_id NOT IN                      \n");
		sql.append("             (SELECT c_payment_id                     \n");
		sql.append("              FROM c_cashline                         \n");
		sql.append("              WHERE exme_ctapac_id=CP.EXME_CtaPac_ID) \n");
		sql.append("    AND    p.C_Invoice_ID IS NULL                     \n");
		sql.append("    AND    p.IsActive = 'Y'                           \n");
		sql.append("    ) +                                               \n");
		// Anticipos
		sql.append("    (SELECT COALESCE(SUM(cl.amount),0)                \n");
		sql.append("     FROM   C_CashLine cl                             \n");
		sql.append("     WHERE  cl.EXME_CtaPac_ID=CP.EXME_CtaPac_ID       \n");
		sql.append("     AND    cl.IsActive = 'Y'                         \n");
		sql.append("    ) +                                               \n");
		// Pagos Directos
		sql.append("    (SELECT COALESCE(SUM(cl.amount),0)                \n");
		sql.append("     FROM   C_CashLine cl                             \n");
		sql.append("     INNER  JOIN C_Invoice i ON                       \n");
		sql.append("                       cl.C_Invoice_ID=i.C_Invoice_ID \n");
		sql.append("     WHERE  cl.IsActive = 'Y'                         \n");
		sql.append("     AND    i.EXME_CtaPac_ID=CP.EXME_CtaPac_ID        \n");
		sql.append("    ))) As Total                                      \n");
		sql.append(" FROM  EXME_CtaPac CP                                 \n");
		sql.append(" WHERE cp.EXME_CtaPac_ID=?                            \n");
		sql.append(" AND   cp.IsActive='Y'                                \n");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMECtaPac.Table_Name, "CP"));
		// ("Cargos").subtract(rs.getBigDecimal("Anticipos").add(rs.getBigDecimal("PagosDirectos")));
		return DB.getSQLValueBD(null, sql.toString(), X_EXME_CtaPacDet.TIPOLINEA_Exempt, ctaPacID);
	}

	/**
	 * Busca al contacto responsable que se encentre activo
	 * @return MEXMEPacienteRel - Deudor solidario
	 */
	public MEXMEPacienteRel getDeudorSolidario(){

		MEXMEPacienteRel relCat = null;
		final MEXMEI18N objcReg = MEXMEI18N.getFromCountry(getCtx(), Env.getC_Country_ID(getCtx()), null);

		if (objcReg != null && objcReg.isResponsibleMexico()) {
			relCat = getPacienteGuarantor();
		}

		return relCat;
	}

	private MEXMEPacienteRel mPacienteRel = null;
	/**
	 * Deudor o persona responsable
	 * @return
	 */
	public MEXMEPacienteRel getMEXMEPacienteRel(boolean reload){
		// Deudor solidario
		if(mPacienteRel==null || reload){
			mPacienteRel = getDeudorSolidario();
		}

		// Persona responable
		if(mPacienteRel==null){
			mPacienteRel = getGuarantor();
		}
		return mPacienteRel;
	}

	/**
	 * Direccion de facturación del particular.
	 * El socio de negocios del paciente esta a nivel system
	 * por lo que si existe cuenta paciente debe ser reemplazado por el de la cuenta paciente
	 * @return
	 */
	public BeanDatosFacturacion getDireccionParticular(){

		// Deudor solidario
		final MEXMEPacienteRel mPacienteRel = getMEXMEPacienteRel(true);

		// Dirección
		MLocation retValue = null;
		if(mPacienteRel==null||mPacienteRel.getEXME_PacienteRel_ID()<=0){

			//Paciente
			retValue = getLocation();
		} else {

			// Deudor o responsable
			retValue = mPacienteRel.getLocation();
		}

		if(retValue==null){
			datosFacturacion = null;
		} else {
			datosFacturacion = new BeanDatosFacturacion(
					getCtx(),
					retValue.getC_Location_ID(),
					get_TrxName());
			datosFacturacion.setRfc(mPacienteRel==null||mPacienteRel.getEXME_PacienteRel_ID()<=0
					?getRfc()
							:mPacienteRel.getRfc()==null?getRfc():mPacienteRel.getRfc());
			String descr = getDescription();
			datosFacturacion.setName(mPacienteRel==null||mPacienteRel.getEXME_PacienteRel_ID()<=0
					?descr==null
						?getNombre_Pac():descr
					:descr==null
					    ?mPacienteRel.getNombreCompletoXApellido():descr);
			// Socio de System
			datosFacturacion.setC_BPartner_ID(getC_BPartner_ID());
		}
		return datosFacturacion;
	}
//
//	/**
//	 * Direccion de facturación de un socio determinado
//	 * @param cBParterID Socio que deberia ser una aseguradora pero puede ser otro
//	 * @return
//	 */
//	public BeanDatosFacturacion getDireccionAseguradora(final int cBParterID){
//		// Cliente
//		final MEXMEBPartner socio = new MEXMEBPartner(getCtx(), cBParterID, get_TrxName());
//		BeanDatosFacturacion datosFactAseg = null;
//		try {
//			// Direccion del socio de negocios no necesariamente del paciente
//			datosFactAseg = new BeanDatosFacturacion(
//					getCtx(),
//					socio.getLocationPac()==null?0:socio.getLocationPac().getC_Location_ID(),
//							get_TrxName());
//
//			datosFactAseg.setRfc(socio.getTaxID());
//			datosFactAseg.setName(socio.getFullName());
//			datosFactAseg.setC_BPartner_ID(socio.getC_BPartner_ID());
//
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, e.getMessage(), e);
//			datosFactAseg = null;
//		}
//		return datosFactAseg;
//	}

	/**
	 * Regresa el Registro en la tabla PHR_PacienteCompl relacionado
	 * al paciente, o un registro nuevo si no existe aun la relacion
	 * @return
	 */
	public MPHRPacienteCompl getPacienteCompl(){
		return MPHRPacienteCompl.getPacienteCompl(getCtx(), getEXME_Paciente_ID(), get_TrxName());
	}

	/** Regresa el nombre de un paciente  a partir de un Id */
	public static String getNombre_Pac(final int patientId,final String trxName) {
		String name = StringUtils.EMPTY;
		if (patientId > 0) {
			name =
					SecureEngine.decrypt(
							DB.getSQLValueString(
									trxName,
									"SELECT Nombre_Pac FROM EXME_Paciente WHERE EXME_Paciente_ID=?",
									patientId
							)
					);
		}
		return name;
	}


	/**
	 * Returns the patient full name based on the encounter id.
	 * @param ctaPacId The encounter Id.
	 * @param trxName A transacion name
	 * @return The patient name or an empty space.
	 */
	public static String getNombre_PacByCtaPac(final int ctaPacId, final String trxName) {
		String name = StringUtils.EMPTY;
		if (ctaPacId > 0) {
			name =
					SecureEngine.decrypt(
							DB.getSQLValueString(
									trxName,
									"select pac.nombre_pac, cp.exme_paciente_id"
									+ " from exme_paciente pac"
									+ "   inner join exme_ctapac cp on (cp.exme_paciente_id = pac.exme_paciente_id) "
									+ "where cp.exme_ctapac_id = ?",
									ctaPacId
							)
					);
		}
		return name;
	}
	
	/**
	 * Deudor solidario para contrato
	 * 
	 * @param ctx
	 *            Contexto
	 * @param pacId
	 *            Paciente
	 * @return Deudor o -1 de no tener
	 */
	public static int getDeudorSolidarioContrato(Properties ctx, int pacId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  exme_pacienterel_id ");
		sql.append("FROM ");
		sql.append("  exme_pacienterel ");
		sql.append("WHERE ");
		sql.append("  exme_paciente1_id = ? AND ");
		sql.append("  IsBusinessGuarantor = 'Y' AND ");
		sql.append("  isactive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, X_EXME_PacienteRel.Table_Name));
		sql.append(" ORDER BY ");
		sql.append("  created asc ");

		return DB.getSQLValue(null, sql.toString(), pacId);

	}

	/**
	 * Paciente cercano para contrato
	 * 
	 * @param ctx
	 *            Contexto
	 * @param pacId
	 *            Paciente
	 * @return Pariente cercano o -1 de no tener
	 */
	public static int getParienteCercanoContrato(Properties ctx, int pacId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  exme_pacienterel_id ");
		sql.append("FROM ");
		sql.append("  exme_pacienterel ");
		sql.append("WHERE ");
		sql.append("  exme_paciente1_id = ? AND ");
		sql.append("  isactive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, X_EXME_PacienteRel.Table_Name));
		sql.append(" ORDER BY ");
		sql.append("  created asc ");

		return DB.getSQLValue(null, sql.toString(), pacId);
	}

	/**
	 * Socio de negocios por defecto a nivel de system
	 * @return id del socio de negocios de system
	 */
	public int setC_BPartner_ID(){
		final int socio = MBPartner.getBPartnerSystem() ;
		super.setC_BPartner_ID(socio);
		return socio;
	}
	
	private boolean createHL7_A08;
	
	/** si cambio cualquiera de las columnas:
	 * Name,Nombre2,Apellido1,Apellido2,FechaNac,Sexo,TelFamiliar,EXME_Nacionalidad_ID,
	 * EdoCivil,EXME_Religion_ID,Imss,Fecha_Muerte,EXME_GpoEtnico_ID*/
	private void setCreateHL7_A08() {
		// Card #1231 - Manda el mensaje Hl7 ADT A08 (actualizacion demograficos)
		createHL7_A08 = is_ValueChanged(COLUMNNAME_Name)//
					|| is_ValueChanged(COLUMNNAME_Nombre2)//
					|| is_ValueChanged(COLUMNNAME_Apellido1)//
					|| is_ValueChanged(COLUMNNAME_Apellido2)//
					|| is_ValueChanged(COLUMNNAME_FechaNac)//
					|| is_ValueChanged(COLUMNNAME_Sexo)//
					|| is_ValueChanged(COLUMNNAME_TelFamiliar)//
					|| is_ValueChanged(COLUMNNAME_EXME_Nacionalidad_ID)//
					|| is_ValueChanged(COLUMNNAME_EdoCivil)//
					|| is_ValueChanged(COLUMNNAME_EXME_Religion_ID)//
					|| is_ValueChanged(COLUMNNAME_Imss)//
					|| is_ValueChanged(COLUMNNAME_Fecha_Muerte)//
					|| is_ValueChanged(COLUMNNAME_EXME_GpoEtnico_ID);
	}
	
	public void setCreateHL7_A08(boolean createHL7_A08) {
		this.createHL7_A08 = createHL7_A08;
	}
	
	/** @return true si cambio cualquiera de las columnas:
	 * Name,Nombre2,Apellido1,Apellido2,FechaNac,Sexo,TelFamiliar,EXME_Nacionalidad_ID,
	 * EdoCivil,EXME_Religion_ID,Imss,Fecha_Muerte,EXME_GpoEtnico_ID*/
	public boolean isCreateHL7_A08() {
		return createHL7_A08;
	}
	
	/** MessageGenerator: Create message ADT A08  (Card #1231) */
	public static void createHl7(Properties ctx, int patientId) {
		try {
			if(MEXMEConfigEC.get(ctx).isHL7ADT()) {
				MessageGenerator msg =	new MessageGenerator(ctx, true);
				msg.generateMessage(patientId, X_EXME_Paciente_ADT_V.Table_Name, X_HL7_MessageConf.CONFTYPE_PatientAdministrativeADT, null);
			}
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, "MEXMEPaciente - SmartConnector Fail", e);
		}
	}
	
	/** MessageGenerator: Create message ADT A08  (Card #1231) */
	public void createHl7() {
		createHl7(getCtx(), get_ID());
	}

	@Override
	public String getNamePac() {
		return getName();
	}

	@Override
	public String getLast_Name() {
		return getApellido1() + Constantes.SPACE + StringUtils.trimToEmpty(getApellido2());
	}

	@Override
	public String getPhone() {
		return getTelParticular();
	}

	@Override
	public String getCelular() {
		return getTelCelular();
	}

	@Override
	public String getLastname2() {
		return getApellido2();
	}
}