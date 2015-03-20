package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEEnfermeriaEsp extends X_EXME_EnfermeriaEsp {

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEEnfermeriaEsp.class);

	public MEXMEEnfermeriaEsp(Properties ctx, int EXME_EnfermeriaEsp_ID,
			String trxName) {
		super(ctx, EXME_EnfermeriaEsp_ID, trxName);
	}

	public MEXMEEnfermeriaEsp(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
/*	@Deprecated
	public static List<Especialidad_VO> getEspecialidades(Properties ctx,
			int EXME_Enfermeria_ID, String trxName) {
		StringBuilder st = new StringBuilder(
				"select esp.EXME_Especialidad_ID as id, esp.NAME as name from EXME_EnfermeriaEsp enesp ")
				.append(
						"inner join EXME_Especialidad esp on esp.EXME_Especialidad_ID = enesp.EXME_Especialidad_ID ")
				.append("where enesp.EXME_Enfermeria_ID = ? ").append(
						"AND enesp.isActive = 'Y' ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st
				.toString(), "EXME_EnfermeriaEsp", "enesp"));

		List<Especialidad_VO> lista = new ArrayList<Especialidad_VO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, EXME_Enfermeria_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Especialidad_VO tmp = new Especialidad_VO();
				tmp.setEspecialidadID(rs.getInt("id"));
				tmp.setEspecialidadName(rs.getString("name"));
				lista.add(tmp);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}*/
	
	/**
	 * Obtener un hash de los cuestionarios por especialidad </br>relacionadas a
	 * una enfermera
	 * 
	 * @param ctx
	 *            Contexto
	 * @param enfId
	 *            Enfermera
	 * @param trxName
	 *            Trx
	 * @return Hash de cuestionarios por especialidad
	 */
	public static HashMap<Integer, List<Integer>> getQuestionnaires(Properties ctx, int enfId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  ee.exme_especialidad_id, ");
		sql.append("  sf.exme_cuestionario_id ");
		sql.append("FROM ");
		sql.append("  exme_enfermeriaesp ee ");
		sql.append("  INNER JOIN exme_specialtyform sf ");
		sql.append("  ON ee.exme_especialidad_id = sf.exme_especialidad_id ");
		sql.append("WHERE ");
		sql.append("  ee.exme_enfermeria_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "ee"));
		HashMap<Integer, List<Integer>> hashMap = new HashMap<Integer, List<Integer>>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, enfId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				List<Integer> ids = hashMap.get(rs.getInt(1));
				if (ids == null) {
					ids = new ArrayList<Integer>();
				}
				ids.add(rs.getInt(2));
				hashMap.put(rs.getInt(1), ids);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return hashMap;
	}
	
	/**
	 * Regresa un listado de especialidade
	 * @param ctx
	 * @param EXME_Enfermeria_ID
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> getLstEspecialidades(Properties ctx, int EXME_Enfermeria_ID, String trxName) {
		
		StringBuilder st = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		st.append("select esp.EXME_Especialidad_ID as id, esp.NAME as name from EXME_EnfermeriaEsp enesp ")
			.append("inner join EXME_Especialidad esp on esp.EXME_Especialidad_ID = enesp.EXME_Especialidad_ID and esp.ISMED = 'N' ")
			.append("where enesp.EXME_Enfermeria_ID = ? ")
			.append("AND enesp.isActive = 'Y' ");
		st.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "enesp"));

		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, EXME_Enfermeria_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LabelValueBean tmp = new LabelValueBean(rs.getString("name"), rs.getString("id"));
				lista.add(tmp);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}

}
