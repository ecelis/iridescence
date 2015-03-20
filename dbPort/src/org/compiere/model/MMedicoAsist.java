/*
 * Created on 7/05/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

public class MMedicoAsist extends X_EXME_MedicoAsist{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3934311407537421950L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MMedicoAsist.class);

	/**
	 * 
	 * @param ctx
	 * @param EXME_MedicoAsist_ID
	 * @param trxName
	 */
	public MMedicoAsist (Properties ctx, int EXME_MedicoAsist_ID, String trxName){
		super(ctx, EXME_MedicoAsist_ID, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MMedicoAsist (Properties ctx, ResultSet rs, String trxName){
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param EXME_Asistente_ID
	 * @param trxName
	 * @return
	 *
	public static MMedicoAsist[] get(Properties ctx, int EXME_Asistente_ID, String trxName){
		ArrayList<MMedicoAsist> lista = new ArrayList<MMedicoAsist>();

		if(ctx == null){
			return null;
		}

		if(EXME_Asistente_ID <= 0){
			return null;
		}

		MMedicoAsist retValue = null;
		StringBuilder sqlB = new StringBuilder(); 

		sqlB.append(" SELECT * FROM EXME_MedicoAsist ")
		.append(" INNER JOIN EXME_Medico on (EXME_Medico.EXME_MEDICO_ID = EXME_MedicoAsist.EXME_MEDICO_ID) ")
		.append(" WHERE EXME_MedicoAsist.IsActive='Y' AND EXME_MedicoAsist.EXME_Asistente_ID = ? ")
		.append(" AND EXME_Medico.isActive = 'Y' ");

		sqlB = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sqlB.toString(),"EXME_MedicoAsist"));  	

		sqlB.append(" ORDER BY EXME_Medico.apellido1");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{	
			pstmt = DB.prepareStatement (sqlB.toString(), null);
			pstmt.setInt(1,EXME_Asistente_ID);
			rs = pstmt.executeQuery ();
			while (rs.next()) {
				retValue = new MMedicoAsist(ctx, rs, trxName);
				lista.add(retValue);
			}

		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}finally {
			DB.close(rs, pstmt);
		}

		MMedicoAsist[] listMedAs = new MMedicoAsist[lista.size()];
		lista.toArray(listMedAs);
		return listMedAs;
	}*/

	/**
	 * 
	 * @param ctx
	 * @param EXME_Asistente_ID
	 * @param trxName
	 * @return
	 *
	public static List<MMedicoAsist> getLstMedAsist(Properties ctx, int EXME_Asistente_ID, String trxName){

//		List<MMedicoAsist> lista = new ArrayList<MMedicoAsist>();
//		try {
//			//cambiar de arreglo a lista
//			MMedicoAsist[] detalle = get(ctx, EXME_Asistente_ID, trxName);
//			for (int i = 0; i < detalle.length; i++) {
//				MMedicoAsist line = detalle[i];
//				lista.add(new MMedicoAsist(ctx, line.getEXME_MedicoAsist_ID(), trxName));
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
//		}
		List<MMedicoAsist> lista = new ArrayList<MMedicoAsist>();

		if (ctx == null || EXME_Asistente_ID <= 0) {
			return null;
		}

		StringBuilder sqlB = new StringBuilder();

		sqlB.append(" SELECT * FROM EXME_MedicoAsist ")
			.append(" INNER JOIN EXME_Medico on (EXME_Medico.EXME_MEDICO_ID = EXME_MedicoAsist.EXME_MEDICO_ID) ")
			.append(" WHERE EXME_MedicoAsist.IsActive='Y' AND EXME_MedicoAsist.EXME_Asistente_ID = ? ").append(" AND EXME_Medico.isActive = 'Y' ");
		sqlB.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_MedicoAsist"));

		sqlB.append(" ORDER BY EXME_Medico.apellido1");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sqlB.toString(), null);
			pstmt.setInt(1, EXME_Asistente_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MMedicoAsist(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}*/

	/**@deprecated solo usado por odontologia struts*/
	public static boolean tieneAccesoMedicoAsist(Properties ctx, int medico_id, 
			int asistente_id, int empresa_id, String trxName)
	throws Exception {
//		Boolean retValue = true;
//		StringBuilder sql = new StringBuilder();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			MEXMEMedico medico = new MEXMEMedico(ctx, medico_id, trxName);
//			if (medico.getAD_Client_ID() == empresa_id) {
//
//				//string con el sql
//				sql.append(" SELECT EXME_MedicoAsist.EXME_Medico_ID ")
//				.append(" FROM EXME_MedicoAsist ")
//				.append(" WHERE EXME_MedicoAsist.EXME_Medico_ID = ? AND ")
//				.append(" EXME_MedicoAsist.EXME_Asistente_ID = ? AND ")
//				.append(" EXME_MedicoAsist.IsActive = 'Y' ");
//
//				String sSql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_MedicoAsist");
//
//				pstmt = DB.prepareStatement(sSql, null);
//				pstmt.setInt(1, medico_id);
//				pstmt.setInt(2, asistente_id);
//				rs = pstmt.executeQuery();
//
//				if (!rs.next()) {
//					//throw new Exception("error.accesoMedicoAsist");
//					retValue = false;
//				}
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//			throw new Exception("error.accesoMedicoAsist");
//		}finally {
//			DB.close(rs, pstmt);
//		}

		return false;
	}

	/**@ deprecated cambiar por {@link KeyNamePair} 
	public static List<LabelValueBean> getMed(Properties ctx, int assistantID,String trxName){

		List<LabelValueBean> retLst = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT EXME_Medico.Nombre_Med, EXME_Medico.EXME_Medico_ID")
			.append(" FROM EXME_MedicoAsist  ")
			.append(" INNER JOIN EXME_Medico on (EXME_Medico.EXME_Medico_ID = EXME_MedicoAsist.EXME_Medico_ID)")
			.append(" INNER JOIN EXME_Medico_Org mo on (EXME_Medico.EXME_Medico_ID = mo.EXME_Medico_ID)")
			.append(" WHERE EXME_MedicoAsist.EXME_Asistente_ID = ? AND EXME_MedicoAsist.isActive = 'Y'")
//			.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",MMedicoAsist.Table_Name))
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",MEXMEMedicoOrg.Table_Name, "mo"))
			;
			sql.append(" order by EXME_Medico.nombre_med");

			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, assistantID);

			rs = psmt.executeQuery();
			while (rs.next()) {
				LabelValueBean retValue = new LabelValueBean(rs.getString(1),rs.getString(2));
				retLst.add(retValue);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, psmt);
		}

		return retLst;	
	}*/
	
	public static List<KeyNamePair> getPhysicians(Properties ctx, int assistantID,String trxName){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_Medico.EXME_Medico_ID, EXME_Medico.Nombre_Med ");
		sql.append(" FROM EXME_MedicoAsist  ");
//		sql.append(" INNER JOIN EXME_ASISTENTE ON (EXME_Asistente.EXME_Asistente_ID = EXME_MedicoAsist.EXME_Asistente_ID");
//		sql.append(" AND EXME_MedicoAsist.EXME_Asistente_ID = ? ) ");
		sql.append(" INNER JOIN EXME_Medico_Org mo on (EXME_MedicoAsist.EXME_Medico_ID = mo.EXME_Medico_ID)");
		sql.append(" INNER JOIN EXME_Medico on (EXME_Medico.EXME_Medico_ID = EXME_MedicoAsist.EXME_Medico_ID)");
		sql.append(" WHERE EXME_MedicoAsist.EXME_Asistente_ID = ? AND EXME_MedicoAsist.isActive = 'Y' ");
		// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",MMedicoAsist.Table_Name));
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",MEXMEMedicoOrg.Table_Name, "mo"));
		sql.append(" order by EXME_Medico.nombre_med");
		return Query.getListKN(sql.toString(), trxName, assistantID);	
	}

	public static List<MMedicoAsist> get(Properties ctx, int medico_ID, int assistantID, String trxName){
		MMedicoAsist retValue = null;
		List<MMedicoAsist> retLst = new ArrayList<MMedicoAsist>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT * FROM EXME_MedicoAsist WHERE isActive = 'Y' ");

			if(medico_ID > 0)
				sql.append("AND EXME_Medico_ID = ?");
			if(assistantID > 0)
				sql.append("AND EXME_Asistente_ID = ?");

			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",MMedicoAsist.Table_Name));
			//.append(" ORDER BY secuencia ");

			psmt = DB.prepareStatement(sql.toString(), trxName);
			int i = 0;
			if(medico_ID > 0)
				psmt.setInt(++i, medico_ID);
			if(assistantID > 0)
				psmt.setInt(++i, assistantID);

			rs = psmt.executeQuery();

			while (rs.next()) {
				retValue = new MMedicoAsist(ctx, rs, trxName);
				retLst.add(retValue);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, psmt);
		}

		return retLst;

	}


	private MEXMEMedico m_medico = null;
	public MEXMEMedico getMedico() {

		if(m_medico == null)
			m_medico = new MEXMEMedico(getCtx(),getEXME_Medico_ID(),get_TrxName());
		return m_medico;

	}

}
