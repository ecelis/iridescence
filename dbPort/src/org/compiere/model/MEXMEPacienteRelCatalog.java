package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;


import com.ecaresoft.api.Generic;

/**
 * @author vperez
 * 
 */
public class MEXMEPacienteRelCatalog extends X_EXME_PacienteRelCatalog {
	private static CLogger s_log = CLogger.getCLogger(MEXMEPacienteRelCatalog.class);
	private static final long serialVersionUID = -6397567303546142560L;

	/**
	 * Obtener los guarantors de la cuenta
	 * 
	 * @param ctx
	 *            Contexto de aplicación
	 * @param ctaPacId
	 *            Cuenta paciente
	 * @param isDefault
	 *            Si se requiere o no el default
	 * @param trxName
	 *            Nombre de la trx
	 * @return Listado de guarantors
	 */
	public static List<Generic> getFromEncounter(Properties ctx, int ctaPacId, boolean isActive, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  C.EXME_PacienteRelCatalog_ID, ");
		sql.append("  R.Name GName, R.Nombre2 GNombre2, R.Last_Name GApellido1 ");
		//sql.append("  Coalesce(R.Name, P.Name) Name, Coalesce(R.Nombre2, P.Nombre2) ");
		//sql.append("  Nombre2, Coalesce(R.Last_Name,P.Apellido1) Apellido1 ");
		sql.append("FROM EXME_PacienteRelCatalog C ");
		sql.append("  INNER JOIN EXME_PacienteRel R ");
		sql.append("  ON R.EXME_PacienteRel_ID = C.EXME_PacienteRel_ID ");
		sql.append("WHERE ");
		sql.append("  C.IsActive = ? AND C.Type = ? AND C.EXME_CtaPac_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "C"));

		List<Generic> list = new ArrayList<Generic>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setString(1, DB.TO_STRING(isActive));
			pstmt.setString(2, MEXMEPacienteRelCatalog.TYPE_Responsible);
			pstmt.setInt(3, ctaPacId);
			//pstmt.setString(3, DB.TO_STRING(isDefault));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				StringBuilder name = new StringBuilder();
				name.append(StringUtils.defaultIfEmpty(SecureEngine.decrypt(rs.getString(4)), StringUtils.EMPTY));
				name.append(Constantes.SPACE);
				name.append(SecureEngine.decrypt(rs.getString(2)));
				name.append(Constantes.SPACE);
				name.append(StringUtils.defaultIfEmpty(SecureEngine.decrypt(rs.getString(3)), StringUtils.EMPTY));
				list.add(new Generic(name.toString(), rs.getString(1)));
				
				
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Actualiza todos los guarantors de la cuenta para que no sean default
	 * 
	 * @param ctx
	 *            Contexto de la aplicación
	 * @param ctaPacId
	 *            Cuenta paciente
	 * @param trxName
	 *            Trx
	 */
	private static void updateRelCatLog(Properties ctx, int ctaPacId, int relCatID, String columnName, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append("  exme_pacienterelcatalog ");
		sql.append("SET ");
		sql.append(columnName);
		sql.append(" = 'N' WHERE ");
		sql.append("  exme_ctapac_id = ? ");
		sql.append("  AND EXME_PacienteRelCatalog_ID <> ? ");

		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacId);
			pstmt.setInt(2, relCatID);

			pstmt.executeUpdate();

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(pstmt);
		}
	}

