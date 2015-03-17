package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.QuickSearchTables;

import com.ecaresoft.api.Generic;

public class MEXMEMedicoOrg extends X_EXME_Medico_Org {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Static Logger */
	private static CLogger log = CLogger.getCLogger(MEXMEMedicoOrg.class);
	
	public MEXMEMedicoOrg(Properties ctx, int EXME_Medico_Org_ID, String trxName) {
		super(ctx, EXME_Medico_Org_ID, trxName);
	}
	
	public MEXMEMedicoOrg(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Obtener el turno de un médico.
	 * @param ctx
	 * @param medicoId
	 * @return
	 */
	public static int getTurnoMedico(Properties ctx, int medicoId){
//		int turnoId = 0;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		sql.append("SELECT EXME_Medico_Org.EXME_Turnos_ID FROM EXME_Medico_Org ")
		.append("WHERE EXME_Medico_Org.EXME_Medico_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMedicoOrg.Table_Name));
//		try{
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, medicoId); 
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				turnoId = rs.getInt(1);
//			}
//
//		} catch(SQLException e){
//			log.log(Level.SEVERE, "getTurnoMedico " + e.getMessage());
//		} finally{
//			DB.close(rs,pstmt);
//		}
		return DB.getSQLValue(null, sql.toString(), medicoId);
	}
	
	
	/**
	 * Obtener el producto relacionado al médico.
	 * @param ctx
	 * @param medicoId
	 * @return
	 */
	public static int getProductoMedico(Properties ctx, int medicoId){
//		int productoId = 0;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		sql.append("SELECT EXME_Medico_Org.M_Product_ID FROM EXME_Medico_Org ")
		.append("WHERE EXME_Medico_Org.EXME_Medico_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMedicoOrg.Table_Name));
//		try{
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, medicoId); 
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				productoId = rs.getInt(1);
//			}
//
//		} catch(SQLException e){
//			log.log(Level.SEVERE, "getProductoMedico " + e.getMessage());
//		} finally{
//			DB.close(rs,pstmt);
//		}
		return DB.getSQLValue(null, sql.toString(), medicoId);
	}

	/**
	 * Retotna un objeto MEXMEMedicoOrg
	 * @param ctx
	 * @param medicoId
	 * @return
	 */
	public static MEXMEMedicoOrg getMedicoOrg(Properties ctx, int medicoId){
//		MEXMEMedicoOrg medicoOrg = null;
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql.append("SELECT EXME_Medico_Org.* FROM EXME_Medico_Org ")
//		.append("WHERE EXME_Medico_Org.EXME_Medico_ID = ? ");
//
//
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMEMedicoOrg.Table_Name));
//
//		try{
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, medicoId); 
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				medicoOrg = new MEXMEMedicoOrg(ctx, rs, null);
//			}
//
//		} catch(SQLException e){
//			log.log(Level.SEVERE, "getMedicoOrg" + e.getLocalizedMessage(), e);
//		} finally{
//			DB.close(rs,pstmt);
//		}
		return new Query(ctx, Table_Name, " EXME_Medico_Org.EXME_Medico_ID=? ", null)//
			.setParameters(medicoId)//
			.setOnlyActiveRecords(true)// ?
			.addAccessLevelSQL(true)//
			.first();
	}
	
	
	
	/**
	 * regresa los primeros 10 caracteres del SPI del medico en caso de exisitr
	 * @param medicoId
	 * @return
	 */
	public static String getSPIRoot(int medicoId){
//		String spiRoot = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		sql.append("SELECT DISTINCT SUBSTR(SPI,0,10) FROM EXME_MEDICO_ORG")
		.append(" WHERE EXME_MEDICO_ID =? AND SPI IS NOT NULL ");
//		try{
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, medicoId); 
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				spiRoot = rs.getString(1);
//			}

