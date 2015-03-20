package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.form.Category;
import org.compiere.model.form.Question;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

import com.ecaresoft.api.Generic;

/**
 * @author odelarosa
 * 
 */
public class MEXMETipoPregunta extends X_EXME_TipoPregunta {
	private static CLogger s_log = CLogger.getCLogger(MEXMETipoPregunta.class);
	private static final long serialVersionUID = -16808265078015499L;

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMETipoPregunta> get(Properties ctx, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_tipopregunta ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " where ", MEXMETipoPregunta.Table_Name));
		sql.append(" order by name");
		List<MEXMETipoPregunta> list = new ArrayList<MEXMETipoPregunta>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMETipoPregunta(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Obtiene un listado de las categorias del cuestionario
	 * 
	 * @param ctx
	 *            Contexto
	 * @param questionnaireId
	 *            Cuestionario
	 * @param isCopy
	 *            Si es una copia
	 * @return Categorias del cuestionarios
	 */
	public static List<Category> getCategories(Properties ctx, int questionnaireId, boolean isCopy) {
		List<Category> categories = new ArrayList<Category>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_tipopregunta tp ");
		sql.append("WHERE ");
		sql.append("  tp.exme_cuestionario_id = ? AND ");
		sql.append("  tp.isactive = 'Y' ");
		sql.append(" order by tp.SeqNo ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, questionnaireId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				MEXMETipoPregunta tipoPregunta = new MEXMETipoPregunta(ctx, rs, null);
				category.setId(tipoPregunta.getEXME_TipoPregunta_ID());
				category.setName(tipoPregunta.getName());
				category.setRows(tipoPregunta.getNRows());
				category.setColumns(tipoPregunta.getNColumns());
				category.setQuestions(MEXMEPregunta.getQuestions(ctx, tipoPregunta.getEXME_TipoPregunta_ID(), isCopy));
				category.setOrder(tipoPregunta.getSeqNo());
				categories.add(category);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return categories;
	}

	/**
	 * 
	 * @param ctx
	 * @param tipoPregunta
	 * @return
	 */
	public static List<MEXMEPregunta> getQuestions(Properties ctx, int tipoPregunta) {
		List<MEXMEPregunta> questions = new ArrayList<MEXMEPregunta>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_pregunta ");
		sql.append("WHERE ");
		sql.append("  exme_tipopregunta_id = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, tipoPregunta);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				questions.add(new MEXMEPregunta(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return questions;
	}

	private List<Generic> questions = null;

	/**
	 * @param ctx
	 * @param EXME_TipoPregunta_ID
	 * @param trxName
	 */
	public MEXMETipoPregunta(Properties ctx, int EXME_TipoPregunta_ID, String trxName) {
		super(ctx, EXME_TipoPregunta_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETipoPregunta(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	public boolean delete(boolean force, String trxName) {
		boolean retValue = true;
		for (MEXMEPregunta pregunta : getQuestions(getCtx(), getEXME_TipoPregunta_ID())) {
			if (!pregunta.delete(false, trxName)) {
				retValue = false;
				break;
			}
		}
		if (retValue) {
			retValue = super.delete(force, trxName);
		}
		return retValue;
	}

	/**
	 * Obtenemos las preguntas de la categoria que pueden ser usadas para reglas
	 * 
	 * @return
	 */
	public List<Generic> getRuleQuestions() {
		if (questions == null) {
			List<String> lst = new ArrayList<String>();
			lst.add(DB.TO_STRING(MEXMEPregunta.TIPODATO_Decimal));
			lst.add(DB.TO_STRING(MEXMEPregunta.TIPODATO_Integer));
			lst.add(DB.TO_STRING(MEXMEPregunta.TIPODATO_Logial));
			lst.add(DB.TO_STRING(MEXMEPregunta.TIPODATO_OptionList));
			lst.add(DB.TO_STRING(MEXMEPregunta.TIPODATO_Text));
			lst.add(DB.TO_STRING(MEXMEPregunta.TIPODATO_TextArea));
			lst.add(DB.TO_STRING(MEXMEPregunta.TIPODATO_MultiOptions));
			
			questions = getRuleQuestions(lst, true);
		}
		return questions;
	}
	
	/**
	 * Obtenemos las preguntas de la categoria enviadas de parametro
	 * 
	 * @param lst
	 *            Lista de categorias
	 * @param firstBlank
	 *            Espacio en blanco
	 * @return
	 */
	public List<Generic> getRuleQuestions(List<String> lst, boolean firstBlank) {
		List<Generic> questions = new ArrayList<Generic>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  p.value, ");
		sql.append("  p.name, ");
		sql.append("  p.exme_pregunta_id ");
		sql.append("FROM ");
		sql.append("  exme_pregunta p ");
		sql.append("WHERE ");
		sql.append("  p.exme_tipopregunta_id = ? AND ");
		sql.append("  p.tipodato IN (");
		sql.append(StringUtils.join(lst, ","));
		sql.append(")");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPregunta.Table_Name, "p"));
		questions = new ArrayList<Generic>();
		if (firstBlank) {
			questions.add(new Generic(" ", "0"));
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, getEXME_TipoPregunta_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				questions.add(new Generic(rs.getString(1) + " - " +rs.getString(2), rs.getString(3)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return questions;
	}

	/**
	 * Obtener preguntas inactivas de un tipo de Pregunta
	 * 
	 * @param ctx
	 *            Contexto de App
	 * @param id
	 *            Id del tipo de pregunta
	 * @param trxName
	 *            trxName
	 * @return Preguntas Inactivas
	 */
	public static List<Question> getInactiveQuestions(Properties ctx, int id, String trxName) {
		List<Question> lst = new ArrayList<Question>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_pregunta ");
		sql.append("WHERE ");
		sql.append("  Exme_Tipopregunta_Id = ? AND ");
		sql.append("  Isactive = 'N' ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(MEXMEPregunta.fromPO(ctx, new MEXMEPregunta(ctx, rs, null), false));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lst;
	}

}
