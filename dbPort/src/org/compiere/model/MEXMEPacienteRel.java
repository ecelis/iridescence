package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author lorena
 * 
 */
public class MEXMEPacienteRel extends X_EXME_PacienteRel implements PatientRelData {
	
	public boolean validateUnique = true;
	/** serialVersionUID */
	private static final long serialVersionUID = -8921012333587052081L;
	/** Log */
	private static CLogger cLogger = CLogger.getCLogger(MEXMEPacienteRel.class);
	/** Paciente */
	private MEXMEPaciente patient1 = null;
	/** Paciente */
	private MEXMEPaciente patient2 = null;
	/** direccion del paciente. */
	private MLocation m_location = null;
	/**  Para no validar si es de cuenta */
	private boolean fromCtaPac = false;
	/**
	 * @param ctx
	 * @param pacienteRelID
	 * @param trxName
	 */
	public MEXMEPacienteRel(final Properties ctx,final int pacienteRelID, final String trxName) {
		super(ctx, pacienteRelID, trxName);
	}

	/**
	 * @param ctx
	 * @param resultSet
	 * @param trxName
	 */
	public MEXMEPacienteRel(final Properties ctx, final ResultSet resultSet,final String trxName) {
		super(ctx, resultSet, trxName);
	}
	
	/**
	 * En el caso de consultas para estados unidos agregar el paciente como el primer parametro
	 * @param ctx
	 * @param typePatient "1" o "2" Indica el el tipo de paciente por el que se filtrara (Solo aplica en mexico). Si es null se tomara el paciente1
	 * @param whereClause
	 * @param params El primer parametro debe de ser el pacienteID
	 * @return
	 */
	public static List<MEXMEPacienteRel> getFromPatient(final Properties ctx, String typePatient, final StringBuilder whereClause, final Object[] params) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
		ResultSet resultSet = null; // NOPMD by ecaresoft-pedro on 15/05/12 11:02 AM
		PreparedStatement pstmt = null;
		final List<MEXMEPacienteRel> responsible = new ArrayList<MEXMEPacienteRel>();
		try {
			sql.append("SELECT EXME_PacienteRel.* FROM EXME_PacienteRel ");
			MEXMEI18N objcReg = MEXMEI18N.getFromCountry(Env.getCtx(), Env.getC_Country_ID(Env.getCtx()), null);
			if (objcReg != null && objcReg.isResponsibleMexico()) {
				sql.append("WHERE EXME_PacienteRel.isActive = 'Y' ");
				if (typePatient == null) {
					sql.append("AND EXME_PacienteRel.EXME_Paciente1_ID = ? ");
				} else {
					sql.append(" AND EXME_PacienteRel.EXME_Paciente").append(typePatient).append("_ID = ? ");
				}
				sql.append(whereClause);
			} else {
				sql.append("INNER JOIN EXME_PacienteRelCatalog  ");
				sql.append("ON EXME_PacienteRelCatalog.ISACTIVE = EXME_PacienteRelCatalog.ISACTIVE ");
				sql.append("AND EXME_PacienteRelCatalog.Type = EXME_PacienteRel.Type2 ");
				sql.append("AND EXME_PacienteRelCatalog.EXME_PacienteRel_ID = EXME_PacienteRel.EXME_PacienteRel_ID ");
				sql.append("AND EXME_PacienteRelCatalog.EXME_Paciente_ID = ? ");
				sql.append("WHERE EXME_PacienteRel.isActive = 'Y' ");
				sql.append(whereClause);
			}
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_PacienteRel.Table_Name));
			sql.append(" ORDER BY EXME_PacienteRel.Created Desc ");
			pstmt = DB.prepareStatement(sql.toString(), null);

			DB.setParameters(pstmt, params);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				responsible.add(new MEXMEPacienteRel(ctx, resultSet, null));
			}
		} catch (Exception e) {
			final StringBuffer parVal = new StringBuffer();
			
			if(params != null) {
				for (Object parameter : params) {
					parVal.append(parameter).append('\n');
				}
			}
			
			cLogger.log(Level.SEVERE, sql.toString() + '\n' + parVal.toString(), e);
		} finally {
			DB.close(resultSet, pstmt);
		}
		return responsible;
	}
	
	
	/**
	 * El Guarantor que es creado desde el paciente es el unico registro que tiene valor en EXME_Paciente_ID
	 * @param ctx
	 * @param type Indica que tipo de PacienteRel recuperar (22 Nov 2012 solo hay de tipo R)
	 * @param whereClause
	 * @param params El primer parametro debe de ser el Type, luego pacienteID y al final los que se requieran por whereClause
	 * @return
	 */
	public static int getGuarFromPatient(final Properties ctx, final StringBuilder whereClause, final Object[] params) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
		ResultSet resultSet = null; // NOPMD by ecaresoft-pedro on 15/05/12 11:02 AM
		PreparedStatement pstmt = null;
		int guarantor = -1;
		try {
			sql.append("SELECT EXME_PacienteRel.EXME_PacienteRel_ID FROM EXME_PacienteRel ");
			sql.append("WHERE EXME_PacienteRel.isActive = 'Y' ");
			sql.append("AND EXME_PacienteRel.Type2 = ?  ");
			sql.append("AND EXME_PacienteRel.EXME_Paciente_ID = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_PacienteRel.Table_Name));
			if (whereClause != null &&  StringUtils.isNotBlank(whereClause.toString())){
				sql.append(whereClause);
			}
			sql.append(" ORDER BY EXME_PacienteRel.Created Desc ");
			pstmt = DB.prepareStatement(sql.toString(), null);

			DB.setParameters(pstmt, params);

			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				guarantor = resultSet.getInt("EXME_PacienteRel_ID");
			}
		} catch (Exception e) {
			final StringBuffer parVal = new StringBuffer();
			
			if(params != null) {
				for (Object parameter : params) {
					parVal.append(parameter).append('\n');
				}
			}
			
			cLogger.log(Level.SEVERE, sql.toString() + '\n' + parVal.toString(), e);
		} finally {
			DB.close(resultSet, pstmt);
		}
		return guarantor;
	}
	
	

	/**
	 * 
	 * @param ctx
	 * @param type
	 * @param patientID
	 * @param recordID
	 * @return
	 */
	public static boolean validateType(final Properties ctx,final boolean type1,final String type,final int patientID,final int recordID) {
		final List<MEXMEPacienteRel> responsible = get(ctx, type1, type, patientID, recordID, false);
		return responsible == null || responsible.isEmpty();
	}
	
	/**
	 * Regresa la relacion entre el paciente y el contacto
	 * @return
	 */
	public static String getPacienteRelQuery(String aliasPacienteRel, String aliasPaciente) {
		StringBuilder str = new StringBuilder();
		if (StringUtils.isBlank(aliasPacienteRel)) {
			aliasPacienteRel = MEXMEPacienteRel.Table_Name;
		}
		if (StringUtils.isBlank(aliasPaciente)) {
			aliasPaciente = MEXMEPaciente.Table_Name;
		}
		MEXMEI18N objcReg = MEXMEI18N.getFromCountry(Env.getCtx(), Env.getC_Country_ID(Env.getCtx()), null);
		if (objcReg != null && objcReg.isResponsibleMexico()) {
			str.append(" EXME_PacienteRel ")
			.append(" ON ").append(aliasPaciente).append(".EXME_Paciente_ID = ").append(aliasPacienteRel).append(".EXME_Paciente1_ID ");
		} else {
			str.append(" EXME_PacienteRelCatalog ")
			   .append(" ON EXME_PacienteRelCatalog.exme_pacienterel_id = ").append(aliasPaciente).append(".exme_paciente_id ")
			.append("INNER JOIN EXME_PacienteRel ")
			.append(" ON ").append(aliasPacienteRel).append(".exme_pacienterel_id = EXME_PacienteRelCatalog.exme_pacienterel_id ");
		}
		return str.toString();
	}

	/**
	 * 
	 * @param ctx
	 * @param type
	 * @param patientID
	 * @param recordID
	 * @param forceType 
	 * @return
	 */
	public static List<MEXMEPacienteRel> get(final Properties ctx,final boolean type1,final String type,final int patientID,final int recordID,final boolean forceType) {
		List<MEXMEPacienteRel> responsible;
		if (StringUtils.isBlank(type) || patientID <= 0) {
			responsible = null; // NOPMD by ecaresoft-pedro on 15/05/12 11:13 AM
		}else{
			final StringBuilder sql = new StringBuilder();
			sql.append(" AND EXME_PacienteRel.EXME_PacienteRel_ID != ? ");
			
			final List<Object> lst = new ArrayList<Object>();
			lst.add(patientID);
			lst.add(recordID);
			
			if(!type.equals(X_EXME_PacienteRel.TYPE_Emergency)){
				MEXMEI18N objcReg = MEXMEI18N.getFromCountry(Env.getCtx(), Env.getC_Country_ID(Env.getCtx()), null);
				if (objcReg != null && objcReg.isResponsibleMexico()) {
					sql.append(" AND EXME_PacienteRel.Type").append(type1 ? "" : 2).append(" IN ( ");
				} else {
					sql.append(" AND prc.Type IN ( ");
				}
				if (forceType){
					sql.append(" ? )");
				}else{
					sql.append(" ?, ? ")
					   .append(type.equals(X_EXME_PacienteRel.TYPE_ResponsiblePolicyHolder) ? ", ? " : "").append(" ) ");					
				}
				
		
				if (type.equals(X_EXME_PacienteRel.TYPE_Responsible)) {
					lst.add(X_EXME_PacienteRel.TYPE_Responsible);
					if (!forceType){
						lst.add(X_EXME_PacienteRel.TYPE_ResponsiblePolicyHolder);
					}
				} else if (type.equals(X_EXME_PacienteRel.TYPE_PolicyHolder)) {
					lst.add(X_EXME_PacienteRel.TYPE_PolicyHolder);
					if (!forceType){
						lst.add(X_EXME_PacienteRel.TYPE_ResponsiblePolicyHolder);
					}
				} else if (type.equals(X_EXME_PacienteRel.TYPE_ResponsiblePolicyHolder)) {

					if (!forceType) {
						lst.add(X_EXME_PacienteRel.TYPE_Responsible);
						lst.add(X_EXME_PacienteRel.TYPE_PolicyHolder);
					}

					lst.add(X_EXME_PacienteRel.TYPE_ResponsiblePolicyHolder);

				}
			}
			responsible = getFromPatient(ctx, type1 ? "1" : "2", sql, lst.toArray());
		}
		return responsible;
	}

	/**
	 * Se valida el Tipo de relacion del contancto con el paciente.
	 * Un paciente puede tener cualquier cantidad de relaciones de tipo Emergency 
	 * pero de PolicyHolder o Responsible solo puede haber una activa
	 * @param ctx
	 * @param type
	 * @param patientID
	 * @param recordID
	 * @return
	 */
	public boolean validateType(final boolean type1) {
		String type = null;
		int patientID = 0;
		if (type1) {
			if (StringUtils.isBlank(getType()) || getEXME_Paciente2_ID() <= 0 || MEXMEPacienteRel.TYPE_Emergency.equals(getType())) {
				return true;
			}
			type = getType();
			patientID = getEXME_Paciente2_ID();
		} else {
			if (StringUtils.isBlank(getType2()) || getEXME_Paciente1_ID() <= 0 || MEXMEPacienteRel.TYPE_Emergency.equals(getType2())) {
				return true;
			}
			type = getType2();
			patientID = getEXME_Paciente1_ID();
		}
		return validateType(getCtx(), type1, type, patientID, get_ID());
	}
	


	/**
	 * Si viene o desde cuenta paciente
	 * 
	 * @return
	 */
	public boolean isFromCtaPac() {
		return fromCtaPac;
	}

	/**
	 * Asignar si viene o no desde cuenta paciente
	 * 
	 * @param fromCtaPac
	 */
	public void setFromCtaPac(boolean fromCtaPac) {
		this.fromCtaPac = fromCtaPac;
	}

	@Override
	protected boolean beforeSave(final boolean newRecord) {
		boolean success = true;
		
		if (!isFromCtaPac()) {
			if (newRecord) {
				MEXMEI18N objcReg = MEXMEI18N.getFromCountry(Env.getCtx(), Env.getC_Country_ID(Env.getCtx()), null);
				if (objcReg != null && objcReg.isResponsibleMexico()) {
					success = validateType(true) && validateType(false);
				}
				if (success && validateUnique) {
					success ^= alreadyExists(getCtx(), getEXME_Paciente1_ID());
					if (!success) {
						log.saveError("Error", Msg.parseTranslation(getCtx(), "@msj.OnlyOneRecord@"));
					}
				}
			} else {
				if (is_ValueChanged(COLUMNNAME_Type)) {
					success = validateType(true);
					if (!success) {
						return success;
					}
				}
				if (is_ValueChanged(COLUMNNAME_Type2)) {
					success = validateType(false);
					if (!success) {
						return success;
					}
				}
			}
		}

		return success;
	}

	@Override
	protected boolean afterSave(final boolean newRecord, final boolean success) {
		
		if (!isFromCtaPac() && success){
			// if responsible is patient 1
			if (!StringUtils.isBlank(getType()) && getPatient2() != null) {
				if (getType().equals(TYPE_PolicyHolder) || getType().equals(TYPE_ResponsiblePolicyHolder)) {
					getPatient2().setEsTitular(false);
				}
				if (getType().equals(TYPE_Responsible) || getType().equals(TYPE_ResponsiblePolicyHolder)) {
					if (!updatePatient(getPatient2(), getPatient1())) {
						return false;
					}
				} else {
					if (!getPatient2().save()) {
						return false;
					}
				}
			}

			// if responsible is patient 2
			if (!StringUtils.isBlank(getType2()) && getPatient1() != null) {
				if (getType2().equals(TYPE_PolicyHolder) || getType2().equals(TYPE_ResponsiblePolicyHolder)) {
					getPatient1().setEsTitular(false);
				}
				if (getType2().equals(TYPE_Responsible) || getType2().equals(TYPE_ResponsiblePolicyHolder)) {
					if (!updatePatient(getPatient1(), getPatient2())) {
						return false;
					}
				} else {
					if (!getPatient1().save()) {
						return false;
					}
				}
			}
		}

		return success;
	}

	/**
	 * 
	 * @param patient
	 * @param resp
	 * @return
	 */
	public boolean updatePatient(final MEXMEPaciente patient, final MEXMEPaciente resp) {
		if(patient == null){
			return true; //Lama: NullPointerException
		}
		//lo siguiente se hace solo para mexico(se espera se valide por MXTeam.
		MEXMEI18N objcReg = MEXMEI18N.getFromCountry(Env.getCtx(), Env.getC_Country_ID(Env.getCtx()), null);
		if (objcReg != null && objcReg.isResponsibleMexico()){
			patient.setcopyResponsible(false);
		}

		if (resp == null) {
			patient.setNombre_Fam(getName());
		} else {
			patient.setNombre_Fam(resp.getName() + " " + resp.getNombre2());
			patient.setApellido1_Fam(resp.getApellido1());
			patient.setApellido2_Fam(resp.getApellido2());			
			
		}

		patient.setTelParticular_Fam(getPhone());
		if (StringUtils.isNotBlank(getParticularComCode())){
			patient.setFamiliarComCode(getParticularComCode());
		}
		patient.setRFC_Resp(getRfc());

		// address
		final MLocation location = new MLocation(getCtx(), getC_Location_ID(), null);
		final MLocation locationResp = MLocation.copyFrom(getCtx(), location, patient.getC_LocationPerResp_ID(), null);
		locationResp.save(get_TrxName());
		patient.setC_LocationPerResp_ID(locationResp.getC_Location_ID());
		return patient.save(get_TrxName());
	}

	public MEXMEPaciente getPatient1() {
		if (patient1 == null && getEXME_Paciente1_ID() > 0) {
			patient1 = new MEXMEPaciente(getCtx(), getEXME_Paciente1_ID(), get_TrxName());
		}
		return patient1;
	}

	public void setPatient1(final MEXMEPaciente patient1) {
		this.patient1 = patient1;
	}

	public MEXMEPaciente getPatient2() {
		if (patient2 == null && getEXME_Paciente2_ID() > 0) {
			patient2 = new MEXMEPaciente(getCtx(), getEXME_Paciente2_ID(), get_TrxName());
		}
		return patient2;
	}

	public void setPatient2(final MEXMEPaciente patient2) {
		this.patient2 = patient2;
	}
	
	/**
	 * Verifica si ya existe un registro para el paciente seleccionado.
	 * @param ctx
	 * @param exmePacienteId
	 * @return
	 */
	public static boolean alreadyExists(final Properties ctx, final int exmePacienteId){
		boolean exists = false;
		//PARA USA no aplica esta validacion por  que , esta debe de ir en PacienteRelCatalog.
		MEXMEI18N objcReg = MEXMEI18N.getFromCountry(Env.getCtx(), Env.getC_Country_ID(Env.getCtx()), null);
		if (objcReg != null && objcReg.isResponsibleMexico()) {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			PreparedStatement pstmt = null;
			
			ResultSet result = null;
			sql.append(" SELECT EXME_PACIENTEREL.EXME_PACIENTEREL_ID FROM EXME_PACIENTEREL ")
			   .append(" WHERE (EXME_PACIENTEREL.TYPE2 = '").append(MEXMEPacienteRel.TYPE_Responsible).append("')")
			   .append(" AND ( EXME_PACIENTEREL.EXME_PACIENTE1_ID = ? ) ");
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, exmePacienteId);
				result = pstmt.executeQuery();
				if (result.next()) {
					exists = true;
				}
			} catch(SQLException e){
				cLogger.log(Level.SEVERE, e.getLocalizedMessage());
			} finally {
				DB.close(result, pstmt);
			}
		}
				
		return exists;
	}

	public boolean isValidateUnique() {
		return validateUnique;
	}

	public void setValidateUnique(final boolean validateUnique) {
		this.validateUnique = validateUnique;
	}
	
	/**
	 * Obtiene los contactos similares segun el name y lastName
	 * @param name
	 * @param lastName
	 * @return List<MEXMEPacienteRel> -- Lista de contactos
	 */
	public static List<MEXMEPacienteRel> getSimiliarContacts(String name, String lastName) {
		List<MEXMEPacienteRel> lst = new ArrayList<MEXMEPacienteRel>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT cont.* FROM EXME_PACIENTEREL cont ")
		.append(" WHERE upper(cont.name) = ?")
		.append(" AND upper(cont.last_Name) = ? AND cont.isActive = 'Y'");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", Table_Name, "cont"));

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, name);
			pstmt.setString(2, lastName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMEPacienteRel(Env.getCtx(), rs, null));
			}
		} catch(SQLException e){
			cLogger.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}
	
	/**
	 * Retornamos la direccion donde vive el paciente.
	 * @return
	 */
	public MLocation getLocation() {
		if (m_location == null && getC_Location_ID() > 0) {
			m_location = new MLocation(getCtx(), getC_Location_ID(), get_TrxName());
		}
		return m_location;
	}

	public static int createFromPatient(MEXMEPaciente pac, int parenID , String trxName) {
		MEXMEPacienteRel rel = new MEXMEPacienteRel(Env.getCtx(), 0, trxName);
		//ESTE CAMPO SOLO SE LLENA CUANDO EL GUARANTOR(PACIENTEREL) FUE CREADO DESDE UN EXME_PACIENTE
		rel.setEXME_Paciente_ID(pac.getEXME_Paciente_ID());
		//---------------------------------------------
		rel.setEXME_Paciente1_ID(pac.getEXME_Paciente_ID());
		rel.setType2(MEXMEPacienteRel.TYPE2_Responsible);
		rel.setName(pac.getName());
		rel.setNombre2(pac.getNombre2());
		rel.setLast_Name(pac.getApellido1());
		rel.setC_LocationPhys_ID(pac.getC_Location_Mail_ID());
		rel.setC_Location_ID(pac.getC_Location_ID());
		rel.setPhone(pac.getTelParticular());
		rel.setTelefonoTrabajo(pac.getTelTrabajo());
		rel.setCelular(pac.getTelCelular());
		rel.setEMail(pac.getEMail());
		rel.setEXME_Employment_ID(pac.getEXME_Employment_ID());
		rel.setEXME_Ocupacion_ID(pac.getEXME_Ocupacion_ID());
		rel.setEXME_Parentesco_ID(parenID);
		rel.setFromCtaPac(Boolean.FALSE);
				
		if (rel.save()){
			return rel.getEXME_PacienteRel_ID();
		}else{
			cLogger.log(Level.SEVERE, "Fallo al crear PacienteRel: PacienteID = "+pac.getEXME_Paciente_ID());
			rel=null;
			return 0;
		}
	}
	
	/**
	 * Obtiene todos los registros de Guarantor 
	 * 
	 * @param ctx El Properties con el contexto de la aplicacion
	 * @param rowNum El int con el numero de registros a obtener
	 * @param type El  String con el el tipo de regitros,  MEXMEPacienteRelCatalog.TYPE
	 * @param trxName el String con el nombre de la transaccion
	 * 
	 * @return un List con los registros obtenidos o vacio en caso de no existir*/
	public static List<MEXMEPacienteRel> getRels(Properties ctx, String trxName, int rowNum, String where, Object... params){
		List<MEXMEPacienteRel> records = new ArrayList<MEXMEPacienteRel>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		String querySql = new Query(ctx, Table_Name, where, trxName).setOnlyActiveRecords(true).setOrderBy(" created desc ").getSQL();//
		ResultSet resultSet = null; 
		PreparedStatement pstmt = null;
		
				
		try {
			if(rowNum > 0){
				sql.append("SELECT * FROM (  ");
			}
			
			sql.append(querySql);
						
			if (rowNum > 0) {
				sql.append(" ) ");
				
				if(DB.isPostgreSQL()) {
					sql.append("AS prc");
					
					sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, rowNum));
				} else {
					sql.append("WHERE ROWNUM <= ").append(rowNum);
				}
			}
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			DB.setParameters(pstmt, params);
			
			
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				records.add(new MEXMEPacienteRel(ctx, resultSet, trxName));
			}
			
			
		} catch (Exception e) {
			final StringBuffer parVal = new StringBuffer();
			
			cLogger.log(Level.SEVERE, sql.toString() , e);
		} finally {
			DB.close(resultSet, pstmt);
		}

		return records;
	}
	
	public static List<MEXMEPaciente> getPatients(Properties ctx, String trxName, int pacienterel_id){
		
		List<MEXMEPaciente> patients = new ArrayList<MEXMEPaciente>();
		final StringBuilder joins = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" inner join exme_pacienterelcatalog prc ")
		.append("       on prc.exme_paciente_id = EXME_Paciente.exme_paciente_id ")
		.append(" inner join exme_hist_exp hp ")
		.append("       on hp.exme_paciente_id = EXME_Paciente.exme_paciente_id ")
		.append("       and hp.ad_org_id = ? ")
		.append("       and hp.ad_client_id = ? ")
		.append("       and prc.exme_pacienterel_id = ? ")
		.append("       and prc.isActive = ? ");
		
		String select = new Query(ctx, MEXMEPaciente.Table_Name, null, trxName)
		.setJoins(joins)
		//.setParameters(Env.getAD_Org_ID(ctx),Env.getAD_Client_ID(ctx),pacienterel_id,true)		
		.getSQL();
		
		StringBuilder sql = new StringBuilder(500)
		.append("SELECT DISTINCT * FROM (")
		.append(select)
		.append(" ) t ");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			
			DB.setParameters(pstmt, new Object[]{Env.getAD_Org_ID(ctx),Env.getAD_Client_ID(ctx),pacienterel_id,true});
			rs = pstmt.executeQuery();
			while (rs.next ())
			{
				patients.add(new MEXMEPaciente(ctx, rs, trxName));
//				T po = (T)table.getPO(rs, trxName);
//				if(po != null){
//					list.add(po);
//				}
			}
		}
		catch (SQLException e)
		{
			//cLogger.log(Level.SEVERE, sql);
			//throw new DBException(e, sql);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		
		return patients;
	}
	/**
	 * Nombre completo
	 * @return
	 */
	public String getNombreCompletoXApellido(){
		StringBuilder nombre = new StringBuilder();
		nombre.append(getLast_Name()==null?StringUtils.EMPTY:getLast_Name());
		nombre.append(getLastname2()==null?StringUtils.EMPTY:" " + getLastname2());
		nombre.append(getName()==null?StringUtils.EMPTY:" " + getName());
		nombre.append(getNombre2()==null?StringUtils.EMPTY:" " + getNombre2());
		return nombre.toString().trim();
	}
	
	/**
	 * Nombre completo
	 * @return
	 */
	public static String getNombreCompletoXApellido(final int pacienteRelId){
		return pacienteRelId > 0 ? new MEXMEPacienteRel(Env.getCtx(), pacienteRelId, null).getNombreCompletoXApellido() : "";
	}
	
	@Override
	public String getCurp() {
		return getEXME_Paciente().getCurp();
	}

	@Override
	public String getNamePac() {
		return getName();
	}
	
}
