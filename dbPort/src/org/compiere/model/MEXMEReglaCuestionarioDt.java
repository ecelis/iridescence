package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.ReglaVistaCuest;

public class MEXMEReglaCuestionarioDt extends X_EXME_ReglaCuestionarioDt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEReglaCuestionarioDt.class);

	public MEXMEReglaCuestionarioDt(Properties ctx, int EXME_ReglaCuestionarioDt_ID, String trxName) {
		super(ctx, EXME_ReglaCuestionarioDt_ID, trxName);
		if (EXME_ReglaCuestionarioDt_ID == 0) {
			setFromDB(false);
		}
	}

	public MEXMEReglaCuestionarioDt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		setFromDB(true);
	}

	public static MEXMEReglaCuestionarioDt getReglaCuestionarioDt(Properties ctx, int exme_reglacuestionario_id, String trxName) throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMEReglaCuestionarioDt resultado = null;
		try {
			if (ctx == null)
				return null;

			sql.append("SELECT * ").append(" FROM EXME_ReglaCuestionarioDt").append(" WHERE  EXME_ReglaCuestionario_ID=? ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, exme_reglacuestionario_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resultado = new MEXMEReglaCuestionarioDt(ctx, rs.getInt("EXME_ReglaCuestionarioDt_ID"), null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "", e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}
		return resultado;
	}

	public static void elimDetalle(Properties ctx, int exme_reglacuestionario_id, String trxName) throws Exception {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
			sql.append("SELECT EXME_ReglaCuestionarioDT_ID FROM EXME_ReglaCuestionarioDT ").append("WHERE EXME_ReglaCuestionario_ID = ? ");
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, exme_reglacuestionario_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEReglaCuestionarioDt obj = new MEXMEReglaCuestionarioDt(ctx, rs, null);
				if (!obj.delete(true, trxName)) {
					s_log.log(Level.SEVERE, "error.insulinas.registro.eliminar");
					throw new Exception();
				}
				obj = null;
			}
			/*
			 * sql.append("delete from exme_reglacuestionariodt ") .append("where exme_reglacuestionario_id=? "); pstmt = DB.prepareStatement(sql.toString(),null); pstmt.setInt(1,
			 * exme_reglacuestionario_id); rs=pstmt.executeQuery();
			 */

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}
	}

	public static void elimDetalle(Properties ctx, int exme_reglacuestionario_id, int exme_pregunta_id, String trxName) throws Exception {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT EXME_ReglaCuestionarioDT_ID FROM EXME_ReglaCuestionarioDT ").append("WHERE EXME_ReglaCuestionario_ID = ?  AND EXME_Pregunta_ID = ?");
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, exme_reglacuestionario_id);
			pstmt.setInt(2, exme_pregunta_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEReglaCuestionarioDt obj = new MEXMEReglaCuestionarioDt(ctx, rs, null);
				if (!obj.delete(true, trxName)) {
					s_log.log(Level.SEVERE, "error.insulinas.registro.eliminar");
					throw new Exception();
				}
				obj = null;
			}
			/*
			 * sql.append("delete from exme_reglacuestionariodt ") .append("where exme_reglacuestionario_id=? "); pstmt = DB.prepareStatement(sql.toString(),null); pstmt.setInt(1,
			 * exme_reglacuestionario_id); rs=pstmt.executeQuery();
			 */

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}
	}

	public static ReglaVistaCuest entero(int exme_preg_cond, int exme_cuestionario_id) throws Exception {
		ReglaVistaCuest vista = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			sql.append("	SELECT * FROM exme_reglacuestionario er ").append("	INNER JOIN exme_reglacuestionariodt edt ").append("	ON er.exme_reglacuestionario_id= edt.exme_reglacuestionario_id ").append("	WHERE er.exme_cuestionario_id=? and er.preg_condicionante=?");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, exme_cuestionario_id);
			pstmt.setInt(2, exme_preg_cond);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vista = new ReglaVistaCuest();
				vista.setExme_cuestionario_id(rs.getInt("EXME_ReglaCuestionario_ID"));
				vista.setExme_cuestionario_id(rs.getInt("EXME_Cuestionario_ID"));
				vista.setExme_pregunta_id(rs.getInt("EXME_Pregunta_ID"));
				vista.setExme_tipo_pregunta(rs.getInt("EXME_TipoPregunta_ID"));
				vista.setPreg_condicionante(rs.getInt("Preg_Condicionante"));
				vista.setExme_pregunta_lista_id(rs.getInt("EXME_Pregunta_Lista_ID"));
				vista.setRespuestaTxt(rs.getString("Respuesta"));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
			sql = null;
		}

		return vista;
	}

	private boolean fromDB = true;

	public boolean isFromDB() {
		return fromDB;
	}

	public void setFromDB(boolean fromDB) {
		this.fromDB = fromDB;
	}

}
