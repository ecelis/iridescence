package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Utilerias;
import org.compiere.util.cuestionarios.Regla_VO;

public class MEXMEReglaCuestionario extends X_EXME_ReglaCuestionario {
	private static CLogger logger = CLogger.getCLogger(MEXMEReglaCuestionario.class);
	private static final long serialVersionUID = 1L;

	public static void elimDetalle(Properties ctx, int EXME_Cuestionario_ID, String trxName) throws Exception {

		// Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT EXME_ReglaCuestionario_ID FROM EXME_ReglaCuestionario").append(" WHERE EXME_Cuestionario_ID = ?  ");
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Cuestionario_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEReglaCuestionario reglaCuest = new MEXMEReglaCuestionario(ctx, rs, null);
				if (!reglaCuest.delete(true, trxName)) {
					logger.log(Level.SEVERE, "error.insulinas.registro.eliminar");
					throw new MedsysException(Utilerias.getMsg(ctx, "error.insulinas.registro.eliminar"));
				}
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, sql.toString(), e.getMessage());
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}

	}

	public static List<MPregunta> getPreg(Properties ctx, long exme_cuestionario_id, String trxName) throws Exception {
		List<MPregunta> lista = new ArrayList<MPregunta>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MPregunta preg = null;
		try {
			sql.append("SELECT exme_pregunta_id FROM EXME_REGLACUESTIONARIO ").append("WHERE exme_cuestionario_id= ? ");
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, exme_cuestionario_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("EXME_Pregunta_Id") != 0) {
					preg = new MPregunta(ctx, rs.getInt("EXME_Pregunta_Id"), null);
					lista.add(preg);
				}
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "", e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}

		return lista;
	}

	public static List<Regla_VO> getPreguntasCuestionario(Integer pregunta, Integer cuestionario) throws Exception {
		// lista con las preguntas
		List<Regla_VO> lstReglas = new ArrayList<Regla_VO>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	select").append(" 	r.exme_cuestionario_id,").append(" 	r.preg_condicionante,").append(" 	r.preg_condicionante,").append(" 	r.exme_pregunta_id,").append(" 	r.exme_tipopregunta_id,").append(" 	rd.respuesta,").append(" 	rd.exme_pregunta_lista_id,").append(" 	lista.name")
				.append(" 	from exme_reglacuestionario r").append(" 	inner join exme_reglacuestionariodt rd on rd.exme_reglacuestionario_id=r.exme_reglacuestionario_id").append(" 	left join exme_pregunta_lista lista on lista.exme_pregunta_lista_id=rd.exme_pregunta_lista_id")
				.append(" 	where r.exme_cuestionario_id=?").append(" 	and r.exme_pregunta_id = ?");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, cuestionario.intValue());
			pstmt.setInt(2, pregunta.intValue());
			rs = pstmt.executeQuery();
			Regla_VO reg = null;
			while (rs.next()) {

				reg = new Regla_VO();

				reg.setCuestionarioId(rs.getInt(1));
				reg.setPreguntaId(rs.getInt(2));
				reg.setPregCondicionante(rs.getInt(3));
				reg.setPregModifica(rs.getInt(4));
				reg.setTipoPregunta(rs.getInt(5));
				reg.setBanderaResp(rs.getString(6));
				reg.setListaId(rs.getInt(7));
				reg.setRespLista(rs.getString(8));

				lstReglas.add(reg);
			}

		} catch (Exception e) {
			logger.log(Level.WARNING, "", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lstReglas;
	}

	public static List<Regla_VO> getPreguntasCuestionarioForms(Integer pregunta, Integer cuestionario) throws Exception {
		// lista con las preguntas
		List<Regla_VO> lstReglas = new ArrayList<Regla_VO>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	select").append(" 	rr.exme_cuestionario_id,").append(" 	rr.preg_condicionante,").append(" 	rr.preg_condicionante,").append(" 	r.exme_pregunta_id,").append(" 	rr.exme_tipopregunta_id,").append(" 	rr.respuesta,").append(" 	r.exme_pregunta_lista_id,").append(" 	lista.name,  ")

		.append(" 	cu.EXME_CuestionarioDt_id, ").append(" 	r.respuesta, ").append(" 	r.exme_pregunta_lista_id")

		.append(" 	FROM EXME_CuestionarioDt cu  ").append(" 	INNER JOIN exme_pregunta p ON (cu.exme_pregunta_id = p.exme_pregunta_id)  ").append(" 	INNER JOIN exme_tipopregunta tp ON (p.exme_tipopregunta_id=tp.exme_tipopregunta_id)  ")
				.append(" 	LEFT JOIN exme_pregunta_lista rl ON (p.exme_pregunta_id = rl.exme_pregunta_id AND (p.TipoDato = 'C' OR p.TipoDato = 'O'))  ").append(" 	left JOIN ( select ri.exme_cuestionario_id, ri.preg_condicionante, ri.exme_tipopregunta_id,  ")
				.append(" 	rdti.exme_pregunta_lista_id, rdti.exme_pregunta_id, rdti.respuesta   ").append(" 	from exme_reglacuestionario ri  ").append(" 	INNER join exme_reglacuestionariodt rdti on rdti.exme_reglacuestionario_id = ri.exme_reglacuestionario_id  ").append(" 	) r    ")
				.append(" 	ON (r.exme_cuestionario_id = cu.exme_cuestionario_id  ").append(" 	AND r.preg_condicionante = cu.exme_pregunta_id   ").append(" 	and (  ").append(" 	(r.exme_pregunta_lista_id = rl.exme_pregunta_lista_id AND (p.TipoDato = 'C' OR p.TipoDato = 'O'))  ")
				.append(" 	or (r.exme_pregunta_lista_id is null AND (p.TipoDato <> 'C' OR p.TipoDato <> 'O'))  ").append(" 	)  ").append(" 	)  ").append(" 	left JOIN ( select ri.exme_cuestionario_id, ri.preg_condicionante, ri.exme_tipopregunta_id,  ")
				.append(" 	rdti.exme_pregunta_lista_id, rdti.exme_pregunta_id , rdti.respuesta  ").append(" 	from exme_reglacuestionario ri  ").append(" 	inner join exme_reglacuestionariodt rdti on rdti.exme_reglacuestionario_id = ri.exme_reglacuestionario_id  ")
				.append(" 	) rr ON (cu.exme_cuestionario_id = rr.exme_cuestionario_id AND cu.exme_pregunta_id = rr.exme_pregunta_id)  ").append(" 	LEFT JOIN exme_pregunta_lista lista ON (lista.exme_pregunta_lista_id=r.exme_pregunta_lista_id)  ").append(" 	where cu.exme_cuestionario_id=?")
				.append(" 	and cu.exme_pregunta_id = ?");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, cuestionario.intValue());
			pstmt.setInt(2, pregunta.intValue());
			rs = pstmt.executeQuery();
			Regla_VO reg = null;
			while (rs.next()) {

				reg = new Regla_VO();

				reg.setCuestionarioId(rs.getInt(1));
				reg.setPreguntaId(rs.getInt(2));
				reg.setPregCondicionante(rs.getInt(3));
				reg.setPregModifica(rs.getInt(4));
				reg.setTipoPregunta(rs.getInt(5));
				reg.setBanderaResp(rs.getString(6));
				reg.setListaId(rs.getInt(7));
				reg.setRespLista(rs.getString(8));
				reg.setBanderaResp2(rs.getString(10));
				reg.setBanderaRespLstId(rs.getInt(11));

				lstReglas.add(reg);
			}

		} catch (Exception e) {
			logger.log(Level.WARNING, "", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lstReglas;
	}

	public static List<MEXMEReglaCuestionario> getReglaCuestionario(Properties ctx, long exme_cuestionario_id, String trxName) throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<MEXMEReglaCuestionario> resultados = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			resultados = new ArrayList<MEXMEReglaCuestionario>();

			sql.append("SELECT * FROM EXME_ReglaCuestionario WHERE EXME_Cuestionario_ID = ? ");
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, exme_cuestionario_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEReglaCuestionario resultado = new MEXMEReglaCuestionario(ctx, rs.getInt("EXME_ReglaCuestionario_ID"), null);
				resultados.add(resultado);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "", e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}
		return resultados;
	}

	public MEXMEReglaCuestionario(Properties ctx, int EXME_ReglaCuestionario_ID, String trxName) {
		super(ctx, EXME_ReglaCuestionario_ID, trxName);
	}

	public MEXMEReglaCuestionario(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MEXMEReglaCuestionarioDt getDetalle(int pregList, int EXME_Pregunta_ID) {
		MEXMEReglaCuestionarioDt reglaDt = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM EXME_ReglaCuestionarioDt WHERE EXME_ReglaCuestionario_ID = ?   AND EXME_Pregunta_ID = ? ");
		if (pregList == 0) {
			sql.append("AND EXME_Pregunta_Lista_ID IS NULL ");
		} else {
			sql.append("AND EXME_Pregunta_Lista_ID = ?");
		}

		ResultSet rs = null;
		PreparedStatement stmt = DB.prepareStatement(sql.toString(), null);

		try {

			stmt.setInt(1, getEXME_ReglaCuestionario_ID());
			stmt.setInt(2, EXME_Pregunta_ID);
			if (pregList > 0) {
				stmt.setInt(3, pregList);
			}

			rs = stmt.executeQuery();

			while (rs.next()) {
				reglaDt = new MEXMEReglaCuestionarioDt(getCtx(), rs, null);
			}

		} catch (SQLException e) {
			logger.log(Level.WARNING, "", e);
		} finally {
			DB.close(rs, stmt);

		}

		return reglaDt;
	}

	public boolean hasDetails() {

		boolean res = false;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM EXME_ReglaCuestionarioDt WHERE EXME_ReglaCuestionario_ID = ? ");

		ResultSet rs = null;
		PreparedStatement stmt = DB.prepareStatement(sql.toString(), null);

		try {

			stmt.setInt(1, getEXME_ReglaCuestionario_ID());
			rs = stmt.executeQuery();

			while (rs.next()) {
				res = true;
			}

		} catch (SQLException e) {
			logger.log(Level.WARNING, "", e);
		} finally {
			DB.close(rs, stmt);

		}

		return res;
	}

	/**
	 * Obtiene la regla de la pregunta
	 * 
	 * @param ctx
	 * @param pregId
	 *            Pregunta a buscar
	 * @param trxName
	 * @return Regla de la pregunta, nulo si no tiene
	 */
	public static MEXMEReglaCuestionario get(Properties ctx, int pregId, boolean automatic, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_reglacuestionario ");
		sql.append("WHERE ");
		sql.append("  exme_pregunta_id = ? AND");
		sql.append("  operator ").append(automatic ? "=" : "!=").append(" ? ");
		// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEReglaCuestionario.Table_Name));
		MEXMEReglaCuestionario rule = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pregId);
			pstmt.setString(2, "+");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rule = new MEXMEReglaCuestionario(ctx, rs, null);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return rule;
	}

	/**
	 * Borra los detalles de la regla
	 * 
	 * @param trxName
	 * @return
	 */
	public boolean deleteDetail(String trxName) {
		return deleteDetail(getCtx(), getEXME_ReglaCuestionario_ID(), trxName);
	}

	/**
	 * Borra los detalles de la regla
	 * 
	 * @param ctx
	 * @param ruleId
	 * @param trxName
	 * @return
	 */
	public static boolean deleteDetail(Properties ctx, int ruleId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("  exme_reglacuestionariodt ");
		sql.append("WHERE ");
		sql.append("  exme_reglacuestionario_id = ");
		sql.append(ruleId);

		try {
			DB.executeUpdate(sql.toString(), trxName);
		} catch (Exception e) {
			logger.log(Level.SEVERE, null, e);
			return false;
		}

		return true;

	}

	/**
	 * Obtiene una copia con los detalles de la regla
	 * 
	 * @param trxName
	 * @return
	 */
	public List<MEXMEReglaCuestionarioDt> getCopyDet(String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_reglacuestionariodt ");
		sql.append("WHERE ");
		sql.append("  exme_reglacuestionario_id = ? ");
		// sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEReglaCuestionarioDt.Table_Name));
		sql.append(" order by seqNo");
		List<MEXMEReglaCuestionarioDt> lst = new ArrayList<MEXMEReglaCuestionarioDt>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, getEXME_ReglaCuestionario_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEReglaCuestionarioDt copy = new MEXMEReglaCuestionarioDt(getCtx(), 0, null);
				PO.copyValues(new MEXMEReglaCuestionarioDt(getCtx(), rs, null), copy);
				copy.setFromDB(true);
				lst.add(copy);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

	/**
	 * Obtiene la regla de sumatoria automática
	 * 
	 * @return
	 */
	public String getSumRule() {
		StringBuilder rule = new StringBuilder();
		List<MEXMEReglaCuestionarioDt> dets = getCopyDet(null);
		rule.append("(");
		for (int i = 0; i < dets.size(); i++) {
			MEXMEReglaCuestionarioDt det = dets.get(i);

			MEXMEPregunta p = new MEXMEPregunta(getCtx(), det.getEXME_Pregunta_ID(), null);

			if (MEXMEPregunta.TIPODATO_MultiOptions.equals(p.getTipoDato())) {
				rule.append("#{p").append(det.getEXME_Pregunta_ID()).append("Multi} ");
			} else if (MEXMEPregunta.TIPODATO_OptionList.equals(p.getTipoDato())) {
				rule.append("#{p").append(det.getEXME_Pregunta_ID()).append("List} ");
			} else {
				rule.append("#{p").append(det.getEXME_Pregunta_ID()).append("} ");
			}

			if (i + 1 < dets.size()) {
				rule.append("+").append(" ");
			}
		}
		
		if (dets.size() == 1) {
			rule.append("+ 0) ");
		} else {
			rule.append(") ");
		}
			
		return rule.toString();
	}

	/**
	 * Obtiene la regla para jEval
	 * 
	 * @return
	 */
	public String getRule() {
		StringBuilder rule = new StringBuilder();
		List<MEXMEReglaCuestionarioDt> dets = getCopyDet(null);
		for (int i = 0; i < dets.size(); i++) {
			MEXMEReglaCuestionarioDt det = dets.get(i);
			rule.append("(");
			if (MEXMEPregunta.TIPODATO_MultiOptions.equals(det.getEXME_Pregunta().getTipoDato())) {
				rule.append("multiple(#{p").append(det.getEXME_Pregunta_ID()).append("}, '");
				rule.append(StringUtils.replace(det.getRespuesta(), ",", "|")).append("')");
			} else {
				rule.append("#{p").append(det.getEXME_Pregunta_ID()).append("} ").append(det.getOperator()).append(" ");
				if (MEXMEPregunta.TIPODATO_Decimal.equals(det.getEXME_Pregunta().getTipoDato()) || MEXMEPregunta.TIPODATO_Integer.equals(det.getEXME_Pregunta().getTipoDato())) {
					rule.append(StringUtils.defaultIfEmpty(det.getRespuesta(), "0"));
				} else if (MEXMEPregunta.TIPODATO_OptionList.equals(det.getEXME_Pregunta().getTipoDato())) {
					rule.append(DB.TO_STRING(Integer.toString(det.getEXME_Pregunta_Lista_ID())));
				} else {
					rule.append(DB.TO_STRING(det.getRespuesta()));
				}
			}
			rule.append(") ");
			if (i + 1 < dets.size()) {
				rule.append(getOperator()).append(" ");
			}
		}

		return rule.toString();
	}

}