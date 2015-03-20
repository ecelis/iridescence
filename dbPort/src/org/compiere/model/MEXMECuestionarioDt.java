package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Modelo del detalle de cuestionario.
 * 
 * @author mrojas
 * @version $Revision: 1.2 $
 */
public class MEXMECuestionarioDt extends X_EXME_CuestionarioDt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger
			.getCLogger(MEXMECuestionarioDt.class);

	/**
	 * @param ctx
	 * @param EXME_Cuestionario_ID
	 * @param trxName
	 */
	public MEXMECuestionarioDt(Properties ctx, int EXME_CuestionarioDt_ID,
			String trxName) {
		super(ctx, EXME_CuestionarioDt_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECuestionarioDt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtenemos el detalle del cuestionario. (Lama)
	 * 
	 * @param ctx
	 * @param whereClause
	 * @param trxName
	 * @return MCuestionarioDt
	 */
	public static MEXMECuestionarioDt get(Properties ctx, String whereClause,
			String trxName) {

		MEXMECuestionarioDt cuest = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_CuestionarioDt WHERE isActive = 'Y' ");

		if (whereClause != null)
			sql.append(whereClause);

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
				"EXME_CuestionarioDt"));

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				cuest = new MEXMECuestionarioDt(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return cuest;
	}

	public MPregunta getMPregunta() {
		if (getEXME_Pregunta_ID() != 0)
			return new MPregunta(this.getCtx(), getEXME_Pregunta_ID(),
					this.get_TrxName());
		return null;
	} //

	public MTipoPregunta getMTipoPregunta() {
		if (getEXME_TipoPregunta_ID() != 0)
			return new MTipoPregunta(this.getCtx(), getEXME_TipoPregunta_ID(),
					this.get_TrxName());
		return null;
	} //

	/** Obtiene las preguntas del cuestionario de una secuencia anterior */
	public List<MEXMECuestionarioDt> getSecuenciasAnteriores() {
		List<MEXMECuestionarioDt> anteriores = new ArrayList<MEXMECuestionarioDt>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM EXME_CuestionarioDT WHERE EXME_Cuestionario_ID = ?  AND SECUENCIA < ?");

		ResultSet rs = null;
		PreparedStatement stmt = DB.prepareStatement(sql.toString(), null);

		try {
			stmt.setInt(1, this.getEXME_Cuestionario_ID());
			stmt.setInt(2, getSecuencia());

			rs = stmt.executeQuery();

			while (rs.next()) {

				anteriores.add(new MEXMECuestionarioDt(Env.getCtx(), rs, null));

			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, stmt);
		}
		return anteriores;
	}

	public MEXMEReglaCuestionario getRule() {

		MEXMEReglaCuestionario regla = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM EXME_ReglaCuestionario WHERE EXME_Cuestionario_ID = ? AND ");
		sql.append(" PREG_Condicionante = ? ");

		ResultSet rs = null;
		PreparedStatement stmt = DB.prepareStatement(sql.toString(), null);

		try {

			stmt.setInt(1, getEXME_Cuestionario_ID());
			stmt.setInt(2, getEXME_Pregunta_ID());
			rs = stmt.executeQuery();
			while (rs.next()) {
				regla = new MEXMEReglaCuestionario(getCtx(), rs, null);
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "geting Rule", e);
		} finally {
			DB.close(rs, stmt);
		}

		return regla;
	}
	
	private MEXMETipoPregunta tipoPregunta = null;

	/**
	 * Tipo de pregunta
	 * 
	 * @return
	 */
	public MEXMETipoPregunta getTipoPregunta() {
		if (tipoPregunta == null) {
			tipoPregunta = new MEXMETipoPregunta(getCtx(), getEXME_TipoPregunta_ID(), null);
		}
		return tipoPregunta;
	}

}