//		} catch(SQLException e){
//			log.log(Level.SEVERE, "getSPIRoot" + e.getLocalizedMessage(), e);
//		} finally{
//			DB.close(rs,pstmt);
//		}
		return DB.getSQLValueString(null, sql.toString(), medicoId);
	}
	
	/**
	 * Validar que el medico tenga acceso a la organizacion logeada
	 * 
	 *@param medico El identificador del medico
	 *@param organizacion El identificador de la la organizacion logeada
	 *@exception Exception 
	 *@deprecated solo usada en clases Action
	 */
	public static void accesoOrg(long medico, int organizacion) throws Exception {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql .append("SELECT DISTINCT AD_Role_OrgAccess.AD_Org_ID ")
			 .append("FROM AD_Role ")
			 .append(" INNER JOIN AD_Role_OrgAccess ON  AD_Role_OrgAccess.AD_Role_ID = AD_Role.AD_Role_ID ")
			 .append(" INNER JOIN AD_User_Roles ON  AD_User_Roles.AD_Role_ID = AD_Role.AD_Role_ID ")
			 .append(" INNER JOIN EXME_Medico_Org ON  EXME_Medico_Org.AD_User_ID = AD_User_Roles.AD_User_ID ")
			 .append(" WHERE EXME_Medico_Org.EXME_Medico_ID = ? ")
			 .append(" AND AD_Role_OrgAccess.AD_Org_ID = ? ")
			 .append(" AND AD_Role.IsActive = 'Y' ")
			 .append(" AND AD_Role_OrgAccess.IsActive = 'Y' ")
			 .append(" AND AD_User_Roles.IsActive = 'Y' ")
			 .append(" AND EXME_Medico_Org.IsActive = 'Y' ");
//		try {
			if (DB.getSQLValue(null, sql.toString(), medico, organizacion) <= 0) {
				log.log(Level.SEVERE, sql.toString(), "error.medico.noOrg");
				throw new Exception("error.medico.noOrg");// debe regresar la excepcion
			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, sql.toString(), e);
//			throw e; // debe regresar la excepcion
//		}
	}
	
	/** @deprecated use KeyNamePair {@link #getPhysiciansByOrg(Properties, int, String)} */
	public static List<LabelValueBean> getPhysicianByOrg(Properties ctx, int AD_Org_ID, String trxName){

		List<LabelValueBean> lst = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT physician.* FROM EXME_Medico physician")
		.append(" INNER JOIN EXME_Medico_Org org ON org.EXME_Medico_ID = physician.EXME_Medico_ID ")
		.append(" WHERE org.AD_Org_ID = ? ");

		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Org_ID); 

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEMedico physician = new MEXMEMedico(ctx, rs, trxName);
				lst.add(new LabelValueBean(physician.getFullName(), String.valueOf(physician.getEXME_Medico_ID())));
			}

		} catch(SQLException e){
			log.log(Level.SEVERE, "getMedicoOrg" + e.getLocalizedMessage(), e);
		} finally{
			DB.close(rs,pstmt);
		}

		return lst;

	}
	
	/** @deprecated use KeyNamePair {@link #getPhysiciansByOrg(Properties, int, String)} */
	public static List<Generic> getPhysicianByOrgGeneric(Properties ctx, int AD_Org_ID, String trxName){
		List<Generic> lst = new ArrayList<Generic>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT physician.* FROM EXME_Medico physician")
		.append(" INNER JOIN EXME_Medico_Org org ON org.EXME_Medico_ID = physician.EXME_Medico_ID ")
		.append(" WHERE org.AD_Org_ID = ? ");

		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Org_ID); 

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEMedico physician = new MEXMEMedico(ctx, rs, trxName);
				lst.add(new Generic(physician.getFullName(), String.valueOf(physician.getEXME_Medico_ID())));
			}

		} catch(SQLException e){
			log.log(Level.SEVERE, "getMedicoOrg" + e.getLocalizedMessage(), e);
		} finally{
			DB.close(rs,pstmt);
		}

		return lst;

	}
	
	public static List<KeyNamePair> getPhysiciansByOrg(Properties ctx, int AD_Org_ID, String trxName){
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT physician.EXME_Medico_ID, physician.Nombre_Med FROM EXME_Medico physician")
		.append(" INNER JOIN EXME_Medico_Org org ON org.EXME_Medico_ID = physician.EXME_Medico_ID ")
		.append(" WHERE org.AD_Org_ID = ? ");

		return Query.getListKN(sql.toString(), trxName, AD_Org_ID);

	}

	/** Obtiene Medico org atravez del SPI, el indentificador de surescripts del medico
	 * TODO agregas mas campos a la consulta segun identificacion de Request*/
	public static MEXMEMedicoOrg getMedicoOrg(Properties ctx, String spi) {
//		MEXMEMedicoOrg medicoOrg = null;
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql.append("SELECT EXME_Medico_Org.* FROM EXME_Medico_Org ")
//		.append("WHERE isActive = 'Y' AND EXME_Medico_Org.SPI = ? ");
//
//		try{
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setString(1, spi);
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				medicoOrg = new MEXMEMedicoOrg(ctx, rs, null);
//			}
//
//		} catch(SQLException e){
//			log.log(Level.SEVERE, "getMedicoOrg" + e.getLocalizedMessage(), e);
//		} finally{
//			DB.close(rs,pstmt);
//		}
		return new Query(ctx, Table_Name, " EXME_Medico_Org.SPI=? ", null)//
			.setParameters(spi)//
			.setOnlyActiveRecords(true)//
			// .addAccessLevelSQL(true)// ?
			.first();

	}

	/**
	 * Regresa el usuario relacionado a un medico.
	 * Si el usuario es Medico, regresa el AD_User_ID del contexto (opcional).
	 * Si no, busca el usuario en la tabla de EXME_Medico_Org
	 * 
	 * @param ctx - Contexto
	 * @param medicoId - Id del medico a buscar en EXME_Medico_Org
	 * @param useDoctorCtx
	 *            true: regresa el ID del usuario del ctx en caso que el usuario sea medico.
	 *            false: busca en EXME_Medico_Org
	 * @return AD_User_ID
	 */
	public static int getUserId(final Properties ctx, final int medicoId, final boolean useDoctorCtx) {
		int retValue;
		if (useDoctorCtx && Env.getEXME_Medico_ID(ctx) > 0) {
			retValue = Env.getAD_User_ID(ctx);
		} else {
			retValue =
				DB.getSQLValue(null,
					MEXMELookupInfo.addAccessLevelSQL(ctx, "SELECT AD_User_ID FROM EXME_Medico_Org WHERE EXME_Medico_ID=?", MEXMEMedicoOrg.Table_ID),
					medicoId);
		}
		return retValue;
	}
	
	
	/**
	 * After Save
	 * @param newRecord new
	 * @param success success
	 * @return success
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {

		if (!success){
			return success;
		}
		
		try {
			QuickSearchTables.checkTables(MEXMEMedico.class,MEXMEMedico.Table_Name, getEXME_Medico_ID(), get_TrxName() , p_ctx);
		} catch (Exception ex) {
			log.log(Level.WARNING, "afterSave.QuickSearchTables.checkTables", ex);
		}
		return true;
	}
}