	/**
	 * @param ctx
	 * @param EXME_PacienteRelCatalog_ID
	 * @param trxName
	 */
	public MEXMEPacienteRelCatalog(Properties ctx, int EXME_PacienteRelCatalog_ID, String trxName) {
		super(ctx, EXME_PacienteRelCatalog_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPacienteRelCatalog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Actualizar el guarantos para que sea default y actualiza los demas para que no lo sean
	 * 
	 * @param trxName
	 *            TrxName
	 * @return Si pudo o no cambiarlo
	 */
	public boolean makeDefault(String trxName) {
		updateRelCatLog(getCtx(), getEXME_CtaPac_ID(), getEXME_PacienteRelCatalog_ID(), COLUMNNAME_IsDefault,   trxName);
		setIsDefault(true);
		return save(trxName);
	}
	
	/**
	 * Actualizar el guarantos para que sea activo y desactiva los demas para que no lo sean
	 * 
	 * @param trxName
	 *            TrxName
	 * @return Si pudo o no cambiarlo
	 */
	public boolean makeActive(String trxName) {
		updateRelCatLog(getCtx(), getEXME_CtaPac_ID(), getEXME_PacienteRelCatalog_ID(), COLUMN_IsActive,  trxName);
		setIsActive(true);
		return save(trxName);
	}
	
	/**
	 * Crear el guarantor
	 * 
	 * @param trxName
	 *            TrxName
	 * @return Si fue creado o no
	 */
	
	public static boolean createFromPatient(int pacID, int parenID, int pacRelID, String trxName) {
		MEXMEPacienteRelCatalog catalog = null;
		catalog = new MEXMEPacienteRelCatalog(Env.getCtx(), 0, trxName);
		catalog.setEXME_Paciente_ID(pacID);
		catalog.setEXME_PacienteRel_ID(pacRelID);
		catalog.setEXME_Parentesco_ID(parenID);
		catalog.setType(MEXMEPacienteRelCatalog.TYPE_Responsible);
		catalog.setIsDefault(true);
		catalog.setIsPatient(Boolean.TRUE);
		if (catalog.save()){
			return true;
		}else{
			s_log.log(Level.SEVERE, "Fallo al crear PacienteRelCatalog: PacienteID = "+pacID);
			return false;
		}
		
	}
	
	/**
	 * Crear el guarantor
	 * 
	 * @param trxName
	 *            TrxName
	 * @return Si fue creado o no
	 */
	
	public static boolean updateFromPatient(int pacID, int parenID, int pacRelID, boolean isSame, String trxName) {
		MEXMEPacienteRelCatalog catalog = get(Env.getCtx(),pacID,TYPE_Responsible, Boolean.FALSE,0, trxName);
		catalog.setEXME_PacienteRel_ID(pacRelID);
		catalog.setEXME_Parentesco_ID(parenID);
		catalog.setIsPatient(isSame);
		if (catalog.save()){
			return true;
		}else{
			s_log.log(Level.SEVERE, "Fallo al actualizar PacienteRelCatalog: PacienteID = "+pacID);
			return false;
		}
		
	}

	public static boolean deactivate(Properties ctx, int pacID, String type, boolean isEncounter , int ctaPacID, String get_TrxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		Object[] params = null;
		ResultSet resultSet = null; 
		PreparedStatement pstmt = null;
		MEXMEPacienteRelCatalog guarantor = null;
		try {
			sql.append("SELECT PRC.* FROM EXME_PacienteRelCatalog PRC ");
			sql.append("WHERE PRC.isActive = 'Y' ");
			sql.append("AND PRC.Type = ?  ");
			sql.append("AND PRC.EXME_Paciente_ID = ? ");
			if (isEncounter){
				sql.append("AND PRC.EXME_CtaPac_ID = ? ");
			}else{
				sql.append("AND PRC.EXME_CtaPac_ID IS NULL ");
			}
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "PRC"));
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			
			if (isEncounter){
				params= new Object[] {type, pacID, ctaPacID};
			}else{
				params= new Object[] {type, pacID};
			}
			

			DB.setParameters(pstmt, params);

			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				guarantor = new MEXMEPacienteRelCatalog(ctx, resultSet, get_TrxName);
			}
			guarantor.setIsActive(Boolean.FALSE);
			
		} catch (Exception e) {
			final StringBuffer parVal = new StringBuffer();
			
			if(params != null) {
				for (Object parameter : params) {
					parVal.append(parameter).append('\n');
				}
			}
			
			s_log.log(Level.SEVERE, sql.toString() + '\n' + parVal.toString(), e);
		} finally {
			DB.close(resultSet, pstmt);
		}
		return guarantor.save();
		
	}
	
	public static MEXMEPacienteRelCatalog get(Properties ctx, int pacID, String type, boolean isEncounter , int ctaPacID, String get_TrxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		Object[] params = null;
		ResultSet resultSet = null; 
		PreparedStatement pstmt = null;
		MEXMEPacienteRelCatalog guarantor = null;
		try {
			sql.append("SELECT PRC.* FROM EXME_PacienteRelCatalog PRC ");
			sql.append("WHERE PRC.isActive = 'Y' ");
			sql.append("AND PRC.Type = ?  ");
			sql.append("AND PRC.EXME_Paciente_ID = ? ");
			if (isEncounter){
				sql.append("AND PRC.EXME_CtaPac_ID = ? ");
			}else{
				sql.append("AND PRC.EXME_CtaPac_ID IS NULL ");
			}
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "PRC"));
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			
			if (isEncounter){
				params= new Object[] {type, pacID, ctaPacID};
			}else{
				params= new Object[] {type, pacID};
			}
			

			DB.setParameters(pstmt, params);

			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				guarantor = new MEXMEPacienteRelCatalog(ctx, resultSet, get_TrxName);
			}
			
			
		} catch (Exception e) {
			final StringBuffer parVal = new StringBuffer();
			
			if(params != null) {
				for (Object parameter : params) {
					parVal.append(parameter).append('\n');
				}
			}
			
			s_log.log(Level.SEVERE, sql.toString() + '\n' + parVal.toString(), e);
		} finally {
			DB.close(resultSet, pstmt);
		}
		return guarantor;
		
	}

}
